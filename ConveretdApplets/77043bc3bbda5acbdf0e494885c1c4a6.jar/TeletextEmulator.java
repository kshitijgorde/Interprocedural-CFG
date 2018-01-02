import java.awt.Dimension;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.Cursor;
import teletext.TeletextFont;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import teletext.TeletextPage;
import java.applet.AppletContext;
import teletext.TeletextPageImage;
import java.util.Vector;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class TeletextEmulator extends Canvas
{
    public final int SIZE_THRESHOLD = 64;
    Vector pageHistory;
    Vector teletextPages;
    int rollingNumber;
    boolean pageFound;
    boolean hasFocus;
    TeletextPageImage teletextPageImage;
    StringBuffer inputBuffer;
    PageLoaderThread pageLoaderThread;
    AppletContext appletContext;
    
    public TeletextEmulator(final String s, final AppletContext appletContext) {
        this.hasFocus = false;
        this.pageHistory = new Vector(10, 5);
        (this.teletextPages = new Vector(10, 5)).addElement(new TeletextPage());
        this.addFocusListener(new FocusAdapter() {
            public void focusGained(final FocusEvent focusEvent) {
                TeletextEmulator.this.hasFocus = true;
                TeletextEmulator.this.repaint();
            }
            
            public void focusLost(final FocusEvent focusEvent) {
                TeletextEmulator.this.hasFocus = false;
                TeletextEmulator.this.repaint();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(final MouseEvent mouseEvent) {
                final int n = mouseEvent.getX() / TeletextFont.getPixelWidth();
                int n2 = mouseEvent.getY() / TeletextFont.getPixelHeight();
                if (n2 > 2 && TeletextEmulator.this.teletextPageImage.getRenderBuffer().getDoubleHeightFlag(n2 - 1)) {
                    --n2;
                }
                if (TeletextEmulator.this.getTeletextPage().numberHitTest(n, n2) > 0) {
                    TeletextEmulator.this.setCursor(new Cursor(12));
                }
                else if (TeletextEmulator.this.getTeletextPage().siteHitTest(n, n2) != null && TeletextEmulator.this.appletContext != null) {
                    TeletextEmulator.this.setCursor(new Cursor(12));
                }
                else {
                    TeletextEmulator.this.setCursor(new Cursor(0));
                }
            }
        });
        this.rollingNumber = 0;
        this.pageFound = false;
        this.teletextPageImage = new TeletextPageImage(this.getTeletextPage());
        this.inputBuffer = new StringBuffer("100");
        this.pageLoaderThread = new PageLoaderThread(this, s);
        this.appletContext = appletContext;
        this.pageLoaderThread.run();
        this.pageHistory.addElement(new Integer(100));
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                TeletextEmulator.this.processKeyPressedEvent(keyEvent);
            }
            
            public void keyTyped(final KeyEvent keyEvent) {
                TeletextEmulator.this.processKeyTypedEvent(keyEvent);
            }
        });
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                final int n = mouseEvent.getX() / TeletextFont.getPixelWidth();
                int n2 = mouseEvent.getY() / TeletextFont.getPixelHeight();
                if (TeletextEmulator.this.teletextPageImage.getRenderBuffer().getDoubleHeightFlag(n2 - 1)) {
                    --n2;
                }
                final int numberHitTest = TeletextEmulator.this.getTeletextPage().numberHitTest(n, n2);
                if (numberHitTest > 0) {
                    TeletextEmulator.this.seekPage(numberHitTest);
                }
                final String siteHitTest = TeletextEmulator.this.getTeletextPage().siteHitTest(n, n2);
                if (siteHitTest != null && TeletextEmulator.this.appletContext != null) {
                    try {
                        TeletextEmulator.this.appletContext.showDocument(new URL("http://" + siteHitTest));
                    }
                    catch (MalformedURLException ex) {}
                }
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
            }
            
            public void mouseClicked(final MouseEvent mouseEvent) {
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
            }
        });
    }
    
    public TeletextPage getTeletextPage() {
        return this.teletextPages.elementAt(this.rollingNumber);
    }
    
    public Vector getTeletextPages() {
        return this.teletextPages;
    }
    
    public void setRollingNumber(final int rollingNumber) {
        this.rollingNumber = rollingNumber;
    }
    
    public int getRollingNumber() {
        return this.rollingNumber;
    }
    
    public void setPageFound(final boolean pageFound) {
        this.pageFound = pageFound;
    }
    
    public boolean getPageFound() {
        return this.pageFound;
    }
    
    public int getCurrentPageNumber() {
        if (this.inputBuffer.length() < 3) {
            return 0;
        }
        return Integer.parseInt(this.inputBuffer.toString());
    }
    
    public void renderLine(final int n) {
        this.teletextPageImage.renderLine(n);
        this.teletextPageImage.updateImage(n);
    }
    
    public void paintHeader() {
        final StringBuffer sb = new StringBuffer();
        sb.append('\u0002');
        sb.append("P");
        if (this.inputBuffer.length() < 3) {
            sb.append((Object)this.inputBuffer);
            for (int i = this.inputBuffer.length(); i < 3; ++i) {
                sb.append('-');
            }
        }
        else {
            sb.append((Object)this.inputBuffer);
        }
        this.getTeletextPage().fillRow(0, ' ');
        if (!this.pageFound) {
            this.getTeletextPage().fill(' ');
            sb.append('\u0003');
            sb.append("PAGINA NON IN ONDA");
            sb.append('\u0007');
        }
        else {
            sb.append('\u0003');
            sb.append("Pagina " + (this.rollingNumber + 1) + "/" + this.teletextPages.size());
            sb.append('\u0007');
        }
        this.getTeletextPage().print(0, 0, sb.toString());
    }
    
    public synchronized void render() {
        this.paintHeader();
        this.teletextPageImage.setPage(this.getTeletextPage());
        this.teletextPageImage.render();
        this.teletextPageImage.updateImage();
    }
    
    public void update(final Graphics graphics) {
        this.implementPaint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.implementPaint(graphics);
    }
    
    public void implementPaint(final Graphics graphics) {
        graphics.drawImage(this.teletextPageImage.getImage(), 0, 0, null);
        if (this.hasFocus) {
            graphics.setColor(Color.white);
            graphics.drawRect(0, 0, this.teletextPageImage.getPixelWidth() - 1, this.teletextPageImage.getPixelHeight() - 1);
            graphics.setColor(Color.green);
            graphics.fillOval(this.teletextPageImage.getPixelWidth() - 10, 5, 5, 5);
        }
    }
    
    public void implementPaint(final Graphics graphics, final int n) {
        final int[] characterBoundingBox = this.teletextPageImage.getRenderBuffer().getCharacterBoundingBox(0, n, 39, n);
        graphics.drawImage(this.teletextPageImage.getImage(), characterBoundingBox[0], characterBoundingBox[1], characterBoundingBox[2], characterBoundingBox[3], characterBoundingBox[0], characterBoundingBox[1], characterBoundingBox[2], characterBoundingBox[3], null);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(40 * TeletextFont.getPixelWidth(), 24 * TeletextFont.getPixelHeight());
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public Dimension getMaximumSize() {
        return this.getPreferredSize();
    }
    
    public void processKeyPressedEvent(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 33: {
                this.decrementRollingNumber();
                break;
            }
            case 34: {
                this.incrementRollingNumber();
                break;
            }
        }
    }
    
    public void processKeyTypedEvent(final KeyEvent keyEvent) {
        final char keyChar = keyEvent.getKeyChar();
        if (this.inputBuffer.length() == 3) {
            this.inputBuffer.setLength(0);
        }
        if (keyChar >= '0' && keyChar <= '9') {
            this.inputBuffer.append(keyEvent.getKeyChar());
            if (this.inputBuffer.length() == 1 && (this.inputBuffer.charAt(0) < '1' || this.inputBuffer.charAt(0) > '8')) {
                this.inputBuffer.setLength(0);
            }
            if (this.inputBuffer.length() == 3) {
                final int currentPageNumber = this.getCurrentPageNumber();
                if (currentPageNumber < 100 || currentPageNumber > 899) {
                    this.inputBuffer.setLength(0);
                }
                else {
                    this.seekPage(this.getCurrentPageNumber());
                }
            }
        }
        else {
            this.inputBuffer.setLength(0);
        }
        this.render();
        this.repaint();
    }
    
    public void seekPage(final int n) {
        if (this.pageHistory.size() == 64) {
            this.pageHistory.removeElementAt(0);
        }
        this.pageHistory.addElement(new Integer(n));
        this.inputBuffer.setLength(0);
        this.inputBuffer.append(Integer.toString(n));
        this.pageLoaderThread.setPageNumber(this.getCurrentPageNumber());
        new Thread(this.pageLoaderThread).start();
    }
    
    public void back() {
        if (this.pageHistory.size() > 1) {
            final Integer n = this.pageHistory.lastElement();
            this.seekPage(n);
            this.pageHistory.removeElement(n);
        }
    }
    
    public void incrementPageNumber() {
        if (this.getCurrentPageNumber() < 899) {
            this.seekPage(this.getCurrentPageNumber() + 1);
            this.render();
            this.repaint();
        }
    }
    
    public void decrementPageNumber() {
        if (this.getCurrentPageNumber() > 100) {
            this.seekPage(this.getCurrentPageNumber() - 1);
            this.render();
            this.repaint();
        }
    }
    
    void incrementRollingNumber() {
        if (this.rollingNumber < this.teletextPages.size() - 1) {
            ++this.rollingNumber;
            this.render();
            this.repaint();
        }
    }
    
    void decrementRollingNumber() {
        if (this.rollingNumber > 0) {
            --this.rollingNumber;
            this.render();
            this.repaint();
        }
    }
}
