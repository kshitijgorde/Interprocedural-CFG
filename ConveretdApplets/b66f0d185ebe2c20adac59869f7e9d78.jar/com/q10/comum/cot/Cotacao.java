// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.comum.cot;

import com.q10.util.QData;

public class Cotacao
{
    private static final float INVALIDO = Float.NEGATIVE_INFINITY;
    private String cod;
    private float ult;
    private QData dataHoraUlt;
    private QData dataUlt;
    private float max;
    private float min;
    private float abt;
    private float fec;
    private QData dataFec;
    private boolean dataInvalida;
    private boolean horaInvalida;
    private float volume;
    private float quantidade;
    private int numNeg;
    private int dec;
    
    public Cotacao(final String cod) {
        this.dec = 2;
        this.setCod(cod);
        final float ult = Float.NEGATIVE_INFINITY;
        this.abt = ult;
        this.min = ult;
        this.max = ult;
        this.fec = ult;
        this.ult = ult;
    }
    
    public void setCod(final String s) {
        this.cod = s.toUpperCase();
    }
    
    public String getCod() {
        return this.cod;
    }
    
    public void setUlt(final float ult) {
        this.ult = ult;
    }
    
    public float getUlt() {
        return this.ult;
    }
    
    public void setDataHoraUlt(final QData dataHoraUlt) {
        this.dataHoraUlt = dataHoraUlt;
    }
    
    public QData getDataHoraUlt() {
        return this.dataHoraUlt;
    }
    
    public void setDataUlt(final QData dataUlt) {
        this.dataUlt = dataUlt;
    }
    
    public QData getDataUlt() {
        return this.dataUlt;
    }
    
    public void setMax(final float max) {
        this.max = max;
    }
    
    public float getMax() {
        return this.max;
    }
    
    public void setMin(final float min) {
        this.min = min;
    }
    
    public float getMin() {
        return this.min;
    }
    
    public void setAbt(final float abt) {
        this.abt = abt;
    }
    
    public float getAbt() {
        return this.abt;
    }
    
    public void setFec(final float fec) {
        this.fec = fec;
    }
    
    public float getFec() {
        return this.fec;
    }
    
    public void setDataFec(final QData dataFec) {
        this.dataFec = dataFec;
    }
    
    public QData getDataFec() {
        return this.dataFec;
    }
    
    public void setDataInvalida(final boolean dataInvalida) {
        this.dataInvalida = dataInvalida;
    }
    
    public boolean getDataInvalida() {
        return this.dataInvalida;
    }
    
    public void setHoraInvalida(final boolean horaInvalida) {
        this.horaInvalida = horaInvalida;
    }
    
    public boolean getHoraInvalida() {
        return this.horaInvalida;
    }
    
    public void setVolume(final float volume) {
        this.volume = volume;
    }
    
    public float getVolume() {
        return this.volume;
    }
    
    public void setQuantidade(final float quantidade) {
        this.quantidade = quantidade;
    }
    
    public float getQuantidade() {
        return this.quantidade;
    }
    
    public void setNumNeg(final int numNeg) {
        this.numNeg = numNeg;
    }
    
    public int getNumNeg() {
        return this.numNeg;
    }
    
    public void setDec(final int dec) {
        this.dec = dec;
    }
    
    public int getDec() {
        return this.dec;
    }
    
    public String toString() {
        return this.cod;
    }
}
