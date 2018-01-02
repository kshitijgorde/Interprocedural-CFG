import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class DrawingCanvas extends Canvas implements MouseListener, MouseMotionListener
{
    private static final int MAX_TRIES = 5;
    private static final int HELP_TIME_OUT = 1500;
    private static final int ANIMATION_DELAY = 50;
    private DrawableTextBox helpBox;
    private int helpCount;
    private Vector drawableObjects;
    private Vector clickableObjects;
    private Vector selectableObjects;
    private Vector highlighted;
    private Enumeration objectList;
    private Image canvasImage;
    private Graphics canvasGraphics;
    private int tries;
    private int selection;
    private Position mousePosition;
    private boolean clickAll;
    private boolean awaitButton;
    private boolean pressed;
    private boolean moving;
    private boolean answerShown;
    private boolean awaitingClick;
    
    public DrawingCanvas(final Color canvasColor) {
        this.helpCount = 0;
        this.drawableObjects = new Vector();
        this.highlighted = new Vector();
        this.tries = 1;
        this.awaitButton = false;
        this.pressed = false;
        this.moving = false;
        this.answerShown = false;
        this.awaitingClick = false;
        this.setSize(546, 270);
        this.setFont(new Font("Dialog", 0, 12));
        this.setBackground(canvasColor);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public synchronized void acceptClick(final Clickable object) {
        this.repaint();
        if (this.clickableObjects == null) {
            this.clickableObjects = new Vector();
        }
        this.clickableObjects.addElement(object);
    }
    
    public synchronized void acceptSelection(final Selectable object) {
        if (this.selectableObjects == null) {
            this.selectableObjects = new Vector();
        }
        this.selectableObjects.addElement(object);
    }
    
    public void add(final Drawable object) {
        this.drawableObjects.addElement(object);
    }
    
    public void addHelp(final DrawableTextBox helpBox) {
        this.helpBox = helpBox;
        this.helpCount = 1500;
    }
    
    public boolean answerWasShown() {
        return this.answerShown;
    }
    
    public boolean awaitClick(final String awaitMessage, final boolean clickAll, final boolean buttonCorrect) {
        this.repaint();
        this.clickAll = clickAll;
        this.awaitButton = buttonCorrect;
        this.answerShown = false;
        this.awaitingClick = true;
        if (!Screen.controls.isAborted()) {
            Screen.prompt.displayPrompt(awaitMessage);
        }
        Screen.interaction.displayMessage("");
        synchronized (this) {
            this.tries = 1;
        }
        int timer = 0;
        while (!Screen.controls.isAborted()) {
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
            synchronized (this) {
                if (this.clickableObjects == null && !this.awaitButton) {
                    Screen.progress.oneMoreRight(Screen.thread.button());
                    Screen.interaction.displayMessage("Very Good, That's Correct");
                    Screen.prompt.displayPrompt("");
                    try {
                        Thread.sleep(500L);
                    }
                    catch (InterruptedException ex2) {}
                    this.awaitingClick = false;
                    // monitorexit(this)
                    return true;
                }
                if (this.quitTrying(this.clickableObjects, timer)) {
                    // monitorexit(this)
                    break;
                }
            }
            ++timer;
        }
        this.clickableObjects = null;
        return this.awaitingClick = false;
    }
    
    public void awaitMovingCompletion() {
        if (Screen.controls.isAborted()) {
            return;
        }
        while (true) {
            Label_0044: {
                synchronized (this) {
                    this.moving = true;
                    break Label_0044;
                }
                this.repaint();
                try {
                    Thread.sleep(50L);
                }
                catch (InterruptedException ex) {}
            }
            if (!this.moving) {
                return;
            }
            continue;
        }
    }
    
    public int awaitSelection(final String awaitMessage) {
        if (!Screen.controls.isAborted()) {
            Screen.prompt.displayPrompt(awaitMessage);
        }
        Screen.interaction.displayMessage("");
        synchronized (this) {
            this.tries = 1;
        }
        int timer = 0;
        while (!Screen.controls.isAborted()) {
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
            synchronized (this) {
                if (this.selectableObjects == null) {
                    // monitorexit(this)
                    return this.selection;
                }
                if (this.quitTrying(this.selectableObjects, timer)) {
                    this.selectableObjects = null;
                    // monitorexit(this)
                    return -1;
                }
            }
            ++timer;
        }
        this.selectableObjects = null;
        return -1;
    }
    
    public synchronized void buttonClicked(final boolean correct) {
        if (correct) {
            this.awaitButton = false;
        }
        else {
            this.invalidClick();
        }
    }
    
    private void checkClickables(final int x, final int y) {
        if (Screen.panel.isInteractive() && this.clickableObjects != null && !this.clickableObjects.isEmpty()) {
            this.objectList = this.clickableObjects.elements();
            while (this.objectList.hasMoreElements()) {
                final Clickable object = this.objectList.nextElement();
                if (object.inside(new Position(x, y))) {
                    object.show();
                    this.clickableObjects.removeElement(object);
                    if (this.clickAll && !this.clickableObjects.isEmpty()) {
                        Screen.prompt.displayPrompt("OK, Click The Other One");
                    }
                    else {
                        this.clickableObjects = null;
                    }
                    return;
                }
            }
            this.invalidClick();
        }
        else if (Screen.panel.isInteractive() && this.awaitButton) {
            this.invalidClick();
        }
    }
    
    private void checkSelectables(final int x, final int y) {
        if ((Screen.panel.userPicks() || Screen.panel.isInteractive()) && this.selectableObjects != null && !this.selectableObjects.isEmpty()) {
            this.objectList = this.selectableObjects.elements();
            while (this.objectList.hasMoreElements()) {
                final Selectable object = this.objectList.nextElement();
                if ((this.selection = object.select(new Position(x, y))) >= 0) {
                    this.selectableObjects = null;
                    return;
                }
            }
            this.invalidClick();
        }
    }
    
    public void clear() {
        for (int index = 0; index < this.highlighted.size(); ++index) {
            final Drawable object = this.highlighted.elementAt(index);
            this.remove(object);
        }
        this.highlighted = new Vector();
    }
    
    public Position getMousePosition() {
        return this.mousePosition;
    }
    
    public void highlight(final Drawable object, final Color color, final boolean checkWho) {
        object.setColor(color);
        if (checkWho && Screen.panel.isInteractive()) {
            this.acceptClick(object);
        }
        else {
            object.show();
        }
        this.highlighted.addElement(object);
        this.add(object);
    }
    
    private void invalidClick() {
        Screen.progress.oneMoreWrong(Screen.thread.button());
        if (this.tries == 1) {
            Screen.interaction.displayMessage("Not Correct, Try Again");
        }
        else if (this.tries == 2) {
            Screen.interaction.displayMessage("Still Wrong, Try Again");
        }
        else if (this.tries == 3) {
            Screen.interaction.displayMessage("OK, Try One More Time");
        }
        ++this.tries;
    }
    
    public void mouseClicked(final MouseEvent event) {
    }
    
    public void mouseDragged(final MouseEvent event) {
    }
    
    public void mouseEntered(final MouseEvent event) {
    }
    
    public void mouseExited(final MouseEvent event) {
        this.mousePosition = null;
    }
    
    public void mouseMoved(final MouseEvent event) {
        this.mousePosition = new Position(event.getX(), event.getY());
        this.repaint();
    }
    
    public synchronized void mousePressed(final MouseEvent event) {
        this.pressed = true;
    }
    
    public synchronized void mouseReleased(final MouseEvent event) {
        if (this.pressed) {
            this.pressed = false;
            this.checkClickables(event.getX(), event.getY());
            this.checkSelectables(event.getX(), event.getY());
        }
    }
    
    public synchronized void paint(final Graphics g) {
        if (Screen.controls.isAborted()) {
            return;
        }
        if (this.canvasImage == null) {
            this.canvasImage = this.createImage(546, 270);
            this.canvasGraphics = this.canvasImage.getGraphics();
        }
        this.canvasGraphics.setColor(this.getBackground());
        this.canvasGraphics.fillRect(0, 0, 546, 270);
        this.canvasGraphics.setColor(this.getForeground());
        boolean movingObjects = false;
        this.objectList = this.drawableObjects.elements();
        while (this.objectList.hasMoreElements()) {
            final Drawable object = this.objectList.nextElement();
            movingObjects |= object.drawObject(this.canvasGraphics);
        }
        if (Screen.screen.isHelpOn() && this.helpCount > 0) {
            this.helpBox.draw(this.canvasGraphics);
            --this.helpCount;
        }
        g.drawImage(this.canvasImage, 0, 0, null);
        this.moving = movingObjects;
    }
    
    private boolean quitTrying(final Vector objects, final int timer) {
        if (this.tries >= 5) {
            Screen.interaction.displayMessage("OK, Let Me Show You");
        }
        else {
            if (timer < 50) {
                return false;
            }
            Screen.interaction.displayMessage("Time's Up, I'll Show You");
        }
        Screen.prompt.displayPrompt("");
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex) {}
        return this.answerShown = true;
    }
    
    public void remove(final Drawable object) {
        this.drawableObjects.removeElement(object);
    }
    
    public void removeHelp(final DrawableTextBox helpBox) {
        if (this.helpBox == helpBox) {
            this.helpCount = 0;
        }
    }
    
    public boolean showClickables() {
        return this.awaitingClick;
    }
    
    public synchronized void showMe() {
        this.tries = 5;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
