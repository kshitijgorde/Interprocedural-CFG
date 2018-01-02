// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.assignment;

import java.util.logging.Logger;
import prefuse.visual.VisualItem;
import prefuse.data.expression.parser.ExpressionParser;
import prefuse.data.expression.Predicate;
import prefuse.util.FontLib;
import java.awt.Font;
import prefuse.action.EncoderAction;

public class FontAction extends EncoderAction
{
    protected Font defaultFont;
    
    public FontAction() {
        this.defaultFont = FontLib.getFont("SansSerif", 0, 10);
    }
    
    public FontAction(final String s) {
        super(s);
        this.defaultFont = FontLib.getFont("SansSerif", 0, 10);
    }
    
    public FontAction(final String s, final Font defaultFont) {
        super(s);
        this.defaultFont = FontLib.getFont("SansSerif", 0, 10);
        this.defaultFont = defaultFont;
    }
    
    public void setDefaultFont(final Font defaultFont) {
        this.defaultFont = defaultFont;
    }
    
    public Font getDefaultFont() {
        return this.defaultFont;
    }
    
    public void add(final Predicate predicate, final Font font) {
        super.add(predicate, font);
    }
    
    public void add(final String s, final Font font) {
        super.add((Predicate)ExpressionParser.parse(s), font);
    }
    
    public void add(final Predicate predicate, final FontAction fontAction) {
        super.add(predicate, fontAction);
    }
    
    public void add(final String s, final FontAction fontAction) {
        super.add((Predicate)ExpressionParser.parse(s), fontAction);
    }
    
    public void process(final VisualItem visualItem, final double n) {
        final Font font = this.getFont(visualItem);
        visualItem.setStartFont(visualItem.getFont());
        visualItem.setEndFont(font);
        visualItem.setFont(font);
    }
    
    public Font getFont(final VisualItem visualItem) {
        final Object lookup = this.lookup(visualItem);
        if (lookup != null) {
            if (lookup instanceof FontAction) {
                return ((FontAction)lookup).getFont(visualItem);
            }
            if (lookup instanceof Font) {
                return (Font)lookup;
            }
            Logger.getLogger(this.getClass().getName()).warning("Unrecognized Object from predicate chain.");
        }
        return this.defaultFont;
    }
}
