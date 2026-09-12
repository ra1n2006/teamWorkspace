import {type FC} from "react"
import { projectData } from "../../data/ProjectData"

export interface Props {
    name: string
    navFn: () => void
    id: number
}
const ProjectCard: FC<Props> = ({name, navFn, id}) => {
    return (
        <section className="workspace-card" onClick={() =>  {
            projectData.setProjectData({id: id})
            navFn()}
        }
        >
            <section className="workspace-card-header">
                <section>{name}</section>
                <button className="workspace-menu">•••</button>
            </section>

            <article className="workspace-description">
                Collaborative workspace for the TeamWorkspace project.
            </article>

            <article className="workspace-info">
                <span>👥 4 members</span>
                <span>Updated 2h ago</span>
            </article>
        </section>
    )
}

export default ProjectCard