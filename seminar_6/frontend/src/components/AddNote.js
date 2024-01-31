import React, { useState } from "react";
import NoteService from "../service/NoteService";
import { useNavigate } from "react-router-dom";

const AddNote = () => {

    const initialNoteState = {
        id: null,
        title: "",
        description: "",
    };

    const navigate = useNavigate();
    const [note, setNote] = useState(initialNoteState);
    const [submitted, setSubmitted] = useState(false);

    const handleInputChange = event => {
        const { name, value } = event.target;
        setNote({ ...note, [name]: value });
    };

    const saveNote = () => {
        let data = {
            title: note.title,
            description: note.description
        };

        NoteService.create(data)
            .then(response => {
                setNote({
                    id: response.data.id,
                    title: response.data.title,
                    description: response.data.description,
                });
                setSubmitted(true);
                navigate("/notes")
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

    // const newNote = () => {
    //     setNote(initialNoteState);
    //     setSubmitted(false);
    // };

    return (
        <div className="submit-form">
            {submitted ? (
                <div>
                    <h3>Заметка успешно добавлена</h3>
                </div>
            ) : (
                <div>
                    <div className="form-group">
                        <label htmlFor="title">Название</label>
                        <input
                            type="text"
                            className="form-control"
                            id="title"
                            required
                            value={note.title}
                            onChange={handleInputChange}
                            name="title"
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="description">Описание</label>
                        <input
                            type="text"
                            className="form-control"
                            id="description"
                            required
                            value={note.description}
                            onChange={handleInputChange}
                            name="description"
                        />
                    </div>

                    <button onClick={saveNote} className="btn btn-success">
                        Добавить
                    </button>
                </div>
            )}
        </div>
    );
};

export default AddNote;