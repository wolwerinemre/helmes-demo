import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {decorateUrl} from "../util/url.util";
import {Observable} from "rxjs";
import {catchError} from "rxjs/operators";
import {handleError} from "../util/error";
import {Sector} from "../domain/sector";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class SectorService {

  private serviceUrl: string;

  constructor(private readonly httpClient: HttpClient) {
    this.serviceUrl = decorateUrl(`/sector/`);
  }

  public getAll(): Observable<Sector[]> {
    return this.httpClient.get<Sector[]>(this.serviceUrl ,httpOptions)
      .pipe(
        catchError(handleError)
      );
  }

}
