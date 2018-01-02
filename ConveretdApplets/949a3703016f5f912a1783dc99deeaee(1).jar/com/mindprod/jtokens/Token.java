// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens;

import com.mindprod.entities.EntifyStrings;
import java.util.Collection;
import java.util.Arrays;
import java.awt.GraphicsEnvironment;
import com.mindprod.common15.FontFactory;
import java.awt.FontMetrics;
import com.mindprod.common11.ST;
import java.awt.Component;
import java.awt.Color;
import java.util.HashSet;
import java.awt.Panel;
import java.util.HashMap;
import java.awt.Font;
import java.io.Serializable;

public abstract class Token implements Cloneable, Serializable
{
    static final boolean DEBUGGING = false;
    public static final long serialVersionUID = 28L;
    private static final Font lineNumberFont;
    private static final HashMap<String, Float> adjustFontSize;
    private static final Panel dummyPanelForFontMetrics;
    private static HashSet<String> allFontFamilies;
    protected String text;
    
    public static Font getLineNumberFont() {
        return Token.lineNumberFont;
    }
    
    public static Color getLineNumberForeground() {
        return TokenColourScheme.FOREGROUND_FOR_LINE_NUMBER;
    }
    
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            return null;
        }
    }
    
    public abstract Font getFont();
    
    public abstract Color getForeground();
    
    public String getHTML() {
        return this.getRawHTML();
    }
    
    public String getName() {
        String className = this.getClass().toString().trim();
        if (className.startsWith("final")) {
            className = className.substring("final".length()).trim();
        }
        if (className.startsWith("class")) {
            className = className.substring("class".length()).trim();
        }
        if (className.startsWith("com.mindprod.jtokens.")) {
            className = className.substring("com.mindprod.jtokens.".length()).trim();
        }
        return className;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(final String text) {
        this.text = text;
    }
    
    public String getTrimmedText() {
        return this.getText().trim();
    }
    
    public boolean isCollapsible() {
        return true;
    }
    
    public boolean isCollapsible(final Token t) {
        return this.isCollapsible() && this.getClass() == t.getClass();
    }
    
    public boolean isUseless() {
        return this.text == null || this.text.length() == 0;
    }
    
    public String toString() {
        final String text = this.getText();
        final FontMetrics fm = getFontMetrics(this.getFont(), Token.dummyPanelForFontMetrics);
        final int widthInPixels = fm.stringWidth(text);
        return "Token: " + this.getName() + "\n" + "                text: " + this.getText() + "\n" + "                html: " + this.getHTML() + "\n" + "                font: " + this.getFont().toString().substring("java.awt.Font".length()) + "\n" + "                color: " + ST.toString(this.getForeground()) + "   width: " + widthInPixels + "\n";
    }
    
    private static float adjust(final int eHeight) {
        return 121.520004f / eHeight;
    }
    
    protected static Font bestFont(final String[] preferredfontNames, final int fontStyle, int fontSize) {
        if (Token.allFontFamilies == null) {
            Token.allFontFamilies = findAllFontFamilies();
        }
        for (final String preferredFontName : preferredfontNames) {
            if (Token.allFontFamilies.contains(preferredFontName)) {
                final Float adjustment = Token.adjustFontSize.get(preferredFontName);
                if (adjustment == null) {
                    System.err.println("Program bug. Unknown font. Cannot adjust true size. " + preferredFontName);
                    System.exit(2);
                }
                fontSize = (int)(fontSize * adjustment + 0.5f);
                return FontFactory.build(preferredFontName, fontStyle, fontSize);
            }
        }
        return FontFactory.build("Dialog", fontStyle, fontSize);
    }
    
    private static HashSet<String> findAllFontFamilies() {
        final String[] names = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        return new HashSet<String>(Arrays.asList(names));
    }
    
    static FontMetrics getFontMetrics(final Font font, final Component panel) {
        if (font == null) {
            throw new IllegalArgumentException("null Font");
        }
        final FontMetrics fm = panel.getFontMetrics(font);
        if (fm == null) {
            throw new IllegalArgumentException("no FontMetrics available for font " + font);
        }
        return fm;
    }
    
    protected Token(final char c) {
        this.text = String.valueOf(c);
    }
    
    protected Token(final String text) {
        this.text = text;
    }
    
    protected String getRawHTML() {
        return EntifyStrings.entifyHTML(this.getText());
    }
    
    static {
        adjustFontSize = new HashMap<String, Float>(25);
        dummyPanelForFontMetrics = new Panel();
        Token.adjustFontSize.put("Arial", adjust(112));
        Token.adjustFontSize.put("Bitstream Vera Sans", adjust(117));
        Token.adjustFontSize.put("Bitstream Vera Sans Mono", adjust(117));
        Token.adjustFontSize.put("Bitstream Vera Serif", adjust(112));
        Token.adjustFontSize.put("Consolas", adjust(103));
        Token.adjustFontSize.put("Constantia", adjust(96));
        Token.adjustFontSize.put("Courier New", adjust(93));
        Token.adjustFontSize.put("Dialog", adjust(111));
        Token.adjustFontSize.put("DialogInput", adjust(91));
        Token.adjustFontSize.put("Lucida Console", adjust(113));
        Token.adjustFontSize.put("Lucida Sans Typewriter", adjust(114));
        Token.adjustFontSize.put("Lucida Sans Unicode", adjust(112));
        Token.adjustFontSize.put("Lucida Sans", adjust(114));
        Token.adjustFontSize.put("Monospaced", adjust(91));
        Token.adjustFontSize.put("SansSerif", adjust(109));
        Token.adjustFontSize.put("Segoe UI", adjust(108));
        Token.adjustFontSize.put("Tiresias PCfont Z", adjust(114));
        lineNumberFont = bestFont(TokenFonts.MONO_FONTS, 0, 12);
    }
}
