// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

import org.jfree.util.ObjectUtils;
import org.jfree.ui.TextAnchor;
import org.jfree.text.TextBlockAnchor;
import org.jfree.text.TextBlock;

public class CategoryTick extends Tick
{
    private Comparable category;
    private TextBlock label;
    private TextBlockAnchor labelAnchor;
    
    public CategoryTick(final Comparable category, final TextBlock label, final TextBlockAnchor labelAnchor, final TextAnchor rotationAnchor, final double angle) {
        super("", TextAnchor.CENTER, rotationAnchor, angle);
        this.category = category;
        this.label = label;
        this.labelAnchor = labelAnchor;
    }
    
    public Comparable getCategory() {
        return this.category;
    }
    
    public TextBlock getLabel() {
        return this.label;
    }
    
    public TextBlockAnchor getLabelAnchor() {
        return this.labelAnchor;
    }
    
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CategoryTick && super.equals(obj)) {
            final CategoryTick ct = (CategoryTick)obj;
            return ObjectUtils.equal(this.category, ct.category) && ObjectUtils.equal(this.label, ct.label) && ObjectUtils.equal(this.labelAnchor, ct.labelAnchor);
        }
        return false;
    }
}
