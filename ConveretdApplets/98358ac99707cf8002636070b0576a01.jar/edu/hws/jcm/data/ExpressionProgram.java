// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.data;

public class ExpressionProgram implements Expression
{
    public static final int PLUS = -1;
    public static final int MINUS = -2;
    public static final int TIMES = -3;
    public static final int DIVIDE = -4;
    public static final int POWER = -5;
    public static final int EQ = -6;
    public static final int NE = -7;
    public static final int LT = -8;
    public static final int GT = -9;
    public static final int LE = -10;
    public static final int GE = -11;
    public static final int AND = -12;
    public static final int OR = -13;
    public static final int NOT = -14;
    public static final int UNARY_MINUS = -15;
    public static final int FACTORIAL = -16;
    public static final int SIN = -17;
    public static final int COS = -18;
    public static final int TAN = -19;
    public static final int COT = -20;
    public static final int SEC = -21;
    public static final int CSC = -22;
    public static final int ARCSIN = -23;
    public static final int ARCCOS = -24;
    public static final int ARCTAN = -25;
    public static final int ABS = -26;
    public static final int SQRT = -27;
    public static final int EXP = -28;
    public static final int LN = -29;
    public static final int LOG2 = -30;
    public static final int LOG10 = -31;
    public static final int TRUNC = -32;
    public static final int ROUND = -33;
    public static final int FLOOR = -34;
    public static final int CEILING = -35;
    public static final int CUBERT = -36;
    public String sourceString;
    private int[] prog;
    private int progCt;
    private Cases cases;
    private StackOfDouble stack;
    private double[] constant;
    private int constantCt;
    private ExpressionCommand[] command;
    private int commandCt;
    
    public ExpressionProgram() {
        this.prog = new int[1];
        this.stack = new StackOfDouble();
        this.constant = new double[1];
        this.command = new ExpressionCommand[1];
    }
    
    public void addCommandObject(final ExpressionCommand expressionCommand) {
        this.addCommandCode(this.findCommand(expressionCommand) + 1073741823);
    }
    
    public void addConstant(final double n) {
        this.addCommandCode(this.findConstant(n));
    }
    
    public void addCommand(final int n) {
        if (n >= 0 || n < -36) {
            throw new IllegalArgumentException("Internal Error.  Illegal command code.");
        }
        this.addCommandCode(n);
    }
    
    public void trim() {
        if (this.progCt != this.prog.length) {
            final int[] prog = new int[this.progCt];
            System.arraycopy(this.prog, 0, prog, 0, this.progCt);
            this.prog = prog;
        }
        if (this.commandCt != this.command.length) {
            final ExpressionCommand[] command = new ExpressionCommand[this.commandCt];
            System.arraycopy(this.command, 0, command, 0, this.commandCt);
            this.command = command;
        }
        if (this.constantCt != this.constant.length) {
            final double[] constant = new double[this.constantCt];
            System.arraycopy(this.constant, 0, constant, 0, this.constantCt);
            this.constant = constant;
        }
    }
    
    private void addCommandCode(final int n) {
        if (this.progCt == this.prog.length) {
            final int[] prog = new int[this.prog.length * 2];
            System.arraycopy(this.prog, 0, prog, 0, this.progCt);
            this.prog = prog;
        }
        this.prog[this.progCt++] = n;
    }
    
    public synchronized double getVal() {
        this.cases = null;
        return this.basicGetValue();
    }
    
    public synchronized double getValueWithCases(final Cases cases) {
        this.cases = cases;
        final double basicGetValue = this.basicGetValue();
        this.cases = null;
        return basicGetValue;
    }
    
    private double basicGetValue() {
        this.stack.makeEmpty();
        for (int i = 0; i < this.progCt; ++i) {
            final int n = this.prog[i];
            if (n < 0) {
                final double applyCommandCode = this.applyCommandCode(n);
                if (Double.isNaN(applyCommandCode) || Double.isInfinite(applyCommandCode)) {
                    if (this.cases != null) {
                        this.cases.addCase(0);
                    }
                    return Double.NaN;
                }
                this.stack.push(applyCommandCode);
            }
            else if (n < 1073741823) {
                this.stack.push(this.constant[n]);
            }
            else {
                this.command[n - 1073741823].apply(this.stack, this.cases);
            }
        }
        if (this.stack.size() != 1) {
            throw new IllegalArgumentException("Internal Error:  Improper stack state after expression evaluation.");
        }
        final double pop = this.stack.pop();
        if (this.cases != null) {
            this.cases.addCase(Double.isNaN(pop) ? 0 : 1);
        }
        return pop;
    }
    
    private void addCase(final int n) {
        if (this.cases != null) {
            this.cases.addCase(n);
        }
    }
    
    protected double applyCommandCode(final int n) {
        double n2;
        if (n < -13) {
            n2 = this.eval(n, this.stack.pop());
        }
        else {
            n2 = this.eval(n, this.stack.pop(), this.stack.pop());
        }
        return n2;
    }
    
    private double eval(final int n, final double n2) {
        switch (n) {
            case -14: {
                return (n2 == 0.0) ? 1.0 : 0.0;
            }
            case -15: {
                return -n2;
            }
            case -16: {
                return this.factorial(n2);
            }
            case -17: {
                return Math.sin(n2);
            }
            case -18: {
                return Math.cos(n2);
            }
            case -19: {
                this.addCase((int)Math.floor((n2 - 1.5707963267948966) / 3.141592653589793));
                return Math.tan(n2);
            }
            case -20: {
                this.addCase((int)Math.floor(n2 / 3.141592653589793));
                return Math.cos(n2) / Math.sin(n2);
            }
            case -21: {
                this.addCase((int)Math.floor((n2 - 1.5707963267948966) / 3.141592653589793));
                return 1.0 / Math.cos(n2);
            }
            case -22: {
                this.addCase((int)Math.floor(n2 / 3.141592653589793));
                return 1.0 / Math.sin(n2);
            }
            case -23: {
                return Math.asin(n2);
            }
            case -24: {
                return Math.acos(n2);
            }
            case -25: {
                return Math.atan(n2);
            }
            case -26: {
                this.addCase((n2 > 0.0) ? 1 : ((n2 < 0.0) ? -1 : 0));
                return Math.abs(n2);
            }
            case -27: {
                return (n2 < 0.0) ? Double.NaN : Math.sqrt(n2);
            }
            case -28: {
                return Math.exp(n2);
            }
            case -29: {
                return (n2 <= 0.0) ? Double.NaN : Math.log(n2);
            }
            case -30: {
                return (n2 <= 0.0) ? Double.NaN : (Math.log(n2) / Math.log(2.0));
            }
            case -31: {
                return (n2 <= 0.0) ? Double.NaN : (Math.log(n2) / Math.log(10.0));
            }
            case -32: {
                this.addCase((int)n2);
                return (long)n2;
            }
            case -33: {
                this.addCase((int)Math.floor(n2 + 0.5));
                return Math.floor(n2 + 0.5);
            }
            case -34: {
                this.addCase((int)Math.floor(n2));
                return Math.floor(n2);
            }
            case -35: {
                this.addCase((int)Math.floor(n2));
                return Math.ceil(n2);
            }
            case -36: {
                this.addCase((n2 > 0.0) ? 1 : -1);
                return (n2 >= 0.0) ? Math.pow(n2, 0.3333333333333333) : (-Math.pow(-n2, 0.3333333333333333));
            }
            default: {
                return Double.NaN;
            }
        }
    }
    
    private double factorial(final double n) {
        if (n <= -0.5 || n > 170.5) {
            this.addCase(-1);
            return Double.NaN;
        }
        final int n2 = (int)n;
        this.addCase(n2);
        double n3 = 1.0;
        for (int i = 1; i <= n2; ++i) {
            n3 *= i;
        }
        return n3;
    }
    
    private double eval(final int n, final double n2, final double n3) {
        switch (n) {
            case -1: {
                return n3 + n2;
            }
            case -2: {
                return n3 - n2;
            }
            case -3: {
                return n3 * n2;
            }
            case -4: {
                this.addCase((n2 > 0.0) ? 1 : ((n2 < 0.0) ? -1 : 0));
                return n3 / n2;
            }
            case -5: {
                return Math.pow(n3, n2);
            }
            case -6: {
                return (n3 == n2) ? 1.0 : 0.0;
            }
            case -7: {
                return (n3 != n2) ? 1.0 : 0.0;
            }
            case -9: {
                return (n3 > n2) ? 1.0 : 0.0;
            }
            case -8: {
                return (n3 < n2) ? 1.0 : 0.0;
            }
            case -11: {
                return (n3 >= n2) ? 1.0 : 0.0;
            }
            case -10: {
                return (n3 <= n2) ? 1.0 : 0.0;
            }
            case -12: {
                return (n3 != 0.0 && n2 != 0.0) ? 1.0 : 0.0;
            }
            case -13: {
                return (n3 != 0.0 || n2 != 0.0) ? 1.0 : 0.0;
            }
            default: {
                return Double.NaN;
            }
        }
    }
    
    public String toString() {
        if (this.sourceString != null) {
            return this.sourceString;
        }
        final StringBuffer sb = new StringBuffer();
        this.appendOutputString(this.progCt - 1, sb);
        return sb.toString();
    }
    
    public void appendOutputString(final int n, final StringBuffer sb) {
        if (this.prog[n] >= 1073741823) {
            this.command[this.prog[n] - 1073741823].appendOutputString(this, n, sb);
        }
        else if (this.prog[n] >= 0) {
            sb.append(NumUtils.realToString(this.constant[this.prog[n]]));
        }
        else if (this.prog[n] >= -13) {
            final int n2 = n - 1;
            final int n3 = n2 - this.extent(n2);
            if (this.precedence(this.prog[n3]) < this.precedence(this.prog[n]) || (this.prog[n] == -5 && this.precedence(this.prog[n3]) == this.precedence(this.prog[n]))) {
                sb.append('(');
                this.appendOutputString(n3, sb);
                sb.append(')');
            }
            else {
                this.appendOutputString(n3, sb);
            }
            switch (this.prog[n]) {
                case -1: {
                    sb.append(" + ");
                    break;
                }
                case -2: {
                    sb.append(" - ");
                    break;
                }
                case -3: {
                    sb.append("*");
                    break;
                }
                case -4: {
                    sb.append("/");
                    break;
                }
                case -5: {
                    sb.append("^");
                    break;
                }
                case -12: {
                    sb.append(" AND ");
                    break;
                }
                case -13: {
                    sb.append(" OR ");
                    break;
                }
                case -6: {
                    sb.append(" = ");
                    break;
                }
                case -7: {
                    sb.append(" <> ");
                    break;
                }
                case -11: {
                    sb.append(" >= ");
                    break;
                }
                case -10: {
                    sb.append(" <= ");
                    break;
                }
                case -9: {
                    sb.append(" > ");
                    break;
                }
                case -8: {
                    sb.append(" < ");
                    break;
                }
            }
            if (this.prog[n2] == -15 || this.precedence(this.prog[n2]) < this.precedence(this.prog[n]) || ((this.prog[n] == -2 || this.prog[n] == -4) && this.precedence(this.prog[n2]) == this.precedence(this.prog[n]))) {
                sb.append('(');
                this.appendOutputString(n2, sb);
                sb.append(')');
            }
            else {
                this.appendOutputString(n2, sb);
            }
        }
        else if (this.prog[n] <= -17) {
            sb.append(StandardFunction.standardFunctionName(this.prog[n]));
            sb.append('(');
            this.appendOutputString(n - 1, sb);
            sb.append(')');
        }
        else if (this.prog[n] == -15) {
            sb.append('-');
            if (this.precedence(this.prog[n - 1]) <= this.precedence(-15)) {
                sb.append('(');
                this.appendOutputString(n - 1, sb);
                sb.append(')');
            }
            else {
                this.appendOutputString(n - 1, sb);
            }
        }
        else if (this.prog[n] == -14) {
            sb.append("NOT (");
            this.appendOutputString(n - 1, sb);
            sb.append(')');
        }
        else if (this.prog[n] == -16) {
            if (this.prog[n - 1] >= 0 && (this.prog[n - 1] < 1073741823 || this.command[this.prog[n - 1] - 1073741823] instanceof Variable || this.command[this.prog[n - 1] - 1073741823] instanceof Constant)) {
                this.appendOutputString(n - 1, sb);
            }
            else {
                sb.append('(');
                this.appendOutputString(n - 1, sb);
                sb.append(')');
            }
            sb.append('!');
        }
    }
    
    private int precedence(final int n) {
        if (n >= 0) {
            if (n >= 1073741823 && this.command[n - 1073741823] instanceof ConditionalExpression) {
                return 0;
            }
            return 7;
        }
        else {
            switch (n) {
                case -16:
                case -13:
                case -12: {
                    return 1;
                }
                case -11:
                case -10:
                case -9:
                case -8:
                case -7:
                case -6: {
                    return 2;
                }
                case -15:
                case -2:
                case -1: {
                    return 3;
                }
                case -4:
                case -3: {
                    return 4;
                }
                case -5: {
                    return 6;
                }
                default: {
                    return 7;
                }
            }
        }
    }
    
    public Expression derivative(final Variable variable) {
        final ExpressionProgram expressionProgram = new ExpressionProgram();
        this.compileDerivative(this.progCt - 1, expressionProgram, variable);
        expressionProgram.trim();
        return expressionProgram;
    }
    
    public void compileDerivative(final int n, final ExpressionProgram expressionProgram, final Variable variable) {
        if (!this.dependsOn(n, variable)) {
            expressionProgram.addConstant(0.0);
        }
        else if (this.prog[n] >= 1073741823) {
            this.command[this.prog[n] - 1073741823].compileDerivative(this, n, expressionProgram, variable);
        }
        else if (this.prog[n] >= 0) {
            expressionProgram.addConstant(0.0);
        }
        else if (this.prog[n] >= -5) {
            final int n2 = n - 1;
            this.doBinDeriv(this.prog[n], n2 - this.extent(n2), n2, expressionProgram, variable);
        }
        else if (this.prog[n] <= -17) {
            this.doFuncDeriv(this.prog[n], n - 1, expressionProgram, variable);
        }
        else if (this.prog[n] == -15) {
            this.compileDerivative(n - 1, expressionProgram, variable);
            expressionProgram.addCommand(-15);
        }
        else if (this.prog[n] == -16) {
            expressionProgram.addConstant(Double.NaN);
        }
        else {
            if (this.prog[n] >= -14) {
                throw new IllegalArgumentException("Internal Error: Attempt to take the derivative of a logical-valued expression.");
            }
            throw new IllegalArgumentException("Internal Error: Unknown opcode.");
        }
    }
    
    public int extent(final int n) {
        if (this.prog[n] <= -14) {
            return 1 + this.extent(n - 1);
        }
        if (this.prog[n] < 0) {
            final int extent = this.extent(n - 1);
            return extent + this.extent(n - 1 - extent) + 1;
        }
        if (this.prog[n] < 1073741823) {
            return 1;
        }
        return this.command[this.prog[n] - 1073741823].extent(this, n);
    }
    
    public void copyExpression(final int n, final ExpressionProgram expressionProgram) {
        for (int i = n - this.extent(n) + 1; i <= n; ++i) {
            if (this.prog[i] < 0) {
                expressionProgram.addCommand(this.prog[i]);
            }
            else if (this.prog[i] >= 1073741823) {
                expressionProgram.addCommandObject(this.command[this.prog[i] - 1073741823]);
            }
            else {
                expressionProgram.addConstant(this.constant[this.prog[i]]);
            }
        }
    }
    
    public boolean dependsOn(final int n, final Variable variable) {
        for (int i = n - this.extent(n) + 1; i <= n; ++i) {
            if (this.prog[i] >= 1073741823) {
                final ExpressionCommand expressionCommand = this.command[this.prog[i] - 1073741823];
                if (expressionCommand == variable || expressionCommand.dependsOn(variable)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean dependsOn(final Variable variable) {
        return this.dependsOn(this.progCt - 1, variable);
    }
    
    private void doBinDeriv(final int n, final int n2, final int n3, final ExpressionProgram expressionProgram, final Variable variable) {
        switch (n) {
            case -1: {
                if (!this.dependsOn(n2, variable)) {
                    this.compileDerivative(n3, expressionProgram, variable);
                    break;
                }
                if (!this.dependsOn(n3, variable)) {
                    this.compileDerivative(n2, expressionProgram, variable);
                    break;
                }
                this.compileDerivative(n2, expressionProgram, variable);
                this.compileDerivative(n3, expressionProgram, variable);
                expressionProgram.addCommand(-1);
                break;
            }
            case -2: {
                if (!this.dependsOn(n2, variable)) {
                    this.compileDerivative(n3, expressionProgram, variable);
                    expressionProgram.addCommand(-15);
                    break;
                }
                if (!this.dependsOn(n3, variable)) {
                    this.compileDerivative(n2, expressionProgram, variable);
                    break;
                }
                this.compileDerivative(n2, expressionProgram, variable);
                this.compileDerivative(n3, expressionProgram, variable);
                expressionProgram.addCommand(-2);
                break;
            }
            case -3: {
                int n4 = 0;
                if (this.dependsOn(n3, variable)) {
                    this.copyExpression(n2, expressionProgram);
                    if (this.prog[n3] < 1073741823 || this.command[this.prog[n3] - 1073741823] != variable) {
                        this.compileDerivative(n3, expressionProgram, variable);
                        expressionProgram.addCommand(-3);
                    }
                    ++n4;
                }
                if (this.dependsOn(n2, variable)) {
                    this.copyExpression(n3, expressionProgram);
                    if (this.prog[n2] < 1073741823 || this.command[this.prog[n2] - 1073741823] != variable) {
                        this.compileDerivative(n2, expressionProgram, variable);
                        expressionProgram.addCommand(-3);
                    }
                    ++n4;
                }
                if (n4 == 2) {
                    expressionProgram.addCommand(-1);
                }
                break;
            }
            case -4: {
                if (!this.dependsOn(n3, variable)) {
                    this.compileDerivative(n2, expressionProgram, variable);
                    this.copyExpression(n3, expressionProgram);
                    expressionProgram.addCommand(-4);
                    break;
                }
                if (this.dependsOn(n2, variable)) {
                    this.copyExpression(n3, expressionProgram);
                    if (this.prog[n2] < 1073741823 || this.command[this.prog[n2] - 1073741823] != variable) {
                        this.compileDerivative(n2, expressionProgram, variable);
                        expressionProgram.addCommand(-3);
                    }
                    this.copyExpression(n2, expressionProgram);
                    if (this.prog[n3] < 1073741823 || this.command[this.prog[n3] - 1073741823] != variable) {
                        this.compileDerivative(n3, expressionProgram, variable);
                        expressionProgram.addCommand(-3);
                    }
                    expressionProgram.addCommand(-2);
                    this.copyExpression(n3, expressionProgram);
                    expressionProgram.addConstant(2.0);
                    expressionProgram.addCommand(-5);
                    expressionProgram.addCommand(-4);
                    break;
                }
                this.copyExpression(n2, expressionProgram);
                expressionProgram.addCommand(-15);
                this.copyExpression(n3, expressionProgram);
                expressionProgram.addConstant(2.0);
                expressionProgram.addCommand(-5);
                expressionProgram.addCommand(-4);
                if (this.prog[n3] < 1073741823 || this.command[this.prog[n3] - 1073741823] != variable) {
                    this.compileDerivative(n3, expressionProgram, variable);
                    expressionProgram.addCommand(-3);
                    break;
                }
                break;
            }
            case -5: {
                if (!this.dependsOn(n3, variable)) {
                    this.copyExpression(n3, expressionProgram);
                    this.copyExpression(n2, expressionProgram);
                    if (this.prog[n3] >= 0 && this.prog[n3] < 1073741823) {
                        if (this.constant[this.prog[n3]] != 2.0) {
                            expressionProgram.addConstant(this.constant[this.prog[n3]] - 1.0);
                            expressionProgram.addCommand(-5);
                        }
                    }
                    else if (this.prog[n3] == -15 && this.prog[n3 - 1] >= 0 && this.prog[n3 - 1] < 1073741823) {
                        expressionProgram.addConstant(this.constant[this.prog[n3 - 1]] + 1.0);
                        expressionProgram.addCommand(-15);
                        expressionProgram.addCommand(-5);
                    }
                    else {
                        this.copyExpression(n3, expressionProgram);
                        expressionProgram.addConstant(1.0);
                        expressionProgram.addCommand(-2);
                        expressionProgram.addCommand(-5);
                    }
                    expressionProgram.addCommand(-3);
                    if (this.prog[n2] < 1073741823 || this.command[this.prog[n2] - 1073741823] != variable) {
                        this.compileDerivative(n2, expressionProgram, variable);
                        expressionProgram.addCommand(-3);
                        break;
                    }
                    break;
                }
                else {
                    if (this.dependsOn(n2, variable)) {
                        this.copyExpression(n2, expressionProgram);
                        this.copyExpression(n3, expressionProgram);
                        expressionProgram.addCommand(-5);
                        boolean b = true;
                        final int extent = this.extent(n2);
                        if (extent != this.extent(n3)) {
                            b = false;
                        }
                        else {
                            for (int i = 0; i < extent; ++i) {
                                if (this.prog[n2 - i] != this.prog[n3 - i]) {
                                    b = false;
                                    break;
                                }
                            }
                        }
                        if (b) {
                            expressionProgram.addConstant(1.0);
                        }
                        else {
                            this.copyExpression(n3, expressionProgram);
                            this.copyExpression(n2, expressionProgram);
                            expressionProgram.addCommand(-4);
                        }
                        if (this.prog[n2] < 1073741823 || this.command[this.prog[n2] - 1073741823] != variable) {
                            this.compileDerivative(n2, expressionProgram, variable);
                            expressionProgram.addCommand(-3);
                        }
                        this.copyExpression(n2, expressionProgram);
                        expressionProgram.addCommand(-29);
                        if (this.prog[n3] < 1073741823 || this.command[this.prog[n3] - 1073741823] != variable) {
                            this.compileDerivative(n3, expressionProgram, variable);
                            expressionProgram.addCommand(-3);
                        }
                        expressionProgram.addCommand(-1);
                        expressionProgram.addCommand(-3);
                        break;
                    }
                    this.copyExpression(n2, expressionProgram);
                    this.copyExpression(n3, expressionProgram);
                    expressionProgram.addCommand(-5);
                    if (this.prog[n2] < 1073741823 || !(this.command[this.prog[n2] - 1073741823] instanceof Constant) || ((Constant)this.command[this.prog[n2] - 1073741823]).getVal() != 2.718281828459045) {
                        this.copyExpression(n2, expressionProgram);
                        expressionProgram.addCommand(-29);
                        expressionProgram.addCommand(-3);
                    }
                    if (this.prog[n3] < 1073741823 || this.command[this.prog[n3] - 1073741823] != variable) {
                        this.compileDerivative(n3, expressionProgram, variable);
                        expressionProgram.addCommand(-3);
                        break;
                    }
                    break;
                }
                break;
            }
        }
    }
    
    private void doFuncDeriv(final int n, final int n2, final ExpressionProgram expressionProgram, final Variable variable) {
        switch (n) {
            case -35:
            case -34:
            case -33:
            case -32: {
                this.copyExpression(n2, expressionProgram);
                if (n == -33) {
                    expressionProgram.addConstant(0.5);
                    expressionProgram.addCommand(-1);
                }
                expressionProgram.addCommand(-33);
                this.copyExpression(n2, expressionProgram);
                if (n == -33) {
                    expressionProgram.addConstant(0.5);
                    expressionProgram.addCommand(-1);
                }
                expressionProgram.addCommand(-7);
                if (n == -32) {
                    this.copyExpression(n2, expressionProgram);
                    expressionProgram.addConstant(0.0);
                    expressionProgram.addCommand(-6);
                    expressionProgram.addCommand(-13);
                }
                final ExpressionProgram expressionProgram2 = new ExpressionProgram();
                expressionProgram2.addConstant(0.0);
                expressionProgram.addCommandObject(new ConditionalExpression(expressionProgram2, null));
                return;
            }
            case -17: {
                this.copyExpression(n2, expressionProgram);
                expressionProgram.addCommand(-18);
                break;
            }
            case -18: {
                this.copyExpression(n2, expressionProgram);
                expressionProgram.addCommand(-17);
                expressionProgram.addCommand(-15);
                break;
            }
            case -19: {
                this.copyExpression(n2, expressionProgram);
                expressionProgram.addCommand(-21);
                expressionProgram.addConstant(2.0);
                expressionProgram.addCommand(-5);
                break;
            }
            case -20: {
                this.copyExpression(n2, expressionProgram);
                expressionProgram.addCommand(-22);
                expressionProgram.addConstant(2.0);
                expressionProgram.addCommand(-5);
                expressionProgram.addCommand(-15);
                break;
            }
            case -21: {
                this.copyExpression(n2, expressionProgram);
                expressionProgram.addCommand(-21);
                this.copyExpression(n2, expressionProgram);
                expressionProgram.addCommand(-19);
                expressionProgram.addCommand(-3);
                break;
            }
            case -22: {
                this.copyExpression(n2, expressionProgram);
                expressionProgram.addCommand(-22);
                this.copyExpression(n2, expressionProgram);
                expressionProgram.addCommand(-20);
                expressionProgram.addCommand(-3);
                expressionProgram.addCommand(-15);
                break;
            }
            case -24:
            case -23: {
                expressionProgram.addConstant(1.0);
                if (n == -24) {
                    expressionProgram.addCommand(-15);
                }
                expressionProgram.addConstant(1.0);
                this.copyExpression(n2, expressionProgram);
                expressionProgram.addConstant(2.0);
                expressionProgram.addCommand(-5);
                expressionProgram.addCommand(-2);
                expressionProgram.addCommand(-27);
                expressionProgram.addCommand(-4);
                break;
            }
            case -25: {
                expressionProgram.addConstant(1.0);
                expressionProgram.addConstant(1.0);
                this.copyExpression(n2, expressionProgram);
                expressionProgram.addConstant(2.0);
                expressionProgram.addCommand(-5);
                expressionProgram.addCommand(-1);
                expressionProgram.addCommand(-4);
                break;
            }
            case -26: {
                final ExpressionProgram expressionProgram3 = new ExpressionProgram();
                final ExpressionProgram expressionProgram4 = new ExpressionProgram();
                this.compileDerivative(n2, expressionProgram3, variable);
                this.compileDerivative(n2, expressionProgram4, variable);
                expressionProgram4.addCommand(-15);
                final ExpressionProgram expressionProgram5 = new ExpressionProgram();
                this.copyExpression(n2, expressionProgram5);
                expressionProgram5.addConstant(0.0);
                expressionProgram5.addCommand(-8);
                expressionProgram5.addCommandObject(new ConditionalExpression(expressionProgram4, null));
                this.copyExpression(n2, expressionProgram);
                expressionProgram.addConstant(0.0);
                expressionProgram.addCommand(-9);
                expressionProgram.addCommandObject(new ConditionalExpression(expressionProgram3, expressionProgram5));
                return;
            }
            case -27: {
                expressionProgram.addConstant(1.0);
                expressionProgram.addConstant(2.0);
                this.copyExpression(n2, expressionProgram);
                expressionProgram.addCommand(-27);
                expressionProgram.addCommand(-3);
                expressionProgram.addCommand(-4);
                break;
            }
            case -28: {
                this.copyExpression(n2, expressionProgram);
                expressionProgram.addCommand(-28);
                break;
            }
            case -31:
            case -30:
            case -29: {
                final ExpressionProgram expressionProgram6 = new ExpressionProgram();
                expressionProgram6.addConstant(1.0);
                this.copyExpression(n2, expressionProgram6);
                expressionProgram6.addCommand(-4);
                if (n != -29) {
                    expressionProgram6.addConstant((n == -30) ? 2.0 : 10.0);
                    expressionProgram6.addCommand(-29);
                    expressionProgram6.addCommand(-4);
                }
                this.copyExpression(n2, expressionProgram);
                expressionProgram.addConstant(0.0);
                expressionProgram.addCommand(-9);
                expressionProgram.addCommandObject(new ConditionalExpression(expressionProgram6, null));
                break;
            }
            case -36: {
                expressionProgram.addConstant(1.0);
                expressionProgram.addConstant(3.0);
                this.copyExpression(n2, expressionProgram);
                expressionProgram.addConstant(2.0);
                expressionProgram.addCommand(-5);
                expressionProgram.addCommand(-36);
                expressionProgram.addCommand(-3);
                expressionProgram.addCommand(-4);
                break;
            }
        }
        if (this.prog[n2] < 1073741823 || this.command[this.prog[n2] - 1073741823] != variable) {
            this.compileDerivative(n2, expressionProgram, variable);
            expressionProgram.addCommand(-3);
        }
    }
    
    private int findConstant(final double n) {
        for (int i = 0; i < this.constantCt; ++i) {
            if (this.constant[i] == n) {
                return i;
            }
        }
        if (this.constantCt == this.constant.length) {
            final double[] constant = new double[this.constant.length * 2];
            System.arraycopy(this.constant, 0, constant, 0, this.constantCt);
            this.constant = constant;
        }
        this.constant[this.constantCt++] = n;
        return this.constantCt - 1;
    }
    
    private int findCommand(final ExpressionCommand expressionCommand) {
        for (int i = 0; i < this.commandCt; ++i) {
            if (this.command[i] == expressionCommand) {
                return i;
            }
        }
        if (this.commandCt == this.command.length) {
            final ExpressionCommand[] command = new ExpressionCommand[this.command.length * 2];
            System.arraycopy(this.command, 0, command, 0, this.commandCt);
            this.command = command;
        }
        this.command[this.commandCt++] = expressionCommand;
        return this.commandCt - 1;
    }
}
