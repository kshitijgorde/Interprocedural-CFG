// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import edu.hws.eck.umb.util.I18n;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.Timer;
import javax.swing.JLabel;

class StatusBar extends JLabel
{
    private String mainText;
    private Timer timer;
    
    public StatusBar(final MandelbrotDisplay mandelbrotDisplay) {
        this.setOpaque(true);
        this.setBackground(new Color(230, 230, 230));
        this.setForeground(new Color(150, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(3, 5, 2, 1));
        this.setText(I18n.tr("statusBar.text.idle", new Object[0]));
        mandelbrotDisplay.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                final String propertyName = propertyChangeEvent.getPropertyName();
                if (propertyName.equals("mb_stauts")) {
                    final MandelbrotDisplay.StatusInfo statusInfo = (MandelbrotDisplay.StatusInfo)propertyChangeEvent.getNewValue();
                    if (statusInfo.status == 0 || statusInfo.status == 2) {
                        StatusBar.this.setText(I18n.tr("statusBar.text.Idle", new Object[0]));
                    }
                    else {
                        String s = "";
                        if (mandelbrotDisplay.getSubpixelSamplingEnabled()) {
                            if (statusInfo.status == 1) {
                                s = I18n.tr("statusBar.text.FirstPass", new Object[0]) + "  ";
                            }
                            else if (statusInfo.status == 3) {
                                s = I18n.tr("statusBar.text.SecondPass", new Object[0]) + "  ";
                            }
                        }
                        final BigDecimal[] limits = mandelbrotDisplay.getLimits();
                        final BigDecimal divide = limits[3].subtract(limits[2]).divide(new BigDecimal(mandelbrotDisplay.getActualImageSize().height - 1), limits[3].scale(), 6);
                        String s2;
                        if (mandelbrotDisplay.getHighPrecisionEnabled() && Math.abs(divide.doubleValue()) < 1.0E-15) {
                            s2 = s + I18n.tr("statusBar.text.HighPrecision", "" + limits[0].scale());
                        }
                        else {
                            s2 = s + I18n.tr("statusBar.text.NormalPrecision", new Object[0]);
                        }
                        StatusBar.this.setText(s2 + " " + I18n.tr("statusBar.text.PercentComplete", "" + (int)(statusInfo.fractionComplete * 100.0)));
                    }
                }
                else if (propertyName.equals("mb_OSC_size")) {
                    final Dimension dimension = (Dimension)propertyChangeEvent.getNewValue();
                    if (dimension != null) {
                        StatusBar.this.setTempText(I18n.tr("statusBar.text.NewImageSize", "" + dimension.width, "" + dimension.height), 3);
                    }
                }
                else if (propertyName.equals("mb_orbit_point")) {
                    final String[] orbitStartPointAsStrings = mandelbrotDisplay.getOrbitStartPointAsStrings();
                    if (orbitStartPointAsStrings != null) {
                        StatusBar.this.setTempText(I18n.tr("statusBar.text.OrbitPointCoords", orbitStartPointAsStrings[0], orbitStartPointAsStrings[1]), 6);
                    }
                    else {
                        StatusBar.this.clearTempText();
                    }
                }
            }
        });
    }
    
    public void setText(final String s) {
        this.mainText = s;
        if (this.timer == null) {
            super.setText(s);
        }
    }
    
    public void clearTempText() {
        if (this.timer != null) {
            this.timer.stop();
        }
        super.setText(this.mainText);
    }
    
    public void setTempText(final String text, final int n) {
        super.setText(text);
        if (this.timer != null) {
            this.timer.stop();
        }
        (this.timer = new Timer(1, new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                StatusBar.this.setText(StatusBar.this.mainText);
                StatusBar.this.timer = null;
            }
        })).setInitialDelay(1000 * n);
        this.timer.setRepeats(false);
        this.timer.start();
    }
}
