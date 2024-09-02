import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { MainMenuComponent } from './app/main-menu/main-menu.component';
import { routes } from './app/app.routes';
import { importProvidersFrom } from '@angular/core';
<<<<<<< HEAD
import { HttpClient, HttpClientModule } from '@angular/common/http';
=======
import { HttpClientModule } from '@angular/common/http';
>>>>>>> 3f2a6fe8126504c2e5bb501a097f41f7cfdf6b7f
import { BrowserModule } from '@angular/platform-browser';

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),
<<<<<<< HEAD
    importProvidersFrom(FormsModule, HttpClientModule, BrowserModule),
=======
    importProvidersFrom(FormsModule, HttpClientModule, BrowserModule), // HttpClient yerine HttpClientModule kullanın
>>>>>>> 3f2a6fe8126504c2e5bb501a097f41f7cfdf6b7f
  ]
})
.catch((err) => console.error(err));
