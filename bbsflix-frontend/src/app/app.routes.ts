import { Routes } from '@angular/router';
import { MainMenuComponent } from './main-menu/main-menu.component'
import { HttpClient } from '@angular/common/http';

export const routes: Routes = [
  { path: 'mainMenu', component: MainMenuComponent },
  
  {path: '', redirectTo: 'mainMenu', pathMatch: 'full'},
  {path: '**', redirectTo: 'mainMenu', pathMatch: 'full'}];
