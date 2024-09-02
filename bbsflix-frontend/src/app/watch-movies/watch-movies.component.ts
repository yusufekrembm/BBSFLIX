import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FrontService } from '../front.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-watch-movies',
  standalone: true,
  imports: [CommonModule], // CommonModule'ı buraya ekleyin
  templateUrl: './watch-movies.component.html',
  styleUrls: ['./watch-movies.component.css']
})
export class WatchMoviesComponent implements OnInit {
  movie: any;

  constructor(private route: ActivatedRoute, private frontService: FrontService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.getMovieDetails(id);
      }
    });
  }

  getMovieDetails(id: string): void {
    this.frontService.getMovieById(id).subscribe({
      next: (data) => {
        this.movie = data;
      },
      error: (err) => {
        console.error('Error fetching movie details:', err);
      }
    });
  }

  getImageUrl(path: string): string {
    return `https://image.tmdb.org/t/p/w500${path}`;
  }
}
