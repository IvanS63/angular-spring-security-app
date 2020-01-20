
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'; 5
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './user';
import { map } from 'rxjs/operators';

const BASE_URL = 'http://localhost:8090/userapp-backend/';
const GET_ALL_USERS_URL = 'ui/users/list';
const DELETE_USER_URL = 'ui/users/delete/';
const ADD_USER_URL = 'ui/users/add';
const EDIT_USER_URL = 'ui/users/edit/';
const UPLOAD_FILE = 'api/files/upload';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
};

@Injectable()
export class UserService {

    constructor(private httpClient: HttpClient) { }

    getAllUsers() {
        return this.httpClient.get(BASE_URL + GET_ALL_USERS_URL);
    }

    addUser(user: User) {
        return this.httpClient.post<User>(BASE_URL + ADD_USER_URL, user, httpOptions);
    }

    updateUser(id: string, user: User) {
        return this.httpClient.put<User>(BASE_URL + EDIT_USER_URL + id, user, httpOptions);
    }

    deleteUser(id: number): Observable<any> {
        return this.httpClient.delete(BASE_URL + DELETE_USER_URL + id);
    }

    uploadFile(formData: FormData) {
        return this.httpClient.post<FormData>(BASE_URL + UPLOAD_FILE, formData);
    }

    private handleError(error: Response) {
        return Observable.throw(error);
    }
}