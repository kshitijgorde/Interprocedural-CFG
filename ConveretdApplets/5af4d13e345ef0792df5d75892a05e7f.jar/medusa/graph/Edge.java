// 
// Decompiled by Procyon v0.5.30
// 

package medusa.graph;

public class Edge extends BasicEdge
{
    private float conf;
    private double orientation;
    private int type;
    private int width;
    private String url;
    private int dir;
    private String interactLabel;
    private boolean display;
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setDisplay(final boolean display) {
        this.display = display;
    }
    
    public boolean getDisplay() {
        return this.display;
    }
    
    public Edge(final String n1, final String n2, final float conf, final int type) {
        this.orientation = 0.0;
        this.width = 3;
        this.interactLabel = "unlabeled";
        this.display = true;
        this.n1 = n1;
        this.n2 = n2;
        this.conf = conf;
        this.type = type;
    }
    
    public Edge(final String n1, final String n2, final float conf, final int type, final double orientation) {
        this.orientation = 0.0;
        this.width = 3;
        this.interactLabel = "unlabeled";
        this.display = true;
        this.n1 = n1;
        this.n2 = n2;
        this.conf = conf;
        this.type = type;
        this.orientation = orientation;
    }
    
    public Edge(final String n1, final String n2, final Integer n3, final Double n4, final Float n5) {
        this.orientation = 0.0;
        this.width = 3;
        this.interactLabel = "unlabeled";
        this.display = true;
        this.n1 = n1;
        this.n2 = n2;
        this.conf = n5;
        this.type = n3;
        this.orientation = n4;
    }
    
    public Edge(final Edge edge) {
        this(edge.getFromName(), edge.getToName(), edge.getConf(), edge.getType(), edge.getOrientation());
    }
    
    public Edge(final String s, final String s2, final Integer n, final Float n2, final Double n3) {
        this(s, s2, n, n3, n2);
    }
    
    public Edge(final String s, final String s2, final int n, final float n2, final double n3) {
        this(s, s2, n2, n, n3);
    }
    
    public Edge(final String s, final String s2) {
        this(s, s2, 1.0f, 1);
    }
    
    public Edge(final String s, final String s2, final int n) {
        this(s, s2, 1.0f, n);
    }
    
    public Edge(final String s, final String s2, final float n) {
        this(s, s2, n, 1);
    }
    
    public Edge(final String n1, final String n2, final float conf, final int type, final double orientation, final String url) {
        this.orientation = 0.0;
        this.width = 3;
        this.interactLabel = "unlabeled";
        this.display = true;
        this.n1 = n1;
        this.n2 = n2;
        this.conf = conf;
        this.type = type;
        this.orientation = orientation;
        this.url = url;
    }
    
    public String getURL() {
        return this.url;
    }
    
    public void setURL(final String url) {
        this.url = url;
    }
    
    public void setDir(final int dir) {
        this.dir = dir;
    }
    
    public int getDir() {
        return this.dir;
    }
    
    public void setInteractLabel(final String interactLabel) {
        this.interactLabel = interactLabel;
    }
    
    public String getInteractLabel() {
        return this.interactLabel;
    }
    
    public void setType(final int type) {
        this.type = type;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setConf(final float conf) {
        this.conf = conf;
    }
    
    public float getConf() {
        return this.conf;
    }
    
    public Node getComplement(final String s) {
        if (this.n1.compareTo(s) == 0) {
            return new Node(this.n2);
        }
        if (this.n2.compareTo(s) == 0) {
            return new Node(this.n1);
        }
        return null;
    }
    
    public Node getComplement(final Node node) {
        return this.getComplement(node.getLabel());
    }
    
    public boolean equals(final Object o) {
        if (o.getClass() == this.getClass()) {
            final Edge edge = (Edge)o;
            return this.sameName(o) && this.type == edge.type;
        }
        return this.sameName(o);
    }
    
    public String toString() {
        return this.n1 + "---" + this.n2;
    }
    
    public String report() {
        return super.report() + "\ti " + this.type + "\tc " + this.conf + "\to " + this.orientation;
    }
    
    public boolean containsNode(final Node node) {
        final String label = node.getLabel();
        return label.compareTo(this.n1) == 0 || label.compareTo(this.n2) == 0;
    }
    
    public void setOrientation(final double orientation) {
        this.orientation = orientation;
    }
    
    public double getOrientation() {
        return this.orientation;
    }
    
    public void renameNode(final String s, final String s2) {
        if (this.n1.compareTo(s) == 0) {
            this.n1 = s2;
        }
        if (this.n2.compareTo(s) == 0) {
            this.n2 = s2;
        }
    }
}
