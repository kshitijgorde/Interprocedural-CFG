// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import java.net.MalformedURLException;
import javax.swing.ImageIcon;
import java.net.URL;
import logging.LogHolder;
import logging.LogType;
import javax.swing.Icon;
import javax.swing.event.ChangeEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import anon.util.JAPMessages;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import gui.dialog.JAPDialog;

public class MapBox extends JAPDialog implements ChangeListener
{
    private static final String MSG_ERROR_WHILE_LOADING;
    private static final String MSG_PLEASE_WAIT;
    private static final String MSG_CLOSE;
    private static final String MSG_TITLE;
    private static final String MSG_ZOOM;
    private JLabel m_lblMap;
    private JSlider m_sldZoom;
    private JButton m_btnClose;
    private String m_sImageURL;
    private static final String KEY = "ABQIAAAAvDhPn6b_F550GDisnEZpIxQrda7TSvuNFYSGo_31R-LaV_0iCRRJ7r3yduvtz_ZgBJjj2VOFap_JoQ";
    private String m_sLatitude;
    private String m_sLongitude;
    private String m_sImageSize;
    static /* synthetic */ Class class$gui$MapBox;
    
    public MapBox(final Component component, final String sLatitude, final String sLongitude, final int n) {
        super(component, "");
        this.m_sImageSize = "550x550";
        this.m_sLongitude = sLongitude;
        this.m_sLatitude = sLatitude;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.getContentPane().setLayout(layout);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        this.m_lblMap = new JLabel();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        layout.setConstraints(this.m_lblMap, gridBagConstraints);
        this.getContentPane().add(this.m_lblMap);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.insets = new Insets(20, 10, 5, 10);
        final JLabel label = new JLabel(JAPMessages.getString(MapBox.MSG_ZOOM));
        layout.setConstraints(label, gridBagConstraints);
        this.getContentPane().add(label);
        (this.m_sldZoom = new JSlider(1, 0, 15, n)).setPaintTicks(true);
        this.m_sldZoom.setMajorTickSpacing(1);
        this.m_sldZoom.setMinorTickSpacing(1);
        this.m_sldZoom.setSnapToTicks(true);
        this.m_sldZoom.setPaintLabels(true);
        this.m_sldZoom.setRequestFocusEnabled(false);
        this.m_sldZoom.addChangeListener(this);
        gridBagConstraints.insets = new Insets(5, 10, 20, 10);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 3;
        layout.setConstraints(this.m_sldZoom, gridBagConstraints);
        this.getContentPane().add(this.m_sldZoom);
        (this.m_btnClose = new JButton(JAPMessages.getString(MapBox.MSG_CLOSE))).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MapBox.this.dispose();
            }
        });
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        layout.setConstraints(this.m_btnClose, gridBagConstraints);
        this.getContentPane().add(this.m_btnClose);
        this.refresh();
        this.pack();
        this.setResizable(false);
    }
    
    public void setGeo(final String sLatitude, final String sLongitude) throws IOException {
        this.m_sLongitude = sLongitude;
        this.m_sLatitude = sLatitude;
        this.refresh();
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        if (!((JSlider)changeEvent.getSource()).getValueIsAdjusting()) {
            this.refresh();
        }
    }
    
    private void refresh() {
        this.m_lblMap.setIcon(null);
        this.m_lblMap.setText(JAPMessages.getString(MapBox.MSG_PLEASE_WAIT) + "...");
        this.m_lblMap.repaint();
        this.m_sImageURL = "http://maps.google.com/staticmap?markers=" + this.m_sLatitude + "," + this.m_sLongitude + "&zoom=" + (this.m_sldZoom.getValue() + 2) + "&size=" + this.m_sImageSize + "&key=" + "ABQIAAAAvDhPn6b_F550GDisnEZpIxQrda7TSvuNFYSGo_31R-LaV_0iCRRJ7r3yduvtz_ZgBJjj2VOFap_JoQ";
        LogHolder.log(7, LogType.MISC, "Getting map: " + this.m_sImageURL);
        this.setTitle(JAPMessages.getString(MapBox.MSG_TITLE, new String[] { this.m_sLatitude, this.m_sLongitude }));
        try {
            final ImageIcon icon = new ImageIcon(new URL(this.m_sImageURL));
            if (icon.getIconHeight() == -1) {
                this.dispose();
                JAPDialog.showErrorDialog(this.getParentComponent(), JAPMessages.getString(MapBox.MSG_ERROR_WHILE_LOADING), LogType.NET);
            }
            else {
                this.m_lblMap.setText("");
                this.m_lblMap.setIcon(icon);
            }
        }
        catch (MalformedURLException ex) {
            this.dispose();
            JAPDialog.showErrorDialog(this.getParentComponent(), ex.getMessage(), LogType.NET);
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        MSG_ERROR_WHILE_LOADING = ((MapBox.class$gui$MapBox == null) ? (MapBox.class$gui$MapBox = class$("gui.MapBox")) : MapBox.class$gui$MapBox).getName() + "_errorLoading";
        MSG_PLEASE_WAIT = ((MapBox.class$gui$MapBox == null) ? (MapBox.class$gui$MapBox = class$("gui.MapBox")) : MapBox.class$gui$MapBox).getName() + "_pleaseWait";
        MSG_CLOSE = ((MapBox.class$gui$MapBox == null) ? (MapBox.class$gui$MapBox = class$("gui.MapBox")) : MapBox.class$gui$MapBox).getName() + "_close";
        MSG_TITLE = ((MapBox.class$gui$MapBox == null) ? (MapBox.class$gui$MapBox = class$("gui.MapBox")) : MapBox.class$gui$MapBox).getName() + "_title";
        MSG_ZOOM = ((MapBox.class$gui$MapBox == null) ? (MapBox.class$gui$MapBox = class$("gui.MapBox")) : MapBox.class$gui$MapBox).getName() + "_zoom";
    }
    
    private class SiteParser extends HTMLEditorKit.ParserCallback
    {
        public void handleSimpleTag(final HTML.Tag tag, final MutableAttributeSet set, final int n) {
            this.handleStartTag(tag, set, n);
        }
        
        public void handleStartTag(final HTML.Tag tag, final MutableAttributeSet set, final int n) {
            if (tag == HTML.Tag.IMG) {
                try {
                    if (set.getAttribute(HTML.Attribute.ID).toString().equals("map")) {
                        LogHolder.log(7, LogType.MISC, "Map image found: " + set.getAttribute(HTML.Attribute.SRC).toString());
                        MapBox.this.m_sImageURL = set.getAttribute(HTML.Attribute.SRC).toString();
                    }
                }
                catch (NullPointerException ex) {}
            }
        }
    }
}
