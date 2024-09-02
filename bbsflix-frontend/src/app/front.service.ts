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
  getFilteredMovies(title: string): Observable<any> {
    let params = new HttpParams().set('title', title);
    return this.http.get<any>(`${this.baseUrl}/filterAndOrderMovies`, { params });
  }
  
}
