import { Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { controlRoute } from './control/control.route';

const IFRAME_ROUTES = [controlRoute];

export const iframeState: Routes = [
    {
        path: '',
        data: {
            authorities: []
        },
        canActivate: [UserRouteAccessService],
        children: IFRAME_ROUTES
    }
];
