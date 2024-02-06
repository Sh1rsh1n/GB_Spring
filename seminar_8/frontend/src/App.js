import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import {Link, Route, Routes} from "react-router-dom";
import NoteList from "./components/NoteList";
import AddNote from "./components/AddNote";
import Note from "./components/Note";

const App = () => {
    return (
        <div>
            <nav className="navbar navbar-expand navbar-light bg-light">
                <a href="/" className="navbar-brand">
                    МОИ ЗАМЕТКИ
                </a>
                <div className="navbar-nav mr-auto">
                    <li className="nav-item">
                        <Link to={"/notes"} className="nav-link">
                            Все заметки
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to={"/add"} className="nav-link">
                            Добавить заметку
                        </Link>
                    </li>
                </div>
            </nav>

            <div className="container mt-3">
                <Routes>
                    <Route path="/" element={<NoteList/>} />
                    <Route path="/notes" element={<NoteList/>} />
                    <Route path="/add" element={<AddNote/>} />
                    <Route path="/notes/:id" element={<Note/>} />
                </Routes>
            </div>
        </div>
    );
}

export default App;