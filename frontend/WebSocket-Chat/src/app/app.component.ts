import {Component, OnInit} from '@angular/core';
import * as SockJS from "sockjs-client";
import {CompatClient, Stomp} from "@stomp/stompjs";
import {OutputMessage} from "./model/OutputMessage";
import {MessageService} from "./service/message.service";
import {ActiveUser} from "./model/ActiveUser";
import {ActiveUserService} from "./service/active-user.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {


  constructor(private messageService: MessageService, private activeUserService: ActiveUserService) {
  }

  ngOnInit(): void {
    let _this = this;
    window.onbeforeunload = function () {
      _this.disconnect();
    }
  }

  private stompClient: CompatClient | undefined;
  private ipAddress: string | undefined;

  allowedColors: string[] = ['Black', 'Aqua', 'Green', 'Blue', 'Yellow', 'Purple', 'Orange', 'Pink'];
  activeUsers: ActiveUser[] = [];
  nickname: string = "";
  message: string = '';
  pickerFontColor: string = "black"
  timeToSend: number = 0;
  allowSendMessages: boolean = true;
  adblock = false;
  connected = false; //<---- TODO: Change to false
  messages: OutputMessage[] = [];

  connect() {
    if (this.nickname.length > 0) {

      //if (this.activeUsers != undefined && !this.activeUsers.find(e => e.name == this.nickName)) {
        const socket = new SockJS('http://localhost:8080/chat');
        this.stompClient = Stomp.over(socket);

        let _this = this;
        this.stompClient.connect({}, function () {
          _this.setConnected(true);

          _this.stompClient?.subscribe('/topic/messages', function (msg) {
            _this.showMessage(JSON.parse(msg.body))
          })

          _this.broadcastMessage(_this.nickname + ' has joined to the channel!');
        })

        this.getIpAddress();

      this.activeUserService.saveActiveUser({
        nickname: this.nickname,
        color: this.pickerFontColor
      });

      if(this.activeUserService.getAllActiveUsers() != undefined) {
        this.activeUserService.getAllActiveUsers().subscribe((response: any) => {
          this.activeUsers = response;
        });
      }
      //}
    }
  }

  setConnected(connected: boolean) {
    if (!connected) {
      this.messages = [];
    }

    this.connected = connected;
  }

  disconnect() {
    if (this.stompClient != undefined) {
      this.broadcastMessage(this.nickname + ' has left the channel!');
      this.activeUserService.deleteUserByName(this.nickname);
      this.stompClient.disconnect();
    }


    this.setConnected(false);
    this.activeUserService.deleteUserByName(this.nickname);
    this.nickname = "";
  }

  sendMessage() {
    if (this.message != '') {
      this.allowSendMessages = false;
      this.stompClient?.send(
        '/app/chat',
        {},
        JSON.stringify({
          'from': this.nickname,
          'text': this.message,
          'nickColor': this.pickerFontColor,
          'ipAddress': this.ipAddress
        })
      );
      this.message = '';
      this.timeToSend = 3;


      let noSpamInterval = setInterval(() => {
        this.timeToSend! -= 1;
        if (this.timeToSend! <= 0) {
          this.allowSendMessages = true;
          clearInterval(noSpamInterval);
        }
      }, 1000)

    }
  }

  broadcastMessage(message: string) {
    this.stompClient?.send(
      '/app/chat',
      {},
      JSON.stringify({
        'from': 'Server',
        'text': message,
        'nickColor': 'red'
      })
    );
  }

  showMessage(message: OutputMessage) {
    this.messages.push(message);
  }

  private getIpAddress() {
    this.messageService.getIpAddress().subscribe((result: any) => {
      this.ipAddress = result.ip;
    })
  }

  adblockDetected(adblockDetected: boolean) {
    this.adblock = adblockDetected;
  }
}
