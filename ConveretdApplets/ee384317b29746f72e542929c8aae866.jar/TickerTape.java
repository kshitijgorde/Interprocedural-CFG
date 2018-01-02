import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class TickerTape
{
    private int yPosition1;
    private int yPosition2;
    private Font font1;
    private Font font2;
    private int fontSize;
    private Color foreColor;
    private Color backColor;
    private Color timeColor;
    private Color positiveColor;
    private Color negativeColor;
    private Color noChgColor;
    private int horizontalSpacing;
    private int increment;
    private int fastIncrement;
    private int lastIncrement;
    int width;
    int height;
    private boolean forward;
    private int xBegin;
    private int indexBegin;
    private String[] dataArray1;
    private String[] dataArray2;
    private String[] tempDataArray1;
    private int[] columnSize;
    private Applet appletComponent;
    private Graphics scratchGrfx;
    public Image scratchImage;
    private Image upImage;
    private Image downImage;
    private Image noChgImage;
    int imageWidth;
    int imageHeight;
    
    TickerTape(final Applet appletComponent) {
        this.yPosition2 = 25;
        this.increment = 2;
        this.lastIncrement = 2;
        this.forward = true;
        this.xBegin = 0;
        this.indexBegin = 0;
        this.appletComponent = appletComponent;
        this.width = appletComponent.size().width;
        this.height = appletComponent.size().height;
        if (this.scratchGrfx == null) {
            this.scratchImage = appletComponent.createImage(this.width, this.height);
            this.scratchGrfx = this.scratchImage.getGraphics();
        }
    }
    
    public void setImages(final String s, final String s2, final String s3) {
        this.upImage = this.loadImage(s);
        this.downImage = this.loadImage(s2);
        this.noChgImage = this.loadImage(s3);
        this.imageWidth = this.upImage.getWidth(this.appletComponent);
        this.imageHeight = this.upImage.getHeight(this.appletComponent);
    }
    
    private Image loadImage(final String s) {
        try {
            final Image image = this.appletComponent.getImage(this.appletComponent.getDocumentBase(), s);
            image.getHeight(this.appletComponent);
            while ((this.appletComponent.checkImage(image, this.appletComponent) & 0x20) == 0x0) {}
            return image;
        }
        catch (Exception ex) {
            this.scratchGrfx.drawString(ex.toString(), 10, 20);
            return this.appletComponent.createImage(1, 1);
        }
    }
    
    public void setYPositions(final String s, final String s2) {
        this.yPosition1 = Integer.valueOf(s);
        this.yPosition2 = Integer.valueOf(s2);
    }
    
    public void setFonts(final String s, final String s2, final String s3) {
        this.fontSize = Integer.valueOf(s3);
        this.font1 = new Font(s, 1, this.fontSize);
        this.font2 = new Font(s2, 0, this.fontSize);
    }
    
    public void setColors(final String clr, final String clr2, final String clr3, final String clr4, final String clr5, final String clr6) {
        this.foreColor = Convert.string2Color(clr);
        this.backColor = Convert.string2Color(clr2);
        this.timeColor = Convert.string2Color(clr3);
        this.positiveColor = Convert.string2Color(clr4);
        this.negativeColor = Convert.string2Color(clr5);
        this.noChgColor = Convert.string2Color(clr6);
    }
    
    public void setHorizontalSpacing(final String s) {
        this.horizontalSpacing = Integer.valueOf(s);
    }
    
    public void setMoveIncrement(final String s) {
        this.fastIncrement = Integer.valueOf(s);
    }
    
    private void printText() {
        if (this.xBegin > 0) {
            this.indexBegin -= 2;
            if (this.indexBegin < 0) {
                this.indexBegin = this.columnSize.length - 2;
            }
            this.xBegin -= this.columnSize[this.indexBegin] + this.columnSize[this.indexBegin + 1];
        }
        if (this.indexBegin > this.dataArray1.length - 1) {
            this.indexBegin = 0;
            this.xBegin = 0;
        }
        int i = this.xBegin;
        int indexBegin = this.indexBegin;
        final int length = this.dataArray1.length;
        this.tempDataArray1 = new String[length];
        System.arraycopy(this.dataArray1, 0, this.tempDataArray1, 0, length);
        do {
            this.scratchGrfx.setColor(this.foreColor);
            this.scratchGrfx.setFont(this.font1);
            final int n = i + this.columnSize[indexBegin] + this.columnSize[indexBegin + 1] - this.imageWidth - this.horizontalSpacing + 3;
            if (this.dataArray1[indexBegin + 1].indexOf(" 0.00") > -1) {
                this.scratchGrfx.setColor(this.noChgColor);
                this.scratchGrfx.drawString(this.dataArray1[indexBegin], i + 12, this.yPosition1);
                this.scratchGrfx.drawString(this.dataArray2[indexBegin], i + 12, this.yPosition2);
                this.scratchGrfx.drawString(this.dataArray1[indexBegin + 1], i + 6 + this.columnSize[indexBegin], this.yPosition1);
                this.scratchGrfx.drawString(this.dataArray2[indexBegin + 1], i + 6 + this.columnSize[indexBegin], this.yPosition2);
            }
            else if (this.dataArray2[indexBegin + 1].indexOf(":") > -1) {
                this.scratchGrfx.setColor(this.timeColor);
                this.scratchGrfx.drawString(this.dataArray1[indexBegin + 1], i + 6 + this.columnSize[indexBegin], this.yPosition1);
                this.scratchGrfx.drawString(this.dataArray2[indexBegin + 1], i + 6 + this.columnSize[indexBegin], this.yPosition2);
            }
            else if (this.dataArray1[indexBegin + 1].indexOf("-") > -1) {
                this.scratchGrfx.setColor(this.negativeColor);
                this.tempDataArray1[indexBegin + 1] = this.tempDataArray1[indexBegin + 1].replace('-', ' ');
                this.scratchGrfx.drawString(this.dataArray1[indexBegin], i + 12, this.yPosition1);
                this.scratchGrfx.drawString(this.dataArray2[indexBegin], i + 12, this.yPosition2);
                this.scratchGrfx.drawString(this.tempDataArray1[indexBegin + 1], i + 6 + this.columnSize[indexBegin], this.yPosition1);
                this.scratchGrfx.drawString(this.dataArray2[indexBegin + 1], i + 6 + this.columnSize[indexBegin], this.yPosition2);
                this.scratchGrfx.drawImage(this.downImage, n + 6, this.yPosition1, null);
            }
            else {
                this.scratchGrfx.setColor(this.positiveColor);
                this.scratchGrfx.drawString(this.dataArray1[indexBegin], i + 12, this.yPosition1);
                this.scratchGrfx.drawString(this.dataArray2[indexBegin], i + 12, this.yPosition2);
                this.scratchGrfx.drawString(this.dataArray1[indexBegin + 1], i + 6 + this.columnSize[indexBegin], this.yPosition1);
                this.scratchGrfx.drawString(this.dataArray2[indexBegin + 1], i + 6 + this.columnSize[indexBegin], this.yPosition2);
                this.scratchGrfx.drawImage(this.upImage, n + 6, this.yPosition1, null);
            }
            final int n2 = indexBegin;
            indexBegin += 2;
            if (indexBegin >= this.columnSize.length) {
                indexBegin = 0;
            }
            i = i + this.columnSize[n2] + this.columnSize[n2 + 1];
            if (i < 0) {
                this.indexBegin = indexBegin;
                this.xBegin = i;
            }
        } while (i <= this.width);
    }
    
    public void drawMoMen() {
        if (this.forward) {
            this.xBegin -= this.increment;
        }
        else {
            this.xBegin += this.increment;
        }
        this.scratchGrfx.setColor(this.backColor);
        this.scratchGrfx.fillRect(0, 0, this.width, this.height);
        this.scratchGrfx.setColor(this.foreColor);
        this.printText();
    }
    
    public void fastMove() {
        if (this.increment == 0) {
            this.lastIncrement = this.fastIncrement;
            return;
        }
        this.increment = this.fastIncrement;
    }
    
    public void slowMove() {
        if (this.increment == 0) {
            this.lastIncrement = 2;
            return;
        }
        this.increment = 2;
    }
    
    public void startMove() {
        this.increment = this.lastIncrement;
    }
    
    public void stopMove() {
        this.lastIncrement = this.increment;
        this.increment = 0;
    }
    
    public void moveForward() {
        this.forward = true;
    }
    
    public void moveBackward() {
        this.forward = false;
    }
    
    public void setDataStream(final String[] dataArray1, final String[] dataArray2) {
        this.dataArray1 = dataArray1;
        this.dataArray2 = dataArray2;
        this.columnSize = this.computeColumnSize();
    }
    
    public int[] computeColumnSize() {
        final int[] array = new int[this.dataArray1.length];
        for (int i = 0; i < this.dataArray1.length; ++i) {
            Font font;
            int horizontalSpacing;
            if (i % 2 == 0) {
                font = this.font1;
                horizontalSpacing = this.horizontalSpacing;
            }
            else {
                font = this.font2;
                horizontalSpacing = this.horizontalSpacing + this.imageWidth;
            }
            final int stringWidth = this.appletComponent.getFontMetrics(font).stringWidth(this.dataArray1[i]);
            final int stringWidth2 = this.appletComponent.getFontMetrics(this.font2).stringWidth(this.dataArray2[i]);
            if (stringWidth > stringWidth2) {
                array[i] = stringWidth + horizontalSpacing;
            }
            else {
                array[i] = stringWidth2 + horizontalSpacing;
            }
        }
        return array;
    }
}
