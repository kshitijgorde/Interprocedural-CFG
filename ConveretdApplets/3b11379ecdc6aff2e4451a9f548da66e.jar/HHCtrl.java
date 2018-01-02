import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Font;
import java.util.Properties;
import java.util.Vector;
import java.awt.Button;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class HHCtrl extends Applet implements Runnable
{
    private static final int CMD_CONTENTS = 0;
    private static final int CMD_INDEX = 1;
    private static final int CMD_RELATED = 2;
    private Dimension m_Size;
    private boolean fLoaded;
    private Image m_bgImage;
    private Color m_fontColor;
    private long m_exWinStyle;
    private long m_winStyle;
    private long[] m_flags;
    private boolean fSized;
    TreeView m_tview;
    private int m_itemHeight;
    private int m_style;
    private int m_redrawMode;
    private int m_clickMode;
    private int m_sizeMode;
    private IndexPanel m_index;
    private int m_autoKey;
    private Color m_bgcolor;
    private Button btnRelated;
    private RelatedDialog m_dlgRelated;
    private Vector q_commands;
    private Vector q_objects;
    private Thread m_workerThread;
    private Properties m_locStrings;
    static final int WCMD_LOADLIST = 1;
    static final int WCMD_SHOWDOC = 2;
    static final int WCMD_SHOWURL = 3;
    static final int WCMD_CLICK = 4;
    private int m_command;
    private String m_button;
    private String m_targetFrame;
    private String m_initState;
    private int m_autoExpandLevel;
    private Vector m_itemParams;
    private Vector m_relatedParams;
    private Font m_font;
    private final int maxWidth = 1500;
    private final int maxHeight = 1500;
    private final int minWidth = 50;
    private final int minHeight = 50;
    private final String PARAM_command = "Command";
    private final String PARAM_button = "button";
    private final String PARAM_item1 = "Item1";
    private final String PARAM_background = "Background";
    private final String PARAM_backgroundimage = "BackgroundImage";
    private final String PARAM_properties = "Properties";
    private final String PARAM_font = "Font";
    private final String PARAM_flags = "Flags";
    private final String PARAM_frame = "Frame";
    private final String PARAM_sizemode = "SizeMode";
    private final String m_engStrings;
    
    void setWinStyle(final long winStyle) {
        this.m_winStyle = winStyle;
        if ((this.m_winStyle & 0x200L) > 0L && this.m_command == 0) {
            this.m_tview.reshape(2, 2, this.size().width - 4, this.size().height - 4);
            this.repaint();
            return;
        }
        this.m_tview.reshape(0, 0, this.size().width, this.size().height);
        this.repaint();
    }
    
    public void stop() {
        if (this.m_workerThread != null) {
            this.m_workerThread.stop();
            this.m_workerThread = null;
        }
        if (this.m_command == 2 && this.m_dlgRelated != null) {
            this.m_dlgRelated.hide();
        }
    }
    
    private synchronized void addJob(final int n, final Object o) {
        this.q_commands.addElement(new Integer(n));
        this.q_objects.addElement(o);
        this.notifyAll();
    }
    
    private void initContents() {
        this.setLayout(null);
        (this.m_tview = new TreeView(this)).setSize(new Dimension(this.size().width, this.size().height));
        this.add(this.m_tview);
        this.m_tview.addControls();
        if ((this.m_winStyle & 0x200L) > 0L && this.m_sizeMode == 0) {
            this.m_tview.reshape(2, 2, this.size().width - 4, this.size().height - 4);
        }
        else if (this.m_sizeMode == 0) {
            this.m_tview.reshape(0, 0, this.size().width, this.size().height);
        }
        this.m_tview.setBackground(this.m_bgcolor);
        this.m_tview.setBackgroundImage(this.m_bgImage);
        this.m_tview.setItemHeight(this.m_itemHeight);
        this.m_tview.setStyle(this.m_style);
        this.m_tview.setStyles(this.m_winStyle, this.m_exWinStyle);
        this.m_tview.setRedrawMode(this.m_redrawMode);
        this.m_tview.setClickMode(this.m_clickMode);
        this.m_tview.setAutoExpandLevel(this.m_autoExpandLevel);
        this.m_tview.setFont(this.m_font);
        this.repaint();
    }
    
    public boolean gotFocus(final Event event, final Object o) {
        return this.m_tview.gotFocus(event, o);
    }
    
    public boolean lostFocus(final Event event, final Object o) {
        return this.m_tview.lostFocus(event, o);
    }
    
    private synchronized void runJob() {
        while (this.q_commands.size() == 0) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
        final int intValue = this.q_commands.elementAt(0);
        final String element = this.q_objects.elementAt(0);
        this.q_commands.removeElementAt(0);
        this.q_objects.removeElementAt(0);
        switch (intValue) {
            case 1: {
                this.fLoaded = true;
                this.initList();
            }
            case 2: {
                this.loadElement(element);
            }
            case 3: {
                this.loadURL(element, this.m_targetFrame);
            }
            case 4: {
                this.showRelated();
            }
            default: {}
        }
    }
    
    void initList() {
        boolean loadFromHHC = false;
        if (this.m_itemParams.size() > 0 && this.m_itemParams.elementAt(0).compareTo("") != 0) {
            this.showStatus(this.getString("cnt.load") + ": " + this.m_itemParams.elementAt(0));
            try {
                loadFromHHC = this.m_tview.loadFromHHC(new URL(this.getDocumentBase(), this.m_itemParams.elementAt(0)));
                if (this.m_tview.getFrame().compareTo("") != 0) {
                    this.m_targetFrame = this.m_tview.getFrame();
                }
            }
            catch (MalformedURLException ex) {
                this.showStatus(this.getString("cnt.load.err") + " " + this.m_itemParams.elementAt(0));
            }
        }
        if (!loadFromHHC) {
            this.showStatus(this.getString("cnt.load.err") + " " + this.m_itemParams.elementAt(0));
        }
        this.repaint();
        this.m_tview.repaint();
    }
    
    private Font parseFont(String s) {
        final int n = 12;
        final int n2 = 5;
        if (s == null) {
            this.m_fontColor = Color.black;
            return new Font("Helvetica", 0, 12);
        }
        String s2 = "Helvetica";
        int int1 = n;
        int n3 = 0;
        int n4 = s.indexOf(",");
        if (n4 > 0) {
            String substring;
            if (n4 != -1) {
                substring = s.substring(0, n4);
            }
            else {
                substring = s;
            }
            if (substring.length() > 0) {
                s2 = substring;
            }
        }
        if (n4 >= 0) {
            s = s.substring(n4 + 1);
            n4 = s.indexOf(",");
            String substring2;
            if (n4 != -1) {
                substring2 = s.substring(0, n4);
            }
            else {
                substring2 = s;
            }
            if (substring2.length() > 0) {
                try {
                    int1 = Integer.parseInt(substring2);
                    if (int1 < n2) {
                        int1 = n;
                    }
                }
                catch (Exception ex) {
                    int1 = n;
                }
            }
        }
        if (n4 >= 0) {
            s = s.substring(n4 + 1);
            n4 = s.indexOf(",");
        }
        if (n4 >= 0) {
            s = s.substring(n4 + 1);
            n4 = s.indexOf(",");
            String s3;
            if (n4 != -1) {
                s3 = s.substring(0, n4).trim().toUpperCase();
            }
            else {
                s3 = s.trim().toUpperCase();
            }
            if (s3.length() > 0) {
                try {
                    this.m_fontColor = new Color(Integer.parseInt(s3, 16));
                }
                catch (Exception ex2) {
                    this.m_fontColor = Color.black;
                }
            }
        }
        if (n4 >= 0) {
            s = s.substring(n4 + 1);
            final int index = s.indexOf(",");
            String s4;
            if (index != -1) {
                s4 = s.substring(0, index).trim().toUpperCase();
            }
            else {
                s4 = s.trim().toUpperCase();
            }
            n3 = 0;
            if (s4.length() > 0) {
                if (s4.indexOf("BOLD") != -1) {
                    ++n3;
                }
                if (s4.indexOf("ITALIC") != -1) {
                    n3 += 2;
                }
            }
        }
        return new Font(s2, n3, int1);
    }
    
    private void initRelated() {
        this.btnRelated = new Button(this.m_button);
        this.setLayout(new BorderLayout());
        this.add("Center", this.btnRelated);
        this.m_relatedParams = this.m_itemParams;
        this.m_dlgRelated = new RelatedDialog(this.getString("rel.dlgcaption"), this.m_targetFrame, this, this.getParentFrame());
        this.setFont(new Font("Dialog", 0, 16));
        this.m_dlgRelated.CreateControls();
    }
    
    public void paint(final Graphics graphics) {
        this.m_Size = this.size();
        if (this.m_sizeMode == 0 && this.size().width > 0 && this.size().height > 0 && this.m_command == 0) {
            this.m_tview.setRedraw(false);
            if ((this.m_winStyle & 0x200L) > 0L) {
                this.m_tview.reshape(2, 2, this.size().width - 4, this.size().height - 4);
                this.m_tview.setRedraw(true);
                this.m_tview.setSize(new Dimension(this.size().width - 4, this.size().height - 4));
            }
            else {
                this.m_tview.reshape(0, 0, this.size().width, this.size().height);
                this.m_tview.setRedraw(true);
                this.m_tview.setSize(new Dimension(this.size().width, this.size().height));
            }
            this.m_tview.doValidate();
        }
        if ((this.m_winStyle & 0x200L) > 0L && this.m_command == 0) {
            graphics.setColor(Color.gray);
            graphics.drawLine(0, 0, 0, this.m_Size.height);
            graphics.drawLine(0, 0, this.m_Size.width, 0);
            graphics.setColor(Color.black);
            graphics.drawLine(1, 1, this.m_Size.width - 2, 1);
            graphics.drawLine(1, 1, 1, this.m_Size.height - 2);
            graphics.setColor(Color.white);
            graphics.drawLine(0, this.m_Size.height - 1, this.m_Size.width, this.m_Size.height - 1);
            graphics.drawLine(this.m_Size.width - 1, 0, this.m_Size.width - 1, this.m_Size.height);
            graphics.setColor(Color.lightGray);
            graphics.drawLine(1, this.m_Size.height - 2, this.m_Size.width - 2, this.m_Size.height - 2);
            graphics.drawLine(this.m_Size.width - 2, 1, 1, this.m_Size.height - 2);
        }
    }
    
    private void initIndex() {
        this.m_winStyle = 0L;
        this.setLayout(new BorderLayout());
        (this.m_index = new IndexPanel(this)).setBackground(this.m_bgcolor);
        this.m_index.setBackgroundImage(this.m_bgImage);
        this.m_index.setFont(this.m_font, this.m_fontColor);
        this.add("Center", this.m_index);
        this.m_index.CreateControls();
        this.validate();
        this.m_dlgRelated = new RelatedDialog(this.getString("rel.dlgcaption"), this.m_targetFrame, this, this.getParentFrame());
        this.setFont(new Font("Dialog", 0, 12));
        this.m_dlgRelated.CreateControls();
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "Command", "String", "Command" }, { "button", "String", "Button" }, { "Font", "String", "Font" }, { "Flags", "String", "Flags" }, { "Background", "String", "Background" }, { "BackgroundImage", "String", "Background Image" }, { "Frame", "String", "Frame" }, { "Properties", "String", "Property file" } };
    }
    
    private void loadRelated() {
        if (this.m_dlgRelated.lstItems.getSelectedIndex() == -1) {
            return;
        }
        final String s = this.m_dlgRelated.m_itemList.elementAt(this.m_dlgRelated.lstItems.getSelectedIndex());
        if (s == null) {
            return;
        }
        final int index = s.indexOf(";");
        if (index == -1) {
            return;
        }
        this.loadURL(s.substring(index + 1), this.m_targetFrame);
    }
    
    protected String getString(final String s) {
        if (s.equalsIgnoreCase("cnt.load.status") && this.m_command == 1) {
            return this.m_locStrings.getProperty("idx.load.status2");
        }
        if (s.equalsIgnoreCase("cnt.load.status2") && this.m_command == 1) {
            return this.m_locStrings.getProperty("idx.load.status2");
        }
        return this.m_locStrings.getProperty(s);
    }
    
    public void syncURL(final String s) {
        if (this.m_command == 0) {
            this.m_tview.sync(s, 0);
        }
    }
    
    private void loadURL(String string, final String s) {
        if (System.getProperty("java.vendor").indexOf("Microsoft") != -1 && string.indexOf("#") != -1) {
            string = string.substring(0, string.indexOf("#") + 1) + string.substring(string.indexOf("#"));
        }
        try {
            final URL url = new URL(string);
            if (s.compareTo("") == 0) {
                this.getAppletContext().showDocument(url);
                return;
            }
            this.getAppletContext().showDocument(url, s);
        }
        catch (MalformedURLException ex) {
            try {
                final URL url2 = new URL(this.getDocumentBase(), string);
                if (s.compareTo("") == 0) {
                    this.getAppletContext().showDocument(url2);
                    return;
                }
                this.getAppletContext().showDocument(url2, s);
            }
            catch (MalformedURLException ex2) {
                this.showStatus(this.getString("err.badurl") + " " + string);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.m_sizeMode == 0) {
            this.paint(graphics);
        }
    }
    
    void showRelated() {
        if (this.m_relatedParams.size() > 1) {
            final Thread currentThread = Thread.currentThread();
            this.m_dlgRelated.setCallingThread(currentThread);
            this.m_dlgRelated.setItems(this.m_relatedParams);
            this.m_dlgRelated.show();
            if (this.m_dlgRelated.getStatus() == 0) {
                currentThread.suspend();
            }
            switch (this.m_dlgRelated.getStatus()) {
                case 1: {
                    this.loadRelated();
                    break;
                }
            }
        }
        if (this.m_relatedParams.size() == 1) {
            final String s = this.m_relatedParams.elementAt(0);
            final int index = s.indexOf(";");
            if (index == -1) {
                return;
            }
            this.addJob(3, s.substring(index + 1));
        }
    }
    
    private void parseFlags(String s) {
        Label_0305: {
            if (s == null) {
                this.m_flags[0] = 512L;
                this.m_flags[1] = 8388661L;
                this.m_flags[2] = -1L;
            }
            else {
                int n = s.indexOf(",");
                Label_0119: {
                    if (n > 0) {
                        String substring;
                        if (n != -1) {
                            substring = s.substring(0, n);
                        }
                        else {
                            substring = s;
                        }
                        if (substring.length() > 0) {
                            if (!substring.startsWith("0x")) {
                                if (!substring.startsWith("0X")) {
                                    break Label_0119;
                                }
                            }
                            try {
                                this.m_flags[0] = Long.parseLong(substring.substring(2), 16);
                            }
                            catch (Exception ex) {
                                this.m_flags[0] = 512L;
                            }
                        }
                    }
                }
                Label_0212: {
                    if (n >= 0) {
                        s = s.substring(n + 1);
                        n = s.indexOf(",");
                        String substring2;
                        if (n != -1) {
                            substring2 = s.substring(0, n);
                        }
                        else {
                            substring2 = s;
                        }
                        if (substring2.length() > 0) {
                            if (!substring2.startsWith("0x")) {
                                if (!substring2.startsWith("0X")) {
                                    break Label_0212;
                                }
                            }
                            try {
                                this.m_flags[1] = Long.parseLong(substring2.substring(2), 16);
                            }
                            catch (Exception ex2) {
                                this.m_flags[1] = 53L;
                            }
                        }
                    }
                }
                if (n >= 0) {
                    s = s.substring(n + 1);
                    final int index = s.indexOf(",");
                    String substring3;
                    if (index != -1) {
                        substring3 = s.substring(0, index);
                    }
                    else {
                        substring3 = s;
                    }
                    if (substring3.length() > 0) {
                        if (!substring3.startsWith("0x")) {
                            if (!substring3.startsWith("0X")) {
                                break Label_0305;
                            }
                        }
                        try {
                            this.m_flags[2] = Long.parseLong(substring3.substring(2), 16);
                        }
                        catch (Exception ex3) {
                            this.m_flags[2] = -1L;
                        }
                    }
                }
            }
        }
        this.m_winStyle = this.m_flags[0];
        this.m_exWinStyle = this.m_flags[1];
    }
    
    public void start() {
        this.m_Size = this.size();
        if (this.m_workerThread == null) {
            (this.m_workerThread = new Thread(this)).start();
        }
        this.requestFocus();
    }
    
    public String getAppletInfo() {
        return "HTML Help Java Applet Version 4.72.7346 \r\n" + "Copyright (C) 1996-1997 Microsoft Corporation. All rights reserved";
    }
    
    public boolean action(final Event event, final Object o) {
        if (this.m_command == 0) {
            if (((Element)o).m_url.compareTo("") != 0) {
                this.showStatus(this.getString("cnt.load") + ": " + ((Element)o).m_url);
                this.addJob(2, o);
            }
            else {
                this.showStatus(((Element)o).m_text);
            }
            return true;
        }
        if (this.m_command == 1) {
            if (o != null) {
                this.addJob(2, o);
            }
            return true;
        }
        if (this.m_command == 2 && event.target == this.btnRelated) {
            this.addJob(4, "");
            return true;
        }
        return false;
    }
    
    private Frame getParentFrame() {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        if (container instanceof Frame) {
            return (Frame)container;
        }
        return null;
    }
    
    public void HHClick() {
        if (this.m_command == 2) {
            this.addJob(4, "");
        }
    }
    
    public void click() {
        if (this.m_command == 2) {
            this.addJob(4, "");
        }
    }
    
    public void run() {
        if (this.m_command == 0 && !this.fLoaded) {
            this.initList();
            this.fLoaded = true;
        }
    Label_0147:
        while (true) {
            if (this.m_command != 1 || this.fLoaded) {
                break Label_0147;
            }
            try {
                this.m_index.loadFromHHC(new URL(this.getDocumentBase(), this.m_itemParams.elementAt(0)));
                if (this.m_index.getFrame().compareTo("") != 0) {
                    this.m_targetFrame = this.m_index.getFrame();
                }
                this.fLoaded = true;
                break Label_0147;
            }
            catch (MalformedURLException ex) {
                this.showStatus(this.getString("idx.load.err") + " " + this.m_itemParams.elementAt(0));
            }
            while (true) {
                try {
                    while (true) {
                        this.runJob();
                    }
                }
                catch (InterruptedException ex2) {
                    this.stop();
                    continue;
                }
                continue Label_0147;
            }
            break;
        }
    }
    
    public void init() {
        final String parameter = this.getParameter("Command");
        if (parameter != null) {
            if (parameter.toUpperCase().startsWith("RELATED TOPICS")) {
                this.m_command = 2;
            }
            if (parameter.equalsIgnoreCase("INDEX")) {
                this.m_command = 1;
            }
        }
        final String parameter2 = this.getParameter("button");
        if (parameter2 != null) {
            this.m_button = parameter2;
        }
        this.m_itemParams = new Vector();
        int n = 1;
        String parameter3;
        while ((parameter3 = this.getParameter("Item" + n++)) != null) {
            this.m_itemParams.addElement(parameter3);
        }
        if (this.m_command == 0) {
            this.m_bgcolor = Color.white;
        }
        if (this.m_command == 1) {
            this.m_bgcolor = Color.lightGray;
        }
        final String parameter4 = this.getParameter("Background");
        if (parameter4 != null) {
            try {
                final int intValue = Integer.valueOf(parameter4, 16);
                if (intValue != -1) {
                    this.m_bgcolor = new Color(intValue);
                }
            }
            catch (Exception ex) {}
        }
        final String parameter5 = this.getParameter("BackgroundImage");
        if (parameter5 != null) {
            try {
                this.m_bgImage = this.getImage(new URL(this.getDocumentBase(), parameter5));
            }
            catch (Exception ex2) {
                this.m_bgImage = null;
            }
        }
        this.m_font = this.parseFont(this.getParameter("Font"));
        this.parseFlags(this.getParameter("Flags"));
        new Color(0, 0, 128);
        final String parameter6 = this.getParameter("Frame");
        if (parameter6 != null) {
            this.m_targetFrame = parameter6;
        }
        final Properties properties = new Properties();
        try {
            properties.load(new StringBufferInputStream(this.m_engStrings));
        }
        catch (Exception ex3) {}
        this.m_locStrings = new Properties(properties);
        final String parameter7 = this.getParameter("Properties");
        if (parameter7 != null) {
            try {
                this.m_locStrings.load(Runtime.getRuntime().getLocalizedInputStream(new DataInputStream(new BufferedInputStream(new URL(this.getCodeBase(), parameter7).openStream(), 8192))));
            }
            catch (Exception ex4) {
                this.showStatus(this.getString("err.propload"));
            }
        }
        switch (this.m_command) {
            case 0: {
                this.initContents();
            }
            case 1: {
                this.initIndex();
            }
            case 2: {
                this.initRelated();
            }
            default: {}
        }
    }
    
    public boolean handleEvent(final Event event) {
        return super.handleEvent(event);
    }
    
    private void loadElement(final Object o) {
        if (((Element)o).m_related == null) {
            if (((Element)o).m_url.compareTo("") != 0) {
                this.loadURL(((Element)o).m_url, (((Element)o).m_target.compareTo("") == 0) ? this.m_targetFrame : ((Element)o).m_target);
            }
        }
        else {
            this.m_relatedParams = ((Element)o).m_related;
            new Thread(this.m_dlgRelated).start();
        }
    }
    
    public HHCtrl() {
        this.m_fontColor = Color.black;
        this.m_exWinStyle = 8388661L;
        this.m_winStyle = 512L;
        this.m_itemHeight = 18;
        this.m_style = TreeView.STYLE_WIN95;
        this.m_redrawMode = 1;
        this.m_clickMode = 1;
        this.m_bgcolor = Color.white;
        this.m_button = "";
        this.m_targetFrame = "";
        this.m_autoExpandLevel = 2;
        this.m_engStrings = "err.propload=Error loading properties\n" + "err.badurl=Invalid URL:\n" + "cnt.load=Loading\n" + "cnt.load.status=Loading contents...\n" + "cnt.load.elementname=Loading...\n" + "cnt.load.success=Contents loaded successfully.\n" + "cnt.load.status2=Loading contents\n" + "cnt.load.err=Error loading\n" + "cnt.merge.err=Error merging\n" + "cnt.merge.elementname=Loading...\n" + "cnt.merge.errelement=Cannot load section\n" + "cnt.merge.success=Section loaded successfully.\n" + "idx.load.err=Error loading\n" + "idx.load.success=Index loaded successfully\n" + "idx.load.status=Loading index...\n" + "idx.load.status2=Loading index\n" + "idx.load.elementname=Loading...\n" + "idx.display=Display\n" + "rel.dlgwidth=294\n" + "rel.dlgheight=238\n" + "rel.btnwidth=50\n" + "rel.dlgcaption=Topics Found\n" + "rel.display=Display\n" + "rel.cancel=Cancel\n" + "rel.label=Click a topic, then click Display.\n";
        this.m_flags = new long[3];
        this.q_commands = new Vector();
        this.q_objects = new Vector();
    }
    
    public boolean keyDown(final Event event, final int n) {
        return this.m_command == 0 && this.m_tview.keyDown(event, n);
    }
}
