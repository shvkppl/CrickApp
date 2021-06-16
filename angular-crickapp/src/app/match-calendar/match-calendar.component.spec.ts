import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatchCalendarComponent } from './match-calendar.component';

describe('MatchCalendarComponent', () => {
  let component: MatchCalendarComponent;
  let fixture: ComponentFixture<MatchCalendarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MatchCalendarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MatchCalendarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
