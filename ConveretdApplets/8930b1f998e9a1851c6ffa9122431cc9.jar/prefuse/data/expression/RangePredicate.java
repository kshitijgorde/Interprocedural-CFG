// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.event.ExpressionListener;
import prefuse.data.Schema;
import prefuse.util.collections.LiteralComparator;
import prefuse.util.TypeLib;
import prefuse.data.Tuple;
import prefuse.util.collections.DefaultLiteralComparator;
import java.util.Comparator;

public class RangePredicate extends BinaryExpression implements Predicate
{
    public static final int IN_IN = 0;
    public static final int IN_EX = 1;
    public static final int EX_IN = 2;
    public static final int EX_EX = 3;
    private Expression m_middle;
    private Comparator m_cmp;
    
    public RangePredicate(final Expression expression, final Expression expression2, final Expression expression3) {
        this(0, expression, expression2, expression3, DefaultLiteralComparator.getInstance());
    }
    
    public RangePredicate(final Expression expression, final Expression expression2, final Expression expression3, final Comparator comparator) {
        this(0, expression, expression2, expression3, comparator);
    }
    
    public RangePredicate(final int n, final Expression expression, final Expression expression2, final Expression expression3) {
        this(n, expression, expression2, expression3, DefaultLiteralComparator.getInstance());
    }
    
    public RangePredicate(final int n, final Expression middle, final Expression expression, final Expression expression2, final Comparator cmp) {
        super(n, 0, 3, expression, expression2);
        this.m_middle = middle;
        this.m_cmp = cmp;
    }
    
    public Expression getMiddleExpression() {
        return this.m_middle;
    }
    
    public Comparator getComparator() {
        return this.m_cmp;
    }
    
    public boolean getBoolean(final Tuple tuple) {
        final Class type = this.m_left.getType(tuple.getSchema());
        final Class type2 = this.m_right.getType(tuple.getSchema());
        final Class type3 = this.m_middle.getType(tuple.getSchema());
        Class clazz = null;
        if (type.isAssignableFrom(type2)) {
            clazz = type;
        }
        else if (type2.isAssignableFrom(type)) {
            clazz = type2;
        }
        int n;
        int n2;
        if (clazz != null && TypeLib.isNumericType(clazz) && TypeLib.isNumericType(type3)) {
            final Class numericType = TypeLib.getNumericType(clazz, type3);
            if (numericType == Integer.TYPE) {
                final int int1 = this.m_left.getInt(tuple);
                final int int2 = this.m_right.getInt(tuple);
                final int int3 = this.m_middle.getInt(tuple);
                n = ((LiteralComparator)this.m_cmp).compare(int3, int1);
                n2 = ((LiteralComparator)this.m_cmp).compare(int3, int2);
            }
            else if (numericType == Long.TYPE) {
                final long long1 = this.m_left.getLong(tuple);
                final long long2 = this.m_right.getLong(tuple);
                final long long3 = this.m_middle.getLong(tuple);
                n = ((LiteralComparator)this.m_cmp).compare(long3, long1);
                n2 = ((LiteralComparator)this.m_cmp).compare(long3, long2);
            }
            else if (numericType == Float.TYPE) {
                final float float1 = this.m_left.getFloat(tuple);
                final float float2 = this.m_right.getFloat(tuple);
                final float float3 = this.m_middle.getFloat(tuple);
                n = ((LiteralComparator)this.m_cmp).compare(float3, float1);
                n2 = ((LiteralComparator)this.m_cmp).compare(float3, float2);
            }
            else {
                if (numericType != Double.TYPE) {
                    throw new IllegalStateException();
                }
                final double double1 = this.m_left.getDouble(tuple);
                final double double2 = this.m_right.getDouble(tuple);
                final double double3 = this.m_middle.getDouble(tuple);
                n = ((LiteralComparator)this.m_cmp).compare(double3, double1);
                n2 = ((LiteralComparator)this.m_cmp).compare(double3, double2);
            }
        }
        else {
            final Object value = this.m_left.get(tuple);
            final Object value2 = this.m_right.get(tuple);
            final Object value3 = this.m_middle.get(tuple);
            n = this.m_cmp.compare(value3, value);
            n2 = this.m_cmp.compare(value3, value2);
        }
        switch (this.m_op) {
            case 0: {
                return n >= 0 && n2 <= 0;
            }
            case 1: {
                return n >= 0 && n2 < 0;
            }
            case 2: {
                return n > 0 && n2 <= 0;
            }
            case 3: {
                return n > 0 && n2 < 0;
            }
            default: {
                throw new IllegalStateException("Unknown operation.");
            }
        }
    }
    
    public Class getType(final Schema schema) {
        return Boolean.TYPE;
    }
    
    public Object get(final Tuple tuple) {
        return this.getBoolean(tuple) ? Boolean.TRUE : Boolean.FALSE;
    }
    
    public void visit(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visitExpression(this);
        expressionVisitor.down();
        this.m_left.visit(expressionVisitor);
        expressionVisitor.up();
        expressionVisitor.down();
        this.m_middle.visit(expressionVisitor);
        expressionVisitor.up();
        expressionVisitor.down();
        this.m_right.visit(expressionVisitor);
        expressionVisitor.up();
    }
    
    protected void addChildListeners() {
        super.addChildListeners();
        this.m_middle.addExpressionListener(this);
    }
    
    protected void removeChildListeners() {
        super.removeChildListeners();
        this.m_middle.removeExpressionListener(this);
    }
    
    public String toString() {
        String s = "?";
        String s2 = "?";
        switch (this.m_op) {
            case 0: {
                s2 = (s = "<=");
                break;
            }
            case 1: {
                s = "<=";
                s2 = "<";
                break;
            }
            case 2: {
                s = "<";
                s2 = "<=";
                break;
            }
            case 3: {
                s2 = (s = "<");
                break;
            }
        }
        return '(' + this.m_left.toString() + ' ' + s + ' ' + this.m_middle.toString() + " AND " + this.m_middle.toString() + ' ' + s2 + ' ' + this.m_right.toString() + ')';
    }
}
