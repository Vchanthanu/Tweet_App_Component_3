import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TweetService } from '../tweet.service';

@Component({
  selector: 'app-add-update-tweet',
  templateUrl: './add-update-tweet.component.html',
  styleUrls: ['./add-update-tweet.component.css']
})
export class AddUpdateTweetComponent implements OnInit {
  "addTweetForm": FormGroup;
  "isUpdate": Boolean;
  "updateDetail": any;
  "msgs":any = [];
  constructor(private fb: FormBuilder, private router: Router, private tweetService: TweetService) { }

  ngOnInit(): void {
    this.formInitialization();
    let url = window.location.pathname.split('/').pop();
    if (url == 'addTweet') {
      this.isUpdate = false;
    } else {
      this.isUpdate = true;
      this.updateDetail = sessionStorage.getItem('updateTweet');
      this.patchValue(JSON.parse(this.updateDetail));
    }
  }

  formInitialization(): void {
    this.addTweetForm = this.fb.group({
      tweet: ['', [Validators.required]],
      tags: [''],
    })
  }

  patchValue(detail: any): void {
    this.addTweetForm.patchValue({
      tweet: detail.tweet,
      tags: detail.tags
    })
  }

  submit(): void {
    this.msgs = [];
    if (this.addTweetForm.valid) {
      if (this.isUpdate) {
        this.tweetService.updateTweet(this.addTweetForm.value, JSON.parse(this.updateDetail).id).subscribe(
          (data) => {
            if (data.status) {
              this.msgs.push({ severity: 'success', summary: 'Success', detail: data.message });
              setTimeout(() => {
                this.router.navigateByUrl('/home');
              }, 3000);
            }else{
              this.msgs.push({ severity: 'error', summary: 'Error', detail: data.message });
            }
          });
      } else {
        this.tweetService.addTweet(this.addTweetForm.value).subscribe(
          (data) => {
            if (data.status) {
              this.msgs.push({ severity: 'success', summary: 'Success', detail: data.message });
              setTimeout(() => {
                this.router.navigateByUrl('/home');
              }, 3000);
            }else{
              this.msgs.push({ severity: 'error', summary: 'Error', detail: data.message });
            }
          });
      }
    } else {
      this.msgs.push({ severity: 'error', summary: 'Error', detail: "Message is mandatory field" });
    }
  }

}
