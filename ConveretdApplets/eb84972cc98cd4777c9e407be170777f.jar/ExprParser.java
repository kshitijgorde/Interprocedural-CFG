// 
// Decompiled by Procyon v0.5.30
// 

class ExprParser
{
    String text;
    String token;
    int pos;
    int tlen;
    boolean err;
    
    void getToken() {
        while (this.pos < this.tlen && this.text.charAt(this.pos) == ' ') {
            ++this.pos;
        }
        if (this.pos == this.tlen) {
            this.token = "";
            return;
        }
        int i = this.pos;
        final char char1 = this.text.charAt(i);
        if ((char1 >= '0' && char1 <= '9') || char1 == '.') {
            for (i = this.pos; i != this.tlen; ++i) {
                if ((this.text.charAt(i) < '0' || this.text.charAt(i) > '9') && this.text.charAt(i) != '.') {
                    break;
                }
            }
        }
        else if (char1 >= 'a' && char1 <= 'z') {
            for (i = this.pos; i != this.tlen && this.text.charAt(i) >= 'a'; ++i) {
                if (this.text.charAt(i) > 'z') {
                    break;
                }
            }
        }
        else {
            ++i;
        }
        this.token = this.text.substring(this.pos, i);
        this.pos = i;
    }
    
    boolean skip(final String s) {
        if (this.token.compareTo(s) != 0) {
            return false;
        }
        this.getToken();
        return true;
    }
    
    void skipOrError(final String s) {
        if (!this.skip(s)) {
            this.err = true;
        }
    }
    
    Expr parseExpression() {
        if (this.token.length() == 0) {
            return new Expr(6, 0.0);
        }
        final Expr parse = this.parse();
        if (this.token.length() > 0) {
            this.err = true;
        }
        return parse;
    }
    
    Expr parse() {
        Expr mult = this.parseMult();
        while (true) {
            if (this.skip("+")) {
                mult = new Expr(mult, this.parseMult(), 1);
            }
            else {
                if (!this.skip("-")) {
                    break;
                }
                mult = new Expr(mult, this.parseMult(), 2);
            }
        }
        return mult;
    }
    
    Expr parseMult() {
        Expr uminus = this.parseUminus();
        while (true) {
            if (this.skip("*")) {
                uminus = new Expr(uminus, this.parseUminus(), 7);
            }
            else {
                if (!this.skip("/")) {
                    break;
                }
                uminus = new Expr(uminus, this.parseUminus(), 8);
            }
        }
        return uminus;
    }
    
    Expr parseUminus() {
        this.skip("+");
        if (this.skip("-")) {
            return new Expr(this.parsePow(), null, 10);
        }
        return this.parsePow();
    }
    
    Expr parsePow() {
        Expr term = this.parseTerm();
        while (this.skip("^")) {
            term = new Expr(term, this.parseTerm(), 9);
        }
        return term;
    }
    
    Expr parseFunc(final int n) {
        this.skipOrError("(");
        final Expr parse = this.parse();
        this.skipOrError(")");
        return new Expr(parse, null, n);
    }
    
    Expr parseTerm() {
        if (this.skip("(")) {
            final Expr parse = this.parse();
            this.skipOrError(")");
            return parse;
        }
        if (this.skip("x")) {
            return new Expr(3);
        }
        if (this.skip("y")) {
            return new Expr(4);
        }
        if (this.skip("z")) {
            return new Expr(5);
        }
        if (this.skip("r")) {
            return new Expr(18);
        }
        if (this.skip("pi")) {
            return new Expr(6, 3.141592653589793);
        }
        if (this.skip("e")) {
            return new Expr(6, 2.718281828459045);
        }
        if (this.skip("sin")) {
            return this.parseFunc(11);
        }
        if (this.skip("cos")) {
            return this.parseFunc(12);
        }
        if (this.skip("abs")) {
            return this.parseFunc(13);
        }
        if (this.skip("exp")) {
            return this.parseFunc(14);
        }
        if (this.skip("log")) {
            return this.parseFunc(15);
        }
        if (this.skip("sqrt")) {
            return this.parseFunc(16);
        }
        if (this.skip("tan")) {
            return this.parseFunc(17);
        }
        try {
            final Expr expr = new Expr(6, Double.valueOf(this.token));
            this.getToken();
            return expr;
        }
        catch (Exception ex) {
            this.err = true;
            System.out.print("unrecognized token: " + this.token + "\n");
            return new Expr(6, 0.0);
        }
    }
    
    ExprParser(final String text) {
        this.text = text;
        this.tlen = this.text.length();
        this.pos = 0;
        this.err = false;
        this.getToken();
    }
    
    boolean gotError() {
        return this.err;
    }
}
