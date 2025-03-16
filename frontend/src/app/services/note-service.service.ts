import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NoteDto } from '../dto/NoteDto';

@Injectable({
  providedIn: 'root',
})
export class NoteService {
  private baseUrl = 'http://127.0.0.1:8000';

  constructor(private http: HttpClient) {}

  getNotes(): Observable<NoteDto[]> {
    let url = this.baseUrl + '/notes'
    return this.http.get<NoteDto[]>(url);
  }
}
