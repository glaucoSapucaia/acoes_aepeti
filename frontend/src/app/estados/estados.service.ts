import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Estados } from './estados.model';

@Injectable({
  providedIn: 'root',
})
export class EstadoService {
  private baseUrl = 'http://localhost:8080/estados'; // URL do backend

  constructor(private http: HttpClient) {}

  listarEstados(): Observable<string[]> {
    return this.http.get<string[]>(this.baseUrl);
  }

  listarPorCodigoIbge(codigoIbge: string): Observable<Estados[]> {
    return this.http.get<Estados[]>(`${this.baseUrl}/${codigoIbge}`);
  }
}
