import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.FontMetrics;
import java.util.Vector;
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
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class CluePanel extends JPanel
{
    private final int RIGHT_MARGIN = 26;
    private int top;
    private String[] words;
    private String[] clues;
    private boolean[] found;
    private boolean useClues;
    private ClueInfo[] clue_info;
    private Color wordColor;
    private Color slashColor;
    private Color foundColor;
    private Color backColor;
    public BufferedImage upImg;
    public BufferedImage downImg;
    
    public CluePanel(final Color backColor) {
        final String[] array = null;
        this.clues = array;
        this.words = array;
        this.found = null;
        this.useClues = false;
        this.clue_info = null;
        this.wordColor = Color.BLACK;
        this.slashColor = Color.RED;
        this.foundColor = Color.GRAY;
        this.backColor = backColor;
        this.top = 0;
        this.addMouseListener(new MouseHandler());
    }
    
    public void paint(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Rectangle visibleRect = this.getVisibleRect();
        graphics2D.setPaint(this.backColor);
        graphics2D.draw(visibleRect);
        graphics2D.fill(visibleRect);
        if (this.useClues && this.clues != null) {
            this.fitClues(graphics2D, visibleRect.width - 26);
            this.drawClues(graphics2D);
        }
        else if (this.words != null) {
            this.drawWords(graphics2D);
        }
        this.drawScroll(graphics2D);
    }
    
    private void drawScroll(final Graphics2D graphics2D) {
        final Rectangle visibleRect = this.getVisibleRect();
        int n = 0;
        final int n2 = graphics2D.getFontMetrics().getHeight() + graphics2D.getFontMetrics().getDescent();
        if (this.useClues && this.clues != null) {
            for (int i = 0; i < this.clue_info.length; ++i) {
                n += this.clue_info[i].height;
            }
        }
        else {
            n = n2 * this.words.length;
        }
        final boolean b = n > visibleRect.height;
        if (!b && this.top != 0) {
            this.top = 0;
            this.repaint();
            return;
        }
        if (!b) {
            return;
        }
        if (this.upImg != null && this.downImg != null) {
            final int n3 = visibleRect.width - 26 + 2;
            final int n4 = 4;
            if (this.top != 0) {
                graphics2D.drawImage(this.upImg, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, n3, n4), null);
            }
            if (this.useClues && this.clues != null) {
                if (this.top != this.clue_info.length - 1) {
                    graphics2D.drawImage(this.downImg, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, n3, visibleRect.height - this.downImg.getHeight() - 4), null);
                }
            }
            else if (n - this.top * n2 > visibleRect.height) {
                graphics2D.drawImage(this.downImg, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, n3, visibleRect.height - this.downImg.getHeight() - 4), null);
            }
        }
    }
    
    private void drawClues(final Graphics2D graphics2D) {
        final Font font = graphics2D.getFont();
        graphics2D.setFont(new Font("Arial", 1, 12));
        final int n = graphics2D.getFontMetrics().getHeight() + graphics2D.getFontMetrics().getDescent();
        final int n2 = graphics2D.getFontMetrics().getAscent() + graphics2D.getFontMetrics().getLeading();
        int n3 = n;
        if (this.clue_info != null) {
            for (int i = this.top; i < this.clue_info.length; ++i) {
                for (int j = 0; j < this.clue_info[i].lines.length; ++j) {
                    graphics2D.setPaint(this.found[i] ? this.foundColor : this.wordColor);
                    graphics2D.drawString(this.clue_info[i].lines[j], 10, n3 + j * n);
                    if (this.found[i]) {
                        graphics2D.setPaint(this.slashColor);
                        graphics2D.draw(new Line2D.Float(5.0f, n3 + j * n - n2 / 2, graphics2D.getFontMetrics().stringWidth(this.clue_info[i].lines[j]) + 15, n3 + j * n - n2 / 2));
                    }
                }
                n3 += this.clue_info[i].height;
            }
        }
        graphics2D.setFont(font);
    }
    
    private void fitClues(final Graphics2D graphics2D, final int n) {
        if (this.clue_info != null) {
            final FontMetrics fontMetrics = graphics2D.getFontMetrics();
            final int n2 = fontMetrics.getHeight() + fontMetrics.getDescent();
            for (int i = 0; i < this.clue_info.length; ++i) {
                final ClueInfo clueInfo = this.clue_info[i];
                int j = 0;
                final Vector vector = new Vector<String>();
                vector.add(this.clues[i]);
                while (j <= vector.lastIndexOf(vector.lastElement())) {
                    String substring = vector.get(j);
                    String string = new String();
                    while (fontMetrics.stringWidth(substring) > n) {
                        final int lastIndex = substring.lastIndexOf(32);
                        if (lastIndex == -1) {
                            break;
                        }
                        string = substring.substring(lastIndex + 1, substring.length()) + ' ' + string;
                        substring = substring.substring(0, lastIndex);
                        vector.set(j, substring.trim());
                    }
                    if (string.length() > 0) {
                        vector.add(string.trim());
                    }
                    ++j;
                }
                clueInfo.lines = new String[vector.lastIndexOf(vector.lastElement()) + 1];
                for (int k = 0; k < clueInfo.lines.length; ++k) {
                    clueInfo.lines[k] = vector.get(k);
                }
                clueInfo.height = clueInfo.lines.length * n2;
            }
        }
    }
    
    private void drawWords(final Graphics2D graphics2D) {
        final Font font = graphics2D.getFont();
        graphics2D.setFont(new Font("Arial", 1, 12));
        final int n = graphics2D.getFontMetrics().getHeight() + graphics2D.getFontMetrics().getDescent();
        final int n2 = graphics2D.getFontMetrics().getAscent() + graphics2D.getFontMetrics().getLeading();
        for (int i = this.top; i < this.words.length; ++i) {
            final int n3 = (i - this.top + 1) * n;
            if (this.found[i]) {
                graphics2D.setPaint(this.foundColor);
            }
            else {
                graphics2D.setPaint(this.wordColor);
            }
            graphics2D.drawString(this.words[i], 10, n3);
            if (this.found[i]) {
                graphics2D.setPaint(this.slashColor);
                graphics2D.draw(new Line2D.Float(5.0f, n3 - n2 / 2, graphics2D.getFontMetrics().stringWidth(this.words[i]) + 15, n3 - n2 / 2));
            }
        }
        graphics2D.setFont(font);
    }
    
    public void setWords(final String[] words, final String[] clues, final boolean useClues) {
        this.words = words;
        this.clues = clues;
        this.useClues = useClues;
        this.found = new boolean[words.length];
        for (int i = 0; i < this.found.length; ++i) {
            this.found[i] = false;
        }
        if (clues != null) {
            this.clue_info = new ClueInfo[clues.length];
            for (int j = 0; j < clues.length; ++j) {
                this.clue_info[j] = new ClueInfo();
            }
        }
        this.repaint();
    }
    
    public boolean setWordFound(final int n) {
        if (n >= 0 && n < this.found.length) {
            this.found[n] = true;
        }
        this.repaint();
        boolean b = true;
        for (int n2 = 0; n2 < this.found.length && b; b = this.found[n2], ++n2) {}
        return b;
    }
    
    public void setColors(final Color wordColor, final Color slashColor, final Color foundColor) {
        if (wordColor != null) {
            this.wordColor = wordColor;
        }
        if (slashColor != null) {
            this.slashColor = slashColor;
        }
        if (foundColor != null) {
            this.foundColor = foundColor;
        }
    }
    
    private class ClueInfo
    {
        public String[] lines;
        public int height;
    }
    
    class MouseHandler extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            super.mousePressed(mouseEvent);
            final Graphics2D graphics2D = (Graphics2D)CluePanel.this.getGraphics();
            final int n = graphics2D.getFontMetrics().getHeight() + graphics2D.getFontMetrics().getDescent();
            CluePanel.this.getTopLevelAncestor().requestFocus();
            final Point point = mouseEvent.getPoint();
            final Rectangle visibleRect = CluePanel.this.getVisibleRect();
            if (point.x > visibleRect.width - 26 && CluePanel.this.upImg != null && CluePanel.this.downImg != null) {
                if (point.y >= 4 && point.y < CluePanel.this.upImg.getHeight() + 4) {
                    if (CluePanel.this.top > 0) {
                        CluePanel.this.top--;
                        CluePanel.this.repaint(visibleRect);
                    }
                }
                else if (point.y <= visibleRect.height - 4 && point.y >= visibleRect.height - 4 - CluePanel.this.downImg.getHeight() && ((CluePanel.this.useClues && CluePanel.this.clue_info != null && CluePanel.this.top < CluePanel.this.clue_info.length - 1) || n * (CluePanel.this.words.length - CluePanel.this.top) > visibleRect.height)) {
                    CluePanel.this.top++;
                    CluePanel.this.repaint(visibleRect);
                }
            }
        }
    }
}
