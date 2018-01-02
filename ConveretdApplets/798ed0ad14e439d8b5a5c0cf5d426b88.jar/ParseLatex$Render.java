import java.util.Hashtable;
import java.util.Dictionary;

// 
// Decompiled by Procyon v0.5.30
// 

final class ParseLatex$Render
{
    ParseLatex$UserRender I;
    Dictionary Z;
    String C;
    int B;
    ParseLatex$CallbackRender append;
    private final ParseLatex this$0;
    
    ParseLatex$Render(final ParseLatex this$0) {
        this.this$0 = this$0;
        this.append = new ParseLatex$Render$1(this);
    }
    
    final void I(final ParseLatex$UserRender i) {
        this.I = i;
        this.Z = new Hashtable();
        this.B = 5;
        final ParseLatex$RenderitzadorPrint parseLatex$RenderitzadorPrint = new ParseLatex$RenderitzadorPrint(this.this$0);
        for (int j = 32; j < 256; ++j) {
            if (j != 92 && j != 94) {
                this.Z.put(new Integer(j), parseLatex$RenderitzadorPrint);
            }
        }
        this.Z.put(new Integer(65554), parseLatex$RenderitzadorPrint);
        this.Z.put(new Integer(8364), parseLatex$RenderitzadorPrint);
        for (int k = 913; k < 974; ++k) {
            this.Z.put(new Integer(k), parseLatex$RenderitzadorPrint);
        }
        this.Z.put(new Integer(65539), new ParseLatex$Render$2(this));
        this.Z.put(new Integer(65541), parseLatex$RenderitzadorPrint);
        this.Z.put(new Integer(65555), parseLatex$RenderitzadorPrint);
        this.Z.put(new Integer(65540), parseLatex$RenderitzadorPrint);
        this.Z.put(new Integer(65545), new ParseLatex$Render$3(this));
        this.Z.put(new Integer(this.this$0.I("\\relax")), new ParseLatex$Render$4(this));
    }
    
    final void I() {
        if (this.B != 5) {
            this.I.I(this.C, this.B);
            this.C = "";
            this.B = 5;
        }
    }
    
    final void I(final int n) {
        if (n >= 65536 && n != 65554) {
            final String s = this.this$0.C.get(new Integer(n));
            if (s != null) {
                for (int length = s.length(), i = 0; i < length; ++i) {
                    this.I(s.charAt(i));
                }
                return;
            }
        }
        String value = this.this$0.C.get(new Integer(n));
        if (value == null) {
            value = String.valueOf((char)n);
        }
        if (this.B == 0 && (Token.Z(n) || Token.C(n))) {
            this.C += value;
        }
        else if (this.B == 2 && Token.C(n)) {
            this.C += value;
        }
        else if (this.B == 1 && Token.B(n)) {
            this.C += value;
        }
        else if (this.B != 3 || !Token.I(n)) {
            this.I();
            if (Token.Z(n)) {
                this.B = 0;
            }
            else if (Token.C(n)) {
                this.B = 2;
            }
            else if (Token.B(n)) {
                this.B = 1;
            }
            else if (Token.I(n)) {
                this.B = 3;
            }
            else {
                this.B = 6;
            }
            this.C = "" + value;
        }
    }
    
    final ParseLatex$CallbackRender Z(final int n) {
        final ParseLatex$CallbackRender parseLatex$CallbackRender = this.Z.get(new Integer(n));
        if (parseLatex$CallbackRender == null) {
            return this.append;
        }
        return parseLatex$CallbackRender;
    }
    
    final void I(final Token token) {
        String s = this.this$0.C.get(new Integer(((ParseLatex$TokenSimple)token).c));
        if (s == null) {
            s = "";
        }
        this.append(s);
    }
    
    final void append(final String s) {
        for (int length = s.length(), i = 0; i < length; ++i) {
            this.I(s.charAt(i));
        }
    }
    
    static final ParseLatex I(final ParseLatex$Render parseLatex$Render) {
        return parseLatex$Render.this$0;
    }
}
