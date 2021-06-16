import { TestBed } from '@angular/core/testing';

import { UpdateserveService } from './updateserve.service';

describe('UpdateserveService', () => {
  let service: UpdateserveService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UpdateserveService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
