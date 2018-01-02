// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeEvent;
import com.itt.J2KViewer.controller.DimensionManager;
import java.util.List;
import java.awt.Point;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.itt.J2KViewer.controller.WaypointManager;
import com.itt.J2KViewer.controller.ViewCentral;
import java.beans.PropertyChangeListener;
import javax.swing.event.ChangeListener;
import javax.swing.JDialog;

public class WaypointDialog extends JDialog implements ChangeListener, PropertyChangeListener
{
    private ViewCentral viewCentral;
    private WaypointManager waypointManager;
    private JPanel instructionsPanel;
    private JPanel sliderPanel;
    private JPanel buttonPanel;
    private JLabel instructionsLabel1;
    private JLabel instructionsLabel2;
    private JSlider scrollWaitSlider;
    private JButton startButton;
    private JButton stopButton;
    private JButton closeButton;
    private static final int SCROLL_WAIT_MIN = 100;
    private static final int SCROLL_WAIT_MAX = 0;
    private static final int SCROLL_WAIT_SLIDER_MIN = 0;
    private static final int SCROLL_WAIT_SLIDER_MAX = 100;
    private static final int SCROLL_WAIT_TICK_SPACING = 10;
    private static final double SCROLL_WAIT_SLIDER_SCALE = -1.0;
    
    public WaypointDialog(final Frame frame, final boolean b, final ViewCentral viewCentral) {
        super(frame, b);
        this.viewCentral = null;
        this.waypointManager = null;
        this.viewCentral = viewCentral;
        this.waypointManager = viewCentral.getWaypointManager();
        this.setDefaultCloseOperation(2);
        this.setTitle("Waypoint Following");
        this.initComponents();
        this.getRootPane().setDefaultButton(this.closeButton);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(final WindowEvent windowEvent) {
                WaypointDialog.this.stopNavigation();
            }
        });
        viewCentral.eventController().addPropertyChangeListener(this);
        final List waypointList = viewCentral.getWaypointManager().getWaypointList();
        final DimensionManager dimensionManager = viewCentral.getDimensionManager();
        dimensionManager.setDisplayWindowCenter(dimensionManager.imageToDisplay(waypointList.get(0)));
        viewCentral.viewChanged();
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (propertyName != null && propertyName.equals("WaypointNavigationRunning")) {
            final boolean booleanValue = (boolean)propertyChangeEvent.getNewValue();
            this.stopButton.setEnabled(booleanValue);
            this.startButton.setEnabled(!booleanValue);
        }
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        final int value = slider.getValue();
        if (slider == this.scrollWaitSlider) {
            this.waypointManager.setScrollWait(this.getScrollWait(value));
        }
    }
    
    private int getScrollWait(final int n) {
        return (int)Math.round(100.0 + (n - 0.0f) / -1.0);
    }
    
    private int getScrollWaitSliderValue(final int n) {
        return (int)Math.round(0.0 + (n - 100.0f) * -1.0);
    }
    
    private void initComponents() {
        final WaypointManager waypointManager = this.viewCentral.getWaypointManager();
        (this.instructionsPanel = new JPanel()).setLayout(new BorderLayout());
        this.instructionsPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        this.instructionsLabel1 = new JLabel("Click Start to begin waypoint following.");
        this.instructionsLabel2 = new JLabel("Move the slider to control the scroll speed.");
        this.instructionsPanel.add(this.instructionsLabel1, "North");
        this.instructionsPanel.add(this.instructionsLabel2, "South");
        this.getContentPane().add(this.instructionsPanel, "North");
        (this.sliderPanel = new JPanel()).setLayout(new BoxLayout(this.sliderPanel, 1));
        this.sliderPanel.setBorder(BorderFactory.createTitledBorder("Scroll Speed"));
        this.scrollWaitSlider = new JSlider(0, 0, 100, this.getScrollWaitSliderValue(waypointManager.getScrollWait()));
        final Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
        labelTable.put(new Integer(0), new JLabel("Slower"));
        labelTable.put(new Integer(100), new JLabel("Faster"));
        this.scrollWaitSlider.setLabelTable(labelTable);
        this.scrollWaitSlider.setMajorTickSpacing(10);
        this.scrollWaitSlider.setPaintTicks(true);
        this.scrollWaitSlider.setPaintLabels(true);
        this.scrollWaitSlider.addChangeListener(this);
        this.sliderPanel.add(this.scrollWaitSlider);
        this.getContentPane().add(this.sliderPanel, "Center");
        (this.buttonPanel = new JPanel()).setLayout(new FlowLayout(2));
        this.buttonPanel.setBorder(new EmptyBorder(new Insets(3, 3, 3, 3)));
        (this.startButton = new JButton()).setMnemonic('S');
        this.startButton.setText("Start");
        this.startButton.setToolTipText("Start automated waypoint following");
        this.startButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                WaypointDialog.this.startButtonActionPerformed(actionEvent);
            }
        });
        this.buttonPanel.add(this.startButton);
        (this.stopButton = new JButton()).setMnemonic('T');
        this.stopButton.setText("Stop");
        this.stopButton.setToolTipText("Stop automated waypoint following");
        this.stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                WaypointDialog.this.stopButtonActionPerformed(actionEvent);
            }
        });
        this.stopButton.setEnabled(false);
        this.buttonPanel.add(this.stopButton);
        (this.closeButton = new JButton()).setMnemonic('C');
        this.closeButton.setText("Close");
        this.closeButton.setToolTipText("Close this dialog");
        this.closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                WaypointDialog.this.closeButtonActionPerformed(actionEvent);
            }
        });
        this.buttonPanel.add(this.closeButton);
        this.getContentPane().add(this.buttonPanel, "South");
        this.pack();
    }
    
    private void startButtonActionPerformed(final ActionEvent actionEvent) {
        this.waypointManager.start();
    }
    
    private void stopButtonActionPerformed(final ActionEvent actionEvent) {
        this.stopNavigation();
    }
    
    private void closeButtonActionPerformed(final ActionEvent actionEvent) {
        this.dispose();
    }
    
    private void stopNavigation() {
        this.waypointManager.stop();
    }
}
