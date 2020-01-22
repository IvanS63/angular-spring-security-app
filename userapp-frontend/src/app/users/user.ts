export class User {
    id: number;
    login: string;
    email: string;
    name: string;
    photo: string;

    constructor(login: string, name: string, email: string) {
        this.login = login;
        this.name = name;
        this.email = email;
    }
}