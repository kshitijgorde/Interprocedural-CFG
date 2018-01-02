// 
// Decompiled by Procyon v0.5.30
// 

package classes.anaxee.gui;

import java.io.InputStream;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.awt.Graphics;
import java.nio.ByteBuffer;
import org.apache.commons.codec.binary.Base64;
import classes.anaxee.neurotec.SaveImage;
import java.awt.event.ItemEvent;
import com.neurotec.biometrics.NFPosition;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import classes.anaxee.gui.hand.Scenario;
import java.awt.Component;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Image;
import classes.anaxee.gui.hand.HandSegmentSelector;
import classes.anaxee.neurotec.JavaDevice;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class GuiComponents extends JPanel implements ActionListener, ItemListener
{
    boolean is_mode_debug;
    JLabel status_label;
    String num_fingers_scanned;
    int num_fingers;
    JLabel num_fingers_registered_label;
    JButton scan_finger;
    JPanel image_display_panel;
    JPanel components_panel;
    JPanel status_n_button_panel;
    JavaDevice java_device;
    DynamicImagePanel image_panel_dynamic;
    JPanel logo_hands_panel;
    LogoImagePanel logo_image;
    HandSegmentSelector hand_image;
    static Image place_finger_applet;
    static Image scan_completed_applet;
    static Image scanner_connected_applet;
    static Image scanner_not_connected_applet;
    static boolean is_applet;
    int global_flag;
    boolean gui_use_mode_enrollment;
    
    public static void setAppletUsage(final boolean is_applet) {
        GuiComponents.is_applet = is_applet;
    }
    
    public static void setAppletImagesForGUI(final Image place_finger_applet, final Image scan_completed_applet, final Image scanner_connected_applet, final Image scanner_not_connected_applet) {
        GuiComponents.place_finger_applet = place_finger_applet;
        GuiComponents.scan_completed_applet = scan_completed_applet;
        GuiComponents.scanner_connected_applet = scanner_connected_applet;
        GuiComponents.scanner_not_connected_applet = scanner_not_connected_applet;
    }
    
    public void setUseDebugMode(final boolean b) {
        DynamicImagePanel.setUseDebugMode(this.is_mode_debug = b);
        if (this.logo_image != null) {
            this.logo_image.setUseDebugMode(b);
        }
        if (this.hand_image != null) {
            this.hand_image.setUseDebugMode(b);
        }
        if (this.java_device != null) {
            this.java_device.setUseDebugMode(b);
        }
    }
    
    public GuiComponents() {
        this.is_mode_debug = false;
        this.status_label = new JLabel();
        this.num_fingers_scanned = "Number of Fingers Registered : ";
        this.num_fingers = 0;
        this.num_fingers_registered_label = new JLabel(this.num_fingers_scanned + this.num_fingers);
        this.image_display_panel = new JPanel(null);
        this.components_panel = new JPanel(new GridLayout(2, 1));
        this.status_n_button_panel = new JPanel(new GridLayout(3, 1));
        this.java_device = null;
        this.image_panel_dynamic = null;
        this.logo_hands_panel = new JPanel(null);
        this.hand_image = new HandSegmentSelector();
        this.global_flag = 0;
        this.gui_use_mode_enrollment = false;
        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(1, 2));
        this.java_device = new JavaDevice();
        final boolean init = this.java_device.init();
        if (this.is_mode_debug) {
            System.out.println("init status " + init);
        }
        this.scan_finger = new JButton("ScanFinger");
        final JPanel panel = new JPanel(null);
        final Insets insets = panel.getInsets();
        this.scan_finger.getPreferredSize();
        this.scan_finger.setBounds(45 + insets.left, 0 + insets.top, 100, 35);
        panel.add(this.scan_finger);
        this.status_n_button_panel.add(panel);
        this.status_n_button_panel.add(this.status_label);
        this.status_n_button_panel.add(this.num_fingers_registered_label);
        this.hand_image.setScenario(Scenario.ALL_PLAIN_FINGERS);
        final Insets insets2 = this.logo_hands_panel.getInsets();
        this.hand_image.getPreferredSize();
        final int top = insets2.top;
        this.hand_image.setBounds(10 + insets2.left, top, 180, 120);
        if (this.is_mode_debug) {
            System.out.println("top " + top);
        }
        this.logo_hands_panel.add(this.hand_image);
        this.components_panel.add(this.logo_hands_panel);
        this.components_panel.add(this.status_n_button_panel);
        this.scan_finger.addActionListener(this);
        this.add(this.components_panel);
        this.add(this.image_display_panel);
        this.status_label.setHorizontalTextPosition(10);
        this.setBackground(new Color(255, 0, 0));
        if (this.java_device.checkConnectedDevices()) {
            this.status_label.setText("Status : " + this.java_device.getDeviceName(0) + " Scanner is connected");
        }
        else {
            this.status_label.setForeground(Color.red);
            this.status_label.setText("Status : Scanner is not connected");
        }
    }
    
    public boolean isScanned() {
        return this.java_device.isScanned();
    }
    
    public void setUseMode(final String useMode) {
        if (useMode.trim().equalsIgnoreCase("verification")) {
            this.logo_image = new LogoImagePanel();
            this.logo_hands_panel.remove(this.hand_image);
            this.logo_hands_panel.revalidate();
            final Insets insets = this.logo_hands_panel.getInsets();
            this.logo_image.getPreferredSize();
            this.logo_image.setBounds(10 + insets.left, 0 + insets.top, 180, 220);
            this.logo_hands_panel.add(this.logo_image);
            this.logo_hands_panel.revalidate();
            this.num_fingers_registered_label.setVisible(false);
            this.gui_use_mode_enrollment = false;
        }
        else if (useMode.trim().equalsIgnoreCase("enrollment")) {
            this.gui_use_mode_enrollment = true;
        }
        this.java_device.setUseMode(useMode);
    }
    
    public void setTimeOut(final int timeOut) {
        this.java_device.setTimeOut(timeOut);
    }
    
    public void setBiometricMode(final String biometricMode) {
        this.java_device.setBiometricMode(biometricMode);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.java_device.checkConnectedDevices()) {
            this.status_label.setForeground(Color.BLACK);
            this.status_label.setText("Status : Scanner is connected");
            this.update(this.getGraphics());
            if (this.gui_use_mode_enrollment) {
                if (this.hand_image.mouseClickedOnHands()) {
                    if (this.is_mode_debug) {
                        System.out.println("mouse clicked on hand");
                    }
                    this.guiComponentScan(this.hand_image.getCurrentFingerSelectedPosition());
                    this.hand_image.clearSelection();
                }
                else {
                    this.guiComponentScan(NFPosition.UNKNOWN);
                }
            }
            else {
                this.guiComponentScan(NFPosition.UNKNOWN);
            }
        }
        else {
            this.status_label.setForeground(Color.RED);
            this.status_label.setText("Status : Scanner is not connected");
            this.update(this.getGraphics());
        }
    }
    
    public void setStatusIcon() {
    }
    
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
    }
    
    public String getData() {
        final ByteBuffer data = this.java_device.getData();
        if (data == null) {
            return "";
        }
        if (!GuiComponents.is_applet) {
            new SaveImage().saveImageFromByteBuffer(data, "ISOTemplatesBinary.dat");
        }
        data.clear();
        final byte[] binaryData = new byte[data.capacity()];
        data.get(binaryData, 0, binaryData.length);
        return new String(Base64.encodeBase64(binaryData));
    }
    
    @Override
    public void paintComponents(final Graphics graphics) {
        if (this.is_mode_debug) {
            System.out.println("Inside paintComponents() of GuiComonents opaque is " + this.isOpaque());
        }
        this.setBackground(Color.GREEN);
        super.paintComponents(graphics);
    }
    
    @Override
    public void update(final Graphics graphics) {
        this.paintComponents(graphics);
    }
    
    private void guiComponentScan(final NFPosition nfPosition) {
        this.hand_image.setMouseClickedStatus(false);
        this.status_label.setForeground(Color.BLACK);
        this.status_label.setText("Status : Place Your finger on Scanner");
        this.update(this.getGraphics());
        if (this.image_panel_dynamic != null) {
            this.image_display_panel.remove(this.image_panel_dynamic);
            this.image_display_panel.revalidate();
        }
        if (this.is_mode_debug) {
            System.out.println("selected finger is " + nfPosition);
        }
        try {
            this.java_device.scan(nfPosition);
            if (this.isScanned()) {
                ++this.num_fingers;
                this.status_label.setForeground(Color.BLACK);
                this.status_label.setText("Status : Scan complete Remove finger");
                this.num_fingers_registered_label.setText(this.num_fingers_scanned + this.num_fingers);
                this.update(this.getGraphics());
                final ByteBuffer imageDisplayData = this.java_device.getImageDisplayData();
                imageDisplayData.clear();
                final byte[] array = new byte[imageDisplayData.capacity()];
                imageDisplayData.get(array, 0, array.length);
                this.image_panel_dynamic = new DynamicImagePanel(ImageIO.read(new ByteArrayInputStream(array)));
                final Insets insets = this.image_display_panel.getInsets();
                this.image_panel_dynamic.getPreferredSize();
                this.image_panel_dynamic.setBounds(10 + insets.left, 0 + insets.top, 180, 220);
                this.image_display_panel.add(this.image_panel_dynamic);
                this.image_display_panel.revalidate();
                this.image_panel_dynamic.paint(this.image_panel_dynamic.getGraphics());
                this.update(this.getGraphics());
            }
            else if (!this.java_device.getNeurotecLicenseObtained()) {
                this.status_label.setForeground(Color.RED);
                this.status_label.setText("Status :License Not Activated");
                this.update(this.getGraphics());
            }
            else {
                this.status_label.setForeground(Color.RED);
                this.status_label.setText("Status : Timeout please scan again");
                this.update(this.getGraphics());
                if (this.is_mode_debug) {
                    System.out.println("Timeout please capture again");
                }
            }
        }
        catch (NullPointerException ex) {
            if (this.is_mode_debug) {
                System.out.println("inside catch of NPE ofguiComponentScan()");
                ex.printStackTrace();
            }
            this.status_label.setForeground(Color.RED);
            this.status_label.setText("Status : Timeout please scan again");
            this.update(this.getGraphics());
            if (this.is_mode_debug) {
                System.out.println("Timeout please capture again");
            }
        }
        catch (Exception ex2) {
            this.status_label.setForeground(Color.RED);
            this.status_label.setText("Status1 : License Not Activated");
            this.update(this.getGraphics());
            if (this.is_mode_debug) {
                System.out.println("inside catch of Ex of guiComponentScan()");
                ex2.printStackTrace();
            }
        }
    }
    
    public void refreshGUI() {
        this.num_fingers = 0;
        if (this.image_panel_dynamic != null) {
            this.image_display_panel.remove(this.image_panel_dynamic);
            this.image_display_panel.revalidate();
            this.java_device.setScannedStatus(false);
            if (this.java_device.checkConnectedDevices()) {
                this.status_label.setForeground(Color.BLACK);
                this.status_label.setText("Status : Scanner is connected");
                this.num_fingers_registered_label.setText(this.num_fingers_scanned + this.num_fingers);
                this.update(this.getGraphics());
            }
            else {
                this.status_label.setForeground(Color.RED);
                this.status_label.setText("Status :Scanner is connected");
                this.num_fingers_registered_label.setText(this.num_fingers_scanned + this.num_fingers);
                this.update(this.getGraphics());
            }
        }
    }
    
    static {
        GuiComponents.scanner_not_connected_applet = null;
        GuiComponents.is_applet = false;
    }
}
