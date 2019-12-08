export class User {
    id: number;
    login: string;
    email: string;
    name: string;
    birthdate: Date;

    constructor(login: string, name: string, email: string, birthdate: Date) {
        this.login = login;
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
    }
}