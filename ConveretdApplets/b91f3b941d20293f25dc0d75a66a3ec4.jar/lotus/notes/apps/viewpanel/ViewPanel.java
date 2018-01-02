// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewpanel;

import java.io.FilterInputStream;
import java.io.DataOutputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Enumeration;
import lotus.notes.components.StateChangeEvent;
import java.io.InputStream;
import java.io.DataInputStream;
import lotus.notes.components.NScrollbar;
import java.awt.Insets;
import java.text.ParseException;
import lotus.notes.components.MultiLineTextCell;
import java.util.StringTokenizer;
import lotus.notes.components.TwistieCell;
import lotus.notes.components.CellEntry;
import lotus.notes.components.ImageCell;
import lotus.notes.util.TreeList;
import lotus.notes.util.TreeListEntry;
import java.net.URL;
import netscape.security.PrivilegeManager;
import java.awt.Point;
import java.awt.Component;
import java.awt.Event;
import java.awt.Container;
import lotus.notes.apps.viewapplet.DefaultProperties;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.awt.Dimension;
import java.awt.MediaTracker;
import java.util.Hashtable;
import lotus.notes.components.LineEntry;
import java.awt.Font;
import java.util.Vector;
import java.awt.Color;
import java.util.Properties;
import java.applet.Applet;
import java.awt.Image;
import lotus.notes.components.StateChangeListener;
import lotus.notes.components.IrisScrollablePanel;

public class ViewPanel extends IrisScrollablePanel implements StateChangeListener, HeaderListener
{
    private String lastDocReadPos;
    private boolean lastDocWasRead;
    String currentDisplayedUNID;
    private String iconPath;
    private Image twistieCollapseImage;
    private Image twistieExpandImage;
    private Image checkImage;
    private Image uncheckImage;
    private Image trashImage;
    private Image backgroundImage;
    private int backgroundRepeat;
    private Applet applet;
    private HeaderPanel headerPanel;
    private Properties props;
    private String viewName;
    private String DBName;
    private String dataURL;
    private String hostName;
    private String protocol;
    public String startkey;
    public boolean getfocus;
    private int gutterColumnOffset;
    private Color backgroundColor;
    private Color foregroundColor;
    private int oldColumnResizeX;
    private int totalColumnWidth;
    private String target;
    private String doubleClickTarget;
    private Vector vColDesc;
    private int responseColXPos;
    private int responseColNumber;
    private int firstTotalColumnXPos;
    private int firstTotalColumn;
    private Header lastHeader;
    private GutterOutlineView viewComponent;
    private Font viewFont;
    private boolean bPreviewFrame;
    private int currentVScrollPos;
    private int currentHScrollPos;
    private int toplevelEntryCount;
    private ViewDesignInfo designInfo;
    public ViewLineRange visibleLineRange;
    private LineEntry lastparentLine;
    private Hashtable imageTable;
    private MediaTracker imageTracker;
    private int imageGroupID;
    private DisplayThread displayThread;
    private ViewColDesc[] categoryColDescs;
    private Dimension savedDimension;
    private String viewUNID;
    private String trashUNID;
    private static int READ_LINE_COUNT;
    private Hashtable selectedDocs;
    private Hashtable markedDocs;
    private CollapsedTable collapsedTable;
    private int sortColumnNumber;
    private boolean bShowOnlySelected;
    private boolean bInitialized;
    private boolean bViewOpened;
    private int expansionState;
    private boolean headerVisible;
    private boolean scrollbarsVisible;
    private boolean selectionMarginVisible;
    private boolean gotoBottom;
    private boolean restrictToCategory;
    private String restrictToCategoryName;
    private boolean bSimpleView;
    private String localeString;
    private static Locale fallbackLocale;
    private SimpleDateFormat dateParser;
    private SimpleDateFormat dateFormatter;
    private String dateOutputFormat;
    private static final String DATE_INPUT_FORMAT = "yyyyMMdd'T'kkmmss";
    private static final int DATE_INPUT_FORMAT_LENGTH;
    private DecimalFormat numberParser;
    private DecimalFormat numberFormatter;
    private DecimalFormat percentFormatter;
    private DecimalFormat currencyFormatter;
    private String defaultNegativePrefix;
    private String defaultNegativeSuffix;
    private static String NUMBER_INPUT_FORMAT;
    private String ImagePath;
    static final String CHECK_IMAGE_NAME = "vwicn082.gif";
    static final String TWISTIEC_IMAGE_NAME = "expand.gif";
    static final String TWISTIECB_IMAGE_NAME = "bexpand.gif";
    static final String TWISTIEX_IMAGE_NAME = "collapse.gif";
    static final String TRASH_IMAGE = "trash.gif";
    static final int SCROLL_DIRECTION_FORWARD = 1;
    static final int SCROLL_DIRECTION_BACK = 0;
    private static final int KEY_DELETE = 127;
    private static final int KEY_SPACE = 32;
    private static final int KEY_MACDELETE = 8;
    private static final int ICON_WIDTH = 16;
    private static final int TWISTIE_WIDTH = 16;
    private static final int DEFAULT_INTERLINE_SPACING = 2;
    private static final int GUTTER_WIDTH = 33;
    private static final int DEFAULT_RESPONSE_INDENT = 32;
    private static final int DEFAULT_CATEGORY_INDENT = 24;
    private static final int COLUMN_FUDGE = 25;
    private static final char LIST_SEP = '\u0001';
    private static final int DISPLAY_DELAY = 1000;
    public static final int SELECT_FIRST = 0;
    public static final int SELECT_CURRENT = 1;
    public static final int SELECT_LAST = 2;
    public static final int NONE = 0;
    public static final int EXPAND_ALL = 1;
    public static final int COLLAPSE_ALL = 2;
    private static final int NAVIGATE_CURRENT = 0;
    private static final int NAVIGATE_CHILD = 4;
    private static final int NAVIGATE_NEXT_PEER = 5;
    private static final int NAVIGATE_PREV_PEER = 6;
    private static final int NAVIGATE_PREV_MAIN = 13;
    private static final int NAVIGATE_NEXT = 1;
    private static final int NAVIGATE_PREV = 9;
    private static final int NAVIGATE_NEXT_EXPANDED = 15;
    private static final int NAVIGATE_PREV_EXPANDED = 16;
    private static final int NAVIGATE_ALL_DESCENDANTS = 17;
    private static final int NAVIGATE_CONTINUE = 32768;
    private static int MAX_SCROLL_POS;
    private static final boolean DEBUG = false;
    private static final boolean PROPERTIES_DEBUG = false;
    private static final boolean RANGE_DEBUG = false;
    private static final boolean SCROLL_VALUES_DEBUG = false;
    private static final boolean COMMAND_DEBUG = false;
    private static final boolean CLIENT_FORMATTING = false;
    private int defaultCollectionPos;
    private boolean bFirstLineInTree;
    private boolean bNeedsPriv;
    Graphics g;
    int lineHeight;
    Color oldForeground;
    boolean bResizing;
    private static final String[][] resortImageNames;
    private static Image[][] resortImages;
    
    public ViewPanel() {
        this.lastDocReadPos = null;
        this.lastDocWasRead = false;
        this.currentDisplayedUNID = null;
        this.iconPath = "icons";
        this.uncheckImage = null;
        this.backgroundImage = null;
        this.backgroundRepeat = 0;
        this.applet = null;
        this.headerPanel = null;
        this.props = null;
        this.viewName = null;
        this.DBName = null;
        this.dataURL = null;
        this.hostName = null;
        this.protocol = "http:";
        this.startkey = null;
        this.getfocus = false;
        this.gutterColumnOffset = 0;
        this.backgroundColor = Color.white;
        this.foregroundColor = Color.black;
        this.oldColumnResizeX = -1;
        this.totalColumnWidth = 0;
        this.target = null;
        this.doubleClickTarget = null;
        this.vColDesc = null;
        this.firstTotalColumnXPos = 0;
        this.firstTotalColumn = -1;
        this.lastHeader = null;
        this.viewComponent = null;
        this.viewFont = null;
        this.bPreviewFrame = false;
        this.currentVScrollPos = 0;
        this.currentHScrollPos = 0;
        this.toplevelEntryCount = 0;
        this.designInfo = null;
        this.lastparentLine = null;
        this.imageTable = new Hashtable();
        this.imageGroupID = 0;
        this.displayThread = null;
        this.categoryColDescs = null;
        this.savedDimension = new Dimension(0, 0);
        this.trashUNID = null;
        this.selectedDocs = new Hashtable();
        this.markedDocs = new Hashtable();
        this.collapsedTable = new CollapsedTable();
        this.sortColumnNumber = -1;
        this.bShowOnlySelected = false;
        this.bInitialized = false;
        this.bViewOpened = false;
        this.expansionState = 0;
        this.headerVisible = true;
        this.scrollbarsVisible = true;
        this.selectionMarginVisible = true;
        this.gotoBottom = false;
        this.restrictToCategory = false;
        this.restrictToCategoryName = null;
        this.bSimpleView = false;
        this.localeString = null;
        this.defaultNegativePrefix = "-";
        this.defaultNegativeSuffix = "";
        this.defaultCollectionPos = 1;
        this.bFirstLineInTree = false;
        this.bNeedsPriv = false;
        this.bResizing = false;
        this.setLayout(null);
        this.setBackground(this.backgroundColor);
        try {
            this.props = new DefaultProperties();
        }
        catch (Exception ex) {}
    }
    
    public void addNotify() {
        super.addNotify();
        final Container parent = this.getParent();
        if (parent != null && parent instanceof Applet) {
            this.applet = (Applet)parent;
        }
        else {
            this.applet = null;
        }
        if (this.getfocus) {
            this.requestFocus();
        }
    }
    
    public boolean handleEvent(final Event event) {
        boolean b = false;
        boolean b2 = false;
        try {
            final String property = System.getProperty("os.name");
            if (property != null) {
                b2 = (property.toLowerCase().indexOf("mac") != -1);
            }
        }
        catch (Exception ex) {}
        synchronized (this) {
            if (event.id == 401 || event.id == 403) {
                switch (event.key) {
                    case 127: {
                        this.markSelectedDocumentsForDelete();
                        b = true;
                        break;
                    }
                    case 8: {
                        if (b2 && event.modifiers == 8) {
                            this.markSelectedDocumentsForDelete();
                            b = true;
                            break;
                        }
                    }
                    case 1016: {
                        this.refresh(true, true, 1);
                        b = true;
                        break;
                    }
                    case 32: {
                        final ViewLine viewLine = (ViewLine)this.viewComponent.getSelectedEntry();
                        if (viewLine != null) {
                            this.markLineSelected(viewLine);
                        }
                        b = true;
                        break;
                    }
                    case 43: {
                        if (event.shiftDown()) {
                            this.expandAll();
                        }
                        else {
                            this.expandSelectedDocument();
                        }
                        b = true;
                        break;
                    }
                    case 45: {
                        if (event.shiftDown()) {
                            this.collapseAll();
                        }
                        else {
                            this.collapseSelectedDocument();
                        }
                        b = true;
                        break;
                    }
                    case 95: {
                        this.collapseAll();
                        b = true;
                        break;
                    }
                    case 1002: {
                        this.scrollPageUp();
                        this.updateVScrollValues();
                        this.isScrollBarStillNeeded();
                        this.viewComponent.repaint();
                        b = true;
                        break;
                    }
                    case 1003: {
                        this.scrollPageDown();
                        this.viewComponent.repaint();
                        this.updateVScrollValues();
                        b = true;
                        break;
                    }
                }
            }
            else if (event.target == super.vScroll) {
                switch (event.id) {
                    case 604: {
                        this.scrollPageDown();
                        this.viewComponent.repaint();
                        this.updateVScrollValues();
                        b = true;
                        break;
                    }
                    case 603: {
                        this.scrollPageUp();
                        this.updateVScrollValues();
                        this.isScrollBarStillNeeded();
                        this.viewComponent.repaint();
                        b = true;
                        break;
                    }
                }
            }
            return b || super.handleEvent(event);
        }
    }
    
    public void init(final boolean b) {
        this.lastDocWasRead = false;
        this.savedDimension.width = this.getParent().size().width;
        this.savedDimension.height = this.getParent().size().height;
        if (this.imageTracker == null) {
            this.imageTracker = new MediaTracker(this);
        }
        if (!b && this.isScrollbarsVisible()) {
            this.InitScrollBars();
        }
        if (this.viewComponent != null && !b) {
            this.remove(this.viewComponent);
            this.viewComponent = null;
        }
        if (!b) {
            this.add(this.headerPanel = new HeaderPanel());
            this.bInitialized = true;
            (this.viewComponent = new GutterOutlineView(this.size())).setPaintGutterLine(this.isSelectionMarginVisible());
            this.add(this.viewComponent);
            this.viewFont = new Font("Helvetica", 0, 14);
            this.viewComponent.setFont(this.viewFont);
        }
        else if (this.isHeaderVisible()) {
            this.headerPanel.show();
        }
        else {
            this.headerPanel.hide();
        }
        this.viewComponent.SetInterlineSpacing(2);
        this.viewComponent.setBackground(this.backgroundColor);
        this.insets();
        this.reshapeViewComponent(false);
        if (this.headerPanel != null && this.isHeaderVisible()) {
            this.initHeader();
        }
        this.viewComponent.show();
        this.viewComponent.repaint();
        if (!b) {
            this.bViewOpened = false;
        }
    }
    
    public void refresh(final boolean b, final boolean b2, final int n) {
        this.refresh(b, b2, n, null);
    }
    
    public void refresh(final boolean b, final boolean b2, final int n, final String s) {
        String s2 = "1";
        String getCollectionPos = null;
        int countLinesBetweenLines = 0;
        final int currentVScrollPos = this.currentVScrollPos;
        this.lastDocWasRead = false;
        final ViewLine viewLine = (ViewLine)this.viewComponent.getFirstVisibleEntry();
        if (viewLine != null) {
            getCollectionPos = viewLine.GetCollectionPos();
        }
        if (n == 1) {
            final ViewLine viewLine2 = (ViewLine)this.viewComponent.TraverseEntries(3, null, null, this.viewComponent.getSelectedEntry());
            if (viewLine2 != null) {
                s2 = viewLine2.GetCollectionPos();
                countLinesBetweenLines = this.visibleLineRange.countLinesBetweenLines((ViewLine)this.viewComponent.getFirstVisibleEntry(), viewLine2);
            }
            else if (getCollectionPos != null) {
                s2 = getCollectionPos;
            }
            else {
                s2 = "1";
            }
            if (this.expansionState == 2) {
                s2 = this.visibleLineRange.getPosAtLevel(s2, 1);
            }
        }
        this.visibleLineRange = new ViewLineRange();
        this.viewComponent.setSelectedEntry(null);
        this.clearTableValues(this.selectedDocs);
        if (this.trashUNID != null) {
            this.markedDocs.clear();
        }
        else {
            this.clearTableValues(this.markedDocs);
        }
        int n2 = ViewPanel.READ_LINE_COUNT;
        int n3 = 0;
        int n4 = 0;
        int n5;
        if (b2) {
            n5 = 15;
        }
        else if (this.expansionState == 1) {
            n5 = 1;
        }
        else {
            n5 = 5;
        }
        if (n == 1) {
            n4 = countLinesBetweenLines;
            if (b2) {
                n3 = 32784;
            }
            else if (this.expansionState == 1) {
                n3 = 32777;
            }
            else {
                n3 = 32774;
            }
        }
        else if (n == 2) {
            n2 = ViewPanel.READ_LINE_COUNT;
            n4 = -1;
            n5 = 32784;
            n3 = 32783;
        }
        int n6 = this.ReadLineRange(this.visibleLineRange, s2, n5, n2, n3, n4, null, false, this.expansionState == 1, s);
        if (n6 <= 0 && n == 1) {
            s2 = null;
            if (getCollectionPos != null) {
                n6 = this.ReadLineRange(this.visibleLineRange, getCollectionPos, n5, ViewPanel.READ_LINE_COUNT, 0, 0, null, false, this.expansionState == 1, s);
            }
            if (n6 <= 0) {
                n6 = this.ReadLineRange(this.visibleLineRange, "1", n5, ViewPanel.READ_LINE_COUNT, 0, 0, null, false, this.expansionState == 1, s);
            }
        }
        if (n == 2) {
            this.visibleLineRange.allEntriesRead = true;
            this.visibleLineRange.isEndLineRange = true;
        }
        this.viewComponent.setLineTree(this.visibleLineRange.getTreeList(), null);
        if (b) {
            if (n == 2) {
                if (this.expansionState == 1) {
                    this.viewComponent.setSelectedEntry((LineEntry)this.visibleLineRange.getTreeList().getTail());
                }
                else {
                    this.viewComponent.setSelectedEntry((LineEntry)this.visibleLineRange.getTreeList().getLast());
                }
            }
            else if (s2 == null) {
                this.viewComponent.setSelectedEntry(this.viewComponent.getFirstVisibleEntry());
            }
            else {
                final LineEntry entryByPos = this.visibleLineRange.findEntryByPos(s2);
                if (entryByPos != null) {
                    this.viewComponent.setSelectedEntry(entryByPos);
                }
                else {
                    this.viewComponent.setSelectedEntry(this.viewComponent.getFirstVisibleEntry());
                }
            }
            this.viewComponent.repaint();
        }
        if (n == 1) {
            final ViewLine viewLine3 = (ViewLine)this.viewComponent.getFirstVisibleEntry();
            if (viewLine3 != null) {
                this.currentVScrollPos = this.visibleLineRange.computeEstimatedScrollPosition(viewLine3.GetCollectionPos());
            }
            else {
                this.currentVScrollPos = 0;
            }
        }
        this.updateValues();
        if (n == 2) {
            this.currentVScrollPos = super.vScroll.getMaximum();
            super.vScroll.setValue(this.currentVScrollPos);
            final int n7 = n6 - super.vScroll.getPageIncrement();
            if (n7 > 0) {
                this.viewComponent.ScrollLineArray(1, n7);
            }
        }
    }
    
    private void open(final String viewName) {
        if (null == viewName) {
            return;
        }
        this.viewName = viewName;
        this.sortColumnNumber = -1;
        if (!this.bInitialized) {
            this.init(false);
        }
        if (this.applet != null && this.props != null) {
            this.applet.showStatus(this.props.getProperty("reading.design", "Reading View Design Information..."));
        }
        this.ReadDesignInfo();
        this.loadImages();
        if (this.backgroundImage != null) {
            this.viewComponent.setBackgroundImage(this.backgroundImage, this.backgroundRepeat);
        }
        if (this.designInfo.altrowColor != null) {
            this.viewComponent.altrowColor = this.designInfo.altrowColor;
        }
        this.viewComponent.hideMarginBorder = this.designInfo.hideMarginBorder;
        if (this.applet != null) {
            this.applet.showStatus(" ");
        }
        this.initColumnInfo();
        if (this.gotoBottom && this.startkey == null) {
            this.currentVScrollPos = ViewPanel.MAX_SCROLL_POS;
            this.refresh(true, true, 2, this.startkey);
        }
        else {
            this.refresh(this.bFirstLineInTree = true, true, 0, this.startkey);
        }
        if (this.headerPanel != null && this.isHeaderVisible()) {
            this.initHeader();
        }
        if (this.isVScrollbarNeeded()) {
            this.EnableVertScrollBar(true, this.viewComponent);
        }
        else {
            this.EnableVertScrollBar(false, this.viewComponent);
        }
        if (this.isHScrollbarNeeded()) {
            this.EnableHorzScrollBar(true, this.viewComponent);
        }
        else {
            this.EnableHorzScrollBar(false, this.viewComponent);
        }
        this.updateVScrollValues();
        this.updateHScrollValues();
        if (this.getfocus) {
            this.requestFocus();
        }
        this.bViewOpened = true;
    }
    
    private void initHeader() {
        if (super.vScroll.isShowing()) {
            final int width = super.vScroll.bounds().width;
        }
        if (super.direction == 0) {
            this.headerPanel.resize(this.bounds().width, this.headerPanel.preferredSize().height);
        }
        else {
            this.headerPanel.resize(this.bounds().width, this.headerPanel.preferredSize().height);
        }
        this.reshapeViewComponent(this.isHeaderVisible());
        this.invalidate();
        this.validate();
    }
    
    private boolean ReadDesignInfo() {
        if (this.bNeedsPriv) {
            try {
                PrivilegeManager.enablePrivilege("UniversalBrowserRead");
                PrivilegeManager.enablePrivilege("UniversalConnect");
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
        try {
            final URL constructURL = this.constructURL(this.dataURL + this.DBName + "/" + this.viewUNID + "?" + "ReadDesign");
            this.designInfo = new ViewDesignInfo();
            String language = "";
            try {
                language = this.getParent().getLocale().getLanguage();
            }
            catch (Exception ex3) {}
            new XMLViewDesignParser(constructURL.toString(), this.designInfo, language).parse();
            this.headerPanel.setDirection(this.designInfo.direction);
            this.headerPanel.setForeground(this.designInfo.headerColor);
            this.viewComponent.setDirection(this.designInfo.direction);
            this.setDirection(this.designInfo.direction);
            if (this.restrictToCategory) {
                int n = 0;
                for (int i = 0; i < this.designInfo.vColumnInfo.size(); ++i) {
                    final ViewColumnInfo viewColumnInfo = this.designInfo.vColumnInfo.elementAt(i);
                    if (viewColumnInfo != null) {
                        if (n != 0) {
                            final ViewColumnInfo viewColumnInfo2 = viewColumnInfo;
                            --viewColumnInfo2.colNumber;
                        }
                        if (viewColumnInfo.sortCategorize && n == 0) {
                            this.designInfo.vColumnInfo.removeElementAt(i);
                            final ViewDesignInfo designInfo = this.designInfo;
                            --designInfo.numColumns;
                            --i;
                            n = 1;
                        }
                    }
                }
            }
        }
        catch (Exception ex2) {
            System.out.println("An error occurred while reading design data: " + ex2);
            ex2.printStackTrace();
        }
        return true;
    }
    
    void openDocumentByUNID(final String s, final boolean b) {
        if (this.applet != null) {
            if (this.currentDisplayedUNID != s || !b) {
                if (b && this.target != null) {
                    if (this.displayThread != null) {
                        this.displayThread.stop();
                        this.displayThread = null;
                    }
                    (this.displayThread = new DisplayThread(this, s, 1000)).start();
                }
                else {
                    final String dblClickTarget = this.getDblClickTarget();
                    if (this.displayThread != null) {
                        this.displayThread.stop();
                        this.displayThread = null;
                    }
                    if (dblClickTarget == null) {
                        this.showDocumentByUNID(s, "_top");
                    }
                    else {
                        this.showDocumentByUNID(s, dblClickTarget);
                    }
                }
            }
            else if (this.displayThread != null) {
                this.displayThread.stop();
            }
        }
    }
    
    void showDocumentByUNID(final String s) {
        this.showDocumentByUNID(s, this.target);
    }
    
    void showDocumentByUNID(final String currentDisplayedUNID, final String s) {
        try {
            this.applet.getAppletContext().showDocument(this.constructURL(this.dataURL + this.DBName + "/" + this.viewUNID + "/" + currentDisplayedUNID + "?OpenDocument"), s);
            this.currentDisplayedUNID = currentDisplayedUNID;
        }
        catch (Exception ex) {
            System.out.println("An error occurred while trying to display a document: " + ex);
            ex.printStackTrace();
        }
    }
    
    public boolean isPreviewMode() {
        return this.target != null;
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.isShowing()) {
            if (!this.bInitialized || !this.bViewOpened) {
                this.open(this.viewName);
            }
            else {
                this.viewComponent.repaint();
            }
        }
    }
    
    private void initColumnInfo() {
        this.vColDesc = new Vector();
        this.responseColXPos = 0;
        this.responseColNumber = 0;
        int xPos = 0;
        this.totalColumnWidth = 0;
        if (this.designInfo.numCategories > 0) {
            this.categoryColDescs = new ViewColDesc[this.designInfo.numCategories];
        }
        if (this.isSelectionMarginVisible()) {
            final ViewColDesc viewColDesc = new ViewColDesc();
            viewColDesc.xPos = 0;
            viewColDesc.Width = 16;
            viewColDesc.bClip = true;
            this.vColDesc.addElement(viewColDesc);
            final ViewColDesc viewColDesc2 = new ViewColDesc();
            viewColDesc2.xPos = 16;
            viewColDesc2.Width = 16;
            this.totalColumnWidth += viewColDesc2.Width;
            viewColDesc2.bClip = true;
            this.vColDesc.addElement(viewColDesc2);
            xPos = 34;
            if (this.headerPanel != null) {
                this.headerPanel.addHeader(new Header(" ", 33, false, 0, 0, 0));
                this.totalColumnWidth += 33;
            }
        }
        try {
            int n = 0;
            for (int i = 0; i < this.designInfo.numColumns; ++i) {
                final ViewColumnInfo viewColumnInfo = this.designInfo.vColumnInfo.elementAt(i);
                final ViewColDesc viewColDesc3 = new ViewColDesc();
                viewColDesc3.xPos = xPos;
                viewColDesc3.bClip = true;
                viewColDesc3.ColTextFont = viewColumnInfo.columnFont;
                viewColDesc3.ColTextColor = viewColumnInfo.textColor;
                if (viewColumnInfo.columnTotal && this.firstTotalColumn == -1) {
                    this.firstTotalColumn = i;
                    this.firstTotalColumnXPos = viewColDesc3.xPos;
                }
                if (viewColumnInfo.response) {
                    this.vColDesc.addElement(viewColDesc3);
                    xPos += viewColDesc3.Width;
                    if (viewColumnInfo.resizable) {
                        xPos += this.headerPanel.hl.hGap;
                    }
                    this.headerPanel.addHeader(new Header(viewColDesc3.Width));
                    this.designInfo.showTwistiesForResponses = viewColumnInfo.twistie;
                    this.responseColNumber = i;
                    this.responseColXPos = viewColDesc3.xPos;
                    viewColDesc3.Width = this.viewComponent.size().width - viewColDesc3.xPos;
                    viewColDesc3.bClip = false;
                    this.totalColumnWidth += viewColDesc3.Width;
                }
                else if (viewColumnInfo.hidden) {
                    viewColDesc3.Width = 0;
                    this.vColDesc.addElement(viewColDesc3);
                }
                else {
                    if (i == this.designInfo.numColumns - 1 && this.designInfo.extendLastColumnWidth) {
                        viewColDesc3.Width = this.viewComponent.size().width - viewColDesc3.xPos;
                        viewColDesc3.isLastColumn = true;
                        if (viewColDesc3.Width < 0) {
                            viewColDesc3.Width = 0;
                        }
                    }
                    else {
                        viewColDesc3.Width = this.getFontMetrics(viewColumnInfo.columnFont).charWidth('M') * viewColumnInfo.width / 8;
                        if (viewColumnInfo.resortAscending || viewColumnInfo.resortDescending || viewColumnInfo.resortToView) {
                            final ViewColDesc viewColDesc4 = viewColDesc3;
                            viewColDesc4.Width += 25;
                        }
                    }
                    this.vColDesc.addElement(viewColDesc3);
                    xPos += viewColDesc3.Width;
                    if (viewColumnInfo.resizable) {
                        xPos += this.headerPanel.hl.hGap;
                    }
                    int n2 = 0;
                    if (viewColumnInfo.resortAscending && viewColumnInfo.resortDescending) {
                        n2 = 3;
                    }
                    else {
                        if (viewColumnInfo.resortAscending) {
                            n2 = 1;
                        }
                        if (viewColumnInfo.resortDescending) {
                            n2 = 2;
                        }
                        if (viewColumnInfo.resortToView) {
                            n2 = 4;
                        }
                    }
                    if (this.headerPanel != null) {
                        final Header lastHeader = new Header(viewColumnInfo, viewColDesc3.Width, i + this.gutterColumnOffset);
                        if (n2 != 0) {
                            try {
                                lastHeader.setResortImages(this.loadImagesForResortType(n2));
                            }
                            catch (Exception ex) {
                                System.out.println("An error occurred while loading resort images " + ex);
                            }
                        }
                        lastHeader.setDirection(viewColumnInfo.headerReadingOrder);
                        this.headerPanel.addHeader(lastHeader);
                        this.totalColumnWidth += viewColDesc3.Width;
                        this.lastHeader = lastHeader;
                        if (!lastHeader.isFiller()) {
                            lastHeader.addHeaderListener(this);
                            lastHeader.setFont(viewColumnInfo.headerFont);
                            lastHeader.setForeground(viewColumnInfo.headerTextColor);
                        }
                    }
                    if (viewColumnInfo.sortCategorize) {
                        if (this.firstTotalColumnXPos == 0 || this.firstTotalColumnXPos < viewColDesc3.xPos) {
                            viewColDesc3.Width = this.viewComponent.size().width - viewColDesc3.xPos;
                        }
                        else {
                            viewColDesc3.Width = this.firstTotalColumnXPos - viewColDesc3.xPos;
                        }
                        viewColDesc3.isCategory = true;
                        this.categoryColDescs[n++] = viewColDesc3;
                    }
                }
            }
            if (!this.designInfo.extendLastColumnWidth) {
                if (this.viewComponent.size().width - this.totalColumnWidth < 0) {}
                this.headerPanel.addHeader(this.lastHeader = new Header(this.viewComponent.size().width - this.totalColumnWidth));
                this.lastHeader.addHeaderListener(this);
                this.lastHeader.setDirection(this.designInfo.direction);
            }
            this.viewComponent.AddColumnDescVector(this.vColDesc);
            final Vector<ViewColDesc> vector = new Vector<ViewColDesc>(1);
            final ViewColDesc viewColDesc5 = new ViewColDesc();
            viewColDesc5.xPos = 32;
            viewColDesc5.Width = this.viewComponent.size().width;
            viewColDesc5.isCategory = true;
            viewColDesc5.ColTextFont = new Font("Helvetica", 1, 14);
            vector.addElement(viewColDesc5);
            this.viewComponent.AddColumnDescVector(vector);
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    private int ReadLineRange(final ViewLineRange viewLineRange, final String s, final int n, final int n2, final int n3, final int n4, final LineEntry lineEntry, final boolean b, final boolean b2) {
        return this.ReadLineRange(viewLineRange, s, n, n2, n3, n4, lineEntry, b, b2, null);
    }
    
    private int ReadLineRange(final ViewLineRange range, final String s, final int n, final int n2, final int n3, final int n4, LineEntry lineEntry, final boolean b, final boolean b2, final String s2) {
        int n5 = 0;
        String position = s;
        final String startPos = range.getStartPos();
        final boolean b3 = lineEntry != null && n3 == 4;
        final int n6 = n & 0xFFFF7FFF;
        boolean b4 = n6 == 9 || n6 == 6 || n6 == 16;
        if (n3 == 32783 && n4 == -1) {
            b4 = false;
        }
        if (startPos != null) {
            Integer.parseInt(range.getPosAtLevel(startPos, 1));
        }
        final Vector readAndParseViewEntries = this.ReadAndParseViewEntries(s, null, n, n2, n3, n4, s2);
        if (null == readAndParseViewEntries || this.toplevelEntryCount == 0) {
            return 0;
        }
        int n7 = readAndParseViewEntries.size();
        if (n7 <= 0) {
            return n7;
        }
        if (!b4 && !b3 && n7 < n2) {
            range.allEntriesRead = true;
            range.isEndLineRange = true;
        }
        if (b3 && n7 < n2) {
            ((ViewLine)lineEntry).SetChildCount(((ViewLine)lineEntry).GetChildCount() - (n2 - n7));
        }
        try {
            if (b3 && readAndParseViewEntries.size() > 0) {
                this.ValidateViewEntries(s, readAndParseViewEntries);
                if (readAndParseViewEntries.size() != ((ViewLine)lineEntry).GetChildCount()) {
                    ((ViewLine)lineEntry).SetChildCount(readAndParseViewEntries.size());
                    n7 = readAndParseViewEntries.size();
                }
            }
        }
        catch (Exception ex) {}
        TreeListEntry treeListEntry = null;
        if (null == lineEntry) {
            final String computeParentPos = range.computeParentPos(s);
            if (null != computeParentPos) {
                lineEntry = range.findEntryByPos(computeParentPos);
            }
        }
        final TreeList treeList = range.getTreeList();
        final ViewLine viewLine = (ViewLine)treeList.getTail();
        if (b) {
            treeListEntry = treeList.getFirst();
        }
        int n8;
        int n9;
        int n10;
        if (b4) {
            n8 = n7 - 1;
            n9 = -1;
            n10 = -1;
        }
        else {
            n8 = 0;
            n9 = n7;
            n10 = 1;
        }
        int i = n8;
        int n11 = 1;
        while (i != n9) {
            final ViewEntriesLine viewEntriesLine = readAndParseViewEntries.elementAt(i);
            if (b && startPos != null && viewEntriesLine.Position.equals(startPos)) {
                break;
            }
            final ViewLine formatLine = this.formatLine(viewEntriesLine, b3);
            if (formatLine != null) {
                formatLine.range = range;
                String s3 = range.computeParentPos(formatLine.GetCollectionPos());
                lineEntry = null;
                while (s3 != null) {
                    lineEntry = range.findEntryByPos(s3);
                    if (lineEntry != null) {
                        break;
                    }
                    s3 = range.computeParentPos(s3);
                }
                if (lineEntry != null) {
                    if (n11 != 0 && treeList.getNumberOfEntries() > 0) {
                        if (!formatLine.GetCollectionPos().equals(this.visibleLineRange.getEndPos())) {
                            treeList.AddEntry(formatLine, lineEntry);
                        }
                    }
                    else {
                        treeList.AddEntry(formatLine, lineEntry);
                    }
                }
                else {
                    if (b) {
                        if (n11 != 0 && treeList.getNumberOfEntries() > 0) {
                            if (!formatLine.GetCollectionPos().equals(this.visibleLineRange.getEndPos())) {
                                treeList.InsertEntry(formatLine, treeListEntry);
                            }
                        }
                        else {
                            treeList.InsertEntry(formatLine, treeListEntry);
                        }
                    }
                    else if (n11 != 0 && treeList.getNumberOfEntries() > 0) {
                        if (!formatLine.GetCollectionPos().equals(this.visibleLineRange.getEndPos())) {
                            treeList.AddEntry(formatLine, null);
                        }
                    }
                    else {
                        treeList.AddEntry(formatLine, null);
                    }
                    if (this.bFirstLineInTree && this.currentVScrollPos == 0 && this.startkey == null) {
                        this.defaultCollectionPos = formatLine.GetCollectionPosAsInt();
                        formatLine.setFirstEntry(true);
                        this.bFirstLineInTree = false;
                    }
                }
                position = viewEntriesLine.Position;
                ++n5;
            }
            i += n10;
            n11 = 0;
        }
        range.setStartPos(((ViewLine)treeList.getFirst()).GetCollectionPos());
        if (b2 && !b) {
            range.setEndPos(position);
        }
        else {
            ViewLine viewLine2 = (ViewLine)TreeList.findMostDescendedChild(treeList.getLast());
            if (viewLine2 == null) {
                viewLine2 = (ViewLine)treeList.getLast();
            }
            range.setEndPos(viewLine2.GetCollectionPos());
        }
        if (range.allEntriesRead) {
            final ViewLine viewLine3 = (ViewLine)treeList.getTail();
            if (viewLine != null && viewLine != viewLine3) {
                viewLine.setLastEntry(false);
            }
            viewLine3.setLastEntry(true);
        }
        return n5;
    }
    
    private int ReadChildLines(final ViewLine viewLine, final ViewLineRange viewLineRange) {
        int readLineRange = 0;
        if (!viewLine.wasFullyExpanded) {
            readLineRange = this.ReadLineRange(viewLineRange, viewLine.GetCollectionPos(), 5, viewLine.GetChildCount(), 4, 1, viewLine, true, false);
            viewLine.wasFullyExpanded = true;
        }
        return readLineRange;
    }
    
    private ViewLine formatLine(final ViewEntriesLine viewEntriesLine, final boolean b) {
        final ViewLine viewLine = new ViewLine(this, null, this.viewComponent, null);
        final boolean b2 = viewEntriesLine.children > 0;
        final int indentLevel = viewEntriesLine.indentLevel;
        viewLine.UNID = viewEntriesLine.UNID;
        viewLine.setDirection(super.direction);
        if (viewLine.UNID == null) {
            viewLine.UNID = viewEntriesLine.noteID;
        }
        viewLine.collectionPos = viewEntriesLine.Position;
        viewLine.children = viewEntriesLine.children;
        viewLine.isCategoryTotal = viewEntriesLine.isCategoryTotal;
        viewLine.isDocument = !viewLine.isCategoryTotal;
        if (b2) {
            if (this.collapsedTable.isLineCollapsed(viewLine.UNID)) {
                viewLine.Collapse();
                viewLine.wasFullyExpanded = false;
            }
            else {
                viewLine.Expand();
                viewLine.wasFullyExpanded = true;
            }
        }
        viewLine.setBackgroundColor(null);
        final Vector getColumnDescVector = this.viewComponent.GetColumnDescVector(0);
        if (this.isSelectionMarginVisible()) {
            final ViewColDesc colInfo = getColumnDescVector.elementAt(0);
            final ImageCell imageCell = new ImageCell(this.trashImage, null, true);
            imageCell.ColInfo = colInfo;
            imageCell.vAlignment = 3;
            imageCell.bIncludeInSelection = false;
            imageCell.setDirection(this.designInfo.direction);
            imageCell.bgColor = this.getBackground();
            if (this.markedDocs.containsKey(viewLine.UNID)) {
                imageCell.ShowImage();
                this.markedDocs.put(viewLine.UNID, viewLine);
            }
            else {
                imageCell.HideImage();
            }
            imageCell.addStateChangeListener(this);
            viewLine.AddCell(imageCell);
            final ViewColDesc colInfo2 = getColumnDescVector.elementAt(1);
            final ImageCell imageCell2 = new ImageCell(this.checkImage, this.uncheckImage, true);
            imageCell2.ColInfo = colInfo2;
            imageCell2.vAlignment = 3;
            imageCell2.bIncludeInSelection = false;
            imageCell2.setDirection(this.designInfo.direction);
            imageCell2.bgColor = this.getBackground();
            if (this.selectedDocs.containsKey(viewLine.UNID)) {
                imageCell2.ShowImage();
                this.selectedDocs.put(viewLine.UNID, viewLine);
            }
            else {
                imageCell2.HideImage();
            }
            imageCell2.addStateChangeListener(this);
            viewLine.AddCell(imageCell2);
        }
        if (viewEntriesLine.isMarkedForDelete) {
            this.markedDocs.put(viewLine.UNID, viewLine);
            if (this.isSelectionMarginVisible()) {
                ((ImageCell)viewLine.GetCellAtIndex(0)).SetSelected(true);
            }
        }
        try {
            final String unid = viewEntriesLine.UNID;
            for (int size = viewEntriesLine.vColumn.size(), i = 0; i < size; ++i) {
                final ViewEntriesColumn viewEntriesColumn = viewEntriesLine.vColumn.elementAt(i);
                final ViewColumnInfo columnDesign = this.findColumnDesign(viewEntriesColumn.columnNumber);
                if (null != columnDesign) {
                    if (viewEntriesColumn.isCategory) {
                        viewLine.isDocument = false;
                        if (viewEntriesColumn.dataValue == null && this.props != null) {
                            viewEntriesColumn.dataValue = this.props.getProperty("not.categorized", "(Not Categorized)");
                        }
                    }
                    if (null != viewEntriesColumn.dataValue) {
                        if (!columnDesign.hidden) {
                            if (!columnDesign.response || viewEntriesLine.indentLevel != 0) {
                                int n;
                                if (indentLevel > 0 && (columnDesign.response || columnDesign.sortCategorize)) {
                                    n = indentLevel * 32;
                                }
                                else {
                                    n = 0;
                                }
                                if (b2 && columnDesign.twistie) {
                                    final Image twistieExpandImage = this.twistieExpandImage;
                                    final Image twistieCollapseImage = this.twistieCollapseImage;
                                    TwistieCell twistieCell;
                                    if (columnDesign.twistieimage != null) {
                                        final Image loadImage = this.loadImage(false, true, columnDesign.twistieimage);
                                        twistieCell = new TwistieCell(loadImage, loadImage, viewLine.IsCollapsed());
                                        twistieCell.setWellInfo(this, loadImage, 2, 1);
                                        twistieCell.isCustomTwistie = true;
                                    }
                                    else {
                                        twistieCell = new TwistieCell(twistieExpandImage, twistieCollapseImage, viewLine.IsCollapsed());
                                    }
                                    twistieCell.addStateChangeListener(this);
                                    twistieCell.ColInfo = getColumnDescVector.elementAt(viewEntriesColumn.columnNumber + this.gutterColumnOffset);
                                    twistieCell.padding = n;
                                    twistieCell.setDirection(super.direction);
                                    viewLine.AddCell(twistieCell);
                                }
                                CellEntry cellEntry;
                                if (columnDesign.isIcon && viewEntriesColumn.dataValue != null) {
                                    cellEntry = this.buildIconCell(viewEntriesColumn);
                                }
                                else if (viewEntriesColumn.dataValue != null) {
                                    cellEntry = this.createFormattedTextCell(viewEntriesColumn, columnDesign, viewLine.isDocument, viewLine.isCategoryTotal);
                                }
                                else {
                                    cellEntry = new ImageCell();
                                    cellEntry.bHandleMouseEvents = false;
                                }
                                cellEntry.setReadingOrder(columnDesign.readingOrder);
                                cellEntry.setDirection(this.designInfo.direction);
                                cellEntry.fgColor = viewEntriesColumn.customTextColor;
                                cellEntry.bgColor = viewEntriesColumn.customBGColor;
                                if (cellEntry != null) {
                                    cellEntry.ColInfo = getColumnDescVector.elementAt(viewEntriesColumn.columnNumber + this.gutterColumnOffset);
                                    cellEntry.padding = n;
                                    if (columnDesign.twistie) {
                                        final CellEntry cellEntry2 = cellEntry;
                                        cellEntry2.padding += 16;
                                    }
                                    if (columnDesign.sortCategorize || viewEntriesLine.isConflict) {
                                        cellEntry.bUseAllWidth = true;
                                    }
                                    if (viewEntriesLine.isConflict) {
                                        cellEntry.ColInfo = this.viewComponent.GetColumnDescVector(1).elementAt(0);
                                        cellEntry.padding = 32;
                                    }
                                    if (viewEntriesLine.isConflict) {
                                        cellEntry.hAlignment = 0;
                                    }
                                    else {
                                        cellEntry.hAlignment = columnDesign.alignment;
                                    }
                                    viewLine.AddCell(cellEntry);
                                }
                                if (viewEntriesLine.isConflict) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return viewLine;
    }
    
    private ImageCell buildIconCell(final ViewEntriesColumn viewEntriesColumn) {
        ImageCell imageCell;
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(viewEntriesColumn.dataValue, new String(new char[] { '\u0001' }));
            final int countTokens = stringTokenizer.countTokens();
            boolean isCustomIcon = countTokens > 1;
            final Image[] array = new Image[countTokens];
            int n = 0;
            do {
                final String nextToken = stringTokenizer.nextToken();
                Image image;
                if (!this.isInteger(nextToken)) {
                    image = this.loadImage(false, true, nextToken);
                    isCustomIcon = true;
                }
                else {
                    image = this.loadIcon(nextToken);
                }
                array[n] = image;
                ++n;
            } while (stringTokenizer.hasMoreElements());
            imageCell = new ImageCell(array);
            imageCell.isCustomIcon = isCustomIcon;
            imageCell.bHandleMouseEvents = false;
        }
        catch (Exception ex) {
            imageCell = new ImageCell();
        }
        return imageCell;
    }
    
    MultiLineTextCell createFormattedTextCell(final ViewEntriesColumn viewEntriesColumn, final ViewColumnInfo viewColumnInfo, final boolean b, final boolean b2) throws ParseException {
        MultiLineTextCell multiLineTextCell = null;
        if (viewEntriesColumn != null && viewColumnInfo != null) {
            multiLineTextCell = new MultiLineTextCell(viewEntriesColumn.dataValue);
            multiLineTextCell.interlineSpacing = 2;
            multiLineTextCell.maxRows = this.designInfo.rowsPerLine;
            if (viewColumnInfo.columnTotal && !b) {
                multiLineTextCell.fgColor = this.designInfo.totalsColor;
                if (b2) {
                    multiLineTextCell.drawLine = true;
                }
            }
        }
        return multiLineTextCell;
    }
    
    ViewColumnInfo findColumnDesign(final int n) {
        for (int size = this.designInfo.vColumnInfo.size(), i = 0; i < size; ++i) {
            final ViewColumnInfo viewColumnInfo = this.designInfo.vColumnInfo.elementAt(i);
            if (viewColumnInfo.colNumber == n) {
                return viewColumnInfo;
            }
        }
        return null;
    }
    
    String formatNumber(final String s, final DecimalFormat decimalFormat, final boolean b) throws ParseException {
        return null;
    }
    
    String formatDate(final String s) throws ParseException {
        return null;
    }
    
    Vector parseMultiValueString(final String s) {
        return new Vector();
    }
    
    String convertToScientific(final Number n, final DecimalFormat decimalFormat) {
        return null;
    }
    
    String modifyDateFormat(final String s) {
        return null;
    }
    
    DecimalFormat getNumberFormatter(final boolean b, final boolean b2) {
        return null;
    }
    
    public void reshape(final int n, final int n2, final int width, final int height) {
        super.reshape(n, n2, super.PanelDimension.width = width, super.PanelDimension.height = height);
    }
    
    public void resize(final int n, final int n2) {
        if (this.savedDimension.width == n && this.savedDimension.height == n2) {
            return;
        }
        final int n3 = n - this.savedDimension.width;
        this.savedDimension.width = n;
        this.savedDimension.height = n2;
        super.resize(super.PanelDimension.width = n, super.PanelDimension.height = n2);
        this.init(true);
        if (this.lastHeader != null) {
            final int width = this.lastHeader.getWidth();
            if (width + n3 >= 0) {
                this.lastHeader.setWidth(width + n3);
            }
        }
        if (this.isVScrollbarNeeded()) {
            this.EnableVertScrollBar(true, this.viewComponent);
        }
        else {
            this.EnableVertScrollBar(false, this.viewComponent);
        }
        if (this.isHScrollbarNeeded()) {
            this.EnableHorzScrollBar(true, this.viewComponent);
        }
        else {
            this.EnableHorzScrollBar(false, this.viewComponent);
        }
        this.updateVScrollValues();
        this.updateHScrollValues();
    }
    
    private void reshapeViewComponent(final boolean b) {
        final Insets insets = this.insets();
        final int n = super.vScroll.isVisible() ? super.vScroll.size().width : 0;
        final int n2 = super.hScroll.isVisible() ? super.hScroll.size().height : 0;
        final int n3 = b ? (this.headerPanel.bounds().y + this.headerPanel.bounds().height) : insets.top;
        final int n4 = b ? this.headerPanel.bounds().height : 0;
        int left = insets.left;
        if (super.direction == 1) {
            left += n;
        }
        this.viewComponent.reshape(left, n3, this.size().width - (insets.left + insets.right + n), this.size().height - (insets.top + insets.bottom + n2 + n4));
    }
    
    public boolean ProcessKeyDown() {
        final LineEntry selectedEntry = this.viewComponent.getSelectedEntry();
        if (selectedEntry != null) {
            ViewLine selectedEntry2 = (ViewLine)TreeList.Traverse(selectedEntry, 0, TreeList.FULL);
            if (selectedEntry2 != null) {
                final ViewLine viewLine = selectedEntry2;
                final GutterOutlineView viewComponent = this.viewComponent;
                final GutterOutlineView viewComponent2 = this.viewComponent;
                if (viewLine != viewComponent.TraverseEntries(3, null, null, selectedEntry2)) {
                    if (selectedEntry2.getParent() == selectedEntry && selectedEntry.IsCollapsed()) {
                        selectedEntry2 = (ViewLine)TreeList.Traverse(selectedEntry, 0, TreeList.CURRENT);
                    }
                    else {
                        super.vScroll.setValue(super.vScroll.getValue() + 1);
                        this.ProcessScrollAbsolute(super.vScroll);
                    }
                }
                this.viewComponent.setSelectedEntry(selectedEntry2);
                if (this.isPreviewMode() && selectedEntry2.getUNID() != null && selectedEntry2.isDocument) {
                    this.openDocumentByUNID(selectedEntry2.getUNID(), true);
                }
            }
        }
        return true;
    }
    
    public boolean ProcessKeyUp() {
        final LineEntry selectedEntry = this.viewComponent.getSelectedEntry();
        final ViewLine viewLine = (ViewLine)this.viewComponent.getFirstVisibleEntry();
        if (selectedEntry != null) {
            ViewLine selectedEntry2 = (ViewLine)TreeList.TraverseBackwardFull(selectedEntry);
            if (selectedEntry2 != null) {
                selectedEntry2 = (ViewLine)this.VisibleExpanded(selectedEntry2);
                if (selectedEntry == viewLine) {
                    super.vScroll.setValue(super.vScroll.getValue() - 1);
                    this.ProcessScrollAbsolute(super.vScroll);
                }
            }
            else if (viewLine == this.visibleLineRange.getTreeList().getFirst() && !viewLine.isFirstEntry()) {
                try {
                    this.extendLineRange(this.visibleLineRange, 0, super.vScroll.getPageIncrement() * 2, false);
                    this.viewComponent.ScrollLineArray(0, 1);
                    selectedEntry2 = (ViewLine)this.viewComponent.getFirstVisibleEntry();
                }
                catch (Exception ex) {}
            }
            if (selectedEntry2 != null) {
                this.viewComponent.setSelectedEntry(selectedEntry2);
                if (this.isPreviewMode() && selectedEntry2.getUNID() != null && selectedEntry2.isDocument) {
                    this.openDocumentByUNID(selectedEntry2.getUNID(), true);
                }
            }
        }
        return true;
    }
    
    private LineEntry VisibleExpanded(final LineEntry lineEntry) {
        LineEntry lineEntry2 = lineEntry;
        for (LineEntry lineEntry3 = (LineEntry)lineEntry.getParent(); lineEntry3 != null; lineEntry3 = (LineEntry)lineEntry3.getParent()) {
            if (!(!lineEntry3.IsCollapsed())) {
                lineEntry2 = lineEntry3;
            }
        }
        return lineEntry2;
    }
    
    private void loadImages() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        if (super.direction == 1) {
            this.twistieCollapseImage = this.loadImage(true, "bexpand.gif");
        }
        else {
            this.twistieCollapseImage = this.loadImage(true, "expand.gif");
        }
        this.twistieExpandImage = this.loadImage(true, "collapse.gif");
        this.trashImage = this.loadImage(true, "trash.gif");
        this.checkImage = this.loadImage(true, "vwicn082.gif");
        if (this.designInfo.bgImage != null) {
            this.backgroundImage = this.loadImage(false, this.dataURL + "/" + this.designInfo.bgImage);
            this.backgroundRepeat = this.designInfo.bgImageRepeat;
        }
    }
    
    private Image loadImage(final boolean b, final String s) {
        return this.loadImage(b, false, s);
    }
    
    private Image loadImage(final boolean b, final boolean b2, final String s) {
        if (this.bNeedsPriv) {
            try {
                PrivilegeManager.enablePrivilege("UniversalBrowserRead");
                PrivilegeManager.enablePrivilege("UniversalConnect");
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
        Image image = null;
        if (s != null) {
            try {
                URL url;
                if (b) {
                    url = new URL(this.dataURL + this.iconPath + "/" + s);
                }
                else if (b2 && !s.startsWith(this.dataURL)) {
                    url = new URL(this.dataURL + s);
                }
                else {
                    url = new URL(s);
                }
                image = this.getToolkit().getImage(url);
                this.imageTracker.addImage(image, this.imageGroupID);
                this.imageTracker.waitForID(this.imageGroupID++);
            }
            catch (Exception ex2) {}
        }
        return image;
    }
    
    public boolean ProcessScrollAbsolute(final NScrollbar nScrollbar) {
        if (nScrollbar == super.vScroll) {
            return this.ProcessVScrollAbsolute(nScrollbar);
        }
        return nScrollbar == super.hScroll && this.ProcessHScrollAbsolute(nScrollbar);
    }
    
    private boolean ProcessHScrollAbsolute(final NScrollbar nScrollbar) {
        final int value = nScrollbar.getValue();
        this.viewComponent.setHScrollOffset(value * -1);
        ((HeaderLayout)this.headerPanel.getLayout()).setXOffset(value * -1);
        for (int i = 0; i <= this.vColDesc.size() - 1; ++i) {
            ((ViewColDesc)this.vColDesc.elementAt(i)).hOffset = value;
        }
        this.viewComponent.repaint();
        this.headerPanel.invalidate();
        this.headerPanel.layout();
        this.headerPanel.repaint();
        return true;
    }
    
    private synchronized boolean ProcessVScrollAbsolute(final NScrollbar nScrollbar) {
        final int value = nScrollbar.getValue();
        final int n = value - this.currentVScrollPos;
        if (value <= this.currentVScrollPos) {
            if (value < this.currentVScrollPos) {}
        }
        if (n == 0) {
            return true;
        }
        if ((this.currentVScrollPos = value) == 0) {
            this.scrollToTop();
            this.isScrollBarStillNeeded();
        }
        else if (value >= super.vScroll.getMaximum()) {
            this.scrollToEnd();
        }
        else if (n == 1) {
            this.scrollForward();
        }
        else if (n == -1) {
            this.scrollBackward();
        }
        else {
            this.scrollAbsolute(value);
        }
        this.updateVScrollValues();
        this.viewComponent.repaint();
        this.isScrollBarStillNeeded();
        return true;
    }
    
    private void scrollForward() {
        final ViewLine viewLine = (ViewLine)this.viewComponent.getLastVisibleEntry();
        if (viewLine.isLastEntry()) {
            return;
        }
        if (viewLine == this.visibleLineRange.getTreeList().getTail()) {
            this.extendLineRange(this.visibleLineRange, 1, super.vScroll.getPageIncrement() * 2, false);
        }
        this.viewComponent.ScrollLineArray(1, 1);
    }
    
    private void scrollBackward() {
        final ViewLine viewLine = (ViewLine)this.viewComponent.getFirstVisibleEntry();
        if (viewLine.isFirstEntry()) {
            return;
        }
        if (viewLine == this.visibleLineRange.getTreeList().getFirst()) {
            this.extendLineRange(this.visibleLineRange, 0, super.vScroll.getPageIncrement() * 2, false);
        }
        this.viewComponent.ScrollLineArray(0, 1);
    }
    
    private void scrollToTop() {
        final ViewLine viewLine = (ViewLine)this.visibleLineRange.getTreeList().getFirst();
        if (viewLine.isFirstEntry()) {
            this.viewComponent.setLineTree(this.visibleLineRange.getTreeList(), viewLine);
            this.refresh(true, true, 1);
        }
        else {
            this.visibleLineRange = this.createLineRange(0, false);
            this.viewComponent.setLineTree(this.visibleLineRange.getTreeList(), null);
            this.refresh(true, true, 1);
        }
    }
    
    private void scrollToEnd() {
        if (((ViewLine)this.viewComponent.getLastVisibleEntry()).isLastEntry()) {
            return;
        }
        if (!((ViewLine)this.visibleLineRange.getTreeList().getTail()).isLastEntry()) {
            this.visibleLineRange = this.createLineRange(-1, true);
            this.viewComponent.setLineTree(this.visibleLineRange.getTreeList(), null);
        }
        for (ViewLine viewLine = (ViewLine)this.viewComponent.getLastVisibleEntry(); viewLine != null && !viewLine.isLastEntry(); viewLine = (ViewLine)this.viewComponent.getLastVisibleEntry()) {
            this.viewComponent.ScrollLineArray(1, 1, false);
        }
        this.viewComponent.repaint();
    }
    
    private void scrollAbsolute(final int n) {
        final ViewLineRange lineRange = this.createLineRange(n, false);
        if (lineRange.getTreeList().getNumberOfEntries() > 0) {
            this.visibleLineRange = lineRange;
        }
        this.viewComponent.setLineTree(this.visibleLineRange.getTreeList(), null);
    }
    
    private void scrollPageUp() {
        final ViewLine viewLine = (ViewLine)this.viewComponent.getFirstVisibleEntry();
        if (viewLine.isFirstEntry() && this.isVScrollbarNeeded()) {
            return;
        }
        final int pageIncrement = super.vScroll.getPageIncrement();
        final ViewLine viewLine2 = (ViewLine)this.visibleLineRange.getTreeList().getFirst();
        if (!viewLine2.isFirstEntry() && this.visibleLineRange.countLinesBetweenLines(viewLine2, viewLine) < pageIncrement) {
            this.extendLineRange(this.visibleLineRange, 0, pageIncrement * 2, false);
        }
        this.viewComponent.ScrollLineArray(0, pageIncrement);
    }
    
    private void scrollPageDown() {
        final ViewLine viewLine = (ViewLine)this.viewComponent.getLastVisibleEntry();
        if (viewLine.isLastEntry()) {
            return;
        }
        final int pageIncrement = super.vScroll.getPageIncrement();
        final ViewLine viewLine2 = (ViewLine)this.visibleLineRange.getTreeList().getTail();
        int n = this.visibleLineRange.countLinesBetweenLines(viewLine, viewLine2);
        if (!viewLine2.isLastEntry()) {
            if (n < pageIncrement) {
                this.extendLineRange(this.visibleLineRange, 1, pageIncrement * 2, false);
            }
            n = this.visibleLineRange.countLinesBetweenLines(viewLine, (ViewLine)this.visibleLineRange.getTreeList().getTail());
        }
        final int min = Math.min(n, pageIncrement);
        if (min > 0) {
            this.viewComponent.ScrollLineArray(1, min);
        }
    }
    
    private void extendLineRange(final ViewLineRange viewLineRange, final int n, int n2, final boolean b) {
        if (1 == n) {
            if (this.expansionState != 1) {
                final ViewLine viewLine = (ViewLine)viewLineRange.getLastExpandedDescendant((LineEntry)viewLineRange.getTreeList().getLast());
                if (null != viewLine) {
                    int n3 = Math.min(viewLine.GetChildCount() - viewLineRange.getLineChildCount(viewLine), ViewPanel.READ_LINE_COUNT);
                    if (n3 != 0) {
                        n3 = this.ReadChildLines(viewLine, viewLineRange);
                    }
                    n2 -= n3;
                }
            }
            if (n2 > 0) {
                String s;
                if (this.expansionState == 1) {
                    s = viewLineRange.computeFirstChildPos(viewLineRange.getEndPos());
                }
                else {
                    s = viewLineRange.computeFirstChildPos(viewLineRange.getEndPos());
                }
                if (this.expansionState == 1) {
                    this.ReadLineRange(viewLineRange, s, 1, n2, 0, 0, null, false, true);
                }
                else {
                    this.ReadLineRange(viewLineRange, s, 15, n2, 0, 0, null, false, false);
                }
            }
        }
        else {
            final String startPos = viewLineRange.getStartPos();
            final int expandedCount = viewLineRange.getExpandedCount();
            if (this.expansionState == 1) {
                this.ReadLineRange(viewLineRange, startPos, 9, n2, 9, 1, null, true, true);
            }
            else {
                this.ReadLineRange(viewLineRange, startPos, 16, n2, 32784, 1, null, true, false);
            }
            this.viewComponent.updateExpandedCount();
            if (this.currentVScrollPos != 0) {
                this.currentVScrollPos += viewLineRange.getExpandedCount() - expandedCount;
            }
        }
        this.updateVScrollValues();
    }
    
    private ViewLineRange createLineRange(final int n, final boolean b) {
        final ViewLineRange viewLineRange = new ViewLineRange();
        if (!b) {
            final int computeCollectionPos = this.computeCollectionPos(n);
            super.vScroll.getPageIncrement();
            if (this.expansionState == 1) {
                this.ReadLineRange(viewLineRange, new Integer(computeCollectionPos).toString(), 1, ViewPanel.READ_LINE_COUNT, 0, 0, null, false, true);
            }
            else {
                this.ReadLineRange(viewLineRange, new Integer(computeCollectionPos).toString(), 15, ViewPanel.READ_LINE_COUNT, 0, 0, null, false, false);
            }
        }
        else {
            this.ReadLineRange(viewLineRange, "1", 16, super.vScroll.getPageIncrement() * 2, 32783, -1, null, false, true);
            viewLineRange.allEntriesRead = true;
            viewLineRange.isEndLineRange = true;
            ((ViewLine)viewLineRange.getTreeList().getTail()).setLastEntry(true);
        }
        return viewLineRange;
    }
    
    private int computeLineRangeMinscrollPos(final ViewLineRange viewLineRange) {
        int n = Integer.valueOf(viewLineRange.getPosAtLevel(viewLineRange.getStartPos(), 1));
        if (viewLineRange.computeLevelAtPos(viewLineRange.getStartPos()) > 1) {
            n = viewLineRange.computeEstimatedScrollPosition(viewLineRange.getStartPos());
        }
        return n;
    }
    
    private int computeCollectionPos(final int n) {
        if (n == 0) {
            return this.defaultCollectionPos;
        }
        final int pageIncrement = super.vScroll.getPageIncrement();
        int toplevelEntryCount = this.toplevelEntryCount;
        if (toplevelEntryCount <= 1) {
            return this.defaultCollectionPos;
        }
        if (toplevelEntryCount > pageIncrement) {
            toplevelEntryCount -= pageIncrement;
        }
        int n2 = (toplevelEntryCount * n + ViewPanel.MAX_SCROLL_POS / 2) / ViewPanel.MAX_SCROLL_POS;
        if (n2 == 0) {
            n2 = this.defaultCollectionPos + 1;
        }
        else {
            ++n2;
        }
        if (n2 > toplevelEntryCount) {
            return toplevelEntryCount;
        }
        return n2;
    }
    
    private int computeCurrentScrollPos() {
        final ViewLine viewLine = (ViewLine)this.viewComponent.getFirstVisibleEntry();
        final ViewLine viewLine2 = (ViewLine)this.viewComponent.getLastVisibleEntry();
        if (viewLine == null || viewLine2 == null) {
            return 0;
        }
        if (viewLine.isFirstEntry()) {
            return 0;
        }
        if (viewLine.getParent() == null && viewLine.GetCollectionPos().equals(String.valueOf(this.defaultCollectionPos))) {
            return 0;
        }
        if (viewLine2.isLastEntry()) {
            return ViewPanel.MAX_SCROLL_POS;
        }
        viewLine.GetCollectionPos();
        viewLine2.GetCollectionPos();
        final int int1 = Integer.parseInt(this.visibleLineRange.getPosAtLevel(viewLine.GetCollectionPos(), 1));
        final int n = int1 / (this.toplevelEntryCount - (Integer.parseInt(this.visibleLineRange.getPosAtLevel(viewLine2.GetCollectionPos(), 1)) - int1)) * ViewPanel.MAX_SCROLL_POS;
        final int n2 = (this.toplevelEntryCount >= ViewPanel.MAX_SCROLL_POS) ? 2 : (ViewPanel.MAX_SCROLL_POS / this.toplevelEntryCount / 2);
        return Math.min(Math.max(n2, n), ViewPanel.MAX_SCROLL_POS - n2);
    }
    
    private void ValidateViewEntries(final String s, final Vector vector) {
        for (int i = 0; i < vector.size(); ++i) {
            String s2 = vector.elementAt(i).Position;
            if (s2.length() > s.length()) {
                s2 = s2.substring(0, s.length());
            }
            if (!s2.equals(s)) {
                vector.removeElementAt(i);
                --i;
            }
        }
    }
    
    private void updateHScrollValues() {
        if (super.hScroll != null && this.isHScrollbarNeeded()) {
            this.currentHScrollPos = super.hScroll.getValue();
        }
        else {
            this.currentHScrollPos = 0;
        }
        super.hScroll.setValues(this.currentHScrollPos, this.size().width, 0, this.totalColumnWidth);
        super.hScroll.setLineIncrement(5);
        super.hScroll.setPageIncrement(this.size().width / 2);
    }
    
    private void updateVScrollValues() {
        final int getVisibleCount = this.viewComponent.GetVisibleCount();
        this.currentVScrollPos = this.computeCurrentScrollPos();
        super.vScroll.setValues(this.currentVScrollPos, getVisibleCount, 0, ViewPanel.MAX_SCROLL_POS);
        super.vScroll.setLineIncrement(1);
        super.vScroll.setPageIncrement(getVisibleCount);
    }
    
    private Vector ReadAndParseViewEntries(final String s, final String s2, final int n, final int n2, final int n3, final int n4, final String s3) {
        Vector vector = null;
        synchronized (this) {
            InputStream inputStream = null;
            final boolean b = s2 != null;
            if (this.bNeedsPriv) {
                try {
                    PrivilegeManager.enablePrivilege("UniversalBrowserRead");
                    PrivilegeManager.enablePrivilege("UniversalConnect");
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
            }
            if (this.applet != null && this.props != null) {
                this.applet.showStatus(this.props.getProperty("reading.entries", "Reading View Data..."));
            }
            vector = new Vector(100);
            try {
                final StringBuffer sb = new StringBuffer();
                sb.append(this.dataURL).append(this.DBName).append("/").append(this.viewUNID).append("?");
                sb.append("ReadViewEntries");
                sb.append("&PreFormat");
                if (this.restrictToCategory) {
                    sb.append("&RestrictToCategory=").append(this.restrictToCategoryName);
                }
                if (b) {
                    sb.append("&UNID=").append(s2);
                }
                else {
                    sb.append("&Start=").append(s);
                }
                sb.append("&Navigate=").append(n);
                sb.append("&Count=").append(n2);
                sb.append("&SkipNavigate=").append(n3);
                if (n4 == -1) {
                    sb.append("&EndView=").append("1");
                }
                else {
                    sb.append("&SkipCount=").append(n4);
                }
                if (s3 != null) {
                    sb.append("&StartKey=").append(s3);
                }
                sb.append(this.constructSortArgs());
                final URL constructURL = this.constructURL(sb.toString());
                inputStream = new DataInputStream(this.postUNIDList(constructURL, true).getInputStream());
                final XMLViewEntriesParser xmlViewEntriesParser = new XMLViewEntriesParser(constructURL.toString(), inputStream, vector);
                xmlViewEntriesParser.parse();
                this.toplevelEntryCount = xmlViewEntriesParser.topLevelEntries;
                this.lastDocReadPos = xmlViewEntriesParser.lastViewEntryPos;
            }
            catch (Exception ex2) {
                System.out.println("An error occurred while reading in documents: " + ex2);
                ex2.printStackTrace();
            }
            finally {
                if (this.applet != null) {
                    this.applet.showStatus(" ");
                }
                try {
                    if (inputStream != null) {
                        ((FilterInputStream)inputStream).close();
                    }
                }
                catch (Exception ex3) {}
            }
        }
        return vector;
    }
    
    public void stateChanged(final StateChangeEvent stateChangeEvent) {
        ViewLine selectedEntry = null;
        if (!(stateChangeEvent.getSource() instanceof ImageCell)) {
            return;
        }
        final ImageCell imageCell = (ImageCell)stateChangeEvent.getSource();
        if (imageCell != null) {
            selectedEntry = (ViewLine)imageCell.GetCellContainer();
        }
        if (selectedEntry != null) {
            if (imageCell instanceof TwistieCell) {
                this.viewComponent.setSelectedEntry(selectedEntry);
                if (((TwistieCell)imageCell).IsCollapsed()) {
                    this.collapseSelectedDocument();
                }
                else {
                    this.expandSelectedDocument();
                }
                this.updateValues();
                if (this.isPreviewMode() && selectedEntry.getUNID() != null && selectedEntry.isDocument) {
                    this.openDocumentByUNID(selectedEntry.getUNID(), true);
                }
                this.viewComponent.repaint();
            }
            else if (imageCell != selectedEntry.GetCellAtIndex(0)) {
                this.markLineSelected(selectedEntry, imageCell.IsSelected());
            }
            else {
                final ImageCell imageCell2 = (ImageCell)selectedEntry.GetCellAtIndex(1);
                imageCell2.SetSelected(!imageCell2.IsSelected());
                imageCell.SetSelected(!imageCell.IsSelected());
                this.markLineSelected(selectedEntry, imageCell2.IsSelected());
            }
        }
    }
    
    void markLineSelected(ViewLine viewLine, final boolean b) {
        if (viewLine != null && this.isSelectionMarginVisible()) {
            final ImageCell imageCell = (ImageCell)viewLine.GetCellAtIndex(1);
            if (imageCell != null) {
                imageCell.SetSelected(b);
                this.updateSelectedList(viewLine, b);
                viewLine = (ViewLine)this.viewComponent.TraverseEntries(3, null, null, viewLine);
                if (viewLine != null) {
                    this.viewComponent.paintOneLine(viewLine);
                }
            }
        }
    }
    
    void markLineSelected(final ViewLine viewLine) {
        if (viewLine != null && this.isSelectionMarginVisible()) {
            final ImageCell imageCell = (ImageCell)viewLine.GetCellAtIndex(1);
            if (imageCell != null) {
                this.markLineSelected(viewLine, !imageCell.IsSelected());
            }
        }
    }
    
    void updateValues() {
        if (this.visibleLineRange != null) {
            this.visibleLineRange.setExpandedCount(this.viewComponent.GetExpandedCount());
        }
        this.updateHScrollValues();
        if (this.isHScrollbarNeeded()) {
            this.EnableHorzScrollBar(true, this.viewComponent);
        }
        else {
            this.EnableHorzScrollBar(false, this.viewComponent);
        }
        this.updateVScrollValues();
        if (this.isVScrollbarNeeded()) {
            this.EnableVertScrollBar(true, this.viewComponent);
        }
        else {
            this.EnableVertScrollBar(false, this.viewComponent);
        }
    }
    
    public void isScrollBarStillNeeded() {
        if (super.vScroll != null && !this.isVScrollbarNeeded()) {
            this.EnableVertScrollBar(false, this.viewComponent);
        }
    }
    
    boolean isVScrollbarNeeded() {
        if (!this.isScrollbarsVisible()) {
            return false;
        }
        if (this.viewComponent.IsVScrollbarNeeded()) {
            return true;
        }
        String startPos = null;
        if (this.visibleLineRange != null) {
            startPos = this.visibleLineRange.getStartPos();
        }
        return startPos != null && !startPos.equals(String.valueOf(this.defaultCollectionPos));
    }
    
    boolean isHScrollbarNeeded() {
        return this.isScrollbarsVisible() && ((super.hScroll != null && super.hScroll.getValue() > 0) || this.viewComponent.IsHScrollbarNeeded(this.totalColumnWidth));
    }
    
    private void updateSelectedList(final ViewLine viewLine, final boolean b) {
        if (viewLine != null) {
            if (b) {
                this.selectedDocs.put(viewLine.UNID, viewLine);
            }
            else {
                this.selectedDocs.remove(viewLine.UNID);
            }
        }
    }
    
    private void clearSelectedList() {
        final Enumeration<ViewLine> elements = this.selectedDocs.elements();
        while (elements.hasMoreElements()) {
            final ViewLine viewLine = elements.nextElement();
            if (viewLine != null) {
                ((ImageCell)viewLine.GetCellAtIndex(1)).SetSelected(false);
                final ViewLine viewLine2 = (ViewLine)this.viewComponent.TraverseEntries(3, null, null, viewLine);
                if (viewLine2 == null) {
                    continue;
                }
                this.viewComponent.paintOneLine(viewLine2);
            }
        }
        this.selectedDocs.clear();
    }
    
    private void updateMarkedList(final ViewLine viewLine, final boolean b) {
        if (viewLine != null) {
            if (b) {
                this.markedDocs.put(viewLine.UNID, viewLine);
            }
            else {
                this.markedDocs.remove(viewLine.UNID);
            }
        }
    }
    
    private void clearMarkedList() {
        final Enumeration<ViewLine> elements = this.markedDocs.elements();
        while (elements.hasMoreElements()) {
            final ViewLine viewLine = elements.nextElement();
            if (viewLine != null) {
                ((ImageCell)viewLine.GetCellAtIndex(0)).SetSelected(false);
                final ViewLine viewLine2 = (ViewLine)this.viewComponent.TraverseEntries(3, null, null, viewLine);
                if (viewLine2 == null) {
                    continue;
                }
                this.viewComponent.paintOneLine(viewLine2);
            }
        }
        this.markedDocs.clear();
    }
    
    private void clearTableValues(final Hashtable hashtable) {
        if (hashtable != null) {
            final Enumeration<Object> keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                hashtable.put(keys.nextElement(), "");
            }
        }
    }
    
    public String getCurrentDocument() {
        final ViewLine viewLine = (ViewLine)this.viewComponent.getSelectedEntry();
        if (viewLine != null) {
            return viewLine.getUNID();
        }
        return null;
    }
    
    public synchronized String[] getSelectedDocuments() {
        return this.getSelectedDocumentUNIDS();
    }
    
    public void setIconPath(String iconPath) {
        if (iconPath != null) {
            if (iconPath.startsWith("/") || iconPath.startsWith("\\")) {
                iconPath = iconPath.substring(1);
            }
            if (iconPath.endsWith("/") || iconPath.endsWith("\\")) {
                iconPath = iconPath.substring(0, iconPath.length() - 1);
            }
            this.iconPath = iconPath;
        }
    }
    
    public void setProperties(final Properties props) {
        this.props = props;
        if (props == null) {
            throw new IllegalArgumentException("Attempting to set a null resource bundle");
        }
    }
    
    public void setSimpleView(final boolean bSimpleView) {
        this.bSimpleView = bSimpleView;
    }
    
    public boolean isSimpleView() {
        return this.bSimpleView;
    }
    
    public void setHeaderVisible(final boolean headerVisible) {
        this.headerVisible = headerVisible;
    }
    
    public boolean isHeaderVisible() {
        return this.headerVisible && !this.isSimpleView();
    }
    
    public void setScrollbarsVisible(final boolean scrollbarsVisible) {
        this.scrollbarsVisible = scrollbarsVisible;
    }
    
    public boolean isScrollbarsVisible() {
        return this.scrollbarsVisible && !this.isSimpleView();
    }
    
    public void setSelectionMarginVisible(final boolean selectionMarginVisible) {
        this.selectionMarginVisible = selectionMarginVisible;
        if (this.isSelectionMarginVisible()) {
            this.gutterColumnOffset = 2;
        }
        else {
            this.gutterColumnOffset = 0;
        }
        if (this.viewComponent != null) {
            this.viewComponent.setPaintGutterLine(this.isSelectionMarginVisible());
        }
    }
    
    public void setGotoBottom(final boolean gotoBottom) {
        this.gotoBottom = gotoBottom;
    }
    
    public boolean isSelectionMarginVisible() {
        return this.selectionMarginVisible && !this.isSimpleView();
    }
    
    public void setDatabaseName(final String dbName) {
        this.DBName = dbName;
    }
    
    public String getDatabaseName() {
        return this.DBName;
    }
    
    public void setViewName(final String viewName) {
        if (this.isShowing()) {
            this.open(viewName);
        }
        else {
            this.viewName = viewName;
        }
    }
    
    public void setViewUNID(final String viewUNID) {
        this.viewUNID = viewUNID;
    }
    
    public void setTrashUNID(final String trashUNID) {
        this.trashUNID = trashUNID;
    }
    
    public String getViewName() {
        return this.viewName;
    }
    
    public void setShowOnlySelected(final boolean bShowOnlySelected) {
        this.bShowOnlySelected = bShowOnlySelected;
    }
    
    public boolean getShowOnlySelected() {
        return this.bShowOnlySelected;
    }
    
    public void setRestrictToCategoryName(final String restrictToCategoryName) {
        this.restrictToCategory = (restrictToCategoryName != null);
        this.restrictToCategoryName = restrictToCategoryName;
        if (this.restrictToCategory) {
            this.setExpansionState(1);
        }
    }
    
    public void setHostName(final String hostName) {
        this.hostName = hostName;
        if (this.hostName == null || this.hostName.length() == 0) {
            this.hostName = new String("localhost");
        }
        this.dataURL = this.protocol + "://" + this.hostName + "/";
    }
    
    public void setProtocol(final String protocol) {
        this.protocol = protocol;
        if (protocol.equals("file") || protocol == null || protocol.length() == 0) {
            this.protocol = "http";
        }
        this.dataURL = this.protocol + "://" + this.hostName + "/";
    }
    
    public String getHostName() {
        return this.hostName;
    }
    
    public void setLocaleString(final String localeString) {
        this.localeString = localeString;
    }
    
    public String getLocaleString() {
        return this.localeString;
    }
    
    public void setTarget(final String target) {
        this.target = target;
    }
    
    public void setDblClickTarget(final String doubleClickTarget) {
        this.doubleClickTarget = doubleClickTarget;
    }
    
    public String getDblClickTarget() {
        return this.doubleClickTarget;
    }
    
    public synchronized void setBackground(final Color color) {
        super.setBackground(this.backgroundColor = color);
        if (this.viewComponent != null) {
            this.viewComponent.setBackground(color);
        }
    }
    
    public synchronized void setForeground(final Color foregroundColor) {
        super.setForeground(this.foregroundColor = foregroundColor);
    }
    
    public void headerResized(final HeaderEvent headerEvent) {
        final Vector getColumnDescVector = this.viewComponent.GetColumnDescVector(0);
        final int n = this.viewComponent.GetColDescVectorSize() - 1;
        final int ordinal = headerEvent.getHeader().getOrdinal();
        final int newWidth = headerEvent.getNewWidth();
        final int previousWidth = headerEvent.getPreviousWidth();
        ViewColDesc viewColDesc = null;
        if (ordinal >= 0) {
            viewColDesc = getColumnDescVector.elementAt(ordinal);
        }
        if (viewColDesc != null) {
            if (!viewColDesc.isCategory) {
                viewColDesc.SetWidth(headerEvent.getHeader().getWidth());
            }
            this.resizeColumnVector(getColumnDescVector, ordinal + 1, newWidth - previousWidth);
            if (ordinal <= this.responseColNumber) {
                for (int i = 1; i <= n; ++i) {
                    this.resizeColumnVector(this.viewComponent.GetColumnDescVector(i), 0, newWidth - previousWidth);
                }
                this.responseColXPos += newWidth - previousWidth;
            }
            if (ordinal <= this.firstTotalColumn + this.gutterColumnOffset) {
                this.firstTotalColumnXPos += newWidth - previousWidth;
                for (int j = 0; j < this.designInfo.numCategories; ++j) {
                    this.categoryColDescs[j].Width = this.firstTotalColumnXPos - this.categoryColDescs[j].xPos;
                }
            }
        }
        this.viewComponent.invalidateAllLines();
        this.viewComponent.repaint();
        if (headerEvent.getHeader() != this.lastHeader) {
            this.totalColumnWidth += newWidth - previousWidth;
        }
        this.viewComponent.UpdateCounts();
        this.updateValues();
    }
    
    public void headerStateChanged(final HeaderEvent headerEvent) {
        final Header header = headerEvent.getHeader();
        final int sortState = header.getSortState();
        this.sortColumnNumber = header.getOrdinal() - this.gutterColumnOffset;
        final ViewColumnInfo viewColumnInfo = this.designInfo.vColumnInfo.elementAt(this.sortColumnNumber);
        if (viewColumnInfo.resortToView && viewColumnInfo.resortViewUNID != null) {
            this.gotoView(viewColumnInfo.resortViewUNID);
        }
        else {
            if (sortState == 0) {
                viewColumnInfo.sort = false;
            }
            else {
                viewColumnInfo.sort = true;
                viewColumnInfo.sortDescending = (sortState == 2);
            }
            this.refresh(true, true, 0);
        }
    }
    
    private void gotoView(final String s) {
        try {
            this.applet.getAppletContext().showDocument(this.constructURL(this.dataURL + this.DBName + "/" + s + "?OpenView"), "_self");
        }
        catch (Exception ex) {
            System.out.println("An error occurred while trying switch views: " + ex);
            ex.printStackTrace();
        }
    }
    
    private void resizeColumnVector(final Vector vector, final int n, final int n2) {
        final int size = vector.size();
        ViewColDesc viewColDesc = null;
        for (int i = n; i < size; ++i) {
            final ViewColDesc viewColDesc2;
            viewColDesc = (viewColDesc2 = vector.elementAt(i));
            viewColDesc2.xPos += n2;
        }
        if (this.designInfo.extendLastColumnWidth) {
            if (viewColDesc == null) {
                viewColDesc = vector.lastElement();
            }
            viewColDesc.Width = this.viewComponent.size().width - viewColDesc.xPos;
        }
    }
    
    void startColumnResize(final int oldColumnResizeX) {
        this.bResizing = true;
        this.g = this.viewComponent.getGraphics();
        this.oldForeground = this.g.getColor();
        this.lineHeight = this.viewComponent.size().height;
        this.g.setXORMode(this.getBackground());
        this.g.setColor(Color.black);
        this.g.drawLine(oldColumnResizeX, 0, oldColumnResizeX, this.lineHeight);
        this.oldColumnResizeX = oldColumnResizeX;
    }
    
    void endColumnResize() {
        this.bResizing = false;
        if (this.g != null) {
            this.g.drawLine(this.oldColumnResizeX, 0, this.oldColumnResizeX, this.lineHeight);
            this.g.setPaintMode();
            this.g.setColor(this.oldForeground);
            this.g = null;
        }
        this.oldColumnResizeX = -1;
    }
    
    void drawColumnResizeLine(final int oldColumnResizeX) {
        if (!this.bResizing) {
            return;
        }
        if (this.g != null) {
            if (this.oldColumnResizeX != -1) {
                this.g.drawLine(this.oldColumnResizeX, 0, this.oldColumnResizeX, this.lineHeight);
            }
            this.g.drawLine(oldColumnResizeX, 0, oldColumnResizeX, this.lineHeight);
            this.oldColumnResizeX = oldColumnResizeX;
        }
    }
    
    private URL constructURL(final String s) throws MalformedURLException {
        URL url = null;
        if (s != null) {
            url = new URL(s.replace(' ', '+'));
        }
        return url;
    }
    
    private Image[] loadImagesForResortType(final int n) {
        final int n2 = n - 1;
        if (ViewPanel.resortImages[n2][0] == null) {
            for (int i = 0; i < ViewPanel.resortImageNames[n2].length; ++i) {
                ViewPanel.resortImages[n2][i] = this.loadImage(true, ViewPanel.resortImageNames[n2][i]);
            }
        }
        return ViewPanel.resortImages[n2];
    }
    
    private Applet findApplet() {
        Applet applet = null;
        for (Container container = this.getParent(); container != null; container = container.getParent()) {
            if (container instanceof Applet) {
                applet = (Applet)container;
                break;
            }
        }
        return applet;
    }
    
    public synchronized void expandAll() {
        this.setExpansionState(1);
        this.refresh(true, false, 1);
    }
    
    public synchronized void collapseAll() {
        this.setExpansionState(2);
        this.refresh(true, false, 1);
    }
    
    public synchronized void expandSelectedDocument() {
        final ViewLine viewLine = (ViewLine)this.viewComponent.getSelectedEntry();
        if (viewLine != null) {
            this.ReadChildLines(viewLine, this.visibleLineRange);
            viewLine.Expand();
            this.collapsedTable.expand(viewLine.UNID);
            final TwistieCell twistieCell = viewLine.getTwistieCell();
            if (twistieCell != null) {
                twistieCell.setCollapsed(false);
            }
            this.expansionState = 0;
            this.updateValues();
            this.viewComponent.repaint();
        }
    }
    
    public synchronized void collapseSelectedDocument() {
        final ViewLine viewLine = (ViewLine)this.viewComponent.getSelectedEntry();
        if (viewLine != null) {
            viewLine.Collapse();
            this.collapsedTable.collapse(viewLine.UNID);
            final Vector allDescendantParents = this.visibleLineRange.getAllDescendantParents(viewLine);
            if (allDescendantParents != null) {
                final Enumeration<String> elements = allDescendantParents.elements();
                while (elements.hasMoreElements()) {
                    final String s = elements.nextElement();
                    if (s != null) {
                        this.collapsedTable.collapse(s);
                    }
                }
            }
            final String getCollectionPos = viewLine.GetCollectionPos();
            this.refresh(true, true, 1);
            final LineEntry entryByPos = this.visibleLineRange.findEntryByPos(getCollectionPos);
            if (entryByPos != null) {
                this.viewComponent.setSelectedEntry(entryByPos);
            }
            this.expansionState = 0;
            this.updateValues();
            this.viewComponent.repaint();
        }
    }
    
    public synchronized void markSelectedDocumentsForDelete() {
        Vector<String> vector = null;
        Vector<String> vector2 = null;
        final ViewLine[] selectedDocumentEntries = this.getSelectedDocumentEntries();
        if (selectedDocumentEntries != null) {
            if (this.trashUNID != null) {
                vector = new Vector<String>();
                vector2 = new Vector<String>();
            }
            for (int i = 0; i < selectedDocumentEntries.length; ++i) {
                final ViewLine viewLine = selectedDocumentEntries[i];
                if (viewLine != null && viewLine.isDocument) {
                    if (this.markedDocs.containsKey(viewLine.UNID)) {
                        this.markedDocs.remove(viewLine.UNID);
                        if (this.trashUNID != null) {
                            vector2.addElement(viewLine.UNID);
                        }
                        if (this.isSelectionMarginVisible()) {
                            ((ImageCell)viewLine.GetCellAtIndex(0)).SetSelected(false);
                        }
                    }
                    else {
                        this.markedDocs.put(viewLine.UNID, viewLine);
                        if (this.trashUNID != null) {
                            vector.addElement(viewLine.UNID);
                        }
                        if (this.isSelectionMarginVisible()) {
                            ((ImageCell)viewLine.GetCellAtIndex(0)).SetSelected(true);
                        }
                    }
                    final ViewLine viewLine2 = (ViewLine)this.viewComponent.TraverseEntries(3, null, null, viewLine);
                    if (viewLine2 != null) {
                        this.viewComponent.paintOneLine(viewLine2);
                    }
                }
            }
            if (this.trashUNID != null) {
                if (vector.size() > 0) {
                    this.executeCommand(this.trashUNID, "CopyToFolder", vector);
                }
                if (vector2.size() > 0) {
                    this.executeCommand(this.trashUNID, "RemoveFromFolder", vector2);
                }
            }
            this.clearSelectedList();
        }
    }
    
    public synchronized void deleteMarkedDocuments(final boolean b) {
        if (this.trashUNID != null) {
            this.executeCommand(this.trashUNID, "DeleteDocuments&EmptyTrash=1");
        }
        else if (this.markedDocs.size() > 0) {
            final String[] array = new String[this.markedDocs.size()];
            int n = 0;
            final Enumeration<String> keys = this.markedDocs.keys();
            while (keys.hasMoreElements()) {
                array[n++] = keys.nextElement();
            }
            this.executeCommand(this.viewUNID, "DeleteDocuments", array);
        }
        if (b) {
            this.clearMarkedList();
            this.refresh(true, true, 1);
        }
    }
    
    public synchronized void deleteSelectedDocuments(final boolean b, final boolean b2) {
        final String[] selectedDocumentUNIDS = this.getSelectedDocumentUNIDS();
        if (selectedDocumentUNIDS != null && selectedDocumentUNIDS.length > 0) {
            if (b) {
                this.executeCommand(this.viewUNID, "DeleteDocuments&HardDelete=1", selectedDocumentUNIDS);
            }
            else {
                this.executeCommand(this.viewUNID, "DeleteDocuments", selectedDocumentUNIDS);
            }
            if (b2) {
                this.clearSelectedList();
                this.refresh(true, true, 1);
            }
        }
    }
    
    public synchronized void undeleteSelectedDocuments(final boolean b) {
        final String[] selectedDocumentUNIDS = this.getSelectedDocumentUNIDS();
        if (selectedDocumentUNIDS != null && selectedDocumentUNIDS.length > 0) {
            this.executeCommand(this.viewUNID, "UndeleteDocuments", selectedDocumentUNIDS);
            if (b) {
                this.clearSelectedList();
                this.refresh(true, true, 1);
            }
        }
    }
    
    public synchronized void moveSelectedDocumentsToFolder(final String s) {
        final String[] selectedDocumentUNIDS = this.getSelectedDocumentUNIDS();
        if (selectedDocumentUNIDS != null && selectedDocumentUNIDS.length > 0) {
            this.executeCommand(s, "MoveToFolder&SourceFolder=" + this.viewUNID, selectedDocumentUNIDS);
            this.clearSelectedList();
            this.refresh(true, true, 1);
        }
    }
    
    public synchronized void copySelectedDocumentsToFolder(final String s) {
        final String[] selectedDocumentUNIDS = this.getSelectedDocumentUNIDS();
        if (selectedDocumentUNIDS != null && selectedDocumentUNIDS.length > 0) {
            this.executeCommand(s, "CopyToFolder", selectedDocumentUNIDS);
        }
    }
    
    public synchronized void removeSelectedDocumentsFromFolder() {
        final String[] selectedDocumentUNIDS = this.getSelectedDocumentUNIDS();
        if (selectedDocumentUNIDS != null && selectedDocumentUNIDS.length > 0) {
            this.executeCommand(this.viewUNID, "RemoveFromFolder", selectedDocumentUNIDS);
            this.clearSelectedList();
            this.refresh(true, true, 1);
        }
    }
    
    private int executeCommand(final String s, final String s2) {
        return this.executeCommand(s, s2, (String[])null);
    }
    
    private int executeCommand(final String s, final String s2, final Vector vector) {
        int executeCommand = -1;
        if (vector != null) {
            final int size = vector.size();
            if (size > 0) {
                final String[] array = new String[size];
                for (int i = 0; i < size; ++i) {
                    array[i] = vector.elementAt(i);
                }
                executeCommand = this.executeCommand(s, s2, array);
            }
        }
        return executeCommand;
    }
    
    private int executeCommand(final String s, final String s2, final String[] array) {
        InputStream inputStream = null;
        int status = 0;
        if (this.bNeedsPriv) {
            try {
                PrivilegeManager.enablePrivilege("UniversalBrowserRead");
                PrivilegeManager.enablePrivilege("UniversalConnect");
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
        try {
            final URL constructURL = this.constructURL(this.dataURL + this.getDatabaseName() + "/" + s + "?" + s2);
            URLConnection urlConnection;
            if (array != null) {
                urlConnection = this.postUNIDList(constructURL, array, false);
            }
            else {
                urlConnection = constructURL.openConnection();
                urlConnection.setDoInput(true);
                if (this.getLocaleString() != null) {
                    urlConnection.setRequestProperty("Accept-Language", this.getLocaleString());
                }
            }
            inputStream = new DataInputStream(urlConnection.getInputStream());
            final StatusParser statusParser = new StatusParser(constructURL.toString(), inputStream, this.bNeedsPriv);
            statusParser.parse();
            status = statusParser.getStatus();
            if (status != 0) {
                final String message = statusParser.getMessage();
                if (this.applet != null && message != null) {
                    this.applet.showStatus(message);
                }
                System.out.println("Command " + s2 + " failed on one or more documents");
                System.out.println("Error message was: " + statusParser.getMessage());
            }
        }
        catch (Exception ex2) {
            System.out.println("An error occurred while executing command " + s2);
            System.out.println(ex2);
            ex2.printStackTrace();
        }
        finally {
            try {
                if (inputStream != null) {
                    ((FilterInputStream)inputStream).close();
                }
            }
            catch (IOException ex3) {}
        }
        return status;
    }
    
    private String arrayToList(final String[] array) {
        String string = null;
        if (array != null && array.length > 0) {
            final StringBuffer sb = new StringBuffer(array.length * 38);
            for (int i = 0; i < array.length; ++i) {
                sb.append("unid=").append(array[i]).append('&');
            }
            sb.setLength(sb.length() - 1);
            string = sb.toString();
        }
        return string;
    }
    
    private URLConnection postUNIDList(final URL url, final boolean b) throws IOException {
        return this.postUNIDList(url, null, b);
    }
    
    private URLConnection postUNIDList(final URL url, final String[] array, final boolean b) throws IOException {
        DataOutputStream dataOutputStream = null;
        URLConnection openConnection;
        try {
            openConnection = url.openConnection();
            openConnection.setDoInput(true);
            openConnection.setDoOutput(true);
            openConnection.setUseCaches(false);
            openConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String s;
            if (array == null) {
                final String list = this.collapsedTable.getList();
                if (this.collapsedTable.isCollapsed()) {
                    s = "collapsed=1&" + list;
                }
                else {
                    s = "expanded=1&" + list;
                }
            }
            else {
                s = this.arrayToList(array);
            }
            openConnection.setRequestProperty("Content-Length", String.valueOf(s.length()));
            if (this.getLocaleString() != null) {
                openConnection.setRequestProperty("Accept-Language", this.getLocaleString());
            }
            dataOutputStream = new DataOutputStream(openConnection.getOutputStream());
            dataOutputStream.writeBytes(s);
            dataOutputStream.flush();
            dataOutputStream.close();
        }
        catch (IOException ex) {
            System.out.println("Exception occurred while posting UNID list:" + ex);
            try {
                dataOutputStream.close();
            }
            catch (Exception ex2) {}
            throw ex;
        }
        return openConnection;
    }
    
    private ViewLine[] getSelectedDocumentEntries() {
        ViewLine[] array = null;
        int n = 0;
        if (this.selectedDocs.size() == 0) {
            array = new ViewLine[] { null };
            final ViewLine viewLine = (ViewLine)this.viewComponent.getSelectedEntry();
            if (viewLine != null) {
                array[0] = viewLine;
            }
        }
        else {
            final Enumeration<Object> elements = this.selectedDocs.elements();
            while (elements.hasMoreElements()) {
                final Object nextElement = elements.nextElement();
                if (nextElement != null && nextElement instanceof ViewLine) {
                    ++n;
                }
            }
            if (n > 0) {
                array = new ViewLine[n];
                int n2 = 0;
                final Enumeration<ViewLine> elements2 = this.selectedDocs.elements();
                while (elements2.hasMoreElements()) {
                    final ViewLine nextElement2 = elements2.nextElement();
                    if (nextElement2 != null && nextElement2 instanceof ViewLine) {
                        array[n2++] = nextElement2;
                    }
                }
            }
        }
        return array;
    }
    
    private String[] getSelectedDocumentUNIDS() {
        String[] array = null;
        if (this.selectedDocs.size() == 0) {
            array = new String[] { null };
            final ViewLine viewLine = (ViewLine)this.viewComponent.getSelectedEntry();
            if (viewLine != null) {
                array[0] = viewLine.UNID;
            }
        }
        else {
            final int size = this.selectedDocs.size();
            if (size > 0) {
                array = new String[size];
                int n = 0;
                final Enumeration<String> keys = this.selectedDocs.keys();
                while (keys.hasMoreElements()) {
                    final String s = keys.nextElement();
                    if (s != null) {
                        array[n++] = s;
                    }
                }
            }
        }
        return array;
    }
    
    public synchronized void setExpansionState(final int expansionState) {
        this.expansionState = expansionState;
        this.collapsedTable.setCollapsed(expansionState == 1);
    }
    
    public void EnableHorzScrollBar(final boolean b, final Component component) {
        super.EnableHorzScrollBar(b, component);
        super.hScroll.setTrackOnDrag(false);
    }
    
    public void EnableVertScrollBar(final boolean b, final Component component) {
        if (this.isHeaderVisible()) {
            this.initHeader();
            super.vScrollYoff = this.headerPanel.preferredSize().height;
        }
        super.EnableVertScrollBar(b, component);
        super.vScroll.setTrackOnDrag(false);
        if (b) {
            this.updateVScrollValues();
        }
    }
    
    private String constructSortArgs() {
        String string = "";
        if (this.sortColumnNumber != -1) {
            final ViewColumnInfo viewColumnInfo = this.designInfo.vColumnInfo.elementAt(this.sortColumnNumber);
            final boolean sort = viewColumnInfo.sort;
            final boolean sortDescending = viewColumnInfo.sortDescending;
            if (sort) {
                String string2;
                if (sortDescending) {
                    string2 = "&ResortDescending=";
                }
                else {
                    string2 = string + "&ResortAscending=";
                }
                string = string2 + viewColumnInfo.colNumber;
            }
        }
        return string;
    }
    
    private Image loadIcon(final String s) {
        Image loadImage = null;
        if (s != null) {
            final int length = s.length();
            String s2;
            if (length == 1) {
                s2 = "vwicn00" + s;
            }
            else if (length == 2) {
                s2 = "vwicn0" + s;
            }
            else {
                s2 = "vwicn" + s;
            }
            loadImage = this.loadImage(true, s2 + ".gif");
        }
        return loadImage;
    }
    
    private boolean isInteger(final String s) {
        try {
            Integer.valueOf(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
    
    int getSelectionMarginWidth() {
        if (this.isSelectionMarginVisible()) {
            return 33;
        }
        return 0;
    }
    
    public void setNeedsPriv(final boolean bNeedsPriv) {
        this.bNeedsPriv = bNeedsPriv;
    }
    
    public void stop() {
        this.currentDisplayedUNID = null;
    }
    
    static {
        ViewPanel.READ_LINE_COUNT = 40;
        ViewPanel.fallbackLocale = new Locale("en", "EN");
        DATE_INPUT_FORMAT_LENGTH = "yyyyMMdd'T'kkmmss".length() - 2;
        ViewPanel.NUMBER_INPUT_FORMAT = "###0.###############";
        ViewPanel.MAX_SCROLL_POS = 2000;
        resortImageNames = new String[][] { { "ascsort.gif", "altasc.gif", null }, { "descsort.gif", "altdesc.gif", null }, { "dblsort.gif", "dblasc.gif", "dbldesc.gif" }, { "viewsort.gif", null, null } };
        ViewPanel.resortImages = new Image[][] { { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null } };
    }
}
