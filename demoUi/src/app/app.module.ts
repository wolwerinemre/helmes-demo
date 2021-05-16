import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FormComponent } from './component/form/form.component';
import {InputSwitchModule} from 'primeng/inputswitch';
import {MessageModule} from "primeng/message";
import {FormService} from "./service/form.service";
import {NotificationService} from "./service/notification.service";
import {MessageService} from "primeng/api";
import {InputTextModule} from "primeng/inputtext";
import {ButtonModule} from "primeng/button";
import {PanelModule} from "primeng/panel";
import {MessagesModule} from "primeng/messages";
import {InputMaskModule} from "primeng/inputmask";
import {BrowserAnimationsModule, NoopAnimationsModule} from "@angular/platform-browser/animations";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {SectorService} from "./service/sector.service";
import {ToastModule} from "primeng/toast";
import {TreeModule} from "primeng/tree";
import {ListboxModule} from "primeng/listbox";


@NgModule({
  declarations: [
    AppComponent,
    FormComponent
  ],
  imports: [
    BrowserModule,
    MessageModule,
    MessagesModule,
    InputSwitchModule,
    InputTextModule,
    InputMaskModule,
    BrowserAnimationsModule,
    NoopAnimationsModule,
    ButtonModule,
    PanelModule,
    HttpClientModule,
    FormsModule,
    ToastModule,
    TreeModule,
    ListboxModule,
    ReactiveFormsModule
  ],
  providers: [
    FormService,
    SectorService,
    NotificationService,
    MessageService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
