export class SignupDto {
    login: string;
    password: string;
    email: string;

    constructor(login: string, password: string, email: string) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
}