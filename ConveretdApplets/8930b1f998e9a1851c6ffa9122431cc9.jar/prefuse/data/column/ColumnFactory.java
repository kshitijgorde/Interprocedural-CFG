// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.column;

import prefuse.data.expression.Expression;
import prefuse.data.Table;
import prefuse.data.DataTypeException;
import java.util.Date;

public class ColumnFactory
{
    public static final Column getColumn(final Class clazz) {
        return getColumn(clazz, 0, 0, null);
    }
    
    public static final Column getColumn(final Class clazz, final int n) {
        return getColumn(clazz, n, n, null);
    }
    
    public static final Column getColumn(final Class clazz, final int n, final Object o) {
        return getColumn(clazz, n, n, o);
    }
    
    public static final Column getColumn(final Class clazz, final int n, final int n2, final Object o) {
        if (clazz == Byte.TYPE) {
            if (o == null) {
                return new ByteColumn(n);
            }
            return new ByteColumn(n, n, ((Number)o).byteValue());
        }
        else if (clazz == Integer.TYPE) {
            if (o == null) {
                return new IntColumn(n);
            }
            return new IntColumn(n, n, ((Number)o).intValue());
        }
        else if (clazz == Long.TYPE) {
            if (o == null) {
                return new LongColumn(n);
            }
            return new LongColumn(n, n, ((Number)o).longValue());
        }
        else if (clazz == Float.TYPE) {
            if (o == null) {
                return new FloatColumn(n);
            }
            return new FloatColumn(n, n, ((Number)o).floatValue());
        }
        else if (clazz == Double.TYPE) {
            if (o == null) {
                return new DoubleColumn(n);
            }
            return new DoubleColumn(n, n, ((Number)o).doubleValue());
        }
        else if (clazz == Boolean.TYPE) {
            if (o == null) {
                return new BooleanColumn(n);
            }
            return new BooleanColumn(n, n, (boolean)o);
        }
        else if (Date.class.isAssignableFrom(clazz)) {
            if (o == null) {
                return new DateColumn(clazz, n);
            }
            return new DateColumn(clazz, n, n, ((Date)o).getTime());
        }
        else {
            if (clazz == Byte.TYPE || clazz == Short.TYPE || clazz == Character.TYPE || clazz == Void.TYPE) {
                throw new DataTypeException(clazz);
            }
            return new ObjectColumn(clazz, n, n, o);
        }
    }
    
    public static final Column getColumn(final Table table, final Expression expression) {
        return new ExpressionColumn(table, expression);
    }
    
    public static final Column getConstantColumn(final Class clazz, final Object o) {
        return new ConstantColumn(clazz, o);
    }
}
