import { TestBed } from '@angular/core/testing';

import { FrontServiceService } from './front-service.service';

describe('FrontServiceService', () => {
  let service: FrontServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FrontServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
