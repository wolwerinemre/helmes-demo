import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {decorateUrl} from "../util/url.util";
import {Form} from "../domain/form";
import {catchError} from "rxjs/operators";
import {handleError} from "../util/error";
import {Observable} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class FormService {

  private serviceUrl: string;

  constructor(private readonly httpClient: HttpClient) {
    this.serviceUrl = decorateUrl(`/form/`);
  }

  public save(form: Form) {
    return this.httpClient.post<Form>(this.serviceUrl,form,httpOptions)
      .pipe(
        catchError(handleError)
      );
  }

  public update(form: Form) {
    return this.httpClient.put<Form>(this.serviceUrl,form,httpOptions)
      .pipe(
        catchError(handleError)
      );
  }

  public getRecord(id): Observable<Form> {
    return this.httpClient.get<Form>(this.serviceUrl + id,httpOptions)
      .pipe(
        catchError(handleError)
      );
  }

  public getLastRecord(): Observable<Form> {
    return this.httpClient.get<Form>(this.serviceUrl,httpOptions)
      .pipe(
        catchError(handleError)
      );
  }


}
