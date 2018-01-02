// 
// Decompiled by Procyon v0.5.30
// 

package com.pchat.sc;

import java.io.Serializable;

public class TransPack implements Serializable
{
    public byte[] ident;
    public byte type;
    public short length;
    public byte[] data;
    public boolean isValid;
    
    public TransPack() {
        this.type = 0;
        this.length = 0;
        this.data = null;
        this.isValid = false;
        this.ident = new byte[2];
    }
    
    public String toString() {
        final String string = "TransP:len=" + this.length + " " + "ident=" + this.ident[0] + " " + this.ident[1] + " ";
        String s;
        if (this.data != null) {
            s = string + StringUtil.showByte(this.data);
        }
        else {
            s = string + "=null";
        }
        return s + " " + this.isValid;
    }
}
