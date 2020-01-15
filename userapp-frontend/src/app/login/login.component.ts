import { Component, OnInit, ViewEncapsulation, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { LoginDto } from '../auth/login-dto';
import { AuthService } from '../auth/auth.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { CookieService } from 'ngx-cookie-service';
import { TranslateService } from '@ngx-translate/core';
import { SignupDto } from '../auth/sign-up-dto';
import { NgForOf } from '@angular/common';
import { NgForm } from '@angular/forms';

const LANG_COOKIE_KEY = "lang";

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    @ViewChild('closebutton', { static: false }) closebutton;
    isLoggedIn = false;
    isLoginFailed = false;
    isSignupFailed = false;
    errorMessage = '';
    roles: string[] = [];
    private loginDto: LoginDto;
    private signupDto: SignupDto;

    constructor(private authService: AuthService,
        private tokenStorage: TokenStorageService,
        private router: Router,
        private cookieService: CookieService,
        private translateService: TranslateService) {
        if (cookieService.get(LANG_COOKIE_KEY)) {
            translateService.setDefaultLang(cookieService.get(LANG_COOKIE_KEY));
            translateService.use(cookieService.get(LANG_COOKIE_KEY));
        } else {
            translateService.setDefaultLang('en');
            translateService.use('en');
            cookieService.set(LANG_COOKIE_KEY, "en");
        }
    }

    ngOnInit() {
        if (this.tokenStorage.getToken()) {
            this.isLoggedIn = true;
            this.roles = this.tokenStorage.getAuthorities();
            this.navigateToDashboard();
        }
    }

    onSubmit(loginForm: NgForm) {
        this.loginDto = new LoginDto(
            loginForm.value.username,
            loginForm.value.password);

        this.authService.authenticate(this.loginDto).subscribe(
            data => {
                this.tokenStorage.saveToken(data.token);
                this.tokenStorage.saveLogin(data.login);
                this.tokenStorage.saveAuthorities(data.authorities);

                this.isLoginFailed = false;
                this.isLoggedIn = true;
                this.roles = this.tokenStorage.getAuthorities();
                this.navigateToDashboard();
            },
            error => {
                console.log(error);
                this.errorMessage = error.error.message;
                this.isLoginFailed = true;
            }
        );
    }

    changeLanguage(locale: string) {
        this.cookieService.set(LANG_COOKIE_KEY, locale);
        this.translateService.use(locale);
        this.translateService.setDefaultLang(locale);
    }

    signup(signupForm: NgForm) {
        this.signupDto = new SignupDto(
            signupForm.value.signupLogin,
            signupForm.value.signupPassword,
            signupForm.value.signupEmail
        )
        this.authService.signUp(this.signupDto).subscribe(
            data => {
                console.log(data);
                this.isSignupFailed = false;
                this.closebutton.nativeElement.click();
            },
            error => {
                this.isSignupFailed = true;
                this.errorMessage = error.error.message;
                console.log(error);
            }
        )
    }

    reloadPage() {
        window.location.reload();
    }

    navigateToDashboard() {
        this.router.navigateByUrl("users");
    }
}
