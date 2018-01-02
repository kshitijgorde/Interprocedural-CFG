import java.awt.Event;
import java.awt.Font;
import java.util.Map;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.awt.Button;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class graphFrame extends Frame
{
    final char deg = '°';
    Button okButton;
    double[][] data;
    int N;
    double ptFaktor;
    boolean online;
    
    public graphFrame(final String titleStr, final double[][] Data, final int nData, final double pt, final boolean demo) {
        this.data = new double[150][2];
        this.setTitle(titleStr);
        this.setBackground(Color.white);
        this.N = nData;
        this.data = Data;
        this.ptFaktor = pt;
        this.online = demo;
        this.setBackground(Color.white);
        this.add(this.okButton = new Button("hide window"), "South");
        this.pack();
        this.repaint();
    }
    
    public void paint(final Graphics g) {
        final RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        final Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHints(renderHints);
        final int x0 = 50;
        final int y0 = 400;
        final int fx = 4;
        final int fy = 9;
        g.setColor(Color.white);
        g.fillRect(0, 0, 500, 550);
        g.setFont(new Font("Helvetiva", 0, 10));
        g.setColor(Color.black);
        g.drawString("p,T factor = " + Math.round(1000.0 * this.ptFaktor) / 1000.0, x0 + 50, 40);
        for (int i = 0; i <= 9; ++i) {
            final int x2 = x0 + Math.round(fx * i * 10);
            g.setColor(Color.lightGray);
            g.drawLine(x2, y0, x2, y0 - 350);
            g.setColor(Color.black);
            g.drawString(new StringBuffer().append(i * 10).append('°').toString(), x2 - 7, y0 + 15);
        }
        for (int j = 0; j <= 7; ++j) {
            g.setColor(Color.lightGray);
            final int y2 = y0 - Math.round(j * 50);
            g.drawLine(x0, y2, x0 + 90 * fx, y2);
            g.setColor(Color.black);
            g.drawString(j * 5 + "'", x0 - 20, y2 + 5);
        }
        g.setColor(Color.red);
        for (int k = 1; k < this.N; ++k) {
            final int x2 = x0 + (int)Math.round(fx * this.data[k][0]);
            final int y2 = y0 - (int)Math.round(fy * this.data[k][1]);
            final int x3 = x0 + (int)Math.round(fx * this.data[k + 1][0]);
            final int y3 = y0 - (int)Math.round(fy * this.data[k + 1][1]);
            if (this.data[k][0] < 90.0) {
                g.drawLine(x2, y2, x3, y3);
            }
        }
        if (this.online) {
            g.setFont(new Font("Helvetiva", 0, 24));
            g.drawString("online", x0 + 100, y0 - 30);
        }
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
    
    public boolean action(final Event evt, final Object whichAction) {
        if (evt.target instanceof Button && evt.target == this.okButton) {
            this.hide();
        }
        return true;
    }
}
