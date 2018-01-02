// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.numerics;

import java.util.Vector;
import java.util.Hashtable;

public final class Parser
{
    private int var_count;
    private String[] var_name;
    private double[] var_value;
    private double[] number;
    private String function;
    private String postfix_code;
    private boolean valid;
    private int error;
    private boolean ISBOOLEAN;
    private boolean INRELATION;
    private int position;
    private int start;
    private int num;
    private char character;
    private boolean radian;
    private int numberindex;
    private double[] refvalue;
    private static final int MAX_NUM = 200;
    private static final int NO_FUNCS = 26;
    private static final int NO_EXT_FUNCS = 4;
    private static final int STACK_SIZE = 50;
    private double[] stack;
    private static final double DEGTORAD = 0.017453292519943295;
    private static final double LOG10;
    private Hashtable references;
    private Vector refnames;
    public static final int NO_ERROR = 0;
    public static final int SYNTAX_ERROR = 1;
    public static final int PAREN_EXPECTED = 2;
    public static final int UNCOMPILED_FUNCTION = 3;
    public static final int EXPRESSION_EXPECTED = 4;
    public static final int UNKNOWN_IDENTIFIER = 5;
    public static final int OPERATOR_EXPECTED = 6;
    public static final int PAREN_NOT_MATCH = 7;
    public static final int CODE_DAMAGED = 8;
    public static final int STACK_OVERFLOW = 9;
    public static final int TOO_MANY_CONSTS = 10;
    public static final int COMMA_EXPECTED = 11;
    public static final int INVALID_OPERAND = 12;
    public static final int INVALID_OPERATOR = 13;
    public static final int NO_FUNC_DEFINITION = 14;
    public static final int REF_NAME_EXPECTED = 15;
    private static final int FUNC_OFFSET = 1000;
    private static final int EXT_FUNC_OFFSET = 1026;
    private static final int VAR_OFFSET = 2000;
    private static final int REF_OFFSET = 3000;
    private static final char PI_CODE = '\u00fd';
    private static final char E_CODE = '\u00fe';
    private static final char NUMERIC = '\u00ff';
    private static final char JUMP_CODE = '\u0001';
    private static final char LESS_THAN = '\u0002';
    private static final char GREATER_THAN = '\u0003';
    private static final char LESS_EQUAL = '\u0004';
    private static final char GREATER_EQUAL = '\u0005';
    private static final char NOT_EQUAL = '\u0006';
    private static final char EQUAL = '\u0007';
    private static final char IF_CODE = '\b';
    private static final char ENDIF = '\t';
    private static final char AND_CODE = '\n';
    private static final char OR_CODE = '\u000b';
    private static final char NOT_CODE = '\f';
    private String[] funcname;
    private String[] extfunc;
    
    public Parser(final int var_count) {
        this.function = "";
        this.postfix_code = "";
        this.valid = false;
        this.ISBOOLEAN = false;
        this.INRELATION = false;
        this.refvalue = null;
        this.stack = new double[50];
        this.references = null;
        this.refnames = null;
        this.funcname = new String[] { "sin", "cos", "tan", "ln", "log", "abs", "int", "frac", "asin", "acos", "atan", "sinh", "cosh", "tanh", "asinh", "acosh", "atanh", "ceil", "floor", "round", "exp", "sqr", "sqrt", "sign", "step", "random" };
        this.extfunc = new String[] { "min", "max", "mod", "atan2" };
        this.var_count = var_count;
        this.references = new Hashtable();
        this.refnames = new Vector();
        this.radian = true;
        this.var_name = new String[var_count];
        this.var_value = new double[var_count];
        this.number = new double[200];
    }
    
    public Parser() {
        this.function = "";
        this.postfix_code = "";
        this.valid = false;
        this.ISBOOLEAN = false;
        this.INRELATION = false;
        this.refvalue = null;
        this.stack = new double[50];
        this.references = null;
        this.refnames = null;
        this.funcname = new String[] { "sin", "cos", "tan", "ln", "log", "abs", "int", "frac", "asin", "acos", "atan", "sinh", "cosh", "tanh", "asinh", "acosh", "atanh", "ceil", "floor", "round", "exp", "sqr", "sqrt", "sign", "step", "random" };
        this.extfunc = new String[] { "min", "max", "mod", "atan2" };
        final int var_count = 1;
        this.var_count = var_count;
        this.references = new Hashtable();
        this.refnames = new Vector();
        this.radian = true;
        this.var_name = new String[var_count];
        this.var_value = new double[var_count];
        this.number = new double[200];
    }
    
    private void adjustVarCount(final int var_count) {
        this.var_count = var_count;
        this.references = new Hashtable();
        this.refnames = new Vector();
        this.var_name = new String[var_count];
        this.var_value = new double[var_count];
        this.number = new double[200];
    }
    
    public void useRadian() {
        this.radian = true;
    }
    
    public void useDegree() {
        this.radian = false;
    }
    
    public void defineVariable(final int n, final String s) {
        if (n > this.var_count) {
            return;
        }
        this.var_name[n - 1] = s;
    }
    
    public void defineVariables(final String[] array) {
        if (array.length != this.var_count) {
            this.adjustVarCount(array.length);
        }
        for (int i = 0; i < array.length; ++i) {
            this.defineVariable(i + 1, array[i]);
        }
    }
    
    public void setVariable(final int n, final double n2) {
        if (n > this.var_count) {
            return;
        }
        this.var_value[n - 1] = n2;
    }
    
    public void setVariable(final String s, final double n) {
        for (int i = 0; i < this.var_count; ++i) {
            if (this.var_name[i].equals(s)) {
                this.var_value[i] = n;
                break;
            }
        }
    }
    
    private String removeEscapeCharacter(final String s) {
        if (s == null || s.length() < 1) {
            return s;
        }
        final StringBuffer sb = new StringBuffer(s.length());
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != '\\') {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    
    private String fixUnaryMinus(final String s) {
        if (s == null || s.length() < 1) {
            return s;
        }
        final StringBuffer sb = new StringBuffer(s.length());
        if (s.charAt(0) == '-') {
            sb.append('0');
        }
        for (int i = 0; i < s.length(); ++i) {
            if (i > 0 && s.charAt(i) == '-' && s.charAt(i - 1) == '(') {
                sb.append('0');
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    
    public void define(final String function) {
        (this.function = function).toLowerCase();
        this.function = this.removeEscapeCharacter(this.function);
        this.function = this.fixUnaryMinus(this.function);
        this.valid = false;
    }
    
    public void parse() {
        String substring = new String(this.function);
        final String function = new String(this.function);
        if (this.valid) {
            return;
        }
        this.num = 0;
        this.error = 0;
        this.references.clear();
        this.refnames.removeAllElements();
        int lastIndex;
        while ((lastIndex = substring.lastIndexOf(";")) != -1) {
            this.function = String.valueOf(String.valueOf(substring.substring(lastIndex + 1))).concat(String.valueOf(String.valueOf(')')));
            substring = substring.substring(0, lastIndex++);
            String trim = null;
            int index = this.function.indexOf(":");
            if (index == -1) {
                this.error = 14;
                this.position = 0;
                while (this.position < this.function.length() && this.function.charAt(this.position) == ' ') {
                    ++this.position;
                }
                ++this.position;
            }
            else {
                final String substring2 = this.function.substring(0, index);
                this.function = this.function.substring(index + 1);
                trim = substring2.trim();
                if (trim.equals("")) {
                    this.error = 15;
                    this.position = 1;
                }
                else {
                    lastIndex += ++index;
                    this.parseSubFunction();
                }
            }
            if (this.error != 0) {
                this.position += lastIndex;
                break;
            }
            this.references.put(trim, this.postfix_code);
            this.refnames.addElement(trim);
        }
        if (this.error == 0) {
            this.function = String.valueOf(String.valueOf(substring)).concat(String.valueOf(String.valueOf(')')));
            this.parseSubFunction();
        }
        this.function = function;
        this.valid = (this.error == 0);
    }
    
    public double evaluate(final double n, final double n2) {
        if (this.var_count != 2) {
            return 0.0;
        }
        this.var_value[0] = n;
        this.var_value[1] = n2;
        return this.evaluate();
    }
    
    public double evaluate(final double n, final double n2, final double n3) {
        if (this.var_count != 3) {
            return 0.0;
        }
        this.var_value[0] = n;
        this.var_value[1] = n2;
        this.var_value[2] = n3;
        return this.evaluate();
    }
    
    public double evaluate(final double n) {
        if (this.var_count != 1) {
            return 0.0;
        }
        this.var_value[0] = n;
        return this.evaluate();
    }
    
    public double evaluate(final double[] array) {
        if (this.var_value.length != array.length) {
            return 0.0;
        }
        System.arraycopy(array, 0, this.var_value, 0, array.length);
        return this.evaluate();
    }
    
    public double evaluate() {
        final int size = this.refnames.size();
        if (!this.valid) {
            this.error = 3;
            return 0.0;
        }
        this.error = 0;
        this.numberindex = 0;
        if (size != 0) {
            final String postfix_code = this.postfix_code;
            this.refvalue = new double[size];
            for (int i = 0; i < this.refnames.size(); ++i) {
                this.postfix_code = (String)this.references.get(this.refnames.elementAt(i));
                final double evaluateSubFunction = this.evaluateSubFunction();
                if (this.error != 0) {
                    this.postfix_code = postfix_code;
                    this.refvalue = null;
                    return evaluateSubFunction;
                }
                this.refvalue[i] = evaluateSubFunction;
            }
            this.postfix_code = postfix_code;
        }
        double evaluateSubFunction2 = this.evaluateSubFunction();
        this.refvalue = null;
        if (Double.isNaN(evaluateSubFunction2)) {
            evaluateSubFunction2 = 0.0;
        }
        return evaluateSubFunction2;
    }
    
    public int getErrorCode() {
        return this.error;
    }
    
    public String getErrorString() {
        return toErrorString(this.error);
    }
    
    public int getErrorPosition() {
        return this.position;
    }
    
    public static String toErrorString(final int n) {
        String s = "";
        switch (n) {
            case 0: {
                s = "no error";
                break;
            }
            case 1: {
                s = "syntax error";
                break;
            }
            case 2: {
                s = "parenthesis expected";
                break;
            }
            case 3: {
                s = "uncompiled function";
                break;
            }
            case 4: {
                s = "expression expected";
                break;
            }
            case 5: {
                s = "unknown identifier";
                break;
            }
            case 6: {
                s = "operator expected";
                break;
            }
            case 7: {
                s = "parentheses not match";
                break;
            }
            case 8: {
                s = "internal code damaged";
                break;
            }
            case 9: {
                s = "execution stack overflow";
                break;
            }
            case 10: {
                s = "too many constants";
                break;
            }
            case 11: {
                s = "comma expected";
                break;
            }
            case 12: {
                s = "invalid operand type";
                break;
            }
            case 13: {
                s = "invalid operator";
                break;
            }
            case 14: {
                s = "bad reference definition (: expected)";
                break;
            }
            case 15: {
                s = "reference name expected";
                break;
            }
        }
        return s;
    }
    
    public String getFunctionString() {
        return this.function;
    }
    
    private void skipSpaces() throws ParserException {
        try {
            while (this.function.charAt(this.position - 1) == ' ') {
                ++this.position;
            }
            this.character = this.function.charAt(this.position - 1);
        }
        catch (StringIndexOutOfBoundsException ex) {
            throw new ParserException(7);
        }
    }
    
    private void getNextCharacter() throws ParserException {
        ++this.position;
        try {
            this.character = this.function.charAt(this.position - 1);
        }
        catch (StringIndexOutOfBoundsException ex) {
            throw new ParserException(7);
        }
    }
    
    private void addCode(final char c) {
        this.postfix_code = String.valueOf(String.valueOf(this.postfix_code)).concat(String.valueOf(String.valueOf(c)));
    }
    
    private void scanNumber() throws ParserException {
        String s = "";
        if (this.num == 200) {
            throw new ParserException(10);
        }
        if (this.character != '.') {
            do {
                s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(this.character)));
                this.getNextCharacter();
                if (this.character >= '0') {
                    continue;
                }
                break;
            } while (this.character <= '9');
        }
        else {
            s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf('0')));
        }
        if (this.character == '.') {
            do {
                s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(this.character)));
                this.getNextCharacter();
            } while (this.character >= '0' && this.character <= '9');
        }
        if (this.character == 'e') {
            s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(this.character)));
            this.getNextCharacter();
            if (this.character == '+' || this.character == '-') {
                s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(this.character)));
                this.getNextCharacter();
            }
            while (this.character >= '0' && this.character <= '9') {
                s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(this.character)));
                this.getNextCharacter();
            }
        }
        double doubleValue;
        try {
            doubleValue = Double.valueOf(s);
        }
        catch (NumberFormatException ex) {
            this.position = this.start;
            throw new ParserException(1);
        }
        this.number[this.num++] = doubleValue;
        this.addCode('\u00ff');
    }
    
    private void scanNonNumeric() throws ParserException {
        String concat = "";
        if (this.character == '*' || this.character == '/' || this.character == '^' || this.character == ')' || this.character == ',' || this.character == '<' || this.character == '>' || this.character == '=' || this.character == '&' || this.character == '|') {
            throw new ParserException(1);
        }
        do {
            concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(this.character)));
            this.getNextCharacter();
        } while (this.character != ' ' && this.character != '+' && this.character != '-' && this.character != '*' && this.character != '/' && this.character != '^' && this.character != '(' && this.character != ')' && this.character != ',' && this.character != '<' && this.character != '>' && this.character != '=' && this.character != '&' && this.character != '|');
        if (concat.equals("pi")) {
            this.addCode('\u00fd');
            return;
        }
        if (concat.equals("e")) {
            this.addCode('\u00fe');
            return;
        }
        if (concat.equals("if")) {
            this.skipSpaces();
            if (this.character != '(') {
                throw new ParserException(2);
            }
            this.scanAndParse();
            if (this.character != ',') {
                throw new ParserException(11);
            }
            this.addCode('\b');
            final String s = new String(this.postfix_code);
            this.postfix_code = "";
            this.scanAndParse();
            if (this.character != ',') {
                throw new ParserException(11);
            }
            this.addCode('\u0001');
            final String concat2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf((char)(this.postfix_code.length() + 2)))))).concat(String.valueOf(String.valueOf(this.postfix_code)));
            this.postfix_code = "";
            this.scanAndParse();
            if (this.character != ')') {
                throw new ParserException(2);
            }
            this.postfix_code = new String(String.valueOf(String.valueOf(String.valueOf(String.valueOf(concat2)).concat(String.valueOf(String.valueOf((char)(this.postfix_code.length() + 1)))))).concat(String.valueOf(String.valueOf(this.postfix_code))));
            this.getNextCharacter();
        }
        else {
            int i = 0;
            while (i < 26) {
                if (concat.equals(this.funcname[i])) {
                    this.skipSpaces();
                    if (this.character != '(') {
                        throw new ParserException(2);
                    }
                    this.scanAndParse();
                    if (this.character != ')') {
                        throw new ParserException(2);
                    }
                    this.getNextCharacter();
                    this.addCode((char)(i + 1000));
                    return;
                }
                else {
                    ++i;
                }
            }
            int j = 0;
            while (j < 4) {
                if (concat.equals(this.extfunc[j])) {
                    this.skipSpaces();
                    if (this.character != '(') {
                        throw new ParserException(2);
                    }
                    this.scanAndParse();
                    if (this.character != ',') {
                        throw new ParserException(11);
                    }
                    final String s2 = new String(this.postfix_code);
                    this.postfix_code = "";
                    this.scanAndParse();
                    if (this.character != ')') {
                        throw new ParserException(2);
                    }
                    this.getNextCharacter();
                    this.postfix_code = new String(String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(this.postfix_code))));
                    this.addCode((char)(j + 1026));
                    return;
                }
                else {
                    ++j;
                }
            }
            for (int k = 0; k < this.var_count; ++k) {
                if (concat.equals(this.var_name[k])) {
                    this.addCode((char)(k + 2000));
                    return;
                }
            }
            final int index = this.refnames.indexOf(concat);
            if (index != -1) {
                this.addCode((char)(index + 3000));
                return;
            }
            this.position = this.start;
            throw new ParserException(5);
        }
    }
    
    private void getIdentifier() throws ParserException {
        boolean b = false;
        this.getNextCharacter();
        this.skipSpaces();
        if (this.character != '!') {
            this.ISBOOLEAN = false;
            while (this.character == '+' || this.character == '-') {
                if (this.character == '-') {
                    b = !b;
                }
                this.getNextCharacter();
                this.skipSpaces();
            }
            this.start = this.position;
            if ((this.character >= '0' && this.character <= '9') || this.character == '.') {
                this.scanNumber();
            }
            else if (this.character == '(') {
                this.scanAndParse();
                this.getNextCharacter();
            }
            else {
                this.scanNonNumeric();
            }
            this.skipSpaces();
            if (b) {
                this.addCode('_');
            }
            return;
        }
        this.getNextCharacter();
        this.skipSpaces();
        if (this.character != '(') {
            throw new ParserException(2);
        }
        this.scanAndParse();
        if (this.character != ')') {
            throw new ParserException(2);
        }
        if (!this.ISBOOLEAN) {
            throw new ParserException(12);
        }
        this.addCode('\f');
        this.getNextCharacter();
    }
    
    private void arithmeticLevel3() throws ParserException {
        int n = 0;
        if (this.ISBOOLEAN) {
            throw new ParserException(12);
        }
        do {
            this.getIdentifier();
            if (this.ISBOOLEAN) {
                throw new ParserException(12);
            }
            n = (byte)(n + 1);
        } while (this.character == '^');
        for (int i = 1; i <= n; ++i) {
            this.addCode('^');
        }
    }
    
    private void arithmeticLevel2() throws ParserException {
        if (this.ISBOOLEAN) {
            throw new ParserException(12);
        }
        do {
            final char character = this.character;
            this.getIdentifier();
            if (this.ISBOOLEAN) {
                throw new ParserException(12);
            }
            if (this.character == '^') {
                this.arithmeticLevel3();
            }
            this.addCode(character);
        } while (this.character == '*' || this.character == '/');
    }
    
    private void arithmeticLevel1() throws ParserException {
        if (this.ISBOOLEAN) {
            throw new ParserException(12);
        }
        do {
            final char character = this.character;
            this.getIdentifier();
            if (this.ISBOOLEAN) {
                throw new ParserException(12);
            }
            if (this.character == '^') {
                this.arithmeticLevel3();
            }
            else if (this.character == '*' || this.character == '/') {
                this.arithmeticLevel2();
            }
            this.addCode(character);
        } while (this.character == '+' || this.character == '-');
    }
    
    private void relationLevel() throws ParserException {
        char c = '\0';
        if (this.INRELATION) {
            throw new ParserException(13);
        }
        this.INRELATION = true;
        if (this.ISBOOLEAN) {
            throw new ParserException(12);
        }
        switch (this.character) {
            case '=': {
                c = '\u0007';
                break;
            }
            case '<': {
                c = '\u0002';
                this.getNextCharacter();
                if (this.character == '>') {
                    c = '\u0006';
                    break;
                }
                if (this.character == '=') {
                    c = '\u0004';
                    break;
                }
                --this.position;
                break;
            }
            case '>': {
                c = '\u0003';
                this.getNextCharacter();
                if (this.character == '=') {
                    c = '\u0005';
                    break;
                }
                --this.position;
                break;
            }
        }
        this.scanAndParse();
        this.INRELATION = false;
        if (this.ISBOOLEAN) {
            throw new ParserException(12);
        }
        this.addCode(c);
        this.ISBOOLEAN = true;
    }
    
    private void booleanLevel() throws ParserException {
        if (!this.ISBOOLEAN) {
            throw new ParserException(12);
        }
        final char character = this.character;
        this.scanAndParse();
        if (!this.ISBOOLEAN) {
            throw new ParserException(12);
        }
        switch (character) {
            case 38: {
                this.addCode('\n');
                break;
            }
            case 124: {
                this.addCode('\u000b');
                break;
            }
        }
    }
    
    private void scanAndParse() throws ParserException {
        this.getIdentifier();
        while (true) {
            switch (this.character) {
                case '+':
                case '-': {
                    this.arithmeticLevel1();
                    continue;
                }
                case '*':
                case '/': {
                    this.arithmeticLevel2();
                    continue;
                }
                case '^': {
                    this.arithmeticLevel3();
                    continue;
                }
                case ')':
                case ',': {}
                case '<':
                case '=':
                case '>': {
                    this.relationLevel();
                    continue;
                }
                case '&':
                case '|': {
                    this.booleanLevel();
                    continue;
                }
                default: {
                    throw new ParserException(6);
                }
            }
        }
    }
    
    private void parseSubFunction() {
        this.position = 0;
        this.postfix_code = "";
        this.INRELATION = false;
        this.ISBOOLEAN = false;
        try {
            this.scanAndParse();
        }
        catch (ParserException ex) {
            this.error = ex.getErrorCode();
            if (this.error == 1 && this.postfix_code == "") {
                this.error = 4;
            }
        }
        if (this.error == 0 && this.position != this.function.length()) {
            this.error = 7;
        }
    }
    
    private double builtInFunction(final int n, final double n2) {
        switch (n) {
            case 0: {
                if (this.radian) {
                    return Math.sin(n2);
                }
                return Math.sin(n2 * 0.017453292519943295);
            }
            case 1: {
                if (this.radian) {
                    return Math.cos(n2);
                }
                return Math.cos(n2 * 0.017453292519943295);
            }
            case 2: {
                if (this.radian) {
                    return Math.tan(n2);
                }
                return Math.tan(n2 * 0.017453292519943295);
            }
            case 3: {
                return Math.log(n2);
            }
            case 4: {
                return Math.log(n2) / Parser.LOG10;
            }
            case 5: {
                return Math.abs(n2);
            }
            case 6: {
                return Math.rint(n2);
            }
            case 7: {
                return n2 - Math.rint(n2);
            }
            case 8: {
                if (this.radian) {
                    return Math.asin(n2);
                }
                return Math.asin(n2) / 0.017453292519943295;
            }
            case 9: {
                if (this.radian) {
                    return Math.acos(n2);
                }
                return Math.acos(n2) / 0.017453292519943295;
            }
            case 10: {
                if (this.radian) {
                    return Math.atan(n2);
                }
                return Math.atan(n2) / 0.017453292519943295;
            }
            case 11: {
                return (Math.exp(n2) - Math.exp(-n2)) / 2;
            }
            case 12: {
                return (Math.exp(n2) + Math.exp(-n2)) / 2;
            }
            case 13: {
                final double exp = Math.exp(n2);
                final double exp2 = Math.exp(-n2);
                return (exp - exp2) / (exp + exp2);
            }
            case 14: {
                return Math.log(n2 + Math.sqrt(n2 * n2 + 1));
            }
            case 15: {
                return Math.log(n2 + Math.sqrt(n2 * n2 - 1));
            }
            case 16: {
                return Math.log((1 + n2) / (1 - n2)) / 2;
            }
            case 17: {
                return Math.ceil(n2);
            }
            case 18: {
                return Math.floor(n2);
            }
            case 19: {
                return Math.round(n2);
            }
            case 20: {
                return Math.exp(n2);
            }
            case 21: {
                return n2 * n2;
            }
            case 22: {
                return Math.sqrt(n2);
            }
            case 23: {
                if (n2 == 0.0) {
                    return 0.0;
                }
                if (n2 > 0.0) {
                    return 1.0;
                }
                return -1.0;
            }
            case 24: {
                if (n2 < 0) {
                    return 0.0;
                }
                return 1.0;
            }
            case 25: {
                return n2 * Math.random();
            }
            default: {
                this.error = 8;
                return Double.NaN;
            }
        }
    }
    
    private double builtInExtFunction(final int n, final double n2, final double n3) {
        switch (n) {
            case 0: {
                return Math.min(n2, n3);
            }
            case 1: {
                return Math.max(n2, n3);
            }
            case 2: {
                return Math.IEEEremainder(n2, n3);
            }
            case 3: {
                return Math.atan2(n2, n3);
            }
            default: {
                this.error = 8;
                return Double.NaN;
            }
        }
    }
    
    private double evaluateSubFunction() {
        int n = -1;
        char c = '\0';
        final int length = this.postfix_code.length();
        while (true) {
            char char1;
            try {
                if (c == length) {
                    return this.stack[0];
                }
                char1 = this.postfix_code.charAt(c++);
            }
            catch (StringIndexOutOfBoundsException ex) {
                return this.stack[0];
            }
            try {
                switch (char1) {
                    case 43: {
                        final double[] stack = this.stack;
                        final int n2 = n - 1;
                        stack[n2] += this.stack[n];
                        --n;
                        continue;
                    }
                    case 45: {
                        final double[] stack2 = this.stack;
                        final int n3 = n - 1;
                        stack2[n3] -= this.stack[n];
                        --n;
                        continue;
                    }
                    case 42: {
                        final double[] stack3 = this.stack;
                        final int n4 = n - 1;
                        stack3[n4] *= this.stack[n];
                        --n;
                        continue;
                    }
                    case 47: {
                        if (this.stack[n] != 0) {
                            final double[] stack4 = this.stack;
                            final int n5 = n - 1;
                            stack4[n5] /= this.stack[n];
                        }
                        else {
                            final double[] stack5 = this.stack;
                            final int n6 = n - 1;
                            stack5[n6] /= 1.0E-128;
                        }
                        --n;
                        continue;
                    }
                    case 94: {
                        this.stack[n - 1] = Math.pow(this.stack[n - 1], this.stack[n]);
                        --n;
                        continue;
                    }
                    case 95: {
                        this.stack[n] = -this.stack[n];
                        continue;
                    }
                    case 1: {
                        while (c < c + this.postfix_code.charAt(c++)) {
                            if (this.postfix_code.charAt(c++) == '\u00ff') {
                                ++this.numberindex;
                            }
                        }
                        continue;
                    }
                    case 2: {
                        --n;
                        this.stack[n] = ((this.stack[n] < this.stack[n + 1]) ? 1.0 : 0.0);
                        continue;
                    }
                    case 3: {
                        --n;
                        this.stack[n] = ((this.stack[n] > this.stack[n + 1]) ? 1.0 : 0.0);
                        continue;
                    }
                    case 4: {
                        --n;
                        this.stack[n] = ((this.stack[n] <= this.stack[n + 1]) ? 1.0 : 0.0);
                        continue;
                    }
                    case 5: {
                        --n;
                        this.stack[n] = ((this.stack[n] >= this.stack[n + 1]) ? 1.0 : 0.0);
                        continue;
                    }
                    case 7: {
                        --n;
                        this.stack[n] = ((this.stack[n] == this.stack[n + 1]) ? 1.0 : 0.0);
                        continue;
                    }
                    case 6: {
                        --n;
                        this.stack[n] = ((this.stack[n] != this.stack[n + 1]) ? 1.0 : 0.0);
                        continue;
                    }
                    case 8: {
                        if (this.stack[n--] == 0.0) {
                            while (c < c + this.postfix_code.charAt(c++)) {
                                if (this.postfix_code.charAt(c++) == '\u00ff') {
                                    ++this.numberindex;
                                }
                            }
                            continue;
                        }
                        ++c;
                        continue;
                    }
                    case 9: {
                        continue;
                    }
                    case 10: {
                        --n;
                        if (this.stack[n] != 0.0 && this.stack[n + 1] != 0.0) {
                            this.stack[n] = 1.0;
                            continue;
                        }
                        this.stack[n] = 0.0;
                        continue;
                    }
                    case 11: {
                        --n;
                        if (this.stack[n] != 0.0 || this.stack[n + 1] != 0.0) {
                            this.stack[n] = 1.0;
                            continue;
                        }
                        this.stack[n] = 0.0;
                        continue;
                    }
                    case 12: {
                        this.stack[n] = ((this.stack[n] == 0.0) ? 1.0 : 0.0);
                        continue;
                    }
                    case 255: {
                        this.stack[++n] = this.number[this.numberindex++];
                        continue;
                    }
                    case 253: {
                        this.stack[++n] = 3.141592653589793;
                        continue;
                    }
                    case 254: {
                        this.stack[++n] = 2.718281828459045;
                        continue;
                    }
                    default: {
                        if (char1 >= '\u0bb8') {
                            this.stack[++n] = this.refvalue[char1 - '\u0bb8'];
                            continue;
                        }
                        if (char1 >= '\u07d0') {
                            this.stack[++n] = this.var_value[char1 - '\u07d0'];
                            continue;
                        }
                        if (char1 >= '\u0402') {
                            this.stack[n - 1] = this.builtInExtFunction(char1 - '\u0402', this.stack[n - 1], this.stack[n]);
                            --n;
                            continue;
                        }
                        if (char1 >= '\u03e8') {
                            this.stack[n] = this.builtInFunction(char1 - '\u03e8', this.stack[n]);
                            continue;
                        }
                        this.error = 8;
                        return Double.NaN;
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException ex2) {
                this.error = 9;
                return Double.NaN;
            }
            catch (NullPointerException ex3) {
                this.error = 8;
                return Double.NaN;
            }
        }
    }
    
    static {
        LOG10 = Math.log(10.0);
    }
}
