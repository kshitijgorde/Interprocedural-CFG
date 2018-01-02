// 
// Decompiled by Procyon v0.5.30
// 

package ru.kryshen.graphg;

import java.util.Enumeration;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;

class GraphCanvas extends Component implements Runnable
{
    Graph graph;
    final int border = 15;
    Edge selectedEdge;
    private Player player1;
    private Player player2;
    private Player current;
    private Thread timer;
    private long time;
    private long startTime;
    private String message;
    final Color clrNormal;
    final Color clrConnected;
    final Color clrStartFinish;
    final Color clrSelected;
    final Color clrMarked;
    final Game game;
    
    GraphCanvas(final Game game) {
        this.graph = null;
        this.selectedEdge = null;
        this.player1 = null;
        this.player2 = null;
        this.timer = null;
        this.message = null;
        this.clrNormal = new Color(90, 150, 90);
        this.clrConnected = new Color(0, 255, 0);
        this.clrStartFinish = new Color(0, 255, 0);
        this.clrSelected = new Color(210, 245, 210);
        this.clrMarked = Color.red;
        this.game = game;
    }
    
    synchronized void newGame(final Player player1, final Player player2) {
        this.stop();
        this.message = null;
        this.player1 = player1;
        this.player2 = player2;
        this.startTime = System.currentTimeMillis();
        this.game.setStatus1(player1.name + "'s move");
        this.game.setStatus2("Moves: 0");
        this.game.setStatus3("Time: --:--");
        player1.play();
        this.time = -1000L;
        (this.timer = new Thread(this)).start();
    }
    
    synchronized void stop() {
        this.timer = null;
        if (this.player1 != null) {
            this.player1.stop();
        }
        if (this.player2 != null) {
            this.player2.stop();
        }
    }
    
    public synchronized void nextTurn(final Player player) {
        this.game.setStatus2("Moves: " + player.moves);
        this.graph.shortestPath(this.graph.start.multiNode, this.graph.finish.multiNode);
        int n = 0;
        if (this.graph.finish.multiNode == this.graph.start.multiNode) {
            n = 2;
        }
        else if (this.graph.shortestPath(this.graph.start.multiNode, this.graph.finish.multiNode) == null) {
            n = 1;
        }
        if (n > 0) {
            if (n == this.player1.player) {
                this.game.setStatus1(this.player1.name + " wins!");
                this.message = "Game over:\n" + this.player1.name + " wins!";
            }
            else if (n == this.player2.player) {
                this.game.setStatus1(this.player2.name + " wins!");
                this.message = "Game over:\n" + this.player2.name + " wins!";
            }
            this.game.repaintStatus();
            this.repaint();
            this.timer = null;
            return;
        }
        try {
            Thread.currentThread();
            Thread.sleep(50L);
        }
        catch (Exception ex) {}
        Player player2;
        if (player == this.player1) {
            player2 = this.player2;
        }
        else {
            player2 = this.player1;
        }
        this.game.setStatus1(player2.name + "'s move");
        this.game.setStatus2("Moves: " + player2.moves);
        player2.play();
        this.game.repaintStatus();
    }
    
    public void run() {
        while (Thread.currentThread() == this.timer) {
            final long time = this.time;
            this.time = System.currentTimeMillis() - this.startTime;
            if (this.isVisible()) {
                if (this.time / 1000L != time / 1000L) {
                    final int n = (int)(this.time / 60000L);
                    final int n2 = (int)(this.time / 1000L) - n * 60;
                    this.game.setStatus3("Time: " + ((n < 10) ? "0" : "") + n + ((n2 < 10) ? ":0" : ":") + n2);
                    this.game.repaintStatus();
                }
            }
            else {
                this.startTime += this.time - time;
                this.time = time;
            }
            try {
                Thread.currentThread();
                Thread.sleep(100L);
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
        }
        if (this.timer == null) {
            try {
                Thread.currentThread();
                Thread.sleep(2000L);
            }
            catch (InterruptedException ex2) {
                System.err.println(ex2);
            }
            synchronized (this) {
                if (this.timer == null && this.message != null) {
                    this.message = null;
                    this.repaint();
                }
            }
        }
    }
    
    void setSelectedEdge(final Edge selectedEdge) {
        if (this.selectedEdge == selectedEdge) {
            return;
        }
        if (this.selectedEdge != null && this.selectedEdge.equals(selectedEdge)) {
            return;
        }
        final Edge selectedEdge2 = this.selectedEdge;
        this.selectedEdge = selectedEdge;
        this.repaint(selectedEdge2);
        this.repaint(selectedEdge);
    }
    
    public synchronized void paint(final Graphics graphics) {
        RenderingManager.setAA(graphics, 2);
        final Rectangle clipBounds = graphics.getClipBounds();
        if (this.graph == null) {
            return;
        }
        final float n = (this.getSize().width - 30) / this.graph.width;
        final float n2 = (this.getSize().height - 30) / this.graph.height;
        final Enumeration<Edge> elements = this.graph.edges.elements();
        while (elements.hasMoreElements()) {
            final Edge edge = elements.nextElement();
            if (edge == this.selectedEdge) {
                graphics.setColor(this.clrSelected);
            }
            else if (edge.marked) {
                graphics.setColor(this.clrMarked);
            }
            else if (edge.connected) {
                graphics.setColor(this.clrConnected);
            }
            else {
                graphics.setColor(this.clrNormal);
            }
            final int n3 = Math.round(edge.node1.x * n) + 15;
            final int n4 = Math.round(edge.node1.y * n2) + 15;
            final int n5 = Math.round(edge.node2.x * n) + 15;
            final int n6 = Math.round(edge.node2.y * n2) + 15;
            if (Math.max(n3, n5) >= clipBounds.x && Math.min(n3, n5) <= clipBounds.x + clipBounds.width && Math.max(n4, n6) >= clipBounds.y && Math.min(n4, n6) <= clipBounds.y + clipBounds.height) {
                graphics.drawLine(n3, n4, n5, n6);
            }
        }
        final Enumeration<Node> elements2 = this.graph.nodes.elements();
        while (elements2.hasMoreElements()) {
            final Node node = elements2.nextElement();
            if (node.equals(this.graph.start) | node.equals(this.graph.finish)) {
                graphics.setColor(this.clrStartFinish);
            }
            else {
                graphics.setColor(this.clrNormal);
            }
            final int n7 = Math.round(node.x * n) + 15 - 4;
            final int n8 = Math.round(node.y * n2) + 15 - 4;
            final int n9 = n7 + 8;
            final int n10 = n8 + 8;
            if (Math.max(n7, n9) >= clipBounds.x && Math.min(n7, n9) <= clipBounds.x + clipBounds.width && Math.max(n8, n10) >= clipBounds.y && Math.min(n8, n10) <= clipBounds.y + clipBounds.height) {
                graphics.fillOval(n7, n8, 8, 8);
            }
        }
        RenderingManager.setAA(graphics, 1);
        if (this.message != null) {
            graphics.setColor(new Color(2, 165, 2));
            graphics.setFont(new Font("Helvetica", 1, 48));
            RenderingManager.drawStringInRect(this.message, 0, 0, this.getSize().width, this.getSize().height, 1, graphics);
        }
    }
    
    synchronized void repaint(final Edge edge) {
        if (edge == null || this.graph == null) {
            return;
        }
        final float n = (this.getSize().width - 30) / this.graph.width;
        final float n2 = (this.getSize().height - 30) / this.graph.height;
        final int n3 = Math.round(edge.node1.x * n) + 15;
        final int n4 = Math.round(edge.node1.y * n2) + 15;
        final int n5 = Math.round(edge.node2.x * n) + 15;
        final int n6 = Math.round(edge.node2.y * n2) + 15;
        this.repaint(Math.min(n3, n5) - 2, Math.min(n4, n6) - 2, Math.abs(n5 - n3) + 4, Math.abs(n6 - n4) + 4);
    }
}
