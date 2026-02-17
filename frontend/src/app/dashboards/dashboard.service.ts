import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environments';

export interface DashboardUf {
  siglaUf: string;
  total: number;
}

export interface DashboardEixo {
  nomeEixo: string;
  total: number;
}

export interface DashboardPeriodo {
  data: string;
  total: number;
}

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  private http = inject(HttpClient);
  private readonly API = `${environment.apiUrl}/dashboard`;

  getUf(filters: any): Observable<DashboardUf[]> {
    return this.http.get<DashboardUf[]>(`${this.API}/uf`, {
      params: this.buildParams(filters),
    });
  }

  getEixo(filters: any): Observable<DashboardEixo[]> {
    return this.http.get<DashboardEixo[]>(`${this.API}/eixo`, {
      params: this.buildParams(filters),
    });
  }

  getPeriodo(filters: any): Observable<DashboardPeriodo[]> {
    return this.http.get<DashboardPeriodo[]>(`${this.API}/periodo`, {
      params: this.buildParams(filters),
    });
  }

  private buildParams(filters: any): HttpParams {
    let params = new HttpParams();

    Object.keys(filters).forEach((key) => {
      if (filters[key]) {
        params = params.set(key, filters[key]);
      }
    });

    return params;
  }
}
