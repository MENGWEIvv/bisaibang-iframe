import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { IIframe } from 'app/shared/model/iframe.model';

@Injectable({ providedIn: 'root' })
export class LookService {
    public resourceUrl = SERVER_API_URL + 'api/iframeList';

    constructor(protected http: HttpClient) {}
    getiframe(name: string): Observable<HttpResponse<IIframe[]>> {
        return this.http.get<IIframe[]>(`${this.resourceUrl}/${name}`, { observe: 'response' });
    }
}
