import { Routes } from '@angular/router';
import { EstadosComponent } from './estados/estados';
import { HomeComponent } from './home/home';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'estados', component: EstadosComponent },
];
