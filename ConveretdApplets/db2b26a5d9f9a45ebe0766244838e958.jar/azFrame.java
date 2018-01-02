import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class azFrame extends Frame
{
    final char deg = '°';
    int locOffset;
    int N;
    int[] az;
    int[] elev;
    boolean demo;
    boolean sun;
    double currentAzim;
    double currentElev;
    double time;
    double transTime;
    double transElev;
    Checkbox azBox;
    Checkbox elevBox;
    Checkbox dayBox;
    Checkbox dAzBox;
    boolean azOK;
    boolean elevOK;
    boolean dayOK;
    boolean dAzOK;
    compute comp;
    int[] dAz;
    
    public azFrame(final String titleStr, final double[] azData, final double[] elevData, final int nData, final int y, final double trans, final double transAlt, final boolean isSun, final double curAz, final double curElev, final double UT, final int offset, final boolean isDemo) {
        this.az = new int[100];
        this.elev = new int[100];
        this.azOK = true;
        this.elevOK = true;
        this.dayOK = false;
        this.dAzOK = false;
        this.dAz = new int[100];
        this.setTitle(titleStr);
        this.setBackground(Color.white);
        this.comp = new compute();
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(5, 30, 0, 0);
        gbl.setConstraints(this.azBox = new Checkbox("Azimuth"), gbc);
        this.azBox.setState(true);
        this.add(this.azBox);
        gbl.setConstraints(this.dAzBox = new Checkbox("d Az"), gbc);
        this.dAzBox.setState(false);
        this.add(this.dAzBox);
        gbl.setConstraints(this.dayBox = new Checkbox("Day only"), gbc);
        this.dayBox.setState(false);
        this.add(this.dayBox);
        gbl.setConstraints(this.elevBox = new Checkbox("Elevation"), gbc);
        this.elevBox.setState(true);
        this.add(this.elevBox);
        gbc.gridy = 2;
        gbc.weighty = 160.0;
        final Canvas myCan = new Canvas();
        gbl.setConstraints(myCan, gbc);
        this.add(myCan);
        this.N = nData;
        this.transTime = trans;
        this.locOffset = offset;
        this.demo = isDemo;
        this.transElev = transAlt;
        this.sun = isSun;
        this.currentAzim = curAz;
        this.currentElev = curElev;
        this.time = UT + offset;
        for (int i = 0; i <= nData; ++i) {
            if (i < nData) {
                this.dAz[i + 1] = (int)Math.round(15.0 * (azData[i + 1] - azData[i]));
            }
            this.az[i] = (int)Math.round(azData[i]);
            this.elev[i] = (int)Math.round(2.0 * elevData[i]);
        }
        this.repaint();
    }
    
    public void paint(final Graphics g) {
        final int y0 = 430;
        final int links = 50;
        final int dx = 5;
        final int DX = 20;
        g.setColor(Color.white);
        g.fillRect(0, 0, 1000, 1000);
        g.setColor(Color.darkGray);
        for (int a = 0; a < 13; ++a) {
            g.drawLine(links, y0 - a * 30, links + 24 * DX, y0 - a * 30);
        }
        for (int n = 0; n <= 24; ++n) {
            g.drawLine(links + n * DX, y0, links + n * DX, y0 - 360);
        }
        g.setColor(Color.blue);
        g.setFont(new Font("Helvetica", 0, 10));
        for (int i = -6; i <= 6; ++i) {
            g.drawString(new StringBuffer().append(i * 15).append('°').toString(), links + 24 * DX + 10, y0 - 180 - i * 30 + 2);
        }
        g.drawString("Elev.", links + 24 * DX - 20, 60);
        g.setColor(Color.red);
        for (int j = 0; j < 13; ++j) {
            g.drawString(new StringBuffer().append(j * 30).append('°').toString(), links - 35, y0 - j * 30 + 2);
        }
        g.drawString("Azim.", links, 60);
        g.setColor(Color.black);
        for (int k = 0; k <= 12; ++k) {
            g.drawString(String.valueOf(2 * k), links + k * 2 * DX - 3, y0 + 13);
        }
        g.setColor(Color.red);
        g.drawLine(links + (int)Math.round(this.transTime * DX), y0, links + (int)Math.round(this.transTime * DX), y0 - 360);
        g.drawString(this.comp.makeTimeString(this.transTime), links - 15 + (int)Math.round(this.transTime * DX), y0 + 30);
        g.setColor(Color.blue);
        g.drawLine(links, y0 - 180, links + 24 * DX, y0 - 180);
        if (this.dAzOK) {
            g.setColor(Color.green);
            for (int l = 0; l < this.N; ++l) {
                if (this.dayOK) {
                    if (this.elev[l] > -0.8) {
                        g.drawOval(links + l * dx - 1, y0 - 180 - this.dAz[l] - 1, 2, 2);
                    }
                }
                else {
                    g.drawOval(links + l * dx - 1, y0 - 180 - this.dAz[l] - 1, 2, 2);
                }
            }
        }
        for (int l = 0; l < this.N; ++l) {
            if (this.azOK) {
                g.setColor(Color.red);
                if (this.dayOK) {
                    if (this.elev[l] > -0.8) {
                        g.drawOval(links + l * dx - 1, y0 - this.az[l] - 1, 2, 2);
                    }
                }
                else {
                    g.drawOval(links + l * dx - 1, y0 - this.az[l] - 1, 2, 2);
                }
            }
            if (this.elevOK) {
                g.setColor(Color.blue);
                if (this.dayOK) {
                    if (this.elev[l] > -0.8) {
                        g.drawOval(links + l * dx - 1, y0 - 180 - this.elev[l] - 1, 2, 2);
                    }
                }
                else {
                    g.drawOval(links + l * dx - 1, y0 - 180 - this.elev[l] - 1, 2, 2);
                }
            }
        }
        if (this.sun) {
            if (this.azOK) {
                final int azSun = (int)Math.round(this.currentAzim);
                final int xSun = (int)Math.round(DX * this.time);
                g.setColor(Color.black);
                g.drawOval(links + xSun - 6, y0 - azSun - 6, 12, 12);
                g.setColor(Color.red);
                g.fillOval(links + xSun - 5, y0 - azSun - 5, 10, 10);
            }
            if (this.elevOK) {
                final int elevSun = (int)Math.round(2.0 * this.currentElev);
                final int xSun = (int)Math.round(DX * this.time);
                g.setColor(Color.black);
                g.drawOval(links + xSun - 6, y0 - 180 - elevSun - 6, 12, 12);
                g.setColor(Color.blue);
                g.fillOval(links + xSun - 5, y0 - 180 - elevSun - 5, 10, 10);
                g.setColor(Color.blue);
                g.setFont(new Font("Helvetica", 0, 10));
                final int y2 = (int)Math.round(2.0 * this.transElev);
                g.drawString(new StringBuffer().append(Math.round(10.0 * this.transElev) / 10.0).append('°').toString(), links + 24 * DX + 35, y0 - 180 - y2);
            }
        }
        g.setColor(Color.black);
        g.drawRect(links, y0 - 360, DX * 24, 360);
        if (this.demo) {
            g.setFont(new Font("Chicago", 0, 100));
            g.setColor(Color.red);
            g.drawString("D E M O", 40, 300);
        }
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Checkbox) {
            if (event.target == this.azBox) {
                this.azOK ^= true;
            }
            if (event.target == this.elevBox) {
                this.elevOK ^= true;
            }
            if (event.target == this.dayBox) {
                this.dayOK ^= true;
            }
            if (event.target == this.dAzBox) {
                this.dAzOK ^= true;
            }
        }
        this.repaint();
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
