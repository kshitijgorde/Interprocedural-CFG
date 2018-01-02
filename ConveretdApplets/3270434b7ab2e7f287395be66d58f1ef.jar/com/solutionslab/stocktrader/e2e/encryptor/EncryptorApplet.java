// 
// Decompiled by Procyon v0.5.30
// 

package com.solutionslab.stocktrader.e2e.encryptor;

import java.applet.Applet;

public class EncryptorApplet extends Applet
{
    private Encryptor encryptor;
    
    public void init() {
        this.encryptor = new Encryptor();
        System.out.println("Applet initialised ok");
    }
    
    public int encodeAndEncryptUserLoginMsg(final String s, final String s2) {
        return this.encryptor.encodeAndEncryptUserLoginMsg(s, s2);
    }
    
    public int encodeAndEncryptChangePINMsg(final String s, final String s2, final String s3) {
        return this.encryptor.encodeAndEncryptChangePINMsg(s, s2, s3);
    }
    
    public String getEncryptedUserLoginMsg() {
        return this.encryptor.getEncryptedUserLoginMsg();
    }
    
    public String getEncodingParameter() {
        return this.encryptor.getEncodingParameter();
    }
}
