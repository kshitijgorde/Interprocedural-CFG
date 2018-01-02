// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import java.util.Hashtable;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.util.Enumeration;
import java.net.URL;
import com.pchat.sc.WindowUtil;
import java.awt.Image;
import com.pchat.sc.StringUtil;
import pclient.main.MainClient;
import java.applet.Applet;
import java.util.Properties;

public class Config
{
    private Properties confProperties;
    private Properties serverLevelProps;
    private Properties theDefault;
    private Properties allowedAppletParms;
    private Properties denyAppletParms;
    private PrefDef prefDefault;
    private boolean serverOverride;
    private Applet guiApplet;
    private SmileDef smileDef;
    private boolean allowOverride;
    public static final String CONF_ROOT = "appletconf";
    public static final String CONF_SITES_DIR = "appsites";
    public static final String SITES_DIR = "appletconf/appsites";
    public static final String SITE_EXT = ".st";
    public static final String SITE_CONF = "site.cf";
    public static final String ROOM_EXT = ".rm";
    public static final String ROOM_CONF = "room.cf";
    public static final String AD_DIR = "panel.ad";
    public static final String IMAGE_DIR = "image.dir";
    public static final String AD_BR_CONF = "brand.cf";
    public static final String AD_TXT_CONF = "text.cf";
    public static final String AVATAR_DIR = "avatar";
    private static final String ICON_DIR = "icon";
    public static final String SMILE_DIR = "smiley";
    public static final String SMILEY_CONF = "smiley.conf";
    public static final String EMOT_CONF = "emoticon.conf";
    public static final String SOUND_DIR = "sound";
    public static final String SERVER_CONF = "applet.conf";
    public static final String LANG_DIR = "translation";
    public static final String LANG_EXT = ".txt";
    private static final String CONFIG_FILE = "appletconf/applet.conf";
    public static final String IMG_TAG = "::";
    public static final String PIPE = "|";
    public static final String COMMA = ",";
    private String[][] defaultTable;
    protected IconShop iconShop;
    private ChatContact chatContact;
    protected MainClient mainClient;
    
    public Config() {
        this.confProperties = null;
        this.theDefault = null;
        this.allowedAppletParms = null;
        this.denyAppletParms = null;
        this.prefDefault = null;
        this.serverOverride = false;
        this.smileDef = null;
        this.allowOverride = true;
        this.defaultTable = new String[0][];
        this.chatContact = new ChatContact(this);
        this.initVariables(null);
    }
    
    public Config(final MainClient mainClient, final Applet guiApplet) {
        this.confProperties = null;
        this.theDefault = null;
        this.allowedAppletParms = null;
        this.denyAppletParms = null;
        this.prefDefault = null;
        this.serverOverride = false;
        this.smileDef = null;
        this.allowOverride = true;
        this.defaultTable = new String[0][];
        this.mainClient = mainClient;
        this.chatContact = new ChatContact(this);
        this.guiApplet = guiApplet;
        this.iconShop = new IconShop(guiApplet, this);
        this.initVariables(guiApplet);
        this.loadProperties(guiApplet.getCodeBase());
        boolean b = false;
        final String property = this.serverLevelProps.getProperty("Scf.AllowDeny.SiteOverride");
        if (property != null && StringUtil.isTrue(property.trim())) {
            b = true;
        }
        if (!b) {
            this.initAllowedHtml(this.serverLevelProps);
        }
        else {
            this.initAllowedHtml(this.confProperties);
        }
        if (this.allowedAppletParms == null) {
            if (!b) {
                this.initDeniedHtml(this.serverLevelProps);
            }
            else {
                this.initDeniedHtml(this.confProperties);
            }
        }
        this.printer().print("Default Prop: " + this.theDefault);
        this.printer().print("server: " + this.serverOverride + " " + this.serverLevelProps);
        this.printer().print("Props: " + this.confProperties);
        this.printer().print("allowed html parms: " + this.allowedAppletParms);
        this.printer().print("deny html parms: " + this.denyAppletParms);
        this.serverOverride = false;
        final String property2 = this.serverLevelProps.getProperty("Scf.OverrideSrv");
        if (property2 != null && StringUtil.isTrue(property2.trim())) {
            this.serverOverride = true;
        }
    }
    
    public ChatContact printer() {
        return this.chatContact;
    }
    
    public void play(String string) {
        final String value = this.get("Net.Site");
        String string2 = "appletconf/sound";
        if (this.getBool("Ctrl.Site.Snd", false) && !StringUtil.isTrimmedEmpty(value)) {
            string2 = "appletconf/appsites/" + value + ".st" + "/" + "sound";
        }
        string = string2 + "/" + string + ".au";
        this.guiApplet.play(this.guiApplet.getCodeBase(), string);
        this.printer().print("play sound: " + string);
    }
    
    public void playServer(String string) {
        string = "appletconf/sound" + "/" + string + ".au";
        this.guiApplet.play(this.guiApplet.getCodeBase(), string);
        this.printer().print("play sound: " + string);
    }
    
    public Image getAvatar(final String s) {
        final String value = this.get("Net.Site");
        String string = "appletconf/avatar";
        if (this.getBool("Ctrl.Site.Avat", false) && !StringUtil.isTrimmedEmpty(value)) {
            string = "appletconf/appsites/" + value + ".st" + "/" + "avatar";
        }
        return this.iconShop.getImage(string, s);
    }
    
    public Image getIcon(final String s) {
        return this.iconShop.getImage("appletconf/icon", s);
    }
    
    public SmileDef getSmiley() {
        if (this.smileDef == null) {
            this.smileDef = new SmileDef(this);
        }
        return this.smileDef;
    }
    
    public Applet getApplet() {
        return this.guiApplet;
    }
    
    public static String getSitesDir() {
        return "appletconf/appsites";
    }
    
    public PrefDef getPref() {
        if (this.prefDefault == null) {
            this.prefDefault = new PrefDef(this);
        }
        return this.prefDefault;
    }
    
    public String title() {
        return this.get("Lb.Title", "Chat");
    }
    
    public void loadPage(final String s) {
        WindowUtil.loadURL(this.getApplet(), s);
    }
    
    public boolean isAdv() {
        final String property = this.confProperties.getProperty("Host.Customer", null);
        return property != null && property.trim().equalsIgnoreCase("adv");
    }
    
    public boolean isRoam() {
        return this.getBool("Ctrl.Roam", true);
    }
    
    public boolean isSecure() {
        return this.getBool("Ctrl.Secure", false);
    }
    
    public boolean isSecOnly() {
        return this.getBool("Net.SecOnly", true);
    }
    
    public boolean isAudioOnly() {
        return this.getBool("Ctrl.AuOnly", false);
    }
    
    public boolean isSimpleCSR() {
        return this.getBool("Ctrl.MainOnly", false);
    }
    
    public String getDefOrBgImg(final String s, final String s2) {
        if (this.getBool("Op.Bt.Bg", false)) {
            return s2;
        }
        return s;
    }
    
    private void loadProperties(final URL url) {
        this.readFile(url, "appletconf/applet.conf", this.confProperties);
        this.lowerCase(this.confProperties);
        this.serverLevelProps = new Properties();
        this.copyNewProps(this.confProperties, this.serverLevelProps);
        final Properties properties = new Properties();
        final String value = this.get("Net.Site");
        if (value != null) {
            this.readFile(url, "appletconf/appsites/" + value + ".st" + "/" + "site.cf", properties);
            this.copyNewProps(properties, this.confProperties);
            this.lowerCase(this.confProperties);
        }
        final Properties properties2 = new Properties();
        final String value2 = this.get("Net.Room");
        if (value2 != null && value != null) {
            this.readFile(url, "appletconf/appsites/" + value + ".st" + "/" + value2 + ".rm" + "/" + "room.cf", properties2);
            this.copyNewProps(properties2, this.confProperties);
            this.lowerCase(this.confProperties);
        }
        final String value3 = this.get("Ctrl.Language");
        if (!StringUtil.isTrimmedEmpty(value3)) {
            final Properties properties3 = new Properties();
            this.readFile(url, "appletconf/translation/" + value3 + ".txt", properties3);
            this.copyNewProps(properties3, this.confProperties);
            this.lowerCase(this.confProperties);
        }
        this.copyNewProps(properties, this.confProperties);
        this.copyNewProps(properties2, this.confProperties);
        this.lowerCase(this.confProperties);
    }
    
    private void initAllowedHtml(final Properties properties) {
        String s = "Scf.AllowedAppletParms";
        if (this.isAdv()) {
            s = "Scf.Adv.AllowedParms";
        }
        final String property = properties.getProperty(s);
        if (StringUtil.isTrimmedEmpty(property)) {
            this.allowedAppletParms = null;
            return;
        }
        this.populateCommaList(this.allowedAppletParms = new Properties(), property);
        ((Hashtable<String, String>)this.allowedAppletParms).put("Net.Site", "Net.Site");
        this.lowerCase(this.allowedAppletParms);
    }
    
    private void initDeniedHtml(final Properties properties) {
        String s = "Scf.DeniedAppletParms";
        if (this.isAdv()) {
            s = "Scf.Adv.DeniedParms";
        }
        final String property = properties.getProperty(s);
        if (StringUtil.isTrimmedEmpty(property)) {
            this.denyAppletParms = null;
            return;
        }
        this.populateCommaList(this.denyAppletParms = new Properties(), property);
        this.lowerCase(this.denyAppletParms);
    }
    
    private void populateCommaList(final Properties properties, final String s) {
        final String[] splitString = StringUtil.splitString(s, ",", true);
        if (splitString == null) {
            return;
        }
        for (int i = 0; i < splitString.length; ++i) {
            final String trim = splitString[i].trim();
            ((Hashtable<String, String>)properties).put(trim, trim);
        }
    }
    
    private void copyNewProps(final Properties properties, final Properties properties2) {
        final Enumeration<Object> keys = ((Hashtable<Object, V>)properties).keys();
        while (keys.hasMoreElements()) {
            final Object nextElement = keys.nextElement();
            properties2.put(nextElement, properties.get(nextElement));
        }
    }
    
    public boolean readFile(final URL url, final String s, final Properties properties) {
        this.printer().print("read file=" + s);
        return loadFile(url, s, properties);
    }
    
    public static boolean loadProps(final URL url, final Properties properties) {
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setDoInput(true);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(dataInputStream);
            properties.load(bufferedInputStream);
            bufferedInputStream.close();
            dataInputStream.close();
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    private static boolean loadFile(final URL url, final String s, final Properties properties) {
        try {
            final URLConnection openConnection = new URL(url, s).openConnection();
            openConnection.setDoInput(true);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(dataInputStream);
            properties.load(bufferedInputStream);
            bufferedInputStream.close();
            dataInputStream.close();
        }
        catch (Exception ex) {
            System.out.println("alert3892." + s);
            return false;
        }
        return true;
    }
    
    public String get(final String s, final String s2) {
        final String value = this.get(s);
        if (value == null) {
            return s2;
        }
        return value;
    }
    
    public String get(final String s) {
        if (this.confProperties == null) {
            return null;
        }
        String s2 = null;
        if (this.allowedAppletParms == null && this.denyAppletParms == null) {
            s2 = this.appletTagConf(s);
        }
        if (s2 != null) {
            return s2;
        }
        if (this.allowedAppletParms != null && this.allowedAppletParms.containsKey(s)) {
            s2 = this.appletTagConf(s);
        }
        if (this.denyAppletParms != null && !this.denyAppletParms.containsKey(s)) {
            s2 = this.appletTagConf(s);
        }
        if (s2 != null) {
            return s2;
        }
        return this.confProperties.getProperty(s);
    }
    
    private String appletTagConf(final String s) {
        String parameter;
        try {
            parameter = this.guiApplet.getParameter(s);
        }
        catch (Exception ex) {
            System.out.println("Err #892," + ex);
            parameter = null;
        }
        return parameter;
    }
    
    public String serverConf(final String s, final String s2) {
        if (this.serverOverride) {
            final String value = this.get(s);
            if (value == null) {
                return s2;
            }
            return value;
        }
        else {
            final String property = this.serverLevelProps.getProperty(s);
            if (property != null) {
                return property;
            }
            return s2;
        }
    }
    
    public int getInt(final String s, final int n) {
        final String value = this.get(s);
        if (value == null) {
            return n;
        }
        final String trim = value.trim();
        int int1;
        try {
            int1 = Integer.parseInt(trim);
        }
        catch (NumberFormatException ex) {
            System.out.println("error E651. integer:" + trim);
            int1 = n;
        }
        return int1;
    }
    
    public boolean getBool(final String s, final boolean b) {
        final String value = this.get(s);
        if (value == null) {
            return b;
        }
        final String trim = value.trim();
        return StringUtil.isTrue(trim) || (!StringUtil.isFalse(trim) && b);
    }
    
    public boolean getBool(final String s) {
        final String value = this.get(s);
        if (value == null) {
            return false;
        }
        final String trim = value.trim();
        return StringUtil.isTrue(trim) || (!StringUtil.isFalse(trim) && this.stringToBoolean(this.theDefault.getProperty(s)));
    }
    
    public void save(final OutputStream outputStream, final String s) {
        this.confProperties.save(outputStream, s);
    }
    
    public void saveDefault(final OutputStream outputStream, final String s) {
        if (this.theDefault == null) {
            System.out.println("default not initialized.");
        }
        this.theDefault.save(outputStream, s);
    }
    
    private void initVariables(final Applet applet) {
        this.theDefault = new Properties();
        this.confProperties = new Properties(this.theDefault);
        this.setDefaultValues(this.theDefault);
    }
    
    private void lowerCase(final Properties properties) {
        try {
            final Enumeration<String> keys = ((Hashtable<String, V>)properties).keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                ((Hashtable<String, Object>)properties).put(s.toLowerCase(), properties.get(s));
            }
        }
        catch (Exception ex) {
            System.out.println("MS sucks.");
            ex.printStackTrace();
        }
    }
    
    private void setDefaultValues(final Properties properties) {
        for (int i = 0; i < this.defaultTable.length; ++i) {
            ((Hashtable<String, String>)properties).put(this.defaultTable[i][0], this.defaultTable[i][1]);
        }
    }
    
    private boolean stringToBoolean(final String s) {
        return StringUtil.getBool(s, false);
    }
    
    public static void main(final String[] array) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream("test_properties.conf");
        }
        catch (IOException ex) {
            System.out.println("write error, properties file.");
            return;
        }
        new Config().saveDefault(fileOutputStream, "#class properties\n");
    }
}
