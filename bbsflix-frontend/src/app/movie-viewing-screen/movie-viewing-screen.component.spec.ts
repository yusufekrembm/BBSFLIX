import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieViewingScreenComponent } from './movie-viewing-screen.component';

describe('MovieViewingScreenComponent', () => {
  let component: MovieViewingScreenComponent;
  let fixture: ComponentFixture<MovieViewingScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MovieViewingScreenComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MovieViewingScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
