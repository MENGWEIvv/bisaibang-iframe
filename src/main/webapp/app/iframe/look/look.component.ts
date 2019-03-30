import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
//import { LookService } from './look.component.service'
@Component({
    selector: 'jhi-look',
    templateUrl: './look.component.html',
    styleUrls: ['./look.component.scss']
})
export class LookComponent implements OnInit {
    constructor(private route: ActivatedRoute) {}
    iframeList: any[] = [];
    getStageList: String[] = [];
    getTimeList: String[] = [];
    getGroupList: String[] = [];
    teamList: any[] = [];
    nowStage: string;
    nowTime: string;
    nowGrouop: string;
    // index=0;
    list: object[] = [
        {
            stage: '第一天 ',
            times: [
                {
                    time: '1第一组',
                    groups: [
                        {
                            group: '777'
                        },
                        {
                            group: '2222'
                        }
                    ]
                },
                {
                    time: '1第3组',
                    groups: [
                        {
                            group: '333333333'
                        }
                    ]
                }
            ]
        },
        {
            stage: '第一天 ',
            times: [
                {
                    time: '1第一组',
                    groups: [
                        {
                            group: '1111111111111'
                        }
                    ]
                },
                {
                    time: '1第3组',
                    groups: [
                        {
                            group: '333333333'
                        }
                    ]
                }
            ]
        }
    ];
    ngOnInit() {
        // this.route.params.subscribe(params => { this.lookService.getiframe(params.name)})
    }
}
