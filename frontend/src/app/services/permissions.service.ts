import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Permission} from "../models/permission";

@Injectable({
  providedIn: 'root'
})
export class PermissionsService {
  private _URL = environment.url  + "permissions";

  constructor(private http: HttpClient) { }

  salvar(permission: Permission) {
    return this.http.post<Permission>(this._URL, permission);
  }

  buscarTodos() {
    return this.http.get<Permission[]>(this._URL);
  }

  remover(id: number) {
    return this.http.delete(`${this._URL}/${id}`);
  }
}
