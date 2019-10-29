import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {UserComponent} from './users/user.component';
import { UserService } from './users/user.service';
import { HttpModule } from '@angular/http';

@NgModule({
  declarations: [
    AppComponent, UserComponent
  ],
  imports: [
    BrowserModule, HttpModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
