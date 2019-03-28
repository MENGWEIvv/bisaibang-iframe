/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { RaceTestModule } from '../../../test.module';
import { IframeUpdateComponent } from 'app/entities/iframe/iframe-update.component';
import { IframeService } from 'app/entities/iframe/iframe.service';
import { Iframe } from 'app/shared/model/iframe.model';

describe('Component Tests', () => {
    describe('Iframe Management Update Component', () => {
        let comp: IframeUpdateComponent;
        let fixture: ComponentFixture<IframeUpdateComponent>;
        let service: IframeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [RaceTestModule],
                declarations: [IframeUpdateComponent]
            })
                .overrideTemplate(IframeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(IframeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IframeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Iframe(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.iframe = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Iframe();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.iframe = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
