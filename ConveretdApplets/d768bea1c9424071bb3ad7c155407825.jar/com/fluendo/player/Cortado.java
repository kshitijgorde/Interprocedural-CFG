// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.net.URLConnection;
import com.fluendo.utils.Base64Converter;
import java.io.FileInputStream;
import java.awt.FontMetrics;
import java.util.Date;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.util.TimeZone;
import com.fluendo.utils.Debug;
import java.awt.Font;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Hashtable;
import java.awt.PopupMenu;
import java.io.InputStream;
import java.awt.image.ImageProducer;
import java.awt.Image;
import java.applet.Applet;

public class Cortado extends Applet implements ImageTarget, PreBufferNotify, Runnable
{
    private static Cortado cortado;
    private String urlString;
    private boolean local;
    private double framerate;
    private boolean audio;
    private boolean video;
    private boolean keepAspect;
    private int bufferSize;
    private String userId;
    private String password;
    private boolean usePrebuffer;
    private PreBuffer preBuffer;
    private int bufferLow;
    private int bufferHigh;
    private int debug;
    private double aspect;
    private Image image;
    private ImageProducer imageProd;
    private Thread videoThread;
    private Thread audioThread;
    private Thread mainThread;
    private Thread statusThread;
    private DataConsumer videoConsumer;
    private DataConsumer audioConsumer;
    private Demuxer demuxer;
    private InputStream ois;
    private Clock clock;
    private boolean havePreroll;
    private Status status;
    private PopupMenu menu;
    private boolean stopping;
    private Hashtable params;
    private Configure configure;
    private boolean useDb;
    private Dimension dbSize;
    private Image dbImage;
    private Graphics dbGraphics;
    private Dimension appletDimension;
    private long imageCounter;
    private boolean needRepaint;
    private long baseTime;
    private SimpleDateFormat sdf;
    private String chronoFormat;
    private String dateFormat;
    private InputStream sis;
    private String mime;
    private boolean loop;
    private int loopPause;
    private int loadingTime;
    private boolean logo;
    private Image logo_image;
    private String logo_filename;
    private int logo_width;
    private int logo_height;
    private String loadscreen_filename;
    private Image loadscreen_image;
    private int loadscreen_width;
    private boolean loadscreen;
    private boolean displayingLoadscreen;
    private boolean display_time;
    private Color time_color;
    private Color time_background;
    private int time_height;
    private int time_x;
    private int time_y;
    private Font time_font;
    private String comment;
    private String file_comment;
    private int comment_maxSize;
    private String last;
    
    public Cortado() {
        this.params = new Hashtable();
        this.useDb = true;
        this.imageCounter = 0L;
        this.chronoFormat = "HH:mm:ss";
        this.dateFormat = "dd/MM/yyyy HH:mm:ss";
        this.loopPause = 1000;
        this.loadingTime = 3000;
        this.logo_filename = "/images/logo.jpg";
        this.loadscreen_filename = "/images/ldscrn.jpg";
        this.loadscreen = true;
        this.displayingLoadscreen = true;
        this.display_time = true;
        this.time_color = Color.white;
        this.time_background = Color.black;
        this.time_height = 10;
        this.time_x = 2;
        this.time_y = 0;
        this.comment = "Viewsurf.com";
        this.comment_maxSize = 40;
    }
    
    public String getAppletInfo() {
        return "Title: Fluendo media player \nAuthor: Wim Taymans \nA Java based network multimedia player.";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "url", "URL", "The media file to play" }, { "local", "boolean", "Is this a local file (default false)" }, { "framerate", "float", "The default framerate of the video (default 5.0)" }, { "audio", "boolean", "Enable audio playback (default true)" }, { "video", "boolean", "Enable video playback (default true)" }, { "keepAspect", "boolean", "Use aspect ratio of video (default true)" }, { "preBuffer", "boolean", "Use Prebuffering (default = true)" }, { "doubleBuffer", "boolean", "Use double buffering for screen updates (default = true)" }, { "bufferSize", "int", "The size of the prebuffer in Kbytes (default 100)" }, { "bufferLow", "int", "Percent of empty buffer (default 10)" }, { "bufferHigh", "int", "Percent of full buffer (default 70)" }, { "userId", "string", "userId for basic authentication (default null)" }, { "password", "string", "password for basic authentication (default null)" }, { "debug", "int", "Debug level 0 - 4 (default = 3)" }, { "loopPause", "int", "Time in seconds before looping" }, { "loop", "boolean", "Need looping, is live" }, { "logo", "boolean", "Enable display of small logo in bottom-left corner" }, { "comment", "string", "File containing comment displayed on the top-left of the video" }, { "last", "URL", "File containing media file to play" } };
    }
    
    public void setParam(final String s, final String s2) {
        this.params.put(s, s2);
    }
    
    public void restart() {
        this.stop();
        this.init();
        this.realstart();
    }
    
    public String getParam(final String s, final String s2) {
        String parameter = this.params.get(s);
        if (parameter == null) {
            try {
                parameter = this.getParameter(s);
            }
            catch (Exception ex) {}
        }
        if (parameter == null) {
            parameter = s2;
        }
        return parameter;
    }
    
    public static void shutdown(final Throwable t) {
        Debug.log(3, "shutting down: reason: " + t.getMessage());
        t.printStackTrace();
        Cortado.cortado.stop();
    }
    
    public void init() {
        Cortado.cortado = this;
        (this.sdf = new SimpleDateFormat(this.chronoFormat)).setTimeZone(TimeZone.getTimeZone("GMT"));
        this.time_font = new Font("dialog", 0, this.time_height);
        this.image = null;
        this.aspect = 0.0;
        this.ois = null;
        this.clock = null;
        this.preBuffer = null;
        this.urlString = this.getParam("url", null);
        this.local = String.valueOf(this.getParam("local", "false")).equals("true");
        this.audio = String.valueOf(this.getParam("audio", "false")).equals("true");
        this.video = String.valueOf(this.getParam("video", "true")).equals("true");
        this.keepAspect = true;
        this.usePrebuffer = true;
        this.useDb = String.valueOf(this.getParam("doubleBuffer", "true")).equals("true");
        this.bufferSize = Integer.valueOf(this.getParam("bufferSize", "200"));
        this.bufferLow = Integer.valueOf(this.getParam("bufferLow", "10"));
        this.bufferHigh = Integer.valueOf(this.getParam("bufferHigh", "70"));
        this.debug = Integer.valueOf(this.getParam("debug", "3"));
        this.loop = String.valueOf(this.getParam("loop", "true")).equals("true");
        this.loopPause = Integer.valueOf(this.getParam("loopPause", "2")) * 1000;
        this.logo = !String.valueOf(this.getParam("logo", "true")).equals("false");
        this.file_comment = this.getParam("comment", "");
        this.last = this.getParam("last", "");
        this.userId = this.getParam("userId", null);
        this.password = this.getParam("password", null);
        this.configure = new Configure();
        Debug.level = this.debug;
        Debug.log(2, "build info: " + this.configure.buildInfo);
        this.needRepaint = true;
        this.appletDimension = this.getSize();
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        (this.status = new Status(this)).setVisible(false);
        this.status.setHaveAudio(this.audio);
        (this.menu = new PopupMenu()).add("About...");
        this.add(this.menu);
        try {
            this.loadscreen = true;
            this.loadscreen_image = null;
            this.loadscreen_image = this.getImage(this.loadscreen_filename);
            this.loadscreen_width = this.loadscreen_image.getWidth(this);
            this.prepareImage(this.loadscreen_image, this);
        }
        catch (Exception ex) {
            this.loadscreen_image = null;
            this.loadscreen = false;
            Debug.log(3, "loadscreen introuvable");
        }
        if (this.logo) {
            try {
                this.prepareImage(this.logo_image = this.getImage(this.logo_filename), this);
                this.logo_width = this.logo_image.getWidth(this);
                this.logo_height = this.logo_image.getHeight(this);
            }
            catch (Exception ex2) {
                this.logo_image = null;
                this.logo = false;
                Debug.log(3, "logo introuvable");
            }
        }
        else {
            Debug.log(2, "Pas de logo !!!");
        }
        if (this.file_comment != "") {
            try {
                this.comment = new BufferedReader(new InputStreamReader(new URL(this.file_comment).openStream())).readLine();
                if (this.comment.length() > this.comment_maxSize) {
                    this.comment = this.comment.substring(0, this.comment_maxSize - 1);
                }
            }
            catch (Exception ex3) {
                Debug.log(3, "Fichier commentaire manquant ou inexploitable");
            }
        }
        final String processLast = this.processLast();
        if (processLast != null) {
            this.urlString = processLast;
        }
    }
    
    private String processLast() {
        if (String.valueOf(this.last).equals("")) {
            return null;
        }
        String string = null;
        try {
            final URL url = new URL(this.last);
            string = url.getProtocol() + "://" + url.getHost() + url.getFile().substring(0, url.getFile().lastIndexOf("/")).concat("/" + new BufferedReader(new InputStreamReader(url.openStream())).readLine().concat(".ogg"));
            Debug.log(3, "fileUrl: " + string);
        }
        catch (Exception ex) {
            Debug.log(3, "Fichier last invalide");
        }
        return string;
    }
    
    public Component getComponent() {
        return this;
    }
    
    public synchronized void update(final Graphics graphics) {
        if (!this.needRepaint) {
            return;
        }
        if (this.useDb) {
            if (this.appletDimension == null) {
                this.appletDimension = this.getSize();
            }
            if (this.dbImage == null || this.dbSize.height != this.appletDimension.height || this.dbSize.width != this.appletDimension.width) {
                this.dbSize = this.appletDimension;
                this.dbImage = this.createImage(this.dbSize.width, this.dbSize.height);
                this.dbGraphics = this.dbImage.getGraphics();
            }
            this.dbGraphics.setColor(this.getBackground());
            this.dbGraphics.fillRect(0, 0, this.dbSize.width, this.dbSize.height);
            this.dbGraphics.setColor(Color.black);
            this.dbGraphics.setFont(this.getFont());
            this.paint(this.dbGraphics);
            graphics.drawImage(this.dbImage, 0, 0, null);
        }
        else {
            this.paint(graphics);
        }
    }
    
    public void run() {
        if (this.displayingLoadscreen) {
            try {
                this.forceRepaint();
                Thread.sleep(3000L);
            }
            catch (Exception ex) {}
            this.displayingLoadscreen = false;
            Debug.log(3, "now clock can play");
            this.clock.play();
        }
        try {
            this.realRun();
        }
        catch (Throwable t) {
            shutdown(t);
        }
    }
    
    private void realRun() {
        Debug.log(3, "entering status thread");
        while (!this.stopping) {
            try {
                if (this.preBuffer != null) {
                    final int bufferPercent = this.preBuffer.getFilled() * 100 / (1024 * this.bufferSize);
                    if (this.status.isVisible()) {
                        this.status.setBufferPercent(bufferPercent);
                        this.forceRepaint();
                    }
                    this.preBuffer.dumpStats();
                    QueueManager.dumpStats();
                }
                Thread.sleep(500L);
            }
            catch (Exception ex) {
                if (this.stopping) {
                    continue;
                }
                ex.printStackTrace();
            }
        }
        Debug.log(3, "exit status thread");
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this.appletDimension == null) {
            this.appletDimension = this.getSize();
        }
        final int width = this.appletDimension.width;
        final int height = this.appletDimension.height;
        int n = 0;
        final int n2 = 0;
        int n3 = width;
        if (this.displayingLoadscreen) {
            graphics.drawImage(this.loadscreen_image, n, n2, n3, height, null);
        }
        else {
            if (this.image != null) {
                int n4 = height;
                final int width2 = this.image.getWidth(this);
                final int height2 = this.image.getHeight(this);
                if (this.keepAspect) {
                    final double n5 = width2 / height2 * this.aspect;
                    n4 = (int)(n3 / n5);
                    if (n4 > height) {
                        n4 = height;
                        n3 = (int)(n4 * n5);
                    }
                }
                n = (width - n3) / 2;
                final int n6 = (height - n4) / 2;
                if (this.status.isVisible()) {
                    graphics.setClip(n, n6, n3, height - 12 - n6);
                    graphics.drawImage(this.image, n, n6, n3, n4, null);
                    graphics.setClip(0, 0, width, height);
                }
                else {
                    graphics.drawImage(this.image, n, n6, n3, n4, null);
                    graphics.setColor(Color.black);
                    graphics.fillRect(n, Math.max(n6 + n4, height - 12), n + n3, height);
                }
                if (this.logo) {
                    graphics.drawImage(this.logo_image, 2, this.appletDimension.height - this.logo_height - 2, this.logo_width, this.logo_height, null);
                }
                this.image.flush();
                if (this.display_time) {
                    graphics.setFont(this.time_font);
                    if (this.time_background != null) {
                        graphics.setColor(this.time_background);
                        graphics.fillRect(0, this.time_y, this.appletDimension.width, this.time_height + 2);
                    }
                    graphics.setColor(Color.white);
                    final FontMetrics fontMetrics = graphics.getFontMetrics();
                    graphics.drawString(this.comment, this.time_x, this.time_y + this.time_height);
                    final String format = this.sdf.format(new Date(this.baseTime));
                    graphics.drawString(format, this.appletDimension.width - this.time_x - fontMetrics.stringWidth(format), this.time_y + this.time_height);
                }
            }
            if (this.status != null && this.status.isVisible()) {
                this.status.setBounds(n, height - 12, n3, 12);
                this.status.paint(graphics);
            }
        }
        this.needRepaint = false;
    }
    
    private synchronized void forceRepaint() {
        this.needRepaint = true;
        this.repaint();
    }
    
    public synchronized void setImage(final Object o, final double n, final double n2) {
        if (o instanceof Image) {
            this.setImage((Image)o, n, n2);
        }
        else if (o instanceof ImageProducer) {
            this.setImage((ImageProducer)o, n, n2);
        }
    }
    
    public synchronized void setImage(final ImageProducer imageProd, final double n, final double n2) {
        if (!this.needRepaint) {
            if (this.imageProd != imageProd) {
                this.image = this.createImage(imageProd);
                this.imageProd = imageProd;
            }
            this.setImage(this.image, n, n2);
        }
    }
    
    public synchronized void setImage(final Image image, final double framerate, final double aspect) {
        if (!this.needRepaint) {
            ++this.imageCounter;
            this.image = image;
            this.framerate = framerate;
            this.aspect = aspect;
            if (!this.havePreroll) {
                this.getGraphics().clearRect(0, 0, this.appletDimension.width, this.appletDimension.height);
                this.status.setMessage("Buffering...");
            }
            this.forceRepaint();
        }
        else {
            ++this.imageCounter;
        }
    }
    
    public void preBufferNotify(final int n) {
        String message = null;
        synchronized (this.preBuffer) {
            if (!this.havePreroll && n != 1) {
                return;
            }
            switch (n) {
                case 1: {
                    message = "Buffering...";
                    this.clock.pause();
                    break;
                }
                case 2: {
                    message = "Playing...";
                    if (!this.displayingLoadscreen) {
                        this.clock.play();
                    }
                    this.status.setVisible(false);
                    break;
                }
                case 3: {
                    if (!this.displayingLoadscreen) {
                        this.clock.play();
                        break;
                    }
                    break;
                }
            }
        }
        if (message == null) {
            return;
        }
        this.status.setMessage(message);
        this.forceRepaint();
    }
    
    public void start() {
        this.displayingLoadscreen = true;
        this.realstart();
    }
    
    public void realstart() {
        this.stopping = false;
        Plugin plugin = null;
        int contentLength = 1048576;
        this.status.setMessage("Opening " + this.urlString + "...");
        try {
            try {
                if (this.local) {
                    Debug.log(3, "reading from file " + this.urlString);
                    this.ois = new FileInputStream(this.urlString);
                }
                else {
                    Debug.log(3, "reading from url " + this.urlString);
                    final URL url = new URL(this.urlString);
                    Debug.log(3, "trying to open " + url);
                    final URLConnection openConnection = url.openConnection();
                    if (this.userId != null && this.password != null) {
                        openConnection.setRequestProperty("Authorization", "Basic " + Base64Converter.encode((this.userId + ":" + this.password).getBytes()));
                    }
                    contentLength = openConnection.getContentLength();
                    this.mime = openConnection.getContentType();
                    if (this.mime == null) {
                        this.mime = "application/ogg";
                        Debug.log(3, "could not get mime type, using: " + this.mime);
                    }
                    else if (this.mime.equals("application/octet-stream")) {
                        this.mime = "application/ogg";
                        Debug.log(3, "mime type application/octet-stream, falling back to application/ogg");
                    }
                    final int index = this.mime.indexOf(59);
                    if (index != -1) {
                        this.mime = this.mime.substring(0, index);
                    }
                    Debug.log(3, "got stream mime: " + this.mime);
                    plugin = Plugin.makeByMime(this.mime);
                    if (plugin == null) {
                        this.status.setMessage("Unknown stream " + this.urlString + "...");
                        return;
                    }
                    this.ois = openConnection.getInputStream();
                    Debug.log(3, "opened " + url);
                }
            }
            catch (SecurityException ex) {
                ex.printStackTrace();
                this.status.setMessage("Not allowed " + this.urlString + "...");
                return;
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
                this.status.setMessage("Failed opening " + this.urlString + "...");
                return;
            }
            this.status.setMessage("Loading media...");
            this.clock = new Clock();
            if (this.video) {
                this.videoConsumer = new VideoConsumer(this.clock, this, this.framerate);
                this.videoThread = new Thread(this.videoConsumer, "videoConsummer");
            }
            if (this.audio) {
                try {
                    Class.forName("javax.sound.sampled.AudioSystem");
                    Debug.log(3, "using high quality javax.sound.* as audio backend");
                    this.audioConsumer = new AudioConsumer(this.clock);
                }
                catch (ClassNotFoundException ex4) {
                    Debug.log(3, "using low quality sun.audio.* as audio backend");
                    this.audioConsumer = new AudioConsumerSun(this.clock);
                }
                this.audioThread = new Thread(this.audioConsumer, "audioConsummer");
            }
            if (plugin == null) {
                plugin = Plugin.makeByMime("application/ogg");
            }
            this.sis = new StoreInputStream(this.ois, contentLength);
            InputStream inputStream;
            if (!this.loop) {
                if (this.usePrebuffer) {
                    this.preBuffer = new PreBuffer(this.sis, 1024 * this.bufferSize, this.bufferLow, this.bufferHigh, this);
                    inputStream = this.preBuffer;
                }
                else {
                    inputStream = this.sis;
                }
            }
            else {
                inputStream = this.sis;
            }
            this.demuxer = new Demuxer(inputStream, plugin, this, this.audioConsumer, this.videoConsumer);
            this.mainThread = new Thread(this.demuxer, "demuxer-mainThread");
            (this.statusThread = new Thread(this, "statusThread")).start();
            if (this.audio) {
                this.audioThread.start();
            }
            if (this.video) {
                this.videoThread.start();
            }
            try {
                synchronized (Thread.currentThread()) {
                    this.mainThread.start();
                }
                synchronized (this.clock) {
                    this.havePreroll = false;
                    Debug.log(3, "waiting for preroll...");
                    boolean b;
                    do {
                        b = true;
                        if (this.video) {
                            b &= this.videoConsumer.isReady();
                        }
                        if (this.audio) {
                            b &= this.audioConsumer.isReady();
                        }
                        if (!b) {
                            this.clock.wait(100L);
                        }
                    } while (!b);
                }
                this.havePreroll = true;
                long n = 0L;
                if (this.video) {
                    n = this.videoConsumer.getQueuedTime();
                    Debug.log(3, "video timeBase: " + n);
                }
                if (this.audio) {
                    n = this.audioConsumer.getQueuedTime();
                    Debug.log(3, "audio timeBase: " + n);
                }
                this.clock.updateAdjust(n);
                if (this.preBuffer != null) {
                    synchronized (this.preBuffer) {
                        Debug.log(3, "consumers ready");
                        Debug.log(3, "preroll done, starting prebuffer...");
                        this.status.setHavePercent(this.usePrebuffer);
                        this.preBuffer.startBuffer();
                        if (this.preBuffer.isFilled()) {
                            if (!this.displayingLoadscreen) {
                                this.clock.play();
                            }
                        }
                        else {
                            Debug.log(3, "not buffered, not starting yet " + this.preBuffer.getFilled());
                        }
                        return;
                    }
                }
                Debug.log(3, "consumers ready");
                Debug.log(3, "preroll done, starting...");
                this.status.setVisible(false);
                this.status.setMessage("Playing...");
                if (!this.displayingLoadscreen) {
                    this.clock.play();
                }
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
            this.status.setMessage("Failed opening " + this.urlString + "...");
            this.stop();
        }
    }
    
    private final void interruptThread(final Thread thread) {
        try {
            if (thread != null) {
                thread.interrupt();
            }
        }
        catch (Exception ex) {}
    }
    
    private final void joinThread(final Thread thread) {
        try {
            if (thread != null) {
                thread.join();
            }
        }
        catch (Exception ex) {}
    }
    
    public void stop() {
        if (this.demuxer != null) {
            this.demuxer.stop();
        }
        try {
            this.stopping = true;
            if (this.preBuffer != null) {
                this.preBuffer.stop();
            }
            if (this.video && this.videoConsumer != null) {
                this.videoConsumer.stop();
            }
            if (this.audio && this.audioConsumer != null) {
                this.audioConsumer.stop();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (this.video) {
            this.interruptThread(this.videoThread);
        }
        if (this.audio) {
            this.interruptThread(this.audioThread);
        }
        this.interruptThread(this.mainThread);
        this.interruptThread(this.statusThread);
        try {
            if (this.ois != null) {
                this.ois.close();
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        if (this.video) {
            this.joinThread(this.videoThread);
        }
        if (this.audio) {
            this.joinThread(this.audioThread);
        }
        this.joinThread(this.mainThread);
        this.joinThread(this.statusThread);
        QueueManager.reset();
    }
    
    public void setImageTime(final long baseTime) {
        this.baseTime = baseTime;
    }
    
    public static void setEOS() {
        if (Cortado.cortado.preBuffer != null) {
            Cortado.cortado.preBuffer.stop();
        }
        try {
            if (Cortado.cortado.ois != null) {
                Cortado.cortado.ois.close();
                Cortado.cortado.ois = null;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        Debug.log(3, "Cortado.setEOS done !");
        Cortado.cortado.preBuffer = null;
    }
    
    public void reset() {
        Debug.log(3, "Cortado RESET");
        try {
            if (this.video && this.videoConsumer != null) {
                this.videoConsumer.stop();
            }
            if (this.audio && this.audioConsumer != null) {
                this.audioConsumer.stop();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (this.video) {
            this.interruptThread(this.videoThread);
        }
        if (this.audio) {
            this.interruptThread(this.audioThread);
        }
        if (this.video) {
            this.joinThread(this.videoThread);
        }
        if (this.audio) {
            this.joinThread(this.audioThread);
        }
        Debug.log(3, "Cortado RESET done");
        if (!this.loop || this.stopping) {
            return;
        }
        Debug.log(3, "Cortado starting second pass");
        this.clock = new Clock();
        if (this.video) {
            this.videoConsumer = new VideoConsumer(this.clock, this, this.framerate);
            this.videoThread = new Thread(this.videoConsumer, "videoConsummer-reset");
        }
        if (this.audio) {
            try {
                Class.forName("javax.sound.sampled.AudioSystem");
                Debug.log(3, "using high quality javax.sound.* as audio backend");
                this.audioConsumer = new AudioConsumer(this.clock);
            }
            catch (ClassNotFoundException ex3) {
                Debug.log(3, "using low quality sun.audio.* as audio backend");
                this.audioConsumer = new AudioConsumerSun(this.clock);
            }
            this.audioThread = new Thread(this.audioConsumer, "audioConsumer-reset");
        }
        final int index = this.mime.indexOf(59);
        if (index != -1) {
            this.mime = this.mime.substring(0, index);
        }
        Debug.log(3, "got stream mime: " + this.mime);
        this.demuxer = new Demuxer(this.sis, Plugin.makeByMime(this.mime), this, this.audioConsumer, this.videoConsumer);
        this.mainThread = new Thread(this.demuxer, "demuxer-reset");
        if (this.audio) {
            this.audioThread.start();
        }
        if (this.video) {
            this.videoThread.start();
        }
        Debug.log(3, "Cortado second pass done");
        if (this.loopPause > 0) {
            try {
                Thread.sleep(this.loopPause);
            }
            catch (InterruptedException ex4) {}
        }
        try {
            synchronized (Thread.currentThread()) {
                this.mainThread.start();
            }
            synchronized (this.clock) {
                this.havePreroll = false;
                Debug.log(3, "waiting for preroll...");
                boolean b;
                do {
                    b = true;
                    if (this.video) {
                        b &= this.videoConsumer.isReady();
                    }
                    if (this.audio) {
                        b &= this.audioConsumer.isReady();
                    }
                    if (!b) {
                        this.clock.wait(100L);
                    }
                } while (!b);
                this.havePreroll = true;
            }
            long n = 0L;
            if (this.video) {
                n = this.videoConsumer.getQueuedTime();
                Debug.log(3, "video timeBase: " + n);
            }
            if (this.audio) {
                n = this.audioConsumer.getQueuedTime();
                Debug.log(3, "audio timeBase: " + n);
            }
            this.clock.updateAdjust(n);
            this.clock.play();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        Debug.log(3, "Cortado second pass done");
    }
    
    Image getImage(final String s) {
        Image image = null;
        try {
            final DataInputStream dataInputStream = new DataInputStream(this.getClass().getResourceAsStream(s));
            final byte[] array = new byte[dataInputStream.available()];
            dataInputStream.readFully(array);
            dataInputStream.close();
            image = Toolkit.getDefaultToolkit().createImage(array);
        }
        catch (Exception ex) {}
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex2) {}
        return image;
    }
    
    public synchronized void setHaveBasetime() {
        (this.sdf = new SimpleDateFormat(this.dateFormat)).setTimeZone(TimeZone.getTimeZone("GMT"));
    }
}
