import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { LoginDto } from '../auth/login-dto';
import { AuthService } from '../auth/auth.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { CookieService } from 'ngx-cookie-service';
import { TranslateService } from '@ngx-translate/core';

const LANG_COOKIE_KEY = "lang";

@Component({
    encapsulation: ViewEncapsulation.None,
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    form: any = {};
    isLoggedIn = false;
    isLoginFailed = false;
    errorMessage = '';
    roles: string[] = [];
    private loginDto: LoginDto;

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

    onSubmit() {
        console.log(this.form);

        this.loginDto = new LoginDto(
            this.form.username,
            this.form.password);

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

    reloadPage() {
        window.location.reload();
    }

    navigateToDashboard() {
        this.router.navigateByUrl("users");
    }
}
