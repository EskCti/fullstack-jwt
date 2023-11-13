import { EventEmitter, Component, Output } from '@angular/core';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
  })
export class LoginFormComponent {

  @Output() onSubmitLoginEvent = new EventEmitter();
  @Output() onSubmitRegisterEvent = new EventEmitter();

	active: string = "login";
  firstName: string = "";
  lastName: string = "";
  login: string = "";
  cpfCnpj: string = "";
  password: string = "";

	onLoginTab(): void {
		this.active = "login";
	}

	onRegisterTab(): void {
		this.active = "register";
	}

  onSubmitLogin(): void {
    this.onSubmitLoginEvent.emit({"cpfCnpj": this.cpfCnpj, "password": this.password});
  }

  onSubmitRegister(): void {
    this.onSubmitRegisterEvent.emit({"firstName": this.firstName, "lastName": this.lastName, "cpfCnpj": this.cpfCnpj, "login": this.login, "password": this.password});
  }

}
