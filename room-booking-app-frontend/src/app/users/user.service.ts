import { Http, Response, RequestOptions } from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';
import { map} from 'rxjs/operators';

@Injectable()
export class UserService {
    constructor(private _httpService: Http) { }

    getAllUsers(): Observable<User[]> {
        return this._httpService.get("http://localhost:8090/room-booking-app-backend/ui/users")
            .pipe(map((response: Response) => response.json()));
    }

    private handleError(error: Response) {
        return Observable.throw(error);
    }
}