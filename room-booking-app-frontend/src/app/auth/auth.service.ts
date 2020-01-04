import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginDto } from './login-dto';
import { JwtResponseDto } from './jwt-response-dto';
import { SignupDto } from './sign-up-dto';


const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'}),
    withCredentials: true
};

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private loginUrl = 'http://localhost:8090/room-booking-app-backend/auth/login';
    private signuoUrl = 'http://localhost:8090/room-booking-app-backend/auth/signup';

    constructor(private http: HttpClient) {
    }

    authenticate(credentials: LoginDto): Observable<JwtResponseDto> {
        return this.http.post<JwtResponseDto>(this.loginUrl, credentials, httpOptions);
    }

    signUp(info: SignupDto): Observable<any> {
        return this.http.post<any>(this.signuoUrl, info, httpOptions);
    }
}
