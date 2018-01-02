// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.feature;

import java.util.Iterator;
import org.xidget.ifeature.IWidgetCreationFeature;
import java.awt.Font;
import java.util.EnumSet;
import javax.swing.JComponent;
import org.xidget.IXidget;
import org.xidget.ifeature.ITextWidgetFeature;

public class SwingContainerTextWidgetFeature implements ITextWidgetFeature
{
    private IXidget xidget;
    
    public SwingContainerTextWidgetFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void setHAlign(final HAlign hAlign) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void setVAlign(final VAlign vAlign) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void setEditable(final boolean b) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void setFontFamily(final String s) {
        this.setChildrenFonts(this.xidget.getFeature(JComponent.class).getFont());
    }
    
    @Override
    public void setFontStyles(final EnumSet<FontStyle> set) {
        this.setChildrenFonts(this.xidget.getFeature(JComponent.class).getFont());
    }
    
    @Override
    public void setFontSize(final double n) {
        this.setChildrenFonts(this.xidget.getFeature(JComponent.class).getFont());
    }
    
    private void setChildrenFonts(final Font font) {
        final Iterator<IXidget> iterator = this.xidget.getChildren().iterator();
        while (iterator.hasNext()) {
            final Object[] lastWidgets = iterator.next().getFeature(IWidgetCreationFeature.class).getLastWidgets();
            ((JComponent)lastWidgets[lastWidgets.length - 1]).setFont(font);
        }
    }
}
