import {Component, OnInit} from '@angular/core';
import * as SockJS from "sockjs-client";
import {Client, CompatClient, Stomp, StompConfig} from "@stomp/stompjs";
import {OutputMessage} from "./model/OutputMessage";
import {MessageService} from "./service/message.service";
import {ActiveUser} from "./model/ActiveUser";
import {ActiveUserService} from "./service/active-user.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {restrictedNames} from "./validators/restricted-names";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  private REFRESH_USER_LIST = 15000;
  private stompClient: CompatClient | undefined;
  private ipAddress: string | undefined;
  private refreshInterval: any;

  connectForm: FormGroup | undefined;
  allowedColors: string[] = ['Black', 'Aqua', 'Green', 'Blue', 'Yellow', 'Purple', 'Orange', 'Pink'];
  activeUsers: Array<ActiveUser> = [];
  messages: OutputMessage[] = [];
  nickname: string = "";
  message: string = '';
  pickerFontColor: string = "black"
  timeToSend: number = 0;
  online: number = 0;
  adblock = false;
  connected = false;
  allowSendMessages: boolean = true;
  theSameNickname: boolean = false;

  constructor(private messageService: MessageService, private activeUserService: ActiveUserService) {
  }

  ngOnInit(): void {
    this.connectForm = new FormGroup({
      nickname: new FormControl(this.nickname, [
        Validators.required,
        Validators.minLength(3),
        Validators.pattern("^[a-zA-Z0-9]*$"),
        restrictedNames()
      ])
    })

    let _this = this;
    window.onbeforeunload = function () {
      _this.disconnect();
    }
  }

  async connect() {
    this.nickname = this.connectForm?.value.nickname;
    if (this.nickname.length >= 3 && this.nickname.match("^[a-zA-Z0-9]*$")) {
      const socket = new SockJS('http://localhost:8080/chat');
      this.stompClient = Stomp.over(socket);

      await this.isTheSameNickname(this.nickname);
      if (!this.theSameNickname) {

        let _this = this;
        this.stompClient.connect({}, async function () {

          await _this.saveConnectingUser({
            nickname: _this.nickname,
            color: _this.pickerFontColor
          });

          await _this.getActiveUsers();
          _this.setConnected(true);

          _this.stompClient?.subscribe('/topic/messages', function (msg) {
            _this.showMessage(JSON.parse(msg.body))
          });

          _this.getIpAddress();
          _this.broadcastMessage(_this.nickname + ' has joined to the channel!');

          _this.refreshInterval = setInterval(() => {
            _this.activeUserService.getAllActiveUsers().subscribe((response: Array<ActiveUser>) => {
              _this.activeUsers = response
              _this.online = response.length;
            });
          }, _this.REFRESH_USER_LIST);
        })
      }
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
    this.nickname = "";
  }

  sendMessage() {
    if (this.allowSendMessages && this.message != '') {
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

  adblockDetected(adblockDetected: boolean) {
    this.adblock = adblockDetected;
  }

  get nicknameValid() {
    return this.connectForm?.get('nickname');
  }

  private broadcastMessage(message: string) {
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

  private showMessage(message: OutputMessage) {
    this.messages.push(message);
  }

  private getIpAddress() {
    this.messageService.getIpAddress().subscribe((result: any) => {
      this.ipAddress = result.ip;
    })
  }

  private async getActiveUsers() {
    await this.activeUserService.getAllActiveUsers().toPromise().then(
      (res: any) => {
        this.activeUsers = res.map((res: any) => {
          return {
            nickname: res.nickname,
            color: res.color
          }
        });
        this.online = this.activeUsers.length;
      }
    );
  }

  private async saveConnectingUser(user: ActiveUser) {
    await this.activeUserService.saveActiveUser(user).toPromise().then(
      (res: any) => {
        this.activeUsers.push(res);
      }
    )
  }

  private async isTheSameNickname(nickname: string){
    await this.activeUserService.isTheSameNickname(nickname).toPromise().then(
      (res: any) => {
        this.theSameNickname = res;
      }
    )
  }
}
