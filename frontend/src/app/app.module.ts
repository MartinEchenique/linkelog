import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ObtenerPostService } from './post.service';
import { PostComponent } from './post/post.component';
import { ComentComponent } from './coment/coment.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { HomeComponent } from './home/home.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'
import { LoginModule } from './login/login.module';
@NgModule({
  declarations: [
    AppComponent,
    PostComponent,
    ComentComponent,
    UserProfileComponent,
    HomeComponent
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, 
    HttpClientModule,
    LoginModule
  ],
  providers: [ObtenerPostService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
