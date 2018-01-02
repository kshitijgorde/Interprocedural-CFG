// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer;

import javax.swing.event.ChangeEvent;
import com.itt.J2KViewer.gui.MenuBar;
import javax.swing.AbstractAction;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.beans.PropertyChangeEvent;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import javax.swing.Icon;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.awt.Point;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Dimension;
import java.beans.PropertyDescriptor;
import com.itt.J2KViewer.util.J2KViewerBeanInfo;
import java.util.Properties;
import java.io.File;
import java.io.IOException;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.net.URL;
import java.beans.PropertyVetoException;
import java.net.URISyntaxException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import com.itt.J2KViewer.util.Helper;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JMenuItem;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.event.ChangeListener;
import java.net.Authenticator;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import com.itt.J2KViewer.util.Log;
import javax.swing.JFrame;

public class EnterpriseViewerFrame extends JFrame
{
    private static Log log;
    private String defaultPropFile;
    private String userPropFile;
    private String argPropFile;
    private String homeDir;
    private JTabbedPane tabs;
    private JPopupMenu tabMenu;
    private JMenuBar defaultMenu;
    private JMenu defaultFileMenu;
    private ArrayList beansTabs;
    private ArrayList beansFrames;
    private ArrayList recentFiles;
    private JMenu recentFileMenu;
    private JMenu recentFileMenuDefault;
    private boolean showRecentFilesMenu;
    static /* synthetic */ Class class$com$itt$J2KViewer$EnterpriseViewerFrame;
    
    public EnterpriseViewerFrame() {
        this.defaultPropFile = "J2KViewer.properties";
        this.userPropFile = "EnterpriseViewer.properties";
        this.argPropFile = null;
        this.tabMenu = new JPopupMenu();
        this.defaultMenu = new JMenuBar();
        this.defaultFileMenu = new JMenu("File");
        this.beansTabs = new ArrayList();
        this.beansFrames = new ArrayList();
        this.recentFiles = null;
        this.recentFileMenu = null;
        this.recentFileMenuDefault = null;
        this.showRecentFilesMenu = true;
        Authenticator.setDefault(new MyAuthenticator());
        this.homeDir = System.getProperty("user.home");
        this.initializeMenus();
        (this.tabs = new JTabbedPane()).addChangeListener(new TabListener());
        this.tabs.addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent mouseEvent) {
                if (mouseEvent.isPopupTrigger()) {
                    if (EnterpriseViewerFrame.this.tabs.getTabCount() == 1) {
                        EnterpriseViewerFrame.this.tabMenu.setEnabled(false);
                    }
                    EnterpriseViewerFrame.this.tabMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
                }
            }
        });
        this.initComponents();
        this.setTitle("Enterprise Viewer 1.7");
        this.setJMenuBar(this.defaultMenu);
    }
    
    public void initializeMenus() {
        this.tabMenu.add(new JMenuItem(new TearOffAction("Tear Off")));
        this.tabMenu.add(new JMenuItem(new CloseTabAction("Close Tab")));
        final JMenuItem menuItem = new JMenuItem("Open...");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                EnterpriseViewerFrame.this.doOpenURLDialog();
            }
        });
        this.defaultFileMenu.add(menuItem);
        this.defaultFileMenu.add(new ExitAction("Exit"));
        this.defaultMenu.add(this.defaultFileMenu);
    }
    
    public void openImage(final String s, String string) {
        final J2KViewerBean j2KViewerBean = new J2KViewerBean(this);
        j2KViewerBean.addPropertyChangeListener(new IconListener(j2KViewerBean, this));
        this.cancelStreams();
        this.beansTabs.add(j2KViewerBean);
        this.setBeanProps(j2KViewerBean, s);
        j2KViewerBean.setPropFile(this.homeDir + "/" + "EnterpriseViewer" + "/", this.userPropFile);
        if (j2KViewerBean.propFileWasSet()) {
            this.setUserProps(j2KViewerBean, this.userPropFile);
        }
        this.addPanelToTab(j2KViewerBean, string);
        this.setJMenuBar(j2KViewerBean.getMenuBar());
        if (this.showRecentFilesMenu) {
            this.loadRecentFiles();
            this.updateRecentFiles(string);
        }
        this.customizeMenu();
        if (string != null) {
            try {
                if (!Helper.isJPIPProtocol(string)) {
                    string = "file:///" + URLEncoder.encode(string, "UTF-8");
                }
                final URI uri = new URI(string);
                j2KViewerBean.setImageURL(string);
            }
            catch (UnsupportedEncodingException ex) {
                EnterpriseViewerFrame.log.error("The image path is invalid", ex);
            }
            catch (URISyntaxException ex2) {
                EnterpriseViewerFrame.log.error("The image URL is invalid", ex2);
            }
            catch (PropertyVetoException ex3) {
                EnterpriseViewerFrame.log.error("Error setting bean properties", ex3);
            }
        }
        j2KViewerBean.openImage();
        this.setTitle("Enterprise Viewer 1.7");
        if (!j2KViewerBean.isOpen()) {
            if (this.recentFiles != null) {
                this.recentFiles.remove(this.recentFiles.size() - 1);
                this.recentFileMenu.remove(0);
                this.recentFileMenuDefault.remove(0);
            }
            this.beansTabs.remove(j2KViewerBean);
            this.tabs.remove(this.tabs.getTabCount() - 1);
            if (this.tabs.getTabCount() == 0) {
                this.setJMenuBar(this.defaultMenu);
            }
        }
    }
    
    public void setBeanProps(final J2KViewerBean j2KViewerBean, final String argPropFile) {
        URL urlAsResource = null;
        try {
            EnterpriseViewerFrame.log.info("Attempting to load properties from '" + argPropFile + "'");
            if (argPropFile == null || this.defaultPropFile.equals(argPropFile)) {
                EnterpriseViewerFrame.log.info("Using default properties");
                urlAsResource = Helper.getURLAsResource(this.defaultPropFile);
            }
            else {
                this.argPropFile = argPropFile;
                urlAsResource = new URL(argPropFile);
            }
            this.loadProperties(j2KViewerBean, urlAsResource);
        }
        catch (IOException ex) {
            if (!this.defaultPropFile.equals(argPropFile)) {
                JOptionPane.showMessageDialog(null, "Failed to load properties from: \n " + argPropFile + "\n Trying default properties");
                this.argPropFile = null;
                this.setBeanProps(j2KViewerBean, this.defaultPropFile);
            }
            else {
                JOptionPane.showMessageDialog(null, "Failed to load default properties");
            }
            EnterpriseViewerFrame.log.error("Error loading properties file from " + urlAsResource, ex);
        }
    }
    
    public void setUserProps(final J2KViewerBean j2KViewerBean, final String s) {
        try {
            this.loadProperties(j2KViewerBean, new File(this.homeDir + "/" + "EnterpriseViewer" + "/" + s).toURL());
        }
        catch (IOException ex) {
            EnterpriseViewerFrame.log.error("Error loading user preferences", ex);
        }
    }
    
    public void loadProperties(final J2KViewerBean j2KViewerBean, final URL url) throws IOException {
        try {
            final Properties properties = new Properties();
            if (url != null) {
                properties.load(url.openStream());
                if (properties.size() > 0) {
                    final PropertyDescriptor[] propertyDescriptors = new J2KViewerBeanInfo().getPropertyDescriptors();
                    for (int i = 0; i < propertyDescriptors.length; ++i) {
                        final PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
                        propertyDescriptor.getName();
                        j2KViewerBean.initProperty(propertyDescriptor, properties.getProperty(propertyDescriptor.getName()));
                    }
                }
            }
        }
        catch (IOException ex) {
            throw ex;
        }
    }
    
    public void addPanelToTab(final J2KViewerBean j2KViewerBean, final String s) {
        String s2 = s.substring(s.lastIndexOf("/") + 1);
        if (s2.length() > 15) {
            s2 = s2.substring(0, 15) + "...";
        }
        if (this.tabs.getTabCount() == 0 && this.getContentPane().getComponentCount() == 0) {
            this.tabs.addTab(s2, j2KViewerBean.getMainPanel());
            this.getContentPane().add(this.tabs);
            this.setLocation(10, 10);
            final int[] applicationSize = j2KViewerBean.getApplicationSize();
            this.setSize(new Dimension(applicationSize[0], applicationSize[1]));
            this.validate();
            this.setVisible(true);
        }
        else {
            this.tabs.addTab(s2, j2KViewerBean.getMainPanel());
            this.setVisible(true);
        }
        this.tabs.setSelectedIndex(this.beansTabs.size() - 1);
        if (!this.isVisible()) {
            this.setVisible(true);
        }
    }
    
    public void customizeMenu() {
        final JMenu menu = (JMenu)this.getJMenuBar().getComponent(0);
        menu.remove(menu.getItemCount() - 2);
        menu.remove(menu.getItemCount() - 1);
        menu.add(new ExitAction("Exit"));
        final JMenuItem menuItem = new JMenuItem("Open...");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                EnterpriseViewerFrame.this.doOpenURLDialog();
            }
        });
        menu.insert(menuItem, 0);
        if (this.recentFileMenu != null) {
            menu.insert(this.recentFileMenu, 1);
        }
        if (this.recentFileMenuDefault != null) {
            this.defaultFileMenu.insert(this.recentFileMenuDefault, 1);
        }
        menu.insert(new TearOffAction("Tear Off"), menu.getItemCount() - 1);
        menu.insert(new CloseTabAction("Close Tab"), menu.getItemCount() - 1);
    }
    
    private void initComponents() {
        this.setDefaultCloseOperation(0);
        this.setTitle("Enterprise Viewer Frame");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                EnterpriseViewerFrame.this.checkBeforeExit();
            }
        });
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent componentEvent) {
                EnterpriseViewerFrame.this.formComponentResized(componentEvent);
            }
        });
        this.pack();
    }
    
    private void formComponentResized(final ComponentEvent componentEvent) {
        final Component component = componentEvent.getComponent();
        if (component.getWidth() < 475) {
            component.setSize(475, component.getHeight());
        }
        for (int i = 0; i < this.beansTabs.size(); ++i) {
            final J2KViewerBean j2KViewerBean = this.beansTabs.get(i);
            j2KViewerBean.setApplicationWidth(component.getWidth());
            j2KViewerBean.setApplicationHeight(component.getHeight());
        }
    }
    
    public void doOpenURLDialog() {
        final OpenURLDialog openURLDialog = new OpenURLDialog(this, true);
        final Point locationOnScreen = this.getLocationOnScreen();
        openURLDialog.setLocation(locationOnScreen.x + 20, locationOnScreen.y + 20);
        openURLDialog.setVisible(true);
        if (!openURLDialog.isCancelled() && openURLDialog.getSelectedURL() != null) {
            if (!Helper.isJPIPProtocol(openURLDialog.getSelectedURL())) {
                JOptionPane.showMessageDialog(this, "The URL syntax is invalid. Try again.", "URL Syntax Error", 0);
                return;
            }
            String s = this.defaultPropFile;
            if (this.argPropFile != null) {
                s = this.argPropFile;
            }
            this.openImage(s, openURLDialog.getSelectedURL());
        }
    }
    
    public void closeAllImages() {
        for (int i = 0; i < this.beansFrames.size(); ++i) {
            ((J2KViewerBean)this.beansFrames.get(i)).closeImage();
        }
        for (int j = 0; j < this.beansTabs.size(); ++j) {
            ((J2KViewerBean)this.beansTabs.get(j)).closeImage();
        }
    }
    
    public void exitApplication() {
        if (this.showRecentFilesMenu) {
            this.saveRecentFiles();
        }
        this.closeAllImages();
        System.runFinalization();
        System.gc();
        System.exit(0);
    }
    
    public void checkBeforeExit() {
        if (this.beansTabs.size() > 0 || this.beansFrames.size() > 0) {
            if (JOptionPane.showConfirmDialog(this, " You still have open images, do you want to quit?", "Quit Enterprise Viewer", 2) == 0) {
                this.exitApplication();
            }
        }
        else {
            this.exitApplication();
        }
    }
    
    public ArrayList getListOfBeansInTabs() {
        return this.beansTabs;
    }
    
    public JTabbedPane getTabbedPane() {
        return this.tabs;
    }
    
    public void loadRecentFiles() {
        if (this.recentFileMenu == null && this.recentFiles == null) {
            this.recentFileMenu = new JMenu("Recent Files");
            this.recentFileMenuDefault = new JMenu("Recent Files");
            try {
                final FileInputStream fileInputStream = new FileInputStream(this.homeDir + "/" + "EnterpriseViewer" + "/recent_files" + ".state");
                final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                this.recentFiles = (ArrayList)objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
                for (int i = this.recentFiles.size() - 1; i >= 0; --i) {
                    this.recentFileMenu.add(new RecentFileAction((String)this.recentFiles.get(i), null));
                    this.recentFileMenuDefault.add(new RecentFileAction((String)this.recentFiles.get(i), null));
                }
            }
            catch (Exception ex) {
                System.out.println(ex);
                this.recentFiles = new ArrayList();
            }
        }
    }
    
    public void saveRecentFiles() {
        try {
            if (this.recentFiles != null) {
                final FileOutputStream fileOutputStream = new FileOutputStream(this.homeDir + "/" + "EnterpriseViewer" + "/recent_files" + ".state");
                final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(this.recentFiles);
                objectOutputStream.close();
                fileOutputStream.close();
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void updateRecentFiles(final String s) {
        for (int i = 0; i < this.recentFiles.size(); ++i) {
            if (((String)this.recentFiles.get(i)).equals(s)) {
                this.recentFiles.remove(i);
                this.recentFileMenu.remove(i);
                this.recentFileMenuDefault.remove(i);
                break;
            }
        }
        while (this.recentFiles.size() > 10) {
            this.recentFiles.remove(0);
        }
        this.recentFiles.add(s);
        this.recentFileMenu.removeAll();
        this.recentFileMenuDefault.removeAll();
        for (int j = this.recentFiles.size() - 1; j >= 0; --j) {
            this.recentFileMenu.add(new RecentFileAction((String)this.recentFiles.get(j), null));
            this.recentFileMenuDefault.add(new RecentFileAction((String)this.recentFiles.get(j), null));
        }
    }
    
    private void cancelStreams() {
        for (int i = 0; i < this.beansTabs.size(); ++i) {
            final J2KViewerBean j2KViewerBean = this.beansTabs.get(i);
            if (j2KViewerBean.isOpen()) {
                j2KViewerBean.cancelStream();
            }
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
        EnterpriseViewerFrame.log = new Log((EnterpriseViewerFrame.class$com$itt$J2KViewer$EnterpriseViewerFrame == null) ? (EnterpriseViewerFrame.class$com$itt$J2KViewer$EnterpriseViewerFrame = class$("com.itt.J2KViewer.EnterpriseViewerFrame")) : EnterpriseViewerFrame.class$com$itt$J2KViewer$EnterpriseViewerFrame);
    }
    
    private class IconListener implements PropertyChangeListener
    {
        J2KViewerBean viewerBean;
        EnterpriseViewerFrame parentFrame;
        
        public IconListener(final J2KViewerBean viewerBean, final EnterpriseViewerFrame parentFrame) {
            this.viewerBean = viewerBean;
            this.parentFrame = parentFrame;
        }
        
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            if (propertyChangeEvent.getPropertyName().equals("DisplayUpdated")) {
                final int index = this.parentFrame.getListOfBeansInTabs().indexOf(this.viewerBean);
                final JTabbedPane tabbedPane = this.parentFrame.getTabbedPane();
                if (index != -1) {
                    final BufferedImage bufferedImage = this.viewerBean.getBufferedImage();
                    if (bufferedImage != null) {
                        tabbedPane.setIconAt(index, new ImageIcon(bufferedImage.getScaledInstance(20, -1, 2)));
                    }
                }
            }
        }
    }
    
    private class CloseFrameListener extends WindowAdapter
    {
        JFrame parentFrame;
        J2KViewerBean activeBean;
        
        public CloseFrameListener(final JFrame parentFrame, final J2KViewerBean activeBean) {
            this.parentFrame = parentFrame;
            this.activeBean = activeBean;
        }
        
        public void windowClosing(final WindowEvent windowEvent) {
            this.activeBean.closeImage();
            EnterpriseViewerFrame.this.beansFrames.remove(this.activeBean);
            this.parentFrame.setVisible(false);
            this.parentFrame.dispose();
        }
    }
    
    private class ExitAction extends AbstractAction
    {
        public ExitAction(final String s) {
            super(s);
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            EnterpriseViewerFrame.this.checkBeforeExit();
        }
    }
    
    private class RecentFileAction extends AbstractAction
    {
        protected String file_name;
        
        public RecentFileAction(final String file_name, final Icon icon) {
            super(file_name, icon);
            this.file_name = file_name;
            this.putValue("Name", this.file_name.substring(this.file_name.lastIndexOf("/") + 1));
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            String s = EnterpriseViewerFrame.this.defaultPropFile;
            if (EnterpriseViewerFrame.this.argPropFile != null) {
                s = EnterpriseViewerFrame.this.argPropFile;
            }
            EnterpriseViewerFrame.this.openImage(s, this.file_name);
        }
    }
    
    private class CloseTabAction extends AbstractAction
    {
        public CloseTabAction(final String s) {
            super(s);
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final int selectedIndex = EnterpriseViewerFrame.this.tabs.getSelectedIndex();
            System.out.println("Closing Tab index: " + selectedIndex);
            ((J2KViewerBean)EnterpriseViewerFrame.this.beansTabs.get(selectedIndex)).closeImage();
            EnterpriseViewerFrame.this.beansTabs.remove(selectedIndex);
            EnterpriseViewerFrame.this.tabs.remove(selectedIndex);
            if (EnterpriseViewerFrame.this.tabs.getTabCount() == 0) {
                EnterpriseViewerFrame.this.setJMenuBar(EnterpriseViewerFrame.this.defaultMenu);
            }
            else if (EnterpriseViewerFrame.this.beansTabs.size() > 0) {
                final int selectedIndex2 = EnterpriseViewerFrame.this.tabs.getSelectedIndex();
                if (EnterpriseViewerFrame.this.isVisible()) {
                    EnterpriseViewerFrame.this.setJMenuBar(((J2KViewerBean)EnterpriseViewerFrame.this.beansTabs.get(selectedIndex2)).getMenuBar());
                    EnterpriseViewerFrame.this.customizeMenu();
                    EnterpriseViewerFrame.this.cancelStreams();
                    ((J2KViewerBean)EnterpriseViewerFrame.this.beansTabs.get(selectedIndex2)).restartImageStream();
                }
            }
            EnterpriseViewerFrame.this.setVisible(true);
        }
    }
    
    private class CloseSatelliteAction extends AbstractAction
    {
        JFrame parentFrame;
        J2KViewerBean activeBean;
        
        public CloseSatelliteAction(final String s, final JFrame parentFrame, final J2KViewerBean activeBean) {
            super(s);
            this.parentFrame = parentFrame;
            this.activeBean = activeBean;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            this.activeBean.closeImage();
            EnterpriseViewerFrame.this.beansFrames.remove(this.activeBean);
            this.parentFrame.setVisible(false);
            this.parentFrame.dispose();
        }
    }
    
    private class TearOffAction extends AbstractAction
    {
        public TearOffAction(final String s) {
            super(s);
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            if (EnterpriseViewerFrame.this.tabs.getTabCount() > 0) {
                final int selectedIndex = EnterpriseViewerFrame.this.tabs.getSelectedIndex();
                final String title = EnterpriseViewerFrame.this.tabs.getTitleAt(selectedIndex);
                final J2KViewerBean j2KViewerBean = EnterpriseViewerFrame.this.beansTabs.get(selectedIndex);
                EnterpriseViewerFrame.this.beansFrames.add(EnterpriseViewerFrame.this.beansTabs.remove(selectedIndex));
                EnterpriseViewerFrame.this.tabs.remove(selectedIndex);
                final JFrame viewerFrame = new JFrame();
                viewerFrame.setTitle(title);
                viewerFrame.getContentPane().add(j2KViewerBean.getMainPanel());
                viewerFrame.setLocation(30, 30);
                viewerFrame.setSize(EnterpriseViewerFrame.this.getSize());
                viewerFrame.addWindowListener(new CloseFrameListener(viewerFrame, j2KViewerBean));
                final MenuBar menuBar = j2KViewerBean.getMenuBar();
                final JMenu menu = (JMenu)menuBar.getComponent(0);
                menu.remove(menu.getItemCount() - 1);
                menu.remove(menu.getItemCount() - 1);
                menu.add(new CloseSatelliteAction("Close Window", viewerFrame, j2KViewerBean));
                viewerFrame.setJMenuBar(menuBar);
                j2KViewerBean.setViewerFrame(viewerFrame);
                EnterpriseViewerFrame.this.setVisible(true);
                viewerFrame.setVisible(true);
                if (EnterpriseViewerFrame.this.tabs.getTabCount() == 0) {
                    EnterpriseViewerFrame.this.setJMenuBar(EnterpriseViewerFrame.this.defaultMenu);
                }
            }
        }
    }
    
    private class TabListener implements ChangeListener
    {
        public void stateChanged(final ChangeEvent changeEvent) {
            if (changeEvent.getSource() instanceof JTabbedPane) {
                final JTabbedPane tabbedPane = (JTabbedPane)changeEvent.getSource();
                if (EnterpriseViewerFrame.this.beansTabs.size() > 0) {
                    final int selectedIndex = tabbedPane.getSelectedIndex();
                    if (EnterpriseViewerFrame.this.isVisible()) {
                        EnterpriseViewerFrame.this.setJMenuBar(((J2KViewerBean)EnterpriseViewerFrame.this.beansTabs.get(selectedIndex)).getMenuBar());
                        EnterpriseViewerFrame.this.customizeMenu();
                        EnterpriseViewerFrame.this.cancelStreams();
                        ((J2KViewerBean)EnterpriseViewerFrame.this.beansTabs.get(selectedIndex)).restartImageStream();
                    }
                }
            }
        }
    }
    
    protected class MyAuthenticator extends Authenticator
    {
    }
}
