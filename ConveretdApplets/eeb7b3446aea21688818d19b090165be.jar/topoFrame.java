import java.awt.Event;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Checkbox;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class topoFrame extends Frame
{
    boolean isOnline;
    int[] d;
    int nData;
    String[] monthArray;
    String LatLongStr;
    double newMoon;
    double fullMoon;
    compute comp;
    Checkbox box;
    boolean newfull;
    
    public topoFrame(final String vStr, final String LatLong, final int n, final int[] D, final double neu, final double voll, final boolean online) {
        this.d = new int[750];
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.newfull = true;
        this.setTitle(vStr);
        this.isOnline = online;
        this.nData = n;
        this.setLocation(250, 200);
        this.LatLongStr = LatLong;
        for (int i = 0; i <= n; ++i) {
            this.d[i] = D[i];
        }
        this.newMoon = neu;
        this.fullMoon = voll;
        this.comp = new compute();
        final GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 100.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 0, 0);
        gbl.setConstraints(this.box = new Checkbox("Show New/Full"), gbc);
        this.add(this.box);
        this.box.setState(this.newfull);
        final Canvas can = new Canvas();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 160.0;
        gbl.setConstraints(can, gbc);
        this.add(can);
        this.repaint();
    }
    
    public void paint(final Graphics g) {
        final int x0 = 60;
        final int y0 = 420;
        final int dy = 50;
        g.setColor(Color.white);
        g.fillRect(0, 0, 1000, 500);
        g.setFont(new Font("Courier", 0, 10));
        g.setColor(Color.black);
        g.drawString(this.LatLongStr, 200, 60);
        g.drawString("km", 25, 60);
        for (int i = 0; i <= 7; ++i) {
            g.drawString(350 + i * 10 + ",000", 10, y0 - i * dy + 5);
        }
        for (int j = 0; j <= this.nData; ++j) {
            if (j % 24 == 0) {
                if ((j + 24) % 120 == 0) {
                    g.setColor(Color.black);
                    g.drawString(String.valueOf((j + 24) / 24), x0 + j - 4, y0 + 15);
                    g.setColor(Color.gray);
                }
                else {
                    g.setColor(Color.lightGray);
                }
                g.drawLine(x0 + j, y0, x0 + j, y0 - 350);
            }
        }
        for (int k = 0; k <= 14; ++k) {
            if (k % 2 == 0) {
                g.setColor(Color.gray);
            }
            else {
                g.setColor(Color.lightGray);
            }
            g.drawLine(x0, y0 - k * dy / 2, x0 + this.nData, y0 - k * dy / 2);
        }
        g.setColor(Color.red);
        int min = 500000;
        int max = 0;
        int iMin = 0;
        int iMax = 0;
        for (int l = 0; l < this.nData - 1; ++l) {
            if (this.d[l] < min) {
                min = this.d[l];
                iMin = l;
            }
            if (this.d[l] > max) {
                max = this.d[l];
                iMax = l;
            }
            final int Y = (int)Math.round(dy * (this.d[l] - 350000) / 10000.0);
            final int Y2 = (int)Math.round(dy * (this.d[l + 1] - 350000) / 10000.0);
            g.drawLine(x0 + l, y0 - Y, x0 + l + 1, y0 - Y2);
        }
        if (this.newfull) {
            final int nn = 24 * ((int)this.newMoon - 1) + (int)Math.round(24.0 * this.comp.frac(this.newMoon));
            final int ff = 24 * ((int)this.fullMoon - 1) + (int)Math.round(24.0 * this.comp.frac(this.fullMoon));
            int Y = (int)Math.round(dy * (this.d[nn] - 350000) / 10000.0);
            g.setColor(Color.black);
            g.fillOval(x0 + nn - 5, y0 - Y - 5, 10, 10);
            Y = (int)Math.round(dy * (this.d[ff] - 350000) / 10000.0);
            g.drawOval(x0 + ff - 5, y0 - Y - 5, 10, 10);
        }
        g.setColor(Color.blue);
        int Y = (int)Math.round(dy * (this.d[iMin] - 350000) / 10000.0);
        g.drawOval(x0 + iMin - 2, y0 - Y - 2, 4, 4);
        Y = (int)Math.round(dy * (this.d[iMax] - 350000) / 10000.0);
        g.drawOval(x0 + iMax - 2, y0 - Y - 2, 4, 4);
        g.drawString("Min " + this.d[iMin] + " km,  Max " + this.d[iMax] + " km,  Max/Min = " + Math.round(1000 * this.d[iMax] / this.d[iMin]) / 1000.0, 350, 60);
        g.setColor(Color.red);
        g.drawString("www.GeoAstro.de", 650, y0 - 15);
        if (this.isOnline) {
            g.setFont(new Font("Chicago", 0, 48));
            g.setColor(Color.red);
            g.drawString("D E M O", 150, 150);
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
        if (evt.target instanceof Checkbox && evt.target == this.box) {
            this.newfull ^= true;
            this.repaint();
        }
        return true;
    }
}
