import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { RaceSharedModule } from 'app/shared';
import {
    IframeComponent,
    IframeDetailComponent,
    IframeUpdateComponent,
    IframeDeletePopupComponent,
    IframeDeleteDialogComponent,
    iframeRoute,
    iframePopupRoute
} from './';

const ENTITY_STATES = [...iframeRoute, ...iframePopupRoute];

@NgModule({
    imports: [RaceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [IframeComponent, IframeDetailComponent, IframeUpdateComponent, IframeDeleteDialogComponent, IframeDeletePopupComponent],
    entryComponents: [IframeComponent, IframeUpdateComponent, IframeDeleteDialogComponent, IframeDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RaceIframeModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
