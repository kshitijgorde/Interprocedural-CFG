// 
// Decompiled by Procyon v0.5.30
// 

public class XMLText extends XMLNode
{
    public String text;
    
    private final void print() {
        System.out.print(this.text);
    }
    
    protected final void I(final StringBuffer sb) {
        sb.append(this.text);
    }
    
    public final void toString(final StringBuffer sb) {
        XMLNode.appendText(sb, this.text);
    }
}
