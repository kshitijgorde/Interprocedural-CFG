// 
// Decompiled by Procyon v0.5.30
// 

public class StringStream
{
    private static final String CLASS_NAME = "StringStream";
    private int index;
    private int length;
    private String string;
    
    public StringStream(final StringBuffer sb) {
        this(sb.toString());
    }
    
    public StringStream(final String string) {
        this.string = string;
        this.length = string.length();
        this.index = -1;
    }
    
    public char read() {
        if (++this.index < this.length) {
            return this.string.charAt(this.index);
        }
        return '\0';
    }
    
    public char reread() {
        if (this.index >= 0 && this.index < this.length) {
            return this.string.charAt(this.index);
        }
        return '\0';
    }
}
