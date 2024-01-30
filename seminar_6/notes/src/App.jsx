import axios from 'axios';
import './App.css';
import React, { useState, useEffect } from 'react';
import NewNote from './components/NewNote';
import TableView from './components/TableView';


const App = () => {

  const [items, setItems] = useState([]);
  useEffect(() => {
    axios.get('http://localhost:8080/api/notes')
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
        setItems(data);
      })
  }, []);

  const appendNote = (title, description) => {

    const temp = {
      title: title,
      description: description,
    };

    const url = 'http://localhost:8080/api/notes';
    axios.post(url, temp)
      .then(() => {
        setItems([...items, temp]);
      });
  }

  const removeNote = (id) => {
    const url = `http://localhost:8080/api/notes/delete/${id}`;
    axios.delete(url);
    setItems(items.filter(item => item.id !== id));
  }

  return (
    <div className='container mt-5'>
      <div className='card'>
        <div className='card-header'>
          <h1>Заметки</h1>
        </div>
        <div className='card-body'>
          <TableView data={items} removeNote={removeNote} />
          <NewNote appendNote={appendNote} />
        </div>
      </div>
    </div>
  );
}

export default App;
