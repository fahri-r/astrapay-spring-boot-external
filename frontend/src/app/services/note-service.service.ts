import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NoteDto } from '../dto/NoteDto';
import { BaseApiDto } from '../dto/BaseApiDto';

@Injectable({
  providedIn: 'root',
})
export class NoteService {
  private baseUrl = 'http://127.0.0.1:8000/api';

  constructor(private http: HttpClient) {}

  getNotes(): Observable<BaseApiDto<NoteDto>> {
    let url = this.baseUrl + '/notes';
    return this.http.get<BaseApiDto<NoteDto>>(url);
  }

  getNoteById(id: string): Observable<BaseApiDto<NoteDto>> {
    let url = this.baseUrl + `/notes/${id}`;
    return this.http.get<BaseApiDto<NoteDto>>(url);
  }

  createNote(note: Omit<NoteDto, 'id'>): Observable<any> {
    let url = this.baseUrl + '/notes';
    return this.http.post(url, note);
  }

  deleteNotes(id: string): Observable<any> {
    let url = this.baseUrl + `/notes/${id}`;
    return this.http.delete(url);
  }
}
