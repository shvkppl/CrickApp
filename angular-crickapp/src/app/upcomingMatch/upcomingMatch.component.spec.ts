import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpcomingMatchComponent } from './upcomingMatch.component';

describe('MatchComponent', () => {
  let component: UpcomingMatchComponent;
  let fixture: ComponentFixture<UpcomingMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpcomingMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpcomingMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
