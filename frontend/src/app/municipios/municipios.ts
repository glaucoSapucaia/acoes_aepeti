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
  municipios = signal<Municipios[]>([]);
  filtroCodigo = signal('');
  paginaAtual = signal(0);
  totalPaginas = signal(0);
  tamanhoPagina = 10;

  constructor(private municipiosService: MunicipiosService) {}

  ngOnInit(): void {
    this.carregarMunicipios();
  }

  aplicarFiltro(): void {
    this.paginaAtual.set(0);
    this.carregarMunicipios();
  }

  carregarMunicipios(): void {
    this.municipiosService
      .listar(this.paginaAtual(), this.tamanhoPagina, this.filtroCodigo())
      .subscribe((data) => {
        this.municipios.set(data.content);
        this.totalPaginas.set(data.totalPages);
      });
  }

  proximaPagina() {
    if (this.paginaAtual() + 1 < this.totalPaginas()) {
      this.paginaAtual.update((p) => p + 1);
      this.carregarMunicipios();
    }
  }

  paginaAnterior() {
    if (this.paginaAtual() > 0) {
      this.paginaAtual.update((p) => p - 1);
      this.carregarMunicipios();
    }
  }
}
