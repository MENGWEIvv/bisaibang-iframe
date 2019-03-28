/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RaceTestModule } from '../../../test.module';
import { IframeDeleteDialogComponent } from 'app/entities/iframe/iframe-delete-dialog.component';
import { IframeService } from 'app/entities/iframe/iframe.service';

describe('Component Tests', () => {
    describe('Iframe Management Delete Component', () => {
        let comp: IframeDeleteDialogComponent;
        let fixture: ComponentFixture<IframeDeleteDialogComponent>;
        let service: IframeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [RaceTestModule],
                declarations: [IframeDeleteDialogComponent]
            })
                .overrideTemplate(IframeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(IframeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IframeService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
