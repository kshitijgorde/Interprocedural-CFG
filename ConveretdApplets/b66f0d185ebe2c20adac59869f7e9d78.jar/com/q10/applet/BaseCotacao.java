// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.applet;

import java.util.Observable;
import com.q10.applet.modelo.IModeloCotacao;
import java.util.Observer;
import com.q10.comum.cot.Cotacao;
import com.q10.applet.modelo.FactoryModeloCotacao;

public abstract class BaseCotacao extends BaseQ10
{
    protected String nomeArquivo;
    private FactoryModeloCotacao factory;
    protected Cotacao[] cot;
    
    public void init() {
        final String parameter = this.getParameter("nomeArquivo");
        this.factory = new FactoryModeloCotacao((parameter == null) ? this.getCodeBase().getHost() : this.getCodeBase().toString(), super.codAtivos.length);
        if (new Boolean(this.getParameter("tickerCompleto"))) {
            super.modelo = this.factory.getModelo("com.q10.applet.modelo.ModeloTickerCompleto");
        }
        else {
            super.modelo = this.factory.getModelo((parameter == null) ? "com.q10.applet.modelo.ModeloCotacao" : "com.q10.applet.modelo.ModeloArquivoCotacao");
        }
        super.modelo.addObserver(this);
        super.modelo.setLicenca(this.getParameter("licenca"));
        super.modelo.setOrigem(this.getDocumentBase().toString());
        ((IModeloCotacao)super.modelo).setAtivos(super.codAtivos);
        if (parameter != null) {
            this.nomeArquivo = parameter;
            ((IModeloCotacao)super.modelo).setArquivo(this.nomeArquivo);
        }
        super.init();
    }
    
    public void update(final Observable observable, final Object o) {
        this.cot = (Cotacao[])super.modelo.getDados();
        this.atualizaTela();
    }
}
