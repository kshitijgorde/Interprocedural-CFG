// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils.synthetic;

public class SynthesisException extends Exception
{
    int code;
    public static final int SYNTAX = 0;
    public static final int UNSUPPORTED = 1;
    public static final int REIFIED = 2;
    public static final int UNREIFIED = 3;
    public static final int WRONG_OWNER = 4;
    public static final String[] errToString;
    
    static {
        errToString = new String[] { "(Syntax error; specific message should be passed in)", "Feature not yet supported", "Can't change features of 'real' class", "Can't yet instantiate/invoke without 'real' class", "Can't add Member to an object other than its declarer" };
    }
    
    public SynthesisException(final int code) {
        super(SynthesisException.errToString[code]);
        this.code = code;
    }
    
    public SynthesisException(final int code, final String msg) {
        super(msg);
        this.code = code;
    }
    
    int getCode() {
        return this.code;
    }
}
