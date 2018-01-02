// 
// Decompiled by Procyon v0.5.30
// 

public class Token
{
    public static final int UNKNOWN = -1;
    public static final int COMMENT = 1;
    public static final int OPEN_BRACKET = 2;
    public static final int OPEN_PAREN = 3;
    public static final int OPEN_SQUIGGLY = 4;
    public static final int WORD = 5;
    private static final String CLASS_NAME = "Token";
    public int typeCode;
    public Object value;
    
    public Token(final int typeCode, final Object value) {
        this.typeCode = typeCode;
        this.value = value;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        switch (this.typeCode) {
            case 1: {
                sb.append(";");
                break;
            }
            case -1: {
                sb.append("*UNKNOWN*");
                break;
            }
            case 2: {
                sb.append("[");
                break;
            }
            case 3: {
                sb.append("(");
                break;
            }
            case 4: {
                sb.append("{");
                break;
            }
        }
        if (this.value != null) {
            if (this.value instanceof String) {
                sb.append((String)this.value);
            }
            else {
                sb.append(this.value.toString());
            }
        }
        switch (this.typeCode) {
            case 2: {
                sb.append("]");
                break;
            }
            case 3: {
                sb.append(")");
                break;
            }
            case 4: {
                sb.append("}");
                break;
            }
        }
        return sb.toString();
    }
}
