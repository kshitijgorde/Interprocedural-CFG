import java.io.IOException;
import java.io.InputStream;
import java.awt.Point;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class JavaMiner102V01Level
{
    public static final int MAP_WIDTH = 14;
    public static final int MAP_HEIGHT = 13;
    public static final int BEFAHREN = 0;
    public static final int NICHT_BEFAHREN = 1;
    public static final int DIAMANT = 2;
    public static final int GOLD = 3;
    public static final int WALL_1 = 4;
    public static final int WALL_2 = 5;
    public static final int WALL_3 = 6;
    public static final int WALL_4 = 7;
    public static final int WALL_5 = 8;
    public static final int WALL_6 = 9;
    Image[] backgrounds;
    int[][] staticObjects;
    int numberOfDiamonds;
    int numberOfGold;
    int diggerStartX;
    int diggerStartY;
    int diggerStartRichtung;
    int simpleHostiles;
    Point[] startGoldLocations;
    
    public JavaMiner102V01Level(final Image[] backgrounds) {
        this.backgrounds = backgrounds;
        this.staticObjects = new int[14][13];
    }
    
    public void readLevelFile(final InputStream inputStream) {
        this.numberOfDiamonds = 0;
        this.numberOfGold = 0;
        try {
            for (int i = 0; i < 14; ++i) {
                for (int j = 0; j < 13; ++j) {
                    this.staticObjects[i][j] = inputStream.read();
                    if (this.staticObjects[i][j] == 2) {
                        ++this.numberOfDiamonds;
                    }
                    if (this.staticObjects[i][j] == 3) {
                        ++this.numberOfGold;
                    }
                }
            }
            this.startGoldLocations = new Point[this.numberOfGold];
            int n = 0;
            for (int k = 0; k < 14; ++k) {
                for (int l = 0; l < 13; ++l) {
                    if (this.staticObjects[k][l] == 3) {
                        this.staticObjects[k][l] = 1;
                        this.startGoldLocations[n] = new Point(k, l);
                        ++n;
                    }
                }
            }
            this.diggerStartX = inputStream.read();
            this.diggerStartY = inputStream.read();
            this.diggerStartRichtung = inputStream.read();
            this.simpleHostiles = inputStream.read();
            inputStream.close();
        }
        catch (IOException ex) {
            System.out.println("Fehler beim Lesen der Level-Datei!");
        }
    }
    
    public Image getBackground(final int n, final int n2) {
        return this.backgrounds[this.staticObjects[n][n2]];
    }
    
    public void drivesOver(final int n, final int n2) {
        if (this.staticObjects[n][n2] == 2) {
            --this.numberOfDiamonds;
        }
        this.staticObjects[n][n2] = 0;
    }
    
    public boolean isGoldAt(final int n, final int n2) {
        return this.staticObjects[n][n2] == 3;
    }
    
    public boolean isDiamondAt(final int n, final int n2) {
        return this.staticObjects[n][n2] == 2;
    }
    
    public boolean isAnyDiamondLeft() {
        return this.numberOfDiamonds > 0;
    }
    
    public int getMinerStartX() {
        return this.diggerStartX;
    }
    
    public int getMinerStartY() {
        return this.diggerStartY;
    }
    
    public int getMinerStartRichtung() {
        return this.diggerStartRichtung;
    }
    
    public int getSimpleHostiles() {
        return this.simpleHostiles;
    }
    
    public boolean isWay(final int n, final int n2) {
        return this.staticObjects[n][n2] == 0;
    }
    
    public boolean isDriveable(final int n, final int n2) {
        return this.staticObjects[n][n2] == 0 || this.staticObjects[n][n2] == 1;
    }
    
    public Point[] getStartGoldLocations() {
        return this.startGoldLocations;
    }
}
