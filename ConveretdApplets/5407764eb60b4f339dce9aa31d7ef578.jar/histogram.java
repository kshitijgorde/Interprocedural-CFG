import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
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

class histogram extends Frame
{
    final double K = 0.017453292519943295;
    char deg;
    int x0;
    int y0;
    int[] f;
    int[] ff;
    String text;
    Checkbox box;
    boolean int2OK;
    double latitude;
    double hAngle;
    int RiseSet;
    int RS;
    int pm;
    int SM;
    
    public histogram(final int sm, String titleStr, final String t, final int[] fr, final double lat, final double angle, final int rs, final boolean demo) {
        this.deg = '°';
        this.f = new int[180];
        this.ff = new int[180];
        this.int2OK = false;
        if (sm == 1) {
            this.SM = 1;
        }
        else {
            this.SM = 2;
        }
        if (rs == 1) {
            titleStr = "Rise " + titleStr;
            this.RS = 0;
            this.pm = 1;
        }
        else {
            titleStr = "Set " + titleStr;
            this.RS = 180;
            this.pm = -1;
        }
        this.setTitle(String.valueOf(titleStr) + ",   " + t);
        this.x0 = 40;
        this.y0 = 420;
        this.text = t;
        this.latitude = lat;
        this.hAngle = angle;
        this.RiseSet = rs;
        for (int i = 0; i < 180; ++i) {
            this.f[i] = fr[i];
        }
        final GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 100.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 0, 0);
        gbl.setConstraints(this.box = new Checkbox("Interval 2°"), gbc);
        this.add(this.box);
        this.box.setState(this.int2OK);
        gbc.gridx = 1;
        final Canvas can = new Canvas();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 160.0;
        gbl.setConstraints(can, gbc);
        this.add(can);
    }
    
    public boolean action(final Event evt, final Object whichAction) {
        if (evt.target instanceof Checkbox && evt.target == this.box) {
            this.int2OK ^= true;
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
    
    public void paint(final Graphics g) {
        g.setFont(new Font("Courier", 0, 10));
        g.setColor(Color.white);
        g.fillRect(0, 0, 1000, 1000);
        g.setColor(Color.black);
        int F = 22;
        int N = 15;
        if (this.int2OK) {
            F = 11;
            N = 30;
            for (int i = 1; i <= 179; ++i) {
                if (i % 2 == 0) {
                    this.ff[i] = this.f[i] + this.f[i + 1];
                    this.ff[i + 1] = this.f[i] + this.f[i + 1];
                }
            }
        }
        for (int i = 1; i <= N; ++i) {
            g.setColor(Color.lightGray);
            final int y = this.y0 - i * F;
            g.drawLine(this.x0, y, this.x0 + 720, y);
            g.setColor(Color.black);
            g.drawString(String.valueOf(i), 20, y + 4);
        }
        int d;
        if (this.RiseSet == 1) {
            d = 0;
        }
        else {
            d = 180;
        }
        for (int j = 0; j <= 18; ++j) {
            g.setColor(Color.lightGray);
            final int x = this.x0 + j * 10 * 4;
            g.drawLine(x, this.y0 + 10, x, this.y0 - 330);
            g.setColor(Color.black);
            g.drawString(String.valueOf(d + j * 10), x - 7, this.y0 + 25);
        }
        g.setColor(Color.red);
        final int sum = 0;
        g.setColor(Color.red);
        for (int k = 1; k < 180; ++k) {
            if (this.int2OK) {
                final int y = this.y0 - F * this.ff[k];
                final int x = this.x0 + 4 * k;
                g.drawLine(x + 1, this.y0, x + 1, y);
                g.drawLine(x + 2, this.y0, x + 2, y);
                g.drawLine(x + 3, this.y0, x + 3, y);
                g.drawLine(x + 4, this.y0, x + 4, y);
                if (k % 2 == 0) {
                    g.setColor(Color.white);
                    g.drawLine(x, this.y0, x, y);
                    g.setColor(Color.red);
                }
            }
            else {
                final int x = this.x0 + 4 * k;
                final int y = this.y0 - F * this.f[k];
                g.drawLine(x + 1, this.y0, x + 1, y);
                g.drawLine(x + 2, this.y0, x + 2, y);
                g.drawLine(x + 3, this.y0, x + 3, y);
            }
        }
        g.setColor(Color.blue);
        final String elevStr = "Elevation " + this.hAngle + this.deg;
        g.drawString(elevStr, this.x0, this.y0 - 335);
        g.drawString("Elevation 0" + this.deg, this.x0, this.y0 - 350);
        if (this.SM == 2) {
            final double max = 28.585;
            final double min = -28.585;
            double az = Math.acos(Math.sin(0.017453292519943295 * max) / Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
            int x = this.x0 + (int)Math.round(4.0 * az);
            g.drawLine(x, this.y0, x, this.y0 - 330);
            g.drawString(new StringBuffer().append(Math.round(10.0 * (az + this.RS)) / 10.0).append(this.deg).toString(), x - 10, this.y0 - 350);
            double PA = this.pm * Math.acos(Math.sin(0.017453292519943295 * this.latitude) / Math.cos(0.017453292519943295 * max)) / 0.017453292519943295;
            double deltaAZ = this.hAngle / Math.tan(0.017453292519943295 * PA);
            g.drawString("PA " + Math.round(10.0 * PA) / 10.0 + this.deg, x - 10, this.y0 - 365);
            x = this.x0 + (int)Math.round(4.0 * (az + deltaAZ));
            g.drawLine(x, this.y0, x, this.y0 - 300);
            g.drawString(new StringBuffer().append(Math.round(10.0 * (az + deltaAZ + this.RS)) / 10.0).append(this.deg).toString(), x - 10, this.y0 - 335);
            az = Math.acos(Math.sin(0.017453292519943295 * min) / Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
            x = this.x0 + (int)Math.round(4.0 * az);
            g.drawLine(x, this.y0, x, this.y0 - 330);
            g.drawString(new StringBuffer().append(Math.round(10.0 * (az + this.RS)) / 10.0).append(this.deg).toString(), x - 10, this.y0 - 350);
            g.drawString("PA " + Math.round(10.0 * PA) / 10.0 + this.deg, x - 10, this.y0 - 365);
            PA = this.pm * Math.acos(Math.sin(0.017453292519943295 * this.latitude) / Math.cos(0.017453292519943295 * min)) / 0.017453292519943295;
            deltaAZ = this.hAngle / Math.tan(0.017453292519943295 * PA);
            x = this.x0 + (int)Math.round(4.0 * (az + deltaAZ));
            g.drawLine(x, this.y0, x, this.y0 - 300);
            g.drawString(new StringBuffer().append(Math.round(10.0 * (az + deltaAZ + this.RS)) / 10.0).append(this.deg).toString(), x - 10, this.y0 - 335);
            g.setColor(Color.magenta);
            final double maxK = 18.425;
            final double minK = -18.425;
            az = Math.acos(Math.sin(0.017453292519943295 * maxK) / Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
            x = this.x0 + (int)Math.round(4.0 * az);
            g.drawLine(x, this.y0, x, this.y0 - 330);
            g.drawString(new StringBuffer().append(Math.round(10.0 * az) / 10.0).append(this.deg).toString(), x - 10, this.y0 - 350);
            PA = this.pm * Math.acos(Math.sin(0.017453292519943295 * this.latitude) / Math.cos(0.017453292519943295 * maxK)) / 0.017453292519943295;
            deltaAZ = this.hAngle / Math.tan(0.017453292519943295 * PA);
            g.drawString("PA " + Math.round(10.0 * PA) / 10.0 + this.deg, x - 10, this.y0 - 365);
            x = this.x0 + (int)Math.round(4.0 * (az + deltaAZ));
            g.drawLine(x, this.y0, x, this.y0 - 300);
            g.drawString(new StringBuffer().append(Math.round(10.0 * (az + deltaAZ)) / 10.0).append(this.deg).toString(), x - 10, this.y0 - 335);
            az = Math.acos(Math.sin(0.017453292519943295 * minK) / Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
            x = this.x0 + (int)Math.round(4.0 * az);
            g.drawLine(x, this.y0, x, this.y0 - 330);
            g.drawString(new StringBuffer().append(Math.round(10.0 * az) / 10.0).append(this.deg).toString(), x - 10, this.y0 - 350);
            g.drawString("PA " + Math.round(10.0 * PA) / 10.0 + this.deg, x - 10, this.y0 - 365);
            PA = this.pm * Math.acos(Math.sin(0.017453292519943295 * this.latitude) / Math.cos(0.017453292519943295 * minK)) / 0.017453292519943295;
            deltaAZ = this.hAngle / Math.tan(0.017453292519943295 * PA);
            x = this.x0 + (int)Math.round(4.0 * (az + deltaAZ));
            g.drawLine(x, this.y0, x, this.y0 - 300);
            g.drawString(new StringBuffer().append(Math.round(10.0 * (az + deltaAZ)) / 10.0).append(this.deg).toString(), x - 10, this.y0 - 335);
        }
        else {
            final double max = 23.57;
            final double min = -23.57;
            double az = Math.acos(Math.sin(0.017453292519943295 * max) / Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
            int x = this.x0 + (int)Math.round(4.0 * az);
            g.drawLine(x, this.y0, x, this.y0 - 330);
            g.drawString(new StringBuffer().append(Math.round(10.0 * (az + this.RS)) / 10.0).append(this.deg).toString(), x - 10, this.y0 - 350);
            double PA = this.pm * Math.acos(Math.sin(0.017453292519943295 * this.latitude) / Math.cos(0.017453292519943295 * max)) / 0.017453292519943295;
            double deltaAZ = this.hAngle / Math.tan(0.017453292519943295 * PA);
            g.drawString("PA " + Math.round(10.0 * PA) / 10.0 + this.deg, x - 10, this.y0 - 365);
            x = this.x0 + (int)Math.round(4.0 * (az + deltaAZ));
            g.drawLine(x, this.y0, x, this.y0 - 300);
            g.drawString(new StringBuffer().append(Math.round(10.0 * (az + deltaAZ + this.RS)) / 10.0).append(this.deg).toString(), x - 10, this.y0 - 335);
            az = Math.acos(Math.sin(0.017453292519943295 * min) / Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
            x = this.x0 + (int)Math.round(4.0 * az);
            g.drawLine(x, this.y0, x, this.y0 - 330);
            g.drawString(new StringBuffer().append(Math.round(10.0 * (az + this.RS)) / 10.0).append(this.deg).toString(), x - 10, this.y0 - 350);
            g.drawString("PA " + Math.round(10.0 * PA) / 10.0 + this.deg, x - 10, this.y0 - 365);
            PA = this.pm * Math.acos(Math.sin(0.017453292519943295 * this.latitude) / Math.cos(0.017453292519943295 * min)) / 0.017453292519943295;
            deltaAZ = this.hAngle / Math.tan(0.017453292519943295 * PA);
            x = this.x0 + (int)Math.round(4.0 * (az + deltaAZ));
            g.drawLine(x, this.y0, x, this.y0 - 300);
            g.drawString(new StringBuffer().append(Math.round(10.0 * (az + deltaAZ + this.RS)) / 10.0).append(this.deg).toString(), x - 10, this.y0 - 335);
        }
    }
}
