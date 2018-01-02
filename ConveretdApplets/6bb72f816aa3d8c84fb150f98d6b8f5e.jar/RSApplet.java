import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.Graphics;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class RSApplet extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener, WindowListener, MouseWheelListener
{
    public static int clientSize;
    public static int clientWidth;
    public static int clientHeight;
    public static int hotKey;
    private int anInt4;
    private int delayTime;
    int minDelay;
    private final long[] aLongArray7;
    int fps;
    boolean shouldDebug;
    int myWidth;
    int myHeight;
    Graphics graphics;
    RSImageProducer fullGameScreen;
    RSFrame gameFrame;
    private boolean shouldClearScreen;
    boolean awtFocus;
    int idleTime;
    int clickMode2;
    public int mouseX;
    public int mouseY;
    private int clickMode1;
    private int clickX;
    private int clickY;
    private long clickTime;
    int clickMode3;
    int saveClickX;
    int saveClickY;
    long aLong29;
    final int[] keyArray;
    private final int[] charQueue;
    private int readIndex;
    private int writeIndex;
    public static int anInt34;
    
    final void createClientFrame(final int myHeight, final int myWidth) {
        this.myWidth = myWidth;
        this.myHeight = myHeight;
        this.gameFrame = new RSFrame(this, this.myWidth, this.myHeight);
        this.setResizable(true);
        this.graphics = this.getGameComponent().getGraphics();
        this.fullGameScreen = new RSImageProducer(this.myWidth, this.myHeight, this.getGameComponent());
        this.startRunnable(this, 1);
    }
    
    @Override
    public void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
        final int wheelRotation = mouseWheelEvent.getWheelRotation();
        if (this.mouseX > 0 && this.mouseY > 340 && this.mouseX < 510 && this.mouseY < 500) {
            client.anInt1089 -= wheelRotation * 30;
        }
        if (this.mouseY > 210 && this.mouseY < 473 && this.mouseX > 514 && this.mouseX < 762 && client.tabInterfaceIDs[client.tabID] == 638) {
            final RSInterface rsInterface = RSInterface.interfaceCache[639];
            rsInterface.scrollPosition += wheelRotation * 30;
        }
        if (this.mouseY > 210 && this.mouseY < 473 && this.mouseX > 514 && this.mouseX < 762 && client.tabInterfaceIDs[client.tabID] == 147) {
            final RSInterface rsInterface2 = RSInterface.interfaceCache[148];
            rsInterface2.scrollPosition += wheelRotation * 30;
        }
        if (this.mouseY > 210 && this.mouseY < 473 && this.mouseX > 514 && this.mouseX < 762 && client.tabInterfaceIDs[client.tabID] == 638) {
            final RSInterface rsInterface3 = RSInterface.interfaceCache[16025];
            rsInterface3.scrollPosition += wheelRotation * 30;
        }
        if (this.mouseY > 210 && this.mouseY < 473 && this.mouseX > 514 && this.mouseX < 762 && client.tabInterfaceIDs[client.tabID] == 18128) {
            final RSInterface rsInterface4 = RSInterface.interfaceCache[18143];
            rsInterface4.scrollPosition += wheelRotation * 30;
        }
        if (this.mouseY > 210 && this.mouseY < 473 && this.mouseX > 514 && this.mouseX < 762 && client.tabInterfaceIDs[client.tabID] == 5065) {
            final RSInterface rsInterface5 = RSInterface.interfaceCache[5066];
            rsInterface5.scrollPosition += wheelRotation * 30;
        }
        if (this.mouseY > 210 && this.mouseY < 473 && this.mouseX > 514 && this.mouseX < 762 && client.tabInterfaceIDs[client.tabID] == 5715) {
            final RSInterface rsInterface6 = RSInterface.interfaceCache[5716];
            rsInterface6.scrollPosition += wheelRotation * 30;
        }
        if (this.mouseX > 0 && this.mouseX < 512 && this.mouseY > 0 && this.mouseY < 334 && client.openInterfaceID == 5292) {
            final RSInterface rsInterface7 = RSInterface.interfaceCache[5385];
            rsInterface7.scrollPosition += wheelRotation * 30;
        }
    }
    
    final void initClientFrame(final int myHeight, final int myWidth) {
        this.myWidth = myWidth;
        this.myHeight = myHeight;
        this.graphics = this.getGameComponent().getGraphics();
        this.fullGameScreen = new RSImageProducer(this.myWidth, this.myHeight, this.getGameComponent());
        this.startRunnable(this, 1);
    }
    
    public void setResizable(final boolean resizable) {
        if (this.gameFrame != null) {
            this.gameFrame.setResizable(resizable);
        }
    }
    
    public void resetImageProducers() {
    }
    
    public void resetImageProducers2() {
    }
    
    @Override
    public void run() {
        this.getGameComponent().addMouseWheelListener(this);
        this.getGameComponent().addMouseListener(this);
        this.getGameComponent().addMouseMotionListener(this);
        this.getGameComponent().addKeyListener(this);
        this.getGameComponent().addFocusListener(this);
        if (this.gameFrame != null) {
            this.gameFrame.addWindowListener(this);
        }
        this.drawLoadingText(0, "Loading...");
        this.startUp();
        int n = 0;
        int n2 = 256;
        int n3 = 1;
        int i = 0;
        int n4 = 0;
        for (int j = 0; j < 10; ++j) {
            this.aLongArray7[j] = System.currentTimeMillis();
        }
        System.currentTimeMillis();
        this.resetImageProducers();
        this.resetImageProducers2();
        while (this.anInt4 >= 0) {
            if (this.anInt4 > 0) {
                --this.anInt4;
                if (this.anInt4 == 0) {
                    this.exit();
                    return;
                }
            }
            final int n5 = n2;
            final int n6 = n3;
            n2 = 300;
            n3 = 1;
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.aLongArray7[n] == 0L) {
                n2 = n5;
                n3 = n6;
            }
            else if (currentTimeMillis > this.aLongArray7[n]) {
                n2 = (int)(2560 * this.delayTime / (currentTimeMillis - this.aLongArray7[n]));
            }
            if (n2 < 25) {
                n2 = 25;
            }
            if (n2 > 256) {
                n2 = 256;
                n3 = (int)(this.delayTime - (currentTimeMillis - this.aLongArray7[n]) / 10L);
            }
            if (n3 > this.delayTime) {
                n3 = this.delayTime;
            }
            this.aLongArray7[n] = currentTimeMillis;
            n = (n + 1) % 10;
            if (n3 > 1) {
                for (int k = 0; k < 10; ++k) {
                    if (this.aLongArray7[k] != 0L) {
                        final long[] aLongArray7 = this.aLongArray7;
                        final int n7 = k;
                        aLongArray7[n7] += n3;
                    }
                }
            }
            if (n3 < this.minDelay) {
                n3 = this.minDelay;
            }
            try {
                Thread.sleep(n3);
            }
            catch (InterruptedException ex) {
                ++n4;
            }
            while (i < 256) {
                this.clickMode3 = this.clickMode1;
                this.saveClickX = this.clickX;
                this.saveClickY = this.clickY;
                this.aLong29 = this.clickTime;
                this.clickMode1 = 0;
                this.processGameLoop();
                this.readIndex = this.writeIndex;
                i += n2;
            }
            i &= 0xFF;
            if (this.delayTime > 0) {
                this.fps = 1000 * n2 / (this.delayTime * 256);
            }
            this.processDrawing();
            if (this.shouldDebug) {
                for (int l = 0; l < 10; ++l) {
                    final int n8 = (n - l - 1 + 20) % 10;
                    System.out.println("otim" + n8 + ":" + this.aLongArray7[n8]);
                }
                System.out.println("fps:" + this.fps + " ratio:" + n2 + " count:" + i);
                System.out.println("del:" + n3 + " deltime:" + this.delayTime + " mindel:" + this.minDelay);
                System.out.println("intex:" + n4 + " opos:" + n);
                this.shouldDebug = false;
                n4 = 0;
            }
        }
        if (this.anInt4 == -1) {
            this.exit();
        }
    }
    
    private void exit() {
        this.anInt4 = -2;
        this.cleanUpForQuit();
        if (this.gameFrame != null) {
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex) {}
            try {
                System.exit(0);
            }
            catch (Throwable t) {}
        }
    }
    
    final void method4(final int n) {
        this.delayTime = 1000 / n;
    }
    
    @Override
    public final void start() {
        if (this.anInt4 >= 0) {
            this.anInt4 = 0;
        }
    }
    
    @Override
    public final void stop() {
        if (this.anInt4 >= 0) {
            this.anInt4 = 4000 / this.delayTime;
        }
    }
    
    @Override
    public final void destroy() {
        System.exit(0);
    }
    
    @Override
    public final void update(final Graphics graphics) {
        if (this.graphics == null) {
            this.graphics = graphics;
        }
        this.shouldClearScreen = true;
        this.raiseWelcomeScreen();
    }
    
    @Override
    public final void paint(final Graphics graphics) {
        if (this.graphics == null) {
            this.graphics = graphics;
        }
        this.shouldClearScreen = true;
        this.raiseWelcomeScreen();
    }
    
    @Override
    public final void mousePressed(final MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        if (this.gameFrame != null) {
            x -= 4;
            y -= 22;
        }
        this.idleTime = 0;
        if (RSApplet.clientSize == 0) {
            final int n = x;
            final client instance = client.instance;
            x = n - ((client.clientWidth - 765) / 2 - 2);
        }
        this.clickX = x;
        this.clickY = y;
        this.clickTime = System.currentTimeMillis();
        if (mouseEvent.isMetaDown()) {
            this.clickMode1 = 2;
            this.clickMode2 = 2;
        }
        else {
            this.clickMode1 = 1;
            this.clickMode2 = 1;
        }
    }
    
    @Override
    public final void mouseReleased(final MouseEvent mouseEvent) {
        this.idleTime = 0;
        this.clickMode2 = 0;
    }
    
    @Override
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    @Override
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    @Override
    public final void mouseExited(final MouseEvent mouseEvent) {
        this.idleTime = 0;
        this.mouseX = -1;
        this.mouseY = -1;
    }
    
    @Override
    public final void mouseDragged(final MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        if (this.gameFrame != null) {
            x -= 4;
            y -= 22;
        }
        if (System.currentTimeMillis() - this.clickTime >= 250L || Math.abs(this.saveClickX - x) > 5 || Math.abs(this.saveClickY - y) > 5) {
            this.idleTime = 0;
            this.mouseX = x;
            this.mouseY = y;
        }
    }
    
    @Override
    public final void mouseMoved(final MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        if (this.gameFrame != null) {
            x -= 4;
            y -= 22;
        }
        if (System.currentTimeMillis() - this.clickTime >= 250L || Math.abs(this.saveClickX - x) > 5 || Math.abs(this.saveClickY - y) > 5) {
            this.idleTime = 0;
            this.mouseX = x;
            this.mouseY = y;
        }
    }
    
    @Override
    public final void keyPressed(final KeyEvent keyEvent) {
        this.idleTime = 0;
        final int keyCode = keyEvent.getKeyCode();
        int keyChar = keyEvent.getKeyChar();
        if (keyCode == 155) {
            client.zoom += 15;
        }
        if (keyCode == 33) {
            client.zoom -= 15;
        }
        if (RSApplet.hotKey == 508) {
            if (keyCode == 27) {
                client.canSwap = false;
            }
            else if (keyCode == 112) {
                client.setTab(3);
            }
            else if (keyCode == 113) {
                client.setTab(4);
            }
            else if (keyCode == 114) {
                client.setTab(5);
            }
            else if (keyCode == 115) {
                client.setTab(6);
            }
            else if (keyCode == 116) {
                client.setTab(0);
            }
        }
        else if (keyCode != 27) {
            if (keyCode == 112) {
                client.setTab(3);
            }
            else if (keyCode == 113) {
                client.setTab(4);
            }
            else if (keyCode == 114) {
                client.setTab(5);
            }
            else if (keyCode == 115) {
                client.setTab(6);
            }
            else if (keyCode == 116) {
                client.setTab(0);
            }
        }
        if (keyChar < 30) {
            keyChar = 0;
        }
        if (keyCode == 37) {
            keyChar = 1;
        }
        if (keyCode == 39) {
            keyChar = 2;
        }
        if (keyCode == 38) {
            keyChar = 3;
        }
        if (keyCode == 40) {
            keyChar = 4;
        }
        if (keyCode == 17) {
            keyChar = 5;
        }
        if (keyCode == 8) {
            keyChar = 8;
        }
        if (keyCode == 127) {
            keyChar = 8;
        }
        if (keyCode == 9) {
            keyChar = 9;
        }
        if (keyCode == 10) {
            keyChar = 10;
        }
        if (keyCode >= 112 && keyCode <= 123) {
            keyChar = 1008 + keyCode - 112;
        }
        if (keyCode == 36) {
            keyChar = 1000;
        }
        if (keyCode == 35) {
            keyChar = 1001;
        }
        if (keyCode == 33) {
            keyChar = 1002;
        }
        if (keyCode == 34) {
            keyChar = 1003;
        }
        if (keyChar > 0 && keyChar < 128) {
            this.keyArray[keyChar] = 1;
        }
        if (keyChar > 4) {
            this.charQueue[this.writeIndex] = keyChar;
            this.writeIndex = (this.writeIndex + 1 & 0x7F);
        }
    }
    
    @Override
    public final void keyReleased(final KeyEvent keyEvent) {
        this.idleTime = 0;
        final int keyCode = keyEvent.getKeyCode();
        int keyChar = keyEvent.getKeyChar();
        if (keyChar < 30) {
            keyChar = 0;
        }
        if (keyCode == 37) {
            keyChar = 1;
        }
        if (keyCode == 39) {
            keyChar = 2;
        }
        if (keyCode == 38) {
            keyChar = 3;
        }
        if (keyCode == 40) {
            keyChar = 4;
        }
        if (keyCode == 17) {
            keyChar = 5;
        }
        if (keyCode == 8) {
            keyChar = 8;
        }
        if (keyCode == 127) {
            keyChar = 8;
        }
        if (keyCode == 9) {
            keyChar = 9;
        }
        if (keyCode == 10) {
            keyChar = 10;
        }
        if (keyChar > 0 && keyChar < 128) {
            this.keyArray[keyChar] = 0;
        }
        client.canSwap = false;
    }
    
    @Override
    public final void keyTyped(final KeyEvent keyEvent) {
    }
    
    final int readChar(final int i) {
        while (i >= 0) {
            for (int j = 1; j > 0; ++j) {}
        }
        int n = -1;
        if (this.writeIndex != this.readIndex) {
            n = this.charQueue[this.readIndex];
            this.readIndex = (this.readIndex + 1 & 0x7F);
        }
        return n;
    }
    
    @Override
    public final void focusGained(final FocusEvent focusEvent) {
        this.awtFocus = true;
        this.shouldClearScreen = true;
        this.raiseWelcomeScreen();
    }
    
    @Override
    public final void focusLost(final FocusEvent focusEvent) {
        this.awtFocus = false;
        for (int i = 0; i < 128; ++i) {
            this.keyArray[i] = 0;
        }
    }
    
    @Override
    public final void windowActivated(final WindowEvent windowEvent) {
    }
    
    @Override
    public final void windowClosed(final WindowEvent windowEvent) {
    }
    
    @Override
    public final void windowClosing(final WindowEvent windowEvent) {
        this.destroy();
    }
    
    @Override
    public final void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    @Override
    public final void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    @Override
    public final void windowIconified(final WindowEvent windowEvent) {
    }
    
    @Override
    public final void windowOpened(final WindowEvent windowEvent) {
    }
    
    void startUp() {
    }
    
    void processGameLoop() {
    }
    
    void cleanUpForQuit() {
    }
    
    void processDrawing() {
    }
    
    void raiseWelcomeScreen() {
    }
    
    Component getGameComponent() {
        if (this.gameFrame != null) {
            return this.gameFrame;
        }
        return this;
    }
    
    public void startRunnable(final Runnable runnable, final int priority) {
        final Thread thread = new Thread(runnable);
        thread.start();
        thread.setPriority(priority);
    }
    
    void drawLoadingText(final int n, final String s) {
        while (this.graphics == null) {
            this.graphics = this.getGameComponent().getGraphics();
            try {
                this.getGameComponent().repaint();
            }
            catch (Exception ex) {}
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex2) {}
        }
        final Font font = new Font("Helvetica", 1, 13);
        final FontMetrics fontMetrics = this.getGameComponent().getFontMetrics(font);
        this.getGameComponent().getFontMetrics(new Font("Helvetica", 0, 13));
        if (this.shouldClearScreen) {
            this.graphics.setColor(Color.black);
            this.graphics.fillRect(0, 0, this.myWidth, this.myHeight);
            this.shouldClearScreen = false;
        }
        final Color color = new Color(140, 17, 17);
        final int n2 = this.myHeight / 2 - 18;
        this.graphics.setColor(color);
        this.graphics.drawRect(this.myWidth / 2 - 152, n2, 304, 33);
        this.graphics.fillRect(this.myWidth / 2 - 150, n2 + 2, n * 3, 30);
        this.graphics.setColor(Color.black);
        this.graphics.fillRect(this.myWidth / 2 - 150 + n * 3, n2 + 2, 300 - n * 3, 30);
        this.graphics.setFont(font);
        this.graphics.setColor(Color.white);
        this.graphics.drawString("Devilishpkz is Loading... Please wait.", (this.myWidth - fontMetrics.stringWidth("Devilishpkz is Loading... Please wait.")) / 2, n2 - 11);
        this.graphics.drawString(s, (this.myWidth - fontMetrics.stringWidth(s)) / 2, n2 + 22);
    }
    
    RSApplet() {
        this.delayTime = 20;
        this.minDelay = 1;
        this.aLongArray7 = new long[10];
        this.shouldDebug = false;
        this.shouldClearScreen = true;
        this.awtFocus = true;
        this.keyArray = new int[128];
        this.charQueue = new int[128];
    }
    
    static {
        RSApplet.hotKey = 508;
    }
}
