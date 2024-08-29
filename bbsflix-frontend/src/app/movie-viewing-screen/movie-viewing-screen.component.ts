import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-movie-viewing-screen',
  standalone: true,
  imports: [],
  templateUrl: './movie-viewing-screen.component.html',
  styleUrls: ['./movie-viewing-screen.component.css']
})
export class MovieViewingScreenComponent {

  constructor(private router: Router) {}

  goBack() {
    this.router.navigate(['/']); // Ana sayfaya yönlendirme
  }
}
