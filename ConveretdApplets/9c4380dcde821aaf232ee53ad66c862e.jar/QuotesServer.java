import netscape.javascript.JSObject;
import java.io.InterruptedIOException;
import java.net.URL;
import java.io.Reader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Enumeration;
import java.util.Date;
import java.util.Hashtable;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.net.Socket;
import java.net.URLConnection;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class QuotesServer extends Applet implements Runnable
{
    public static final int TCP = 1;
    public static final int HTTP = 2;
    private int httpSessionTime;
    private int sleepTime;
    private int reconnectSleepTime;
    private int maxTimeoutCount;
    private String loginKey;
    private String user;
    private Vector tcpList;
    private Vector httpList;
    private boolean compress;
    private boolean encode;
    private String raiseMessageScript;
    private boolean raiseMessage;
    private boolean stop;
    private boolean connected;
    private int connectType;
    private int connectId;
    private boolean chkB;
    private URLConnection urlConnection;
    private Socket socket;
    private BufferedReader bufferedReader;
    private InputStream inputStream;
    private BufferedOutputStream bufferedOutputStream;
    private int timeoutCount;
    private String mId;
    private Hashtable mktWatches;
    private Vector mbpKeys;
    private String mwColumnList;
    private Vector interactiveCache;
    private String[] broadcastCache;
    private int broadcastCacheSize;
    private int broadcastCacheFreePtr;
    private int broadcastCacheLastPtr;
    private Hashtable stats;
    private long firstConnectTime;
    private int connectCount;
    private long totReceivedBytes;
    private long totUnCompressedBytes;
    private long lStartTime;
    private long lStopTime;
    
    public QuotesServer() {
        this.httpSessionTime = 600000;
        this.sleepTime = 100;
        this.reconnectSleepTime = 2000;
        this.maxTimeoutCount = 1;
        this.broadcastCacheSize = 100;
        this.broadcastCacheFreePtr = 0;
        this.broadcastCacheLastPtr = 0;
    }
    
    public void init() {
        this.info("init start");
        this.loginKey = this.getParameter("LoginKey");
        this.user = this.getParameter("User");
        this.tcpList = this.split(this.getParameter("TcpList"), ",");
        this.httpList = this.split(this.getParameter("HttpList"), ",");
        final String parameter = this.getParameter("Compress");
        this.compress = false;
        if ("Y".equalsIgnoreCase(parameter)) {
            this.compress = true;
        }
        final String parameter2 = this.getParameter("Encode");
        this.encode = false;
        if ("Y".equalsIgnoreCase(parameter2)) {
            this.encode = true;
        }
        this.raiseMessageScript = this.getParameter("RaiseMessageScript");
        this.raiseMessage = this.hasValue(this.raiseMessageScript);
        this.mktWatches = new Hashtable();
        this.mbpKeys = new Vector();
        this.stats = new Hashtable();
        this.info("init complete");
    }
    
    public void setUser(final String user) {
        this.user = user;
    }
    
    public void setLoginKey(final String loginKey) {
        this.loginKey = loginKey;
    }
    
    public void setCompress(final boolean compress) {
        this.compress = compress;
    }
    
    public void setEncode(final boolean encode) {
        this.encode = encode;
    }
    
    public String getStats() {
        final Enumeration<String> keys = this.stats.keys();
        final StringBuffer sb = new StringBuffer();
        final String string = new Date(this.firstConnectTime).toString();
        final long n = System.currentTimeMillis() - this.firstConnectTime;
        sb.append(this.connectCount).append("|").append(this.totReceivedBytes).append("|").append(this.totUnCompressedBytes).append("|");
        sb.append(string).append("|").append(n).append("~");
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final long[] array = this.stats.get(s);
            sb.append(s).append("|");
            sb.append(array[0]).append("|");
            sb.append(array[1]).append("~");
        }
        return sb.toString();
    }
    
    private Vector split(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final Vector<String> vector = new Vector<String>();
        while (stringTokenizer.hasMoreTokens()) {
            vector.addElement(stringTokenizer.nextToken());
        }
        return vector;
    }
    
    public void start() {
        this.info("start begin");
        this.connected = false;
        this.stop = false;
        this.chkB = false;
        this.connectType = 2;
        this.connectId = 0;
        this.interactiveCache = new Vector();
        this.broadcastCache = new String[this.broadcastCacheSize];
    }
    
    public void run() {
        if (Thread.currentThread().getName().compareTo("receive") == 0) {
            this.receive();
        }
        else if (Thread.currentThread().getName().compareTo("send") == 0) {
            this.send();
        }
    }
    
    public void reConnect() {
    }
    
    public void setMWOpenClose(final String s, final String s2, final String s3, final String mwColumnList) {
        this.mId = s2.trim();
        final Vector<String> vector = new Vector<String>();
        if (this.hasValue(s)) {
            this.mktWatches.remove(s.trim());
            vector.addElement("CLOSE|" + this.user + "|" + this.loginKey + "|" + s);
        }
        if (this.hasValue(s2)) {
            this.mktWatches.put(s2.trim(), this.split(s3, "~"));
            vector.addElement("OPEN|" + this.user + "|" + this.loginKey + "|" + s2 + "|" + s3);
        }
        if (this.hasValue(mwColumnList)) {
            this.mwColumnList = mwColumnList;
            vector.addElement("SETCOLUMNS|" + this.user + "|" + this.loginKey + "|61|" + mwColumnList);
        }
        this.chkB = true;
        new Thread(this, "receive").start();
        new Thread(this, "send").start();
        this.info("start end");
    }
    
    private void setMWAddRemove(String trim, final String s) {
        final Vector<String> vector = new Vector<String>();
        trim = trim.trim();
        final int index = trim.indexOf("|");
        final String trim2 = trim.substring(0, index).trim();
        final Vector split = this.split(trim.substring(index + 1).trim(), "~");
        final Vector vector2 = this.mktWatches.get(trim2);
        if (vector2 != null) {
            if ("ADD".equals(s)) {
                this.addAll(vector2, split);
            }
            else {
                this.removeAll(vector2, split);
            }
        }
        vector.addElement(s + "|" + this.user + "|" + this.loginKey + "|" + trim);
        this.sendClientMessages(vector);
    }
    
    public void setMWRemove(final String s) {
        this.setMWAddRemove(s, "REMOVE");
    }
    
    public void setMWAdd(final String s) {
        this.setMWAddRemove(s, "ADD");
    }
    
    public void setMBPOpenClose(final String s, final String s2) {
        final Vector<String> vector = new Vector<String>();
        if (this.hasValue(s)) {
            this.removeAll(this.mbpKeys, this.split(s, "~"));
            vector.addElement("CLOSEMBP|" + this.user + "|" + this.loginKey + "|" + s.trim());
        }
        if (this.hasValue(s2)) {
            this.addAll(this.mbpKeys, this.split(s2, "~"));
            vector.addElement("OPENMBP|" + this.user + "|" + this.loginKey + "|" + s2.trim());
        }
        this.sendClientMessages(vector);
    }
    
    private Vector getReConnectMessages() {
        final Vector<String> vector = new Vector<String>();
        String s = "N";
        final StringBuffer sb = new StringBuffer();
        if (this.compress && this.encode) {
            s = "YE";
        }
        else if (!this.compress && !this.encode) {
            s = "N";
        }
        else if (this.compress) {
            s = "Y";
        }
        else if (this.encode) {
            s = "E";
        }
        vector.addElement("CONNECT|" + this.user + "|" + this.loginKey + "|TCP|" + s + "|||Y|");
        this.mktWatches.keys();
        while (!this.chkB) {
            this.info("inside chkb");
        }
        final Vector<String> vector2 = this.mktWatches.get(this.mId);
        sb.setLength(0);
        sb.append("OPEN|").append(this.user).append("|").append(this.loginKey).append("|").append(this.mId).append("|");
        for (int i = 0; i < vector2.size(); ++i) {
            sb.append(vector2.elementAt(i)).append("~");
        }
        vector.addElement(sb.toString());
        for (int j = 0; j < this.mbpKeys.size(); ++j) {
            vector.addElement("OPENMBP|" + this.user + "|" + this.loginKey + "|" + (String)this.mbpKeys.elementAt(j));
        }
        if (this.hasValue(this.mwColumnList)) {
            vector.addElement("SETCOLUMNS|" + this.user + "|" + this.loginKey + "|61|" + this.mwColumnList);
        }
        return vector;
    }
    
    private void closeConnection() {
        try {
            if (this.bufferedOutputStream != null) {
                this.bufferedOutputStream.close();
            }
            if (this.inputStream != null) {
                this.inputStream.close();
            }
            if (this.bufferedReader != null) {
                this.bufferedReader.close();
            }
            if (this.socket != null) {
                this.socket.close();
            }
            this.bufferedOutputStream = null;
            this.inputStream = null;
            this.bufferedReader = null;
            this.socket = null;
            this.urlConnection = null;
        }
        catch (Exception ex) {
            this.error(ex);
        }
    }
    
    private boolean hasValue(final String s) {
        return s != null && s.trim().length() > 0;
    }
    
    private void getConnection() {
        this.info("getconnection begin");
        this.info("Getting connection11111");
        if (!this.hasValue(this.user) || !this.hasValue(this.loginKey)) {
            return;
        }
        if (!this.connected) {
            for (int n = 0; n < 2 && !this.connected; ++n) {
                final Vector reConnectMessages = this.getReConnectMessages();
                if (this.connectType == 1) {
                    for (int connectId = 0; connectId < this.tcpList.size() && !this.connected; ++connectId) {
                        try {
                            this.closeConnection();
                            final String s = this.tcpList.elementAt(connectId);
                            String s2 = s.substring(0, s.indexOf(":"));
                            if (!this.hasValue(s2)) {
                                s2 = this.getDocumentBase().getHost();
                            }
                            final int int1 = Integer.parseInt(s.substring(s.indexOf(":") + 1));
                            this.info("Socket: " + s2 + " " + int1);
                            this.socket = new Socket(s2, int1);
                            this.inputStream = this.socket.getInputStream();
                            this.bufferedOutputStream = new BufferedOutputStream(this.socket.getOutputStream());
                            this.socket.setSoTimeout(5000);
                            if (!this.compress && !this.encode) {
                                this.bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));
                            }
                            this.connected = true;
                            this.connectId = connectId;
                            this.sendClientMessages(reConnectMessages);
                        }
                        catch (Exception ex) {
                            this.connected = false;
                            this.error(ex);
                        }
                    }
                    if (!this.connected) {
                        this.connectType = 2;
                    }
                }
                else if (this.connectType == 2) {
                    final StringBuffer sb = new StringBuffer("?MODE=CONNECT&MESSAGECOUNT=");
                    sb.append(reConnectMessages.size()).append("&MAXTIMEOUT=").append(this.httpSessionTime);
                    for (int i = 0; i < reConnectMessages.size(); ++i) {
                        sb.append("&MESSAGE").append(i).append("=").append(reConnectMessages.elementAt(i));
                    }
                    for (int connectId2 = 0; connectId2 < this.httpList.size() && !this.connected; ++connectId2) {
                        try {
                            this.closeConnection();
                            String string = this.httpList.elementAt(connectId2);
                            this.info("OLD URL " + string);
                            if (!string.startsWith("http")) {
                                string = "http://" + this.getDocumentBase().getHost() + string + sb.toString();
                            }
                            this.info("URL " + string);
                            this.urlConnection = new URL(string).openConnection();
                            this.inputStream = this.urlConnection.getInputStream();
                            if (!this.compress && !this.encode) {
                                this.bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));
                            }
                            this.connected = true;
                            this.connectId = connectId2;
                        }
                        catch (Exception ex2) {
                            this.connected = false;
                            this.error(ex2);
                        }
                    }
                    if (!this.connected) {
                        this.connectType = 1;
                    }
                }
            }
        }
        if (this.connected) {
            this.debug("Got connection of type " + this.connectType);
            ++this.connectCount;
            if (this.firstConnectTime == 0L) {
                this.firstConnectTime = System.currentTimeMillis();
            }
            this.handleMessage("101|" + ((this.connectType == 1) ? "TCP" : "HTTP"));
        }
        this.info("getconnection end");
    }
    
    private void checkConnection() throws Exception {
        this.debug("checkingconnection ");
        this.timeoutCount = 0;
        final Vector<String> vector = new Vector<String>();
        if (this.connected && this.connectType == 1) {
            vector.addElement("DUMMY|" + this.user + "|" + this.loginKey + "|");
            this.sendClientMessages(vector);
            if (!this.connected) {
                throw new Exception("TCP connection broken");
            }
        }
    }
    
    private void sendClientMessages(final Vector vector) {
        this.debug("sendClientMessages connectType=" + this.connectType);
        try {
            if (this.connected && vector.size() > 0) {
                final StringBuffer sb = new StringBuffer();
                for (int i = 0; i < vector.size(); ++i) {
                    this.info("i" + vector.elementAt(i));
                    if (this.connectType == 1) {
                        sb.append(vector.elementAt(i)).append("\n");
                    }
                    else if (this.connectType == 2) {
                        sb.append("&MESSAGE").append(i).append("=").append(vector.elementAt(i));
                    }
                }
                if (this.connectType == 1) {
                    this.info("Sending Client message on TCP : " + sb.toString());
                    this.bufferedOutputStream.write(sb.toString().getBytes());
                    this.bufferedOutputStream.flush();
                }
                else if (this.connectType == 2) {
                    new URL("http://" + this.httpList.elementAt(this.connectId) + "/stocks/BroadcastServlet?MODE=PROXY&MESSAGECOUNT=" + vector.size() + sb.toString()).openConnection().getInputStream();
                    this.debug("Client message sent");
                }
            }
        }
        catch (Exception ex) {
            this.error(ex);
            this.connected = false;
            this.info("inside eror");
        }
    }
    
    private void sendClientMessagesP(final Vector vector) {
        this.debug("sendClientMessages connectType=" + this.connectType);
        try {
            if (this.connected && vector.size() > 0) {
                final StringBuffer sb = new StringBuffer();
                for (int i = 0; i < vector.size(); ++i) {
                    this.info("i" + vector.elementAt(i));
                    if (this.connectType == 1) {
                        sb.append(vector.elementAt(i)).append("\n");
                    }
                    else if (this.connectType == 2) {
                        sb.append("&MESSAGE").append(i).append("=").append(vector.elementAt(i));
                    }
                }
                if (this.connectType == 1) {
                    this.info("Sending Client message on TCP : " + sb.toString());
                    this.bufferedOutputStream.write(sb.toString().getBytes());
                    this.bufferedOutputStream.flush();
                }
                else if (this.connectType == 2) {
                    new URL("http://" + this.httpList.elementAt(this.connectId) + "/stocks/BroadcastServlet?MODE=PROXY&MESSAGECOUNT=" + vector.size() + sb.toString()).openConnection().getInputStream();
                    this.debug("Client message sent");
                }
            }
        }
        catch (Exception ex) {
            this.error(ex);
            this.connected = false;
            this.info("inside eror");
        }
    }
    
    public void receive() {
        this.info("receive begin");
        int n = 0;
        int n2 = 0;
        final byte[] array = new byte[5000];
        boolean b = false;
        this.lStopTime = System.currentTimeMillis();
        this.lStartTime = System.currentTimeMillis();
        this.debug("In receive");
        while (!this.stop) {
            if (!this.connected) {
                this.getConnection();
            }
            if (!this.connected) {
                try {
                    Thread.sleep(this.reconnectSleepTime);
                }
                catch (InterruptedException ex2) {}
            }
            else {
                while (this.connected) {
                    this.lStopTime = System.currentTimeMillis();
                    this.info("before");
                    if (this.lStopTime - this.lStartTime > 120000L) {
                        this.info("after");
                        this.connected = false;
                        this.closeConnection();
                        this.info("connection close");
                        b = true;
                        break;
                    }
                    try {
                        if (!this.compress) {
                            if (this.encode) {
                                this.readBytes(this.inputStream, 2, array);
                                n = (array[0] & 0xFF) * 256 + (array[1] & 0xFF);
                                this.readBytes(this.inputStream, n, array);
                                final String decode = this.decode(array, n);
                                final Vector split = this.split(decode, "\n");
                                for (int i = 0; i < split.size(); ++i) {
                                    this.handleMessage(split.elementAt(i));
                                }
                                this.debug("lLine " + decode + "|" + this.broadcastCacheLastPtr + "," + this.broadcastCacheFreePtr);
                                n += 2;
                                n2 = decode.length();
                            }
                            else {
                                try {
                                    final String line = this.bufferedReader.readLine();
                                    if (line != null) {
                                        this.debug("Receoived message " + line);
                                        n = (n2 = line.length());
                                        this.handleMessage(line);
                                    }
                                    else {
                                        this.connected = false;
                                    }
                                }
                                catch (InterruptedIOException ex3) {
                                    ++this.timeoutCount;
                                    this.debug("timeout " + this.timeoutCount);
                                    if (this.timeoutCount >= this.maxTimeoutCount) {
                                        this.checkConnection();
                                    }
                                }
                            }
                        }
                        this.totReceivedBytes += n;
                        this.totUnCompressedBytes += n2;
                    }
                    catch (Exception ex) {
                        this.error(ex);
                        this.connected = false;
                    }
                }
                this.handleMessage("101|OFF");
                if (b) {
                    break;
                }
                continue;
            }
        }
        if (b) {
            this.handleMessage("103|Do you want to continue the streaming?");
        }
        this.info("receive end");
    }
    
    private void handleMessage(final String s) {
        this.info("pMsg:-" + s);
        final String substring = s.substring(0, s.indexOf("|"));
        long[] array = this.stats.get(substring);
        if (array == null) {
            array = new long[2];
            this.stats.put(substring, array);
        }
        final long[] array2 = array;
        final int n = 0;
        ++array2[n];
        final long[] array3 = array;
        final int n2 = 1;
        array3[n2] += s.length();
        if ("57".equals(substring)) {
            synchronized (this.interactiveCache) {
                this.interactiveCache.addElement(s);
                return;
            }
        }
        synchronized (this.broadcastCache) {
            this.broadcastCache[this.broadcastCacheFreePtr++] = s;
            if (this.broadcastCacheFreePtr >= this.broadcastCacheSize) {
                this.broadcastCacheFreePtr = 0;
            }
        }
    }
    
    private void readBytes(final InputStream inputStream, final int n, final byte[] array) throws Exception {
        int n2 = 0;
        while (n2 != n && !this.stop) {
            try {
                n2 += inputStream.read(array, n2, n - n2);
            }
            catch (InterruptedIOException ex) {
                ++this.timeoutCount;
                if (this.timeoutCount < this.maxTimeoutCount) {
                    continue;
                }
                this.checkConnection();
            }
        }
        if (this.stop) {
            throw new Exception("Receive Thread Stopped");
        }
    }
    
    public void send() {
        this.info("send begin");
        final String[] array = { null };
        while (!this.stop) {
            try {
                while (!this.stop && this.interactiveCache.size() > 0) {
                    array[0] = this.interactiveCache.elementAt(0);
                    this.debug("Raising interactive message " + array[0]);
                    if (this.raiseMessage) {
                        JSObject.getWindow((Applet)this).call(this.raiseMessageScript, (Object[])array);
                    }
                    synchronized (this.interactiveCache) {
                        this.interactiveCache.removeElementAt(0);
                        continue;
                    }
                    break;
                }
                while (!this.stop && this.broadcastCacheLastPtr != this.broadcastCacheFreePtr) {
                    array[0] = this.broadcastCache[this.broadcastCacheLastPtr++];
                    if (array[0].startsWith("99")) {
                        this.stop = true;
                    }
                    if (this.raiseMessage) {
                        JSObject.getWindow((Applet)this).call(this.raiseMessageScript, (Object[])array);
                    }
                    if (this.broadcastCacheLastPtr >= this.broadcastCacheSize) {
                        this.broadcastCacheLastPtr = 0;
                    }
                }
            }
            catch (Exception ex) {
                this.error(ex);
            }
            try {
                Thread.sleep(this.sleepTime);
            }
            catch (InterruptedException ex2) {}
        }
        this.info("send end");
    }
    
    public void destroy() {
        this.stop = true;
    }
    
    private void error(final Exception ex) {
        System.out.println("ERROR:" + ex.getMessage());
        ex.printStackTrace();
    }
    
    private void debug(final String s) {
    }
    
    private void info(final String s) {
        System.out.println("INFO:" + s);
    }
    
    private int decodeInt(final int n) {
        if (n >= 0 && n <= 9) {
            return n + 48;
        }
        if (n == 11) {
            return 43;
        }
        if (n == 12) {
            return 45;
        }
        if (n == 13) {
            return 58;
        }
        if (n == 14) {
            return 124;
        }
        if (n == 15) {
            return 255;
        }
        return 255;
    }
    
    public String decode(final byte[] array, final int n) {
        final char[] array2 = new char[n];
        for (int i = 0; i < n; ++i) {
            if (array[i] < 0) {
                array2[i] = (char)(array[i] + 256);
            }
            else {
                array2[i] = (char)array[i];
            }
        }
        return this.decode(array2);
    }
    
    public String decode(final String s) {
        return this.decode(s.toCharArray());
    }
    
    public String decode(final char[] array) {
        final StringBuffer sb = new StringBuffer();
        int n = 1;
        for (int i = 0; i < array.length; ++i) {
            int n2 = array[i];
            if (n2 < 0) {
                n2 += 256;
            }
            final char c = (char)n2;
            if (n != 0) {
                final int decodeInt = this.decodeInt(c >> 4);
                if (decodeInt != 255) {
                    sb.append((char)decodeInt);
                }
                else {
                    n = 0;
                }
                if (n != 0) {
                    final int decodeInt2 = this.decodeInt(c & '\u000f');
                    if (decodeInt2 != 255) {
                        sb.append((char)decodeInt2);
                    }
                    else {
                        n = 0;
                    }
                }
            }
            else if (c != '\u00ff') {
                sb.append(c);
            }
            else {
                n = 1;
            }
        }
        return sb.toString();
    }
    
    public void addAll(final Vector vector, final Vector vector2) {
        for (int i = 0; i < vector2.size(); ++i) {
            vector.addElement(vector2.elementAt(i));
        }
    }
    
    public void removeAll(final Vector vector, final Vector vector2) {
        for (int i = 0; i < vector2.size(); ++i) {
            for (int j = vector.size() - 1; j >= 0; --j) {
                if (vector.elementAt(j).equals(vector2.elementAt(i))) {
                    vector.removeElementAt(j);
                }
            }
        }
    }
}
