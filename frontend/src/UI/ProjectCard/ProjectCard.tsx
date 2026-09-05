import {type FC} from "react"

export interface Props {
    name: string
}
const ProjectCard: FC<Props> = ({name}) => {
    return (
        <section className="workspace-card">
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