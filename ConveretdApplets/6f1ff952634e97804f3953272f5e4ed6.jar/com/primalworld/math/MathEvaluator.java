// 
// Decompiled by Procyon v0.5.30
// 

package com.primalworld.math;

import java.util.HashMap;

public class MathEvaluator
{
    protected static Operator[] operators;
    private Node node;
    private String expression;
    private HashMap variables;
    
    public MathEvaluator() {
        this.node = null;
        this.expression = null;
        this.variables = new HashMap();
        this.init();
    }
    
    public MathEvaluator(final String expression) {
        this.node = null;
        this.expression = null;
        this.variables = new HashMap();
        this.init();
        this.setExpression(expression);
    }
    
    private void init() {
        if (MathEvaluator.operators == null) {
            this.initializeOperators();
        }
    }
    
    public void addVariable(final String s, final double n) {
        this.addVariable(s, new Double(n));
    }
    
    public void addVariable(final String s, final Double n) {
        this.variables.put(s, n);
    }
    
    public void setExpression(final String expression) {
        this.expression = expression;
    }
    
    public void reset() {
        this.node = null;
        this.expression = null;
        this.variables = new HashMap();
    }
    
    public void trace() {
        try {
            (this.node = new Node(this.expression)).trace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Double getValue() {
        if (this.expression == null) {
            return null;
        }
        try {
            this.node = new Node(this.expression);
            return evaluate(this.node);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private static Double evaluate(final Node node) {
        if (node.hasOperator() && node.hasChild()) {
            if (node.getOperator().getType() == 1) {
                node.setValue(evaluateExpression(node.getOperator(), evaluate(node.getLeft()), null));
            }
            else if (node.getOperator().getType() == 2) {
                node.setValue(evaluateExpression(node.getOperator(), evaluate(node.getLeft()), evaluate(node.getRight())));
            }
        }
        return node.getValue();
    }
    
    private static Double evaluateExpression(final Operator operator, final Double n, final Double n2) {
        final String operator2 = operator.getOperator();
        Double n3 = null;
        if ("+".equals(operator2)) {
            n3 = new Double(n + n2);
        }
        else if ("-".equals(operator2)) {
            n3 = new Double(n - n2);
        }
        else if ("*".equals(operator2)) {
            n3 = new Double(n * n2);
        }
        else if ("/".equals(operator2)) {
            n3 = new Double(n / n2);
        }
        else if ("^".equals(operator2)) {
            n3 = new Double(Math.pow(n, n2));
        }
        else if ("%".equals(operator2)) {
            n3 = new Double(n % n2);
        }
        else if ("&".equals(operator2)) {
            n3 = new Double(n + n2);
        }
        else if ("|".equals(operator2)) {
            n3 = new Double(n + n2);
        }
        else if ("cos".equals(operator2)) {
            n3 = new Double(Math.cos(n));
        }
        else if ("sin".equals(operator2)) {
            n3 = new Double(Math.sin(n));
        }
        else if ("tan".equals(operator2)) {
            n3 = new Double(Math.tan(n));
        }
        else if ("acos".equals(operator2)) {
            n3 = new Double(Math.acos(n));
        }
        else if ("asin".equals(operator2)) {
            n3 = new Double(Math.asin(n));
        }
        else if ("atan".equals(operator2)) {
            n3 = new Double(Math.atan(n));
        }
        else if ("sqr".equals(operator2)) {
            n3 = new Double(n * n);
        }
        else if ("sqrt".equals(operator2)) {
            n3 = new Double(Math.sqrt(n));
        }
        else if ("log".equals(operator2)) {
            n3 = new Double(Math.log(n));
        }
        else if ("min".equals(operator2)) {
            n3 = new Double(Math.min(n, n2));
        }
        else if ("max".equals(operator2)) {
            n3 = new Double(Math.max(n, n2));
        }
        else if ("exp".equals(operator2)) {
            n3 = new Double(Math.exp(n));
        }
        else if ("floor".equals(operator2)) {
            n3 = new Double(Math.floor(n));
        }
        else if ("ceil".equals(operator2)) {
            n3 = new Double(Math.ceil(n));
        }
        else if ("abs".equals(operator2)) {
            n3 = new Double(Math.abs(n));
        }
        else if ("neg".equals(operator2)) {
            n3 = new Double(-n);
        }
        else if ("rnd".equals(operator2)) {
            n3 = new Double(Math.random() * n);
        }
        return n3;
    }
    
    private void initializeOperators() {
        (MathEvaluator.operators = new Operator[25])[0] = new Operator("+", 2, 0);
        MathEvaluator.operators[1] = new Operator("-", 2, 0);
        MathEvaluator.operators[2] = new Operator("*", 2, 10);
        MathEvaluator.operators[3] = new Operator("/", 2, 10);
        MathEvaluator.operators[4] = new Operator("^", 2, 10);
        MathEvaluator.operators[5] = new Operator("%", 2, 10);
        MathEvaluator.operators[6] = new Operator("&", 2, 0);
        MathEvaluator.operators[7] = new Operator("|", 2, 0);
        MathEvaluator.operators[8] = new Operator("cos", 1, 20);
        MathEvaluator.operators[9] = new Operator("sin", 1, 20);
        MathEvaluator.operators[10] = new Operator("tan", 1, 20);
        MathEvaluator.operators[11] = new Operator("acos", 1, 20);
        MathEvaluator.operators[12] = new Operator("asin", 1, 20);
        MathEvaluator.operators[13] = new Operator("atan", 1, 20);
        MathEvaluator.operators[14] = new Operator("sqrt", 1, 20);
        MathEvaluator.operators[15] = new Operator("sqr", 1, 20);
        MathEvaluator.operators[16] = new Operator("log", 1, 20);
        MathEvaluator.operators[17] = new Operator("min", 2, 0);
        MathEvaluator.operators[18] = new Operator("max", 2, 0);
        MathEvaluator.operators[19] = new Operator("exp", 1, 20);
        MathEvaluator.operators[20] = new Operator("floor", 1, 20);
        MathEvaluator.operators[21] = new Operator("ceil", 1, 20);
        MathEvaluator.operators[22] = new Operator("abs", 1, 20);
        MathEvaluator.operators[23] = new Operator("neg", 1, 20);
        MathEvaluator.operators[24] = new Operator("rnd", 1, 20);
    }
    
    public Double getVariable(final String s) {
        return this.variables.get(s);
    }
    
    private Double getDouble(final String s) {
        if (s == null) {
            return null;
        }
        Double n;
        try {
            n = new Double(Double.parseDouble(s));
        }
        catch (Exception ex) {
            return this.getVariable(s);
        }
        return n;
    }
    
    protected Operator[] getOperators() {
        return MathEvaluator.operators;
    }
    
    protected static void _D(final String s) {
        System.err.println(s);
    }
    
    static {
        MathEvaluator.operators = null;
    }
    
    protected class Operator
    {
        private String op;
        private int type;
        private int priority;
        
        public Operator(final String op, final int type, final int priority) {
            this.op = op;
            this.type = type;
            this.priority = priority;
        }
        
        public String getOperator() {
            return this.op;
        }
        
        public void setOperator(final String op) {
            this.op = op;
        }
        
        public int getType() {
            return this.type;
        }
        
        public int getPriority() {
            return this.priority;
        }
    }
    
    protected class Node
    {
        public String nString;
        public Operator nOperator;
        public Node nLeft;
        public Node nRight;
        public Node nParent;
        public int nLevel;
        public Double nValue;
        
        public Node(final String s) throws Exception {
            this.nString = null;
            this.nOperator = null;
            this.nLeft = null;
            this.nRight = null;
            this.nParent = null;
            this.nLevel = 0;
            this.nValue = null;
            this.init(null, s, 0);
        }
        
        public Node(final Node node, final String s, final int n) throws Exception {
            this.nString = null;
            this.nOperator = null;
            this.nLeft = null;
            this.nRight = null;
            this.nParent = null;
            this.nLevel = 0;
            this.nValue = null;
            this.init(node, s, n);
        }
        
        private void init(final Node nParent, String nString, final int nLevel) throws Exception {
            nString = this.removeIllegalCharacters(nString);
            nString = this.removeBrackets(nString);
            nString = this.addZero(nString);
            if (this.checkBrackets(nString) != 0) {
                throw new Exception("Wrong number of brackets in [" + nString + "]");
            }
            this.nParent = nParent;
            this.nString = nString;
            this.nValue = MathEvaluator.this.getDouble(nString);
            this.nLevel = nLevel;
            final int length = nString.length();
            int n = 0;
            int n2 = 0;
            for (int i = 0; i < length; ++i) {
                if (nString.charAt(i) == '(') {
                    ++n;
                }
                else if (nString.charAt(i) == ')') {
                    --n;
                }
                else if (n == 0) {
                    final Operator operator = this.getOperator(this.nString, i);
                    if (operator != null && (this.nOperator == null || this.nOperator.getPriority() >= operator.getPriority())) {
                        this.nOperator = operator;
                        n2 = i;
                    }
                }
            }
            if (this.nOperator != null) {
                if (n2 == 0 && this.nOperator.getType() == 1) {
                    if (this.checkBrackets(nString.substring(this.nOperator.getOperator().length())) == 0) {
                        this.nLeft = new Node(this, nString.substring(this.nOperator.getOperator().length()), this.nLevel + 1);
                        this.nRight = null;
                        return;
                    }
                    throw new Exception("Error during parsing... missing brackets in [" + nString + "]");
                }
                else if (n2 > 0 && this.nOperator.getType() == 2) {
                    this.nOperator = this.nOperator;
                    this.nLeft = new Node(this, nString.substring(0, n2), this.nLevel + 1);
                    this.nRight = new Node(this, nString.substring(n2 + this.nOperator.getOperator().length()), this.nLevel + 1);
                }
            }
        }
        
        private Operator getOperator(final String s, final int n) {
            final Operator[] operators = MathEvaluator.this.getOperators();
            final String nextWord = this.getNextWord(s.substring(n));
            for (int i = 0; i < operators.length; ++i) {
                if (nextWord.startsWith(operators[i].getOperator())) {
                    return operators[i];
                }
            }
            return null;
        }
        
        private String getNextWord(final String s) {
            for (int length = s.length(), i = 1; i < length; ++i) {
                final char char1 = s.charAt(i);
                if ((char1 > 'z' || char1 < 'a') && (char1 > '9' || char1 < '0')) {
                    return s.substring(0, i);
                }
            }
            return s;
        }
        
        protected int checkBrackets(final String s) {
            final int length = s.length();
            int n = 0;
            for (int i = 0; i < length; ++i) {
                if (s.charAt(i) == '(' && n >= 0) {
                    ++n;
                }
                else if (s.charAt(i) == ')') {
                    --n;
                }
            }
            return n;
        }
        
        protected String addZero(final String s) {
            if (s.startsWith("+") || s.startsWith("-")) {
                for (int length = s.length(), i = 0; i < length; ++i) {
                    if (this.getOperator(s, i) != null) {
                        return "0" + s;
                    }
                }
            }
            return s;
        }
        
        public void trace() {
            this._D(((this.getOperator() == null) ? " " : this.getOperator().getOperator()) + " : " + this.getString());
            if (this.hasChild()) {
                if (this.hasLeft()) {
                    this.getLeft().trace();
                }
                if (this.hasRight()) {
                    this.getRight().trace();
                }
            }
        }
        
        protected boolean hasChild() {
            return this.nLeft != null || this.nRight != null;
        }
        
        protected boolean hasOperator() {
            return this.nOperator != null;
        }
        
        protected boolean hasLeft() {
            return this.nLeft != null;
        }
        
        protected Node getLeft() {
            return this.nLeft;
        }
        
        protected boolean hasRight() {
            return this.nRight != null;
        }
        
        protected Node getRight() {
            return this.nRight;
        }
        
        protected Operator getOperator() {
            return this.nOperator;
        }
        
        protected int getLevel() {
            return this.nLevel;
        }
        
        protected Double getValue() {
            return this.nValue;
        }
        
        protected void setValue(final Double nValue) {
            this.nValue = nValue;
        }
        
        protected String getString() {
            return this.nString;
        }
        
        public String removeBrackets(final String s) {
            String substring = s;
            if (s.length() > 2 && substring.startsWith("(") && substring.endsWith(")") && this.checkBrackets(s.substring(1, s.length() - 1)) == 0) {
                substring = substring.substring(1, substring.length() - 1);
            }
            if (substring != s) {
                return this.removeBrackets(substring);
            }
            return substring;
        }
        
        public String removeIllegalCharacters(final String s) {
            final char[] array = { ' ' };
            String string = s;
            for (int i = 0; i < array.length; ++i) {
                for (int j = string.lastIndexOf(array[i], string.length()); j != -1; j = string.lastIndexOf(array[i], s.length())) {
                    final String s2 = string;
                    string = s2.substring(0, j) + s2.substring(j + 1);
                }
            }
            return string;
        }
        
        protected void _D(final String s) {
            String string = "";
            for (int i = 0; i < this.nLevel; ++i) {
                string += "  ";
            }
            System.out.println(string + "|" + s);
        }
    }
}
