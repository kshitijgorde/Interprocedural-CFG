import java.awt.Event;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Checkbox;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class drawDurationFrame extends Frame
{
    double[] Lat;
    final char deg = '°';
    double latitude;
    double T;
    Checkbox box;
    boolean show;
    
    public drawDurationFrame(final String title, final double[] L, final double t, final double lat) {
        this.Lat = new double[50];
        this.show = true;
        this.setTitle("Duration of Daylight  " + title);
        this.setLocation(250, 200);
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(2, 0, 0, 0);
        gbc.gridy = 0;
        gbl.setConstraints(this.box = new Checkbox("Show Latitude"), gbc);
        this.box.setState(this.show);
        this.add(this.box);
        gbc.gridy = 1;
        gbc.weighty = 160.0;
        final Canvas can = new Canvas();
        gbl.setConstraints(can, gbc);
        this.add(can);
        this.latitude = lat;
        this.T = t;
        for (int i = 0; i <= 48; ++i) {
            this.Lat[i] = L[i];
        }
    }
    
    public void paint(final Graphics g) {
        final int y0 = 230;
        final int x0 = 50;
        final int dL = 20;
        final int Dx = 480;
        g.setColor(Color.white);
        g.fillRect(0, 0, 1000, 1000);
        g.setFont(new Font("Helvetica", 0, 10));
        g.setColor(Color.black);
        g.drawString("Latitude", 10, 37);
        for (int i = -9; i <= 9; ++i) {
            if (i % 3 == 0) {
                g.setColor(Color.darkGray);
            }
            else {
                g.setColor(Color.lightGray);
            }
            g.drawLine(x0, y0 - i * dL, x0 + Dx, y0 - i * dL);
            if (i % 3 == 0) {
                g.setColor(Color.black);
            }
            else {
                g.setColor(Color.darkGray);
            }
            g.drawString(new StringBuffer().append(i * 10).append('°').toString(), x0 - 40, y0 - i * dL + 5);
        }
        int x2 = 0;
        int y2 = 0;
        int x3 = 0;
        int y3 = 0;
        for (int t = 0; t < 48; ++t) {
            g.setColor(Color.red);
            if (Math.abs(this.Lat[t]) < 89.5 && Math.abs(this.Lat[t]) > 0.5 && Math.abs(this.Lat[t + 1]) < 89.5 && Math.abs(this.Lat[t + 1]) > 0.5) {
                x2 = x0 + t * 10;
                x3 = x0 + (t + 1) * 10;
                y2 = y0 - (int)Math.round(2.0 * this.Lat[t]);
                y3 = y0 - (int)Math.round(2.0 * this.Lat[t + 1]);
                g.drawLine(x2, y2, x3, y3);
            }
            g.setColor(Color.darkGray);
            if (t % 2 == 0) {
                g.drawLine(x0 + t * 10, y0 - 9 * dL, x0 + t * 10, y0 + 9 * dL);
            }
        }
        g.drawLine(x0 + 480, y0 - 9 * dL, x0 + 480, y0 + 9 * dL);
        if (this.show) {
            g.setColor(Color.blue);
            g.drawOval(x0 + (int)Math.round(this.T * 20.0) - 2, y0 - (int)Math.round(2.0 * this.latitude) - 2, 4, 4);
            g.setColor(Color.blue);
            g.drawString(new StringBuffer().append(Math.round(100.0 * this.latitude) / 100.0).append('°').append(": daylight ").append(Math.round(10.0 * this.T) / 10.0).append(" h").toString(), 350, 37);
        }
        g.setColor(Color.black);
        for (int t2 = 0; t2 <= 24; ++t2) {
            g.drawString(String.valueOf(t2), x0 + t2 * 20 - 5, 430);
        }
        g.drawString("Hours", x0 + 450 - 5, 400);
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Checkbox) {
            if (event.target == this.box) {
                this.show ^= true;
            }
            this.repaint();
        }
        return true;
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
}
