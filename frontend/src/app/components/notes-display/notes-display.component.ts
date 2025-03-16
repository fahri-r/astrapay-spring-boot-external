import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NoteService } from '../../services/note-service.service';
import { NoteDto } from '../../dto/NoteDto';

@Component({
  selector: 'app-notes-display',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './notes-display.component.html',
  styleUrl: './notes-display.component.css',
})
export class NotesDisplayComponent implements OnInit {
  notes: NoteDto[] = [];

  constructor(private noteService: NoteService) {}

  ngOnInit(): void {
    this.noteService.getNotes().subscribe(res => {
      this.notes = res;
    });
  }
}
