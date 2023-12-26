import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenService } from '../services/token.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
    //dang ky interceptior trong module
    constructor(private tokenService: TokenService) { }

    intercept(
        req: HttpRequest<any>,
        next: HttpHandler): Observable<HttpEvent<any>> {
        //lay token de xu ly them  
        const token = this.tokenService.getToken();
        if (token) {
            //nhan bao doi tuong req
            req = req.clone({
                setHeaders: {
                    Authorization: `Bearer ${token}`,
                },
            });
        }
        //don re quest roi di tiep
        return next.handle(req);
    }

}