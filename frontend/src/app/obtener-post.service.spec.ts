import { TestBed } from '@angular/core/testing';

import { ObtenerPostService } from './post.service';

describe('ObtenerPostService', () => {
  let service: ObtenerPostService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ObtenerPostService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
