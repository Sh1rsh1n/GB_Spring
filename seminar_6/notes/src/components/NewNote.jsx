import React, { useState } from "react";

const NewNote = (props) => {

  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');

  const submit = () => {

    props.appendNote(title, description);
    setTitle('');
    setDescription('');
  }

  return (
    <div className='mt-3'>
      <form>
        <div className='mb-3'>
          <label className='form-label'>Title</label>
          <input
            className='form-control'
            type='text'
            value={title}
            onChange={(e) => { setTitle(e.target.value); }}
          />
        </div>
        <div className='mb-3'>
          <label className='form-label'>Description</label>
          <textarea
            className='form-control'
            rows={4}
            value={description}
            onChange={(e) => { setDescription(e.target.value); }}
          />
        </div>
        <div>

          <button
            type='button'
            className='btn btn-primary'
            onClick={submit}
          >
            New note
          </button>
        </div>
      </form>
    </div>
  );
}

export default NewNote;