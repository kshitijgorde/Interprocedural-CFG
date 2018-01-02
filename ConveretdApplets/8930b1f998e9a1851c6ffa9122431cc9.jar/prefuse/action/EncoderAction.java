// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action;

import prefuse.data.expression.ObjectLiteral;
import prefuse.data.expression.Expression;
import prefuse.data.expression.ExpressionVisitor;
import prefuse.data.Tuple;
import prefuse.visual.VisualItem;
import prefuse.data.expression.Predicate;
import prefuse.Visualization;
import prefuse.util.PredicateChain;

public abstract class EncoderAction extends ItemAction
{
    private PredicateChain m_chain;
    
    public EncoderAction() {
        this.m_chain = null;
    }
    
    public EncoderAction(final Visualization visualization) {
        super(visualization);
        this.m_chain = null;
    }
    
    public EncoderAction(final String s) {
        super(s);
        this.m_chain = null;
    }
    
    public EncoderAction(final String s, final Predicate predicate) {
        super(s, predicate);
        this.m_chain = null;
    }
    
    public EncoderAction(final Visualization visualization, final String s) {
        super(visualization, s);
        this.m_chain = null;
    }
    
    public EncoderAction(final Visualization visualization, final String s, final Predicate predicate) {
        super(visualization, s, predicate);
        this.m_chain = null;
    }
    
    protected void add(final Predicate predicate, final Object o) {
        if (this.m_chain == null) {
            this.m_chain = new PredicateChain();
        }
        if (o instanceof Action) {
            ((Action)o).setVisualization(this.m_vis);
        }
        this.m_chain.add(predicate, o);
    }
    
    protected Object lookup(final VisualItem visualItem) {
        return (this.m_chain == null) ? null : this.m_chain.get(visualItem);
    }
    
    public void clear() {
        if (this.m_chain != null) {
            this.m_chain.clear();
        }
    }
    
    public boolean remove(final Predicate predicate) {
        return this.m_chain != null && this.m_chain.remove(predicate);
    }
    
    public void setVisualization(final Visualization visualization) {
        super.setVisualization(visualization);
        if (this.m_chain != null) {
            this.m_chain.getExpression().visit(new SetVisualizationVisitor());
        }
    }
    
    public void run(final double n) {
        this.setup();
        if (this.m_chain != null) {
            this.m_chain.getExpression().visit(SetupVisitor.getInstance());
        }
        super.run(n);
        if (this.m_chain != null) {
            this.m_chain.getExpression().visit(FinishVisitor.getInstance());
        }
        this.finish();
    }
    
    protected void setup() {
    }
    
    protected void finish() {
    }
    
    private static class FinishVisitor extends ActionVisitor
    {
        private static FinishVisitor s_instance;
        
        public static FinishVisitor getInstance() {
            if (FinishVisitor.s_instance == null) {
                FinishVisitor.s_instance = new FinishVisitor();
            }
            return FinishVisitor.s_instance;
        }
        
        public void visitAction(final Action action) {
            if (action instanceof EncoderAction) {
                ((EncoderAction)action).setup();
            }
        }
    }
    
    private abstract static class ActionVisitor implements ExpressionVisitor
    {
        public void visitExpression(final Expression expression) {
            if (expression instanceof ObjectLiteral) {
                final Object value = expression.get(null);
                if (value instanceof Action) {
                    this.visitAction((Action)value);
                }
            }
        }
        
        public abstract void visitAction(final Action p0);
        
        public void down() {
        }
        
        public void up() {
        }
    }
    
    private static class SetupVisitor extends ActionVisitor
    {
        private static SetupVisitor s_instance;
        
        public static SetupVisitor getInstance() {
            if (SetupVisitor.s_instance == null) {
                SetupVisitor.s_instance = new SetupVisitor();
            }
            return SetupVisitor.s_instance;
        }
        
        public void visitAction(final Action action) {
            if (action instanceof EncoderAction) {
                ((EncoderAction)action).setup();
            }
        }
    }
    
    private class SetVisualizationVisitor extends ActionVisitor
    {
        public void visitAction(final Action action) {
            action.setVisualization(EncoderAction.this.m_vis);
        }
    }
}
