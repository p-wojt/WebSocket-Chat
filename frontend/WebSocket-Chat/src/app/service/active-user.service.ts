import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {ActiveUser} from "../model/ActiveUser";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ActiveUserService {

  constructor(private http: HttpClient) { }

  saveActiveUser(activeUser: ActiveUser){
    const body = JSON.stringify(activeUser);
    return this.http.post(environment.apiUrl + environment.saveActiveUser, body, {headers: {
        'Content-Type': 'application/json'
      }});
  }

  getAllActiveUsers(): any{
    return this.http.get<Array<ActiveUser>>(environment.apiUrl + environment.getAllActiveUsers);
  }

  deleteUserByName(nickname: string): any{
    this.http.delete(environment.apiUrl + environment.deleteActiveUser + `/${nickname}`).subscribe();
  }

  isTheSameNickname(nickname: string): any{
    return this.http.get<boolean>(environment.apiUrl + environment.isTheSameNickname + `/${nickname}`);
  }
}
