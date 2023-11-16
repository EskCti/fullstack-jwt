import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-buttons',
  templateUrl: './buttons.component.html',
  styleUrls: ['./buttons.component.css']
})
export class ButtonsComponent {
	@Output() loginEvent = new EventEmitter();
	@Output() logoutEvent = new EventEmitter();
  @Output() groupEvent = new EventEmitter();
  @Output() permissionEvent = new EventEmitter();
  @Output() categoryEvent = new EventEmitter();
  @Output() userEvent = new EventEmitter();
}
