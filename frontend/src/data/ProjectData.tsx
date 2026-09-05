import {makeAutoObservable} from 'mobx'

export interface Project {
    name: string
}

class ProjectData {
    constructor() {
        makeAutoObservable(this)
    }

    public project: Project | undefined

    setProjectData(data: any) {
        this.project = data
    }
}

let projectData = new ProjectData();

export {projectData}