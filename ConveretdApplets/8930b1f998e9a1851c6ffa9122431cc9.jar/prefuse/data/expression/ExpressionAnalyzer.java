// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import java.util.HashSet;
import java.util.Collections;
import java.util.Set;

public class ExpressionAnalyzer
{
    public static boolean hasDependency(final Expression expression) {
        final ColumnCollector columnCollector = new ColumnCollector(false);
        expression.visit(columnCollector);
        return columnCollector.getColumnCount() > 0;
    }
    
    public static Set getReferencedColumns(final Expression expression) {
        final ColumnCollector columnCollector = new ColumnCollector(true);
        expression.visit(columnCollector);
        return columnCollector.getColumnSet();
    }
    
    private static class ColumnCollector implements ExpressionVisitor
    {
        private boolean store;
        private Set m_cols;
        private int m_count;
        
        public ColumnCollector(final boolean store) {
            this.store = store;
        }
        
        public int getColumnCount() {
            return this.m_count;
        }
        
        public Set getColumnSet() {
            if (this.m_cols == null) {
                return Collections.EMPTY_SET;
            }
            return this.m_cols;
        }
        
        public void visitExpression(final Expression expression) {
            if (expression instanceof ColumnExpression) {
                ++this.m_count;
                if (this.store) {
                    final String columnName = ((ColumnExpression)expression).getColumnName();
                    if (this.m_cols == null) {
                        this.m_cols = new HashSet();
                    }
                    this.m_cols.add(columnName);
                }
            }
        }
        
        public void down() {
        }
        
        public void up() {
        }
    }
}
