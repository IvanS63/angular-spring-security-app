import { Component, OnInit } from '@angular/core';
import { User } from './user';
import { UserService } from './user.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-user',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
    constructor(private userService: UserService) { }
    users: User[];
    private user: User;
    form: any = {};

    getUsers(): void {
        this.userService.getAllUsers()
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

    addUser() {
        this.user = new User(
            this.form.login, this.form.name, this.form.email);
        this.userService.addUser(this.user)
            .subscribe(response => {
                window.location.reload();
                console.log(response);
            },
                (error) => { console.log(error); }
            );
    }

    deleteUser(id: number) {
        this.userService.deleteUser(id)
            .subscribe(
                data => {
                    console.log(data);
                    this.userService.getAllUsers().subscribe((data: User[]) => {
                        this.users = data
                    })
                }, error =>
                console.error(error));
    }

}
