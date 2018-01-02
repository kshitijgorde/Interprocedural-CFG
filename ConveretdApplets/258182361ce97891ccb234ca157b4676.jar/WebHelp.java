import java.util.Enumeration;
import hhapplet.URLFileHandler;
import XMLConsumer.Project;
import hhapplet.IndexViewSkin;
import hhapplet.BsscHelpRedirector;
import BsscXML.BsscXML;
import hhapplet.ImageCache;
import hhapplet.FtsViewSkin;
import java.net.URL;
import hhapplet.TocViewSkin;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import hhapplet.ResourceLib;
import hhapplet.GloViewSkin;
import java.awt.Component;
import java.awt.LayoutManager;
import hhapplet.ButtonPushEvent;
import java.awt.Event;
import netscape.javascript.JSObject;
import hhapplet.PaneSetting;
import java.util.Vector;
import hhapplet.TabButton;
import java.awt.CardLayout;
import hhapplet.TabPanel;
import java.awt.Panel;
import hhapplet.GlossaryView;
import hhapplet.FtsView;
import hhapplet.IndexView;
import hhapplet.TocView;
import hhapplet.TabManagerAdaper;
import hhapplet.BsscHelpCommandAdapter;
import hhapplet.ButtonPushEventListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class WebHelp extends Applet implements ButtonPushEventListener, BsscHelpCommandAdapter, TabManagerAdaper, Runnable
{
    private static final String STR_APPLET_ID = "WebHelp Applet Version 5.00.000";
    private static final String STR_COPYRIGHT = "Copyright (c) 1998-2001 eHelp Corporation. All rights reserved.";
    private boolean m_bShowTab;
    private String m_strProjectFile;
    private TocView m_tocView;
    private IndexView m_indexView;
    private FtsView m_ftsView;
    private GlossaryView m_gloView;
    private Panel m_pnlNav;
    private TabPanel m_pnlTabs;
    private CardLayout m_clLayout;
    private TabButton[] m_abtns;
    private CommandBuffer m_commandBuffer;
    private String m_sLangId;
    private boolean m_bMustHaveSize;
    boolean m_bCanAccessJSObject;
    private boolean m_bCanAccessJSObjectChecked;
    private Thread m_CommandThread;
    private Vector m_vProjects;
    private PaneSetting m_tocPaneSetting;
    private PaneSetting m_idxPaneSetting;
    private PaneSetting m_ftsPaneSetting;
    private PaneSetting m_gloPaneSetting;
    private int m_nFirstPane;
    private static final int m_nMaxPanes = 4;
    
    public void stop() {
        System.out.println("!!!Applet Stop!!!");
        if (System.getProperty("java.vendor").indexOf("Netscape") != -1 && this.CanQueryHeighAndWidthForNS()) {
            try {
                JSObject.getWindow((Applet)this).setMember("gbLoading", (Object)"Phase1");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            if (this.m_CommandThread != null) {
                new WhCommand("Quit", null, null);
                this.m_CommandThread.stop();
            }
            System.gc();
            Runtime.getRuntime().gc();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    public boolean gotFocus(final Event event, final Object o) {
        this.nextFocus();
        return true;
    }
    
    public void notifyButtonPushEvent(final ButtonPushEvent buttonPushEvent) {
        if (this.m_tocPaneSetting != null && buttonPushEvent.getSource() == this.m_abtns[this.m_tocPaneSetting.getIndex()]) {
            this.DoContents();
            return;
        }
        if (this.m_idxPaneSetting != null && buttonPushEvent.getSource() == this.m_abtns[this.m_idxPaneSetting.getIndex()]) {
            this.DoIndex();
            return;
        }
        if (this.m_ftsPaneSetting != null && buttonPushEvent.getSource() == this.m_abtns[this.m_ftsPaneSetting.getIndex()]) {
            this.DoSearch("");
            return;
        }
        if (this.m_gloPaneSetting != null && buttonPushEvent.getSource() == this.m_abtns[this.m_gloPaneSetting.getIndex()]) {
            this.DoGlossary();
        }
    }
    
    private void activeTabButton(final int n) {
        int n2 = 0;
        do {
            if (this.m_abtns[n2] != null) {
                if (n2 != n) {
                    this.m_abtns[n2].disactive();
                    if (n2 == n - 1) {
                        this.m_abtns[n2].SetDrawRight(false);
                    }
                    else {
                        this.m_abtns[n2].SetDrawRight(true);
                    }
                    if (n2 == n + 1) {
                        this.m_abtns[n2].SetDrawLeft(false);
                    }
                    else {
                        this.m_abtns[n2].SetDrawLeft(true);
                    }
                }
                else {
                    this.m_abtns[n2].active();
                }
                this.m_abtns[n2].repaint(0L);
            }
        } while (++n2 < 4);
    }
    
    public boolean CanQueryHeighAndWidthForNS() {
        if (this.m_bCanAccessJSObjectChecked) {
            return this.m_bCanAccessJSObject;
        }
        this.m_bCanAccessJSObjectChecked = true;
        return this._checkAccess();
    }
    
    public void start() {
        System.out.println("!!!Applet Start!!!");
        try {
            if (this.m_bMustHaveSize) {
                this.checkSize();
            }
            (this.m_CommandThread = new Thread(this)).start();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (System.getProperty("java.vendor").indexOf("Netscape") == -1) {
            this.myrun();
        }
    }
    
    public String getAppletInfo() {
        return "WebHelp Applet Version 5.00.000" + "\r\n" + "Copyright (c) 1998-2001 eHelp Corporation. All rights reserved.";
    }
    
    private void DoGlossary() {
        if (this.m_gloPaneSetting != null) {
            this.activeTabButton(this.m_gloPaneSetting.getIndex());
            if (this.m_pnlNav == null) {
                this.m_clLayout = new CardLayout();
                (this.m_pnlNav = new Panel()).setLayout(this.m_clLayout);
                this.add("Center", this.m_pnlNav);
            }
            this.m_bMustHaveSize = true;
            if (this.m_gloView == null) {
                this.m_gloView = new GlossaryView(this.m_vProjects, (GloViewSkin)this.m_gloPaneSetting.getViewSkin());
                this.m_pnlNav.add(ResourceLib.GetRes("Glossary"), this.m_gloView);
                this.showStatus(ResourceLib.GetRes("LoadingGlossary"));
            }
            this.m_clLayout.show(this.m_pnlNav, ResourceLib.GetRes("Glossary"));
            this.repaint();
            this.RepaintTabs();
            if (this.m_gloView != null) {
                this.m_gloView.requestFocus();
            }
        }
    }
    
    public void reshape(final int n, final int n2, int getWidth, int getHeight) {
        System.out.println("!!!Applet Reshape!!!");
        try {
            if (!System.getProperty("java.vendor").startsWith("Netscape") || (!System.getProperty("os.name").startsWith("Windows") && !System.getProperty("os.name").startsWith("Mac"))) {
                super.reshape(n, n2, getWidth, getHeight);
                this.validate();
                return;
            }
            if (this.CanQueryHeighAndWidthForNS()) {
                final JavaScriptAccess javaScriptAccess = new JavaScriptAccess(this);
                getHeight = javaScriptAccess.GetHeight(getHeight);
                getWidth = javaScriptAccess.GetWidth(getWidth);
                if (!this.m_bShowTab) {
                    try {
                        if (System.getProperty("java.vendor").indexOf("Netscape") != -1) {
                            this.getParent().reshape(n, n2, getWidth, getHeight);
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                super.reshape(n, n2, getWidth, getHeight);
                this.validate();
                return;
            }
            super.reshape(n, n2, getWidth, getHeight);
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    private void DoNavPane() {
        this.setBackground(new Color(192, 192, 192));
        this.setLayout(new BorderLayout());
        this.m_strProjectFile = this.getParameter("ProjectFile");
        if (this.m_strProjectFile != null && this.m_strProjectFile.length() > 0) {
            this.loadProject(this.m_strProjectFile);
        }
        final String parameter = this.getParameter("HideTabs");
        if (parameter != null && parameter.toUpperCase().compareTo("TRUE") == 0) {
            this.m_bShowTab = false;
        }
        else {
            this.m_bShowTab = true;
        }
        String s = this.getParameter("ContentsTab");
        if (s == null) {
            s = ResourceLib.GetRes("Contents");
        }
        String s2 = this.getParameter("IndexTab");
        if (s2 == null) {
            s2 = ResourceLib.GetRes("Index");
        }
        String s3 = this.getParameter("SearchTab");
        if (s3 == null) {
            s3 = ResourceLib.GetRes("Search");
        }
        String s4 = this.getParameter("GlossaryTab");
        if (s4 == null) {
            s4 = ResourceLib.GetRes("Glossary");
        }
        this.m_pnlTabs = null;
        (this.m_pnlTabs = new TabPanel()).setLayout(null);
        this.m_abtns = new TabButton[4];
        if (this.m_tocPaneSetting != null) {
            final TabButton tabButton = new TabButton(s, this);
            tabButton.addButtonPushEventListener(this);
            if (4 > this.m_tocPaneSetting.getIndex()) {
                this.m_abtns[this.m_tocPaneSetting.getIndex()] = tabButton;
            }
        }
        if (this.m_idxPaneSetting != null) {
            final TabButton tabButton2 = new TabButton(s2, this);
            tabButton2.addButtonPushEventListener(this);
            if (4 > this.m_idxPaneSetting.getIndex()) {
                this.m_abtns[this.m_idxPaneSetting.getIndex()] = tabButton2;
            }
        }
        if (this.m_ftsPaneSetting != null) {
            final TabButton tabButton3 = new TabButton(s3, this);
            tabButton3.addButtonPushEventListener(this);
            if (4 > this.m_ftsPaneSetting.getIndex()) {
                this.m_abtns[this.m_ftsPaneSetting.getIndex()] = tabButton3;
            }
        }
        if (this.m_gloPaneSetting != null) {
            final TabButton tabButton4 = new TabButton(s4, this);
            tabButton4.addButtonPushEventListener(this);
            if (4 > this.m_gloPaneSetting.getIndex()) {
                this.m_abtns[this.m_gloPaneSetting.getIndex()] = tabButton4;
            }
        }
        int n = 0;
        do {
            if (this.m_abtns[n] != null) {
                this.m_pnlTabs.add(this.m_abtns[n]);
            }
        } while (++n < 4);
        if (this.m_bShowTab) {
            this.add("North", this.m_pnlTabs);
        }
        final Rectangle bounds = this.bounds();
        int n2 = 1;
        int n3 = 0;
        do {
            if (this.m_abtns[n3] != null) {
                this.rods_reshape(this.m_abtns[n3], n2, bounds.y, this.m_abtns[n3].preferredSize().width, 25);
                n2 += this.m_abtns[n3].bounds().width;
            }
        } while (++n3 < 4);
        this.rods_reshape(this.m_pnlTabs, bounds.x, bounds.y, bounds.width, 28);
        this.m_pnlTabs.requestFocus();
        int n4 = 0;
        do {
            if (this.m_abtns[n4] != null) {
                this.m_abtns[n4].active();
            }
        } while (++n4 < 4);
        int n5 = 0;
        do {
            if (this.m_abtns[n5] != null) {
                this.m_abtns[n5].disactive();
            }
        } while (++n5 < 4);
        if (this.m_abtns[this.m_nFirstPane] != null) {
            this.notifyButtonPushEvent(new ButtonPushEvent(this.m_abtns[this.m_nFirstPane], 0, 0));
        }
    }
    
    public void Command(final String s, final String s2) {
        this.Command(s, s2, null);
    }
    
    public synchronized void Command(final String s, final String s2, final String s3) {
        if (this.m_commandBuffer != null) {
            this.m_commandBuffer.putCommand(new WhCommand(s, s2, s3));
        }
    }
    
    public void Command(final String s) {
        this.Command(s, null, null);
    }
    
    public void printVMInfo() {
        try {
            System.out.println(System.getProperty("java.version"));
            System.out.println(System.getProperty("java.vendor"));
            System.out.println(System.getProperty("java.vendor.url"));
            System.out.println(System.getProperty("java.home"));
            System.out.println(System.getProperty("java.class.path"));
            System.out.println(System.getProperty("java.class.version"));
            System.out.println(System.getProperty("os.name"));
            System.out.println(System.getProperty("os.arch"));
            System.out.println(System.getProperty("os.version"));
            System.out.println(System.getProperty("user.name"));
            System.out.println(System.getProperty("user.home"));
            System.out.println(System.getProperty("user.dir"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void paint(final Graphics graphics) {
        System.out.println("!!!Applet paint!!!");
        super.paint(graphics);
    }
    
    public void destroy() {
        System.out.println("!!!Applet Destroy!!!");
        try {
            System.gc();
            Runtime.getRuntime().gc();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected void rods_reshape(final Component component, final int n, final int n2, final int n3, final int n4) {
        component.move(n, n2);
        component.resize(n3, n4);
    }
    
    public WebHelp() {
        this.m_bShowTab = true;
        this.m_strProjectFile = null;
        this.m_tocView = null;
        this.m_indexView = null;
        this.m_ftsView = null;
        this.m_gloView = null;
        this.m_pnlNav = null;
        this.m_pnlTabs = null;
        this.m_clLayout = null;
        this.m_sLangId = "";
        this.m_bMustHaveSize = false;
        this.m_bCanAccessJSObject = false;
        this.m_bCanAccessJSObjectChecked = false;
        this.m_CommandThread = null;
    }
    
    private void DoContents() {
        if (this.m_tocPaneSetting != null) {
            this.activeTabButton(this.m_tocPaneSetting.getIndex());
            if (this.m_pnlNav == null) {
                this.m_clLayout = new CardLayout();
                (this.m_pnlNav = new Panel()).setLayout(this.m_clLayout);
                this.add("Center", this.m_pnlNav);
            }
            this.m_bMustHaveSize = true;
            this.RepaintTabs();
            if (this.m_tocView == null) {
                this.m_tocView = new TocView(this.m_vProjects, (TocViewSkin)this.m_tocPaneSetting.getViewSkin());
                this.m_pnlNav.add(ResourceLib.GetRes("Contents"), this.m_tocView);
                this.showStatus(ResourceLib.GetRes("LoadingContents"));
            }
            this.m_clLayout.show(this.m_pnlNav, ResourceLib.GetRes("Contents"));
            if (this.m_tocView != null) {
                this.m_tocView.requestFocus();
            }
        }
    }
    
    public void resize(int getWidth, int getHeight) {
        System.out.println("!!!Applet Resize!!!");
        if (System.getProperty("java.vendor").indexOf("Netscape") != -1) {
            if (this.CanQueryHeighAndWidthForNS()) {
                try {
                    JSObject.getWindow((Applet)this).setMember("gbLoading", (Object)"Phase1");
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        else if (System.getProperty("java.vendor").indexOf("Microsoft") != -1 && System.getProperty("java.version").startsWith("1.1")) {
            try {
                JSObject.getWindow((Applet)this).setMember("gbLoading", (Object)"Phase1");
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        try {
            if (this.CanQueryHeighAndWidthForNS()) {
                final JavaScriptAccess javaScriptAccess = new JavaScriptAccess(this);
                getHeight = javaScriptAccess.GetHeight(getHeight);
                getWidth = javaScriptAccess.GetWidth(getWidth);
            }
            super.resize(getWidth, getHeight);
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
    }
    
    public synchronized boolean checkSize() {
        if (this.bounds().width == 0 && this.bounds().height == 0) {
            final String string = this.getDocumentBase().toString();
            final int lastIndex = string.lastIndexOf(".");
            final String string2 = string.substring(0, lastIndex) + "f" + string.substring(lastIndex, string.length());
            try {
                this.getAppletContext().showDocument(new URL(string2), "_self");
                return false;
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }
    
    private void DoSearch(final String searchString) {
        if (this.m_ftsPaneSetting != null) {
            this.activeTabButton(this.m_ftsPaneSetting.getIndex());
            if (this.m_pnlNav == null) {
                this.m_clLayout = new CardLayout();
                (this.m_pnlNav = new Panel()).setLayout(this.m_clLayout);
                this.add("Center", this.m_pnlNav);
            }
            this.m_bMustHaveSize = true;
            if (this.m_ftsView == null) {
                this.m_ftsView = new FtsView(this.m_vProjects, (FtsViewSkin)this.m_ftsPaneSetting.getViewSkin());
                this.m_pnlNav.add(ResourceLib.GetRes("Search"), this.m_ftsView);
                this.showStatus(ResourceLib.GetRes("LoadingFTS"));
            }
            if (searchString != null && searchString.length() != 0) {
                this.m_ftsView.setSearchString(searchString);
            }
            this.m_clLayout.show(this.m_pnlNav, ResourceLib.GetRes("Search"));
            this.repaint();
            this.RepaintTabs();
            if (this.m_ftsView != null) {
                this.m_ftsView.requestFocus();
            }
        }
    }
    
    public void myrun() {
        try {
            System.gc();
            Runtime.getRuntime().gc();
            ImageCache.createInstance(this);
            BsscXML.setDocumentBase(this.getDocumentBase());
            ResourceLib.InitRes();
            final String parameter = this.getParameter("ResourceFile");
            if (parameter != null) {
                ResourceLib.LoadResource(parameter);
            }
            this.m_tocPaneSetting = ResourceLib.getPaneSetting("toc");
            this.m_idxPaneSetting = ResourceLib.getPaneSetting("index");
            this.m_ftsPaneSetting = ResourceLib.getPaneSetting("fts");
            this.m_gloPaneSetting = ResourceLib.getPaneSetting("glossary");
            this.m_nFirstPane = ResourceLib.getFirstShowPaneIndex();
            String parameter2 = this.getParameter("Frame");
            if (parameter2 == null) {
                parameter2 = "bsscright";
            }
            BsscHelpRedirector.initRedirector(this, parameter2);
            this.DoNavPane();
            this.show();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void RepaintTabs() {
        if (this.m_bShowTab) {
            if (this.m_pnlNav != null) {
                this.m_pnlNav.paintAll(this.m_pnlNav.getGraphics());
            }
            int n = 0;
            do {
                if (this.m_abtns[n] != null) {
                    this.m_abtns[n].paint(this.m_abtns[n].getGraphics());
                }
            } while (++n < 4);
        }
    }
    
    private void DoIndex() {
        if (this.m_idxPaneSetting != null) {
            this.activeTabButton(this.m_idxPaneSetting.getIndex());
            if (this.m_pnlNav == null) {
                this.m_clLayout = new CardLayout();
                (this.m_pnlNav = new Panel()).setLayout(this.m_clLayout);
                this.add("Center", this.m_pnlNav);
            }
            this.m_bMustHaveSize = true;
            if (this.m_indexView == null) {
                this.m_indexView = new IndexView(this.m_vProjects, (IndexViewSkin)this.m_idxPaneSetting.getViewSkin());
                this.m_pnlNav.add(ResourceLib.GetRes("Index"), this.m_indexView);
                this.showStatus(ResourceLib.GetRes("LoadingIndex"));
            }
            this.m_clLayout.show(this.m_pnlNav, ResourceLib.GetRes("Index"));
            this.m_pnlNav.paintAll(this.m_pnlNav.getGraphics());
            this.RepaintTabs();
            if (this.m_indexView != null) {
                this.m_indexView.requestFocus();
            }
        }
    }
    
    public boolean GoNext(final Object o) {
        if (this.m_pnlTabs == null) {
            return false;
        }
        int i = 0;
        while (i < this.m_pnlTabs.getComponentCount()) {
            if (this.m_pnlTabs.getComponent(i) == o) {
                if (i != this.m_pnlTabs.getComponentCount() - 1) {
                    final Component component = this.m_pnlTabs.getComponent(i + 1);
                    component.requestFocus();
                    this.notifyButtonPushEvent(new ButtonPushEvent(component, 0, 0));
                    return true;
                }
                return false;
            }
            else {
                ++i;
            }
        }
        return false;
    }
    
    private void DoSync(final String s, final String s2) {
        if (this.m_tocView != null) {
            this.m_tocView.sync(s, s2);
        }
    }
    
    public void run() {
        while (true) {
            try {
                while (true) {
                    WhCommand command;
                    while ((command = this.m_commandBuffer.getCommand()) == null) {
                        Thread.currentThread();
                        Thread.sleep(100L);
                    }
                    final String name = command.getName();
                    final String param0 = command.getParam0();
                    final String param2 = command.getParam1();
                    if (name.equalsIgnoreCase("SyncToc")) {
                        this.DoSync(param0, param2);
                    }
                    else if (name.equalsIgnoreCase("Contents")) {
                        this.DoContents();
                    }
                    else if (name.equalsIgnoreCase("Index")) {
                        this.DoIndex();
                    }
                    else if (name.equalsIgnoreCase("Search")) {
                        this.DoSearch(param0);
                    }
                    else if (name.equalsIgnoreCase("Glossary")) {
                        this.DoGlossary();
                    }
                    else {
                        if (name.equalsIgnoreCase("Quit")) {
                            break;
                        }
                        continue;
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    public void init() {
        this.m_commandBuffer = new CommandBuffer();
        System.out.println("!!!Applet Init!!!");
        if (System.getProperty("java.vendor").indexOf("Netscape") != -1) {
            this.myrun();
        }
    }
    
    public boolean GoPrev(final Object o) {
        if (this.m_pnlTabs == null) {
            return false;
        }
        int i = 0;
        while (i < this.m_pnlTabs.getComponentCount()) {
            if (this.m_pnlTabs.getComponent(i) == o) {
                if (i != 0) {
                    final Component component = this.m_pnlTabs.getComponent(i - 1);
                    component.requestFocus();
                    this.notifyButtonPushEvent(new ButtonPushEvent(component, 0, 0));
                    return true;
                }
                return false;
            }
            else {
                ++i;
            }
        }
        return false;
    }
    
    public synchronized void resizeForNS(final int n, final int n2) {
        try {
            if (System.getProperty("java.vendor").indexOf("Netscape") != -1) {
                this.getParent().reshape(0, 0, n, n2);
            }
            super.reshape(0, 0, n, n2);
            this.validate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private boolean _checkAccess() {
        if (System.getProperty("java.vendor").startsWith("Netscape")) {
            if (!System.getProperty("os.name").startsWith("Windows")) {
                if (!System.getProperty("os.name").startsWith("Mac")) {
                    return this.m_bCanAccessJSObject;
                }
            }
            try {
                if (this.getParameter("BrowserVersion") == null || !this.getParameter("BrowserVersion").equalsIgnoreCase("4.6")) {
                    JSObject.getWindow((Applet)this);
                    this.m_bCanAccessJSObject = true;
                }
            }
            catch (Exception ex) {
                System.gc();
                ex.printStackTrace();
            }
        }
        return this.m_bCanAccessJSObject;
    }
    
    public void loadProject(final String fileName) {
        try {
            Project.setFileName(fileName);
            (this.m_vProjects = new Vector()).addElement(new Project(URLFileHandler.makeURL(BsscXML.getDocumentBase(), fileName, null)));
            int i = 0;
            do {
                final Project project = this.m_vProjects.elementAt(i);
                try {
                    project.process();
                    if (i == 0) {
                        this.m_sLangId = project.getLangId();
                    }
                    else {
                        final String langId = project.getLangId();
                        if (langId.length() != 0 && !this.m_sLangId.equals(langId)) {
                            System.out.println("The Project:" + project.getURL() + "is using a different language to the main project, which will cause the index and full text search functionality to be disabled for this remote project");
                        }
                    }
                    final URL url = project.getURL();
                    final Enumeration elements = project.getRemoteProject().elements();
                    while (elements.hasMoreElements()) {
                        final String nextElement = elements.nextElement();
                        if (nextElement instanceof String) {
                            final URL url2 = URLFileHandler.makeURL(url, nextElement + fileName, null);
                            boolean b = false;
                            final Enumeration<Project> elements2 = this.m_vProjects.elements();
                            while (elements2.hasMoreElements()) {
                                final Project nextElement2 = elements2.nextElement();
                                if (nextElement2 instanceof Project && nextElement2.getURL().equals(url2)) {
                                    b = true;
                                    break;
                                }
                            }
                            if (b) {
                                continue;
                            }
                            this.m_vProjects.addElement(new Project(url2));
                        }
                    }
                    ++i;
                }
                catch (Exception ex) {
                    this.m_vProjects.removeElementAt(i);
                    ex.printStackTrace();
                }
            } while (i < this.m_vProjects.size());
            System.out.println("Well done!");
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    class JavaScriptAccess
    {
        Applet m_a;
        
        JavaScriptAccess(final Applet a) {
            WebHelp.this.getClass();
            this.m_a = a;
        }
        
        public int GetHeight(final int n) {
            try {
                return (int)(Object)Double.valueOf(JSObject.getWindow(this.m_a).eval("innerHeight").toString());
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return n;
            }
        }
        
        public int GetWidth(final int n) {
            try {
                return (int)(Object)Double.valueOf(JSObject.getWindow(this.m_a).eval("innerWidth").toString());
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return n;
            }
        }
    }
}
