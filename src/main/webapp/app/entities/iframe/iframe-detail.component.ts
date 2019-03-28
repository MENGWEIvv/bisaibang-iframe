import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IIframe } from 'app/shared/model/iframe.model';

@Component({
    selector: 'jhi-iframe-detail',
    templateUrl: './iframe-detail.component.html'
})
export class IframeDetailComponent implements OnInit {
    iframe: IIframe;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ iframe }) => {
            this.iframe = iframe;
        });
    }

    previousState() {
        window.history.back();
    }
}
