import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UsersService} from "../../services/users.service";
import {Group} from "../../models/group";
import {GroupsService} from "../../services/groups.service";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  displayModal: boolean = false;

  userSelected: User | undefined | null;

  users: User[] = [];
  groups: Group[] = [];
  selectedGroups: Group[] = [];

  ngOnInit() {
  }

  userForm = this.fb.group({
    id: [],
    firstName: [null, Validators.required],
    lastName: [],
    cpfCnpj: [],
    career: [],
  })

  constructor(
    private fb: FormBuilder,
    private service: UsersService,
    private groupsService: GroupsService) {
    this.buscarUsers();
    this.buscarGroups();
  }

  buscarUsers() {
    this.service.buscarTodos().subscribe(
      {
        next: (res) => {
          this.users = res;
        },
        error: (error) => {
          console.error(error);
        },
        complete: () => console.log('lista de Usuários', this.users)
      }
    )
  }


  criarUser(): User {
    return {
      id: this.userForm.get('id')?.value,
      firstName: this.userForm.get('firstName')?.value,
      lastName: this.userForm.get('lastName')?.value,
      cpfCnpj: this.userForm.get('cpfCnpj')?.value,
      career: this.userForm.get('career')?.value,
      password: this.userForm.get('password')?.value,
    }
  }

  salvar() {
    console.log("salvar", this.selectedGroups);
    if (this.userForm.valid) {
      const ids = this.selectedGroups.map(item => item.id);
      if (ids) {
        this.service.associar(1, ids)
          .subscribe({
            next: (res) => {
              this.userForm.reset();
              this.buscarUsers();
              alert("Associação efetuado com sucesso");
            },
            error: (error) => {
              console.log(error);
            },
            complete: () => console.log('Associação efetuado com sucesso')
          });
      }
      // const user = this.criarUser();
    }
  }

  remover(user: User) {
    const confirmacao = confirm("Quer realmente excluir esse Usuário?");
    if (confirmacao) {
      if (user.id) {
        this.service.remover(user.id).subscribe({
          next: () => {
            this.buscarUsers();
            alert("Usuário removido com sucesso");
          }
        })
      }
    }
  }

  buscarGroups() {
    this.groupsService.buscarTodos()
      .subscribe({
        next: (res) => {
          this.groups = res;
        },
        error: (error) => {
          console.error(error);
        },
        complete: () => console.log('lista de Grupos', this.groups)
      });
  }
  onChange(event: any) {
    this.selectedGroups = event.value
    console.log('onChange', this.selectedGroups);
  }

  onModalAssociar(user: User) {
    this.userSelected = user;
    this.displayModal = true;
  }

  closeModal() {
    this.displayModal = false;
  }

  handleAssociar(user: any) {
    const idsGroups = this.selectedGroups.map(item => item.id);
    if (user.id && idsGroups.length > 0) {
      this.service.associar(user.id, idsGroups)
        .subscribe({
          next: (res) => {
           console.log('result salvar', res);
          },
          error: (error) => {
            console.error(error);
          },
          complete: () => {
            console.log('Associados usuário e grupos');
            this.displayModal = false;
          }
        });
    }
  }
}
