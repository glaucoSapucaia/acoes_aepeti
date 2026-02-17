import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { NgApexchartsModule } from 'ng-apexcharts';
import { EixoChart, PeriodoChart, UfChart } from './charts.models';
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

  // ===== Gráficos como classes =====
  ufChartClass = new UfChart();
  eixoChartClass = new EixoChart();
  periodoChartClass = new PeriodoChart();

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
      this.ufChartClass.series.set([{ name: 'Total', data: data.map((d) => d.total) }]);
      this.ufChartClass.xaxis.set({
        ...this.ufChartClass.xaxis(),
        categories: data.map((d) => d.siglaUf),
      });
    });

    // 🔹 Gráfico Eixo
    this.service.getEixo(filters).subscribe((data) => {
      this.eixoChartClass.series.set(data.map((d) => d.total));
      this.eixoChartClass.labels.set(data.map((d) => d.nomeEixo));
    });

    // 🔹 Gráfico Período
    this.service.getPeriodo(filters).subscribe((data) => {
      this.periodoChartClass.series.set([{ name: 'Total', data: data.map((d) => d.total) }]);
      this.periodoChartClass.xaxis.set({
        ...this.periodoChartClass.xaxis(),
        categories: data.map((d) => d.data),
      });
    });
  }

  hasUfData(): boolean {
    const s = this.ufChartClass.series();
    return s.length > 0 && Array.isArray(s[0].data) && s[0].data.length > 0;
  }

  hasEixoData(): boolean {
    const s = this.eixoChartClass.series();
    return s.length > 0 && s.some((v) => typeof v === 'number' && v > 0);
  }

  hasPeriodoData(): boolean {
    const s = this.periodoChartClass.series();
    return s.length > 0 && Array.isArray(s[0].data) && s[0].data.length > 0;
  }
}
