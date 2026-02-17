import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environments';
import { PageResponse } from '../shared/models/page-response.model';
import { Municipios } from './municipios.model';

@Injectable({
  providedIn: 'root',
})
export class MunicipiosService {
  private baseUrl = `${environment.apiUrl}/municipios`;

  constructor(private http: HttpClient) {}

  listar(page: number, size: number, codigoIbge?: string) {
    let url = `${this.baseUrl}?page=${page}&size=${size}`;

    if (codigoIbge) {
      url += `&codigoIbge=${codigoIbge}`;
    }

    return this.http.get<PageResponse<Municipios>>(url);
  }
}
