// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.data;

public class StandardFunction implements MathObject
{
    private String name;
    private int code;
    
    public StandardFunction(final int n) {
        this(standardFunctionName(n), n);
    }
    
    public StandardFunction(final String name, final int code) {
        this.setName(name);
        this.code = code;
    }
    
    public int getOpCode() {
        return this.code;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public static String standardFunctionName(final int n) {
        switch (n) {
            case -17: {
                return "sin";
            }
            case -18: {
                return "cos";
            }
            case -19: {
                return "tan";
            }
            case -20: {
                return "cot";
            }
            case -21: {
                return "sec";
            }
            case -22: {
                return "csc";
            }
            case -23: {
                return "arcsin";
            }
            case -24: {
                return "arccos";
            }
            case -25: {
                return "arctan";
            }
            case -26: {
                return "abs";
            }
            case -27: {
                return "sqrt";
            }
            case -28: {
                return "exp";
            }
            case -29: {
                return "ln";
            }
            case -30: {
                return "log2";
            }
            case -31: {
                return "log10";
            }
            case -32: {
                return "trunc";
            }
            case -33: {
                return "round";
            }
            case -34: {
                return "floor";
            }
            case -35: {
                return "ceiling";
            }
            case -36: {
                return "cubert";
            }
            default: {
                throw new IllegalArgumentException("Internal Error: Unknown standard function code.");
            }
        }
    }
}
