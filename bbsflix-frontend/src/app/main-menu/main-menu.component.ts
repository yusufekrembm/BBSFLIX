import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common'; 
import { Router } from '@angular/router';
import { FrontService } from '../front.service';

@Component({
  selector: 'app-main-menu',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.css']
})
export class MainMenuComponent implements OnInit {
  searchQuery: string = ''; 
  selectedFilter: string[] = []; 
  filterOptions: string[] = []; 
  showFilterOptions: boolean = false; 
  movies: any[] = [];

  constructor(private frontService: FrontService, private router: Router) {}

  ngOnInit(): void {
    this.showMovies(); 
  }


  onSearch() {
    const filters: any = { title: this.searchQuery };
    // Extract filter values
    this.selectedFilter.forEach(filter => {
      const [key, value] = filter.split(':');
      if (key && value) {
        filters[key] = value;
      }
    });
  
    console.log('Filters:', filters);
  
    this.frontService.filterAndOrderMovies(filters).subscribe({
      next: (data) => {
        if (data) {
          this.movies = data;
          console.log('Filtered Movies:', this.movies);
        } else {
          console.error('No movies found for the given query.');
        }
      },
      error: (err) => {
        console.error('Error filtering movies:', err);
      }
    });
  }
  
  

  selectFilter(option: string) {
    const parts = option.split(':');
    const key = parts[0];
    const value = parts[1];
  
    if (key === 'genre') {
      const genreId = this.getGenreId(value);
      this.selectedFilter = [`genreId:${genreId}`];
    } else {
    }
  
    this.showFilterOptions = false;
  }


  showMovies() {
    this.frontService.getAllMovies().subscribe({
      next: (data) => {
        if (data && data.results) {
          this.movies = data.results;
          console.log(this.movies);
        } else {
          console.error('Unexpected data format:', data);
        }
      },
      error: (err) => {
        console.error('Error fetching movies:', err);
      }
    });
  }

  viewMovieDetails(id: string): void {
    this.router.navigate(['/watchMovies', id]);
  }

  getImageUrl(path: string): string {
    return `https://image.tmdb.org/t/p/w500${path}`;
  }

  getGenreId(genreName: string): number | null {
    const genreMap: { [key: string]: number } = {
      'Action': 28,
      'Adventure': 12,
      'Animation': 16,
      'Comedy': 35,
      'Crime': 80,
      'Documentary': 99,
      'Drama': 18,
      'Family': 10751,
      'Fantasy': 14,
      'History': 36,
      'Horror': 27,
      'Music': 10402,
      'Mystery': 9648,
      'Romance': 10749,
      'Science Fiction': 878,
      'TV Movie': 10770,
      'Thriller': 53,
      'War': 10752,
      'Western': 37
    };

    return genreMap[genreName] || null;
  }

  clearFilters() {
    this.selectedFilter = [];
    this.searchQuery = '';
    this.showMovies();
  }
}
