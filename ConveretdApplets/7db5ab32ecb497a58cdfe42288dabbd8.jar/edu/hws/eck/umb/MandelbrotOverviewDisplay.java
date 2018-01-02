// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb;

import java.awt.Graphics;
import java.math.BigDecimal;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import edu.hws.eck.umb.palette.Palette;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Container;
import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import edu.hws.eck.umb.util.I18n;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class MandelbrotOverviewDisplay extends MandelbrotDisplay
{
    private MandelbrotDisplay owner;
    private JLabel limitsDisplay;
    
    public static JDialog createDialog(final MandelbrotDisplay mandelbrotDisplay) {
        Container container;
        for (container = mandelbrotDisplay.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        final JDialog dialog = new JDialog((Frame)container, I18n.tr("mandelbrotOverviewDisplay.DialogTitle", new Object[0]));
        final MandelbrotOverviewDisplay mandelbrotOverviewDisplay = new MandelbrotOverviewDisplay(mandelbrotDisplay);
        final JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(2, 2));
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        contentPane.add(mandelbrotOverviewDisplay, "Center");
        contentPane.add(mandelbrotOverviewDisplay.limitsDisplay, "South");
        dialog.setContentPane(contentPane);
        dialog.setDefaultCloseOperation(2);
        dialog.pack();
        dialog.addWindowListener(new WindowAdapter() {
            public void windowClosed(final WindowEvent windowEvent) {
                mandelbrotOverviewDisplay.closing();
            }
        });
        if (container != null) {
            final Rectangle bounds = container.getBounds();
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int n = bounds.x + bounds.width + 8;
            if (n + dialog.getWidth() > screenSize.width) {
                n = screenSize.width - dialog.getWidth() - 5;
            }
            int n2 = bounds.y + 8;
            if (n2 + dialog.getHeight() > screenSize.height) {
                n2 = screenSize.height - dialog.getHeight() - 5;
            }
            dialog.setLocation(n, n2);
        }
        return dialog;
    }
    
    private MandelbrotOverviewDisplay(final MandelbrotDisplay owner) {
        super(false, false);
        this.owner = owner;
        (this.limitsDisplay = new JLabel()).setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        this.limitsDisplay.setFont(new Font("Monospaced", 0, 11));
        this.limitsDisplay.setBackground(Color.WHITE);
        this.limitsDisplay.setOpaque(true);
        this.checkLimits();
        this.setPreferredSize(new Dimension(250, 250));
        this.setPalette(new Palette(0));
        owner.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                final String propertyName = propertyChangeEvent.getPropertyName();
                if (propertyName.equals("mb_property_limits")) {
                    MandelbrotOverviewDisplay.this.repaint();
                    MandelbrotOverviewDisplay.this.checkLimits();
                }
                else if (propertyName.equals("mb_points_on_orbit")) {
                    MandelbrotOverviewDisplay.this.setPointsOnOrbit((int)propertyChangeEvent.getNewValue());
                }
                else if (propertyName.equals("mb_orbit_point")) {
                    final BigDecimal[] array = (BigDecimal[])propertyChangeEvent.getNewValue();
                    if (array == null) {
                        MandelbrotOverviewDisplay.this.setOrbitStart(null, null);
                    }
                    else {
                        MandelbrotOverviewDisplay.this.setOrbitStart(array[0], array[1]);
                    }
                }
            }
        });
    }
    
    private void checkLimits() {
        final String[] limitsAsStrings = this.owner.getLimitsAsStrings();
        this.limitsDisplay.setText("<html>" + I18n.tr("term.MinimumX", new Object[0]) + " = " + limitsAsStrings[0] + "<br>" + I18n.tr("term.MaximumX", new Object[0]) + " = " + limitsAsStrings[1] + "<br>" + I18n.tr("term.MinimumY", new Object[0]) + " = " + limitsAsStrings[2] + "<br>" + I18n.tr("term.MaximumY", new Object[0]) + " = " + limitsAsStrings[3] + "</html>");
    }
    
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final BigDecimal[] limits = this.getLimits();
        final BigDecimal[] limits2 = this.owner.getLimits();
        final double doubleValue = limits[0].doubleValue();
        final double doubleValue2 = limits[1].doubleValue();
        final double doubleValue3 = limits[2].doubleValue();
        final double doubleValue4 = limits[3].doubleValue();
        final double n = (doubleValue2 - doubleValue) / this.getWidth();
        final double n2 = (doubleValue4 - doubleValue3) / this.getHeight();
        final double doubleValue5 = limits2[0].doubleValue();
        final double doubleValue6 = limits2[1].doubleValue();
        final double doubleValue7 = limits2[2].doubleValue();
        final double doubleValue8 = limits2[3].doubleValue();
        final int n3 = (int)((doubleValue5 - doubleValue) / n + 0.499);
        final int n4 = (int)((doubleValue6 - doubleValue) / n + 0.499);
        final int n5 = (int)((doubleValue4 - doubleValue8) / n2 + 0.499);
        final int n6 = (int)((doubleValue4 - doubleValue7) / n2 + 0.499);
        final int n7 = n4 - n3;
        final int n8 = n6 - n5;
        final int n9 = (n3 + n4) / 2;
        final int n10 = (n5 + n6) / 2;
        if (n7 < 15) {
            graphics.setColor(Color.WHITE);
            graphics.fillRect(n9 - 1, n5 - 15, 3, 15);
            graphics.fillRect(n9 - 1, n6, 3, 15);
            graphics.fillRect(n3 - 15, n10 - 1, 15, 3);
            graphics.fillRect(n4, n10 - 1, 15, 3);
        }
        graphics.setColor(Color.WHITE);
        graphics.drawRect(n3 - 1, n5 - 1, n7 + 1, n8 + 1);
        graphics.drawRect(n3 + 1, n5 + 1, n7 - 3, n8 - 3);
        graphics.setColor(Color.RED);
        if (n7 < 15) {
            graphics.drawLine(n9, n5 - 14, n9, n5);
            graphics.drawLine(n9, n6, n9, n6 + 13);
            graphics.drawLine(n3 - 14, n10, n3, n10);
            graphics.drawLine(n4, n10, n4 + 13, n10);
        }
        graphics.drawRect(n3, n5, n7 - 1, n8 - 1);
    }
}
