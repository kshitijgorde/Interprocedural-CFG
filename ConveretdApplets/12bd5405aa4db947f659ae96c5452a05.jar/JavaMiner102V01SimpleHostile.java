import java.awt.Image;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

class JavaMiner102V01SimpleHostile extends JavaMiner102V01Hostile
{
    public static int SPEED;
    int gotoX;
    int gotoY;
    int ausrichtung;
    JavaMiner102V01Level map;
    JavaMiner102V01Miner digger;
    JavaMiner102V01Object[] objects;
    Random random;
    
    public JavaMiner102V01SimpleHostile(final Image image, final JavaMiner102V01Level map, final JavaMiner102V01Miner digger, final JavaMiner102V01Object[] objects) {
        this.objects = objects;
        this.setImage(image);
        this.setWidth(32);
        this.setHeight(32);
        this.gotoX = 13;
        this.gotoY = 0;
        this.setXPos(this.getXValueFromKoordinate(this.gotoX));
        this.setYPos(this.getYValueFromKoordinate(this.gotoY));
        this.ausrichtung = 0;
        this.map = map;
        this.digger = digger;
        this.random = new Random();
    }
    
    public void act() {
        if (this.getXPos() == this.getXValueFromKoordinate(this.gotoX) && this.getYPos() == this.getYValueFromKoordinate(this.gotoY)) {
            final int possibilities = this.getPossibilities(this.getXKoord(), this.getYKoord(), this.map, this.objects);
            final int possibleDirections = this.getPossibleDirections(this.getXKoord(), this.getYKoord(), this.map, this.objects);
            if (possibilities == 1) {
                this.goStepToDirection(possibleDirections);
            }
            else if (possibilities == 2) {
                if (this.ausrichtung == 0) {
                    this.goStepToDirection(this.randomDirection(possibleDirections, 2));
                }
                else {
                    this.goStepToDirection(possibleDirections - this.getOppositeDirection(this.ausrichtung));
                }
            }
            else if (possibilities == 3) {
                this.goStepToDirection(this.randomDirection(possibleDirections - this.getOppositeDirection(this.ausrichtung), 2));
            }
            else if (possibilities == 4) {
                this.goStepToDirection(this.randomDirection(possibleDirections - this.getOppositeDirection(this.ausrichtung), 3));
            }
            this.digger.hostilePosition(this.getXPos(), this.getYPos(), this.getWidth(), this.getHeight());
        }
        if (this.getXPos() > this.getXValueFromKoordinate(this.gotoX)) {
            this.setXPos(this.getXPos() - JavaMiner102V01SimpleHostile.SPEED);
            return;
        }
        if (this.getXPos() < this.getXValueFromKoordinate(this.gotoX)) {
            this.setXPos(this.getXPos() + JavaMiner102V01SimpleHostile.SPEED);
            return;
        }
        if (this.getYPos() > this.getYValueFromKoordinate(this.gotoY)) {
            this.setYPos(this.getYPos() - JavaMiner102V01SimpleHostile.SPEED);
            return;
        }
        if (this.getYPos() < this.getYValueFromKoordinate(this.gotoY)) {
            this.setYPos(this.getYPos() + JavaMiner102V01SimpleHostile.SPEED);
        }
    }
    
    protected void goStepToDirection(final int n) {
        if (this.ausrichtung != n) {
            this.changeAusrichtung(n);
        }
        switch (n) {
            case 1: {
                ++this.gotoX;
            }
            case 2: {
                --this.gotoX;
            }
            case 4: {
                --this.gotoY;
            }
            case 8: {
                ++this.gotoY;
            }
            default: {
                System.out.println("Ung\u00fcltige Ausrichtung in SimpleHostile:" + this.ausrichtung);
            }
        }
    }
    
    protected boolean mayGo(final int n) {
        return this.isDirectionPossible(this.getXKoord(), this.getYKoord(), n, this.map, this.objects);
    }
    
    protected void changeAusrichtung(final int ausrichtung) {
        this.ausrichtung = ausrichtung;
    }
    
    protected int randomDirection(int n, final int n2) {
        final int[] array = new int[n2];
        int n3 = 0;
        if ((n & 0x1) == 0x1) {
            array[n3] = 1;
            --n;
            ++n3;
        }
        if ((n & 0x2) == 0x2) {
            array[n3] = 2;
            n -= 2;
            ++n3;
        }
        if ((n & 0x4) == 0x4) {
            array[n3] = 4;
            n -= 4;
            ++n3;
        }
        if ((n & 0x8) == 0x8) {
            array[n3] = 8;
            n -= 8;
            ++n3;
        }
        return array[(int)(this.random.nextFloat() * n2)];
    }
    
    static {
        JavaMiner102V01SimpleHostile.SPEED = 8;
    }
}
