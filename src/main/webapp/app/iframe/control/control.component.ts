import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { IframeService } from 'app/entities/iframe';

@Component({
    selector: 'jhi-control',
    templateUrl: './control.component.html',
    styleUrls: ['./control.component.scss']
})
export class ControlComponent implements OnInit {
    [x: string]: any;
    showErr = false;
    constructor(private modalService: NgbModal, private iframeService: IframeService) {}
    ngOnInit() {
        this.loadAll();
    }
    inputChange(event) {
        const exp = /^[A-Za-z]+$/;
        exp.test(event) ? (this.showErr = false) : (this.showErr = true);
    }
    open(content, item) {
        this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' });
        if (item) {
            this.id = item.id;
            this.updateName = item.name;
            this.updateStage = item.stage;
            this.updateTime = item.time;
            this.updateGroup = item.group;
            this.updateFlag = item.flag;
            this.updateRaceId = item.raceId;
        }
    }
    addIframe() {
        this.iframeName = '';
        const arr = this.treeToPath(this.nodeList, [], []);
        const arrNode = [];
        arr.forEach(item => {
            arrNode.push({
                name: item[0],
                stage: item[1] || '',
                time: item[2] || '',
                group: item[3] || '',
                flag: 0,
                raceId: 0
            });
        });
        this.iframeService.create(arrNode).subscribe(res => {
            this.iframeList = res.body;
        }, console.log);
    }
    treeToPath(tree, path, currentPath) {
        for (let i = 0; i < tree.length; i++) {
            if (i !== 0) {
                currentPath.pop();
            }
            currentPath.push(tree[i].title);
            if (tree[i].children.length) {
                this.treeToPath(tree[i].children, path, currentPath);
            } else {
                path.push(currentPath.slice(0));
            }
        }
        currentPath.pop();
        return path;
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
            .subscribe(res => {
                this.iframeList = res.body;
            }, console.log);
    }
    delIframe(id, name) {
        this.iframeService.delete(id).subscribe(res => {
            this.iframeList = res.body;
        }, console.log);
    }
    defaultIframe(item) {
        this.iframeService
            .update({
                id: item.id,
                name: item.name,
                stage: item.stage,
                time: item.time,
                group: item.group,
                flag: 1,
                raceId: item.raceId
            })
            .subscribe(res => {
                this.iframeList = res.body;
            }, console.log);
    }
    loadAll() {
        this.iframeService.query().subscribe(res => {
            this.iframeList = res.body;
        }, console.log);
    }
    runParent(nodes) {
        this.nodeList = nodes;
    }
}
