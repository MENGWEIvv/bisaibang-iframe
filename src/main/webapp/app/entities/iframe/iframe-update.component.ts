import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IIframe } from 'app/shared/model/iframe.model';
import { IframeService } from './iframe.service';

@Component({
    selector: 'jhi-iframe-update',
    templateUrl: './iframe-update.component.html'
})
export class IframeUpdateComponent implements OnInit {
    iframe: IIframe;
    isSaving: boolean;

    constructor(protected iframeService: IframeService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ iframe }) => {
            this.iframe = iframe;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.iframe.id !== undefined) {
            this.subscribeToSaveResponse(this.iframeService.update(this.iframe));
        } else {
            this.subscribeToSaveResponse(this.iframeService.create(this.iframe));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IIframe>>) {
        result.subscribe((res: HttpResponse<IIframe>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
