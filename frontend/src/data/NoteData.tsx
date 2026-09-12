import { makeAutoObservable } from "mobx/src/internal.js"

export interface Note {
    id: number
    title: string
    content: string
}

class NoteData {
    constructor() {
        makeAutoObservable(this)
    }

    public note : Note | undefined

    public setData(data : any) {
        this.note = data
    }


}

let noteData = new NoteData()

export default noteData