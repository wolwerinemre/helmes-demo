import {throwError} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";

export function handleError(error: any) {
  if (error instanceof HttpErrorResponse) {
    // The backend returned an unsuccessful response code
    console.error('Status code:', error.status);
    console.error('Response body:', error.message);
  } else {
    // A client-side or network error occurred
    console.error('An error occurred:', error.message);
  }
  return throwError(error.message || error);
}
