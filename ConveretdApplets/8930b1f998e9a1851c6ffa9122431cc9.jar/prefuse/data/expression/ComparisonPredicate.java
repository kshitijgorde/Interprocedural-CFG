// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.util.collections.LiteralComparator;
import prefuse.util.TypeLib;
import prefuse.data.Tuple;
import prefuse.data.Schema;
import prefuse.util.collections.DefaultLiteralComparator;
import java.util.Comparator;

public class ComparisonPredicate extends BinaryExpression implements Predicate
{
    public static final int LT = 0;
    public static final int GT = 1;
    public static final int EQ = 2;
    public static final int NEQ = 3;
    public static final int LTEQ = 4;
    public static final int GTEQ = 5;
    private Comparator m_cmp;
    
    public ComparisonPredicate(final int n, final Expression expression, final Expression expression2) {
        this(n, expression, expression2, DefaultLiteralComparator.getInstance());
    }
    
    public ComparisonPredicate(final int n, final Expression expression, final Expression expression2, final Comparator cmp) {
        super(n, 0, 5, expression, expression2);
        this.m_cmp = cmp;
    }
    
    public Comparator getComparator() {
        return this.m_cmp;
    }
    
    public Class getType(final Schema schema) {
        return Boolean.TYPE;
    }
    
    public boolean getBoolean(final Tuple tuple) {
        final Class type = this.m_left.getType(tuple.getSchema());
        final Class type2 = this.m_right.getType(tuple.getSchema());
        int n;
        if (TypeLib.isNumericType(type) && TypeLib.isNumericType(type2)) {
            final Class numericType = TypeLib.getNumericType(type, type2);
            if (numericType == Integer.TYPE || numericType == Byte.TYPE) {
                n = ((LiteralComparator)this.m_cmp).compare(this.m_left.getInt(tuple), this.m_right.getInt(tuple));
            }
            else if (numericType == Long.TYPE) {
                n = ((LiteralComparator)this.m_cmp).compare(this.m_left.getLong(tuple), this.m_right.getLong(tuple));
            }
            else if (numericType == Float.TYPE) {
                n = ((LiteralComparator)this.m_cmp).compare(this.m_left.getFloat(tuple), this.m_right.getFloat(tuple));
            }
            else {
                if (numericType != Double.TYPE) {
                    throw new IllegalStateException();
                }
                n = ((LiteralComparator)this.m_cmp).compare(this.m_left.getDouble(tuple), this.m_right.getDouble(tuple));
            }
        }
        else {
            n = this.m_cmp.compare(this.m_left.get(tuple), this.m_right.get(tuple));
        }
        switch (this.m_op) {
            case 0: {
                return n == -1;
            }
            case 1: {
                return n == 1;
            }
            case 2: {
                return n == 0;
            }
            case 3: {
                return n != 0;
            }
            case 4: {
                return n <= 0;
            }
            case 5: {
                return n >= 0;
            }
            default: {
                throw new IllegalStateException("Unknown operation.");
            }
        }
    }
    
    public Object get(final Tuple tuple) {
        return this.getBoolean(tuple) ? Boolean.TRUE : Boolean.FALSE;
    }
    
    public String toString() {
        String s = "?";
        switch (this.m_op) {
            case 0: {
                s = "<";
                break;
            }
            case 1: {
                s = ">";
                break;
            }
            case 2: {
                s = "=";
                break;
            }
            case 3: {
                s = "!=";
                break;
            }
            case 4: {
                s = "<=";
                break;
            }
            case 5: {
                s = ">=";
                break;
            }
        }
        return this.m_left.toString() + ' ' + s + ' ' + this.m_right.toString();
    }
}
