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
    return this.http.get<any>(`${this.baseUrl}/allMovies`).pipe(
      catchError(this.handleError)
    );
  }

  getMovieById(id: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/movie/${id}?api_key=YOUR_API_KEY`).pipe(
      catchError(this.handleError)
    );
  }

  filterAndOrderMovies(params: {
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
    console.error(errorMessage); // Hata mesajını konsola yazdırın
    return throwError(errorMessage);
  }
}