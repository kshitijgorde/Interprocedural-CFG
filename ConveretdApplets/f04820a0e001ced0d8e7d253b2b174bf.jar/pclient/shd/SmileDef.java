// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
import java.net.MalformedURLException;
import com.pchat.sc.StringUtil;
import java.awt.Image;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.net.URL;

public class SmileDef
{
    private Config paraConf;
    private URL classCodebase;
    private Properties popupList;
    private Properties emoticonList;
    private static final String NAME_FILE = "smiley.conf";
    private static final String EMOT_FILE = "emoticon.conf";
    
    public SmileDef(final Config paraConf) {
        this.paraConf = null;
        this.classCodebase = null;
        this.popupList = null;
        this.emoticonList = null;
        this.paraConf = paraConf;
        this.classCodebase = paraConf.getApplet().getCodeBase();
        this.popupList = new Properties();
        this.emoticonList = new Properties();
        this.initItems(this.popupList, this.emoticonList);
        this.paraConf.printer().print("popupList," + this.popupList);
        this.paraConf.printer().print("emoticonList," + this.emoticonList);
    }
    
    public static void getEmoticonSmiley(final Properties properties, final Properties properties2, final String s, final Properties properties3, final Properties properties4, final Properties properties5, final Properties properties6, final Properties properties7, final Properties properties8) {
        if (isSiteLevel(properties3, properties6, "Ctrl.Site.Smile", false)) {
            getEmoticon(properties, properties2, s, properties3, properties6, properties7, properties8);
        }
        else {
            getEmoticon(properties, properties2, s, properties3, properties6, properties4, properties5);
        }
    }
    
    private static void getEmoticon(final Properties properties, final Properties properties2, final String s, final Properties properties3, Properties properties4, Properties properties5, Properties properties6) {
        if (properties6 == null) {
            properties6 = new Properties();
        }
        if (properties4 == null) {
            properties4 = new Properties();
        }
        if (properties5 == null) {
            properties5 = new Properties();
        }
        if (properties6 == null) {
            properties6 = new Properties();
        }
        addStandard(properties, properties5);
        addExtra(properties, getStr(properties3, properties4, "Cf.AddSmiley"));
        deleteIcons(properties, getStr(properties3, properties4, "Cf.DelSmiley"));
        loadEmot(properties2, properties6);
    }
    
    private static String getStr(final Properties properties, final Properties properties2, final String s) {
        String s2 = properties2.getProperty(s);
        if (s2 == null) {
            s2 = properties.getProperty(s);
        }
        return s2;
    }
    
    public Hashtable getEmoticon() {
        return this.emoticonList;
    }
    
    public String[] getNames() {
        final String[] array = new String[this.popupList.size()];
        final Enumeration<String> keys = (Enumeration<String>)this.popupList.keys();
        int n = 0;
        while (keys.hasMoreElements()) {
            try {
                array[n] = keys.nextElement();
                ++n;
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return array;
    }
    
    public Image getImage(final String s) {
        final String s2 = ((Hashtable<K, String>)this.popupList).get(s);
        if (s2 == null) {
            return null;
        }
        final String filePath = this.getFilePath();
        this.paraConf.printer().print("smile path=" + filePath);
        return this.paraConf.iconShop.getImage(filePath, s2);
    }
    
    public static String getSmileyCodebaseForWapPages(final String s, final Properties properties, final Properties properties2, String s2) {
        if (s2 == null) {
            return null;
        }
        if (isSiteLevel(properties, properties2, "Ctrl.Site.Smile", false)) {
            s2 = s2 + "/" + "appletconf/appsites" + "/" + s + ".st" + "/" + "smiley";
        }
        else {
            s2 = s2 + "/" + "appletconf" + "/" + "smiley";
        }
        return s2;
    }
    
    public static String getSoundCodebaseForWapPages(final String s, final Properties properties, final Properties properties2, String s2) {
        if (s2 == null) {
            return null;
        }
        if (isSiteLevel(properties, properties2, "Ctrl.Site.Snd", false)) {
            s2 = s2 + "/" + "appletconf/appsites" + "/" + s + ".st" + "/" + "sound";
        }
        else {
            s2 = s2 + "/" + "appletconf" + "/" + "sound";
        }
        return s2;
    }
    
    private static boolean isSiteLevel(final Properties properties, final Properties properties2, final String s, final boolean b) {
        String s2 = properties2.getProperty(s);
        if (StringUtil.isTrimmedEmpty(s2)) {
            s2 = properties.getProperty(s);
        }
        return StringUtil.getBool(s2, b);
    }
    
    private String getFilePath() {
        final String value = this.paraConf.get("Net.Site");
        String string = "appletconf/smiley";
        if (this.paraConf.getBool("Ctrl.Site.Smile", false) && !StringUtil.isTrimmedEmpty(value)) {
            string = "appletconf/appsites/" + value + ".st" + "/" + "smiley";
        }
        return string;
    }
    
    private URL constructURL(final String s) {
        URL url;
        try {
            url = new URL(this.classCodebase, this.getFilePath() + "/" + s);
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
            url = null;
        }
        this.paraConf.printer().print("downloading URL," + url);
        return url;
    }
    
    private void initItems(final Properties properties, final Properties properties2) {
        this.addStandard(properties);
        this.addExtra(properties);
        this.deleteIcons(properties);
        this.loadEmot(properties2);
    }
    
    private void addStandard(final Properties properties) {
        final URL constructURL = this.constructURL("smiley.conf");
        if (constructURL == null) {
            return;
        }
        final Properties properties2 = new Properties();
        Config.loadProps(constructURL, properties2);
        addStandard(properties, properties2);
    }
    
    private static void addStandard(final Properties properties, final Properties properties2) {
        final Enumeration<String> keys = ((Hashtable<String, V>)properties2).keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            ((Hashtable<String, String>)properties).put(s.toLowerCase(), properties2.getProperty(s));
        }
    }
    
    private void addExtra(final Properties properties) {
        addExtra(properties, this.paraConf.get("Cf.AddSmiley"));
    }
    
    private static void addExtra(final Properties properties, final String s) {
        if (s == null) {
            return;
        }
        final String[] splitString = StringUtil.splitString(s, ",", true);
        for (int i = 0; i < splitString.length; ++i) {
            parseParam(properties, splitString[i]);
        }
    }
    
    private static void parseParam(final Properties properties, final String s) {
        final int index = s.indexOf("=");
        if (index < 0) {
            return;
        }
        final String substring = s.substring(0, index);
        if (index + 1 >= s.length()) {
            return;
        }
        addName(properties, substring, s.substring(index + 1));
    }
    
    private static void addName(final Properties properties, String s, String trim) {
        if (s == null || trim == null) {
            return;
        }
        s = s.trim();
        trim = trim.trim();
        if (s.length() == 0 || trim.length() == 0) {
            return;
        }
        s = s.toLowerCase();
        ((Hashtable<String, String>)properties).put(s, trim);
    }
    
    private void deleteIcons(final Properties properties) {
        deleteIcons(properties, this.paraConf.get("Cf.DelSmiley"));
    }
    
    private static void deleteIcons(final Properties properties, final String s) {
        if (s == null) {
            return;
        }
        final String[] splitString = StringUtil.splitString(s, ",", true);
        if (splitString == null) {
            return;
        }
        for (int i = 0; i < splitString.length; ++i) {
            final String s2 = splitString[i];
            if (!StringUtil.isEmpty(s2)) {
                properties.remove(s2);
            }
        }
    }
    
    private void loadEmot(final Properties properties) {
        final URL constructURL = this.constructURL("emoticon.conf");
        if (constructURL == null) {
            return;
        }
        final Properties properties2 = new Properties();
        Config.loadProps(constructURL, properties2);
        loadEmot(properties, properties2);
    }
    
    private static void loadEmot(final Properties properties, final Properties properties2) {
        final Enumeration<String> keys = ((Hashtable<String, V>)properties2).keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            ((Hashtable<String, String>)properties).put(properties2.getProperty(s), s.toLowerCase());
        }
    }
    
    public static Vector getWebData(final URL url) {
        final Vector<String> vector = new Vector<String>(8);
        try {
            String line;
            while ((line = new BufferedReader(new InputStreamReader(url.openStream())).readLine()) != null) {
                vector.addElement(line);
            }
            return vector;
        }
        catch (IOException ex) {
            System.out.println("Error#891. image lib," + url);
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            System.out.println("Error#892. image lib," + url);
            ex2.printStackTrace();
        }
        return null;
    }
}
