// 
// Decompiled by Procyon v0.5.30
// 

package ManPack;

import java.awt.Graphics;
import java.awt.Color;

class Poly
{
    int[] x;
    int[] y;
    int lastPoint;
    boolean hasTicks;
    int radius;
    int centerX;
    int centerY;
    boolean erasing;
    boolean[][] connected;
    private static double tickLen;
    private static int tick;
    
    Poly(final int lastPoint, final int radius, final int centerX, final int centerY) {
        this.lastPoint = lastPoint;
        this.radius = radius;
        this.centerX = centerX;
        this.centerY = centerY;
        this.connected = new boolean[lastPoint + 1][lastPoint + 1];
        this.x = new int[lastPoint + 1];
        this.y = new int[lastPoint + 1];
        double theta = 0.0;
        final double delta = 6.283185307179586 / (lastPoint + 1);
        for (int i = 0; i <= lastPoint; ++i) {
            this.x[i] = (int)(Math.cos(theta) * radius) + centerX;
            this.y[i] = (int)(Math.sin(theta) * radius) + centerY;
            theta += delta;
        }
    }
    
    public int mod(final int i, final int m) {
        final int truncatedResult = i / m;
        return i - truncatedResult * m;
    }
    
    public void zero() {
        for (int i = 0; i <= this.lastPoint; ++i) {
            for (int j = 0; j <= this.lastPoint; ++j) {
                this.connected[i][j] = false;
            }
        }
    }
    
    public void drawPoly(final Color color, final Graphics g) {
        final Color oldForeground = g.getColor();
        g.setColor(color);
        g.drawPolyline(this.x, this.y, this.lastPoint + 1);
        g.drawLine(this.x[this.lastPoint], this.y[this.lastPoint], this.x[0], this.y[0]);
        for (int i = 0; i <= this.lastPoint; ++i) {
            g.drawLine(this.x[i] + Poly.tick, this.y[i] + Poly.tick, this.x[i] - Poly.tick, this.y[i] - Poly.tick);
            g.drawLine(this.x[i] + Poly.tick, this.y[i] - Poly.tick, this.x[i] - Poly.tick, this.y[i] + Poly.tick);
        }
        g.setColor(oldForeground);
    }
    
    private boolean foundALineForThisPoint(final int curPoint, final Line curLine) {
        int toPoint = this.mod(curPoint + 1, this.x.length);
        boolean found;
        do {
            found = !this.connected[curPoint][toPoint];
            if (!found) {
                toPoint = this.mod(toPoint + 1, this.x.length);
            }
        } while (!found && toPoint != curPoint);
        if (found) {
            curLine.startFrom = curPoint;
            curLine.goTo = toPoint;
        }
        return found;
    }
    
    public boolean thereAreAnyLinesToDraw(int curPoint, final Line curLine) {
        final int startedAt = curPoint;
        boolean found;
        do {
            found = this.foundALineForThisPoint(curPoint, curLine);
            if (!found) {
                curPoint = this.mod(curPoint + 1, this.x.length);
            }
        } while (!found && curPoint != startedAt);
        return found;
    }
    
    static {
        Poly.tickLen = 10.0;
        Poly.tick = (int)(Math.sin(0.7853981633974483) * (Poly.tickLen / 2));
    }
}
