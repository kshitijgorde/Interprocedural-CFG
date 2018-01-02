// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.assignment;

import java.util.logging.Logger;
import prefuse.visual.VisualItem;
import prefuse.data.expression.parser.ExpressionParser;
import prefuse.data.expression.Predicate;
import prefuse.action.EncoderAction;

public class ShapeAction extends EncoderAction
{
    protected int m_defaultShape;
    
    public ShapeAction() {
        this.m_defaultShape = 0;
    }
    
    public ShapeAction(final String s) {
        super(s);
        this.m_defaultShape = 0;
    }
    
    public ShapeAction(final String s, final int defaultShape) {
        super(s);
        this.m_defaultShape = 0;
        this.m_defaultShape = defaultShape;
    }
    
    public int getDefaultSize() {
        return this.m_defaultShape;
    }
    
    public void setDefaultShape(final int defaultShape) {
        this.m_defaultShape = defaultShape;
    }
    
    public void add(final Predicate predicate, final int n) {
        super.add(predicate, new Integer(n));
    }
    
    public void add(final String s, final int n) {
        this.add((Predicate)ExpressionParser.parse(s), n);
    }
    
    public void add(final Predicate predicate, final ShapeAction shapeAction) {
        super.add(predicate, shapeAction);
    }
    
    public void add(final String s, final ShapeAction shapeAction) {
        super.add((Predicate)ExpressionParser.parse(s), shapeAction);
    }
    
    public void process(final VisualItem visualItem, final double n) {
        visualItem.setShape(this.getShape(visualItem));
    }
    
    public int getShape(final VisualItem visualItem) {
        final Object lookup = this.lookup(visualItem);
        if (lookup != null) {
            if (lookup instanceof ShapeAction) {
                return ((ShapeAction)lookup).getShape(visualItem);
            }
            if (lookup instanceof Number) {
                return ((Number)lookup).intValue();
            }
            Logger.getLogger(this.getClass().getName()).warning("Unrecognized Object from predicate chain.");
        }
        return this.m_defaultShape;
    }
}
