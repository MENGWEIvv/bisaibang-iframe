import { Component, OnInit, ViewChild } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { IframeService } from 'app/entities/iframe';
// import { NzDemoTreeLineComponent } from './tree/tree.component';

@Component({
    selector: 'jhi-control',
    templateUrl: './control.component.html',
    styleUrls: ['./control.component.scss']
})
export class ControlComponent implements OnInit {
    [x: string]: any;
    // @ViewChild(NzDemoTreeLineComponent)
    // private treeComponent: NzDemoTreeLineComponent;
    constructor(private modalService: NgbModal, private iframeService: IframeService) {}

    ngOnInit() {
        this.loadAll();
    }

    open(content, item) {
        this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' });
        if (item) {
            this.id = item.id;
            this.updateName = item.name;
            this.updateStage = item.stage;
            this.updateTime = item.tTime;
            this.updateGroup = item.group;
            this.uodateFlag = item.flag;
            this.updateRaceId = item.raceId;
        }
    }

    addIframe() {
        // this.iframeService.create().subscribe(res => {
        //     let con = res.body;
        // }, console.log);
        // console.log(this.addName);
        // console.log(this.addStage);
        // console.log(this.addTime);
        // console.log(this.addGroups);
    }
    updateIframe(item) {
        this.iframeService
            .update({
                id: item.id,
                name: this.updateName,
                stage: this.updateStage,
                time: this.updateTime,
                group: this.updateGroup,
                flag: this.updateFlag,
                raceId: this.updateRaceId
            })
            .subscribe(res => {}, console.log);
    }
    delIframe(id) {
        this.iframeService.delete(id).subscribe(res => {}, console.log);
    }

    defaultIframe(item) {
        this.iframeService
            .update({
                id: item.id,
                name: item.name,
                stage: item.ustage,
                time: item.time,
                group: item.group,
                flag: 1,
                raceId: item.raceId
            })
            .subscribe(res => {}, console.log);
    }

    loadAll() {
        // this.iframeService.query().subscribe(res => {
        //     this.iframeList = res.body;
        // }, console.log);
        this.iframeList = [
            {
                iframe: 'qqqqq',
                data: [
                    {
                        name: 'fghr',
                        stage: '1111',
                        time: '2222',
                        group: '3333',
                        flag: 0,
                        raceId: 1111,
                        id: 1
                    },
                    {
                        name: 'fghr',
                        stage: '1111',
                        time: '2222',
                        group: '3333',
                        flag: 0,
                        id: 2,
                        raceId: 1111
                    },
                    {
                        name: 'fghr',
                        stage: '1111',
                        time: '2222',
                        group: '3333',
                        flag: 0,
                        id: 3,
                        raceId: 1111
                    }
                ]
            },
            {
                iframe: 'bbbbbb',
                data: [
                    {
                        stage: '1111',
                        time: '2222',
                        group: '3333',
                        flag: 0,
                        raceId: 1111
                    },
                    {
                        flag: 0,
                        stage: '1111',
                        time: '2222',
                        raceId: 1111,
                        group: '3333'
                    },
                    {
                        stage: '1111',
                        time: '2222',
                        group: '3333',
                        flag: 0,
                        raceId: 1111
                    }
                ]
            }
        ];
    }
}
