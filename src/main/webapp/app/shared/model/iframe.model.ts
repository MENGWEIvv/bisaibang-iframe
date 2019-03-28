export interface IIframe {
    id?: number;
    iframe?: string;
    name?: string;
    stage?: string;
    time?: string;
    group?: string;
    flag?: number;
    raceId?: number;
}

export class Iframe implements IIframe {
    constructor(
        public id?: number,
        public iframe?: string,
        public name?: string,
        public stage?: string,
        public time?: string,
        public group?: string,
        public flag?: number,
        public raceId?: number
    ) {}
}
