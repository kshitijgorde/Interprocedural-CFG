// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.calendar;

import java.awt.geom.Rectangle2D;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;
import org.xidget.ifeature.model.ISingleValueUpdateFeature;
import javax.swing.SwingUtilities;
import java.util.Calendar;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.xidget.IXidget;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class CalendarPanel extends JPanel
{
    private final MouseListener mouseListener;
    private final Runnable updateRunnable;
    private IXidget xidget;
    private long time;
    private String[][] labels;
    private int iDay;
    private int jDay;
    
    public CalendarPanel(final IXidget xidget) {
        this.mouseListener = new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent mouseEvent) {
                final int x = mouseEvent.getX();
                final int y = mouseEvent.getY();
                final int width = CalendarPanel.this.getWidth();
                final int height = CalendarPanel.this.getHeight();
                final double n = width / 7.0;
                final double n2 = height / 7.0;
                try {
                    final int n3 = (int)(x / n);
                    final int n4 = (int)(y / n2);
                    final int int1 = Integer.parseInt(CalendarPanel.this.labels[n3][n4]);
                    final Calendar instance = Calendar.getInstance();
                    instance.setTimeInMillis(CalendarPanel.this.time);
                    instance.set(5, int1);
                    CalendarPanel.access$2(CalendarPanel.this, instance.getTimeInMillis());
                    CalendarPanel.this.repaint((int)(CalendarPanel.this.iDay * n), (int)(CalendarPanel.this.jDay * n2), (int)n + 1, (int)n2 + 1);
                    CalendarPanel.access$5(CalendarPanel.this, n3);
                    CalendarPanel.access$6(CalendarPanel.this, n4);
                    CalendarPanel.this.repaint((int)(n3 * n), (int)(n4 * n2), (int)n + 1, (int)n2 + 1);
                    SwingUtilities.invokeLater(CalendarPanel.this.updateRunnable);
                }
                catch (Exception ex) {}
            }
        };
        this.updateRunnable = new Runnable() {
            @Override
            public void run() {
                CalendarPanel.this.xidget.getFeature(ISingleValueUpdateFeature.class).updateModel();
            }
        };
        this.xidget = xidget;
        this.labels = new String[7][7];
        final String[] array = { "S", "M", "T", "W", "T", "F", "S" };
        for (int i = 0; i < this.labels.length; ++i) {
            this.labels[i][0] = array[i];
        }
        for (int j = 0; j < this.labels.length; ++j) {
            for (int k = 1; k < this.labels[j].length; ++k) {
                this.labels[j][k] = "";
            }
        }
        this.setTime(System.currentTimeMillis());
        this.setBackground(Color.WHITE);
        this.addMouseListener(this.mouseListener);
        this.setFocusable(true);
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(final long time) {
        this.time = time;
        this.updateDisplay();
        SwingUtilities.invokeLater(this.updateRunnable);
    }
    
    private void updateDisplay() {
        for (int i = 0; i < this.labels.length; ++i) {
            for (int j = 1; j < this.labels[i].length; ++j) {
                this.labels[i][j] = "";
            }
        }
        final Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(this.time);
        final int value = instance.get(5);
        instance.set(5, 1);
        final int n = instance.get(7) - 1;
        instance.add(2, 1);
        instance.add(5, -1);
        final int value2 = instance.get(5);
        int iDay = n;
        int jDay = 1;
        for (int k = 1; k <= value2; ++k, ++iDay) {
            if (iDay > 6) {
                iDay = 0;
                ++jDay;
            }
            if (k == value) {
                this.iDay = iDay;
                this.jDay = jDay;
            }
            this.labels[iDay][jDay] = new StringBuilder().append(k).toString();
        }
        this.repaint();
    }
    
    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int width = this.getWidth();
        final int height = this.getHeight();
        final double n = width / 7.0;
        final double n2 = height / 7.0;
        final double n3 = n / 2.0;
        final double n4 = n2 / 2.0;
        final int n5 = (int)Math.round(n2);
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, width, n5);
        graphics.setColor(Color.WHITE);
        double n6 = 0.0;
        final double n7 = 0.0;
        for (int i = 0; i < 7; ++i) {
            final Rectangle2D stringBounds = fontMetrics.getStringBounds(this.labels[i][0], graphics);
            graphics.drawString(this.labels[i][0], (int)Math.round(n6 + n3 - (stringBounds.getX() + stringBounds.getWidth() / 2.0)), (int)(n7 + n4 - (stringBounds.getY() + stringBounds.getHeight() / 2.0)));
            n6 += n;
        }
        double n8 = 0.0;
        for (int j = 0; j < 7; ++j) {
            double n9 = n2;
            for (int k = 1; k < 7; ++k) {
                if (j == this.iDay && k == this.jDay) {
                    graphics.setColor(Color.LIGHT_GRAY);
                    graphics.fillRect((int)n8, (int)n9, (int)n + 1, (int)n2 + 1);
                    graphics.setColor(Color.WHITE);
                }
                else {
                    graphics.setColor(Color.BLACK);
                }
                final Rectangle2D stringBounds2 = fontMetrics.getStringBounds(this.labels[j][k], graphics);
                graphics.drawString(this.labels[j][k], (int)(n8 + n3 - (stringBounds2.getX() + stringBounds2.getWidth() / 2.0)), (int)(n9 + n4 - (stringBounds2.getY() + stringBounds2.getHeight() / 2.0)));
                n9 += n2;
            }
            n8 += n;
        }
    }
    
    static /* synthetic */ void access$2(final CalendarPanel calendarPanel, final long time) {
        calendarPanel.time = time;
    }
    
    static /* synthetic */ void access$5(final CalendarPanel calendarPanel, final int iDay) {
        calendarPanel.iDay = iDay;
    }
    
    static /* synthetic */ void access$6(final CalendarPanel calendarPanel, final int jDay) {
        calendarPanel.jDay = jDay;
    }
}
