// 
// Decompiled by Procyon v0.5.30
// 

package ru.kryshen.graphg;

import java.util.Enumeration;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class HumanPlayer extends Player implements MouseListener, MouseMotionListener
{
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (!super.myTurn | super.canvas.selectedEdge == null) {
            return;
        }
        switch (super.player) {
            case 2: {
                super.canvas.selectedEdge.connect();
                break;
            }
            case 1: {
                super.graph.removeEdge(super.canvas.selectedEdge);
                break;
            }
        }
        super.canvas.setSelectedEdge(null);
        this.done();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (!super.myTurn) {
            return;
        }
        final int width = super.canvas.getSize().width;
        super.canvas.getClass();
        final float n = (width - 15 * 2) / super.graph.width;
        final int height = super.canvas.getSize().height;
        super.canvas.getClass();
        final float n2 = (height - 15 * 2) / super.graph.height;
        final int x = mouseEvent.getX();
        super.canvas.getClass();
        final int round = Math.round((x - 15) / n);
        final int y = mouseEvent.getY();
        super.canvas.getClass();
        final Point point = new Point(round, Math.round((y - 15) / n2));
        Edge selectedEdge = null;
        double n3 = super.graph.width / 20;
        final Enumeration<Edge> elements = (Enumeration<Edge>)super.graph.edges.elements();
        while (elements.hasMoreElements()) {
            final Edge edge = elements.nextElement();
            final Node node1 = edge.node1;
            final Node node2 = edge.node2;
            final double lineLength = getLineLength(node1, node2);
            final double n4 = lineLength * lineLength;
            if (n4 != 0.0) {
                final double lineLength2 = getLineLength(node1, point);
                final double n5 = lineLength2 * lineLength2;
                final double lineLength3 = getLineLength(node2, point);
                final double n6 = lineLength3 * lineLength3;
                double n7;
                if (n5 + n6 <= n4) {
                    n7 = Math.sqrt(n5 - (n5 + n4 - n6) * (n5 + n4 - n6) / (4.0 * n4));
                }
                else {
                    n7 = Math.min(lineLength2, lineLength3);
                }
                if (n7 > n3) {
                    continue;
                }
                n3 = n7;
                selectedEdge = edge;
            }
        }
        if (selectedEdge != null && !selectedEdge.connected) {
            super.canvas.setSelectedEdge(selectedEdge);
        }
    }
    
    public static double getLineLength(final Point point, final Point point2) {
        final int n = point2.x - point.x;
        final int n2 = n * n;
        final int n3 = point2.y - point.y;
        return Math.sqrt(n2 + n3 * n3);
    }
    
    public HumanPlayer(final String s, final int n, final GraphCanvas graphCanvas) {
        super(s, n, graphCanvas);
        super.canvas.addMouseListener(this);
        super.canvas.addMouseMotionListener(this);
    }
    
    public synchronized void stop() {
        super.canvas.removeMouseListener(this);
        super.canvas.removeMouseMotionListener(this);
        super.stop();
    }
}
