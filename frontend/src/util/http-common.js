import axios from "axios";

export default axios.create({
    baseURL: "https://bonzina-api.herokuapp.com/"
});