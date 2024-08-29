import { Routes } from '@angular/router';
import { MainMenuComponent } from './main-menu/main-menu.component';
import { MovieViewingScreenComponent } from './movie-viewing-screen/movie-viewing-screen.component';

export const routes: Routes = [
  { path: 'mainMenu', component: MainMenuComponent },
  
  {path: '', redirectTo: 'mainMenu', pathMatch: 'full'},
  /*{path: '**', redirectTo: 'mainMenu', pathMatch: 'full'},*/
  { path: 'viewing', component: MovieViewingScreenComponent /* Yeni ekran için rota}*/}]; 
