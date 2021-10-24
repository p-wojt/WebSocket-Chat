import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private http: HttpClient) { }


  getIpAddress(): any{
    return this.http.get("https://api.ipify.org/?format=json");
  }
}
