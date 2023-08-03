import { NgModule } from '@angular/core';
import { provideRouter, RouterModule, Routes, withComponentInputBinding } from '@angular/router';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { loginGuard } from './guards/login.guard';
import { LoginComponent } from './login/login/login.component';
import { PostComponent } from './post/post.component';
import { RegisterComponent } from './login/register/register.component';
const routes: Routes = [{ path: 'home', component: HomeComponent, canActivate: [loginGuard] },{ path: 'login', component: LoginComponent},{ path: 'post/:id', component: PostComponent, canActivate: [loginGuard] }, { path: '',   redirectTo: '/home', pathMatch: 'full' },{path: 'signup', component:RegisterComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
