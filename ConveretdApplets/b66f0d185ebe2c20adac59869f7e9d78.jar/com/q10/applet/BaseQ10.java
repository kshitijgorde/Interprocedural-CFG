// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.applet;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import com.q10.modelo.ExcecaoAtualizaModelo;
import java.awt.Cursor;
import com.q10.applet.modelo.ModeloApplet;
import java.util.Observer;
import java.applet.Applet;

public abstract class BaseQ10 extends Applet implements Observer, Runnable
{
    protected String[] codAtivos;
    protected Thread thrApplet;
    protected String licenca;
    protected String origem;
    protected String idioma;
    protected boolean bDebug;
    protected boolean ehSSL;
    protected Applet pThis;
    protected ModeloApplet modelo;
    private boolean terminou;
    protected long tempoEspera;
    
    public void init() {
        if (this.pThis == null) {
            this.pThis = this;
        }
        this.ehSSL = this.pThis.getCodeBase().getProtocol().equalsIgnoreCase("https");
        final String parameter = this.pThis.getParameter("confregional");
        if (parameter != null) {
            this.idioma = parameter;
        }
        this.bDebug = "true".equals(this.pThis.getParameter("debug"));
    }
    
    public void start() {
        (this.thrApplet = new Thread(this)).start();
    }
    
    public void destroy() {
        this.modelo.deleteObserver(this);
        this.modelo = null;
        this.thrApplet = null;
        this.terminou = true;
        System.gc();
    }
    
    protected void atualizaModelo() {
        try {
            this.setCursor(new Cursor(3));
            this.modelo.atualiza();
        }
        catch (ExcecaoAtualizaModelo excecaoAtualizaModelo) {
            this.msg(excecaoAtualizaModelo.getMessage());
        }
        finally {
            this.setCursor(new Cursor(0));
        }
    }
    
    protected synchronized boolean getTerminou() {
        return this.terminou;
    }
    
    public void run() {
        while (!this.getTerminou()) {
            try {
                this.atualizaModelo();
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
            this.cochila(this.tempoEspera);
        }
    }
    
    public abstract void update(final Observable p0, final Object p1);
    
    public abstract void atualizaTela();
    
    public void cochila(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    protected void msg(final String s) {
        if (this.bDebug) {
            System.err.println(s);
        }
    }
    
    public static void abreJanela(final Applet applet, final String s) {
        try {
            applet.getAppletContext().showDocument(new URL(s), "_blank");
        }
        catch (MalformedURLException ex) {
            System.out.println(String.valueOf(ex) + ": " + s);
        }
    }
    
    public BaseQ10() {
        this.idioma = "br";
        this.tempoEspera = 120000L;
    }
}
