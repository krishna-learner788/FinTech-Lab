import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/users'; // Replace with your Spring Boot API URL

  constructor(private http: HttpClient) {}

  // Fetch all users
  getUsers(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  // Fetch a user by ID
  getUserById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  // Create a new user
  createUser(user: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, user);
  }

  // Add a new user (alias for createUser)
  addUser(user: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, user);
  }

  // Update an existing user
  updateUser(user: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${user.id}`, user);
  }

  // Delete a user
  deleteUser(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }
}
