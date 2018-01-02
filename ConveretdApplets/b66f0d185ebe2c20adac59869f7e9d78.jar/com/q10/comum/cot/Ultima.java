// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.comum.cot;

import com.q10.util.QData;
import java.util.Vector;
import java.io.Serializable;
import java.util.Hashtable;

public class Ultima extends Hashtable implements Serializable
{
    public static final String ULT = "ult";
    public static final String DATA = "dt";
    public static final String HORA = "hora";
    public static final String MAX = "max";
    public static final String MIN = "min";
    public static final String ABT = "abt";
    public static final String VOL = "vol";
    public static final String QTT = "qtt";
    public static final String NUM_NEG = "numNeg";
    private static Vector vCampos;
    private String sData;
    
    public Ultima(final QData qData, final float ultima) {
        this.sData = "n/d";
        if (qData == null) {
            throw new IllegalArgumentException("Data n\u00e3o pode ser nula!");
        }
        this.put("dt", qData);
        this.setUltima(ultima);
    }
    
    public synchronized Object put(final Object o, final Object o2) {
        if (!Ultima.vCampos.contains(o)) {
            throw new IllegalArgumentException("'" + o + "' n\u00e3o \u00e9 v\u00e1lida.");
        }
        return super.put(o, o2);
    }
    
    public QData retornaData() {
        return this.get("dt");
    }
    
    public float ultima() {
        return this.get("ult");
    }
    
    public void setUltima(final float n) {
        this.put("ult", new Float(n));
    }
    
    public void setSData(final int n) {
        this.sData = new Integer(n).toString();
    }
    
    public String toString() {
        return super.toString();
    }
    
    static {
        (Ultima.vCampos = new Vector()).addElement("ult");
        Ultima.vCampos.addElement("dt");
        Ultima.vCampos.addElement("hora");
        Ultima.vCampos.addElement("max");
        Ultima.vCampos.addElement("min");
        Ultima.vCampos.addElement("abt");
        Ultima.vCampos.addElement("vol");
        Ultima.vCampos.addElement("qtt");
        Ultima.vCampos.addElement("numNeg");
    }
}
