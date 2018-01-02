import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class Army
{
    public Vector canLinkTo;
    public OrMask[] orMasks;
    public int mySmallestX;
    public int mySmallestY;
    public int myLargestX;
    public int myLargestY;
    public int x;
    public int y;
    public int myOrientation;
    public Image myImage;
    private int[] pix;
    private int w;
    private int h;
    private int ps;
    private int psplus;
    
    Army(final int mySmallestX, final int mySmallestY, final int myLargestX, final int myLargestY, final OrMask orMask, final Puzzle image) {
        this.mySmallestX = mySmallestX;
        this.myLargestX = myLargestX;
        this.mySmallestY = mySmallestY;
        this.myLargestY = myLargestY;
        this.canLinkTo = new Vector();
        (this.orMasks = new OrMask[1])[0] = orMask;
        this.setImage(image);
    }
    
    Army(final int n, final int n2, final OrMask orMask, final Puzzle puzzle) {
        this(n, n2, n, n2, orMask, puzzle);
    }
    
    public void setLocation(final int x, final int y, final int n, final Puzzle puzzle) {
        this.x = x;
        this.y = y;
        this.setOrientation(puzzle, n);
    }
    
    Army(final Army army, final Army army2, final Puzzle image) {
        army.pix = null;
        army2.pix = null;
        army.myImage.flush();
        this.mySmallestX = Math.min(army.mySmallestX, army2.mySmallestX);
        this.myLargestX = Math.max(army.myLargestX, army2.myLargestX);
        this.mySmallestY = Math.min(army.mySmallestY, army2.mySmallestY);
        this.myLargestY = Math.max(army.myLargestY, army2.myLargestY);
        this.x = Math.min(army.x, army2.x);
        this.y = Math.min(army.y, army2.y);
        this.canLinkTo = new Vector();
        for (int i = 0; i < army.canLinkTo.size(); ++i) {
            if (army.canLinkTo.elementAt(i) != army2) {
                this.canLinkTo.addElement(army.canLinkTo.elementAt(i));
                ((Army)army.canLinkTo.elementAt(i)).notifyMerge(army, army2, this);
            }
        }
        army.canLinkTo = null;
        for (int j = 0; j < army2.canLinkTo.size(); ++j) {
            if (army2.canLinkTo.elementAt(j) != army && !this.canLinkTo.contains(army2.canLinkTo.elementAt(j))) {
                this.canLinkTo.addElement(army2.canLinkTo.elementAt(j));
                ((Army)army2.canLinkTo.elementAt(j)).notifyMerge(army, army2, this);
            }
        }
        army2.canLinkTo = null;
        this.orMasks = new OrMask[army.orMasks.length + army2.orMasks.length];
        this.ps = Global.pieceSize;
        int n = 0;
        for (int k = 0; k < army.orMasks.length; ++k) {
            this.orMasks[n] = army.orMasks[k];
            this.orMasks[n++].shiftBy((army.mySmallestX - this.mySmallestX) * this.ps, (army.mySmallestY - this.mySmallestY) * this.ps);
        }
        army.orMasks = null;
        for (int l = 0; l < army2.orMasks.length; ++l) {
            this.orMasks[n] = army2.orMasks[l];
            this.orMasks[n++].shiftBy((army2.mySmallestX - this.mySmallestX) * this.ps, (army2.mySmallestY - this.mySmallestY) * this.ps);
        }
        army2.orMasks = null;
        this.setImage(image);
        this.setOrientation(image, army.myOrientation);
    }
    
    private void setImage(final Puzzle puzzle) {
        final int deep = Global.deep;
        this.ps = Global.pieceSize;
        this.psplus = this.ps + 2 * deep;
        this.w = (this.myLargestX - this.mySmallestX + 1) * this.ps + 2 * deep;
        this.h = (this.myLargestY - this.mySmallestY + 1) * this.ps + 2 * deep;
        this.pix = puzzle.getPixels(this.mySmallestX, this.mySmallestY, this.myLargestX, this.myLargestY);
        for (int i = 0; i < this.orMasks.length; ++i) {
            int n = this.orMasks[i].xOffset + (this.orMasks[i].yOffset + this.orMasks[i].ymin) * this.w;
            int n2 = this.orMasks[i].ymin * this.orMasks[i].getWidth();
            for (int j = this.orMasks[i].ymin; j < this.orMasks[i].ymax; ++j) {
                for (int k = this.orMasks[i].xmin; k < this.orMasks[i].xmax; ++k) {
                    this.pix[n + k] |= this.orMasks[i].theMask[n2 + k];
                }
                n += this.w;
                n2 += this.orMasks[i].getWidth();
            }
        }
        this.myImage = puzzle.createImage(new MemoryImageSource(this.w, this.h, this.pix, 0, this.w));
        this.myOrientation = 0;
    }
    
    public Rectangle rotateMe(final Puzzle puzzle) {
        return this.rotateMe(puzzle, true);
    }
    
    public Rectangle rotateMe(final Puzzle puzzle, final boolean b) {
        final Rectangle bounding = this.getBounding();
        final Point myCenter = this.myCenter();
        if (this.myOrientation == 0 || this.myOrientation == 2) {
            this.pix = Global.rotateSet(this.pix, this.h, this.w);
            this.myImage = puzzle.createImage(new MemoryImageSource(this.h, this.w, this.pix, 0, this.h));
        }
        else {
            this.pix = Global.rotateSet(this.pix, this.w, this.h);
            this.myImage = puzzle.createImage(new MemoryImageSource(this.w, this.h, this.pix, 0, this.w));
        }
        ++this.myOrientation;
        if (this.myOrientation == 4) {
            this.myOrientation = 0;
        }
        final Point myCenter2 = this.myCenter();
        if (b) {
            this.moveMeBy(myCenter.x - myCenter2.x, myCenter.y - myCenter2.y);
        }
        return bounding.union(this.getBounding());
    }
    
    public void setOrientation(final Puzzle puzzle, final int n) {
        while (this.myOrientation != n) {
            this.rotateMe(puzzle, false);
        }
    }
    
    public void addCanLinkTo(final Army army) {
        this.canLinkTo.addElement(army);
    }
    
    public void notifyMerge(final Army army, final Army army2, final Army army3) {
        boolean b = false;
        if (this.canLinkTo.contains(army)) {
            this.canLinkTo.removeElement(army);
            b = true;
        }
        if (this.canLinkTo.contains(army2)) {
            this.canLinkTo.removeElement(army2);
            b = true;
        }
        if (b) {
            this.canLinkTo.addElement(army3);
        }
    }
    
    public boolean doIFitWith(final Army army) {
        if (!this.canLinkTo.contains(army)) {
            return false;
        }
        if (this.myOrientation != army.myOrientation) {
            return false;
        }
        if (this.myOrientation == 0) {
            return this.nearEqual(this.x - this.mySmallestX * this.ps, army.x - army.mySmallestX * this.ps) && this.nearEqual(this.y - this.mySmallestY * this.ps, army.y - army.mySmallestY * this.ps);
        }
        if (this.myOrientation == 1) {
            return this.nearEqual(this.y - this.mySmallestX * this.ps, army.y - army.mySmallestX * this.ps) && this.nearEqual(this.x + this.myLargestY * this.ps, army.x + army.myLargestY * this.ps);
        }
        if (this.myOrientation == 2) {
            return this.nearEqual(this.x + this.myLargestX * this.ps, army.x + army.myLargestX * this.ps) && this.nearEqual(this.y + this.myLargestY * this.ps, army.y + army.myLargestY * this.ps);
        }
        return this.nearEqual(this.y + this.myLargestX * this.ps, army.y + army.myLargestX * this.ps) && this.nearEqual(this.x - this.mySmallestY * this.ps, army.x - army.mySmallestY * this.ps);
    }
    
    private boolean nearEqual(final int n, final int n2) {
        return n == n2 || n + 1 == n2 || n - 1 == n2 || n - 2 == n2 || n + 2 == n2;
    }
    
    private Point myCenter() {
        if (this.myOrientation == 0 || this.myOrientation == 2) {
            return new Point(this.x + (this.myLargestX - this.mySmallestX + 1) * Global.pieceSize / 2, this.y + (this.myLargestY - this.mySmallestY + 1) * Global.pieceSize / 2);
        }
        return new Point(this.x + (this.myLargestY - this.mySmallestY + 1) * Global.pieceSize / 2, this.y + (this.myLargestX - this.mySmallestX + 1) * Global.pieceSize / 2);
    }
    
    public Rectangle getBounding() {
        final Rectangle rectangle = new Rectangle();
        Rectangle rectangle2;
        if (this.myOrientation == 0 || this.myOrientation == 2) {
            rectangle2 = new Rectangle(this.x - 1, this.y - 1, 3 + this.w, 3 + this.h);
        }
        else {
            rectangle2 = new Rectangle(this.x - 1, this.y - 1, 3 + this.h, 3 + this.w);
        }
        return rectangle2;
    }
    
    public Rectangle moveMeBy(final int n, final int n2, final int n3, final int n4) {
        final Rectangle bounding = this.getBounding();
        int x;
        int y;
        if (this.myOrientation == 0 || this.myOrientation == 2) {
            x = n3 - (this.myLargestX - this.mySmallestX) * Global.pieceSize;
            y = n4 - (this.myLargestY - this.mySmallestY) * Global.pieceSize;
        }
        else {
            x = n3 - (this.myLargestY - this.mySmallestY) * Global.pieceSize;
            y = n4 - (this.myLargestX - this.mySmallestX) * Global.pieceSize;
        }
        this.x += n;
        if (this.x < 0) {
            this.x = 0;
        }
        if (this.x > x) {
            this.x = x;
        }
        this.y += n2;
        if (this.y < 0) {
            this.y = 0;
        }
        if (this.y > y) {
            this.y = y;
        }
        return bounding.union(this.getBounding());
    }
    
    public Rectangle moveMeBy(final int n, final int n2) {
        final Rectangle bounding = this.getBounding();
        this.x += n;
        this.y += n2;
        return bounding.union(this.getBounding());
    }
    
    public boolean isThisMe(final int n, final int n2) {
        final int deep = Global.deep;
        int n3;
        int n4;
        if (this.myOrientation == 0) {
            n3 = n - this.x;
            n4 = n2 - this.y;
        }
        else if (this.myOrientation == 1) {
            n3 = n2 - this.y;
            n4 = this.x + 2 * deep + (this.myLargestY - this.mySmallestY + 1) * this.ps - n;
        }
        else if (this.myOrientation == 2) {
            n3 = this.x + 2 * deep + (this.myLargestX - this.mySmallestX + 1) * this.ps - n;
            n4 = this.y + 2 * deep + (this.myLargestY - this.mySmallestY + 1) * this.ps - n2;
        }
        else {
            n3 = this.y + deep + (this.myLargestX - this.mySmallestX + 1) * this.ps - n2;
            n4 = n - this.x;
        }
        for (int i = 0; i < this.orMasks.length; ++i) {
            final int n5 = n3 - this.orMasks[i].xOffset;
            final int n6 = n4 - this.orMasks[i].yOffset;
            if (n5 >= 0 && n5 < this.orMasks[i].getWidth() && n6 >= 0 && n6 < this.orMasks[i].getHeight() && this.orMasks[i].theMask[n5 + n6 * this.psplus] != 0) {
                return true;
            }
        }
        return false;
    }
    
    public void drawMe(final Graphics graphics, final Puzzle puzzle) {
        graphics.drawImage(this.myImage, this.x, this.y, puzzle);
    }
}
