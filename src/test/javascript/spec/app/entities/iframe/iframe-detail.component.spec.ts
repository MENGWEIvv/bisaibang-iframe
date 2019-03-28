/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RaceTestModule } from '../../../test.module';
import { IframeDetailComponent } from 'app/entities/iframe/iframe-detail.component';
import { Iframe } from 'app/shared/model/iframe.model';

describe('Component Tests', () => {
    describe('Iframe Management Detail Component', () => {
        let comp: IframeDetailComponent;
        let fixture: ComponentFixture<IframeDetailComponent>;
        const route = ({ data: of({ iframe: new Iframe(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [RaceTestModule],
                declarations: [IframeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(IframeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(IframeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.iframe).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
