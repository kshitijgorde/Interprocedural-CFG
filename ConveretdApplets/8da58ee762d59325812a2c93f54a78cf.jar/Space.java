import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.MediaTracker;
import java.applet.AudioClip;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Space extends Applet implements Runnable
{
    private static final int _$24921 = 420;
    private static final int _$24926 = 420;
    private static final int _$38299 = 15;
    private static final int _$38309 = 2;
    private static final int _$38322 = 400;
    private static boolean _$1286;
    private static boolean _$1391;
    private static boolean _$1397;
    private static final int _$38337 = 0;
    private static final int _$38341 = 1;
    private static final int _$38350 = 2;
    private static final int _$38359 = 3;
    private static final int _$38368 = 4;
    private static final int _$38373 = 5;
    private static final int _$38382 = 6;
    private static final int _$38389 = 7;
    private static final int _$38393 = 8;
    private static final int _$38399 = 9;
    private static final int _$38409 = 10;
    private static final int _$38417 = 11;
    private static final int _$38422 = 12;
    private static final int _$38429 = 13;
    private static final int _$38438 = 0;
    private static final int _$38449 = 1;
    private static final int _$38459 = 2;
    private static final int _$38474 = 3;
    private static final int _$38488 = 4;
    private static int _$38503;
    private static int _$38514;
    private static long _$38519;
    private static String[] _$1190;
    private static String[] _$38534;
    private static int[] _$38539;
    private static char[] _$38545;
    private static char[] _$38558;
    private static int _$38570;
    private static int _$38585;
    private static int _$38592;
    private static int _$38599;
    private static int _$38607;
    private static UFO[] _$38616;
    private static int _$38619;
    private static Image[] _$1214;
    private static Image _$38628;
    private static Graphics _$1240;
    private static int[] _$38639;
    private static int[] _$38645;
    private static int[] _$38651;
    private static int[] _$38658;
    private static int _$38665;
    private static int _$38672;
    private static Polygon _$38679;
    private static Polygon _$38685;
    private static AudioClip _$38691;
    private static AudioClip _$38696;
    private static AudioClip _$38703;
    private static MediaTracker _$1238;
    private static int _$38710;
    private static int _$38720;
    private static int _$38728;
    private static int _$38730;
    private static int _$38735;
    private static boolean _$2630;
    private static boolean _$2634;
    private static boolean _$38738;
    private static boolean _$38743;
    private static int _$38749;
    private static int _$38754;
    private static int _$38761;
    private static int _$38767;
    private static Font _$38777;
    private static Font _$38785;
    private static long _$38794;
    
    public Space() {
        this.enableEvents(56L);
    }
    
    public void init() {
        Space._$38514 = 0;
        Space._$1286 = true;
        Space._$1397 = false;
        Space._$38710 = 0;
        Space._$38720 = Space._$1190.length;
        this.setCursor(Cursor.getPredefinedCursor(1));
        Space._$38777 = new Font("Monospaced", 0, 10);
        Space._$38785 = new Font("Monospaced", 1, 20);
    }
    
    public void start() {
        new Thread(this).start();
        this.requestFocus();
    }
    
    private void _$1565(final int t) {
        try {
            Thread.sleep(t);
        }
        catch (Exception ex) {}
    }
    
    private Image _$1497(final String name) {
        if (Space._$1238 == null) {
            Space._$1238 = new MediaTracker(this);
        }
        final Image img = this.getImage(this.getCodeBase(), name);
        Space._$1238.addImage(img, 0);
        try {
            Space._$1238.waitForID(0);
        }
        catch (Exception ex) {}
        return img;
    }
    
    public void stop() {
        Space._$1397 = false;
        Space._$1391 = true;
    }
    
    public void destroy() {
        Space._$1397 = false;
        Space._$1240.dispose();
        Space._$38628.flush();
    }
    
    public String getAppletInfo() {
        return "Applet Information";
    }
    
    public String[][] getParameterInfo() {
        return null;
    }
    
    public void update(final Graphics g) {
        this.paint(Space._$1240);
        g.drawImage(Space._$38628, 0, 0, this);
    }
    
    public void paintGame(final Graphics g) {
        g.setFont(Space._$38777);
        final int vx_ = Space._$38728 >> 1;
        final int vx__ = Space._$38728 << 1;
        g.drawImage(Space._$1214[1], 0, 0, 420, 320, vx_, 0, vx_ + 420, 320, null);
        g.drawImage(Space._$1214[2], 0, 212, 420, 348, Space._$38728, 0, Space._$38728 + 420, 136, null);
        g.drawImage(Space._$1214[3], 0, 259, 420, 364, vx__, 0, vx__ + 420, 105, null);
        for (int a = 0; a < Space._$38616.length; ++a) {
            final int x = Space._$38616[a].x;
            final int y = Space._$38616[a].y;
            int index = 4;
            if (Space._$38616[a].bomber) {
                index = 8;
            }
            if (Space._$38616[a].hit) {
                ++index;
                g.drawImage(Space._$1214[index], x - vx__, y, null);
            }
            else {
                g.drawImage(Space._$1214[index], x - vx__, y, null);
                if (Space._$38616[a].attacking) {
                    if (Space._$38616[a].phase) {
                        g.drawImage(Space._$1214[7], x - vx__ + 4, y + 33, x - vx__ + 96, y + 91, 0, 0, 92, 58, null);
                    }
                    else {
                        g.drawImage(Space._$1214[7], x - vx__ + 4, y + 33, x - vx__ + 96, y + 91, 92, 0, 184, 58, null);
                    }
                    Space._$38616[a].phase = !Space._$38616[a].phase;
                }
            }
        }
        if (Space._$38619 > 0) {
            --Space._$38619;
            g.setColor(Color.blue);
            g.fillPolygon(Space._$38679);
            g.setColor(Color.cyan);
            g.fillPolygon(Space._$38685);
        }
        if (Space._$38607 > 0) {
            g.drawImage(Space._$1214[10], 4, 310, 36, 346, 0, 0, 32, 32, null);
        }
        if (Space._$38599 > 0) {
            g.drawImage(Space._$1214[10], 40, 310, 72, 346, 32, 0, 64, 32, null);
        }
        if (Space._$38592 > 0) {
            g.drawImage(Space._$1214[10], 76, 310, 112, 346, 64, 0, 96, 32, null);
        }
        g.drawImage(Space._$1214[6], 0, 343, null);
        if (Space._$38761 < 25) {
            g.setColor(Color.red);
        }
        else if (Space._$38761 < 50) {
            g.setColor(Color.yellow);
        }
        else {
            g.setColor(Color.green);
        }
        g.fillRect(4, 377, Space._$38761, 8);
        g.setColor(Color.green);
        g.drawString(String.valueOf("").concat(String.valueOf(Space._$38749)), 4, 402);
        final int ce = Space._$38767 * 100 / 400;
        if (ce < 25) {
            g.setColor(Color.red);
        }
        else if (ce < 50) {
            g.setColor(Color.yellow);
        }
        else {
            g.setColor(Color.green);
        }
        g.fillRect(310, 386, ce, 8);
        this.drawRadar(g);
    }
    
    public void paint(final Graphics g) {
        if (Space._$1397) {
            if (Space._$38514 == 1) {
                this.paintGame(g);
            }
            else if (Space._$38514 == 0) {
                g.drawImage(Space._$1214[11], 0, 0, null);
            }
            else if (Space._$38514 == 2) {
                g.drawImage(Space._$1214[12], 0, 0, null);
                if (Space._$38503 > 0) {
                    final int w = 334 + Space._$38503;
                    final int h = 50 + Space._$38503;
                    final int x = (420 - w) / 2;
                    final int y = (420 - h) / 2;
                    g.drawImage(Space._$1214[13], x, y, x + w, y + h, 0, 0, 334, 50, null);
                }
                else if (Space._$38503 > -200) {
                    g.drawImage(Space._$1214[13], 43, 185, null);
                }
                else if (Space._$38503 <= -200 && Space._$38503 > -375) {
                    g.drawImage(Space._$1214[13], 43, 385 + Space._$38503, null);
                }
                else {
                    g.setFont(Space._$38785);
                    g.drawImage(Space._$1214[13], 43, 10, null);
                    int y2 = Space._$38503 + 725;
                    if (y2 < 120) {
                        y2 = 120;
                        if (Space._$38754 >= 0) {
                            this._$38866(3);
                        }
                        else {
                            this._$38866(4);
                        }
                    }
                    for (int a = 0; a < Space._$38534.length; ++a) {
                        g.setColor(Color.orange);
                        g.drawString(Space._$38534[a], 80, y2);
                        g.setColor(Color.white);
                        g.drawString(String.valueOf("").concat(String.valueOf(Space._$38539[a])), 306, y2);
                        y2 += 25;
                    }
                }
            }
            else if (Space._$38514 == 3) {
                g.setFont(Space._$38785);
                g.drawImage(Space._$1214[12], 0, 0, null);
                g.drawImage(Space._$1214[13], 43, 10, null);
                for (int a2 = 0; a2 < Space._$38534.length; ++a2) {
                    g.setColor(Color.orange);
                    g.drawString(Space._$38534[a2], 80, 120 + a2 * 25);
                    g.setColor(Color.white);
                    g.drawString(String.valueOf("").concat(String.valueOf(Space._$38539[a2])), 306, 120 + a2 * 25);
                }
                g.drawString(String.valueOf(Space._$38545), 80, 122 + Space._$38754 * 25);
            }
            else if (Space._$38514 == 4) {
                g.setFont(Space._$38785);
                g.drawImage(Space._$1214[12], 0, 0, null);
                g.drawImage(Space._$1214[13], 43, 10, null);
                for (int a2 = 0; a2 < Space._$38534.length; ++a2) {
                    g.setColor(Color.orange);
                    g.drawString(Space._$38534[a2], 80, 120 + a2 * 25);
                    g.setColor(Color.white);
                    g.drawString(String.valueOf("").concat(String.valueOf(Space._$38539[a2])), 306, 120 + a2 * 25);
                }
            }
        }
        else if (Space._$1214 != null && Space._$1214[0] != null) {
            g.setColor(Color.black);
            g.fillRect(0, 0, 420, 420);
            g.drawImage(Space._$1214[0], 133, 50, null);
            g.setColor(Color.blue);
            g.drawRect(158, 300, 104, 10);
            final int w = 100 * Space._$38710 / Space._$38720;
            g.setColor(Color.cyan);
            g.fillRect(160, 301, w, 8);
        }
    }
    
    public void drawRadar(final Graphics g) {
        g.setColor(Color.green);
        g.drawRect(115 + Space._$38728 / 5, 378, 42, 20);
        for (int a = 0; a < Space._$38616.length; ++a) {
            if (Space._$38616[a].y > 0 && Space._$38616[a].x > 0 && Space._$38616[a].x < 1820) {
                if (Space._$38616[a].attacking) {
                    g.setColor(Color.red);
                    g.fillRect(115 + Space._$38616[a].x / 10, 377 + Space._$38616[a].y / 20, 4, 4);
                }
                else {
                    g.setColor(Color.orange);
                    g.fillRect(115 + Space._$38616[a].x / 10, 378 + Space._$38616[a].y / 20, 2, 2);
                }
            }
        }
    }
    
    private void _$38866(final int s) {
        final long t = System.currentTimeMillis();
        if (t - Space._$38519 < 400) {
            return;
        }
        Space._$38519 = t;
        switch (s) {
            case 0: {
                Space._$38585 = -1;
                Space._$38592 = 0;
                Space._$38599 = 0;
                Space._$38607 = 0;
                Space._$38619 = 0;
                Space._$38730 = 5;
                Space._$38749 = 0;
                Space._$38761 = 100;
                Space._$38767 = 400;
                this.setFont(Space._$38777);
                for (int a = 0; a < Space._$38558.length; ++a) {
                    Space._$38558[a] = ' ';
                    Space._$38545[a] = ' ';
                }
                Space._$38545[0] = '_';
                Space._$38570 = 0;
                Space._$38794 = 0L;
                Space._$2630 = (Space._$2634 = (Space._$38738 = (Space._$38743 = false)));
                break;
            }
            case 1: {
                for (int a = 0; a < Space._$38616.length; ++a) {
                    Space._$38616[a].respawn();
                }
                Space._$38503 = 300;
                break;
            }
            case 2: {
                Space._$38754 = -1;
                for (int a = 0; a < Space._$38534.length; ++a) {
                    if (Space._$38749 > Space._$38539[a]) {
                        for (int b = Space._$38534.length - 1; b > a; --b) {
                            Space._$38534[b] = Space._$38534[b - 1];
                            Space._$38539[b] = Space._$38539[b - 1];
                        }
                        Space._$38539[a] = Space._$38749;
                        Space._$38534[a] = "";
                        Space._$38754 = a;
                        System.out.println(String.valueOf("rank:").concat(String.valueOf(Space._$38754)));
                        break;
                    }
                }
                break;
            }
        }
        Space._$38514 = s;
    }
    
    protected void processMouseEvent(final MouseEvent e) {
        this.requestFocus();
        super.processMouseEvent(e);
        if (e.getID() == 501) {
            if (Space._$38514 == 1) {
                if (Space._$38761 >= 15) {
                    Space._$38761 -= 15;
                    final int x = e.getX();
                    Space._$38672 = e.getY();
                    Space._$38679.xpoints[0] = (Space._$38685.xpoints[0] = x);
                    Space._$38679.ypoints[0] = (Space._$38685.ypoints[0] = Space._$38672);
                    Space._$38619 = 1;
                    Space._$38665 = x + (Space._$38728 << 1);
                    Space._$38691.play();
                }
                else {
                    Space._$38696.play();
                }
            }
            else if (Space._$38514 == 0) {
                this._$38866(1);
            }
            else if (Space._$38514 == 4) {
                this._$38866(0);
            }
        }
    }
    
    protected void processMouseMotionEvent(final MouseEvent e) {
        final int x = e.getX();
        Space._$38738 = false;
        Space._$38743 = false;
        if (x < 20) {
            Space._$38738 = true;
        }
        else if (x > 400) {
            Space._$38743 = true;
        }
    }
    
    protected void processKeyEvent(final KeyEvent e) {
        if (Space._$38514 == 1) {
            boolean value = true;
            if ((e.getID() & 0x192) == 0x192) {
                value = false;
            }
            if (Space._$1391) {
                Space._$1391 = false;
                this.repaint();
            }
            else {
                switch (e.getKeyCode()) {
                    case 37: {
                        Space._$2630 = value;
                        break;
                    }
                    case 39: {
                        Space._$2634 = value;
                        break;
                    }
                }
            }
        }
        else if ((e.getID() & 0x191) == 0x191) {
            if (Space._$38514 == 0) {
                this._$38866(1);
            }
            else if (Space._$38514 == 3) {
                if (Space._$38754 >= 0) {
                    final int code = e.getKeyCode();
                    if (code == 8) {
                        Space._$38545[Space._$38570] = ' ';
                        Space._$38558[Space._$38570] = ' ';
                        if (Space._$38570 > 0) {
                            --Space._$38570;
                        }
                        Space._$38545[Space._$38570] = '_';
                        Space._$38558[Space._$38570] = ' ';
                        Space._$38534[Space._$38754] = String.valueOf(Space._$38558);
                    }
                    else if (code == 10) {
                        Space._$38754 = -1;
                        Space._$38749 = 0;
                        this._$38866(4);
                    }
                }
            }
            else if (Space._$38514 == 4) {
                this._$38866(0);
            }
        }
        else if ((e.getID() & 0x190) == 0x190 && Space._$38514 == 3 && Space._$38754 >= 0 && Space._$38503 < 375) {
            final char c = e.getKeyChar();
            final long d = System.currentTimeMillis() - Space._$38794;
            if (d > 150 && (Character.isLetterOrDigit(c) || c == '_' || c == ' ')) {
                Space._$38794 = System.currentTimeMillis();
                Space._$38558[Space._$38570] = c;
                Space._$38545[Space._$38570] = ' ';
                if (Space._$38570 < Space._$38558.length - 1) {
                    ++Space._$38570;
                }
                Space._$38545[Space._$38570] = '_';
                Space._$38534[Space._$38754] = String.valueOf(Space._$38558);
            }
        }
    }
    
    private void _$38996(final int a) {
        Space._$38616[a].step(Space._$38730);
        if (Space._$38616[a].attacking) {
            if (Space._$38616[a].sound) {
                Space._$38616[a].sound = false;
                Space._$38703.play();
            }
            if (Space._$38767 > 0) {
                --Space._$38767;
            }
        }
    }
    
    private void _$39012(final int a) {
        Space._$38616[a].hit = false;
        final UFO ufo = Space._$38616[a];
        --ufo.shield;
        if (Space._$38616[a].shield < 0) {
            if (Space._$38616[a].bomber) {
                Space._$38749 += 50;
                if (Math.random() > 0.0) {
                    switch (Space._$38585 = (int)(Math.random() * 3)) {
                        case 0: {
                            Space._$38607 = 100;
                            break;
                        }
                        case 1: {
                            Space._$38599 = 100;
                            break;
                        }
                        case 2: {
                            Space._$38592 = 75;
                            break;
                        }
                        case 3: {
                            Space._$38599 = 100;
                            break;
                        }
                    }
                }
                else {
                    Space._$38585 = -1;
                }
            }
            else {
                Space._$38749 += 25;
            }
            ++Space._$38735;
            if (Space._$38735 % 5 == 0) {
                ++Space._$38730;
            }
            Space._$38616[a].respawn();
        }
    }
    
    public void run() {
        if (Space._$1286) {
            Space._$1286 = false;
            Space._$38628 = this.createImage(420, 420);
            Space._$1240 = Space._$38628.getGraphics();
            Space._$38639[0] = 210;
            Space._$38645[0] = 210;
            Space._$38639[1] = 230;
            Space._$38645[1] = 352;
            Space._$38639[2] = 190;
            Space._$38645[2] = 352;
            Space._$38651[0] = 210;
            Space._$38658[0] = 210;
            Space._$38651[1] = 220;
            Space._$38658[1] = 352;
            Space._$38651[2] = 200;
            Space._$38658[2] = 352;
            Space._$38679 = new Polygon(Space._$38639, Space._$38645, 3);
            Space._$38685 = new Polygon(Space._$38651, Space._$38658, 3);
            Space._$38720 = Space._$1190.length;
            Space._$1214 = new Image[Space._$1190.length];
            for (int a = 0; a < Space._$1190.length; ++a) {
                Space._$1214[a] = this._$1497(Space._$1190[a]);
                ++Space._$38710;
                this.repaint();
                Thread.yield();
            }
            (Space._$38691 = this.getAudioClip(this.getDocumentBase(), "shoot.au")).play();
            Thread.yield();
            Space._$38691.stop();
            (Space._$38703 = this.getAudioClip(this.getDocumentBase(), "warn.au")).play();
            Thread.yield();
            Space._$38703.stop();
            (Space._$38696 = this.getAudioClip(this.getDocumentBase(), "noshoot.au")).play();
            Thread.yield();
            Space._$38696.stop();
            while (Space._$38710 < Space._$38720) {
                this.repaint();
                ++Space._$38710;
            }
            Space._$38616 = new UFO[5];
            for (int a = 0; a < Space._$38616.length; ++a) {
                Space._$38616[a] = new UFO(1820, 420);
            }
        }
        Space._$1397 = true;
        while (Space._$1397) {
            if (Space._$38514 == 1) {
                final long t1 = System.currentTimeMillis();
                int delta = 10;
                if (Space._$38599 > 0) {
                    if (Space._$38767 < 395) {
                        Space._$38767 += 5;
                    }
                    else {
                        Space._$38767 = 400;
                    }
                }
                else if (Space._$38592 > 0) {
                    delta <<= 1;
                }
                if (Space._$2630) {
                    if (Space._$38728 > delta) {
                        Space._$38728 -= delta;
                    }
                    else {
                        Space._$38728 = 0;
                    }
                }
                if (Space._$38738) {
                    if (Space._$38728 > delta) {
                        Space._$38728 -= delta;
                    }
                    else {
                        Space._$38728 = 0;
                    }
                }
                if (Space._$2634) {
                    final int mx = 700;
                    if (Space._$38728 < mx - delta) {
                        Space._$38728 += delta;
                    }
                    else {
                        Space._$38728 = mx;
                    }
                }
                if (Space._$38743) {
                    final int mx = 700;
                    if (Space._$38728 < mx - delta) {
                        Space._$38728 += delta;
                    }
                    else {
                        Space._$38728 = mx;
                    }
                }
                if (Space._$38619 > 0) {
                    for (int a2 = 0; a2 < Space._$38616.length; ++a2) {
                        if (Space._$38616[a2].hit) {
                            this._$39012(a2);
                        }
                        else if (Space._$38616[a2].bomber) {
                            if (Space._$38616[a2].x + 6 < Space._$38665 && Space._$38616[a2].x + 56 > Space._$38665 && Space._$38616[a2].y + 8 < Space._$38672 && Space._$38616[a2].y + 28 > Space._$38672) {
                                Space._$38616[a2].hit = true;
                                break;
                            }
                        }
                        else if (Space._$38616[a2].x + 6 < Space._$38665 && Space._$38616[a2].x + 96 > Space._$38665 && Space._$38616[a2].y + 7 < Space._$38672 && Space._$38616[a2].y + 26 > Space._$38672) {
                            Space._$38616[a2].hit = true;
                            break;
                        }
                        this._$38996(a2);
                    }
                }
                else {
                    for (int a2 = 0; a2 < Space._$38616.length; ++a2) {
                        if (Space._$38616[a2].hit) {
                            this._$39012(a2);
                        }
                        this._$38996(a2);
                    }
                }
                --Space._$38599;
                --Space._$38592;
                --Space._$38607;
                if (Space._$38767 <= 0) {
                    this._$38866(2);
                }
                this.repaint();
                if (Space._$38761 < 100) {
                    Space._$38761 += 2;
                    if (Space._$38607 > 0) {
                        Space._$38761 += 2;
                    }
                    if (Space._$38761 > 100) {
                        Space._$38761 = 100;
                    }
                }
                final long t2 = System.currentTimeMillis();
                final long t3 = t2 - t1;
                if (t3 > 100) {
                    Thread.yield();
                }
                else {
                    this._$1565((int)(100 - t3));
                }
            }
            else {
                if (Space._$38514 == 2) {
                    Space._$38503 -= 20;
                }
                Thread.yield();
                this.repaint();
            }
        }
    }
    
    static {
        Space._$1286 = true;
        Space._$1391 = false;
        Space._$1397 = false;
        Space._$38514 = 0;
        Space._$38519 = 0L;
        Space._$1190 = new String[] { "logo.gif", "bgl1.jpg", "fg.gif", "fg2.gif", "fighter.gif", "fighter2.gif", "console.gif", "attack.gif", "bomber.gif", "bomber2.gif", "powerup.gif", "titel.jpg", "darkbg.jpg", "gameover.gif" };
        Space._$38534 = new String[] { "www", "steinke", "net", "", "", "hope", "you", "had", "fun", ":-)" };
        Space._$38539 = new int[] { 500, 450, 400, 350, 300, 250, 200, 150, 100, 50 };
        Space._$38545 = new char[] { '_', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
        Space._$38558 = new char[] { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
        Space._$38570 = 0;
        Space._$38585 = -1;
        Space._$38592 = 0;
        Space._$38599 = 0;
        Space._$38607 = 0;
        Space._$38619 = 0;
        Space._$38639 = new int[3];
        Space._$38645 = new int[3];
        Space._$38651 = new int[3];
        Space._$38658 = new int[3];
        Space._$1238 = null;
        Space._$38710 = 0;
        Space._$38720 = Space._$1190.length;
        Space._$38728 = 350;
        Space._$38730 = 5;
        Space._$38735 = 0;
        Space._$38749 = 0;
        Space._$38754 = -1;
        Space._$38761 = 100;
        Space._$38767 = 400;
        Space._$38794 = 0L;
    }
}
