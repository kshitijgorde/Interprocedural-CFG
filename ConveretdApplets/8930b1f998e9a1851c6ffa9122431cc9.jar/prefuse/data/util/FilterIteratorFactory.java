// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import prefuse.util.PrefuseConfig;
import java.util.Comparator;
import prefuse.data.expression.Expression;
import prefuse.data.Tuple;
import prefuse.data.expression.ExpressionAnalyzer;
import prefuse.util.collections.CompositeIntIterator;
import prefuse.data.expression.RangePredicate;
import prefuse.data.expression.ComparisonPredicate;
import prefuse.data.expression.OrPredicate;
import prefuse.data.expression.AndPredicate;
import prefuse.data.expression.NotPredicate;
import prefuse.data.expression.ColumnExpression;
import prefuse.util.collections.IntIterator;
import prefuse.data.Table;
import java.util.Iterator;
import prefuse.data.expression.Predicate;
import prefuse.data.tuple.TupleSet;

public class FilterIteratorFactory
{
    private static final int OPTIMIZATION_THRESHOLD;
    
    public static Iterator tuples(final TupleSet set, final Predicate predicate) {
        if (predicate == null) {
            return set.tuples();
        }
        Iterator tuples = null;
        if (set instanceof Table) {
            final Table table = (Table)set;
            final IntIterator optimizedIterator = getOptimizedIterator(table, predicate);
            if (optimizedIterator != null) {
                tuples = table.tuples(optimizedIterator);
            }
        }
        if (tuples == null) {
            tuples = new FilterIterator(set.tuples(), predicate);
        }
        return tuples;
    }
    
    public static IntIterator rows(final Table table, final Predicate predicate) {
        IntIterator optimizedIterator = getOptimizedIterator(table, predicate);
        if (optimizedIterator == null) {
            optimizedIterator = new FilterRowIterator(table.rows(), table, predicate);
        }
        return optimizedIterator;
    }
    
    protected static IntIterator getOptimizedIterator(final Table table, final Predicate predicate) {
        if (table.getRowCount() < FilterIteratorFactory.OPTIMIZATION_THRESHOLD) {
            return null;
        }
        if (predicate instanceof ColumnExpression) {
            return getColumnIterator(table, ((ColumnExpression)predicate).getColumnName(), true);
        }
        if (predicate instanceof NotPredicate) {
            final Predicate predicate2 = ((NotPredicate)predicate).getPredicate();
            if (predicate2 instanceof ColumnExpression) {
                return getColumnIterator(table, ((ColumnExpression)predicate2).getColumnName(), false);
            }
        }
        else {
            if (predicate instanceof AndPredicate) {
                return getAndIterator(table, (AndPredicate)predicate);
            }
            if (predicate instanceof OrPredicate) {
                return getOrIterator(table, (OrPredicate)predicate);
            }
            if (predicate instanceof ComparisonPredicate) {
                return getComparisonIterator(table, (ComparisonPredicate)predicate);
            }
            if (predicate instanceof RangePredicate) {
                return getRangeIterator(table, (RangePredicate)predicate);
            }
        }
        return null;
    }
    
    protected static IntIterator getColumnIterator(final Table table, final String s, final boolean b) {
        if (table.getColumnType(s) != Boolean.TYPE) {
            return null;
        }
        final Index index = table.getIndex(s);
        if (index == null) {
            return null;
        }
        return index.rows(b);
    }
    
    protected static IntIterator getOrIterator(final Table table, final OrPredicate orPredicate) {
        final int size = orPredicate.size();
        if (size > 1) {
            final IntIterator[] array = new IntIterator[size];
            for (int i = 0; i < array.length; ++i) {
                array[i] = getOptimizedIterator(table, orPredicate.get(i));
                if (array[i] == null) {
                    return null;
                }
            }
            return new UniqueRowIterator(new CompositeIntIterator(array));
        }
        if (size == 1) {
            return getOptimizedIterator(table, orPredicate.get(0));
        }
        return null;
    }
    
    protected static IntIterator getAndIterator(final Table table, final AndPredicate andPredicate) {
        IntIterator optimizedIterator = null;
        Predicate value = null;
        int size = andPredicate.size();
        while (--size >= 0) {
            value = andPredicate.get(size);
            if ((optimizedIterator = getOptimizedIterator(table, value)) != null) {
                break;
            }
        }
        if (optimizedIterator == null) {
            return null;
        }
        if (andPredicate.size() == 1) {
            return optimizedIterator;
        }
        return new FilterRowIterator(optimizedIterator, table, andPredicate.getSubPredicate(value));
    }
    
    protected static IntIterator getComparisonIterator(final Table table, final ComparisonPredicate comparisonPredicate) {
        final Expression leftExpression = comparisonPredicate.getLeftExpression();
        final Expression rightExpression = comparisonPredicate.getRightExpression();
        final int operation = comparisonPredicate.getOperation();
        if (operation == 3) {
            return null;
        }
        ColumnExpression columnExpression;
        Expression expression;
        if (leftExpression instanceof ColumnExpression && !ExpressionAnalyzer.hasDependency(rightExpression)) {
            columnExpression = (ColumnExpression)leftExpression;
            expression = rightExpression;
        }
        else {
            if (!(rightExpression instanceof ColumnExpression) || ExpressionAnalyzer.hasDependency(leftExpression)) {
                return null;
            }
            columnExpression = (ColumnExpression)rightExpression;
            expression = leftExpression;
        }
        final Comparator comparator = comparisonPredicate.getComparator();
        final Index index = table.getIndex(columnExpression.getColumnName());
        if (index == null || !comparator.equals(index.getComparator())) {
            return null;
        }
        final Class<? extends Expression> class1 = expression.getClass();
        if (class1 == Integer.TYPE) {
            final int int1 = expression.getInt(null);
            switch (operation) {
                case 0: {
                    return index.rows(Integer.MIN_VALUE, int1, 41);
                }
                case 1: {
                    return index.rows(int1, Integer.MAX_VALUE, 38);
                }
                case 2: {
                    return index.rows(int1, int1, 42);
                }
                case 4: {
                    return index.rows(Integer.MIN_VALUE, int1, 42);
                }
                case 5: {
                    return index.rows(int1, Integer.MAX_VALUE, 42);
                }
                default: {
                    throw new IllegalStateException();
                }
            }
        }
        else if (class1 == Long.TYPE) {
            final long long1 = expression.getLong(null);
            switch (operation) {
                case 0: {
                    return index.rows(Long.MIN_VALUE, long1, 41);
                }
                case 1: {
                    return index.rows(long1, Long.MAX_VALUE, 38);
                }
                case 2: {
                    return index.rows(long1, long1, 42);
                }
                case 4: {
                    return index.rows(Long.MIN_VALUE, long1, 42);
                }
                case 5: {
                    return index.rows(long1, Long.MAX_VALUE, 42);
                }
                default: {
                    throw new IllegalStateException();
                }
            }
        }
        else if (class1 == Float.TYPE) {
            final float float1 = expression.getFloat(null);
            switch (operation) {
                case 0: {
                    return index.rows(Float.MIN_VALUE, float1, 41);
                }
                case 1: {
                    return index.rows(float1, Float.MAX_VALUE, 38);
                }
                case 2: {
                    return index.rows(float1, float1, 42);
                }
                case 4: {
                    return index.rows(Float.MIN_VALUE, float1, 42);
                }
                case 5: {
                    return index.rows(float1, Float.MAX_VALUE, 42);
                }
                default: {
                    throw new IllegalStateException();
                }
            }
        }
        else if (class1 == Double.TYPE) {
            final double double1 = expression.getDouble(null);
            switch (operation) {
                case 0: {
                    return index.rows(Double.MIN_VALUE, double1, 41);
                }
                case 1: {
                    return index.rows(double1, Double.MAX_VALUE, 38);
                }
                case 2: {
                    return index.rows(double1, double1, 42);
                }
                case 4: {
                    return index.rows(Double.MIN_VALUE, double1, 42);
                }
                case 5: {
                    return index.rows(double1, Double.MAX_VALUE, 42);
                }
                default: {
                    throw new IllegalStateException();
                }
            }
        }
        else {
            final Object value = expression.get(null);
            switch (operation) {
                case 0: {
                    return index.rows(null, value, 41);
                }
                case 1: {
                    return index.rows(value, null, 38);
                }
                case 2: {
                    return index.rows(value, value, 42);
                }
                case 4: {
                    return index.rows(null, value, 42);
                }
                case 5: {
                    return index.rows(value, null, 42);
                }
                default: {
                    throw new IllegalStateException();
                }
            }
        }
    }
    
    protected static IntIterator getRangeIterator(final Table table, final RangePredicate rangePredicate) {
        if (!(rangePredicate.getMiddleExpression() instanceof ColumnExpression) || ExpressionAnalyzer.hasDependency(rangePredicate.getLeftExpression()) || ExpressionAnalyzer.hasDependency(rangePredicate.getRightExpression())) {
            return null;
        }
        final ColumnExpression columnExpression = (ColumnExpression)rangePredicate.getMiddleExpression();
        final Expression leftExpression = rangePredicate.getLeftExpression();
        final Expression rightExpression = rangePredicate.getRightExpression();
        final Comparator comparator = rangePredicate.getComparator();
        final Index index = table.getIndex(columnExpression.getColumnName());
        if (index == null || !comparator.equals(index.getComparator())) {
            return null;
        }
        final int operation = rangePredicate.getOperation();
        final Class columnType = table.getColumnType(columnExpression.getColumnName());
        int n = 0;
        switch (operation) {
            case 0: {
                n = 42;
                break;
            }
            case 1: {
                n = 41;
                break;
            }
            case 2: {
                n = 38;
                break;
            }
            case 3: {
                n = 37;
                break;
            }
            default: {
                throw new IllegalStateException();
            }
        }
        if (columnType == Integer.TYPE) {
            return index.rows(leftExpression.getInt(null), rightExpression.getInt(null), n);
        }
        if (columnType == Long.TYPE) {
            return index.rows(leftExpression.getLong(null), rightExpression.getLong(null), n);
        }
        if (columnType == Float.TYPE) {
            return index.rows(leftExpression.getFloat(null), rightExpression.getFloat(null), n);
        }
        if (columnType == Double.TYPE) {
            return index.rows(leftExpression.getDouble(null), rightExpression.getDouble(null), n);
        }
        return index.rows(leftExpression.get(null), rightExpression.get(null), n);
    }
    
    static {
        OPTIMIZATION_THRESHOLD = PrefuseConfig.getInt("data.filter.optimizeThreshold");
    }
}
