// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import java.io.ObjectStreamException;
import java.io.Serializable;

public final class TextAnchor implements Serializable
{
    public static final TextAnchor TOP_LEFT;
    public static final TextAnchor TOP_CENTER;
    public static final TextAnchor TOP_RIGHT;
    public static final TextAnchor HALF_ASCENT_LEFT;
    public static final TextAnchor HALF_ASCENT_CENTER;
    public static final TextAnchor HALF_ASCENT_RIGHT;
    public static final TextAnchor CENTER_LEFT;
    public static final TextAnchor CENTER;
    public static final TextAnchor CENTER_RIGHT;
    public static final TextAnchor BASELINE_LEFT;
    public static final TextAnchor BASELINE_CENTER;
    public static final TextAnchor BASELINE_RIGHT;
    public static final TextAnchor BOTTOM_LEFT;
    public static final TextAnchor BOTTOM_CENTER;
    public static final TextAnchor BOTTOM_RIGHT;
    private String name;
    
    private TextAnchor(final String name) {
        this.name = name;
    }
    
    public String toString() {
        return this.name;
    }
    
    public boolean equals(final Object o) {
        return this == o || (o instanceof TextAnchor && this.name.equals(((TextAnchor)o).name));
    }
    
    public int hashCode() {
        return this.name.hashCode();
    }
    
    public static TextAnchor getHorizontalOpposite(final TextAnchor textAnchor) {
        if (textAnchor == TextAnchor.TOP_LEFT) {
            return TextAnchor.TOP_RIGHT;
        }
        if (textAnchor == TextAnchor.TOP_CENTER) {
            return TextAnchor.TOP_CENTER;
        }
        if (textAnchor == TextAnchor.TOP_RIGHT) {
            return TextAnchor.TOP_LEFT;
        }
        if (textAnchor == TextAnchor.HALF_ASCENT_LEFT) {
            return TextAnchor.HALF_ASCENT_RIGHT;
        }
        if (textAnchor == TextAnchor.HALF_ASCENT_CENTER) {
            return TextAnchor.HALF_ASCENT_CENTER;
        }
        if (textAnchor == TextAnchor.HALF_ASCENT_RIGHT) {
            return TextAnchor.HALF_ASCENT_LEFT;
        }
        if (textAnchor == TextAnchor.CENTER_LEFT) {
            return TextAnchor.CENTER_RIGHT;
        }
        if (textAnchor == TextAnchor.CENTER) {
            return TextAnchor.CENTER;
        }
        if (textAnchor == TextAnchor.CENTER_RIGHT) {
            return TextAnchor.CENTER_LEFT;
        }
        if (textAnchor == TextAnchor.BASELINE_LEFT) {
            return TextAnchor.BASELINE_RIGHT;
        }
        if (textAnchor == TextAnchor.BASELINE_CENTER) {
            return TextAnchor.BASELINE_CENTER;
        }
        if (textAnchor == TextAnchor.BASELINE_RIGHT) {
            return TextAnchor.BASELINE_LEFT;
        }
        if (textAnchor == TextAnchor.BOTTOM_LEFT) {
            return TextAnchor.BOTTOM_RIGHT;
        }
        if (textAnchor == TextAnchor.BOTTOM_CENTER) {
            return TextAnchor.BOTTOM_CENTER;
        }
        if (textAnchor == TextAnchor.BOTTOM_RIGHT) {
            return TextAnchor.BOTTOM_LEFT;
        }
        return null;
    }
    
    public static TextAnchor getVerticalOpposite(final TextAnchor textAnchor) {
        if (textAnchor == TextAnchor.TOP_LEFT) {
            return TextAnchor.BOTTOM_LEFT;
        }
        if (textAnchor == TextAnchor.TOP_CENTER) {
            return TextAnchor.BOTTOM_CENTER;
        }
        if (textAnchor == TextAnchor.TOP_RIGHT) {
            return TextAnchor.BOTTOM_RIGHT;
        }
        if (textAnchor == TextAnchor.HALF_ASCENT_LEFT) {
            return TextAnchor.HALF_ASCENT_LEFT;
        }
        if (textAnchor == TextAnchor.HALF_ASCENT_CENTER) {
            return TextAnchor.HALF_ASCENT_CENTER;
        }
        if (textAnchor == TextAnchor.HALF_ASCENT_RIGHT) {
            return TextAnchor.HALF_ASCENT_RIGHT;
        }
        if (textAnchor == TextAnchor.CENTER_LEFT) {
            return TextAnchor.CENTER_LEFT;
        }
        if (textAnchor == TextAnchor.CENTER) {
            return TextAnchor.CENTER;
        }
        if (textAnchor == TextAnchor.CENTER_RIGHT) {
            return TextAnchor.CENTER_RIGHT;
        }
        if (textAnchor == TextAnchor.BASELINE_LEFT) {
            return TextAnchor.TOP_LEFT;
        }
        if (textAnchor == TextAnchor.BASELINE_CENTER) {
            return TextAnchor.TOP_CENTER;
        }
        if (textAnchor == TextAnchor.BASELINE_RIGHT) {
            return TextAnchor.TOP_RIGHT;
        }
        if (textAnchor == TextAnchor.BOTTOM_LEFT) {
            return TextAnchor.TOP_LEFT;
        }
        if (textAnchor == TextAnchor.BOTTOM_CENTER) {
            return TextAnchor.TOP_CENTER;
        }
        if (textAnchor == TextAnchor.BOTTOM_RIGHT) {
            return TextAnchor.TOP_RIGHT;
        }
        return null;
    }
    
    private Object readResolve() throws ObjectStreamException {
        Object o = null;
        if (this.equals(TextAnchor.TOP_LEFT)) {
            o = TextAnchor.TOP_LEFT;
        }
        else if (this.equals(TextAnchor.TOP_CENTER)) {
            o = TextAnchor.TOP_CENTER;
        }
        else if (this.equals(TextAnchor.TOP_RIGHT)) {
            o = TextAnchor.TOP_RIGHT;
        }
        else if (this.equals(TextAnchor.BOTTOM_LEFT)) {
            o = TextAnchor.BOTTOM_LEFT;
        }
        else if (this.equals(TextAnchor.BOTTOM_CENTER)) {
            o = TextAnchor.BOTTOM_CENTER;
        }
        else if (this.equals(TextAnchor.BOTTOM_RIGHT)) {
            o = TextAnchor.BOTTOM_RIGHT;
        }
        else if (this.equals(TextAnchor.BASELINE_LEFT)) {
            o = TextAnchor.BASELINE_LEFT;
        }
        else if (this.equals(TextAnchor.BASELINE_CENTER)) {
            o = TextAnchor.BASELINE_CENTER;
        }
        else if (this.equals(TextAnchor.BASELINE_RIGHT)) {
            o = TextAnchor.BASELINE_RIGHT;
        }
        else if (this.equals(TextAnchor.CENTER_LEFT)) {
            o = TextAnchor.CENTER_LEFT;
        }
        else if (this.equals(TextAnchor.CENTER)) {
            o = TextAnchor.CENTER;
        }
        else if (this.equals(TextAnchor.CENTER_RIGHT)) {
            o = TextAnchor.CENTER_RIGHT;
        }
        else if (this.equals(TextAnchor.HALF_ASCENT_LEFT)) {
            o = TextAnchor.HALF_ASCENT_LEFT;
        }
        else if (this.equals(TextAnchor.HALF_ASCENT_CENTER)) {
            o = TextAnchor.HALF_ASCENT_CENTER;
        }
        else if (this.equals(TextAnchor.HALF_ASCENT_RIGHT)) {
            o = TextAnchor.HALF_ASCENT_RIGHT;
        }
        return o;
    }
    
    static {
        TOP_LEFT = new TextAnchor("TextAnchor.TOP_LEFT");
        TOP_CENTER = new TextAnchor("TextAnchor.TOP_CENTER");
        TOP_RIGHT = new TextAnchor("TextAnchor.TOP_RIGHT");
        HALF_ASCENT_LEFT = new TextAnchor("TextAnchor.HALF_ASCENT_LEFT");
        HALF_ASCENT_CENTER = new TextAnchor("TextAnchor.HALF_ASCENT_CENTER");
        HALF_ASCENT_RIGHT = new TextAnchor("TextAnchor.HALF_ASCENT_RIGHT");
        CENTER_LEFT = new TextAnchor("TextAnchor.CENTER_LEFT");
        CENTER = new TextAnchor("TextAnchor.CENTER");
        CENTER_RIGHT = new TextAnchor("TextAnchor.CENTER_RIGHT");
        BASELINE_LEFT = new TextAnchor("TextAnchor.BASELINE_LEFT");
        BASELINE_CENTER = new TextAnchor("TextAnchor.BASELINE_CENTER");
        BASELINE_RIGHT = new TextAnchor("TextAnchor.BASELINE_RIGHT");
        BOTTOM_LEFT = new TextAnchor("TextAnchor.BOTTOM_LEFT");
        BOTTOM_CENTER = new TextAnchor("TextAnchor.BOTTOM_CENTER");
        BOTTOM_RIGHT = new TextAnchor("TextAnchor.BOTTOM_RIGHT");
    }
}
