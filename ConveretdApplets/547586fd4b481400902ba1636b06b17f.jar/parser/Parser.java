// 
// Decompiled by Procyon v0.5.30
// 

package parser;

import java.util.Vector;

public class Parser
{
    private String inputString;
    private int pos;
    private Vector nodeList;
    private int nextNodePosition;
    private final String OP_SYMBOLS = "+-*/^<>=&|!?:";
    private final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUM_SYMBOLS = "0123456789.";
    public static final String WHITESPACE = ",\n\t\r ";
    public static final String LPAREN = "(";
    public static final String RPAREN = ")";
    public static final String OPERATORS = "#+#-#*#/#^#<#>#=#==#<=#>=#!=#<>#&#&&#and#|#||#or#!#not#?#:#";
    public static final String VARIABLES = "xyzt";
    public static final String CONSTANTS = "abcdfghijklmnABCDEFGHIJKLMN";
    public static final String FCN0 = "#pi#e#rnd#";
    public static final String FCN1 = "#abs#exp#sqrt#floor#log#ln#log10#log2#signum#sin#cos#tan#asin#acos#atan#";
    
    public Parser(final String s) throws ParseException {
        this.nodeList = new Vector();
        this.inputString = String.valueOf(s) + "#";
        this.pos = 0;
        while (this.moreNodes()) {
            this.nodeList.addElement(this.getNextNode());
        }
        final Node node = new Node("#", this.pos);
        this.nodeList.addElement(new Node("#", this.pos));
        this.nextNodePosition = 0;
        this.insertImpliedMultiplication();
    }
    
    public boolean hasMoreNodes() {
        return this.nextNodePosition < this.nodeList.size();
    }
    
    public Node nextNode() {
        return this.nodeList.elementAt(this.nextNodePosition++);
    }
    
    public void setPrevNode() {
        --this.nextNodePosition;
    }
    
    private boolean moreNodes() {
        this.skipWhiteSpace();
        return this.pos != this.inputString.length() - 1;
    }
    
    private Node getNextNode() throws ParseException {
        final char char1 = this.inputString.charAt(this.pos);
        String s = "";
        if ("+-*/^<>=&|!?:".indexOf(char1) >= 0) {
            s = this.getOperator();
        }
        else if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(char1) >= 0) {
            s = this.getIdentifier();
        }
        else if ("0123456789.".indexOf(char1) >= 0) {
            s = this.getNumber();
        }
        else if ("(".indexOf(char1) >= 0) {
            s = "(";
        }
        else if (")".indexOf(char1) >= 0) {
            s = ")";
        }
        else {
            ParseException.throwParseException("What is this? " + s, this.pos);
        }
        final Node node = new Node(s, this.pos);
        this.pos += s.length();
        return node;
    }
    
    private void skipWhiteSpace() {
        while (",\n\t\r ".indexOf(this.inputString.charAt(this.pos)) >= 0) {
            ++this.pos;
        }
    }
    
    private String getOperator() {
        String s = String.valueOf(this.inputString.charAt(this.pos));
        for (int n = this.pos + 1; "+-*/^<>=&|!?:".indexOf(this.inputString.charAt(n)) >= 0 && "-!".indexOf(this.inputString.charAt(n)) < 0; ++n) {
            s = String.valueOf(s) + this.inputString.charAt(n);
        }
        return s;
    }
    
    private String getIdentifier() {
        String s = String.valueOf(this.inputString.charAt(this.pos));
        for (int n = this.pos + 1; "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".indexOf(this.inputString.charAt(n)) >= 0; ++n) {
            s = String.valueOf(s) + this.inputString.charAt(n);
        }
        return s;
    }
    
    private String getNumber() {
        String string = "";
        for (int pos = this.pos; "0123456789.".indexOf(this.inputString.charAt(pos)) >= 0; ++pos) {
            string = String.valueOf(string) + this.inputString.charAt(pos);
        }
        return string;
    }
    
    private void insertImpliedMultiplication() throws ParseException {
        for (int n = 0; !((Node)this.nodeList.elementAt(n)).nodeToken.equals("#"); ++n) {
            final Node node = this.nodeList.elementAt(n);
            if (node.nodeType.equals("NUMBER") || node.nodeType.equals("CONSTANT") || node.nodeType.equals("VARIABLE") || node.nodeType.equals("FCN0") || node.nodeType.equals("RPAREN")) {
                final Node node2 = this.nodeList.elementAt(n + 1);
                if (!node2.nodeType.equals("OPERATOR") && ")#".indexOf(node2.nodeToken) < 0) {
                    final Node node3 = new Node("*", node2.nodePosition);
                    node3.nodeType = "OPERATOR";
                    this.nodeList.insertElementAt(node3, n + 1);
                }
            }
        }
    }
}
