import {Component, OnInit} from '@angular/core';
import {Group} from "../../models/group";
import {FormBuilder, Validators} from "@angular/forms";
import {GroupsService} from "../../services/groups.service";
import {User} from "../../models/user";
import {Permission} from "../../models/permission";
import {PermissionsService} from "../../services/permissions.service";

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css']
})
export class GroupsComponent implements OnInit  {
  displayModal: boolean = false;

  groupSelected: Group | undefined | null;

  groups: Group[] = [];
  permissions: Permission[] = [];
  selectedPermissions: Permission[] = [];

  ngOnInit() {
  }


  groupForm = this.fb.group({
    id: [],
    name: [null, Validators.required],
  })

  constructor(
    private fb: FormBuilder,
    private service: GroupsService,
    private permissionsService: PermissionsService) {
    this.buscarGrupos();
    this.buscarPermissions();
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
  buscarPermissions() {
    this.permissionsService.buscarTodos()
      .subscribe({
        next: (res) => {
          this.permissions = res;
        },
        error: (error) => {
          console.error(error);
        },
        complete: () => console.log('lista de Permissões', this.permissions)
      });
  }
  onChange(event: any) {
    this.selectedPermissions = event.value
    console.log('onChange', this.selectedPermissions);
  }

  onModalAssociar(group: Group) {
    this.groupSelected = group;
    this.displayModal = true;
  }

  closeModal() {
    this.displayModal = false;
  }

  handleAssociar(group: any) {
    const idsPermissions = this.selectedPermissions.map(item => item.id);
    if (group.id && idsPermissions.length > 0) {
      this.service.associar(group.id, idsPermissions)
        .subscribe({
          next: (res) => {
            console.log('result salvar', res);
          },
          error: (error) => {
            console.error(error);
          },
          complete: () => {
            console.log('Associados grupo e permissões');
            this.displayModal = false;
          }
        });
    }
  }
}
