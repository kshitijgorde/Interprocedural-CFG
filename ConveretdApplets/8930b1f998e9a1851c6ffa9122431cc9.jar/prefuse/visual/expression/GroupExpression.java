// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual.expression;

import prefuse.data.Tuple;
import prefuse.data.expression.ObjectLiteral;
import prefuse.data.expression.Expression;
import java.util.logging.Logger;
import prefuse.data.expression.Function;
import prefuse.data.expression.AbstractExpression;

public abstract class GroupExpression extends AbstractExpression implements Function
{
    private static final Logger s_logger;
    protected Expression m_group;
    
    protected GroupExpression() {
        this.m_group = null;
    }
    
    protected GroupExpression(final String s) {
        this.m_group = new ObjectLiteral(s);
    }
    
    protected String getGroup(final Tuple tuple) {
        final String s = (String)this.m_group.get(tuple);
        if (s == null) {
            GroupExpression.s_logger.warning("Null group lookup");
        }
        return s;
    }
    
    public void addParameter(final Expression group) {
        if (this.m_group == null) {
            this.m_group = group;
            return;
        }
        throw new IllegalStateException("This function takes only 1 parameter.");
    }
    
    public int getParameterCount() {
        return 1;
    }
    
    public String toString() {
        return this.getName() + "(" + this.m_group + ")";
    }
    
    static {
        s_logger = Logger.getLogger(GroupExpression.class.getName());
    }
}
