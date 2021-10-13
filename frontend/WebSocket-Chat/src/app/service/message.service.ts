import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observer} from "rxjs";
import {catchError, retry} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private http: HttpClient) { }


  getIpAddress(): any{
    return this.http.get("https://api.ipify.org/?format=json");
  }
}
