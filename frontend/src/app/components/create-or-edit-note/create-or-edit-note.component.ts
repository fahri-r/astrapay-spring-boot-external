import { Component, inject, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { Router } from '@angular/router';
import { NoteDto } from '../../dto/NoteDto';
import { NoteService } from '../../services/note-service.service';

@Component({
  selector: 'app-add-note',
  standalone: true,
  imports: [ReactiveFormsModule, MatInputModule, MatButtonModule, RouterModule],
  templateUrl: './create-or-edit-note.component.html',
  styleUrl: './create-or-edit-note.component.css',
})
export class CreateOrEditNoteComponent implements OnInit {
  router = inject(Router);

  constructor(private route: ActivatedRoute, private noteService: NoteService) {
    this.route.url.subscribe((params) => {
      var contextType = params[0].path;

      if (contextType === 'edit') {
        this.id = Number.parseInt(params[1].path);
      }
    });
  }

  ngOnInit(): void {
    if (this.id) {
      this.noteService.getNoteById(this.id.toString()).subscribe({
        next: (res) => {
          const { title, content } = res.data as NoteDto;
          this.noteForm.setValue({
            title,
            content,
          });
        },
        error: (err) => {
          console.error(err);
        },
      });
    }
  }

  id: number | null = null;
  noteForm = new FormGroup({
    title: new FormControl('', Validators.required),
    content: new FormControl('', Validators.required),
  });

  save() {
    if (this.noteForm.valid) {
      const { title, content } = this.noteForm.value;
      var input = new NoteDto({
        id: this.id,
        title: title!,
        content: content!,
      });

      this.noteService.createNote(input).subscribe({
        next: (res) => {
          this.noteForm.reset();
          this.router.navigateByUrl('/');
        },
        error: (err) => {
          console.error(err);
        },
      });
    }
  }

  delete() {
    this.noteService.deleteNotes(this.id!.toString()).subscribe({
      next: () => {
        this.noteForm.reset();
        this.router.navigateByUrl('/');
      },
      error: (err) => {
        console.error(err);
      },
    });
  }
}
