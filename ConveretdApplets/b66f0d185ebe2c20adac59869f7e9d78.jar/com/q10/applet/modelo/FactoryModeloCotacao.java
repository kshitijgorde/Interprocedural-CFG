// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.applet.modelo;

public class FactoryModeloCotacao
{
    private String host;
    private int nAtivos;
    
    public FactoryModeloCotacao(final String host, final int nAtivos) {
        this.host = host;
        this.nAtivos = nAtivos;
    }
    
    public ModeloApplet getModelo(final String s) {
        Object o = null;
        try {
            o = (ModeloApplet)Class.forName(s).newInstance();
            ((IModeloCotacao)o).setNAtivos(this.nAtivos);
            ((IModeloCotacao)o).setUrl(this.host);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return (ModeloApplet)o;
    }
}
