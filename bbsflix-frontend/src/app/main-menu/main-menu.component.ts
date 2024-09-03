import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common'; 
import { Router } from '@angular/router';
import { FrontService } from '../front.service';
import { ResultsEntity } from '../results-entity.model';

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
  movies: ResultsEntity[] = [];  // Uygun türde tanımladık
  title: string = '';
  language: string = '';
  releaseDate: string = '';
  genreId: number | undefined;
  sortBy: string = 'title';
  ascending: boolean = true;
  showFilterOptions: boolean = false; 

  // Filtre seçenekleri
  genres: string[] = [
    'Action', 'Adventure', 'Animation', 'Comedy', 'Crime', 'Documentary',
    'Drama', 'Family', 'Fantasy', 'History', 'Horror', 'Music', 'Mystery',
    'Romance', 'Science Fiction', 'TV Movie', 'Thriller', 'War', 'Western'
  ];
  
  languages: string[] = [
    'English', 'Spanish', 'French', 'German', 'Chinese', 'Turkish'
  ];
  
  releaseYears: string[] = [
    '2024', '2023', '2022', '2021', '2020', '2019'
  ];

  constructor(private frontService: FrontService, private router: Router) {}

  ngOnInit(): void {
    this.showMovies(); 
  }

  onSearch() {
    const filters: any = { title: this.searchQuery };
    this.selectedFilter.forEach(filter => {
      const [key, value] = filter.split(':');
      if (key && value) {
        filters[key] = value;
      }
    });
  
    this.frontService.filterAndOrderMovies(filters).subscribe({
      next: (data) => {
        if (data) {
          this.movies = data;
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
      if (genreId !== null) {
        this.updateOrAddFilter('genreId', genreId.toString());
      }
    } else if (key === 'language') {
      const languageCode = this.getLanguage(value);
      if (languageCode !== null) {
        this.updateOrAddFilter('language', languageCode);
      }
    } else if (key === 'releaseDate') {
      this.updateOrAddFilter('releaseDate', value);
    } else if (key === 'sortBy') {
      this.sortBy = value;
      this.onFilterChange();
    }
    
    this.showFilterOptions = false;
  }
  
  updateOrAddFilter(key: string, value: string) {
    const index = this.selectedFilter.findIndex(filter => filter.startsWith(key));
    if (index !== -1) {
      this.selectedFilter[index] = `${key}:${value}`;
    } else {
      this.selectedFilter.push(`${key}:${value}`);
    }
  }

  showMovies() {
    this.frontService.getAllMovies().subscribe({
      next: (data) => {
        if (data && data.results) {
          this.movies = data.results;
        } else {
          console.error('Unexpected data format:', data);
        }
      },
      error: (err) => {
        console.error('Error fetching movies:', err);
      }
    });
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

  getLanguage(language: string): string | null {
    const languageMap: { [key: string]: string } = {
      'English': 'en',
      'Spanish': 'es',
      'French': 'fr',
      'German': 'de',
      'Chinese': 'zh',
      'Turkish': 'tr'
    };
  
    return languageMap[language] || null;
  }

  clearFilters() {
    this.selectedFilter = [];
    this.searchQuery = '';
    this.showMovies();
  }

  viewMovieDetails(id: number): void {
    this.router.navigate(['/watchMovies', id]);
  }

  getImageUrl(path: string): string {
    return `https://image.tmdb.org/t/p/w500${path}`;
  }

  loadMovies(): void {
    const params = {
      title: this.title,
      language: this.language,
      releaseDate: this.releaseDate,
      genreId: this.genreId,
      sortBy: this.sortBy,
      ascending: this.ascending
    };
  
    this.frontService.filterAndOrderMovies(params).subscribe(
      (data: ResultsEntity[]) => this.movies = data,
      error => console.error('Error fetching movies', error)
    );
  }
  
  onFilterChange(): void {
    this.loadMovies();
  }
}