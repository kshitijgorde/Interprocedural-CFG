// 
// Decompiled by Procyon v0.5.30
// 

package dispMag;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Movimiento
{
    private static final long serialVersionUID = 1L;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String claveTrabajador;
    private String claveUnicaRegistroPoblacion;
    private int digitoVerificadorNSS;
    private int digitoVerificadorRP;
    private Date fechaHoraEnvio;
    private Date fechaMovimiento;
    private int identificadorFormato;
    private String nombreAsegurado;
    private String numeroSeguridadSocial;
    private String registroPatronal;
    private double salarioDiarioIntegrado;
    private int semanaReducida;
    private int tipoMovimiento;
    private int tipoSalario;
    private int tipoTrabajador;
    private int unidadMedicinaFamiliar;
    private String causaBaja;
    private int codigoError;
    private String mensajeError;
    private char charEsp;
    
    public Movimiento() {
        this.apellidoMaterno = "";
        this.apellidoPaterno = "";
        this.claveTrabajador = "";
        this.claveUnicaRegistroPoblacion = "";
        this.digitoVerificadorNSS = 0;
        this.digitoVerificadorRP = 0;
        this.fechaHoraEnvio = new Date();
        this.fechaMovimiento = new Date();
        this.identificadorFormato = 9;
        this.nombreAsegurado = "";
        this.numeroSeguridadSocial = "";
        this.registroPatronal = "";
        this.salarioDiarioIntegrado = 0.0;
        this.semanaReducida = 0;
        this.tipoMovimiento = 0;
        this.tipoSalario = 0;
        this.tipoTrabajador = 0;
        this.unidadMedicinaFamiliar = 0;
        this.causaBaja = "0";
        this.codigoError = 0;
        this.mensajeError = "";
        this.charEsp = '\u001a';
    }
    
    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }
    
    public void setApellidoMaterno(final String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno.toUpperCase();
        this.apellidoMaterno = this.apellidoMaterno.replace('\u00d1', '#');
        this.apellidoMaterno = this.apellidoMaterno.replace('&', '#');
        this.apellidoMaterno = this.apellidoMaterno.replace(this.charEsp, '#');
    }
    
    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }
    
    public void setApellidoPaterno(final String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno.toUpperCase();
        this.apellidoPaterno = this.apellidoPaterno.replace('\u00d1', '#');
        this.apellidoPaterno = this.apellidoPaterno.replace('&', '#');
        this.apellidoPaterno = this.apellidoPaterno.replace(this.charEsp, '#');
    }
    
    public String getClaveTrabajador() {
        return this.claveTrabajador;
    }
    
    public void setClaveTrabajador(final String claveTrabajador) {
        this.claveTrabajador = claveTrabajador;
    }
    
    public String getClaveUnicaRegistroPoblacion() {
        return this.claveUnicaRegistroPoblacion;
    }
    
    public void setClaveUnicaRegistroPoblacion(final String claveUnicaRegistroPoblacion) {
        this.claveUnicaRegistroPoblacion = claveUnicaRegistroPoblacion;
    }
    
    public int getDigitoVerificadorNSS() {
        return this.digitoVerificadorNSS;
    }
    
    public void setDigitoVerificadorNSS(final int digitoVerificadorNSS) {
        this.digitoVerificadorNSS = digitoVerificadorNSS;
    }
    
    public Date getFechaMovimiento() {
        return this.fechaMovimiento;
    }
    
    public void setFechaMovimiento(final Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }
    
    public int getIdentificadorFormato() {
        return this.identificadorFormato;
    }
    
    public void setIdentificadorFormato(final int identificadorFormato) {
        this.identificadorFormato = identificadorFormato;
    }
    
    public String getNombreAsegurado() {
        return this.nombreAsegurado;
    }
    
    public void setNombreAsegurado(final String nombreAsegurado) {
        this.nombreAsegurado = nombreAsegurado.toUpperCase();
        this.nombreAsegurado = this.nombreAsegurado.replace('\u00d1', '#');
        this.nombreAsegurado = this.nombreAsegurado.replace('&', '#');
        this.nombreAsegurado = this.nombreAsegurado.replace(this.charEsp, '#');
    }
    
    public String getNumeroSeguridadSocial() {
        return this.numeroSeguridadSocial;
    }
    
    public void setNumeroSeguridadSocial(final String numeroSeguridadSocial) {
        this.numeroSeguridadSocial = numeroSeguridadSocial;
    }
    
    public String getRegistroPatronal() {
        return this.registroPatronal;
    }
    
    public void setRegistroPatronal(final String registroPatronal) {
        this.registroPatronal = registroPatronal.toUpperCase();
    }
    
    public double getSalarioDiarioIntegrado() {
        return this.salarioDiarioIntegrado;
    }
    
    public void setSalarioDiarioIntegrado(final double salarioDiarioIntegrado) {
        this.salarioDiarioIntegrado = salarioDiarioIntegrado;
    }
    
    public int getSemanaReducida() {
        return this.semanaReducida;
    }
    
    public void setSemanaReducida(final int semanaReducida) {
        this.semanaReducida = semanaReducida;
    }
    
    public int getTipoMovimiento() {
        return this.tipoMovimiento;
    }
    
    public void setTipoMovimiento(final int tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }
    
    public int getTipoSalario() {
        return this.tipoSalario;
    }
    
    public void setTipoSalario(final int tipoSalario) {
        this.tipoSalario = tipoSalario;
    }
    
    public int getTipoTrabajador() {
        return this.tipoTrabajador;
    }
    
    public void setTipoTrabajador(final int tipoTrabajador) {
        this.tipoTrabajador = tipoTrabajador;
    }
    
    public int getUnidadMedicinaFamiliar() {
        return this.unidadMedicinaFamiliar;
    }
    
    public void setUnidadMedicinaFamiliar(final int unidadMedicinaFamiliar) {
        this.unidadMedicinaFamiliar = unidadMedicinaFamiliar;
    }
    
    public String getCausaBaja() {
        return this.causaBaja;
    }
    
    public void setCausaBaja(final String causaBaja) {
        this.causaBaja = causaBaja;
    }
    
    public Date getFechaHoraEnvio() {
        return this.fechaHoraEnvio;
    }
    
    public void setFechaHoraEnvio(final Date fechaHoraEnvio) {
        this.fechaHoraEnvio = fechaHoraEnvio;
    }
    
    public int getDigitoVerificadorRP() {
        return this.digitoVerificadorRP;
    }
    
    public void setDigitoVerificadorRP(final int digitoVerificadorRP) {
        this.digitoVerificadorRP = digitoVerificadorRP;
    }
    
    public String getMensajeError() {
        return this.mensajeError;
    }
    
    public void setMensajeError(final String string) {
        this.mensajeError = string;
    }
    
    public int getCodigoError() {
        return this.codigoError;
    }
    
    public void setCodigoError(final int codigoError) {
        this.codigoError = codigoError;
    }
    
    public String getValuesDispmag() {
        final StringBuffer registro = new StringBuffer("                                                                                                                                                                        ");
        try {
            final SimpleDateFormat formatoFechaMov = new SimpleDateFormat("ddMMyyyy");
            final SimpleDateFormat formatoFechaEnv = new SimpleDateFormat("ddMMyy");
            final SimpleDateFormat formatoHoraEnv = new SimpleDateFormat("HHmm ");
            final DecimalFormat formatoSDI = new DecimalFormat("000000");
            final DecimalFormat formatoUMF = new DecimalFormat("000");
            final DecimalFormat formatoTM = new DecimalFormat("00");
            registro.insert(0, this.registroPatronal);
            registro.insert(10, this.digitoVerificadorRP);
            registro.insert(11, this.numeroSeguridadSocial);
            registro.insert(21, this.digitoVerificadorNSS);
            registro.insert(22, this.apellidoPaterno);
            registro.insert(49, this.apellidoMaterno);
            registro.insert(76, this.nombreAsegurado);
            registro.insert(103, formatoSDI.format(this.salarioDiarioIntegrado * 100.0));
            registro.insert(109, formatoFechaEnv.format(this.fechaHoraEnvio));
            registro.insert(115, this.tipoTrabajador);
            registro.insert(116, this.tipoSalario);
            registro.insert(117, this.semanaReducida);
            registro.insert(118, formatoFechaMov.format(this.fechaMovimiento));
            registro.insert(126, formatoUMF.format(this.unidadMedicinaFamiliar));
            registro.insert(131, formatoTM.format(this.tipoMovimiento));
            registro.insert(133, formatoHoraEnv.format(this.fechaHoraEnvio));
            registro.insert(138, this.claveTrabajador);
            registro.insert(148, this.causaBaja);
            registro.insert(149, this.claveUnicaRegistroPoblacion);
            registro.insert(167, this.identificadorFormato);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return registro.toString().substring(0, 168);
    }
}
