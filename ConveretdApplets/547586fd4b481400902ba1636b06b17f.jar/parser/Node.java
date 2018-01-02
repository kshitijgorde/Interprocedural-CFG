// 
// Decompiled by Procyon v0.5.30
// 

package parser;

import mathTools.MyMath;

public class Node
{
    private static int indent;
    private static boolean changed;
    private static Node[] constList;
    public String nodeType;
    public String nodeToken;
    public int nodePosition;
    public double nodeValue;
    public boolean isBoolean;
    public Node B1;
    public Node B2;
    public Node B3;
    
    static {
        Node.indent = 0;
        Node.changed = false;
        Node.constList = new Node[8];
    }
    
    public Node(final Node node) throws ParseException {
        this.nodeType = node.nodeType;
        this.nodeToken = node.nodeToken;
        this.nodeValue = node.nodeValue;
        this.nodePosition = 0;
        final Node b1 = null;
        this.B3 = b1;
        this.B2 = b1;
        this.B1 = b1;
    }
    
    public Node(final String nodeToken, final int nodePosition) throws ParseException {
        this.nodeToken = nodeToken;
        this.nodePosition = nodePosition;
        this.nodeValue = 0.0;
        Label_0319: {
            if ("#+#-#*#/#^#<#>#=#==#<=#>=#!=#<>#&#&&#and#|#||#or#!#not#?#:#".indexOf("#" + nodeToken + "#") >= 0) {
                this.nodeType = "OPERATOR";
            }
            else if ("(".indexOf(nodeToken) >= 0) {
                this.nodeType = "LPAREN";
            }
            else if (")".indexOf(nodeToken) >= 0) {
                this.nodeType = "RPAREN";
            }
            else if ("xyzt".indexOf(nodeToken) >= 0 && nodeToken.length() == 1) {
                this.nodeType = "VARIABLE";
            }
            else if ("#pi#e#rnd#".indexOf("#" + nodeToken + "#") >= 0) {
                this.nodeType = "FCN0";
            }
            else if ("#abs#exp#sqrt#floor#log#ln#log10#log2#signum#sin#cos#tan#asin#acos#atan#".indexOf("#" + nodeToken + "#") >= 0) {
                this.nodeType = "FCN1";
            }
            else if ("abcdfghijklmnABCDEFGHIJKLMN".indexOf(nodeToken) >= 0 && nodeToken.length() == 1) {
                this.nodeType = "CONSTANT";
            }
            else {
                if ("0123456789.".indexOf(nodeToken.charAt(0)) >= 0) {
                    try {
                        this.nodeType = "NUMBER";
                        this.nodeValue = Double.valueOf(nodeToken);
                        break Label_0319;
                    }
                    catch (Exception ex) {
                        throw new ParseException("Illegal number format", this.nodePosition);
                    }
                }
                if (!nodeToken.equals("#")) {
                    throw new ParseException("Unrecognized symbol: " + nodeToken, this.nodePosition);
                }
                this.nodeType = "TERMINATOR";
            }
        }
        if (this.nodeToken.equals("ln")) {
            this.nodeToken = "log";
        }
        if (this.nodeToken.equals("||") || this.nodeToken.equals("or")) {
            this.nodeToken = "|";
        }
        if (this.nodeToken.equals("&&") || this.nodeToken.equals("and")) {
            this.nodeToken = "&";
        }
        if (this.nodeToken.equals("==")) {
            this.nodeToken = "=";
        }
        if (this.nodeToken.equals("!=")) {
            this.nodeToken = "<>";
        }
        if (this.nodeToken.equals("not")) {
            this.nodeToken = "!";
        }
        if ("#<#>#=#<=#>=#<>#&#|#!#".indexOf("#" + this.nodeToken + "#") >= 0) {
            this.isBoolean = true;
        }
        final Node b1 = null;
        this.B3 = b1;
        this.B2 = b1;
        this.B1 = b1;
    }
    
    public Node treeCopy() throws ParseException {
        Node node = null;
        if (this == null) {
            node = null;
        }
        else if ("#NUMBER#CONSTANT#VARIABLE#FCN0#".indexOf(this.nodeType) != -1) {
            node = new Node(this);
        }
        else if (this.nodeType.equals("FCN1")) {
            node = new Node(this);
            node.B2 = this.B2.treeCopy();
        }
        else if (this.nodeType.equals("OPERATOR")) {
            node = new Node(this);
            node.B1 = this.B1.treeCopy();
            node.B2 = this.B2.treeCopy();
            if (this.B3 != null) {
                node.B3 = this.B3.treeCopy();
            }
        }
        return node;
    }
    
    public boolean isConstant() {
        boolean constant = false;
        if (this.nodeType.equals("NUMBER")) {
            constant = true;
        }
        else if (this.nodeType.equals("CONSTANT")) {
            constant = true;
        }
        else if (this.nodeType.equals("VARIABLE")) {
            constant = false;
        }
        else if (this.nodeType.equals("FCN0")) {
            constant = true;
        }
        else if (this.nodeType.equals("FCN1")) {
            constant = this.B2.isConstant();
        }
        else if (this.nodeType.equals("OPERATOR")) {
            constant = (this.B1.isConstant() && this.B2.isConstant());
        }
        return constant;
    }
    
    public String toString() {
        return "nodeToken: " + this.nodeToken + ", " + "nodeType: " + this.nodeType + ", " + "nodePos: " + this.nodePosition + ", " + "nodeValue: " + this.nodeValue;
    }
    
    public void printTree() {
        Node.indent += 3;
        if (this == null) {
            return;
        }
        System.out.println(String.valueOf(this.repeat(Node.indent)) + this.toString());
        if (this.B1 != null) {
            this.B1.printTree();
        }
        if (this.B2 != null) {
            this.B2.printTree();
        }
        if (this.B3 != null) {
            this.B3.printTree();
        }
        Node.indent -= 3;
    }
    
    private String repeat(final int n) {
        String string = "";
        for (int i = 0; i < n; ++i) {
            string = String.valueOf(string) + " ";
        }
        return string;
    }
    
    public double value(final double n) {
        double n2 = 0.0;
        if (this.nodeType.equals("NUMBER")) {
            n2 = this.nodeValue;
        }
        else if (this.nodeType.equals("CONSTANT")) {
            n2 = Function.paramValue(this.nodeToken);
        }
        else if (this.nodeType.equals("VARIABLE")) {
            n2 = n;
        }
        else if (this.nodeType.equals("FCN0")) {
            if (this.nodeToken.equals("pi")) {
                n2 = 3.141592653589793;
            }
            else if (this.nodeToken.equals("rnd")) {
                n2 = Math.random();
            }
            else if (this.nodeToken.equals("e")) {
                n2 = 2.718281828459045;
            }
        }
        else if (this.nodeType.equals("FCN1")) {
            final double value = this.B2.value(n);
            if (this.nodeToken.equals("abs")) {
                n2 = Math.abs(value);
            }
            else if (this.nodeToken.equals("exp")) {
                n2 = Math.exp(value);
            }
            else if (this.nodeToken.equals("sqrt")) {
                n2 = Math.sqrt(value);
            }
            else if (this.nodeToken.equals("floor")) {
                n2 = Math.floor(value);
            }
            else if (this.nodeToken.equals("log")) {
                n2 = Math.log(value);
            }
            else if (this.nodeToken.equals("log10")) {
                n2 = Math.log(value) / Math.log(10.0);
            }
            else if (this.nodeToken.equals("log2")) {
                n2 = Math.log(value) / Math.log(2.0);
            }
            else if (this.nodeToken.equals("signum")) {
                n2 = ((value > 0.0) ? 1 : ((value < 0.0) ? -1 : 0));
            }
            else if (this.nodeToken.equals("sin")) {
                n2 = Math.sin(value);
            }
            else if (this.nodeToken.equals("cos")) {
                n2 = Math.cos(value);
            }
            else if (this.nodeToken.equals("tan")) {
                n2 = Math.tan(value);
            }
            else if (this.nodeToken.equals("asin")) {
                n2 = Math.asin(value);
            }
            else if (this.nodeToken.equals("acos")) {
                n2 = Math.acos(value);
            }
            else if (this.nodeToken.equals("atan")) {
                n2 = Math.atan(value);
            }
            else if (this.nodeToken.equals("-")) {
                n2 = -value;
            }
            else if (this.nodeToken.equals("!")) {
                n2 = -value;
            }
        }
        else if (this.nodeType.equals("OPERATOR")) {
            final double value2 = this.B1.value(n);
            final double value3 = this.B2.value(n);
            if (this.nodeToken.equals("?")) {
                final double value4 = this.B3.value(n);
                n2 = ((value2 > 0.0) ? value3 : value4);
            }
            if (this.nodeToken.equals("+")) {
                n2 = value2 + value3;
            }
            else if (this.nodeToken.equals("-")) {
                n2 = value2 - value3;
            }
            else if (this.nodeToken.equals("*")) {
                n2 = value2 * value3;
            }
            else if (this.nodeToken.equals("/")) {
                n2 = value2 / value3;
            }
            else if (this.nodeToken.equals("^")) {
                n2 = Math.pow(value2, value3);
            }
            else if (this.nodeToken.equals("|")) {
                n2 = Math.max(value2, value3);
            }
            else if (this.nodeToken.equals("&")) {
                n2 = Math.min(value2, value3);
            }
            else if (this.nodeToken.equals("<")) {
                n2 = value3 - value2;
            }
            else if (this.nodeToken.equals(">")) {
                n2 = value2 - value3;
            }
            else if (this.nodeToken.equals("=")) {
                n2 = Math.abs(value2 - value3);
            }
            else if (this.nodeToken.equals("<=")) {
                n2 = value3 - value2;
            }
            else if (this.nodeToken.equals(">=")) {
                n2 = value2 - value3;
            }
            else if (this.nodeToken.equals("<>")) {
                n2 = -Math.abs(value2 - value3);
            }
        }
        return n2;
    }
    
    public double value(final double n, final double n2) {
        double n3 = 0.0;
        if (this.nodeType.equals("NUMBER")) {
            n3 = this.nodeValue;
        }
        else if (this.nodeType.equals("CONSTANT")) {
            n3 = Function.paramValue(this.nodeToken);
        }
        else if (this.nodeType.equals("VARIABLE")) {
            if (this.nodeToken.equals("x")) {
                n3 = n;
            }
            else {
                n3 = n2;
            }
        }
        else if (this.nodeType.equals("FCN0")) {
            if (this.nodeToken.equals("pi")) {
                n3 = 3.141592653589793;
            }
            else if (this.nodeToken.equals("rnd")) {
                n3 = Math.random();
            }
            else if (this.nodeToken.equals("e")) {
                n3 = 2.718281828459045;
            }
        }
        else if (this.nodeType.equals("FCN1")) {
            final double value = this.B2.value(n, n2);
            if (this.nodeToken.equals("abs")) {
                n3 = Math.abs(value);
            }
            else if (this.nodeToken.equals("exp")) {
                n3 = Math.exp(value);
            }
            else if (this.nodeToken.equals("sqrt")) {
                n3 = Math.sqrt(value);
            }
            else if (this.nodeToken.equals("floor")) {
                n3 = Math.floor(value);
            }
            else if (this.nodeToken.equals("log")) {
                n3 = Math.log(value);
            }
            else if (this.nodeToken.equals("signum")) {
                n3 = ((value > 0.0) ? 1 : ((value < 0.0) ? -1 : 0));
            }
            else if (this.nodeToken.equals("sin")) {
                n3 = Math.sin(value);
            }
            else if (this.nodeToken.equals("cos")) {
                n3 = Math.cos(value);
            }
            else if (this.nodeToken.equals("tan")) {
                n3 = Math.tan(value);
            }
            else if (this.nodeToken.equals("asin")) {
                n3 = Math.asin(value);
            }
            else if (this.nodeToken.equals("acos")) {
                n3 = Math.acos(value);
            }
            else if (this.nodeToken.equals("atan")) {
                n3 = Math.atan(value);
            }
            else if (this.nodeToken.equals("-")) {
                n3 = -value;
            }
            else if (this.nodeToken.equals("!")) {
                n3 = -value;
            }
        }
        else if (this.nodeType.equals("OPERATOR")) {
            final double value2 = this.B1.value(n, n2);
            final double value3 = this.B2.value(n, n2);
            if (this.nodeToken.equals("?")) {
                final double value4 = this.B3.value(n, n2);
                n3 = ((value2 > 0.0) ? value3 : value4);
            }
            if (this.nodeToken.equals("+")) {
                n3 = value2 + value3;
            }
            else if (this.nodeToken.equals("-")) {
                n3 = value2 - value3;
            }
            else if (this.nodeToken.equals("*")) {
                n3 = value2 * value3;
            }
            else if (this.nodeToken.equals("/")) {
                n3 = value2 / value3;
            }
            else if (this.nodeToken.equals("^")) {
                n3 = Math.pow(value2, value3);
            }
            else if (this.nodeToken.equals("|")) {
                n3 = Math.max(value2, value3);
            }
            else if (this.nodeToken.equals("&")) {
                n3 = Math.min(value2, value3);
            }
            else if (this.nodeToken.equals("<")) {
                n3 = value3 - value2;
            }
            else if (this.nodeToken.equals(">")) {
                n3 = value2 - value3;
            }
            else if (this.nodeToken.equals("=")) {
                n3 = -Math.abs(value2 - value3);
            }
            else if (this.nodeToken.equals("<=")) {
                n3 = value3 - value2;
            }
            else if (this.nodeToken.equals(">=")) {
                n3 = value2 - value3;
            }
            else if (this.nodeToken.equals("<>")) {
                n3 = Math.abs(value2 - value3);
            }
        }
        return n3;
    }
    
    public Node deriv() throws ParseException {
        Node node = null;
        if (this.nodeType.equals("NUMBER")) {
            node = new Node("0", 0);
        }
        else if (this.nodeType.equals("CONSTANT")) {
            node = new Node("0", 0);
        }
        else if (this.nodeType.equals("VARIABLE")) {
            node = new Node("1", 0);
        }
        else if (this.nodeType.equals("FCN0")) {
            node = new Node("0", 0);
        }
        else if (this.nodeType.equals("FCN1")) {
            if (this.nodeToken.equals("abs")) {
                final Node b1 = new Node("signum", 0);
                b1.B2 = this.B2;
                node = new Node("*", 0);
                node.B1 = b1;
                node.B2 = this.B2.deriv();
            }
            else if (this.nodeToken.equals("exp")) {
                node = new Node("*", 0);
                node.B1 = this;
                node.B2 = this.B2.deriv();
            }
            else if (this.nodeToken.equals("sqrt")) {
                final Node b2 = new Node("/", 0);
                final Node b3 = new Node("1", 0);
                final Node b4 = new Node("*", 0);
                b4.B1 = new Node("2", 0);
                b4.B2 = this;
                b2.B1 = b3;
                b2.B2 = b4;
                node = new Node("*", 0);
                node.B1 = b2;
                node.B2 = this.B2.deriv();
            }
            else if (this.nodeToken.equals("floor")) {
                node = new Node("0", 0);
            }
            else if (this.nodeToken.equals("log")) {
                final Node b5 = new Node("/", 0);
                b5.B1 = new Node("1", 0);
                b5.B2 = this.B2;
                node = new Node("*", 0);
                node.B1 = b5;
                node.B2 = this.B2.deriv();
            }
            else if (this.nodeToken.equals("log10")) {
                final Node b6 = new Node("/", 0);
                b6.B1 = new Node("1", 0);
                b6.B2 = this.B2;
                final Node b7 = new Node("*", 0);
                b7.B1 = b6;
                b7.B2 = this.B2.deriv();
                final Node node2 = new Node("/", 0);
                final Node b8 = new Node("log", 0);
                b8.B2 = new Node("10", 0);
                node2.B1 = b7;
                node2.B2 = b8;
                node = node2;
            }
            else if (this.nodeToken.equals("log2")) {
                final Node b9 = new Node("/", 0);
                b9.B1 = new Node("1", 0);
                b9.B2 = this.B2;
                final Node b10 = new Node("*", 0);
                b10.B1 = b9;
                b10.B2 = this.B2.deriv();
                final Node node3 = new Node("/", 0);
                final Node b11 = new Node("log", 0);
                b11.B2 = new Node("2", 0);
                node3.B1 = b10;
                node3.B2 = b11;
                node = node3;
            }
            else if (this.nodeToken.equals("signum")) {
                node = new Node("0", 0);
            }
            if (this.nodeToken.equals("sin")) {
                final Node b12 = new Node("cos", 0);
                b12.B2 = this.B2;
                final Node deriv = this.B2.deriv();
                node = new Node("*", 0);
                node.B1 = b12;
                node.B2 = deriv;
            }
            else if (this.nodeToken.equals("cos")) {
                final Node b13 = new Node("sin", 0);
                b13.B2 = this.B2;
                final Node b14 = new Node("-", 0);
                b14.nodeType = "FCN1";
                b14.B2 = b13;
                final Node deriv2 = this.B2.deriv();
                node = new Node("*", 0);
                node.B1 = b14;
                node.B2 = deriv2;
            }
            else if (this.nodeToken.equals("tan")) {
                final Node node4 = new Node("cos", 0);
                node4.B2 = this.B2;
                final Node b15 = new Node("*", 0);
                b15.B1 = node4;
                b15.B2 = node4;
                final Node b16 = new Node("/", 0);
                b16.B1 = new Node("1", 0);
                b16.B2 = b15;
                node = new Node("*", 0);
                node.B1 = b16;
                node.B2 = this.B2.deriv();
            }
            else if (this.nodeToken.equals("asin")) {
                final Node b17 = new Node("/", 0);
                final Node b18 = new Node("sqrt", 0);
                final Node b19 = new Node("-", 0);
                final Node node5 = new Node("1", 0);
                final Node b20 = new Node("*", 0);
                b20.B1 = this.B2;
                b20.B2 = this.B2;
                b19.B1 = node5;
                b19.B2 = b20;
                b18.B2 = b19;
                b17.B1 = node5;
                b17.B2 = b18;
                node = new Node("*", 0);
                node.B1 = b17;
                node.B2 = this.B2.deriv();
            }
            else if (this.nodeToken.equals("acos")) {
                final Node b21 = new Node("-", 0);
                b21.nodeType = "FCN1";
                final Node b22 = new Node("/", 0);
                final Node b23 = new Node("sqrt", 0);
                final Node b24 = new Node("-", 0);
                final Node node6 = new Node("1", 0);
                final Node b25 = new Node("*", 0);
                b25.B1 = this.B2;
                b25.B2 = this.B2;
                b24.B1 = node6;
                b24.B2 = b25;
                b23.B2 = b24;
                b22.B1 = node6;
                b22.B2 = b23;
                b21.B2 = b22;
                node = new Node("*", 0);
                node.B1 = b21;
                node.B2 = this.B2.deriv();
            }
            else if (this.nodeToken.equals("atan")) {
                final Node b26 = new Node("/", 0);
                final Node b27 = new Node("+", 0);
                final Node node7 = new Node("1", 0);
                final Node b28 = new Node("*", 0);
                b28.B1 = this.B2;
                b28.B2 = this.B2;
                b27.B1 = node7;
                b27.B2 = b28;
                b26.B1 = node7;
                b26.B2 = b27;
                node = new Node("*", 0);
                node.B1 = b26;
                node.B2 = this.B2.deriv();
            }
            else if (this.nodeToken.equals("-")) {
                node = new Node("-", 0);
                node.nodeType = "FCN1";
                node.B2 = this.B2.deriv();
            }
        }
        else if (this.nodeType.equals("OPERATOR")) {
            switch (this.nodeToken.charAt(0)) {
                case '?': {
                    node = new Node("?", 0);
                    node.B1 = this.B1;
                    node.B2 = this.B2.deriv();
                    node.B3 = this.B3.deriv();
                    break;
                }
                case '+': {
                    node = new Node("+", 0);
                    node.B1 = this.B1.deriv();
                    node.B2 = this.B2.deriv();
                    break;
                }
                case '-': {
                    node = new Node("-", 0);
                    node.B1 = this.B1.deriv();
                    node.B2 = this.B2.deriv();
                    break;
                }
                case '*': {
                    final Node deriv3 = this.B1.deriv();
                    final Node deriv4 = this.B2.deriv();
                    final Node b29 = new Node("*", 0);
                    b29.B1 = this.B1;
                    b29.B2 = deriv4;
                    final Node b30 = new Node("*", 0);
                    b30.B1 = this.B2;
                    b30.B2 = deriv3;
                    node = new Node("+", 0);
                    node.B1 = b29;
                    node.B2 = b30;
                    break;
                }
                case '/': {
                    final Node b31 = new Node("*", 0);
                    b31.B1 = this.B2;
                    b31.B2 = this.B1.deriv();
                    final Node b32 = new Node("*", 0);
                    b32.B1 = this.B1;
                    b32.B2 = this.B2.deriv();
                    final Node b33 = new Node("-", 0);
                    b33.B1 = b31;
                    b33.B2 = b32;
                    final Node b34 = new Node("*", 0);
                    b34.B1 = this.B2;
                    b34.B2 = this.B2;
                    node = new Node("/", 0);
                    node.B1 = b33;
                    node.B2 = b34;
                    break;
                }
                case '^': {
                    if (this.B2.isConstant()) {
                        final Node b35 = new Node("*", 0);
                        final Node b36 = new Node("^", 0);
                        final Node b37 = new Node("-", 0);
                        final Node b38 = new Node("1", 0);
                        b37.B1 = this.B2;
                        b37.B2 = b38;
                        b36.B1 = this.B1;
                        b36.B2 = b37;
                        b35.B1 = this.B2;
                        b35.B2 = b36;
                        node = new Node("*", 0);
                        node.B1 = b35;
                        node.B2 = this.B1.deriv();
                        break;
                    }
                    final Node b39 = new Node("+", 0);
                    final Node b40 = new Node("*", 0);
                    final Node b41 = new Node("*", 0);
                    final Node b42 = new Node("log", 0);
                    final Node b43 = new Node("/", 0);
                    b43.B1 = this.B1.deriv();
                    b43.B2 = this.B1;
                    b42.B2 = this.B1;
                    b40.B1 = this.B2.deriv();
                    b40.B2 = b42;
                    b41.B1 = this.B2;
                    b41.B2 = b43;
                    b39.B1 = b40;
                    b39.B2 = b41;
                    node = new Node("*", 0);
                    node.B1 = this;
                    node.B2 = b39;
                    break;
                }
                default: {
                    node = new Node("#", 0);
                    break;
                }
            }
        }
        return node;
    }
    
    public Node normalize() throws ParseException {
        Node normalizeTree = this;
        do {
            Node.changed = false;
            normalizeTree = normalizeTree.normalizeTree();
        } while (Node.changed);
        return normalizeTree;
    }
    
    public Node normalizeTree() throws ParseException {
        if (this == null) {
            return null;
        }
        if (this.B1 != null) {
            this.B1 = this.B1.normalizeTree();
        }
        if (this.B2 != null) {
            this.B2 = this.B2.normalizeTree();
        }
        if (this.B3 != null) {
            this.B3 = this.B3.normalizeTree();
        }
        Node node;
        if (this.matches("A-B")) {
            node = this.patterns("A+(-1)*B");
        }
        else if (this.matches("A/B")) {
            node = this.patterns("A*B^(-1)");
        }
        else {
            if (!this.nodeType.equals("FCN1") || !this.nodeToken.equals("-") || this.B2.nodeType.equals("NUMBER")) {
                return this;
            }
            node = new Node("*", 0);
            node.B1 = new Node("-", 0);
            node.B1.nodeType = "FCN1";
            node.B1.B2 = new Node("1", 0);
            node.B2 = this.B2;
        }
        Node.changed = true;
        return node;
    }
    
    public Node expand() throws ParseException {
        Node expandTree = this;
        do {
            Node.changed = false;
            expandTree = expandTree.expandTree();
        } while (Node.changed);
        return expandTree;
    }
    
    public Node expandTree() throws ParseException {
        if (this == null) {
            return null;
        }
        if (this.B1 != null) {
            this.B1 = this.B1.expandTree();
        }
        if (this.B2 != null) {
            this.B2 = this.B2.expandTree();
        }
        if (this.B3 != null) {
            this.B3 = this.B3.expandTree();
        }
        Node node;
        if (this.matches("K")) {
            node = this.doArithmetic(this);
            if (node == this) {
                return this;
            }
        }
        else if (this.matches("A+K")) {
            node = this.patterns("K+A");
        }
        else if (this.matches("A*K")) {
            node = this.patterns("K*A");
        }
        else if (this.nodeType.equals("FCN1") && this.nodeToken.equals("-") && this.B2.nodeType.equals("FCN1") && this.B2.nodeToken.equals("-")) {
            node = this.B2.B2;
        }
        else if (this.matches("0+A")) {
            node = this.patterns("A");
        }
        else if (this.matches("0*A")) {
            node = new Node("0", 0);
        }
        else if (this.matches("1*A")) {
            node = this.patterns("A");
        }
        else {
            if (this.matches("A^0")) {
                return new Node("1", 1);
            }
            if (this.matches("A^1")) {
                return this.patterns("A");
            }
            if (this.matches("A*(B*C)")) {
                node = this.patterns("(A*B)*C");
            }
            else if (this.matches("A+(B+C)")) {
                node = this.patterns("(A+B)+C");
            }
            else if (this.matches("A*(B+C)")) {
                node = this.patterns("A*B+A*C");
            }
            else if (this.matches("(A+B)*C")) {
                node = this.patterns("A*C+B*C");
            }
            else if (this.matches("A*A")) {
                node = this.patterns("A^2");
            }
            else if (this.matches("D*A*A")) {
                node = this.patterns("D*A^2");
            }
            else if (this.matches("(A^K)*A")) {
                node = this.patterns("A^(K+1)");
            }
            else if (this.matches("D*(A^K)*A")) {
                node = this.patterns("D*A^(K+1)");
            }
            else if (this.matches("A*(A^K)")) {
                node = this.patterns("A^(K+1)");
            }
            else if (this.matches("D*A*(A^K)")) {
                node = this.patterns("D*A^(K+1)");
            }
            else if (this.matches("(A^K)^L")) {
                node = this.patterns("A^(K*L)");
            }
            else if (this.matches("A^K*A^L")) {
                node = this.patterns("A^(K+L)");
            }
            else {
                if (!this.matches("D*A^K*A^L")) {
                    return this;
                }
                node = this.patterns("D*A^(K+L)");
            }
        }
        Node.changed = true;
        return node;
    }
    
    public Node sort() throws ParseException {
        Node sortTree = this;
        do {
            Node.changed = false;
            sortTree = sortTree.sortTree();
        } while (Node.changed);
        return sortTree;
    }
    
    public Node sortTree() throws ParseException {
        if (this == null) {
            return null;
        }
        if (this.B1 != null) {
            this.B1 = this.B1.sortTree();
        }
        if (this.B2 != null) {
            this.B2 = this.B2.sortTree();
        }
        if (this.B3 != null) {
            this.B3 = this.B3.sortTree();
        }
        Node node;
        if (this.matches("D*A*B")) {
            if (!this.branch("B").lessthan(this.branch("A"))) {
                return this;
            }
            System.out.println(this.expression());
            node = this.patterns("D*B*A");
            System.out.println(node.expression());
            System.out.println();
            MyMath.wait(2000);
        }
        else {
            if (!this.matches("A*B")) {
                return this;
            }
            if (!this.branch("B").lessthan(this.branch("A"))) {
                return this;
            }
            System.out.println("***" + this.expression());
            node = this.patterns("B*A");
            System.out.println("***" + node.expression());
            System.out.println();
            MyMath.wait(2000);
        }
        Node.changed = true;
        return node;
    }
    
    public Node doArithmetic(final Node node) throws ParseException {
        if (node.nodeType.equals("NUMBER") || (node.nodeType.equals("FCN1") && node.nodeToken.equals("-") && node.B2.nodeType.equals("NUMBER"))) {
            return node;
        }
        final int n = (int)Node.constList[4].value(0.0);
        Node node2;
        if (n >= 0) {
            node2 = new Node(String.valueOf(n), 0);
        }
        else {
            node2 = new Node("-", 0);
            node2.nodeType = "FCN1";
            node2.B2 = new Node(String.valueOf(-n), 0);
        }
        return node2;
    }
    
    public String expression() {
        if (this == null) {
            return "";
        }
        String s;
        if ("#NUMBER#CONSTANT#VARIABLE#FCN0#".indexOf("#" + this.nodeType + "#") != -1) {
            s = this.nodeToken;
        }
        else if (this.nodeType.equals("FCN1") && !this.nodeToken.equals("-")) {
            s = String.valueOf(this.nodeToken) + "(" + this.B2.expression() + ")";
        }
        else if (this.nodeType.equals("FCN1") && this.nodeToken.equals("-")) {
            s = String.valueOf(this.nodeToken) + this.argument(2);
        }
        else if (this.nodeType.equals("OPERATOR")) {
            s = String.valueOf(this.argument(1)) + (("?+-&|".indexOf(this.nodeToken) != -1) ? " " : "") + this.nodeToken + (("?+-&|".indexOf(this.nodeToken) != -1) ? " " : "") + this.argument(2) + this.argument(3);
        }
        else {
            s = "uncovered case";
        }
        return s;
    }
    
    public String argument(final int n) {
        if (n == 1) {
            String s;
            if ("?+".indexOf(this.nodeToken) != -1) {
                s = "?";
            }
            else if (this.nodeToken.equals("-")) {
                s = "?+-";
            }
            else if (this.nodeToken.equals("*")) {
                s = "?+-";
            }
            else if (this.nodeToken.equals("/")) {
                s = "?+-";
            }
            else if (this.nodeToken.equals("^")) {
                s = "?+-*/";
            }
            else if (this.nodeToken.equals("&")) {
                s = "|";
            }
            else {
                s = "";
            }
            return String.valueOf((s.indexOf(this.B1.nodeToken) != -1) ? "(" : "") + this.B1.expression() + ((s.indexOf(this.B1.nodeToken) != -1) ? ")" : "");
        }
        if (n == 2) {
            String s2;
            if (this.nodeType.equals("FCN1") && this.nodeToken.equals("-")) {
                s2 = "?+-";
            }
            else if ("?+".indexOf(this.nodeToken) != -1) {
                s2 = "?";
            }
            else if (this.nodeToken.equals("-")) {
                s2 = "?+-";
            }
            else if (this.nodeToken.equals("*")) {
                s2 = "?+-/";
            }
            else if (this.nodeToken.equals("/")) {
                s2 = "?+-*/";
            }
            else if (this.nodeToken.equals("^")) {
                s2 = "?+-*/^";
            }
            else if (this.nodeToken.equals("&")) {
                s2 = "|";
            }
            else {
                s2 = "";
            }
            return String.valueOf((s2.indexOf(this.B2.nodeToken) != -1) ? "(" : "") + this.B2.expression() + ((s2.indexOf(this.B2.nodeToken) != -1) ? ")" : "");
        }
        if (this.B3 != null) {
            return " : " + this.B3.expression();
        }
        return "";
    }
    
    private boolean isInteger() {
        if (!this.isConstant()) {
            return false;
        }
        final double value = this.value(0.0);
        return Math.abs(value - (int)value) / (1.0 + Math.abs(value)) < 1.0E-14;
    }
    
    private boolean sameInteger(final Node node) {
        if (!this.isInteger() || !node.isInteger()) {
            return false;
        }
        final double value = this.value(0.0);
        return Math.abs(value - node.value(0.0)) / (1.0 + Math.abs(value)) < 1.0E-15;
    }
    
    public boolean matches(final String s) {
        final Function function = new Function(s);
        for (int i = 0; i < 8; ++i) {
            Node.constList[i] = null;
        }
        if (!function.isCompiled()) {
            System.out.println("'" + s + "' is not a valid pattern!");
            return false;
        }
        return match(this, function.tree);
    }
    
    public static boolean match(final Node node, final Node node2) {
        boolean b = false;
        if (node == null && node2 != null) {
            b = false;
        }
        else if (node2 == null) {
            b = true;
        }
        else if (!match(node.B1, node2.B1) || !match(node.B2, node2.B2) || !match(node.B3, node2.B3)) {
            b = false;
        }
        else if ("#VARIABLE#FCN0#FCN1#OPERATOR#".indexOf(node2.nodeType) != -1) {
            if (node2.nodeToken.equals("-") && node.nodeToken.equals("-")) {
                b = node2.nodeType.equals(node.nodeType);
            }
            else {
                b = node2.nodeToken.equals(node.nodeToken);
            }
        }
        else if (node2.nodeType.equals("NUMBER")) {
            b = (node.isConstant() && Math.abs(node.value(0.0) - node2.nodeValue) / (1.0 + Math.abs(node2.nodeValue)) < 1.0E-15);
        }
        else if (node2.nodeType.equals("CONSTANT") && "KLMN".indexOf(node2.nodeToken) != -1) {
            final int index = "KLMN".indexOf(node2.nodeToken);
            if (!node.isInteger()) {
                b = false;
            }
            else if (Node.constList[4 + index] == null) {
                Node.constList[4 + index] = node;
                b = true;
            }
            else {
                b = node.sameInteger(Node.constList[4 + index]);
            }
        }
        else if (node2.nodeType.equals("CONSTANT") && "ABCD".indexOf(node2.nodeToken) != -1) {
            final int index2 = "ABCD".indexOf(node2.nodeToken);
            if (Node.constList[index2] == null) {
                Node.constList[index2] = node;
                b = true;
            }
            else {
                b = node.equals(Node.constList[index2]);
            }
        }
        return b;
    }
    
    public Node patterns(final String s) throws ParseException {
        return new Function(s).tree.buildCopy();
    }
    
    public Node buildCopy() throws ParseException {
        if (this == null) {
            return null;
        }
        Node buildCopy = null;
        Node buildCopy2 = null;
        Node buildCopy3 = null;
        if (this.nodeType.equals("CONSTANT") && "ABCDKLMN".indexOf(this.nodeToken) != -1) {
            return Node.constList["ABCDKLMN".indexOf(this.nodeToken)];
        }
        if (this.B1 != null) {
            buildCopy = this.B1.buildCopy();
        }
        if (this.B2 != null) {
            buildCopy2 = this.B2.buildCopy();
        }
        if (this.B3 != null) {
            buildCopy3 = this.B3.buildCopy();
        }
        final Node node = new Node(this);
        node.B1 = buildCopy;
        node.B2 = buildCopy2;
        node.B3 = buildCopy3;
        return node;
    }
    
    public boolean equals(final Node node) {
        return same(this, node);
    }
    
    public static boolean same(final Node node, final Node node2) {
        return (node == null && node2 == null) || (node != null && node2 != null && node.nodeToken.equals(node2.nodeToken) && same(node.B1, node2.B1) && same(node.B2, node2.B2) && same(node.B3, node2.B3));
    }
    
    public boolean lessthan(final Node node) {
        boolean b = false;
        if (this.isConstant() && !node.isConstant()) {
            return true;
        }
        if (!this.isConstant() && node.isConstant()) {
            return false;
        }
        if (this.isConstant() && node.isConstant() && this.numberType() != node.numberType()) {
            return this.numberType() < node.numberType();
        }
        if (node.nodeType.equals("FCN1") && node.nodeToken.equals("-")) {
            b = false;
        }
        else if (this.nodeType.equals("FCN1") && this.nodeToken.equals("-")) {
            b = true;
        }
        else if (this.nodeType.equals("NUMBER")) {
            b = ("#FCN0#CONSTANT#FCN1#VARIABLE#OPERATOR#".indexOf(node.nodeType) != -1);
        }
        else if (this.nodeType.equals("FCN0")) {
            b = ("#CONSTANT#FCN1#VARIABLE#OPERATOR#".indexOf(node.nodeType) != -1);
        }
        else if (this.nodeType.equals("CONSTANT")) {
            b = ("#FCN1#VARIABLE#OPERATOR#".indexOf(node.nodeType) != -1);
        }
        else if (this.isConstant()) {
            b = ("#VARIABLE#OPERATOR#".indexOf(node.nodeType) != -1);
        }
        else if (this.nodeType.equals("VARIABLE")) {
            if (node.nodeType.equals("VARIABLE")) {
                b = (this.nodeToken.compareTo(node.nodeToken) < 0);
            }
            else {
                b = (!node.isConstant() && "#FCN1#OPERATOR#".indexOf(node.nodeType) != -1);
            }
        }
        else if (this.nodeToken.equals("^")) {
            if (node.nodeToken.equals("^")) {
                b = (this.B1.equals(node.B1) && this.B2.lessthan(node.B2));
                System.out.println();
                System.out.println(String.valueOf(this.B2.expression()) + "  <  " + node.B2.expression() + "  :  " + this.B2.lessthan(node.B2));
                MyMath.wait(4000);
            }
            else {
                b = ("#FCN1#".indexOf(node.nodeType) != -1);
            }
        }
        return b;
    }
    
    public Node branch(final String s) {
        return Node.constList["ABCDKLMN".indexOf(s)];
    }
    
    public int numberType() {
        if (this.nodeType.equals("FCN1") && this.nodeToken.equals("-")) {
            return 0;
        }
        if (this.nodeType.equals("NUMBER")) {
            return 1;
        }
        if (this.nodeType.equals("FCN0")) {
            return 2;
        }
        if (this.nodeType.equals("CONSTANT")) {
            return 3;
        }
        if (this.nodeToken.equals("^")) {
            return 4;
        }
        if (this.nodeType.equals("FCN1")) {
            return 5;
        }
        return 6;
    }
}
