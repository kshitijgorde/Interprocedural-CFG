// 
// Decompiled by Procyon v0.5.30
// 

package com.charliemouse.cambozola.shared;

import java.net.URLConnection;
import java.util.Hashtable;
import java.net.SocketException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.net.Socket;
import java.util.Enumeration;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.Image;
import java.util.Vector;

public class CamStream extends Thread
{
    public static final int CONNECT_STYLE_SOCKET = 1;
    public static final int CONNECT_STYLE_HTTP = 2;
    private static final int IMG_FLUFF_FACTOR = 1;
    private static int m_connectionMethod;
    private ExceptionReporter m_reporter;
    private Vector m_listeners;
    private Object m_lock;
    private Image m_imain;
    private Toolkit m_tk;
    private URL m_stream;
    private URL m_docBase;
    private DataInputStream m_inputStream;
    private DataOutputStream m_outputStream;
    private boolean m_isDefunct;
    private boolean m_collecting;
    private byte[] m_rawImage;
    private String m_imageType;
    private long m_startTime;
    private int m_imgidx;
    private int m_retryCount;
    private int m_retryDelay;
    private String m_appName;
    
    public CamStream(final URL stream, final String appName, final URL docBase, final int retryCount, final int retryDelay, final ExceptionReporter reporter) {
        this.m_reporter = null;
        this.m_lock = new Object();
        this.m_imain = null;
        this.m_inputStream = null;
        this.m_outputStream = null;
        this.m_isDefunct = false;
        this.m_collecting = false;
        this.m_imageType = "image/jpeg";
        this.m_startTime = 0L;
        this.m_imgidx = 0;
        this.m_retryCount = 1;
        this.m_retryDelay = 1000;
        this.m_appName = "";
        this.m_tk = Toolkit.getDefaultToolkit();
        this.m_listeners = new Vector();
        this.m_stream = stream;
        this.m_appName = appName;
        this.m_reporter = reporter;
        this.m_isDefunct = false;
        this.m_docBase = docBase;
        this.m_retryCount = retryCount;
        this.m_retryDelay = retryDelay;
    }
    
    public static void setConnectionMethod(final int connectionMethod) {
        CamStream.m_connectionMethod = connectionMethod;
    }
    
    public Image getCurrent() {
        synchronized (this.m_lock) {
            return this.m_imain;
        }
    }
    
    public byte[] getRawImage() {
        synchronized (this.m_lock) {
            return this.m_rawImage;
        }
    }
    
    public int getIndex() {
        synchronized (this.m_lock) {
            return this.m_imgidx;
        }
    }
    
    public String getType() {
        synchronized (this.m_lock) {
            return this.m_imageType;
        }
    }
    
    public URL getStreamURL() {
        return this.m_stream;
    }
    
    public double getFPS() {
        if (this.m_startTime == 0L) {
            return 0.0;
        }
        return 1000.0 * (this.m_imgidx - 1) / (System.currentTimeMillis() - this.m_startTime);
    }
    
    public void addImageChangeListener(final ImageChangeListener imageChangeListener) {
        this.m_listeners.addElement(imageChangeListener);
    }
    
    public void removeImageChangeListener(final ImageChangeListener imageChangeListener) {
        this.m_listeners.removeElement(imageChangeListener);
    }
    
    private void fireImageChange() {
        final ImageChangeEvent imageChangeEvent = new ImageChangeEvent(this);
        final Enumeration<ImageChangeListener> elements = (Enumeration<ImageChangeListener>)this.m_listeners.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().imageChanged(imageChangeEvent);
        }
    }
    
    public void run() {
        try {
            int i = 0;
            final int retryCount = this.m_retryCount;
            final int retryDelay = this.m_retryDelay;
            StreamSplit streamSplit;
            String s;
            String substring;
            do {
                ++i;
                Hashtable hashtable;
                if (CamStream.m_connectionMethod == 1) {
                    int port = this.m_stream.getPort();
                    if (port == -1) {
                        port = 80;
                    }
                    final Socket socket = new Socket(this.m_stream.getHost(), port);
                    final DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    this.m_inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    final StringBuffer sb = new StringBuffer();
                    sb.append("GET " + this.m_stream.getFile() + " HTTP/1.0\r\n");
                    if (this.m_docBase != null) {
                        sb.append("Referer: " + this.m_docBase + "\r\n");
                    }
                    sb.append("User-Agent: " + this.m_appName + "\r\n");
                    sb.append("Host: " + this.m_stream.getHost() + "\r\n");
                    sb.append("\r\n");
                    dataOutputStream.writeBytes(sb.toString());
                    streamSplit = new StreamSplit(this.m_inputStream);
                    hashtable = streamSplit.readHeaders();
                }
                else {
                    final URLConnection openConnection = this.m_stream.openConnection();
                    if (this.m_docBase != null) {
                        openConnection.setRequestProperty("Referer", this.m_docBase.toString());
                    }
                    openConnection.setRequestProperty("User-Agent", this.m_appName);
                    openConnection.setRequestProperty("Host", this.m_stream.getHost());
                    this.m_inputStream = new DataInputStream(new BufferedInputStream(openConnection.getInputStream()));
                    streamSplit = new StreamSplit(this.m_inputStream);
                    hashtable = streamSplit.readHeaders(openConnection);
                }
                this.m_collecting = true;
                s = null;
                substring = hashtable.get("content-type");
                if (substring == null) {
                    s = "No main content type";
                }
                else if (substring.indexOf("text") != -1) {
                    String line;
                    while ((line = this.m_inputStream.readLine()) != null) {
                        System.out.println(line);
                    }
                    s = "Failed to connect to server (denied?)";
                }
                if (s == null) {
                    break;
                }
                if (this.m_isDefunct) {
                    return;
                }
                this.m_reporter.reportFailure(s);
                Thread.sleep(retryDelay);
            } while (i < retryCount);
            if (s != null) {
                return;
            }
            final int index = substring.indexOf("boundary=");
            String s2 = null;
            if (index != -1) {
                s2 = substring.substring(index + 9);
                substring = substring.substring(0, index);
                if (!s2.startsWith("--")) {
                    s2 = "--" + s2;
                }
            }
            if (substring.startsWith("multipart/x-mixed-replace")) {
                streamSplit.skipToBoundary(s2);
            }
            do {
                if (this.m_collecting) {
                    if (s2 != null) {
                        final Hashtable headers = streamSplit.readHeaders();
                        if (streamSplit.isAtStreamEnd()) {
                            break;
                        }
                        substring = headers.get("content-type");
                        if (substring == null) {
                            throw new Exception("No part content type");
                        }
                    }
                    if (substring.startsWith("multipart/x-mixed-replace")) {
                        s2 = substring.substring(substring.indexOf("boundary=") + 9);
                        streamSplit.skipToBoundary(s2);
                    }
                    else {
                        final byte[] toBoundary = streamSplit.readToBoundary(s2);
                        if (toBoundary.length == 0) {
                            break;
                        }
                        if (this.m_imgidx > 1 && this.m_startTime == 0L) {
                            this.m_startTime = System.currentTimeMillis();
                        }
                        this.updateImage(substring, toBoundary);
                    }
                }
                try {
                    Thread.sleep(20L);
                }
                catch (InterruptedException ex3) {}
            } while (!this.m_isDefunct);
        }
        catch (SocketException ex) {
            if (!this.m_isDefunct) {
                this.m_reporter.reportError(ex);
            }
        }
        catch (Exception ex2) {
            this.m_reporter.reportError(ex2);
        }
        finally {
            this.unhook();
        }
    }
    
    private void updateImage(final String imageType, final byte[] rawImage) {
        synchronized (this.m_lock) {
            this.m_imageType = imageType;
            this.m_imain = this.m_tk.createImage(rawImage);
            this.m_rawImage = rawImage;
            ++this.m_imgidx;
        }
        this.fireImageChange();
    }
    
    public void finalize() {
        this.unhook();
    }
    
    public void unhook() {
        this.m_collecting = false;
        this.m_isDefunct = true;
        try {
            if (this.m_inputStream != null) {
                this.m_inputStream.close();
            }
            this.m_inputStream = null;
            if (this.m_outputStream != null) {
                this.m_outputStream.close();
            }
            this.m_outputStream = null;
        }
        catch (Exception ex) {}
    }
    
    static {
        CamStream.m_connectionMethod = 2;
    }
}
