// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.applet.modelo;

import java.util.Hashtable;

public interface IModeloCotacao
{
    public static final String MODELO_ARQUIVO = "com.q10.applet.modelo.ModeloArquivoCotacao";
    public static final String MODELO_SERVLET = "com.q10.applet.modelo.ModeloCotacao";
    public static final String MODELO_TICKER_COMPLETO = "com.q10.applet.modelo.ModeloTickerCompleto";
    
    void setAtivos(final String[] p0);
    
    void setUrl(final String p0);
    
    void setNAtivos(final int p0);
    
    void setArquivo(final String p0);
    
    Hashtable getVar30Dias();
}
