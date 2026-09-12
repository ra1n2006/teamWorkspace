import { useState, type FC } from "react";

interface Note {
    id: number;
    title: string;
    content: string;
}

interface Props {
    note: Note;
    onSave: (note: Note) => void;
}

const NoteWindow: FC<Props> = ({ note, onSave }) => {

    const [title, setTitle] = useState(note.title);
    const [content, setContent] = useState(note.content);

    const handleSave = () => {
        onSave({
            ...note,
            title,
            content
        });
    };

    return (
        <div className="note-window">

            <div className="note-toolbar">

                <input
                    className="note-title"
                    value={title}
                    onChange={(event) => setTitle(event.target.value)}
                    placeholder="Note title"
                />

                <button
                    className="save-note-button"
                    onClick={handleSave}
                >
                    Save
                </button>

            </div>

            <textarea
                className="note-content"
                value={content}
                onChange={(event) => setContent(event.target.value)}
                placeholder="Write something..."
            />

        </div>
    );
};

export default NoteWindow;