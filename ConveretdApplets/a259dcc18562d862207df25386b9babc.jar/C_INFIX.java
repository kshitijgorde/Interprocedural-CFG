// 
// Decompiled by Procyon v0.5.30
// 

class C_INFIX
{
    public String equStr;
    public String xName;
    public String yName;
    public final int Kcount = 6;
    public String[] kName;
    public boolean[] kCostant;
    public double[] kValue;
    public boolean[] kUsed;
    public C_INFIX[] kInfix;
    public int errorType;
    public int errorIndex;
    public String errorString;
    public static final int LITERAL = 1;
    public static final int SYMBOL = 2;
    public static final int OPERATOR = 3;
    public static final int FUNCTION = 4;
    public static final int PARENTHESES = 5;
    public static final int OP_ADD = 0;
    public static final int OP_SUBTRACT = 1;
    public static final int OP_NEGATION = 2;
    public static final int OP_TIMES = 3;
    public static final int OP_DIVIDE = 4;
    public static final int OP_EXPONENT = 5;
    public static final int OP_LESS = 6;
    public static final int OP_LESS_EQUAL = 7;
    public static final int OP_GREATER = 8;
    public static final int OP_GREATER_EQUAL = 9;
    public static final int OP_EQUAL = 10;
    public static final int OP_NOT_EQUAL = 11;
    public static final int PAREN_LEFT = 100;
    public static final int PAREN_RIGHT = 101;
    public static final int SYMBOL_X = 200;
    public static final int SYMBOL_Y = 201;
    public static final int SYMBOL_PI = 202;
    public static final int SYMBOL_E = 203;
    public static final int[] SYMBOL_K;
    public static final int FUN_ABS = 300;
    public static final int FUN_ACOSH = 301;
    public static final int FUN_ACOS = 302;
    public static final int FUN_ACSC = 303;
    public static final int FUN_ASEC = 304;
    public static final int FUN_ASINH = 305;
    public static final int FUN_ASIN = 306;
    public static final int FUN_ATANH = 307;
    public static final int FUN_ATAN = 308;
    public static final int FUN_COSH = 309;
    public static final int FUN_COS = 310;
    public static final int FUN_COT = 311;
    public static final int FUN_CSC = 312;
    public static final int FUN_EXP = 313;
    public static final int FUN_LOG10 = 314;
    public static final int FUN_LOG = 315;
    public static final int FUN_SEC = 316;
    public static final int FUN_SINH = 317;
    public static final int FUN_SIN = 318;
    public static final int FUN_SIGN = 319;
    public static final int FUN_SQRT = 320;
    public static final int FUN_TANH = 321;
    public static final int FUN_TAN = 322;
    public static final int ERROR_PARENTHESES = 1;
    public static final int ERROR_ILLEGAL_CHAR = 2;
    public static final int ERROR_FUNCTION = 3;
    public static final int ERROR_LITERAL = 4;
    public static final int ERROR_EDITFIELD = 5;
    public static final int ERROR_OPERATOR = 6;
    public static final int ERROR_SYNTAX = 7;
    public static final int ERROR_NOT_A_CONSTANT = 8;
    public static final String NULL = "NULLNAME";
    private int infixQTop;
    private int[] infixQType;
    private double[] infixQValue;
    private int[] infixQCode;
    private int[] infixQOriginalIndex;
    private int postfixTop;
    private int[] postfixType;
    private double[] postfixValue;
    private int[] postfixCode;
    
    public C_INFIX() {
        this.equStr = "0";
        this.xName = "var1";
        this.yName = "var2";
        this.kName = new String[6];
        this.kCostant = new boolean[6];
        this.kValue = new double[6];
        this.kUsed = new boolean[6];
        this.kInfix = new C_INFIX[6];
        this.infixQTop = 0;
        this.infixQType = new int[100];
        this.infixQValue = new double[100];
        this.infixQCode = new int[100];
        this.infixQOriginalIndex = new int[100];
        this.postfixTop = 0;
        this.postfixType = new int[30];
        this.postfixValue = new double[30];
        this.postfixCode = new int[30];
        for (int i = 0; i < 6; ++i) {
            this.kName[i] = "NULLNAME";
            this.kValue[i] = Double.NaN;
        }
    }
    
    public boolean checkSyntax() {
        this.errorString = "";
        boolean b = true;
        if (b) {
            b = this.checkCharacters();
        }
        if (b) {
            b = this.checkParentheses();
        }
        if (b) {
            b = this.parseInfix();
        }
        if (b) {
            this.infixMultiplication();
        }
        if (b) {
            b = this.checkUnaryNegation();
        }
        if (!b) {
            this.setErrorString();
        }
        return b;
    }
    
    private void setErrorString() {
        if (this.errorType == 2) {
            this.errorString = String.valueOf(String.valueOf(this.errorString)).concat("Unrecognized Character.");
        }
        else if (this.errorType == 1) {
            this.errorString = String.valueOf(String.valueOf(this.errorString)).concat("Mismatched Parentheses.");
        }
        else if (this.errorType == 3) {
            this.errorString = String.valueOf(String.valueOf(this.errorString)).concat("Unrecognized Function or Variable.");
        }
        else if (this.errorType == 4) {
            this.errorString = String.valueOf(String.valueOf(this.errorString)).concat("illegal Numeric Constant.");
        }
        else if (this.errorType == 6) {
            this.errorString = String.valueOf(String.valueOf(this.errorString)).concat("Bad Operator Syntax.");
        }
        else if (this.errorType == 7) {
            this.errorString = String.valueOf(String.valueOf(this.errorString)).concat("Syntax Error.");
        }
    }
    
    private boolean checkCharacters() {
        boolean b = true;
        for (int n = this.equStr.length() - 1, errorIndex = 0; errorIndex <= n && b; ++errorIndex) {
            final char char1 = this.equStr.charAt(errorIndex);
            if ((char1 < '0' || char1 > '9') && char1 != '(' && char1 != ')' && (char1 < 'a' || char1 > 'z') && (char1 < 'A' || char1 > 'Z') && char1 != '+' && char1 != '-' && char1 != '*' && char1 != '/' && char1 != '^' && char1 != '.' && char1 != ' ' && char1 != '<' && char1 != '>') {
                if (char1 != '=') {
                    b = false;
                    this.errorIndex = errorIndex;
                    this.errorType = 2;
                    this.errorString = String.valueOf(String.valueOf(new StringBuffer("'").append(char1).append("':  ")));
                }
            }
        }
        return b;
    }
    
    private boolean checkParentheses() {
        int n = -1;
        final int[] array = new int[25];
        boolean b = true;
        for (int n2 = this.equStr.length() - 1, errorIndex = 0; errorIndex <= n2 && b; ++errorIndex) {
            final char char1 = this.equStr.charAt(errorIndex);
            if (char1 == ')') {
                if (n < 0) {
                    b = false;
                    this.errorIndex = errorIndex;
                    this.errorType = 1;
                    this.errorString = String.valueOf(String.valueOf(new StringBuffer("'").append(this.equStr.substring(0, this.errorIndex + 1)).append("':  ")));
                }
                else {
                    --n;
                }
            }
            else if (char1 == '(') {
                ++n;
                array[n] = errorIndex;
            }
        }
        if (b && n != -1) {
            b = false;
            this.errorIndex = array[n];
            this.errorType = 1;
            this.errorString = String.valueOf(String.valueOf(new StringBuffer("'").append(this.equStr.substring(0, this.errorIndex + 1)).append("':  ")));
        }
        return b;
    }
    
    private boolean parseInfix() {
        for (int i = 0; i < 6; ++i) {
            this.kUsed[i] = false;
        }
        final int n = this.equStr.length() - 1;
        final String concat = String.valueOf(String.valueOf(this.equStr)).concat("        ");
        this.infixQTop = 0;
        int j = 0;
        while (j <= n) {
            char c = concat.charAt(j);
            ++this.infixQTop;
            this.infixQOriginalIndex[this.infixQTop] = j;
            if (c == ' ') {
                --this.infixQTop;
                ++j;
            }
            else {
                if ((c >= '0' && c <= '9') || c == '.') {
                    int k = 1;
                    this.infixQType[this.infixQTop] = 1;
                    String concat2 = "";
                    while (k != 0) {
                        concat2 = String.valueOf(String.valueOf(concat2)).concat(String.valueOf(String.valueOf(c)));
                        if (++j > n) {
                            k = 0;
                        }
                        else {
                            c = concat.charAt(j);
                            if ((c >= '0' && c <= '9') || c == '.') {
                                continue;
                            }
                            k = 0;
                        }
                    }
                    try {
                        this.infixQValue[this.infixQTop] = Double.valueOf(concat2);
                        continue;
                    }
                    catch (NumberFormatException ex) {
                        this.errorIndex = this.infixQOriginalIndex[this.infixQTop];
                        this.errorType = 4;
                        this.errorString = String.valueOf(String.valueOf(new StringBuffer("'").append(concat2).append("':  ")));
                        return false;
                    }
                }
                if (c == '-') {
                    this.infixQCode[this.infixQTop] = 1;
                    this.infixQType[this.infixQTop] = 3;
                    ++j;
                }
                else if (c == '+') {
                    this.infixQCode[this.infixQTop] = 0;
                    this.infixQType[this.infixQTop] = 3;
                    ++j;
                }
                else if (c == '*') {
                    this.infixQCode[this.infixQTop] = 3;
                    this.infixQType[this.infixQTop] = 3;
                    ++j;
                }
                else if (c == '/') {
                    this.infixQCode[this.infixQTop] = 4;
                    this.infixQType[this.infixQTop] = 3;
                    ++j;
                }
                else if (c == '^') {
                    this.infixQCode[this.infixQTop] = 5;
                    this.infixQType[this.infixQTop] = 3;
                    ++j;
                }
                else if (c == '(') {
                    this.infixQCode[this.infixQTop] = 100;
                    this.infixQType[this.infixQTop] = 5;
                    ++j;
                }
                else if (c == ')') {
                    this.infixQCode[this.infixQTop] = 101;
                    this.infixQType[this.infixQTop] = 5;
                    ++j;
                }
                else if (c == '<' || c == '>' || c == '=') {
                    this.infixQType[this.infixQTop] = 3;
                    final String value = String.valueOf(String.valueOf(new StringBuffer("").append(c).append(concat.charAt(j + 1))));
                    if (value.equals("<=")) {
                        this.infixQCode[this.infixQTop] = 7;
                        j += 2;
                    }
                    else if (value.equals(">=")) {
                        this.infixQCode[this.infixQTop] = 9;
                        j += 2;
                    }
                    else if (value.equals("==")) {
                        this.infixQCode[this.infixQTop] = 10;
                        j += 2;
                    }
                    else if (value.equals("!=")) {
                        this.infixQCode[this.infixQTop] = 11;
                        j += 2;
                    }
                    else if (c == '>') {
                        this.infixQCode[this.infixQTop] = 8;
                        ++j;
                    }
                    else {
                        if (c != '<') {
                            this.errorIndex = j;
                            this.errorType = 6;
                            this.errorString = String.valueOf(String.valueOf(new StringBuffer("'").append(this.equStr.substring(0, j + 1)).append("':  ")));
                            return false;
                        }
                        this.infixQCode[this.infixQTop] = 6;
                        ++j;
                    }
                }
                else {
                    this.infixQType[this.infixQTop] = 4;
                    final String value2 = String.valueOf(String.valueOf(new StringBuffer("").append(c).append(concat.charAt(j + 1))));
                    final String concat3 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(value2)).concat(String.valueOf(String.valueOf(concat.charAt(j + 2)))))).concat(String.valueOf(String.valueOf(concat.charAt(j + 3))));
                    final String concat4 = String.valueOf(String.valueOf(concat3)).concat(String.valueOf(String.valueOf(concat.charAt(j + 4))));
                    final String concat5 = String.valueOf(String.valueOf(concat4)).concat(String.valueOf(String.valueOf(concat.charAt(j + 5))));
                    final String concat6 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(concat5)).concat(String.valueOf(String.valueOf(concat.charAt(j + 6)))))).concat(String.valueOf(String.valueOf(concat.charAt(j + 7))));
                    if (concat3.equals("abs(")) {
                        this.infixQCode[this.infixQTop] = 300;
                        j += 3;
                    }
                    else if (concat5.equalsIgnoreCase("acosh(")) {
                        this.infixQCode[this.infixQTop] = 301;
                        j += 5;
                    }
                    else if (concat5.equalsIgnoreCase("asinh(")) {
                        this.infixQCode[this.infixQTop] = 305;
                        j += 5;
                    }
                    else if (concat5.equalsIgnoreCase("atanh(")) {
                        this.infixQCode[this.infixQTop] = 307;
                        j += 5;
                    }
                    else if (concat4.equalsIgnoreCase("acos(")) {
                        this.infixQCode[this.infixQTop] = 302;
                        j += 4;
                    }
                    else if (concat4.equalsIgnoreCase("acsc(")) {
                        this.infixQCode[this.infixQTop] = 303;
                        j += 4;
                    }
                    else if (concat4.equalsIgnoreCase("asec(")) {
                        this.infixQCode[this.infixQTop] = 304;
                        j += 4;
                    }
                    else if (concat4.equalsIgnoreCase("asin(")) {
                        this.infixQCode[this.infixQTop] = 306;
                        j += 4;
                    }
                    else if (concat4.equalsIgnoreCase("atan(")) {
                        this.infixQCode[this.infixQTop] = 308;
                        j += 4;
                    }
                    else if (concat4.equalsIgnoreCase("cosh(")) {
                        this.infixQCode[this.infixQTop] = 309;
                        j += 4;
                    }
                    else if (concat3.equalsIgnoreCase("cos(")) {
                        this.infixQCode[this.infixQTop] = 310;
                        j += 3;
                    }
                    else if (concat3.equalsIgnoreCase("cot(")) {
                        this.infixQCode[this.infixQTop] = 311;
                        j += 3;
                    }
                    else if (concat3.equalsIgnoreCase("csc(")) {
                        this.infixQCode[this.infixQTop] = 312;
                        j += 3;
                    }
                    else if (concat3.equalsIgnoreCase("exp(")) {
                        this.infixQCode[this.infixQTop] = 313;
                        j += 3;
                    }
                    else if (concat5.equalsIgnoreCase("log10(")) {
                        this.infixQCode[this.infixQTop] = 314;
                        j += 5;
                    }
                    else if (concat3.equalsIgnoreCase("log(")) {
                        this.infixQCode[this.infixQTop] = 315;
                        j += 3;
                    }
                    else if (concat3.equalsIgnoreCase("sec(")) {
                        this.infixQCode[this.infixQTop] = 316;
                        j += 3;
                    }
                    else if (concat4.equalsIgnoreCase("sinh(")) {
                        this.infixQCode[this.infixQTop] = 317;
                        j += 4;
                    }
                    else if (concat3.equalsIgnoreCase("sin(")) {
                        this.infixQCode[this.infixQTop] = 318;
                        j += 3;
                    }
                    else if (concat4.equalsIgnoreCase("sign(")) {
                        this.infixQCode[this.infixQTop] = 319;
                        j += 4;
                    }
                    else if (concat4.equalsIgnoreCase("sqrt(")) {
                        this.infixQCode[this.infixQTop] = 320;
                        j += 4;
                    }
                    else if (concat4.equalsIgnoreCase("tanh(")) {
                        this.infixQCode[this.infixQTop] = 321;
                        j += 4;
                    }
                    else if (concat3.equalsIgnoreCase("tan(")) {
                        this.infixQCode[this.infixQTop] = 322;
                        j += 3;
                    }
                    else if (concat6.startsWith(this.xName)) {
                        this.infixQCode[this.infixQTop] = 200;
                        this.infixQType[this.infixQTop] = 2;
                        j += this.xName.length();
                    }
                    else if (concat6.startsWith(this.yName)) {
                        this.infixQCode[this.infixQTop] = 201;
                        this.infixQType[this.infixQTop] = 2;
                        j += this.yName.length();
                    }
                    else if (concat6.startsWith(this.kName[0])) {
                        this.infixQCode[this.infixQTop] = C_INFIX.SYMBOL_K[0];
                        this.infixQType[this.infixQTop] = 2;
                        j += this.kName[0].length();
                        this.kUsed[0] = true;
                    }
                    else if (concat6.startsWith(this.kName[1])) {
                        this.infixQCode[this.infixQTop] = C_INFIX.SYMBOL_K[1];
                        this.infixQType[this.infixQTop] = 2;
                        j += this.kName[1].length();
                        this.kUsed[1] = true;
                    }
                    else if (concat6.startsWith(this.kName[2])) {
                        this.infixQCode[this.infixQTop] = C_INFIX.SYMBOL_K[2];
                        this.infixQType[this.infixQTop] = 2;
                        j += this.kName[2].length();
                        this.kUsed[2] = true;
                    }
                    else if (concat6.startsWith(this.kName[3])) {
                        this.infixQCode[this.infixQTop] = C_INFIX.SYMBOL_K[3];
                        this.infixQType[this.infixQTop] = 2;
                        j += this.kName[3].length();
                        this.kUsed[3] = true;
                    }
                    else if (concat6.startsWith(this.kName[4])) {
                        this.infixQCode[this.infixQTop] = C_INFIX.SYMBOL_K[4];
                        this.infixQType[this.infixQTop] = 2;
                        j += this.kName[4].length();
                        this.kUsed[4] = true;
                    }
                    else if (concat6.startsWith(this.kName[5])) {
                        this.infixQCode[this.infixQTop] = C_INFIX.SYMBOL_K[5];
                        this.infixQType[this.infixQTop] = 2;
                        j += this.kName[5].length();
                        this.kUsed[5] = true;
                    }
                    else if (c == 'e') {
                        this.infixQCode[this.infixQTop] = 203;
                        this.infixQType[this.infixQTop] = 2;
                        ++j;
                    }
                    else {
                        if (!value2.equals("pi")) {
                            this.errorIndex = j;
                            this.errorType = 3;
                            int n2;
                            for (n2 = 1; n2 <= 7 && isAlphaNumeric(concat.charAt(j + n2)); ++n2) {}
                            this.errorString = String.valueOf(String.valueOf(new StringBuffer("'").append(concat.substring(j, j + n2)).append("':  ")));
                            return false;
                        }
                        this.infixQCode[this.infixQTop] = 202;
                        this.infixQType[this.infixQTop] = 2;
                        j += 2;
                    }
                }
            }
        }
        for (int l = 0; l < 6; ++l) {
            if (!this.kUsed[l]) {
                this.kName[l] = "NULLNAME";
            }
        }
        return true;
    }
    
    public void infixMultiplication() {
        for (int i = 1; i < this.infixQTop; ++i) {
            if (this.infixQType[i] == 1 || this.infixQType[i] == 2) {
                if (this.infixQType[i + 1] == 1 || this.infixQType[i + 1] == 2) {
                    this.insertMultiplication(i);
                }
                else if (this.infixQType[i + 1] == 5 && this.infixQCode[i + 1] == 100) {
                    this.insertMultiplication(i);
                }
                else if (this.infixQType[i + 1] == 4) {
                    this.insertMultiplication(i);
                }
            }
            else if (this.infixQType[i] == 5 && this.infixQCode[i] == 101) {
                if (this.infixQType[i + 1] == 5 && this.infixQCode[i + 1] == 100) {
                    this.insertMultiplication(i);
                }
                else if (this.infixQType[i + 1] == 4 || this.infixQType[i + 1] == 2 || this.infixQType[i + 1] == 1) {
                    this.insertMultiplication(i);
                }
            }
        }
    }
    
    public void insertMultiplication(final int n) {
        for (int i = this.infixQTop; i > n; --i) {
            this.infixQType[i + 1] = this.infixQType[i];
            this.infixQValue[i + 1] = this.infixQValue[i];
            this.infixQCode[i + 1] = this.infixQCode[i];
            this.infixQOriginalIndex[i + 1] = this.infixQOriginalIndex[i];
        }
        this.infixQType[n + 1] = 3;
        this.infixQCode[n + 1] = 3;
        this.infixQOriginalIndex[n + 1] = this.infixQOriginalIndex[n];
        ++this.infixQTop;
    }
    
    public boolean checkUnaryNegation() {
        if (this.infixQType[1] == 3 && !this.MustBeUnaryNegation(1)) {
            return false;
        }
        if (this.infixQType[this.infixQTop] == 3) {
            this.errorIndex = this.infixQOriginalIndex[this.infixQTop];
            this.errorType = 6;
            this.errorString = String.valueOf(String.valueOf(new StringBuffer("'").append(this.equStr.substring(0, this.errorIndex + 1)).append("':  ")));
            return false;
        }
        for (int i = 2; i < this.infixQTop; ++i) {
            if (this.infixQType[i] == 3) {
                if (this.infixQType[i - 1] == 3 || this.infixQType[i - 1] == 4) {
                    if (!this.MustBeUnaryNegation(i)) {
                        return false;
                    }
                }
                else if (this.infixQType[i - 1] == 5 && this.infixQCode[i - 1] == 100 && !this.MustBeUnaryNegation(i)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean MustBeUnaryNegation(final int n) {
        if (this.infixQCode[n] == 2) {
            return true;
        }
        if (this.infixQCode[n] == 1) {
            this.infixQCode[n] = 2;
            return true;
        }
        this.errorIndex = this.infixQOriginalIndex[n];
        this.errorType = 6;
        this.errorString = String.valueOf(String.valueOf(new StringBuffer("'").append(this.equStr.substring(0, this.errorIndex + 1)).append("':  ")));
        return false;
    }
    
    public void FindPostFix() {
        final int[] array = new int[50];
        final int[] array2 = new int[50];
        int i = -1;
        this.postfixTop = 0;
        for (int j = 1; j <= this.infixQTop; ++j) {
            if (this.infixQType[j] == 1) {
                ++this.postfixTop;
                this.postfixType[this.postfixTop] = 1;
                this.postfixValue[this.postfixTop] = this.infixQValue[j];
            }
            else if (this.infixQType[j] == 2) {
                ++this.postfixTop;
                this.postfixType[this.postfixTop] = 2;
                this.postfixCode[this.postfixTop] = this.infixQCode[j];
            }
            else if (this.infixQType[j] == 3 || this.infixQType[j] == 5 || this.infixQType[j] == 4) {
                while (i >= 0 && this.Precdence(array[i], this.infixQCode[j])) {
                    ++this.postfixTop;
                    this.postfixType[this.postfixTop] = array2[i];
                    this.postfixCode[this.postfixTop] = array[i];
                    --i;
                }
                if (this.infixQCode[j] != 101) {
                    ++i;
                    array[i] = this.infixQCode[j];
                    array2[i] = this.infixQType[j];
                }
                else {
                    --i;
                }
            }
        }
        while (i >= 0) {
            if (array[i] != 100) {
                ++this.postfixTop;
                this.postfixType[this.postfixTop] = array2[i];
                this.postfixCode[this.postfixTop] = array[i];
            }
            --i;
        }
    }
    
    public boolean Precdence(final int n, final int n2) {
        return n != 100 && n2 != 100 && (n2 == 101 || this.PrecdenceValue(n) >= this.PrecdenceValue(n2));
    }
    
    public int PrecdenceValue(final int n) {
        if (n == 6) {
            return 1;
        }
        if (n == 7) {
            return 1;
        }
        if (n == 8) {
            return 1;
        }
        if (n == 9) {
            return 1;
        }
        if (n == 10) {
            return 1;
        }
        if (n == 11) {
            return 1;
        }
        if (n == 0) {
            return 2;
        }
        if (n == 1) {
            return 2;
        }
        if (n == 4) {
            return 3;
        }
        if (n == 3) {
            return 3;
        }
        if (n == 5) {
            return 4;
        }
        if (n == 2) {
            return 5;
        }
        return 6;
    }
    
    public double functionValue(final double n, final double n2) {
        final double[] array = new double[50];
        int n3 = 0;
        for (int i = 1; i <= this.postfixTop; ++i) {
            if (this.postfixType[i] == 1) {
                ++n3;
                array[n3] = this.postfixValue[i];
            }
            else if (this.postfixType[i] == 2) {
                ++n3;
                final int n4 = this.postfixCode[i];
                if (n4 == 200) {
                    array[n3] = n;
                }
                else if (n4 == 201) {
                    array[n3] = n2;
                }
                else if (n4 == 203) {
                    array[n3] = 2.718281828459045;
                }
                else if (n4 == C_INFIX.SYMBOL_K[0]) {
                    if (this.kCostant[0]) {
                        array[n3] = this.kValue[0];
                    }
                    else {
                        array[n3] = this.kInfix[0].functionValue(n, n2);
                    }
                }
                else if (n4 == C_INFIX.SYMBOL_K[1]) {
                    if (this.kCostant[1]) {
                        array[n3] = this.kValue[1];
                    }
                    else {
                        array[n3] = this.kInfix[1].functionValue(n, n2);
                    }
                }
                else if (n4 == C_INFIX.SYMBOL_K[2]) {
                    if (this.kCostant[2]) {
                        array[n3] = this.kValue[2];
                    }
                    else {
                        array[n3] = this.kInfix[2].functionValue(n, n2);
                    }
                }
                else if (n4 == C_INFIX.SYMBOL_K[3]) {
                    if (this.kCostant[3]) {
                        array[n3] = this.kValue[3];
                    }
                    else {
                        array[n3] = this.kInfix[3].functionValue(n, n2);
                    }
                }
                else if (n4 == C_INFIX.SYMBOL_K[4]) {
                    if (this.kCostant[4]) {
                        array[n3] = this.kValue[4];
                    }
                    else {
                        array[n3] = this.kInfix[4].functionValue(n, n2);
                    }
                }
                else if (n4 == C_INFIX.SYMBOL_K[5]) {
                    if (this.kCostant[5]) {
                        array[n3] = this.kValue[5];
                    }
                    else {
                        array[n3] = this.kInfix[5].functionValue(n, n2);
                    }
                }
                else {
                    array[n3] = 3.141592653589793;
                }
            }
            else if (this.postfixType[i] == 3) {
                if (this.postfixCode[i] == 0) {
                    array[n3 - 1] += array[n3];
                    --n3;
                }
                else if (this.postfixCode[i] == 1) {
                    array[n3 - 1] -= array[n3];
                    --n3;
                }
                else if (this.postfixCode[i] == 3) {
                    array[n3 - 1] *= array[n3];
                    --n3;
                }
                else if (this.postfixCode[i] == 4) {
                    array[n3 - 1] /= array[n3];
                    --n3;
                }
                else if (this.postfixCode[i] == 5) {
                    if (array[n3] == 2.0) {
                        array[n3 - 1] *= array[n3 - 1];
                    }
                    else if (array[n3 - 1] == 2.718281828459045) {
                        array[n3 - 1] = Math.exp(array[n3]);
                    }
                    else if (array[n3 - 1] > 0.0) {
                        array[n3 - 1] = Math.pow(array[n3 - 1], array[n3]);
                    }
                    else if (array[n3] == 0.0) {
                        array[n3 - 1] = 1.0;
                    }
                    else if (Math.ceil(array[n3]) == array[n3]) {
                        array[n3 - 1] = Math.pow(array[n3 - 1], array[n3]);
                    }
                    else {
                        array[n3 - 1] = -Math.pow(Math.abs(array[n3 - 1]), array[n3]);
                    }
                    --n3;
                }
                else if (this.postfixCode[i] == 2) {
                    array[n3] = -array[n3];
                }
                else if (this.postfixCode[i] == 6) {
                    if (array[n3 - 1] < array[n3]) {
                        array[n3 - 1] = 1.0;
                    }
                    else {
                        array[n3 - 1] = 0.0;
                    }
                    --n3;
                }
                else if (this.postfixCode[i] == 7) {
                    if (array[n3 - 1] <= array[n3]) {
                        array[n3 - 1] = 1.0;
                    }
                    else {
                        array[n3 - 1] = 0.0;
                    }
                    --n3;
                }
                else if (this.postfixCode[i] == 8) {
                    if (array[n3 - 1] > array[n3]) {
                        array[n3 - 1] = 1.0;
                    }
                    else {
                        array[n3 - 1] = 0.0;
                    }
                    --n3;
                }
                else if (this.postfixCode[i] == 9) {
                    if (array[n3 - 1] >= array[n3]) {
                        array[n3 - 1] = 1.0;
                    }
                    else {
                        array[n3 - 1] = 0.0;
                    }
                    --n3;
                }
                else if (this.postfixCode[i] == 10) {
                    if (array[n3 - 1] == array[n3]) {
                        array[n3 - 1] = 1.0;
                    }
                    else {
                        array[n3 - 1] = 0.0;
                    }
                    --n3;
                }
                else if (this.postfixCode[i] == 11) {
                    if (array[n3 - 1] != array[n3]) {
                        array[n3 - 1] = 1.0;
                    }
                    else {
                        array[n3 - 1] = 0.0;
                    }
                    --n3;
                }
            }
            else if (this.postfixType[i] == 4) {
                if (this.postfixCode[i] == 300) {
                    array[n3] = Math.abs(array[n3]);
                }
                else if (this.postfixCode[i] == 301) {
                    final double n5 = array[n3];
                    double n6 = 1.0;
                    if (n5 < 0) {
                        n6 = -1.0;
                    }
                    array[n3] = Math.log(n5 + n6 * Math.sqrt(n5 * n5 - 1.0));
                    if (Double.isNaN(array[n3])) {
                        this.errorString = String.valueOf(String.valueOf(new StringBuffer("Illegal Function Call in Inverse Hyperbolic Cosine\n     At ").append(this.yName).append("(").append(Double.toString(n)).append(") = ").append(Double.toString(n2)).append("\n").append("     Attempted to find ACOSH(").append(Double.toString(n5)).append(")\n\n")));
                        return Double.NaN;
                    }
                }
                else if (this.postfixCode[i] == 302) {
                    final double n7 = array[n3];
                    array[n3] = Math.acos(array[n3]);
                    if (Double.isNaN(array[n3])) {
                        this.errorString = String.valueOf(String.valueOf(new StringBuffer("Illegal Function Call in Arc-Cosine\n     At y(").append(Double.toString(n)).append(") = ").append(Double.toString(n2)).append("\n").append("     Attempted to find ACOS(").append(Double.toString(n7)).append(")\n\n")));
                        return Double.NaN;
                    }
                }
                else if (this.postfixCode[i] == 303) {
                    final double n8 = array[n3];
                    array[n3] = Math.acos(1 / array[n3]);
                    if (Double.isNaN(array[n3])) {
                        this.errorString = String.valueOf(String.valueOf(new StringBuffer("Illegal Function Call in Arc-Cosecant\n     At ").append(this.yName).append("(").append(Double.toString(n)).append(") = ").append(Double.toString(n2)).append("\n").append("     Attempted to find ACSC(").append(Double.toString(n8)).append(")\n\n")));
                        return Double.NaN;
                    }
                }
                else if (this.postfixCode[i] == 304) {
                    final double n9 = array[n3];
                    array[n3] = Math.asin(1 / array[n3]);
                    if (Double.isNaN(array[n3])) {
                        this.errorString = String.valueOf(String.valueOf(new StringBuffer("Illegal Function Call in Arc-Secant\n     At ").append(this.yName).append("(").append(Double.toString(n)).append(") = ").append(Double.toString(n2)).append("\n").append("     Attempted to find ASEC(").append(Double.toString(n9)).append(")\n\n")));
                        return Double.NaN;
                    }
                }
                else if (this.postfixCode[i] == 305) {
                    final double n10 = array[n3];
                    double n11 = 1.0;
                    if (n10 < 0) {
                        n11 = -1.0;
                    }
                    array[n3] = n11 * Math.log(Math.abs(n10 + n11 * Math.sqrt(n10 * n10 + 1.0)));
                    if (Double.isNaN(array[n3])) {
                        this.errorString = String.valueOf(String.valueOf(new StringBuffer("Illegal Function Call in Inverse Hyperbolic Sine\n     At ").append(this.yName).append("(").append(Double.toString(n)).append(") = ").append(Double.toString(n2)).append("\n").append("     Attempted to find ASINH(").append(Double.toString(n10)).append(")\n\n")));
                        return Double.NaN;
                    }
                }
                else if (this.postfixCode[i] == 306) {
                    final double n12 = array[n3];
                    array[n3] = Math.asin(array[n3]);
                    if (Double.isNaN(array[n3])) {
                        this.errorString = String.valueOf(String.valueOf(new StringBuffer("Illegal Function Call in Arc-Sine\n     At ").append(this.yName).append("(").append(Double.toString(n)).append(") = ").append(Double.toString(n2)).append("\n").append("     Attempted to find ASIN(").append(Double.toString(n12)).append(")\n\n")));
                        return Double.NaN;
                    }
                }
                else if (this.postfixCode[i] == 307) {
                    final double n13 = array[n3];
                    array[n3] = Math.log(-(n + 1.0) / (n - 1)) / 2.0;
                    if (Double.isNaN(array[n3])) {
                        this.errorString = String.valueOf(String.valueOf(new StringBuffer("Illegal Function Call in Inverse Hyperbolic Tangent\n     At ").append(this.yName).append("(").append(Double.toString(n)).append(") = ").append(Double.toString(n2)).append("\n").append("     Attempted to find ATANH(").append(Double.toString(n13)).append(")\n\n")));
                        return Double.NaN;
                    }
                }
                else if (this.postfixCode[i] == 308) {
                    final double n14 = array[n3];
                    array[n3] = Math.atan(array[n3]);
                    if (Double.isNaN(array[n3])) {
                        this.errorString = String.valueOf(String.valueOf(new StringBuffer("Illegal Function Call in Arc-Tangent\n     At ").append(this.yName).append("(").append(Double.toString(n)).append(") = ").append(Double.toString(n2)).append("\n").append("     Attempted to find ATAN(").append(Double.toString(n14)).append(")\n\n")));
                        return Double.NaN;
                    }
                }
                else if (this.postfixCode[i] == 309) {
                    array[n3] = (Math.exp(array[n3]) + Math.exp(-array[n3])) / 2.0;
                }
                else if (this.postfixCode[i] == 310) {
                    array[n3] = Math.cos(array[n3]);
                }
                else if (this.postfixCode[i] == 311) {
                    array[n3] = 1 / Math.tan(array[n3]);
                }
                else if (this.postfixCode[i] == 312) {
                    array[n3] = 1 / Math.cos(array[n3]);
                }
                else if (this.postfixCode[i] == 313) {
                    array[n3] = Math.exp(array[n3]);
                }
                else if (this.postfixCode[i] == 314) {
                    final double n15 = array[n3];
                    array[n3] = Math.log(array[n3]) / Math.log(10.0);
                    if (Double.isNaN(array[n3])) {
                        this.errorString = String.valueOf(String.valueOf(new StringBuffer("Illegal Function Call in Bace 10 Logarithm\n     At ").append(this.yName).append("(").append(Double.toString(n)).append(") = ").append(Double.toString(n2)).append("\n").append("     Attempted to find LOG10(").append(Double.toString(n15)).append(")\n\n")));
                        return Double.NaN;
                    }
                }
                else {
                    if (this.postfixCode[i] == 315) {
                        final double n16 = array[n3];
                        array[n3] = Math.log(array[n3]);
                        Math.log(0.0);
                        if (Double.isInfinite(array[n3])) {
                            array[n3] = Double.NaN;
                        }
                        this.errorString = String.valueOf(String.valueOf(new StringBuffer("Illegal Function Call in Natural Logarithm\n     At ").append(this.yName).append("(").append(Double.toString(n)).append(") = ").append(Double.toString(n2)).append("\n").append("     Attempted to find LOG(").append(Double.toString(n16)).append(")\n\n")));
                        return Double.NaN;
                    }
                    if (this.postfixCode[i] == 316) {
                        array[n3] = 1 / Math.sin(array[n3]);
                    }
                    else if (this.postfixCode[i] == 317) {
                        array[n3] = (Math.exp(array[n3]) - Math.exp(-array[n3])) / 2.0;
                    }
                    else if (this.postfixCode[i] == 318) {
                        array[n3] = Math.sin(array[n3]);
                    }
                    else if (this.postfixCode[i] == 319) {
                        array[n3] = sign(array[n3]);
                    }
                    else if (this.postfixCode[i] == 320) {
                        final double n17 = array[n3];
                        array[n3] = Math.sqrt(array[n3]);
                        if (Double.isNaN(array[n3])) {
                            this.errorString = String.valueOf(String.valueOf(new StringBuffer("Illegal Function Call in Square Root\n     At ").append(this.yName).append("(").append(Double.toString(n)).append(") = ").append(Double.toString(n2)).append("\n").append("     Attempted to find SQRT(").append(Double.toString(n17)).append(")\n\n")));
                            return Double.NaN;
                        }
                    }
                    else if (this.postfixCode[i] == 321) {
                        array[n3] = (Math.exp(array[n3]) - Math.exp(-array[n3])) / (Math.exp(array[n3]) + Math.exp(-array[n3]));
                    }
                    else if (this.postfixCode[i] == 322) {
                        array[n3] = Math.tan(array[n3]);
                    }
                }
            }
        }
        if (Double.isNaN(array[1]) || Double.isInfinite(array[1])) {
            this.errorString = String.valueOf(String.valueOf(new StringBuffer("Undefined value:\n     At ").append(this.yName).append("(").append(Double.toString(n)).append(") = ").append(Double.toString(n2)).append("\n\n")));
            return Double.NaN;
        }
        return array[1];
    }
    
    public boolean isConstant() {
        for (int i = 1; i <= this.infixQTop; ++i) {
            if (this.infixQType[i] == 2) {
                if (this.infixQCode[i] == 200) {
                    return false;
                }
                if (this.infixQCode[i] == 201) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public double constantValue() {
        this.FindPostFix();
        final double functionValue = this.functionValue(Double.NaN, Double.NaN);
        if (Double.isNaN(functionValue)) {
            this.errorString = "Bad mathematical expression.";
            this.errorType = 8;
        }
        return functionValue;
    }
    
    public static double sign(final double n) {
        if (n < 0.0) {
            return -1.0;
        }
        if (n > 0.0) {
            return 1.0;
        }
        return 0.0;
    }
    
    public static boolean isAlphaNumeric(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }
    
    static {
        SYMBOL_K = new int[] { 204, 205, 206, 207, 208, 209 };
    }
}
