// 
// Decompiled by Procyon v0.5.30
// 

public abstract class XMLNode
{
    public String element_name;
    
    protected abstract void toString(final StringBuffer p0);
    
    protected abstract void I(final StringBuffer p0);
    
    public final String getText() {
        final StringBuffer sb = new StringBuffer();
        this.I(sb);
        return sb.toString();
    }
    
    public final String toString() {
        final StringBuffer sb = new StringBuffer();
        this.toString(sb);
        return sb.toString();
    }
    
    public final String localName() {
        return localName(this.element_name);
    }
    
    public static final String localName(final String s) {
        final int index = s.indexOf(":");
        if (index >= 0) {
            return s.substring(index + 1);
        }
        return s;
    }
    
    void I() {
    }
    
    public static final void appendText(final StringBuffer sb, final String s) {
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '<') {
                sb.append("&lt;");
            }
            else if (char1 == '&') {
                sb.append("&amp;");
            }
            else if (char1 == '\"') {
                sb.append("&quot;");
            }
            else if (char1 == '\'') {
                sb.append("&apos;");
            }
            else {
                sb.append(char1);
            }
        }
    }
}
