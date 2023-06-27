import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from "../../../environments/environment";
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ShopService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get<any>(`${environment.api}/shop`);
  }

  getInfo(shopName:String): Observable<any>{
    return this.http.get<any>(`${environment.api}/shop/${shopName}/info`);
  }

 
}
