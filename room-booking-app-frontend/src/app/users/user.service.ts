
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';5
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './user';
import { map} from 'rxjs/operators';


@Injectable()
export class UserService {

    private usersUrl = 'http://localhost:8090/room-booking-app-backend/ui/users';

    constructor(private httpClient: HttpClient) { }

    getAllUsers() {
        return this.httpClient.get(this.usersUrl);
    }

    private handleError(error: Response) {
        return Observable.throw(error);
    }
}