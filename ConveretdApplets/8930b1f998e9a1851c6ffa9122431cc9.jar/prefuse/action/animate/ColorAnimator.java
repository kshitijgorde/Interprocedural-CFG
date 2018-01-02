// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.animate;

import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;
import prefuse.util.PrefuseLib;
import prefuse.util.collections.CopyOnWriteArrayList;
import prefuse.action.ItemAction;

public class ColorAnimator extends ItemAction
{
    private static final String[] DEFAULTS;
    private CopyOnWriteArrayList m_colorFields;
    
    public ColorAnimator() {
        this.setColorFields(ColorAnimator.DEFAULTS);
    }
    
    public ColorAnimator(final String s) {
        super(s);
        this.setColorFields(ColorAnimator.DEFAULTS);
    }
    
    public ColorAnimator(final String s, final String s2) {
        super(s);
        this.setColorFields(new String[] { s2 });
    }
    
    public ColorAnimator(final String s, final String[] colorFields) {
        super(s);
        this.setColorFields(colorFields);
    }
    
    public void setColorFields(final String[] array) {
        if (array == null) {
            throw new IllegalArgumentException();
        }
        if (this.m_colorFields == null) {
            this.m_colorFields = new CopyOnWriteArrayList();
        }
        else {
            this.m_colorFields.clear();
        }
        for (int i = 0; i < array.length; ++i) {
            this.m_colorFields.add(array[i]);
            this.m_colorFields.add(PrefuseLib.getStartField(array[i]));
            this.m_colorFields.add(PrefuseLib.getEndField(array[i]));
        }
    }
    
    public void process(final VisualItem visualItem, final double n) {
        if (this.m_colorFields == null) {
            return;
        }
        final Object[] array = this.m_colorFields.getArray();
        for (int i = 0; i < array.length; i += 3) {
            visualItem.setInt((String)array[i], ColorLib.interp(visualItem.getInt((String)array[i + 1]), visualItem.getInt((String)array[i + 2]), n));
        }
    }
    
    static {
        DEFAULTS = new String[] { VisualItem.STROKECOLOR, VisualItem.FILLCOLOR, VisualItem.TEXTCOLOR };
    }
}
