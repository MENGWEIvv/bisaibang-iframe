import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IIframe } from 'app/shared/model/iframe.model';
import { IframeService } from './iframe.service';

@Component({
    selector: 'jhi-iframe-delete-dialog',
    templateUrl: './iframe-delete-dialog.component.html'
})
export class IframeDeleteDialogComponent {
    iframe: IIframe;

    constructor(protected iframeService: IframeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.iframeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'iframeListModification',
                content: 'Deleted an iframe'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-iframe-delete-popup',
    template: ''
})
export class IframeDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ iframe }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(IframeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.iframe = iframe;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/iframe', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/iframe', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
