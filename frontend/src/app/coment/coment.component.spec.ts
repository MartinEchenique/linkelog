import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComentComponent } from './coment.component';

describe('ComentComponent', () => {
  let component: ComentComponent;
  let fixture: ComponentFixture<ComentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ComentComponent]
    });
    fixture = TestBed.createComponent(ComentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
