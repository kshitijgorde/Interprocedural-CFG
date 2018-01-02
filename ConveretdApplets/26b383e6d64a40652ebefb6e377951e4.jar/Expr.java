// 
// Decompiled by Procyon v0.5.30
// 

class Expr
{
    Expr left;
    Expr right;
    double value;
    int type;
    static final int E_ADD = 1;
    static final int E_SUB = 2;
    static final int E_X = 3;
    static final int E_Y = 4;
    static final int E_Z = 5;
    static final int E_VAL = 6;
    static final int E_MUL = 7;
    static final int E_DIV = 8;
    static final int E_POW = 9;
    static final int E_UMINUS = 10;
    static final int E_SIN = 11;
    static final int E_COS = 12;
    static final int E_ABS = 13;
    static final int E_EXP = 14;
    static final int E_LOG = 15;
    static final int E_SQRT = 16;
    static final int E_TAN = 17;
    static final int E_R = 18;
    
    Expr(final Expr left, final Expr right, final int type) {
        this.left = left;
        this.right = right;
        this.type = type;
    }
    
    Expr(final int type, final double value) {
        this.type = type;
        this.value = value;
    }
    
    Expr(final int type) {
        this.type = type;
    }
    
    double eval(final double[] array) {
        switch (this.type) {
            case 1: {
                return this.left.eval(array) + this.right.eval(array);
            }
            case 2: {
                return this.left.eval(array) - this.right.eval(array);
            }
            case 7: {
                return this.left.eval(array) * this.right.eval(array);
            }
            case 8: {
                return this.left.eval(array) / this.right.eval(array);
            }
            case 9: {
                return Math.pow(this.left.eval(array), this.right.eval(array));
            }
            case 10: {
                return -this.left.eval(array);
            }
            case 6: {
                return this.value;
            }
            case 3: {
                return array[0] * 10.0;
            }
            case 4: {
                return array[1] * 10.0;
            }
            case 18: {
                return Math.sqrt(array[0] * array[0] + array[1] * array[1]) * 10.0;
            }
            case 11: {
                return Math.sin(this.left.eval(array));
            }
            case 12: {
                return Math.cos(this.left.eval(array));
            }
            case 13: {
                return Math.abs(this.left.eval(array));
            }
            case 14: {
                return Math.exp(this.left.eval(array));
            }
            case 15: {
                return Math.log(this.left.eval(array));
            }
            case 16: {
                return Math.sqrt(this.left.eval(array));
            }
            case 17: {
                return Math.tan(this.left.eval(array));
            }
            default: {
                System.out.print("unknown\n");
                return 0.0;
            }
        }
    }
}
