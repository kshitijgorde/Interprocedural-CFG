// 
// Decompiled by Procyon v0.5.30
// 

class PGC_Eval
{
    private PGC owner;
    public String input;
    public Complex value;
    public String message;
    public boolean empty;
    public boolean uses_autoplot;
    public boolean uses_autoplot_x;
    public boolean uses_autoplot_t;
    public boolean[] uses_input;
    public boolean[] allowed;
    public String recursive;
    private int pos;
    private int ch;
    private String token;
    private int token_pos;
    private int token_type;
    private int CHAR_EOS;
    private static final int TOKEN_TYPE_UNDEFINED = 0;
    private static final int TOKEN_TYPE_EOS = 1;
    private static final int TOKEN_TYPE_NUMBER = 2;
    private static final int TOKEN_TYPE_SYMBOL = 3;
    private static final int TOKEN_TYPE_ID = 16384;
    private static final int TOKEN_TYPE_ID_CLASS_VAR = 20480;
    private static final int TOKEN_TYPE_ID_CLASS_FUNC = 24576;
    private static final int TOKEN_TYPE_ID_I = 20481;
    private static final int TOKEN_TYPE_ID_PI = 20482;
    private static final int TOKEN_TYPE_ID_E = 20483;
    private static final int TOKEN_TYPE_ID_INFINITY = 20484;
    private static final int TOKEN_TYPE_ID_NAN = 20485;
    private static final int TOKEN_TYPE_ID_A = 20486;
    private static final int TOKEN_TYPE_ID_AX = 20487;
    private static final int TOKEN_TYPE_ID_AY = 20488;
    private static final int TOKEN_TYPE_ID_B = 20489;
    private static final int TOKEN_TYPE_ID_BX = 20490;
    private static final int TOKEN_TYPE_ID_BY = 20491;
    private static final int TOKEN_TYPE_ID_C = 20492;
    private static final int TOKEN_TYPE_ID_CX = 20493;
    private static final int TOKEN_TYPE_ID_CY = 20494;
    private static final int TOKEN_TYPE_ID_D = 20495;
    private static final int TOKEN_TYPE_ID_DX = 20496;
    private static final int TOKEN_TYPE_ID_DY = 20497;
    private static final int TOKEN_TYPE_ID_F = 20498;
    private static final int TOKEN_TYPE_ID_FX = 20499;
    private static final int TOKEN_TYPE_ID_FY = 20500;
    private static final int TOKEN_TYPE_ID_G = 20501;
    private static final int TOKEN_TYPE_ID_GX = 20502;
    private static final int TOKEN_TYPE_ID_GY = 20503;
    private static final int TOKEN_TYPE_ID_H = 20504;
    private static final int TOKEN_TYPE_ID_HX = 20505;
    private static final int TOKEN_TYPE_ID_HY = 20506;
    private static final int TOKEN_TYPE_ID_K = 20507;
    private static final int TOKEN_TYPE_ID_KX = 20508;
    private static final int TOKEN_TYPE_ID_KY = 20509;
    private static final int TOKEN_TYPE_ID_M = 20510;
    private static final int TOKEN_TYPE_ID_MX = 20511;
    private static final int TOKEN_TYPE_ID_MY = 20512;
    private static final int TOKEN_TYPE_ID_N = 20513;
    private static final int TOKEN_TYPE_ID_NX = 20514;
    private static final int TOKEN_TYPE_ID_NY = 20515;
    private static final int TOKEN_TYPE_ID_X = 20516;
    private static final int TOKEN_TYPE_ID_T = 20517;
    private static final int TOKEN_TYPE_ID_S = 20518;
    private static final int TOKEN_TYPE_ID_Z = 20519;
    private static final int TOKEN_TYPE_ID_ABS = 24577;
    private static final int TOKEN_TYPE_ID_ARG = 24578;
    private static final int TOKEN_TYPE_ID_CONJ = 24579;
    private static final int TOKEN_TYPE_ID_INT = 24580;
    private static final int TOKEN_TYPE_ID_CEIL = 24581;
    private static final int TOKEN_TYPE_ID_FLOOR = 24582;
    private static final int TOKEN_TYPE_ID_SQRT = 24583;
    private static final int TOKEN_TYPE_ID_EXP = 24584;
    private static final int TOKEN_TYPE_ID_LOG = 24585;
    private static final int TOKEN_TYPE_ID_SIN = 24586;
    private static final int TOKEN_TYPE_ID_COS = 24587;
    private static final int TOKEN_TYPE_ID_TAN = 24588;
    private static final int TOKEN_TYPE_ID_COT = 24589;
    private static final int TOKEN_TYPE_ID_SEC = 24590;
    private static final int TOKEN_TYPE_ID_CSC = 24591;
    private static final int TOKEN_TYPE_ID_SINH = 24592;
    private static final int TOKEN_TYPE_ID_COSH = 24593;
    private static final int TOKEN_TYPE_ID_TANH = 24594;
    private static final int TOKEN_TYPE_ID_COTH = 24595;
    private static final int TOKEN_TYPE_ID_SECH = 24596;
    private static final int TOKEN_TYPE_ID_CSCH = 24597;
    private static final int TOKEN_TYPE_ID_ASIN = 24598;
    private static final int TOKEN_TYPE_ID_ACOS = 24599;
    private static final int TOKEN_TYPE_ID_ATAN = 24600;
    private static final int TOKEN_TYPE_ID_ACOT = 24601;
    private static final int TOKEN_TYPE_ID_ASEC = 24602;
    private static final int TOKEN_TYPE_ID_ACSC = 24603;
    private static final int TOKEN_TYPE_ID_ASINH = 24604;
    private static final int TOKEN_TYPE_ID_ACOSH = 24605;
    private static final int TOKEN_TYPE_ID_ATANH = 24606;
    private static final int TOKEN_TYPE_ID_ACOTH = 24607;
    private static final int TOKEN_TYPE_ID_ASECH = 24608;
    private static final int TOKEN_TYPE_ID_ACSCH = 24609;
    private static final int TOKEN_TYPE_ID_RE = 24610;
    private static final int TOKEN_TYPE_ID_IM = 24611;
    private static final int TOKEN_TYPE_ID_REALONLY = 24612;
    
    public PGC_Eval(final PGC owner) {
        this.uses_input = new boolean[14];
        this.allowed = new boolean[14];
        this.CHAR_EOS = -1;
        this.owner = owner;
        for (int i = 0; i < this.allowed.length; ++i) {
            this.allowed[i] = true;
        }
    }
    
    public void Evaluate(final String input) {
        this.input = input;
        this.message = "";
        this.uses_autoplot = false;
        this.uses_autoplot_x = false;
        this.uses_autoplot_t = false;
        for (int i = 0; i < this.uses_input.length; ++i) {
            this.uses_input[i] = false;
        }
        this.LexInitialize();
        this.empty = (this.token_type == 1);
        this.recursive = null;
        this.value = this.ParseExpression();
        if (this.recursive != null) {
            this.ParseError("Recursive definition with " + this.recursive);
        }
    }
    
    private void LexInitialize() {
        this.pos = 0;
        this.LexGetChar();
        this.LexGetToken();
    }
    
    private boolean LexIsEos() {
        return this.ch == this.CHAR_EOS;
    }
    
    private boolean LexIsSpace() {
        return this.ch >= 0 && this.ch <= 32;
    }
    
    private boolean LexIsIdentifierChar() {
        return (this.ch >= 97 && this.ch <= 122) || (this.ch >= 65 && this.ch <= 90) || this.ch == 95;
    }
    
    private boolean LexIsDigit() {
        return this.ch >= 48 && this.ch <= 57;
    }
    
    private void LexGetChar() {
        if (this.pos >= this.input.length()) {
            this.ch = this.CHAR_EOS;
            return;
        }
        this.ch = this.input.charAt(this.pos);
        ++this.pos;
    }
    
    private void LexAdvance() {
        this.token = String.valueOf(this.token) + (char)this.ch;
        this.LexGetChar();
    }
    
    private void LexWhiteSpace() {
        while (true) {
            if (!this.LexIsSpace()) {
                if (this.ch != 123) {
                    break;
                }
                int n = 1;
                this.LexGetChar();
                while (!this.LexIsEos()) {
                    if (this.ch == 123) {
                        ++n;
                    }
                    if (this.ch == 125) {
                        --n;
                    }
                    this.LexGetChar();
                    if (n == 0) {
                        break;
                    }
                }
            }
            else {
                this.LexGetChar();
            }
        }
    }
    
    private void LexGetToken() {
        this.token = "";
        this.token_type = 0;
        this.LexWhiteSpace();
        this.token_pos = this.pos;
        if (this.LexIsEos()) {
            this.token_type = 1;
            return;
        }
        if (this.LexIsIdentifierChar()) {
            while (this.LexIsIdentifierChar()) {
                this.LexAdvance();
            }
            this.token_type = this.LexTokenId(this.token);
            return;
        }
        if (this.LexIsDigit() || this.ch == 46) {
            this.token_type = 2;
            final boolean b = this.ch == 46;
            while (this.LexIsDigit()) {
                this.LexAdvance();
            }
            if (this.ch == 46) {
                this.LexAdvance();
                if (b && !this.LexIsDigit()) {
                    this.token_type = 3;
                }
                else {
                    while (this.LexIsDigit()) {
                        this.LexAdvance();
                    }
                }
            }
            if ((this.token_type == 2 && this.ch == 101) || this.ch == 69) {
                this.LexAdvance();
                if (this.ch == 43 || this.ch == 45) {
                    this.LexAdvance();
                }
                while (this.LexIsDigit()) {
                    this.LexAdvance();
                }
            }
        }
        else {
            this.token_type = 3;
            this.LexAdvance();
        }
    }
    
    private int LexTokenId(final String s) {
        int n = 16384;
        if (s.equalsIgnoreCase("i")) {
            n = 20481;
        }
        else if (s.equalsIgnoreCase("j")) {
            n = 20481;
        }
        else if (s.equalsIgnoreCase("pi")) {
            n = 20482;
        }
        else if (s.equalsIgnoreCase("e")) {
            n = 20483;
        }
        else if (s.equalsIgnoreCase("infinity")) {
            n = 20484;
        }
        else if (s.equalsIgnoreCase("nan")) {
            n = 20485;
        }
        else if (s.equalsIgnoreCase("a")) {
            n = 20486;
        }
        else if (s.equalsIgnoreCase("ax")) {
            n = 20487;
        }
        else if (s.equalsIgnoreCase("ay")) {
            n = 20488;
        }
        else if (s.equalsIgnoreCase("b")) {
            n = 20489;
        }
        else if (s.equalsIgnoreCase("bx")) {
            n = 20490;
        }
        else if (s.equalsIgnoreCase("by")) {
            n = 20491;
        }
        else if (s.equalsIgnoreCase("c")) {
            n = 20492;
        }
        else if (s.equalsIgnoreCase("cx")) {
            n = 20493;
        }
        else if (s.equalsIgnoreCase("cy")) {
            n = 20494;
        }
        else if (s.equalsIgnoreCase("d")) {
            n = 20495;
        }
        else if (s.equalsIgnoreCase("dx")) {
            n = 20496;
        }
        else if (s.equalsIgnoreCase("dy")) {
            n = 20497;
        }
        else if (s.equalsIgnoreCase("f")) {
            n = 20498;
        }
        else if (s.equalsIgnoreCase("fx")) {
            n = 20499;
        }
        else if (s.equalsIgnoreCase("fy")) {
            n = 20500;
        }
        else if (s.equalsIgnoreCase("g")) {
            n = 20501;
        }
        else if (s.equalsIgnoreCase("gx")) {
            n = 20502;
        }
        else if (s.equalsIgnoreCase("gy")) {
            n = 20503;
        }
        else if (s.equalsIgnoreCase("h")) {
            n = 20504;
        }
        else if (s.equalsIgnoreCase("hx")) {
            n = 20505;
        }
        else if (s.equalsIgnoreCase("hy")) {
            n = 20506;
        }
        else if (s.equalsIgnoreCase("k")) {
            n = 20507;
        }
        else if (s.equalsIgnoreCase("kx")) {
            n = 20508;
        }
        else if (s.equalsIgnoreCase("ky")) {
            n = 20509;
        }
        else if (s.equalsIgnoreCase("m")) {
            n = 20510;
        }
        else if (s.equalsIgnoreCase("mx")) {
            n = 20511;
        }
        else if (s.equalsIgnoreCase("my")) {
            n = 20512;
        }
        else if (s.equalsIgnoreCase("n")) {
            n = 20513;
        }
        else if (s.equalsIgnoreCase("nx")) {
            n = 20514;
        }
        else if (s.equalsIgnoreCase("ny")) {
            n = 20515;
        }
        else if (s.equalsIgnoreCase("x")) {
            n = 20516;
        }
        else if (s.equalsIgnoreCase("t")) {
            n = 20517;
        }
        else if (s.equalsIgnoreCase("s")) {
            n = 20518;
        }
        else if (s.equalsIgnoreCase("z")) {
            n = 20519;
        }
        else if (s.equalsIgnoreCase("abs")) {
            n = 24577;
        }
        else if (s.equalsIgnoreCase("arg")) {
            n = 24578;
        }
        else if (s.equalsIgnoreCase("conj")) {
            n = 24579;
        }
        else if (s.equalsIgnoreCase("int")) {
            n = 24580;
        }
        else if (s.equalsIgnoreCase("ceil")) {
            n = 24581;
        }
        else if (s.equalsIgnoreCase("floor")) {
            n = 24582;
        }
        else if (s.equalsIgnoreCase("sqrt")) {
            n = 24583;
        }
        else if (s.equalsIgnoreCase("exp")) {
            n = 24584;
        }
        else if (s.equalsIgnoreCase("log")) {
            n = 24585;
        }
        else if (s.equalsIgnoreCase("ln")) {
            n = 24585;
        }
        else if (s.equalsIgnoreCase("sin")) {
            n = 24586;
        }
        else if (s.equalsIgnoreCase("cos")) {
            n = 24587;
        }
        else if (s.equalsIgnoreCase("tan")) {
            n = 24588;
        }
        else if (s.equalsIgnoreCase("cot")) {
            n = 24589;
        }
        else if (s.equalsIgnoreCase("sec")) {
            n = 24590;
        }
        else if (s.equalsIgnoreCase("csc")) {
            n = 24591;
        }
        else if (s.equalsIgnoreCase("sinh")) {
            n = 24592;
        }
        else if (s.equalsIgnoreCase("cosh")) {
            n = 24593;
        }
        else if (s.equalsIgnoreCase("tanh")) {
            n = 24594;
        }
        else if (s.equalsIgnoreCase("coth")) {
            n = 24595;
        }
        else if (s.equalsIgnoreCase("sech")) {
            n = 24596;
        }
        else if (s.equalsIgnoreCase("csch")) {
            n = 24597;
        }
        else if (s.equalsIgnoreCase("asin")) {
            n = 24598;
        }
        else if (s.equalsIgnoreCase("acos")) {
            n = 24599;
        }
        else if (s.equalsIgnoreCase("atan")) {
            n = 24600;
        }
        else if (s.equalsIgnoreCase("acot")) {
            n = 24601;
        }
        else if (s.equalsIgnoreCase("asec")) {
            n = 24602;
        }
        else if (s.equalsIgnoreCase("acsc")) {
            n = 24603;
        }
        else if (s.equalsIgnoreCase("asinh")) {
            n = 24604;
        }
        else if (s.equalsIgnoreCase("acosh")) {
            n = 24605;
        }
        else if (s.equalsIgnoreCase("atanh")) {
            n = 24606;
        }
        else if (s.equalsIgnoreCase("acoth")) {
            n = 24607;
        }
        else if (s.equalsIgnoreCase("asech")) {
            n = 24608;
        }
        else if (s.equalsIgnoreCase("acsch")) {
            n = 24609;
        }
        else if (s.equalsIgnoreCase("arcsin")) {
            n = 24598;
        }
        else if (s.equalsIgnoreCase("arccos")) {
            n = 24599;
        }
        else if (s.equalsIgnoreCase("arctan")) {
            n = 24600;
        }
        else if (s.equalsIgnoreCase("arccot")) {
            n = 24601;
        }
        else if (s.equalsIgnoreCase("arcsec")) {
            n = 24602;
        }
        else if (s.equalsIgnoreCase("arccsc")) {
            n = 24603;
        }
        else if (s.equalsIgnoreCase("arcsinh")) {
            n = 24604;
        }
        else if (s.equalsIgnoreCase("arccosh")) {
            n = 24605;
        }
        else if (s.equalsIgnoreCase("arctanh")) {
            n = 24606;
        }
        else if (s.equalsIgnoreCase("arccoth")) {
            n = 24607;
        }
        else if (s.equalsIgnoreCase("arcsech")) {
            n = 24608;
        }
        else if (s.equalsIgnoreCase("arccsch")) {
            n = 24609;
        }
        else if (s.equalsIgnoreCase("re")) {
            n = 24610;
        }
        else if (s.equalsIgnoreCase("im")) {
            n = 24611;
        }
        else if (s.equalsIgnoreCase("realonly")) {
            n = 24612;
        }
        return n;
    }
    
    private Complex ParseExpression() {
        Complex parseExpr;
        if (this.token_type == 1) {
            parseExpr = null;
        }
        else {
            parseExpr = this.ParseExpr(0, false);
            if (this.token_type != 1) {
                this.ParseError("Unexpected text, at " + this.token_pos);
            }
        }
        return parseExpr;
    }
    
    private Complex ParseExpr(final int n, final boolean b) {
        Complex complex = null;
        if (n == 5) {
            if (this.token.equals("+") || this.token.equals("-")) {
                final String token = this.token;
                this.LexGetToken();
                complex = this.EvalOp(token, this.ParseExpr(n, b));
            }
            else if ((this.token_type & 0x6000) == 0x6000) {
                final int token_type = this.token_type;
                this.LexGetToken();
                complex = this.EvalFunction(token_type, this.ParseExpr(n, b));
            }
            else {
                complex = this.ParseExpr(n + 1, b);
            }
        }
        else if (n == 6) {
            if (this.token.equals("(")) {
                this.LexGetToken();
                complex = this.ParseExpr(0, false);
                if (this.token.equals(")")) {
                    this.LexGetToken();
                }
                else {
                    this.ParseError("Expected a matching ')', at " + this.token_pos);
                }
            }
            else if (this.token.equals("|") && !b) {
                final String token2 = this.token;
                this.LexGetToken();
                final Complex parseExpr = this.ParseExpr(0, true);
                if (this.token.equals("|")) {
                    this.LexGetToken();
                }
                else {
                    this.ParseError("Expected a matching '|', at " + this.token_pos);
                }
                complex = this.EvalOp(token2, parseExpr);
            }
            else if (this.token_type == 2) {
                complex = this.EvalNumber(this.token);
                this.LexGetToken();
            }
            else if ((this.token_type & 0x5000) == 0x5000) {
                complex = this.EvalVariable(this.token_type);
                this.LexGetToken();
            }
            else if ((this.token_type & 0x4000) == 0x4000) {
                this.ParseError("Unknown identifier '" + this.token + "', at " + this.token_pos);
                this.LexGetToken();
            }
            else {
                this.ParseError("Expected an expression, at " + this.token_pos);
            }
        }
        else {
            complex = this.ParseExpr(n + 1, b);
        }
        return this.ParseExprTail(n, complex, b);
    }
    
    private Complex ParseExprTail(final int n, final Complex complex, final boolean b) {
        Complex evalComponent = null;
        Complex complex2;
        if ((n == 0 && this.token.equals(",")) || (n == 1 && this.token.equalsIgnoreCase("dot")) || (n == 1 && this.token.equalsIgnoreCase("cross")) || (n == 2 && this.token.equals("+")) || (n == 2 && this.token.equals("-")) || (n == 3 && this.token.equals("*")) || (n == 3 && this.token.equals("/")) || (n == 3 && this.token.equalsIgnoreCase("rem")) || (n == 3 && this.token.equalsIgnoreCase("mod")) || (n == 4 && this.token.equals("^"))) {
            final String token = this.token;
            this.LexGetToken();
            complex2 = this.ParseExprTail(n, this.EvalOp(token, complex, this.ParseExpr(n + 1, b)), b);
        }
        else if (n == 3 && (this.token.equals("(") || (this.token.equals("|") && !b) || this.token_type == 2 || (this.token_type & 0x5000) == 0x5000 || (this.token_type & 0x6000) == 0x6000)) {
            complex2 = this.ParseExprTail(n, this.EvalOp("*", complex, this.ParseExpr(n + 1, b)), b);
        }
        else if (n == 6 && this.token.equals(".")) {
            this.LexGetToken();
            if (this.token.equalsIgnoreCase("x") || this.token.equalsIgnoreCase("y")) {
                evalComponent = this.EvalComponent(complex, this.token);
                this.LexGetToken();
            }
            else {
                this.ParseError("Expected '.x' or '.y', at " + this.token_pos);
            }
            complex2 = this.ParseExprTail(n, evalComponent, b);
        }
        else {
            complex2 = complex;
        }
        return complex2;
    }
    
    private void ParseError(final String message) {
        if (this.message.length() == 0) {
            this.message = message;
        }
    }
    
    private Complex EvalOp(final String s, final Complex complex) {
        Complex complex2 = null;
        if (complex != null) {
            if (s.equals("+")) {
                complex2 = complex;
            }
            else if (s.equals("-")) {
                complex2 = Complex.Neg(complex);
            }
            else if (s.equals("|")) {
                complex2 = Complex.Number(Complex.Abs(complex));
            }
        }
        return complex2;
    }
    
    private Complex EvalOp(final String s, final Complex complex, final Complex complex2) {
        Complex complex3 = null;
        if (complex != null && complex2 != null) {
            if (s.equals(",")) {
                complex3 = Complex.Number(complex.re, complex2.re);
            }
            else if (s.equals("+")) {
                complex3 = Complex.Add(complex, complex2);
            }
            else if (s.equals("-")) {
                complex3 = Complex.Sub(complex, complex2);
            }
            else if (s.equals("*")) {
                complex3 = Complex.Mul(complex, complex2);
            }
            else if (s.equals("/")) {
                complex3 = Complex.Div(complex, complex2);
            }
            else if (s.equalsIgnoreCase("rem")) {
                complex3 = Complex.Rem(complex, complex2);
            }
            else if (s.equalsIgnoreCase("mod")) {
                complex3 = Complex.Mod(complex, complex2);
            }
            else if (s.equals("^")) {
                complex3 = Complex.Pow(complex, complex2);
            }
            else if (s.equalsIgnoreCase("dot")) {
                complex3 = Complex.Number(Complex.Dot(complex, complex2));
            }
            else if (s.equalsIgnoreCase("cross")) {
                complex3 = Complex.Number(Complex.Cross(complex, complex2));
            }
            else {
                this.ParseError("Expected an operator, at " + this.token_pos);
            }
        }
        return complex3;
    }
    
    private Complex EvalComponent(final Complex complex, final String s) {
        Complex complex2 = null;
        if (complex != null) {
            if (s.equalsIgnoreCase("x")) {
                complex2 = Complex.Number(complex.re);
            }
            else if (s.equalsIgnoreCase("y")) {
                complex2 = Complex.Number(complex.im);
            }
            else {
                this.ParseError("Expected 'x' or 'y', at " + this.token_pos);
            }
        }
        return complex2;
    }
    
    private Complex EvalNumber(final String s) {
        Complex number = null;
        final Double doubleValue = PGC_Utility.DoubleValue(s);
        if (doubleValue == null) {
            this.ParseError("Bad number format '" + s + "', at " + this.token_pos);
        }
        else {
            number = Complex.Number(doubleValue);
        }
        return number;
    }
    
    private Complex EvalVariable(final int n) {
        Complex complex = null;
        if (n == 20481) {
            complex = Complex.Number(0.0, 1.0);
        }
        else if (n == 20482) {
            complex = Complex.Number(3.141592653589793);
        }
        else if (n == 20483) {
            complex = Complex.Number(2.718281828459045);
        }
        else if (n == 20484) {
            complex = Complex.Number(Double.POSITIVE_INFINITY);
        }
        else if (n == 20485) {
            complex = Complex.Number(Double.NaN);
        }
        else if (n == 20486) {
            complex = this.owner.GetVariableCoordinates(0);
            if (complex != null) {
                complex = Complex.Number(complex);
            }
            this.uses_input[0] = true;
        }
        else if (n == 20487) {
            complex = this.owner.GetVariableCoordinates(0);
            if (complex != null) {
                complex = Complex.Number(complex.re);
            }
            this.uses_input[0] = true;
        }
        else if (n == 20488) {
            complex = this.owner.GetVariableCoordinates(0);
            if (complex != null) {
                complex = Complex.Number(complex.im);
            }
            this.uses_input[0] = true;
        }
        else if (n == 20489) {
            complex = this.owner.GetVariableCoordinates(1);
            if (complex != null) {
                complex = Complex.Number(complex);
            }
            this.uses_input[1] = true;
        }
        else if (n == 20490) {
            complex = this.owner.GetVariableCoordinates(1);
            if (complex != null) {
                complex = Complex.Number(complex.re);
            }
            this.uses_input[1] = true;
        }
        else if (n == 20491) {
            complex = this.owner.GetVariableCoordinates(1);
            if (complex != null) {
                complex = Complex.Number(complex.im);
            }
            this.uses_input[1] = true;
        }
        else if (n == 20492) {
            complex = this.owner.GetVariableCoordinates(2);
            if (complex != null) {
                complex = Complex.Number(complex);
            }
            this.uses_input[2] = true;
        }
        else if (n == 20493) {
            complex = this.owner.GetVariableCoordinates(2);
            if (complex != null) {
                complex = Complex.Number(complex.re);
            }
            this.uses_input[2] = true;
        }
        else if (n == 20494) {
            complex = this.owner.GetVariableCoordinates(2);
            if (complex != null) {
                complex = Complex.Number(complex.im);
            }
            this.uses_input[2] = true;
        }
        else if (n == 20495) {
            complex = this.owner.GetVariableCoordinates(3);
            if (complex != null) {
                complex = Complex.Number(complex);
            }
            this.uses_input[3] = true;
        }
        else if (n == 20496) {
            complex = this.owner.GetVariableCoordinates(3);
            if (complex != null) {
                complex = Complex.Number(complex.re);
            }
            this.uses_input[3] = true;
        }
        else if (n == 20497) {
            complex = this.owner.GetVariableCoordinates(3);
            if (complex != null) {
                complex = Complex.Number(complex.im);
            }
            this.uses_input[3] = true;
        }
        else if (n == 20498) {
            complex = this.EvalUserDefinedFunction(4, 0);
        }
        else if (n == 20499) {
            complex = this.EvalUserDefinedFunction(4, 1);
        }
        else if (n == 20500) {
            complex = this.EvalUserDefinedFunction(4, 2);
        }
        else if (n == 20501) {
            complex = this.EvalUserDefinedFunction(5, 0);
        }
        else if (n == 20502) {
            complex = this.EvalUserDefinedFunction(5, 1);
        }
        else if (n == 20503) {
            complex = this.EvalUserDefinedFunction(5, 2);
        }
        else if (n == 20504) {
            complex = this.EvalUserDefinedFunction(6, 0);
        }
        else if (n == 20505) {
            complex = this.EvalUserDefinedFunction(6, 1);
        }
        else if (n == 20506) {
            complex = this.EvalUserDefinedFunction(6, 2);
        }
        else if (n == 20507) {
            complex = this.EvalUserDefinedFunction(7, 0);
        }
        else if (n == 20508) {
            complex = this.EvalUserDefinedFunction(7, 1);
        }
        else if (n == 20509) {
            complex = this.EvalUserDefinedFunction(7, 2);
        }
        else if (n == 20510) {
            complex = this.EvalUserDefinedFunction(8, 0);
        }
        else if (n == 20511) {
            complex = this.EvalUserDefinedFunction(8, 1);
        }
        else if (n == 20512) {
            complex = this.EvalUserDefinedFunction(8, 2);
        }
        else if (n == 20513) {
            complex = this.EvalUserDefinedFunction(9, 0);
        }
        else if (n == 20514) {
            complex = this.EvalUserDefinedFunction(9, 1);
        }
        else if (n == 20515) {
            complex = this.EvalUserDefinedFunction(9, 2);
        }
        else if (n == 20516) {
            complex = this.owner.GetAutoplotVariable();
            this.uses_autoplot = true;
            this.uses_autoplot_x = true;
        }
        else if (n == 20517) {
            complex = this.owner.GetAutoplotVariable();
            this.uses_autoplot = true;
            this.uses_autoplot_t = true;
        }
        else if (n == 20518) {
            complex = this.owner.GetAutoincrementVariable();
        }
        else if (n == 20519) {
            complex = Complex.Number(this.owner.GetZoom(), 0.0);
        }
        return complex;
    }
    
    private Complex EvalFunction(final int n, final Complex complex) {
        Complex complex2 = null;
        if (complex != null) {
            if (n == 24577) {
                complex2 = Complex.Number(Complex.Abs(complex));
            }
            else if (n == 24578) {
                complex2 = Complex.Number(Complex.Arg(complex));
            }
            else if (n == 24579) {
                complex2 = Complex.Conj(complex);
            }
            else if (n == 24580) {
                complex2 = Complex.Int(complex);
            }
            else if (n == 24581) {
                complex2 = Complex.Ceil(complex);
            }
            else if (n == 24582) {
                complex2 = Complex.Floor(complex);
            }
            else if (n == 24583) {
                complex2 = Complex.Sqrt(complex);
            }
            else if (n == 24584) {
                complex2 = Complex.Exp(complex);
            }
            else if (n == 24585) {
                complex2 = Complex.Log(complex);
            }
            else if (n == 24586) {
                complex2 = Complex.Sin(complex);
            }
            else if (n == 24587) {
                complex2 = Complex.Cos(complex);
            }
            else if (n == 24588) {
                complex2 = Complex.Tan(complex);
            }
            else if (n == 24589) {
                complex2 = Complex.Cot(complex);
            }
            else if (n == 24590) {
                complex2 = Complex.Sec(complex);
            }
            else if (n == 24591) {
                complex2 = Complex.Csc(complex);
            }
            else if (n == 24592) {
                complex2 = Complex.Sinh(complex);
            }
            else if (n == 24593) {
                complex2 = Complex.Cosh(complex);
            }
            else if (n == 24594) {
                complex2 = Complex.Tanh(complex);
            }
            else if (n == 24595) {
                complex2 = Complex.Coth(complex);
            }
            else if (n == 24596) {
                complex2 = Complex.Sech(complex);
            }
            else if (n == 24597) {
                complex2 = Complex.Csch(complex);
            }
            else if (n == 24598) {
                complex2 = Complex.Asin(complex);
            }
            else if (n == 24599) {
                complex2 = Complex.Acos(complex);
            }
            else if (n == 24600) {
                complex2 = Complex.Atan(complex);
            }
            else if (n == 24601) {
                complex2 = Complex.Acot(complex);
            }
            else if (n == 24602) {
                complex2 = Complex.Asec(complex);
            }
            else if (n == 24603) {
                complex2 = Complex.Acsc(complex);
            }
            else if (n == 24604) {
                complex2 = Complex.Asinh(complex);
            }
            else if (n == 24605) {
                complex2 = Complex.Acosh(complex);
            }
            else if (n == 24606) {
                complex2 = Complex.Atanh(complex);
            }
            else if (n == 24607) {
                complex2 = Complex.Acoth(complex);
            }
            else if (n == 24608) {
                complex2 = Complex.Asech(complex);
            }
            else if (n == 24609) {
                complex2 = Complex.Acsch(complex);
            }
            else if (n == 24610) {
                complex2 = Complex.Number(complex.re);
            }
            else if (n == 24611) {
                complex2 = Complex.Number(complex.im);
            }
            else if (n == 24612 && this.owner.LooksReal(complex)) {
                complex2 = Complex.Number(complex);
            }
        }
        return complex2;
    }
    
    private Complex EvalUserDefinedFunction(final int n, final int n2) {
        Complex complex = null;
        if (this.allowed[n]) {
            final PGC_Eval pgc_Eval = new PGC_Eval(this.owner);
            for (int i = 0; i < this.allowed.length; ++i) {
                pgc_Eval.allowed[i] = this.allowed[i];
            }
            pgc_Eval.allowed[n] = false;
            pgc_Eval.Evaluate(this.owner.GetVariableExpression(n));
            complex = pgc_Eval.value;
            if (complex != null) {
                if (n2 == 1) {
                    complex = Complex.Number(complex.re);
                }
                if (n2 == 2) {
                    complex = Complex.Number(complex.im);
                }
            }
            if (pgc_Eval.recursive != null) {
                this.recursive = pgc_Eval.recursive;
            }
            if (pgc_Eval.uses_autoplot) {
                this.uses_autoplot = true;
            }
            if (pgc_Eval.uses_autoplot_x) {
                this.uses_autoplot_x = true;
            }
            if (pgc_Eval.uses_autoplot_t) {
                this.uses_autoplot_t = true;
            }
            for (int j = 0; j < this.uses_input.length; ++j) {
                if (pgc_Eval.uses_input[j]) {
                    this.uses_input[j] = true;
                }
            }
        }
        else {
            this.recursive = this.owner.variable[n].name;
        }
        return complex;
    }
}
