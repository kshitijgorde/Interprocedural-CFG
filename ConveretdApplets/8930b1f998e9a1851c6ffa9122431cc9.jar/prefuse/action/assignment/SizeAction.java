// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.assignment;

import java.util.logging.Logger;
import prefuse.visual.VisualItem;
import prefuse.data.expression.parser.ExpressionParser;
import prefuse.data.expression.Predicate;
import prefuse.action.EncoderAction;

public class SizeAction extends EncoderAction
{
    protected double m_defaultSize;
    
    public SizeAction() {
        this.m_defaultSize = 1.0;
    }
    
    public SizeAction(final String s) {
        super(s);
        this.m_defaultSize = 1.0;
    }
    
    public SizeAction(final String s, final double defaultSize) {
        super(s);
        this.m_defaultSize = 1.0;
        this.m_defaultSize = defaultSize;
    }
    
    public double getDefaultSize() {
        return this.m_defaultSize;
    }
    
    public void setDefaultSize(final double defaultSize) {
        this.m_defaultSize = defaultSize;
    }
    
    public void add(final Predicate predicate, final double n) {
        super.add(predicate, new Double(n));
    }
    
    public void add(final String s, final double n) {
        this.add((Predicate)ExpressionParser.parse(s), n);
    }
    
    public void add(final Predicate predicate, final SizeAction sizeAction) {
        super.add(predicate, sizeAction);
    }
    
    public void add(final String s, final SizeAction sizeAction) {
        super.add((Predicate)ExpressionParser.parse(s), sizeAction);
    }
    
    public void process(final VisualItem visualItem, final double n) {
        final double size = this.getSize(visualItem);
        visualItem.setStartSize(visualItem.getSize());
        visualItem.setEndSize(size);
        visualItem.setSize(size);
    }
    
    public double getSize(final VisualItem visualItem) {
        final Object lookup = this.lookup(visualItem);
        if (lookup != null) {
            if (lookup instanceof SizeAction) {
                return ((SizeAction)lookup).getSize(visualItem);
            }
            if (lookup instanceof Number) {
                return ((Number)lookup).doubleValue();
            }
            Logger.getLogger(this.getClass().getName()).warning("Unrecognized Object from predicate chain.");
        }
        return this.m_defaultSize;
    }
}
