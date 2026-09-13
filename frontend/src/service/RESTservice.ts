export class ApiEndpoints {
    static base : string = "http://localhost:8080"
    static authPOST: string = this.base + "/api/auth"
    static userGET: string = this.base + "/api/main"
    static project: string = this.base + "/api/new"
    static projectGET: string = this.base + "/api/project"
    static notePost: string = this.base + "/api/project/newNote"
    static projectPUT: string = this.base + "/api/project/join"
}