import java.awt.Container;
import java.net.URL;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;
import java.applet.AudioClip;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Event;
import java.awt.Image;
import java.awt.Frame;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class uaplayer extends Applet implements Runnable
{
    private Frame $214;
    private boolean $208;
    private Image $215;
    private int $298;
    private Event $199;
    private boolean $200;
    private int $307;
    private int $266;
    private int $296;
    private Rectangle $323;
    private Rectangle $315;
    private Thread $336;
    private uasbase[] $92;
    private int $264;
    private String $325;
    private boolean $206;
    private final String $17 = "Trial version";
    private final String $1 = "Ulead Animation.Applet (c)1999, wwww.webutilities.com";
    private boolean $212;
    private boolean $209;
    private String $324;
    private uasbase $76;
    private Rectangle[] $144;
    private Rectangle[] $143;
    private int $269;
    private int[] $129;
    private int[] $135;
    private int[] $131;
    private int[] $111;
    private boolean $213;
    private int $308;
    
    private boolean $353() {
        try {
            Integer.valueOf("");
            return true;
        }
        catch (NumberFormatException ex) {}
        catch (Exception ex2) {}
        return false;
    }
    
    public String getAppletInfo() {
        return "Ulead Animation.Applet (c)1999, wwww.webutilities.com";
    }
    
    public void start() {
        if (this.$336 == null) {
            (this.$336 = new Thread(this)).start();
        }
    }
    
    public void destroy() {
        for (int i = 0; i < this.$298; ++i) {
            if (this.$92[i] != null) {
                this.$92[i].stopAudio();
                this.$92[i].DeleteSprite();
                this.$92[i] = null;
            }
        }
        System.gc();
    }
    
    public void stop() {
        if (this.$336 != null) {
            for (int i = 0; i < this.$298; ++i) {
                if (this.$92[i] != null) {
                    this.$92[i].stopAudio();
                }
            }
            this.$336.stop();
            this.$336 = null;
        }
        System.gc();
    }
    
    public void paint(final Graphics graphics) {
        if (this.$215 != null) {
            graphics.drawImage(this.$215, 0, 0, this);
            if (this.$212) {
                graphics.drawString("Trial version", 10, 10);
                graphics.drawString("Ulead Animation.Applet (c)1999, wwww.webutilities.com", 10, 30);
            }
        }
    }
    
    private synchronized void $364(final boolean b) {
        if (b) {
            this.$213 = true;
            return;
        }
        this.$40();
        this.$213 = false;
    }
    
    public void update(final Graphics graphics) {
        this.$364(false);
        this.paint(graphics);
    }
    
    public void run() {
        if (!this.$200) {
            final MediaTracker mediaTracker = new MediaTracker(this);
            final int[] array = { 0, 0, 0, 0 };
            if (!this.$31(array)) {
                return;
            }
            Image[] array2 = null;
            boolean[] array3 = null;
            if (array[0] > 0) {
                array2 = new Image[array[0]];
                array3 = new boolean[array[0]];
            }
            for (int i = 0; i < array[0]; ++i) {
                array3[i] = this.$24(mediaTracker, array2, i);
            }
            final AudioClip[] array4 = (AudioClip[])((array[1] > 0) ? new AudioClip[array[1]] : null);
            for (int j = 0; j < array[1]; ++j) {
                this.$20(array4, j);
            }
            this.$19();
            if ((this.$76 = new uasbase()) != null) {
                this.$76.InitBack(array[2], this, this.$315, mediaTracker, array2, array3);
                this.$76.InitBord(array[3], this, this.$315, mediaTracker, array2, array3);
            }
            this.$323 = this.$315;
            this.repaint();
            this.InitSprite(mediaTracker, array2, array3, array4);
            this.$200 = true;
        }
        int $296 = this.$296;
        try {
            this.$336.setPriority(1);
        }
        catch (Exception ex) {}
    Label_0258_Outer:
        while (true) {
            while (true) {
                boolean b = false;
                Label_0373: {
                    if (this.$296 == 0) {
                        b = true;
                        break Label_0373;
                    }
                    if ($296 <= 0) {
                        b = false;
                        break Label_0373;
                    }
                    --$296;
                    this.$307 = 0;
                    do {
                        final long currentTimeMillis = System.currentTimeMillis();
                        this.$364(true);
                        this.repaint();
                        this.$308 = this.getTimeUnit(this.$307);
                        long n;
                        if ((n = currentTimeMillis + this.$308 - System.currentTimeMillis()) < 0L) {
                            n = 0L;
                        }
                        try {
                            Thread.sleep(n);
                        }
                        catch (Exception ex2) {}
                        while (this.$213) {
                            Thread.yield();
                            this.repaint();
                        }
                    } while ((this.$307 += this.$308) <= this.$266);
                    continue Label_0258_Outer;
                }
                if (!b) {
                    break;
                }
                continue;
            }
        }
    }
    
    private boolean $31(final int[] array) {
        final Object[] array2 = { new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0), new String() };
        uasbase.parser(this.getParameter("number"), array2, ",");
        final int intValue = (int)array2[0];
        this.$298 = intValue;
        if (intValue <= 0) {
            return false;
        }
        array[0] = (int)array2[1];
        array[1] = (int)array2[2];
        this.$144 = new Rectangle[this.$298];
        this.$143 = new Rectangle[this.$298];
        this.$131 = new int[this.$298];
        this.$111 = new int[this.$298];
        this.$129 = new int[this.$298];
        this.$135 = new int[this.$298 * 2 + 1];
        this.$92 = new uasbase[this.$298];
        for (int i = 0; i < this.$298; ++i) {
            this.$129[i] = -1;
            this.$92[i] = null;
        }
        array[2] = (int)array2[3];
        array[3] = (int)array2[4];
        this.$266 = (int)array2[5];
        if (this.$266 <= 0) {
            this.$266 = 2000;
        }
        this.$296 = (int)array2[6];
        if (this.$296 < 0) {
            this.$296 = 0;
        }
        final int n = this.$298 * 4021 + array[1] * 3 + array[0] * 61 + 103;
        int n2 = 0;
        for (int j = 23; j >= 0; --j) {
            n2 <<= 1;
            if ((n & 1 << j) != 0x0) {
                n2 |= 0x1;
            }
        }
        this.$212 = ((n2 & 0xFFFFFF) != ((int)array2[7] & 0xFFFFFF) && this.getCodeBase().getProtocol().compareTo("file") != 0);
        this.$209 = ((int)array2[8] != 0);
        this.$324 = uasbase.decodeDelim((String)array2[9]);
        return true;
    }
    
    private boolean InitSprite(final MediaTracker mediaTracker, final Image[] array, final boolean[] array2, final AudioClip[] array3) {
        int n = Integer.MAX_VALUE;
        int $266 = Integer.MIN_VALUE;
        final Object[] array4 = { new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0), new String(), new String() };
        for (int i = 0; i < this.$298; ++i) {
            uasbase.parser(this.getParameter("sprite" + String.valueOf(i + 1)), array4, ",");
            final int intValue = (int)array4[0];
            final int intValue2 = (int)array4[1];
            final int intValue3 = (int)array4[2];
            final int intValue4 = (int)array4[3];
            if (intValue3 > 0 && intValue4 > 0) {
                this.$144[i] = new Rectangle(intValue, intValue2, intValue3, intValue4);
                int intValue5;
                if ((this.$131[i] = (intValue5 = (int)array4[4])) < 0) {
                    intValue5 = (this.$131[i] = 0);
                }
                int intValue6;
                if ((this.$111[i] = (intValue6 = (int)array4[5])) < 0) {
                    intValue6 = (this.$111[i] = intValue5);
                }
                if (intValue6 < intValue5) {
                    intValue6 = intValue5;
                }
                if (intValue5 < n) {
                    n = intValue5;
                }
                if (intValue6 > $266) {
                    $266 = intValue6;
                }
                int intValue7 = (int)array4[7];
                if (intValue7 < 0) {
                    intValue7 = 0;
                }
                int intValue8 = (int)array4[8];
                if (intValue8 < 0) {
                    intValue8 = 0;
                }
                int intValue9 = (int)array4[9];
                if (intValue9 < 0) {
                    intValue9 = 0;
                }
                int intValue10 = (int)array4[10];
                if (intValue10 < 0) {
                    intValue10 = 0;
                }
                int intValue11 = (int)array4[11];
                if (intValue11 < 0) {
                    intValue11 = 0;
                }
                int intValue12 = (int)array4[12];
                if (intValue12 < 0) {
                    intValue12 = 0;
                }
                final String s = (String)array4[14];
                final String s2 = (String)array4[15];
                try {
                    this.$92[i] = (uasbase)Class.forName(s).newInstance();
                }
                catch (Exception ex) {}
                if (this.$92[i] != null) {
                    this.$92[i].InitBack(intValue11, this, this.$144[i], mediaTracker, array, array2);
                    this.$92[i].InitBord(intValue12, this, this.$144[i], mediaTracker, array, array2);
                    final int borderWidth = this.$92[i].getBorderWidth();
                    int n2 = this.$144[i].x + intValue7 + borderWidth;
                    if (n2 >= intValue3 + this.$144[i].x && n2 > 0) {
                        n2 = intValue3 + this.$144[i].x - 1;
                    }
                    int n3 = this.$144[i].y + intValue8 + borderWidth;
                    if (n3 > 0 && n3 >= intValue4 + this.$144[i].y) {
                        n3 = intValue4 + this.$144[i].y - 1;
                    }
                    int n4 = this.$144[i].width - 2 * borderWidth - intValue7 - intValue9;
                    if (n4 < 1) {
                        n4 = 1;
                    }
                    int n5 = this.$144[i].height - 2 * borderWidth - intValue8 - intValue10;
                    if (n5 < 1) {
                        n5 = 1;
                    }
                    this.$143[i] = new Rectangle(n2, n3, n4, n5);
                    final Object[] array5 = { new Color(this.$76.m_clrBk.getRGB()) };
                    if (this.$76.m_nBkType == 1) {
                        this.$92[i].SetExtra(array5);
                    }
                    else {
                        this.$92[i].SetExtra(null);
                    }
                    if (!this.$92[i].InitSprite(this, this.$143[i], intValue5, intValue6, mediaTracker, array, array2, array3, (int)array4[13] != 0, this.$208, s2)) {
                        this.$92[i] = null;
                    }
                    else {
                        this.$129[i] = (int)array4[6];
                        if (this.$129[i] < 0) {
                            this.$129[i] = 0;
                        }
                    }
                }
            }
        }
        this.$266 = $266;
        if (this.$266 <= 0) {
            this.$266 = 10000;
        }
        for (int j = 0; j < this.$298; ++j) {
            if (this.$92[j] != null) {
                this.$92[j].AppletEndTime = this.$266;
            }
        }
        return true;
    }
    
    private boolean $24(final MediaTracker mediaTracker, final Image[] array, final int n) {
        final Object[] array2 = { new Integer(0), new Point(0, 0), null, null };
        uasbase.parser(this.getParameter("image" + String.valueOf(n + 1)), array2, ",");
        array[n] = null;
        if ((int)array2[0] == 0) {
            if (array2[2] == null) {
                return true;
            }
            mediaTracker.addImage(array[n] = this.getImage(this.getDocumentBase(), (String)array2[2]), n);
            return false;
        }
        else {
            final Point point = (Point)array2[1];
            if (point.x > 0 && point.y > 0) {
                final int[] array3 = new int[point.x * point.y];
                if (array3 != null && uasbase.ProcessImg((String)array2[3], this, null, null, array3, new Dimension(point.x, point.y), 3) != null) {
                    array[n] = this.createImage(new MemoryImageSource(point.x, point.y, array3, 0, point.x));
                }
                return true;
            }
            return false;
        }
    }
    
    private void $40() {
        if (this.$215 == null || this.$323 == null) {
            return;
        }
        if (this.$76 != null) {
            this.$76.DrawBackground(this, this.$215, this.$307, this.$315, this.$323);
        }
        this.$269 = -2;
        if (this.$199 != null && this.$199.id != 505) {
            this.$269 = -1;
            for (int n = this.$298 - 1; n >= 0 && this.Hittest(n, this.$199.x, this.$199.y) <= 0; --n) {}
        }
        for (int i = 0; i < this.$298; ++i) {
            if (this.$129[i] >= 0 && this.$307 >= this.$131[i] && this.$307 <= this.$111[i]) {
                this.$92[i].DrawBackground(this, this.$215, this.$307, this.$144[i], this.$144[i]);
                this.$92[i].Process(this, this.$215, this.$143[i], (this.$269 == i) ? this.$199 : null, this.$307);
                this.$92[i].DrawBorder(this, this.$215, this.$144[i], this.$144[i]);
            }
        }
        if (this.$76 != null) {
            this.$76.DrawBorder(this, this.$215, this.$315, this.$323);
        }
        int mouseEvent = 0;
        String $324 = "";
        if (this.$269 >= 0) {
            if (this.$199.id == 501) {
                this.$206 = false;
            }
            final String[] array = { "", null, null };
            mouseEvent = this.$92[this.$269].mouseEvent(this.$199, array);
            if (array[0] != null) {
                $324 = array[0];
            }
            if (array[1] != null) {
                if (array[2] == null) {
                    array[2] = "_self";
                }
                try {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), array[1]), array[2]);
                }
                catch (Exception ex) {}
            }
        }
        else if (this.$269 == -1 && this.$209) {
            $324 = this.$324;
        }
        if (mouseEvent != this.$264) {
            this.$214.setCursor(this.$264 = mouseEvent);
        }
        if (!$324.equals(this.$325)) {
            this.showStatus(this.$325 = $324);
        }
    }
    
    private void $20(final AudioClip[] array, final int n) {
        final Object[] array2 = { null };
        uasbase.parser(this.getParameter("audio" + String.valueOf(n + 1)), array2, ",");
        array[n] = this.getAudioClip(this.getDocumentBase(), (String)array2[0]);
    }
    
    private int getTimeUnit(final int n) {
        int n2 = Integer.MAX_VALUE;
        final int n3 = this.$298 << 1;
        for (int i = 0; i < this.$298; ++i) {
            if (this.$129[i] >= 0) {
                final int n4 = this.$131[i];
                final int n5 = this.$111[i];
                if (n < n4) {
                    this.$135[i] = (this.$135[this.$298 + i] = n4 - n);
                }
                else if (n >= n5) {
                    if (this.$135[i] != -1) {
                        this.$92[i].stopAudio();
                    }
                    this.$135[i] = (this.$135[this.$298 + i] = -1);
                }
                else {
                    this.$135[i] = this.$92[i].getTimeUnit(n);
                    this.$135[this.$298 + i] = this.$92[i].getBkTimeUnit(n, n4, n5);
                }
            }
        }
        if (n >= this.$266) {
            return 20;
        }
        this.$135[n3] = ((this.$76 != null) ? this.$76.getBkTimeUnit(n, 0, this.$266) : -1);
        for (int j = 0; j <= n3; ++j) {
            if (this.$135[j] != -1 && this.$135[j] < n2) {
                n2 = this.$135[j];
            }
        }
        if (n2 > 0 && n2 != Integer.MAX_VALUE) {
            return n2;
        }
        return this.$266 - n + 1;
    }
    
    private void $19() {
        this.$208 = uasbase.IsPixelGrabberWork(this);
        this.$214 = this.$68();
        this.$315 = new Rectangle(this.size().width, this.size().height);
        this.$215 = this.createImage(this.$315.width, this.$315.height);
    }
    
    private Frame $68() {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        return (Frame)container;
    }
    
    public boolean handleEvent(final Event $199) {
        if (!this.$206 && ($199.id == 501 || $199.id == 502 || $199.id == 504 || $199.id == 505 || $199.id == 503)) {
            this.$199 = $199;
            if ($199.id == 501) {
                this.$206 = true;
                this.repaint();
            }
            else if (this.$308 > 100) {
                this.repaint();
            }
        }
        return true;
    }
    
    private int Hittest(final int $269, final int n, final int n2) {
        if (this.$129[$269] >= 0 && this.$307 >= this.$131[$269] && this.$307 <= this.$111[$269]) {
            if (this.$143[$269].inside(n, n2) && this.$92[$269].Hittest(n, n2)) {
                this.$269 = $269;
                return 1;
            }
            if (this.$92[$269].hittestBDBK(this.$144[$269], n, n2)) {
                return 2;
            }
        }
        return 0;
    }
    
    public uaplayer() {
        this.$200 = false;
        this.$206 = false;
    }
}
