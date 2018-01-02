// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.esial.util.c;
import java.util.StringTokenizer;
import java.util.Enumeration;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Locale;
import com.diginet.digichat.util.DES;
import java.util.Hashtable;
import java.awt.Component;
import com.diginet.digichat.common.Game;
import java.awt.Toolkit;
import com.diginet.digichat.util.a5;
import java.applet.Applet;

public class Scripts
{
    public static final int ADDITION = 222;
    public static final long FOREVER = 140737488355327L;
    private static final int NONE = 0;
    private static final int FAVOR = 1;
    private static final int BAR = 2;
    private static final String strUndef = "undefined";
    private static final String strTitle;
    private static final Class jsClass;
    private static int nGame;
    private int nAdd;
    private Object jsWindow;
    private Object jsFavor;
    
    private static Object getWindow(final Applet applet) throws Throwable {
        return Scripts.jsClass.getMethod("getWindow", Class.forName("java.applet.Applet")).invoke(null, applet);
    }
    
    public Scripts(final Applet applet) {
        this.nAdd = 0;
        this.jsWindow = null;
        if (Scripts.jsClass != null) {
            try {
                this.jsWindow = getWindow(applet);
            }
            catch (Throwable t) {}
        }
    }
    
    private Object call(final Object o, final String s, final Object[] array) throws Throwable {
        return Scripts.jsClass.getMethod("call", Class.forName("java.lang.String"), Class.forName("[Ljava.lang.Object;")).invoke(o, s, array);
    }
    
    private void call(final Object o, final String s) throws Throwable {
        this.call(o, s, null);
    }
    
    private static Object getMember(final Object o, final String s) throws Throwable {
        return Scripts.jsClass.getMethod("getMember", Class.forName("java.lang.String")).invoke(o, s);
    }
    
    public static boolean isScriptable() {
        try {
            if (Scripts.jsClass != null && getWindow(DigiChatAppletAbstract.applet) != null) {
                return true;
            }
        }
        catch (Throwable t) {}
        return false;
    }
    
    private static boolean noWord(final String s, final String s2) {
        final int index;
        final int n;
        return (index = s.toLowerCase().indexOf(s2)) < 0 || (index > 0 && !Character.isSpace(s.charAt(index - 1))) || ((n = index + s2.length()) < s.length() && !Character.isSpace(s.charAt(n)));
    }
    
    public static boolean checkEmbed() {
        try {
            return noWord(System.getProperty("java.vendor"), "sun") || noWord(getMember(getMember(getWindow(DigiChatAppletAbstract.applet), "navigator"), "userAgent").toString(), "msie");
        }
        catch (Throwable t) {
            return true;
        }
    }
    
    public boolean isAdded() {
        if (Scripts.jsClass != null) {
            try {
                final String s = "undefined";
                final Object member = getMember(this.jsWindow, "external");
                this.jsFavor = member;
                if (!s.equals(member)) {
                    this.nAdd = 1;
                    return true;
                }
                final String s2 = "undefined";
                final Object member2 = getMember(this.jsWindow, "sidebar");
                this.jsFavor = member2;
                if (!s2.equals(member2)) {
                    this.nAdd = 2;
                    return true;
                }
            }
            catch (Throwable t) {}
        }
        this.nAdd = 0;
        return false;
    }
    
    public void addFavor() {
        try {
            final String externalForm = DigiChatAppletAbstract.applet.getDocumentBase().toExternalForm();
            switch (this.nAdd) {
                case 1: {
                    this.call(this.jsWindow, "focus");
                    if (this.call(this.jsFavor, "AddFavorite", new Object[] { externalForm, a5.a(Scripts.strTitle, new String[] { DigiChatAppletAbstract.OEM_DigiChat }) }) != null) {
                        break;
                    }
                }
                case 2: {
                    this.call(this.jsFavor, "addPanel", new Object[] { a5.a(Scripts.strTitle, new String[] { DigiChatAppletAbstract.OEM_DigiChat }), externalForm, "" });
                    break;
                }
            }
        }
        catch (Throwable t) {}
    }
    
    public void upWin(final Object o) {
        if (Scripts.jsClass != null) {
            try {
                this.call(o, "focus");
            }
            catch (Throwable t) {}
        }
    }
    
    public void removeIMList(final Object o) throws Throwable {
        if (Scripts.jsClass != null) {
            this.call(o, "removeIMList");
        }
    }
    
    public void addIMList(final Object o) throws Throwable {
        if (Scripts.jsClass != null) {
            this.call(o, "addIMList", new Object[] { new Integer(222) });
        }
    }
    
    public void closeWin(final Object o) {
        if (Scripts.jsClass != null && o != null) {
            try {
                this.call(o, "close");
            }
            catch (Throwable t) {}
        }
    }
    
    public boolean isWinClosed(final Object o) {
        if (Scripts.jsClass != null) {
            try {
                return o == null || (boolean)getMember(o, "closed");
            }
            catch (Throwable t) {}
        }
        return true;
    }
    
    public Object callWebIM(final String s, final int n, final boolean b) {
        if (Scripts.jsClass != null) {
            try {
                final int n2 = b ? 550 : 772;
                final Object call;
                this.upWin(call = this.call(this.jsWindow, "open", new Object[] { s, String.valueOf("WebIM").concat(String.valueOf(Integer.toString(n))), String.valueOf(String.valueOf(String.valueOf(String.valueOf("width=").concat(String.valueOf(n2))).concat(String.valueOf(",height=401,top=0,left="))).concat(String.valueOf(Integer.toString(Toolkit.getDefaultToolkit().getScreenSize().width - n2 >> 1)))).concat(String.valueOf(",toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no")) }));
                return call;
            }
            catch (Throwable t) {}
        }
        return null;
    }
    
    private void setMember(final Object o, final String s, final String s2) throws Throwable {
        Scripts.jsClass.getMethod("setMember", Class.forName("java.lang.String"), Class.forName("java.lang.Object")).invoke(o, s, s2);
    }
    
    private void setIMValue(final Object o, final String s, final Object o2, final Boolean b) {
        if (Scripts.jsClass != null) {
            try {
                this.call(o, "setValue", new Object[] { s, o2, b });
                return;
            }
            catch (Throwable t) {}
        }
        throw new NumberFormatException();
    }
    
    public void setIMValue(final Object o, final String s, final int n) {
        this.setIMValue(o, s, new Integer(n), Boolean.TRUE);
    }
    
    public void setIMValue(final Object o, final String s, final String s2) {
        this.setIMValue(o, s, s2, Boolean.FALSE);
    }
    
    private String gameName() {
        return String.valueOf("Game").concat(String.valueOf(Scripts.nGame++));
    }
    
    public Play callGame(final String s, final Game game, final int n, int n2) {
        if (Scripts.jsClass != null) {
            try {
                final int nGame = Scripts.nGame;
                if (n2 < 0) {
                    n2 = nGame;
                }
                return new Play(this.call(this.jsWindow, "open", new Object[] { s, this.gameName(), String.valueOf(String.valueOf(String.valueOf(String.valueOf("width=").concat(String.valueOf(Integer.toString(game.nWidth)))).concat(String.valueOf(",height="))).concat(String.valueOf(Integer.toString(game.nHeight)))).concat(String.valueOf(",toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no")) }), n, nGame, game.w(), n2);
            }
            catch (Throwable t) {}
        }
        return null;
    }
    
    public Object callGames(final String s) {
        if (Scripts.jsClass != null) {
            try {
                final Object call;
                this.upWin(call = this.call(this.jsWindow, "open", new Object[] { s, this.gameName(), "width=1,height=1,top=0,left=0,directories=no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no" }));
                return call;
            }
            catch (Throwable t) {}
        }
        return null;
    }
    
    public Play setPanel(final Object o, final i i) {
        Object window = null;
        if (Scripts.jsClass != null) {
            try {
                final Applet applet;
                window = getWindow(applet = (Applet)o);
                final GamesPanel pnlGames;
                applet.add(pnlGames = new GamesPanel(i));
                final Object member = getMember(window, "top");
                final Play play;
                (play = new Play(member, Integer.parseInt(((String)getMember(member, "name")).substring(4)), -1, -1, -1)).pnlGames = pnlGames;
                i.gamesHeight = pnlGames.getPreferredSize().height;
                return play;
            }
            catch (Throwable t) {}
        }
        this.closeWin(window);
        return null;
    }
    
    public boolean runGame(final Play play, final String s, final int n, final int n2, final int nGame) {
        if (Scripts.jsClass != null) {
            try {
                this.call(play.objTop, "runGame", new Object[] { s, new Integer(n), new Integer(n2) });
                play.nGame = nGame;
                return true;
            }
            catch (Throwable t) {}
        }
        return false;
    }
    
    public void setFrame(final Play play, final String s) {
        if (Scripts.jsClass != null) {
            try {
                play.objFrame = ((s == null) ? play.objTop : getMember(play.objTop, s));
            }
            catch (Throwable t) {}
        }
    }
    
    public void addPlayer(final Object o, final String s, final boolean b) {
        if (Scripts.jsClass != null) {
            try {
                this.call(o, "addPlayer", new Object[] { s, new Boolean(b) });
            }
            catch (Throwable t) {}
        }
    }
    
    public void removePlayer(final Object o, final int n) {
        if (Scripts.jsClass != null) {
            try {
                this.call(o, "removePlayer", new Object[] { new Integer(n) });
            }
            catch (Throwable t) {}
        }
    }
    
    public void startPlay(final Object o, final boolean b) {
        if (Scripts.jsClass != null) {
            try {
                this.call(o, "startPlay", new Object[] { new Boolean(b) });
            }
            catch (Throwable t) {}
        }
    }
    
    public void sendMove(final Object o, final int n, final int n2, final String s) {
        if (Scripts.jsClass != null) {
            try {
                this.call(o, "takeMove", new Object[] { new Integer(n), new Integer(n2), s });
            }
            catch (Throwable t) {}
        }
    }
    
    public Object callUpload(final String s, final String s2) {
        if (Scripts.jsClass != null) {
            try {
                final Object call;
                this.upWin(call = this.call(this.jsWindow, "open", new Object[] { s, s2, "width=270,height=50,top=0,left=0,directories=no,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no" }));
                for (int i = 10; i > 0; --i) {
                    try {
                        final Object member;
                        if ((member = getMember(call, "fLoaded")) instanceof Boolean && (boolean)member) {
                            return call;
                        }
                    }
                    catch (Throwable t) {}
                    Thread.sleep(50L);
                }
                this.closeWin(call);
            }
            catch (Throwable t2) {}
        }
        return null;
    }
    
    public boolean setDir(final Object o, final int n, final String s, final String[][] array, final String s2) {
        if (Scripts.jsClass != null) {
            try {
                final StringBuffer sb = new StringBuffer();
                for (int i = 0; i < array.length; ++i) {
                    if (sb.length() > 0) {
                        sb.append(';');
                    }
                    sb.append(array[i][0]);
                }
                this.call(o, "setDir", new Object[] { new Integer(n), s, sb.toString(), (s2 == null) ? "" : s2 });
                return false;
            }
            catch (Throwable t) {}
        }
        this.closeWin(o);
        return true;
    }
    
    public static void setCookie(final Object[] array, final Hashtable hashtable, final long n) {
        if (Scripts.jsClass != null) {
            try {
                final StringBuffer sb = new StringBuffer();
                if (hashtable != null) {
                    final Enumeration keys = hashtable.keys();
                    while (keys.hasMoreElements()) {
                        final Object nextElement;
                        sb.append(nextElement = keys.nextElement());
                        sb.append('=');
                        sb.append(hashtable.get(nextElement));
                        sb.append(',');
                    }
                    sb.setLength(sb.length() - 1);
                    final byte[] bytes = sb.toString().getBytes();
                    final byte[] array2 = new byte[bytes.length + 7 & 0xFFFFFFF8];
                    for (int i = 0; i < array2.length; ++i) {
                        array2[i] = 0;
                    }
                    System.arraycopy(bytes, 0, array2, 0, bytes.length);
                    final byte[] array3;
                    new DES((byte[])array[1]).encrypt(array2, 0, array3 = new byte[array2.length], 0, array2.length);
                    sb.setLength(0);
                    for (int j = 0; j < array3.length; ++j) {
                        final String hexString;
                        if ((hexString = Integer.toHexString(array3[j] & 0xFF)).length() < 2) {
                            sb.append('0');
                        }
                        sb.append(hexString);
                    }
                }
                final SimpleDateFormat simpleDateFormat;
                (simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US)).setTimeZone(TimeZone.getTimeZone("GMT"));
                Scripts.jsClass.getMethod("setMember", Class.forName("java.lang.String"), Class.forName("java.lang.Object")).invoke(getMember(getWindow(DigiChatAppletAbstract.applet), "document"), "cookie", String.valueOf(String.valueOf(String.valueOf(String.valueOf(array[0]).concat(String.valueOf('='))).concat(String.valueOf(sb.toString()))).concat(String.valueOf("; expires="))).concat(String.valueOf(simpleDateFormat.format(new Date(n)))));
            }
            catch (Throwable t) {}
        }
    }
    
    public static void setCookie(final Object[] array, final Hashtable hashtable) {
        setCookie(array, hashtable, 140737488355327L);
    }
    
    public static Hashtable getCookie(final Object[] array) {
        if (Scripts.jsClass != null) {
            try {
                final String s = (String)array[0];
                final StringTokenizer stringTokenizer = new StringTokenizer((String)getMember(getMember(getWindow(DigiChatAppletAbstract.applet), "document"), "cookie"), ";");
                while (stringTokenizer.hasMoreTokens()) {
                    final String trim;
                    if ((trim = stringTokenizer.nextToken().trim()).startsWith(s) && trim.charAt(s.length()) == '=') {
                        final String substring;
                        final int length;
                        final byte[] array2 = new byte[(length = (substring = trim.substring(s.length() + 1)).length()) >> 1];
                        for (int i = 0; i < length; ++i) {
                            final char char1;
                            final char c = (char)(((char1 = substring.charAt(i)) > '9') ? (char1 - 'a' + '\n') : (char1 - '0'));
                            if ((i & 0x1) == 0x0) {
                                array2[i >> 1] = (byte)(c << 4);
                            }
                            else {
                                final byte[] array3 = array2;
                                final int n = i >> 1;
                                array3[n] |= (byte)c;
                            }
                        }
                        final byte[] array4;
                        new DES((byte[])array[1]).decrypt(array2, 0, array4 = new byte[array2.length], 0, array2.length);
                        final Hashtable hashtable = new Hashtable<String, String>();
                        final StringTokenizer stringTokenizer2 = new StringTokenizer(new String(array4), ",");
                        while (stringTokenizer2.hasMoreTokens()) {
                            final String trim2;
                            final int index;
                            if ((index = (trim2 = stringTokenizer2.nextToken().trim()).indexOf(61)) > 0) {
                                hashtable.put(trim2.substring(0, index), trim2.substring(index + 1));
                            }
                        }
                        return hashtable.isEmpty() ? null : hashtable;
                    }
                }
            }
            catch (Throwable t) {}
        }
        return null;
    }
    
    static {
        strTitle = c.a("Live chat %1");
        Class<?> forName;
        try {
            forName = Class.forName("netscape.javascript.JSObject");
        }
        catch (Throwable t) {
            forName = null;
        }
        jsClass = forName;
        Scripts.nGame = 0;
    }
}
