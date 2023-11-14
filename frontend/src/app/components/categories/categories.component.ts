import {Component} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {CategoriesService} from "../../services/categories.service";
import {Category} from "../../models/category";

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent {

  categories: Category[] = [];

  categoryForm = this.fb.group({
    id: [],
    name: [null, Validators.required],
  })

  constructor(
    private fb: FormBuilder,
    private service: CategoriesService) {
    this.buscarCategories();
  }

  buscarCategories() {
    this.service.buscarTodos().subscribe(
      {
        next: (res) => {
          this.categories = res;
        },
        error: (error) => {
          console.error(error);
        },
        complete: () => console.log('lista de categorias', this.categories)
      }
    )
  }


  criarCategory(): Category {
    return {
      id: this.categoryForm.get('id')?.value,
      name: this.categoryForm.get('name')?.value,
    }
  }

  salvar() {
    if (this.categoryForm.valid) {
      const group = this.criarCategory();
      this.service.salvar(group).subscribe(
        {
          next: (res) => {
            this.categoryForm.reset();
            this.buscarCategories();
            alert("Categoria salvo com sucesso");
          },
          error: (error) => {
            console.log(error);
          },
          complete: () => console.log('Categoria salvo')
        }
      )
    }
  }

  remover(category: Category) {
    const confirmacao = confirm("Quer realmente excluir essa categoria?");
    if (confirmacao) {
      if (category.id) {
        this.service.remover(category.id).subscribe({
          next: () => {
            this.buscarCategories();
            alert("Categoria removido com sucesso");
          }
        })
      }
    }
  }

}
