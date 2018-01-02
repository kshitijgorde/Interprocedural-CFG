import java.awt.Font;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class ryStageFader extends Canvas
{
    private int \u0186;
    private int \u0185;
    private int \u0184;
    private int \u0183;
    private int \u0182;
    private int \u0181;
    private int \u0180;
    private int \u017f;
    private int \u017e;
    private int \u017d;
    private int \u017c;
    private int \u017b;
    private int \u017a;
    private int \u0179;
    private boolean \u0178;
    private boolean \u0177;
    private Color \u0176;
    private Color \u0175;
    private Color \u0174;
    
    public ryStageFader(final Color color, final Color color2, final int \u017a, final int \u017a2) {
        this.\u0178 = false;
        this.\u0177 = false;
        final int red = color.getRed();
        this.\u0183 = red;
        this.\u0186 = red;
        final int green = color.getGreen();
        this.\u0182 = green;
        this.\u0185 = green;
        final int blue = color.getBlue();
        this.\u0181 = blue;
        this.\u0184 = blue;
        this.\u0180 = color2.getRed();
        this.\u017f = color2.getGreen();
        this.\u017e = color2.getBlue();
        this.\u0187(this.\u0179 = \u017a);
        this.\u017a = \u017a2;
        this.getFontMetrics(new Font("TimesRoman", 1, 14));
    }
    
    private void \u0187(final int \u017c) {
        if (this.\u0183 < this.\u0180) {
            this.\u017d = \u017c;
        }
        else if (this.\u0183 == this.\u0180) {
            this.\u017d = 0;
        }
        else {
            this.\u017d = -\u017c;
        }
        if (this.\u0182 < this.\u017f) {
            this.\u017c = \u017c;
        }
        else if (this.\u0182 == this.\u017f) {
            this.\u017c = 0;
        }
        else {
            this.\u017c = -\u017c;
        }
        if (this.\u0181 < this.\u017e) {
            this.\u017b = \u017c;
            return;
        }
        if (this.\u0181 == this.\u017e) {
            this.\u017b = 0;
            return;
        }
        this.\u017b = -\u017c;
    }
    
    public final boolean getFadeCompleted() {
        return this.\u0177;
    }
    
    public final boolean getFinished() {
        return this.\u0178;
    }
    
    public final int fadeUp() {
        int \u017a = this.\u017a;
        this.\u0178 = false;
        this.\u0186 += this.\u017d;
        this.\u0185 += this.\u017c;
        this.\u0184 += this.\u017b;
        if ((this.\u017d <= 0 || this.\u0186 >= this.\u0180) && (this.\u017d >= 0 || this.\u0186 <= this.\u0180)) {
            this.\u0186 = this.\u0180;
        }
        if ((this.\u017c <= 0 || this.\u0185 >= this.\u017f) && (this.\u017c >= 0 || this.\u0185 <= this.\u017f)) {
            this.\u0185 = this.\u017f;
        }
        if ((this.\u017b <= 0 || this.\u0184 >= this.\u017e) && (this.\u017b >= 0 || this.\u0184 <= this.\u017e)) {
            this.\u0184 = this.\u017e;
        }
        if (this.\u0186 == this.\u0180 && this.\u0185 == this.\u017f && this.\u0184 == this.\u017e) {
            this.\u0178 = true;
            \u017a *= 15;
        }
        return \u017a;
    }
    
    public final int fadeDown() {
        int \u017a = this.\u017a;
        this.\u0177 = false;
        this.\u0186 -= this.\u017d;
        this.\u0185 -= this.\u017c;
        this.\u0184 -= this.\u017b;
        if ((this.\u017d <= 0 || this.\u0186 <= this.\u0183) && (this.\u017d >= 0 || this.\u0186 >= this.\u0183)) {
            this.\u0186 = this.\u0183;
        }
        if ((this.\u017c <= 0 || this.\u0185 <= this.\u0182) && (this.\u017c >= 0 || this.\u0185 >= this.\u0182)) {
            this.\u0185 = this.\u0182;
        }
        if ((this.\u017b <= 0 || this.\u0184 <= this.\u0181) && (this.\u017b >= 0 || this.\u0184 >= this.\u0181)) {
            this.\u0184 = this.\u0181;
        }
        if (this.\u0186 == this.\u0183 && this.\u0185 == this.\u0182 && this.\u0184 == this.\u0181) {
            this.\u0178 = false;
            this.\u0177 = true;
            \u017a *= 5;
        }
        return \u017a;
    }
    
    public final Color getFadeColor() {
        return this.\u0176 = new Color(this.\u0186, this.\u0185, this.\u0184);
    }
}
