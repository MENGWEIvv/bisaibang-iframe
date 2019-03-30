import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { IIframe } from 'app/shared/model/iframe.model';

Injectable({ providedIn: 'root' });
export class LookService {
    public resourceUrl = SERVER_API_URL + 'api';

    constructor(protected http: HttpClient) {}
    getiframe(iframe: IIframe): Observable<HttpResponse<IIframe[]>> {
        return this.http.post<IIframe[]>(this.resourceUrl, iframe, { observe: 'response' });
    }
}
