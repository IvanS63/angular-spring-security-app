
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'; 5
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './user';
import { map } from 'rxjs/operators';

const USERS_BASE_URL = 'http://localhost:8090/room-booking-app-backend/ui/users';
const GET_ALL_USERS_URL = '/list';
const DELETE_USER_URL = '/delete/';

@Injectable()
export class UserService {

    constructor(private httpClient: HttpClient) { }

    getAllUsers() {
        return this.httpClient.get(USERS_BASE_URL + GET_ALL_USERS_URL);
    }

    deleteUser(id: number): Observable<any> {
        return this.httpClient.delete(USERS_BASE_URL + DELETE_USER_URL + id);
    }

    private handleError(error: Response) {
        return Observable.throw(error);
    }
}