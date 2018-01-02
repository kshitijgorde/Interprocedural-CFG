// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.applet.modelo;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;
import com.q10.util.QData;
import java.util.Vector;
import java.util.StringTokenizer;
import com.q10.modelo.ExcecaoAtualizaModelo;
import com.q10.applet.conexao.ConServlet;
import com.q10.comum.cot.Cotacao;
import java.util.Hashtable;

public class ModeloCotacao extends ModeloApplet implements IModeloCotacao
{
    private String erro;
    private Hashtable param;
    private Cotacao[] cot;
    
    public ModeloCotacao(final String url, final int nAtivos) {
        this.setUrl(url);
        this.setNAtivos(nAtivos);
        this.param = new Hashtable();
    }
    
    public ModeloCotacao() {
        this.param = new Hashtable();
    }
    
    public void setUrl(final String s) {
        super.url = String.valueOf(super.sslHabilitado ? "https://" : "http://") + s + "/finansite2/servlet/request";
    }
    
    public void setNAtivos(final int n) {
        this.cot = new Cotacao[n];
    }
    
    public void atualizaDados() throws ExcecaoAtualizaModelo {
        final ConServlet conServlet = new ConServlet();
        try {
            conServlet.abreConexao(super.url);
            conServlet.enviaComando(conServlet.setParametros(this.param));
            this.parse(conServlet.recebeResposta2());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.erro = conServlet.getErro();
            throw new ExcecaoAtualizaModelo("ModeloCotacao: " + ex);
        }
        finally {
            conServlet.terminaConexao();
        }
    }
    
    public String getErro() {
        return this.erro;
    }
    
    public void setAtivos(final String[] ativos, final String[] array) {
        final Hashtable hashtable = new Hashtable<String, String>();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            final StringTokenizer stringTokenizer = new StringTokenizer(array[i], ":");
            stringTokenizer.nextElement();
            final String nextToken = stringTokenizer.nextToken();
            if (!hashtable.containsKey(nextToken)) {
                hashtable.put(nextToken, array[i]);
                sb.append(nextToken);
                sb.append(";");
            }
        }
        this.param.put("futuros", sb.toString());
        this.setAtivos(ativos);
    }
    
    public void setAtivos(final String[] array) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(array[i]);
            sb.append(";");
        }
        this.param.put("codigos", sb.toString());
        this.param.put("controle", "cota");
        this.param.put("tipo", "cotacaoCache");
        this.param.put("licenca", (super.licenca != null) ? super.licenca : "nd");
        this.param.put("origem", (super.origem != null) ? super.origem : "nd");
    }
    
    private void parse(final byte[] array) {
        final StringTokenizer stringTokenizer = new StringTokenizer(new String(array), ";");
        final Vector vector = new Vector<Cotacao>();
        while (stringTokenizer.hasMoreElements()) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), ":");
            final String nextToken = stringTokenizer2.nextToken();
            final Cotacao cotacao = new Cotacao(nextToken);
            try {
                cotacao.setUlt(new Float(stringTokenizer2.nextToken()));
                cotacao.setDataUlt(new QData(this.getData(stringTokenizer2.nextToken())));
                cotacao.setDataHoraUlt(new QData(this.getData(stringTokenizer2.nextToken())));
                cotacao.setDataInvalida(new Integer(stringTokenizer2.nextToken()) != 0);
                cotacao.setHoraInvalida(new Integer(stringTokenizer2.nextToken()) != 0);
                cotacao.setMax(new Float(stringTokenizer2.nextToken()));
                cotacao.setMin(new Float(stringTokenizer2.nextToken()));
                cotacao.setAbt(new Float(stringTokenizer2.nextToken()));
                cotacao.setFec(new Float(stringTokenizer2.nextToken()));
                cotacao.setDataFec(new QData(this.getData(stringTokenizer2.nextToken())));
                cotacao.setVolume(new Float(stringTokenizer2.nextToken()));
                cotacao.setQuantidade(new Float(stringTokenizer2.nextToken()));
                cotacao.setDec(new Integer(stringTokenizer2.nextToken()));
            }
            catch (NumberFormatException ex) {
                System.out.println(String.valueOf(nextToken) + ": " + ex);
            }
            catch (NoSuchElementException ex2) {
                System.out.println(String.valueOf(nextToken) + ": " + ex2);
            }
            finally {
                vector.addElement(cotacao);
            }
        }
        vector.copyInto(this.cot = new Cotacao[vector.size()]);
    }
    
    private GregorianCalendar getData(final String s) {
        final long longValue = new Long(s);
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date(longValue));
        return gregorianCalendar;
    }
    
    public Object getDados() {
        return this.cot;
    }
    
    public void setArquivo(final String s) {
    }
    
    public Hashtable getVar30Dias() {
        return null;
    }
    
    public String getTipo() {
        return "com.q10.applet.modelo.ModeloCotacao";
    }
    
    public void habilitaSSL(final boolean sslHabilitado) {
        super.sslHabilitado = sslHabilitado;
    }
}
