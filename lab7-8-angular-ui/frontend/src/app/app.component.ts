import { Component } from '@angular/core';
import { UserListComponent } from "./user/user-list/user-list.component";
import { CommonModule } from '@angular/common';
import { provideHttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  imports: [UserListComponent, CommonModule],
  providers: []
})
export class AppComponent {
  title = 'User Management';
}
