// 
// Decompiled by Procyon v0.5.30
// 

package kiang.chinese.font;

import kiang.swing.JFontChooser;
import java.awt.GraphicsEnvironment;
import java.awt.Font;
import java.awt.Component;

public class ChineseFontChooserFactory
{
    public static Font showDialog(final Component owner) {
        final Font initialFont = owner.getFont();
        final String defaultPreviewString = "\u6c49  \u6f22";
        final int[] defaultSizeOptions = { 8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72 };
        final Font[] systemFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        return showDialog(owner, initialFont, systemFonts, defaultSizeOptions, defaultPreviewString);
    }
    
    public static Font showDialog(final Component owner, final Font initialFont, final Font[] fontOptions, final int[] sizeOptions, final String previewString) {
        final JFontChooser.FontFilter[] chineseFilters = getChineseFilters(initialFont);
        return JFontChooser.showDialog(owner, initialFont, fontOptions, sizeOptions, chineseFilters, previewString);
    }
    
    static JFontChooser getInstance(final Font initialFont, final Font[] fontOptions, final int[] sizeOptions, final String previewString) {
        final JFontChooser.FontFilter[] chineseFilters = getChineseFilters(initialFont);
        return new JFontChooser(initialFont, fontOptions, sizeOptions, chineseFilters, previewString);
    }
    
    private static JFontChooser.FontFilter[] getChineseFilters(final Font initialFont) {
        return new JFontChooser.FontFilter[] { new SimplifiedFontFilter(initialFont, null), new TraditionalFontFilter(initialFont, null) };
    }
    
    private static class SimplifiedFontFilter implements JFontChooser.FontFilter
    {
        private Font initialFont;
        
        private SimplifiedFontFilter(final Font initialFont) {
            this.initialFont = initialFont;
        }
        
        public String getDisplayName() {
            return "Simplified";
        }
        
        public boolean isDefaultOn() {
            return this.shouldInclude(this.initialFont);
        }
        
        public boolean shouldInclude(final Font font) {
            return ChineseFontFinder.isSimplifiedFont(font);
        }
    }
    
    private static class TraditionalFontFilter implements JFontChooser.FontFilter
    {
        private Font initialFont;
        
        private TraditionalFontFilter(final Font initialFont) {
            this.initialFont = initialFont;
        }
        
        public String getDisplayName() {
            return "Traditional";
        }
        
        public boolean isDefaultOn() {
            return this.shouldInclude(this.initialFont);
        }
        
        public boolean shouldInclude(final Font font) {
            return ChineseFontFinder.isTraditionalFont(font);
        }
    }
}
