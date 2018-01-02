// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.force;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class NBodyForce extends AbstractForce
{
    private static String[] pnames;
    public static final float DEFAULT_GRAV_CONSTANT = -1.0f;
    public static final float DEFAULT_MIN_GRAV_CONSTANT = -10.0f;
    public static final float DEFAULT_MAX_GRAV_CONSTANT = 10.0f;
    public static final float DEFAULT_DISTANCE = -1.0f;
    public static final float DEFAULT_MIN_DISTANCE = -1.0f;
    public static final float DEFAULT_MAX_DISTANCE = 500.0f;
    public static final float DEFAULT_THETA = 0.9f;
    public static final float DEFAULT_MIN_THETA = 0.0f;
    public static final float DEFAULT_MAX_THETA = 1.0f;
    public static final int GRAVITATIONAL_CONST = 0;
    public static final int MIN_DISTANCE = 1;
    public static final int BARNES_HUT_THETA = 2;
    private float xMin;
    private float xMax;
    private float yMin;
    private float yMax;
    private QuadTreeNodeFactory factory;
    private QuadTreeNode root;
    private Random rand;
    
    public NBodyForce() {
        this(-1.0f, -1.0f, 0.9f);
    }
    
    public NBodyForce(final float n, final float n2, final float n3) {
        this.factory = new QuadTreeNodeFactory();
        this.rand = new Random(12345678L);
        this.params = new float[] { n, n2, n3 };
        this.minValues = new float[] { -10.0f, -1.0f, 0.0f };
        this.maxValues = new float[] { 10.0f, 500.0f, 1.0f };
        this.root = this.factory.getQuadTreeNode();
    }
    
    public boolean isItemForce() {
        return true;
    }
    
    protected String[] getParameterNames() {
        return NBodyForce.pnames;
    }
    
    private void setBounds(final float xMin, final float yMin, final float xMax, final float yMax) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
    }
    
    public void clear() {
        this.clearHelper(this.root);
        this.root = this.factory.getQuadTreeNode();
    }
    
    private void clearHelper(final QuadTreeNode quadTreeNode) {
        for (int i = 0; i < quadTreeNode.children.length; ++i) {
            if (quadTreeNode.children[i] != null) {
                this.clearHelper(quadTreeNode.children[i]);
            }
        }
        this.factory.reclaim(quadTreeNode);
    }
    
    public void init(final ForceSimulator forceSimulator) {
        this.clear();
        float n = Float.MAX_VALUE;
        float n2 = Float.MAX_VALUE;
        float n3 = Float.MIN_VALUE;
        float n4 = Float.MIN_VALUE;
        final Iterator items = forceSimulator.getItems();
        while (items.hasNext()) {
            final ForceItem forceItem = items.next();
            final float n5 = forceItem.location[0];
            final float n6 = forceItem.location[1];
            if (n5 < n) {
                n = n5;
            }
            if (n6 < n2) {
                n2 = n6;
            }
            if (n5 > n3) {
                n3 = n5;
            }
            if (n6 > n4) {
                n4 = n6;
            }
        }
        final float n7 = n3 - n;
        final float n8 = n4 - n2;
        if (n7 > n8) {
            n4 = n2 + n7;
        }
        else {
            n3 = n + n8;
        }
        this.setBounds(n, n2, n3, n4);
        final Iterator items2 = forceSimulator.getItems();
        while (items2.hasNext()) {
            this.insert(items2.next());
        }
        this.calcMass(this.root);
    }
    
    public void insert(final ForceItem forceItem) {
        try {
            this.insert(forceItem, this.root, this.xMin, this.yMin, this.xMax, this.yMax);
        }
        catch (StackOverflowError stackOverflowError) {
            stackOverflowError.printStackTrace();
        }
    }
    
    private void insert(final ForceItem value, final QuadTreeNode quadTreeNode, final float n, final float n2, final float n3, final float n4) {
        if (quadTreeNode.hasChildren) {
            this.insertHelper(value, quadTreeNode, n, n2, n3, n4);
        }
        else if (quadTreeNode.value != null) {
            if (isSameLocation(quadTreeNode.value, value)) {
                this.insertHelper(value, quadTreeNode, n, n2, n3, n4);
            }
            else {
                final ForceItem value2 = quadTreeNode.value;
                quadTreeNode.value = null;
                this.insertHelper(value2, quadTreeNode, n, n2, n3, n4);
                this.insertHelper(value, quadTreeNode, n, n2, n3, n4);
            }
        }
        else {
            quadTreeNode.value = value;
        }
    }
    
    private static boolean isSameLocation(final ForceItem forceItem, final ForceItem forceItem2) {
        final float abs = Math.abs(forceItem.location[0] - forceItem2.location[0]);
        final float abs2 = Math.abs(forceItem.location[1] - forceItem2.location[1]);
        return abs < 0.01 && abs2 < 0.01;
    }
    
    private void insertHelper(final ForceItem forceItem, final QuadTreeNode quadTreeNode, float n, float n2, float n3, float n4) {
        final float n5 = forceItem.location[0];
        final float n6 = forceItem.location[1];
        final float n7 = (n + n3) / 2.0f;
        final float n8 = (n2 + n4) / 2.0f;
        final int n9 = ((n5 >= n7) ? 1 : 0) + ((n6 >= n8) ? 2 : 0);
        if (quadTreeNode.children[n9] == null) {
            quadTreeNode.children[n9] = this.factory.getQuadTreeNode();
            quadTreeNode.hasChildren = true;
        }
        if (n9 == 1 || n9 == 3) {
            n = n7;
        }
        else {
            n3 = n7;
        }
        if (n9 > 1) {
            n2 = n8;
        }
        else {
            n4 = n8;
        }
        this.insert(forceItem, quadTreeNode.children[n9], n, n2, n3, n4);
    }
    
    private void calcMass(final QuadTreeNode quadTreeNode) {
        float n = 0.0f;
        float n2 = 0.0f;
        quadTreeNode.mass = 0.0f;
        if (quadTreeNode.hasChildren) {
            for (int i = 0; i < quadTreeNode.children.length; ++i) {
                if (quadTreeNode.children[i] != null) {
                    this.calcMass(quadTreeNode.children[i]);
                    quadTreeNode.mass += quadTreeNode.children[i].mass;
                    n += quadTreeNode.children[i].mass * quadTreeNode.children[i].com[0];
                    n2 += quadTreeNode.children[i].mass * quadTreeNode.children[i].com[1];
                }
            }
        }
        if (quadTreeNode.value != null) {
            quadTreeNode.mass += quadTreeNode.value.mass;
            n += quadTreeNode.value.mass * quadTreeNode.value.location[0];
            n2 += quadTreeNode.value.mass * quadTreeNode.value.location[1];
        }
        quadTreeNode.com[0] = n / quadTreeNode.mass;
        quadTreeNode.com[1] = n2 / quadTreeNode.mass;
    }
    
    public void getForce(final ForceItem forceItem) {
        try {
            this.forceHelper(forceItem, this.root, this.xMin, this.yMin, this.xMax, this.yMax);
        }
        catch (StackOverflowError stackOverflowError) {
            stackOverflowError.printStackTrace();
        }
    }
    
    private void forceHelper(final ForceItem forceItem, final QuadTreeNode quadTreeNode, final float n, final float n2, final float n3, final float n4) {
        float n5 = quadTreeNode.com[0] - forceItem.location[0];
        float n6 = quadTreeNode.com[1] - forceItem.location[1];
        float n7 = (float)Math.sqrt(n5 * n5 + n6 * n6);
        boolean b = false;
        if (n7 == 0.0f) {
            n5 = (this.rand.nextFloat() - 0.5f) / 50.0f;
            n6 = (this.rand.nextFloat() - 0.5f) / 50.0f;
            n7 = (float)Math.sqrt(n5 * n5 + n6 * n6);
            b = true;
        }
        final boolean b2 = this.params[1] > 0.0f && n7 > this.params[1];
        if ((!quadTreeNode.hasChildren && quadTreeNode.value != forceItem) || (!b && (n3 - n) / n7 < this.params[2])) {
            if (b2) {
                return;
            }
            final float n8 = this.params[0] * forceItem.mass * quadTreeNode.mass / (n7 * n7 * n7);
            final float[] force = forceItem.force;
            final int n9 = 0;
            force[n9] += n8 * n5;
            final float[] force2 = forceItem.force;
            final int n10 = 1;
            force2[n10] += n8 * n6;
        }
        else if (quadTreeNode.hasChildren) {
            final float n11 = (n + n3) / 2.0f;
            final float n12 = (n2 + n4) / 2.0f;
            for (int i = 0; i < quadTreeNode.children.length; ++i) {
                if (quadTreeNode.children[i] != null) {
                    this.forceHelper(forceItem, quadTreeNode.children[i], (i == 1 || i == 3) ? n11 : n, (i > 1) ? n12 : n2, (i == 1 || i == 3) ? n3 : n11, (i > 1) ? n4 : n12);
                }
            }
            if (b2) {
                return;
            }
            if (quadTreeNode.value != null && quadTreeNode.value != forceItem) {
                final float n13 = this.params[0] * forceItem.mass * quadTreeNode.value.mass / (n7 * n7 * n7);
                final float[] force3 = forceItem.force;
                final int n14 = 0;
                force3[n14] += n13 * n5;
                final float[] force4 = forceItem.force;
                final int n15 = 1;
                force4[n15] += n13 * n6;
            }
        }
    }
    
    static {
        NBodyForce.pnames = new String[] { "GravitationalConstant", "Distance", "BarnesHutTheta" };
    }
    
    public static final class QuadTreeNodeFactory
    {
        private int maxNodes;
        private ArrayList nodes;
        
        public QuadTreeNodeFactory() {
            this.maxNodes = 50000;
            this.nodes = new ArrayList();
        }
        
        public QuadTreeNode getQuadTreeNode() {
            if (this.nodes.size() > 0) {
                return this.nodes.remove(this.nodes.size() - 1);
            }
            return new QuadTreeNode();
        }
        
        public void reclaim(final QuadTreeNode quadTreeNode) {
            quadTreeNode.mass = 0.0f;
            quadTreeNode.com[0] = 0.0f;
            quadTreeNode.com[1] = 0.0f;
            quadTreeNode.value = null;
            quadTreeNode.hasChildren = false;
            Arrays.fill(quadTreeNode.children, null);
            if (this.nodes.size() < this.maxNodes) {
                this.nodes.add(quadTreeNode);
            }
        }
    }
    
    public static final class QuadTreeNode
    {
        boolean hasChildren;
        float mass;
        float[] com;
        ForceItem value;
        QuadTreeNode[] children;
        
        public QuadTreeNode() {
            this.hasChildren = false;
            this.com = new float[] { 0.0f, 0.0f };
            this.children = new QuadTreeNode[4];
        }
    }
}
