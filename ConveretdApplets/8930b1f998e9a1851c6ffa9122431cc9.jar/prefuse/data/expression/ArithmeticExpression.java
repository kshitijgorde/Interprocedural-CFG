// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;
import prefuse.util.TypeLib;
import prefuse.data.Schema;

public class ArithmeticExpression extends BinaryExpression
{
    public static final int ADD = 0;
    public static final int SUB = 1;
    public static final int MUL = 2;
    public static final int DIV = 3;
    public static final int POW = 4;
    public static final int MOD = 5;
    private Class m_type;
    
    public ArithmeticExpression(final int n, final Expression expression, final Expression expression2) {
        super(n, 0, 5, expression, expression2);
        this.m_type = null;
    }
    
    public Class getType(final Schema schema) {
        if (this.m_type == null) {
            this.m_type = TypeLib.getNumericType(this.m_left.getType(schema), this.m_right.getType(schema));
        }
        return this.m_type;
    }
    
    public Object get(final Tuple tuple) {
        final Class type = this.getType(tuple.getSchema());
        if (Integer.TYPE == type || Byte.TYPE == type) {
            return new Integer(this.getInt(tuple));
        }
        if (Long.TYPE == type) {
            return new Long(this.getInt(tuple));
        }
        if (Float.TYPE == type) {
            return new Float(this.getFloat(tuple));
        }
        if (Double.TYPE == type) {
            return new Double(this.getDouble(tuple));
        }
        throw new IllegalStateException();
    }
    
    public int getInt(final Tuple tuple) {
        final int int1 = this.m_left.getInt(tuple);
        final int int2 = this.m_right.getInt(tuple);
        switch (this.m_op) {
            case 0: {
                return int1 + int2;
            }
            case 1: {
                return int1 - int2;
            }
            case 2: {
                return int1 * int2;
            }
            case 3: {
                return int1 / int2;
            }
            case 4: {
                return (int)Math.pow(int1, int2);
            }
            case 5: {
                return int1 % int2;
            }
            default: {
                throw new IllegalStateException("Unknown operation type.");
            }
        }
    }
    
    public long getLong(final Tuple tuple) {
        final long long1 = this.m_left.getLong(tuple);
        final long long2 = this.m_right.getLong(tuple);
        switch (this.m_op) {
            case 0: {
                return long1 + long2;
            }
            case 1: {
                return long1 - long2;
            }
            case 2: {
                return long1 * long2;
            }
            case 3: {
                return long1 / long2;
            }
            case 4: {
                return (long)Math.pow(long1, long2);
            }
            case 5: {
                return long1 % long2;
            }
            default: {
                throw new IllegalStateException("Unknown operation type.");
            }
        }
    }
    
    public float getFloat(final Tuple tuple) {
        final float float1 = this.m_left.getFloat(tuple);
        final float float2 = this.m_right.getFloat(tuple);
        switch (this.m_op) {
            case 0: {
                return float1 + float2;
            }
            case 1: {
                return float1 - float2;
            }
            case 2: {
                return float1 * float2;
            }
            case 3: {
                return float1 / float2;
            }
            case 4: {
                return (float)Math.pow(float1, float2);
            }
            case 5: {
                return (float)Math.IEEEremainder(float1, float2);
            }
            default: {
                throw new IllegalStateException("Unknown operation type.");
            }
        }
    }
    
    public double getDouble(final Tuple tuple) {
        final double double1 = this.m_left.getDouble(tuple);
        final double double2 = this.m_right.getDouble(tuple);
        switch (this.m_op) {
            case 0: {
                return double1 + double2;
            }
            case 1: {
                return double1 - double2;
            }
            case 2: {
                return double1 * double2;
            }
            case 3: {
                return double1 / double2;
            }
            case 4: {
                return Math.pow(double1, double2);
            }
            case 5: {
                return Math.IEEEremainder(double1, double2);
            }
            default: {
                throw new IllegalStateException("Unknown operation type.");
            }
        }
    }
    
    public String toString() {
        char c = '?';
        switch (this.m_op) {
            case 0: {
                c = '+';
                break;
            }
            case 1: {
                c = '-';
                break;
            }
            case 2: {
                c = '*';
                break;
            }
            case 3: {
                c = '/';
                break;
            }
            case 4: {
                c = '^';
                break;
            }
            case 5: {
                c = '%';
                break;
            }
        }
        return '(' + this.m_left.toString() + ' ' + c + ' ' + this.m_right.toString() + ')';
    }
}
