// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

public class Powerup extends PolygonGameObject
{
    private static SuperPolygon[] powerupOutline;
    private static final Rectangle[] powerupOutlineBounds;
    private static final double POWERUP_ROTATION_SPEED = 60.0;
    private static final double POWERUP_MAX_SPEED = 40.0;
    private static final int POWERUP_DIMENSION_MINOR = 4;
    private static final int POWERUP_DIMENSION_MAJOR = 10;
    private static final long POWERUP_FINALISATION_INTERVAL = 2000L;
    private static final long POWERUP_INVINCIBILITY_DURATION = 10000L;
    private static final Font powerupTextFont;
    private static final char POWERUP_SYMBOL = '?';
    private static final int POWERUP_HEAL = 1;
    private static final int POWERUP_MISSILES = 2;
    private static final int POWERUP_SUPERGUN = 3;
    private static final int POWERUP_INVINCIBILITY = 4;
    private static final int POWERUP_AUTOFIRE = 5;
    private static final int POWERUP_FASTENERGYRECHARGE = 6;
    private static final int POWERUP_DEBRISSHIELD = 7;
    private static final int POWERUP_MISSILEGUIDANCE = 8;
    private static int powerupSymbolXOffset;
    private static int powerupSymbolYOffset;
    private static Font powerupSymbolFont;
    protected boolean powerupAvailable;
    private long powerupFinalisationTimer;
    private String powerupFinalisationText;
    private int powerupFinalisationTextXOffset;
    private int powerupType;
    
    public Powerup(final HighPrecisionPoint createLocation) {
        super(Powerup.powerupOutline, Powerup.powerupOutlineBounds, createLocation, Math.random() * 360, Math.random() * 40.0, 60.0);
        super.healthLevel = 0;
        super.maxHealthLevel = 0;
        this.powerupAvailable = true;
        switch (this.powerupType = (int)Math.ceil(Math.random() * 8)) {
            case 1: {
                this.powerupFinalisationText = "Shield Recharge";
                break;
            }
            case 2: {
                this.powerupFinalisationText = "+5 Missiles";
                break;
            }
            case 3: {
                this.powerupFinalisationText = "Super Gun";
                break;
            }
            case 4: {
                this.powerupFinalisationText = "Invincibility";
                break;
            }
            case 5: {
                this.powerupFinalisationText = "Auto-Fire";
                break;
            }
            case 6: {
                this.powerupFinalisationText = "Fast Energy Recharge";
                break;
            }
            case 7: {
                this.powerupFinalisationText = "Debris Shield";
                break;
            }
            case 8: {
                this.powerupFinalisationText = "Homing Missile System, +2 Missiles";
                break;
            }
        }
        this.powerupFinalisationTextXOffset = GameObject.asteroidsGame.gameScreenGraphics.getFontMetrics(Powerup.powerupTextFont).stringWidth(this.powerupFinalisationText) / 2;
    }
    
    protected void collideWith(final PolygonGameObject o, final int oBeforeHealthLevel) {
        if (this.powerupAvailable && o instanceof Ship) {
            GameObject.asteroidsGame.asteroidsUI.soundManager.playPowerupSound();
            this.powerupAvailable = false;
            this.powerupFinalisationTimer = System.currentTimeMillis() + 2000L;
            switch (this.powerupType) {
                case 1: {
                    ((Ship)o).healthLevel = o.maxHealthLevel;
                    break;
                }
                case 2: {
                    ((Ship)o).missilesRemaining += 5;
                    break;
                }
                case 3: {
                    ((Ship)o).superGunActive = true;
                    break;
                }
                case 4: {
                    ((Ship)o).invincibilityTimer = System.currentTimeMillis() + 10000L;
                    break;
                }
                case 5: {
                    ((Ship)o).autoFireAvailable = true;
                    break;
                }
                case 6: {
                    ((Ship)o).fastEnergyRechargeActive = true;
                    break;
                }
                case 7: {
                    ((Ship)o).debrisShieldActive = true;
                    break;
                }
                case 8: {
                    ((Ship)o).missileGuidanceActive = true;
                    ((Ship)o).missilesRemaining += 2;
                    break;
                }
            }
        }
        else if (this.powerupAvailable) {
            this.explode(o);
        }
    }
    
    protected void checkCollision(final PolygonGameObject o) {
        if (this.powerupAvailable && (o instanceof Ship || o instanceof Asteroid || o instanceof Projectile)) {
            super.checkCollision(o);
        }
    }
    
    public void gameTick(final double gameTickInterval) {
        this.updatePosition(gameTickInterval);
    }
    
    protected void updatePosition(final double gameTickInterval) {
        super.updatePosition(gameTickInterval);
        if (!this.powerupAvailable && System.currentTimeMillis() > this.powerupFinalisationTimer) {
            super.isAlive = false;
        }
    }
    
    public void draw() {
        if (this.powerupAvailable) {
            super.draw();
            GameObject.asteroidsGame.gameScreenGraphics.setFont(Powerup.powerupSymbolFont);
            GameObject.asteroidsGame.gameScreenGraphics.drawString(String.valueOf('?'), (int)super.objectLocation.x - Powerup.powerupSymbolXOffset, (int)super.objectLocation.y + Powerup.powerupSymbolYOffset);
        }
        else {
            GameObject.asteroidsGame.gameScreenGraphics.setFont(Powerup.powerupTextFont);
            GameObject.asteroidsGame.gameScreenGraphics.setColor(Color.white);
            GameObject.asteroidsGame.gameScreenGraphics.drawString(this.powerupFinalisationText, (int)super.objectLocation.x - this.powerupFinalisationTextXOffset, (int)super.objectLocation.y);
        }
    }
    
    public void explode(final GameObject destroyingObject) {
        super.explode(destroyingObject);
        for (int i = 0; i < 20; ++i) {
            GameObject.asteroidsGame.addGameObject(new DotObject(new HighPrecisionPoint((int)super.objectLocation.x, (int)super.objectLocation.y), Math.random(), Math.random() * 360, Math.random() * 90));
        }
        GameObject.asteroidsGame.asteroidsUI.soundManager.playSmallExplosionSound();
    }
    
    static {
        Powerup.powerupOutline = new SuperPolygon[360];
        powerupOutlineBounds = new Rectangle[360];
        powerupTextFont = new Font("Arial", 0, 10);
        (Powerup.powerupOutline[0] = new SuperPolygon()).addPoint(4, -10);
        Powerup.powerupOutline[0].addPoint(10, -4);
        Powerup.powerupOutline[0].addPoint(10, 4);
        Powerup.powerupOutline[0].addPoint(4, 10);
        Powerup.powerupOutline[0].addPoint(-4, 10);
        Powerup.powerupOutline[0].addPoint(-10, 4);
        Powerup.powerupOutline[0].addPoint(-10, -4);
        Powerup.powerupOutline[0].addPoint(-4, -10);
        PolygonGameObject.setupReferenceOutlineArrays(Powerup.powerupOutline, Powerup.powerupOutlineBounds);
        int i = 20;
        int symbolWidth;
        int symbolHeight;
        while (true) {
            Powerup.powerupSymbolFont = new Font("Arial", 0, i);
            symbolWidth = GameObject.asteroidsGame.gameScreenGraphics.getFontMetrics(Powerup.powerupSymbolFont).charWidth('?');
            symbolHeight = GameObject.asteroidsGame.gameScreenGraphics.getFontMetrics(Powerup.powerupSymbolFont).getHeight();
            if (symbolWidth < 20 && symbolHeight < 20) {
                break;
            }
            --i;
        }
        Powerup.powerupSymbolXOffset = symbolWidth / 2 - 1;
        Powerup.powerupSymbolYOffset = symbolHeight / 2 - 2;
    }
}
