import { TestBed } from '@angular/core/testing';

import { RecserveService } from './recserve.service';

describe('RecserveService', () => {
  let service: RecserveService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecserveService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
