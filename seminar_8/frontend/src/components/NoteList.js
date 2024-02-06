import React, { useState, useEffect } from "react";
import NoteService from "../service/NoteService";
import { Link } from "react-router-dom";

const NoteList = () => {
    const [notes, setNotes] = useState([]);
    const [currentNote, setCurrentNote] = useState(null);
    const [currentIndex, setCurrentIndex] = useState(-1);

    useEffect(() => {
        retrieveNotes();
    }, []);

    const retrieveNotes = () => {
        NoteService.getAll()
            .then(response => {
                setNotes(response.data);
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

    const setActiveNote = (note, index) => {
        setCurrentNote(note);
        setCurrentIndex(index);
    };

    return (
        <div className="list row">
            <div className="col-md-6">
                <h4>Список заметок</h4>

                <ul className="list-group">
                    {notes &&
                        notes.map((note, index) => (
                            <li
                                className={
                                    "list-group-item " + (index === currentIndex ? "active" : "")
                                }
                                onClick={() => setActiveNote(note, index)}
                                key={index}
                            >
                                {note.title}
                            </li>
                        ))}
                </ul>
            </div>
            <div className="col-md-6">
                {currentNote ? (
                    <div>
                        <h4>Заметка</h4>
                        <div>
                            <label>
                                <strong>Название:</strong>
                            </label>{" "}
                            {currentNote.title}
                        </div>
                        <div>
                            <label>
                                <strong>Описание:</strong>
                            </label>{" "}
                            {currentNote.description}
                        </div>
                        <Link
                            to={"/notes/" + currentNote.id}
                            className="badge text-bg-warning"
                        >
                            Редактировать
                        </Link>
                    </div>
                ) : (
                    <div>
                        <br />
                        <p>Выберите любую заметку</p>
                    </div>
                )}
            </div>
        </div>
    );
};

export default NoteList;