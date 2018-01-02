// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

import java.awt.Polygon;
import java.awt.Color;
import java.awt.Rectangle;

public abstract class PolygonGameObject extends GameObject
{
    protected SuperPolygon[] objectOutline;
    protected Rectangle[] objectOutlineBounds;
    protected SuperPolygon objectLocationOutline;
    protected Rectangle objectLocationBounds;
    protected long damageDisplayTimer;
    
    public PolygonGameObject(final SuperPolygon[] objectOutline, final Rectangle[] objectOutlineBounds, final HighPrecisionPoint createLocation, final double createHeading, final double createSpeed, final double createRotationalSpeed) {
        super(createLocation, createHeading, createSpeed, createRotationalSpeed);
        this.objectLocationBounds = new Rectangle();
        this.objectOutline = objectOutline;
        this.objectOutlineBounds = objectOutlineBounds;
        this.objectLocationOutline = objectOutline[(int)super.objectHeading].cloneTranslate((int)super.objectLocation.x, (int)super.objectLocation.y);
        this.objectLocationBounds.setBounds(objectOutlineBounds[(int)super.objectHeading]);
        this.objectLocationBounds.translate((int)super.objectLocation.x, (int)super.objectLocation.y);
    }
    
    protected static void setupReferenceOutlineArrays(final SuperPolygon[] outlineArray, final Rectangle[] outlineBounds) {
        outlineBounds[0] = outlineArray[0].getBounds();
        final Rectangle rectangle = outlineBounds[0];
        --rectangle.x;
        final Rectangle rectangle2 = outlineBounds[0];
        --rectangle2.y;
        outlineBounds[0].width += 2;
        outlineBounds[0].height += 2;
        for (int i = 1; i < 360; ++i) {
            outlineArray[i] = outlineArray[0].cloneRotate(i);
            outlineBounds[i] = outlineArray[i].getBounds();
            final Rectangle rectangle3 = outlineBounds[i];
            --rectangle3.x;
            final Rectangle rectangle4 = outlineBounds[i];
            --rectangle4.y;
            outlineBounds[i].width += 2;
            outlineBounds[i].height += 2;
        }
    }
    
    protected void updatePosition(final double gameTickInterval) {
        super.updatePosition(gameTickInterval);
        this.objectLocationOutline = this.objectOutline[(int)super.objectHeading].cloneTranslate((int)super.objectLocation.x, (int)super.objectLocation.y);
        this.objectLocationBounds.setBounds(this.objectOutlineBounds[(int)super.objectHeading]);
        this.objectLocationBounds.translate((int)super.objectLocation.x, (int)super.objectLocation.y);
    }
    
    public void draw() {
        if (this.isInvincible()) {
            GameObject.asteroidsGame.gameScreenGraphics.setColor(new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255)));
            GameObject.asteroidsGame.gameScreenGraphics.drawPolygon(this.objectLocationOutline);
        }
        else {
            GameObject.asteroidsGame.gameScreenGraphics.setColor(Color.white);
            if (System.currentTimeMillis() < this.damageDisplayTimer) {
                GameObject.asteroidsGame.gameScreenGraphics.drawRect((int)super.objectLocation.x - 5, (int)super.objectLocation.y - 2, 20, 5);
                GameObject.asteroidsGame.gameScreenGraphics.fillRect((int)super.objectLocation.x - 5, (int)super.objectLocation.y - 2, 20 * super.healthLevel / super.maxHealthLevel, 5);
            }
            GameObject.asteroidsGame.gameScreenGraphics.setColor(Color.white);
            GameObject.asteroidsGame.gameScreenGraphics.drawPolygon(this.objectLocationOutline);
        }
    }
    
    protected void checkCollision(final PolygonGameObject o) {
        boolean collision = false;
        if (!super.isAlive || !o.isAlive) {
            return;
        }
        if (this.isInvincible() || o.isInvincible()) {
            return;
        }
        if (this.objectLocationBounds.intersects(o.objectLocationBounds)) {
            for (int i = 0; i < this.objectLocationOutline.npoints; ++i) {
                int thisPoint2ArrayIndex;
                if (i == this.objectLocationOutline.npoints - 1) {
                    thisPoint2ArrayIndex = 0;
                }
                else {
                    thisPoint2ArrayIndex = i + 1;
                }
                for (int j = 0; j < o.objectLocationOutline.npoints; ++j) {
                    int oPoint2ArrayIndex;
                    if (j == o.objectLocationOutline.npoints - 1) {
                        oPoint2ArrayIndex = 0;
                    }
                    else {
                        oPoint2ArrayIndex = j + 1;
                    }
                    if (Util.linesIntersect(this.objectLocationOutline.xpoints[i], this.objectLocationOutline.ypoints[i], this.objectLocationOutline.xpoints[thisPoint2ArrayIndex], this.objectLocationOutline.ypoints[thisPoint2ArrayIndex], o.objectLocationOutline.xpoints[j], o.objectLocationOutline.ypoints[j], o.objectLocationOutline.xpoints[oPoint2ArrayIndex], o.objectLocationOutline.ypoints[oPoint2ArrayIndex])) {
                        collision = true;
                        break;
                    }
                }
                if (collision) {
                    break;
                }
            }
            if (this.objectLocationOutline.contains((int)o.objectLocation.x, (int)o.objectLocation.y) || o.objectLocationOutline.contains((int)super.objectLocation.x, (int)super.objectLocation.y)) {
                collision = true;
            }
        }
        if (collision) {
            final int thisBeforeHealth = super.healthLevel;
            final int oBeforeHealth = o.healthLevel;
            o.collideWith(this, thisBeforeHealth);
            this.collideWith(o, oBeforeHealth);
        }
    }
    
    protected void collideWith(final PolygonGameObject o, final int oBeforeHealthLevel) {
        super.healthLevel -= oBeforeHealthLevel;
        if (super.healthLevel <= 0) {
            this.explode(o);
        }
    }
    
    protected boolean clearLineToObject(final PolygonGameObject o) {
        int i = 0;
        while (true) {
            try {
                final PolygonGameObject objToCheck = GameObject.asteroidsGame.collidableObjectList.elementAt(i);
                if (objToCheck != this && objToCheck != o && (objToCheck instanceof Asteroid || objToCheck instanceof Ship) && objToCheck.lineIntersects(new HighPrecisionPoint(super.objectLocation), new HighPrecisionPoint(o.objectLocation))) {
                    return false;
                }
            }
            catch (IndexOutOfBoundsException e) {
                break;
            }
            ++i;
        }
        return true;
    }
    
    protected boolean clearLineAhead(final double range) {
        final HighPrecisionPoint pointAhead = new HighPrecisionPoint(super.objectLocation);
        pointAhead.directionalMove(super.objectHeading, range);
        int i = 0;
        while (true) {
            try {
                final PolygonGameObject objToCheck = GameObject.asteroidsGame.collidableObjectList.elementAt(i);
                if (objToCheck != this && (objToCheck instanceof Asteroid || objToCheck instanceof Ship) && objToCheck.lineIntersects(new HighPrecisionPoint(super.objectLocation), new HighPrecisionPoint(pointAhead))) {
                    return false;
                }
            }
            catch (IndexOutOfBoundsException e) {
                break;
            }
            ++i;
        }
        return true;
    }
    
    protected boolean lineIntersects(final HighPrecisionPoint source, final HighPrecisionPoint dest) {
        if (Util.linesIntersect(source.x, source.y, dest.x, dest.y, this.objectLocationBounds.x, this.objectLocationBounds.y, this.objectLocationBounds.x, this.objectLocationBounds.y + this.objectLocationBounds.height) || Util.linesIntersect(source.x, source.y, dest.x, dest.y, this.objectLocationBounds.x, this.objectLocationBounds.y, this.objectLocationBounds.x + this.objectLocationBounds.width, this.objectLocationBounds.y) || Util.linesIntersect(source.x, source.y, dest.x, dest.y, this.objectLocationBounds.x, this.objectLocationBounds.y + this.objectLocationBounds.height, this.objectLocationBounds.x, this.objectLocationBounds.y + this.objectLocationBounds.height) || Util.linesIntersect(source.x, source.y, dest.x, dest.y, this.objectLocationBounds.x + this.objectLocationBounds.width, this.objectLocationBounds.y, this.objectLocationBounds.x + this.objectLocationBounds.width, this.objectLocationBounds.y + this.objectLocationBounds.height)) {
            for (int i = 0; i < this.objectLocationOutline.npoints; ++i) {
                int thisPoint2ArrayIndex;
                if (i == this.objectLocationOutline.npoints - 1) {
                    thisPoint2ArrayIndex = 0;
                }
                else {
                    thisPoint2ArrayIndex = i + 1;
                }
                if (Util.linesIntersect(this.objectLocationOutline.xpoints[i], this.objectLocationOutline.ypoints[i], this.objectLocationOutline.xpoints[thisPoint2ArrayIndex], this.objectLocationOutline.ypoints[thisPoint2ArrayIndex], source.x, source.y, dest.x, dest.y)) {
                    return true;
                }
            }
            if (this.objectLocationOutline.contains((int)source.x, (int)source.y) || this.objectLocationOutline.contains((int)dest.x, (int)dest.y)) {
                return true;
            }
        }
        return false;
    }
}
