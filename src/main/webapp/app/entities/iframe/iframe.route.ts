import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Iframe } from 'app/shared/model/iframe.model';
import { IframeService } from './iframe.service';
import { IframeComponent } from './iframe.component';
import { IframeDetailComponent } from './iframe-detail.component';
import { IframeUpdateComponent } from './iframe-update.component';
import { IframeDeletePopupComponent } from './iframe-delete-dialog.component';
import { IIframe } from 'app/shared/model/iframe.model';

@Injectable({ providedIn: 'root' })
export class IframeResolve implements Resolve<IIframe> {
    constructor(private service: IframeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IIframe> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Iframe>) => response.ok),
                map((iframe: HttpResponse<Iframe>) => iframe.body)
            );
        }
        return of(new Iframe());
    }
}

export const iframeRoute: Routes = [
    {
        path: '',
        component: IframeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'raceApp.iframe.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: IframeDetailComponent,
        resolve: {
            iframe: IframeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'raceApp.iframe.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: IframeUpdateComponent,
        resolve: {
            iframe: IframeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'raceApp.iframe.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: IframeUpdateComponent,
        resolve: {
            iframe: IframeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'raceApp.iframe.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const iframePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: IframeDeletePopupComponent,
        resolve: {
            iframe: IframeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'raceApp.iframe.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
