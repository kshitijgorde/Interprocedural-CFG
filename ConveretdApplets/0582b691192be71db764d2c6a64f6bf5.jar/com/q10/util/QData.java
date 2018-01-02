// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.util;

import java.util.TimeZone;
import java.util.StringTokenizer;
import java.util.GregorianCalendar;
import java.io.Serializable;

public class QData implements Serializable, Cloneable
{
    private GregorianCalendar data;
    private byte hora;
    private byte minuto;
    
    public QData(final GregorianCalendar gregorianCalendar, final byte hora, final byte minuto) {
        this.hora = -1;
        this.minuto = -1;
        this.data = new GregorianCalendar(gregorianCalendar.get(1), gregorianCalendar.get(2), gregorianCalendar.get(5), hora, minuto);
        this.hora = hora;
        this.minuto = minuto;
    }
    
    public QData(final GregorianCalendar gregorianCalendar, final byte b, final byte b2, final String s) {
        this(gregorianCalendar, b, b2);
    }
    
    public QData(final byte hora, final byte minuto) {
        this.hora = -1;
        this.minuto = -1;
        this.hora = hora;
        this.minuto = minuto;
    }
    
    public QData(final GregorianCalendar data) {
        this.hora = -1;
        this.minuto = -1;
        this.data = data;
    }
    
    public QData(final String s) {
        this.hora = -1;
        this.minuto = -1;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ":");
        this.hora = new Byte(stringTokenizer.nextToken());
        this.minuto = new Byte(stringTokenizer.nextToken());
        this.data = new GregorianCalendar();
    }
    
    public void setData(final GregorianCalendar data) {
        this.data = data;
    }
    
    public GregorianCalendar getData() {
        return this.data;
    }
    
    public void setHora(final byte hora) {
        this.hora = hora;
    }
    
    public byte getHora() {
        return this.hora;
    }
    
    public void setMinuto(final byte minuto) {
        this.minuto = minuto;
    }
    
    public byte getMinuto() {
        return this.minuto;
    }
    
    public String getHoraAsString() {
        if (this.hora == -1 || this.minuto == -1) {
            return "-- : --";
        }
        final StringBuffer sb = new StringBuffer();
        if (this.hora < 10) {
            sb.append("0");
        }
        sb.append(this.hora);
        sb.append(":");
        if (this.minuto < 10) {
            sb.append("0");
        }
        sb.append(this.minuto);
        return sb.toString();
    }
    
    public boolean before(final QData qData) {
        boolean b = true;
        if (this.data != null && qData.data != null) {
            return this.data.getTime().before(qData.data.getTime());
        }
        if ((this.hora != 255 || this.minuto != 255) && (qData.hora != 255 || qData.minuto != 255)) {
            b = (this.hora < qData.hora);
            if (!b && this.hora == qData.hora) {
                b = (this.minuto < qData.minuto);
            }
        }
        return b;
    }
    
    public Object clone() {
        QData qData = null;
        try {
            qData = (QData)super.clone();
            qData.hora = this.hora;
            qData.minuto = this.minuto;
            qData.data = (GregorianCalendar)this.data.clone();
        }
        catch (CloneNotSupportedException ex) {}
        return qData;
    }
    
    public String toString() {
        return "[" + this.data.getTime() + "]";
    }
    
    static {
        TimeZone.setDefault(TimeZone.getTimeZone("AGT"));
    }
}
