import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class JavaMiner102V01Object
{
    public static final int RICHTUNG_RECHTS = 1;
    public static final int RICHTUNG_LINKS = 2;
    public static final int RICHTUNG_OBEN = 4;
    public static final int RICHTUNG_UNTEN = 8;
    public static final int OBJECT_DIGGER = 0;
    public static final int OBJECT_GOLDSACK = 1;
    public static final int OBJECT_HOSTILE = 2;
    public static final int OBJECT_MISC = 3;
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private int ausrichtung;
    private Image image;
    private boolean doesDispose;
    
    public void setXPos(final int xPos) {
        this.xPos = xPos;
    }
    
    public void setYPos(final int yPos) {
        this.yPos = yPos;
    }
    
    public void setPos(final int xPos, final int yPos) {
        this.setXPos(xPos);
        this.setYPos(yPos);
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public void setImage(final Image image) {
        this.image = image;
    }
    
    public int getXPos() {
        return this.xPos;
    }
    
    public int getYPos() {
        return this.yPos;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public int getYValueFromKoordinate(final int n) {
        return 32 + n * 32;
    }
    
    public int getXValueFromKoordinate(final int n) {
        return 32 + n * 32;
    }
    
    public int getXKoord() {
        return (this.xPos - 32) / 32;
    }
    
    public int getYKoord() {
        return (this.yPos - 32) / 32;
    }
    
    public boolean getDispose() {
        return this.doesDispose;
    }
    
    public void dispose() {
        this.doesDispose = true;
    }
    
    public static boolean isInMap(final int n, final int n2) {
        return n >= 0 && n < 14 && n2 >= 0 && n2 < 13;
    }
    
    public int getAusrichtung() {
        return this.ausrichtung;
    }
    
    public void setAusrichtung(final int ausrichtung) {
        this.ausrichtung = ausrichtung;
    }
    
    public boolean touchsObject(final JavaMiner102V01Object javaMiner102V01Object) {
        return this.getXPos() < javaMiner102V01Object.getXPos() + 32 && this.getXPos() + 32 > javaMiner102V01Object.getXPos() && this.getYPos() < javaMiner102V01Object.getYPos() + 32 && this.getYPos() + 32 > javaMiner102V01Object.getYPos();
    }
    
    public boolean goldBlocks(final int n, final int n2, final JavaMiner102V01Object[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null && array[i].getObjectType() == 1 && (array[i].getXPos() - 32) / 32 == n && (array[i].getYPos() - 32) / 32 == n2) {
                return true;
            }
        }
        return false;
    }
    
    public abstract void act();
    
    public abstract int shotHits();
    
    public abstract int goldHits();
    
    public abstract int goldCollected();
    
    public abstract boolean stayAtLevelRestart();
    
    public abstract int getObjectType();
}
