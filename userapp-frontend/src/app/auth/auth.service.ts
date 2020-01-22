import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginDto } from './login-dto';
import { JwtResponseDto } from './jwt-response-dto';
import { SignupDto } from './sign-up-dto';

const lOGIN_URL = 'http://localhost:8090/userapp-backend/auth/login';
const SIGNUP_URL = 'http://localhost:8090/userapp-backend/auth/signup';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
};

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    constructor(private http: HttpClient) {
    }

    authenticate(credentials: LoginDto): Observable<JwtResponseDto> {
        return this.http.post<JwtResponseDto>(lOGIN_URL, credentials, httpOptions);
    }

    signUp(info: SignupDto): Observable<any> {
        return this.http.post<any>(SIGNUP_URL, info, httpOptions);
    }

}
