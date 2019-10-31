export class JwtResponseDto {
    jwt: string;
    login: string;
    authorities: string[];

    constructor(jwt: string, login: string, authorities: string[]) {
        this.jwt = jwt;
        this.login = login;
        this.authorities = authorities;
    }
}