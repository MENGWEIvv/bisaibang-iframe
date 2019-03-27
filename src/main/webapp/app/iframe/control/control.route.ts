import { Route } from '@angular/router';
import { ControlComponent } from './control.component';

export const controlRoute: Route = {
    path: 'control',
    component: ControlComponent,
    data: {
        pageTitle: '控制台'
    }
};
