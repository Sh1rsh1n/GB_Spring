import React from 'react'
import TableRowNote from './TableRowNote';

const TableView = (props) => {

  return (
    <table className='table table-hover'>
      <thead>
        <tr>
          <th scope='col'>№</th>
          <th scope='col'>Title</th>
          <th scope='col'>Description</th>
        </tr>
      </thead>
      <tbody>
        {
          props.data.map((item, index) => (
            <TableRowNote
              key={item.id}
              removeNote={props.removeNote}
              note={item}
              index={index + 1}
            />
          ))
        }
      </tbody>
    </table>
  );
}

export default TableView;