import { NgModule } from '@angular/core';

import { ControlComponent } from './control/control.component';
import { RouterModule } from '@angular/router';
import { iframeState } from 'app/iframe/iframe.route';
import { RaceSharedModule } from 'app/shared';
import { LookComponent } from './look/look.component';
import { NzDemoTreeLineComponent } from './control/tree/tree.component';
import { NgZorroAntdModule } from 'ng-zorro-antd';

@NgModule({
    declarations: [ControlComponent, NzDemoTreeLineComponent, LookComponent],
    imports: [RaceSharedModule, RouterModule.forChild(iframeState), NgZorroAntdModule]
})
export class IframeModule {}
