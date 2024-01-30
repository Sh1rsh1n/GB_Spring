import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.min.css";

class App extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <a href="/api/notes" className="navbar-brand">
            NOTES
          </a>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/api/notes"} className="nav-link">
                All NOTES
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/api/new_note"} className="nav-link">
                Create NOTES
              </Link>
            </li>
          </div>
        </nav>

        <div className="container mt-3">
          <Routes>
            <Route path="/" element={<NotesList/>} />
            <Route path="/api/notes" element={<NotesList/>} />
            <Route path="/api/new_note" element={<AddNotes/>} />
            <Route path="/api/note/:id" element={<Note/>} />
          </Routes>
        </div>
      </div>
    );
  }
}

export default App;