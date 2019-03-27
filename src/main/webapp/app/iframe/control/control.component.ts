import { Component, OnInit } from '@angular/core';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'jhi-control',
    templateUrl: './control.component.html',
    styles: []
})
export class ControlComponent implements OnInit {
    iframeList: object[];
    addStage: String;
    addTime: String;
    addGroup: String;

    constructor(private modalService: NgbModal) {}

    ngOnInit() {
        this.addStage = '';
        this.addTime = '';
        this.addGroup = '';
        this.loadAll();
    }

    open(content) {
        this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' });
    }

    addIframe() {
        console.log(1111111111);
        this.addStage = '';
        this.addTime = '';
        this.addGroup = '';
        console.log(this.addStage);
    }

    loadAll() {
        this.iframeList = [
            {
                iframe: 'qqqqq',
                data: [
                    {
                        stage: '1111',
                        time: '2222',
                        group: '3333',
                        default: true,
                        raceid: 1111
                    },
                    {
                        stage: '1111',
                        time: '2222',
                        group: '3333',
                        default: true,
                        raceid: 1111
                    },
                    {
                        stage: '1111',
                        time: '2222',
                        group: '3333',
                        default: true,
                        raceid: 1111
                    }
                ]
            },
            {
                iframe: 'qqqqq',
                data: [
                    {
                        stage: '1111',
                        time: '2222',
                        group: '3333',
                        default: true,
                        raceid: 1111
                    },
                    {
                        stage: '1111',
                        time: '2222',
                        group: '3333'
                    },
                    {
                        stage: '1111',
                        time: '2222',
                        group: '3333'
                    }
                ]
            }
        ];
    }
}
