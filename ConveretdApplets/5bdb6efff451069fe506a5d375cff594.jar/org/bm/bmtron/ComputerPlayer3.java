// 
// Decompiled by Procyon v0.5.30
// 

package org.bm.bmtron;

import java.util.Enumeration;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Color;
import java.util.Vector;

public class ComputerPlayer3 extends ThreadedPlayer
{
    private static final Object PRESENT;
    private Vector nodes;
    private Vector cNodes;
    private Vector nNodes;
    private int moveCount;
    private FieldRectangle[] rectToGo;
    
    ComputerPlayer3(final Field field, final String s, final Color color) {
        super(field, s, color);
        this.nodes = new Vector();
        this.cNodes = new Vector();
        this.nNodes = new Vector();
        this.moveCount = 0;
        this.rectToGo = new FieldRectangle[4];
    }
    
    synchronized void start() {
        final Vector nodes = this.nodes;
        final boolean b = false;
        final boolean b2 = false;
        super.field.getClass();
        final int n = 50;
        super.field.getClass();
        nodes.addElement(new FieldRectangle((int)(b ? 1 : 0), (int)(b2 ? 1 : 0), n, 35));
        this.moveCount = 0;
        super.start();
    }
    
    synchronized void stop() {
        super.stop();
        this.nodes.removeAllElements();
    }
    
    protected void move() {
        ++this.moveCount;
        for (int i = 0; i <= 3; ++i) {
            this.rectToGo[i] = null;
        }
        for (int j = 0; j <= super.field.players.length - 1; ++j) {
            if (!super.field.players[j].isStoped()) {
                this.splitRects(super.field.players[j].getLocation(), super.field.players[j] == this);
            }
        }
        FieldRectangle fieldRectangle = null;
        for (int k = 0; k <= 3; ++k) {
            if (this.rectToGo[k] != null && this.rectToGo[k].compareTo(fieldRectangle) > 0) {
                fieldRectangle = this.rectToGo[k];
                this.setDirection(k);
            }
        }
    }
    
    private synchronized void splitRects(final Point point, final boolean b) {
        final Object[] array = this.nodes.toArray();
        for (int i = 0; i <= array.length - 1; ++i) {
            final FieldRectangle fieldRectangle = (FieldRectangle)array[i];
            if (fieldRectangle.contains(point)) {
                this.nodes.removeElement(fieldRectangle);
                final FieldRectangle fieldRectangle2 = new FieldRectangle(fieldRectangle.x, fieldRectangle.y, fieldRectangle.width, point.y - fieldRectangle.y, b ? fieldRectangle.enemyTrace : this.moveCount);
                int n;
                if (b) {
                    final Field field = super.field;
                    n = 0;
                }
                else {
                    n = -1;
                }
                this.addNode(fieldRectangle2, n);
                final FieldRectangle fieldRectangle3 = new FieldRectangle(point.x + 1, fieldRectangle.y, fieldRectangle.x + fieldRectangle.width - point.x - 1, fieldRectangle.height, b ? fieldRectangle.enemyTrace : this.moveCount);
                int n2;
                if (b) {
                    final Field field2 = super.field;
                    n2 = 1;
                }
                else {
                    n2 = -1;
                }
                this.addNode(fieldRectangle3, n2);
                final FieldRectangle fieldRectangle4 = new FieldRectangle(fieldRectangle.x, point.y + 1, fieldRectangle.width, fieldRectangle.y + fieldRectangle.height - point.y - 1, b ? fieldRectangle.enemyTrace : this.moveCount);
                int n3;
                if (b) {
                    final Field field3 = super.field;
                    n3 = 2;
                }
                else {
                    n3 = -1;
                }
                this.addNode(fieldRectangle4, n3);
                final FieldRectangle fieldRectangle5 = new FieldRectangle(fieldRectangle.x, fieldRectangle.y, point.x - fieldRectangle.x, fieldRectangle.height, b ? fieldRectangle.enemyTrace : this.moveCount);
                int n4;
                if (b) {
                    final Field field4 = super.field;
                    n4 = 3;
                }
                else {
                    n4 = -1;
                }
                this.addNode(fieldRectangle5, n4);
            }
        }
    }
    
    private void addNode(final FieldRectangle fieldRectangle, final int n) {
        if (fieldRectangle.isEmpty()) {
            return;
        }
        final Enumeration<FieldRectangle> elements = this.nodes.elements();
        while (elements.hasMoreElements()) {
            final FieldRectangle fieldRectangle2 = elements.nextElement();
            if (fieldRectangle2.contains(fieldRectangle)) {
                if (n >= 0) {
                    this.rectToGo[n] = fieldRectangle2;
                }
                return;
            }
            if (!fieldRectangle.contains(fieldRectangle2)) {
                continue;
            }
            this.nodes.removeElement(fieldRectangle2);
            for (int i = 0; i <= 3; ++i) {
                if (this.rectToGo[i] == fieldRectangle2) {
                    this.rectToGo[i] = fieldRectangle;
                    break;
                }
            }
        }
        if (n >= 0) {
            this.rectToGo[n] = fieldRectangle;
        }
        this.nodes.addElement(fieldRectangle);
    }
    
    static {
        PRESENT = new Object();
    }
    
    private class FieldRectangle extends Rectangle
    {
        private int mina;
        private int maxa;
        int enemyTrace;
        
        FieldRectangle(final ComputerPlayer3 computerPlayer3, final int n, final int n2, final int n3, final int n4) {
            this(computerPlayer3, n, n2, n3, n4, 0);
        }
        
        FieldRectangle(final int n, final int n2, final int n3, final int n4, final int enemyTrace) {
            super(n, n2, n3, n4);
            this.enemyTrace = enemyTrace;
            this.mina = Math.min(n3, n4);
            this.maxa = Math.max(n3, n4) + Game.nextRandom(-2, 2);
        }
        
        int compareTo(final FieldRectangle fieldRectangle) {
            if (fieldRectangle == null) {
                return 1;
            }
            if (this.mina > fieldRectangle.mina) {
                return 1;
            }
            if (this.mina < fieldRectangle.mina) {
                return -1;
            }
            if (this.enemyTrace > fieldRectangle.enemyTrace) {
                return 1;
            }
            if (this.enemyTrace < fieldRectangle.enemyTrace) {
                return -1;
            }
            if (this.maxa > fieldRectangle.maxa) {
                return 1;
            }
            if (this.maxa < fieldRectangle.maxa) {
                return -1;
            }
            return 0;
        }
    }
}
