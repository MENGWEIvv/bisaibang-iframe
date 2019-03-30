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
        let arr = this.treeToPath(this.nodeList, [], []);
        let arrNode = [];
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
            this.iframeService.query().subscribe(list => {
                this.iframeList = list.body;
            }, console.log);
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
                this.iframeList.forEach(unit => {
                    if (unit.iframe === item.name) {
                        unit.forEach(it => {
                            if (it.id === item.id) {
                                it.name = this.updateName;
                                it.stage = this.updateStage;
                                it.time = this.updateTime;
                                it.group = this.updateGroup;
                                it.flag = this.updateFlag;
                                it.raceId = this.updateRaceId;
                            }
                        });
                    }
                });
            }, console.log);
    }
    delIframe(id, name) {
        this.iframeService.delete(id).subscribe(res => {
            this.iframeList.forEach(unit => {
                if (unit.iframe === name) {
                    unit.forEach((it, index) => {
                        if (it.id === id) {
                            unit.splice(index, 1);
                            return;
                        }
                    });
                }
            });
        }, console.log);
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
            .subscribe(res => {
                this.iframeList.forEach(unit => {
                    if (unit.iframe === item.name) {
                        unit.forEach(it => {
                            if (it.id === item.id) {
                                it.flage = 1;
                            }
                        });
                    }
                });
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
