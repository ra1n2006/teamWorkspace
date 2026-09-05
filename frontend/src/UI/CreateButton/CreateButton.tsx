import axios from "axios"
import { ApiEndpoints } from "../../service/RESTservice"
import { type FC } from "react"
import { projectData } from "../../data/ProjectData"
import { userData } from "../../data/userData"

export interface Props {
    title: string
}

const CreateButton: FC<Props> = ({title}) => {

    const handleClick = async () => {
        console.log(userData.user?.token)
        const response = await axios.post(
        ApiEndpoints.project,
        { title: title },
        {
            headers: {
                Authorization: `Bearer ${userData.user?.token}`
            }
        }
)
        console.log(response.data)
        projectData.setProjectData(response.data)
    }
    return (
        <button className="create-workspace" onClick={() => handleClick()}>
            + Create workspace
        </button>
    )
}

export default CreateButton