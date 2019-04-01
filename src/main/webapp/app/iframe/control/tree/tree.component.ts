import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { NzFormatEmitEvent } from 'ng-zorro-antd';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'jhi-tree-line',
    template: `
        <nz-tree [nzData]="nodes" nzShowExpand="false">
            <ng-template #nzTreeTemplate let-node>
                <span>{{ node.title }}</span>
                <a (click)="showModel(edit)" class="text-primary" *ngIf="node.level !== 0">edit</a>
                <a (click)="showModel(add)" class="text-primary" *ngIf="node.level !== 3">add</a>
                <a (click)="delNode(node)" class="text-primary">del</a>
                <ng-template #edit let-modal>
                    <div class="modal-header">
                        <h4 class="modal-title" id="modal-basic-title">编辑title</h4>
                        <button type="button" class="close" aria-label="Close" (click)="modal.dismiss('Cross click')">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <input [(ngModel)]="editValue" />
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" (click)="modal.close('Save click')" (click)="saveEdit(node)">
                            Save
                        </button>
                    </div>
                </ng-template>
                <ng-template #add let-modal>
                    <div class="modal-header">
                        <h4 class="modal-title" id="modal-basic-title">添加子节点</h4>
                        <button type="button" class="close" aria-label="Close" (click)="modal.dismiss('Cross click')">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <input [(ngModel)]="addValue" />
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" (click)="modal.close('Save click')" (click)="saveAdd(node)">
                            Save
                        </button>
                    </div>
                </ng-template>
            </ng-template>
        </nz-tree>
    `
})
export class NzDemoTreeLineComponent implements OnInit {
    @Input() name;
    nodes = [];
    editValue = '';
    addValue = '';
    @Output() private outer = new EventEmitter();
    constructor(private modalService: NgbModal) {}
    nzEvent(event: NzFormatEmitEvent): void {
        console.log(event);
    }
    ngOnInit(): void {
        this.nodes = [
            {
                title: this.name,
                expanded: true,
                key: '0',
                children: []
            }
        ];
    }
    showModel(content) {
        this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' });
    }
    saveEdit(node) {
        node.title = this.editValue;
        this.editValue = '';
        this.outer.emit(this.nodes);
    }
    saveAdd(node) {
        node.addChildren([
            {
                title: this.addValue,
                expanded: true,
                key: node.key + '-' + node.children.length,
                children: []
            }
        ]);
        this.addValue = '';
        this.outer.emit(this.nodes);
    }
    delNode(node) {
        node.remove();
        this.outer.emit(this.nodes);
    }
}
