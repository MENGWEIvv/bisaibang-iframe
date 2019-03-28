import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IIframe } from 'app/shared/model/iframe.model';

type EntityResponseType = HttpResponse<IIframe>;
type EntityArrayResponseType = HttpResponse<IIframe[]>;

@Injectable({ providedIn: 'root' })
export class IframeService {
    public resourceUrl = SERVER_API_URL + 'api/iframes';

    constructor(protected http: HttpClient) {}

    create(iframe: IIframe): Observable<EntityResponseType> {
        return this.http.post<IIframe>(this.resourceUrl, iframe, { observe: 'response' });
    }

    update(iframe: IIframe): Observable<EntityResponseType> {
        return this.http.put<IIframe>(this.resourceUrl, iframe, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IIframe>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IIframe[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
