import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private _URL = environment.url  + "users";

  constructor(private http: HttpClient) { }

  salvar(user: User) {
    return this.http.post<User>(this._URL, user);
  }

  buscarTodos() {
    return this.http.get<User[]>(this._URL);
  }

  remover(id: number) {
    return this.http.delete(`${this._URL}/${id}`);
  }

  associar(id: number, idsGroups: (number | undefined | null)[]) {
    return this.http.put(`${this._URL}/${id}/groups`, idsGroups );
  }
}
