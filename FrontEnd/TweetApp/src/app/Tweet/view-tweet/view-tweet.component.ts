import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';
import { TweetService } from '../tweet.service';
import { FormGroup, Validators, FormArray, FormControl } from '@angular/forms';

@Component({
  selector: 'app-view-tweet',
  templateUrl: './view-tweet.component.html',
  styleUrls: ['./view-tweet.component.css']
})
export class ViewTweetComponent implements OnInit {
  "tweetList": any = [];
  "byUser": boolean
  "replyForm": FormGroup;
  "msgs": any = [];
  constructor(private tweetService: TweetService, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    sessionStorage.clear();
    let url = window.location.pathname.split('/').pop();
    this.formInitialization();
    if (url == 'home') {
      this.byUser = false;
      this.tweetService.getAllTweet().subscribe({
        next: (data: any) => {
          if (data.status) {
            this.tweetList = data.posts;
            let array = this.replyForm.controls['replyFormArray'] as FormArray
            this.tweetList.forEach((element: any) => {
              array.push(new FormGroup({
                id: new FormControl(element.id, Validators.required),
                message: new FormControl('', Validators.required),
                tags: new FormControl(''),
              }))
            });
          }
        },
      });
    } else {
      this.byUser = true;
      this.tweetService.getAllTweetByUserName().subscribe({
        next: (data: any) => {
          if (data.status) {
            this.tweetList = data.posts;
            let array = this.replyForm.controls['replyFormArray'] as FormArray
            this.tweetList.forEach((element: any) => {
              array.push(new FormGroup({
                id: new FormControl(element.id, Validators.required),
                message: new FormControl('', Validators.required),
                tags: new FormControl(''),
              }))
            });
          }
        },
      });
    }
  }

  formInitialization(): void {
    this.replyForm = new FormGroup(
      {
        replyFormArray: new FormArray([])
      }
    )
  }

  getUserName() {
    return this.authService.getUser();
  }

  getDuration(createdTime: any) {
    let currectDate = new Date();
    let created = new Date(createdTime);
    let res = '';
    let day = currectDate.getDate() - created.getDate();
    if (day == 0) {
      let hrs = currectDate.getHours() - created.getHours();
      if (hrs == 0) {
        let mins = currectDate.getMinutes() - created.getMinutes();
        if (mins == 0) {
          let sec = currectDate.getSeconds() - created.getSeconds();
          if (sec == 0) {

          } else {
            res = sec.toString() + " second";
          }
        } else {
          res = mins.toString() + " mins";
        }
      } else {
        res = hrs.toString() + " hrs";
      }
    } else {
      res = day.toString() + " days";
    }
    return res
  }
  onUpdate(detail: any) {
    sessionStorage.setItem('updateTweet', JSON.stringify(detail));
    this.router.navigateByUrl('/updateTweet');
  }

  onDelete(tweetId: string) {
    this.msgs = []
    this.tweetService.deleteTweet(tweetId).subscribe(
      data => {
        if (data.status) {
          this.msgs.push({ severity: 'success', summary: 'Success', detail: data.message })
          let index = this.tweetList.findIndex((data: any) => data.id == tweetId);
          this.tweetList.splice(index, 1);
        } else {
          this.msgs.push({ severity: 'error', summary: 'Error', detail: data.message })
        }
      }
    )
  }

  AddLike(tweetId: string) {
    this.msgs = []
    this.tweetService.likeATweet(tweetId).subscribe({
      next: (data: any) => {
        if (data.status) {
          let index = this.tweetList.findIndex((data: any) => data.id == tweetId);
          this.tweetList[index].likes++;
        } else {
          this.msgs.push({ severity: 'error', summary: 'Error', detail: data.message })
        }
      }
    })
  }

  onReply(index: any) {
    this.msgs = [];
    let array = this.replyForm.controls['replyFormArray'] as FormArray
    let info = array.controls[index];
    if (info.valid) {
      let data = {
        message: info.value.message,
        tags: info.value.tags
      }
      this.tweetService.replyToTweet(data, info.value.id).subscribe(
        (data) => {
          if (data.status) {
            this.msgs.push({ severity: 'success', summary: 'Success', detail: data.message })
            setTimeout(() => {
              this.msgs = []
              this.router.navigateByUrl('/home');
            }, 2000);
            this.ngOnInit();
          } else {
            this.msgs.push({ severity: 'error', summary: 'Error', detail: data.message })
          }
        }
      );
    } else {
      this.msgs.push({ severity: 'error', summary: 'Error', detail: "Comment field is mandatory" })
    }
  }
}
