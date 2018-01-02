// 
// Decompiled by Procyon v0.5.30
// 

package org.bm.bmtron;

import java.awt.Point;
import java.awt.Color;

public class ComputerPlayer2 extends ThreadedPlayer
{
    private int[][] field2;
    private boolean firstMove;
    private float k1;
    private float k2;
    
    ComputerPlayer2(final Field field, final String s, final Color color) {
        super(field, s, color);
        this.firstMove = true;
        this.k1 = 1.0f;
        this.k2 = 1.0f;
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
            this.k1 = 1.0f - Game.random.nextFloat() * 0.2f;
            this.k2 = Game.random.nextFloat() * 2.0f + 2.0f;
            final Point location = this.getLocation();
            final Field field = super.field;
            int direction = 0;
            float calcArea = this.calcArea(location.x, location.y - 1, location.x, location.y + 1);
            final float calcArea2 = this.calcArea(location.x - 1, location.y, location.x + 1, location.y);
            if (calcArea2 > calcArea | (Game.nextRandom(0, 1) == 1 & calcArea2 == calcArea)) {
                final Field field2 = super.field;
                direction = 3;
                calcArea = calcArea2;
            }
            final float calcArea3 = this.calcArea(location.x, location.y + 1, location.x, location.y - 1);
            if (calcArea3 > calcArea | (Game.nextRandom(0, 1) == 1 & calcArea3 == calcArea)) {
                final Field field3 = super.field;
                direction = 2;
                calcArea = calcArea3;
            }
            final float calcArea4 = this.calcArea(location.x + 1, location.y, location.x - 1, location.y);
            if (calcArea4 > calcArea | (Game.nextRandom(0, 1) == 1 & calcArea4 == calcArea)) {
                final Field field4 = super.field;
                direction = 1;
            }
            this.setDirection(direction);
        }
    }
    
    private void resetField2() {
        this.field2 = new int[50][35];
        for (int i = 0; i <= super.field.players.length - 1; ++i) {
            if (super.field.players[i] != this & !super.field.players[i].isStoped()) {
                final Point location = super.field.players[i].getLocation();
                int n = location.x - 3;
                if (n < 0) {
                    n = 0;
                }
                int n2 = location.y - 3;
                if (n2 < 0) {
                    n2 = 0;
                }
                int n3 = location.x + 3;
                if (n3 > 49) {
                    n3 = 49;
                }
                int n4 = location.y + 3;
                if (n4 > 34) {
                    n4 = 34;
                }
                for (int j = n; j <= n3; ++j) {
                    for (int k = n2; k <= n4; ++k) {
                        if (Math.abs(j - location.x) <= 1 & Math.abs(k - location.y) <= 1) {
                            this.field2[j][k] = -1;
                        }
                        else {
                            this.field2[j][k] = 2;
                        }
                    }
                }
            }
        }
    }
    
    private float calcArea(final int n, final int n2, final int n3, final int n4) {
        if (!super.field.isFree(n, n2)) {
            return Float.NEGATIVE_INFINITY;
        }
        this.resetField2();
        return this.calcArea_Rec(n, n2, n3, n4);
    }
    
    private float calcArea_Rec(final int n, final int n2, final int n3, final int n4) {
        if (!super.field.isFree(n, n2)) {
            return -1.0f;
        }
        if (Math.abs(n - n3) == Math.abs(n2 - n4) | this.field2[n][n2] == 1) {
            return 0.0f;
        }
        final float n5 = 2.0f;
        final boolean b = this.field2[n][n2] < 0;
        final boolean b2 = this.field2[n][n2] >= 2;
        this.field2[n][n2] = 1;
        final float calcArea_Rec = this.calcArea_Rec(n, n2 - 1, n3, n4);
        final float calcArea_Rec2 = this.calcArea_Rec(n, n2 + 1, n3, n4);
        if (calcArea_Rec == -1.0f & calcArea_Rec2 == -1.0f & (b | b2)) {
            return -1000.0f;
        }
        final float n6 = n5 + (calcArea_Rec + calcArea_Rec2);
        final float calcArea_Rec3 = this.calcArea_Rec(n - 1, n2, n3, n4);
        final float calcArea_Rec4 = this.calcArea_Rec(n + 1, n2, n3, n4);
        if (calcArea_Rec3 == -1.0f & calcArea_Rec4 == -1.0f & (b | b2)) {
            return -1000.0f;
        }
        float n7 = n6 + (calcArea_Rec3 + calcArea_Rec4);
        if (b) {
            n7 *= this.k1;
        }
        else if (b2) {
            n7 *= this.k2;
        }
        return n7;
    }
}
