// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.modelo;

import com.q10.util.TimeZoneQ10;
import java.util.Observable;

public abstract class ModeloGUI extends Observable
{
    protected String url;
    protected boolean sslHabilitado;
    
    public ModeloGUI() {
        this.sslHabilitado = false;
        TimeZoneQ10.setTimeZoneQ10();
    }
    
    public abstract void atualiza() throws ExcecaoAtualizaModelo;
    
    public abstract Object getDados();
    
    protected final void notifica() {
        TimeZoneQ10.setTimeZoneQ10();
        this.setChanged();
        this.notifyObservers();
    }
    
    public abstract void habilitaSSL(final boolean p0);
    
    public final boolean isSslHabilitado() {
        return this.sslHabilitado;
    }
}
