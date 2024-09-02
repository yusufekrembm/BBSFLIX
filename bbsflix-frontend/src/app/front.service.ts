import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FrontService {
  private baseUrl = 'http://localhost:8080/api/bbsflix';

  private readonly GENRE_IDS: { [key: string]: number } = {
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
    'Western': 37,
  };
  

  constructor(private http: HttpClient) { }

  getAllMovies(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/allMovies`);
  }

  getMovieById(id: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/movie/${id}?api_key=YOUR_API_KEY`);
  }
  
  filterAndOrderMovies(filters: any): Observable<any> {
    let params = new HttpParams()
      .set('title', filters.title || '')
      .set('sortBy', 'title') // Default sorting, you might want to make this dynamic
      .set('ascending', 'true'); // Default sorting order, you might want to make this dynamic
  
    // Add dynamic filter parameters
    if (filters.genreId) {
      params = params.set('genreId', filters.genreId); // Ensure the key is 'genreId'
    }
  
    // Log the URL for debugging
    console.log('Request URL:', `${this.baseUrl}/filterAndOrderMovies?${params.toString()}`);
    
    return this.http.get(`${this.baseUrl}/filterAndOrderMovies`, { params });
  }
  
  
  
  
  
}
