// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.rubik.parserAWT;

import java.util.NoSuchElementException;
import ch.randelshofer.util.SingletonEnumeration;
import ch.randelshofer.util.ReverseVectorEnumeration;
import java.util.Vector;
import java.util.Enumeration;
import ch.randelshofer.rubik.RubiksCubeCore;
import ch.randelshofer.gui.tree.DefaultMutableTreeNode;

public class ScriptNode extends DefaultMutableTreeNode
{
    protected int startpos;
    protected int endpos;
    private static final int[][] orientationToSymbolMap;
    
    public ScriptNode() {
        this.setAllowsChildren(true);
    }
    
    public ScriptNode(final int startpos, final int endpos) {
        this.startpos = startpos;
        this.endpos = endpos;
        this.setAllowsChildren(true);
    }
    
    public int getStartPosition() {
        return this.startpos;
    }
    
    public void setStartPosition(final int startpos) {
        this.startpos = startpos;
    }
    
    public int getEndPosition() {
        return this.endpos;
    }
    
    public void setEndPosition(final int endpos) {
        this.endpos = endpos;
    }
    
    public void applyTo(final RubiksCubeCore rubiksCubeCore) {
    }
    
    public void applySubtreeTo(final RubiksCubeCore rubiksCubeCore, final boolean b) {
        final Enumeration resolvedEnumeration = this.resolvedEnumeration(b);
        while (resolvedEnumeration.hasMoreElements()) {
            resolvedEnumeration.nextElement().applyTo(rubiksCubeCore);
        }
    }
    
    public void applyInverseTo(final RubiksCubeCore rubiksCubeCore) {
    }
    
    public int getSymbol() {
        return 113;
    }
    
    public void transform(final int n) {
        final Enumeration children = this.children();
        while (children.hasMoreElements()) {
            children.nextElement().transform(n);
        }
    }
    
    public void transformOrientation(final int n) {
        if (n >= 1) {
            if (ScriptNode.orientationToSymbolMap[n].length == 2) {
                final SequenceNode sequenceNode = new SequenceNode();
                sequenceNode.add(new TwistNode(ScriptNode.orientationToSymbolMap[n][0]));
                sequenceNode.add(new TwistNode(ScriptNode.orientationToSymbolMap[n][1]));
                this.insert(sequenceNode, 0);
            }
            else {
                this.insert(new TwistNode(ScriptNode.orientationToSymbolMap[n][0]), 0);
            }
            this.insert(new TwistNode(84), 1);
        }
    }
    
    public void inverse() {
        if (super.children != null) {
            final Enumeration enumerateChildrenReversed = this.enumerateChildrenReversed();
            super.children = new Vector();
            while (enumerateChildrenReversed.hasMoreElements()) {
                final ScriptNode scriptNode = enumerateChildrenReversed.nextElement();
                scriptNode.inverse();
                super.children.addElement(scriptNode);
            }
        }
    }
    
    public void reflect() {
        if (super.children != null) {
            final Enumeration children = this.children();
            while (children.hasMoreElements()) {
                children.nextElement().reflect();
            }
        }
    }
    
    public Enumeration resolvedEnumeration(final boolean b) {
        return new ResolvedEnumeration(this, b);
    }
    
    public Enumeration enumerateChildrenReversed() {
        return new ReverseVectorEnumeration(super.children);
    }
    
    public int getFullTurnCount() {
        int n = 0;
        final Enumeration children = this.children();
        while (children.hasMoreElements()) {
            n += children.nextElement().getFullTurnCount();
        }
        return n;
    }
    
    public int getQuarterTurnCount() {
        int n = 0;
        final Enumeration children = this.children();
        while (children.hasMoreElements()) {
            n += children.nextElement().getQuarterTurnCount();
        }
        return n;
    }
    
    public ScriptNode cloneSubtree() {
        final ScriptNode scriptNode = (ScriptNode)this.clone();
        final Enumeration children = this.children();
        while (children.hasMoreElements()) {
            scriptNode.add(children.nextElement().cloneSubtree());
        }
        return scriptNode;
    }
    
    static {
        orientationToSymbolMap = new int[][] { new int[0], { 72 }, { 78 }, { 75 }, { 73 }, { 79 }, { 76 }, { 74 }, { 80 }, { 77 }, { 72, 73 }, { 72, 79 }, { 72, 76 }, { 78, 73 }, { 78, 76 }, { 75, 73 }, { 75, 79 }, { 75, 76 }, { 72, 74 }, { 72, 77 }, { 78, 74 }, { 78, 77 }, { 75, 74 }, { 75, 77 } };
    }
    
    private static class ResolvedEnumeration implements Enumeration
    {
        protected ScriptNode root;
        protected Enumeration children;
        protected Enumeration subtree;
        boolean inverse;
        
        public ResolvedEnumeration(final ScriptNode root, final boolean inverse) {
            this.root = root;
            this.inverse = inverse;
            this.children = (inverse ? this.root.enumerateChildrenReversed() : this.root.children());
            this.subtree = new SingletonEnumeration(this.root.clone());
        }
        
        public boolean hasMoreElements() {
            return this.subtree.hasMoreElements() || this.children.hasMoreElements();
        }
        
        public Object nextElement() {
            Object o;
            if (this.subtree.hasMoreElements()) {
                o = this.subtree.nextElement();
            }
            else {
                if (!this.children.hasMoreElements()) {
                    throw new NoSuchElementException();
                }
                this.subtree = this.children.nextElement().resolvedEnumeration(this.inverse);
                o = this.subtree.nextElement();
            }
            return o;
        }
    }
}
