// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.security;

import com.postx.util.StringConversion;
import java.util.Date;
import java.math.BigInteger;

public class Certificate
{
    public static final String Ident = "$Id: Certificate.java,v 1.3 2011/01/10 05:13:52 blm Exp $";
    protected Object[] fields;
    protected static final int KEY_TYPE = 0;
    protected static final int ISSUE_DATE = 1;
    protected static final int EXPIRES_DATE = 2;
    protected static final int PUBLIC_KEY_MODULUS = 3;
    protected static final int PUBLIC_KEY_EXPONENT = 4;
    protected static final int PRIVATE_KEY_EXPONENT = 5;
    protected static final int OWNER_NAME = 6;
    protected static final int OWNER_EMAIL = 7;
    protected static final int DESCRIPTION = 8;
    protected static final int IMAGE_DATA = 9;
    private static final int FIELD_COUNT = 10;
    
    public BigInteger getPublicKeyModulus() {
        return (BigInteger)this.fields[3];
    }
    
    public String getDescription() {
        return (String)this.fields[8];
    }
    
    public byte[] getImageData() {
        return (byte[])this.fields[9];
    }
    
    public BigInteger getPublicKeyExponent() {
        return (BigInteger)this.fields[4];
    }
    
    public BigInteger getPrivateKeyExponent() {
        return (BigInteger)this.fields[5];
    }
    
    public Date getIssueDate() {
        return (Date)this.fields[1];
    }
    
    protected void parseCertificate(final int n, final long n2, final long n3, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        this.fields = new Object[] { new Integer(n), new Date(n2), new Date(n3), new BigInteger(s, 16), new BigInteger(s2, 16), (s3 != null) ? new BigInteger(s3, 16) : null, s4, s5, s6, (s7 != null) ? StringConversion.hexStringToBytes(s7) : new byte[0] };
    }
    
    public int getKeyType() {
        return (int)this.fields[0];
    }
    
    public String getName() {
        final String name = this.getClass().getName();
        return name.substring(name.lastIndexOf(46) + 1);
    }
    
    public String getOwnerName() {
        return (String)this.fields[6];
    }
    
    public Date getExpiresDate() {
        return (Date)this.fields[2];
    }
    
    public String getOwnerEmail() {
        return (String)this.fields[7];
    }
    
    public static Certificate getInstance(final String s) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return (Certificate)Class.forName("com.postx.client.certs." + s).newInstance();
    }
}
