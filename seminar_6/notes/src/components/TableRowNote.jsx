const TableRowNote = (props) => {

    return (
      <tr onClick={() => { props.removeNote(props.note.id); }}>
        {/* <th scope='row'>{props.contact.id}</th> */}
        <th scope='row'>{props.index}</th>
        <th>{props.note.title}</th>
        <th>{props.note.description}</th>
      </tr>
    );
  }
  
  export default TableRowNote;