import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateOrEditNoteComponent } from './create-or-edit-note.component';

describe('CreateOrEditNoteComponent', () => {
  let component: CreateOrEditNoteComponent;
  let fixture: ComponentFixture<CreateOrEditNoteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateOrEditNoteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateOrEditNoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
