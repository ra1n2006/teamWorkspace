import {makeAutoObservable} from 'mobx'
import type { Project } from './ProjectData'

export interface User {
    login: string
    token: string | undefined
    projects: Project[]
}

class UserData {
    constructor() {
        makeAutoObservable(this)
    }

    public user: User | undefined

    setUserData(data: any) {
        this.user = {
        ...data,
        projects: data.projects ?? []
    }
    }

    setProjects(projects: Project[]) {
        if (this.user) {
            this.user.projects = projects
        }
    }

    addProject(project: Project) {
        if (this.user) {
            this.user.projects.push(project)
        }
    }
}

let userData = new UserData();

export {userData}