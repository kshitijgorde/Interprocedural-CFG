import java.io.PrintWriter;

// 
// Decompiled by Procyon v0.5.30
// 

public class BFontAI extends BFont
{
    String face;
    PrintWriter writer;
    
    public void drawChar(final char c, final float n, final float n2) {
        this.drawString(String.valueOf(c), n, n2);
    }
    
    public float stringWidth(final String s) {
        System.err.println("string width not implemented on ai fonts");
        return 0.0f;
    }
    
    public void drawString(String escapeString, final float n, final float n2) {
        escapeString = this.escapeString(escapeString);
        this.writer.print("0 To \r\n");
        this.writer.print("1 0 0 1 " + BGraphicsAI.format(n, 4, 4) + " " + BGraphicsAI.format(((BGraphicsAI)super.parent).flip ? (super.parent.height - n2) : n2, 4, 4) + " 0 Tp \r\n");
        this.writer.print("TP \r\n");
        this.writer.print("/_" + this.face + " " + BGraphicsAI.format(super.size, 4, 4) + " Tf\r\n");
        this.writer.print(BGraphicsAI.format(super.leading, 4, 2) + " 0 Tl\r\n");
        this.writer.print("0 Tr \r\n");
        this.writer.print((super.parent.text_mode >> 1 & 0x7) + " Ta \r\n");
        this.writer.print("(" + escapeString + ") Tx 1 0 Tk\r\n");
        this.writer.print("(\\r) TX \r\n");
        this.writer.print("TO \r\n");
    }
    
    protected String escapeString(String s) {
        if (s == null) {
            System.err.println("null string passed to Illustrator.drawString");
            return "";
        }
        s = s.replace('(', '[');
        s = s.replace(')', ']');
        return s;
    }
    
    public BFontAI(final String face, final BGraphics parent) {
        super.parent = parent;
        this.face = face;
        super.size = 10.0f;
        super.leading = 12.0f;
        this.writer = ((BGraphicsAI)parent).writer;
    }
}
