// 
// Decompiled by Procyon v0.5.30
// 

package dispMag;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.applet.Applet;

public class AppletDispMag extends Applet
{
    private static final long serialVersionUID = 7683737746617251932L;
    private StringBuffer movimientos;
    private static final String CR = "\n";
    private static final SimpleDateFormat FORMATO_FECHA;
    
    static {
        FORMATO_FECHA = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    public AppletDispMag() {
        this.movimientos = new StringBuffer();
        this.reiniciaRegistros();
    }
    
    public void init() {
        this.reiniciaRegistros();
    }
    
    public void reiniciaRegistros() {
        this.movimientos = new StringBuffer();
    }
    
    public void agregaMov(final String rp, final String nss, final String dvNss, final String apPat, final String apMat, final String nombre, final String sdi, final String tipoTrab, final String tipoSal, final String semRec, final String fechaMov, final String umf, final String tipoMov, final String claveTrab, final String causaBaja, final String curp) {
        System.out.println(String.valueOf(rp) + " " + nss + " " + dvNss + " " + apPat + " " + apMat + " " + nombre + " " + sdi + " " + tipoTrab + " " + tipoSal + " " + semRec + " " + fechaMov + " " + umf + " " + tipoMov + " " + claveTrab + " " + causaBaja + " " + curp);
        final Movimiento mov = new Movimiento();
        mov.setRegistroPatronal(rp);
        mov.setDigitoVerificadorRP(this.calculaDigVer(rp));
        mov.setNumeroSeguridadSocial(nss);
        mov.setDigitoVerificadorNSS(new Integer(dvNss));
        mov.setApellidoPaterno(apPat);
        mov.setApellidoMaterno(apMat);
        mov.setNombreAsegurado(nombre);
        if (tipoMov.equals("2") || tipoMov.equals("02")) {
            mov.setSalarioDiarioIntegrado(0.0);
            mov.setTipoSalario(0);
            mov.setSemanaReducida(0);
            mov.setCausaBaja(causaBaja);
        }
        else {
            mov.setSalarioDiarioIntegrado(new Double(sdi));
            mov.setTipoSalario(new Integer(tipoSal));
            mov.setSemanaReducida(new Integer(semRec));
            mov.setCausaBaja("0");
        }
        mov.setTipoTrabajador(new Integer(tipoTrab));
        try {
            mov.setFechaMovimiento(AppletDispMag.FORMATO_FECHA.parse(fechaMov));
        }
        catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        try {
            mov.setUnidadMedicinaFamiliar(new Integer(umf));
        }
        catch (Exception e2) {
            mov.setUnidadMedicinaFamiliar(0);
        }
        mov.setTipoMovimiento(new Integer(tipoMov));
        mov.setClaveTrabajador(claveTrab);
        mov.setClaveUnicaRegistroPoblacion(curp);
        this.movimientos.append(String.valueOf(mov.getValuesDispmag()) + "\n");
        System.out.println(String.valueOf(mov.getValuesDispmag()) + "\n");
    }
    
    private short calculaDigVer(final String regPatron) {
        final byte[] caracteres = regPatron.getBytes();
        final int[] calculo = new int[regPatron.length() + 1];
        switch (regPatron.charAt(0)) {
            case 'A': {
                calculo[calculo[0] = 1] = 0;
                break;
            }
            case 'B': {
                calculo[calculo[0] = 1] = 1;
                break;
            }
            case 'C': {
                calculo[calculo[0] = 1] = 2;
                break;
            }
            case 'D': {
                calculo[calculo[0] = 1] = 3;
                break;
            }
            case 'E': {
                calculo[calculo[0] = 1] = 4;
                break;
            }
            case 'F': {
                calculo[calculo[0] = 1] = 5;
                break;
            }
            case 'G': {
                calculo[calculo[0] = 1] = 6;
                break;
            }
            case 'H': {
                calculo[calculo[0] = 1] = 7;
                break;
            }
            case 'I': {
                calculo[calculo[0] = 1] = 8;
                break;
            }
            case 'J': {
                calculo[calculo[0] = 1] = 9;
                break;
            }
            case 'K': {
                calculo[0] = 2;
                calculo[1] = 0;
                break;
            }
            case 'L': {
                calculo[0] = 2;
                calculo[1] = 1;
                break;
            }
            case 'M': {
                calculo[1] = (calculo[0] = 2);
                break;
            }
            case 'N': {
                calculo[0] = 2;
                calculo[1] = 3;
                break;
            }
            case 'O': {
                calculo[0] = 2;
                calculo[1] = 4;
                break;
            }
            case 'P': {
                calculo[0] = 2;
                calculo[1] = 5;
                break;
            }
            case 'Q': {
                calculo[0] = 2;
                calculo[1] = 6;
                break;
            }
            case 'R': {
                calculo[0] = 2;
                calculo[1] = 7;
                break;
            }
            case 'S': {
                calculo[0] = 2;
                calculo[1] = 8;
                break;
            }
            case 'T': {
                calculo[0] = 2;
                calculo[1] = 9;
                break;
            }
            case 'U': {
                calculo[0] = 3;
                calculo[1] = 0;
                break;
            }
            case 'V': {
                calculo[0] = 3;
                calculo[1] = 1;
                break;
            }
            case 'W': {
                calculo[0] = 3;
                calculo[1] = 2;
                break;
            }
            case 'X': {
                calculo[1] = (calculo[0] = 3);
                break;
            }
            case 'Y': {
                calculo[0] = 3;
                calculo[1] = 4;
                break;
            }
            case 'Z': {
                calculo[0] = 3;
                calculo[1] = 5;
                break;
            }
            default: {
                calculo[0] = 0;
                calculo[1] = regPatron.charAt(0) - '0';
                break;
            }
        }
        for (int i = 1; i < caracteres.length; ++i) {
            int num = caracteres[i];
            num -= 48;
            calculo[i + 1] = num;
        }
        int resultado = 0;
        for (int j = 0; j < calculo.length; ++j) {
            if (j % 2 == 0) {
                final int[] array = calculo;
                final int n = j;
                array[n] *= 2;
            }
            resultado += calculo[j] / 10 + calculo[j] % 10;
        }
        resultado %= 10;
        if (resultado == 0) {
            return (short)resultado;
        }
        return (short)(10 - resultado);
    }
    
    public String generaDispMag() {
        return this.movimientos.toString();
    }
}
