import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.Point;
import java.util.Vector;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Random;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class Com2meFWl1 extends Panel implements Runnable
{
    private Com2meFWl2 loadCanvas;
    private Com2meFWO loadStatus;
    private Image image;
    private Com2meFWPP[] pieces;
    private int piecesNumber;
    private int grid;
    private int rowNumber;
    private int colNumber;
    private int[][] piecesMatrix;
    private int[] imageData;
    private int xSize;
    private int ySize;
    private Thread cropper;
    private Random random;
    private int checkx;
    private int checky;
    
    public Com2meFWl1(final Com2meFWO loadStatus) {
        this.loadStatus = loadStatus;
        this.loadCanvas = new Com2meFWl2();
        this.setLayout(new BorderLayout());
        this.add("Center", this.loadCanvas);
        this.random = new Random();
        this.checkx = 400;
        this.checky = 300;
    }
    
    public boolean action(final Event event, final Object o) {
        return false;
    }
    
    public void createPuzzle(final Image image, final int piecesNumber, final int grid, final int checkx, final int checky) {
        this.image = image;
        this.piecesNumber = piecesNumber;
        this.grid = grid;
        this.pieces = new Com2meFWPP[piecesNumber];
        this.checkx = checkx;
        this.checky = checky;
        this.start();
    }
    
    private Com2meFWPP getCom2mePP(final int n) {
        int rowNumber = this.rowNumber;
        int n2 = 0;
        int colNumber = this.colNumber;
        int n3 = 0;
        for (int i = 0; i < this.rowNumber; ++i) {
            for (int j = 0; j < this.colNumber; ++j) {
                if (this.piecesMatrix[i][j] == n) {
                    if (rowNumber > i) {
                        rowNumber = i;
                    }
                    if (n2 < i) {
                        n2 = i;
                    }
                    if (colNumber > j) {
                        colNumber = j;
                    }
                    if (n3 < j) {
                        n3 = j;
                    }
                }
            }
        }
        final int n4 = n3 - colNumber + 1;
        final int n5 = n2 - rowNumber + 1;
        final int[][] array = new int[n5][n4];
        for (int k = rowNumber; k <= n2; ++k) {
            for (int l = colNumber; l <= n3; ++l) {
                if (this.piecesMatrix[k][l] == n) {
                    array[k - rowNumber][l - colNumber] = 1;
                }
                else {
                    array[k - rowNumber][l - colNumber] = 0;
                }
            }
        }
        final int n6 = n4 * this.grid;
        final int n7 = n5 * this.grid;
        final int n8 = colNumber * this.grid;
        final int n9 = rowNumber * this.grid;
        final int[] array2 = new int[n6 * n7];
        for (int n10 = 0; n10 < n7; ++n10) {
            for (int n11 = 0; n11 < n6; ++n11) {
                if (array[n10 / this.grid][n11 / this.grid] != 0) {
                    array2[n11 + n10 * n6] = this.imageData[n8 + n11 + (n9 + n10) * this.xSize];
                }
            }
        }
        return new Com2meFWPP(this.createImage(new MemoryImageSource(n6, n7, array2, 0, n6)), array, n8, n9, this.grid, n, this);
    }
    
    private Vector getEmptyNeighbours(final int n) {
        final Vector<Point> vector = new Vector<Point>();
        for (int i = 0; i < this.rowNumber; ++i) {
            for (int j = 0; j < this.colNumber; ++j) {
                if (this.piecesMatrix[i][j] < 0 && ((i > 0 && this.piecesMatrix[i - 1][j] == n) || (i < this.rowNumber - 1 && this.piecesMatrix[i + 1][j] == n) || (j > 0 && this.piecesMatrix[i][j - 1] == n) || (j < this.colNumber - 1 && this.piecesMatrix[i][j + 1] == n))) {
                    vector.addElement(new Point(i, j));
                }
            }
        }
        return vector;
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public Com2meFWPP[] getPieces() {
        return this.pieces;
    }
    
    private int getRandomInt(final int n) {
        return (int)(this.random.nextFloat() * n);
    }
    
    public void run() {
        if (this.run_image_maker()) {
            this.cropper = null;
            return;
        }
        this.image = null;
        this.pieces = null;
        this.loadStatus.setObject("error");
        this.cropper = null;
    }
    
    private boolean run_image_maker() {
        this.loadCanvas.showStatus(1);
        try {
            Thread.sleep(10L);
        }
        catch (InterruptedException ex) {}
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex2) {}
        if (mediaTracker.isErrorAny()) {
            this.loadCanvas.showErrorMessage("Couldn't load the image!");
            mediaTracker.removeImage(this.image);
            return false;
        }
        this.xSize = this.image.getWidth(this) / 10 * 10;
        this.ySize = this.image.getHeight(this) / 10 * 10;
        if (this.image.getWidth(this) != this.checkx || this.image.getHeight(this) != this.checky) {
            this.loadCanvas.showErrorMessage("Image has the wrong size!");
            return false;
        }
        this.loadCanvas.showProgress(1, 1);
        try {
            Thread.sleep(10L);
        }
        catch (InterruptedException ex3) {}
        this.imageData = new int[this.xSize * this.ySize];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.image, 0, 0, this.xSize, this.ySize, this.imageData, 0, this.xSize);
        try {
            if (!pixelGrabber.grabPixels()) {
                this.loadCanvas.showErrorMessage("Couldn't create the pieces!");
                return false;
            }
        }
        catch (InterruptedException ex4) {}
        this.rowNumber = this.ySize / this.grid;
        this.colNumber = this.xSize / this.grid;
        this.piecesMatrix = new int[this.rowNumber][this.colNumber];
        for (int i = 0; i < this.rowNumber; ++i) {
            for (int j = 0; j < this.colNumber; ++j) {
                this.piecesMatrix[i][j] = -1;
            }
        }
        this.loadCanvas.showStatus(2);
        try {
            Thread.sleep(10L);
        }
        catch (InterruptedException ex5) {}
        final int n;
        int k = n = this.rowNumber * this.colNumber;
        final int n2 = n / 20;
        int n3 = 0;
        do {
            int randomInt = this.getRandomInt(this.rowNumber);
            int randomInt2 = this.getRandomInt(this.colNumber);
            while (true) {
                if (++randomInt2 >= this.colNumber) {
                    randomInt2 = 0;
                    if (++randomInt >= this.rowNumber) {
                        randomInt = 0;
                    }
                }
                if (this.piecesMatrix[randomInt][randomInt2] < 0) {
                    if (randomInt2 > 0 && this.piecesMatrix[randomInt][randomInt2 - 1] >= 0) {
                        continue;
                    }
                    if (randomInt2 < this.colNumber - 1 && this.piecesMatrix[randomInt][randomInt2 + 1] >= 0) {
                        continue;
                    }
                    if (randomInt < this.rowNumber - 1 && this.piecesMatrix[randomInt + 1][randomInt2] >= 0) {
                        continue;
                    }
                    if (randomInt > 0 && this.piecesMatrix[randomInt - 1][randomInt2] >= 0) {
                        continue;
                    }
                    break;
                }
            }
            this.piecesMatrix[randomInt][randomInt2] = n3;
            --k;
        } while (++n3 < this.piecesNumber);
        while (k > 0) {
            for (int l = 0; l < this.piecesNumber; ++l) {
                final Vector emptyNeighbours = this.getEmptyNeighbours(l);
                if (emptyNeighbours.size() > 0) {
                    final Point point = emptyNeighbours.elementAt(this.getRandomInt(emptyNeighbours.size()));
                    this.piecesMatrix[point.x][point.y] = l;
                    --k;
                    if ((n - k) % n2 == 0) {
                        this.loadCanvas.showProgress(n - k, n);
                        try {
                            Thread.sleep(10L);
                        }
                        catch (InterruptedException ex6) {}
                    }
                }
            }
        }
        this.loadCanvas.showProgress(1, 1);
        this.loadCanvas.showStatus(3);
        try {
            Thread.sleep(10L);
        }
        catch (InterruptedException ex7) {}
        final MediaTracker mediaTracker2 = new MediaTracker(this);
        for (int n4 = 0; n4 < this.piecesNumber; ++n4) {
            this.pieces[n4] = this.getCom2mePP(n4);
            mediaTracker2.addImage(this.pieces[n4].getImage(), n4);
        }
        for (int n5 = 0; n5 < this.piecesNumber; ++n5) {
            this.loadCanvas.showProgress(n5, this.piecesNumber - 1);
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException ex8) {}
            try {
                mediaTracker2.waitForID(n5);
            }
            catch (InterruptedException ex9) {}
        }
        this.loadStatus.setObject("ok");
        return true;
    }
    
    public void start() {
        (this.cropper = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.cropper != null && this.cropper.isAlive()) {
            this.cropper.stop();
        }
        this.cropper = null;
    }
}
