import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {

  "notifications": any = [];
  "msgs": any = [{ severity: 'error', summary: '!!',detail: "No Notification" }];
  constructor(private auth: AuthService) { }

  ngOnInit(): void {
    this.notifications = this.auth.getNotification();
  }

}
