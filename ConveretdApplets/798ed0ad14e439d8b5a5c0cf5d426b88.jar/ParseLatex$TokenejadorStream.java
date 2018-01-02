// 
// Decompiled by Procyon v0.5.30
// 

class ParseLatex$TokenejadorStream extends ParseLatex$Tokenejador
{
    public int linia;
    private final ParseLatex this$0;
    
    ParseLatex$TokenejadorStream(final ParseLatex this$0) {
        super(this$0);
        this.this$0 = this$0;
        this.linia = 1;
    }
    
    char C() {
        return '\0';
    }
    
    void B() {
    }
    
    boolean D() {
        return false;
    }
    
    boolean F() {
        return true;
    }
    
    int J() {
        return 0;
    }
    
    final Token Z() {
        char c = '\0';
        int intValue = 65537;
        while (this.D()) {
            c = this.C();
            if (c == '\n') {
                ++this.linia;
            }
            final Integer n = this.this$0.I.get("" + c);
            if (n != null) {
                intValue = n;
                break;
            }
            this.this$0.Error(0, this.J(), "", "Token no reconegut: \"" + c + "\"" + (int)c + ".\n");
            intValue = 65537;
        }
        if (intValue == 65547) {
            int n2 = 0;
            int n3;
            char c2;
            do {
                ++n2;
                c2 = this.C();
                if (c2 == '\n') {
                    ++this.linia;
                }
                n3 = this.this$0.I(c2);
            } while (this.D() && n3 == 65547);
            int n4 = 0;
            while (this.D() && Token.C(n3)) {
                n4 = n4 * 10 + n3 - 48;
                c2 = this.C();
                if (c2 == '\n') {
                    ++this.linia;
                }
                n3 = this.this$0.I(c2);
            }
            if (n3 != 0) {
                if (c2 == '\n') {
                    --this.linia;
                }
                this.B();
            }
            return new ParseLatex$TokenArgument(this.this$0, (n4 != 0) ? n4 : true, n2, 0);
        }
        if (intValue == 65537) {
            return new ParseLatex$TokenSimple(this.this$0, 65537, 0);
        }
        if (intValue == 65538) {
            final int j = this.J();
            String s = "";
            char c3 = this.C();
            if (c3 == '\n') {
                ++this.linia;
            }
            else {
                s += c3;
            }
            int n5;
            for (n5 = this.this$0.I(c3); this.D() && n5 != 65541 && n5 != 65537 && n5 != 0; n5 = this.this$0.I(c3)) {
                c3 = this.C();
                if (c3 == '\n') {
                    ++this.linia;
                }
                else {
                    s += c3;
                }
            }
            while (this.D() && Token.I(n5) && n5 != 65541 && n5 != 65537 && n5 != 0) {
                c3 = this.C();
                if (c3 == '\n') {
                    ++this.linia;
                }
                n5 = this.this$0.I(c3);
            }
            if (c3 == '\n') {
                --this.linia;
            }
            this.B();
            return new ParseLatex$TokenComentari(this.this$0, s, j);
        }
        if (intValue != 65542) {
            return new ParseLatex$TokenSimple(this.this$0, intValue, this.J());
        }
        final int i = this.J();
        String s2 = "" + c;
        if (this.F()) {
            this.this$0.Error(0, this.J(), "", "Esperava algun car\ufffdcter despr\ufffds de \"\\\".\n");
            return new ParseLatex$TokenSimple(this.this$0, 65542, 0);
        }
        char c4 = this.C();
        if (c4 == '\n') {
            ++this.linia;
        }
        final int k = this.this$0.I(c4);
        if (k != 0 && k != 65537) {
            if (Token.Z(k)) {
                int l;
                do {
                    s2 += c4;
                    if (!this.D()) {
                        l = 65540;
                        break;
                    }
                    c4 = this.C();
                    if (c4 == '\n') {
                        ++this.linia;
                    }
                    l = this.this$0.I(c4);
                } while (Token.Z(l) && l != 0 && l != 65537);
                if (c4 == '\n') {
                    --this.linia;
                }
                if (l != 65540) {
                    this.B();
                }
            }
            else {
                s2 += c4;
            }
        }
        else {
            if (c4 == '\n') {
                --this.linia;
            }
            if (k != 65540) {
                this.B();
            }
        }
        this.this$0.Z();
        final Integer n6 = this.this$0.I.get(s2);
        if (n6 != null) {
            return new ParseLatex$TokenSimple(this.this$0, n6, i);
        }
        return new ParseLatex$TokenComandaNoDefinida(this.this$0, s2, 0);
    }
}
