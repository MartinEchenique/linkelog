import { CompilerConfig } from '@angular/compiler';
import { Component } from '@angular/core';
import { LoginService } from '../login.service';
import { SignUpDetails } from '../../Dto/SignUpDetails.dto'
import { catchError } from 'rxjs';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  firstName:string="asde";
  lastName:string="last";
  companyName:string;
  role:string;
  username:string=crypto.randomUUID();
  password:string="ABcd123456";
  constructor(private loginService:LoginService){}
signUp() {
  let details:SignUpDetails = {firstName:this.firstName,
            lastName:this.lastName,
            companyName:this.companyName,
            role:this.role,
            username:this.username,
            password:this.password}
this.loginService.signUp(details).pipe(catchError((err)=> {console.log(err);return err})).subscribe((data:any) =>{
  console.log("la data es-.")

  console.log(data)
})
}

}
