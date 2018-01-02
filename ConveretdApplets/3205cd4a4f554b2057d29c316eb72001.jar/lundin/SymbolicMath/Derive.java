// 
// Decompiled by Procyon v0.5.30
// 

package lundin.SymbolicMath;

import java.util.Vector;

public class Derive
{
    String[] allowedops;
    String[] twoargops;
    int maxoplength;
    public String VARIABLES;
    String storedvars;
    String defaultvar;
    int lastIndex;
    Vector tempstorage;
    
    public Derive() {
        this.allowedops = new String[] { "^", "+", "-", "/", "*", "cos", "sin", "exp", "ln", "tan", "acos", "asin", "atan", "cosh", "sinh", "tanh", "sqrt", "cotan", "acotan" };
        this.twoargops = new String[] { "^", "+", "-", "/", "*" };
        this.maxoplength = this.setMaxOpLength();
        this.VARIABLES = "";
        this.storedvars = "";
        this.defaultvar = "x";
        this.tempstorage = new Vector();
    }
    
    int setMaxOpLength() {
        int length = 0;
        for (int i = 0; i < this.allowedops.length; ++i) {
            if (this.allowedops[i].length() > length) {
                length = this.allowedops[i].length();
            }
        }
        return length;
    }
    
    String car(final String s) {
        s.length();
        int n = 0;
        int i = 2;
        int n2 = 0;
        if (s.charAt(2) == '(') {
            while (i < s.length()) {
                if (s.charAt(i) == '(') {
                    ++n2;
                }
                else if (s.charAt(i) == ')') {
                    --n2;
                }
                if (n2 == 0) {
                    n = i;
                    break;
                }
                ++i;
            }
            return s.substring(2, n + 1);
        }
        return s.substring(2, s.indexOf(" ", 2));
    }
    
    String cdr(final String s) {
        return "(" + s.substring((String.valueOf("") + this.car(s)).length() + 2, s.length());
    }
    
    String arg1(final String s) {
        return this.car(this.cdr(s));
    }
    
    String arg2(final String s) {
        return this.car(this.cdr(this.cdr(s)));
    }
    
    void Syntax(final String s) throws Exception {
        int i = 0;
        if (!this.MatchParant(s)) {
            throw new Exception("Non matching brackets");
        }
        while (i < s.length()) {
            try {
                final String op;
                if ((op = this.getOp(s, i)) != null) {
                    final String op2 = this.getOp(s, op.length() + i);
                    if (this.isTwoArgOp(op2) && !op2.equals("+") && !op2.equals("-")) {
                        throw new Exception("Syntax error near -> " + s.substring(i, s.length()));
                    }
                    if (!this.isTwoArgOp(op) && this.BackTrack(s.substring(0, i)) == null && this.isConstant(s.charAt(i - 1))) {
                        throw new Exception("Missing operator before -> " + s.substring(i, s.length()));
                    }
                }
                else if (!this.isAlpha(s.charAt(i)) && !this.isConstant(s.charAt(i)) && !this.isAllowedSym(s.charAt(i))) {
                    throw new Exception("Syntax error near -> " + s.substring(i, s.length()));
                }
            }
            catch (StringIndexOutOfBoundsException ex) {}
            ++i;
        }
    }
    
    boolean MatchParant(final String s) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                ++n;
            }
            else if (s.charAt(i) == ')') {
                --n;
            }
        }
        return n == 0;
    }
    
    String list(final String s, final String s2, final String s3) {
        return "( " + s + " " + s2 + " " + s3 + " )";
    }
    
    String list(final String s, final String s2) {
        return "( " + s + " " + s2 + " )";
    }
    
    boolean isVariable(final String s) {
        if (this.isAllNumbers(s)) {
            return false;
        }
        for (int i = 0; i < s.length(); ++i) {
            if (this.getOp(s, i) != null || this.isAllowedSym(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isSupported(final String s) {
        String substring;
        if (s.indexOf("(") != -1) {
            substring = s.substring(0, s.indexOf("("));
        }
        else {
            substring = s;
        }
        return this.isOperator(substring);
    }
    
    boolean isAlpha(final String s) {
        if (s == null) {
            return false;
        }
        if (s.length() > 1) {
            return false;
        }
        final char char1 = s.charAt(0);
        return (char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z');
    }
    
    boolean isAlpha(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
    
    boolean isAllowedSym(final char c) {
        return c == '(' || c == ')' || c == '.';
    }
    
    boolean isConstant(final char c) {
        return this.isConstant(String.valueOf(c));
    }
    
    boolean isConstant(final String s) {
        try {
            if (Double.isNaN(Double.valueOf(s))) {
                return false;
            }
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    boolean isAllNumbers(final String s) {
        int i = 0;
        int n = 0;
        final char char1 = s.charAt(0);
        if (char1 == '-' || char1 == '+') {
            i = 1;
        }
        while (i < s.length()) {
            final char char2 = s.charAt(i);
            if (!this.isConstant(char2) && (char2 != '.' || n != 0)) {
                return false;
            }
            if (char2 == '.') {
                n = 1;
            }
            ++i;
        }
        return true;
    }
    
    boolean isSameVariable(final String s, final String s2) {
        return this.isVariable(s) && this.isVariable(s2) && s.equalsIgnoreCase(s2);
    }
    
    boolean isOperator(final String s) {
        for (int i = 0; i < this.allowedops.length; ++i) {
            if (this.allowedops[i].equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
    
    boolean isTwoArgOp(final String s) {
        for (int i = 0; i < this.twoargops.length; ++i) {
            if (this.twoargops[i].equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
    
    boolean isOne(final String s) {
        return Double.valueOf(s) == 1.0;
    }
    
    boolean isZero(final String s) {
        return Double.valueOf(s) == 0.0;
    }
    
    boolean isInteger(final double n) {
        return n - (int)n == 0.0;
    }
    
    boolean isEven(final int n) {
        return this.isInteger(n / 2);
    }
    
    boolean isEven(final double n) {
        return this.isInteger(n / 2.0);
    }
    
    boolean isEven(final float n) {
        return this.isInteger(n / 2.0f);
    }
    
    boolean isSum(final String s) {
        return this.car(s).equals("+");
    }
    
    boolean isSubtraction(final String s) {
        return this.car(s).equals("-");
    }
    
    boolean isProduct(final String s) {
        return this.car(s).equals("*");
    }
    
    boolean isDivision(final String s) {
        return this.car(s).equals("/");
    }
    
    boolean isSquareroot(final String s) {
        return this.car(s).equals("sqrt");
    }
    
    boolean isCosine(final String s) {
        return this.car(s).equals("cos");
    }
    
    boolean isSine(final String s) {
        return this.car(s).equals("sin");
    }
    
    boolean isTan(final String s) {
        return this.car(s).equals("tan");
    }
    
    boolean isAtan(final String s) {
        return this.car(s).equals("atan");
    }
    
    boolean isAcos(final String s) {
        return this.car(s).equals("acos");
    }
    
    boolean isAsin(final String s) {
        return this.car(s).equals("asin");
    }
    
    boolean isSinhyp(final String s) {
        return this.car(s).equals("sinh");
    }
    
    boolean isCoshyp(final String s) {
        return this.car(s).equals("cosh");
    }
    
    boolean isTanhyp(final String s) {
        return this.car(s).equals("tanh");
    }
    
    boolean isLn(final String s) {
        return this.car(s).equals("ln");
    }
    
    boolean isPower(final String s) {
        return this.car(s).equals("^");
    }
    
    boolean isE(final String s) {
        return this.car(s).equals("exp");
    }
    
    boolean isCotan(final String s) {
        return this.car(s).equals("cotan");
    }
    
    boolean isAcotan(final String s) {
        return this.car(s).equals("acotan");
    }
    
    String makeE(final String s) {
        if (!this.isConstant(s) && !this.isVariable(s) && this.isLn(s)) {
            return this.arg1(s);
        }
        if (this.isConstant(s) && this.isZero(s)) {
            return "1";
        }
        return this.list("exp", s);
    }
    
    String makeSum(final String s, final String s2) {
        if (this.isConstant(s) && this.isConstant(s2)) {
            return String.valueOf(Double.valueOf(s) + Double.valueOf(s2));
        }
        if (this.isConstant(s) && this.isZero(s)) {
            return s2;
        }
        if (this.isConstant(s2) && this.isZero(s2)) {
            return s;
        }
        if (s.equals(s2)) {
            return this.makeProduct("2", s);
        }
        if (!this.isConstant(s) && !this.isVariable(s)) {
            if (this.isConstant(s2)) {
                return this.makeSumSimplifyConstant(s, s2);
            }
            if (this.isVariable(s2)) {
                return this.makeSumSimplifyVariable(s, s2);
            }
            return this.makeSumSimplifyTwoExpressions(s, s2);
        }
        else {
            if (this.isConstant(s2) || this.isVariable(s2)) {
                return this.list("+", s, s2);
            }
            if (this.isConstant(s)) {
                return this.makeSumSimplifyConstant(s2, s);
            }
            if (this.isVariable(s)) {
                return this.makeSumSimplifyVariable(s2, s);
            }
            return this.makeSumSimplifyTwoExpressions(s2, s);
        }
    }
    
    String makeSquareroot(final String s) {
        if (this.isConstant(s) && this.isEven(Math.sqrt(Double.valueOf(s)))) {
            return String.valueOf(Math.sqrt(Double.valueOf(s)));
        }
        if (!this.isConstant(s) && !this.isVariable(s) && this.isPower(s) && this.isEven(Double.valueOf(this.arg2(s)))) {
            return this.makePower(this.arg1(s), String.valueOf(Double.valueOf(this.arg2(s)) / 2.0));
        }
        return this.list("sqrt", s);
    }
    
    String makeProduct(final String s, final String s2) {
        if (this.isConstant(s) && this.isConstant(s2)) {
            return String.valueOf(Double.valueOf(s) * Double.valueOf(s2));
        }
        if (this.isConstant(s) && this.isZero(s)) {
            return "0";
        }
        if (this.isConstant(s) && this.isOne(s)) {
            return s2;
        }
        if (this.isConstant(s2) && this.isZero(s2)) {
            return "0";
        }
        if (this.isConstant(s2) && this.isOne(s2)) {
            return s;
        }
        if (s.equals(s2)) {
            return this.makePower(s, "2");
        }
        if (!this.isConstant(s) && !this.isVariable(s) && !this.isConstant(s2) && !this.isVariable(s2)) {
            return this.makeProductSimplifyTwoExp(s, s2);
        }
        if (!this.isConstant(s) && !this.isVariable(s)) {
            if (this.isConstant(s2)) {
                return this.makeProductSimplifyConstant(s, s2);
            }
            if (this.isVariable(s2)) {
                return this.makeProductSimplifyVariable(s, s2);
            }
        }
        else if (!this.isConstant(s2) && !this.isVariable(s2)) {
            if (this.isConstant(s)) {
                return this.makeProductSimplifyConstant(s2, s);
            }
            if (this.isVariable(s)) {
                return this.makeProductSimplifyVariable(s2, s);
            }
        }
        return this.list("*", s, s2);
    }
    
    String makeDivision(final String s, final String s2) {
        if (this.isConstant(s) && this.isConstant(s2)) {
            if (Double.valueOf(s2) != 0.0 && this.isInteger(Double.valueOf(s) / Double.valueOf(s2))) {
                return String.valueOf(Double.valueOf(s) / Double.valueOf(s2));
            }
        }
        else {
            if (this.isConstant(s) && this.isZero(s)) {
                return "0";
            }
            if (this.isConstant(s2) && this.isOne(s2)) {
                return s;
            }
            if (s.equals(s2)) {
                return "1";
            }
            if (!this.isConstant(s) && !this.isVariable(s)) {
                if (this.isSum(s) || this.isSubtraction(s)) {
                    return this.makeDivisionSimplifyExprThruVar(s, s2);
                }
                if (this.isDivision(s)) {
                    if (this.isVariable(s2) || this.isConstant(s2)) {
                        return this.makeDivision(this.arg1(s), this.makeProduct(s2, this.arg2(s)));
                    }
                    if (this.isDivision(s2)) {
                        return this.makeDivision(this.makeProduct(this.arg1(s), this.arg2(s2)), this.makeProduct(this.arg2(s), this.arg1(s2)));
                    }
                }
            }
        }
        return this.list("/", s, s2);
    }
    
    String makeSubtraction(final String s, final String s2) {
        if (this.isConstant(s) && this.isConstant(s2)) {
            return String.valueOf(Double.valueOf(s) - Double.valueOf(s2));
        }
        if (this.isConstant(s) && this.isZero(s)) {
            return this.makeProduct("-1", s2);
        }
        if (this.isConstant(s2) && this.isZero(s2)) {
            return s;
        }
        if (s.equals(s2)) {
            return "0";
        }
        if (!this.isConstant(s2) && !this.isVariable(s2) && (this.isConstant(s) || this.isVariable(s))) {
            return this.makeSubtractionSimplifyConstantVariableArg1(s, s2);
        }
        if (!this.isConstant(s) && !this.isVariable(s) && (this.isConstant(s2) || this.isVariable(s2))) {
            return this.makeSubtractionSimplifyConstantVariableArg2(s, s2);
        }
        if (!this.isConstant(s) && !this.isVariable(s) && !this.isConstant(s2) && !this.isVariable(s2)) {
            return this.makeSubtractionSimplifyTwoExp(s, s2);
        }
        return this.list("-", s, s2);
    }
    
    String makePower(final String s, final String s2) {
        if (this.isConstant(s) && this.isConstant(s2)) {
            if (this.isOne(s) || this.isZero(s2)) {
                return "1";
            }
            if (this.isOne(s2)) {
                return s;
            }
            if (this.isInteger(Math.pow(Double.valueOf(s), Double.valueOf(s2)))) {
                return String.valueOf(Math.pow(Double.valueOf(s), Double.valueOf(s2)));
            }
        }
        else {
            if (this.isConstant(s2) && this.isZero(s2)) {
                return "1";
            }
            if (this.isConstant(s2) && this.isOne(s2)) {
                return s;
            }
            if (!this.isConstant(s) && !this.isVariable(s) && this.isPower(s) && this.isConstant(s2) && this.isConstant(this.arg2(s))) {
                return this.makePower(this.arg1(s), this.makeProduct(this.arg2(s), s2));
            }
        }
        return this.list("^", s, s2);
    }
    
    String makeSine(final String s) {
        if (!this.isVariable(s) && !this.isConstant(s) && this.isAsin(s)) {
            return this.arg1(s);
        }
        if (!this.isVariable(s) && !this.isConstant(s) && this.isAcos(s)) {
            return this.makeSquareroot(this.makeSubtraction("1", this.makePower(this.arg1(s), "2")));
        }
        return this.list("sin", s);
    }
    
    String makeCosine(final String s) {
        if (!this.isVariable(s) && !this.isConstant(s) && this.isAcos(s)) {
            return this.arg1(s);
        }
        if (!this.isVariable(s) && !this.isConstant(s) && this.isAsin(s)) {
            return this.makeSquareroot(this.makeSubtraction("1", this.makePower(this.arg1(s), "2")));
        }
        return this.list("cos", s);
    }
    
    String makeTan(final String s) {
        if (!this.isVariable(s) && !this.isConstant(s) && this.isAtan(s)) {
            return this.arg1(s);
        }
        if (!this.isVariable(s) && !this.isConstant(s) && this.isAcotan(s)) {
            return this.makeDivision("1", this.arg1(s));
        }
        return this.list("tan", s);
    }
    
    String makeLn(final String s) {
        if (!this.isVariable(s) && !this.isConstant(s) && this.isE(s)) {
            return this.arg1(s);
        }
        return this.list("ln", s);
    }
    
    String makeSinhyp(final String s) {
        return this.list("sinh", s);
    }
    
    String makeCoshyp(final String s) {
        return this.list("cosh", s);
    }
    
    String makeTanhyp(final String s) {
        return this.list("tanh", s);
    }
    
    String makeCotan(final String s) {
        if (!this.isVariable(s) && !this.isConstant(s) && this.isAcotan(s)) {
            return this.arg1(s);
        }
        return this.list("cotan", s);
    }
    
    String makeAcotan(final String s) {
        if (!this.isVariable(s) && !this.isConstant(s) && this.isCotan(s)) {
            return this.arg1(s);
        }
        return this.list("acotan", s);
    }
    
    String makeSumSimplifyConstant(final String s, final String s2) {
        if (this.isSum(s)) {
            if (this.isConstant(this.arg1(s))) {
                final double n = Double.valueOf(s2) + Double.valueOf(this.arg1(s));
                if (n >= 0.0) {
                    return this.makeSum(String.valueOf(n), this.arg2(s));
                }
                return this.makeSubtraction(this.arg2(s), String.valueOf(-1.0 * n));
            }
            else if (this.isConstant(this.arg2(s))) {
                final double n2 = Double.valueOf(s2) + Double.valueOf(this.arg2(s));
                if (n2 >= 0.0) {
                    return this.makeSum(String.valueOf(n2), this.arg1(s));
                }
                return this.makeSum(this.arg1(s), String.valueOf(-1.0 * n2));
            }
        }
        else if (this.isSubtraction(s)) {
            if (this.isConstant(this.arg1(s))) {
                final double n3 = Double.valueOf(s2) + Double.valueOf(this.arg1(s));
                if (n3 >= 0.0) {
                    return this.makeSum(String.valueOf(n3), this.arg2(s));
                }
                return this.makeSubtraction(this.arg2(s), String.valueOf(-1.0 * n3));
            }
            else if (this.isConstant(this.arg2(s))) {
                final double n4 = Double.valueOf(s2) - Double.valueOf(this.arg2(s));
                if (n4 >= 0.0) {
                    return this.makeSum(String.valueOf(n4), this.arg1(s));
                }
                return this.makeSubtraction(this.arg1(s), String.valueOf(-1.0 * n4));
            }
        }
        return this.list("+", s, s2);
    }
    
    String makeSumSimplifyVariable(final String s, final String s2) {
        if (this.isSum(s)) {
            if (this.isVariable(this.arg1(s)) && this.isSameVariable(s2, this.arg1(s))) {
                return this.makeSum(this.makeProduct("2", s2), this.arg2(s));
            }
            if (this.isVariable(this.arg2(s)) && this.isSameVariable(s2, this.arg2(s))) {
                return this.makeSum(this.makeProduct("2", s2), this.arg1(s));
            }
        }
        else if (this.isSubtraction(s)) {
            if (this.isVariable(this.arg1(s)) && this.isSameVariable(s2, this.arg1(s))) {
                return this.makeSum(this.makeProduct("2", s2), this.arg2(s));
            }
            if (this.isVariable(this.arg2(s)) && this.isSameVariable(s2, this.arg2(s))) {
                return this.arg1(s);
            }
        }
        else if (this.isProduct(s)) {
            if (this.isConstant(this.arg1(s)) && this.arg2(s).equals(s2)) {
                return this.makeProduct(this.makeSum("1", this.arg1(s)), s2);
            }
            if (this.isConstant(this.arg2(s)) && this.arg1(s).equals(s2)) {
                return this.makeProduct(this.makeSum("1", this.arg2(s)), s2);
            }
        }
        return this.list("+", s, s2);
    }
    
    String makeSumSimplifyTwoExpressions(final String s, final String s2) {
        if (this.isSum(s) && this.isSum(s2)) {
            if (this.arg1(s).equals(this.arg1(s2))) {
                return this.makeSum(this.makeProduct("2", this.arg1(s)), this.makeSum(this.arg2(s), this.arg2(s2)));
            }
            if (this.arg2(s).equals(this.arg2(s2))) {
                return this.makeSum(this.makeProduct("2", this.arg2(s)), this.makeSum(this.arg1(s), this.arg1(s2)));
            }
            if (this.arg1(s).equals(this.arg2(s2))) {
                return this.makeSum(this.makeProduct("2", this.arg1(s)), this.makeSum(this.arg2(s), this.arg1(s2)));
            }
            if (this.arg2(s).equals(this.arg1(s2))) {
                return this.makeSum(this.makeProduct("2", this.arg1(s2)), this.makeSum(this.arg1(s), this.arg2(s2)));
            }
        }
        else if (this.isSum(s) && this.isSubtraction(s2)) {
            if (this.arg1(s).equals(this.arg1(s2))) {
                return this.makeSum(this.makeProduct("2", this.arg1(s)), this.makeSubtraction(this.arg2(s), this.arg2(s2)));
            }
            if (this.arg1(s).equals(this.arg2(s2))) {
                return this.makeSum(this.arg2(s), this.arg1(s2));
            }
            if (this.arg2(s).equals(this.arg1(s2))) {
                return this.makeSum(this.makeProduct("2", this.arg1(s2)), this.makeSubtraction(this.arg1(s), this.arg2(s2)));
            }
            if (this.arg2(s).equals(this.arg2(s2))) {
                return this.makeSum(this.arg1(s), this.arg1(s2));
            }
        }
        else if (this.isSum(s) && this.isProduct(s2)) {
            if (this.isConstant(this.arg1(s2))) {
                if (this.arg1(s).equals(this.arg2(s2))) {
                    return this.makeSum(this.arg2(s), this.makeProduct(this.makeSum("1", this.arg1(s2)), this.arg1(s)));
                }
                if (this.arg2(s).equals(this.arg2(s2))) {
                    return this.makeSum(this.arg1(s), this.makeProduct(this.makeSum("1", this.arg1(s2)), this.arg2(s)));
                }
            }
            else if (this.isConstant(this.arg2(s2))) {
                if (this.arg1(s).equals(this.arg1(s2))) {
                    return this.makeSum(this.arg2(s), this.makeProduct(this.makeSum("1", this.arg2(s2)), this.arg1(s)));
                }
                if (this.arg2(s).equals(this.arg1(s2))) {
                    return this.makeSum(this.arg1(s), this.makeProduct(this.makeSum("1", this.arg2(s2)), this.arg2(s)));
                }
            }
        }
        else {
            if (this.isSubtraction(s) && this.isSum(s2)) {
                return this.makeSumSimplifyTwoExpressions(s2, s);
            }
            if (this.isSubtraction(s) && this.isSubtraction(s2)) {
                if (this.arg1(s).equals(this.arg1(s2))) {
                    return this.makeSubtraction(this.makeProduct("2", this.arg1(s)), this.makeSum(this.arg2(s), this.arg2(s2)));
                }
                if (this.arg1(s).equals(this.arg2(s2))) {
                    return this.makeSubtraction(this.arg1(s2), this.arg2(s));
                }
                if (this.arg2(s).equals(this.arg1(s2))) {
                    return this.makeSubtraction(this.arg1(s), this.arg2(s2));
                }
                if (this.arg2(s).equals(this.arg2(s2))) {
                    return this.makeSubtraction(this.makeSum(this.arg1(s), this.arg1(s2)), this.makeProduct("2", this.arg2(s)));
                }
            }
            else if (this.isSubtraction(s) && this.isProduct(s2)) {
                if (this.isConstant(this.arg1(s2))) {
                    if (this.arg1(s).equals(this.arg2(s2))) {
                        return this.makeSubtraction(this.makeProduct(this.makeSum("1", this.arg1(s2)), this.arg1(s)), this.arg2(s));
                    }
                    if (this.arg2(s).equals(this.arg2(s2))) {
                        return this.makeSum(this.makeProduct(this.makeSubtraction(this.arg1(s2), "1"), this.arg2(s)), this.arg1(s));
                    }
                }
                else if (this.isConstant(this.arg2(s2))) {
                    if (this.arg1(s).equals(this.arg1(s2))) {
                        return this.makeSubtraction(this.makeProduct(this.makeSum("1", this.arg2(s2)), this.arg1(s)), this.arg2(s));
                    }
                    if (this.arg2(s).equals(this.arg1(s2))) {
                        return this.makeSum(this.makeProduct(this.makeSubtraction(this.arg2(s2), "1"), this.arg2(s)), this.arg1(s));
                    }
                }
            }
        }
        return this.list("+", s, s2);
    }
    
    String makeProductSimplifyVariable(final String s, final String s2) {
        if (this.isSum(s)) {
            return this.makeSum(this.makeProduct(s2, this.arg1(s)), this.makeProduct(s2, this.arg2(s)));
        }
        if (this.isSubtraction(s)) {
            return this.makeSubtraction(this.makeProduct(s2, this.arg1(s)), this.makeProduct(s2, this.arg2(s)));
        }
        if (this.isPower(s) && s2.equals(this.arg1(s))) {
            return this.makePower(s2, this.makeSum("1", this.arg2(s)));
        }
        return this.list("*", s2, s);
    }
    
    String makeProductSimplifyTwoExp(final String s, final String s2) {
        if (this.isSum(s) && this.isSubtraction(s2) && this.arg1(s).equals(this.arg1(s2)) && this.arg2(s).equals(this.arg2(s2))) {
            return this.makeSubtraction(this.makePower(this.arg1(s), "2"), this.makePower(this.arg2(s), "2"));
        }
        if (this.isSum(s) && this.isSubtraction(s2) && this.arg1(s).equals(this.arg2(s2)) && this.arg2(s).equals(this.arg1(s2))) {
            return this.makeSubtraction(this.makePower(this.arg2(s), "2"), this.makePower(this.arg1(s), "2"));
        }
        if (this.isSubtraction(s) && this.isSum(s2) && this.arg1(s).equals(this.arg1(s2)) && this.arg2(s).equals(this.arg2(s2))) {
            return this.makeSubtraction(this.makePower(this.arg1(s), "2"), this.makePower(this.arg2(s), "2"));
        }
        if (this.isSubtraction(s) && this.isSum(s2) && this.arg1(s).equals(this.arg2(s2)) && this.arg2(s).equals(this.arg1(s2))) {
            return this.makeSubtraction(this.makePower(this.arg1(s), "2"), this.makePower(this.arg2(s), "2"));
        }
        return this.list("*", s, s2);
    }
    
    String makeProductSimplifyConstant(final String s, final String s2) {
        final double doubleValue = Double.valueOf(s2);
        if (this.isSum(s)) {
            if (doubleValue < 0.0) {
                return this.makeSubtraction(this.makeProduct(s2, this.arg1(s)), this.makeProduct(String.valueOf(-1.0 * doubleValue), this.arg2(s)));
            }
            if (doubleValue > 0.0) {
                return this.makeSum(this.makeProduct(s2, this.arg1(s)), this.makeProduct(s2, this.arg2(s)));
            }
            return "0";
        }
        else {
            if (!this.isSubtraction(s)) {
                if (this.isProduct(s)) {
                    if (this.isConstant(this.arg1(s))) {
                        return this.makeProduct(String.valueOf(doubleValue * Double.valueOf(this.arg1(s))), this.arg2(s));
                    }
                    if (this.isConstant(this.arg2(s))) {
                        return this.makeProduct(String.valueOf(doubleValue * Double.valueOf(this.arg2(s))), this.arg1(s));
                    }
                }
                return this.list("*", s2, s);
            }
            if (doubleValue > 0.0) {
                return this.makeSubtraction(this.makeProduct(s2, this.arg1(s)), this.makeProduct(s2, this.arg2(s)));
            }
            if (doubleValue < 0.0) {
                return this.makeSum(this.makeProduct(s2, this.arg1(s)), this.makeProduct(String.valueOf(-1.0 * doubleValue), this.arg2(s)));
            }
            return "0";
        }
    }
    
    String makeDivisionSimplifyExprThruVar(final String s, final String s2) {
        if (s2.equals(this.arg1(s))) {
            if (this.isSum(s)) {
                return this.makeSum("1", this.makeDivision(this.arg2(s), s2));
            }
            if (this.isSubtraction(s)) {
                return this.makeSubtraction("1", this.makeDivision(this.arg2(s), s2));
            }
            if (this.isProduct(s)) {
                return this.makeDivision(this.arg2(s), s2);
            }
        }
        else if (s2.equals(this.arg2(s))) {
            if (this.isSum(s)) {
                return this.makeSum(this.makeDivision(this.arg1(s), s2), "1");
            }
            if (this.isSubtraction(s)) {
                return this.makeSubtraction(this.makeDivision(this.arg1(s), s2), "1");
            }
            if (this.isProduct(s)) {
                return this.makeDivision(this.arg1(s), s2);
            }
        }
        return this.list("/", s, s2);
    }
    
    String makeSubtractionSimplifyConstantVariableArg2(final String s, final String s2) {
        if (this.isConstant(s2)) {
            if (this.isSum(s)) {
                if (this.isConstant(this.arg1(s))) {
                    return this.makeSum(String.valueOf(Double.valueOf(this.arg1(s)) - Double.valueOf(s2)), this.arg2(s));
                }
                if (this.isConstant(this.arg2(s))) {
                    return this.makeSum(String.valueOf(Double.valueOf(this.arg2(s)) - Double.valueOf(s2)), this.arg1(s));
                }
            }
            else if (this.isSubtraction(s)) {
                if (this.isConstant(this.arg1(s))) {
                    return this.makeSum(String.valueOf(Double.valueOf(this.arg1(s)) - Double.valueOf(s2)), this.arg2(s));
                }
                if (this.isConstant(this.arg2(s))) {
                    return this.makeSum(String.valueOf(-1.0 * Double.valueOf(this.arg2(s)) - Double.valueOf(s2)), this.arg1(s));
                }
            }
        }
        else if (this.isVariable(s2)) {
            if (this.isSum(s)) {
                if (this.isVariable(this.arg1(s)) && this.isSameVariable(s2, this.arg1(s))) {
                    return this.arg2(s);
                }
                if (this.isVariable(this.arg2(s)) && this.isSameVariable(s2, this.arg2(s))) {
                    return this.arg1(s);
                }
            }
            else if (this.isSubtraction(s)) {
                if (this.isVariable(this.arg1(s)) && this.isSameVariable(s2, this.arg1(s))) {
                    return this.makeProduct("-1", this.arg2(s));
                }
                if (this.isVariable(this.arg2(s)) && this.isSameVariable(s2, this.arg2(s))) {
                    return this.makeSubtraction(this.arg1(s), this.makeProduct("2", this.arg2(s)));
                }
            }
            else if (this.isProduct(s)) {
                if (this.isConstant(this.arg1(s)) && this.arg2(s).equals(s2)) {
                    return this.makeProduct(this.makeSubtraction(this.arg1(s), "1"), s2);
                }
                if (this.isConstant(this.arg2(s)) && this.arg1(s).equals(s2)) {
                    return this.makeProduct(this.makeSubtraction(this.arg2(s), "1"), s2);
                }
            }
        }
        return this.list("-", s, s2);
    }
    
    String makeSubtractionSimplifyConstantVariableArg1(final String s, final String s2) {
        if (this.isConstant(s)) {
            if (this.isSum(s2)) {
                if (this.isConstant(this.arg1(s2))) {
                    return this.makeSubtraction(String.valueOf(Double.valueOf(this.arg1(s2)) - Double.valueOf(s)), this.arg2(s2));
                }
                if (this.isConstant(this.arg2(s2))) {
                    return this.makeSubtraction(String.valueOf(Double.valueOf(this.arg2(s2)) - Double.valueOf(s)), this.arg1(s2));
                }
            }
            else if (this.isSubtraction(s2)) {
                if (this.isConstant(this.arg1(s2))) {
                    return this.makeSum(String.valueOf(Double.valueOf(this.arg1(s2)) - Double.valueOf(s)), this.arg2(s2));
                }
                if (this.isConstant(this.arg2(s2))) {
                    return this.makeSubtraction(String.valueOf(Double.valueOf(s) + Double.valueOf(this.arg2(s2))), this.arg1(s2));
                }
            }
        }
        else if (this.isVariable(s)) {
            if (this.isSum(s2)) {
                if (this.isVariable(this.arg1(s2)) && this.isSameVariable(s, this.arg1(s2))) {
                    return this.makeProduct("-1", this.arg2(s2));
                }
                if (this.isVariable(this.arg2(s2)) && this.isSameVariable(s, this.arg2(s2))) {
                    return this.makeProduct("-1", this.arg1(s2));
                }
            }
            else if (this.isSubtraction(s2)) {
                if (this.isVariable(this.arg1(s2)) && this.isSameVariable(s, this.arg1(s2))) {
                    return this.arg2(s2);
                }
                if (this.isVariable(this.arg2(s2)) && this.isSameVariable(s, this.arg2(s2))) {
                    return this.makeSubtraction(this.arg1(s2), this.makeProduct("2", this.arg2(s2)));
                }
            }
            else if (this.isProduct(s2)) {
                if (this.isConstant(this.arg1(s2)) && this.arg2(s2).equals(s)) {
                    return this.makeProduct(this.makeSubtraction("1", this.arg1(s2)), s);
                }
                if (this.isConstant(this.arg2(s2)) && this.arg1(s2).equals(s)) {
                    return this.makeProduct(this.makeSubtraction("1", this.arg2(s2)), s);
                }
            }
        }
        return this.list("-", s, s2);
    }
    
    String makeSubtractionSimplifyTwoExp(final String s, final String s2) {
        if (this.isSum(s) && this.isSum(s2)) {
            if (this.arg1(s).equals(this.arg1(s2))) {
                return this.makeSubtraction(this.arg2(s), this.arg2(s2));
            }
            if (this.arg1(s).equals(this.arg2(s2))) {
                return this.makeSubtraction(this.arg2(s), this.arg1(s2));
            }
            if (this.arg2(s).equals(this.arg1(s2))) {
                return this.makeSubtraction(this.arg1(s), this.arg2(s2));
            }
            if (this.arg2(s).equals(this.arg2(s2))) {
                return this.makeSubtraction(this.arg1(s), this.arg1(s2));
            }
        }
        else if (this.isSum(s) && this.isSubtraction(s2)) {
            if (this.arg1(s).equals(this.arg1(s2))) {
                return this.makeSum(this.arg2(s), this.arg2(s2));
            }
            if (this.arg1(s).equals(this.arg2(s2))) {
                return this.makeSum(this.makeProduct("2", this.arg1(s)), this.makeSubtraction(this.arg2(s), this.arg1(s2)));
            }
            if (this.arg2(s).equals(this.arg1(s2))) {
                return this.makeSum(this.arg1(s), this.arg2(s2));
            }
            if (this.arg2(s).equals(this.arg2(s2))) {
                return this.makeSum(this.makeProduct("2", this.arg2(s)), this.makeSubtraction(this.arg1(s), this.arg1(s2)));
            }
        }
        else if (this.isSum(s) && this.isProduct(s2)) {
            if (this.isConstant(this.arg1(s2))) {
                if (this.arg1(s).equals(this.arg2(s2))) {
                    return this.makeSum(this.makeProduct(this.makeSubtraction("1", this.arg1(s2)), this.arg1(s)), this.arg2(s));
                }
                if (this.arg2(s).equals(this.arg2(s2))) {
                    return this.makeSum(this.makeProduct(this.makeSubtraction("1", this.arg1(s2)), this.arg2(s)), this.arg1(s));
                }
            }
            else if (this.isConstant(this.arg2(s2))) {
                if (this.arg1(s).equals(this.arg1(s2))) {
                    return this.makeSum(this.makeProduct(this.makeSubtraction("1", this.arg2(s2)), this.arg1(s)), this.arg2(s));
                }
                if (this.arg2(s).equals(this.arg1(s2))) {
                    return this.makeSum(this.makeProduct(this.makeSubtraction("1", this.arg2(s2)), this.arg2(s)), this.arg1(s));
                }
            }
        }
        else if (this.isSubtraction(s) && this.isSum(s2)) {
            if (this.arg1(s).equals(this.arg1(s2))) {
                return this.makeSubtraction(this.makeProduct("-1", this.arg2(s)), this.arg2(s2));
            }
            if (this.arg1(s).equals(this.arg2(s2))) {
                return this.makeSubtraction(this.makeProduct("-1", this.arg2(s)), this.arg1(s2));
            }
            if (this.arg2(s).equals(this.arg1(s2))) {
                return this.makeSubtraction(this.makeSubtraction(this.arg1(s), this.arg2(s2)), this.makeProduct("2", this.arg1(s2)));
            }
            if (this.arg2(s).equals(this.arg2(s2))) {
                return this.makeSubtraction(this.makeSubtraction(this.arg1(s), this.arg1(s2)), this.makeProduct("2", this.arg2(s)));
            }
        }
        else if (this.isSubtraction(s) && this.isSubtraction(s2)) {
            if (this.arg1(s).equals(this.arg1(s2))) {
                return this.makeSubtraction(this.arg2(s2), this.arg2(s));
            }
            if (this.arg1(s).equals(this.arg2(s2))) {
                return this.makeSubtraction(this.makeProduct("2", this.arg1(s)), this.makeSum(this.arg2(s), this.arg1(s2)));
            }
            if (this.arg2(s).equals(this.arg1(s2))) {
                return this.makeSubtraction(this.makeSum(this.arg1(s), this.arg2(s2)), this.makeProduct("2", this.arg1(s2)));
            }
            if (this.arg2(s).equals(this.arg2(s2))) {
                return this.makeSubtraction(this.arg1(s), this.arg1(s2));
            }
        }
        else if (this.isSubtraction(s) && this.isProduct(s2)) {
            if (this.isConstant(this.arg1(s2))) {
                if (this.arg1(s).equals(this.arg2(s2))) {
                    return this.makeSubtraction(this.makeProduct(this.makeSubtraction("1", this.arg1(s2)), this.arg1(s)), this.arg2(s));
                }
                if (this.arg2(s).equals(this.arg2(s2))) {
                    return this.makeSubtraction(this.makeProduct(this.makeSubtraction("-1", this.arg1(s2)), this.arg2(s)), this.arg1(s));
                }
            }
            else if (this.isConstant(this.arg2(s2))) {
                if (this.arg1(s).equals(this.arg1(s2))) {
                    return this.makeSubtraction(this.makeProduct(this.makeSubtraction("1", this.arg2(s2)), this.arg1(s)), this.arg2(s));
                }
                if (this.arg2(s).equals(this.arg1(s2))) {
                    return this.makeSubtraction(this.makeProduct(this.makeSubtraction("-1", this.arg2(s2)), this.arg2(s)), this.arg1(s));
                }
            }
        }
        return this.list("-", s, s2);
    }
    
    String Derive(final String s, final String s2) {
        if (this.isConstant(s)) {
            return "0";
        }
        if (this.isVariable(s)) {
            return this.deriveVariable(s, s2);
        }
        if (this.isSum(s)) {
            return this.deriveSum(s, s2);
        }
        if (this.isSubtraction(s)) {
            return this.deriveSubtraction(s, s2);
        }
        if (this.isProduct(s)) {
            return this.deriveProduct(s, s2);
        }
        if (this.isDivision(s)) {
            return this.deriveDivision(s, s2);
        }
        if (this.isSquareroot(s)) {
            return this.deriveSqrt(s, s2);
        }
        if (this.isSine(s)) {
            return this.deriveSin(s, s2);
        }
        if (this.isCosine(s)) {
            return this.deriveCos(s, s2);
        }
        if (this.isTan(s)) {
            return this.deriveTan(s, s2);
        }
        if (this.isPower(s)) {
            return this.derivePower(s, s2);
        }
        if (this.isLn(s)) {
            return this.deriveLn(s, s2);
        }
        if (this.isE(s)) {
            return this.deriveE(s, s2);
        }
        if (this.isAtan(s)) {
            return this.deriveAtan(s, s2);
        }
        if (this.isAsin(s)) {
            return this.deriveAsin(s, s2);
        }
        if (this.isAcos(s)) {
            return this.deriveAcos(s, s2);
        }
        if (this.isSinhyp(s)) {
            return this.deriveSinhyp(s, s2);
        }
        if (this.isCoshyp(s)) {
            return this.deriveCoshyp(s, s2);
        }
        if (this.isTanhyp(s)) {
            return this.deriveTanhyp(s, s2);
        }
        if (this.isCotan(s)) {
            return this.deriveCotan(s, s2);
        }
        if (this.isAcotan(s)) {
            return this.deriveAcotan(s, s2);
        }
        return "";
    }
    
    String deriveAcotan(final String s, final String s2) {
        return this.makeDivision(this.makeProduct("-1", this.Derive(this.arg1(s), s2)), this.makeSum("1", this.makePower(this.arg1(s), "2")));
    }
    
    String deriveCotan(final String s, final String s2) {
        return this.makeProduct(this.Derive(this.arg1(s), s2), this.makeSubtraction("-1", this.makePower(this.makeCotan(this.arg1(s)), "2")));
    }
    
    String deriveTanhyp(final String s, final String s2) {
        return this.makeProduct(this.Derive(this.arg1(s), s2), this.makeSubtraction("1", this.makePower(this.makeTanhyp(this.arg1(s)), "2")));
    }
    
    String deriveCoshyp(final String s, final String s2) {
        return this.makeProduct(this.Derive(this.arg1(s), s2), this.makeSinhyp(this.arg1(s)));
    }
    
    String deriveSinhyp(final String s, final String s2) {
        return this.makeProduct(this.Derive(this.arg1(s), s2), this.makeCoshyp(this.arg1(s)));
    }
    
    String deriveAcos(final String s, final String s2) {
        return this.makeProduct("-1", this.makeDivision(this.Derive(this.arg1(s), s2), this.makeSquareroot(this.makeSubtraction("1", this.makePower(this.arg1(s), "2")))));
    }
    
    String deriveAsin(final String s, final String s2) {
        return this.makeDivision(this.Derive(this.arg1(s), s2), this.makeSquareroot(this.makeSubtraction("1", this.makePower(this.arg1(s), "2"))));
    }
    
    String deriveAtan(final String s, final String s2) {
        return this.makeDivision(this.Derive(this.arg1(s), s2), this.makeSum("1", this.makePower(this.arg1(s), "2")));
    }
    
    String deriveE(final String s, final String s2) {
        return this.makeProduct(this.Derive(this.arg1(s), s2), this.makeE(this.arg1(s)));
    }
    
    String deriveLn(final String s, final String s2) {
        return this.makeProduct(this.Derive(this.arg1(s), s2), this.makeDivision("1", this.arg1(s)));
    }
    
    String deriveTan(final String s, final String s2) {
        return this.makeProduct(this.Derive(this.arg1(s), s2), this.makeSum("1", this.makePower(this.makeTan(this.arg1(s)), "2")));
    }
    
    String deriveSin(final String s, final String s2) {
        return this.makeProduct(this.Derive(this.arg1(s), s2), this.makeCosine(this.arg1(s)));
    }
    
    String deriveCos(final String s, final String s2) {
        return this.makeProduct(this.Derive(this.arg1(s), s2), this.makeProduct("-1", this.makeSine(this.arg1(s))));
    }
    
    String deriveSqrt(final String s, final String s2) {
        return this.makeDivision(this.Derive(this.arg1(s), s2), this.makeProduct("2", this.makeSquareroot(this.arg1(s))));
    }
    
    String deriveDivision(final String s, final String s2) {
        return this.makeDivision(this.makeSubtraction(this.makeProduct(this.arg2(s), this.Derive(this.arg1(s), s2)), this.makeProduct(this.arg1(s), this.Derive(this.arg2(s), s2))), this.makePower(this.arg2(s), "2"));
    }
    
    String deriveProduct(final String s, final String s2) {
        return this.makeSum(this.makeProduct(this.arg1(s), this.Derive(this.arg2(s), s2)), this.makeProduct(this.Derive(this.arg1(s), s2), this.arg2(s)));
    }
    
    String deriveSubtraction(final String s, final String s2) {
        return this.makeSubtraction(this.Derive(this.arg1(s), s2), this.Derive(this.arg2(s), s2));
    }
    
    String deriveSum(final String s, final String s2) {
        return this.makeSum(this.Derive(this.arg1(s), s2), this.Derive(this.arg2(s), s2));
    }
    
    String derivePower(final String s, final String s2) {
        if (this.isConstant(this.arg1(s))) {
            return this.makeProduct(this.makeProduct(this.makeLn(this.arg1(s)), this.Derive(this.arg2(s), s2)), s);
        }
        if (this.isConstant(this.arg2(s))) {
            return this.makeProduct(this.Derive(this.arg1(s), s2), this.makeProduct(this.arg2(s), this.makePower(this.arg1(s), this.makeSubtraction(this.arg2(s), "1"))));
        }
        return this.makeProduct(s, this.makeSum(this.makeProduct(this.Derive(this.arg2(s), s2), this.makeLn(this.arg1(s))), this.makeProduct(this.Derive(this.makeLn(this.arg1(s)), s2), this.arg2(s))));
    }
    
    String deriveVariable(final String s, final String s2) {
        if (this.isSameVariable(s, s2)) {
            return "1";
        }
        return "0";
    }
    
    String Simplify(final String s) {
        if (this.isConstant(s)) {
            if (this.isInteger(Double.valueOf(s))) {
                return String.valueOf((int)(Object)Double.valueOf(s));
            }
            return s;
        }
        else {
            if (this.isVariable(s)) {
                return s;
            }
            if (this.isSum(s)) {
                return this.makeSum(this.Simplify(this.arg1(s)), this.Simplify(this.arg2(s)));
            }
            if (this.isSubtraction(s)) {
                return this.makeSubtraction(this.Simplify(this.arg1(s)), this.Simplify(this.arg2(s)));
            }
            if (this.isProduct(s)) {
                return this.makeProduct(this.Simplify(this.arg1(s)), this.Simplify(this.arg2(s)));
            }
            if (this.isDivision(s)) {
                return this.makeDivision(this.Simplify(this.arg1(s)), this.Simplify(this.arg2(s)));
            }
            if (this.isSquareroot(s)) {
                return this.makeSquareroot(this.Simplify(this.arg1(s)));
            }
            if (this.isSine(s)) {
                return this.makeSine(this.Simplify(this.arg1(s)));
            }
            if (this.isCosine(s)) {
                return this.makeCosine(this.Simplify(this.arg1(s)));
            }
            if (this.isTan(s)) {
                return this.makeTan(this.Simplify(this.arg1(s)));
            }
            if (this.isPower(s)) {
                return this.makePower(this.Simplify(this.arg1(s)), this.Simplify(this.arg2(s)));
            }
            if (this.isLn(s)) {
                return this.makeLn(this.Simplify(this.arg1(s)));
            }
            if (this.isE(s)) {
                return this.makeE(this.Simplify(this.arg1(s)));
            }
            if (this.isSinhyp(s)) {
                return this.makeSinhyp(this.Simplify(this.arg1(s)));
            }
            if (this.isCoshyp(s)) {
                return this.makeCoshyp(this.Simplify(this.arg1(s)));
            }
            if (this.isTanhyp(s)) {
                return this.makeTanhyp(this.Simplify(this.arg1(s)));
            }
            if (this.isCotan(s)) {
                return this.makeCotan(this.Simplify(this.arg1(s)));
            }
            if (this.isAcotan(s)) {
                return this.makeAcotan(this.Simplify(this.arg1(s)));
            }
            return s;
        }
    }
    
    String SimplifyAsMuchAsPossible(final String s) {
        String s2 = "";
        String simplify = s;
        while (true) {
            simplify = this.Simplify(simplify);
            if (s2.equalsIgnoreCase(simplify)) {
                break;
            }
            s2 = simplify;
        }
        return simplify;
    }
    
    String firstOp(final String s) {
        return this.car(s);
    }
    
    String preToInfix(final String s) {
        if (this.isVariable(s) || this.isConstant(s)) {
            return s;
        }
        final String firstOp = this.firstOp(s);
        final String arg1 = this.arg1(s);
        if (!this.isTwoArgOp(firstOp)) {
            return String.valueOf(firstOp) + "(" + this.preToInfix(arg1) + ")";
        }
        final String arg2 = this.arg2(s);
        if (this.isConstant(arg1) || this.isVariable(arg1)) {
            if (this.isConstant(arg2) || this.isVariable(arg2)) {
                return String.valueOf(arg1) + firstOp + arg2;
            }
            if (firstOp.equalsIgnoreCase("+")) {
                return String.valueOf(arg1) + firstOp + this.preToInfix(arg2);
            }
            if (firstOp.equalsIgnoreCase("-") && (this.isDivision(arg2) || this.isProduct(arg2))) {
                return String.valueOf(arg1) + firstOp + this.preToInfix(arg2);
            }
            if (firstOp.equalsIgnoreCase("*") && (this.isPower(arg2) || this.isProduct(arg2) || !this.isTwoArgOp(this.firstOp(arg2)))) {
                return String.valueOf(arg1) + firstOp + this.preToInfix(arg2);
            }
            if (this.isTwoArgOp(this.firstOp(arg2))) {
                return String.valueOf(arg1) + firstOp + "(" + this.preToInfix(arg2) + ")";
            }
            return String.valueOf(arg1) + firstOp + this.preToInfix(arg2);
        }
        else if (this.isConstant(arg2) || this.isVariable(arg2)) {
            if (firstOp.equalsIgnoreCase("+")) {
                return String.valueOf(this.preToInfix(arg1)) + firstOp + arg2;
            }
            if (firstOp.equalsIgnoreCase("-")) {
                return String.valueOf(this.preToInfix(arg1)) + firstOp + arg2;
            }
            if (this.isTwoArgOp(this.firstOp(arg1))) {
                return "(" + this.preToInfix(arg1) + ")" + firstOp + arg2;
            }
            return String.valueOf(this.preToInfix(arg1)) + firstOp + arg2;
        }
        else {
            if (firstOp.equalsIgnoreCase("+")) {
                return String.valueOf(this.preToInfix(arg1)) + firstOp + this.preToInfix(arg2);
            }
            if (firstOp.equalsIgnoreCase("-")) {
                if (this.isProduct(arg2) || this.isDivision(arg2)) {
                    return String.valueOf(this.preToInfix(arg1)) + firstOp + this.preToInfix(arg2);
                }
                return String.valueOf(this.preToInfix(arg1)) + firstOp + "(" + this.preToInfix(arg2) + ")";
            }
            else {
                if (this.isTwoArgOp(this.firstOp(arg1)) && this.isTwoArgOp(this.firstOp(arg2))) {
                    return "(" + this.preToInfix(arg1) + ")" + firstOp + "(" + this.preToInfix(arg2) + ")";
                }
                if (this.isTwoArgOp(this.firstOp(arg1)) && !this.isTwoArgOp(this.firstOp(arg2))) {
                    return "(" + this.preToInfix(arg1) + ")" + firstOp + this.preToInfix(arg2);
                }
                if (this.isTwoArgOp(this.firstOp(arg2)) && !this.isTwoArgOp(this.firstOp(arg1))) {
                    return String.valueOf(this.preToInfix(arg1)) + firstOp + "(" + this.preToInfix(arg2) + ")";
                }
                return String.valueOf(this.preToInfix(arg1)) + firstOp + this.preToInfix(arg2);
            }
        }
    }
    
    String InToPrefix(final String s) throws Exception {
        String s2 = "";
        int i = 0;
        if (s == "") {
            throw new Exception("Wrong number of arguments to operator");
        }
        if (this.isVariable(s)) {
            this.storeVars(s);
            return s;
        }
        if (this.isAllNumbers(s)) {
            return s;
        }
        final int match;
        if (s.charAt(0) == '(' && (match = this.Match(s, 0)) == s.length() - 1) {
            return this.InToPrefix(s.substring(1, match));
        }
        while (i < s.length()) {
            final String op;
            if ((op = this.getOp(s, i)) != null) {
                if (this.isTwoArgOp(op)) {
                    String s3;
                    if (op.equalsIgnoreCase("+") || op.equalsIgnoreCase("-")) {
                        if (s2 == "") {
                            s2 = "0";
                        }
                        s3 = this.ArgToPlusOrMinus(s, i + 1);
                    }
                    else if (op.equalsIgnoreCase("*") || op.equalsIgnoreCase("/")) {
                        if (s2 == "") {
                            throw new Exception("Wrong number of arguments to operator");
                        }
                        s3 = this.ArgToAnyOpExcept(s, i + 1, "^");
                    }
                    else {
                        if (s2 == "") {
                            throw new Exception("Wrong number of arguments to operator");
                        }
                        s3 = this.Arg(s, i + op.length());
                    }
                    s2 = "( " + op + " " + s2 + " " + this.InToPrefix(s3) + " )";
                    i += op.length() + s3.length();
                }
                else {
                    final String arg = this.Arg(s, i + op.length());
                    s2 = String.valueOf(s2) + "( " + op + " " + this.InToPrefix(arg) + " )";
                    i += op.length() + arg.length();
                }
            }
            else {
                final String arg2 = this.Arg(s, i);
                final String op2 = this.getOp(s, i + arg2.length());
                if (op2 == null) {
                    throw new Exception("Missing operator");
                }
                if (this.isTwoArgOp(op2)) {
                    String s4;
                    if (op2.equalsIgnoreCase("+") || op2.equalsIgnoreCase("-")) {
                        s4 = this.ArgToPlusOrMinus(s, i + 1 + arg2.length());
                    }
                    else if (op2.equalsIgnoreCase("*") || op2.equalsIgnoreCase("/")) {
                        s4 = this.ArgToAnyOpExcept(s, i + 1 + arg2.length(), "^");
                    }
                    else {
                        s4 = this.Arg(s, i + op2.length() + arg2.length());
                    }
                    s2 = String.valueOf(s2) + "( " + op2 + " " + this.InToPrefix(arg2) + " " + this.InToPrefix(s4) + " )";
                    i += arg2.length() + s4.length() + op2.length();
                }
                else {
                    s2 = String.valueOf(s2) + "( " + op2 + " " + this.InToPrefix(arg2) + " )";
                    i += op2.length() + arg2.length();
                }
            }
        }
        return s2;
    }
    
    String Arg(final String s, final int n) {
        int i = n;
        String s2 = "";
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                final int match = this.Match(s, i);
                s2 = String.valueOf(s2) + s.substring(i, match + 1);
                i = match + 1;
            }
            else {
                final String op;
                if ((op = this.getOp(s, i)) != null) {
                    if (s2 != "" && !this.isTwoArgOp(this.BackTrack(s2))) {
                        return s2;
                    }
                    s2 = String.valueOf(s2) + op;
                    i += op.length();
                }
                else {
                    s2 = String.valueOf(s2) + s.charAt(i);
                    ++i;
                }
            }
        }
        return s2;
    }
    
    String ArgToAnyOpExcept(final String s, final int n, final String s2) {
        int i = n;
        String s3 = "";
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                final int match = this.Match(s, i);
                s3 = String.valueOf(s3) + s.substring(i, match + 1);
                i = match + 1;
            }
            else {
                final String op;
                if ((op = this.getOp(s, i)) != null) {
                    if (s3 != "" && !this.isTwoArgOp(this.BackTrack(s3)) && !op.equalsIgnoreCase(s2)) {
                        return s3;
                    }
                    s3 = String.valueOf(s3) + op;
                    i += op.length();
                }
                else {
                    s3 = String.valueOf(s3) + s.charAt(i);
                    ++i;
                }
            }
        }
        return s3;
    }
    
    String ArgToPlusOrMinus(final String s, final int n) {
        int i = n;
        String s2 = "";
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                final int match = this.Match(s, i);
                s2 = String.valueOf(s2) + s.substring(i, match + 1);
                i = match;
            }
            else if ((s.charAt(i) == '+' || s.charAt(i) == '-') && s2 != "") {
                if (!this.isTwoArgOp(this.BackTrack(s2))) {
                    return s2;
                }
                s2 = String.valueOf(s2) + s.charAt(i);
            }
            else {
                s2 = String.valueOf(s2) + s.charAt(i);
            }
            ++i;
        }
        return s2;
    }
    
    String BackTrack(final String s) {
        try {
            for (int i = 0; i <= this.maxoplength; ++i) {
                final String op;
                if ((op = this.getOp(s, s.length() - 1 - this.maxoplength + i)) != null && s.length() - this.maxoplength - 1 + i + op.length() == s.length()) {
                    return op;
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    String getOp(final String s, final int n) {
        for (int i = 0; i < this.maxoplength; ++i) {
            try {
                if (this.isOperator(s.substring(n, n + (this.maxoplength - i)))) {
                    return s.substring(n, n + (this.maxoplength - i));
                }
            }
            catch (Exception ex) {}
        }
        return null;
    }
    
    String PrepareExp(final String s) {
        String s2 = this.SkipSpaces(s).toLowerCase();
        if (s2.charAt(0) == '+' || s2.charAt(0) == '-') {
            s2 = "0" + s2;
        }
        return this.parseE(s2);
    }
    
    String parseE(final String s) {
        String s2 = "";
        for (int i = 0; i < s.length(); ++i) {
            try {
                if (s.charAt(i) == 'e' && this.isConstant(s.charAt(i - 1))) {
                    String s3 = this.Arg(s, i + 1);
                    if (s3.charAt(s3.length() - 1) == ')') {
                        s3 = s3.substring(0, s3.indexOf(")"));
                    }
                    if (this.isAllNumbers(s3)) {
                        s2 = String.valueOf(s2) + "*10^";
                    }
                    else {
                        s2 = String.valueOf(s2) + s.charAt(i);
                    }
                }
                else {
                    s2 = String.valueOf(s2) + s.charAt(i);
                }
            }
            catch (Exception ex) {
                s2 = String.valueOf(s2) + s.charAt(i);
            }
        }
        return s2;
    }
    
    String SkipSpaces(final String s) {
        String string = "";
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != ' ') {
                string = String.valueOf(string) + s.charAt(i);
            }
        }
        return string;
    }
    
    String parseSigns(final String s) {
        String s2 = "";
        for (int i = 0; i < s.length(); ++i) {
            try {
                if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                    s2 = String.valueOf(s2) + "+";
                    ++i;
                }
                else if (s.charAt(i) == '+' && s.charAt(i + 1) == '-') {
                    s2 = String.valueOf(s2) + "-";
                    ++i;
                }
                else if (s.charAt(i) == '-' && s.charAt(i + 1) == '+') {
                    s2 = String.valueOf(s2) + "-";
                    ++i;
                }
                else if (s.charAt(i) == '-' && s.charAt(i + 1) == '-') {
                    s2 = String.valueOf(s2) + "+";
                    ++i;
                }
                else {
                    s2 = String.valueOf(s2) + s.charAt(i);
                }
            }
            catch (Exception ex) {}
        }
        return s2;
    }
    
    int Match(final String s, final int n) {
        int i = n;
        int n2 = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                ++n2;
            }
            else if (s.charAt(i) == ')') {
                --n2;
            }
            if (n2 == 0) {
                return i;
            }
            ++i;
        }
        return n;
    }
    
    String findVariable(final String s) {
        if (this.lastIndex >= s.length()) {
            this.lastIndex = 0;
            return null;
        }
        final int index = s.indexOf(";", this.lastIndex);
        if (index == -1) {
            final String substring = s.substring(this.lastIndex, s.length());
            this.lastIndex = s.length();
            return substring;
        }
        final String substring2 = s.substring(this.lastIndex, index);
        this.lastIndex = index + 1;
        return substring2;
    }
    
    public static void main(final String[] array) {
        final GUIDerive guiDerive = new GUIDerive();
        GUIDerive.main(new String[2]);
    }
    
    String[] doubleAndCopyArray(final String[] array) {
        final int length = array.length;
        final String[] array2 = new String[length * 2];
        for (int i = 0; i < length; ++i) {
            array2[i] = array[i];
        }
        return array2;
    }
    
    void storeVars(final String s) {
        if (!this.tempstorage.contains(s)) {
            if (this.VARIABLES == "") {
                this.VARIABLES = s;
                this.storedvars = s;
            }
            else {
                this.VARIABLES = String.valueOf(this.VARIABLES) + ";" + s;
                this.storedvars = String.valueOf(this.storedvars) + ";" + s;
            }
            this.tempstorage.addElement(s);
        }
    }
    
    void clearVars() {
        this.tempstorage.removeAllElements();
        this.VARIABLES = "";
        this.storedvars = "";
    }
    
    public String[] diff(final String s, final String s2) throws notValidSyntaxException {
        String[] doubleAndCopyArray = new String[100];
        int n = 0;
        if (s == null || s.equals("")) {
            throw new notValidSyntaxException("Arguments null or empty string");
        }
        this.clearVars();
        final String prepareExp = this.PrepareExp(s);
        try {
            this.Syntax(prepareExp);
            final String simplifyAsMuchAsPossible = this.SimplifyAsMuchAsPossible(this.InToPrefix(prepareExp));
            String s3;
            if (s2 == null || s2.equals("")) {
                s3 = this.SkipSpaces(((this.storedvars == "") ? this.defaultvar : this.storedvars).toLowerCase());
            }
            else {
                s3 = this.SkipSpaces(s2.toLowerCase());
            }
            String variable;
            while ((variable = this.findVariable(s3)) != null) {
                this.Syntax(variable);
                if (!this.isVariable(variable)) {
                    throw new Exception("Not a valid variable " + variable);
                }
                final String preToInfix = this.preToInfix(this.SimplifyAsMuchAsPossible(this.Derive(simplifyAsMuchAsPossible, variable)));
                if (n > doubleAndCopyArray.length - 1) {
                    doubleAndCopyArray = this.doubleAndCopyArray(doubleAndCopyArray);
                }
                doubleAndCopyArray[n] = this.parseSigns(preToInfix);
                ++n;
            }
            return doubleAndCopyArray;
        }
        catch (StringIndexOutOfBoundsException ex2) {
            throw new notValidSyntaxException("Wrong number of arguments to operator");
        }
        catch (Exception ex) {
            throw new notValidSyntaxException(ex.getMessage());
        }
    }
    
    public String[] diff(final String s) throws notValidSyntaxException {
        return this.diff(s, "");
    }
}
