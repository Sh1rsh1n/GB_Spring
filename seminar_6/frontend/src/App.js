import './App.css';
import axios from 'axios';
import React, { useState, useEffect } from 'react';
import FormNewNote from './components/FormNewNote';
import NoteTableView from './components/NoteTableView';

const BASE_URL = 'http://localhost:8080/api/notes';

const App = () => {

  const [notes, setNotes] = useState([]);
  useEffect(() => {
    axios.get(BASE_URL)
       .then(res => {
        const data = [];

        res.data._embedded.notes.forEach(item => {
          data.push(
            {
              id: item.id,
              title: item.title,
              description: item.description,
            }
          )
        })
        setNotes(data);
      })
  }, []);

  const appendNote = (title, description) => {

    const temp = {
      // id: currentId,
      title: title,
      description: description,
    };

    axios.post(BASE_URL, temp)
    .then(e => {
      temp.id = e.data.id;
      setNotes([...notes, temp]);
    });
  }

  const removeNote = (id) => {
    axios.get(BASE_URL + '/delete/{id}');
    setNotes(notes.filter(note => note.id !== id));
  }

  return (
    <div className='container mt-5'>
      <div className='card'>
        <div className='card-header'>
          <h1>Note List</h1>
        </div>
        <div className='card-body'>
          <NoteTableView data={notes} removeNote={removeNote} />
          <FormNewNote appendNote={appendNote} />
        </div>
      </div>
    </div>
  );
}

export default App;
