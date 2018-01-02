// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer;

import java.util.ArrayList;
import java.io.RandomAccessFile;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Point;
import javax.swing.JOptionPane;
import java.awt.Rectangle;
import java.net.URISyntaxException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Properties;
import com.itt.J2KViewer.util.Helper;
import java.beans.PropertyDescriptor;
import com.itt.J2KViewer.util.J2KViewerBeanInfo;
import java.util.Enumeration;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.zip.ZipEntry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.io.File;
import java.awt.Component;
import java.beans.PropertyVetoException;
import javax.swing.JFrame;
import java.net.Authenticator;
import javax.swing.UIManager;
import java.awt.TextArea;
import java.awt.TextField;
import com.itt.J2KViewer.util.Log;
import java.awt.event.KeyListener;
import javax.swing.JApplet;

public class J2KViewerAppletTest extends JApplet implements KeyListener
{
    private static final long serialVersionUID = 1L;
    private static Log log;
    private J2KViewerBean viewerBean;
    private String defaultPropFile;
    private String userPropFile;
    private String homeDir;
    private int commandCount;
    private TextField typingArea;
    private TextArea displayArea;
    private String use;
    private int num;
    static /* synthetic */ Class class$com$itt$J2KViewer$J2KViewerAppletTest;
    
    public J2KViewerAppletTest() {
        this.viewerBean = null;
        this.defaultPropFile = "J2KViewer.properties";
        this.userPropFile = "ExpressViewer.properties";
        this.commandCount = 0;
        this.use = null;
        this.num = 0;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            Authenticator.setDefault(new MyAuthenticator());
        }
        catch (Exception ex) {
            J2KViewerAppletTest.log.info(ex.getMessage());
        }
    }
    
    public void init() {
        this.downloadDLLs(System.getProperty("user.home") + "/" + "EnterpriseViewer");
        this.viewerBean = new J2KViewerBean(null);
        this.setBeanDefaultProps();
        this.homeDir = System.getProperty("user.home");
        this.viewerBean.setPropFile(this.homeDir + "/ExpressViewer/", this.userPropFile);
        if (this.viewerBean.propFileWasSet()) {
            this.setBeanProps(this.userPropFile);
        }
        this.initApplet();
        try {
            this.viewerBean.setShowToolBar(false);
            this.viewerBean.setShowSecurityBanner(false);
            this.viewerBean.setShowSplitPane(false);
            System.out.println("isShowSplitPane: " + this.viewerBean.isShowSplitPane());
            System.out.println("isShowSecurityBanner : " + this.viewerBean.isShowSecurityBanner());
        }
        catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
        this.getContentPane().add(this.viewerBean.getMainPanel(), "Center");
        (this.typingArea = new TextField(20)).addKeyListener(this);
        this.getContentPane().add("South", this.typingArea);
        (this.displayArea = new TextArea(17, 20)).setEditable(false);
        J2KViewerAppletTest.log.debug("J2KViewerApplet init");
    }
    
    private void downloadDLLs(final String s) {
        final File file = new File(s);
        if (!file.exists() && !file.mkdir()) {
            J2KViewerAppletTest.log.error("Unable to create " + file.getAbsolutePath());
            return;
        }
        String s2;
        if (System.getProperty("os.name").toLowerCase().indexOf("win") != -1) {
            s2 = "dlls.jar";
        }
        else {
            s2 = "sos.jar";
        }
        this.getDLLJar(s2, file);
    }
    
    private void getDLLJar(final String s, final File file) {
        String string = null;
        boolean b = true;
        final File file2 = new File(file, s);
        try {
            this.showStatus("Checking for updated libraries");
            string = this.getCodeBase() + s;
            final URLConnection openConnection = new URL(string).openConnection();
            InputStream inputStream = openConnection.getInputStream();
            final long lastModified = openConnection.getLastModified();
            if (file2.exists() && file2.lastModified() >= lastModified) {
                b = false;
            }
            if (b) {
                this.showStatus("Downloading native library jar file");
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                int read;
                while ((read = inputStream.read()) != -1) {
                    bufferedOutputStream.write(read);
                }
                bufferedOutputStream.close();
                fileOutputStream.close();
                inputStream.close();
                file2.setLastModified(lastModified);
                JarFile jarFile = null;
                try {
                    jarFile = new JarFile(file2);
                    final Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        final JarEntry jarEntry = entries.nextElement();
                        final String name = jarEntry.getName();
                        this.showStatus("Unpacking " + name);
                        if (name.endsWith(".dll") || name.endsWith(".so")) {
                            fileOutputStream = new FileOutputStream(new File(file, name));
                            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                            inputStream = jarFile.getInputStream(jarEntry);
                            int read2;
                            while ((read2 = inputStream.read()) != -1) {
                                bufferedOutputStream.write(read2);
                            }
                            bufferedOutputStream.close();
                            fileOutputStream.close();
                        }
                    }
                    jarFile.close();
                }
                catch (Exception ex) {
                    bufferedOutputStream.close();
                    fileOutputStream.close();
                    inputStream.close();
                    if (jarFile != null) {
                        jarFile.close();
                    }
                    J2KViewerAppletTest.log.warn("Unable to unpack native library jar file :'" + string + "'. ", ex);
                    if (file2 != null) {
                        J2KViewerAppletTest.log.warn("Deleting native library jar file :'" + file2.getAbsolutePath() + "'. ");
                        if (!file2.delete()) {
                            J2KViewerAppletTest.log.error("Unable to delete obsolete native library jar file :'" + file2.getAbsolutePath() + "'. ");
                        }
                    }
                }
            }
        }
        catch (MalformedURLException ex2) {
            J2KViewerAppletTest.log.error("Invalid URL for native library :'" + string + "'. ", ex2);
        }
        catch (IOException ex3) {
            J2KViewerAppletTest.log.error("Unable to download native library jar file :'" + string + "'. ", ex3);
        }
    }
    
    public void start() {
        this.typingArea.setText("Enter Commands Here");
        J2KViewerAppletTest.log.debug("J2KViewerApplet start");
        this.showStatus("Opening image");
        this.viewerBean.openImage();
        this.showStatus("");
    }
    
    public void stop() {
        J2KViewerAppletTest.log.info("J2KViewerApplet stop");
        this.viewerBean.closeImage();
    }
    
    public void destroy() {
        J2KViewerAppletTest.log.info("J2KViewerApplet destroy");
        this.viewerBean = null;
        System.runFinalization();
        System.gc();
    }
    
    private void setBeanDefaultProps() {
        final PropertyDescriptor[] propertyDescriptors = new J2KViewerBeanInfo().getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; ++i) {
            final PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
            this.viewerBean.initProperty(propertyDescriptor, this.getParameter(propertyDescriptor.getName()));
        }
    }
    
    public void setBeanProps(final String s) {
        URL url = null;
        try {
            J2KViewerAppletTest.log.info("Attempting to load properties from '" + s + "'");
            if (this.defaultPropFile.equals(s)) {
                url = Helper.getURLAsResource(s);
            }
            else if (this.userPropFile.equals(s)) {
                url = new File(this.homeDir + "/ExpressViewer/" + s).toURL();
            }
            final Properties properties = new Properties();
            if (url != null) {
                properties.load(url.openStream());
                if (properties.size() > 0) {
                    final PropertyDescriptor[] propertyDescriptors = new J2KViewerBeanInfo().getPropertyDescriptors();
                    for (int i = 0; i < propertyDescriptors.length; ++i) {
                        final PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
                        propertyDescriptor.getName();
                        this.viewerBean.initProperty(propertyDescriptor, properties.getProperty(propertyDescriptor.getName()));
                    }
                }
            }
        }
        catch (IOException ex) {
            J2KViewerAppletTest.log.error("Error loading properties file", ex);
        }
    }
    
    private void initApplet() {
        this.startGCThread();
    }
    
    private void startGCThread() {
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    System.runFinalization();
                    System.gc();
                    J2KViewerAppletTest.log.debuglowest("Ran Garbage Collection");
                    for (int i = 0; i < 100; ++i) {
                        try {
                            Thread.sleep(200L);
                        }
                        catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        thread.setPriority(1);
        thread.start();
    }
    
    public void openImage(final String imageURL) throws PropertyVetoException {
        if (imageURL != null) {
            this.setImageURL(imageURL);
        }
        this.viewerBean.openImage();
    }
    
    public void closeImage() {
        this.viewerBean.closeImage();
    }
    
    public void applyAutoDRA() {
        this.viewerBean.applyAutoDRA();
    }
    
    public void toggleZoomMode(final String s) throws PropertyVetoException {
        if (Boolean.valueOf(s)) {
            this.viewerBean.setTransformationMode("Zoom In");
        }
        else {
            this.viewerBean.setTransformationMode("Zoom Out");
        }
    }
    
    public void togglePanMode() throws PropertyVetoException {
        this.viewerBean.setTransformationMode("Pan");
    }
    
    public void showCodestreamProps() throws Exception {
        try {
            this.viewerBean.showCodestreamPropsDialog();
        }
        catch (Exception ex) {
            J2KViewerAppletTest.log.error(ex);
            throw ex;
        }
    }
    
    public void showXmlProps() throws Exception {
        try {
            this.viewerBean.showXmlPropsDialog();
        }
        catch (Exception ex) {
            J2KViewerAppletTest.log.error(ex);
            throw ex;
        }
    }
    
    public void showHelp() throws Exception {
        try {
            this.viewerBean.showHelpDialog();
        }
        catch (Exception ex) {
            J2KViewerAppletTest.log.error(ex);
            throw ex;
        }
    }
    
    public void zoom(final String s) {
        this.viewerBean.zoom(Integer.parseInt(s));
    }
    
    public String getZoomLevel() {
        return String.valueOf(this.viewerBean.getDiscardedZoomLevels());
    }
    
    public String getMaxZoomLevel() {
        return String.valueOf(this.viewerBean.getMaxDiscardedZoomLevels());
    }
    
    public void panUp() throws PropertyVetoException {
        this.viewerBean.pan(0.0f, -5.0f);
    }
    
    public void panDown() throws PropertyVetoException {
        this.viewerBean.pan(0.0f, 5.0f);
    }
    
    public void panLeft() throws PropertyVetoException {
        this.viewerBean.pan(-5.0f, 0.0f);
    }
    
    public void panRight() throws PropertyVetoException {
        this.viewerBean.pan(5.0f, 0.0f);
    }
    
    public void panPageUp() throws PropertyVetoException {
        this.viewerBean.pan(0.0f, -90.0f);
    }
    
    public void panPageDown() throws PropertyVetoException {
        this.viewerBean.pan(0.0f, 90.0f);
    }
    
    public void panCtrlPageUp() throws PropertyVetoException {
        this.viewerBean.pan(-90.0f, 0.0f);
    }
    
    public void panCtrlPageDown() throws PropertyVetoException {
        this.viewerBean.pan(90.0f, 0.0f);
    }
    
    public void zoomIn() {
        this.viewerBean.zoom(-1);
    }
    
    public void zoomOut() {
        this.viewerBean.zoom(1);
    }
    
    public void setAllowZoom(final String s) throws PropertyVetoException {
        this.viewerBean.setAllowZoom(new Boolean(s));
    }
    
    public String isAllowZoom() {
        return String.valueOf(this.viewerBean.isAllowZoom());
    }
    
    public void setAllowPan(final String s) throws PropertyVetoException {
        this.viewerBean.setAllowPan(new Boolean(s));
    }
    
    public String isAllowPan() {
        return String.valueOf(this.viewerBean.isAllowPan());
    }
    
    public void setAllowChangeQuality(final String s) throws PropertyVetoException {
        this.viewerBean.setAllowChangeQuality(new Boolean(s));
    }
    
    public String isAllowChangeQuality() {
        return String.valueOf(this.viewerBean.isAllowChangeQuality());
    }
    
    public void setImageURL(String string) throws PropertyVetoException {
        try {
            if (!Helper.isJPIPProtocol(string)) {
                string = "file:///" + URLEncoder.encode(string, "UTF-8");
            }
            final URI uri = new URI(string);
            this.viewerBean.setImageURL(string);
        }
        catch (UnsupportedEncodingException ex) {
            J2KViewerAppletTest.log.error("The image path is invalid", ex);
        }
        catch (URISyntaxException ex2) {
            J2KViewerAppletTest.log.error("The image URL is invalid", ex2);
        }
        catch (PropertyVetoException ex3) {
            J2KViewerAppletTest.log.error("Error setting bean properties", ex3);
        }
    }
    
    public void increaseQuality() throws PropertyVetoException {
        this.viewerBean.setQualityLayers(this.viewerBean.getQualityLayers() + 1);
    }
    
    public void decreaseQuality() throws PropertyVetoException {
        this.viewerBean.setQualityLayers(this.viewerBean.getQualityLayers() - 1);
    }
    
    public void setQualityLayers(final String s) throws PropertyVetoException {
        try {
            this.viewerBean.setQualityLayers(Integer.parseInt(s));
        }
        catch (NumberFormatException ex) {
            J2KViewerAppletTest.log.error("Invalid QualityLayers value supplied: " + s, ex);
        }
    }
    
    public String getMaxQualityLayers() {
        return String.valueOf(this.viewerBean.getMaxQualityLayers());
    }
    
    public Rectangle getCurrentDimensions() {
        return this.viewerBean.getCurrentDimensions();
    }
    
    public Rectangle getTotalDimensions() {
        return this.viewerBean.getTotalDimensions();
    }
    
    public String getTotalBytes() {
        return String.valueOf(this.viewerBean.getTotalBytes());
    }
    
    public String getBytesTransferred() {
        return String.valueOf(this.viewerBean.getBytesTransferred());
    }
    
    public void setShowScrollBars(final String s) throws PropertyVetoException {
        this.viewerBean.setShowScrollbars(Boolean.valueOf(s));
    }
    
    public String isShowScrollbars() {
        return String.valueOf(this.viewerBean.isShowScrollbars());
    }
    
    public String isOpen() {
        return String.valueOf(this.viewerBean.isOpen());
    }
    
    public void doOpenURLDialog() {
        final OpenURLDialog openURLDialog = new OpenURLDialog((JFrame)null, true);
        final Point locationOnScreen = this.getLocationOnScreen();
        openURLDialog.setLocation(locationOnScreen.x + 20, locationOnScreen.y + 20);
        openURLDialog.setVisible(true);
        if (!openURLDialog.isCancelled() && openURLDialog.getSelectedURL() != null) {
            if (!Helper.isJPIPProtocol(openURLDialog.getSelectedURL())) {
                JOptionPane.showMessageDialog(this, "The URL syntax is invalid. Try again.", "URL Syntax Error", 0);
                return;
            }
            try {
                this.openImage(openURLDialog.getSelectedURL());
            }
            catch (PropertyVetoException ex) {
                System.out.println("Could not open image");
                ex.printStackTrace();
            }
        }
    }
    
    public void customizeMenu() {
        final JMenu menu = (JMenu)this.getJMenuBar().getComponent(0);
        final JMenuItem menuItem = new JMenuItem("Open...");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                J2KViewerAppletTest.this.doOpenURLDialog();
            }
        });
        menu.insert(menuItem, 0);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        this.displayInfo(keyEvent);
        this.typingArea.setText("");
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    private String getRequestForKey(final String s, final int n) {
        int n2 = -1;
        if (n >= 0) {
            n2 = n;
        }
        if (s != null && s.length() > 0 && n2 < 0) {
            if (s.equalsIgnoreCase("2")) {
                return "PAN_DOWN";
            }
            if (s.equalsIgnoreCase("8")) {
                return "PAN_UP";
            }
            if (s.equalsIgnoreCase("4")) {
                return "PAN_LEFT";
            }
            if (s.equalsIgnoreCase("6")) {
                return "PAN_RIGHT";
            }
            if (s.equalsIgnoreCase("d")) {
                return "DISPLAY_COMMANDS";
            }
            if (s.equalsIgnoreCase("r")) {
                return "RED_BAND";
            }
            if (s.equalsIgnoreCase("g")) {
                return "GREEN_BAND";
            }
            if (s.equalsIgnoreCase("b")) {
                return "BLUE_BAND";
            }
            if (s.equalsIgnoreCase("j")) {
                return "SAVE_JPEG";
            }
            if (s.equalsIgnoreCase("k")) {
                return "SAVE_GEOTIFF";
            }
            if (s.equalsIgnoreCase("l")) {
                return "SHOW_BANDS";
            }
            if (s.equalsIgnoreCase("m")) {
                return "MODE_ROTATE";
            }
            if (s.equalsIgnoreCase("n")) {
                return "MODE_PAN";
            }
            if (s.equalsIgnoreCase("o")) {
                return "APPLY_DRA";
            }
            if (s.equalsIgnoreCase("p")) {
                return "MODE_FLY";
            }
            if (s.equalsIgnoreCase("t")) {
                return "SHOW_NORTH";
            }
            if (s.equalsIgnoreCase("u")) {
                return "HIDE_NORTH";
            }
            if (s.equalsIgnoreCase("v")) {
                return "ROTATE_90";
            }
            if (s.equals("c")) {
                return "COPY";
            }
            if (s.equals("x")) {
                return "CANCEL";
            }
            if (s.equals("q")) {
                return "GET_DIMS";
            }
            return "NOT A VALID COMMAND.  Press 'c' to see a list of commands.";
        }
        else {
            if (n2 < 0) {
                return s;
            }
            if (n2 == 40) {
                return "PAN_DOWN";
            }
            if (n2 == 38) {
                return "PAN_UP";
            }
            if (n2 == 37) {
                return "PAN_LEFT";
            }
            if (n2 == 39) {
                return "PAN_RIGHT";
            }
            if (n2 == 114) {
                return "CLOSE";
            }
            if (n2 == 115) {
                return "ZOOM_IN";
            }
            if (n2 == 116) {
                return "ZOOM_OUT";
            }
            if (n2 == 117) {
                return "INC_QUALITY";
            }
            if (n2 == 118) {
                return "DEC_QUALITY";
            }
            if (n2 == 119) {
                return "PROPS";
            }
            if (n2 == 120) {
                return "AUTO_DRA";
            }
            if (n2 == 121) {
                return "XML_PROPS";
            }
            if (n2 == 123) {
                return "HELP";
            }
            return "NOT A VALID COMMAND.  Press 'c' to see a list of commands.";
        }
    }
    
    private void displayInfo(final KeyEvent keyEvent) {
        String s = "NOT A VALID COMMAND";
        final char keyChar = keyEvent.getKeyChar();
        int keyCode = keyEvent.getKeyCode();
        if (!Character.isISOControl(keyChar)) {
            if (keyChar == '\uffff') {
                s = this.getRequestForKey(String.valueOf(keyChar), keyCode);
            }
            else {
                keyCode = -1;
                s = this.getRequestForKey(String.valueOf(keyChar), keyCode);
            }
        }
        final String string = keyCode + " (" + KeyEvent.getKeyText(keyCode) + ")";
        ++this.commandCount;
        if (keyCode == -1) {
            this.displayArea.append(this.commandCount + ". " + keyChar + " = " + s + "\n");
        }
        else {
            this.displayArea.append(this.commandCount + ". " + string + " = " + s + "\n");
        }
        if (s != null && s.length() > 0) {
            this.handleRequest(s);
        }
    }
    
    private void handleRequest(final String s) {
        try {
            if (s.equalsIgnoreCase("DISPLAY_COMMANDS")) {
                this.displayArea.append(this.use);
            }
            if (s.equalsIgnoreCase("PAN_DOWN")) {
                this.viewerBean.pan(0.0f, 5.0f);
            }
            if (s.equalsIgnoreCase("PAN_UP")) {
                this.viewerBean.pan(0.0f, -5.0f);
            }
            if (s.equalsIgnoreCase("PAN_LEFT")) {
                this.viewerBean.pan(-5.0f, 0.0f);
            }
            if (s.equalsIgnoreCase("PAN_RIGHT")) {
                this.viewerBean.pan(5.0f, 0.0f);
            }
            if (s.equalsIgnoreCase("CLOSE")) {
                this.viewerBean.closeImage();
            }
            if (s.equalsIgnoreCase("ZOOM_IN")) {
                this.viewerBean.zoom(-1);
            }
            if (s.equalsIgnoreCase("ZOOM_OUT")) {
                this.viewerBean.zoom(1);
            }
            if (s.equalsIgnoreCase("INC_QUALITY")) {
                this.viewerBean.setQualityLayers(this.viewerBean.getQualityLayers() + 1);
            }
            if (s.equalsIgnoreCase("DEC_QUALITY")) {
                this.viewerBean.setQualityLayers(this.viewerBean.getQualityLayers() - 1);
            }
            if (s.equalsIgnoreCase("PROPS")) {
                try {
                    this.viewerBean.showCodestreamPropsDialog();
                }
                catch (Exception ex) {
                    J2KViewerAppletTest.log.error("FAILED to open codestream property dialog.", ex);
                }
            }
            if (s.equalsIgnoreCase("AUTO_DRA")) {
                this.viewerBean.applyAutoDRA();
            }
            if (s.equalsIgnoreCase("XML_PROPS")) {
                try {
                    this.viewerBean.showXmlPropsDialog();
                }
                catch (Exception ex2) {
                    J2KViewerAppletTest.log.error("FAILED to open XML Properties Dialog.", ex2);
                }
            }
            if (s.equalsIgnoreCase("HELP")) {
                try {
                    this.viewerBean.showHelpDialog();
                }
                catch (Exception ex3) {
                    J2KViewerAppletTest.log.error("FAILED to open help dialog.", ex3);
                }
            }
            if (s.equalsIgnoreCase("RED_BAND")) {
                this.incrementRGBMap(0);
            }
            if (s.equalsIgnoreCase("GREEN_BAND")) {
                this.incrementRGBMap(1);
            }
            if (s.equalsIgnoreCase("BLUE_BAND")) {
                this.incrementRGBMap(2);
            }
            if (s.equalsIgnoreCase("SAVE_JPEG")) {
                try {
                    this.viewerBean.saveViewToFile();
                }
                catch (Exception ex4) {
                    J2KViewerAppletTest.log.error("Failed to save JPEG!", ex4);
                }
            }
            if (s.equalsIgnoreCase("SAVE_GEOTIFF")) {
                try {
                    if (this.viewerBean.isGeoTIFFAvailable()) {
                        this.viewerBean.saveViewToGeoTIFFFile();
                    }
                    else {
                        J2KViewerAppletTest.log.info("Geolocation info not available!");
                    }
                }
                catch (Exception ex5) {
                    J2KViewerAppletTest.log.error("Failed to save GEOTIFF!", ex5);
                }
            }
            if (s.equalsIgnoreCase("SHOW_BANDS")) {
                try {
                    this.viewerBean.showBandsDialog();
                }
                catch (Exception ex8) {
                    J2KViewerAppletTest.log.error("Failed to show Bands dialog");
                }
            }
            if (s.equalsIgnoreCase("MODE_ROTATE")) {
                this.viewerBean.setTransformationMode("Manual Rotation");
            }
            if (s.equalsIgnoreCase("MODE_PAN")) {
                this.viewerBean.setTransformationMode("Pan");
            }
            if (s.equalsIgnoreCase("MODE_FLY")) {
                this.viewerBean.setTransformationMode("Fly");
            }
            if (s.equalsIgnoreCase("APPLY_DRA")) {
                this.viewerBean.applyDRA(2);
            }
            if (s.equalsIgnoreCase("SHOW_NORTH")) {
                this.viewerBean.setShowNorthArrow(true);
            }
            if (s.equalsIgnoreCase("HIDE_NORTH")) {
                this.viewerBean.setShowNorthArrow(false);
            }
            if (s.equalsIgnoreCase("ROTATE_90")) {
                this.viewerBean.setRotationAngle(1.5707963267948966);
            }
            if (s.equalsIgnoreCase("COPY")) {
                System.out.println("Copying codestream!");
                final File file = new File(this.homeDir + "\\codestream" + this.num + ".j2c");
                if (file.exists()) {
                    file.delete();
                }
                try {
                    this.viewerBean.copyCodestream(new RandomAccessFile(file, "rw"), new String(), null);
                }
                catch (IOException ex6) {
                    System.out.println("Could not save codestream");
                    System.out.println(ex6.toString());
                }
                ++this.num;
            }
            if (s.equalsIgnoreCase("CANCEL")) {
                this.viewerBean.cancelStream();
            }
            if (s.equalsIgnoreCase("GET_DIMS")) {
                System.out.println("Tiles X: " + this.viewerBean.getNumTilesInXDimension());
                System.out.println("Tiles Y: " + this.viewerBean.getNumTilesInYDimension());
            }
        }
        catch (PropertyVetoException ex7) {
            ex7.printStackTrace();
        }
    }
    
    private void incrementRGBMap(final int n) throws PropertyVetoException {
        final int numberOfComponents = this.viewerBean.getNumberOfComponents();
        final int[] rgbMap = this.viewerBean.getRGBMap();
        if (n >= 0 && n <= rgbMap.length && numberOfComponents > 1) {
            switch (n) {
                case 0: {
                    rgbMap[0] = 0;
                    rgbMap[1] = 1;
                    rgbMap[2] = 2;
                    break;
                }
                case 1: {
                    rgbMap[0] = 2;
                    rgbMap[1] = 0;
                    rgbMap[2] = 1;
                    break;
                }
                case 2: {
                    rgbMap[0] = 1;
                    rgbMap[rgbMap[1] = 2] = 0;
                    break;
                }
            }
            System.out.print("RGB map = ");
            for (int i = 0; i < rgbMap.length; ++i) {
                System.out.print(rgbMap[i]);
                System.out.print(" ");
            }
            System.out.println();
            this.viewerBean.setRGBMap(rgbMap);
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        J2KViewerAppletTest.log = new Log((J2KViewerAppletTest.class$com$itt$J2KViewer$J2KViewerAppletTest == null) ? (J2KViewerAppletTest.class$com$itt$J2KViewer$J2KViewerAppletTest = class$("com.itt.J2KViewer.J2KViewerAppletTest")) : J2KViewerAppletTest.class$com$itt$J2KViewer$J2KViewerAppletTest);
    }
    
    protected class MyAuthenticator extends Authenticator
    {
    }
}
