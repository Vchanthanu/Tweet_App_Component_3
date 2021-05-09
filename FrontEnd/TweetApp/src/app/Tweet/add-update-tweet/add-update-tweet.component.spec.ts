import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddUpdateTweetComponent } from './add-update-tweet.component';

describe('AddUpdateTweetComponent', () => {
  let component: AddUpdateTweetComponent;
  let fixture: ComponentFixture<AddUpdateTweetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddUpdateTweetComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddUpdateTweetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
