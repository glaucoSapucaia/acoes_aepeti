import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboards/dashboard';
import { EstadosComponent } from './estados/estados';
import { HomeComponent } from './home/home';
import { MunicipiosComponent } from './municipios/municipios';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'estados', component: EstadosComponent },
  { path: 'municipios', component: MunicipiosComponent },
  { path: 'dashboard', component: DashboardComponent },
];
