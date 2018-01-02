// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editorapplet;

import lotus.notes.apps.editorpanel.ColorList;
import lotus.notes.apps.editorpanel.ToolTip;
import netscape.javascript.JSObject;
import netscape.security.PrivilegeManager;
import java.awt.Color;
import lotus.notes.apps.editorpanel.Utility;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Event;
import java.lang.reflect.Method;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.ms.io.clientstorage.ClientStorageManager;
import com.ms.security.PolicyEngine;
import com.ms.security.PermissionID;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import lotus.notes.util.PropertyLoader;
import java.awt.TextField;
import java.util.Properties;
import lotus.notes.apps.editor.RTEdit;
import lotus.notes.apps.editorpanel.ToolHost;
import lotus.notes.apps.editorpanel.EditorControls;
import lotus.notes.apps.editorpanel.BorderedPanel;
import java.util.Hashtable;
import java.awt.event.ActionListener;
import java.applet.Applet;

public class EditorApplet extends Applet implements ActionListener
{
    public static final boolean DEBUG = false;
    private static final String PARAM_BGCOLOR = "bgColor";
    private static final String PARAM_TEXT = "text";
    private static final String PARAM_DEFAULT_BODY_FONT = "defaultBodyFont";
    private static final String PARAM_DEFAULT_H1_FONT = "defaultH1Font";
    private static final String PARAM_DATA_URL = "InputSrc";
    private static final String PARAM_ID = "cache_id";
    private static final String PARAM_BEHAVIOR = "behavior";
    private static final String PARAM_LOCALE = "locale";
    private static final String PARAM_BROWSER = "browserCode";
    private static final String BASENAME = "editor";
    private static final String DEFAULT_STYLE = "Default";
    private static final String H1_STYLE = "Heading 1 (H1)";
    private static final String[] INTL_LANGS;
    private static final String HAIKU_DEFAULT_BODY = "serif,12";
    private static final String V5_DEFAULT_BODY = "sansserif,11";
    public boolean imageClicked;
    public boolean headlineClicked;
    public boolean linkClicked;
    public boolean spellClicked;
    public boolean bNeedsPriv;
    private static Hashtable stateTable;
    private BorderedPanel editorPanel;
    private EditorControls controls;
    private ToolHost toolHost;
    private RTEdit theEditor;
    private int behavior;
    private int browserCode;
    private String defaultBodyFont;
    private String defaultH1Font;
    private boolean textInitialized;
    private Properties localeProps;
    private TextField intlInput;
    private int appWidth;
    private int appHeight;
    private String BrowserVendor;
    private String platform;
    
    public EditorApplet() {
        this.imageClicked = false;
        this.headlineClicked = false;
        this.linkClicked = false;
        this.spellClicked = false;
        this.bNeedsPriv = false;
        this.controls = null;
        this.theEditor = null;
        this.behavior = 0;
        this.browserCode = 0;
        this.defaultBodyFont = null;
        this.defaultH1Font = null;
        this.textInitialized = false;
        this.intlInput = null;
        this.platform = "";
    }
    
    public void init() {
        this.BrowserVendor = System.getProperty("browser");
        boolean b = false;
        if (this.BrowserVendor != null && this.BrowserVendor.length() > 0) {
            this.BrowserVendor = this.BrowserVendor.toLowerCase();
            if (this.BrowserVendor.indexOf("netscape") != -1 || this.BrowserVendor.indexOf("lotus") != -1) {
                b = true;
            }
        }
        if (this.BrowserVendor != null && this.BrowserVendor.indexOf("plugin") == -1 && this.BrowserVendor.indexOf("lotus") == -1) {
            this.bNeedsPriv = (this.setInstalled() && b);
        }
        final String parameter = this.getParameter("behavior");
        if (parameter != null && parameter.equalsIgnoreCase("Haiku")) {
            this.behavior = 1;
        }
        this.defaultBodyFont = this.getParameter("defaultBodyFont");
        if (this.defaultBodyFont == null || this.defaultBodyFont.length() == 0) {
            if (this.behavior == 1) {
                this.defaultBodyFont = "serif,12";
            }
            else {
                String language = "";
                this.platform = "";
                try {
                    language = this.getLocale().getLanguage();
                }
                catch (Exception ex) {}
                try {
                    this.platform = System.getProperty("os.name");
                }
                catch (Exception ex2) {}
                if (language != null && language.length() > 0 && language.equals("th") && this.platform != null && this.platform.indexOf("Win") != -1 && this.platform.indexOf("98") != -1) {
                    this.defaultBodyFont = "DialogInput,11";
                }
                else {
                    this.defaultBodyFont = "sansserif,11";
                }
            }
        }
        this.defaultH1Font = this.getParameter("defaultH1Font");
        final String parameter2 = this.getParameter("locale");
        this.localeProps = new PropertyLoader().getProperties("editor", parameter2, new EnProperties(), this.getCodeBase());
        final String parameter3 = this.getParameter("browserCode");
        if (parameter3 != null) {
            try {
                this.browserCode = Integer.valueOf(parameter3);
            }
            catch (NumberFormatException ex3) {
                this.browserCode = 0;
            }
        }
        final String parameter4 = this.getParameter("cache_id");
        if (parameter4 != null && parameter4.length() > 0) {
            final EditState editState = EditorApplet.stateTable.remove(parameter4);
            if (editState != null) {
                this.theEditor = editState.editor;
                this.controls = (EditorControls)editState.panel;
            }
        }
        if (this.theEditor == null) {
            this.initEditor();
        }
        else {
            this.textInitialized = true;
        }
        (this.editorPanel = new BorderedPanel()).add("Center", this.theEditor);
        (this.toolHost = this.createToolHost()).setLayout(new BorderLayout());
        this.toolHost.add("North", this.controls);
        this.toolHost.add("Center", this.editorPanel);
        this.setLayout(new BorderLayout());
        this.add("Center", this.toolHost);
        final Dimension size = this.size();
        this.appWidth = size.width;
        this.appHeight = size.height;
        if (this.behavior == 0 && b && parameter2 != null && parameter2.length() > 0) {
            final String lowerCase = parameter2.toLowerCase();
            for (int i = 0; i < EditorApplet.INTL_LANGS.length; ++i) {
                if (lowerCase.indexOf(EditorApplet.INTL_LANGS[i]) != -1) {
                    this.add("South", this.intlInput = new TextField());
                    break;
                }
            }
        }
        this.theEditor.addActionListener(this);
        this.setFocus();
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
        if (parameter.indexOf("editor") == -1) {
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
    
    public boolean keyDown(final Event event, final int n) {
        boolean b = false;
        if (n == 1011 && this.intlInput != null) {
            if (event.target == this.intlInput) {
                this.getDBText();
                this.setEditable(true);
                this.setFocus();
            }
            else {
                this.intlInput.requestFocus();
            }
            b = true;
        }
        return b;
    }
    
    public boolean action(final Event event, final Object o) {
        boolean b = false;
        if (this.intlInput != null && event.target == this.intlInput) {
            this.getDBText();
            b = true;
        }
        return b;
    }
    
    public boolean gotFocus(final Event event, final Object o) {
        if (event.target == this.intlInput) {
            this.setEditable(false);
            this.theEditor.showCursor();
        }
        return super.gotFocus(event, o);
    }
    
    public boolean lostFocus(final Event event, final Object o) {
        if (event.target == this.intlInput) {
            this.setEditable(true);
        }
        return super.lostFocus(event, o);
    }
    
    private void getDBText() {
        if (this.intlInput != null) {
            final String text = this.intlInput.getText();
            if (this.theEditor != null && text.length() != 0) {
                this.theEditor.insertText(text);
            }
            this.intlInput.setText("");
        }
    }
    
    public void destroy() {
        this.theEditor.removeActionListener(this);
        final String parameter = this.getParameter("cache_id");
        if (parameter != null && parameter.length() > 0) {
            EditorApplet.stateTable.put(parameter, new EditState(this.theEditor, this.controls));
        }
        else {
            this.theEditor.destroy();
        }
        this.theEditor = null;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.appWidth != size.width || this.appHeight != size.height) {
            this.appWidth = size.width;
            this.appHeight = size.height;
            this.resize(this.appWidth, this.appHeight);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.platform.length() > 0 && this.platform.toLowerCase().indexOf("linux") != -1) {
            if (actionEvent == null) {
                return;
            }
            this.controls.updateUI();
        }
        else {
            this.controls.updateUI();
        }
    }
    
    public void setFocus() {
        if (this.theEditor != null) {
            this.theEditor.requestFocus();
        }
    }
    
    public boolean setEditorBackground(final String s) {
        boolean b = false;
        final Color decodeColor = Utility.decodeColor(s);
        if (decodeColor != null && this.theEditor != null) {
            this.theEditor.setBackground(decodeColor);
            b = true;
        }
        return b;
    }
    
    public void readText(final String s) {
        if (this.bNeedsPriv) {
            try {
                PrivilegeManager.enablePrivilege("UniversalBrowserAccess");
            }
            catch (Exception ex) {}
        }
        this.theEditor.readText(s);
        this.textInitialized = true;
    }
    
    public void setText(final String s, final String s2) {
        if (s2 != null) {
            if (s != null && s.equalsIgnoreCase("text/html")) {
                this.theEditor.setText("text/html", s2);
            }
            else {
                this.theEditor.setText("text/plain", s2);
            }
            this.setDefaultFont("Default", this.defaultBodyFont);
            this.setDefaultFont("Heading 1 (H1)", this.defaultH1Font);
        }
    }
    
    public String doYouKnowYourLines() {
        String s = "By heart";
        if (this.theEditor == null) {
            s = "I'm new at this";
        }
        else if (!this.textInitialized) {
            s = "I have no idea";
        }
        return s;
    }
    
    public String getText(final String s) throws Exception {
        return this.theEditor.getText(s);
    }
    
    public void insertImage(final String s, final String s2, final String s3) {
        this.theEditor.insertImage(this, s, s2, s3);
    }
    
    public String getCursorPos() {
        return this.theEditor.getCursorPos();
    }
    
    public boolean setCursorPos(final String cursorPos) {
        return this.theEditor.setCursorPos(cursorPos);
    }
    
    public int getBehavior() {
        return this.behavior;
    }
    
    public boolean select(final int n, final int n2) {
        return this.theEditor.select(n, n2);
    }
    
    public boolean replace(final int n, final int n2, final String s) {
        return this.theEditor.replace(n, n2, s);
    }
    
    public void setEditable(final boolean editable) {
        this.theEditor.setEditable(editable);
    }
    
    public boolean getEditable() {
        return this.theEditor.isEditable();
    }
    
    public void setUIVisible(final boolean b) {
        if (this.controls.isShowing() != b) {
            this.controls.show(b);
        }
    }
    
    public boolean insertLinkedText(final String s, final String s2) {
        final boolean editable = this.getEditable();
        if (!editable) {
            this.setEditable(true);
        }
        final boolean insertLinkedText = this.theEditor.insertLinkedText(s, s2);
        if (!editable) {
            this.setEditable(editable);
        }
        return insertLinkedText;
    }
    
    public void insertUnicode(final String s) {
        final String stringToUnicode = Utility.stringToUnicode(s);
        if (stringToUnicode != null) {
            this.theEditor.insertText(stringToUnicode);
        }
    }
    
    public String getHeadline() {
        return this.theEditor.getHeadline();
    }
    
    public String setHeadline(final String s, final String s2, final String s3) {
        return this.theEditor.setHeadline(s, s2, s3);
    }
    
    public int countHeadlines() {
        return this.theEditor.countEscObjects(3);
    }
    
    public int countImages() {
        return this.theEditor.countEscObjects(2);
    }
    
    public Properties getProperties() {
        return this.localeProps;
    }
    
    private void initEditor() {
        (this.theEditor = new RTEdit(this, true, this.behavior, this.browserCode)).setBackground(Color.white);
        this.theEditor.createHTMLStyles();
        final Color decodeColor = Utility.decodeColor(this.getParameter("bgColor"));
        if (decodeColor != null) {
            this.setBackground(decodeColor);
        }
        else {
            this.setBackground(Color.white);
        }
        this.controls = new EditorControls(this, this.theEditor);
        final String parameter = this.getParameter("text");
        if (parameter != null) {
            this.setText("text/html", parameter);
            this.textInitialized = true;
        }
        else {
            final String parameter2 = this.getParameter("name");
            Class<?> forName;
            try {
                forName = Class.forName("netscape.javascript.JSObject");
            }
            catch (ClassNotFoundException ex) {
                forName = null;
            }
            if (forName != null) {
                try {
                    if (this.bNeedsPriv) {
                        try {
                            PrivilegeManager.enablePrivilege("UniversalBrowserAccess");
                        }
                        catch (Exception ex2) {}
                    }
                    final JSObject window = JSObject.getWindow((Applet)this);
                    final String substring = parameter2.substring(3);
                    final boolean b = this.BrowserVendor != null && this.BrowserVendor.length() > 0 && this.BrowserVendor.toLowerCase().indexOf("plugin") != -1;
                    if (this.bNeedsPriv || b) {
                        final Object eval = window.eval("isFormReady('" + substring + "');");
                        if (eval != null) {
                            this.readText(eval.toString());
                        }
                        else {
                            System.out.println("Error reading field value for " + parameter2);
                            this.readText("");
                        }
                    }
                    else if (parameter2 != null && parameter2.length() > 0) {
                        int n = 5;
                        int i = 1;
                        String s = "";
                        final String string = "rto" + substring;
                        while (i != 0) {
                            try {
                                s = (String)((JSObject)window.getMember(string)).getMember("RichTextData");
                                i = 0;
                            }
                            catch (Exception ex3) {
                                if (n-- == 0) {
                                    throw new Exception();
                                }
                                Thread.sleep(1000L);
                            }
                        }
                        this.readText(s);
                    }
                }
                catch (Exception ex4) {
                    System.out.println("Unable to get data from hidden field " + parameter2 + ", please refresh web page.");
                }
            }
            this.setDefaultFont("Default", this.defaultBodyFont);
            this.setDefaultFont("Heading 1 (H1)", this.defaultH1Font);
        }
    }
    
    private ToolHost createToolHost() {
        final ToolHost toolHost = new ToolHost();
        toolHost.addTool("Tip", new ToolTip(this));
        String s = "";
        if (this.behavior == 1) {
            s = "lotus/notes/apps/editorpanel/images/";
        }
        toolHost.addTool("ColorMap", new ColorList(Utility.loadImage(this.getCodeBase(), s + "colormap.gif", this, true)));
        return toolHost;
    }
    
    private void setDefaultFont(final String s, final String s2) {
        String s3 = "TimesRoman";
        if (s2 != null && s != null) {
            final int index = s2.indexOf(44);
            if (index != -1) {
                try {
                    final String substring = s2.substring(index + 1);
                    final String substring2 = s2.substring(0, index);
                    if (substring2.equalsIgnoreCase("sansserif")) {
                        if (this.behavior == 1) {
                            s3 = "Helvetica";
                        }
                        else {
                            s3 = "Sans Serif";
                        }
                    }
                    else if (substring2.equals("DialogInput")) {
                        s3 = substring2;
                    }
                    else if (this.behavior == 0) {
                        s3 = "Serif";
                    }
                    this.theEditor.setFaceName(s, s3);
                    this.theEditor.setPointSize(s, new Integer(substring));
                }
                catch (Exception ex) {
                    System.out.println("Bad defaultFont info parameter");
                }
            }
        }
    }
    
    static {
        INTL_LANGS = new String[] { "ja", "ko", "zh" };
        EditorApplet.stateTable = new Hashtable();
    }
}
