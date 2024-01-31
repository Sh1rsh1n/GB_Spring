import http from "./http-common"

const getAll = () => {
    return http.get("/notes");
};

const get = id => {
    return http.get(`/notes/${id}`);
};

const create = data => {
    return http.post("/notes", data);
};

const update = (data) => {
    return http.put(`/notes/save`, data);
};

const remove = id => {
    return http.get(`/notes/delete/${id}`);
};

const NoteService = {
    getAll,
    get,
    create,
    update,
    remove
};

export default NoteService;