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
  codigosIbge = signal<string[]>([]);
  estados = signal<Estados[]>([]);
  selecionado = signal('');

  constructor(private estadoService: EstadoService) {}

  ngOnInit(): void {
    this.estadoService.listarEstados().subscribe((data) => {
      this.codigosIbge.set(data);
    });
  }

  carregarEstados(codigoIbge: string): void {
    this.estadoService.listarPorCodigoIbge(codigoIbge).subscribe((data) => {
      this.estados.set(data);
    });
  }
}
