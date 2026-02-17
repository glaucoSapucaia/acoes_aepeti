import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import {
  ApexAxisChartSeries,
  ApexChart,
  ApexNonAxisChartSeries,
  ApexTitleSubtitle,
  ApexXAxis,
  NgApexchartsModule,
} from 'ng-apexcharts';
import { DashboardService } from './dashboard.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, NgApexchartsModule],
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.scss'],
})
export class DashboardComponent implements OnInit {
  private fb = new FormBuilder();
  private service = new DashboardService();

  filterForm = this.fb.group({
    uf: [null as string | null],
    eixo: [null as string | null],
    dataInicio: [null as string | null],
    dataFim: [null as string | null],
  });

  // ===== Gráficos =====
  ufSeries = signal<ApexAxisChartSeries>([]);
  ufXAxis = signal<ApexXAxis>({
    categories: [],
    labels: { style: { colors: '#e0e0e0' } },
    axisBorder: { color: '#333' },
    axisTicks: { color: '#333' },
  });
  eixoSeries = signal<ApexNonAxisChartSeries>([]);
  eixoLabels = signal<string[]>([]);
  periodoSeries = signal<ApexAxisChartSeries>([]);
  periodoXAxis = signal<ApexXAxis>({
    categories: [],
    labels: { style: { colors: '#e0e0e0' } },
    axisBorder: { color: '#333' },
    axisTicks: { color: '#333' },
  });

  ufChart: ApexChart = {
    type: 'bar',
    height: 350,
    background: '#1f1f1f', // fundo escuro
    foreColor: '#e0e0e0', // textos do chart
    toolbar: { show: true },
    zoom: { enabled: true },
  };

  ufTitle: ApexTitleSubtitle = {
    text: 'Distribuição por UF',
    style: { color: '#e0e0e0', fontSize: '16px' },
  };

  eixoChart: ApexChart = {
    type: 'pie',
    height: 350,
    background: '#1f1f1f',
    foreColor: '#e0e0e0',
  };

  periodoChart: ApexChart = {
    type: 'line',
    height: 350,
    background: '#1f1f1f',
    foreColor: '#e0e0e0',
    toolbar: { show: true },
    zoom: { enabled: true },
  };

  ngOnInit(): void {
    const hoje = new Date();
    const seisMesesAtras = new Date();
    seisMesesAtras.setMonth(hoje.getMonth() - 6);

    this.filterForm.patchValue({
      dataInicio: seisMesesAtras.toISOString().slice(0, 10),
      dataFim: hoje.toISOString().slice(0, 10),
    });

    // 🔹 Busca inicial
    this.buscarDados();

    // 🔹 Atualiza gráficos ao mudar filtros
    this.filterForm.valueChanges.subscribe(() => this.buscarDados());
  }

  buscarDados() {
    const filters = this.filterForm.value;

    // 🔹 Gráfico UF
    this.service.getUf(filters).subscribe((data) => {
      this.ufSeries.set([{ name: 'Total', data: data.map((d) => d.total) }]);
      this.ufXAxis.set({ ...this.ufXAxis(), categories: data.map((d) => d.siglaUf) });
    });

    // 🔹 Gráfico Eixo
    this.service.getEixo(filters).subscribe((data) => {
      this.eixoSeries.set(data.map((d) => d.total));
      this.eixoLabels.set(data.map((d) => d.nomeEixo));
    });

    // 🔹 Gráfico Período
    this.service.getPeriodo(filters).subscribe((data) => {
      this.periodoSeries.set([{ name: 'Total', data: data.map((d) => d.total) }]);
      this.periodoXAxis.set({ ...this.periodoXAxis(), categories: data.map((d) => d.data) });
    });
  }

  hasUfData(): boolean {
    const s = this.ufSeries();
    if (!s || s.length === 0) return false;

    const first = s[0];
    if (typeof first === 'object' && 'data' in first) {
      return Array.isArray(first.data) && first.data.length > 0;
    }
    return false;
  }

  hasEixoData(): boolean {
    const s = this.eixoSeries();
    return Array.isArray(s) && s.length > 0 && s.some((v) => typeof v === 'number' && v > 0);
  }

  hasPeriodoData(): boolean {
    const s = this.periodoSeries();
    if (!s || s.length === 0) return false;

    const first = s[0];
    if (typeof first === 'object' && 'data' in first) {
      return Array.isArray(first.data) && first.data.length > 0;
    }
    return false;
  }
}
