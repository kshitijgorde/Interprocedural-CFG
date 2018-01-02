// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import javax.swing.text.AttributeSet;

public class Option
{
    private boolean selected;
    private String label;
    private AttributeSet attr;
    
    public Option(final AttributeSet set) {
        this.attr = set.copyAttributes();
        this.selected = (set.getAttribute(HTML.Attribute.SELECTED) != null);
    }
    
    public AttributeSet getAttributes() {
        return this.attr;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public String getValue() {
        String label = (String)this.attr.getAttribute(HTML.Attribute.VALUE);
        if (label == null) {
            label = this.label;
        }
        return label;
    }
    
    public boolean isSelected() {
        return this.selected;
    }
    
    public void setLabel(final String label) {
        this.label = label;
    }
    
    protected void setSelection(final boolean selected) {
        this.selected = selected;
    }
    
    public String toString() {
        return this.label;
    }
}
