import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Municipios } from './municipios.model';
import { MunicipiosService } from './municipios.service';

@Component({
  selector: 'app-municipios',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './municipios.html',
  styleUrls: ['./municipios.scss'],
})
export class MunicipiosComponent implements OnInit {
  codigosIbge = signal<string[]>([]);
  municipios = signal<Municipios[]>([]);
  selecionado = signal('');

  constructor(private municipiosService: MunicipiosService) {}

  ngOnInit(): void {
    this.municipiosService.listarMunicipios().subscribe((data) => {
      this.codigosIbge.set(data);
    });
  }

  carregarMunicipios(codigoIbge: string): void {
    this.municipiosService.listarPorCodigoIbge(codigoIbge).subscribe((data) => {
      this.municipios.set(data);
    });
  }
}
