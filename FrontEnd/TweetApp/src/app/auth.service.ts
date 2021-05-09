import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLoggedIn = false;
  private "userName": string;
  private "userId": number;
  private "notification": any = [];
  constructor() { }

  public setUser(userName: string) {
    this.userName = userName;
  }
  public getUser() {
    return this.userName;
  }
  public setUserId(Id: number) {
    this.userId = Id;
  }
  public getUserId() {
    return this.userId;
  }
  public setNotification(notification: any) {
    this.notification = notification;
  }
  public getNotification() {
    return this.notification;
  }
}
