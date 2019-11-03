import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../auth/token-storage.service';
import { User } from '../users/user';
import { UserService } from '../users/user.service';
import { JwtResponseDto } from '../auth/jwt-response-dto';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  users: User[];
  authentification: JwtResponseDto;

  constructor(private userService: UserService, private tokenService: TokenStorageService) { }
  
  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(): void {
    this.userService.getAllUsers()
      .subscribe((data: User[]) => {
        this.users = data,
          console.log(data)
      }, error => {
        console.error(error);
      });
  }

  getToken(): void {
    this.authentification.token = this.tokenService.getToken();
  }

  logout() {
    this.tokenService.signOut();
    window.location.reload();
  }
}