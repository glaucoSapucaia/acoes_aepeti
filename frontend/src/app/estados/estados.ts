import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Estados } from './estados.model';
import { EstadoService } from './estados.service';

@Component({
  selector: 'app-estados',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './estados.html',
  styleUrls: ['./estados.scss'],
})
export class EstadosComponent implements OnInit {
  estados = signal<Estados[]>([]);
  filtroCodigo = signal('');
  paginaAtual = signal(0);
  totalPaginas = signal(0);
  tamanhoPagina = 10;

  constructor(private estadoService: EstadoService) {}

  ngOnInit(): void {
    this.carregarEstados();
  }

  aplicarFiltro(): void {
    this.paginaAtual.set(0); // 👈 volta para primeira página
    this.carregarEstados();
  }

  carregarEstados(): void {
    this.estadoService
      .listar(this.paginaAtual(), this.tamanhoPagina, this.filtroCodigo())
      .subscribe((data) => {
        this.estados.set(data.content);
        this.totalPaginas.set(data.totalPages);
      });
  }

  getNomeEstado(codigoIbge: number): string {
    return this.estadosMap[codigoIbge] ?? 'Desconhecido';
  }

  proximaPagina() {
    if (this.paginaAtual() + 1 < this.totalPaginas()) {
      this.paginaAtual.update((p) => p + 1);
      this.carregarEstados();
    }
  }

  paginaAnterior() {
    if (this.paginaAtual() > 0) {
      this.paginaAtual.update((p) => p - 1);
      this.carregarEstados();
    }
  }

  private readonly estadosMap: Record<number, string> = {
    11: 'Rondônia (RO)',
    12: 'Acre (AC)',
    13: 'Amazonas (AM)',
    14: 'Roraima (RR)',
    15: 'Pará (PA)',
    16: 'Amapá (AP)',
    17: 'Tocantins (TO)',
    21: 'Maranhão (MA)',
    22: 'Piauí (PI)',
    23: 'Ceará (CE)',
    24: 'Rio Grande do Norte (RN)',
    25: 'Paraíba (PB)',
    26: 'Pernambuco (PE)',
    27: 'Alagoas (AL)',
    28: 'Sergipe (SE)',
    29: 'Bahia (BA)',
    31: 'Minas Gerais (MG)',
    32: 'Espírito Santo (ES)',
    33: 'Rio de Janeiro (RJ)',
    35: 'São Paulo (SP)',
    41: 'Paraná (PR)',
    42: 'Santa Catarina (SC)',
    43: 'Rio Grande do Sul (RS)',
    50: 'Mato Grosso do Sul (MS)',
    51: 'Mato Grosso (MT)',
    52: 'Goiás (GO)',
    53: 'Distrito Federal (DF)',
  };
}
