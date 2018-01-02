import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class SVDScrollbar extends Panel implements MouseListener, MouseMotionListener
{
    private SVDCommunicator aplOwner;
    private int nScrollBarHeight;
    private int nScrollBarWidth;
    private int nScrollMaxPos;
    private int nScrollMinPos;
    private int nScrollPos;
    private int nScrollMaxSize;
    private int nScrollMinSize;
    private int nScrollSize;
    private int nScrollBarMiddleSize;
    private Color clScrollLight;
    private Color clScrollShaddow;
    private Color clScrollBacground;
    private int bScrollButtonPressed;
    private int bScrollStatus;
    private int nThumbMousePressPos;
    private int nThumbMouseLastPos;
    public int TotalAmount;
    public int VisibleAmount;
    public int Value;
    public int MinValue;
    public int MaxValue;
    public int UnitIncrement;
    public int BlockIncrement;
    public int LastValue;
    private boolean bDrawShorter;
    public Image imScrollDown;
    public Image imScrollDownPressed;
    public Image imScrollUp;
    public Image imScrollUpPressed;
    public Image imScrollBody;
    public Image imScrollBodyBottom;
    public Image imScrollBodyMiddle;
    public Image imScrollBodyTop;
    public Image imScrollBodyPressed;
    public Image imScrollBodyBottomPressed;
    public Image imScrollBodyMiddlePressed;
    public Image imScrollBodyTopPressed;
    private Image imgScroll;
    private Graphics grScroll;
    
    SVDScrollbar(final SVDCommunicator aplParent, final int nLeft, final int nTop, final int nWidth, final int nHeight, final Color clScroll, final int aVisibleAmount) {
        this.nScrollBarHeight = nHeight;
        this.nScrollBarWidth = nWidth;
        this.imgScroll = aplParent.createImage(nWidth, nHeight);
        this.grScroll = this.imgScroll.getGraphics();
        this.clScrollBacground = clScroll;
        this.clScrollLight = new Color(220, 220, 220);
        this.clScrollShaddow = new Color(20, 20, 20);
        this.setBounds(nLeft, nTop, this.nScrollBarWidth, this.nScrollBarHeight);
        this.setLayout(null);
        this.aplOwner = aplParent;
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.TotalAmount = 0;
        this.VisibleAmount = aVisibleAmount;
        this.nScrollMaxSize = this.nScrollBarHeight - this.nScrollBarWidth * 2;
        this.nScrollMinSize = 5;
        this.bDrawShorter = true;
        if (this.TotalAmount > this.VisibleAmount) {
            this.nScrollSize = this.VisibleAmount * this.nScrollMaxSize / this.TotalAmount;
        }
        else {
            this.nScrollSize = this.nScrollMaxSize;
        }
        if ((this.nScrollSize - 21) % 2 == 0) {
            this.nScrollBarMiddleSize = (this.nScrollSize - 21) / 2;
            this.bDrawShorter = false;
        }
        else {
            this.nScrollBarMiddleSize = (this.nScrollSize - 21) / 2 + 1;
        }
        this.nScrollMaxPos = this.nScrollBarHeight - this.nScrollBarWidth - this.nScrollSize;
        this.nScrollMinPos = this.nScrollBarWidth;
        this.nScrollPos = this.nScrollMinPos;
        this.bScrollButtonPressed = 0;
        this.bScrollStatus = 0;
        this.MinValue = 1;
        this.MaxValue = 100;
        this.Value = 1;
        this.LastValue = 1;
        this.UnitIncrement = 20;
        this.BlockIncrement = 300;
    }
    
    public void resizeScroll(final int aTotalAmount, final int aVisibleAmount) {
        try {
            this.TotalAmount = aTotalAmount;
            if (this.TotalAmount > this.VisibleAmount) {
                if (this.TotalAmount - this.VisibleAmount < 4000) {
                    this.nScrollSize = this.VisibleAmount * this.nScrollMaxSize / this.TotalAmount;
                }
            }
            else {
                this.nScrollSize = this.nScrollMaxSize;
            }
            if ((this.nScrollSize - 21) % 2 == 0) {
                this.nScrollBarMiddleSize = (this.nScrollSize - 21) / 2;
                this.bDrawShorter = false;
            }
            else {
                this.nScrollBarMiddleSize = (this.nScrollSize - 21) / 2 + 1;
            }
            this.nScrollMaxPos = this.nScrollBarHeight - this.nScrollBarWidth - this.nScrollSize;
            this.nScrollMinPos = this.nScrollBarWidth;
            this.imScrollBody = this.imScrollBody.getScaledInstance(this.nScrollBarWidth, this.nScrollBarMiddleSize, 2);
            this.imScrollBodyPressed = this.imScrollBodyPressed.getScaledInstance(this.nScrollBarWidth, this.nScrollBarMiddleSize, 2);
            if (aTotalAmount > aVisibleAmount) {
                this.MaxValue = aTotalAmount - aVisibleAmount;
                this.Value = this.MaxValue;
            }
            else {
                this.MaxValue = 1;
                this.Value = 1;
            }
        }
        catch (Exception ex) {}
        this.adjustThumbPositionByValue(this.Value);
    }
    
    private void sendNotification(final SVDCommunicator aOwner, final int aValue) {
        if (aValue != this.LastValue) {
            this.LastValue = aValue;
            final SVDScrollable a = (SVDScrollable)this.getParent();
            a.scrollNotification(aValue);
        }
    }
    
    public void adjustThumbPositionByValue(int aValue) {
        if (aValue > this.MaxValue) {
            aValue = this.MaxValue;
        }
        if (aValue < this.MinValue) {
            aValue = this.MinValue;
        }
        try {
            this.nScrollPos = (aValue - this.MinValue) * (this.nScrollMaxPos - this.nScrollMinPos) / (this.MaxValue - this.MinValue) + this.nScrollMinPos;
        }
        catch (Exception ex) {}
        this.sendNotification(this.aplOwner, aValue);
        this.repaintScrollBar();
    }
    
    public void adjustValueByThumbPosition(final int aPosition) {
        try {
            this.Value = (this.MaxValue - this.MinValue) * (aPosition - this.nScrollMinPos) / (this.nScrollMaxPos - this.nScrollMinPos) + this.MinValue;
        }
        catch (Exception ex) {}
    }
    
    public void loadTheme() {
        this.createScrollImages();
        this.imScrollBody = this.imScrollBody.getScaledInstance(this.nScrollBarWidth, this.nScrollBarMiddleSize, 2);
        this.imScrollBodyPressed = this.imScrollBodyPressed.getScaledInstance(this.nScrollBarWidth, this.nScrollBarMiddleSize, 2);
    }
    
    public void paint(final Graphics gr) {
        gr.drawImage(this.imgScroll, 0, 0, this);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void repaintScrollBar() {
        if (this.grScroll != null) {
            this.grScroll.setColor(this.clScrollBacground);
            this.grScroll.fillRect(0, this.nScrollBarWidth, this.nScrollBarWidth, this.nScrollPos - this.nScrollBarWidth);
            this.grScroll.fillRect(0, this.nScrollPos + this.nScrollSize, this.nScrollBarWidth, this.nScrollBarHeight - this.nScrollBarWidth - this.nScrollPos - this.nScrollSize);
            if (this.bScrollButtonPressed == 0) {
                this.grScroll.drawImage(this.imScrollUp, 0, 0, this);
                this.grScroll.drawImage(this.imScrollDown, 0, this.nScrollBarHeight - this.nScrollBarWidth, this);
            }
            else if (this.bScrollButtonPressed == 1) {
                this.grScroll.drawImage(this.imScrollUpPressed, 0, 0, this);
                this.grScroll.drawImage(this.imScrollDown, 0, this.nScrollBarHeight - this.nScrollBarWidth, this);
            }
            else {
                this.grScroll.drawImage(this.imScrollUp, 0, 0, this);
                this.grScroll.drawImage(this.imScrollDownPressed, 0, this.nScrollBarHeight - this.nScrollBarWidth, this);
            }
            if (this.bScrollStatus == 0) {
                this.grScroll.drawImage(this.imScrollBodyTop, 0, this.nScrollPos, this);
                this.grScroll.drawImage(this.imScrollBody, 0, this.nScrollPos + 7, this);
                this.grScroll.drawImage(this.imScrollBodyMiddle, 0, this.nScrollPos + 7 + this.nScrollBarMiddleSize, this);
                this.grScroll.drawImage(this.imScrollBody, 0, this.nScrollPos + 14 + this.nScrollBarMiddleSize, this);
                if (this.bDrawShorter) {
                    this.grScroll.drawImage(this.imScrollBodyBottom, 0, this.nScrollPos + 13 + 2 * this.nScrollBarMiddleSize, this);
                }
                else {
                    this.grScroll.drawImage(this.imScrollBodyBottom, 0, this.nScrollPos + 14 + 2 * this.nScrollBarMiddleSize, this);
                }
            }
            else {
                this.grScroll.drawImage(this.imScrollBodyTopPressed, 0, this.nScrollPos, this);
                this.grScroll.drawImage(this.imScrollBodyPressed, 0, this.nScrollPos + 7, this);
                this.grScroll.drawImage(this.imScrollBodyMiddlePressed, 0, this.nScrollPos + 7 + this.nScrollBarMiddleSize, this);
                this.grScroll.drawImage(this.imScrollBodyPressed, 0, this.nScrollPos + 14 + this.nScrollBarMiddleSize, this);
                if (this.bDrawShorter) {
                    this.grScroll.drawImage(this.imScrollBodyBottomPressed, 0, this.nScrollPos + 13 + 2 * this.nScrollBarMiddleSize, this);
                }
                else {
                    this.grScroll.drawImage(this.imScrollBodyBottomPressed, 0, this.nScrollPos + 14 + 2 * this.nScrollBarMiddleSize, this);
                }
            }
        }
        this.getGraphics().drawImage(this.imgScroll, 0, 0, this);
    }
    
    public void mouseExited(final MouseEvent me) {
    }
    
    public void mouseEntered(final MouseEvent me) {
    }
    
    public void mouseReleased(final MouseEvent me) {
        if (me.getY() <= this.nScrollBarWidth) {
            this.grScroll.drawImage(this.imScrollUp, 0, 0, this);
            this.getGraphics().drawImage(this.imgScroll, 0, 0, this);
            this.bScrollButtonPressed = 0;
        }
        if (me.getY() >= this.nScrollBarHeight - this.nScrollBarWidth) {
            this.grScroll.drawImage(this.imScrollDown, 0, this.nScrollBarHeight - this.nScrollBarWidth, this);
            this.getGraphics().drawImage(this.imgScroll, 0, 0, this);
            this.bScrollButtonPressed = 0;
        }
        this.bScrollStatus = 0;
        this.repaintScrollBar();
    }
    
    public void mousePressed(final MouseEvent me) {
        if (me.getY() <= this.nScrollBarWidth) {
            this.grScroll.drawImage(this.imScrollUpPressed, 0, 0, this);
            this.getGraphics().drawImage(this.imgScroll, 0, 0, this);
            this.bScrollButtonPressed = 1;
            if (this.TotalAmount > this.VisibleAmount) {
                if (this.Value - this.UnitIncrement >= this.MinValue) {
                    this.Value -= this.UnitIncrement;
                }
                else {
                    this.Value = this.MinValue;
                }
                this.adjustThumbPositionByValue(this.Value);
            }
        }
        if (me.getY() >= this.nScrollBarHeight - this.nScrollBarWidth) {
            this.grScroll.drawImage(this.imScrollDownPressed, 0, this.nScrollBarHeight - this.nScrollBarWidth, this);
            this.getGraphics().drawImage(this.imgScroll, 0, 0, this);
            this.bScrollButtonPressed = 2;
            if (this.TotalAmount > this.VisibleAmount) {
                if (this.Value + this.UnitIncrement <= this.MaxValue) {
                    this.Value += this.UnitIncrement;
                }
                else {
                    this.Value = this.MaxValue;
                }
                this.adjustThumbPositionByValue(this.Value);
            }
        }
        if (me.getY() >= this.nScrollPos && me.getY() <= this.nScrollPos + this.nScrollSize) {
            this.bScrollStatus = 1;
            this.nThumbMousePressPos = me.getY();
            this.nThumbMouseLastPos = this.nThumbMousePressPos;
            this.repaintScrollBar();
        }
        if (me.getY() > this.nScrollBarWidth && me.getY() < this.nScrollPos) {
            if (this.Value - this.BlockIncrement >= this.MinValue) {
                this.Value -= this.BlockIncrement;
            }
            else {
                this.Value = this.MinValue;
            }
            this.adjustThumbPositionByValue(this.Value);
        }
        if (me.getY() > this.nScrollPos + this.nScrollSize && me.getY() < this.nScrollBarHeight - this.nScrollBarWidth) {
            if (this.Value + this.BlockIncrement <= this.MaxValue) {
                this.Value += this.BlockIncrement;
            }
            else {
                this.Value = this.MaxValue;
            }
            this.adjustThumbPositionByValue(this.Value);
        }
    }
    
    public void mouseClicked(final MouseEvent me) {
    }
    
    public void mouseMoved(final MouseEvent me) {
        if (this.bScrollButtonPressed > 0 && me.getY() > this.nScrollBarWidth && me.getY() < this.nScrollBarHeight - this.nScrollBarWidth) {
            if (this.bScrollButtonPressed == 1) {
                this.grScroll.drawImage(this.imScrollUp, 0, 0, this);
                this.getGraphics().drawImage(this.imgScroll, 0, 0, this);
            }
            else {
                this.grScroll.drawImage(this.imScrollDown, 0, this.nScrollBarHeight - this.nScrollBarWidth, this);
                this.getGraphics().drawImage(this.imgScroll, 0, 0, this);
            }
            this.bScrollButtonPressed = 0;
        }
    }
    
    public void mouseDragged(final MouseEvent me) {
        if (this.bScrollStatus == 1) {
            if (this.nScrollPos + me.getY() - this.nThumbMouseLastPos >= this.nScrollMinPos && this.nScrollPos + me.getY() - this.nThumbMouseLastPos <= this.nScrollMaxPos) {
                this.nScrollPos = this.nScrollPos + me.getY() - this.nThumbMouseLastPos;
                this.nThumbMouseLastPos = me.getY();
                this.adjustValueByThumbPosition(this.nScrollPos);
                this.sendNotification(this.aplOwner, this.Value);
            }
            if (this.nScrollPos + me.getY() - this.nThumbMouseLastPos < this.nScrollMinPos) {
                this.adjustValueByThumbPosition(this.nScrollPos = this.nScrollMinPos);
                this.sendNotification(this.aplOwner, this.Value);
            }
            if (this.nScrollPos + me.getY() - this.nThumbMouseLastPos > this.nScrollMaxPos) {
                this.adjustValueByThumbPosition(this.nScrollPos = this.nScrollMaxPos);
                this.sendNotification(this.aplOwner, this.Value);
            }
            this.repaintScrollBar();
        }
    }
    
    public void createScrollImages() {
        final Color clNorm = new Color(14999765);
        final Color clDown = new Color(8421504);
        final Color clWhat = new Color(11382189);
        final Color clWhere = new Color(14999765);
        final Color cl4 = new Color(16580093);
        final Color cl5 = new Color(8297142);
        final Color cl6 = new Color(9020092);
        final Color cl7 = new Color(12307160);
        final Color cl8 = new Color(14279145);
        this.imScrollUp = this.aplOwner.createImage(16, 16);
        Graphics g = this.imScrollUp.getGraphics();
        g.setColor(cl5);
        g.fillRect(0, 0, 16, 16);
        g.setColor(cl6);
        g.fillRect(2, 0, 1, 1);
        g.fillRect(0, 2, 1, 1);
        g.fillRect(13, 0, 1, 1);
        g.fillRect(15, 2, 1, 1);
        g.fillRect(0, 13, 1, 1);
        g.fillRect(2, 15, 1, 1);
        g.fillRect(13, 15, 1, 1);
        g.fillRect(15, 13, 1, 1);
        g.setColor(cl7);
        g.fillRect(1, 0, 1, 1);
        g.fillRect(0, 1, 1, 1);
        g.fillRect(14, 0, 1, 1);
        g.fillRect(15, 1, 1, 1);
        g.fillRect(0, 14, 1, 1);
        g.fillRect(1, 15, 1, 1);
        g.fillRect(14, 15, 1, 1);
        g.fillRect(15, 14, 1, 1);
        g.setColor(cl4);
        g.fillRect(0, 0, 1, 1);
        g.fillRect(15, 0, 1, 1);
        g.fillRect(0, 15, 1, 1);
        g.fillRect(15, 15, 1, 1);
        g.setColor(cl8);
        g.fillRect(7, 6, 2, 1);
        g.fillRect(6, 7, 4, 1);
        g.fillRect(5, 8, 6, 1);
        g.fillRect(4, 9, 8, 1);
        this.imScrollDown = this.aplOwner.createImage(16, 16);
        g = this.imScrollDown.getGraphics();
        g.setColor(cl5);
        g.fillRect(0, 0, 16, 16);
        g.setColor(cl6);
        g.fillRect(2, 0, 1, 1);
        g.fillRect(0, 2, 1, 1);
        g.fillRect(13, 0, 1, 1);
        g.fillRect(15, 2, 1, 1);
        g.fillRect(0, 13, 1, 1);
        g.fillRect(2, 15, 1, 1);
        g.fillRect(13, 15, 1, 1);
        g.fillRect(15, 13, 1, 1);
        g.setColor(cl7);
        g.fillRect(1, 0, 1, 1);
        g.fillRect(0, 1, 1, 1);
        g.fillRect(14, 0, 1, 1);
        g.fillRect(15, 1, 1, 1);
        g.fillRect(0, 14, 1, 1);
        g.fillRect(1, 15, 1, 1);
        g.fillRect(14, 15, 1, 1);
        g.fillRect(15, 14, 1, 1);
        g.setColor(cl4);
        g.fillRect(0, 0, 1, 1);
        g.fillRect(15, 0, 1, 1);
        g.fillRect(0, 15, 1, 1);
        g.fillRect(15, 15, 1, 1);
        g.setColor(cl8);
        g.fillRect(4, 6, 8, 1);
        g.fillRect(5, 7, 6, 1);
        g.fillRect(6, 8, 4, 1);
        g.fillRect(7, 9, 2, 1);
        this.imScrollBody = this.aplOwner.createImage(16, 1);
        g = this.imScrollBody.getGraphics();
        g.setColor(cl5);
        g.fillRect(0, 0, 16, 1);
        this.imScrollBodyBottom = this.aplOwner.createImage(16, 7);
        g = this.imScrollBodyBottom.getGraphics();
        g.setColor(cl5);
        g.fillRect(0, 0, 16, 7);
        g.setColor(cl6);
        g.fillRect(0, 3, 1, 1);
        g.fillRect(15, 3, 1, 1);
        g.fillRect(2, 5, 1, 1);
        g.fillRect(13, 5, 1, 1);
        g.setColor(cl7);
        g.fillRect(0, 4, 1, 1);
        g.fillRect(15, 4, 1, 1);
        g.fillRect(1, 5, 1, 1);
        g.fillRect(14, 5, 1, 1);
        g.setColor(cl4);
        g.fillRect(0, 5, 1, 1);
        g.fillRect(15, 5, 1, 1);
        g.fillRect(0, 6, 16, 1);
        this.imScrollBodyTop = this.aplOwner.createImage(16, 7);
        g = this.imScrollBodyTop.getGraphics();
        g.setColor(cl5);
        g.fillRect(0, 0, 16, 7);
        g.setColor(cl6);
        g.fillRect(0, 3, 1, 1);
        g.fillRect(2, 1, 1, 1);
        g.fillRect(13, 1, 1, 1);
        g.fillRect(15, 3, 1, 1);
        g.setColor(cl7);
        g.fillRect(0, 2, 1, 1);
        g.fillRect(1, 1, 1, 1);
        g.fillRect(14, 1, 1, 1);
        g.fillRect(15, 2, 1, 1);
        g.setColor(cl4);
        g.fillRect(0, 1, 1, 1);
        g.fillRect(15, 1, 1, 1);
        g.fillRect(0, 0, 16, 1);
        this.imScrollBodyMiddle = this.aplOwner.createImage(16, 7);
        g = this.imScrollBodyMiddle.getGraphics();
        g.setColor(cl5);
        g.fillRect(0, 0, 16, 7);
        g.setColor(cl8);
        g.fillRect(3, 1, 10, 1);
        g.fillRect(3, 3, 10, 1);
        g.fillRect(3, 5, 10, 1);
        this.imScrollUpPressed = this.aplOwner.createImage(16, 16);
        g = this.imScrollUpPressed.getGraphics();
        g.setColor(cl5);
        g.fillRect(0, 0, 16, 16);
        g.setColor(cl6);
        g.fillRect(2, 0, 1, 1);
        g.fillRect(0, 2, 1, 1);
        g.fillRect(13, 0, 1, 1);
        g.fillRect(15, 2, 1, 1);
        g.fillRect(0, 13, 1, 1);
        g.fillRect(2, 15, 1, 1);
        g.fillRect(13, 15, 1, 1);
        g.fillRect(15, 13, 1, 1);
        g.setColor(cl7);
        g.fillRect(1, 0, 1, 1);
        g.fillRect(0, 1, 1, 1);
        g.fillRect(14, 0, 1, 1);
        g.fillRect(15, 1, 1, 1);
        g.fillRect(0, 14, 1, 1);
        g.fillRect(1, 15, 1, 1);
        g.fillRect(14, 15, 1, 1);
        g.fillRect(15, 14, 1, 1);
        g.setColor(cl4);
        g.fillRect(0, 0, 1, 1);
        g.fillRect(15, 0, 1, 1);
        g.fillRect(0, 15, 1, 1);
        g.fillRect(15, 15, 1, 1);
        g.setColor(cl8);
        g.fillRect(7, 6, 2, 1);
        g.fillRect(6, 7, 4, 1);
        g.fillRect(5, 8, 6, 1);
        g.fillRect(4, 9, 8, 1);
        this.imScrollDownPressed = this.aplOwner.createImage(16, 16);
        g = this.imScrollDownPressed.getGraphics();
        g.setColor(cl5);
        g.fillRect(0, 0, 16, 16);
        g.setColor(cl6);
        g.fillRect(2, 0, 1, 1);
        g.fillRect(0, 2, 1, 1);
        g.fillRect(13, 0, 1, 1);
        g.fillRect(15, 2, 1, 1);
        g.fillRect(0, 13, 1, 1);
        g.fillRect(2, 15, 1, 1);
        g.fillRect(13, 15, 1, 1);
        g.fillRect(15, 13, 1, 1);
        g.setColor(cl7);
        g.fillRect(1, 0, 1, 1);
        g.fillRect(0, 1, 1, 1);
        g.fillRect(14, 0, 1, 1);
        g.fillRect(15, 1, 1, 1);
        g.fillRect(0, 14, 1, 1);
        g.fillRect(1, 15, 1, 1);
        g.fillRect(14, 15, 1, 1);
        g.fillRect(15, 14, 1, 1);
        g.setColor(cl4);
        g.fillRect(0, 0, 1, 1);
        g.fillRect(15, 0, 1, 1);
        g.fillRect(0, 15, 1, 1);
        g.fillRect(15, 15, 1, 1);
        g.setColor(cl8);
        g.fillRect(4, 6, 8, 1);
        g.fillRect(5, 7, 6, 1);
        g.fillRect(6, 8, 4, 1);
        g.fillRect(7, 9, 2, 1);
        this.imScrollBodyPressed = this.aplOwner.createImage(16, 1);
        g = this.imScrollBodyPressed.getGraphics();
        g.setColor(cl5);
        g.fillRect(0, 0, 16, 1);
        this.imScrollBodyBottomPressed = this.aplOwner.createImage(16, 7);
        g = this.imScrollBodyBottomPressed.getGraphics();
        g.setColor(cl5);
        g.fillRect(0, 0, 16, 7);
        g.setColor(cl6);
        g.fillRect(0, 3, 1, 1);
        g.fillRect(15, 3, 1, 1);
        g.fillRect(2, 5, 1, 1);
        g.fillRect(13, 5, 1, 1);
        g.setColor(cl7);
        g.fillRect(0, 4, 1, 1);
        g.fillRect(15, 4, 1, 1);
        g.fillRect(1, 5, 1, 1);
        g.fillRect(14, 5, 1, 1);
        g.setColor(cl4);
        g.fillRect(0, 5, 1, 1);
        g.fillRect(15, 5, 1, 1);
        g.fillRect(0, 6, 16, 1);
        this.imScrollBodyTopPressed = this.aplOwner.createImage(16, 7);
        g = this.imScrollBodyTopPressed.getGraphics();
        g.setColor(cl5);
        g.fillRect(0, 0, 16, 7);
        g.setColor(cl6);
        g.fillRect(0, 3, 1, 1);
        g.fillRect(2, 1, 1, 1);
        g.fillRect(13, 1, 1, 1);
        g.fillRect(15, 3, 1, 1);
        g.setColor(cl7);
        g.fillRect(0, 2, 1, 1);
        g.fillRect(1, 1, 1, 1);
        g.fillRect(14, 1, 1, 1);
        g.fillRect(15, 2, 1, 1);
        g.setColor(cl4);
        g.fillRect(0, 1, 1, 1);
        g.fillRect(15, 1, 1, 1);
        g.fillRect(0, 0, 16, 1);
        this.imScrollBodyMiddlePressed = this.aplOwner.createImage(16, 7);
        g = this.imScrollBodyMiddlePressed.getGraphics();
        g.setColor(cl5);
        g.fillRect(0, 0, 16, 7);
        g.setColor(cl8);
        g.fillRect(3, 1, 10, 1);
        g.fillRect(3, 3, 10, 1);
        g.fillRect(3, 5, 10, 1);
    }
}
