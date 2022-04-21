export interface User {
  userId: number,
  fullName : string,
  email : string,
  mobile : string;
  password : string,
	isEmailVerified : boolean,
  token : string
}
