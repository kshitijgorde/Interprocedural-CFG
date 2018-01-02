import java.awt.Container;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Scrollbar;
import java.util.Vector;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class Grid extends Panel
{
    private Dimension dMinimum;
    private Font fFont;
    private FontMetrics fMetrics;
    private Graphics gImage;
    private Image iImage;
    private int iWidth;
    private int iHeight;
    private int iRowHeight;
    private int iFirstRow;
    private int iGridWidth;
    private int iGridHeight;
    private int iX;
    private int iY;
    private String[] sColHead;
    private Vector vData;
    private int[] iColWidth;
    private int iColCount;
    private int iRowCount;
    private Scrollbar sbHoriz;
    private Scrollbar sbVert;
    private int iSbWidth;
    private int iSbHeight;
    private boolean bDrag;
    private int iXDrag;
    private int iColDrag;
    
    public Grid() {
        this.fFont = new Font("Dialog", 0, 12);
        this.setLayout(null);
        this.add(this.sbHoriz = new Scrollbar(0));
        this.add(this.sbVert = new Scrollbar(1));
    }
    
    public void setMinimumSize(final Dimension dMinimum) {
        this.dMinimum = dMinimum;
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
        this.iSbHeight = this.sbHoriz.preferredSize().height;
        this.iSbWidth = this.sbVert.preferredSize().width;
        this.iHeight = n4 - this.iSbHeight;
        this.iWidth = n3 - this.iSbWidth;
        this.sbHoriz.reshape(0, this.iHeight, this.iWidth, this.iSbHeight);
        this.sbVert.reshape(this.iWidth, 0, this.iSbWidth, this.iHeight);
        this.adjustScroll();
        this.iImage = null;
        this.repaint();
    }
    
    public void setHead(final String[] array) {
        this.vData = new Vector();
        this.iColCount = array.length;
        this.sColHead = new String[this.iColCount];
        this.iColWidth = new int[this.iColCount];
        for (int i = 0; i < this.iColCount; ++i) {
            this.sColHead[i] = array[i];
            this.iColWidth[i] = 100;
        }
        this.iRowCount = 0;
        this.iRowHeight = 0;
    }
    
    public void addRow(final String[] array) {
        if (array.length != this.iColCount) {
            return;
        }
        final String[] array2 = new String[this.iColCount];
        for (int i = 0; i < this.iColCount; ++i) {
            array2[i] = array[i];
        }
        this.vData.addElement(array2);
        ++this.iRowCount;
        this.adjustScroll();
        this.repaint();
    }
    
    void adjustScroll() {
        if (this.iRowHeight == 0) {
            return;
        }
        int iGridWidth = 0;
        for (int i = 0; i < this.iColCount; ++i) {
            iGridWidth += this.iColWidth[i];
        }
        this.iGridWidth = iGridWidth;
        this.iGridHeight = this.iRowHeight * (this.iRowCount + 1);
        this.sbHoriz.setValues(this.iX, this.iWidth, 0, this.iGridWidth);
        this.sbVert.setValues(this.iY / this.iRowHeight, this.iHeight / this.iRowHeight, 0, this.iRowCount + 1);
        this.iX = this.sbHoriz.getValue();
        this.iY = this.iRowHeight * this.sbVert.getValue();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 601:
            case 602:
            case 603:
            case 604:
            case 605: {
                this.iX = this.sbHoriz.getValue();
                this.iY = this.iRowHeight * this.sbVert.getValue();
                this.repaint();
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        if (this.iWidth <= 0 || this.iHeight <= 0) {
            return;
        }
        graphics.setColor(Color.lightGray);
        graphics.fillRect(this.iWidth, this.iHeight, this.iSbWidth, this.iSbHeight);
        if (this.iImage == null) {
            this.iImage = this.createImage(this.iWidth, this.iHeight);
            (this.gImage = this.iImage.getGraphics()).setFont(this.fFont);
            if (this.fMetrics == null) {
                this.fMetrics = this.gImage.getFontMetrics();
            }
        }
        if (this.iRowHeight == 0) {
            this.iRowHeight = this.fMetrics.getHeight() + 4;
            for (int i = 0; i < this.iColCount; ++i) {
                this.calcAutoWidth(i);
            }
            this.adjustScroll();
        }
        this.gImage.setColor(Color.white);
        this.gImage.fillRect(0, 0, this.iWidth, this.iHeight);
        this.gImage.setColor(Color.darkGray);
        this.gImage.drawLine(0, this.iRowHeight, this.iWidth, this.iRowHeight);
        int n = -this.iX;
        for (int j = 0; j < this.iColCount; ++j) {
            final int n2 = this.iColWidth[j];
            this.gImage.setColor(Color.lightGray);
            this.gImage.fillRect(n + 1, 0, n2 - 2, this.iRowHeight);
            this.gImage.setColor(Color.black);
            this.gImage.drawString(this.sColHead[j], n + 2, this.iRowHeight - 5);
            this.gImage.setColor(Color.darkGray);
            this.gImage.drawLine(n + n2 - 1, 0, n + n2 - 1, this.iRowHeight - 1);
            this.gImage.setColor(Color.white);
            this.gImage.drawLine(n + n2, 0, n + n2, this.iRowHeight - 1);
            n += n2;
        }
        this.gImage.setColor(Color.lightGray);
        this.gImage.fillRect(0, 0, 1, this.iRowHeight);
        this.gImage.fillRect(n + 1, 0, this.iWidth - n, this.iRowHeight);
        this.gImage.drawLine(0, 0, 0, this.iRowHeight - 1);
        int k = this.iRowHeight + 1 - this.iY;
        int iFirstRow = 0;
        while (k < this.iRowHeight + 1) {
            ++iFirstRow;
            k += this.iRowHeight;
        }
        this.iFirstRow = iFirstRow;
        for (int n3 = this.iRowHeight + 1; n3 < this.iHeight && iFirstRow < this.iRowCount; ++iFirstRow, n3 += this.iRowHeight) {
            int n4 = -this.iX;
            for (int l = 0; l < this.iColCount; ++l) {
                final int n5 = this.iColWidth[l];
                final Color white = Color.white;
                final Color black = Color.black;
                this.gImage.setColor(white);
                this.gImage.fillRect(n4, n3, n5 - 1, this.iRowHeight - 1);
                this.gImage.setColor(black);
                this.gImage.drawString(this.getDisplay(l, iFirstRow), n4 + 2, n3 + this.iRowHeight - 5);
                this.gImage.setColor(Color.lightGray);
                this.gImage.drawLine(n4 + n5 - 1, n3, n4 + n5 - 1, n3 + this.iRowHeight - 1);
                this.gImage.drawLine(n4, n3 + this.iRowHeight - 1, n4 + n5 - 1, n3 + this.iRowHeight - 1);
                n4 += n5;
            }
            this.gImage.setColor(Color.white);
            this.gImage.fillRect(n4, n3, this.iWidth - n4, this.iRowHeight - 1);
        }
        graphics.drawImage(this.iImage, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void setCursor(final int cursor) {
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        ((Frame)container).setCursor(cursor);
    }
    
    public boolean mouseMove(final Event event, int n, final int n2) {
        if (n2 <= this.iRowHeight) {
            final int n3 = n;
            int iColDrag;
            for (n = n + this.iX - this.iGridWidth, iColDrag = this.iColCount - 1; iColDrag >= 0 && (n <= -7 || n >= 7); n += this.iColWidth[iColDrag], --iColDrag) {}
            if (iColDrag >= 0) {
                if (!this.bDrag) {
                    this.setCursor(11);
                    this.bDrag = true;
                    this.iXDrag = n3 - this.iColWidth[iColDrag];
                    this.iColDrag = iColDrag;
                }
                return true;
            }
        }
        return this.mouseExit(event, n, n2);
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.bDrag && n < this.iWidth) {
            int n3 = n - this.iXDrag;
            if (n3 < 0) {
                n3 = 0;
            }
            this.iColWidth[this.iColDrag] = n3;
            this.adjustScroll();
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.bDrag) {
            this.setCursor(0);
            this.bDrag = false;
        }
        return true;
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public Dimension getPreferredSize() {
        return this.minimumSize();
    }
    
    public Dimension getMinimumSize() {
        return this.minimumSize();
    }
    
    public Dimension minimumSize() {
        return this.dMinimum;
    }
    
    private void calcAutoWidth(final int n) {
        int n2 = Math.max(10, this.fMetrics.stringWidth(this.sColHead[n]));
        for (int i = 0; i < this.iRowCount; ++i) {
            n2 = Math.max(n2, this.fMetrics.stringWidth(((String[])this.vData.elementAt(i))[n]));
        }
        this.iColWidth[n] = n2 + 6;
    }
    
    private String getDisplay(final int n, final int n2) {
        return ((String[])this.vData.elementAt(n2))[n];
    }
    
    private String get(final int n, final int n2) {
        return ((String[])this.vData.elementAt(n2))[n];
    }
}
