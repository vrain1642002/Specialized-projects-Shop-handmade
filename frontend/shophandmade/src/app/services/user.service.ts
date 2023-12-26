import { Injectable } from '@angular/core';
//nhan request
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegisterDTO } from '../dtos/user/register.dto';
import { LoginDTO } from '../dtos/user/login.dto';
import { environment } from '../environments/environment';
import { HttpUtilService } from './http.util.service';

import { UpdateUserDTO } from '../dtos/user/update.user.dto';
import { UserResponse } from '../responses/user/user.response copy';
@Injectable({
  providedIn: 'root'
})

export class UserService {
  
  private apiRegister = `${environment.apiBaseUrl}/nguoidungs/dangki`;
  private apiLogin = `${environment.apiBaseUrl}/nguoidungs/dangnhap`;
  private apiUserDetail = `${environment.apiBaseUrl}/nguoidungs/chitiets`;
  private apiConfig = {
    headers: this.httpUtilService.createHeaders(),
  }

  //da ngon ngu o phia back end con front end sinh ssau

  constructor(
    private http: HttpClient,
    private httpUtilService: HttpUtilService
  ) { }

  register(registerDTO:RegisterDTO ):Observable<any> {
    return this.http.post(this.apiRegister, registerDTO, this.apiConfig);
  }

  login(loginDTO: LoginDTO): Observable<any> {    
    return this.http.post(this.apiLogin, loginDTO, this.apiConfig);
  }
  getUserDetail(token: string) {
    return this.http.post(this.apiUserDetail, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`
      })
    })
  }
  updateUserDetail(token: string, updateUserDTO: UpdateUserDTO) {
     let userResponse = this.getUserResponseFromLocalStorage();        
    return this.http.put(`${this.apiUserDetail}/${userResponse?.ma}`,updateUserDTO,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`
      })
    })
  }
  saveUserResponseToLocalStorage(userResponse?: UserResponse) {
    try {
      debugger
      if(userResponse == null || !userResponse) {
        return;
      }
      // Convert the userResponse object to a JSON string
      const userResponseJSON = JSON.stringify(userResponse);  
      // Save the JSON string to local storage with a key (e.g., "userResponse")
      localStorage.setItem('user', userResponseJSON);  
      console.log('User response saved to local storage.');
    } catch (error) {
      console.error('Error saving user response to local storage:', error);
    }
  }
  getUserResponseFromLocalStorage():UserResponse | null {
    try {
      // Retrieve the JSON string from local storage using the key
      const userResponseJSON = localStorage.getItem('user'); 
      if(userResponseJSON == null || userResponseJSON == undefined) {
        return null;
      }
      // Parse the JSON string back to an object
      const userResponse = JSON.parse(userResponseJSON!);  
      console.log('User response retrieved from local storage.');
      return userResponse;
    } catch (error) {
      console.error('Error retrieving user response from local storage:', error);
      return null; // Return null or handle the error as needed
    }
  }
  removeUserFromLocalStorage():void {
    try {
      // Remove the user data from local storage using the key
      localStorage.removeItem('user');
      console.log('User data removed from local storage.');
    } catch (error) {
      console.error('Error removing user data from local storage:', error);
      // Handle the error as needed
    }
  }
  
}
