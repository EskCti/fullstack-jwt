import {Component} from '@angular/core';
import {Group} from "../../models/group";
import {FormBuilder, Validators} from "@angular/forms";
import {GroupsService} from "../../services/groups.service";
import {Permission} from "../../models/permission";
import {PermissionsService} from "../../services/permissions.service";

@Component({
  selector: 'app-permissions',
  templateUrl: './permissions.component.html',
  styleUrls: ['./permissions.component.css']
})
export class PermissionsComponent {

  permissions: Permission[] = [];

  permissionForm = this.fb.group({
    id: [],
    name: [null, Validators.required],
    description: [],
  })

  constructor(
    private fb: FormBuilder,
    private service: PermissionsService) {
    this.buscarPermissions();
  }

  buscarPermissions() {
    this.service.buscarTodos().subscribe(
      {
        next: (res) => {
          this.permissions = res;
        },
        error: (error) => {
          console.error(error);
        },
        complete: () => console.log('lista de permissões', this.permissions)
      }
    )
  }

  criarPermission(): Permission {
    return {
      id: this.permissionForm.get('id')?.value,
      name: this.permissionForm.get('name')?.value,
      description: this.permissionForm.get('description')?.value,
    }
  }

  salvar() {
    if (this.permissionForm.valid) {
      const permission = this.criarPermission();
      this.service.salvar(permission).subscribe(
        {
          next: (res) => {
            this.permissionForm.reset();
            this.buscarPermissions();
            alert("Permissão salvo com sucesso");
          },
          error: (error) => {
            console.log(error);
          },
          complete: () => console.log('Permissão salvo')
        }
      )
    }
  }

  remover(permission: Permission) {
    const confirmacao = confirm("Quer realmente excluir essa permissão?");
    if (confirmacao) {
      if (permission.id) {
        this.service.remover(permission.id).subscribe({
          next: () => {
            this.buscarPermissions();
            alert("Permissão removido com sucesso");
          }
        })
      }
    }
  }

}
