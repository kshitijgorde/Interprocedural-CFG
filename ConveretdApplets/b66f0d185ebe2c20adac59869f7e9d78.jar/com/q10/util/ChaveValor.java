// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.util;

public class ChaveValor
{
    private Object chave;
    private Object valor;
    
    public ChaveValor(final Object chave, final Object valor) {
        this.setChave(chave);
        this.setValor(valor);
    }
    
    public Object getChave() {
        return this.chave;
    }
    
    public void setChave(final Object chave) {
        this.chave = chave;
    }
    
    public Object getValor() {
        return this.valor;
    }
    
    public void setValor(final Object valor) {
        this.valor = valor;
    }
}
