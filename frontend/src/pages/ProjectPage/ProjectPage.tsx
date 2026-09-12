import { useNavigate } from "react-router";
import { useEffect, useState } from "react";
import axios from "axios";
import { ApiEndpoints } from "../../service/RESTservice";
import "./ProjectPage.css";
import NoteWindow from "../../components/NoteWindow/NoteWindow";
import { projectData } from "../../data/ProjectData";
import { observer } from "mobx-react-lite";
import { userData } from "../../data/userData";


interface Note {
    id: number;
    title: string;
    content: string;
}

interface Project {
    id: number;
    title: string;
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
        console.log(userData.user?.token)
        const loadNotes = async () => {
            try {
                const response = await axios.get(
                    `${ApiEndpoints.projectGET}/${projectId}/notes`, 
                    {
                    headers: {
                        Authorization: `Bearer ${userData.user?.token}`
                    }
                }
                );
                
                projectData.setProjectNotes(response.data);

                if (response.data.length > 0) {
                    setSelectedNote(response.data[0]);
                }
            } catch (error) {
                console.error("Failed to load notes:", error);
            }
        };

        if (projectId) {
            loadNotes();
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
                        <NoteWindow note={selectedNote} onSave={() => {}}/>
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