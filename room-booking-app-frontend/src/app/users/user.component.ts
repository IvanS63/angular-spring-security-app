import { Component, OnInit } from '@angular/core';
import { User } from './user';
import { UserService } from './user.service';

@Component({
    selector: 'app-user',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
    constructor(private _userService: UserService) { }
    users: User[];

    getUsers(): void {
        this._userService.getAllUsers()
            .subscribe((data: User[]) => {
                this.users = data,
                    console.log(data)
            }, error => {
                console.error(error);
            });
    }

    ngOnInit(): void {
        this.getUsers();
    }

}