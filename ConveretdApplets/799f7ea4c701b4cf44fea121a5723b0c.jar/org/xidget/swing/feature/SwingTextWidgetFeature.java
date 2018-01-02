// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.feature;

import org.xidget.ifeature.IWidgetCreationFeature;
import java.util.Iterator;
import java.util.List;
import org.xidget.Creator;
import java.util.EnumSet;
import javax.swing.JComponent;
import java.awt.Font;
import org.xidget.IXidget;
import org.xmodel.log.Log;
import org.xidget.ifeature.ITextWidgetFeature;

public abstract class SwingTextWidgetFeature implements ITextWidgetFeature
{
    public static final Log log;
    protected IXidget xidget;
    
    static {
        log = Log.getLog(SwingTextWidgetFeature.class);
    }
    
    protected SwingTextWidgetFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void setFontFamily(final String s) {
        final JComponent primaryWidget = getPrimaryWidget(this.xidget);
        final Font font = primaryWidget.getFont();
        primaryWidget.setFont(new Font(this.matchFamily(s), font.getStyle(), font.getSize()));
    }
    
    @Override
    public void setFontStyles(final EnumSet<FontStyle> set) {
        int n = 0;
        if (set.contains(FontStyle.italic)) {
            n |= 0x2;
        }
        if (set.contains(FontStyle.bold)) {
            n |= 0x1;
        }
        final JComponent primaryWidget = getPrimaryWidget(this.xidget);
        primaryWidget.setFont(primaryWidget.getFont().deriveFont(n));
    }
    
    @Override
    public void setFontSize(final double n) {
        final JComponent primaryWidget = getPrimaryWidget(this.xidget);
        primaryWidget.setFont(primaryWidget.getFont().deriveFont((float)n));
    }
    
    public String matchFamily(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final List<String> fonts = Creator.getToolkit().getFonts();
        for (final String s : fonts) {
            if (s.toLowerCase().contains(lowerCase)) {
                return s;
            }
        }
        SwingTextWidgetFeature.log.errorf("Unknown font family, '%s'.", lowerCase);
        SwingTextWidgetFeature.log.verbose("Available Font Families: ");
        final Iterator<String> iterator2 = fonts.iterator();
        while (iterator2.hasNext()) {
            SwingTextWidgetFeature.log.verbose(iterator2.next());
        }
        return null;
    }
    
    private static JComponent getPrimaryWidget(final IXidget xidget) {
        final Object[] lastWidgets = xidget.getFeature(IWidgetCreationFeature.class).getLastWidgets();
        return (JComponent)lastWidgets[lastWidgets.length - 1];
    }
    
    @Override
    public String toString() {
        return this.xidget.toString();
    }
}
