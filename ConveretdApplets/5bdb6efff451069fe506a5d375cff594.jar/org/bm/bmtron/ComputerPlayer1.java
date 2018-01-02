// 
// Decompiled by Procyon v0.5.30
// 

package org.bm.bmtron;

import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Color;

public class ComputerPlayer1 extends ThreadedPlayer
{
    private boolean firstMove;
    
    ComputerPlayer1(final Field field, final String s, final Color color) {
        super(field, s, color);
        this.firstMove = true;
    }
    
    synchronized void start() {
        this.firstMove = true;
        super.start();
    }
    
    protected void move() {
        if (this.firstMove) {
            this.setDirection(Game.nextRandom(0, 3));
            this.firstMove = false;
        }
        else {
            final Point location = this.getLocation();
            final Field field = super.field;
            int direction = 0;
            int largestRect = this.findLargestRect(location.x, location.y - 1);
            final int largestRect2 = this.findLargestRect(location.x - 1, location.y);
            if (largestRect2 > largestRect + Game.nextRandom(-2, 1)) {
                final Field field2 = super.field;
                direction = 3;
                largestRect = largestRect2;
            }
            final int largestRect3 = this.findLargestRect(location.x, location.y + 1);
            if (largestRect3 > largestRect + Game.nextRandom(-2, 1)) {
                final Field field3 = super.field;
                direction = 2;
                largestRect = largestRect3;
            }
            if (this.findLargestRect(location.x + 1, location.y) > largestRect + Game.nextRandom(-2, 1)) {
                final Field field4 = super.field;
                direction = 1;
            }
            this.setDirection(direction);
        }
    }
    
    private int findLargestRect(final int n, final int n2) {
        if (!super.field.isFree(n, n2)) {
            return 0;
        }
        final Rectangle rectangle = new Rectangle(n, n2, 1, 1);
        boolean b = true;
        while (b) {
            final boolean b2 = false;
            boolean b3 = true;
            for (int i = rectangle.x; i <= rectangle.width + rectangle.x - 1; ++i) {
                b3 &= super.field.isFree(i, rectangle.y - 1);
                if (!b3) {
                    break;
                }
            }
            final boolean b4 = b2 | b3;
            if (b3) {
                final Rectangle rectangle2 = rectangle;
                --rectangle2.y;
                final Rectangle rectangle3 = rectangle;
                ++rectangle3.height;
            }
            boolean b5 = true;
            for (int j = rectangle.x; j <= rectangle.width + rectangle.x - 1; ++j) {
                b5 &= super.field.isFree(j, rectangle.y + rectangle.height);
                if (!b5) {
                    break;
                }
            }
            final boolean b6 = b4 | b5;
            if (b5) {
                final Rectangle rectangle4 = rectangle;
                ++rectangle4.height;
            }
            boolean b7 = true;
            for (int k = rectangle.y; k <= rectangle.height + rectangle.y - 1; ++k) {
                b7 &= super.field.isFree(rectangle.x - 1, k);
                if (!b7) {
                    break;
                }
            }
            final boolean b8 = b6 | b7;
            if (b7) {
                final Rectangle rectangle5 = rectangle;
                --rectangle5.x;
                final Rectangle rectangle6 = rectangle;
                ++rectangle6.width;
            }
            boolean b9 = true;
            for (int l = rectangle.y; l <= rectangle.height + rectangle.y - 1; ++l) {
                b9 &= super.field.isFree(rectangle.x + rectangle.width, l);
                if (!b9) {
                    break;
                }
            }
            b = (b8 | b9);
            if (b9) {
                final Rectangle rectangle7 = rectangle;
                ++rectangle7.width;
            }
        }
        return rectangle.width * rectangle.height;
    }
}
