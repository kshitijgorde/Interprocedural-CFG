// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer;

import java.net.URISyntaxException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.awt.Point;
import java.beans.PropertyVetoException;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.net.URL;
import java.io.IOException;
import java.util.Properties;
import java.io.File;
import com.itt.J2KViewer.util.Helper;
import java.beans.PropertyDescriptor;
import com.itt.J2KViewer.util.J2KViewerBeanInfo;
import javax.swing.JMenuBar;
import java.awt.Component;
import javax.swing.JFrame;
import java.net.Authenticator;
import javax.swing.UIManager;
import com.itt.J2KViewer.util.Log;
import javax.swing.JApplet;

public class EnterpriseViewerApplet extends JApplet
{
    private static final long serialVersionUID = 1L;
    private static Log log;
    private J2KViewerBean viewerBean;
    private String defaultPropFile;
    private String userPropFile;
    private String homeDir;
    static /* synthetic */ Class class$com$itt$J2KViewer$EnterpriseViewerApplet;
    
    public EnterpriseViewerApplet() {
        this.viewerBean = null;
        this.defaultPropFile = "J2KViewer.properties";
        this.userPropFile = "EnterpriseViewer.properties";
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            Authenticator.setDefault(new MyAuthenticator());
        }
        catch (Exception ex) {
            EnterpriseViewerApplet.log.info(ex.getMessage());
        }
    }
    
    public void init() {
        this.viewerBean = new J2KViewerBean(null);
        this.setBeanProps(this.defaultPropFile);
        this.homeDir = System.getProperty("user.home");
        this.viewerBean.setPropFile(this.homeDir + "/EnterpriseViewer/", this.userPropFile);
        if (this.viewerBean.propFileWasSet()) {
            this.setBeanProps(this.userPropFile);
        }
        this.setBeanDefaultProps();
        this.initApplet();
        this.getContentPane().add(this.viewerBean.getMainPanel(), "Center");
        this.setJMenuBar(this.viewerBean.getMenuBar());
        this.customizeMenu();
    }
    
    public void start() {
        EnterpriseViewerApplet.log.debug("J2KViewerApplet start");
        this.showStatus("Opening image");
        this.viewerBean.openImage();
        this.showStatus("");
    }
    
    public void stop() {
        EnterpriseViewerApplet.log.info("J2KViewerApplet stop");
        this.viewerBean.closeImage();
    }
    
    public void destroy() {
        EnterpriseViewerApplet.log.info("J2KViewerApplet destroy");
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
            EnterpriseViewerApplet.log.info("Attempting to load properties from '" + s + "'");
            if (this.defaultPropFile.equals(s)) {
                url = Helper.getURLAsResource(s);
            }
            else if (this.userPropFile.equals(s)) {
                url = new File(this.homeDir + "/EnterpriseViewer/" + s).toURL();
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
            EnterpriseViewerApplet.log.error("Error loading properties file", ex);
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
                    EnterpriseViewerApplet.log.debuglowest("Ran Garbage Collection");
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
    
    public void customizeMenu() {
        final JMenu menu = (JMenu)this.getJMenuBar().getComponent(0);
        menu.remove(menu.getItemCount() - 1);
        final JMenuItem menuItem = new JMenuItem("Open...");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                EnterpriseViewerApplet.this.doOpenURLDialog();
            }
        });
        menu.insert(menuItem, 0);
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
    
    public void openImage(final String imageURL) throws PropertyVetoException {
        if (imageURL != null) {
            this.setImageURL(imageURL);
        }
        this.viewerBean.openImage();
    }
    
    public void closeImage() {
        this.viewerBean.closeImage();
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
            EnterpriseViewerApplet.log.error("The image path is invalid", ex);
        }
        catch (URISyntaxException ex2) {
            EnterpriseViewerApplet.log.error("The image URL is invalid", ex2);
        }
        catch (PropertyVetoException ex3) {
            EnterpriseViewerApplet.log.error("Error setting bean properties", ex3);
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
        EnterpriseViewerApplet.log = new Log((EnterpriseViewerApplet.class$com$itt$J2KViewer$EnterpriseViewerApplet == null) ? (EnterpriseViewerApplet.class$com$itt$J2KViewer$EnterpriseViewerApplet = class$("com.itt.J2KViewer.EnterpriseViewerApplet")) : EnterpriseViewerApplet.class$com$itt$J2KViewer$EnterpriseViewerApplet);
    }
    
    protected class MyAuthenticator extends Authenticator
    {
    }
}
