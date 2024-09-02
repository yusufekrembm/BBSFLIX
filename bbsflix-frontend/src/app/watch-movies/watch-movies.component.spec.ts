import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WatchMoviesComponent } from './watch-movies.component';

describe('WatchMoviesComponent', () => {
  let component: WatchMoviesComponent;
  let fixture: ComponentFixture<WatchMoviesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WatchMoviesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WatchMoviesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
