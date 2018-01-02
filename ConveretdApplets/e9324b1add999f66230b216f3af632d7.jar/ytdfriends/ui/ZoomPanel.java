// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.ui;

import java.awt.Component;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Cursor;
import javax.swing.border.Border;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.net.MalformedURLException;
import java.net.URL;
import ytdfriends.FriendsPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ZoomPanel extends JPanel
{
    private JButton plusZoom;
    private JButton minusZoom;
    private JLabel zoomLabel;
    private JSlider zoomSlider;
    private FriendsPanel fPanel;
    private int prev;
    private static int MAX;
    private static int MIN;
    
    static {
        ZoomPanel.MAX = 9;
        ZoomPanel.MIN = 1;
    }
    
    public ZoomPanel(final FriendsPanel fPanel) {
        this.prev = 5;
        this.fPanel = fPanel;
        this.zoomLabel = new JLabel("Zoom");
        URL plusURL = null;
        URL minusURL = null;
        try {
            plusURL = new URL(String.valueOf(fPanel.baseUrl) + "images/zoom_in.png");
            minusURL = new URL(String.valueOf(fPanel.baseUrl) + "images/zoom_out.png");
        }
        catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        final ImageIcon plusIco = new ImageIcon(plusURL);
        final ImageIcon minusIco = new ImageIcon(minusURL);
        this.plusZoom = new JButton(plusIco);
        this.minusZoom = new JButton(minusIco);
        this.plusZoom.setSize(20, 16);
        this.minusZoom.setSize(20, 16);
        this.plusZoom.setBackground(null);
        this.minusZoom.setBackground(null);
        this.plusZoom.setBorder(null);
        this.minusZoom.setBorder(null);
        this.plusZoom.setCursor(Cursor.getPredefinedCursor(12));
        this.minusZoom.setCursor(Cursor.getPredefinedCursor(12));
        this.plusZoom.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final ZoomPanel panel = (ZoomPanel)ZoomPanel.this.plusZoom.getParent();
                final int value = panel.zoomSlider.getValue();
                if (value < 10) {
                    ZoomPanel.access$2(ZoomPanel.this, value);
                    panel.zoomSlider.setValue(value + 1);
                    if (value == ZoomPanel.MIN) {
                        ZoomPanel.this.minusZoom.setEnabled(true);
                    }
                    if (value == ZoomPanel.MAX - 1) {
                        ZoomPanel.this.plusZoom.setEnabled(false);
                    }
                }
            }
        });
        this.minusZoom.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final ZoomPanel panel = (ZoomPanel)ZoomPanel.this.minusZoom.getParent();
                final int value = panel.zoomSlider.getValue();
                if (value > 1) {
                    ZoomPanel.access$2(ZoomPanel.this, value);
                    panel.zoomSlider.setValue(value - 1);
                    if (value == ZoomPanel.MAX) {
                        ZoomPanel.this.plusZoom.setEnabled(true);
                    }
                    if (value == ZoomPanel.MIN + 1) {
                        ZoomPanel.this.minusZoom.setEnabled(false);
                    }
                }
            }
        });
        (this.zoomSlider = new JSlider(1, 1, 9, 5)).setPreferredSize(new Dimension(20, 150));
        this.zoomSlider.setMaximumSize(new Dimension(200, 25));
        this.zoomSlider.setEnabled(false);
        this.zoomSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent e) {
                final ZoomPanel panel = (ZoomPanel)ZoomPanel.this.zoomSlider.getParent();
                final int value = ZoomPanel.this.zoomSlider.getValue();
                if (ZoomPanel.this.prev - value > 0) {
                    panel.fPanel.zoomOut(value);
                }
                else if (ZoomPanel.this.prev - value < 0) {
                    panel.fPanel.zoomIn(value);
                }
                ZoomPanel.access$2(ZoomPanel.this, value);
            }
        });
        this.add(this.plusZoom);
        this.add(this.zoomSlider);
        this.add(this.minusZoom);
        this.add(this.zoomLabel);
        this.setBackground(Color.WHITE);
        this.setForeground(Color.BLACK);
    }
    
    public void setForeground(final Color c) {
        super.setForeground(c);
        if (this.zoomLabel != null) {
            this.zoomLabel.setForeground(c);
        }
    }
    
    public void setBackground(final Color c) {
        super.setBackground(c);
        if (this.zoomLabel != null) {
            this.zoomLabel.setBackground(c);
        }
        if (this.zoomSlider != null) {
            this.zoomSlider.setBackground(c);
        }
    }
    
    static /* synthetic */ void access$2(final ZoomPanel zoomPanel, final int prev) {
        zoomPanel.prev = prev;
    }
}
