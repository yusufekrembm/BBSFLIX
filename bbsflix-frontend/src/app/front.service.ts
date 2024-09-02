import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FrontService {
  private baseUrl = 'http://localhost:8080/api/bbsflix';  

  constructor(private http: HttpClient) { }

  getAllMovies(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/allMovies`);
  }

  getMovieById(id: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/movie/${id}?api_key=YOUR_API_KEY`);
  }
  
  filterAndOrderMovies(filters: any): Observable<any> {
    let params = new HttpParams()
      .set('sortBy', filters.sortBy || 'title') 
      .set('ascending', filters.ascending !== undefined ? filters.ascending.toString() : 'true');
  
    if (filters.title) {
      params = params.set('title', filters.title);
    }
  
    if (filters.genreId) {
      params = params.set('genreId', filters.genreId);
    }
  
    if (filters.language) {
      params = params.set('language', filters.language);
    }
  
    console.log('Request URL:', `${this.baseUrl}/filterAndOrderMovies?${params.toString()}`);
  
    return this.http.get(`${this.baseUrl}/filterAndOrderMovies`, { params });
  }
  
  
  
  
  
  
}
