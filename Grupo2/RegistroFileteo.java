package Grupo2; 

import java.util.Date;
class RegistroFileteo 
{
    private Date fechaProduccion;
    private String lote;
    private double pesoInicial;
    private double pesoLomos;
    private double pesoDesperdicio;
    private OperarioProduccion operario;

    public RegistroFileteo(Date fechaProduccion, String lote, double pesoInicial, double pesoLomos, double pesoDesperdicio, OperarioProduccion operario) {
        // Validación 1: Conservación de masa (pesoLomos + pesoDesperdicio == pesoInicial)
        // Se usa un margen pequeño (epsilon) por precisión de punto flotante si fuera necesario, pero aquí se evalúa directamente.
        if (Math.abs((pesoLomos + pesoDesperdicio) - pesoInicial) > 0.001) {
            throw new IllegalArgumentException("Error de Invariante de Masa: pesoLomos + pesoDesperdicio debe ser igual al pesoInicial.");
        }
        
        if (pesoInicial <= 0 || pesoLomos < 0 || pesoDesperdicio < 0) {
            throw new IllegalArgumentException("Los pesos no pueden ser negativos y el peso inicial debe ser mayor a cero.");
        }

        this.fechaProduccion = fechaProduccion;
        this.lote = lote;
        this.pesoInicial = pesoInicial;
        this.pesoLomos = pesoLomos;
        this.pesoDesperdicio = pesoDesperdicio;
        this.operario = operario;
    }

    // Método para calcular el porcentaje de rendimiento de los lomos respecto al peso inicial
    public double calcularRendimientoPorcentaje() {
        return (pesoLomos / pesoInicial) * 100.0;
    }

    // Validación 2: Evaluar si el rendimiento es Bueno o Malo
    public String evaluarRendimiento() {
        double rendimiento = calcularRendimientoPorcentaje();
        // Si los lomos representan del 45% al 51% -> Rendimiento Bueno; de lo contrario -> Rendimiento Malo
        if (rendimiento >= 45.0 && rendimiento <= 51.0) {
            return "Rendimiento Bueno";
        } else {
            return "Rendimiento Malo";
        }
    }

 // Getters para consultar los atributos
    public Date getFechaProduccion() {
        return fechaProduccion;
    }

    public String getLote() {
        return lote;
    }

    public OperarioProduccion getOperario() {
        return operario;
    }

    public double getPesoLomos() {
        return pesoLomos;
    }

    public double getPesoDesperdicio() {
        return pesoDesperdicio;
    }

    public double getPesoInicial() {
        return pesoInicial;
    }
}