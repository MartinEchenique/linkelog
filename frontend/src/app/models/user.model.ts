export class User{
  userId:number;
  firstName:string;
  lastName:string;
  companyName:string;
  profilePictureUrl:string;
  role:string;


  constructor(userId:number,firstName:string, lastName:string, companyName?:string, role?:string,profilePictureUrl?:string ){
    this.firstName = firstName;
    this.lastName = lastName;
    this.companyName = companyName ? companyName : "Company not defined";
    this.role = role ? role : "Role not defined";
    this.profilePictureUrl = profilePictureUrl ? profilePictureUrl : "https://i.pinimg.com/originals/f1/0f/f7/f10ff70a7155e5ab666bcdd1b45b726d.jpg"
  }

}