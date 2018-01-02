import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class AMDProgressBarEmbed
{
    int x_;
    int y_;
    int width_;
    int height_;
    Color outerBorderColor_;
    Color innerBorderColor_;
    Color boxColorA_;
    Color boxColorB_;
    Color barColor_;
    Color backTopColor_;
    Color backBottomColor_;
    Color textColor_;
    Color textShadowColor_;
    Font font_;
    String text_;
    int sinePeriod_;
    int colorSegment_;
    Component parent_;
    Graphics graphics_;
    Image image_;
    double percent_;
    boolean updateTrim_;
    boolean updateLocations_;
    boolean updateBar_;
    int lastDigits_;
    int halfY_;
    int textX_;
    int textY_;
    int r_;
    int g_;
    int b_;
    int r2_;
    int g2_;
    int b2_;
    
    public AMDProgressBarEmbed(final Component parent_) {
        this.outerBorderColor_ = Color.black;
        this.innerBorderColor_ = Color.black;
        this.boxColorA_ = new Color(244, 224, 32);
        this.boxColorB_ = new Color(120, 80, 4);
        this.barColor_ = new Color(52, 112, 4);
        this.backTopColor_ = new Color(40, 40, 40);
        this.backBottomColor_ = new Color(76, 76, 76);
        this.textColor_ = new Color(208, 192, 28);
        this.textShadowColor_ = Color.black;
        this.font_ = new Font("TimesRoman", 1, 14);
        this.sinePeriod_ = 87;
        this.colorSegment_ = 7;
        this.parent_ = parent_;
        this.lastDigits_ = 1;
        this.setPercent(0.0);
        this.updateTrim_ = true;
        this.updateLocations_ = true;
        this.updateBar_ = true;
    }
    
    public void reshape(final int x_, final int y_, final int width_, final int height_) {
        this.x_ = x_;
        this.y_ = y_;
        this.width_ = width_;
        this.height_ = height_;
        if (this.graphics_ != null) {
            this.graphics_.dispose();
            this.graphics_ = null;
        }
        this.parent_.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (this.graphics_ == null) {
            this.image_ = this.parent_.createImage(this.width_, this.height_);
            this.graphics_ = this.image_.getGraphics();
            this.updateTrim_ = true;
            this.updateLocations_ = true;
            this.updateBar_ = true;
        }
        if (this.updateTrim_) {
            this.updateTrim();
        }
        if (this.updateLocations_) {
            this.updateLocations();
        }
        if (this.updateBar_) {
            final int n = (int)((this.width_ - 6) * this.percent_);
            final int n2 = this.width_ - 6 - n;
            this.graphics_.translate(3, 3);
            if (n > 0) {
                this.graphics_.setColor(this.barColor_);
                this.graphics_.fillRect(0, 0, n, this.height_ - 6);
            }
            if (n2 > 0) {
                this.graphics_.setColor(this.backTopColor_);
                this.graphics_.fillRect(n, 0, n2, this.halfY_);
                this.graphics_.setColor(this.backBottomColor_);
                this.graphics_.fillRect(n, this.halfY_, n2, this.height_ - 6 - this.halfY_);
            }
            this.graphics_.translate(-3, -3);
            this.graphics_.setFont(this.font_);
            this.graphics_.setColor(this.textShadowColor_);
            String s;
            if (this.text_ == null) {
                s = "" + (int)(this.percent_ * 100.0) + "%";
            }
            else {
                s = this.text_;
            }
            this.graphics_.drawString(s, this.textX_ + 1, this.textY_ + 1);
            this.graphics_.setColor(this.textColor_);
            this.graphics_.drawString(s, this.textX_, this.textY_);
            this.updateBar_ = false;
        }
        graphics.drawImage(this.image_, this.x_, this.y_, null);
    }
    
    Color getColor(final int n) {
        final double n2 = Math.sin(n % this.sinePeriod_ * 2.0 * 3.141592653589793 / this.sinePeriod_) + 1.0;
        return new Color(this.r_ + (int)(n2 * (this.r2_ - this.r_) / 2.0), this.g_ + (int)(n2 * (this.g2_ - this.g_) / 2.0), this.b_ + (int)(n2 * (this.b2_ - this.b_) / 2.0));
    }
    
    int drawSineLine(int n, int n2, int n3, int n4, int n5, int n6) {
        final boolean b = n3 == n5;
        if (b) {
            final int n7 = n3;
            n3 = n4;
            n4 = n7;
            final int n8 = n5;
            n5 = n6;
            n6 = n8;
        }
        final boolean b2 = n3 > n5;
        if (b2) {
            n2 *= -1;
        }
        this.graphics_.setColor(this.getColor(n));
        if (b) {
            this.graphics_.drawLine(n4, n3, n4, n3 + n2);
        }
        else {
            this.graphics_.drawLine(n3, n4, n3 + n2, n4);
        }
        int n9 = 1;
        if (!b2) {
            n2 += this.colorSegment_;
        }
        for (n += this.colorSegment_; (b2 && n3 + n2 - this.colorSegment_ > n5) || (!b2 && n3 + n2 < n5); n2 += (b2 ? (-this.colorSegment_) : this.colorSegment_), n += this.colorSegment_, ++n9) {
            this.graphics_.setColor(this.getColor(n));
            if (b) {
                this.graphics_.drawLine(n4, n3 + n2 - this.colorSegment_, n4, n3 + n2);
            }
            else {
                this.graphics_.drawLine(n3 + n2 - this.colorSegment_, n4, n3 + n2, n4);
            }
        }
        if (b2) {
            n2 += this.colorSegment_;
        }
        this.graphics_.setColor(this.getColor(n));
        if (b) {
            this.graphics_.drawLine(n4, n3 + n2 - this.colorSegment_, n4, n5);
        }
        else {
            this.graphics_.drawLine(n3 + n2 - this.colorSegment_, n4, n5, n4);
        }
        return n9;
    }
    
    void drawSineBox(final Color color, final Color color2, final int n, final int n2, final int n3, final int n4) {
        this.r_ = color.getRed();
        this.g_ = color.getGreen();
        this.b_ = color.getBlue();
        this.r2_ = color2.getRed();
        this.g2_ = color2.getGreen();
        this.b2_ = color2.getBlue();
        final int n5 = n + n3;
        final int n6 = n2 + n4;
        final int n7 = 0;
        final int n8 = (int)(this.sinePeriod_ / 4.0);
        final int drawSineLine = this.drawSineLine(n8, n7, n, n2, n5, n2);
        final int n9 = n8 + drawSineLine * this.colorSegment_;
        final int n10 = n7 + (drawSineLine * this.colorSegment_ - n5 + 1);
        final int drawSineLine2 = this.drawSineLine(n9, n10, n5, n2, n5, n6);
        final int n11 = n9 + drawSineLine2 * this.colorSegment_;
        final int n12 = n10 + (drawSineLine2 * this.colorSegment_ - n6 + 1);
        final int drawSineLine3 = this.drawSineLine(n11, n12, n5, n6, n, n6);
        this.drawSineLine(n11 + drawSineLine3 * this.colorSegment_, n12 + (drawSineLine3 * this.colorSegment_ - n5 + 1), n, n6, n, n2);
    }
    
    void updateTrim() {
        this.graphics_.setColor(this.outerBorderColor_);
        this.graphics_.drawRect(0, 0, this.width_ - 1, this.height_ - 1);
        this.graphics_.setColor(this.innerBorderColor_);
        this.graphics_.drawRect(2, 2, this.width_ - 5, this.height_ - 5);
        if (this.boxColorB_ == null || this.boxColorA_.equals(this.boxColorB_)) {
            this.graphics_.setColor(this.boxColorA_);
            this.graphics_.drawRect(1, 1, this.width_ - 3, this.height_ - 3);
        }
        else {
            this.drawSineBox(this.boxColorA_, this.boxColorB_, 1, 1, this.width_ - 3, this.height_ - 3);
        }
        this.updateTrim_ = false;
    }
    
    void updateLocations() {
        this.halfY_ = (this.height_ - 6) / 2;
        final FontMetrics fontMetrics = this.parent_.getFontMetrics(this.font_);
        String text_;
        if (this.text_ == null) {
            if (this.lastDigits_ == 1) {
                text_ = "3";
            }
            else if (this.lastDigits_ == 2) {
                text_ = "30";
            }
            else {
                text_ = "100";
            }
        }
        else {
            text_ = this.text_;
        }
        this.textX_ = (this.width_ - fontMetrics.stringWidth(text_)) / 2;
        this.textY_ = (this.height_ + fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
        this.updateLocations_ = false;
    }
    
    public void setPercent(final double percent_) {
        if (percent_ < 0.0) {
            this.percent_ = 0.0;
        }
        else if (percent_ > 1.0) {
            this.percent_ = 1.0;
        }
        else {
            this.percent_ = percent_;
        }
        this.updateBar_ = true;
        int lastDigits_;
        if (percent_ < 0.1) {
            lastDigits_ = 1;
        }
        else if (percent_ < 1.0) {
            lastDigits_ = 2;
        }
        else {
            lastDigits_ = 3;
        }
        if (lastDigits_ != this.lastDigits_) {
            this.lastDigits_ = lastDigits_;
            if (this.text_ == null) {
                this.updateLocations_ = true;
            }
        }
        this.parent_.repaint();
    }
    
    public void setText(final String text_) {
        this.text_ = text_;
        this.updateLocations_ = true;
        this.updateBar_ = true;
        this.parent_.repaint();
    }
    
    public void setBorderColors(final Color outerBorderColor_, final Color innerBorderColor_) {
        this.outerBorderColor_ = outerBorderColor_;
        this.innerBorderColor_ = innerBorderColor_;
        this.updateTrim_ = true;
        this.parent_.repaint();
    }
    
    public void setBoxColors(final Color boxColorA_, final Color boxColorB_) {
        this.boxColorA_ = boxColorA_;
        this.boxColorB_ = boxColorB_;
        this.updateTrim_ = true;
        this.parent_.repaint();
    }
    
    public void setBarColor(final Color barColor_) {
        this.barColor_ = barColor_;
        this.updateBar_ = true;
        this.parent_.repaint();
    }
    
    public void setBackgroundColors(final Color backTopColor_, final Color backBottomColor_) {
        this.backTopColor_ = backTopColor_;
        this.backBottomColor_ = backBottomColor_;
        this.updateBar_ = true;
        this.parent_.repaint();
    }
    
    public void setTextColors(final Color textColor_, final Color textShadowColor_) {
        this.textColor_ = textColor_;
        this.textShadowColor_ = textShadowColor_;
        this.updateBar_ = true;
        this.parent_.repaint();
    }
    
    public void setFont(final Font font_) {
        this.font_ = font_;
        this.updateLocations_ = true;
        this.updateBar_ = true;
        this.parent_.repaint();
    }
    
    public void setSineProperties(final int sinePeriod_, final int colorSegment_) {
        this.sinePeriod_ = sinePeriod_;
        this.colorSegment_ = colorSegment_;
        this.updateTrim_ = true;
        this.parent_.repaint();
    }
}
