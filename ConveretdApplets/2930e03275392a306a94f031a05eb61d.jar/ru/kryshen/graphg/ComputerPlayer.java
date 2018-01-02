// 
// Decompiled by Procyon v0.5.30
// 

package ru.kryshen.graphg;

import java.util.Enumeration;
import java.util.Vector;

public class ComputerPlayer extends Player implements Runnable
{
    private final boolean DEBUG = false;
    private final boolean FAST_FINISH = false;
    private final int K = 128;
    private Thread thread;
    private Vector paths;
    
    public ComputerPlayer(final String s, final int n, final GraphCanvas graphCanvas) {
        super(s, n, graphCanvas);
        this.thread = null;
        this.paths = new Vector();
    }
    
    private boolean handlePath(final Path path, final int n) {
        final Enumeration<Edge> elements = (Enumeration<Edge>)path.elements();
        while (elements.hasMoreElements()) {
            final Edge edge = elements.nextElement();
            ++edge.value;
        }
        return true;
    }
    
    private void ien(final MultiNode multiNode, final MultiNode multiNode2, final int n) {
        this.paths.removeAllElements();
        Path shortestPath = super.graph.shortestPath(multiNode, multiNode2);
        if (shortestPath == null) {
            return;
        }
        this.paths.addElement(shortestPath);
        shortestPath.marked = true;
        if (!this.handlePath(shortestPath, 0)) {
            return;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < shortestPath.size(); ++j) {
                final Enumeration<Path> elements = this.paths.elements();
                while (elements.hasMoreElements()) {
                    final Path path = elements.nextElement();
                    boolean b = true;
                    for (int k = 0; k < j; ++k) {
                        if (shortestPath.getEdge(k) != path.getEdge(k)) {
                            b = false;
                            break;
                        }
                    }
                    if (b) {
                        path.getEdge(j).enabled = false;
                    }
                }
                MultiNode multiNode3 = super.graph.start.multiNode;
                for (int l = 0; l < j; ++l) {
                    final Enumeration elements2 = multiNode3.edges.elements();
                    while (elements2.hasMoreElements()) {
                        elements2.nextElement().enabled = false;
                    }
                    multiNode3 = shortestPath.getEdge(l).getAnotherNode(multiNode3);
                }
                final Path shortestPath2 = super.graph.shortestPath(multiNode3, multiNode2);
                if (shortestPath2 != null) {
                    for (int n2 = j - 1; n2 >= 0; --n2) {
                        shortestPath2.addElement(shortestPath.getEdge(n2));
                    }
                    this.paths.addElement(shortestPath2);
                }
                final Enumeration<Edge> elements3 = super.graph.edges.elements();
                while (elements3.hasMoreElements()) {
                    elements3.nextElement().enabled = true;
                }
            }
            shortestPath = null;
            for (int n3 = 0; n3 < this.paths.size(); ++n3) {
                final Path path2 = this.paths.elementAt(n3);
                if (!path2.marked && (shortestPath == null || path2.size() < shortestPath.size())) {
                    shortestPath = path2;
                }
            }
            if (shortestPath == null) {
                break;
            }
            shortestPath.marked = true;
            if (!this.handlePath(shortestPath, i)) {
                break;
            }
        }
    }
    
    private Edge findEdge() {
        final Enumeration<Edge> elements = super.graph.edges.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().value = 0;
        }
        this.ien(super.graph.start.multiNode, super.graph.finish.multiNode, 128);
        Edge edge = null;
        final Enumeration<Edge> elements2 = super.graph.edges.elements();
        while (elements2.hasMoreElements()) {
            final Edge edge2 = elements2.nextElement();
            edge2.enabled = true;
            if (edge == null || edge2.value > edge.value) {
                edge = edge2;
            }
        }
        return edge;
    }
    
    protected void move() {
        (this.thread = new Thread(this)).start();
    }
    
    public void run() {
        final long currentTimeMillis = System.currentTimeMillis();
        System.out.print(this + ": Thinking...");
        final Edge edge = this.findEdge();
        System.out.println("OK (" + (System.currentTimeMillis() - currentTimeMillis) / 1000.0 + " s)");
        synchronized (this) {
            if (Thread.currentThread() != this.thread) {
                return;
            }
            switch (super.player) {
                case 1: {
                    super.graph.removeEdge(edge);
                    break;
                }
                case 2: {
                    edge.connect();
                    break;
                }
            }
            this.done();
        }
        super.canvas.repaint(edge);
    }
    
    public synchronized void stop() {
        this.thread = null;
        super.stop();
    }
}
