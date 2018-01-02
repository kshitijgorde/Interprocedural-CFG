// 
// Decompiled by Procyon v0.5.30
// 

package Collapse;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.io.BufferedInputStream;
import java.io.InputStream;
import zylom.ZylomDataGather;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.util.Vector;
import java.io.IOException;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.Image;
import java.awt.Color;
import java.applet.AudioClip;
import sprite.GameCanvas;
import java.applet.Applet;

public class CollapseGame extends Applet implements Runnable
{
    private Thread C5;
    private boolean C6;
    private GameCanvas C7;
    private int C8;
    private int C9;
    private boolean CA;
    private boolean CB;
    private boolean CC;
    private String CE;
    private String CF;
    private int D0;
    private String D1;
    private int D2;
    protected boolean m_hasDownloadable;
    protected int m_numAdLevels;
    private ZylomAnim mzylomAnim;
    
    public CollapseGame() {
        System.out.println("MAIN TEST");
        this.C5 = null;
        this.C6 = true;
        this.C7 = null;
        this.C8 = 0;
        this.C9 = 0;
        this.CA = false;
        this.CB = false;
        this.CC = true;
        this.CE = null;
        this.CF = null;
        this.D0 = 5;
        this.D1 = null;
        this.D2 = 0;
        this.m_numAdLevels = 0;
    }
    
    private AudioClip C5(final String s, final boolean flag) {
        try {
            final AudioClip audioclip = this.getAudioClip(this.getCodeBase(), s);
            if (flag && this.C7 != null) {
                this.C7.playSound(audioclip);
            }
            return audioclip;
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }
    
    private final Color C6(String s, Color color) {
        s = this.getParameter(s);
        if (s != null) {
            color = new Color(Integer.parseInt(s, 16));
        }
        return color;
    }
    
    private Image C7(final String s) {
        try {
            final URL url = new URL(this.getCodeBase(), s);
            return Toolkit.getDefaultToolkit().getImage(url);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private void C8(final String s, final Vector vector, final MediaTracker mediatracker) {
        if (s.endsWith(".bin")) {
            this.CC(s, vector, mediatracker);
            return;
        }
        final Image image = this.C7(s);
        vector.addElement(image);
        if (mediatracker != null && image != null) {
            mediatracker.addImage(image, vector.size());
            if (this.prepareImage(image, this)) {
                ++this.C9;
            }
        }
    }
    
    private void C9() {
        this.removeAll();
        final Rectangle rectangle = this.bounds();
        this.setLayout(null);
        this.add(this.C7);
        this.C7.reshape(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    private void CA(final int i) {
        if (!this.CB) {
            int j = 20;
            if (this.C8 > 0) {
                j += this.C9 * 65 / this.C8;
            }
            if (j > 85) {
                j = 85;
            }
            this.C7.setPercent(j, i);
            return;
        }
        this.C7.setFatalMessage(ZylomDataGather.GetProp().getString("STRING_LABEL_ERRORCON", "Error connecting to server.\nYour session may have expired.\n\nPlease close your browser and log in again."));
    }
    
    private int CB(final InputStream inputstream) {
        try {
            int i = inputstream.read() << 24;
            i |= inputstream.read() << 16;
            i |= inputstream.read() << 8;
            i |= inputstream.read();
            return i;
        }
        catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    private void CC(final String s, final Vector vector, final MediaTracker mediatracker) {
        try {
            final Object obj = null;
            final URL url = new URL(this.getCodeBase(), s);
            final BufferedInputStream bufferedinputstream = new BufferedInputStream(url.openStream());
            int j = this.CB(bufferedinputstream);
            if (j < 0) {
                final String s2 = this.getCodeBase().getHost();
                if (s2 == null || s2.length() == 0) {
                    j = -j;
                }
            }
            this.C8 += j - 1;
            final int i = this.CB(bufferedinputstream);
            if (j < 1 || i > 2000000 || i < j * 4) {
                return;
            }
            int l = 8;
            final Toolkit toolkit = Toolkit.getDefaultToolkit();
            for (int j2 = 0; j2 < j; ++j2) {
                final int k = this.CB(bufferedinputstream);
                l += 4;
                if (k > i - l || this.C6) {
                    return;
                }
                if (k == 0) {
                    vector.addElement(null);
                }
                else {
                    final byte[] abyte0 = new byte[k];
                    int i2;
                    int k2;
                    for (i2 = 0; i2 < k; i2 += k2) {
                        k2 = bufferedinputstream.read(abyte0, i2, k - i2);
                        if (k2 == -1) {
                            break;
                        }
                    }
                    l += i2;
                    if (i2 == k) {
                        for (int l2 = 0; l2 < abyte0.length; ++l2) {
                            abyte0[l2] ^= 0x55;
                        }
                        final Image image = toolkit.createImage(abyte0);
                        vector.addElement(image);
                        if (mediatracker != null && image != null) {
                            mediatracker.addImage(image, vector.size());
                            if (this.prepareImage(image, this)) {
                                ++this.C9;
                            }
                        }
                    }
                }
            }
            bufferedinputstream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean action(final Event event, final Object obj) {
        if (obj instanceof String) {
            if (((String)obj).equals("buy")) {
                this.goDeluxe();
                return true;
            }
            if (((String)obj).startsWith("http")) {
                String s = (String)obj;
                String s2 = null;
                final int i = s.indexOf(32);
                if (i != -1) {
                    s2 = s.substring(i + 1);
                    s = s.substring(0, i);
                }
                try {
                    final URL url = new URL(s);
                    if (s2 == null) {
                        this.getAppletContext().showDocument(url);
                    }
                    else {
                        this.getAppletContext().showDocument(url, s2);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                catch (NullPointerException e2) {
                    e2.printStackTrace();
                }
            }
            else {
                if (!((String)obj).equals("refresh")) {
                    return super.action(event, obj);
                }
                this.refresh();
            }
            return true;
        }
        else {
            if (obj instanceof GameCanvas) {
                final GameCanvas gamecanvas = (GameCanvas)obj;
                this.goCanvas(gamecanvas);
                this.start();
                return true;
            }
            return super.action(event, obj);
        }
    }
    
    public void destroy() {
        ZylomDataGather.Stop();
        final GameCanvas gamecanvas = this.C7;
        if (gamecanvas != null && !gamecanvas.isStopped()) {
            gamecanvas.stop();
            this.C5 = null;
        }
        if (gamecanvas != null) {
            gamecanvas.destroy();
        }
        super.destroy();
    }
    
    public void doLayout() {
    }
    
    public String getAppletInfo() {
        return "Copyright (c) 1998-2001 GameHouse, Inc\r\n";
    }
    
    protected void goCanvas(final GameCanvas gamecanvas) {
        GameCanvas gamecanvas2 = this.C7;
        if (gamecanvas2 != null) {
            this.C6 = true;
            gamecanvas2.stop();
            gamecanvas2.destroy();
            gamecanvas2 = null;
            this.C5 = null;
        }
        (this.C7 = gamecanvas).setLicensed();
        if ((this.D2 & 0x4) != 0x0) {
            this.C7.setPrivateLabel();
        }
        this.C7.SetDollarMode(this.CC);
        if (this.D1 != null) {
            this.C7.setXtraCopy(this.D1);
        }
        this.C9();
    }
    
    public void goDeluxe() {
        final Object obj = null;
        try {
            final String s = this.getParameter("adUrl");
            if (s == null) {
                return;
            }
            String s2 = this.getParameter("target");
            if (s2 == null) {
                s2 = "_blank";
            }
            final URL url = new URL(s);
            this.getAppletContext().showDocument(url, s2);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e2) {
            e2.printStackTrace();
        }
    }
    
    public boolean imageUpdate(final Image image, final int i, final int j, final int k, final int l, final int i1) {
        if ((i & 0x20) == 0x20 || (i & 0x40) == 0x40) {
            ++this.C9;
            this.CA(i1);
        }
        else if ((i & 0x8) == 0x8) {
            this.CA(i1);
        }
        super.imageUpdate(image, i, j, k, l, i1);
        return true;
    }
    
    public void init() {
        System.out.println("INIT TEST");
        ZylomDataGather.Start(this);
        final String strCodeBase = null;
        final String strCanvas = null;
        String strDataFile = null;
        final String adUrl = null;
        final String adTarget = null;
        final boolean hasDownloadable = true;
        super.init();
        String s = null;
        String s2 = null;
        final String s3 = null;
        URL url = null;
        final int i = -1;
        s = this.getCodeBase().getHost();
        this.setBackground(Color.gray);
        this.setForeground(Color.white);
        try {
            url = this.getCodeBase();
            String s4 = this.getParameter("numAdLevels");
            if (s4 != null) {
                this.m_numAdLevels = Integer.parseInt(s4);
            }
            s4 = this.getParameter("hasDownloadable");
            if (s4 != null) {
                if (s4.compareTo("true") == 0) {
                    this.m_hasDownloadable = true;
                }
                else {
                    this.m_hasDownloadable = false;
                }
            }
            this.CE = this.getParameter("adUrl");
            final Color Zylomgrey = new Color(51, 51, 51);
            Color color = this.C6("BGCOLOR", Zylomgrey);
            Color color2 = this.C6("TEXTCOLOR", Color.white);
            if (color.equals(color2)) {
                color = Color.black;
                color2 = Color.white;
            }
            this.setBackground(color);
            this.setForeground(color2);
        }
        catch (NumberFormatException _ex) {
            System.out.println("Invalid params");
        }
        if (s == null || s.length() == 0) {
            this.D2 = 1;
        }
        if (this.D2 == 0) {
            s2 = null;
        }
        final CollapseCanvas gamecanvas = new CollapseCanvas();
        strDataFile = "properties/Collapse_" + this.getParameter("language") + "-" + this.getParameter("country") + ".dat";
        gamecanvas.InitCanvas(url, strDataFile, i);
        this.goCanvas(gamecanvas);
    }
    
    public void layout() {
    }
    
    public boolean mouseDown(final Event event, final int i, final int j) {
        if (this.CE != null) {
            try {
                final URL url = new URL(this.CE);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    
    public void paintAll(final Graphics g) {
        super.paintAll(g);
    }
    
    protected void refresh() {
        GameCanvas gamecanvas = this.C7;
        if (gamecanvas != null) {
            gamecanvas.stop();
            gamecanvas.destroy();
            gamecanvas = null;
            this.C5 = null;
        }
        this.init();
        this.start();
    }
    
    public void run() {
        System.out.println("RUN TEST");
        final GameCanvas gamecanvas = this.C7;
        if (gamecanvas == null) {
            this.C5 = null;
            return;
        }
        try {
            if (!gamecanvas.isLoaded()) {
                long l = 0L;
                final Font font = gamecanvas.getFont();
                gamecanvas.setMessage(ZylomDataGather.GetProp().getString("STRING_LABEL_LOADING", "Loading..."), 10);
                Image image = null;
                image = this.C7("images/cologo.jpg");
                gamecanvas.setLoadingImage(image);
                this.repaint();
                final MediaTracker mediatracker = new MediaTracker(this);
                if (image != null) {
                    mediatracker.addImage(image, 0);
                    try {
                        mediatracker.waitForAll();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    l = System.currentTimeMillis();
                }
                gamecanvas.setMessage(ZylomDataGather.GetProp().getString("STRING_LABEL_LOADING", "Loading Images..."), 20);
                final String[] as = gamecanvas.getImageList();
                final int c8 = (as != null) ? as.length : 0;
                this.C8 = c8;
                final int i = c8;
                this.C9 = 0;
                final Vector vector = new Vector(this.C8);
                for (int j = 0; j < i; ++j) {
                    if (this.C6) {
                        return;
                    }
                    if (as[j] != null && as[j].length() > 0) {
                        boolean flag = true;
                        String s1 = as[j];
                        if (s1.charAt(0) == '*') {
                            flag = false;
                            s1 = s1.substring(1);
                        }
                        else {
                            --this.C8;
                        }
                        s1 = "images/" + this.getParameter("language") + "-" + this.getParameter("country") + "/" + s1;
                        this.C8(s1, vector, flag ? mediatracker : null);
                    }
                    else {
                        vector.addElement(null);
                    }
                }
                this.C8 = vector.size();
                this.CA(0);
                gamecanvas.setMessage(ZylomDataGather.GetProp().getString("STRING_LABEL_CONNECTING", "Connecting..."), 0);
                if (!gamecanvas.initGame()) {
                    this.CB = true;
                    gamecanvas.setFatalMessage(ZylomDataGather.GetProp().getString("STRING_LABEL_ERRORINIT", "Error initializing game. Please reload."));
                    return;
                }
                gamecanvas.setMessage(ZylomDataGather.GetProp().getString("STRING_LABEL_LOADIMG", "Loading Images..."), 0);
                mediatracker.waitForAll();
                if (this.C6) {
                    return;
                }
                if (mediatracker.isErrorAny()) {
                    final Object[] aobj = mediatracker.getErrorsAny();
                    System.out.println("" + aobj.length + " image(s) failed to load");
                }
                gamecanvas.setMessage(ZylomDataGather.GetProp().getString("STRING_LABEL_LOADSND", "Loading Sounds..."), 0);
                final String s2 = System.getProperty("os.name");
                if (s2 != null && !s2.startsWith("Mac")) {
                    final AudioClip audioclip = this.C5("sounds/silent.au", false);
                    if (audioclip != null) {
                        gamecanvas.setSilentAudio(audioclip);
                    }
                }
                final String[] as2 = gamecanvas.getSoundList();
                AudioClip[] aaudioclip = null;
                if (as2 != null) {
                    aaudioclip = new AudioClip[as2.length];
                    for (int i2 = 0; i2 < as2.length; ++i2) {
                        boolean flag2 = true;
                        String s3 = as2[i2];
                        if (this.C6) {
                            return;
                        }
                        if (s3.length() != 0) {
                            if (s3.charAt(0) == '*') {
                                flag2 = false;
                                s3 = s3.substring(1);
                            }
                            aaudioclip[i2] = this.C5(s3, flag2);
                            gamecanvas.setPercent(85 + i2 * 15 / as2.length, 100);
                        }
                    }
                }
                final Image[] aimage = new Image[vector.size()];
                for (int k = 0; k < aimage.length; ++k) {
                    if (this.C6) {
                        return;
                    }
                    aimage[k] = vector.elementAt(k);
                    if (aimage[k] != null) {
                        this.prepareImage(aimage[k], null);
                    }
                }
                gamecanvas.setMessage(ZylomDataGather.GetProp().getString("STRING_LABEL_INIT", "Initializing..."), 100);
                gamecanvas.setImages(aimage, aaudioclip);
                if (image != null) {
                    long l2 = System.currentTimeMillis() - l;
                    l2 = this.D0 * 1000 - l2;
                    if (l2 > 0L) {
                        Thread.sleep(l2);
                    }
                }
            }
        }
        catch (InterruptedException e2) {
            gamecanvas.setMessage(ZylomDataGather.GetProp().getString("STRING_LABEL_ABORTED", "Aborted loading"), 0);
            return;
        }
        catch (Throwable throwable) {
            gamecanvas.setFatalMessage(ZylomDataGather.GetProp().getString("STRING_LABEL_UNEXPECTED", "An unexpected error occured while loading game.\n"));
            System.out.println(throwable.toString());
            throwable.printStackTrace();
            return;
        }
        if (this.C6) {
            return;
        }
        System.out.println("Zylom Done loading");
        ZylomDataGather.GetHelper().indicateFinishedLoading();
        this.repaint();
        gamecanvas.normalCursor();
        gamecanvas.start();
    }
    
    public void soundEnable(final boolean flag) {
        final GameCanvas gamecanvas = this.C7;
        if (gamecanvas != null) {
            gamecanvas.enableSound(flag);
        }
    }
    
    public void start() {
        System.out.println("START TEST");
        if (this.C6) {
            if (this.C5 != null) {
                try {
                    this.C5.join();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.C5 = null;
            }
            this.C6 = false;
            (this.C5 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        final GameCanvas gamecanvas = this.C7;
        if (gamecanvas != null) {
            gamecanvas.stop();
        }
        this.C6 = true;
        super.stop();
    }
    
    public void validate() {
    }
}
