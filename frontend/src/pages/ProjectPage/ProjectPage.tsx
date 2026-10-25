import { useNavigate } from "react-router";
import { useEffect, useState } from "react";
import axios from "axios";
import { ApiEndpoints } from "../../service/RESTservice";
import "./ProjectPage.css";
import NoteWindow from "../../components/NoteWindow/NoteWindow";
import { projectData } from "../../data/ProjectData";
import { observer } from "mobx-react-lite";
import { userData, type User } from "../../data/userData";


interface Note {
    id: number;
    title: string;
    content: string;
}

interface Project {
    notes: Note[]
    users: User[]
}

const ProjectPage = observer(() => {
    const navigate = useNavigate();
    const projectId = projectData.project?.id;
    const [selectedNote, setSelectedNote] = useState<Note | null>(null);
    const [newNote, setNewNote] = useState<string>("")

    const handleCreateNote = async () => {
        try {
            const response = await axios.post(
                `${ApiEndpoints.notePost}/${projectId}`,
                {
                    title: newNote
                }, {
                    headers: {
                        Authorization: `Bearer ${userData.user?.token}`
                    }
                }
            );

            const createdNote = response.data;

            projectData.project?.notes.push(createdNote);
            setSelectedNote(createdNote);

            setNewNote("");

            } catch (error) {
                console.error("Failed to create note:", error);
            }
        };

    useEffect(() => {
        // const loadNotes = async () => {
        //     try {
        //         const response = await axios.get(
        //             `${ApiEndpoints.projectGET}/${projectId}/notes`, 
        //             {
        //             headers: {
        //                 Authorization: `Bearer ${userData.user?.token}`
        //             }
        //         }
        //         );
        //         console.log(response.data)
        //         projectData.setProjectNotes(response.data);

        //         if (response.data.length > 0) {
        //             setSelectedNote(response.data[0]);
        //         }
        //     } catch (error) {
        //         console.error("Failed to load notes:", error);
        //     }
        // };

        const loadProject = async () => {
            try {
                const response = await axios.get(
                    `${ApiEndpoints.projectGET}/${projectId}`, 
                    {
                    headers: {
                        Authorization: `Bearer ${userData.user?.token}`
                    }
                }
                );
                let project : Project = response.data
                projectData.setProjectNotes(project.notes);
                projectData.setProjectUsers(project.users);

                if (response.data.length > 0) {
                    setSelectedNote(response.data[0]);
                }
            } catch (error) {
                console.error("Failed to load notes:", error);
            }
        }

        if (projectId) {
            loadProject();
        }
    }, [projectId]);

    return (
        <div className="project-page">

            <header className="project-header">
                <button
                    className="back-button"
                    onClick={() => navigate("/main")}
                >
                    ←
                </button>

                <h1>{projectData.project?.name ?? "Project"}</h1>

                <div className="invite-link">
                    <span>Invite:</span>
                    <span className="invite-link-value">
                        {projectData.project?.inviteLink}
                    </span>
                </div>
                <div className="project-users">
                    <span className="project-users-title">Members</span>

                    <div className="project-users-list">
                        {projectData.project?.users?.map(user => (
                            <div className="project-user">
                                <div className="user-avatar">
                                    {user.login.charAt(0).toUpperCase()}
                                </div>
                                <span className="user-login">{user.login}</span>
                            </div>
                        ))}
                    </div>
                </div>
            </header>

            <div className="project-content">

                <aside className="notes-sidebar">

                    <div className="notes-header">
                        <h2>Notes</h2>
                        <input
                            type="text"
                            value={newNote}
                            onChange={(event) => setNewNote(event.target.value)}
                        />
                        <button className="new-note-button" onClick = {handleCreateNote}>
                            +
                        </button>
                    </div>

                    <div className="notes-list">
                        {projectData.project?.notes?.map(note => (
                            <button
                                key={note.id}
                                className={
                                    selectedNote?.id === note.id
                                        ? "note-item active"
                                        : "note-item"
                                }
                                onClick={() => setSelectedNote(note)}
                            >
                                <span className="note-icon">📝</span>
                                <span>{note.title}</span>
                            </button>
                        ))}
                    </div>

                </aside>

                <main className="note-area">
                    {selectedNote ? (
                    <NoteWindow
                        key={selectedNote.id}
                        note={selectedNote}
                        onSave={() => {}}
                    />
                    ) : (
                        <div className="empty-note">
                            <div className="empty-note-icon">📝</div>
                            <h2>No note selected</h2>
                            <p>
                                Select a note from the sidebar or create a new one.
                            </p>
                        </div>
                    )}
                </main>

            </div>

        </div>
    );
});

export default ProjectPage;