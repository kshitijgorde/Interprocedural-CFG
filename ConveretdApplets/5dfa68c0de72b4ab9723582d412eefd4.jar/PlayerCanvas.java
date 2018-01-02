import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Font;
import java.util.Hashtable;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.sound.sampled.TargetDataLine;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

class PlayerCanvas extends JPanel implements KeyListener, MouseListener, MouseMotionListener
{
    static final String versionString = "v39";
    static final int width = 482;
    static final int height = 387;
    static final int topBarHeight = 26;
    static final int goButtonX = 418;
    static final int stopButtonX = 451;
    static final int buttonY = 4;
    static final Color BLACK;
    static final Color WHITE;
    static final int soundInputBufSize = 50000;
    static TargetDataLine soundInputLine;
    byte[] soundInputBuf;
    int soundLevel;
    boolean overGoButton;
    boolean overStopButton;
    String message;
    boolean isLoading;
    double loadingFraction;
    LContext lc;
    Sprite stage;
    Object[] sprites;
    BufferedImage offscreen;
    BufferedImage penTrails;
    Rectangle invalrect;
    int mouseX;
    int mouseY;
    boolean mouseIsDown;
    int mouseDownX;
    int mouseDownY;
    Drawable mouseDragTarget;
    int mouseDragXOffset;
    int mouseDragYOffset;
    boolean reportClickOnMouseUp;
    Vector mouseclicks;
    boolean[] keydown;
    Vector keystrokes;
    AskPrompter askPrompt;
    String lastAnswer;
    static /* synthetic */ Class class$javax$sound$sampled$TargetDataLine;
    
    PlayerCanvas() {
        this.soundInputBuf = new byte[50000];
        this.soundLevel = 0;
        this.overGoButton = false;
        this.overStopButton = false;
        this.message = "";
        this.isLoading = true;
        this.loadingFraction = 0.2;
        this.sprites = new Object[0];
        this.invalrect = new Rectangle();
        this.mouseIsDown = false;
        this.mouseclicks = new Vector();
        this.keydown = new boolean[256];
        this.keystrokes = new Vector();
        this.askPrompt = null;
        this.lastAnswer = "";
        this.setLayout(null);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void addNotify() {
        super.addNotify();
        (this.offscreen = (BufferedImage)this.createImage(482, 387)).getRaster();
        final Graphics graphics = this.offscreen.getGraphics();
        graphics.setColor(PlayerCanvas.WHITE);
        graphics.fillRect(0, 0, 482, 387);
        graphics.dispose();
        this.repaint();
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(482, 387);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(482, 387);
    }
    
    public synchronized void paintComponent(final Graphics graphics) {
        graphics.drawImage(this.offscreen, 0, 0, 482, 387, this);
    }
    
    void clearall(final LContext lContext) {
        this.stage = null;
        this.sprites = new Object[0];
        this.askPrompt = null;
        this.lastAnswer = "";
        this.penTrails = null;
        SoundPlayer.stopSoundsForApplet(lContext);
        this.soundLevel = 0;
        lContext.props = new Hashtable();
        Runtime.getRuntime().gc();
        this.clearkeys();
        this.mouseclicks = new Vector();
        this.mouseIsDown = false;
        this.mouseDragTarget = null;
    }
    
    void setMessage(final String message) {
        this.message = message;
        this.redraw_all();
    }
    
    synchronized void inval(final Rectangle rectangle) {
        if (this.invalrect.isEmpty()) {
            this.invalrect = new Rectangle(rectangle);
        }
        else {
            this.invalrect = this.invalrect.union(rectangle);
        }
    }
    
    void invalAll() {
        this.inval(new Rectangle(0, 0, 482, 387));
    }
    
    void redraw_all() {
        this.redraw(new Rectangle(0, 0, 482, 387), false);
    }
    
    void redraw_invalid() {
        this.redraw(this.invalrect, false);
    }
    
    synchronized void redraw(final Rectangle rectangle, final boolean b) {
        final Graphics graphics = this.offscreen.getGraphics();
        graphics.setClip(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        graphics.setColor(PlayerCanvas.WHITE);
        graphics.fillRect(0, 0, 482, 387);
        if (this.isLoading) {
            this.drawProgressBar(graphics);
        }
        else {
            if (this.stage != null) {
                this.stage.setStageOffset();
                this.stage.paint(graphics);
            }
            if (this.penTrails != null) {
                graphics.drawImage(this.penTrails, 0, 0, 482, 387, null);
            }
            for (int i = this.sprites.length - 1; i >= 0; --i) {
                final Drawable drawable = (Drawable)this.sprites[i];
                if (drawable.isShowing() && rectangle.intersects(drawable.fullRect())) {
                    drawable.paint(graphics);
                }
            }
            for (int j = this.sprites.length - 1; j >= 0; --j) {
                final Drawable drawable2 = (Drawable)this.sprites[j];
                if (drawable2.isShowing() && rectangle.intersects(drawable2.fullRect())) {
                    drawable2.paintBubble(graphics);
                }
            }
            if (this.askPrompt != null) {
                this.askPrompt.paint(graphics);
            }
        }
        this.drawBorder(graphics);
        if (b) {
            graphics.setColor(new Color(200, 0, 0));
            graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        graphics.dispose();
        this.repaint(rectangle);
        this.invalrect = new Rectangle();
    }
    
    void drawBorder(final Graphics graphics) {
        graphics.setColor(new Color(130, 130, 130));
        graphics.fillRect(0, 0, 482, 25);
        graphics.setColor(PlayerCanvas.BLACK);
        graphics.fillRect(0, 0, 482, 1);
        graphics.fillRect(0, 0, 1, 387);
        graphics.fillRect(0, 386, 482, 1);
        graphics.fillRect(481, 0, 1, 387);
        graphics.fillRect(0, 25, 482, 1);
        graphics.drawImage(this.overGoButton ? Skin.goButtonOver : Skin.goButton, 418, 4, null);
        graphics.drawImage(this.overStopButton ? Skin.stopButtonOver : Skin.stopButton, 451, 4, null);
        graphics.setColor(PlayerCanvas.WHITE);
        graphics.setFont(new Font("SansSerif", 0, 8));
        graphics.drawString("v39", 5, 11);
        if (this.message.length() > 0) {
            graphics.setFont(new Font("SansSerif", 1, 13));
            graphics.setColor(new Color(250, 250, 0));
            graphics.drawString(this.message, 70, 17);
        }
    }
    
    void drawProgressBar(final Graphics graphics) {
        graphics.setColor(PlayerCanvas.BLACK);
        graphics.setFont(new Font("SansSerif", 1, 24));
        graphics.drawString("Loading...", 188, 173);
        final int n = 91;
        final int n2 = 193;
        graphics.fillRect(n, n2, 300, 1);
        graphics.fillRect(n, n2, 1, 29);
        graphics.fillRect(n, n2 + 28, 300, 1);
        graphics.fillRect(n + 299, n2, 1, 29);
        graphics.setColor(new Color(10, 10, 200));
        graphics.fillRect(n + 2, n2 + 2, (int)(296.0 * Math.min(this.loadingFraction, 1.0)), 25);
        this.drawBorder(graphics);
    }
    
    BufferedImage drawAreaWithoutSprite(final Rectangle rectangle, final Sprite sprite) {
        final BufferedImage bufferedImage = new BufferedImage(rectangle.width, rectangle.height, 2);
        final Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(new Color(0, 0, 0, 0));
        graphics.fillRect(0, 0, rectangle.width, rectangle.height);
        final Graphics create = graphics.create(-rectangle.x, -rectangle.y, rectangle.width, rectangle.height);
        create.setClip(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        if (this.stage != null) {
            this.stage.setStageOffset();
            this.stage.paint(create);
        }
        if (this.penTrails != null) {
            create.drawImage(this.penTrails, 0, 0, 482, 387, null);
        }
        for (int i = this.sprites.length - 1; i >= 0; --i) {
            final Drawable drawable = (Drawable)this.sprites[i];
            if (drawable != sprite && drawable.isShowing() && rectangle.intersects(drawable.rect())) {
                drawable.paint(create);
            }
        }
        create.dispose();
        return bufferedImage;
    }
    
    void drawRect(final int n, final int n2, final int n3, final int n4) {
        final Graphics graphics = this.offscreen.getGraphics();
        graphics.setColor(PlayerCanvas.BLACK);
        graphics.fillRect(n, n2, n3, n4);
        graphics.dispose();
        this.repaint();
    }
    
    void startLoading() {
        this.isLoading = true;
        this.loadingFraction = 0.0;
        this.redraw_all();
    }
    
    void stopLoading() {
        this.loadingFraction = 1.0;
        this.redraw_all();
        this.loadingFraction = 0.0;
        this.isLoading = false;
    }
    
    void loadingProgress(final double loadingFraction) {
        this.loadingFraction = loadingFraction;
        this.redraw_all();
    }
    
    boolean logoIsRunning() {
        return this.lc.logoThread != null;
    }
    
    void updatePenTrails() {
        for (int i = this.sprites.length - 1; i >= 0; --i) {
            if (this.sprites[i] instanceof Sprite) {
                final Sprite sprite = (Sprite)this.sprites[i];
                if (sprite.penDown) {
                    this.updatePenTrailForSprite(sprite);
                }
            }
        }
    }
    
    void updatePenTrailForSprite(final Sprite sprite) {
        if (this.penTrails == null) {
            this.createPenTrails();
        }
        final int n = 241 + (int)sprite.x;
        final int n2 = 206 - (int)sprite.y;
        if (sprite.lastPenX == -1000000.0) {
            sprite.lastPenX = n;
            sprite.lastPenY = n2;
        }
        else if (sprite.lastPenX == n && sprite.lastPenY == n2) {
            return;
        }
        final Graphics2D graphics = this.penTrails.createGraphics();
        graphics.setColor(sprite.penColor);
        graphics.setStroke(new BasicStroke(sprite.penSize, 1, 1));
        graphics.drawLine(sprite.lastPenX, sprite.lastPenY, n, n2);
        graphics.dispose();
        final Rectangle rectangle = new Rectangle(sprite.lastPenX, sprite.lastPenY, 0, 0);
        rectangle.add(n, n2);
        rectangle.grow(sprite.penSize, sprite.penSize);
        this.inval(rectangle);
        sprite.lastPenX = n;
        sprite.lastPenY = n2;
    }
    
    void stampCostume(final Sprite sprite) {
        if (this.penTrails == null) {
            this.createPenTrails();
        }
        final Graphics2D graphics = this.penTrails.createGraphics();
        if (sprite.filterChanged) {
            sprite.applyFilters();
        }
        graphics.drawImage(sprite.outImage(), sprite.screenX(), sprite.screenY(), null);
        graphics.dispose();
        sprite.inval();
    }
    
    void createPenTrails() {
        (this.penTrails = new BufferedImage(482, 387, 2)).getRaster();
    }
    
    void clearPenTrails() {
        if (this.penTrails != null) {
            this.penTrails.flush();
        }
        this.penTrails = null;
        this.inval(new Rectangle(0, 0, 482, 387));
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.requestFocus();
        this.mouseDragOrMove(mouseEvent);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.updateMouseXY(mouseEvent);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.mouseDown(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.mouseUp(mouseEvent);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.updateMouseXY(mouseEvent);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.mouseDragOrMove(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.mouseDragOrMove(mouseEvent);
    }
    
    void mouseDown(final MouseEvent mouseEvent) {
        this.updateMouseXY(mouseEvent);
        this.requestFocus();
        if (this.inGoButton(mouseEvent)) {
            this.doStopButton();
            LogoCommandRunner.startLogoThread("greenflag", this.lc);
            return;
        }
        if (this.inStopButton(mouseEvent)) {
            this.doStopButton();
            LogoCommandRunner.startLogoThread("interact", this.lc);
            return;
        }
        this.mouseIsDown = true;
        this.mouseDownX = mouseEvent.getX();
        this.mouseDownY = mouseEvent.getY();
        this.mouseDragTarget = this.findDragTarget(mouseEvent.getX(), mouseEvent.getY());
        final boolean b = false;
        this.mouseDragYOffset = (b ? 1 : 0);
        this.mouseDragXOffset = (b ? 1 : 0);
        this.reportClickOnMouseUp = true;
        if (this.mouseDragTarget instanceof Sprite) {
            final Sprite sprite = (Sprite)this.mouseDragTarget;
            if (sprite.isDraggable) {
                this.mouseDragXOffset = sprite.screenX() - mouseEvent.getX();
                this.mouseDragYOffset = sprite.screenY() - mouseEvent.getY();
                this.moveSpriteToFront(sprite);
            }
            else {
                this.mouseDragTarget = null;
            }
        }
        if (this.mouseDragTarget instanceof ListWatcher) {
            ((ListWatcher)this.mouseDragTarget).mouseDown(mouseEvent.getX(), mouseEvent.getY());
        }
        if (this.askPrompt != null && this.askPrompt.mouseDown(mouseEvent.getX(), mouseEvent.getY(), this)) {
            return;
        }
        if (this.mouseDragTarget == null) {
            this.reportClick();
        }
    }
    
    void mouseDragOrMove(final MouseEvent mouseEvent) {
        this.updateMouseXY(mouseEvent);
        if (mouseEvent.getX() != this.mouseDownX || mouseEvent.getY() != this.mouseDownY) {
            this.reportClickOnMouseUp = false;
        }
        if (this.mouseDragTarget != null) {
            this.mouseDragTarget.dragTo(mouseEvent.getX() + this.mouseDragXOffset, mouseEvent.getY() + this.mouseDragYOffset);
        }
        else {
            final boolean overGoButton = this.overGoButton;
            final boolean overStopButton = this.overStopButton;
            this.overGoButton = this.inGoButton(mouseEvent);
            this.overStopButton = this.inStopButton(mouseEvent);
            if (overGoButton != this.overGoButton || overStopButton != this.overStopButton) {
                this.inval(new Rectangle(0, 0, 482, 26));
                this.redraw_invalid();
            }
        }
    }
    
    void mouseUp(final MouseEvent mouseEvent) {
        this.updateMouseXY(mouseEvent);
        if (this.reportClickOnMouseUp) {
            if (this.mouseDragTarget instanceof Watcher) {
                ((Watcher)this.mouseDragTarget).click(mouseEvent.getX(), mouseEvent.getY());
            }
            else {
                this.reportClick();
            }
        }
        this.mouseDragTarget = null;
        this.mouseIsDown = false;
    }
    
    void reportClick() {
        this.mouseclicks.addElement(new Object[] { new Double(this.mouseDownX - 241), new Double(206 - this.mouseDownY) });
        this.reportClickOnMouseUp = false;
    }
    
    void updateMouseXY(final MouseEvent mouseEvent) {
        this.mouseX = mouseEvent.getX() - 241;
        this.mouseY = 206 - mouseEvent.getY();
    }
    
    boolean inGoButton(final MouseEvent mouseEvent) {
        if (mouseEvent.getY() >= 26) {
            return false;
        }
        final int x = mouseEvent.getX();
        return x >= 418 && x <= 418 + Skin.goButton.getWidth(null);
    }
    
    boolean inStopButton(final MouseEvent mouseEvent) {
        if (mouseEvent.getY() >= 26) {
            return false;
        }
        final int x = mouseEvent.getX();
        return x >= 451 && x <= 451 + Skin.stopButton.getWidth(null);
    }
    
    void doStopButton() {
        SoundPlayer.stopSoundsForApplet(this.lc);
        LogoCommandRunner.stopLogoThread(this.lc);
        new LogoCommandRunner("stopAll", this.lc, true).run();
        this.clearkeys();
        this.mouseclicks = new Vector();
        this.mouseIsDown = false;
        this.mouseDragTarget = null;
        this.redraw_all();
    }
    
    Drawable findDragTarget(final int n, final int n2) {
        for (int i = 0; i < this.sprites.length; ++i) {
            if (this.sprites[i] instanceof Watcher) {
                final Watcher watcher = (Watcher)this.sprites[i];
                if (watcher.inSlider(n, n2)) {
                    return watcher;
                }
            }
            if (this.sprites[i] instanceof ListWatcher) {
                final ListWatcher listWatcher = (ListWatcher)this.sprites[i];
                if (listWatcher.inScrollbar(n, n2)) {
                    return listWatcher;
                }
            }
            if (this.sprites[i] instanceof Sprite) {
                final Sprite sprite = (Sprite)this.sprites[i];
                if (sprite.isShowing() && sprite.containsPoint(n, n2)) {
                    return sprite;
                }
            }
        }
        return null;
    }
    
    void moveSpriteToFront(final Sprite sprite) {
        int n = -1;
        for (int i = 0; i < this.sprites.length; ++i) {
            if (this.sprites[i] == sprite) {
                n = i;
            }
        }
        if (n < 0) {
            return;
        }
        final Object o = this.sprites[n];
        for (int j = n; j > 0; --j) {
            this.sprites[j] = this.sprites[j - 1];
        }
        this.sprites[0] = o;
        sprite.inval();
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final int keyCode = this.keyCodeFor(keyEvent);
        this.keydown[keyCode] = true;
        if (keyCode == 10 || (keyCode >= 28 && keyCode <= 31)) {
            this.keystrokes.addElement(new Double(keyCode));
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        this.keydown[this.keyCodeFor(keyEvent)] = false;
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        int keyChar = keyEvent.getKeyChar();
        if (this.askPrompt != null) {
            this.askPrompt.handleKeystroke(keyChar, this);
            return;
        }
        if (keyChar >= 65 && keyChar <= 90) {
            keyChar += 32;
        }
        this.keystrokes.addElement(new Double(keyChar));
    }
    
    int keyCodeFor(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        if (keyCode == 10) {
            return 10;
        }
        if (keyCode == 37) {
            return 28;
        }
        if (keyCode == 38) {
            return 30;
        }
        if (keyCode == 39) {
            return 29;
        }
        if (keyCode == 40) {
            return 31;
        }
        if (keyCode >= 65 && keyCode <= 90) {
            return keyCode + 32;
        }
        return Math.min(keyCode, 255);
    }
    
    void clearkeys() {
        for (int i = 0; i < this.keydown.length; ++i) {
            this.keydown[i] = false;
        }
        this.keystrokes = new Vector();
    }
    
    int soundLevel() {
        if (PlayerCanvas.soundInputLine == null) {
            return 0;
        }
        final int available = PlayerCanvas.soundInputLine.available();
        if (available == 0) {
            return this.soundLevel;
        }
        final int read = PlayerCanvas.soundInputLine.read(this.soundInputBuf, 0, available);
        int n = 0;
        for (int i = 0; i < read / 2; ++i) {
            int n2 = (this.soundInputBuf[2 * i] << 8) + this.soundInputBuf[2 * i + 1];
            if (n2 >= 32768) {
                n2 = 65536 - n2;
            }
            if (n2 > n) {
                n = n2;
            }
        }
        return this.soundLevel = n / 327;
    }
    
    void openSoundInput() {
        if (PlayerCanvas.soundInputLine != null) {
            PlayerCanvas.soundInputLine.close();
        }
        final AudioFormat audioFormat = new AudioFormat(44100.0f, 16, 1, true, true);
        final DataLine.Info info = new DataLine.Info((PlayerCanvas.class$javax$sound$sampled$TargetDataLine == null) ? (PlayerCanvas.class$javax$sound$sampled$TargetDataLine = class$("javax.sound.sampled.TargetDataLine")) : PlayerCanvas.class$javax$sound$sampled$TargetDataLine, audioFormat);
        try {
            (PlayerCanvas.soundInputLine = (TargetDataLine)AudioSystem.getLine(info)).open(audioFormat, 50000);
            PlayerCanvas.soundInputLine.start();
        }
        catch (LineUnavailableException ex) {
            PlayerCanvas.soundInputLine = null;
        }
    }
    
    void showAskPrompt(final String s) {
        this.askPrompt = new AskPrompter(s);
        this.invalAll();
    }
    
    void hideAskPrompt() {
        if (this.askPrompt != null) {
            this.lastAnswer = this.askPrompt.answerString;
        }
        this.askPrompt = null;
        this.invalAll();
    }
    
    boolean askPromptShowing() {
        return this.askPrompt != null;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        BLACK = new Color(0, 0, 0);
        WHITE = new Color(255, 255, 255);
    }
}
