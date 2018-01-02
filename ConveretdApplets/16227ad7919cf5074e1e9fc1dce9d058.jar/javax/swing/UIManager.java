// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.util.Hashtable;
import javax.swing.event.SwingPropertyChangeSupport;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.StringTokenizer;
import javax.swing.plaf.ComponentUI;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.border.Border;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import java.util.Properties;
import java.io.Serializable;

public class UIManager implements Serializable
{
    private static final Object lafStateACKey;
    private static final Object classLock;
    private static Thread currentLAFStateThread;
    private static LAFState currentLAFState;
    private static final String defaultLAFKey = "swing.defaultlaf";
    private static final String auxiliaryLAFsKey = "swing.auxiliarylaf";
    private static final String multiplexingLAFKey = "swing.plaf.multiplexinglaf";
    private static final String installedLAFsKey = "swing.installedlafs";
    private static LookAndFeelInfo[] installedLAFs;
    static /* synthetic */ Class class$javax$swing$UIManager;
    
    static {
        lafStateACKey = new StringBuffer("LookAndFeel State");
        classLock = new Object();
        UIManager.currentLAFStateThread = null;
        UIManager.currentLAFState = null;
        UIManager.installedLAFs = new LookAndFeelInfo[] { new LookAndFeelInfo("Metal", "javax.swing.plaf.metal.MetalLookAndFeel"), new LookAndFeelInfo("CDE/Motif", "com.sun.java.swing.plaf.motif.MotifLookAndFeel"), new LookAndFeelInfo("Windows", "com.sun.java.swing.plaf.windows.WindowsLookAndFeel") };
    }
    
    public static void addAuxiliaryLookAndFeel(final LookAndFeel lookAndFeel) {
        maybeInitialize();
        Vector auxLookAndFeels = getLAFState().auxLookAndFeels;
        if (auxLookAndFeels == null) {
            auxLookAndFeels = new Vector<LookAndFeel>();
        }
        if (!auxLookAndFeels.contains(lookAndFeel)) {
            auxLookAndFeels.addElement(lookAndFeel);
            lookAndFeel.initialize();
            getLAFState().auxLookAndFeels = auxLookAndFeels;
            if (getLAFState().multiLookAndFeel == null) {
                getLAFState().multiLookAndFeel = getMultiLookAndFeel();
            }
        }
    }
    
    public static void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        synchronized (UIManager.classLock) {
            getLAFState().changeSupport.addPropertyChangeListener(propertyChangeListener);
        }
        // monitorexit(UIManager.classLock)
    }
    
    private static void checkProperty(final Properties properties, final String s) {
        try {
            final String property = System.getProperty(s);
            if (property != null) {
                ((Hashtable<String, String>)properties).put(s, property);
            }
        }
        catch (SecurityException ex) {}
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public static Object get(final Object o) {
        return getDefaults().get(o);
    }
    
    public static LookAndFeel[] getAuxiliaryLookAndFeels() {
        maybeInitialize();
        final Vector auxLookAndFeels = getLAFState().auxLookAndFeels;
        if (auxLookAndFeels == null || auxLookAndFeels.size() == 0) {
            return null;
        }
        final LookAndFeel[] array = new LookAndFeel[auxLookAndFeels.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = auxLookAndFeels.elementAt(i);
        }
        return array;
    }
    
    public static Border getBorder(final Object o) {
        return getDefaults().getBorder(o);
    }
    
    public static Color getColor(final Object o) {
        return getDefaults().getColor(o);
    }
    
    public static String getCrossPlatformLookAndFeelClassName() {
        return "javax.swing.plaf.metal.MetalLookAndFeel";
    }
    
    public static UIDefaults getDefaults() {
        maybeInitialize();
        return getLAFState().multiUIDefaults;
    }
    
    public static Dimension getDimension(final Object o) {
        return getDefaults().getDimension(o);
    }
    
    public static Font getFont(final Object o) {
        return getDefaults().getFont(o);
    }
    
    public static Icon getIcon(final Object o) {
        return getDefaults().getIcon(o);
    }
    
    public static Insets getInsets(final Object o) {
        return getDefaults().getInsets(o);
    }
    
    public static LookAndFeelInfo[] getInstalledLookAndFeels() {
        maybeInitialize();
        final LookAndFeelInfo[] installedLAFs = UIManager.installedLAFs;
        final LookAndFeelInfo[] array = new LookAndFeelInfo[installedLAFs.length];
        System.arraycopy(installedLAFs, 0, array, 0, installedLAFs.length);
        return array;
    }
    
    public static int getInt(final Object o) {
        return getDefaults().getInt(o);
    }
    
    private static LAFState getLAFState() {
        final Thread currentThread = Thread.currentThread();
        if (currentThread == UIManager.currentLAFStateThread) {
            return UIManager.currentLAFState;
        }
        LAFState currentLAFState = (LAFState)SwingUtilities.appContextGet(UIManager.lafStateACKey);
        if (currentLAFState == null) {
            synchronized (UIManager.classLock) {
                currentLAFState = (LAFState)SwingUtilities.appContextGet(UIManager.lafStateACKey);
                if (currentLAFState == null) {
                    SwingUtilities.appContextPut(UIManager.lafStateACKey, currentLAFState = new LAFState());
                }
            }
            // monitorexit(UIManager.classLock)
        }
        UIManager.currentLAFStateThread = currentThread;
        return UIManager.currentLAFState = currentLAFState;
    }
    
    public static LookAndFeel getLookAndFeel() {
        maybeInitialize();
        return getLAFState().lookAndFeel;
    }
    
    public static UIDefaults getLookAndFeelDefaults() {
        maybeInitialize();
        return getLAFState().getLookAndFeelDefaults();
    }
    
    private static LookAndFeel getMultiLookAndFeel() {
        LookAndFeel multiLookAndFeel = getLAFState().multiLookAndFeel;
        if (multiLookAndFeel == null) {
            final String property = getLAFState().swingProps.getProperty("swing.plaf.multiplexinglaf", "javax.swing.plaf.multi.MultiLookAndFeel");
            try {
                multiLookAndFeel = (LookAndFeel)SwingUtilities.loadSystemClass(property).newInstance();
            }
            catch (Exception ex) {
                System.err.println("UIManager: failed loading " + property);
            }
        }
        return multiLookAndFeel;
    }
    
    public static String getString(final Object o) {
        return getDefaults().getString(o);
    }
    
    public static String getSystemLookAndFeelClassName() {
        final String[] array = { "" };
        SwingUtilities.doPrivileged(new Runnable() {
            private final /* synthetic */ String[] val$osName = val$osName;
            
            public void run() {
                this.val$osName[0] = System.getProperty("os.name");
            }
        });
        if (array[0] != null) {
            if (array[0].indexOf("Windows") != -1) {
                return "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
            }
            if (array[0].indexOf("Solaris") != -1 || array[0].indexOf("SunOS") != -1) {
                return "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            }
            if (array[0].indexOf("Mac") != -1) {
                return "com.sun.java.swing.plaf.mac.MacLookAndFeel";
            }
        }
        return getCrossPlatformLookAndFeelClassName();
    }
    
    public static ComponentUI getUI(final JComponent component) {
        maybeInitialize();
        ComponentUI componentUI = null;
        final LookAndFeel multiLookAndFeel = getLAFState().multiLookAndFeel;
        if (multiLookAndFeel != null) {
            componentUI = multiLookAndFeel.getDefaults().getUI(component);
        }
        if (componentUI == null) {
            componentUI = getDefaults().getUI(component);
        }
        return componentUI;
    }
    
    private static void initialize() {
        final Properties loadSwingProperties = loadSwingProperties();
        try {
            Compiler.disable();
            initializeSystemDefaults(loadSwingProperties);
            initializeDefaultLAF(loadSwingProperties);
            initializeAuxiliaryLAFs(loadSwingProperties);
            initializeInstalledLAFs(loadSwingProperties);
        }
        finally {
            Compiler.enable();
        }
    }
    
    private static void initializeAuxiliaryLAFs(final Properties properties) {
        final String property = properties.getProperty("swing.auxiliarylaf");
        if (property == null) {
            return;
        }
        Vector auxLookAndFeels = new Vector<Object>();
        final StringTokenizer stringTokenizer = new StringTokenizer(property, ",");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            try {
                auxLookAndFeels.addElement(SwingUtilities.loadSystemClass(nextToken).newInstance());
            }
            catch (Exception ex) {
                System.err.println("UIManager: failed loading auxiliary look and feel " + nextToken);
            }
        }
        if (auxLookAndFeels.size() == 0) {
            auxLookAndFeels = null;
        }
        else {
            getLAFState().multiLookAndFeel = getMultiLookAndFeel();
            if (getLAFState().multiLookAndFeel == null) {
                auxLookAndFeels = null;
            }
        }
        getLAFState().auxLookAndFeels = auxLookAndFeels;
    }
    
    private static void initializeDefaultLAF(final Properties properties) {
        if (getLAFState().lookAndFeel != null) {
            return;
        }
        final String crossPlatformLookAndFeelClassName = getCrossPlatformLookAndFeelClassName();
        String s = "<undefined>";
        try {
            s = properties.getProperty("swing.defaultlaf", crossPlatformLookAndFeelClassName);
            setLookAndFeel(s);
        }
        catch (Exception ex) {
            try {
                s = properties.getProperty("swing.defaultlaf", crossPlatformLookAndFeelClassName);
                setLookAndFeel(s);
            }
            catch (Exception ex2) {
                throw new Error("can't load " + s);
            }
        }
    }
    
    private static void initializeInstalledLAFs(final Properties properties) {
        final String property = properties.getProperty("swing.installedlafs");
        if (property == null) {
            return;
        }
        final Vector vector = new Vector<String>();
        final StringTokenizer stringTokenizer = new StringTokenizer(property, ",", false);
        while (stringTokenizer.hasMoreTokens()) {
            vector.addElement(stringTokenizer.nextToken());
        }
        final Vector vector2 = new Vector<LookAndFeelInfo>(vector.size());
        for (int i = 0; i < vector.size(); ++i) {
            final String s = vector.elementAt(i);
            final String property2 = properties.getProperty(makeInstalledLAFKey(s, "name"), s);
            final String property3 = properties.getProperty(makeInstalledLAFKey(s, "class"));
            if (property3 != null) {
                vector2.addElement(new LookAndFeelInfo(property2, property3));
            }
        }
        UIManager.installedLAFs = new LookAndFeelInfo[vector2.size()];
        for (int j = 0; j < vector2.size(); ++j) {
            UIManager.installedLAFs[j] = vector2.elementAt(j);
        }
    }
    
    private static void initializeSystemDefaults(final Properties swingProps) {
        getLAFState().setSystemDefaults(new UIDefaults(new Object[] { "FocusManagerClassName", "javax.swing.DefaultFocusManager" }));
        getLAFState().swingProps = swingProps;
    }
    
    public static void installLookAndFeel(final String s, final String s2) {
        installLookAndFeel(new LookAndFeelInfo(s, s2));
    }
    
    public static void installLookAndFeel(final LookAndFeelInfo lookAndFeelInfo) {
        final LookAndFeelInfo[] installedLookAndFeels = getInstalledLookAndFeels();
        final LookAndFeelInfo[] installedLookAndFeels2 = new LookAndFeelInfo[installedLookAndFeels.length + 1];
        System.arraycopy(installedLookAndFeels, 0, installedLookAndFeels2, 0, installedLookAndFeels.length);
        installedLookAndFeels2[installedLookAndFeels.length] = lookAndFeelInfo;
        setInstalledLookAndFeels(installedLookAndFeels2);
    }
    
    private static Properties loadSwingProperties() {
        if (((UIManager.class$javax$swing$UIManager != null) ? UIManager.class$javax$swing$UIManager : (UIManager.class$javax$swing$UIManager = class$("javax.swing.UIManager"))).getClassLoader() != null) {
            return new Properties();
        }
        final Properties properties = new Properties();
        SwingUtilities.doPrivileged(new Runnable() {
            private final /* synthetic */ Properties val$props = val$props;
            
            public void run() {
                try {
                    final BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(makeSwingPropertiesFilename())));
                    this.val$props.load(bufferedInputStream);
                    bufferedInputStream.close();
                }
                catch (Exception ex) {}
                checkProperty(this.val$props, "swing.defaultlaf");
                checkProperty(this.val$props, "swing.auxiliarylaf");
                checkProperty(this.val$props, "swing.plaf.multiplexinglaf");
                checkProperty(this.val$props, "swing.installedlafs");
            }
        });
        return properties;
    }
    
    private static String makeInstalledLAFKey(final String s, final String s2) {
        return "swing.installedlaf." + s + "." + s2;
    }
    
    private static String makeSwingPropertiesFilename() {
        final String[] array = { "<java.home undefined>" };
        SwingUtilities.doPrivileged(new Runnable() {
            private final /* synthetic */ String[] val$homeDir = val$homeDir;
            
            public void run() {
                this.val$homeDir[0] = System.getProperty("java.home", "<java.home undefined>");
            }
        });
        final String separator = File.separator;
        return String.valueOf(array[0]) + separator + "lib" + separator + "swing.properties";
    }
    
    private static void maybeInitialize() {
        synchronized (UIManager.classLock) {
            if (!getLAFState().initialized) {
                getLAFState().initialized = true;
                initialize();
            }
        }
        // monitorexit(UIManager.classLock)
    }
    
    public static Object put(final Object o, final Object o2) {
        return getDefaults().put(o, o2);
    }
    
    public static boolean removeAuxiliaryLookAndFeel(final LookAndFeel lookAndFeel) {
        maybeInitialize();
        final Vector auxLookAndFeels = getLAFState().auxLookAndFeels;
        if (auxLookAndFeels == null || auxLookAndFeels.size() == 0) {
            return false;
        }
        final boolean removeElement = auxLookAndFeels.removeElement(lookAndFeel);
        if (removeElement) {
            if (auxLookAndFeels.size() == 0) {
                getLAFState().auxLookAndFeels = null;
                getLAFState().multiLookAndFeel = null;
            }
            else {
                getLAFState().auxLookAndFeels = auxLookAndFeels;
            }
        }
        lookAndFeel.uninitialize();
        return removeElement;
    }
    
    public static void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        synchronized (UIManager.classLock) {
            getLAFState().changeSupport.removePropertyChangeListener(propertyChangeListener);
        }
        // monitorexit(UIManager.classLock)
    }
    
    public static void setInstalledLookAndFeels(final LookAndFeelInfo[] array) throws SecurityException {
        final LookAndFeelInfo[] installedLAFs = new LookAndFeelInfo[array.length];
        System.arraycopy(array, 0, installedLAFs, 0, array.length);
        UIManager.installedLAFs = installedLAFs;
    }
    
    public static void setLookAndFeel(final String s) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        setLookAndFeel(SwingUtilities.loadSystemClass(s).newInstance());
    }
    
    public static void setLookAndFeel(final LookAndFeel lookAndFeel) throws UnsupportedLookAndFeelException {
        if (lookAndFeel != null && !lookAndFeel.isSupportedLookAndFeel()) {
            throw new UnsupportedLookAndFeelException(String.valueOf(lookAndFeel.toString()) + " not supported on this platform");
        }
        final LookAndFeel lookAndFeel2 = getLAFState().lookAndFeel;
        if (lookAndFeel2 != null) {
            lookAndFeel2.uninitialize();
        }
        if ((getLAFState().lookAndFeel = lookAndFeel) != null) {
            lookAndFeel.initialize();
            getLAFState().setLookAndFeelDefaults(lookAndFeel.getDefaults());
        }
        else {
            getLAFState().setLookAndFeelDefaults(null);
        }
        getLAFState().changeSupport.firePropertyChange("lookAndFeel", lookAndFeel2, lookAndFeel);
    }
    
    private static class LAFState
    {
        Properties swingProps;
        private UIDefaults[] tables;
        boolean initialized;
        MultiUIDefaults multiUIDefaults;
        LookAndFeel lookAndFeel;
        LookAndFeel multiLookAndFeel;
        Vector auxLookAndFeels;
        SwingPropertyChangeSupport changeSupport;
        
        LAFState() {
            this.tables = new UIDefaults[2];
            this.initialized = false;
            this.multiUIDefaults = new MultiUIDefaults(this.tables);
            this.multiLookAndFeel = null;
            this.auxLookAndFeels = null;
            this.changeSupport = new SwingPropertyChangeSupport((UIManager.class$javax$swing$UIManager != null) ? UIManager.class$javax$swing$UIManager : (UIManager.class$javax$swing$UIManager = UIManager.class$("javax.swing.UIManager")));
        }
        
        UIDefaults getLookAndFeelDefaults() {
            return this.tables[0];
        }
        
        UIDefaults getSystemDefaults() {
            return this.tables[1];
        }
        
        void setLookAndFeelDefaults(final UIDefaults uiDefaults) {
            this.tables[0] = uiDefaults;
        }
        
        void setSystemDefaults(final UIDefaults uiDefaults) {
            this.tables[1] = uiDefaults;
        }
    }
    
    public static class LookAndFeelInfo
    {
        private String name;
        private String className;
        
        public LookAndFeelInfo(final String name, final String className) {
            this.name = name;
            this.className = className;
        }
        
        public String getClassName() {
            return this.className;
        }
        
        public String getName() {
            return this.name;
        }
        
        public String toString() {
            return String.valueOf(this.getClass().getName()) + "[" + this.getName() + " " + this.getClassName() + "]";
        }
    }
}
