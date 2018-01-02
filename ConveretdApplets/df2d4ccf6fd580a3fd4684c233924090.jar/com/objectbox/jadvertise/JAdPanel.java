// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.jadvertise;

import java.util.Hashtable;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Frame;
import java.util.Enumeration;
import java.io.InputStream;
import com.objectbox.runner.applet.OBContext;
import java.applet.AppletStub;
import java.awt.Component;
import com.objectbox.runner.applet.OBStub;
import java.applet.Applet;
import com.objectbox.runner.model.SecurityManagerIF;
import com.objectbox.runner.model.JBSecurityModel;
import com.objectbox.runner.gui.JBManagerPanel;
import com.objectbox.runner.gui.AppRegistry;
import com.objectbox.runner.gui.JBee;
import com.objectbox.loader.JBURLClassloader;
import java.util.Properties;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.awt.Font;
import java.beans.PropertyChangeSupport;
import java.net.URL;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.Container;

public class JAdPanel extends Container implements MouseListener
{
    private Dimension thesize;
    private Dimension adsize;
    private String codebase;
    private String classname;
    private String jararchives;
    private boolean useimagecache;
    private boolean usebytecodecache;
    private URL adurl;
    private boolean useindirectadurl;
    private String cachedir;
    protected transient PropertyChangeSupport propertyChange;
    private String[] fieldParameters;
    private boolean appletloaded;
    private String adspotid;
    private Font f;
    private boolean isAppletLoaded;
    
    public JAdPanel() {
        this.thesize = new Dimension(10, 10);
        this.adsize = new Dimension(10, 10);
        this.codebase = "";
        this.classname = "";
        this.jararchives = "";
        this.useimagecache = false;
        this.usebytecodecache = true;
        this.useindirectadurl = true;
        this.cachedir = "";
        this.fieldParameters = new String[1];
        this.appletloaded = false;
        this.f = new Font("SansSerif", 1, 16);
        this.isAppletLoaded = false;
        this.initialize();
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.getPropertyChange().addPropertyChangeListener(propertyChangeListener);
    }
    
    private void connEtoC1(final MouseEvent mouseEvent) {
        try {
            this.jadPanel_MouseClicked(mouseEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    public void firePropertyChange(final String s, final Object o, final Object o2) {
        this.getPropertyChange().firePropertyChange(s, o, o2);
    }
    
    public Dimension getAdsize() {
        return this.adsize;
    }
    
    public String getAdspotid() {
        return this.adspotid;
    }
    
    public URL getAdurl() {
        return this.adurl;
    }
    
    public String getCachedir() {
        return this.cachedir;
    }
    
    public String getClassname() {
        return this.classname;
    }
    
    public String getCodebase() {
        return this.codebase;
    }
    
    public String getJararchives() {
        return this.jararchives;
    }
    
    public Dimension getMaximumSize() {
        return this.adsize;
    }
    
    public Dimension getMinimumSize() {
        return this.adsize;
    }
    
    public String[] getParameters() {
        return this.fieldParameters;
    }
    
    public String getParameters(final int n) {
        return this.getParameters()[n];
    }
    
    public Dimension getPreferredSize() {
        return this.adsize;
    }
    
    protected PropertyChangeSupport getPropertyChange() {
        if (this.propertyChange == null) {
            this.propertyChange = new PropertyChangeSupport(this);
        }
        return this.propertyChange;
    }
    
    public boolean getUsebytecodecache() {
        return this.usebytecodecache;
    }
    
    public boolean getUseimagecache() {
        return this.useimagecache;
    }
    
    public boolean getUseindirectadurl() {
        return this.useindirectadurl;
    }
    
    private void handleException(final Throwable t) {
    }
    
    private void initConnections() {
        this.addMouseListener(this);
    }
    
    private void initialize() {
        this.setName("com.objectbox.jadvertise.JAdPanel");
        this.setLayout(new BorderLayout());
        this.setSize(160, 120);
        this.initConnections();
    }
    
    public boolean isAppletLoaded() {
        return this.isAppletLoaded;
    }
    
    public void jadPanel_MouseClicked(final MouseEvent mouseEvent) {
        if (!this.appletloaded) {
            try {
                Launcher.openURL("http://www.objectbox.com/jad");
            }
            catch (Exception ex) {}
        }
    }
    
    public void loadApplet() {
        ((Thread)new JAdPanel$1.trun(this)).start();
    }
    
    private void loadApplet_impl() {
        try {
            if (this.adurl != null) {
                final Properties properties = new Properties();
                final InputStream inputStream = this.adurl.openConnection().getInputStream();
                properties.load(inputStream);
                inputStream.close();
                final String property = properties.getProperty("forward");
                if (property != null) {
                    final InputStream inputStream2 = new URL(property).openConnection().getInputStream();
                    properties.clear();
                    properties.load(inputStream2);
                    inputStream2.close();
                }
                final String[] parameters = new String[properties.size()];
                int n = 0;
                final Enumeration<String> keys = ((Hashtable<String, V>)properties).keys();
                while (keys.hasMoreElements()) {
                    final String s = keys.nextElement();
                    parameters[n++] = String.valueOf(s) + "=" + properties.getProperty(s);
                }
                this.setParameters(parameters);
                this.setClassname(properties.getProperty("classname"));
                this.setCodebase(properties.getProperty("codebase"));
                this.setJararchives(properties.getProperty("archives"));
            }
            this.isAppletLoaded = true;
            final JBURLClassloader jburlClassloader = new JBURLClassloader(this.cachedir);
            jburlClassloader.setCacheOn(this.usebytecodecache);
            final String string = "-" + this.codebase.hashCode() + this.classname.hashCode();
            jburlClassloader.setSecurityID(string);
            jburlClassloader.setCodebase(this.codebase);
            jburlClassloader.setServer(new URL(this.codebase).getHost());
            jburlClassloader.setRootclass(this.classname);
            jburlClassloader.setJarfile(this.jararchives);
            jburlClassloader.readCacheFromFile();
            jburlClassloader.setOwner(this);
            final JBee bee = (JBee)AppRegistry.getInstance().lookup("JBee");
            final JBManagerPanel jbManagerPanel = (JBManagerPanel)AppRegistry.getInstance().lookup("Manager");
            final JBSecurityModel securityModel = JBSecurityModel.getSecurityModel(2);
            System.out.println("Sec man: " + securityModel);
            bee.setSecurity(string, securityModel, jbManagerPanel);
            final Applet applet = jburlClassloader.resolveClass(this.classname).newInstance();
            final OBStub stub = new OBStub(applet, this, new URL(this.codebase), new URL(this.codebase));
            applet.setStub(stub);
            ((OBContext)stub.getAppletContext()).setUseImageCache(this.useimagecache);
            if (this.fieldParameters != null) {
                for (int i = 0; i < this.fieldParameters.length; ++i) {
                    final int index = this.fieldParameters[i].indexOf("=");
                    stub.addParameter(this.fieldParameters[i].substring(0, index), this.fieldParameters[i].substring(index + 1));
                }
            }
            if (stub.getParameter("height") != null) {
                this.adsize.height = Integer.parseInt(stub.getParameter("height"));
            }
            applet.setSize(this.adsize);
            this.add(applet, "Center");
            applet.init();
            applet.start();
            this.appletloaded = true;
            Container container;
            for (container = this.getParent(); container.getParent() != null; container = container.getParent()) {}
            container.validate();
            jburlClassloader.saveme();
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public static void main(final String[] array) {
        try {
            Frame frame;
            try {
                frame = (Frame)Class.forName("com.ibm.uvm.abt.edit.TestFrame").newInstance();
            }
            catch (Throwable t2) {
                frame = new Frame();
            }
            final JAdPanel adPanel = new JAdPanel();
            frame.add("Center", adPanel);
            frame.setSize(adPanel.getSize());
            frame.setVisible(true);
        }
        catch (Throwable t) {
            System.err.println("Exception occurred in main() of java.awt.Container");
            t.printStackTrace(System.out);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == this) {
            this.connEtoC1(mouseEvent);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (!this.appletloaded) {
            graphics.setColor(Color.orange);
            graphics.fillRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
            graphics.setColor(Color.black);
            graphics.setFont(this.f);
            final FontMetrics fontMetrics = graphics.getFontMetrics(this.f);
            graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
            graphics.drawString("JAdvertise (c)", this.getSize().width / 2 - fontMetrics.stringWidth("JAdvertise (c)") / 2, this.getSize().height / 2 - fontMetrics.getHeight() / 2);
            graphics.drawString("objectBOX 1999", this.getSize().width / 2 - fontMetrics.stringWidth("objectBOX 1999") / 2, this.getSize().height / 2 + fontMetrics.getHeight() / 2);
        }
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.getPropertyChange().removePropertyChangeListener(propertyChangeListener);
    }
    
    public void setAdsize(final Dimension adsize) {
        this.adsize = adsize;
        this.validate();
    }
    
    public void setAdspotid(final String adspotid) {
        this.adspotid = adspotid;
    }
    
    public void setAdurl(final URL adurl) {
        this.adurl = adurl;
    }
    
    public void setCachedir(final String cachedir) {
        this.cachedir = cachedir;
    }
    
    public void setClassname(final String classname) {
        this.classname = classname;
    }
    
    public void setCodebase(final String codebase) {
        this.codebase = codebase;
    }
    
    public void setJararchives(final String jararchives) {
        this.jararchives = jararchives;
    }
    
    public void setParameters(final String[] fieldParameters) {
        this.firePropertyChange("parameters", this.fieldParameters, this.fieldParameters = fieldParameters);
    }
    
    public void setParameters(final int n, final String s) {
        final String[] fieldParameters = this.fieldParameters;
        this.fieldParameters[n] = s;
        this.firePropertyChange("parameters", fieldParameters, this.fieldParameters);
    }
    
    public void setUsebytecodecache(final boolean usebytecodecache) {
        this.usebytecodecache = usebytecodecache;
    }
    
    public void setUseimagecache(final boolean useimagecache) {
        this.useimagecache = useimagecache;
    }
    
    public void setUseindirectadurl(final boolean useindirectadurl) {
        this.useindirectadurl = useindirectadurl;
    }
    
    static void access$loadApplet_impl(final JAdPanel adPanel) {
        adPanel.loadApplet_impl();
    }
}
