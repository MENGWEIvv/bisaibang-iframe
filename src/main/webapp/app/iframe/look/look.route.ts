import { Route } from '@angular/router';
import { LookComponent } from './look.component';

export const lookRoute: Route = {
    path: 'look',
    component: LookComponent,
    data: {
        pageTitle: 'look'
    }
};
