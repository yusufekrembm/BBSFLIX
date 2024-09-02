import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FrontService } from '../front.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-watch-movies',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './watch-movies.component.html',
  styleUrls: ['./watch-movies.component.css']
})
export class WatchMoviesComponent implements OnInit {
  searchQuery: string = ''; 
  selectedFilter: string[] = []; 
  filterOptions: string[] = []; 
  showFilterOptions: boolean = false; 
  movie: any;

  constructor(private route: ActivatedRoute, private frontService: FrontService, private router: Router) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.getMovieDetails(id);
      }
    });
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

  mainMenu(){
    this.router.navigate(['/mainMenu'])
  }

  
}
