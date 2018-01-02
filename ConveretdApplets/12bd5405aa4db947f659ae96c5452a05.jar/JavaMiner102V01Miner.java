import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class JavaMiner102V01Miner extends JavaMiner102V01MoveableObject
{
    public static final int SPEED = 8;
    public static final int BEWEGE_RECHTS = 1;
    public static final int BEWEGE_LINKS = 2;
    public static final int BEWEGE_OBEN = 4;
    public static final int BEWEGE_UNTEN = 8;
    public static final int BEWEGE_STOP = 16;
    public static final int NO_SHOOT_DELAY = 60;
    private Image[] images;
    private int currentImg;
    private int bewegungsRichtung;
    private int score;
    private int lives;
    private boolean hasShot;
    private int noShootCounter;
    private boolean triesToShoot;
    private boolean doesShoot;
    private boolean destroyedByHostile;
    private int gotoXKoordinate;
    private int gotoYKoordinate;
    JavaMiner102V01Level map;
    JavaMiner102V01Object[] objects;
    
    public JavaMiner102V01Miner(final Image[] images, final JavaMiner102V01Object[] objects) {
        this.objects = objects;
        this.setHeight(32);
        this.setWidth(32);
        this.hasShot = true;
        this.noShootCounter = 0;
        this.score = 0;
        this.lives = 3;
        this.images = images;
    }
    
    public void reset(final int gotoXKoordinate, final int gotoYKoordinate, final int ausrichtung, final JavaMiner102V01Level map) {
        this.map = map;
        if (gotoXKoordinate >= 0 && gotoXKoordinate < 14 && gotoYKoordinate >= 0 && gotoYKoordinate < 13) {
            this.gotoXKoordinate = gotoXKoordinate;
            this.gotoYKoordinate = gotoYKoordinate;
            this.setPos(this.getXValueFromKoordinate(gotoXKoordinate), this.getYValueFromKoordinate(gotoYKoordinate));
            this.setAusrichtung(ausrichtung);
        }
        else {
            System.out.println("Ung\u00fcltige Position und Java102VXXMiner::setupStartPosition!");
        }
        this.hasShot = true;
        this.destroyedByHostile = false;
    }
    
    public void setBewegung(final int bewegungsRichtung) {
        this.bewegungsRichtung = bewegungsRichtung;
    }
    
    public void act() {
        if (this.getXPos() == this.getXValueFromKoordinate(this.gotoXKoordinate) && this.getYPos() == this.getYValueFromKoordinate(this.gotoYKoordinate)) {
            if (this.map.isGoldAt(this.getXKoord(), this.getYKoord())) {
                this.goldFound();
            }
            if (this.map.isDiamondAt(this.getXKoord(), this.getYKoord())) {
                this.diamondFound();
            }
            this.map.drivesOver(this.getXKoord(), this.getYKoord());
            switch (this.bewegungsRichtung) {
                case 2: {
                    if (this.mayGo(2)) {
                        this.setAusrichtung(2);
                        --this.gotoXKoordinate;
                        break;
                    }
                    break;
                }
                case 1: {
                    if (this.mayGo(1)) {
                        this.setAusrichtung(1);
                        ++this.gotoXKoordinate;
                        break;
                    }
                    break;
                }
                case 4: {
                    if (this.mayGo(4)) {
                        this.setAusrichtung(4);
                        --this.gotoYKoordinate;
                        break;
                    }
                    break;
                }
                case 8: {
                    if (this.mayGo(8)) {
                        this.setAusrichtung(8);
                        ++this.gotoYKoordinate;
                        break;
                    }
                    break;
                }
            }
        }
        if (this.getXPos() < this.getXValueFromKoordinate(this.gotoXKoordinate)) {
            this.setXPos(this.getXPos() + 8);
        }
        else if (this.getXPos() > this.getXValueFromKoordinate(this.gotoXKoordinate)) {
            this.setXPos(this.getXPos() - 8);
        }
        if (this.getYPos() < this.getYValueFromKoordinate(this.gotoYKoordinate)) {
            this.setYPos(this.getYPos() + 8);
        }
        else if (this.getYPos() > this.getYValueFromKoordinate(this.gotoYKoordinate)) {
            this.setYPos(this.getYPos() - 8);
        }
        if (this.hasShot) {
            if (this.triesToShoot) {
                this.doesShoot = true;
                this.hasShot = false;
                this.noShootCounter = 60;
                this.currentImg -= 4;
                this.setImage(this.images[this.currentImg]);
            }
        }
        else {
            --this.noShootCounter;
            if (this.noShootCounter <= 0) {
                this.hasShot = true;
                this.currentImg += 4;
                this.setImage(this.images[this.currentImg]);
            }
        }
    }
    
    public void setAusrichtung(final int ausrichtung) {
        super.setAusrichtung(ausrichtung);
        int currentImg = 0;
        switch (ausrichtung) {
            case 1: {
                currentImg = 0;
                break;
            }
            case 2: {
                currentImg = 1;
                break;
            }
            case 4: {
                currentImg = 2;
                break;
            }
            case 8: {
                currentImg = 3;
                break;
            }
            default: {
                System.out.println("Fehler in JavaMiner102VXXMiner::setAusrichtung())");
                break;
            }
        }
        if (this.hasShot) {
            currentImg += 4;
        }
        this.currentImg = currentImg;
        this.setImage(this.images[this.currentImg]);
    }
    
    public void setShoot(final boolean triesToShoot) {
        this.triesToShoot = triesToShoot;
    }
    
    protected boolean mayGo(final int n) {
        int xKoord = this.getXKoord();
        int yKoord = this.getYKoord();
        switch (n) {
            case 1: {
                ++xKoord;
                break;
            }
            case 2: {
                --xKoord;
                break;
            }
            case 4: {
                --yKoord;
                break;
            }
            case 8: {
                ++yKoord;
                break;
            }
            default: {
                System.out.println("Ung\u00fcltige Richtung in JavaMiner102VXXMiner::mayGo()");
                break;
            }
        }
        return xKoord >= 0 && xKoord < 14 && yKoord >= 0 && yKoord < 13 && (this.map.isDriveable(xKoord, yKoord) || this.map.isGoldAt(xKoord, yKoord) || this.map.isDiamondAt(xKoord, yKoord)) && !this.goldBlocks(xKoord, yKoord, this.objects);
    }
    
    public void goldFound() {
        this.score += 500;
    }
    
    public void diamondFound() {
        this.score += 100;
    }
    
    public void hostileHit() {
        this.score += 250;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public int getLives() {
        return this.lives;
    }
    
    public void decrLives() {
        if (this.lives >= 0) {
            --this.lives;
            return;
        }
        System.out.println("Ung\u00fcltige Leben-Anzah!");
    }
    
    public void incrLives() {
        if (this.lives < 3 && this.lives >= 0) {
            ++this.lives;
            return;
        }
        System.out.println("Fehler in JavaMiner102VXXMiner::incrLives -> Anzahl der Leben au\u00dferhalb des g\u00fcltigen Bereiches!");
    }
    
    public void resetScore() {
        this.score = 0;
    }
    
    public boolean getDoesShoot() {
        return this.doesShoot;
    }
    
    public void shotDone() {
        this.doesShoot = false;
    }
    
    public void hostilePosition(final int n, final int n2, final int n3, final int n4) {
        if (n + n3 > this.getXPos() && n < this.getXPos() + this.getWidth() && n2 + n3 > this.getYPos() && n2 < this.getYPos() + this.getHeight()) {
            this.destroyedByHostile = true;
        }
    }
    
    public boolean destroyedByHostile() {
        return this.destroyedByHostile;
    }
    
    public int shotHits() {
        return 0;
    }
    
    public int goldHits() {
        this.destroyedByHostile = true;
        return 0;
    }
    
    public int goldCollected() {
        this.goldFound();
        return 0;
    }
    
    public boolean stayAtLevelRestart() {
        return true;
    }
    
    public int getObjectType() {
        return 0;
    }
}
