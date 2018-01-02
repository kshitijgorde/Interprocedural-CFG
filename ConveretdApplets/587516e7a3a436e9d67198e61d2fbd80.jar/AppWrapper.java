import java.util.Iterator;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.awt.image.ImageProducer;
import sun.applet.AppletAudioClip;
import java.applet.AudioClip;
import java.util.Vector;
import java.util.Enumeration;
import java.applet.AppletContext;
import java.awt.Image;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.io.InputStream;
import java.io.FileInputStream;
import java.applet.AppletStub;
import java.awt.Dimension;
import java.awt.Label;
import java.util.Properties;
import java.applet.Applet;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class AppWrapper extends Frame implements Runnable
{
    private static final int DEFAULT_HEIGHT = 280;
    private static final int DEFAULT_WIDTH = 360;
    private static final String APP_TITLE = "appTitle";
    private static final String APP_ICON = "appIcon";
    private static final String APP_WIDTH = "appWidth";
    private static final String APP_HEIGHT = "appHeight";
    private static final String APP_STATUS_BAR = "appStatusbar";
    private Applet m_applet;
    private String m_strAppletName;
    private Properties m_appletProperties;
    private AppWrapperStub m_appWrapperStub;
    private Label m_StatusBarLabel;
    private Dimension m_dimAppletSize;
    
    public AppWrapper(final String strApplet) {
        this.m_appWrapperStub = null;
        this.m_StatusBarLabel = null;
        this.m_StatusBarLabel = null;
        try {
            final Class cApplet = Class.forName(strApplet);
            this.m_applet = cApplet.newInstance();
            this.CreateAppletInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void CreateAppletInstance() {
        this.m_appWrapperStub = new AppWrapperStub();
        this.m_applet.setStub(this.m_appWrapperStub);
        this.m_strAppletName = this.m_applet.getClass().getName();
        this.setResizable(false);
        this.m_appletProperties = new Properties();
        final int nlastIndex = this.m_strAppletName.lastIndexOf(46);
        String strFileName;
        if (nlastIndex == -1) {
            strFileName = String.valueOf(this.m_strAppletName) + ".ini";
        }
        else {
            strFileName = String.valueOf(this.m_strAppletName.substring(nlastIndex + 1)) + ".ini";
        }
        try {
            this.m_appletProperties.load(new FileInputStream(strFileName));
        }
        catch (IOException e3) {
            System.out.println("Error Reading INI File->" + strFileName);
            try {
                final URL url = new URL(this.m_appWrapperStub.getCodeBase(), strFileName);
                System.out.println("Now trying to read from ->" + url.toString());
                this.m_appletProperties.load(url.openStream());
            }
            catch (IOException e1) {
                e1.printStackTrace();
                return;
            }
        }
        final String strTitle = this.m_appWrapperStub.getParameter("appTitle");
        if (strTitle != null) {
            this.setTitle(strTitle);
        }
        else {
            this.setTitle(this.m_strAppletName);
        }
        final String strIcon = this.m_appWrapperStub.getParameter("appIcon");
        if (strIcon != null) {
            Image icon = null;
            try {
                icon = Toolkit.getDefaultToolkit().getImage(new URL(this.m_appWrapperStub.getCodeBase(), strIcon));
            }
            catch (MalformedURLException e2) {
                e2.printStackTrace();
            }
            this.setIconImage(icon);
        }
        int nWidth = 360;
        int nHeight = 280;
        final String strWidth = this.m_appWrapperStub.getParameter("appWidth");
        if (strWidth != null) {
            nWidth = Integer.parseInt(strWidth);
        }
        final String strHeight = this.m_appWrapperStub.getParameter("appHeight");
        if (strHeight != null) {
            nHeight = Integer.parseInt(strHeight);
        }
        if (nWidth == -1 || nHeight == -1) {
            System.err.println("You must enter valid Width and height.");
            return;
        }
        this.setLayout(new BorderLayout());
        this.add("Center", this.m_applet);
        final String statusBar = this.m_appWrapperStub.getParameter("appStatusbar");
        if (statusBar.equalsIgnoreCase("Yes")) {
            this.add("South", this.m_StatusBarLabel = new Label("Status Bar"));
        }
        else {
            this.m_StatusBarLabel = null;
        }
        this.pack();
        this.validate();
        this.m_dimAppletSize = this.m_applet.getSize();
        this.m_applet.setSize(nWidth, nHeight);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowevent) {
                System.exit(0);
            }
        });
        new Thread(this).start();
    }
    
    public void run() {
        this.m_appWrapperStub.showStatus(String.valueOf(this.m_strAppletName) + " is initializing");
        this.m_applet.init();
        this.validate();
        this.m_appWrapperStub.showStatus(String.valueOf(this.m_strAppletName) + " is starting");
        this.m_applet.start();
        this.validate();
        this.m_appWrapperStub.showStatus(String.valueOf(this.m_strAppletName) + " is running");
    }
    
    public static void main(final String[] args) {
        final String strIniFileName = "AppWrapper.ini";
        final Properties appWrapperProperties = new Properties();
        String strAppletName;
        try {
            appWrapperProperties.load(ClassLoader.getSystemClassLoader().getResourceAsStream(strIniFileName));
            strAppletName = appWrapperProperties.getProperty("applet");
        }
        catch (IOException e) {
            System.out.println("Error Reading INI File -> " + strIniFileName);
            return;
        }
        new AppWrapper(strAppletName).show();
    }
    
    static /* synthetic */ void access$4(final AppWrapper appWrapper, final Dimension dimAppletSize) {
        appWrapper.m_dimAppletSize = dimAppletSize;
    }
    
    private class AppWrapperStub implements AppletContext, AppletStub
    {
        public Applet getApplet(final String strApplet) {
            if (strApplet.equals(AppWrapper.this.m_strAppletName)) {
                return AppWrapper.this.m_applet;
            }
            return null;
        }
        
        public Enumeration getApplets() {
            final Vector vector = new Vector();
            vector.addElement(AppWrapper.this.m_applet);
            return vector.elements();
        }
        
        public AudioClip getAudioClip(final URL url) {
            final AppletAudioClip appletAudioClip = new AppletAudioClip(url);
            return appletAudioClip;
        }
        
        public Image getImage(final URL url) {
            try {
                final ImageProducer imageproducer = (ImageProducer)url.getContent();
                return Toolkit.getDefaultToolkit().createImage(imageproducer);
            }
            catch (IOException ioexception) {
                return null;
            }
        }
        
        public void showDocument(final URL url) {
            ShowDocument showDocument = null;
            showDocument = new ShowDocument(url);
        }
        
        public void showDocument(final URL url, final String strTarget) {
            ShowDocument showDocument = null;
            showDocument = new ShowDocument(url, strTarget);
        }
        
        public void showStatus(final String strStatus) {
            if (AppWrapper.this.m_StatusBarLabel != null) {
                AppWrapper.this.m_StatusBarLabel.setText(strStatus);
            }
        }
        
        public void appletResize(final int nWidth, final int nHeight) {
            final Dimension size;
            final Dimension dimension = size = AppWrapper.this.getSize();
            size.width += nWidth - AppWrapper.this.m_dimAppletSize.width;
            final Dimension dimension2 = dimension;
            dimension2.height += nHeight - AppWrapper.this.m_dimAppletSize.height;
            AppWrapper.this.setSize(dimension);
            AppWrapper.access$4(AppWrapper.this, AppWrapper.this.m_applet.getSize());
        }
        
        public AppletContext getAppletContext() {
            return this;
        }
        
        public URL getCodeBase() {
            final Class cls = this.getClass();
            final ProtectionDomain pDomain = cls.getProtectionDomain();
            final CodeSource cSource = pDomain.getCodeSource();
            final URL loc = cSource.getLocation();
            System.out.println("JAR Location:-" + loc.toString());
            try {
                return new URL("jar:" + loc.toString() + "!/");
            }
            catch (MalformedURLException malformedurlexception) {
                return null;
            }
        }
        
        public URL getDocumentBase() {
            return this.getCodeBase();
        }
        
        public String getParameter(final String strKey) {
            return AppWrapper.this.m_appletProperties.getProperty(strKey);
        }
        
        public boolean isActive() {
            return true;
        }
        
        public void setStream(final String key, final InputStream stream) throws IOException {
        }
        
        public InputStream getStream(final String key) {
            return null;
        }
        
        public Iterator getStreamKeys() {
            return null;
        }
    }
}
