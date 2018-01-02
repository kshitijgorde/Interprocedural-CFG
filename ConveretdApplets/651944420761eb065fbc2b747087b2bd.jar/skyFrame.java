import java.awt.Event;
import java.util.Map;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class skyFrame extends Frame
{
    static final double K = 0.017453292519943295;
    final char deg = '°';
    double[] rise;
    double[] set;
    double[] elev;
    double[] az;
    int year;
    double latitude;
    double longitude;
    final String[] monthArray;
    compute comp;
    Choice riseChoice;
    Choice setChoice;
    int tR;
    int tS;
    int RS;
    String latString;
    String longString;
    String timeStr;
    Label L;
    int locOffset;
    int date;
    int month;
    double AZ;
    double ELEV;
    double sunRise;
    double sunSet;
    
    public skyFrame(final String titleStr, final int Year, final double lat, final double longi, final String time, final double[] RISE, final double[] SET, final int offset, final int D, final int M, final double sRise, final double sSet) {
        this.rise = new double[13];
        this.set = new double[13];
        this.elev = new double[13];
        this.az = new double[13];
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.tS = 1;
        this.setTitle(titleStr);
        this.setBackground(Color.white);
        this.year = Year;
        this.set = SET;
        this.rise = RISE;
        this.latitude = lat;
        this.longitude = longi;
        this.locOffset = offset;
        this.RS = 2;
        this.date = D;
        this.month = M;
        this.sunRise = sRise;
        this.sunSet = sSet;
        if (this.latitude >= 0.0) {
            this.latString = new StringBuffer().append(this.latitude).append('°').append(" N").toString();
        }
        else {
            this.latString = new StringBuffer().append(Math.abs(this.latitude)).append('°').append(" S").toString();
        }
        if (this.longitude >= 0.0) {
            this.longString = new StringBuffer().append(this.longitude).append('°').append(" E").toString();
        }
        else {
            this.longString = new StringBuffer().append(Math.abs(this.longitude)).append('°').append(" W").toString();
        }
        this.timeStr = time;
        this.setBackground(Color.white);
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        gbc.insets = new Insets(5, 0, 0, 0);
        gbc.gridy = 0;
        gbc.weightx = 10.0;
        gbl.setConstraints(this.riseChoice = new Choice(), gbc);
        this.riseChoice.addItem("Sunrise     ");
        for (int i = 0; i < 7; ++i) {
            this.riseChoice.addItem("Sunrise - " + i + " h");
        }
        this.riseChoice.select(0);
        this.add(this.riseChoice);
        gbl.setConstraints(this.L = new Label(String.valueOf(this.latString) + ", " + this.longString + ", " + this.timeStr + ",  Sunset + 1 h"), gbc);
        this.add(this.L);
        gbl.setConstraints(this.setChoice = new Choice(), gbc);
        this.setChoice.addItem("Sunset");
        for (int j = 0; j < 7; ++j) {
            this.setChoice.addItem("Sunset + " + j + " h");
        }
        this.setChoice.select(2);
        this.add(this.setChoice);
        gbc.gridy = 1;
        gbc.weighty = 160.0;
        final Canvas myCan = new Canvas();
        gbl.setConstraints(myCan, gbc);
        this.add(myCan);
        this.setFont(new Font("Helvetica", 0, 10));
        this.comp = new compute();
        this.calculate(2);
        this.repaint();
    }
    
    public void calculate(final int rs) {
        for (int m = 0; m < 12; ++m) {
            PlanetRiseSet planetRS;
            if (rs == 1) {
                planetRS = new PlanetRiseSet(1, m + 1, this.year + 1900, this.rise[m] - this.locOffset - this.tR, this.latitude, this.longitude, 4, 0.0);
            }
            else {
                planetRS = new PlanetRiseSet(1, m + 1, this.year + 1900, this.set[m] - this.locOffset + this.tS, this.latitude, this.longitude, 4, 0.0);
            }
            this.elev[m] = planetRS.elev();
            this.az[m] = this.comp.check360(planetRS.azim() + 180.0);
        }
        PlanetRiseSet planetRS;
        if (rs == 1) {
            planetRS = new PlanetRiseSet(this.date, this.month + 1, this.year + 1900, this.rise[this.month] - this.locOffset - this.tR, this.latitude, this.longitude, 4, 0.0);
        }
        else {
            planetRS = new PlanetRiseSet(this.date, this.month + 1, this.year + 1900, this.set[this.month] - this.locOffset + this.tS, this.latitude, this.longitude, 4, 0.0);
        }
        this.ELEV = planetRS.elev();
        this.AZ = this.comp.check360(planetRS.azim() + 180.0);
    }
    
    public void paint(final Graphics g) {
        final RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        final Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHints(renderHints);
        final int x0 = 395;
        final int y0 = 240;
        g.setColor(Color.white);
        g.fillRect(0, 0, 800, 550);
        for (int i = -9; i <= 9; ++i) {
            g.setColor(Color.lightGray);
            g.drawLine(35, y0 + i * 20, 755, y0 + i * 20);
            g.setColor(Color.darkGray);
            g.drawString(new StringBuffer().append(-i * 10).append('°').toString(), 5, y0 + i * 20 + 5);
        }
        for (int j = 0; j <= 24; ++j) {
            final int x2 = x0 + Math.round(2 * (j * 15 - 180));
            g.setColor(Color.lightGray);
            g.drawLine(x2, y0 + 180, x2, y0 - 180);
            g.setColor(Color.darkGray);
            g.drawString(new StringBuffer().append(j * 15).append('°').toString(), x2 - 10, y0 + 200);
        }
        g.setColor(Color.gray);
        g.drawLine(x0, y0 - 180, x0, y0 + 180);
        g.drawLine(x0 - 180, y0 - 180, x0 - 180, y0 + 180);
        g.drawLine(x0 + 180, y0 - 180, x0 + 180, y0 + 180);
        g.setColor(Color.red);
        g.drawLine(35, y0, 755, y0);
        g.setColor(Color.black);
        g.drawString("Azimuth", x0 - 18, y0 + 215);
        g.drawString("Altitude", 40, y0 - 165);
        for (int m = 0; m < 12; ++m) {
            final int x2 = x0 + (int)Math.round(2.0 * (this.az[m] - 180.0));
            final int y2 = y0 - (int)Math.round(2.0 * this.elev[m]);
            g.setColor(Color.red);
            g.drawOval(x2 - 2, y2 - 2, 4, 4);
            String str = String.valueOf(this.monthArray[m]) + " 1";
            if (this.RS == 1) {
                str = String.valueOf(str) + "  " + this.comp.makeTimeString(this.rise[m] - this.tR);
            }
            if (this.RS == 2) {
                str = String.valueOf(str) + "  " + this.comp.makeTimeString(this.set[m] + this.tS);
            }
            g.setColor(Color.black);
            g.drawString(str, x2 + 5, y2 + 4);
        }
        final int x2 = x0 + (int)Math.round(2.0 * (this.AZ - 180.0));
        final int y2 = y0 - (int)Math.round(2.0 * this.ELEV);
        g.setColor(Color.red);
        g.fillOval(x2 - 3, y2 - 3, 6, 6);
        String str = String.valueOf(this.monthArray[this.month]) + " " + this.date;
        if (this.RS == 1) {
            str = String.valueOf(str) + "  " + this.comp.makeTimeString(this.sunRise - this.tR);
        }
        if (this.RS == 2) {
            str = String.valueOf(str) + "  " + this.comp.makeTimeString(this.sunSet + this.tS);
        }
        g.drawString(str, x2 - 25, y0 - 165);
        g.drawString("www.GeoAstro.de", x0 + 260, y0 + 173);
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
    
    public boolean action(final Event evt, final Object whichAction) {
        if (evt.target instanceof Choice) {
            if (evt.target == this.riseChoice) {
                this.RS = 1;
                this.setChoice.select(0);
                if (this.riseChoice.getSelectedItem().equals("Sunrise - 0 h")) {
                    this.tR = 0;
                }
                if (this.riseChoice.getSelectedItem().equals("Sunrise - 1 h")) {
                    this.tR = 1;
                }
                if (this.riseChoice.getSelectedItem().equals("Sunrise - 2 h")) {
                    this.tR = 2;
                }
                if (this.riseChoice.getSelectedItem().equals("Sunrise - 3 h")) {
                    this.tR = 3;
                }
                if (this.riseChoice.getSelectedItem().equals("Sunrise - 4 h")) {
                    this.tR = 4;
                }
                if (this.riseChoice.getSelectedItem().equals("Sunrise - 5 h")) {
                    this.tR = 5;
                }
                if (this.riseChoice.getSelectedItem().equals("Sunrise - 6 h")) {
                    this.tR = 6;
                }
                this.L.setText(String.valueOf(this.latString) + ", " + this.longString + ", " + this.timeStr + ",  Sunrise - " + this.tR + " h");
            }
            if (evt.target == this.setChoice) {
                this.RS = 2;
                this.riseChoice.select(0);
                if (this.setChoice.getSelectedItem().equals("Sunset + 0 h")) {
                    this.tS = 0;
                }
                if (this.setChoice.getSelectedItem().equals("Sunset + 1 h")) {
                    this.tS = 1;
                }
                if (this.setChoice.getSelectedItem().equals("Sunset + 2 h")) {
                    this.tS = 2;
                }
                if (this.setChoice.getSelectedItem().equals("Sunset + 3 h")) {
                    this.tS = 3;
                }
                if (this.setChoice.getSelectedItem().equals("Sunset + 4 h")) {
                    this.tS = 4;
                }
                if (this.setChoice.getSelectedItem().equals("Sunset + 5 h")) {
                    this.tS = 5;
                }
                if (this.setChoice.getSelectedItem().equals("Sunset + 6 h")) {
                    this.tS = 6;
                }
                this.L.setText(String.valueOf(this.latString) + ", " + this.longString + ", " + this.timeStr + ",  Sunset + " + this.tS + " h");
            }
            this.calculate(this.RS);
            this.repaint();
        }
        return true;
    }
}
