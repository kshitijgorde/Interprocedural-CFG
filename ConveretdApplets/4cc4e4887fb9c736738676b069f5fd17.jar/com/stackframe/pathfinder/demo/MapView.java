// 
// Decompiled by Procyon v0.5.30
// 

package com.stackframe.pathfinder.demo;

import java.awt.Shape;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.util.List;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class MapView extends JPanel implements MouseListener
{
    private static final int width = 12;
    private Map map;
    private Location start;
    private Location goal;
    private List<Location> path;
    
    public MapView(final Map map, final Location start, final Location goal) {
        this.map = map;
        this.start = start;
        this.goal = goal;
        this.setBackground(Color.gray);
        this.addMouseListener(this);
    }
    
    public void setEnabled(final boolean enabled) {
        if (enabled) {
            this.addMouseListener(this);
        }
        else {
            this.removeMouseListener(this);
        }
    }
    
    protected double getCellWidth() {
        return this.getWidth() / this.map.getXSize();
    }
    
    protected double getCellHeight() {
        return this.getHeight() / this.map.getYSize();
    }
    
    public void mouseClicked(final MouseEvent e) {
        final int x = (int)(e.getX() / this.getCellWidth());
        final int y = (int)(e.getY() / this.getCellHeight());
        final Location loc = this.map.getLocation(x, y);
        if (e.isControlDown()) {
            loc.setBlocked(true);
        }
        else if (e.isShiftDown()) {
            loc.setBlocked(false);
            loc.setHeight(0);
        }
        else {
            loc.setHeight(100);
        }
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent e) {
    }
    
    public void mouseReleased(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void setMap(final Map map) {
        this.map = map;
        this.repaint();
    }
    
    public void setStart(final Location start) {
        this.start = start;
        this.repaint();
    }
    
    public void setGoal(final Location goal) {
        this.goal = goal;
        this.repaint();
    }
    
    public void setPath(final List<Location> path) {
        this.path = path;
        this.repaint();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(12 * this.map.getXSize(), 12 * this.map.getYSize());
    }
    
    private Ellipse2D buildCircle(final int x, final int y, final double radius) {
        final double a = x * this.getCellWidth() + (this.getCellWidth() - radius) / 2.0;
        final double b = y * this.getCellHeight() + (this.getCellHeight() - radius) / 2.0;
        return new Ellipse2D.Double(a, b, radius, radius);
    }
    
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D)g;
        for (int i = 0; i < this.map.getXSize(); ++i) {
            for (int j = 0; j < this.map.getYSize(); ++j) {
                final Location t = this.map.getLocation(i, j);
                if (t.getBlocked()) {
                    g2.setColor(Color.black);
                }
                else if (t.getHeight() == 0) {
                    g2.setColor(Color.gray);
                }
                else {
                    g2.setColor(new Color(0, 0, 255));
                }
                final Shape cell = new Rectangle2D.Double(i * this.getCellWidth(), j * this.getCellHeight(), this.getCellWidth(), this.getCellHeight());
                g2.fill(cell);
            }
        }
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double pathWidth = Math.min(this.getCellWidth() / 2.0, this.getCellHeight() / 2.0);
        g2.setColor(Color.green);
        Shape sCircle = this.buildCircle(this.start.getX(), this.start.getY(), pathWidth);
        g2.fill(sCircle);
        g2.setColor(Color.red);
        sCircle = this.buildCircle(this.goal.getX(), this.goal.getY(), pathWidth);
        g2.fill(sCircle);
        pathWidth = Math.min(this.getCellWidth() / 3.0, this.getCellHeight() / 3.0);
        if (this.path != null) {
            g2.setColor(Color.white);
            final int pathLength = this.path.size();
            final float minAlpha = 0.2f;
            for (int k = 0; k < pathLength; ++k) {
                final Location loc = this.path.get(k);
                final float alpha = minAlpha + k / pathLength * (1.0f - minAlpha);
                g2.setComposite(AlphaComposite.getInstance(3, alpha));
                sCircle = this.buildCircle(loc.getX(), loc.getY(), pathWidth);
                g2.fill(sCircle);
            }
        }
    }
}
