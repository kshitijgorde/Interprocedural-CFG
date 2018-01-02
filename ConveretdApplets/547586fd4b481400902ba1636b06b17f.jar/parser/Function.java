// 
// Decompiled by Procyon v0.5.30
// 

package parser;

import java.util.Vector;

public class Function
{
    private int pause;
    private String functionType;
    public static String algorithm;
    protected Node tree;
    private Parser p;
    private Node nextNode;
    private boolean isCompiled;
    private String errorMessage;
    private int errorPosition;
    private static Vector params;
    private boolean rememberIfMinus;
    
    static {
        Function.params = new Vector();
    }
    
    public Function(final String algorithm) {
        this.pause = 2000;
        this.functionType = "numeric";
        Function.algorithm = algorithm;
        try {
            this.tree = this.parseFunction(algorithm);
            this.isCompiled = true;
        }
        catch (ParseException ex) {
            this.isCompiled = false;
            this.errorMessage = ex.errorMessage;
            this.errorPosition = ex.errorPosition;
        }
        catch (Exception ex2) {
            this.isCompiled = false;
            this.errorMessage = "Unknown Error";
            this.errorPosition = 0;
        }
    }
    
    public Function(final String algorithm, final String functionType) {
        this.pause = 2000;
        this.functionType = "numeric";
        Function.algorithm = algorithm;
        this.functionType = functionType;
        try {
            this.tree = this.parseFunction(algorithm);
            this.isCompiled = true;
        }
        catch (ParseException ex) {
            this.isCompiled = false;
            this.errorMessage = ex.errorMessage;
            this.errorPosition = ex.errorPosition;
        }
        catch (Exception ex2) {
            this.isCompiled = false;
            this.errorMessage = "Unknown Error";
            this.errorPosition = 0;
        }
    }
    
    public Function(final Function function) throws ParseException {
        this.pause = 2000;
        this.functionType = "numeric";
        this.tree = function.tree.treeCopy();
        Function.algorithm = "";
    }
    
    public static void setParam(final String s, final double n) {
        if (s.length() != 1 || "abcdefghijklmnABCDEFGHIJKLMN".indexOf(s) == -1) {
            return;
        }
        int n2;
        for (n2 = 0; n2 < Function.params.size() && !((Param)Function.params.elementAt(n2)).name().equals(s); ++n2) {}
        if (n2 < Function.params.size()) {
            Function.params.setElementAt(new Param(s, n), n2);
        }
        else {
            Function.params.addElement(new Param(s, n));
        }
    }
    
    public static double paramValue(final String s) {
        int n;
        for (n = 0; n < Function.params.size() && !((Param)Function.params.elementAt(n)).name().equals(s); ++n) {}
        if (n < Function.params.size()) {
            return ((Param)Function.params.elementAt(n)).value();
        }
        return Double.NaN;
    }
    
    public Function derivative() {
        final Function function = new Function("0");
        try {
            function.tree = this.tree.treeCopy().deriv();
        }
        catch (ParseException ex) {}
        return function;
    }
    
    public double value(final double n) {
        return this.tree.value(n);
    }
    
    public double value(final double n, final double n2) {
        return this.tree.value(n, n2);
    }
    
    public boolean isCompiled() {
        return this.isCompiled;
    }
    
    public String errorMessage() {
        return this.errorMessage;
    }
    
    public int errorPosition() {
        return this.errorPosition;
    }
    
    public String errorOffset() {
        String concat = "";
        for (int i = 0; i <= this.errorPosition() - 1; ++i) {
            concat = concat.concat(" ");
        }
        return concat;
    }
    
    public void printFunction() {
        if (this == null) {
            return;
        }
        this.tree.printTree();
    }
    
    public boolean isConstant() {
        return this.tree.isConstant();
    }
    
    public Node normalize() throws ParseException {
        return this.tree.treeCopy().normalize();
    }
    
    public Node expand() throws ParseException {
        return this.tree.treeCopy().expand();
    }
    
    public Node sort() throws ParseException {
        return this.tree.treeCopy().sort();
    }
    
    public boolean matches(final String s) {
        return this.tree.matches(s);
    }
    
    public String expression() {
        if (this == null) {
            return "";
        }
        return this.tree.expression();
    }
    
    public boolean equals(final Function function) {
        return this.tree.equals(function.tree);
    }
    
    public boolean lessthan(final Function function) {
        return this.tree.lessthan(function.tree);
    }
    
    private Node parseFunction(final String s) throws ParseException {
        if (!this.functionType.equals("numeric") && !this.functionType.equals("boolean")) {
            ParseException.throwParseException("2nd argument of Function should be 'numeric' or 'boolean'", 0);
        }
        this.p = new Parser(s);
        if (!this.p.hasMoreNodes()) {
            ParseException.throwParseException("Empty input", 0);
        }
        else {
            this.nextNode = this.p.nextNode();
        }
        final Node expression = this.parseExpression();
        if (!this.nextNode.nodeType.equals("TERMINATOR")) {
            ParseException.throwParseException("Expected an operator!", this.nextNode.nodePosition);
        }
        if (this.functionType.equals("numeric") && expression.isBoolean) {
            ParseException.throwParseException("Expected a numeric expression!", 0);
        }
        if (this.functionType.equals("boolean") && !expression.isBoolean) {
            ParseException.throwParseException("Expected a boolean expression!", 0);
        }
        return expression;
    }
    
    private Node parseExpression() throws ParseException {
        Node booleanExpression = this.parseBooleanExpression();
        if (booleanExpression.isBoolean && this.nextNode.nodeToken.equals("?")) {
            final Node nextNode = this.nextNode;
            this.nextNode = this.p.nextNode();
            final Node expression = this.parseExpression();
            if (expression.isBoolean) {
                ParseException.throwParseException("Expected following argument to be a numeric expression!", nextNode.nodePosition);
            }
            final Node nextNode2 = this.nextNode;
            if (!this.nextNode.nodeToken.equals(":")) {
                ParseException.throwParseException("Expected ':'", this.nextNode.nodePosition);
            }
            this.nextNode = this.p.nextNode();
            final Node expression2 = this.parseExpression();
            if (expression2.isBoolean) {
                ParseException.throwParseException("Expected following argument to be a numeric expression!", nextNode2.nodePosition);
            }
            nextNode.B1 = booleanExpression;
            nextNode.B2 = expression;
            nextNode.B3 = expression2;
            booleanExpression = nextNode;
        }
        return booleanExpression;
    }
    
    private Node parseBooleanExpression() throws ParseException {
        Node booleanTerm = this.parseBooleanTerm();
        while (this.nextNode.nodeToken.equals("|")) {
            final Node nextNode = this.nextNode;
            this.nextNode = this.p.nextNode();
            if (!booleanTerm.isBoolean) {
                ParseException.throwParseException("Expected preceding argument to be a boolean term!", nextNode.nodePosition);
            }
            final Node booleanTerm2 = this.parseBooleanTerm();
            if (!booleanTerm2.isBoolean) {
                ParseException.throwParseException("Expected following argument to be a boolean term!", nextNode.nodePosition);
            }
            nextNode.B1 = booleanTerm;
            nextNode.B2 = booleanTerm2;
            booleanTerm = nextNode;
        }
        return booleanTerm;
    }
    
    private Node parseBooleanTerm() throws ParseException {
        Node booleanFactor = this.parseBooleanFactor();
        while (this.nextNode.nodeToken.equals("&")) {
            final Node nextNode = this.nextNode;
            this.nextNode = this.p.nextNode();
            if (!booleanFactor.isBoolean) {
                ParseException.throwParseException("Expected preceding argument to be a boolean factor!", nextNode.nodePosition);
            }
            final Node booleanFactor2 = this.parseBooleanFactor();
            if (!booleanFactor2.isBoolean) {
                ParseException.throwParseException("Expected following argument to be a boolean factor!", nextNode.nodePosition);
            }
            nextNode.B1 = booleanFactor;
            nextNode.B2 = booleanFactor2;
            booleanFactor = nextNode;
        }
        return booleanFactor;
    }
    
    private Node parseBooleanFactor() throws ParseException {
        this.deleteDoubleNegations();
        Node booleanAtom2;
        if (this.nextNode.nodeToken.equals("!")) {
            final Node nextNode = this.nextNode;
            this.nextNode = this.p.nextNode();
            nextNode.nodeType = "FCN1";
            final Node booleanAtom = this.parseBooleanAtom();
            if (!booleanAtom.isBoolean) {
                ParseException.throwParseException("Expected following argument to be a boolean expression!", nextNode.nodePosition);
            }
            nextNode.B2 = booleanAtom;
            booleanAtom2 = nextNode;
        }
        else {
            booleanAtom2 = this.parseBooleanAtom();
        }
        return booleanAtom2;
    }
    
    private Node parseBooleanAtom() throws ParseException {
        Node numericExpression = this.parseNumericExpression();
        if ("#<#>#=#<=#>=#<>#".indexOf("#" + this.nextNode.nodeToken + "#") >= 0) {
            final Node nextNode = this.nextNode;
            this.nextNode = this.p.nextNode();
            final Node numericExpression2 = this.parseNumericExpression();
            if (numericExpression2.isBoolean) {
                ParseException.throwParseException("Expected following expression to be numeric!", nextNode.nodePosition);
            }
            nextNode.B1 = numericExpression;
            nextNode.B2 = numericExpression2;
            numericExpression = nextNode;
        }
        return numericExpression;
    }
    
    private Node parseNumericExpression() throws ParseException {
        Node numericTerm = this.parseNumericTerm();
        while ("+-".indexOf(this.nextNode.nodeToken) >= 0) {
            final Node nextNode = this.nextNode;
            this.nextNode = this.p.nextNode();
            if (numericTerm.isBoolean) {
                ParseException.throwParseException("Expected preceding argument to be numeric!", nextNode.nodePosition);
            }
            final Node numericTerm2 = this.parseNumericTerm();
            if (numericTerm2.isBoolean) {
                ParseException.throwParseException("Expected following argument to be numeric!", nextNode.nodePosition);
            }
            nextNode.B1 = numericTerm;
            nextNode.B2 = numericTerm2;
            numericTerm = nextNode;
        }
        return numericTerm;
    }
    
    private Node parseNumericTerm() throws ParseException {
        Node numericFactor = this.parseNumericFactor();
        while ("*/".indexOf(this.nextNode.nodeToken) >= 0) {
            final Node nextNode = this.nextNode;
            this.nextNode = this.p.nextNode();
            if (numericFactor.isBoolean) {
                ParseException.throwParseException("Expected preceding argument to be numeric!", nextNode.nodePosition);
            }
            final Node numericFactor2 = this.parseNumericFactor();
            if (numericFactor2.isBoolean) {
                ParseException.throwParseException("Expected following argument to be numeric!", nextNode.nodePosition);
            }
            nextNode.B1 = numericFactor;
            nextNode.B2 = numericFactor2;
            numericFactor = nextNode;
        }
        return numericFactor;
    }
    
    private Node parseNumericFactor() throws ParseException {
        this.deleteDoubleMinuses();
        final boolean equals = this.nextNode.nodeToken.equals("-");
        Node unaryMinus = this.parseUnaryMinus();
        while (this.nextNode.nodeToken.equals("^")) {
            final Node nextNode = this.nextNode;
            this.nextNode = this.p.nextNode();
            if (unaryMinus.isBoolean) {
                ParseException.throwParseException("Expected preceding argument to be numeric!", nextNode.nodePosition);
            }
            final Node unaryMinus2 = this.parseUnaryMinus();
            if (unaryMinus2.isBoolean) {
                ParseException.throwParseException("Expected following argument to be numeric!", nextNode.nodePosition);
            }
            if (!equals) {
                nextNode.B1 = unaryMinus;
                nextNode.B2 = unaryMinus2;
                unaryMinus = nextNode;
            }
            else {
                nextNode.B1 = unaryMinus.B2;
                nextNode.B2 = unaryMinus2;
                unaryMinus.B2 = nextNode;
            }
        }
        return unaryMinus;
    }
    
    private Node parseUnaryMinus() throws ParseException {
        this.deleteDoubleMinuses();
        Node numericAtom2;
        if (this.nextNode.nodeToken.equals("-")) {
            final Node nextNode = this.nextNode;
            this.nextNode = this.p.nextNode();
            nextNode.nodeType = "FCN1";
            final Node numericAtom = this.parseNumericAtom();
            if (numericAtom.isBoolean) {
                ParseException.throwParseException("Expected following argument to be numeric!", nextNode.nodePosition);
            }
            nextNode.B2 = numericAtom;
            numericAtom2 = nextNode;
        }
        else {
            numericAtom2 = this.parseNumericAtom();
        }
        return numericAtom2;
    }
    
    private Node parseNumericAtom() throws ParseException {
        Node node = null;
        final String nodeType = this.nextNode.nodeType;
        if (nodeType.equals("NUMBER") || nodeType.equals("CONSTANT") || nodeType.equals("VARIABLE") || nodeType.equals("FCN0")) {
            node = this.nextNode;
        }
        else if (nodeType.equals("FCN1")) {
            final Node nextNode = this.nextNode;
            this.nextNode = this.p.nextNode();
            if (!this.nextNode.nodeType.equals("LPAREN")) {
                ParseException.throwParseException("Need left parenthesis", this.nextNode.nodePosition);
            }
            this.nextNode = this.p.nextNode();
            final Node expression = this.parseExpression();
            if (expression.isBoolean) {
                ParseException.throwParseException("Expected following argument to be numeric!", nextNode.nodePosition);
            }
            if (!this.nextNode.nodeType.equals("RPAREN")) {
                ParseException.throwParseException("Need right parenthesis", this.nextNode.nodePosition);
            }
            nextNode.B2 = expression;
            node = nextNode;
        }
        else if (nodeType.equals("LPAREN")) {
            this.nextNode = this.p.nextNode();
            node = this.parseExpression();
            if (!this.nextNode.nodeType.equals("RPAREN")) {
                ParseException.throwParseException("Need right parenthesis", this.nextNode.nodePosition);
            }
        }
        this.nextNode = this.p.nextNode();
        return node;
    }
    
    private void deleteDoubleMinuses() {
        int n = 0;
        while (this.nextNode.nodeToken.equals("-")) {
            this.nextNode = this.p.nextNode();
            ++n;
        }
        if (n % 2 == 1) {
            this.p.setPrevNode();
            this.p.setPrevNode();
            this.nextNode = this.p.nextNode();
        }
    }
    
    private void deleteDoubleNegations() {
        int n = 0;
        while (this.nextNode.nodeToken.equals("!")) {
            this.nextNode = this.p.nextNode();
            ++n;
        }
        if (n % 2 == 1) {
            this.p.setPrevNode();
            this.p.setPrevNode();
            this.nextNode = this.p.nextNode();
        }
    }
}
