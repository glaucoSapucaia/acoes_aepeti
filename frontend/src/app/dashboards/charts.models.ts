import { signal } from '@angular/core';
import {
  ApexAxisChartSeries,
  ApexChart,
  ApexNonAxisChartSeries,
  ApexTheme,
  ApexTitleSubtitle,
  ApexXAxis,
} from 'ng-apexcharts';

// ===== Classe UF =====
export class UfChart {
  public series = signal<ApexAxisChartSeries>([]);
  public xaxis = signal<ApexXAxis>({
    categories: [],
    labels: { style: { colors: '#e0e0e0' } },
    axisBorder: { color: '#333' },
    axisTicks: { color: '#333' },
  });

  public chart: ApexChart = {
    type: 'bar',
    height: 350,
    background: '#1f1f1f',
    foreColor: '#e0e0e0',
    toolbar: {
      show: true,
      tools: {
        download: true,
        selection: true,
        zoom: true,
        zoomin: true,
        zoomout: true,
        pan: true,
        reset: true,
      },
      export: {
        csv: { filename: 'uf_chart', columnDelimiter: ',' },
        svg: { filename: 'uf_chart' },
        png: { filename: 'uf_chart' },
      },
    },
    zoom: { enabled: true },
  };

  public theme: ApexTheme = { mode: 'dark' };

  public title: ApexTitleSubtitle = {
    text: 'Distribuição por UF',
    style: { color: '#e0e0e0', fontSize: '16px' },
  };
}

// ===== Classe Eixo =====
export class EixoChart {
  public series = signal<ApexNonAxisChartSeries>([]);
  public labels = signal<string[]>([]);

  public chart: ApexChart = {
    type: 'pie',
    height: 350,
    background: '#1f1f1f',
    foreColor: '#e0e0e0',
  };

  public theme: ApexTheme = { mode: 'dark' };

  public title: ApexTitleSubtitle = {
    text: 'Distribuição por Eixo',
    style: { color: '#e0e0e0', fontSize: '16px' },
  };
}

// ===== Classe Período =====
export class PeriodoChart {
  public series = signal<ApexAxisChartSeries>([]);
  public xaxis = signal<ApexXAxis>({
    categories: [],
    labels: { style: { colors: '#e0e0e0' } },
    axisBorder: { color: '#333' },
    axisTicks: { color: '#333' },
  });

  public chart: ApexChart = {
    type: 'line',
    height: 350,
    background: '#1f1f1f',
    foreColor: '#e0e0e0',
    toolbar: { show: true },
    zoom: { enabled: true },
  };

  public theme: ApexTheme = { mode: 'dark' };

  public title: ApexTitleSubtitle = {
    text: 'Distribuição por Período',
    style: { color: '#e0e0e0', fontSize: '16px' },
  };
}
