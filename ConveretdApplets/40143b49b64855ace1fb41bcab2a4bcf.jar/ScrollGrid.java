import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class ScrollGrid extends Panel
{
    int tx;
    int ty;
    boolean newty;
    int yty;
    int ROWty;
    int y1;
    private int CELLHEIGHT;
    private int HALF_CELLHEIGHT;
    private int PAD_X;
    private int PAD_2X;
    private int PAD_Y;
    int COL;
    int ROW;
    private String str;
    private int rowCOLWIDTH;
    private int cursorCOLWIDTH;
    public int leftCOLSWIDTH;
    public int visibleROWS;
    public int visibleWIDTH;
    public int[] AccCOLS;
    public int imageWIDTH;
    public int imageHEIGHT;
    public Dimension preferredsize;
    private Image offImage;
    private Image off2Image;
    private Image titleImage;
    private Image rowImage;
    private Image colImage;
    private Image cursorImage;
    private Graphics offGraphics;
    private Graphics off2Graphics;
    private Graphics titleGraphics;
    private Graphics colGraphics;
    private Graphics rowGraphics;
    private Graphics cursorGraphics;
    private Color fg;
    public Catalog Cat;
    public Scrollbar vert;
    public Scrollbar horz;
    private PopUpWindow Parent;
    private Font staticFont;
    
    void renderPrevious() {
        if (this.newty) {
            this.vert.setValue(this.ty);
            this.off2Graphics.copyArea(0, this.CELLHEIGHT, this.imageWIDTH, this.imageHEIGHT - this.CELLHEIGHT, 0, this.CELLHEIGHT);
            this.offGraphics.drawImage(this.off2Image, 0, 0, this);
            this.offGraphics.setColor(Color.black);
            this.offGraphics.drawImage(this.rowImage, 0, this.CELLHEIGHT, this);
            this.goDraw(0);
        }
        else {
            this.offGraphics.drawImage(this.off2Image, 0, 0, this);
        }
        this.goErase();
        this.off2Graphics.drawImage(this.offImage, 0, 0, this);
        if (this.ROW < this.ty + this.visibleROWS && this.ROW >= this.ty) {
            this.offGraphics.drawImage(this.cursorImage, this.rowCOLWIDTH + 2, (this.ROW - this.ty + 1) * this.CELLHEIGHT + (this.CELLHEIGHT / 2 - 4), Color.white, this);
            if (this.tx != 0) {
                this.offGraphics.copyArea(this.tx + this.leftCOLSWIDTH + 1, 0, this.Parent.size().width, this.imageHEIGHT, -this.tx + 1, 0);
            }
        }
    }
    
    private void goErase() {
        this.offGraphics.setColor(Color.white);
        this.offGraphics.fillRect(this.imageWIDTH + 1, 0, this.Parent.size().width, (this.visibleROWS + 1) * this.CELLHEIGHT);
    }
    
    public void renderMouse() {
        this.offGraphics.drawImage(this.off2Image, 0, 0, this);
        for (int i = 0; i < this.visibleROWS; ++i) {
            this.yty = this.ty + i;
            this.y1 = i + 1;
        }
        this.off2Graphics.drawImage(this.offImage, 0, 0, this);
        this.goMouseDraw();
        this.goErase();
        if (this.tx != 0) {
            this.offGraphics.copyArea(this.tx + this.leftCOLSWIDTH + 1, 0, this.Parent.size().width, this.imageHEIGHT, -this.tx + 1, 0);
        }
    }
    
    public void renderBack() {
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        this.cursorImage = this.createImage(10, 10);
        this.cursorGraphics = this.cursorImage.getGraphics();
        array[0] = 0;
        array[1] = 10;
        array[2] = 0;
        array2[array[3] = 0] = 0;
        array2[1] = 4;
        array2[2] = 9;
        array2[3] = 0;
        this.cursorGraphics.setColor(Color.white);
        this.cursorGraphics.fillRect(0, 0, 10, 10);
        this.cursorGraphics.setColor(Color.blue);
        this.cursorGraphics.fillPolygon(array, array2, 4);
        this.cursorGraphics.setColor(Color.black);
        this.cursorGraphics.drawPolygon(array, array2, 4);
        this.offImage = this.createImage(this.imageWIDTH + this.Parent.size().width, this.imageHEIGHT);
        this.off2Image = this.createImage(this.imageWIDTH + this.Parent.size().width, this.imageHEIGHT);
        this.titleImage = this.createImage(this.imageWIDTH + 1, this.CELLHEIGHT + 1);
        this.colImage = this.createImage(this.leftCOLSWIDTH + 1, this.imageHEIGHT - this.CELLHEIGHT);
        this.offGraphics = this.offImage.getGraphics();
        this.off2Graphics = this.off2Image.getGraphics();
        this.titleGraphics = this.titleImage.getGraphics();
        this.colGraphics = this.colImage.getGraphics();
        this.titleGraphics.setColor(Color.lightGray);
        this.titleGraphics.fillRect(0, 0, this.imageWIDTH, this.CELLHEIGHT);
        this.titleGraphics.setColor(Color.black);
        this.titleGraphics.drawRect(0, 0, this.leftCOLSWIDTH, this.CELLHEIGHT);
        this.titleGraphics.setFont(this.staticFont);
        this.offGraphics.setFont(this.Parent.DataFont);
        for (int i = 0; i < this.Cat.FieldsVisible.length; ++i) {
            this.titleGraphics.drawString(this.Parent.Cat.FIELDs[this.Cat.FieldsVisible[i]].Name, this.AccCOLS[i] + this.PAD_X, this.CELLHEIGHT - this.PAD_Y);
            this.titleGraphics.setColor(Color.lightGray);
            this.titleGraphics.fillRect(this.AccCOLS[i + 1] - this.PAD_X, 0, this.imageWIDTH - (this.AccCOLS[i + 1] - this.PAD_X), this.CELLHEIGHT);
            this.titleGraphics.setColor(Color.black);
            this.titleGraphics.drawRect(this.AccCOLS[i], 0, this.Cat.FieldsWIDTH[i], this.CELLHEIGHT);
        }
        this.colGraphics.setColor(Color.lightGray);
        this.colGraphics.fillRect(0, 0, this.rowCOLWIDTH, this.imageHEIGHT - this.CELLHEIGHT);
        this.colGraphics.setColor(Color.white);
        this.colGraphics.fillRect(this.rowCOLWIDTH, 0, this.leftCOLSWIDTH - this.rowCOLWIDTH, this.imageHEIGHT - this.CELLHEIGHT);
        this.colGraphics.setColor(Color.black);
        for (int j = 0; j < this.visibleROWS; ++j) {
            this.colGraphics.draw3DRect(0, j * this.CELLHEIGHT, this.rowCOLWIDTH, this.CELLHEIGHT, true);
            this.colGraphics.drawRect(this.rowCOLWIDTH, j * this.CELLHEIGHT, this.cursorCOLWIDTH, this.CELLHEIGHT);
        }
        this.rowImage = this.createImage(this.imageWIDTH + this.Parent.size().width, this.CELLHEIGHT);
        (this.rowGraphics = this.rowImage.getGraphics()).setColor(Color.white);
        this.rowGraphics.fillRect(0, 0, this.imageWIDTH + this.Parent.size().width, this.CELLHEIGHT);
        this.rowGraphics.setColor(Color.lightGray);
        this.rowGraphics.fillRect(0, 0, this.rowCOLWIDTH, this.CELLHEIGHT * 2);
        this.rowGraphics.setColor(Color.black);
        this.rowGraphics.drawRect(0, 0, this.rowCOLWIDTH, this.CELLHEIGHT);
        this.rowGraphics.drawRect(this.rowCOLWIDTH, 0, this.cursorCOLWIDTH, this.CELLHEIGHT);
        this.rowGraphics.setColor(Color.black);
    }
    
    private void goDraw(final int n) {
        this.yty = n + this.ty;
        this.y1 = n + 1;
        this.offGraphics.setFont(this.staticFont);
        this.offGraphics.drawString(Integer.toString(this.yty + 1), 4, (n + 2) * this.CELLHEIGHT - this.PAD_Y);
        this.offGraphics.setFont(this.Parent.DataFont);
        if (this.yty < this.Parent.ResultSet.size()) {
            for (int i = 0; i < this.Cat.FieldsVisible.length; ++i) {
                if (this.Cat.FIELDs[this.Cat.FieldsVisible[i]].Type > 0 && this.Cat.FIELDs[this.Cat.FieldsVisible[i]].Type < 4) {
                    this.fg = Color.blue;
                }
                else {
                    this.fg = Color.black;
                }
                this.offGraphics.setColor(this.fg);
                this.offGraphics.drawString(this.Parent.ResultSet.elementAt(this.yty).FIELDvalue[this.Cat.FieldsVisible[i]], this.AccCOLS[i] + this.PAD_X, (n + 2) * this.CELLHEIGHT - this.PAD_Y);
                this.offGraphics.setColor(Color.white);
                this.offGraphics.fillRect(this.AccCOLS[i] + this.Cat.FieldsWIDTH[i] - this.PAD_X, this.y1 * this.CELLHEIGHT, this.imageWIDTH - (this.AccCOLS[i] + this.Cat.FieldsWIDTH[i] - this.PAD_X), this.CELLHEIGHT);
                this.offGraphics.setColor(Color.black);
                this.offGraphics.drawRect(this.AccCOLS[i], this.y1 * this.CELLHEIGHT, this.Cat.FieldsWIDTH[i], this.CELLHEIGHT);
            }
        }
    }
    
    public ScrollGrid(final PopUpWindow parent) {
        this.tx = 0;
        this.ty = 0;
        this.newty = false;
        this.CELLHEIGHT = 15;
        this.PAD_X = 4;
        this.PAD_Y = 2;
        this.COL = -1;
        this.ROW = 0;
        this.rowCOLWIDTH = 40;
        this.cursorCOLWIDTH = 15;
        this.leftCOLSWIDTH = 70;
        this.visibleROWS = 20;
        this.visibleWIDTH = 600;
        this.imageWIDTH = 0;
        this.imageHEIGHT = 0;
        this.Parent = parent;
        this.Cat = this.Parent.Cat;
        this.leftCOLSWIDTH = this.rowCOLWIDTH + this.cursorCOLWIDTH;
        this.PAD_X = this.Parent.Parent.pPAD_X;
        this.PAD_2X = 2 * this.PAD_X;
        this.PAD_Y = this.Parent.Parent.pPAD_Y;
        this.CELLHEIGHT = this.Parent.DataMetrics.getHeight() + 2 * this.PAD_Y;
        this.HALF_CELLHEIGHT = this.CELLHEIGHT / 2;
        this.visibleWIDTH = this.Parent.Parent.pVISIBLE_WIDTH;
        this.visibleROWS = this.Parent.Parent.pVISIBLE_ROWS;
        this.imageWIDTH = this.Cat.FieldsAcWIDTH[this.Cat.FieldsAcWIDTH.length - 1];
        (this.AccCOLS = new int[this.Cat.FieldsVisible.length + 1])[0] = this.leftCOLSWIDTH;
        for (int i = 1; i <= this.Cat.FieldsAcWIDTH.length; ++i) {
            this.AccCOLS[i] = this.Cat.FieldsAcWIDTH[i - 1] + this.leftCOLSWIDTH;
        }
        this.imageWIDTH += this.leftCOLSWIDTH;
        this.imageHEIGHT = (this.visibleROWS + 1) * this.CELLHEIGHT;
        this.horz = new Scrollbar(0);
        this.vert = new Scrollbar(1);
        this.staticFont = new Font("Helvetica", this.Parent.ComponentFont.getStyle(), this.Parent.DataFont.getSize());
        this.setLayout(new BorderLayout());
        this.add("South", this.horz);
        this.add("East", this.vert);
        this.validate();
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    void resizeHorz() {
        this.horz.setValues(0, 1, 0, this.Cat.FieldsAcWIDTH.length - 1);
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.visibleWIDTH, this.imageHEIGHT + this.horz.size().height);
    }
    
    public void update(final Graphics graphics) {
        if (this.offImage == null) {
            this.renderBack();
            this.render();
        }
        graphics.drawImage(this.offImage, 0, 0, this);
    }
    
    private void goMouseDraw() {
        if (this.ROW < this.ty + this.visibleROWS && this.ROW >= this.ty) {
            this.ROWty = this.ROW - this.ty + 1;
            this.offGraphics.drawImage(this.cursorImage, this.rowCOLWIDTH + 2, this.ROWty * this.CELLHEIGHT + (this.CELLHEIGHT / 2 - 4), Color.white, this);
            if (this.COL >= 0) {
                this.str = this.Parent.ResultSet.elementAt(this.ROW).FIELDvalue[this.Cat.FieldsVisible[this.COL]];
                this.offGraphics.setColor(new Color(100, 200, 200));
                if (this.Parent.DataMetrics.stringWidth(this.str) >= this.Cat.FieldsWIDTH[this.COL] - 2) {
                    this.offGraphics.fillRect(this.AccCOLS[this.COL], this.ROWty * this.CELLHEIGHT, this.Parent.DataMetrics.stringWidth(this.str) + this.PAD_2X, this.CELLHEIGHT);
                    this.offGraphics.setColor(Color.black);
                    this.offGraphics.drawString(this.Parent.ResultSet.elementAt(this.ROW).FIELDvalue[this.Cat.FieldsVisible[this.COL]], this.AccCOLS[this.COL] + this.PAD_X, (this.ROWty + 1) * this.CELLHEIGHT - this.PAD_Y);
                    this.offGraphics.drawRect(this.AccCOLS[this.COL], this.ROWty * this.CELLHEIGHT, this.Parent.DataMetrics.stringWidth(this.str) + this.PAD_2X, this.CELLHEIGHT);
                    return;
                }
                this.offGraphics.fillRect(this.AccCOLS[this.COL], this.ROWty * this.CELLHEIGHT, this.Cat.FieldsWIDTH[this.COL], this.CELLHEIGHT);
                this.offGraphics.setColor(Color.black);
                this.offGraphics.drawString(this.Parent.ResultSet.elementAt(this.ROW).FIELDvalue[this.Cat.FieldsVisible[this.COL]], this.AccCOLS[this.COL] + this.PAD_X, (this.ROWty + 1) * this.CELLHEIGHT - this.PAD_Y);
                this.offGraphics.drawRect(this.AccCOLS[this.COL], this.ROWty * this.CELLHEIGHT, this.Cat.FieldsWIDTH[this.COL], this.CELLHEIGHT);
            }
        }
    }
    
    public void render() {
        this.vert.setValue(this.ty);
        this.offGraphics.setColor(Color.white);
        this.offGraphics.fillRect(0, 0, this.imageWIDTH + this.Parent.size().width, this.imageHEIGHT);
        this.offGraphics.setColor(Color.black);
        this.offGraphics.drawRect(0, 0, this.imageWIDTH + this.Parent.size().width, this.imageHEIGHT);
        this.offGraphics.drawImage(this.titleImage, 0, 0, this);
        this.offGraphics.drawImage(this.colImage, 0, this.CELLHEIGHT, this);
        for (int i = 0; i < this.visibleROWS; ++i) {
            this.goDraw(i);
        }
        this.off2Graphics.drawImage(this.offImage, 0, 0, this);
        this.goMouseDraw();
        this.goErase();
        if (this.tx != 0) {
            this.offGraphics.copyArea(this.tx + this.leftCOLSWIDTH + 1, 0, this.Parent.size().width, this.imageHEIGHT, -this.tx + 1, 0);
        }
    }
    
    public void resetVisibleWIDTH() {
        this.visibleWIDTH = this.size().width;
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.visibleWIDTH, this.imageHEIGHT + this.horz.size().height);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this.vert) {
            if (event.id > 600 && event.id < 605) {
                if (this.vert.getValue() == this.ty + 1) {
                    this.newty = true;
                    this.ty = this.vert.getValue();
                    this.renderNext();
                    this.repaint();
                }
                else if (this.vert.getValue() == this.ty - 1) {
                    this.newty = true;
                    this.ty = this.vert.getValue();
                    this.renderPrevious();
                    this.repaint();
                }
                else if (this.vert.getValue() != this.ty) {
                    this.ty = this.vert.getValue();
                    this.render();
                    this.repaint();
                }
            }
            else if (event.id == 605 && this.vert.getValue() != this.ty) {
                this.ty = this.vert.getValue();
                this.render();
                this.repaint();
            }
        }
        else if (event.target == this.horz) {
            if (event.id > 600 && event.id < 605) {
                if (this.horz.getValue() < 1) {
                    this.tx = 0;
                }
                else {
                    this.tx = this.Cat.FieldsAcWIDTH[this.horz.getValue() - 1];
                }
                this.render();
                this.repaint();
            }
            else if (event.id == 605 && this.horz.getValue() != this.tx) {
                if (this.horz.getValue() < 1) {
                    this.tx = 0;
                }
                else {
                    this.tx = this.Cat.FieldsAcWIDTH[this.horz.getValue() - 1];
                }
                this.render();
                this.repaint();
            }
        }
        else if (event.id == 501) {
            int col = 0;
            if (event.y > this.CELLHEIGHT) {
                if (event.y / this.CELLHEIGHT + this.ty < this.ty + this.Parent.ResultSet.size() + 1) {
                    this.ROW = event.y / this.CELLHEIGHT + this.ty - 1;
                }
                else {
                    this.ROW = this.Parent.ResultSet.size() - 1;
                }
            }
            if (event.x > this.leftCOLSWIDTH) {
                while (event.x + this.tx - this.leftCOLSWIDTH > this.Cat.FieldsAcWIDTH[col] && col < this.Cat.FieldsAcWIDTH.length) {
                    ++col;
                }
                this.COL = col;
            }
            else {
                this.COL = -1;
            }
            this.renderMouse();
            this.repaint();
        }
        return super.handleEvent(event);
    }
    
    void renderNext() {
        if (this.newty) {
            this.vert.setValue(this.ty);
            this.off2Graphics.copyArea(0, 2 * this.CELLHEIGHT, this.imageWIDTH, this.imageHEIGHT - 2 * this.CELLHEIGHT, 0, -this.CELLHEIGHT);
            this.offGraphics.drawImage(this.off2Image, 0, 0, this);
            this.offGraphics.setColor(Color.black);
            this.offGraphics.drawImage(this.rowImage, 0, this.visibleROWS * this.CELLHEIGHT, this);
            this.goDraw(this.visibleROWS - 1);
        }
        else {
            this.offGraphics.drawImage(this.off2Image, 0, 0, this);
        }
        this.goErase();
        this.off2Graphics.drawImage(this.offImage, 0, 0, this);
        if (this.ROW < this.ty + this.visibleROWS && this.ROW >= this.ty) {
            this.offGraphics.drawImage(this.cursorImage, this.rowCOLWIDTH + 2, (this.ROW - this.ty + 1) * this.CELLHEIGHT + (this.CELLHEIGHT / 2 - 4), Color.white, this);
            if (this.tx != 0) {
                this.offGraphics.copyArea(this.tx + this.leftCOLSWIDTH + 1, 0, this.Parent.size().width, this.imageHEIGHT, -this.tx + 1, 0);
            }
        }
    }
    
    void resizeVert() {
        this.vert.setValues(0, 1, 0, this.Parent.Cat.RECORDs.size() - this.visibleROWS);
    }
}
