import java.util.Random;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class Com2meFWg2 extends Canvas
{
    private Image image;
    private Com2meFWPP[] pieces;
    private int xSize;
    private int ySize;
    private int xOffset;
    private int yOffset;
    private int imageXSize;
    private int imageYSize;
    private int selectedPiece;
    private int xDiff;
    private int yDiff;
    private Image bgImage;
    private Image memImage;
    private Image imageAccess;
    private boolean bgIsValid;
    private Color pictureGround;
    private Com2meFWO gameStatus;
    private int pieces_ready;
    private Font textFont;
    private FontMetrics textMetrix;
    private Com2meFWCT textCT;
    
    public Com2meFWg2(final Com2meFWO gameStatus, final Image imageAccess) {
        this.gameStatus = gameStatus;
        this.xSize = 0;
        this.ySize = 0;
        this.xOffset = 0;
        this.yOffset = 0;
        final boolean b = false;
        this.imageYSize = (b ? 1 : 0);
        this.imageXSize = (b ? 1 : 0);
        this.selectedPiece = -1;
        this.bgIsValid = false;
        this.pictureGround = Color.gray;
        this.pieces_ready = 0;
        this.imageAccess = imageAccess;
        this.textFont = new Font("Arial", 0, 13);
        this.textMetrix = this.getFontMetrics(this.textFont);
        this.textCT = new Com2meFWCT();
    }
    
    public void drawOurText(final Graphics graphics) {
        final Dimension size = this.size();
        graphics.setColor(this.getForeground());
        graphics.setFont(this.textFont);
        final String text1 = this.textCT.getText1();
        final String text2 = this.textCT.getText2();
        final String text3 = this.textCT.getText3();
        final String text4 = this.textCT.getText4();
        graphics.drawString(text1, 3, this.textMetrix.getAscent() + 2);
        graphics.drawString(text2, size.width - this.textMetrix.stringWidth(text2) - 3, this.textMetrix.getAscent() + 2);
        graphics.drawString(text3, 3, size.height - this.textMetrix.getDescent() - 2);
        graphics.drawString(text4, size.width - this.textMetrix.stringWidth(text4) - 3, size.height - this.textMetrix.getDescent() - 2);
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public int getPiecesReady() {
        return this.pieces_ready;
    }
    
    public Dimension minimumSize() {
        return this.preferredSize();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.pieces == null) {
            return true;
        }
        for (int i = this.pieces.length - 1; i >= 0; --i) {
            if (this.pieces[i].isInside(n, n2) && this.pieces[i].isMoveable()) {
                this.selectedPiece = i;
                this.xDiff = n - this.pieces[i].getXPos();
                this.yDiff = n2 - this.pieces[i].getYPos();
                this.bgIsValid = false;
                return true;
            }
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.selectedPiece >= 0) {
            this.pieces[this.selectedPiece].moveTo(n - this.xDiff, n2 - this.yDiff, this.xSize, this.ySize);
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.selectedPiece >= 0) {
            this.pieces[this.selectedPiece].checkPosition();
            if (this.pieces[this.selectedPiece].isMoveable()) {
                final Com2meFWPP com2meFWPP = this.pieces[this.selectedPiece];
                int i;
                for (i = this.selectedPiece; i < this.pieces.length - 1; ++i) {
                    this.pieces[i] = this.pieces[i + 1];
                }
                this.pieces[i] = com2meFWPP;
                this.selectedPiece = -1;
                this.bgIsValid = false;
            }
            else {
                final Com2meFWPP com2meFWPP2 = this.pieces[this.selectedPiece];
                for (int j = this.selectedPiece; j > 0; --j) {
                    this.pieces[j] = this.pieces[j - 1];
                }
                this.pieces[0] = com2meFWPP2;
                this.selectedPiece = -1;
                this.bgIsValid = false;
                this.repaint();
                ++this.pieces_ready;
                this.gameStatus.setObject("play");
            }
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        this.paintBackground(graphics, -1);
        this.drawOurText(graphics);
    }
    
    private void paintBackground(final Graphics graphics, final int n) {
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.xSize, this.ySize);
        if (this.image != null) {
            graphics.setColor(this.pictureGround);
            graphics.fillRect(this.xOffset, this.yOffset, this.imageXSize, this.imageYSize);
        }
        if (this.pieces != null) {
            int n2 = 0;
            for (int i = 0; i < this.pieces.length; ++i) {
                if (n != i) {
                    this.pieces[i].paint(graphics);
                }
                if (this.pieces[i].isMoveable()) {
                    ++n2;
                }
            }
            if (n2 == 0) {
                this.gameStatus.setObject("ready");
            }
        }
    }
    
    public Dimension preferredSize() {
        return new Dimension(600, 450);
    }
    
    public void reshape(final int n, final int n2, final int xSize, final int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.xOffset = (this.xSize - this.imageXSize) / 2;
        this.yOffset = (this.ySize - this.imageYSize) / 2;
        if (this.xSize > 0 && this.ySize > 0) {
            if (this.pieces != null) {
                for (int i = 0; i < this.pieces.length; ++i) {
                    this.pieces[i].setOffset(this.xOffset, this.yOffset);
                    this.pieces[i].correctPosition(this.xSize, this.ySize);
                }
            }
            this.bgImage = this.createImage(this.xSize, this.ySize);
            this.memImage = this.createImage(this.xSize, this.ySize);
        }
        this.bgIsValid = false;
        super.reshape(n, n2, xSize, ySize);
    }
    
    public void setNewPieces(final Image image, final Com2meFWPP[] pieces) {
        this.image = image;
        this.pieces = pieces;
        this.imageXSize = image.getWidth(this);
        this.imageYSize = image.getHeight(this);
        this.xOffset = (this.xSize - this.imageXSize) / 2;
        this.yOffset = (this.ySize - this.imageYSize) / 2;
        final Random random = new Random();
        for (int i = 0; i < pieces.length; ++i) {
            pieces[i].setOffset(this.xOffset, this.yOffset);
            pieces[i].moveTo((int)(random.nextFloat() * (this.xSize - pieces[i].getXSize())), (int)(random.nextFloat() * (this.ySize - pieces[i].getYSize())));
            pieces[i].setVisible(true);
        }
        this.selectedPiece = -1;
        this.bgIsValid = false;
        this.repaint();
    }
    
    public void setPictureground(final Color pictureGround) {
        this.pictureGround = pictureGround;
    }
    
    public void update(final Graphics graphics) {
        if (!this.bgIsValid) {
            this.paintBackground(this.bgImage.getGraphics(), this.selectedPiece);
            this.bgIsValid = true;
        }
        final Graphics graphics2 = this.memImage.getGraphics();
        graphics2.drawImage(this.bgImage, 0, 0, this);
        this.drawOurText(graphics2);
        if (this.selectedPiece >= 0) {
            this.pieces[this.selectedPiece].paint(graphics2);
        }
        graphics.drawImage(this.memImage, 0, 0, this);
        graphics.drawImage(this.imageAccess, 0, 0, this);
    }
}
