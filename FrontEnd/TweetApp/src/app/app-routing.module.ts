import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { AddUpdateTweetComponent } from './Tweet/add-update-tweet/add-update-tweet.component';
import { ViewTweetComponent } from './Tweet/view-tweet/view-tweet.component';
import { LoginComponent } from './User/login/login.component';
import { NotificationComponent } from './User/notification/notification.component';
import { RegisterComponent } from './User/register/register.component';

const routes: Routes = [
  { path: "home", component: ViewTweetComponent, canActivate:[AuthGuard] },
  { path: "home/user", component: ViewTweetComponent,canActivate:[AuthGuard] },
  { path: 'addTweet', component: AddUpdateTweetComponent,canActivate:[AuthGuard] },
  { path: 'updateTweet', component: AddUpdateTweetComponent,canActivate:[AuthGuard] },
  { path: 'signup', component: RegisterComponent },
  { path: "", redirectTo: "login", pathMatch: "full" },
  { path: 'notification', component: NotificationComponent,canActivate:[AuthGuard] },
  { path: "login", component: LoginComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
