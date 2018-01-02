// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.applet.ticker;

import com.q10.util.Financeira;
import com.q10.applet.modelo.IModeloCotacao;
import com.q10.applet.bean.ICamposTicker;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.util.Enumeration;
import java.util.Properties;
import java.applet.Applet;
import com.q10.util.Tool;
import java.util.Vector;
import java.util.StringTokenizer;
import com.q10.util.TabFile;
import java.awt.Color;
import java.util.Hashtable;
import com.q10.applet.bean.Marquee;
import com.q10.applet.BaseCotacao;

public class Ticker extends BaseCotacao implements Runnable
{
    private Marquee marquee;
    private Hashtable ativos;
    private String[] nomesLongos;
    private int velocidade;
    private Color corSemVar;
    private Color corVarPos;
    private Color corVarNeg;
    private Color corLegendas;
    private Color corFonteHora;
    private boolean mostraCodigo;
    private boolean mostraNome;
    private boolean mostraHora;
    private String separador;
    private Hashtable campos;
    private FactoryCamposTicker factory;
    private String legMax;
    private String legMin;
    private String legFec;
    private String legUlt;
    private String legVarDia;
    private String legVar30;
    private boolean tipoCompleto;
    private String versao;
    
    public void init() {
        this.ativos = new Hashtable();
        final String parameter = this.getParameter("nomesAtivos");
        int n = 0;
        Properties tabFile = null;
        if (parameter != null) {
            tabFile = new TabFile(this.getCodeBase(), String.valueOf(parameter) + ".properties").getTabFile();
            final Enumeration<String> keys = ((Hashtable<String, V>)tabFile).keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                this.ativos.put(s, new StringTokenizer(tabFile.getProperty(s), ";").nextToken());
                ++n;
            }
        }
        final Vector vector = new Vector<String>();
        final Vector vector2 = new Vector<String>();
        this.tipoCompleto = new Boolean(this.getParameter("tickerCompleto"));
        if (this.tipoCompleto) {
            this.campos = new Hashtable();
        }
        final String s2 = "ativo";
        for (int i = 0; i < n; ++i) {
            final String string = String.valueOf(s2) + (i + 1);
            final String property = tabFile.getProperty(string);
            if (property != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(property, ";");
                vector.addElement(((String)this.ativos.get(string)).toUpperCase());
                stringTokenizer.nextToken();
                vector2.addElement(stringTokenizer.nextToken());
                if (this.tipoCompleto) {
                    final Vector<String> vector3 = new Vector<String>();
                    while (stringTokenizer.hasMoreTokens()) {
                        vector3.addElement(stringTokenizer.nextToken().toUpperCase());
                    }
                    this.campos.put(vector.elementAt(i).toUpperCase(), vector3);
                }
            }
        }
        vector.copyInto(super.codAtivos = new String[vector.size()]);
        vector2.copyInto(this.nomesLongos = new String[vector2.size()]);
        final String property2 = tabFile.getProperty("copyright");
        this.separador = tabFile.getProperty("separador");
        this.initComponents();
        this.marquee.setCopyright(property2);
        final String parameter2 = this.getParameter("velocidade");
        if (parameter2 != null) {
            this.velocidade = Integer.parseInt(parameter2) * 10;
        }
        this.marquee.setEspera((this.velocidade <= 0) ? 50 : (1000 / this.velocidade));
        final String parameter3 = this.getParameter("corSemVar");
        if (parameter3 != null) {
            this.corSemVar = Tool.cor(parameter3);
        }
        final String parameter4 = this.getParameter("corVarNeg");
        if (parameter4 != null) {
            this.corVarNeg = Tool.cor(parameter4);
        }
        final String parameter5 = this.getParameter("corVarPos");
        if (parameter5 != null) {
            this.corVarPos = Tool.cor(parameter5);
        }
        final String parameter6 = this.getParameter("corLegendas");
        if (parameter6 != null) {
            this.corLegendas = Tool.cor(parameter6);
        }
        final String parameter7 = this.getParameter("corFonteHora");
        if (parameter7 != null) {
            this.corFonteHora = Tool.cor(parameter7);
        }
        final String parameter8 = this.getParameter("mostraCodigo");
        if (parameter8 != null) {
            this.mostraCodigo = new Boolean(parameter8);
        }
        final String parameter9 = this.getParameter("mostraNome");
        if (parameter9 != null) {
            this.mostraNome = new Boolean(parameter9);
        }
        final String parameter10 = this.getParameter("mostraHora");
        if (parameter10 != null) {
            this.mostraHora = new Boolean(parameter10);
        }
        this.marquee.setBackground(Tool.cor(this.getParameter("corFundoTicker")));
        this.marquee.setForeground(Tool.cor(this.getParameter("corFonteGeral")));
        this.setFont(Tool.setaFonte(this, "nomeFonte", "tamFonte", "estiloFonte"));
        this.factory = new FactoryCamposTicker(this.tipoCompleto ? "com.q10.applet.bean.CamposAtivoCompleto" : "com.q10.applet.bean.CamposAtivo");
        if (this.tipoCompleto) {
            this.legMax = tabFile.getProperty("max");
            this.legMin = tabFile.getProperty("min");
            this.legFec = tabFile.getProperty("fec");
            this.legUlt = tabFile.getProperty("ult");
            this.legVarDia = tabFile.getProperty("vardia");
            this.legVar30 = tabFile.getProperty("var30");
        }
        super.init();
        System.out.println(this.versao);
    }
    
    private void initComponents() {
        this.setLayout(new GridLayout(1, 1));
        this.add(this.marquee = new Marquee());
    }
    
    public void atualizaTela() {
        final ICamposTicker[] camposAtivo = new ICamposTicker[super.cot.length];
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        for (int i = 0; i < super.codAtivos.length; ++i) {
            hashtable.put(super.codAtivos[i], this.nomesLongos[i]);
        }
        final Hashtable var30Dias = ((IModeloCotacao)super.modelo).getVar30Dias();
        for (int j = 0; j < super.cot.length; ++j) {
            try {
                (camposAtivo[j] = this.factory.getCamposTicker(hashtable.get(super.cot[j].getCod()), super.cot[j].getCod(), true, super.cot[j].getDec())).setIdioma(super.idioma);
                camposAtivo[j].setSeparador(this.separador);
                camposAtivo[j].setUltimo(super.cot[j].getUlt());
                camposAtivo[j].setVar(Financeira.variacao(super.cot[j].getFec(), super.cot[j].getUlt()));
                camposAtivo[j].setCorVarNeg(this.corVarNeg);
                camposAtivo[j].setCorVarPos(this.corVarPos);
                camposAtivo[j].setCorSemVar(this.corSemVar);
                camposAtivo[j].setCorFonteHora(this.corFonteHora);
                camposAtivo[j].setMostraCodigo(this.mostraCodigo);
                camposAtivo[j].setMostraNome(this.mostraNome);
                camposAtivo[j].setMostraHora(this.mostraHora);
                camposAtivo[j].setDataInvalida(super.cot[j].getDataInvalida());
                camposAtivo[j].setDataUlt(super.cot[j].getDataUlt());
                camposAtivo[j].setData(super.cot[j].getDataHoraUlt(), super.cot[j].getHoraInvalida());
                camposAtivo[j].setFec(super.cot[j].getFec());
                camposAtivo[j].setMax(super.cot[j].getMax());
                camposAtivo[j].setMin(super.cot[j].getMin());
                camposAtivo[j].setLegFec(this.legFec);
                camposAtivo[j].setLegMax(this.legMax);
                camposAtivo[j].setLegMin(this.legMin);
                camposAtivo[j].setLegUlt(this.legUlt);
                camposAtivo[j].setLegVarDia(this.legVarDia);
                camposAtivo[j].setLegVar30(this.legVar30);
                camposAtivo[j].setCampos((Vector)this.campos.get(super.cot[j].getCod()));
                camposAtivo[j].setCorLegendas(this.corLegendas);
                camposAtivo[j].setVar30(var30Dias.get(super.cot[j].getCod()));
            }
            catch (NullPointerException ex) {}
        }
        this.marquee.setCamposAtivo(camposAtivo);
    }
    
    public void destroy() {
        this.marquee.destroy();
        super.destroy();
    }
    
    public void run() {
        this.marquee.init();
        this.marquee.start();
        super.run();
    }
    
    public Ticker() {
        this.velocidade = 20;
        this.corSemVar = Color.black;
        this.corVarPos = Color.blue;
        this.corVarNeg = Color.red;
        this.corLegendas = Color.black;
        this.corFonteHora = Color.black;
        this.mostraCodigo = true;
        this.mostraNome = true;
        this.legMax = "max:";
        this.legMin = "min:";
        this.legFec = "fec:";
        this.legUlt = "ult:";
        this.legVarDia = "vardia:";
        this.legVar30 = "var30";
        this.versao = "Ticker - Vers\u00e3o: 2.5.0.01";
    }
}
