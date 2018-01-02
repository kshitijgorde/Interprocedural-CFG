import java.util.Date;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.FontMetrics;

// 
// Decompiled by Procyon v0.5.30
// 

class BarClock extends BarItem
{
    int day;
    FontMetrics fm;
    boolean Clock;
    
    public BarClock(final int n, final int y, final int maxY, final Graphics graphics, final boolean clock, final String s, final int n2, final int n3, final String s2, final int n4, final int n5) {
        BarItem.font = new Font(s, n3, n2);
        BarItem.CommFont = new Font(s2, n5, n4);
        this.fm = graphics.getFontMetrics(BarItem.font);
        if (clock) {
            super.x = n - this.fm.charWidth('8') * 6 - 8;
        }
        else {
            super.x = n - this.fm.stringWidth("LinkBar") - 8;
        }
        super.y = y;
        super.maxX = n - 2;
        super.maxY = maxY;
        this.Clock = clock;
    }
    
    public String DString(final int n, final int n2, final int n3, final int n4) {
        String s = null;
        switch (n) {
            case 0: {
                s = "Sunday";
                break;
            }
            case 1: {
                s = "Monday";
                break;
            }
            case 2: {
                s = "Tuesday";
                break;
            }
            case 3: {
                s = "Wednesday";
                break;
            }
            case 4: {
                s = "Thursday";
                break;
            }
            case 5: {
                s = "Friday";
                break;
            }
            case 6: {
                s = "Saturday";
                break;
            }
            default: {
                s = "";
                break;
            }
        }
        final String string = String.valueOf(s) + " " + n2;
        String s2 = null;
        switch (n3) {
            case 0: {
                s2 = String.valueOf(string) + " January";
                break;
            }
            case 1: {
                s2 = String.valueOf(string) + " February";
                break;
            }
            case 2: {
                s2 = String.valueOf(string) + " March";
                break;
            }
            case 3: {
                s2 = String.valueOf(string) + " April";
                break;
            }
            case 4: {
                s2 = String.valueOf(string) + " May";
                break;
            }
            case 5: {
                s2 = String.valueOf(string) + " June";
                break;
            }
            case 6: {
                s2 = String.valueOf(string) + " July";
                break;
            }
            case 7: {
                s2 = String.valueOf(string) + " August";
                break;
            }
            case 8: {
                s2 = String.valueOf(string) + " September";
                break;
            }
            case 9: {
                s2 = String.valueOf(string) + " October";
                break;
            }
            case 10: {
                s2 = String.valueOf(string) + " November";
                break;
            }
            case 11: {
                s2 = String.valueOf(string) + " December";
                break;
            }
            default: {
                s2 = "";
                break;
            }
        }
        return String.valueOf(s2) + " " + n4;
    }
    
    public void Show(final Graphics graphics) {
        String string = "";
        String string2;
        if (this.Clock) {
            final Date date = new Date();
            final int date2 = date.getDate();
            final int day = date.getDay();
            final int month = date.getMonth();
            final int hours = date.getHours();
            final int minutes = date.getMinutes();
            final int year = date.getYear();
            if (this.day != date2) {
                this.day = date2;
                super.comm = this.DString(day, this.day, month, year + 1900);
            }
            if (hours < 10) {
                string = String.valueOf(string) + "0";
            }
            String s = String.valueOf(string) + hours + ":";
            if (minutes < 10) {
                s = String.valueOf(s) + "0";
            }
            string2 = String.valueOf(s) + minutes;
        }
        else {
            super.comm = "GSA LinkBar";
            string2 = "LinkBar";
        }
        final int n = super.x + (super.maxX - this.fm.stringWidth(string2) - super.x) / 2;
        final int n2 = super.y + (super.maxY - super.y + this.fm.getHeight() - this.fm.getHeight() / 2) / 2;
        graphics.setColor(BarItem.BuCol);
        graphics.fill3DRect(super.x, super.y, super.maxX - super.x, super.maxY - super.y, false);
        graphics.setFont(BarItem.font);
        graphics.setColor(BarItem.TCol);
        graphics.drawString(string2, n, n2);
    }
}
