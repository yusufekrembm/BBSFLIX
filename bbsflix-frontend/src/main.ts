import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { MainMenuComponent } from './app/main-menu/main-menu.component';
import { routes } from './app/app.routes';
import { importProvidersFrom } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),
    importProvidersFrom(FormsModule, HttpClient, BrowserModule),
  ]
})
.catch((err) => console.error(err));
