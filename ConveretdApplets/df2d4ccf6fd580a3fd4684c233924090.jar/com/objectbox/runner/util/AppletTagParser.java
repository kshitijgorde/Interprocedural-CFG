// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.util;

import java.io.InputStream;
import java.net.URLConnection;
import com.objectbox.runner.gui.JBSearchPanel;
import com.objectbox.runner.beans.DownloadView;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.objectbox.runner.model.JBAppletProperties;
import java.util.Hashtable;
import java.util.Vector;
import java.net.URL;

public class AppletTagParser extends Thread
{
    Hyperlink startlink;
    static URL firsturl;
    static String localHost;
    static Object handle;
    Vector appletList;
    static Vector urlList;
    static Hashtable visited;
    static Hashtable noGoHash;
    static int pagesChecked;
    static int maxThreads;
    static int currThreads;
    static boolean checkRefs;
    static boolean isRunning;
    static boolean remoteCheck;
    static int localLevel;
    static int remoteLevel;
    static int appletsFound;
    
    static {
        AppletTagParser.firsturl = null;
        AppletTagParser.localHost = null;
        AppletTagParser.handle = null;
        AppletTagParser.urlList = new Vector();
        AppletTagParser.visited = new Hashtable();
        AppletTagParser.noGoHash = new Hashtable(10);
        AppletTagParser.pagesChecked = 0;
        AppletTagParser.maxThreads = 7;
        AppletTagParser.currThreads = AppletTagParser.maxThreads;
        AppletTagParser.checkRefs = false;
        AppletTagParser.isRunning = true;
        AppletTagParser.remoteCheck = false;
        AppletTagParser.localLevel = 5;
        AppletTagParser.remoteLevel = 1;
        AppletTagParser.appletsFound = 0;
    }
    
    public AppletTagParser() {
        this.startlink = null;
        this.appletList = new Vector();
    }
    
    public AppletTagParser(final ThreadGroup threadGroup, final String s) {
        super(threadGroup, s);
        this.startlink = null;
        this.appletList = new Vector();
    }
    
    private JBAppletProperties getAppletAttributes(final String s) {
        final int n = -1;
        final int length = s.length();
        final int n2 = 0;
        final JBAppletProperties jbAppletProperties = new JBAppletProperties();
        int i = s.indexOf(61, n2 + 1);
        do {
            char c;
            int n3;
            for (c = ' ', n3 = i - 1; s.charAt(n3) == c & n3 >= 0; n3 += n) {}
            int n4 = n3 - 1;
            if (s.charAt(n4) == '\"') {
                c = '\"';
                --n4;
            }
            if (s.charAt(n4) == '\'') {
                c = '\'';
                --n4;
            }
            while (s.charAt(n4) != c & n4 >= 0) {
                n4 += n;
            }
            final String substring = s.substring(n4, n3 + 1);
            char c2;
            int n5;
            for (c2 = ' ', n5 = i + 1; s.charAt(n5) == c2 & n5 < length - 1; n5 -= n) {}
            int n6 = n5 + 1;
            if (s.charAt(n5) == '\"') {
                c2 = '\"';
                ++n5;
            }
            if (s.charAt(n5) == '\'') {
                c2 = '\'';
                ++n5;
            }
            while (s.charAt(n6) != c2 & n6 < length - 1) {
                n6 -= n;
            }
            ((Hashtable<String, String>)jbAppletProperties.getProps()).put(substring.trim().toLowerCase(), s.substring(n5, n6).trim());
            i = s.indexOf(61, i + 1);
        } while (i != -1);
        return jbAppletProperties;
    }
    
    private void getAppletParameters(final JBAppletProperties jbAppletProperties, final String s) {
        final int n = -1;
        final int length = s.length();
        char c = ' ';
        int index;
        for (index = s.toUpperCase().indexOf("NAME"); s.charAt(index) != '=' & index < length - 1; index -= n) {}
        ++index;
        while (s.charAt(index) == ' ' & index < length - 1) {
            index -= n;
        }
        int n2 = index + 1;
        if (s.charAt(index) == '\"') {
            c = '\"';
            ++index;
        }
        if (s.charAt(index) == '\'') {
            c = '\'';
            ++index;
        }
        while (s.charAt(n2) != c & n2 < length - 1) {
            n2 -= n;
        }
        final String substring = s.substring(index, n2);
        char c2 = ' ';
        int index2;
        for (index2 = s.toUpperCase().indexOf("VALUE"); s.charAt(index2) != '=' & index2 < length - 1; index2 -= n) {}
        ++index2;
        while (s.charAt(index2) == ' ' & index2 < length - 1) {
            index2 -= n;
        }
        int n3 = index2 + 1;
        if (s.charAt(index2) == '\"') {
            c2 = '\"';
            ++index2;
        }
        if (s.charAt(index2) == '\'') {
            c2 = '\'';
            ++index2;
        }
        while (s.charAt(n3) != c2 & n3 < length - 1) {
            n3 -= n;
        }
        ((Hashtable<String, String>)jbAppletProperties.getParameters()).put(substring, s.substring(index2, n3));
    }
    
    private String getTagAttribute(final String s, String upperCase) {
        String s2 = "";
        try {
            boolean b = false;
            final int n = -1;
            final int length = s.length();
            final int n2 = 0;
            upperCase = upperCase.toUpperCase();
            for (int n3 = s.indexOf(61, n2 + 1); n3 != -1 & !b; n3 = s.indexOf(61, n3 + 1)) {
                char c;
                int n4;
                for (c = ' ', n4 = n3 - 1; s.charAt(n4) == c & n4 >= 0; n4 += n) {}
                int n5 = n4 - 1;
                if (s.charAt(n5) == '\"') {
                    c = '\"';
                    --n5;
                }
                if (s.charAt(n5) == '\'') {
                    c = '\'';
                    --n5;
                }
                while (s.charAt(n5) != c & n5 >= 0) {
                    n5 += n;
                }
                if (upperCase.equals(s.substring(n5, n4 + 1).trim().toUpperCase())) {
                    b = true;
                    char c2;
                    int n6;
                    for (c2 = ' ', n6 = n3 + 1; s.charAt(n6) == c2 & n6 < length - 1; n6 -= n) {}
                    int n7 = n6 + 1;
                    if (s.charAt(n6) == '\"') {
                        c2 = '\"';
                        ++n6;
                    }
                    if (s.charAt(n6) == '\'') {
                        c2 = '\'';
                        ++n6;
                    }
                    while (s.charAt(n7) != c2 & n7 < length - 1) {
                        n7 -= n;
                    }
                    s2 = s.substring(n6, n7);
                    s2 = s2.trim();
                }
            }
        }
        catch (Exception ex) {}
        return s2;
    }
    
    public void kill() {
        AppletTagParser.isRunning = false;
        AppletTagParser.visited.clear();
        AppletTagParser.urlList.removeAllElements();
    }
    
    public void log(final String s) {
        JBLogger.log(s);
    }
    
    public Vector parse() {
        try {
            String s = "";
            URL url = new URL(this.startlink.url.toString());
            try {
                final URLConnection openConnection = url.openConnection();
                openConnection.setRequestProperty("User-Agent", "JavaBee");
                openConnection.setRequestProperty("Referer", "http://www.javabee.com");
                openConnection.setRequestProperty("Accept", "text/html");
                final String string = String.valueOf(openConnection.getContentType()) + "";
                if (string.indexOf("htm") >= 0 || string.indexOf("unknown") >= 0) {
                    final InputStream inputStream = openConnection.getInputStream();
                    url = openConnection.getURL();
                    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    JBAppletProperties appletAttributes = new JBAppletProperties();
                    boolean b = false;
                    s = "";
                    final int port = url.getPort();
                    String string2;
                    if (port == -1) {
                        string2 = "";
                    }
                    else {
                        string2 = ":" + port;
                    }
                    final String string3 = String.valueOf(url.getProtocol()) + "://" + url.getHost() + string2;
                    s = url.getFile();
                    String string4;
                    if (s.indexOf("http://") != 0) {
                        s = String.valueOf(string3) + s;
                        string4 = string3;
                    }
                    else {
                        final URL url2 = new URL(s);
                        string4 = "http://" + url2.getHost() + url2.getPort();
                    }
                    final String substring = s.substring(s.lastIndexOf("/"));
                    s = s.substring(0, s.lastIndexOf("/"));
                    if (s.indexOf(".nsf") > 0) {
                        s = String.valueOf(url.getProtocol()) + "://" + url.getHost() + string2;
                    }
                    final HTMLTokenizer htmlTokenizer = new HTMLTokenizer(bufferedReader);
                    int nextHTML;
                    while ((nextHTML = htmlTokenizer.nextHTML()) != HTMLTokenizer.HTML_EOF && AppletTagParser.isRunning) {
                        if (nextHTML == HTMLTokenizer.TAG_BASE) {
                            final String tagAttribute = this.getTagAttribute(htmlTokenizer.sval, "href");
                            if (tagAttribute.length() > 0) {
                                s = tagAttribute;
                            }
                        }
                        if (nextHTML != HTMLTokenizer.HTML_UNKNOWN) {
                            final String sval = htmlTokenizer.sval;
                            DownloadView.addBytes(htmlTokenizer.sval.length());
                            final String concat = this.remove(this.remove(sval.trim(), '\n'), '\r').replace('\t', ' ').trim().concat(" ");
                            if (nextHTML == HTMLTokenizer.TAG_APPLET) {
                                concat.toUpperCase().indexOf("CODE");
                                appletAttributes = this.getAppletAttributes(concat);
                                ((Hashtable<String, String>)appletAttributes.getProps()).put("documentbase", s);
                                ((Hashtable<String, String>)appletAttributes.getProps()).put("host", string4);
                                ((Hashtable<String, String>)appletAttributes.getProps()).put("webpage", substring);
                                b = true;
                                ++AppletTagParser.appletsFound;
                            }
                            if (nextHTML == HTMLTokenizer.TAG_applet) {
                                this.appletList.addElement(appletAttributes);
                                b = false;
                            }
                            else if (nextHTML == HTMLTokenizer.TAG_PARAM & b) {
                                this.getAppletParameters(appletAttributes, concat);
                            }
                            if (nextHTML == HTMLTokenizer.TAG_FRAME) {
                                final String tagAttribute2 = this.getTagAttribute(concat, "src");
                                if (tagAttribute2 != "") {
                                    URL url3;
                                    if (tagAttribute2.startsWith("http")) {
                                        url3 = new URL(tagAttribute2);
                                    }
                                    else if (tagAttribute2.startsWith("/")) {
                                        url3 = new URL(String.valueOf(string4) + tagAttribute2);
                                    }
                                    else {
                                        url3 = new URL(String.valueOf(s) + "/" + tagAttribute2);
                                    }
                                    if (AppletTagParser.visited.get(url3) == null) {
                                        final Hyperlink hyperlink = new Hyperlink();
                                        hyperlink.url = new URL(url3.toString());
                                        hyperlink.level = this.startlink.level;
                                        AppletTagParser.urlList.addElement(hyperlink);
                                    }
                                    AppletTagParser.visited.put(url3, new Boolean(false));
                                }
                            }
                            if (!(nextHTML == HTMLTokenizer.TAG_A & AppletTagParser.checkRefs)) {
                                continue;
                            }
                            final String tagAttribute3 = this.getTagAttribute(concat, "href");
                            if (tagAttribute3 == "" || tagAttribute3.startsWith("mailto") || tagAttribute3.endsWith("gif") || tagAttribute3.endsWith("jpg")) {
                                continue;
                            }
                            final String trim = tagAttribute3.replace('\"', ' ').replace('\\', ' ').trim();
                            URL url4;
                            if (trim.startsWith("http")) {
                                url4 = new URL(trim);
                            }
                            else if (trim.startsWith("/")) {
                                url4 = new URL(String.valueOf(string4) + trim);
                            }
                            else {
                                url4 = new URL(String.valueOf(s) + "/" + trim);
                            }
                            if (AppletTagParser.visited.get(url4) != null || AppletTagParser.noGoHash.get(url4.toString()) != null || url4.getHost().compareTo("jump.altavista.com") == 0 || url4.getHost().compareTo("www.doubleclick.net") == 0 || this.getThreadGroup().getName().compareTo("parsegroup" + ((JBSearchPanel)AppletTagParser.handle).getParsegroupNr()) != 0) {
                                continue;
                            }
                            final boolean b2 = AppletTagParser.localHost.compareTo(url4.getHost()) == 0;
                            if (this.startlink.local && this.startlink.level < AppletTagParser.localLevel && b2) {
                                final Hyperlink hyperlink2 = new Hyperlink();
                                hyperlink2.url = new URL(url4.toString());
                                hyperlink2.level = this.startlink.level + 1;
                                AppletTagParser.urlList.addElement(hyperlink2);
                                AppletTagParser.visited.put(url4, new Boolean(false));
                            }
                            else if (this.startlink.local && AppletTagParser.remoteCheck && !b2) {
                                final Hyperlink hyperlink3 = new Hyperlink();
                                hyperlink3.url = new URL(url4.toString());
                                hyperlink3.local = false;
                                hyperlink3.level = 0;
                                AppletTagParser.urlList.addElement(hyperlink3);
                                AppletTagParser.visited.put(url4, new Boolean(false));
                            }
                            else {
                                if (this.startlink.local || this.startlink.level >= AppletTagParser.remoteLevel) {
                                    continue;
                                }
                                final Hyperlink hyperlink4 = new Hyperlink();
                                hyperlink4.url = new URL(url4.toString());
                                hyperlink4.local = false;
                                hyperlink4.level = this.startlink.level + 1;
                                AppletTagParser.urlList.addElement(hyperlink4);
                                AppletTagParser.visited.put(url4, new Boolean(false));
                            }
                        }
                    }
                    bufferedReader.close();
                    url = null;
                }
            }
            catch (Exception ex) {
                JBLogger.log("Couldn't open connection: " + ex + ", Url: " + url + ", docbase: " + s);
            }
            if (this.appletList.size() > 0) {
                ((JBSearchPanel)AppletTagParser.handle).updateAppletList(this.appletList);
                this.appletList.removeAllElements();
            }
        }
        catch (Throwable t) {
            JBLogger.log("Ex: " + t);
        }
        return this.appletList;
    }
    
    private String remove(final String s, final char c) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 != c) {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
    
    public void run() {
        try {
            this.parse();
            ++AppletTagParser.pagesChecked;
            if (AppletTagParser.isRunning) {
                ((JBSearchPanel)AppletTagParser.handle).updateStatus(AppletTagParser.pagesChecked, AppletTagParser.visited.size(), AppletTagParser.appletsFound, this.startlink.toString());
            }
            Thread.yield();
            Thread.sleep(1L);
            while (AppletTagParser.currThreads > 0 && !AppletTagParser.urlList.isEmpty() && AppletTagParser.isRunning && this.getThreadGroup().getName().compareTo("parsegroup" + ((JBSearchPanel)AppletTagParser.handle).getParsegroupNr()) == 0) {
                synchronized (this) {
                    --AppletTagParser.currThreads;
                    this.startChildParser();
                    this.wait(1000L);
                }
            }
            ++AppletTagParser.currThreads;
        }
        catch (InterruptedException ex) {
            JBLogger.log(ex.toString());
        }
    }
    
    public void setHandle(final Object handle) {
        AppletTagParser.handle = handle;
    }
    
    public void setNoGoList() {
        JBLogger.log("Setter noGoListen");
        AppletTagParser.noGoHash.put("http://www.altavista.com/av/content/about.htm", new Boolean(false));
        AppletTagParser.noGoHash.put("http://www.altavista.com/av/content/help.htm", new Boolean(false));
        AppletTagParser.noGoHash.put("http://www.altavista.com/av/content/questions.htm", new Boolean(false));
        AppletTagParser.noGoHash.put("http://www.doubleclick.net/advertisers/altavista/", new Boolean(false));
        AppletTagParser.noGoHash.put("http://www.altavista.com/cgi-bin/query?pg=addurl", new Boolean(false));
        AppletTagParser.noGoHash.put("http://www.altavista.com/av/content/disclaimer.htm", new Boolean(false));
        AppletTagParser.noGoHash.put("http://www.altavista.com/av/content/privacy.htm", new Boolean(false));
        AppletTagParser.noGoHash.put("http://image.altavista.com/AV_CopyrightPolicy.htm", new Boolean(false));
        AppletTagParser.noGoHash.put("http://www.altavista.com/av/content/av_network.html", new Boolean(false));
        AppletTagParser.noGoHash.put("http://www.altavista.com/cgi-bin/query?pg=tmpl&v=pref.html", new Boolean(false));
        AppletTagParser.noGoHash.put("http://www.altavista.com/cgi-bin/query", new Boolean(false));
        AppletTagParser.noGoHash.put("http://www.altavista.com/cgi-bin/query?text&pg=q&what=web", new Boolean(false));
        AppletTagParser.noGoHash.put("http://www.altavista.com/cgi-bin/query?pg=aq&what=web&text=yes", new Boolean(false));
        AppletTagParser.noGoHash.put("http://www.altavista.com/cgi-bin/query?text&pg=q&what=news", new Boolean(false));
        AppletTagParser.noGoHash.put("http://www.altavista.com/cgi-bin/query?pg=aq&what=news&text=yes", new Boolean(false));
        AppletTagParser.noGoHash.put("http://www.altavista.com/cgi-bin/query?pg=&text=yes", new Boolean(false));
        AppletTagParser.noGoHash.put("http://www.altavista.com/cgi-bin/query?pg=q&what=news&text=yes", new Boolean(false));
        AppletTagParser.noGoHash.put("http://www.altavista.com/", new Boolean(false));
        AppletTagParser.noGoHash.put("/av/content/about.htm", new Boolean(false));
        AppletTagParser.noGoHash.put("/av/content/help.htm", new Boolean(false));
        AppletTagParser.noGoHash.put("/av/content/questions.htm", new Boolean(false));
        AppletTagParser.noGoHash.put("/advertisers/altavista/", new Boolean(false));
        AppletTagParser.noGoHash.put("/cgi-bin/query?pg=addurl", new Boolean(false));
        AppletTagParser.noGoHash.put("/av/content/disclaimer.htm", new Boolean(false));
        AppletTagParser.noGoHash.put("/av/content/privacy.htm", new Boolean(false));
        AppletTagParser.noGoHash.put("/AV_CopyrightPolicy.htm", new Boolean(false));
        AppletTagParser.noGoHash.put("/av/content/av_network.html", new Boolean(false));
        AppletTagParser.noGoHash.put("/cgi-bin/query?pg=tmpl&v=pref.html", new Boolean(false));
        AppletTagParser.noGoHash.put("/cgi-bin/query", new Boolean(false));
        AppletTagParser.noGoHash.put("/cgi-bin/query?text&pg=q&what=web", new Boolean(false));
        AppletTagParser.noGoHash.put("/cgi-bin/query?pg=aq&what=web&text=yes", new Boolean(false));
        AppletTagParser.noGoHash.put("/cgi-bin/query?pg=&what=news&text=yes", new Boolean(false));
        AppletTagParser.noGoHash.put("/cgi-bin/query?text&pg=q&what=news", new Boolean(false));
        AppletTagParser.noGoHash.put("/cgi-bin/query?pg=aq&what=news&text=yes", new Boolean(false));
        AppletTagParser.noGoHash.put("/cgi-bin/query?pg=&text=yes", new Boolean(false));
    }
    
    public void setPrefs(final boolean checkRefs, final boolean remoteCheck, final int localLevel, final int remoteLevel, final URL firsturl) {
        AppletTagParser.checkRefs = checkRefs;
        AppletTagParser.remoteCheck = remoteCheck;
        AppletTagParser.localLevel = localLevel;
        AppletTagParser.remoteLevel = remoteLevel;
        AppletTagParser.firsturl = firsturl;
        AppletTagParser.localHost = AppletTagParser.firsturl.getHost();
        AppletTagParser.pagesChecked = 0;
        AppletTagParser.appletsFound = 0;
        AppletTagParser.visited.clear();
        AppletTagParser.urlList.removeAllElements();
        AppletTagParser.isRunning = true;
        AppletTagParser.currThreads = AppletTagParser.maxThreads;
    }
    
    public void setStartURL(final Hyperlink startlink) {
        this.startlink = startlink;
        AppletTagParser.visited.put(this.startlink.url, new Boolean(false));
    }
    
    public void startChildParser() {
        final AppletTagParser appletTagParser = new AppletTagParser();
        final Hyperlink startURL = AppletTagParser.urlList.elementAt(0);
        final URL url = startURL.url;
        AppletTagParser.urlList.removeElementAt(0);
        appletTagParser.setStartURL(startURL);
        appletTagParser.start();
    }
}
