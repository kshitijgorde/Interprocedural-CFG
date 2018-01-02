// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.util.TypeLib;
import prefuse.data.Tuple;

public abstract class Literal extends AbstractExpression
{
    public static Literal getLiteral(final Tuple tuple, final String s) {
        final Class columnType = tuple.getColumnType(s);
        if (columnType == Integer.TYPE) {
            return new NumericLiteral(tuple.getInt(s));
        }
        if (columnType == Long.TYPE) {
            return new NumericLiteral(tuple.getLong(s));
        }
        if (columnType == Float.TYPE) {
            return new NumericLiteral(tuple.getFloat(s));
        }
        if (columnType == Double.TYPE) {
            return new NumericLiteral(tuple.getDouble(s));
        }
        if (columnType == Boolean.TYPE) {
            return new BooleanLiteral(tuple.getBoolean(s));
        }
        return new ObjectLiteral(tuple.get(s));
    }
    
    public static Literal getLiteral(final Object o) {
        return getLiteral(o, o.getClass());
    }
    
    public static Literal getLiteral(final Object o, final Class clazz) {
        if (TypeLib.isNumericType(clazz)) {
            return new NumericLiteral(o);
        }
        if (clazz == Boolean.TYPE) {
            return new BooleanLiteral((boolean)o);
        }
        if (clazz.isInstance(o)) {
            return new ObjectLiteral(o);
        }
        throw new IllegalArgumentException("Object does not match the provided Class type.");
    }
}
