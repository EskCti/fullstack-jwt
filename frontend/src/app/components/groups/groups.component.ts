import { Component } from '@angular/core';
import {Group} from "../../models/group";
import {FormBuilder, Validators} from "@angular/forms";
import {GroupsService} from "../../services/groups.service";

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css']
})
export class GroupsComponent {

  groups: Group[] = [];

  groupForm = this.fb.group({
    id: [],
    name: [null, Validators.required],
  })

  constructor(
    private fb: FormBuilder,
    private service: GroupsService) {
    this.buscarGrupos();
  }

  buscarGrupos() {
    this.service.buscarTodos().subscribe(
      {
        next: (res) => {
          this.groups = res;
        },
        error: (error) => {
          console.error(error);
        },
        complete: () => console.log('lista de grupos', this.groups)
      }
    )
  }


  criarGrupo(): Group {
    return {
      id: this.groupForm.get('id')?.value,
      name: this.groupForm.get('name')?.value,
    }
  }

  salvar() {
    if (this.groupForm.valid) {
      const group = this.criarGrupo();
      this.service.salvar(group).subscribe(
        {
          next: (res) => {
            this.groupForm.reset();
            this.buscarGrupos();
            alert("Grupo salvo com sucesso");
          },
          error: (error) => {
            console.log(error);
          },
          complete: () => console.log('Grupo salvo')
        }
      )
    }
  }

  remover(group: Group) {
    const confirmacao = confirm("Quer realmente excluir esse grupo?");
    if (confirmacao) {
      if (group.id) {
        this.service.remover(group.id).subscribe({
          next: () => {
            this.buscarGrupos();
            alert("Grupo removido com sucesso");
          }
        })
      }
    }
  }
}
