import { Routes } from '@angular/router';
import { MainMenuComponent } from './main-menu/main-menu.component';

export const routes: Routes = [
  { path: 'mainMenu', component: MainMenuComponent },
  
  {path: '', redirectTo: 'mainMenu', pathMatch: 'full'},
  {path: '**', redirectTo: 'mainMenu', pathMatch: 'full'}];
