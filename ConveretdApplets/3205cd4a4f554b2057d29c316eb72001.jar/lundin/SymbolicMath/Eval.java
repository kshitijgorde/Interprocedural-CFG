// 
// Decompiled by Procyon v0.5.30
// 

package lundin.SymbolicMath;

import java.util.Hashtable;

public class Eval
{
    String EXPRESSION;
    String PREFIXEXP;
    Hashtable HTBL;
    final String[] ALLOWEDOPS;
    final String[] TWOARGOPS;
    final String[] ALLOWEDSYM;
    final String[] SPECIAL_CONSTANTS;
    final double[] SPECIAL_CONSTANT_VALUES;
    int MAXOPLENGTH;
    int lastIndex;
    
    int setMaxOpLength() {
        int length = 0;
        for (int i = 0; i < this.ALLOWEDOPS.length; ++i) {
            if (this.ALLOWEDOPS[i].length() > length) {
                length = this.ALLOWEDOPS[i].length();
            }
        }
        return length;
    }
    
    String car(final String s) {
        s.length();
        int n = 0;
        int i = 2;
        int n2 = 0;
        if (s == "( )" || s == null) {
            return null;
        }
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
        if (s == "( )" || s == null) {
            return null;
        }
        return "(" + s.substring((String.valueOf("") + this.car(s)).length() + 2, s.length());
    }
    
    String arg1(final String s) {
        return this.car(this.cdr(s));
    }
    
    String arg2(final String s) {
        return this.car(this.cdr(this.cdr(s)));
    }
    
    boolean isAllowedSym(final char c) {
        for (int i = 0; i < this.ALLOWEDSYM.length; ++i) {
            if (this.ALLOWEDSYM[i].equalsIgnoreCase(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }
    
    boolean isAllowedSym(final String s) {
        for (int i = 0; i < this.ALLOWEDSYM.length; ++i) {
            if (this.ALLOWEDSYM[i].equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
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
                    if (this.isTwoArgOp(op2) && !op2.equalsIgnoreCase("+") && !op2.equalsIgnoreCase("-")) {
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
    
    boolean isConstant(final char c) {
        return this.isConstant(String.valueOf(c));
    }
    
    boolean isConstant(final String s) {
        try {
            if (Float.isNaN(Float.valueOf(s))) {
                return false;
            }
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    boolean isConstant(final float n) {
        return true;
    }
    
    boolean isConstant(final int n) {
        return true;
    }
    
    boolean isConstant(final double n) {
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
    
    boolean isOperator(final String s) {
        for (int i = 0; i < this.ALLOWEDOPS.length; ++i) {
            if (this.ALLOWEDOPS[i].equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
    
    boolean isOperator(final char c) {
        for (int i = 0; i < this.ALLOWEDOPS.length; ++i) {
            if (this.ALLOWEDOPS[i].equalsIgnoreCase(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }
    
    boolean isTwoArgOp(final String s) {
        for (int i = 0; i < this.TWOARGOPS.length; ++i) {
            if (this.TWOARGOPS[i].equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
    
    boolean isTwoArgOp(final char c) {
        for (int i = 0; i < this.TWOARGOPS.length; ++i) {
            if (this.TWOARGOPS[i].equalsIgnoreCase(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }
    
    boolean isInteger(final double n) {
        return n - (int)n == 0.0;
    }
    
    boolean isInteger(final float n) {
        return n - (int)n == 0.0f;
    }
    
    boolean isInteger(final int n) {
        return true;
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
        return this.car(s).equalsIgnoreCase("+");
    }
    
    boolean isSubtraction(final String s) {
        return this.car(s).equalsIgnoreCase("-");
    }
    
    boolean isProduct(final String s) {
        return this.car(s).equalsIgnoreCase("*");
    }
    
    boolean isDivision(final String s) {
        return this.car(s).equalsIgnoreCase("/");
    }
    
    boolean isSquareroot(final String s) {
        return this.car(s).equalsIgnoreCase("sqrt");
    }
    
    boolean isCosine(final String s) {
        return this.car(s).equalsIgnoreCase("cos");
    }
    
    boolean isSine(final String s) {
        return this.car(s).equalsIgnoreCase("sin");
    }
    
    boolean isTan(final String s) {
        return this.car(s).equalsIgnoreCase("tan");
    }
    
    boolean isAtan(final String s) {
        return this.car(s).equalsIgnoreCase("atan");
    }
    
    boolean isAcos(final String s) {
        return this.car(s).equalsIgnoreCase("acos");
    }
    
    boolean isAsin(final String s) {
        return this.car(s).equalsIgnoreCase("asin");
    }
    
    boolean isSinhyp(final String s) {
        return this.car(s).equalsIgnoreCase("sinh");
    }
    
    boolean isCoshyp(final String s) {
        return this.car(s).equalsIgnoreCase("cosh");
    }
    
    boolean isTanhyp(final String s) {
        return this.car(s).equalsIgnoreCase("tanh");
    }
    
    boolean isLn(final String s) {
        return this.car(s).equalsIgnoreCase("ln");
    }
    
    boolean isExponation(final String s) {
        return this.car(s).equalsIgnoreCase("^");
    }
    
    boolean isE(final String s) {
        return this.car(s).equalsIgnoreCase("exp");
    }
    
    boolean isCotan(final String s) {
        return this.car(s).equalsIgnoreCase("cotan");
    }
    
    boolean isAcotan(final String s) {
        return this.car(s).equalsIgnoreCase("acotan");
    }
    
    boolean isRound(final String s) {
        return this.car(s).equalsIgnoreCase("round");
    }
    
    boolean isCeil(final String s) {
        return this.car(s).equalsIgnoreCase("ceil");
    }
    
    boolean isFloor(final String s) {
        return this.car(s).equalsIgnoreCase("floor");
    }
    
    boolean isFac(final String s) {
        return this.car(s).equalsIgnoreCase("fac");
    }
    
    boolean isSfac(final String s) {
        return this.car(s).equalsIgnoreCase("sfac");
    }
    
    boolean isAbs(final String s) {
        return this.car(s).equalsIgnoreCase("abs");
    }
    
    boolean islog(final String s) {
        return this.car(s).equalsIgnoreCase("log");
    }
    
    boolean isFpart(final String s) {
        return this.car(s).equalsIgnoreCase("fpart");
    }
    
    boolean isMod(final String s) {
        return this.car(s).equalsIgnoreCase("%");
    }
    
    boolean isAnd(final String s) {
        return this.car(s).equalsIgnoreCase("&&");
    }
    
    boolean isLess(final String s) {
        return this.car(s).equalsIgnoreCase("<");
    }
    
    boolean isLarger(final String s) {
        return this.car(s).equalsIgnoreCase(">");
    }
    
    boolean isEqual(final String s) {
        return this.car(s).equalsIgnoreCase("==");
    }
    
    boolean isNEqual(final String s) {
        return this.car(s).equalsIgnoreCase("!=");
    }
    
    boolean isOr(final String s) {
        return this.car(s).equalsIgnoreCase("||");
    }
    
    boolean isNot(final String s) {
        return this.car(s).equalsIgnoreCase("!");
    }
    
    boolean isLargerEqual(final String s) {
        return this.car(s).equalsIgnoreCase(">=");
    }
    
    boolean isLessEqual(final String s) {
        return this.car(s).equalsIgnoreCase("<=");
    }
    
    boolean isSpecialConstant(final String s) {
        for (int i = 0; i < this.SPECIAL_CONSTANTS.length; ++i) {
            if (s.equalsIgnoreCase(this.SPECIAL_CONSTANTS[i])) {
                return true;
            }
        }
        return false;
    }
    
    String list(final String s, final String s2, final String s3) {
        return "( " + s + " " + s2 + " " + s3 + " )";
    }
    
    String list(final String s, final String s2) {
        return "( " + s + " " + s2 + " )";
    }
    
    String firstOp(final String s) {
        return this.car(s);
    }
    
    String InToPrefix(final String s) throws Exception {
        String s2 = "";
        int i = 0;
        if (s.equals("")) {
            throw new Exception("Wrong number of arguments to operator");
        }
        if (this.isVariable(s) || this.isAllNumbers(s)) {
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
                        if (s2.equals("")) {
                            s2 = "0";
                        }
                        s3 = this.ArgTo(s, i + 1, new String[] { ">", "<", "<=", ">=", "==", "!=", "&&", "||", "+", "-" });
                    }
                    else if (op.equalsIgnoreCase("==") || op.equalsIgnoreCase("!=")) {
                        s3 = this.ArgTo(s, i + op.length(), new String[] { "==", "!=", "&&", "||" });
                    }
                    else if (op.equalsIgnoreCase(">") || op.equalsIgnoreCase("<") || op.equalsIgnoreCase(">=") || op.equalsIgnoreCase("<=")) {
                        s3 = this.ArgTo(s, i + op.length(), new String[] { ">", "<", "<=", ">=", "==", "!=", "&&", "||" });
                    }
                    else if (op.equalsIgnoreCase("&&")) {
                        s3 = this.ArgTo(s, i + op.length(), "&&");
                    }
                    else if (op.equalsIgnoreCase("||")) {
                        s3 = this.ArgTo(s, i + op.length(), new String[] { "||", "&&" });
                    }
                    else if (op.equalsIgnoreCase("*") || op.equalsIgnoreCase("/") || op.equalsIgnoreCase("%")) {
                        s3 = this.ArgToAnyOpExcept(s, i + 1, "^");
                    }
                    else {
                        s3 = this.Arg(s, i + op.length());
                    }
                    if (s2.equals("")) {
                        throw new Exception("Wrong number of arguments to operator");
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
                        s4 = this.ArgTo(s, i + 1 + arg2.length(), new String[] { ">", "<", "<=", ">=", "==", "!=", "&&", "||", "+", "-" });
                    }
                    else if (op2.equalsIgnoreCase("==") || op2.equalsIgnoreCase("!=")) {
                        s4 = this.ArgTo(s, i + arg2.length() + op2.length(), new String[] { "==", "!=", "&&", "||" });
                    }
                    else if (op2.equalsIgnoreCase(">") || op2.equalsIgnoreCase("<") || op2.equalsIgnoreCase(">=") || op2.equalsIgnoreCase("<=")) {
                        s4 = this.ArgTo(s, i + arg2.length() + op2.length(), new String[] { ">", "<", "<=", ">=", "==", "!=", "&&", "||" });
                    }
                    else if (op2.equalsIgnoreCase("&&")) {
                        s4 = this.ArgTo(s, i + arg2.length() + op2.length(), "&&");
                    }
                    else if (op2.equalsIgnoreCase("||")) {
                        s4 = this.ArgTo(s, i + arg2.length() + op2.length(), new String[] { "||", "&&" });
                    }
                    else if (op2.equalsIgnoreCase("*") || op2.equalsIgnoreCase("/") || op2.equalsIgnoreCase("%")) {
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
    
    String ArgToAnyOpExcept(final String s, final int n, final String[] array) {
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
                    if (s2 != "" && !this.isTwoArgOp(this.BackTrack(s2)) && !this.memberOf(op, array)) {
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
    
    String ArgTo(final String s, final int n, final String[] array) {
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
                    if (s2 != "" && !this.isTwoArgOp(this.BackTrack(s2)) && this.memberOf(op, array)) {
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
    
    String ArgTo(final String s, final int n, final String s2) {
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
                    if (s3 != "" && !this.isTwoArgOp(this.BackTrack(s3)) && op.equals(s2)) {
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
    
    boolean memberOf(final String s, final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (s.equals(array[i])) {
                return true;
            }
        }
        return false;
    }
    
    String BackTrack(final String s) {
        try {
            for (int i = 0; i <= this.MAXOPLENGTH; ++i) {
                final String op;
                if ((op = this.getOp(s, s.length() - 1 - this.MAXOPLENGTH + i)) != null && s.length() - this.MAXOPLENGTH - 1 + i + op.length() == s.length()) {
                    return op;
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    String getOp(final String s, final int n) {
        for (int i = 0; i < this.MAXOPLENGTH; ++i) {
            try {
                if (this.isOperator(s.substring(n, n + (this.MAXOPLENGTH - i)))) {
                    return s.substring(n, n + (this.MAXOPLENGTH - i));
                }
            }
            catch (Exception ex) {}
        }
        return null;
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
    
    String PrepareExp(final String s) {
        String s2 = this.SkipSpaces(s);
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
    
    double fac(final double n) {
        if (!this.isInteger(n)) {
            return Double.NaN;
        }
        if (n < 0.0) {
            return Double.NaN;
        }
        if (n <= 1.0) {
            return 1.0;
        }
        return n * this.fac(n - 1.0);
    }
    
    double sfac(final double n) {
        if (!this.isInteger(n)) {
            return Double.NaN;
        }
        if (n < 0.0) {
            return Double.NaN;
        }
        if (n <= 1.0) {
            return 1.0;
        }
        return n * this.sfac(n - 2.0);
    }
    
    double fpart(final double n) {
        if (n >= 0.0) {
            return n - Math.floor(n);
        }
        return n - Math.ceil(n);
    }
    
    double getSpecialConstantValue(final String s) {
        for (int i = 0; i < this.SPECIAL_CONSTANTS.length; ++i) {
            if (s.equalsIgnoreCase(this.SPECIAL_CONSTANTS[i])) {
                return this.SPECIAL_CONSTANT_VALUES[i];
            }
        }
        return Double.NaN;
    }
    
    double ToValue(final String s) throws Exception {
        try {
            if (this.isConstant(s)) {
                return Double.valueOf(s);
            }
            if (this.isVariable(s)) {
                if (this.isSpecialConstant(s)) {
                    return this.getSpecialConstantValue(s);
                }
                final String skipSpaces = this.SkipSpaces(this.get(s).toLowerCase());
                if (skipSpaces.equalsIgnoreCase(s)) {
                    throw new Exception("Neverending loop, " + s + " is associated with " + skipSpaces);
                }
                if (this.isVariable(skipSpaces) || this.isAllNumbers(skipSpaces)) {
                    return this.ToValue(skipSpaces);
                }
                this.Syntax(skipSpaces);
                final String inToPrefix = this.InToPrefix(this.parseE(skipSpaces));
                if (inToPrefix.indexOf(" " + s + " ") != -1) {
                    throw new Exception("Neverending loop, " + s + " is associated with " + skipSpaces);
                }
                return this.ToValue(inToPrefix);
            }
            else {
                if (this.isSum(s)) {
                    return this.ToValue(this.arg1(s)) + this.ToValue(this.arg2(s));
                }
                if (this.isSubtraction(s)) {
                    return this.ToValue(this.arg1(s)) - this.ToValue(this.arg2(s));
                }
                if (this.isProduct(s)) {
                    return this.ToValue(this.arg1(s)) * this.ToValue(this.arg2(s));
                }
                if (this.isDivision(s)) {
                    return this.ToValue(this.arg1(s)) / this.ToValue(this.arg2(s));
                }
                if (this.isSquareroot(s)) {
                    return Math.sqrt(this.ToValue(this.arg1(s)));
                }
                if (this.isSine(s)) {
                    return Math.sin(this.ToValue(this.arg1(s)));
                }
                if (this.isCosine(s)) {
                    return Math.cos(this.ToValue(this.arg1(s)));
                }
                if (this.isTan(s)) {
                    return Math.tan(this.ToValue(this.arg1(s)));
                }
                if (this.isAsin(s)) {
                    return Math.asin(this.ToValue(this.arg1(s)));
                }
                if (this.isAcos(s)) {
                    return Math.acos(this.ToValue(this.arg1(s)));
                }
                if (this.isAtan(s)) {
                    return Math.atan(this.ToValue(this.arg1(s)));
                }
                if (this.isExponation(s)) {
                    return Math.pow(this.ToValue(this.arg1(s)), this.ToValue(this.arg2(s)));
                }
                if (this.isLn(s)) {
                    return Math.log(this.ToValue(this.arg1(s)));
                }
                if (this.isE(s)) {
                    return Math.exp(this.ToValue(this.arg1(s)));
                }
                if (this.isSinhyp(s)) {
                    return (Math.exp(this.ToValue(this.arg1(s))) - 1.0 / Math.exp(this.ToValue(this.arg1(s)))) / 2.0;
                }
                if (this.isCoshyp(s)) {
                    return (Math.exp(this.ToValue(this.arg1(s))) + 1.0 / Math.exp(this.ToValue(this.arg1(s)))) / 2.0;
                }
                if (this.isTanhyp(s)) {
                    return (Math.exp(this.ToValue(this.arg1(s))) - 1.0 / Math.exp(this.ToValue(this.arg1(s)))) / 2.0 / ((Math.exp(this.ToValue(this.arg1(s))) + 1.0 / Math.exp(this.ToValue(this.arg1(s)))) / 2.0);
                }
                if (this.isCotan(s)) {
                    return 1.0 / Math.tan(this.ToValue(this.arg1(s)));
                }
                if (this.isAcotan(s)) {
                    return 1.5707963267948966 - Math.atan(this.ToValue(this.arg1(s)));
                }
                if (this.isCeil(s)) {
                    return Math.ceil(this.ToValue(this.arg1(s)));
                }
                if (this.isRound(s)) {
                    return Math.round(this.ToValue(this.arg1(s)));
                }
                if (this.isFloor(s)) {
                    return Math.floor(this.ToValue(this.arg1(s)));
                }
                if (this.isFac(s)) {
                    return this.fac(this.ToValue(this.arg1(s)));
                }
                if (this.isAbs(s)) {
                    return Math.abs(this.ToValue(this.arg1(s)));
                }
                if (this.islog(s)) {
                    return Math.log(this.ToValue(this.arg2(s))) / Math.log(this.ToValue(this.arg1(s)));
                }
                if (this.isMod(s)) {
                    return this.ToValue(this.arg1(s)) % this.ToValue(this.arg2(s));
                }
                if (this.isFpart(s)) {
                    return this.fpart(this.ToValue(this.arg1(s)));
                }
                if (this.isSfac(s)) {
                    return this.sfac(this.ToValue(this.arg1(s)));
                }
                if (this.isEqual(s)) {
                    if (this.ToValue(this.arg1(s)) == this.ToValue(this.arg2(s))) {
                        return 1.0;
                    }
                    return 0.0;
                }
                else if (this.isNEqual(s)) {
                    if (this.ToValue(this.arg1(s)) != this.ToValue(this.arg2(s))) {
                        return 1.0;
                    }
                    return 0.0;
                }
                else if (this.isLess(s)) {
                    if (this.ToValue(this.arg1(s)) < this.ToValue(this.arg2(s))) {
                        return 1.0;
                    }
                    return 0.0;
                }
                else if (this.isLarger(s)) {
                    if (this.ToValue(this.arg1(s)) > this.ToValue(this.arg2(s))) {
                        return 1.0;
                    }
                    return 0.0;
                }
                else if (this.isAnd(s)) {
                    if (this.ToValue(this.arg1(s)) == 1.0 && this.ToValue(this.arg2(s)) == 1.0) {
                        return 1.0;
                    }
                    return 0.0;
                }
                else if (this.isOr(s)) {
                    if (this.ToValue(this.arg1(s)) == 1.0 || this.ToValue(this.arg2(s)) == 1.0) {
                        return 1.0;
                    }
                    return 0.0;
                }
                else if (this.isLargerEqual(s)) {
                    if (this.ToValue(this.arg1(s)) >= this.ToValue(this.arg2(s))) {
                        return 1.0;
                    }
                    return 0.0;
                }
                else if (this.isLessEqual(s)) {
                    if (this.ToValue(this.arg1(s)) <= this.ToValue(this.arg2(s))) {
                        return 1.0;
                    }
                    return 0.0;
                }
                else if (this.isNot(s)) {
                    if (this.ToValue(this.arg1(s)) != 1.0) {
                        return 1.0;
                    }
                    return 0.0;
                }
            }
        }
        catch (StringIndexOutOfBoundsException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new Exception(ex2.getMessage());
        }
        throw new Exception("Unknown operator, " + s);
    }
    
    String get(final String s) throws Exception {
        final String value = this.HTBL.get(s);
        if (value == null) {
            throw new Exception("No value associated with " + s);
        }
        final String s2 = value;
        if (s2.equals("")) {
            throw new Exception("No value associated with " + s);
        }
        return s2;
    }
    
    String findValues(final String s) {
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
        final GUIEval guiEval = new GUIEval();
        GUIEval.main(array);
    }
    
    public double eval(final String expression, final Hashtable htbl) throws cannotConvertException {
        if (expression == null || expression.equals("") || expression.length() < 1) {
            throw new cannotConvertException("First argument to method eval is null or empty string");
        }
        if (htbl == null) {
            return this.eval(expression);
        }
        double toValue;
        try {
            this.HTBL = htbl;
            if (this.EXPRESSION == "" || !this.EXPRESSION.equalsIgnoreCase(expression)) {
                this.EXPRESSION = expression;
                this.EXPRESSION = this.EXPRESSION.toLowerCase();
                this.Syntax(this.SkipSpaces(this.EXPRESSION));
                this.PREFIXEXP = this.InToPrefix(this.PrepareExp(this.EXPRESSION));
            }
            toValue = this.ToValue(this.PREFIXEXP);
        }
        catch (StringIndexOutOfBoundsException ex2) {
            throw new cannotConvertException("Wrong number of arguments to operator");
        }
        catch (Exception ex) {
            throw new cannotConvertException(ex.getMessage());
        }
        return toValue;
    }
    
    public double eval(final String s, String skipSpaces) throws cannotConvertException {
        String values = "";
        final Hashtable<String, String> hashtable = new Hashtable<String, String>(1001);
        if (s == null || s.equals("") || s.length() < 1) {
            throw new cannotConvertException("First argument to method eval is null or empty string");
        }
        if (skipSpaces == null || skipSpaces.equals("") || skipSpaces.length() < 1) {
            return this.eval(s);
        }
        try {
            skipSpaces = this.SkipSpaces(skipSpaces.toLowerCase());
            while ((values = this.findValues(skipSpaces)) != null) {
                hashtable.put(values.substring(0, values.indexOf("=")), values.substring(values.indexOf("=") + 1, values.length()));
            }
        }
        catch (StringIndexOutOfBoundsException ex) {
            this.lastIndex = 0;
            throw new cannotConvertException("Syntax error ->" + skipSpaces);
        }
        catch (Exception ex2) {
            this.lastIndex = 0;
            throw new cannotConvertException("Syntax error ->" + values);
        }
        return this.eval(s, hashtable);
    }
    
    public double eval(final String s) throws cannotConvertException {
        return this.eval(s, new Hashtable());
    }
    
    public Eval() {
        this.EXPRESSION = "";
        this.PREFIXEXP = "";
        this.ALLOWEDOPS = new String[] { "^", "+", "-", "/", "*", "cos", "sin", "exp", "ln", "tan", "acos", "asin", "atan", "cosh", "sinh", "tanh", "sqrt", "cotan", "fpart", "acotan", "round", "ceil", "floor", "fac", "sfac", "abs", "log", "%", ">", "<", "&&", "==", "!=", "||", "!", ">=", "<=" };
        this.TWOARGOPS = new String[] { "^", "+", "-", "/", "*", "%", "log", ">", "<", "&&", "==", "!=", "||", ">=", "<=" };
        this.ALLOWEDSYM = new String[] { "(", ")", ".", ">", "<", "&", "=", "|" };
        this.SPECIAL_CONSTANTS = new String[] { "euler", "pi", "nan", "infinity", "true", "false" };
        this.SPECIAL_CONSTANT_VALUES = new double[] { 2.718281828459045, 3.141592653589793, Double.NaN, Double.POSITIVE_INFINITY, 1.0, 0.0 };
        this.MAXOPLENGTH = this.setMaxOpLength();
    }
}
