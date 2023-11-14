import { Component } from '@angular/core';
import { AxiosService } from '../axios.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent {
	componentToShow: string = "welcome";

	constructor(private axiosService: AxiosService) { }

	showComponent(componentToShow: string): void {
    this.componentToShow = componentToShow;
  }

	onLogin(input: any): void {
    console.log("onLogin", input);
		this.axiosService.request(
		    "POST",
		    "/login",
		    {
            cpfCnpj: input.cpfCnpj,
		        password: input.password
		    }).then(
		    response => {
		        this.axiosService.setAuthToken(response.data.token);
		        this.componentToShow = "messages";
		    }).catch(
		    error => {
		        this.axiosService.setAuthToken(null);
		        this.componentToShow = "welcome";
		    }
		);

	}

	onRegister(input: any): void {
    console.log("onRegister", input);
		this.axiosService.request(
		    "POST",
		    "/register",
		    {
		        firstName: input.firstName,
		        lastName: input.lastName,
		        login: input.login,
            cpfCnpj: input.cpfCnpj,
            career: input.career,
		        password: input.password
		    }).then(
		    response => {
		        this.axiosService.setAuthToken(response.data.token);
		        this.componentToShow = "messages";
		    }).catch(
		    error => {
		        this.axiosService.setAuthToken(null);
		        this.componentToShow = "welcome";
		    }
		);
	}
}
