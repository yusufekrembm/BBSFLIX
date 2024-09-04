import { Routes } from '@angular/router';
import { MainMenuComponent } from './main-menu/main-menu.component'
import { HttpClient } from '@angular/common/http';
import { WatchMoviesComponent } from './watch-movies/watch-movies.component';

export const routes: Routes = [
  { path: 'mainMenu', component: MainMenuComponent },
  { path: 'watchMovies', component: WatchMoviesComponent},
  { path: 'watchMovies/:id', component: WatchMoviesComponent },
  
  
  {path: '', redirectTo: 'mainMenu', pathMatch: 'full'},
  {path: '**', redirectTo: 'mainMenu', pathMatch: 'full'}];
