import {makeAutoObservable} from 'mobx'
import { type Note } from './NoteData'

export interface Project {
    name: string
    id : number
    notes: Note[]
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
}

let projectData = new ProjectData();

export {projectData}