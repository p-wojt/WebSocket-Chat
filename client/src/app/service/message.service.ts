import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {IpMessage} from "../model/IpMessage";

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private http: HttpClient) { }


  getIpAddress(): Observable<IpMessage>{
    return this.http.get<IpMessage>("https://api.ipify.org/?format=json");
  }
}
