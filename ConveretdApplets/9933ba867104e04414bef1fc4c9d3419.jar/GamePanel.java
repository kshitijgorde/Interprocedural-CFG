import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Paint;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class GamePanel extends JPanel
{
    public static final int MAXWIDTH = 50;
    public static final int MAXHEIGHT = 50;
    private final int RIGHT_MARGIN = 26;
    private final int BOTTOM_MARGIN = 26;
    private final int SQUAREWIDTH = 23;
    private final int SQUAREHEIGHT = 23;
    private String[][] grid;
    private boolean[][] selected;
    private int[] begSquare;
    private int[] endSquare;
    private boolean[] wordSelected;
    private int width;
    private int height;
    private int left;
    private int top;
    private int start_x;
    private int start_y;
    private int last_x;
    private int last_y;
    private boolean mouseDown;
    private GameHandler eventHandler;
    private Color letterColor;
    private Color selectedColor;
    private Color circleColor;
    private Color backColor;
    private boolean gameOver;
    public BufferedImage upImg;
    public BufferedImage downImg;
    public BufferedImage leftImg;
    public BufferedImage rightImg;
    
    public GamePanel(final Color backColor) {
        final boolean b = false;
        this.height = (b ? 1 : 0);
        this.width = (b ? 1 : 0);
        this.mouseDown = false;
        this.grid = new String[50][50];
        this.selected = new boolean[50][50];
        this.begSquare = null;
        this.endSquare = null;
        this.backColor = backColor;
        final boolean b2 = false;
        this.top = (b2 ? 1 : 0);
        this.left = (b2 ? 1 : 0);
        this.letterColor = Color.BLACK;
        this.selectedColor = Color.RED;
        this.circleColor = Color.BLUE;
        this.gameOver = false;
        this.addMouseListener(new MouseHandler());
        this.addMouseMotionListener(new MouseMotionHandler());
    }
    
    public void paint(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Rectangle visibleRect = this.getVisibleRect();
        graphics2D.setPaint(this.backColor);
        graphics2D.draw(visibleRect);
        graphics2D.fill(visibleRect);
        this.drawPuzzle(graphics2D);
        for (int i = 0; i < this.wordSelected.length; ++i) {
            if (this.wordSelected[i]) {
                if (this.begSquare[i] < this.endSquare[i]) {
                    this.circleWord(graphics2D, this.begSquare[i] - 1, this.endSquare[i] - 1);
                }
                else {
                    this.circleWord(graphics2D, this.endSquare[i] - 1, this.begSquare[i] - 1);
                }
            }
        }
        this.drawHScroll(graphics2D);
        this.drawVScroll(graphics2D);
    }
    
    private void drawHScroll() {
        this.drawHScroll((Graphics2D)this.getGraphics());
    }
    
    private void drawHScroll(final Graphics2D graphics2D) {
        final Rectangle visibleRect = this.getVisibleRect();
        final Rectangle rectangle = new Rectangle();
        rectangle.x = 0;
        rectangle.width = visibleRect.width;
        rectangle.y = visibleRect.height - 26;
        rectangle.height = 26;
        graphics2D.setPaint(this.backColor);
        graphics2D.draw(rectangle);
        graphics2D.fill(rectangle);
        if (this.left > 0) {
            graphics2D.drawImage(this.leftImg, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 2.0f, visibleRect.height - 26), null);
        }
        if ((this.width - this.left) * 23 > visibleRect.width - 26) {
            graphics2D.drawImage(this.rightImg, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, visibleRect.width - 26 - this.rightImg.getWidth() - 2, visibleRect.height - 26), null);
        }
    }
    
    private void drawVScroll() {
        this.drawVScroll((Graphics2D)this.getGraphics());
    }
    
    private void drawVScroll(final Graphics2D graphics2D) {
        final Rectangle visibleRect = this.getVisibleRect();
        final Rectangle rectangle = new Rectangle();
        rectangle.x = visibleRect.width - 26;
        rectangle.width = 26;
        rectangle.y = 0;
        rectangle.height = visibleRect.height;
        graphics2D.setPaint(this.backColor);
        graphics2D.draw(rectangle);
        graphics2D.fill(rectangle);
        if (this.top > 0) {
            graphics2D.drawImage(this.upImg, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, visibleRect.width - 26 + 2, 4.0f), null);
        }
        if ((this.height - this.top) * 23 > visibleRect.height - 26) {
            graphics2D.drawImage(this.downImg, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, visibleRect.width - 26 + 2, visibleRect.height - this.downImg.getHeight() - 4 - 26), null);
        }
    }
    
    private void drawPuzzle(final Graphics2D graphics2D) {
        for (int i = this.top; i < this.height; ++i) {
            for (int j = this.left; j < this.width; ++j) {
                if (this.grid[i][j].compareTo(" ") != 0 && this.grid[i][j].compareTo("\u0000") != 0) {
                    this.drawLetter(graphics2D, j, i);
                }
            }
        }
    }
    
    private void drawLetter(final Graphics2D graphics2D, final int n, final int n2) {
        final Font font = graphics2D.getFont();
        graphics2D.setFont(new Font("Arial", 1, 15));
        final int n3 = 2 + (n - this.left) * 23 + (23 - graphics2D.getFontMetrics().charWidth((new char[2])[0])) / 2;
        final int n4 = (n2 - this.top) * 23 + graphics2D.getFontMetrics().getHeight() + (23 - graphics2D.getFontMetrics().getHeight()) / 2;
        if (this.selected[n2][n]) {
            graphics2D.setPaint(this.selectedColor);
        }
        else {
            graphics2D.setPaint(this.letterColor);
        }
        final Rectangle visibleRect = this.getVisibleRect();
        if (n3 < visibleRect.width - 26 && n4 < visibleRect.height - 26) {
            graphics2D.drawString(this.grid[n2][n], n3, n4);
        }
        graphics2D.setFont(font);
    }
    
    private void drawLine(final Line2D.Float float1) {
        final Graphics2D graphics2D = (Graphics2D)this.getGraphics();
        graphics2D.setXORMode(Color.BLACK);
        graphics2D.setPaint(Color.GREEN);
        graphics2D.draw(float1);
    }
    
    public void setPuzzle(final byte[] array, final int[] begSquare, final int[] endSquare, final int width, final int height) {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                this.grid[i][j] = new String(new char[] { (char)(array[i * 50 + j] & 0xFF), '\0' }, 0, 1);
                this.selected[i][j] = false;
            }
        }
        this.width = width;
        this.height = height;
        this.begSquare = begSquare;
        this.endSquare = endSquare;
        this.wordSelected = new boolean[this.begSquare.length];
        for (int k = 0; k < this.wordSelected.length; ++k) {
            this.wordSelected[k] = false;
        }
        this.repaint();
    }
    
    public void registerEventHandler(final GameHandler eventHandler) {
        this.eventHandler = eventHandler;
    }
    
    private void highlightWord(final int wordIndex, final int n, final int n2) {
        final int n3 = n % 50;
        final int n4 = n / 50;
        final int n5 = n2 % 50;
        final int n6 = n2 / 50;
        final GameEvent gameEvent = new GameEvent();
        gameEvent.setID(2);
        gameEvent.setWordIndex(wordIndex);
        this.eventHandler.gameEvent(gameEvent);
        final int result = gameEvent.getResult();
        int i = 0;
        int n7 = n3;
        int n8 = n4;
        while (i < result) {
            this.selected[n8][n7] = true;
            this.drawLetter((Graphics2D)this.getGraphics(), n7, n8);
            if (n3 > n5) {
                --n7;
            }
            else if (n3 < n5) {
                ++n7;
            }
            if (n4 > n6) {
                --n8;
            }
            else if (n4 < n6) {
                ++n8;
            }
            ++i;
        }
    }
    
    private void circleWord(final int n, final int n2) {
        this.circleWord((Graphics2D)this.getGraphics(), n, n2);
    }
    
    private void circleWord(final Graphics2D graphics2D, final int n, final int n2) {
        final int n3 = n % 50 - this.left;
        final int n4 = n / 50 - this.top;
        final int n5 = n2 % 50 - this.left;
        final int n6 = n2 / 50 - this.top;
        final int n7 = 11;
        final int n8 = 11;
        int n9;
        if (n3 < n5) {
            if (n4 == n6) {
                n9 = 0;
            }
            else if (n4 < n6) {
                n9 = 7;
            }
            else {
                n9 = 1;
            }
        }
        else if (n3 > n5) {
            if (n4 == n6) {
                n9 = 4;
            }
            else if (n4 < n6) {
                n9 = 5;
            }
            else {
                n9 = 3;
            }
        }
        else if (n4 < n6) {
            n9 = 2;
        }
        else {
            n9 = 6;
        }
        int n10 = n3 * 23;
        n10 += 2;
        final int n11 = n4 * 23;
        int n12 = n5 * 23;
        n12 += 2;
        final int n13 = n6 * 23;
        graphics2D.setPaint(this.circleColor);
        switch (n9) {
            case 2: {
                graphics2D.draw(new Line2D.Float(n10, n11 + n7, n10, n13 + n7));
                graphics2D.draw(new Arc2D.Float(n10, n11, 23.0f, 23.0f, 0.0f, 180.0f, 0));
                graphics2D.draw(new Line2D.Float(n10 + 23, n11 + n7, n10 + 23, n13 + n7 + 1));
                graphics2D.draw(new Arc2D.Float(n10, n13, 23.0f, 23.0f, 180.0f, 180.0f, 0));
                break;
            }
            case 3: {
                graphics2D.draw(new Line2D.Float(n10 + n8, n11 + 23, n12, n13 + n7));
                graphics2D.draw(new Arc2D.Float(n12 + 1, n13, 23.0f, 23.0f, 1.0f, 90.0f, 0));
                graphics2D.draw(new Line2D.Float(n10 + 23, n11 + n7, n12 + n8, n13));
                graphics2D.draw(new Arc2D.Float(n10, n11, 23.0f, 23.0f, 181.0f, 90.0f, 0));
                break;
            }
            case 4: {
                graphics2D.draw(new Line2D.Float(n10 + n8, n11, n12 + n8, n11));
                graphics2D.draw(new Arc2D.Float(n10, n11, 23.0f, 23.0f, 90.0f, 180.0f, 0));
                graphics2D.draw(new Line2D.Float(n10 + n8, n11 + 23, n12 + n8, n11 + 23));
                graphics2D.draw(new Arc2D.Float(n12, n11, 23.0f, 23.0f, 270.0f, 180.0f, 0));
                break;
            }
            case 5: {
                graphics2D.draw(new Line2D.Float(n10 + n8, n11, n12, n13 + n7));
                graphics2D.draw(new Arc2D.Float(n10 + 1, n11, 23.0f, 23.0f, 1.0f, 90.0f, 0));
                graphics2D.draw(new Line2D.Float(n10 + 23, n11 + n7, n12 + n8, n13 + 23));
                graphics2D.draw(new Arc2D.Float(n12, n13, 23.0f, 23.0f, 181.0f, 90.0f, 0));
                break;
            }
            case 6: {
                graphics2D.draw(new Line2D.Float(n10, n11 + n7, n10, n13 + n7));
                graphics2D.draw(new Arc2D.Float(n10, n11, 23.0f, 23.0f, 0.0f, 180.0f, 0));
                graphics2D.draw(new Line2D.Float(n10 + 23, n11 + n7, n10 + 23, n13 + n7));
                graphics2D.draw(new Arc2D.Float(n10, n13, 23.0f, 23.0f, 180.0f, 180.0f, 0));
                break;
            }
            case 7: {
                graphics2D.draw(new Line2D.Float(n10, n11 + n7, n12 + n8, n13 + 23));
                graphics2D.draw(new Arc2D.Float(n10, n11, 23.0f, 23.0f, 89.0f, 90.0f, 0));
                graphics2D.draw(new Line2D.Float(n10 + n8, n11, n12 + 23, n13 + n7));
                graphics2D.draw(new Arc2D.Float(n12, n13, 23.0f, 23.0f, 269.0f, 90.0f, 0));
                break;
            }
            case 0: {
                graphics2D.draw(new Line2D.Float(n10 + n8, n11, n12 + n8, n11));
                graphics2D.draw(new Arc2D.Float(n10, n11, 23.0f, 23.0f, 90.0f, 180.0f, 0));
                graphics2D.draw(new Line2D.Float(n10 + n8, n11 + 23, n12 + n8, n11 + 23));
                graphics2D.draw(new Arc2D.Float(n12, n11, 23.0f, 23.0f, 270.0f, 180.0f, 0));
                break;
            }
            case 1: {
                graphics2D.draw(new Line2D.Float(n10, n11 + n7, n12 + n8, n13));
                graphics2D.draw(new Arc2D.Float(n12, n13, 23.0f, 23.0f, 89.0f, 90.0f, 0));
                graphics2D.draw(new Line2D.Float(n10 + n8, n11 + 23, n12 + 23, n13 + n7));
                graphics2D.draw(new Arc2D.Float(n10, n11, 23.0f, 23.0f, 269.0f, 90.0f, 0));
                break;
            }
        }
    }
    
    private void findWord(final int n, final int n2) {
        for (int i = 0; i < this.begSquare.length; ++i) {
            if ((n == this.begSquare[i] && n2 == this.endSquare[i]) || (n == this.endSquare[i] && n2 == this.begSquare[i])) {
                final GameEvent gameEvent = new GameEvent();
                gameEvent.setID(1);
                gameEvent.setWordIndex(i);
                this.eventHandler.gameEvent(gameEvent);
                this.wordSelected[i] = true;
                this.highlightWord(i, n - 1, n2 - 1);
                if (n < n2) {
                    this.circleWord(n - 1, n2 - 1);
                }
                else {
                    this.circleWord(n2 - 1, n - 1);
                }
                this.drawHScroll();
                this.drawVScroll();
                break;
            }
        }
    }
    
    public void setColors(final Color letterColor, final Color selectedColor, final Color circleColor) {
        if (letterColor != null) {
            this.letterColor = letterColor;
        }
        if (selectedColor != null) {
            this.selectedColor = selectedColor;
        }
        if (circleColor != null) {
            this.circleColor = circleColor;
        }
    }
    
    public void setGameOver(final boolean gameOver) {
        this.gameOver = gameOver;
    }
    
    private class MouseHandler extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            if (mouseEvent.getButton() == 1) {
                final Rectangle visibleRect = GamePanel.this.getVisibleRect();
                if (mouseEvent.getX() >= visibleRect.width - 26) {
                    if (GamePanel.this.top > 0 && mouseEvent.getY() >= 4 && mouseEvent.getY() <= GamePanel.this.upImg.getHeight() + 4) {
                        GamePanel.this.top--;
                        GamePanel.this.repaint(visibleRect);
                    }
                    else if ((GamePanel.this.height - GamePanel.this.top) * 23 > visibleRect.height - 26 && mouseEvent.getY() <= visibleRect.height - 26 && mouseEvent.getY() >= visibleRect.height - 26 - GamePanel.this.downImg.getHeight()) {
                        GamePanel.this.top++;
                        GamePanel.this.repaint(visibleRect);
                    }
                }
                else if (mouseEvent.getY() >= visibleRect.height - 26) {
                    if (GamePanel.this.left > 0 && mouseEvent.getX() >= 2 && mouseEvent.getX() <= 2 + GamePanel.this.leftImg.getWidth()) {
                        GamePanel.this.left--;
                        GamePanel.this.repaint(visibleRect);
                    }
                    else if ((GamePanel.this.width - GamePanel.this.left) * 23 > visibleRect.width - 26 && mouseEvent.getX() >= visibleRect.width - 26 - GamePanel.this.rightImg.getWidth() - 2 && mouseEvent.getX() <= visibleRect.width - 26) {
                        GamePanel.this.left++;
                        GamePanel.this.repaint(visibleRect);
                    }
                }
                else if (!GamePanel.this.gameOver) {
                    GamePanel.this.mouseDown = true;
                    GamePanel.this.start_x = (GamePanel.this.last_x = mouseEvent.getX());
                    GamePanel.this.start_y = (GamePanel.this.last_y = mouseEvent.getY());
                }
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            if (mouseEvent.getButton() == 1 && GamePanel.this.mouseDown) {
                GamePanel.this.mouseDown = false;
                final int n = (GamePanel.this.start_y / 23 + GamePanel.this.top) * 50 + (GamePanel.this.start_x / 23 + GamePanel.this.left);
                final int n2 = (GamePanel.this.last_y / 23 + GamePanel.this.top) * 50 + (GamePanel.this.last_x / 23 + GamePanel.this.left);
                GamePanel.this.drawLine(new Line2D.Float(GamePanel.this.start_x, GamePanel.this.start_y, GamePanel.this.last_x, GamePanel.this.last_y));
                GamePanel.this.findWord(n + 1, n2 + 1);
            }
        }
    }
    
    private class MouseMotionHandler extends MouseMotionAdapter
    {
        public void mouseDragged(final MouseEvent mouseEvent) {
            if (GamePanel.this.mouseDown) {
                if (GamePanel.this.last_x != GamePanel.this.start_x || GamePanel.this.last_y != GamePanel.this.start_y) {
                    GamePanel.this.drawLine(new Line2D.Float(GamePanel.this.start_x, GamePanel.this.start_y, GamePanel.this.last_x, GamePanel.this.last_y));
                }
                GamePanel.this.last_x = mouseEvent.getX();
                GamePanel.this.last_y = mouseEvent.getY();
                GamePanel.this.drawLine(new Line2D.Float(GamePanel.this.start_x, GamePanel.this.start_y, GamePanel.this.last_x, GamePanel.this.last_y));
            }
        }
    }
}
