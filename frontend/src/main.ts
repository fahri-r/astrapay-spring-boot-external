import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter, Routes } from '@angular/router';
import { NotesDisplayComponent } from './app/components/notes-display/notes-display.component';
import { provideHttpClient } from '@angular/common/http';
import { CreateOrEditNoteComponent } from './app/components/create-or-edit-note/create-or-edit-note.component';


const routes: Routes = [
  { path: '', component: NotesDisplayComponent },
  { path: 'create', component: CreateOrEditNoteComponent },
  { path: 'edit/:id', component: CreateOrEditNoteComponent },
];

bootstrapApplication(AppComponent, {
  providers: [provideHttpClient(), provideRouter(routes)],
}).catch((err) => console.error(err));
