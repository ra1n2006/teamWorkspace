import { useEffect, useState, type FC } from "react"
import { userData } from "../../data/userData"
import ProjectCard from "../../UI/ProjectCard/ProjectCard"
import "../WorkspaceMenu/WorkspaceMenu.css"
import CreateButton from "../../UI/CreateButton/CreateButton"
import axios from "axios"
import { observer } from "mobx-react-lite";
import { ApiEndpoints } from "../../service/RESTservice"
import { useNavigate } from "react-router"


const WorkspaceMenu = observer((navigateFunction) => {
    const [projectName, setProjectName] = useState("")
    let navigate = useNavigate()
    useEffect(() => {
        const loadProjects = async () => {
        try {
            const response = await axios.get(
                ApiEndpoints.userGET,
                {
                    headers: {
                        Authorization: `Bearer ${userData.user?.token}`
                    }
                }
            )
            console.log(response.data.projects)
            userData.setProjects(response.data.projects)
        } catch (error) {
            console.error("Failed to load projects:", error)
        }
    }

    loadProjects()
    }, [userData.user?.token])
    return (
        <section>
            <main className="home-page">

            <header className="home-header">
                <div>
                    <h1>Привет, {userData.user?.login}</h1>
                    <p>Projects you're currently working on</p>
                </div>
                <input 
                    type="text"
                    value={projectName}
                    onChange={(event) => setProjectName(event.target.value)}
                />
                <CreateButton title={projectName}/>
            </header>

            <section className="workspace-grid">

                {userData.user?.projects?.map(el => <ProjectCard name={el.name} navFn={() => navigate("/project")} id = {el.id}/>)}

                <button className="workspace-card create-card">
                    <span className="create-icon">+</span>
                    <span>Create workspace</span>
                </button>

            </section>
        </main>
        </section>
    )
})

export default WorkspaceMenu