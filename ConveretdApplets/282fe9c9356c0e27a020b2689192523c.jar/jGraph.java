import java.awt.Point;
import java.util.Enumeration;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class jGraph extends Applet
{
    public Vector teams;
    public GraphPlotter plotter;
    public Font tckfont;
    public Font legfont;
    public Dimension dim;
    public int maxlabel;
    public int maxgames;
    public int ymax;
    public String[] xticks;
    public String[] yticks;
    public String legonoff;
    public Color xgrid;
    public Color ygrid;
    public Color fg;
    public Color bg;
    public Color gback;
    
    public jGraph() {
        this.tckfont = null;
        this.legfont = null;
        this.maxlabel = 0;
        this.maxgames = 0;
        this.ymax = 24;
    }
    
    public void init() {
        super.init();
        this.teams = new Vector();
        this.dim = this.size();
        int tcksize = 10;
        int legsize = 12;
        final String tickstr = this.getParameter("ticksize");
        final String legstr = this.getParameter("legendsize");
        this.legonoff = this.getParameter("legendonoff");
        if (this.getParameter("ymax") != null) {
            this.ymax = Integer.parseInt(this.getParameter("ymax"));
        }
        if (this.legonoff == null) {
            this.legonoff = "on";
        }
        if (tickstr != null) {
            tcksize = Integer.parseInt(tickstr);
        }
        if (legstr != null) {
            legsize = Integer.parseInt(legstr);
        }
        this.tckfont = new Font("SansSerif", 0, tcksize);
        this.legfont = new Font("SansSerif", 0, legsize);
        final FontMetrics fmt = Toolkit.getDefaultToolkit().getFontMetrics(this.tckfont);
        final FontMetrics fml = Toolkit.getDefaultToolkit().getFontMetrics(this.legfont);
        Team team;
        for (int i = 1; (team = this.getTeamParameters("team".concat(String.valueOf(String.valueOf(i))))) != null; ++i) {
            this.teams.addElement(team);
            final int width = fml.stringWidth(team.getName());
            if (width > this.maxlabel && i % 2 == 1) {
                this.maxlabel = width;
            }
            if (team.getPositions().length > this.maxgames) {
                this.maxgames = team.getPositions().length;
            }
        }
        this.xticks = this.getTicksInfo("xticks");
        this.yticks = this.getTicksInfo("yticks");
        this.xgrid = this.getColorParameter("xgrid");
        this.ygrid = this.getColorParameter("ygrid");
        this.fg = this.getColorParameter("foreground");
        this.bg = this.getColorParameter("background");
        this.gback = this.getColorParameter("graphbackground");
        Rectangle loc;
        if (!this.legonoff.equals("off")) {
            loc = new Rectangle(20, 25, this.dim.width - 30, this.dim.height - 45 - fml.getHeight() * (this.teams.size() / 2 + this.teams.size() % 2));
        }
        else {
            loc = new Rectangle(20, 25, this.dim.width - 30, this.dim.height - 45);
        }
        final Axis axis = new Axis(1.0, this.maxgames + 0.2, 1.0, this.ymax);
        this.plotter = new GraphPlotter(loc, axis);
        this.repaint();
    }
    
    public void paint(final Graphics g) {
        final int j = 0;
        this.setBackground(this.bg);
        final FontMetrics fmt = Toolkit.getDefaultToolkit().getFontMetrics(this.tckfont);
        final FontMetrics fml = Toolkit.getDefaultToolkit().getFontMetrics(this.legfont);
        this.plotter.drawAxis(g, this.xgrid, this.ygrid, this.fg, this.bg, this.gback);
        int no = 0;
        final Enumeration en = this.teams.elements();
        while (en.hasMoreElements()) {
            final Team team = en.nextElement();
            final int[] pos = team.getPositions();
            for (int i = 1; i < pos.length; ++i) {
                g.setColor(team.getColor());
                this.plotter.drawLine(g, new LongPair(i, pos[i - 1]), new LongPair(i + 1, pos[i]), true);
            }
            if (!this.legonoff.equals("off")) {
                g.fillRect(20 + no % 2 * (this.maxlabel + 20), this.dim.height - 15 - fml.getHeight() * (no / 2), 10, 10);
                g.setFont(this.legfont);
                g.setColor(team.getLegColor());
                g.drawString(team.getName(), 20 + no % 2 * (this.maxlabel + 20) + 12, this.dim.height - 5 - fml.getHeight() * (no / 2));
            }
            ++no;
        }
        g.setFont(this.tckfont);
        g.setColor(this.fg);
        if (this.yticks != null) {
            for (int k = 0; k < this.yticks.length; ++k) {
                if (Integer.parseInt(this.yticks[k]) <= this.ymax) {
                    final String tickstr = this.yticks[k];
                    final Point p = this.plotter.getPoint(new LongPair(1.0, Integer.parseInt(tickstr)));
                    g.drawString(tickstr, p.x - fmt.stringWidth(tickstr) - 3, p.y + fmt.getHeight() / 2 - 3);
                    g.drawLine(p.x, p.y, p.x - 2, p.y);
                }
            }
        }
        if (this.xticks != null) {
            for (int k = 0; k < this.xticks.length; ++k) {
                final String[] tick = new String[2];
                final String[] ticktemp = StringUtils.splitString(this.xticks[k], "=");
                tick[0] = ticktemp[0];
                if (ticktemp.length < 2) {
                    tick[1] = tick[0];
                }
                else {
                    tick[1] = ticktemp[1];
                }
                final Point p2 = this.plotter.getPoint(new LongPair(Integer.parseInt(tick[0]), this.ymax));
                g.drawString(tick[1], p2.x - fmt.stringWidth(tick[1]) / 2, p2.y + fmt.getHeight());
                g.drawLine(p2.x, p2.y, p2.x, p2.y + 2);
            }
        }
        g.drawString("Position", 2, fmt.getHeight());
    }
    
    public Color getColorParameter(final String name) {
        final String value = this.getParameter(name);
        try {
            return new Color(Integer.parseInt(value, 16));
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public Team getTeamParameters(final String name) {
        final String ts = this.getParameter(name);
        if (ts == null) {
            return null;
        }
        final String[] bits = StringUtils.splitString(ts, ",");
        if (bits.length < 4) {
            return null;
        }
        final int[] pos = new int[bits.length - 3];
        for (int i = 3; i < bits.length; ++i) {
            pos[i - 3] = Integer.parseInt(bits[i]);
        }
        return new Team(bits[0], new Color(Integer.parseInt(bits[1], 16)), new Color(Integer.parseInt(bits[2], 16)), pos);
    }
    
    public String[] getTicksInfo(final String name) {
        final String ts = this.getParameter(name);
        if (ts == null) {
            return null;
        }
        final String[] bits = StringUtils.splitString(ts, ",");
        return bits;
    }
}
