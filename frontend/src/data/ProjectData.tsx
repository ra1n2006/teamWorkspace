import {makeAutoObservable} from 'mobx'
import { type Note } from './NoteData'
import type { User } from './userData'

export interface Project {
    name: string
    id : number
    notes: Note[]
    users: User[]
    inviteLink: string
    usersNum: number
}

class ProjectData {
    constructor() {
        makeAutoObservable(this)
    }

    public project: Project | undefined

    setProjectData(data: any) {
        this.project = data
    }

    setProjectNotes(data: any) {
        this.project!.notes = data
    }

    setProjectUsers(data: any) {
        this.project!.users = data
    }
}

let projectData = new ProjectData();

export {projectData}