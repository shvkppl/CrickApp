import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrendingMatchComponent } from './trending-match.component';

describe('TrendingMatchComponent', () => {
  let component: TrendingMatchComponent;
  let fixture: ComponentFixture<TrendingMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrendingMatchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TrendingMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
