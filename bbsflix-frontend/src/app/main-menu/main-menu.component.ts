import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common'; 

@Component({
  selector: 'app-main-menu',
  standalone: true,
  imports: [FormsModule, CommonModule], 
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.css']
})
export class MainMenuComponent {
  searchQuery: string = ''; 
  selectedFilter: string[] = []; 
  filterOptions: string[] = []; 
  showFilterOptions: boolean = false; 

  filterTypes: { [key: string]: string[] } = {
    genre: ['Action', 'Adventure', 'Animation', 'Comedy', 'Crime', 'Documentary', 'Drama', 'Family', 'Fantasy', 'History', 'Horror', 'Music', 'Mystery', 'Romance', 'Science Fiction', 'TV Movie', 'Thriller', 'War', 'Western'],
    language: ['English', 'Spanish', 'French', 'German', 'Chinese', 'Turkish'],
    releaseDate: ['2020', '2021', '2022', '2023']
  };

  filterKeys = ['genre', 'language', 'releaseDate'] as const;

  showFilter(type: string) {
    if (this.filterKeys.includes(type as any)) {
      this.filterOptions = this.filterTypes[type] || [];
      this.showFilterOptions = true;
    }
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

  get selectedFilterDisplay() {
    return this.selectedFilter.join(', ');
  }
}
