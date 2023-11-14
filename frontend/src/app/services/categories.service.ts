import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Category} from "../models/category";

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {
  private _URL = environment.url  + "categories";

  constructor(private http: HttpClient) { }

  salvar(category: Category) {
    return this.http.post<Category>(this._URL, category);
  }

  buscarTodos() {
    return this.http.get<Category[]>(this._URL);
  }

  remover(id: number) {
    return this.http.delete(`${this._URL}/${id}`);
  }
}
