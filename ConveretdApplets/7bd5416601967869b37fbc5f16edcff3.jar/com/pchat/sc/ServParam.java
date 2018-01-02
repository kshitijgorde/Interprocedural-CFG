// 
// Decompiled by Procyon v0.5.30
// 

package com.pchat.sc;

import java.io.Serializable;

public class ServParam implements Serializable
{
    public short pname;
    public String pvalue;
    public byte[] encoded;
    
    public ServParam() {
        this.pname = 0;
        this.pvalue = null;
        this.encoded = null;
    }
    
    public String toString() {
        return "pname=" + this.pname + " v=" + this.pvalue;
    }
}
