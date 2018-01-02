// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.functions;

public class StandardFunction extends MathSymbol
{
    public static final int SIN = 1;
    public static final int COS = 2;
    public static final int TAN = 3;
    public static final int COT = 4;
    public static final int SEC = 5;
    public static final int CSC = 6;
    public static final int ARCSIN = 7;
    public static final int ARCCOS = 8;
    public static final int ARCTAN = 9;
    public static final int EXP = 10;
    public static final int LN = 11;
    public static final int LOG2 = 12;
    public static final int LOG10 = 13;
    public static final int ABS = 14;
    public static final int SQRT = 15;
    public static final int ROUND = 16;
    public static final int TRUNC = 17;
    public static final int FLOOR = 18;
    public static final int CEILING = 19;
    public static final int CUBERT = 20;
    private static final int functionCount = 20;
    private static StandardFunction[] functions;
    private int opcode;
    private long casecode;
    
    public static StandardFunction function(final int n) {
        if (n <= 0 || n > 20) {
            throw new IllegalArgumentException("Attempt to use an unknown standard function.");
        }
        if (StandardFunction.functions == null) {
            (StandardFunction.functions = new StandardFunction[21])[1] = new StandardFunction("sin", 1);
            StandardFunction.functions[2] = new StandardFunction("cos", 2);
            StandardFunction.functions[3] = new StandardFunction("tan", 3);
            StandardFunction.functions[4] = new StandardFunction("cot", 4);
            StandardFunction.functions[5] = new StandardFunction("sec", 5);
            StandardFunction.functions[6] = new StandardFunction("csc", 6);
            StandardFunction.functions[7] = new StandardFunction("arcsin", 7);
            StandardFunction.functions[8] = new StandardFunction("arccos", 8);
            StandardFunction.functions[9] = new StandardFunction("arctan", 9);
            StandardFunction.functions[10] = new StandardFunction("exp", 10);
            StandardFunction.functions[11] = new StandardFunction("ln", 11);
            StandardFunction.functions[12] = new StandardFunction("log2", 12);
            StandardFunction.functions[13] = new StandardFunction("log10", 13);
            StandardFunction.functions[14] = new StandardFunction("abs", 14);
            StandardFunction.functions[15] = new StandardFunction("sqrt", 15);
            StandardFunction.functions[16] = new StandardFunction("round", 16);
            StandardFunction.functions[17] = new StandardFunction("trunc", 17);
            StandardFunction.functions[18] = new StandardFunction("floor", 18);
            StandardFunction.functions[19] = new StandardFunction("ceiling", 19);
            StandardFunction.functions[20] = new StandardFunction("cubert", 20);
        }
        return StandardFunction.functions[n];
    }
    
    public static int standardFunctionCount() {
        return 20;
    }
    
    int getOpcode() {
        return this.opcode;
    }
    
    private StandardFunction(final String name, final int opcode) {
        if (opcode <= 0 || opcode > 20) {
            throw new IllegalArgumentException("Attempt to create an unknown standard function.");
        }
        this.opcode = opcode;
        this.setName(name);
    }
    
    public double eval(final double n, final double n2) {
        throw new IllegalArgumentException("Wrong number of parameters supplied for standard function.");
    }
    
    public double eval(final double[] array) {
        if (array == null || array.length != 1) {
            throw new IllegalArgumentException("Wrong number of parameters supplied for standard function.");
        }
        return this.eval(array[0]);
    }
    
    public Expression derivative(final Expression expression, final Variable variable) {
        if (expression.isLocallyConstantWRT(variable)) {
            return new ConstantNode(0.0);
        }
        Expression expression2 = null;
        switch (this.opcode) {
            case 1: {
                expression2 = new StandardFunctionNode(2, expression.copy());
                break;
            }
            case 2: {
                expression2 = new UnaryNode(1, new StandardFunctionNode(1, expression.copy()));
                break;
            }
            case 3: {
                expression2 = new BinaryNode(5, new StandardFunctionNode(5, expression.copy()), new ConstantNode(2.0));
                break;
            }
            case 4: {
                expression2 = new UnaryNode(1, new BinaryNode(5, new StandardFunctionNode(6, expression.copy()), new ConstantNode(2.0)));
                break;
            }
            case 5: {
                expression2 = new BinaryNode(3, new StandardFunctionNode(5, expression.copy()), new StandardFunctionNode(3, expression.copy()));
                break;
            }
            case 6: {
                expression2 = new UnaryNode(1, new BinaryNode(3, new StandardFunctionNode(6, expression.copy()), new StandardFunctionNode(4, expression.copy())));
                break;
            }
            case 7: {
                expression2 = new BinaryNode(4, new ConstantNode(1.0), new StandardFunctionNode(15, new BinaryNode(2, new ConstantNode(1.0), new BinaryNode(5, expression.copy(), new ConstantNode(2.0)))));
                break;
            }
            case 8: {
                expression2 = new BinaryNode(4, new ConstantNode(-1.0), new StandardFunctionNode(15, new BinaryNode(2, new ConstantNode(1.0), new BinaryNode(5, expression.copy(), new ConstantNode(2.0)))));
                break;
            }
            case 9: {
                expression2 = new BinaryNode(4, new ConstantNode(1.0), new BinaryNode(1, new ConstantNode(1.0), new BinaryNode(5, expression.copy(), new ConstantNode(2.0))));
                break;
            }
            case 10: {
                expression2 = new StandardFunctionNode(10, expression.copy());
                break;
            }
            case 11: {
                expression2 = new ConditionalExpression(new BinaryNode(11, expression.copy(), new ConstantNode(0.0)), new BinaryNode(4, new ConstantNode(1.0), expression.copy()));
                break;
            }
            case 12: {
                expression2 = new ConditionalExpression(new BinaryNode(11, expression.copy(), new ConstantNode(0.0)), new BinaryNode(4, new ConstantNode(1.0), new BinaryNode(3, new StandardFunctionNode(11, new ConstantNode(2.0)), expression.copy())));
                break;
            }
            case 13: {
                expression2 = new ConditionalExpression(new BinaryNode(11, expression.copy(), new ConstantNode(0.0)), new BinaryNode(4, new ConstantNode(1.0), new BinaryNode(3, new StandardFunctionNode(11, new ConstantNode(10.0)), expression.copy())));
                break;
            }
            case 14: {
                expression2 = new BinaryNode(4, new StandardFunctionNode(14, expression.copy()), expression.copy());
                break;
            }
            case 15: {
                expression2 = new BinaryNode(4, new ConstantNode(1.0), new BinaryNode(3, new ConstantNode(2.0), new StandardFunctionNode(15, expression.copy())));
                break;
            }
            case 16: {
                expression2 = null;
                break;
            }
            case 17: {
                expression2 = null;
                break;
            }
            case 18: {
                expression2 = null;
                break;
            }
            case 19: {
                expression2 = null;
                break;
            }
            case 20: {
                expression2 = new BinaryNode(4, new ConstantNode(1.0), new BinaryNode(3, new ConstantNode(3.0), new StandardFunctionNode(20, new BinaryNode(5, expression.copy(), new ConstantNode(2.0)))));
                break;
            }
        }
        if (expression2 == null) {
            return new ConstantNode(0.0);
        }
        final Expression derivative = expression.derivative(variable);
        if (derivative instanceof ConstantNode && ((ConstantNode)derivative).value() == 1.0) {
            return expression2;
        }
        return new BinaryNode(3, expression2, derivative);
    }
    
    public double eval(final double n) {
        switch (this.opcode) {
            case 1: {
                this.casecode = 0L;
                return Math.sin(n);
            }
            case 2: {
                this.casecode = 0L;
                return Math.cos(n);
            }
            case 3: {
                this.casecode = (long)Math.floor((n - 1.5707963267948966) / 3.141592653589793);
                return Math.tan(n);
            }
            case 4: {
                this.casecode = (long)Math.floor(n / 3.141592653589793);
                return Math.cos(n) / Math.sin(n);
            }
            case 5: {
                this.casecode = (long)Math.floor((n - 1.5707963267948966) / 3.141592653589793);
                return 1.0 / Math.cos(n);
            }
            case 6: {
                this.casecode = (long)Math.floor(n / 3.141592653589793);
                return 1.0 / Math.sin(n);
            }
            case 7: {
                this.casecode = 0L;
                return Math.asin(n);
            }
            case 8: {
                this.casecode = 0L;
                return Math.acos(n);
            }
            case 9: {
                this.casecode = 0L;
                return Math.atan(n);
            }
            case 10: {
                this.casecode = 0L;
                return Math.exp(n);
            }
            case 11: {
                this.casecode = 0L;
                return Math.log(n);
            }
            case 12: {
                this.casecode = 0L;
                return Math.log(n) / Math.log(2.0);
            }
            case 13: {
                this.casecode = 0L;
                return Math.log(n) / Math.log(10.0);
            }
            case 14: {
                this.casecode = ((n > 0.0) ? 1 : -1);
                return Math.abs(n);
            }
            case 15: {
                this.casecode = 0L;
                return Math.sqrt(n);
            }
            case 16: {
                final double rint = Math.rint(n);
                this.casecode = (long)rint;
                return rint;
            }
            case 17: {
                final double n2 = (n >= 0.0) ? Math.floor(n) : Math.ceil(n);
                this.casecode = (long)n2;
                return n2;
            }
            case 18: {
                final double floor = Math.floor(n);
                this.casecode = (long)floor;
                return floor;
            }
            case 19: {
                final double ceil = Math.ceil(n);
                this.casecode = (long)ceil;
                return ceil;
            }
            case 20: {
                this.casecode = ((n > 0.0) ? 1 : -1);
                return (n >= 0.0) ? Math.pow(n, 0.3333333333333333) : (-Math.pow(-n, 0.3333333333333333));
            }
            default: {
                return 0.0;
            }
        }
    }
    
    long getCaseCode() {
        return this.casecode;
    }
}
