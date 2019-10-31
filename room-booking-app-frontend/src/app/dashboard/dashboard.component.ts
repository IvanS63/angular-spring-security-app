import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../auth/token-storage.service';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
  })
  export class DashboardComponent implements OnInit {
    info: any;
 
    constructor(private token: TokenStorageService) { }

    ngOnInit() {
        this.info = {
          jtw: this.token.getToken(),
          login: this.token.getLogin(),
          authorities: this.token.getAuthorities()
        };
      }
     
      logout() {
        this.token.signOut();
        window.location.reload();
      }
  }