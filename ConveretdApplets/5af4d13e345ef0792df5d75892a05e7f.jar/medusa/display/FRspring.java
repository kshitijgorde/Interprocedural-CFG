// 
// Decompiled by Procyon v0.5.30
// 

package medusa.display;

import java.util.Iterator;
import medusa.graph.UniqueEdge;
import medusa.graph.Node;
import medusa.graph.Graph;

public class FRspring implements Runnable
{
    private int width;
    private int height;
    private double critXDistance;
    private double critYDistance;
    private int area;
    private double k;
    private Graph g;
    private double temp;
    private Thread me;
    private boolean done;
    private static final double LIMIT = 1.0E-10;
    static int MAX_ITERATIONS;
    private int iterations;
    int borderSize;
    
    public FRspring(final int width, final int height) {
        this.done = true;
        this.iterations = 0;
        this.borderSize = 20;
        this.width = width;
        this.height = height;
        this.area = width * height;
        this.temp = width / 10;
        this.critXDistance = width * 3 / 4;
        this.critYDistance = height * 3 / 4;
    }
    
    public FRspring(final Graph g, final int width, final int height) {
        this.done = true;
        this.iterations = 0;
        this.borderSize = 20;
        this.g = g;
        this.width = width;
        this.height = height;
        this.area = width * height;
        this.temp = width / 10;
        this.critXDistance = width * 3 / 4;
        this.critYDistance = height * 3 / 4;
        this.setK();
    }
    
    private void setK() {
        this.k = 0.8 * Math.sqrt(this.area / this.g.getNodeSize());
    }
    
    private double[] attractDistance(final Node node, final Node node2) {
        final double n = node.getX() - node2.getX();
        final double n2 = node.getY() - node2.getY();
        final double sqrt = Math.sqrt(n * n + n2 * n2);
        final double[] array = new double[2];
        final double max = Math.max(sqrt, 1.0E-10);
        final double n3 = max * max / this.k;
        array[0] = n / max * n3;
        array[1] = n2 / max * n3;
        return array;
    }
    
    private double[] repelDistance(final Node node, final Node node2) {
        double n = node.getX() - node2.getX();
        double n2 = node.getY() - node2.getY();
        if (n > this.critXDistance) {
            n = node.getX() - node2.getX() - this.width;
        }
        if (n < -this.critXDistance) {
            n = node.getX() - node2.getX() + this.width;
        }
        if (n2 > this.critYDistance) {
            n2 = node.getY() - node2.getY() - this.height;
        }
        if (n2 < -this.critYDistance) {
            n2 = node.getY() - node2.getY() + this.height;
        }
        final double max = Math.max(Math.sqrt(n * n + n2 * n2), 1.0E-10);
        final double[] array = new double[2];
        final double n3 = this.k * this.k / max;
        array[0] = n / max * n3;
        array[1] = n2 / max * n3;
        return array;
    }
    
    public void iterateAll() {
        this.done = false;
        for (int i = 0; i < FRspring.MAX_ITERATIONS; ++i) {
            this.moveNodes();
        }
        this.done = true;
    }
    
    public void start() {
        if (this.me == null) {
            this.me = new Thread(this);
        }
        this.me.start();
    }
    
    public void stop() {
        if (this.me != null) {
            this.me = null;
        }
    }
    
    public void run() {
        if (this.me == Thread.currentThread()) {
            this.iterateAll();
        }
    }
    
    public int getCurrent() {
        return this.iterations;
    }
    
    public boolean isDone() {
        return this.done;
    }
    
    public boolean iterate() {
        if (this.iterations < FRspring.MAX_ITERATIONS) {
            this.moveNodes();
            return true;
        }
        return false;
    }
    
    public void moveNodes() {
        ++this.iterations;
        final double[] array = new double[2];
        final Iterator<Node> nodesIterator = this.g.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            node.setDX(0.0);
            node.setDY(0.0);
            final Iterator<Node> nodesIterator2 = this.g.nodesIterator();
            while (nodesIterator2.hasNext()) {
                final Node node2 = nodesIterator2.next();
                if (node.equals(node2)) {
                    continue;
                }
                final double[] repelDistance = this.repelDistance(node, node2);
                node.setDX(node.getDX() + repelDistance[0]);
                node.setDY(node.getDY() + repelDistance[1]);
            }
        }
        final Iterator<UniqueEdge> uniqueEdgesIterator = this.g.uniqueEdgesIterator();
        while (uniqueEdgesIterator.hasNext()) {
            final UniqueEdge uniqueEdge = uniqueEdgesIterator.next();
            final Node node3 = this.g.getNode(uniqueEdge.getFromName());
            final Node node4 = this.g.getNode(uniqueEdge.getToName());
            final double[] attractDistance = this.attractDistance(node3, node4);
            node3.setDX(node3.getDX() - attractDistance[0]);
            node3.setDY(node3.getDY() - attractDistance[1]);
            node4.setDX(node4.getDX() + attractDistance[0]);
            node4.setDY(node4.getDY() + attractDistance[1]);
        }
        final Iterator<Node> nodesIterator3 = this.g.nodesIterator();
        while (nodesIterator3.hasNext()) {
            final Node node5 = nodesIterator3.next();
            if (!node5.isFixed()) {
                node5.setX(node5.getX() + Math.max(-this.temp, Math.min(node5.getDX(), this.temp)));
                node5.setY(node5.getY() + Math.max(-this.temp, Math.min(node5.getDY(), this.temp)));
                if (node5.getX() > this.width) {
                    node5.setX(this.width - this.borderSize - 10.0 * Math.random());
                }
                if (node5.getX() < this.borderSize) {
                    node5.setX(this.borderSize + 10.0 * Math.random());
                }
                if (node5.getY() > this.height) {
                    node5.setY(this.height - this.borderSize - 10.0 * Math.random());
                }
                if (node5.getY() >= this.borderSize + 10) {
                    continue;
                }
                node5.setY(2 * this.borderSize + 10.0 * Math.random());
            }
        }
        this.cool();
    }
    
    public void setBorderSize(final int borderSize) {
        this.borderSize = borderSize;
    }
    
    private void cool() {
        this.temp *= 1.0 - this.iterations / FRspring.MAX_ITERATIONS;
    }
    
    public static void main(final String[] array) {
        final FRspring fRspring = new FRspring(400, 400);
        fRspring.setK();
        for (int i = 0; i < 18; ++i) {
            System.out.println("move " + i);
            fRspring.moveNodes();
        }
    }
    
    static {
        FRspring.MAX_ITERATIONS = 300;
    }
}
