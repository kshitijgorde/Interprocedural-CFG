// 
// Decompiled by Procyon v0.5.30
// 

package jchatbox.client.local;

import jchatbox.client.util.Debug;
import jchatbox.client.http.HTTPClient;
import java.util.Enumeration;
import java.net.URLConnection;
import java.io.ByteArrayOutputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import jchatbox.client.util.Conf;
import java.util.Vector;
import jchatbox.client.util.ChatException;
import nanoxml.XMLElement;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.net.URL;

public class Communication
{
    private static Communication _$5857;
    private URL _$5858;
    private Hashtable _$5859;
    private int _$5860;
    
    private Communication(final String s) {
        this._$5858 = null;
        this._$5859 = null;
        this._$5860 = -1;
        try {
            this._$5858 = new URL(s);
        }
        catch (MalformedURLException ex) {
            this._$619(0, this.getClass().getName(), "Communication error : ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
        }
    }
    
    public static synchronized Communication getInstance(final String s) {
        if (Communication._$5857 != null) {
            return Communication._$5857;
        }
        return Communication._$5857 = new Communication(s);
    }
    
    private ChatroomDesc _$5861(final ChatroomDesc chatroomDesc, final StringBuffer sb) throws ChatException {
        final XMLElement xmlElement = new XMLElement();
        xmlElement.parseString(sb.toString(), 0);
        if (xmlElement.getTagName().equals("ERROR")) {
            throw new ChatException(xmlElement.getContents());
        }
        if (xmlElement.getTagName().equals("CHAT")) {
            chatroomDesc.setPrivateMsg(xmlElement.getProperty("PRIVATEMSG", (String)null));
            chatroomDesc.setRefresh(xmlElement.getProperty("REFRESH", (String)null));
            final Vector children = xmlElement.getChildren();
            for (int i = 0; i < children.size(); ++i) {
                final XMLElement xmlElement2 = children.elementAt(i);
                if (xmlElement2.getTagName().equals("MSGS")) {
                    final Vector children2 = xmlElement2.getChildren();
                    final Vector<Msg> msgs = new Vector<Msg>();
                    for (int j = 0; j < children2.size(); ++j) {
                        final XMLElement xmlElement3 = children2.elementAt(j);
                        msgs.addElement(new Msg(xmlElement3.getProperty("DATE", (String)null), xmlElement3.getProperty("FROM", (String)null), xmlElement3.getProperty("TO", "ALL"), xmlElement3.getContents(), xmlElement3.getProperty("TYPE", -1)));
                    }
                    chatroomDesc.setMsgs(msgs);
                }
                else if (xmlElement2.getTagName().equals("USERLIST")) {
                    chatroomDesc.setTotalUsers(xmlElement2.getProperty("CURRENT", (String)null));
                    chatroomDesc.setMaxUsers(xmlElement2.getProperty("MAX", (String)null));
                    final Vector children3 = xmlElement2.getChildren();
                    final Vector<String> userList = new Vector<String>();
                    for (int k = 0; k < children3.size(); ++k) {
                        userList.addElement(children3.elementAt(k).getProperty("NAME", (String)null));
                    }
                    chatroomDesc.setUserList(userList);
                }
            }
            return chatroomDesc;
        }
        throw new ChatException(Conf.INVALIDXML);
    }
    
    public Vector doLogout() throws Exception, ChatException {
        StringBuffer sb;
        if (this._$5860 > 0) {
            sb = this.getNonBlockingXML("todo=logout&rand=".concat(String.valueOf(String.valueOf(this._$5865()))));
        }
        else {
            sb = this.getXML("todo=logout&rand=".concat(String.valueOf(String.valueOf(this._$5865()))));
        }
        this._$5859 = null;
        this._$619(10, this.getClass().getName(), "XML: ".concat(String.valueOf(String.valueOf(sb.toString()))));
        return this.getChatrooms(sb);
    }
    
    public ChatroomDesc doLogin(ChatroomDesc $5861, final String s) throws Exception, ChatException {
        final String id = $5861.getID();
        if (id == null || s == null) {
            throw new ChatException(Conf.PARAMETERSMISSING);
        }
        if (s.length() >= Conf.NAMELENGTH) {
            throw new ChatException(Conf.NAMETOOLONG);
        }
        StringBuffer sb;
        if (this._$5860 > 0) {
            sb = this.getNonBlockingXML(String.valueOf(String.valueOf(new StringBuffer("todo=login&chatrooms=").append(id).append("&name=").append(s).append("&newonly=true").append("&rand=").append(this._$5865()))));
        }
        else {
            sb = this.getXML(String.valueOf(String.valueOf(new StringBuffer("todo=login&chatrooms=").append(id).append("&name=").append(s).append("&newonly=true").append("&rand=").append(this._$5865()))));
        }
        this._$619(10, this.getClass().getName(), "XML: ".concat(String.valueOf(String.valueOf(sb.toString()))));
        $5861 = this._$5861($5861, sb);
        return $5861;
    }
    
    public ChatroomDesc doRefresh(ChatroomDesc $5861) throws Exception, ChatException {
        StringBuffer sb;
        if (this._$5860 > 0) {
            sb = this.getNonBlockingXML("todo=refresh&newonly=true&rand=".concat(String.valueOf(String.valueOf(this._$5865()))));
        }
        else {
            sb = this.getXML("todo=refresh&newonly=true&rand=".concat(String.valueOf(String.valueOf(this._$5865()))));
        }
        this._$619(10, this.getClass().getName(), "XML: ".concat(String.valueOf(String.valueOf(sb.toString()))));
        $5861 = this._$5861($5861, sb);
        return $5861;
    }
    
    public ChatroomDesc doChat(ChatroomDesc $5861, final String s, String s2) throws Exception, ChatException {
        if (s2 == null) {
            s2 = "ALL";
        }
        StringBuffer sb;
        if (this._$5860 > 0) {
            sb = this.getNonBlockingXML(String.valueOf(String.valueOf(new StringBuffer("todo=chat&to=").append(s2).append("&msg=").append(URLEncoder.encode(s)).append("&newonly=true").append("&rand=").append(this._$5865()))));
        }
        else {
            sb = this.getXML(String.valueOf(String.valueOf(new StringBuffer("todo=chat&to=").append(s2).append("&msg=").append(URLEncoder.encode(s)).append("&newonly=true").append("&rand=").append(this._$5865()))));
        }
        this._$619(10, this.getClass().getName(), "XML: ".concat(String.valueOf(String.valueOf(sb.toString()))));
        $5861 = this._$5861($5861, sb);
        return $5861;
    }
    
    public Vector doManager() throws Exception, ChatException {
        final Vector vector = new Vector();
        StringBuffer sb;
        if (this._$5860 > 0) {
            sb = this.getNonBlockingXML("todo=manager&rand=".concat(String.valueOf(String.valueOf(this._$5865()))));
        }
        else {
            sb = this.getXML("todo=manager&rand=".concat(String.valueOf(String.valueOf(this._$5865()))));
        }
        this._$619(10, this.getClass().getName(), "XML: ".concat(String.valueOf(String.valueOf(sb.toString()))));
        return this.getChatrooms(sb);
    }
    
    public Hashtable doResources(final String s) throws Exception, ChatException {
        final Hashtable hashtable = new Hashtable();
        StringBuffer sb;
        if (this._$5860 > 0) {
            sb = this.getNonBlockingXML(String.valueOf(String.valueOf(new StringBuffer("todo=resource&language=").append(s).append("&rand=").append(this._$5865()))));
        }
        else {
            sb = this.getXML(String.valueOf(String.valueOf(new StringBuffer("todo=resource&language=").append(s).append("&rand=").append(this._$5865()))));
        }
        this._$619(10, this.getClass().getName(), "XML: ".concat(String.valueOf(String.valueOf(sb.toString()))));
        return this.getResources(sb);
    }
    
    public Hashtable getResources(final StringBuffer sb) throws Exception, ChatException {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        final XMLElement xmlElement = new XMLElement();
        xmlElement.parseString(sb.toString(), 0);
        if (xmlElement.getTagName().equals("ERROR")) {
            throw new ChatException(xmlElement.getContents());
        }
        if (xmlElement.getTagName().equals("RESOURCES")) {
            final Vector children = xmlElement.getChildren();
            for (int i = 0; i < children.size(); ++i) {
                final XMLElement xmlElement2 = children.elementAt(i);
                if (!xmlElement2.getTagName().equals("ELEMENT")) {
                    throw new ChatException(Conf.INVALIDXML);
                }
                final Vector children2 = xmlElement2.getChildren();
                final XMLElement xmlElement3 = children2.elementAt(0);
                final XMLElement xmlElement4 = children2.elementAt(1);
                final String contents = xmlElement3.getContents();
                final String contents2 = xmlElement4.getContents();
                if (contents != null && contents2 != null) {
                    hashtable.put(contents, contents2);
                }
            }
            return hashtable;
        }
        throw new ChatException(Conf.INVALIDXML);
    }
    
    public Vector doLanguages() throws Exception, ChatException {
        final Vector vector = new Vector();
        StringBuffer sb;
        if (this._$5860 > 0) {
            sb = this.getNonBlockingXML("todo=languages&rand=".concat(String.valueOf(String.valueOf(this._$5865()))));
        }
        else {
            sb = this.getXML("todo=languages&rand=".concat(String.valueOf(String.valueOf(this._$5865()))));
        }
        this._$619(10, this.getClass().getName(), "XML: ".concat(String.valueOf(String.valueOf(sb.toString()))));
        return this.getLanguages(sb);
    }
    
    public Vector getLanguages(final StringBuffer sb) throws Exception, ChatException {
        final Vector<String> vector = new Vector<String>();
        final XMLElement xmlElement = new XMLElement();
        xmlElement.parseString(sb.toString(), 0);
        if (xmlElement.getTagName().equals("ERROR")) {
            throw new ChatException(xmlElement.getContents());
        }
        if (xmlElement.getTagName().equals("LANGUAGES")) {
            vector.addElement(xmlElement.getProperty("DEFAULT", "english"));
            final Vector children = xmlElement.getChildren();
            for (int i = 0; i < children.size(); ++i) {
                vector.addElement(new String(children.elementAt(i).getProperty("NAME", (String)null)));
            }
            return vector;
        }
        throw new ChatException(Conf.INVALIDXML);
    }
    
    public Vector getChatrooms(final StringBuffer sb) throws Exception, ChatException {
        final Vector<ChatroomDesc> vector = new Vector<ChatroomDesc>();
        final XMLElement xmlElement = new XMLElement();
        xmlElement.parseString(sb.toString(), 0);
        if (xmlElement.getTagName().equals("ERROR")) {
            throw new ChatException(xmlElement.getContents());
        }
        if (xmlElement.getTagName().equals("MANAGER")) {
            final Vector children = xmlElement.getChildren();
            for (int i = 0; i < children.size(); ++i) {
                final XMLElement xmlElement2 = children.elementAt(i);
                vector.addElement(new ChatroomDesc(xmlElement2.getProperty("DATE", (String)null), xmlElement2.getProperty("ID", (String)null), xmlElement2.getProperty("MAXUSERS", (String)null), xmlElement2.getProperty("NAME", (String)null), xmlElement2.getProperty("SUBJECT", (String)null), xmlElement2.getProperty("TOTALUSERS", (String)null), xmlElement2.getProperty("LANGUAGE", (String)null)));
            }
            return vector;
        }
        throw new ChatException(Conf.INVALIDXML);
    }
    
    protected StringBuffer getXML(final String s) throws Exception {
        this._$5878(false);
        final StringBuffer sb = new StringBuffer();
        final URLConnection openConnection = this._$5858.openConnection();
        openConnection.setDoOutput(true);
        openConnection.setDoInput(true);
        openConnection.setUseCaches(false);
        openConnection.setAllowUserInteraction(false);
        openConnection.setRequestProperty("User-Agent", "jChatBox Applet 2.5");
        openConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        if (this._$5859 != null) {
            this._$619(9, this.getClass().getName(), "Cookies : ".concat(String.valueOf(String.valueOf(this._$5859))));
            final Enumeration<String> keys = this._$5859.keys();
            while (keys.hasMoreElements()) {
                final String s2 = keys.nextElement();
                openConnection.setRequestProperty(s2, (String)this._$5859.get(s2));
            }
        }
        final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(openConnection.getOutputStream()));
        bufferedWriter.write(s);
        bufferedWriter.flush();
        bufferedWriter.close();
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(String.valueOf(String.valueOf(line)).concat("\n"));
        }
        bufferedReader.close();
        return sb;
    }
    
    private void _$5878(final boolean b) throws Exception {
        if (this._$5859 == null || b) {
            final URLConnection openConnection = this._$5858.openConnection();
            openConnection.setDoOutput(true);
            openConnection.setDoInput(true);
            openConnection.setUseCaches(false);
            openConnection.setAllowUserInteraction(false);
            openConnection.setRequestProperty("User-Agent", "jChatBox Applet 2.5");
            final Hashtable $5859 = new Hashtable<String, String>();
            int n = 1;
            for (String s = openConnection.getHeaderFieldKey(n); s != null; s = openConnection.getHeaderFieldKey(n)) {
                if (s.startsWith("Set-Cookie")) {
                    final String headerField = openConnection.getHeaderField(s);
                    if (headerField != null) {
                        this._$619(9, this.getClass().getName(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(":").append(headerField))));
                        final int index = headerField.indexOf(";");
                        if (index != -1) {
                            $5859.put(s.substring(4, s.length()), headerField.substring(0, index));
                        }
                    }
                }
                ++n;
            }
            if (!$5859.isEmpty()) {
                this._$5859 = $5859;
            }
        }
    }
    
    protected StringBuffer getNonBlockingXML(final String s) throws Exception {
        this._$5883(false);
        final StringBuffer sb = new StringBuffer();
        final HTTPClient httpClient = new HTTPClient(this._$5858);
        httpClient.setTimeOut(this._$5860);
        httpClient.setParams("?".concat(String.valueOf(String.valueOf(s))));
        httpClient.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpClient.setRequestProperty("User-Agent", "jChatBox Applet 2.5");
        if (this._$5859 != null) {
            this._$619(9, this.getClass().getName(), "Cookies : ".concat(String.valueOf(String.valueOf(this._$5859))));
            final Enumeration<String> keys = this._$5859.keys();
            while (keys.hasMoreElements()) {
                final String s2 = keys.nextElement();
                httpClient.setRequestProperty(s2, (String)this._$5859.get(s2));
            }
        }
        return httpClient.doGet().getBody();
    }
    
    private void _$5883(final boolean b) throws Exception {
        if (this._$5859 == null || b) {
            final HTTPClient httpClient = new HTTPClient(this._$5858);
            httpClient.setTimeOut(this._$5860);
            httpClient.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpClient.setRequestProperty("User-Agent", "jChatBox Applet 2.5");
            final Vector headers = httpClient.doGet().getHeaders();
            final Hashtable $5859 = new Hashtable<String, String>();
            int n = 1;
            String s = headers.elementAt(n);
            String s2 = s.substring(0, s.indexOf(":"));
            while (s2 != null) {
                if (s2.startsWith("Set-Cookie")) {
                    final String trim = s.substring(s.indexOf(":") + 1, s.length()).trim();
                    if (trim != null) {
                        this._$619(9, this.getClass().getName(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(":").append(trim))));
                        final int index = trim.indexOf(";");
                        if (index != -1) {
                            $5859.put(s2.substring(4, s2.length()), trim.substring(0, index));
                        }
                    }
                }
                if (++n < headers.size()) {
                    s = headers.elementAt(n);
                    s2 = s.substring(0, s.indexOf(":"));
                }
                else {
                    s2 = null;
                }
            }
            if (!$5859.isEmpty()) {
                this._$5859 = $5859;
            }
        }
    }
    
    private void _$619(final int n, final String s, final String s2) {
        Debug.log(n, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(":").append(s2))));
    }
    
    private long _$5865() {
        return System.currentTimeMillis();
    }
    
    public void resetSession() {
        this._$5859 = null;
        this._$619(9, this.getClass().getName(), "Reset session");
    }
    
    public int getNonBlockingMode() {
        return this._$5860;
    }
    
    public void setNonBlockingMode(final int $5860) {
        this._$5860 = $5860;
        this._$619(9, this.getClass().getName(), "TimeOut=".concat(String.valueOf(String.valueOf(this._$5860))));
    }
    
    static {
        Communication._$5857 = null;
    }
}
