import { Component, OnInit } from '@angular/core';
import { User } from './user';
import { UserService } from './user.service';
import { Router } from '@angular/router';
import { TokenStorageService } from '../auth/token-storage.service';

@Component({
    selector: 'app-user',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
    constructor(private userService: UserService, private tokenStorage: TokenStorageService) { }
    users: User[];
    private user: User;
    form: any = {};
    private authority: string;

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
        if (this.tokenStorage.getToken()) {
            this.tokenStorage.getAuthorities().every(role => {
                if (role === 'ROLE_ADMIN') {
                    this.authority = 'admin';
                    return false;
                } else if (role === 'ROLE_USER') {
                    this.authority = 'user';
                    return false;
                }
                this.authority = 'user';
                return true;
            });
        }
    }

    addUser() {
        var id = this.form.id;
        var updateResult;
        this.user = new User(
            this.form.login, this.form.name, this.form.email);
        if (id == null) {
            updateResult = this.userService.addUser(this.user);
        } else {
            updateResult = this.userService.updateUser(id, this.user);
        }
        updateResult.subscribe(response => {
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
