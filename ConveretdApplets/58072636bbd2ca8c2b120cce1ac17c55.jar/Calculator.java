// 
// Decompiled by Procyon v0.5.30
// 

public class Calculator
{
    public static String[] BASIC_OPERATORS;
    public static final String[] FUNCTION_NAMES;
    public static final String[] ANGLE_TYPES;
    private boolean reset;
    private boolean cleared;
    private boolean evaluated;
    private double answer;
    private short angleType;
    private String errorMessage;
    private static double[] memory;
    private Chain mainChain;
    
    public Calculator() {
        this.reset = true;
        this.cleared = true;
        this.evaluated = false;
        this.answer = 0.0;
        this.angleType = 0;
        this.errorMessage = null;
        Calculator.memory = new double[10];
        this.clearMemory();
    }
    
    public void calculate(String format) {
        this.errorMessage = null;
        format = this.format(format);
        this.mainChain = new Chain(format, this);
        final String collapseChain = this.collapseChain(this.mainChain);
        try {
            this.answer = new Double(collapseChain);
        }
        catch (Exception ex) {
            this.answer = Double.NaN;
            this.errorMessage = collapseChain;
        }
        this.answer = this.roundTrig(this.answer);
        this.evaluated = true;
    }
    
    public double[][] graph(final GraphArea graphArea, String format) {
        final double[][] array = new double[8001][2];
        format = this.format(format);
        this.mainChain = new Chain(format, this);
        int n = 1;
        final double increment = graphArea.getIncrement();
        final int pixelsPerUnit = graphArea.getPixelsPerUnit();
        array[0][0] = 0.0;
        array[0][1] = 0.0;
        for (double n2 = -20.0; n2 < 20.0; n2 += increment) {
            final Chain chain = new Chain(this.mainChain);
            chain.assignValToVar(new Double(n2).toString());
            array[n][0] = n2 * pixelsPerUnit;
            array[n][1] = new Double(this.collapseChain(chain)) * pixelsPerUnit;
            ++n;
        }
        array[0][0] = n;
        return array;
    }
    
    public void clearMemory() {
        for (int i = 0; i < 10; ++i) {
            Calculator.memory[i] = 0.0;
        }
    }
    
    public void setReset(final boolean reset) {
        if (reset) {
            this.answer = 0.0;
            this.clearMemory();
        }
        this.reset = reset;
    }
    
    public void setCleared(final boolean cleared) {
        this.cleared = cleared;
    }
    
    public void setEvaluated(final boolean evaluated) {
        this.evaluated = evaluated;
    }
    
    public void setAngleType(final short angleType) {
        this.angleType = angleType;
    }
    
    public void setMemorySlot(final int n) {
        Calculator.memory[n] = this.answer;
    }
    
    public void clearMemorySlot(final int n) {
        Calculator.memory[n] = 0.0;
    }
    
    public boolean isReset() {
        return this.reset;
    }
    
    public boolean isCleared() {
        return this.cleared;
    }
    
    public boolean isEvaluated() {
        return this.evaluated;
    }
    
    public double getAnswer() {
        return this.answer;
    }
    
    public String getAnswerSTR() {
        return new Double(this.answer).toString();
    }
    
    public String getErrorMessage() {
        return this.errorMessage;
    }
    
    public double getMemory(final int n) {
        return Calculator.memory[n];
    }
    
    public double[] getAllMemory() {
        return Calculator.memory;
    }
    
    private String format(String s) {
        s = Formatter.trim(s);
        s = "&" + s + "&";
        s = Formatter.replaceAll(s, "*", "x");
        s = Formatter.replaceAll(s, "mod", "%");
        s = s.toLowerCase();
        s = Formatter.replaceAll(s, "e", "E");
        s = Formatter.replaceAll(s, "E^", "e@@");
        s = Formatter.replaceAll(s, "pi", "#");
        s = Formatter.replaceAll(s, "asin", "asi");
        s = Formatter.replaceAll(s, "acos", "aco");
        s = Formatter.replaceAll(s, "atan", "ata");
        s = Formatter.replaceAll(s, "ln", "ln@");
        s = Formatter.replaceAll(s, "sqrt", "sqt");
        s = Formatter.replaceAll(s, "ans", "?");
        for (int i = 0; i < Calculator.FUNCTION_NAMES.length; ++i) {
            s = Formatter.replaceFunctionBrackets(s, Calculator.FUNCTION_NAMES[i]);
        }
        return s;
    }
    
    private String collapseChain(final Chain chain) {
        if (chain.getError() != null) {
            return chain.getError().getValue();
        }
        if (chain.isEnding()) {
            return this.evaluateChain(chain);
        }
        final ChainElement nextBranchingElement = chain.getNextBranchingElement();
        final Parameter parameter = nextBranchingElement.getParameter();
        if (parameter.getNextParameter() == null) {
            nextBranchingElement.setValue(this.applyFunction(nextBranchingElement, this.collapseChain(parameter.getChain())).getSignedValue());
        }
        else {
            nextBranchingElement.setValue(this.applyFunction(nextBranchingElement, this.collapseChain(parameter.getChain()), this.collapseChain(parameter.getNextParameter().getChain())).getSignedValue());
        }
        nextBranchingElement.setType((short)0);
        nextBranchingElement.removeParameter();
        return this.collapseChain(chain);
    }
    
    private String evaluateChain(final Chain chain) {
        ChainElement chainElement = null;
        for (int i = 0; i < Calculator.BASIC_OPERATORS.length; ++i) {
            chainElement = chain.getFirst().getNext();
            while (!chainElement.getNext().getValue().equals("&")) {
                final ChainElement next = chainElement.getNext();
                if (next.getValue().equals(Calculator.BASIC_OPERATORS[i])) {
                    chainElement.setType((short)0);
                    chainElement.setValue(this.evaluateBasicOperation(chainElement.getSignedValue(), Calculator.BASIC_OPERATORS[i], next.getNext().getSignedValue()));
                    chainElement.eliminateNegativeSign();
                    if (next.getNext().getNext().getValue().equals("&")) {
                        chainElement.setNext(new ChainElement((short)5, "&"));
                        break;
                    }
                    chainElement.setNext(next.getNext().getNext());
                }
                else {
                    chainElement = next.getNext();
                }
            }
        }
        return chainElement.getSignedValue();
    }
    
    private ChainElement applyFunction(final ChainElement chainElement, final String s) {
        if (chainElement.getType() == 3) {
            return new ChainElement((short)0, s);
        }
        String s2 = null;
        if (chainElement.getValue().equals("ln@")) {
            s2 = new Double(Math.log(new Double(s))).toString();
        }
        if (chainElement.getValue().equals("log")) {
            s2 = new Double(Math.log(new Double(s)) / Math.log(10.0)).toString();
        }
        if (chainElement.getValue().equals("sqt")) {
            s2 = new Double(Math.sqrt(new Double(s))).toString();
        }
        if (chainElement.getValue().equals("rcl")) {
            s2 = new Double(Calculator.memory[new Integer(s)]).toString();
        }
        if (chainElement.getValue().equals("e@@")) {
            s2 = new Double(Math.pow(2.718281828459045, new Double(s))).toString();
        }
        if (chainElement.getValue().equals("sin") || chainElement.getValue().equals("cos") || chainElement.getValue().equals("tan")) {
            final double convertAngleBefore = this.convertAngleBefore(new Double(s));
            double n = 0.0;
            if (chainElement.getValue().equals("sin")) {
                n = Math.sin(convertAngleBefore);
            }
            if (chainElement.getValue().equals("cos")) {
                n = Math.cos(convertAngleBefore);
            }
            if (chainElement.getValue().equals("tan")) {
                n = Math.tan(convertAngleBefore);
            }
            s2 = new Double(n).toString();
        }
        if (chainElement.getValue().equals("asi") || chainElement.getValue().equals("aco") || chainElement.getValue().equals("ata")) {
            double n2 = 0.0;
            if (chainElement.getValue().equals("asi")) {
                n2 = Math.asin(new Double(s));
            }
            if (chainElement.getValue().equals("aco")) {
                n2 = Math.acos(new Double(s));
            }
            if (chainElement.getValue().equals("ata")) {
                n2 = Math.atan(new Double(s));
            }
            s2 = new Double(this.convertAngleAfter(n2)).toString();
        }
        return new ChainElement((short)0, s2);
    }
    
    private ChainElement applyFunction(final ChainElement chainElement, final String s, final String s2) {
        double n = 0.0;
        if (chainElement.getValue().equals("npr")) {
            n = this.nPr((int)(Object)new Double(s.toString()), (int)(Object)new Double(s2.toString()));
        }
        if (chainElement.getValue().equals("ncr")) {
            n = this.nCr(new Double(s.toString()), (int)(Object)new Double(s2.toString()));
        }
        return new ChainElement((short)0, new Double(n).toString());
    }
    
    private String evaluateBasicOperation(final String s, final String s2, final String s3) {
        if (s3 == null) {
            this.errorMessage = "Can't terminate expression with a basic operator";
            return new Double(Double.NaN).toString();
        }
        if (s2.equals("^")) {
            return this.exponate(s, s3);
        }
        if (s2.equals("/")) {
            return this.divide(s, s3);
        }
        if (s2.equals("%")) {
            return this.mod(s, s3);
        }
        if (s2.equals("x")) {
            return this.multiply(s, s3);
        }
        if (s2.equals("+")) {
            return this.add(s, s3);
        }
        if (s2.equals("-")) {
            return this.substract(s, s3);
        }
        return null;
    }
    
    private String exponate(final String s, final String s2) {
        return new Double(Math.pow(new Double(s), new Double(s2))).toString();
    }
    
    private String divide(final String s, final String s2) {
        return new Double(new Double(s) / new Double(s2)).toString();
    }
    
    private String mod(final String s, final String s2) {
        return new Double(new Double(s) % new Double(s2)).toString();
    }
    
    private String multiply(final String s, final String s2) {
        return new Double(new Double(s) * new Double(s2)).toString();
    }
    
    private String add(final String s, final String s2) {
        return new Double(new Double(s) + new Double(s2)).toString();
    }
    
    private String substract(final String s, final String s2) {
        return new Double(new Double(s) - new Double(s2)).toString();
    }
    
    private double fact(final int n) {
        if (n == 0) {
            return 1.0;
        }
        return n * this.fact(n - 1);
    }
    
    private double nPr(final int n, final int n2) {
        return this.fact(n) / this.fact(n - n2);
    }
    
    private double nCr(final double n, final int n2) {
        double n3 = n;
        for (int i = 1; i <= n2 - 1; ++i) {
            n3 *= n - i;
        }
        return n3 / this.fact(n2);
    }
    
    private double convertAngleBefore(final double n) {
        if (Calculator.ANGLE_TYPES[this.angleType].equals("rad")) {
            return n;
        }
        return Math.toRadians(n);
    }
    
    private double convertAngleAfter(final double n) {
        if (Calculator.ANGLE_TYPES[this.angleType].equals("rad")) {
            return n;
        }
        return Math.toDegrees(n);
    }
    
    private double roundTrig(final double n) {
        return Math.rint(n * 1.0E14) / 1.0E14;
    }
    
    static {
        Calculator.BASIC_OPERATORS = new String[] { "^", "/", "%", "x", "-", "+" };
        FUNCTION_NAMES = new String[] { "log", "ln@", "npr", "ncr", "sin", "cos", "tan", "e@@", "asi", "aco", "ata", "sqt", "rcl" };
        ANGLE_TYPES = new String[] { "rad", "deg" };
    }
}
