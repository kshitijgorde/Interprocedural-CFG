// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.applet.modelo;

import com.q10.modelo.ExcecaoAtualizaModelo;
import com.q10.modelo.ModeloGUI;

public abstract class ModeloApplet extends ModeloGUI
{
    protected String origem;
    protected String licenca;
    protected String idioma;
    
    public void setOrigem(final String origem) {
        this.origem = origem;
    }
    
    public void setLicenca(final String licenca) {
        this.licenca = licenca;
    }
    
    public void setIdioma(final String idioma) {
        this.idioma = idioma;
    }
    
    public final void atualiza() throws ExcecaoAtualizaModelo {
        this.atualizaDados();
        this.notifica();
    }
    
    public abstract void atualizaDados() throws ExcecaoAtualizaModelo;
    
    public ModeloApplet() {
        this.origem = " ";
        this.licenca = " ";
        this.idioma = "pt";
    }
}
