import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import {Group} from "../models/group";

@Injectable({
  providedIn: 'root'
})
export class GroupsService {
  private _URL = environment.url  + "groups";

  constructor(private http: HttpClient) { }

  salvar(group: Group) {
    return this.http.post<Group>(this._URL, group);
  }

  buscarTodos() {
    return this.http.get<Group[]>(this._URL);
  }

  remover(id: number) {
    return this.http.delete(`${this._URL}/${id}`);
  }
}
