import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common'; 
import { Router } from '@angular/router'; // Router'ı import edin
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

  selectFilter(option: string) {
    const index = this.selectedFilter.findIndex(f => f.startsWith(option.split(':')[0]));
    if (index !== -1) {
      this.selectedFilter[index] = option;
    } else {
      this.selectedFilter.push(option);
    }
    this.showFilterOptions = false;
  }

  onSearch() {
    console.log('Searching for:', this.searchQuery);
    console.log('Selected filters:', this.selectedFilter);
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

  // Film detaylarına yönlendirme yapacak fonksiyon
  viewMovieDetails(id: string): void {
    console.log('deneme')
    this.router.navigate(['/watchMovies', id]);
  }

  getImageUrl(path: string): string {
    return `https://image.tmdb.org/t/p/w500${path}`;
  }

}
