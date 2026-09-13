import axios from "axios"
import { ApiEndpoints } from "../../service/RESTservice"
import { type FC } from "react"
import { userData } from "../../data/userData"

export interface Props {
    title: string
}

const CreateButton: FC<Props> = ({title}) => {

    const handleClick = async () => {
        try {
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


            userData.addProject(response.data)
        } catch (error) {
            console.error("Failed to create project:", error)
        }
    }

    return (
        <button
            className="create-workspace"
            onClick={handleClick}
        >
            + Create workspace
        </button>
    )
}

export default CreateButton