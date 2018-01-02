// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.AdjustmentEvent;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.AWTEvent;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Image;
import XMLConsumer.IEntry;
import java.awt.Button;
import java.awt.Scrollbar;
import java.util.Vector;
import java.awt.event.AdjustmentListener;
import java.awt.Panel;

public abstract class ListView extends Panel implements Runnable, AdjustmentListener, IActionSink
{
    private static final int HEIGHT_HORIZONTAL_SB = 16;
    private static final int WIDTH_VERTICAL_SB = 16;
    private Vector m_vToDo;
    private Scrollbar m_sbHorizontal;
    private Scrollbar m_sbVertical;
    private Button m_btnScrollerCorner;
    private int m_nHVisible;
    private int m_nVisible;
    private int m_nUHeight;
    private int m_nMouseX;
    private int m_nMouseY;
    private IEntry m_HighLightedEntry;
    private IEntry m_SelectedEntry;
    private int m_nHighLightedPos;
    private int m_nSelectedPos;
    private Image m_bgImage;
    private int m_nbgWidth;
    private int m_nbgHeight;
    private Image m_cachebgImage;
    private int m_nOldWidth;
    private int m_nOldHeight;
    private boolean m_bBgUpdated;
    private Image m_imgbackground;
    
    protected void procResize() {
        this.doLayout();
    }
    
    public Image getBackgroundImage() {
        if (this.m_nbgWidth <= 0 || this.m_nbgHeight <= 0 || this.m_bgImage == null) {
            return null;
        }
        final Rectangle bounds = this.getBounds();
        if (bounds.height == this.m_nOldHeight && bounds.width == this.m_nOldWidth && !this.m_bBgUpdated) {
            return this.m_cachebgImage;
        }
        this.m_nOldWidth = bounds.width;
        this.m_nOldHeight = bounds.height;
        this.m_cachebgImage = this.createImage(bounds.width, bounds.height);
        this.drawBackGround(this.m_cachebgImage.getGraphics());
        return this.m_cachebgImage;
    }
    
    protected abstract void listPaint(final Graphics p0, final Image p1);
    
    public void clearHightLighted() {
        if (this.m_HighLightedEntry != null) {
            this.m_HighLightedEntry.highLight(false);
        }
        this.m_HighLightedEntry = null;
    }
    
    protected void processEvent(final AWTEvent awtEvent) {
        if (awtEvent.getID() == 101 || awtEvent.getID() == 102) {
            this.procResize();
            return;
        }
        if (awtEvent.getID() == 500) {
            this.m_nMouseX = ((MouseEvent)awtEvent).getX();
            this.m_nMouseY = ((MouseEvent)awtEvent).getY();
            this.procClicked();
            return;
        }
        if (awtEvent.getID() == 503) {
            this.m_nMouseX = ((MouseEvent)awtEvent).getX();
            this.m_nMouseY = ((MouseEvent)awtEvent).getY();
            this.procMoved();
            return;
        }
        if (awtEvent.getID() == 1004) {
            if (this.m_SelectedEntry == null) {
                final int top = this.getTop();
                final IEntry entryByPos = this.getEntryByPos(top);
                if (entryByPos != null) {
                    this.selectEntry(top, top, entryByPos);
                }
            }
        }
        else if (awtEvent.getID() == 401) {
            if (this.m_SelectedEntry == null) {
                return;
            }
            switch (((KeyEvent)awtEvent).getKeyCode()) {
                case 40: {
                    int top2 = this.getTop();
                    int nSelectedPos = this.m_nSelectedPos;
                    if (nSelectedPos >= this.getVerticalMax() - 1) {
                        break;
                    }
                    ++nSelectedPos;
                    final IEntry entryByPos2 = this.getEntryByPos(nSelectedPos);
                    if (entryByPos2 != null) {
                        if (nSelectedPos < top2 || nSelectedPos > top2 + this.getVisible() - 2) {
                            top2 = nSelectedPos - this.getVisible() + 2;
                            this.setTop(top2);
                        }
                        this.selectEntry(top2, nSelectedPos, entryByPos2);
                        return;
                    }
                    break;
                }
                case 38: {
                    int top3 = this.getTop();
                    int nSelectedPos2 = this.m_nSelectedPos;
                    if (nSelectedPos2 <= 0) {
                        break;
                    }
                    --nSelectedPos2;
                    final IEntry entryByPos3 = this.getEntryByPos(nSelectedPos2);
                    if (entryByPos3 != null) {
                        if (nSelectedPos2 < top3 || nSelectedPos2 > top3 + this.getVisible() - 2) {
                            top3 = nSelectedPos2;
                            this.setTop(top3);
                        }
                        this.selectEntry(top3, nSelectedPos2, entryByPos3);
                        return;
                    }
                    break;
                }
                case 34: {
                    final int top4 = this.getTop();
                    int n = top4 + this.getVisible() - 2;
                    final int n2 = this.getVerticalMax() - 1;
                    if (n > n2) {
                        n = n2;
                    }
                    if (this.m_nSelectedPos != n) {
                        this.selectEntry(top4, n, this.getEntryByPos(n));
                        return;
                    }
                    int top5 = top4 + this.getVisible() - 1;
                    if (top5 > n2 - this.getVisible() + 2) {
                        top5 = n2 - this.getVisible() + 2;
                    }
                    if (top5 > 0) {
                        final int n3 = top5 + this.getVisible() - 2;
                        final IEntry entryByPos4 = this.getEntryByPos(n3);
                        this.setTop(top5);
                        this.selectEntry(top5, n3, entryByPos4);
                        return;
                    }
                    break;
                }
                case 33: {
                    final int top6 = this.getTop();
                    if (this.m_nSelectedPos != top6) {
                        this.selectEntry(top6, top6, this.getEntryByPos(top6));
                        return;
                    }
                    int top7 = top6 - this.getVisible() + 1;
                    if (top7 < 0) {
                        top7 = 0;
                    }
                    final IEntry entryByPos5 = this.getEntryByPos(top7);
                    this.setTop(top7);
                    this.selectEntry(top7, top7, entryByPos5);
                }
                case 36: {
                    final IEntry entryByPos6 = this.getEntryByPos(0);
                    if (entryByPos6 != null) {
                        this.setTop(0);
                        this.selectEntry(0, 0, entryByPos6);
                        return;
                    }
                    break;
                }
                case 35: {
                    final int n4 = this.getVerticalMax() - 1;
                    final IEntry entryByPos7 = this.getEntryByPos(n4);
                    if (entryByPos7 == null) {
                        break;
                    }
                    final int top8 = n4 - this.getVisible() + 2;
                    if (top8 > 0) {
                        this.setTop(top8);
                        this.selectEntry(top8, n4, entryByPos7);
                        return;
                    }
                    this.setTop(0);
                    this.selectEntry(0, n4, entryByPos7);
                }
                case 10: {
                    if (this.m_SelectedEntry != null) {
                        this.m_SelectedEntry.action(this);
                        return;
                    }
                    break;
                }
            }
        }
    }
    
    protected void selectEntry(final int n, final int nSelectedPos, final IEntry selectedEntry) {
        final Graphics graphics = this.getGraphics();
        this.translate(graphics);
        if (this.m_SelectedEntry != null) {
            this.m_SelectedEntry.select(false);
            if (graphics != null && this.m_nSelectedPos - n >= 0) {
                this.m_SelectedEntry.display(graphics, this.m_nSelectedPos - n, this.m_nUHeight, this.getBackground(), this.m_imgbackground);
            }
        }
        if (selectedEntry != null) {
            selectedEntry.select(true);
            if (graphics != null) {
                this.getFontMetrics(graphics.getFont());
                selectedEntry.display(graphics, nSelectedPos - n, this.m_nUHeight, this.getBackground(), this.m_imgbackground);
            }
        }
        this.m_nSelectedPos = nSelectedPos;
        this.selectedEntry(this.m_SelectedEntry = selectedEntry);
    }
    
    public int getHorizontalPos() {
        return this.m_sbHorizontal.getValue();
    }
    
    public abstract void accept(final Vector p0);
    
    public void setBgImage(final Image bgImage) {
        this.m_bgImage = bgImage;
        this.m_nbgHeight = this.m_bgImage.getHeight(this);
        this.m_nbgWidth = this.m_bgImage.getWidth(this);
    }
    
    public void dispatchToDo(final String s) {
        if (s == "repaint") {
            this.repaint();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void drawBackGround(final Graphics graphics) {
        if (this.m_nbgWidth > 0 && this.m_nbgHeight > 0 && this.m_bgImage != null) {
            final Rectangle bounds = this.getBounds();
            for (int i = 0; i <= (bounds.width - 1) / this.m_nbgWidth; ++i) {
                for (int j = 0; j <= (bounds.height - 1) / this.m_nbgHeight; ++j) {
                    if (!graphics.drawImage(this.m_bgImage, i * this.m_nbgWidth, j * this.m_nbgHeight, this.m_nbgWidth, this.m_nbgHeight, null)) {
                        this.setTimeout("repaint", 100);
                        return;
                    }
                }
            }
            if (this.m_bBgUpdated) {
                this.m_bBgUpdated = false;
            }
        }
        else {
            final Color color = graphics.getColor();
            graphics.setColor(this.getBackground());
            final Rectangle bounds2 = this.getBounds();
            graphics.fillRect(0, 0, bounds2.width, bounds2.height);
            graphics.setColor(color);
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int nbgWidth, final int nbgHeight) {
        if (image == this.m_bgImage) {
            this.m_nbgWidth = nbgWidth;
            this.m_nbgHeight = nbgHeight;
            this.m_bBgUpdated = true;
            this.repaint();
            return false;
        }
        return true;
    }
    
    public void active() {
        if (this.m_SelectedEntry != null) {
            this.m_SelectedEntry.action(this);
        }
    }
    
    public void setHorizontalMax(int n) {
        n += 16;
        if (n <= this.m_nHVisible) {
            final int value = this.m_sbHorizontal.getValue();
            this.m_sbHorizontal.setValues(value, this.m_nHVisible - 1, 0, n);
            if (!this.m_sbVertical.isVisible()) {
                this.m_btnScrollerCorner.setVisible(false);
            }
            this.m_sbHorizontal.setVisible(false);
            if (value != this.m_sbHorizontal.getValue()) {
                this.repaint();
            }
        }
        else {
            this.m_sbHorizontal.setValues(this.m_sbHorizontal.getValue(), this.m_nHVisible - 1, 0, n);
            if (!this.m_sbVertical.isVisible()) {
                this.m_btnScrollerCorner.setVisible(true);
            }
            this.m_sbHorizontal.setVisible(true);
        }
    }
    
    public int getUnitHeight() {
        return this.m_nUHeight;
    }
    
    public void setUnitHeight(final int nuHeight) {
        if (nuHeight > 0) {
            this.m_nUHeight = nuHeight;
            this.m_sbHorizontal.setUnitIncrement(this.m_nUHeight);
        }
    }
    
    private void translateBack(final Graphics graphics) {
        graphics.translate(this.getHorizontalPos() - 4, -2);
    }
    
    public final void setTimeout(final String s, final int n) {
        final ToDo toDo = new ToDo(s, n);
        synchronized (this.m_vToDo) {
            this.m_vToDo.addElement(toDo);
        }
        // monitorexit(this.m_vToDo)
        new Thread(this).start();
    }
    
    public void doLayout() {
        this.m_sbVertical.setBounds(this.getBounds().width - 16, 0, 16, this.getBounds().height - 16);
        this.m_sbHorizontal.setBounds(0, this.getBounds().height - 16, this.getBounds().width - 16, 16);
        this.m_btnScrollerCorner.setBounds(this.getBounds().width - 16, this.getBounds().height - 16, 16, 16);
        this.m_nVisible = this.getBounds().height / this.m_nUHeight;
        if (this.m_sbVertical.getBlockIncrement() != this.m_nVisible - 1 && this.m_nVisible - 1 > 0) {
            this.m_sbVertical.setBlockIncrement(this.m_nVisible - 1);
        }
        if (this.m_sbVertical.getVisibleAmount() != this.m_nVisible - 1) {
            this.m_sbVertical.setVisibleAmount(this.m_nVisible - 1);
            if (this.m_sbVertical.getMaximum() < this.m_nVisible) {
                if (!this.m_sbHorizontal.isVisible()) {
                    this.m_btnScrollerCorner.setVisible(false);
                }
                this.m_sbVertical.setVisible(false);
            }
            else {
                if (!this.m_sbHorizontal.isVisible()) {
                    this.m_btnScrollerCorner.setVisible(true);
                }
                this.m_sbVertical.setVisible(true);
            }
        }
        this.m_nHVisible = this.getBounds().width;
        if (this.m_sbHorizontal.getBlockIncrement() != this.m_nHVisible - 1 && this.m_nHVisible - 1 > 0) {
            this.m_sbHorizontal.setBlockIncrement(this.m_nHVisible - 1);
        }
        if (this.m_sbHorizontal.getVisibleAmount() != this.m_nHVisible - 1) {
            this.m_sbHorizontal.setVisibleAmount(this.m_nHVisible - 1);
            if (this.m_sbHorizontal.getMaximum() < this.m_nHVisible) {
                if (!this.m_sbVertical.isVisible()) {
                    this.m_btnScrollerCorner.setVisible(false);
                }
                this.m_sbHorizontal.setVisible(false);
                return;
            }
            if (!this.m_sbVertical.isVisible()) {
                this.m_btnScrollerCorner.setVisible(true);
            }
            this.m_sbHorizontal.setVisible(true);
        }
    }
    
    protected void selectedEntry(final IEntry entry) {
    }
    
    public boolean isFocusTraversable() {
        return true;
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        if (adjustmentEvent.getID() == 601) {
            this.procScroll();
        }
    }
    
    protected void procClicked() {
        if (this.m_nUHeight != 0) {
            final int value = this.m_sbVertical.getValue();
            final int n = value + this.m_nMouseY / this.m_nUHeight;
            final IEntry entryByPos = this.getEntryByPos(n);
            if (entryByPos != this.m_SelectedEntry) {
                this.selectEntry(value, n, entryByPos);
            }
            if (entryByPos != null) {
                entryByPos.action(this);
            }
        }
    }
    
    public ListView() {
        this.m_sbHorizontal = new Scrollbar(0);
        this.m_sbVertical = new Scrollbar(1);
        this.m_sbHorizontal.setBackground(new Color(192, 192, 192));
        this.add(this.m_sbHorizontal);
        this.m_sbVertical.setBackground(new Color(192, 192, 192));
        this.add(this.m_sbVertical);
        (this.m_btnScrollerCorner = new Button()).setEnabled(false);
        this.add(this.m_btnScrollerCorner);
        this.enableEvents(61L);
        this.m_sbVertical.addAdjustmentListener(this);
        this.m_sbHorizontal.addAdjustmentListener(this);
        this.m_nVisible = 0;
        this.m_nHVisible = 0;
        this.m_vToDo = new Vector();
        this.setUnitHeight(16);
        this.m_nbgWidth = -1;
        this.m_nbgHeight = -1;
        this.m_bgImage = null;
    }
    
    protected void procMoved() {
        if (this.m_nUHeight != 0) {
            final int value = this.m_sbVertical.getValue();
            final int n = value + this.m_nMouseY / this.m_nUHeight;
            final IEntry entryByPos = this.getEntryByPos(n);
            if (entryByPos != this.m_HighLightedEntry) {
                this.highLightEntry(value, n, entryByPos);
            }
        }
    }
    
    private void translate(final Graphics graphics) {
        graphics.translate(-this.getHorizontalPos() + 4, 2);
    }
    
    public void paint(final Graphics graphics) {
        final Rectangle bounds = this.getBounds();
        if (this.m_sbVertical.isVisible()) {
            final Rectangle rectangle = bounds;
            rectangle.width -= 16;
        }
        final Image image = this.createImage(bounds.width, bounds.height);
        final Graphics graphics2 = image.getGraphics();
        this.m_imgbackground = this.getBackgroundImage();
        if (this.m_imgbackground != null) {
            graphics2.drawImage(this.m_imgbackground, 0, 0, this);
        }
        this.translate(graphics2);
        this.listPaint(graphics2, this.m_imgbackground);
        this.translateBack(graphics2);
        this.paintBorder(graphics2, bounds);
        graphics.drawImage(image, 0, 0, this);
    }
    
    public int getVisible() {
        return this.m_nVisible;
    }
    
    public int getTop() {
        return this.m_sbVertical.getValue();
    }
    
    public void setTop(final int value) {
        this.m_sbVertical.setValue(value);
        this.procScroll();
    }
    
    protected abstract IEntry getEntryByPos(final int p0);
    
    protected void highLightEntry(final int n, final int nHighLightedPos, final IEntry highLightedEntry) {
        final Graphics graphics = this.getGraphics();
        this.translate(graphics);
        if (this.m_HighLightedEntry != null) {
            this.m_HighLightedEntry.highLight(false);
            if (graphics != null) {
                this.m_HighLightedEntry.display(graphics, this.m_nHighLightedPos - n, this.m_nUHeight, this.getBackground(), this.m_imgbackground);
            }
        }
        if (highLightedEntry != null) {
            highLightedEntry.highLight(true);
            if (graphics != null) {
                highLightedEntry.display(graphics, nHighLightedPos - n, this.m_nUHeight, this.getBackground(), this.m_imgbackground);
            }
        }
        this.m_HighLightedEntry = highLightedEntry;
        this.m_nHighLightedPos = nHighLightedPos;
    }
    
    public void setVerticalMax(final int n) {
        this.m_sbVertical.setValues(this.m_sbVertical.getValue(), this.m_nVisible - 1, 0, n);
        if (n <= this.m_nVisible) {
            if (!this.m_sbHorizontal.isVisible()) {
                this.m_btnScrollerCorner.setVisible(false);
            }
            this.m_sbVertical.setVisible(false);
            return;
        }
        if (!this.m_sbHorizontal.isVisible()) {
            this.m_btnScrollerCorner.setVisible(true);
        }
        this.m_sbVertical.setVisible(true);
    }
    
    public int getVerticalMax() {
        return this.m_sbVertical.getMaximum();
    }
    
    protected void paintBorder(final Graphics graphics, final Rectangle rectangle) {
        final Color color = graphics.getColor();
        final Color background = this.getBackground();
        final Color darker = background.darker();
        final Color darker2 = darker.darker();
        final Color darker3 = darker2.darker();
        graphics.setColor(darker2);
        graphics.drawLine(0, 0, rectangle.width - 1, 0);
        graphics.drawLine(0, 0, 0, rectangle.height - 1);
        graphics.setColor(darker3);
        graphics.drawLine(1, 1, rectangle.width - 2, 1);
        graphics.drawLine(1, 1, 1, rectangle.height - 2);
        graphics.setColor(darker);
        graphics.drawLine(rectangle.width - 2, 1, rectangle.width - 2, rectangle.height - 2);
        graphics.drawLine(1, rectangle.height - 2, rectangle.width - 2, rectangle.height - 2);
        graphics.setColor(background);
        graphics.drawLine(rectangle.width - 1, 1, rectangle.width - 1, rectangle.height - 1);
        graphics.drawLine(0, rectangle.height - 1, rectangle.width - 1, rectangle.height - 1);
        graphics.setColor(color);
    }
    
    protected void procScroll() {
        this.repaint();
    }
    
    public Dimension getPreferredSize() {
        return this.getParent().getSize();
    }
    
    public void run() {
        final ToDo toDo;
        synchronized (this.m_vToDo) {
            toDo = this.m_vToDo.elementAt(0);
            this.m_vToDo.removeElementAt(0);
        }
        // monitorexit(this.m_vToDo)
        try {
            Thread.sleep(toDo.m_nMSec);
            this.dispatchToDo(toDo.m_sToDo);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public void clearSelected() {
        if (this.m_SelectedEntry != null) {
            this.m_SelectedEntry.select(false);
        }
        this.selectedEntry(this.m_SelectedEntry = null);
    }
    
    private class ToDo
    {
        public String m_sToDo;
        public int m_nMSec;
        
        public ToDo(final String sToDo, final int nmSec) {
            ListView.this.getClass();
            this.m_sToDo = sToDo;
            this.m_nMSec = nmSec;
        }
    }
}
