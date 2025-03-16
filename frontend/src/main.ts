import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter, Routes } from '@angular/router';
import { NotesDisplayComponent } from './app/components/notes-display/notes-display.component';
import { provideHttpClient } from '@angular/common/http';


const routes: Routes = [
  { path: '', component: NotesDisplayComponent },
];

bootstrapApplication(AppComponent, {
  providers: [provideHttpClient(), provideRouter(routes)],
}).catch((err) => console.error(err));
