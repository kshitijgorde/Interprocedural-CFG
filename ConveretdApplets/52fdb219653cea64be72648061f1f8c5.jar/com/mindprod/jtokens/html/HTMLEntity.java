// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.html;

import com.mindprod.jtokens.TokenFonts;
import com.mindprod.jtokens.TokenColourScheme;
import java.util.regex.Pattern;
import java.awt.Font;
import java.awt.Color;
import com.mindprod.jtokens.Token;

public final class HTMLEntity extends Token
{
    static final long serialVersionUID = 1L;
    private static final String[] ENTITY_CSS_CLASSES;
    private static final Color[] FOREGROUNDS_FOR_ENTITIES;
    private static final Font[] FONTS_FOR_ENTITIES;
    private static final Pattern DECIMAL_ENTITY_PATTERN;
    private static final Pattern HEX_ENTITY_PATTERN;
    private final boolean known;
    
    public HTMLEntity(final String entity, final boolean known) {
        super(entity);
        this.known = known;
    }
    
    public Font getFont() {
        return HTMLEntity.FONTS_FOR_ENTITIES[this.getCategory()];
    }
    
    public Color getForeground() {
        return HTMLEntity.FOREGROUNDS_FOR_ENTITIES[this.getCategory()];
    }
    
    public String getHTML() {
        return "<span class=\"" + HTMLEntity.ENTITY_CSS_CLASSES[this.getCategory()] + "\">&amp;" + this.text + ";</span>";
    }
    
    public String getText() {
        return '&' + this.text + ';';
    }
    
    public boolean isCollapsible() {
        return false;
    }
    
    public boolean isUseless() {
        return false;
    }
    
    private int getCategory() {
        if (this.known) {
            return 1;
        }
        if (this.text.startsWith("#x") && HTMLEntity.HEX_ENTITY_PATTERN.matcher(this.text).matches()) {
            return 2;
        }
        if (this.text.startsWith("#") && HTMLEntity.DECIMAL_ENTITY_PATTERN.matcher(this.text).matches()) {
            return 3;
        }
        return 0;
    }
    
    static {
        ENTITY_CSS_CLASSES = new String[] { "unknownentity", "entity", "hexentity", "decimalentity" };
        FOREGROUNDS_FOR_ENTITIES = new Color[] { TokenColourScheme.FOREGROUND_FOR_HTML_UNKNOWN_ENTITY, TokenColourScheme.FOREGROUND_FOR_HTML_ALPHA_ENTITY, TokenColourScheme.FOREGROUND_FOR_HTML_HEX_ENTITY, TokenColourScheme.FOREGROUND_FOR_DECIMAL_ENTITY };
        FONTS_FOR_ENTITIES = new Font[] { Token.bestFont(TokenFonts.MONO_FONTS, 1, 17), Token.bestFont(TokenFonts.MONO_FONTS, 0, 17), Token.bestFont(TokenFonts.MONO_FONTS, 0, 17), Token.bestFont(TokenFonts.MONO_FONTS, 0, 17) };
        DECIMAL_ENTITY_PATTERN = Pattern.compile("#\\d+");
        HEX_ENTITY_PATTERN = Pattern.compile("#x\\p{XDigit}+");
    }
}
