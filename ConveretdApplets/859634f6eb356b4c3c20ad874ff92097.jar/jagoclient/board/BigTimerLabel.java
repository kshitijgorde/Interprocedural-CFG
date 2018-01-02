// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import jagoclient.Global;
import jagoclient.gui.BigLabel;

public class BigTimerLabel extends BigLabel
{
    int White;
    int Black;
    int Col;
    int MWhite;
    int MBlack;
    static char[] a;
    
    public BigTimerLabel() {
        super(Global.BigMonospaced);
        this.MWhite = -1;
        this.MBlack = -1;
    }
    
    public void drawString(final Graphics graphics, int n, final int n2, final FontMetrics fontMetrics) {
        final int n3 = fontMetrics.charWidth('m') / 4;
        if (Global.BigMonospaced != null) {
            graphics.setFont(Global.BigMonospaced);
        }
        if (this.White < 0) {
            graphics.setColor(Color.blue);
        }
        else if (this.White < 30 && this.Col < 0) {
            graphics.setColor(Color.red);
        }
        else if (this.White < 60 && this.Col < 0) {
            graphics.setColor(Color.red.darker());
        }
        else if (this.Col < 0) {
            graphics.setColor(Color.green.darker());
        }
        else {
            graphics.setColor(Color.black);
        }
        final int formtime = OutputFormatter.formtime(BigTimerLabel.a, this.White);
        graphics.drawChars(BigTimerLabel.a, 0, formtime, n, n2);
        n += fontMetrics.charsWidth(BigTimerLabel.a, 0, formtime) + n3;
        graphics.setFont(Global.Monospaced);
        if (this.MWhite >= 0) {
            BigTimerLabel.a[0] = (char)(48 + this.MWhite % 100 / 10);
            BigTimerLabel.a[1] = (char)(48 + this.MWhite % 10);
        }
        else {
            BigTimerLabel.a[0] = (BigTimerLabel.a[1] = ' ');
        }
        graphics.setColor(Color.black);
        graphics.drawChars(BigTimerLabel.a, 0, 2, n, n2);
        n += fontMetrics.charsWidth(BigTimerLabel.a, 0, 2) + n3;
        if (Global.BigMonospaced != null) {
            graphics.setFont(Global.BigMonospaced);
        }
        if (this.Black < 0) {
            graphics.setColor(Color.blue);
        }
        else if (this.Black < 30 && this.Col > 0) {
            graphics.setColor(Color.red);
        }
        else if (this.Black < 60 && this.Col > 0) {
            graphics.setColor(Color.red.darker());
        }
        else if (this.Col > 0) {
            graphics.setColor(Color.green.darker());
        }
        else {
            graphics.setColor(Color.black);
        }
        final int formtime2 = OutputFormatter.formtime(BigTimerLabel.a, this.Black);
        graphics.drawChars(BigTimerLabel.a, 0, formtime2, n, n2);
        n += fontMetrics.charsWidth(BigTimerLabel.a, 0, formtime2) + n3;
        graphics.setFont(Global.Monospaced);
        if (this.MBlack >= 0) {
            BigTimerLabel.a[0] = (char)(48 + this.MBlack % 100 / 10);
            BigTimerLabel.a[1] = (char)(48 + this.MBlack % 10);
        }
        else {
            BigTimerLabel.a[0] = (BigTimerLabel.a[1] = ' ');
        }
        graphics.setColor(Color.black);
        graphics.drawChars(BigTimerLabel.a, 0, 2, n, n2);
        if (Global.BigMonospaced != null) {
            graphics.setFont(Global.BigMonospaced);
        }
    }
    
    public void setTime(final int white, final int black, final int mWhite, final int mBlack, final int col) {
        this.White = white;
        this.Black = black;
        this.MWhite = mWhite;
        this.MBlack = mBlack;
        this.Col = col;
    }
    
    static {
        BigTimerLabel.a = new char[32];
    }
}
