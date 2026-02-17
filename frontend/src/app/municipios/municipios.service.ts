import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environments';
import { Municipios } from './municipios.model';

@Injectable({
  providedIn: 'root',
})
export class MunicipiosService {
  private baseUrl = `${environment.apiUrl}/municipios`;

  constructor(private http: HttpClient) {}

  listarMunicipios(): Observable<string[]> {
    return this.http.get<string[]>(this.baseUrl);
  }

  listarPorCodigoIbge(codigoIbge: string): Observable<Municipios[]> {
    return this.http.get<Municipios[]>(`${this.baseUrl}/${codigoIbge}`);
  }
}
