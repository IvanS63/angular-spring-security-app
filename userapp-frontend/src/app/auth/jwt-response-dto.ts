export class JwtResponseDto {
    token: string;
    login: string;
    authorities: string[];

    constructor(token: string, login: string, authorities: string[]) {
        this.token = token;
        this.login = login;
        this.authorities = authorities;
    }
}