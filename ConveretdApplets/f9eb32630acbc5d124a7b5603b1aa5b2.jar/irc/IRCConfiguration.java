// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.awt.Image;
import java.util.Locale;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Font;
import irc.security.SecurityProvider;
import java.util.Hashtable;

public class IRCConfiguration
{
    public static final int TILING_CENTER = 0;
    public static final int TILING_STRETCH = 1;
    public static final int TILING_TILE = 2;
    public static final int TILING_FIXED = 3;
    public static final int TILING_HORIZONTAL_LEFT = 0;
    public static final int TILING_HORIZONTAL_RIGHT = 256;
    public static final int TILING_VERTICAL_UP = 0;
    public static final int TILING_VERTICAL_DOWN = 512;
    private NullItem NULL_ITEM;
    private TextProvider _textProvider;
    private ImageLoader _loader;
    private URLHandler _handler;
    private FileHandler _file;
    private AudioConfiguration _audioConfig;
    private RuleList _backgroundImageRules;
    private RuleList _backgroundTilingRules;
    private RuleList _colorsRules;
    private RuleList _fontRules;
    private SmileyTable _table;
    private ListHandler _mayJoinList;
    private ListHandler _mayLeaveList;
    private ListHandler _mayCommandList;
    private Hashtable _htable;
    private SecurityProvider _provider;
    private ParameterProvider _paramProvider;
    private ParameterProvider _interfaceParamProvider;
    private String _guiInfoString;
    private String[] _initCommands;
    
    public IRCConfiguration(final TextProvider textProvider, final URLHandler handler, final ImageLoader loader, final SoundHandler soundHandler, final FileHandler file, final ParameterProvider paramProvider, final ParameterProvider interfaceParamProvider) {
        this.NULL_ITEM = new NullItem();
        this._provider = new SecurityProvider();
        this._paramProvider = paramProvider;
        this._interfaceParamProvider = interfaceParamProvider;
        this._htable = new Hashtable();
        this._backgroundImageRules = new RuleList();
        (this._backgroundTilingRules = new RuleList()).setDefaultValue(new Integer(0));
        (this._fontRules = new RuleList()).setDefaultValue(new Font("Monospaced", 0, 12));
        this._colorsRules = new RuleList();
        final Color[] defaultValue = new Color[16];
        this.loadDefaultColors(defaultValue);
        this._colorsRules.setDefaultValue(defaultValue);
        this._audioConfig = new AudioConfiguration(soundHandler);
        this._table = new SmileyTable();
        this._file = file;
        this._loader = loader;
        this._textProvider = textProvider;
        this._handler = handler;
        this._guiInfoString = "";
        this._initCommands = new String[0];
    }
    
    public static IRCConfiguration createDummyIRCConfiguration() {
        try {
            return new ConfigurationLoader(new StreamParameterProvider(null), new NullURLHandler(), new AWTImageLoader(), new NullSoundHandler(), new LocalFileHandler()).loadIRCConfiguration();
        }
        catch (Exception ex) {
            throw new Error("Error creating dummy IRCConfiguration : " + ex);
        }
    }
    
    public String[] getInitialization() {
        return this._initCommands;
    }
    
    public void setInitialisation(final String[] initCommands) {
        this._initCommands = initCommands;
    }
    
    public String getGUIInfoString() {
        return this._guiInfoString;
    }
    
    public void setGUIInfoString(final String guiInfoString) {
        this._guiInfoString = guiInfoString;
    }
    
    public FileHandler getFileHandler() {
        return this._file;
    }
    
    public void displayAboutPage() {
        new AboutDialog(this);
    }
    
    public ParameterProvider getParameterProvider() {
        return this._paramProvider;
    }
    
    public ParameterProvider getInterfaceParameterProvider() {
        return this._interfaceParamProvider;
    }
    
    public int getVersionHigh() {
        return 2;
    }
    
    public int getVersionMed() {
        return 2;
    }
    
    public int getVersionLow() {
        return 1;
    }
    
    public String getVersionModifiers() {
        return "";
    }
    
    public String getVersion() {
        return this.getVersionHigh() + "." + this.getVersionMed() + "." + this.getVersionLow() + this.getVersionModifiers();
    }
    
    public SecurityProvider getSecurityProvider() {
        return this._provider;
    }
    
    public synchronized void set(final String s, Object null_ITEM) {
        if (null_ITEM == null) {
            null_ITEM = this.NULL_ITEM;
        }
        this._htable.put(s.toLowerCase(Locale.ENGLISH), null_ITEM);
    }
    
    public synchronized void set(final String s, final int n) {
        this.set(s, new Integer(n));
    }
    
    public synchronized void set(final String s, final boolean b) {
        this.set(s, new Boolean(b));
    }
    
    public synchronized Object get(final String s) {
        NullItem value = this._htable.get(s.toLowerCase(Locale.ENGLISH));
        if (value == null) {
            throw new RuntimeException("Unknown configuration property " + s);
        }
        if (value == this.NULL_ITEM) {
            value = null;
        }
        return value;
    }
    
    public synchronized int getI(final String s) {
        return (int)this.get(s);
    }
    
    public synchronized boolean getB(final String s) {
        return (boolean)this.get(s);
    }
    
    public synchronized String getS(final String s) {
        return (String)this.get(s);
    }
    
    public synchronized boolean mayLeave(final String s) {
        return this._mayLeaveList.inList(s);
    }
    
    public synchronized boolean mayJoin(final String s) {
        return this._mayJoinList.inList(s);
    }
    
    public synchronized void setJoinList(final String s) {
        this._mayJoinList = new ListHandler(s);
    }
    
    public synchronized void setLeaveList(final String s) {
        this._mayLeaveList = new ListHandler(s);
    }
    
    public synchronized void setCommandList(final String s) {
        this._mayCommandList = new ListHandler(s);
    }
    
    public synchronized boolean mayCommand(String substring) {
        if (substring.startsWith("/")) {
            substring = substring.substring(1);
        }
        return this._mayCommandList.inList(substring);
    }
    
    public synchronized Image getBackgroundImage(final String s, final String s2) {
        if (!this.getB("style:backgroundImage")) {
            return null;
        }
        return (Image)this._backgroundImageRules.findValue(new String[] { s, s2 });
    }
    
    public synchronized int getBackgroundTiling(final String s, final String s2) {
        return (int)this._backgroundTilingRules.findValue(new String[] { s, s2 });
    }
    
    public synchronized void setBackgroundImage(final String s, final String s2, final String s3) {
        this._backgroundImageRules.addRule(new String[] { s, s2 }, this.getImageLoader().getImage(s3));
    }
    
    public synchronized void setBackgroundTiling(final String s, final String s2, final int n) {
        this._backgroundTilingRules.addRule(new String[] { s, s2 }, new Integer(n));
    }
    
    public synchronized void setFont(final String s, final String s2, final Font font) {
        this._fontRules.addRule(new String[] { s, s2 }, font);
    }
    
    public synchronized String formatASL(String s) {
        final String s2 = this.getS("noasldisplayprefix");
        if (s2.length() > 0 && s.startsWith(s2)) {
            return "";
        }
        final String s3 = this.getS("aslseparatorstring");
        if (s3.length() > 0) {
            final int index = s.indexOf(s3);
            if (index >= 0) {
                s = s.substring(0, index);
            }
        }
        final String s4 = s;
        final int index2 = s.indexOf(32);
        if (index2 < 0) {
            return s4;
        }
        final String trim = s.substring(0, index2).trim();
        s = s.substring(index2 + 1).trim();
        final int index3 = s.indexOf(32);
        if (index3 < 0) {
            return s4;
        }
        final String lowerCase = s.substring(0, index3).trim().toLowerCase(Locale.ENGLISH);
        final String trim2 = s.substring(index3 + 1).trim();
        int n;
        if (lowerCase.equals(this.getS("aslmale").toLowerCase(Locale.ENGLISH))) {
            n = 2049;
        }
        else if (lowerCase.equals(this.getS("aslfemale").toLowerCase(Locale.ENGLISH))) {
            n = 2050;
        }
        else {
            if (!lowerCase.equals(this.getS("aslunknown").toLowerCase(Locale.ENGLISH))) {
                return s4;
            }
            n = 2051;
        }
        return this.getText(n, trim, trim2);
    }
    
    public synchronized Color getASLColor(String s, final Color color, final Color color2, final Color color3) {
        final int index = s.indexOf(32);
        if (index < 0) {
            return color3;
        }
        s = s.substring(index).trim();
        final int index2 = s.indexOf(32);
        if (index2 < 0) {
            return color3;
        }
        s = s.substring(0, index2).trim().toLowerCase(Locale.ENGLISH);
        if (s.equals(this.getS("aslmale").toLowerCase(Locale.ENGLISH))) {
            return color;
        }
        if (s.equals(this.getS("aslfemale").toLowerCase(Locale.ENGLISH))) {
            return color2;
        }
        return color3;
    }
    
    public AudioConfiguration getAudioConfiguration() {
        return this._audioConfig;
    }
    
    public synchronized StyleContext getDefaultStyleContext() {
        return this.getStyleContext("", "");
    }
    
    public synchronized StyleContext getStyleContext(final String type, final String name) {
        final StyleContext styleContext = new StyleContext();
        styleContext.type = type;
        styleContext.name = name;
        return styleContext;
    }
    
    public synchronized void loadDefaultColors(final Color[] array) {
        array[0] = new Color(16777215);
        array[1] = new Color(0);
        array[2] = new Color(127);
        array[3] = new Color(37632);
        array[4] = new Color(16711680);
        array[5] = new Color(8323072);
        array[6] = new Color(10223772);
        array[7] = new Color(16547584);
        array[8] = new Color(16776960);
        array[9] = new Color(64512);
        array[10] = new Color(37779);
        array[11] = new Color(65535);
        array[12] = new Color(252);
        array[13] = new Color(16711935);
        array[14] = new Color(8355711);
        array[15] = new Color(13816530);
    }
    
    public synchronized void setSourceColor(final String s, final String s2, final Color[] array) {
        this._colorsRules.addRule(new String[] { s, s2 }, array);
    }
    
    public synchronized Color[] getStyleColors(final StyleContext styleContext) {
        return (Color[])this._colorsRules.findValue(new String[] { styleContext.type, styleContext.name });
    }
    
    public synchronized Font getStyleFont(final StyleContext styleContext) {
        return (Font)this._fontRules.findValue(new String[] { styleContext.type, styleContext.name });
    }
    
    public synchronized Image getStyleBackgroundImage(final StyleContext styleContext) {
        return this.getBackgroundImage(styleContext.type, styleContext.name);
    }
    
    public synchronized int getStyleBackgroundTiling(final StyleContext styleContext) {
        return this.getBackgroundTiling(styleContext.type, styleContext.name);
    }
    
    public synchronized void addSmiley(final String s, final String s2) {
        this._table.addSmiley(s, this._loader.getImage(s2));
    }
    
    public ImageLoader getImageLoader() {
        return this._loader;
    }
    
    public SmileyTable getSmileyTable() {
        return this._table;
    }
    
    public synchronized boolean getASLMaster() {
        return this.getB("asl") | this.getB("style:floatingasl");
    }
    
    public URLHandler getURLHandler() {
        return this._handler;
    }
    
    public TextProvider getTextProvider() {
        return this._textProvider;
    }
    
    public void internalError(final String s, final Throwable t, final String s2) {
        System.err.println("************ Internal error ************");
        System.err.println("Please submit a bug report to " + s2 + " including the following information:");
        System.err.println("Message:");
        System.err.println(s);
        if (t != null) {
            System.err.println("Root cause:");
            t.printStackTrace();
        }
        System.err.println("Stack trace:");
        Thread.dumpStack();
    }
    
    public synchronized String getText(final int n) {
        return this._textProvider.getString(n);
    }
    
    public synchronized String getText(final int n, final String s) {
        return this._textProvider.getString(n, s);
    }
    
    public synchronized String getText(final int n, final String s, final String s2) {
        return this._textProvider.getString(n, s, s2);
    }
    
    public synchronized String getText(final int n, final String s, final String s2, final String s3) {
        return this._textProvider.getString(n, s, s2, s3);
    }
}
