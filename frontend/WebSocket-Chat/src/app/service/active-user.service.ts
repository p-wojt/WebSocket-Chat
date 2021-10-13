import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {ActiveUser} from "../model/ActiveUser";

@Injectable({
  providedIn: 'root'
})
export class ActiveUserService {

  constructor(private http: HttpClient) { }

  saveActiveUser(activeUser: ActiveUser){
    const body = JSON.stringify(activeUser);
    this.http.post(environment.apiUrl + environment.saveActiveUser, body);
  }

  getAllActiveUsers(): any{
    this.http.get(environment.apiUrl + environment.getAllActiveUsers).subscribe((response: any) => {
      return response;
    });
  }

  deleteUserByName(nickname: string): any{
    this.http.delete(environment.apiUrl + environment.getAllActiveUsers + `/${nickname}`);
  }
}
