// 
// Decompiled by Procyon v0.5.30
// 

class Chain
{
    private final char ERR = '~';
    private ChainElement start;
    private String expr;
    private Calculator calculator;
    
    public Chain(final String expr, final Calculator calculator) {
        this.expr = expr;
        this.calculator = calculator;
        this.start = new ChainElement((short)5, "&");
        this.makeChain(expr);
        this.cleanChain();
    }
    
    public Chain(final Chain chain) {
        this.expr = "";
        this.calculator = null;
        this.start = new ChainElement(chain.getFirst());
        for (ChainElement chainElement = this.start, chainElement2 = chain.getFirst(); chainElement2 != null; chainElement2 = chainElement2.getNext(), chainElement = chainElement.getNext()) {
            if (chainElement2.getNext() != null) {
                chainElement.setNext(new ChainElement(chainElement2.getNext()));
            }
        }
    }
    
    public ChainElement getFirst() {
        return this.start;
    }
    
    public boolean isEnding() {
        for (ChainElement chainElement = this.start; chainElement != null; chainElement = chainElement.getNext()) {
            if (chainElement.hasBranch()) {
                return false;
            }
        }
        return true;
    }
    
    public ChainElement getError() {
        for (ChainElement chainElement = this.start; chainElement != null; chainElement = chainElement.getNext()) {
            if (chainElement.isError()) {
                return chainElement;
            }
        }
        return null;
    }
    
    public ChainElement getNextBranchingElement() {
        for (ChainElement chainElement = this.start; chainElement.getNext() != null; chainElement = chainElement.getNext()) {
            if (chainElement.getParameter() != null) {
                return chainElement;
            }
        }
        return new ChainElement((short)6, "unexpected end to expression.");
    }
    
    public String toString() {
        ChainElement chainElement = this.start;
        String string = "";
        while (chainElement != null) {
            string += chainElement.toString();
            chainElement = chainElement.getNext();
        }
        return string;
    }
    
    public void assignValToVar(final String value) {
        for (ChainElement chainElement = this.getFirst().getNext(); chainElement.getType() != 5 && chainElement != null; chainElement = chainElement.getNext()) {
            if (chainElement.getType() == 1) {
                chainElement.setType((short)0);
                chainElement.setValue(value);
            }
            if (chainElement.hasBranch()) {
                chainElement.getParameter().getChain().assignValToVar(value);
            }
        }
    }
    
    private void makeChain(final String s) {
        int i = 1;
        ChainElement start = this.start;
        if (s.length() == 0) {
            return;
        }
        while (i < s.length()) {
            int j = 1;
            boolean b = false;
            final Chain[] array = new Chain[5];
            short n;
            String s2;
            if (this.isNumeric(s.charAt(i))) {
                n = 0;
                s2 = new Character(s.charAt(i)).toString();
                ++i;
                while (this.isNumeric(s.charAt(i)) || this.isNumeric(s.charAt(i - 1), s.charAt(i))) {
                    s2 += new Character(s.charAt(i)).toString();
                    ++i;
                }
            }
            else if (s.charAt(i) == '#') {
                n = 0;
                s2 = new Double(3.141592653589793).toString();
                ++i;
            }
            else if (s.charAt(i) == '?') {
                n = 0;
                s2 = new Double(this.calculator.getAnswer()).toString();
                ++i;
            }
            else if (s.charAt(i) == '$') {
                n = 1;
                s2 = "var";
                ++i;
            }
            else if (this.isOperator(s.charAt(i))) {
                n = 2;
                s2 = new Character(s.charAt(i)).toString();
                ++i;
            }
            else if (this.isOpenP(s.charAt(i))) {
                n = 3;
                s2 = "(";
                final String inside = this.getInside(s.substring(i, s.length()), '(', ')');
                i = i + inside.length() + 2;
                array[0] = new Chain("&" + inside + "&", this.calculator);
                b = true;
            }
            else if (s.length() - i > 3 && this.isFunction(s.substring(i, i + 3))) {
                final int[] array2 = new int[5];
                array2[0] = -1;
                n = 4;
                s2 = s.substring(i, i + 3);
                final String inside2 = this.getInside(s.substring(i + 3, s.length()), '{', '}');
                while (j < 5) {
                    array2[j] = this.getIndexOfComma(inside2, array2[j - 1] + 1);
                    if (array2[j] == -1) {
                        break;
                    }
                    array[j - 1] = new Chain("&" + inside2.substring(array2[j - 1] + 1, array2[j]) + "&", this.calculator);
                    ++j;
                }
                array[j - 1] = new Chain("&" + inside2.substring(array2[j - 1] + 1, inside2.length()) + "&", this.calculator);
                i = i + inside2.length() + 2 + 3;
                b = true;
            }
            else if (s.charAt(i) == '&') {
                n = 5;
                s2 = "&";
                ++i;
            }
            else {
                final char char1 = s.charAt(i);
                this.getClass();
                if (char1 == '~') {
                    n = 6;
                    s2 = s.substring(1, s.length());
                    i += s.length();
                }
                else {
                    n = 6;
                    s2 = "invalid character " + s.charAt(i) + " in expression.";
                    ++i;
                }
            }
            final ChainElement next = new ChainElement(n, s2);
            if (b) {
                next.setParameter(array[0]);
                Parameter parameter = next.getParameter();
                for (int k = 1; k < j; ++k) {
                    parameter.addNextParameter(array[k]);
                    parameter = parameter.getNextParameter();
                }
            }
            start.setNext(next);
            start = next;
        }
    }
    
    private void cleanChain() {
        ChainElement chainElement = this.start.getNext();
        if (chainElement.getType() == 2) {
            if (chainElement.getValue().equals("-") && chainElement.getNext() != null) {
                this.start.setNext(this.eliminateRepeatingNegatives(chainElement));
                chainElement = this.start.getNext();
            }
            else {
                final ChainElement next = this.start.getNext();
                this.start.setNext(new ChainElement((short)6, "can't start with operator other than -, or can't end with a negative"));
                this.start.getNext().setNext(next);
                chainElement = next;
            }
        }
        while (chainElement.getNext().getType() != 5) {
            ChainElement next2 = chainElement.getNext();
            if (chainElement.getType() == 2 && next2.getType() == 2) {
                if (!next2.getValue().equals("-")) {
                    chainElement.setNext(new ChainElement((short)6, "operator " + chainElement.getValue() + " followed by operator " + next2.getValue() + " is illegal."));
                    next2 = chainElement.getNext();
                }
                else {
                    chainElement.setNext(this.eliminateRepeatingNegatives(next2));
                    next2 = chainElement.getNext();
                }
            }
            if (chainElement.getType() != 2 && next2.getType() != 2) {
                chainElement.setNext(new ChainElement((short)2, "x"));
                chainElement.getNext().setNext(next2);
                next2 = chainElement.getNext();
            }
            chainElement = next2;
            if (chainElement.getNext() == null) {
                break;
            }
        }
        if (chainElement.getType() == 2) {
            final ChainElement next3 = chainElement;
            chainElement.setValue("cannot end an expression with basic operator " + chainElement.getValue());
            chainElement.setType((short)6);
            chainElement.setNext(next3);
        }
    }
    
    private ChainElement eliminateRepeatingNegatives(ChainElement next) {
        int n = 1;
        while (next.getNext().getType() != 5) {
            if (next.getNext().getType() == 0 || next.getNext().getType() == 3 || next.getNext().getType() == 4) {
                if (n % 2 == 1) {
                    next.getNext().setNegative();
                }
                return next.getNext();
            }
            if (next.getNext().getType() != 2) {
                continue;
            }
            if (!next.getNext().getValue().equals("-")) {
                return new ChainElement((short)6, "cannot have operator - followed by operator" + next.getNext().getValue());
            }
            ++n;
            next = next.getNext();
        }
        return new ChainElement((short)6, "cannot end expression with an operator.");
    }
    
    private boolean isOperator(final char c) {
        for (int i = 0; i < Calculator.BASIC_OPERATORS.length; ++i) {
            if (c == Calculator.BASIC_OPERATORS[i].charAt(0)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isNumeric(final char c) {
        if (c == '.' || c == 'E') {
            return true;
        }
        for (int i = 48; i < 58; ++i) {
            if (c == (char)i) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isNumeric(final char c, final char c2) {
        return c == 'E' && c2 == '-';
    }
    
    private boolean isOpenP(final char c) {
        return c == '(';
    }
    
    private boolean isFunction(final String s) {
        for (int i = 0; i < Calculator.FUNCTION_NAMES.length; ++i) {
            if (s.equals(Calculator.FUNCTION_NAMES[i])) {
                return true;
            }
        }
        return false;
    }
    
    private String getInside(final String s, final char c, final char c2) {
        int n = 0;
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == c) {
                ++n;
            }
            else if (s.charAt(i) == c2) {
                --n;
            }
            if (n == 0) {
                if (i == 0 && c == '{') {
                    final StringBuffer sb = new StringBuffer();
                    this.getClass();
                    return sb.append('~').append("empty brackets").toString();
                }
                if (i > 0) {
                    return s.substring(1, i);
                }
                return "";
            }
            else {
                if (n < 0) {
                    final StringBuffer sb2 = new StringBuffer();
                    this.getClass();
                    return sb2.append('~').append("mismatched paranthesis").toString();
                }
                ++i;
            }
        }
        final StringBuffer sb3 = new StringBuffer();
        this.getClass();
        return sb3.append('~').append("unclosed parantheisis").toString();
    }
    
    private int getIndexOfComma(final String s, final int n) {
        s.charAt(0);
        int n2 = 0;
        for (int i = n; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '{') {
                ++n2;
            }
            else if (char1 == '}') {
                --n2;
            }
            if (char1 == ',' && n2 == 0) {
                return i;
            }
        }
        return -1;
    }
}
