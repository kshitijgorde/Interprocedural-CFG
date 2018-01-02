// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.modelo;

import java.util.Observable;

public abstract class ModeloGUI extends Observable
{
    protected String url;
    
    public abstract void atualiza() throws ExcecaoAtualizaModelo;
    
    public abstract Object getDados();
    
    protected final void notifica() {
        this.setChanged();
        this.notifyObservers();
    }
}
