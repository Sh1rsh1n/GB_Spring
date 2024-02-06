import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from 'react-router-dom';
import NoteService from "../service/NoteService";

const Note = props => {
    const { id }= useParams();
    let navigate = useNavigate();

    const initialNoteState = {
        id: null,
        title: "",
        description: "",
    };
    const [currentNote, setCurrentNote] = useState(initialNoteState);

    const getNote = id => {
        NoteService.get(id)
            .then(response => {
                setCurrentNote(response.data);
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

    useEffect(() => {
        if (id)
            getNote(id);
    }, [id]);

    const handleInputChange = event => {
        const { name, value } = event.target;
        setCurrentNote({ ...currentNote, [name]: value });
    };

    const updateNote = () => {
        NoteService.update(currentNote)
            .then(response => {
                navigate("/notes");
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

    const deleteNote = () => {
        NoteService.remove(currentNote.id)
            .then(response => {
                console.log(response.data);
                navigate("/notes");
            })
            .catch(e => {
                console.log(e);
            });
    };

    return (
        <div>
            {currentNote ? (
                <div className="edit-form">
                    <h4>Заметка</h4>
                    <form>
                        <div className="form-group">
                            <label htmlFor="title">Название</label>
                            <input
                                type="text"
                                className="form-control"
                                id="title"
                                name="title"
                                value={currentNote.title}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="description">Описание</label>
                            <input
                                type="text"
                                className="form-control"
                                id="description"
                                name="description"
                                value={currentNote.description}
                                onChange={handleInputChange}
                            />
                        </div>
                    </form>

                    <button className="badge text-bg-danger mr-2" onClick={deleteNote}>
                        Удалить
                    </button>

                    <button type="submit" className="badge text-bg-success" onClick={updateNote}>Редактировать</button>
                </div>
            ) : (
                <div>
                    <br />
                    <p>Выберите заметку</p>
                </div>
            )}
        </div>
    );
};

export default Note;