import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { ResultsEntity } from './results-entity.model';


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

  ffilterAndOrderMovies(params: {
    title?: string;
    language?: string;
    releaseDate?: string;
    genreId?: number;
    sortBy?: string;
    ascending?: boolean;
  }): Observable<ResultsEntity[]> {
    let httpParams = new HttpParams();
    if (params.title) httpParams = httpParams.set('title', params.title);
    if (params.language) httpParams = httpParams.set('language', params.language);
    if (params.releaseDate) httpParams = httpParams.set('releaseDate', params.releaseDate);
    if (params.genreId) httpParams = httpParams.set('genreId', params.genreId.toString());
    if (params.sortBy) httpParams = httpParams.set('sortBy', params.sortBy);
    if (params.ascending !== undefined) httpParams = httpParams.set('ascending', params.ascending.toString());
  
    return this.http.get<ResultsEntity[]>(`${this.baseUrl}/filterAndOrderMovies`, { params: httpParams }).pipe(
      catchError(this.handleError)
    );
  }
  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An unknown error occurred!';
    if (error.error instanceof ErrorEvent) {
      // Client-side or network error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Backend returned an unsuccessful response code
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    // Here you could log the error to an external service if needed
    return throwError(errorMessage);
  }
  





}
