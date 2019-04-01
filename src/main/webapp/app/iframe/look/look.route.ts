import { Route } from '@angular/router';
import { LookComponent } from './look.component';
import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
export const lookRoute: Route = {
    path: 'look/:name',
    component: LookComponent,
    data: {
        pageTitle: 'look'
    }
};
