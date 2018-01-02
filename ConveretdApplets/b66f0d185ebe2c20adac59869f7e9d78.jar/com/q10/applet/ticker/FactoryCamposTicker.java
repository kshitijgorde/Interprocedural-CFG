// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.applet.ticker;

import com.q10.applet.bean.ICamposTicker;

public class FactoryCamposTicker
{
    public static final String TIPO_COMUM = "com.q10.applet.bean.CamposAtivo";
    public static final String TIPO_COMPLETO = "com.q10.applet.bean.CamposAtivoCompleto";
    private String tipo;
    
    public FactoryCamposTicker(final String tipo) {
        this.tipo = "com.q10.applet.bean.CamposAtivo";
        this.setTipo(tipo);
    }
    
    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }
    
    public ICamposTicker getCamposTicker(final String nomeAtivo, final String codAtivo, final boolean varPorcento, final int dec) {
        ICamposTicker camposTicker = null;
        try {
            camposTicker = (ICamposTicker)Class.forName(this.tipo).newInstance();
            camposTicker.setNomeAtivo(nomeAtivo);
            camposTicker.setCodAtivo(codAtivo);
            camposTicker.setVarPorcento(varPorcento);
            camposTicker.setDec(dec);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return camposTicker;
    }
}
