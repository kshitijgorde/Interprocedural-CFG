import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class LabelBar extends Canvas
{
    Applet parent;
    StockDetail detail;
    StockDetail rightDetail;
    int x;
    double value;
    String message;
    Color baseColor;
    Font labelFont;
    
    LabelBar(final Applet applet) {
        this.baseColor = new Color(0, 0, 102);
        this.labelFont = new Font("Serif", 1, 11);
        this.parent = applet;
        this.setBackground(Color.lightGray);
        this.resize(this.parent.size().width, 20);
    }
    
    public void drawLabel(final Graphics g, final String s, String s1, final String s2, final int i) {
        if (s1.length() > s2.length()) {
            s1 = s1.substring(0, s2.length() - 1);
        }
        g.drawString(String.valueOf(String.valueOf(s).concat(String.valueOf(" "))).concat(String.valueOf(s1)), this.x, i + 4);
        this.x += g.getFontMetrics(this.labelFont).stringWidth(String.valueOf(s).concat(String.valueOf("   "))) + g.getFontMetrics(this.labelFont).stringWidth(s2);
    }
    
    public void hideMessage() {
        this.message = null;
        this.repaint();
    }
    
    public void paint(final Graphics g) {
        char c = '\0';
        g.setColor(this.baseColor);
        g.fillRect(0, 0, this.size().width, this.size().height);
        this.x = 2;
        final int i = g.getFontMetrics(this.labelFont).getHeight() - 2;
        g.setColor(Color.white);
        try {
            if (this.detail.getDate().getYear() < 1900) {
                c = '\u076c';
            }
        }
        catch (Exception ex) {}
        if (this.message == null) {
            if (this.detail != null) {
                if (this.rightDetail == null) {
                    this.drawLabel(g, "D:", String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.detail.getDate().getDate()).concat(String.valueOf("/"))).concat(String.valueOf(this.detail.getDate().getMonth() + 1))).concat(String.valueOf("/"))).concat(String.valueOf(this.detail.getDate().getYear() + c))).concat(String.valueOf(" "))).concat(String.valueOf(this.detail.getDate().getHours()))).concat(String.valueOf(":"))).concat(String.valueOf(this.detail.getDate().getMinutes())), "99/99/999999:99", i);
                    this.drawLabel(g, "O:", String.valueOf(this.detail.getOpen()), "999.9999", i);
                    this.drawLabel(g, "H:", String.valueOf(this.detail.getHigh()), "999.9999", i);
                    this.drawLabel(g, "L:", String.valueOf(this.detail.getLow()), "999.9999", i);
                    this.drawLabel(g, "C:", String.valueOf(this.detail.getClose()), "999.9999", i);
                    final String s = String.valueOf(this.detail.getVolume());
                    final int j = s.length();
                    int k = 0;
                    String s2 = "";
                    for (int l = j; l > 0; --l) {
                        s2 = String.valueOf(s.substring(l - 1, l)).concat(String.valueOf(s2));
                        if (++k == 3 && l > 1) {
                            s2 = String.valueOf(",").concat(String.valueOf(s2));
                            k = 0;
                        }
                    }
                    final String s3 = s2;
                    return;
                }
                this.drawLabel(g, "Date:", String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.detail.getDate().getDate()).concat(String.valueOf("/"))).concat(String.valueOf(this.detail.getDate().getMonth() + 1))).concat(String.valueOf("/"))).concat(String.valueOf(this.detail.getDate().getYear() + c))).concat(String.valueOf(" "))).concat(String.valueOf(this.detail.getDate().getHours()))).concat(String.valueOf(":"))).concat(String.valueOf(this.detail.getDate().getMinutes())), "99/99/9999 99:99", i);
                this.drawLabel(g, "to", "", "to", i);
                this.drawLabel(g, "Date:", String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.rightDetail.getDate().getDate()).concat(String.valueOf("/"))).concat(String.valueOf(this.rightDetail.getDate().getMonth() + 1))).concat(String.valueOf("/"))).concat(String.valueOf(this.rightDetail.getDate().getYear() + c))).concat(String.valueOf(" "))).concat(String.valueOf(this.detail.getDate().getHours()))).concat(String.valueOf(":"))).concat(String.valueOf(this.detail.getDate().getMinutes())), "99/99/9999 99:99", i);
            }
        }
        else {
            this.drawLabel(g, this.message, "", "", i);
        }
    }
    
    public void removeRightDetail() {
        this.rightDetail = null;
        this.repaint();
    }
    
    public void setDetail(final StockDetail stockdetail, final double d) {
        this.detail = stockdetail;
        if (d >= 0.0) {
            this.value = (int)(d * 100000.0) / 100000.0;
        }
        this.repaint();
    }
    
    public void setRightDetail(final StockDetail stockdetail) {
        this.rightDetail = stockdetail;
        this.repaint();
    }
    
    public void showMessage(final String s) {
        this.message = s;
        this.repaint();
    }
}
