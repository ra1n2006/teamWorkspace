import axios from "axios";
import { useState } from "react";
import { ApiEndpoints } from "../../service/RESTservice";
import { projectData } from "../../data/ProjectData";
import { userData } from "../../data/userData";

const JoinProject = () => {
    const [link, setLink] = useState("");

    const handleJoin = async () => {
        let response = await axios.put(ApiEndpoints.projectPUT, {
            token: link
        },
            {
                headers: {
                    Authorization: `Bearer ${userData.user?.token}`
                }
            })
        userData.addProject(response.data)
    };

    return (
        <div>
            <input
                type="text"
                value={link}
                onChange={(event) => setLink(event.target.value)}
                placeholder="Paste invite link"
            />

            <button onClick={handleJoin}>
                Join
            </button>
        </div>
    );
};

export default JoinProject;