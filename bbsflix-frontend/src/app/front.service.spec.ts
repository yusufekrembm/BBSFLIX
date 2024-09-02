import { TestBed } from '@angular/core/testing';

import { FrontService } from './front.service';

describe('FrontService', () => {
  let service: FrontService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FrontService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
