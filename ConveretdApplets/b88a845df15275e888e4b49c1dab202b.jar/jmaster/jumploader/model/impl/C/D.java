// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.C;

import jmaster.jumploader.model.api.common.IAttribute;

public class D implements IAttribute
{
    private String A;
    private Object B;
    private boolean C;
    
    public D() {
        this.A = null;
        this.B = null;
        this.C = true;
    }
    
    public D(final String s, final Object value) {
        this.A = null;
        this.B = null;
        this.C = true;
        this.A(s);
        this.setValue(value);
    }
    
    public String toString() {
        return "Attribute,\r\nname=" + this.A + "\r\n" + "value=" + this.B + "\r\n" + "sendToServer=" + this.C + "\r\n" + "";
    }
    
    public String getName() {
        return this.A;
    }
    
    public void A(final String a) {
        this.A = a;
    }
    
    public boolean isSendToServer() {
        return this.C;
    }
    
    public void setSendToServer(final boolean c) {
        this.C = c;
    }
    
    public Object getValue() {
        return this.B;
    }
    
    public void setValue(final Object b) {
        this.B = b;
    }
    
    public String getStringValue() {
        return (this.B == null) ? null : ("" + this.B);
    }
    
    public void setStringValue(final String b) {
        this.B = b;
    }
}
