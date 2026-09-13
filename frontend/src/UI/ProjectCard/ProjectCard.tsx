import {type FC} from "react"
import { projectData, type Project } from "../../data/ProjectData"

export interface Props {
    project: Project | undefined
    navFn: () => void
}

const ProjectCard: FC<Props> = ({project, navFn}) => {
    return (
        <section
            className="workspace-card"
            onClick={() => {
                projectData.setProjectData(project)
                navFn()
            }}
        >
            <section className="workspace-card-header">
                <section>{project!.name}</section>
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