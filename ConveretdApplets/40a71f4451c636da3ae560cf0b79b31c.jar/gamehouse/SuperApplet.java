// 
// Decompiled by Procyon v0.5.30
// 

package gamehouse;

import java.io.BufferedInputStream;
import java.io.InputStream;
import inknet.InkUtil;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.util.Vector;
import java.awt.Toolkit;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.awt.Color;
import java.applet.AudioClip;
import inknet.User;
import sprite.GameCanvas;
import java.applet.Applet;

public class SuperApplet extends Applet implements Runnable
{
    private Thread \u00c0;
    private boolean \u00c1;
    private GameCanvas \u00c2;
    private int \u00dd;
    private int \u00de;
    private boolean \u00c3;
    private boolean \u00c4;
    private boolean \u00c5;
    private User \u00c6;
    private String \u00c7;
    private String \u00c8;
    private int \u00c9;
    private String \u00ca;
    private int \u00cb;
    
    public boolean betsActive() {
        return this.\u00c2.betsActive();
    }
    
    public void stop() {
        final GameCanvas \u00e2 = this.\u00c2;
        if (\u00e2 != null) {
            \u00e2.stop();
        }
        this.\u00c1 = true;
        super.stop();
        System.out.println("Stop ret");
    }
    
    public User getUserObject() {
        return this.\u00c6;
    }
    
    private AudioClip \u00dd(final String s, final boolean b) {
        try {
            final AudioClip audioClip = this.getAudioClip(this.getCodeBase(), s);
            if (b && this.\u00c2 != null) {
                this.\u00c2.playSound(audioClip);
            }
            return audioClip;
        }
        catch (NullPointerException ex2) {}
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
    
    public SuperApplet() {
        this.\u00c0 = null;
        this.\u00c1 = true;
        this.\u00c2 = null;
        this.\u00dd = 0;
        this.\u00de = 0;
        this.\u00c3 = false;
        this.\u00c4 = false;
        this.\u00c5 = true;
        this.\u00c6 = null;
        this.\u00c7 = null;
        this.\u00c8 = null;
        this.\u00c9 = 5;
        this.\u00ca = null;
        this.\u00cb = 0;
    }
    
    private final Color \u00c0(String parameter, Color color) {
        parameter = this.getParameter(parameter);
        if (parameter != null) {
            color = new Color(Integer.parseInt(parameter, 16));
        }
        return color;
    }
    
    public void destroy() {
        final GameCanvas \u00e2 = this.\u00c2;
        System.out.println("destroy v2.4");
        if (\u00e2 != null && !\u00e2.isStopped()) {
            \u00e2.stop();
            this.\u00c0 = null;
        }
        if (\u00e2 != null) {
            \u00e2.destroy();
        }
        if (this.\u00c6 != null) {
            this.\u00c6.endGame();
        }
        super.destroy();
    }
    
    protected void goCanvas(final GameCanvas \u00e2) {
        final GameCanvas \u00e22 = this.\u00c2;
        if (\u00e22 != null) {
            this.\u00c1 = true;
            \u00e22.stop();
            \u00e22.destroy();
            this.\u00c0 = null;
        }
        (this.\u00c2 = \u00e2).setLicensed();
        if ((this.\u00cb & 0x4) != 0x0) {
            this.\u00c2.setPrivateLabel();
        }
        this.\u00c2.SetDollarMode(this.\u00c5);
        if (this.\u00ca != null) {
            this.\u00c2.setXtraCopy(this.\u00ca);
        }
        this.\u00c1();
    }
    
    protected void goCasino() {
        try {
            String s = this.getParameter("BUYURL");
            if (s == null) {
                s = this.getCasinoLink();
            }
            if (s == null) {
                s = this.getParameter("CASINO");
            }
            if (s == null) {
                return;
            }
            String parameter = this.getParameter("TARGET");
            if (parameter == null) {
                parameter = "_self";
            }
            this.getAppletContext().showDocument(new URL(this.getDocumentBase(), s), parameter);
        }
        catch (IOException ex) {}
        catch (NullPointerException ex2) {}
    }
    
    private Image \u00de(final String s) {
        try {
            return Toolkit.getDefaultToolkit().getImage(new URL(this.getCodeBase(), s));
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) == 0x20 || (n & 0x40) == 0x40) {
            ++this.\u00de;
            this.\u00e0(n5);
        }
        else if ((n & 0x8) == 0x8) {
            this.\u00e0(n5);
        }
        super.imageUpdate(image, n, n2, n3, n4, n5);
        return true;
    }
    
    public void start() {
        if (this.\u00c1) {
            if (this.\u00c0 != null) {
                try {
                    this.\u00c0.join();
                }
                catch (InterruptedException ex) {}
                this.\u00c0 = null;
            }
            this.\u00c1 = false;
            (this.\u00c0 = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "Copyright (c) 1998-2001 GameHouse, Inc\r\n";
    }
    
    private void \u00df(final String s, final Vector vector, final MediaTracker mediaTracker) {
        if (s.endsWith(".bin")) {
            this.\u00e2(s, vector, mediaTracker);
            return;
        }
        final Image \u00fe = this.\u00de(s);
        vector.addElement(\u00fe);
        if (mediaTracker != null && \u00fe != null) {
            mediaTracker.addImage(\u00fe, vector.size());
            if (this.prepareImage(\u00fe, this)) {
                ++this.\u00de;
            }
        }
    }
    
    private void \u00c1() {
        this.removeAll();
        final Rectangle bounds = this.bounds();
        this.setLayout(null);
        this.add(this.\u00c2);
        this.\u00c2.reshape(bounds.x, bounds.y, bounds.width, bounds.height);
    }
    
    public boolean action(final Event event, final Object o) {
        if (o instanceof String) {
            if (((String)o).equals("casino")) {
                this.goCasino();
                return true;
            }
            if (((String)o).startsWith("http")) {
                String substring = (String)o;
                String substring2 = null;
                final int index = substring.indexOf(32);
                if (index != -1) {
                    substring2 = substring.substring(index + 1);
                    substring = substring.substring(0, index);
                }
                try {
                    final URL url = new URL(substring);
                    if (substring2 == null) {
                        this.getAppletContext().showDocument(url);
                    }
                    else {
                        this.getAppletContext().showDocument(url, substring2);
                    }
                }
                catch (IOException ex) {}
                catch (NullPointerException ex2) {}
            }
            else {
                if (!((String)o).equals("refresh")) {
                    return super.action(event, o);
                }
                this.refresh();
            }
            return true;
        }
        else {
            if (o instanceof GameCanvas) {
                this.goCanvas((GameCanvas)o);
                this.start();
                return true;
            }
            return super.action(event, o);
        }
    }
    
    private void \u00e0(final int n) {
        if (!this.\u00c4) {
            int n2 = 20;
            if (this.\u00dd > 0) {
                n2 += this.\u00de * 65 / this.\u00dd;
            }
            if (n2 > 85) {
                n2 = 85;
            }
            this.\u00c2.setPercent(n2, n);
            return;
        }
        this.\u00c2.setFatalMessage("Error connecting to server.\nYour session may have expired.\n\nPlease close your browser and log in again.");
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.\u00c7 != null) {
            try {
                this.getAppletContext().showDocument(new URL(this.\u00c7), "_blank");
            }
            catch (IOException ex) {}
        }
        return true;
    }
    
    protected void refresh() {
        final GameCanvas \u00e2 = this.\u00c2;
        if (\u00e2 != null) {
            \u00e2.stop();
            \u00e2.destroy();
            this.\u00c0 = null;
        }
        this.init();
        this.start();
    }
    
    public void run() {
        final GameCanvas \u00e2 = this.\u00c2;
        if (\u00e2 == null) {
            this.\u00c0 = null;
            return;
        }
        try {
            if (!\u00e2.isLoaded()) {
                long currentTimeMillis = 0L;
                \u00e2.getFont();
                \u00e2.setMessage("Loading...", 10);
                Image \u00fe = null;
                if (this.\u00c8 != null) {
                    \u00fe = this.\u00de(this.\u00c8);
                    \u00e2.setLoadingImage(\u00fe);
                    if (this.\u00c7 != null) {
                        \u00e2.handCursor();
                    }
                }
                this.repaint();
                final MediaTracker mediaTracker = new MediaTracker(this);
                if (\u00fe != null) {
                    mediaTracker.addImage(\u00fe, 0);
                    try {
                        mediaTracker.waitForAll();
                    }
                    catch (InterruptedException ex) {}
                    currentTimeMillis = System.currentTimeMillis();
                }
                \u00e2.setMessage("Loading Images...", 20);
                final String[] imageList = \u00e2.getImageList();
                final int \u00fd = (imageList == null) ? 0 : imageList.length;
                this.\u00dd = \u00fd;
                final int n = \u00fd;
                this.\u00de = 0;
                final Vector<Image> vector = new Vector<Image>(this.\u00dd);
                for (int i = 0; i < n; ++i) {
                    if (this.\u00c1) {
                        return;
                    }
                    if (imageList[i] != null && imageList[i].length() > 0) {
                        boolean b = true;
                        String substring = imageList[i];
                        if (substring.charAt(0) == '*') {
                            b = false;
                            substring = substring.substring(1);
                        }
                        else {
                            --this.\u00dd;
                        }
                        this.\u00df(substring, vector, b ? mediaTracker : null);
                    }
                    else {
                        vector.addElement(null);
                    }
                }
                this.\u00dd = vector.size();
                this.\u00e0(0);
                \u00e2.setMessage("Connecting...", 0);
                if (!\u00e2.initGame()) {
                    this.\u00c4 = true;
                    \u00e2.setFatalMessage("Error initializing game. Please reload.");
                    return;
                }
                \u00e2.setMessage("Loading Images...", 0);
                mediaTracker.waitForAll();
                if (this.\u00c1) {
                    return;
                }
                if (mediaTracker.isErrorAny()) {
                    System.out.println("" + mediaTracker.getErrorsAny().length + " image(s) failed to load");
                }
                \u00e2.setMessage("Loading Sounds...", 0);
                final String property = System.getProperty("os.name");
                if (property != null && !property.startsWith("Mac")) {
                    final AudioClip \u00fd2 = this.\u00dd("sounds/silent.au", false);
                    if (\u00fd2 != null) {
                        System.out.println("looping silent audio");
                        \u00e2.setSilentAudio(\u00fd2);
                    }
                }
                final String[] soundList = \u00e2.getSoundList();
                Object[] array = null;
                if (soundList != null) {
                    array = new AudioClip[soundList.length];
                    for (int j = 0; j < soundList.length; ++j) {
                        boolean b2 = true;
                        String substring2 = soundList[j];
                        if (this.\u00c1) {
                            return;
                        }
                        if (substring2.length() != 0) {
                            if (substring2.charAt(0) == '*') {
                                b2 = false;
                                substring2 = substring2.substring(1);
                            }
                            array[j] = this.\u00dd(substring2, b2);
                            \u00e2.setPercent(85 + j * 15 / soundList.length, 100);
                        }
                    }
                }
                final Image[] array2 = new Image[vector.size()];
                for (int k = 0; k < array2.length; ++k) {
                    if (this.\u00c1) {
                        return;
                    }
                    array2[k] = vector.elementAt(k);
                    if (array2[k] != null) {
                        this.prepareImage(array2[k], null);
                    }
                }
                \u00e2.setMessage("Initializing...", 100);
                \u00e2.setImages(array2, array);
                if (\u00fe != null) {
                    final long n2 = this.\u00c9 * 1000 - (System.currentTimeMillis() - currentTimeMillis);
                    if (n2 > 0L) {
                        Thread.sleep(n2);
                    }
                }
            }
        }
        catch (InterruptedException ex2) {
            \u00e2.setMessage("Aborted loading", 0);
            return;
        }
        catch (Throwable t) {
            \u00e2.setFatalMessage("An unexpected error occured while loading game.\n");
            System.out.println(t.toString());
            t.printStackTrace();
        }
        if (this.\u00c1) {
            return;
        }
        this.repaint();
        \u00e2.normalCursor();
        \u00e2.start();
    }
    
    public void init() {
        String s = null;
        String parameter = null;
        URL codeBase = null;
        int int1 = -1;
        final String host = this.getCodeBase().getHost();
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        System.out.println("Init 2.4");
        try {
            codeBase = this.getCodeBase();
            final String parameter2 = this.getParameter("GAMEID");
            if (parameter2 != null) {
                int1 = Integer.parseInt(parameter2);
            }
            parameter = this.getParameter("DATAFILE");
            final String parameter3;
            if ((parameter3 = this.getParameter("CANVAS")) != null && (this.\u00cb = InkUtil.getGameCanvasType(this, parameter3)) != 0) {
                s = parameter3;
            }
            final String parameter4 = this.getParameter("POINTMODE");
            if (parameter4 != null) {
                this.\u00c5 = (!parameter4.equalsIgnoreCase("true") && !parameter4.equalsIgnoreCase("yes") && !parameter4.equalsIgnoreCase("1"));
            }
            this.\u00ca = this.getParameter("XTRACOPY");
            this.\u00c7 = this.getParameter("ADURL");
            this.\u00c8 = this.getParameter("ADIMAGE");
            final String parameter5 = this.getParameter("ADSECS");
            if (parameter5 != null) {
                this.\u00c9 = Integer.parseInt(parameter5);
            }
            Color background = this.\u00c0("BGCOLOR", Color.black);
            Color foreground = this.\u00c0("TEXTCOLOR", Color.white);
            if (background.equals(foreground)) {
                background = Color.black;
                foreground = Color.white;
            }
            this.setBackground(background);
            this.setForeground(foreground);
        }
        catch (NumberFormatException ex4) {
            System.out.println("Invalid params");
        }
        if (host == null || host.length() == 0) {
            this.\u00cb = 1;
        }
        this.\u00c6 = InkUtil.getUser(this, host, this.\u00cb);
        if (this.\u00cb == 0) {
            s = null;
        }
        if (s == null) {
            System.out.println("Please Contact GameHouse, Inc. for configuration instructions.");
            return;
        }
        try {
            final GameCanvas gameCanvas = (GameCanvas)Class.forName(s).newInstance();
            gameCanvas.InitCanvas(codeBase, this.\u00c6, parameter, int1);
            this.goCanvas(gameCanvas);
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            this.postEvent(new Event(this, 1001, "casino"));
        }
        catch (InstantiationException ex2) {
            ex2.printStackTrace();
            this.postEvent(new Event(this, 1001, "casino"));
        }
        catch (IllegalAccessException ex3) {
            ex3.printStackTrace();
            this.postEvent(new Event(this, 1001, "casino"));
        }
    }
    
    private int \u00e1(final InputStream inputStream) {
        return inputStream.read() << 24 | inputStream.read() << 16 | inputStream.read() << 8 | inputStream.read();
    }
    
    private void \u00e2(final String s, final Vector vector, final MediaTracker mediaTracker) {
        try {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(this.getCodeBase(), s).openStream());
            int \u00e1 = this.\u00e1(bufferedInputStream);
            if (\u00e1 < 0) {
                final String host = this.getCodeBase().getHost();
                if (host == null || host.length() == 0) {
                    \u00e1 = -\u00e1;
                }
            }
            this.\u00dd += \u00e1 - 1;
            final int \u00e12 = this.\u00e1(bufferedInputStream);
            if (\u00e1 < 1 || \u00e12 > 2000000 || \u00e12 < \u00e1 * 4) {
                return;
            }
            int n = 8;
            final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
            for (int i = 0; i < \u00e1; ++i) {
                final int \u00e13 = this.\u00e1(bufferedInputStream);
                n += 4;
                if (\u00e13 > \u00e12 - n || this.\u00c1) {
                    return;
                }
                if (\u00e13 == 0) {
                    vector.addElement(null);
                }
                else {
                    final byte[] array = new byte[\u00e13];
                    int j;
                    int read;
                    for (j = 0; j < \u00e13; j += read) {
                        read = bufferedInputStream.read(array, j, \u00e13 - j);
                        if (read == -1) {
                            break;
                        }
                    }
                    n += j;
                    if (j == \u00e13) {
                        for (int k = 0; k < array.length; ++k) {
                            array[k] ^= 0x55;
                        }
                        final Image image = defaultToolkit.createImage(array);
                        vector.addElement(image);
                        if (mediaTracker != null && image != null) {
                            mediaTracker.addImage(image, vector.size());
                            if (this.prepareImage(image, this)) {
                                ++this.\u00de;
                            }
                        }
                    }
                }
            }
            bufferedInputStream.close();
        }
        catch (IOException ex) {
            System.out.println("ld compound:" + ex.toString());
        }
    }
    
    protected final String getCasinoLink() {
        if ((this.\u00cb & 0x2) == 0x0) {
            return "http://www.gamehouse.com";
        }
        return null;
    }
    
    public void soundEnable(final boolean b) {
        final GameCanvas \u00e2 = this.\u00c2;
        if (\u00e2 != null) {
            \u00e2.enableSound(b);
        }
    }
}
