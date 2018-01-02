// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.assignment;

import java.util.logging.Logger;
import java.awt.Stroke;
import prefuse.visual.VisualItem;
import prefuse.data.expression.parser.ExpressionParser;
import prefuse.data.expression.Predicate;
import prefuse.util.StrokeLib;
import java.awt.BasicStroke;
import prefuse.action.EncoderAction;

public class StrokeAction extends EncoderAction
{
    protected BasicStroke defaultStroke;
    
    public StrokeAction() {
        this.defaultStroke = StrokeLib.getStroke(1.0f);
    }
    
    public StrokeAction(final String s) {
        super(s);
        this.defaultStroke = StrokeLib.getStroke(1.0f);
    }
    
    public StrokeAction(final String s, final BasicStroke defaultStroke) {
        super(s);
        this.defaultStroke = StrokeLib.getStroke(1.0f);
        this.defaultStroke = defaultStroke;
    }
    
    public void setDefaultStroke(final BasicStroke defaultStroke) {
        this.defaultStroke = defaultStroke;
    }
    
    public BasicStroke getDefaultStroke() {
        return this.defaultStroke;
    }
    
    public void add(final Predicate predicate, final BasicStroke basicStroke) {
        super.add(predicate, basicStroke);
    }
    
    public void add(final String s, final BasicStroke basicStroke) {
        this.add((Predicate)ExpressionParser.parse(s), basicStroke);
    }
    
    public void add(final Predicate predicate, final StrokeAction strokeAction) {
        super.add(predicate, strokeAction);
    }
    
    public void add(final String s, final StrokeAction strokeAction) {
        super.add((Predicate)ExpressionParser.parse(s), strokeAction);
    }
    
    public void process(final VisualItem visualItem, final double n) {
        visualItem.setStroke(this.getStroke(visualItem));
    }
    
    public BasicStroke getStroke(final VisualItem visualItem) {
        final Object lookup = this.lookup(visualItem);
        if (lookup != null) {
            if (lookup instanceof StrokeAction) {
                return ((StrokeAction)lookup).getStroke(visualItem);
            }
            if (lookup instanceof Stroke) {
                return (BasicStroke)lookup;
            }
            Logger.getLogger(this.getClass().getName()).warning("Unrecognized Object from predicate chain.");
        }
        return this.defaultStroke;
    }
}
