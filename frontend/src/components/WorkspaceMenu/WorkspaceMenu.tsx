import { useEffect } from "react"
import { userData } from "../../data/userData"

const WorkspaceMenu = () => {
    useEffect(() => {

    }, [])
    return (
        <section>
            {userData.user?.login}
            {userData.user?.token}
        </section>
    )
}

export default WorkspaceMenu