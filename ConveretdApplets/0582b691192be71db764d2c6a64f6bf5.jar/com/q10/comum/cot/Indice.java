// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.comum.cot;

import com.q10.comum.constante.DatasVar;

public class Indice implements DatasVar
{
    private String codIndice;
    private String nomeIndice;
    private float ultimo;
    private boolean bUltimo;
    private float variacaoDia;
    private boolean bVariacaoDia;
    private float variacaoSemana;
    private boolean bVariacaoSemana;
    private float variacaoMes;
    private boolean bVariacaoMes;
    private float variacao30Dias;
    private boolean bVariacao30Dias;
    private float variacaoAno;
    private boolean bVariacaoAno;
    private float variacao12Meses;
    private boolean bVariacao12Meses;
    private float[] ultimosMeses;
    private boolean bUltimos12Meses;
    private int mes;
    private int ano;
    private String categoria;
    
    public Indice(final String codIndice) {
        this.mes = -1;
        this.ano = -1;
        this.codIndice = codIndice;
    }
    
    public String getCodIndice() {
        return this.codIndice;
    }
    
    public void setCodIndice(final String codIndice) {
        this.codIndice = codIndice;
    }
    
    public String getNomeIndice() {
        return this.nomeIndice;
    }
    
    public void setNomeIndice(final String nomeIndice) {
        this.nomeIndice = nomeIndice;
    }
    
    public synchronized float getUltimo() {
        return this.ultimo;
    }
    
    public synchronized void setUltimo(final float ultimo) {
        this.ultimo = ultimo;
        this.bUltimo = true;
    }
    
    public synchronized boolean getTemUltimo() {
        return this.bUltimo;
    }
    
    public synchronized float getVariacao12Meses() {
        return this.variacao12Meses;
    }
    
    public synchronized void setVariacao12Meses(final float variacao12Meses) {
        this.variacao12Meses = variacao12Meses;
        this.bVariacao12Meses = true;
    }
    
    public synchronized float getVariacaoDia() {
        return this.variacaoDia;
    }
    
    public synchronized void setVariacaoDia(final float variacaoDia) {
        this.variacaoDia = variacaoDia;
        this.bVariacaoDia = true;
    }
    
    public synchronized boolean contains(final String s) {
        if (s.equals("VAR_SEMANA")) {
            return this.bVariacaoSemana;
        }
        if (s.equals("VAR_MES")) {
            return this.bVariacaoMes;
        }
        if (s.equals("VAR_30_DIAS")) {
            return this.bVariacao30Dias;
        }
        if (s.equals("VAR_ANO")) {
            return this.bVariacaoAno;
        }
        if (s.equals("VAR_12_MESES")) {
            return this.bVariacao12Meses;
        }
        return s.equals("VAR_DIA") && this.bVariacaoDia;
    }
    
    public synchronized float getVariacaoAno() {
        return this.variacaoAno;
    }
    
    public synchronized void setVariacaoAno(final float variacaoAno) {
        this.variacaoAno = variacaoAno;
        this.bVariacaoAno = true;
    }
    
    public synchronized float[] getUltimosMeses() {
        return this.ultimosMeses;
    }
    
    public synchronized void setUltimosMeses(final float[] array) {
        if (array == null) {
            this.ultimosMeses = null;
            return;
        }
        System.arraycopy(array, 0, this.ultimosMeses = new float[array.length], 0, array.length);
    }
    
    public synchronized float getVariacaoSemana() {
        return this.variacaoSemana;
    }
    
    public synchronized void setVariacaoSemana(final float variacaoSemana) {
        this.variacaoSemana = variacaoSemana;
        this.bVariacaoSemana = true;
    }
    
    public synchronized float getVariacaoMes() {
        return this.variacaoMes;
    }
    
    public synchronized void setVariacaoMes(final float variacaoMes) {
        this.variacaoMes = variacaoMes;
        this.bVariacaoMes = true;
    }
    
    public synchronized float getVariacao30Dias() {
        return this.variacao30Dias;
    }
    
    public synchronized void setVariacao30Dias(final float variacao30Dias) {
        this.variacao30Dias = variacao30Dias;
        this.bVariacao30Dias = true;
    }
    
    public boolean equals(final Object o) {
        return o instanceof Indice && ((Indice)o).getCodIndice().equalsIgnoreCase(this.codIndice);
    }
    
    public synchronized int getMes() {
        return this.mes;
    }
    
    public synchronized void setMes(final int mes) {
        this.mes = mes;
    }
    
    public synchronized int getAno() {
        return this.ano;
    }
    
    public synchronized void setAno(final int ano) {
        this.ano = ano;
    }
    
    public void setCategoria(final String categoria) {
        this.categoria = categoria;
    }
    
    public String getCategoria() {
        return this.categoria;
    }
    
    public String toString() {
        return this.codIndice;
    }
}
