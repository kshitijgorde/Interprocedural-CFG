// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import java.net.URLEncoder;
import pclient.shd.SmileDef;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Vector;
import java.util.Enumeration;
import java.net.InetAddress;
import java.net.NetworkInterface;

public class ClientCharacter
{
    public static final String S_COM = "com";
    public static final String V_RET = "ret";
    public static final String V_STO = "sto";
    private static final String V_ENCODING = "UTF-8";
    private AppletSpice parentApplet;
    private String hostServer;
    private int hostPort;
    private String hostURL;
    protected ClientCharData cookieData;
    
    public ClientCharacter(final AppletSpice parentApplet) {
        this.parentApplet = parentApplet;
        this.cookieData = new ClientCharData();
        this.hostURL = this.parentApplet.paraConf.get("Net.PathCookie", "/parachat/chat/user.jsp");
        this.hostPort = this.parentApplet.paraConf.getInt("Net.PortCookie", -1);
        if (this.hostPort < 0) {
            this.hostPort = this.parentApplet.paraConf.getInt("Net.HTTP", 8080);
        }
        this.hostServer = this.parentApplet.paraConf.getApplet().getCodeBase().getHost();
    }
    
    public static void printNet() {
        try {
            final Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                final NetworkInterface networkInterface = networkInterfaces.nextElement();
                System.out.println("Net IF: " + networkInterface.getName() + "," + networkInterface);
                final Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    System.out.println("IP: " + inetAddresses.nextElement().toString());
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void retrieveSaved() {
        if (!this.isCook()) {
            return;
        }
        new Thread(new ClientCharRun(this, "ret")).start();
    }
    
    protected void runGet() {
        final Vector load = this.load("ret");
        this.parentApplet.paraConf.printer().print("ret," + load);
        if (load == null) {
            return;
        }
        for (int i = 0; i < load.size(); ++i) {
            this.parseParam(load.elementAt(i));
        }
        this.parentApplet.charNotify();
    }
    
    public void storeBan(final String s, final String s2) {
        this.cookieData.put("BN", s);
        if (s2 != null) {
            this.cookieData.put("ST", s2);
        }
    }
    
    public String loadBan() {
        return this.cookieData.get("BN");
    }
    
    public String loadBanSite() {
        return this.cookieData.get("ST");
    }
    
    public void storeCkGag(final String s, final String s2) {
        this.cookieData.put("CG", s);
        if (s2 != null) {
            this.cookieData.put("GS", s2);
        }
    }
    
    public String loadCkGag() {
        return this.cookieData.get("CG");
    }
    
    public String loadCkGagSite() {
        return this.cookieData.get("GS");
    }
    
    public String getUser() {
        return this.cookieData.get("UN");
    }
    
    public void setUser(final String s) {
        this.cookieData.put("UN", s);
    }
    
    public String getAvatar() {
        return this.cookieData.get("AT");
    }
    
    public void setAvatar(final String s) {
        this.cookieData.put("AT", s);
    }
    
    public Boolean getTree() {
        final String value = this.cookieData.get("TR");
        if (value == null) {
            return null;
        }
        return this.cookieData.getBool(value);
    }
    
    public void setTree(final boolean b) {
        this.cookieData.put("TR", this.cookieData.convertBool(b));
    }
    
    public Boolean getOptTime() {
        final String value = this.cookieData.get("TM");
        if (value == null) {
            return null;
        }
        return this.cookieData.getBool(value);
    }
    
    public void setOptTime(final boolean b) {
        this.cookieData.put("TM", this.cookieData.convertBool(b));
    }
    
    public Boolean getSmiley() {
        final String value = this.cookieData.get("SL");
        if (value == null) {
            return null;
        }
        return this.cookieData.getBool(value);
    }
    
    public void setSmiley(final boolean b) {
        this.cookieData.put("SL", this.cookieData.convertBool(b));
    }
    
    public Boolean getRoomAudio() {
        final String value = this.cookieData.get("RA");
        if (value == null) {
            return null;
        }
        return this.cookieData.getBool(value);
    }
    
    public void setRoomAudio(final boolean b) {
        this.cookieData.put("RA", this.cookieData.convertBool(b));
    }
    
    public Boolean getBlockPrivate() {
        final String value = this.cookieData.get("BP");
        if (value == null) {
            return null;
        }
        return this.cookieData.getBool(value);
    }
    
    public void setBlockPrivate(final boolean b) {
        this.cookieData.put("BP", this.cookieData.convertBool(b));
    }
    
    public Boolean getAuto() {
        final String value = this.cookieData.get("AU");
        if (value == null) {
            return null;
        }
        return this.cookieData.getBool(value);
    }
    
    public void setAuto(final boolean b) {
        this.cookieData.put("AU", this.cookieData.convertBool(b));
    }
    
    public Boolean getOnEnter() {
        final String value = this.cookieData.get("SE");
        if (value == null) {
            return null;
        }
        return this.cookieData.getBool(value);
    }
    
    public void setOnEnter(final boolean b) {
        this.cookieData.put("SE", this.cookieData.convertBool(b));
    }
    
    public String getFileEnter() {
        return this.cookieData.get("EF");
    }
    
    public void setFileEnter(final String s) {
        this.cookieData.put("EF", s);
    }
    
    public Boolean getOnExit() {
        final String value = this.cookieData.get("SX");
        if (value == null) {
            return null;
        }
        return this.cookieData.getBool(value);
    }
    
    public void setOnExit(final boolean b) {
        this.cookieData.put("SX", this.cookieData.convertBool(b));
    }
    
    public String getFileExit() {
        return this.cookieData.get("XF");
    }
    
    public void setFileExit(final String s) {
        this.cookieData.put("XF", s);
    }
    
    public Boolean getOnText() {
        final String value = this.cookieData.get("SN");
        if (value == null) {
            return null;
        }
        return this.cookieData.getBool(value);
    }
    
    public void setOnText(final boolean b) {
        this.cookieData.put("SN", this.cookieData.convertBool(b));
    }
    
    public String getFileText() {
        return this.cookieData.get("NF");
    }
    
    public void setFileText(final String s) {
        this.cookieData.put("NF", s);
    }
    
    public Boolean getNotifyPub() {
        final String value = this.cookieData.get("NP");
        if (value == null) {
            return null;
        }
        return this.cookieData.getBool(value);
    }
    
    public void setNotifyPub(final boolean b) {
        this.cookieData.put("NP", this.cookieData.convertBool(b));
    }
    
    public Boolean getNotifyPrv() {
        final String value = this.cookieData.get("NV");
        if (value == null) {
            return null;
        }
        return this.cookieData.getBool(value);
    }
    
    public void setNotifyPrv(final boolean b) {
        this.cookieData.put("NV", this.cookieData.convertBool(b));
    }
    
    public Boolean getApprovePrv() {
        final String value = this.cookieData.get("AP");
        if (value == null) {
            return null;
        }
        return this.cookieData.getBool(value);
    }
    
    public void setApprovePrv(final boolean b) {
        this.cookieData.put("AP", this.cookieData.convertBool(b));
    }
    
    public Boolean getShowJoin() {
        final String value = this.cookieData.get("JL");
        if (value == null) {
            return null;
        }
        return this.cookieData.getBool(value);
    }
    
    public void setShowJoin(final boolean b) {
        this.cookieData.put("JL", this.cookieData.convertBool(b));
    }
    
    public Boolean getShowAvatar() {
        final String value = this.cookieData.get("SA");
        if (value == null) {
            return null;
        }
        return this.cookieData.getBool(value);
    }
    
    public void setShowAvatar(final boolean b) {
        this.cookieData.put("SA", this.cookieData.convertBool(b));
    }
    
    public Boolean getShowWater() {
        final String value = this.cookieData.get("SB");
        if (value == null) {
            return null;
        }
        return this.cookieData.getBool(value);
    }
    
    public void setShowWater(final boolean b) {
        this.cookieData.put("SB", this.cookieData.convertBool(b));
    }
    
    public Boolean getDouble() {
        final String value = this.cookieData.get("DS");
        if (value == null) {
            return null;
        }
        return this.cookieData.getBool(value);
    }
    
    public void setDouble(final boolean b) {
        this.cookieData.put("DS", this.cookieData.convertBool(b));
    }
    
    public Boolean getSep() {
        final String value = this.cookieData.get("IS");
        if (value == null) {
            return null;
        }
        return this.cookieData.getBool(value);
    }
    
    public void setSep(final boolean b) {
        this.cookieData.put("IS", this.cookieData.convertBool(b));
    }
    
    public String getFontSize() {
        return this.cookieData.get("FS");
    }
    
    public void setFontSize(final String s) {
        this.cookieData.put("FS", s);
    }
    
    public String getMyFont() {
        return this.cookieData.get("UF");
    }
    
    public void setMyFont(final String s) {
        this.cookieData.put("UF", s);
    }
    
    public String getBg() {
        return this.cookieData.get("BG");
    }
    
    public void setBg(final String s) {
        this.cookieData.put("BG", s);
    }
    
    private void parseParam(final String s) {
        final int index = s.indexOf("=");
        if (index < 0) {
            return;
        }
        final String substring = s.substring(0, index);
        if (index + 1 >= s.length()) {
            return;
        }
        String s2 = s.substring(index + 1);
        try {
            s2 = URLDecoder.decode(s2, "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        this.cookieData.put(substring, s2);
    }
    
    public void saveInfo() {
        if (!this.isCook()) {
            return;
        }
        new Thread(new ClientCharRun(this, "sto")).start();
    }
    
    protected void runStore() {
        this.parentApplet.paraConf.printer().print("sto," + this.load("sto"));
    }
    
    private Vector load(final String s) {
        URL url;
        try {
            url = new URL("http", this.hostServer, this.hostPort, this.hostURL);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        URL url2;
        try {
            this.parentApplet.paraConf.printer().print("cha," + url.toExternalForm());
            url2 = new URL(this.generateFullURL(url.toExternalForm(), s));
        }
        catch (MalformedURLException ex2) {
            ex2.printStackTrace();
            return null;
        }
        this.parentApplet.paraConf.printer().print("cha." + url2);
        return SmileDef.getWebData(url2);
    }
    
    private String generateFullURL(final String s, final String s2) {
        String s3 = s + "?" + "com" + "=" + encode(s2);
        final String[] all = this.cookieData.getAll();
        for (int i = 0; i < all.length; ++i) {
            final String s4 = all[i];
            final String value = this.cookieData.get(s4);
            if (value != null) {
                s3 = s3 + "&" + s4 + "=" + encode(value);
            }
        }
        return s3;
    }
    
    public static String encode(final String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return s;
        }
    }
    
    private boolean isCook() {
        return this.parentApplet.paraConf.getBool("Ctrl.CookieOn", true);
    }
}
