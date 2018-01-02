// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewapplet;

import java.awt.Dialog;
import java.util.Vector;
import java.awt.Component;
import java.awt.Container;
import java.net.URL;
import lotus.notes.util.PropertyLoader;
import java.lang.reflect.Method;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.ms.io.clientstorage.ClientStorageManager;
import com.ms.security.PolicyEngine;
import com.ms.security.PermissionID;
import java.awt.LayoutManager;
import lotus.notes.components.FolderSelectionDialog;
import lotus.notes.apps.viewpanel.ViewPanel;
import java.awt.Rectangle;
import java.util.Properties;
import java.awt.Color;
import java.awt.Frame;
import lotus.notes.components.DialogListener;
import java.applet.Applet;

public class ViewApplet extends Applet implements DialogListener
{
    private String dbName;
    private String viewName;
    private String linkTargetFrame;
    private String hostName;
    private Frame parentFrame;
    private Color bgColor;
    private boolean forceRead;
    private boolean wasNoBorder;
    private String locale;
    private Properties props;
    private boolean restart;
    private boolean sizeInitialized;
    private String panelStyle;
    private String checkStyle;
    private static final boolean DEBUG = false;
    Rectangle AppletRect;
    Rectangle ViewRect;
    ViewPanel viewPanel;
    private String osname;
    private String BrowserVendor;
    private static final String PARAM_SHOW_ONLY_SELECTED = "ShowOnlySelected";
    private static final String PARAM_SHOW_HEADER = "ShowHeader";
    private static final String PARAM_FORCE_READ = "ForceRead";
    private static final String PARAM_SHOW_SELECTION_MARGIN = "ShowSelectionMargin";
    private static final String PARAM_RESTRICT_TO_CATEGORY = "RestrictToCategory";
    private static final String PARAM_HOST = "HostName";
    private static final String PARAM_PREVIEW_MODE = "PreviewMode";
    private static final String PARAM_EXPAND = "Expand";
    private static final String PARAM_LOCALE = "locale";
    private static final String PARAM_SIMPLEVIEW = "SimpleView";
    private static final String PARAM_GOTO_BOTTOM = "GotoBottom";
    private static final String PARAM_DOUBLE_CLICK_TARGET = "DblClickTarget";
    private static final String PARAM_VIEW_NAME = "ViewName";
    private static final String PARAM_VIEW_UNID = "ViewUNID";
    private static final String PARAM_TRASH_UNID = "TrashUNID";
    private static final String PARAM_SHOW_SCROLLBARS = "ShowScrollbars";
    private static final String PARAM_ICON_PATH = "IconPath";
    private static final String PARAM_STARTKEY = "startkey";
    private static final String PARAM_FOCUS = "focus";
    private static final String SELECTION_BORDER_BOX = "BOX";
    private FolderSelectionDialog folderDialog;
    private boolean bNeedsPriv;
    
    public ViewApplet() {
        this.forceRead = false;
        this.wasNoBorder = false;
        this.locale = null;
        this.props = new DefaultProperties();
        this.restart = false;
        this.sizeInitialized = false;
        this.panelStyle = "NO_BORDER";
        this.checkStyle = "CHECK_PLAIN";
        this.bNeedsPriv = false;
    }
    
    public void init() {
        this.BrowserVendor = System.getProperty("browser");
        if (this.BrowserVendor != null) {
            this.BrowserVendor = this.BrowserVendor.toLowerCase();
        }
        if (this.BrowserVendor != null && this.BrowserVendor.indexOf("plugin") == -1 && this.BrowserVendor.indexOf("lotus") == -1) {
            this.bNeedsPriv = (this.setInstalled() && this.BrowserVendor.indexOf("netscape") != -1);
        }
        this.sizeInitialized = false;
        this.setLayout(null);
        this.parentFrame = this.getParentFrame();
        this.osname = System.getProperty("os.name");
        if (this.osname != null) {
            this.osname = this.osname.toUpperCase();
        }
        this.initPanelRects(false);
        this.getParameters();
        if (this.folderDialog == null) {
            this.folderDialog = new FolderSelectionDialog(this.parentFrame, this, this.props);
        }
    }
    
    public boolean setInstalled() {
        boolean b = false;
        int n = 0;
        boolean b2 = false;
        final boolean b3 = this.BrowserVendor != null && this.BrowserVendor.indexOf("netscape") != -1;
        final String parameter = this.getParameter("archive");
        if (parameter == null) {
            return true;
        }
        if (parameter.toLowerCase().indexOf("nvapplet") == -1) {
            return true;
        }
        if (!b3) {
            try {
                PolicyEngine.assertPermission(PermissionID.CLIENTSTORE);
                n = ((new BufferedReader(new InputStreamReader(ClientStorageManager.openReadable("dominoapplets.txt"))).readLine().indexOf("_installed=true;") != -1) ? 1 : 0);
            }
            catch (Exception ex3) {}
        }
        else {
            b2 = (b3 && this.getClass().getClassLoader() == null);
        }
        if (n == 0) {
            if (!b2) {
                return b;
            }
        }
        try {
            final Method[] methods = Class.forName("lotus.notes.util.Util").getMethods();
            Method method = null;
            for (int i = 0; i <= methods.length - 1; ++i) {
                if (methods[i].getName().equals("setInstallCookie")) {
                    method = methods[i];
                    break;
                }
            }
            if (method != null) {
                final Object[] array = { "true", this, b2 ? new Boolean(true) : new Boolean(false) };
                try {
                    method.invoke(this, array);
                    b = true;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        return b;
    }
    
    public void stop() {
        if (this.viewPanel != null) {
            this.viewPanel.stop();
        }
        this.restart = true;
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
        if (this.viewPanel != null) {
            this.viewPanel.resize(n3, n4);
        }
    }
    
    private void getParameters() {
        if (this.viewPanel == null) {
            return;
        }
        final String parameter = this.getParameter("IconPath");
        if (parameter != null) {
            this.viewPanel.setIconPath(parameter);
        }
        final String parameter2 = this.getParameter("GotoBottom");
        if (parameter2 != null && Boolean.valueOf(parameter2) && this.viewPanel != null) {
            this.viewPanel.setGotoBottom(true);
        }
        final String parameter3 = this.getParameter("startkey");
        if (parameter3 != null && this.viewPanel != null) {
            this.viewPanel.startkey = parameter3;
        }
        final String parameter4 = this.getParameter("focus");
        if (parameter4 != null && this.viewPanel != null) {
            this.viewPanel.getfocus = Boolean.valueOf(parameter4);
        }
        final String parameter5 = this.getParameter("SimpleView");
        if (parameter5 != null && this.viewPanel != null) {
            this.viewPanel.setSimpleView(Boolean.valueOf(parameter5));
        }
        this.locale = this.getParameter("locale");
        if (this.locale != null) {
            this.props = new PropertyLoader().getProperties("view", this.locale, this.props, this.getCodeBase());
            this.viewPanel.setLocaleString(this.locale);
        }
        if (this.viewPanel != null) {
            this.viewPanel.setProperties(this.props);
        }
        final String parameter6 = this.getParameter("Expand");
        this.setExpansionState(2);
        if (parameter6 != null && Boolean.valueOf(parameter6)) {
            this.setExpansionState(1);
        }
        this.setBackgroundColor(this.getParameter("bgcolor"));
        this.dbName = this.getParameter("Database");
        this.viewPanel.setDatabaseName(this.dbName);
        this.hostName = this.getParameter("HostName");
        if (this.hostName == null) {
            final int port = this.getCodeBase().getPort();
            final URL codeBase = this.getCodeBase();
            try {
                final String file = codeBase.getFile();
                new URL(file.substring(1, file.length()));
                final String s = (port != -1) ? (codeBase.getHost() + ":" + port + codeBase.getFile()) : (codeBase.getHost() + codeBase.getFile());
                this.hostName = ((s.indexOf("domjava") != -1) ? s.substring(0, s.lastIndexOf("domjava") - 1) : s);
            }
            catch (Exception ex) {
                this.hostName = ((port != -1) ? (this.getCodeBase().getHost() + ":" + String.valueOf(port)) : this.getCodeBase().getHost());
            }
        }
        this.viewPanel.setHostName(this.hostName);
        this.viewPanel.setProtocol(this.getCodeBase().getProtocol());
        this.viewPanel.setTarget(this.getParameter("LinkTargetFrame"));
        this.viewPanel.setDblClickTarget(this.getParameter("DblClickTarget"));
        this.setViewUNID(this.getParameter("ViewUNID"));
        this.setViewName(this.getParameter("ViewName"));
        final String parameter7 = this.getParameter("TrashUNID");
        if (parameter7 != null) {
            this.setTrashUNID(parameter7);
        }
        final String parameter8 = this.getParameter("ForceRead");
        if (parameter8 != null) {
            this.forceRead = Boolean.valueOf(parameter8);
        }
        final String parameter9 = this.getParameter("ShowOnlySelected");
        if (parameter9 != null) {
            this.setShowOnlySelected(Boolean.valueOf(parameter9));
        }
        final String parameter10 = this.getParameter("ShowHeader");
        if (parameter10 != null) {
            this.setHeaderVisible(Boolean.valueOf(parameter10));
        }
        else {
            this.setHeaderVisible(true);
        }
        final String parameter11 = this.getParameter("ShowScrollbars");
        if (parameter11 != null) {
            this.setScrollbarsVisible(Boolean.valueOf(parameter11));
        }
        else {
            this.setScrollbarsVisible(true);
        }
        final String parameter12 = this.getParameter("ShowSelectionMargin");
        if (parameter12 != null) {
            this.setSelectionMarginVisible(Boolean.valueOf(parameter12));
        }
        else {
            this.setSelectionMarginVisible(true);
        }
        final String parameter13 = this.getParameter("RestrictToCategory");
        if (parameter13 != null) {
            this.setRestrictToCategoryName(parameter13);
        }
    }
    
    public String getAppletInfo() {
        if (this.dbName != null && this.viewName != null) {
            return "Database Name:" + this.dbName + " View Name:" + this.viewName;
        }
        return "Invalid Applet Parameters";
    }
    
    private Frame getParentFrame() {
        Container parent = this;
        while (!((parent = parent.getParent()) instanceof Frame)) {}
        return (Frame)parent;
    }
    
    private void initPanelRects(final boolean b) {
        final boolean b2 = false;
        this.AppletRect = this.bounds();
        this.ViewRect = new Rectangle(this.AppletRect.x, this.AppletRect.y, this.AppletRect.width, this.AppletRect.height);
        if (!b) {
            if (this.viewPanel == null) {
                this.viewPanel = new ViewPanel();
            }
            this.add(this.viewPanel);
            this.panelStyle = this.getParameter("panelStyle");
            if (this.panelStyle != null && this.panelStyle.equalsIgnoreCase("NO_BORDER")) {
                this.wasNoBorder = true;
                this.viewPanel.setPanelStyle("LINE_BORDER");
            }
            else {
                this.viewPanel.setPanelStyle(this.panelStyle);
            }
            this.viewPanel.setNeedsPriv(this.bNeedsPriv);
        }
        if (this.viewPanel != null && !b2) {
            this.viewPanel.reshape(this.ViewRect.x, this.ViewRect.y, this.ViewRect.width, this.ViewRect.height);
            this.viewPanel.init(b);
        }
    }
    
    public void setExpansionState(final int expansionState) {
        if (this.viewPanel != null) {
            this.viewPanel.setExpansionState(expansionState);
        }
    }
    
    public void setViewName(final String viewName) {
        if (viewName != null) {
            if (this.viewPanel != null) {
                this.viewPanel.setViewName(viewName);
            }
            return;
        }
        throw new IllegalArgumentException("No View was specified");
    }
    
    public void setViewUNID(final String viewUNID) {
        if (viewUNID != null) {
            if (this.viewPanel != null) {
                this.viewPanel.setViewUNID(viewUNID);
            }
            return;
        }
        throw new IllegalArgumentException("No View UNID was specified");
    }
    
    public void setTrashUNID(final String trashUNID) {
        if (this.viewPanel != null) {
            this.viewPanel.setTrashUNID(trashUNID);
        }
    }
    
    public ViewPanel getViewPanel() {
        return this.viewPanel;
    }
    
    public String getViewName() {
        if (this.viewPanel != null) {
            return this.viewPanel.getViewName();
        }
        return null;
    }
    
    public void setForceRead(final boolean forceRead) {
        this.forceRead = forceRead;
    }
    
    public void setHeaderVisible(final boolean headerVisible) {
        if (this.viewPanel != null) {
            this.viewPanel.setHeaderVisible(headerVisible);
        }
    }
    
    public boolean isHeaderVisible() {
        return this.viewPanel != null && this.viewPanel.isHeaderVisible();
    }
    
    public void setScrollbarsVisible(final boolean scrollbarsVisible) {
        if (this.viewPanel != null) {
            this.viewPanel.setScrollbarsVisible(scrollbarsVisible);
        }
    }
    
    public boolean isScrollbarsVisible() {
        return this.viewPanel != null && this.viewPanel.isScrollbarsVisible();
    }
    
    public void setSelectionMarginVisible(final boolean selectionMarginVisible) {
        if (this.viewPanel != null) {
            this.viewPanel.setSelectionMarginVisible(selectionMarginVisible);
        }
    }
    
    public boolean isSelectionMarginVisible() {
        return this.viewPanel != null && this.viewPanel.isSelectionMarginVisible();
    }
    
    public void setShowOnlySelected(final boolean showOnlySelected) {
        if (this.viewPanel != null) {
            this.viewPanel.setShowOnlySelected(showOnlySelected);
        }
    }
    
    public void toggleShowOnlySelected() {
        if (this.viewPanel != null) {
            this.viewPanel.setShowOnlySelected(!this.viewPanel.getShowOnlySelected());
        }
    }
    
    public String getCurrentDocument() {
        if (this.viewPanel != null) {
            return this.viewPanel.getCurrentDocument();
        }
        return null;
    }
    
    public String[] getSelectedDocuments() {
        if (this.viewPanel != null) {
            return this.viewPanel.getSelectedDocuments();
        }
        return null;
    }
    
    public Vector getSelectedDocumentsEx() {
        Vector<String> vector = null;
        final String[] selectedDocuments = this.getSelectedDocuments();
        if (selectedDocuments != null) {
            final int length = selectedDocuments.length;
            if (length > 0) {
                vector = new Vector<String>(length);
                for (int i = 0; i < length; ++i) {
                    vector.addElement(selectedDocuments[i]);
                }
            }
        }
        return vector;
    }
    
    public void setRestrictToCategoryName(final String restrictToCategoryName) {
        if (this.viewPanel != null) {
            this.viewPanel.setRestrictToCategoryName(restrictToCategoryName);
        }
    }
    
    public void setTarget(final String target) {
        if (this.viewPanel != null) {
            this.viewPanel.setTarget(target);
        }
    }
    
    public void setDblClickTarget(final String dblClickTarget) {
        if (this.viewPanel != null) {
            this.viewPanel.setDblClickTarget(dblClickTarget);
        }
    }
    
    public void setBackgroundColor(final String s) {
        if (this.viewPanel != null) {
            this.viewPanel.setBackground(this.ConvertStringToColor(s, Color.white));
        }
    }
    
    public void setForegroundColor(final String s) {
        if (this.viewPanel != null) {
            this.viewPanel.setForeground(this.ConvertStringToColor(s, Color.black));
        }
    }
    
    private Color ConvertStringToColor(String substring, final Color color) {
        if (substring.startsWith("#")) {
            substring = substring.substring(1, 7);
        }
        Color color2;
        try {
            color2 = new Color(Integer.parseInt(substring, 16));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            color2 = color;
        }
        return color2;
    }
    
    public void start() {
        if (!this.sizeInitialized) {
            this.sizeInitialized = true;
            if (this.wasNoBorder) {
                this.viewPanel.setPanelStyle("NO_BORDER");
            }
        }
        else if (this.forceRead && this.restart && this.viewPanel != null) {
            this.restart = false;
            this.viewPanel.init(false);
        }
    }
    
    public void markSelectedDocumentsForDelete() {
        if (this.viewPanel != null) {
            this.viewPanel.markSelectedDocumentsForDelete();
        }
    }
    
    public void deleteMarkedDocuments() {
        if (this.viewPanel != null) {
            this.viewPanel.deleteMarkedDocuments(true);
        }
    }
    
    public void deleteSelectedDocuments(final boolean b) {
        if (this.viewPanel != null) {
            this.viewPanel.deleteSelectedDocuments(b, true);
        }
    }
    
    public void undeleteSelectedDocuments() {
        if (this.viewPanel != null) {
            this.viewPanel.undeleteSelectedDocuments(true);
        }
    }
    
    public void expandAll() {
        if (this.viewPanel != null) {
            this.viewPanel.expandAll();
        }
    }
    
    public void collapseAll() {
        if (this.viewPanel != null) {
            this.viewPanel.collapseAll();
        }
    }
    
    public void expandSelectedDocument() {
        if (this.viewPanel != null) {
            this.viewPanel.expandSelectedDocument();
        }
    }
    
    public void collapseSelectedDocument() {
        if (this.viewPanel != null) {
            this.viewPanel.collapseSelectedDocument();
        }
    }
    
    public void refresh() {
        if (this.viewPanel != null) {
            this.viewPanel.refresh(true, true, 1);
        }
    }
    
    public void moveSelectedDocumentsToFolder() {
        this.moveSelectedDocumentsToFolder(null);
    }
    
    public void moveSelectedDocumentsToFolder(final String s) {
        if (s == null || s.length() < 1) {
            this.doFolderSelectionDialog(true);
        }
        else if (this.viewPanel != null) {
            this.viewPanel.moveSelectedDocumentsToFolder(s);
        }
    }
    
    public void copySelectedDocumentsToFolder() {
        this.copySelectedDocumentsToFolder(null);
    }
    
    public void copySelectedDocumentsToFolder(final String s) {
        if (s == null || s.length() < 1) {
            this.doFolderSelectionDialog(false);
        }
        else if (this.viewPanel != null) {
            this.viewPanel.copySelectedDocumentsToFolder(s);
        }
    }
    
    private void doFolderSelectionDialog(final boolean b) {
        this.folderDialog.setHostName(this.hostName);
        this.folderDialog.setDatabaseName(this.dbName);
        this.folderDialog.setProtocol(this.getCodeBase().getProtocol());
        if (b) {
            final FolderSelectionDialog folderDialog = this.folderDialog;
            final FolderSelectionDialog folderDialog2 = this.folderDialog;
            folderDialog.setCommandType(1);
        }
        else {
            final FolderSelectionDialog folderDialog3 = this.folderDialog;
            final FolderSelectionDialog folderDialog4 = this.folderDialog;
            folderDialog3.setCommandType(2);
        }
        this.folderDialog.show();
    }
    
    public void removeSelectedDocumentsFromFolder() {
        if (this.viewPanel != null) {
            this.viewPanel.removeSelectedDocumentsFromFolder();
        }
    }
    
    public void dialogDismissed(final Dialog dialog) {
        final FolderSelectionDialog folderSelectionDialog = (FolderSelectionDialog)dialog;
        final int command = folderSelectionDialog.getCommand();
        final String selectedFolderUNID = folderSelectionDialog.getSelectedFolderUNID();
        if (this.viewPanel != null && selectedFolderUNID != null) {
            switch (command) {
                case 1: {
                    this.viewPanel.moveSelectedDocumentsToFolder(selectedFolderUNID);
                    break;
                }
                case 2: {
                    this.viewPanel.copySelectedDocumentsToFolder(selectedFolderUNID);
                    break;
                }
            }
        }
    }
}
