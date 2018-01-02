// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.assignment;

import java.util.logging.Logger;
import prefuse.visual.VisualItem;
import prefuse.data.expression.parser.ExpressionParser;
import prefuse.util.PrefuseLib;
import prefuse.data.expression.Predicate;
import prefuse.util.ColorLib;
import prefuse.action.EncoderAction;

public class ColorAction extends EncoderAction
{
    protected String m_colorField;
    protected String m_startField;
    protected String m_endField;
    protected int m_cidx;
    protected int m_sidx;
    protected int m_eidx;
    protected int m_defaultColor;
    
    public ColorAction(final String s, final String field) {
        super(s);
        this.m_defaultColor = ColorLib.gray(0);
        this.setField(field);
    }
    
    public ColorAction(final String s, final String s2, final int defaultColor) {
        this(s, s2);
        this.m_defaultColor = defaultColor;
    }
    
    public ColorAction(final String s, final Predicate predicate, final String field) {
        super(s, predicate);
        this.m_defaultColor = ColorLib.gray(0);
        this.setField(field);
    }
    
    public ColorAction(final String s, final Predicate predicate, final String s2, final int defaultColor) {
        this(s, predicate, s2);
        this.setDefaultColor(defaultColor);
    }
    
    public void setField(final String colorField) {
        this.m_colorField = colorField;
        this.m_startField = PrefuseLib.getStartField(colorField);
        this.m_endField = PrefuseLib.getEndField(colorField);
    }
    
    public int getDefaultColor() {
        return this.m_defaultColor;
    }
    
    public void setDefaultColor(final int defaultColor) {
        this.m_defaultColor = defaultColor;
    }
    
    public void add(final Predicate predicate, final int n) {
        super.add(predicate, new Integer(n));
    }
    
    public void add(final String s, final int n) {
        this.add((Predicate)ExpressionParser.parse(s), n);
    }
    
    public void add(final Predicate predicate, final ColorAction colorAction) {
        super.add(predicate, colorAction);
    }
    
    public void add(final String s, final ColorAction colorAction) {
        super.add((Predicate)ExpressionParser.parse(s), colorAction);
    }
    
    public void process(final VisualItem visualItem, final double n) {
        final int color = this.getColor(visualItem);
        visualItem.setInt(this.m_startField, visualItem.getInt(this.m_colorField));
        visualItem.setInt(this.m_endField, color);
        visualItem.setInt(this.m_colorField, color);
    }
    
    public int getColor(final VisualItem visualItem) {
        final Object lookup = this.lookup(visualItem);
        if (lookup != null) {
            if (lookup instanceof ColorAction) {
                return ((ColorAction)lookup).getColor(visualItem);
            }
            if (lookup instanceof Integer) {
                return (int)lookup;
            }
            Logger.getLogger(this.getClass().getName()).warning("Unrecognized Object from predicate chain.");
        }
        return this.m_defaultColor;
    }
}
