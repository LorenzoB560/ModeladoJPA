package com.lbodaszsservidor.modeladojpa.entity.auxiliar;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@SuppressWarnings("ALL")
@Embeddable
public class TarjetaCredito {

    private String numero;
    private int CVV;
    private LocalDate fechaCaducidad;

    public TarjetaCredito(String numero, int CVV, LocalDate fechaCaducidad) {
        setNumero(numero);
        setFechaCaducidad(fechaCaducidad);
        this.CVV = CVV;
    }

    public TarjetaCredito() {
    }

    public static boolean esHoyOFuturo(LocalDate fecha) {
        LocalDate hoy = LocalDate.now();
        return !fecha.isBefore(hoy); // Retorna true si es hoy o en el futuro
    }

    private boolean isValidCreditCard(String cardNumber) {
        int sum = 0;
        boolean alternate = false;

        // Iterate from right to left
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));

            // Double every second digit
            if (alternate) {
                n *= 2;
                // If the result is greater than 9, subtract 9
                if (n > 9) {
                    n -= 9;
                }
            }

            sum += n;
            alternate = !alternate; // Flip the alternate flag
        }

        // The number is valid if the total sum is a multiple of 10
        return (sum % 10 == 0);
    }


    public String getNumero() {
        return this.numero;
    }

    public int getCVV() {
        return this.CVV;
    }

    public LocalDate getFechaCaducidad() {
        return this.fechaCaducidad;
    }

    public void setNumero(String numero) {
        if (isValidCreditCard(numero)) {
            this.numero = numero;
        }
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {

        if (esHoyOFuturo(fechaCaducidad)){
            this.fechaCaducidad = fechaCaducidad;
        }
    }


    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TarjetaCredito)) return false;
        final TarjetaCredito other = (TarjetaCredito) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$numero = this.getNumero();
        final Object other$numero = other.getNumero();
        if (this$numero == null ? other$numero != null : !this$numero.equals(other$numero)) return false;
        if (this.getCVV() != other.getCVV()) return false;
        final Object this$fechaCaducidad = this.getFechaCaducidad();
        final Object other$fechaCaducidad = other.getFechaCaducidad();
        if (this$fechaCaducidad == null ? other$fechaCaducidad != null : !this$fechaCaducidad.equals(other$fechaCaducidad))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TarjetaCredito;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $numero = this.getNumero();
        result = result * PRIME + ($numero == null ? 43 : $numero.hashCode());
        result = result * PRIME + this.getCVV();
        final Object $fechaCaducidad = this.getFechaCaducidad();
        result = result * PRIME + ($fechaCaducidad == null ? 43 : $fechaCaducidad.hashCode());
        return result;
    }

    public String toString() {
        return "TarjetaCredito(numero=" + this.getNumero() + ", CVV=" + this.getCVV() + ", fechaCaducidad=" + this.getFechaCaducidad() + ")";
    }
}
