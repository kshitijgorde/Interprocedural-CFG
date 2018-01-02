// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import org.w3c.dom.Node;
import java.io.Serializable;

public class NodeVector implements Serializable, Cloneable
{
    private int m_blocksize;
    private Node[] m_map;
    protected int m_firstFree;
    private int m_mapSize;
    
    public NodeVector() {
        this.m_firstFree = 0;
        this.m_blocksize = 32;
        this.m_mapSize = 0;
    }
    
    public NodeVector(final int blocksize) {
        this.m_firstFree = 0;
        this.m_blocksize = blocksize;
        this.m_mapSize = 0;
    }
    
    public void addElement(final Node value) {
        if (this.m_firstFree + 1 >= this.m_mapSize) {
            if (this.m_map == null) {
                this.m_map = new Node[this.m_blocksize];
                this.m_mapSize = this.m_blocksize;
            }
            else {
                this.m_mapSize += this.m_blocksize;
                final Node[] newMap = new Node[this.m_mapSize];
                System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree + 1);
                this.m_map = newMap;
            }
        }
        this.m_map[this.m_firstFree] = value;
        ++this.m_firstFree;
    }
    
    public void appendNodes(final NodeVector nodes) {
        final int nNodes = nodes.size();
        if (this.m_map == null) {
            this.m_mapSize = nNodes + this.m_blocksize;
            this.m_map = new Node[this.m_mapSize];
        }
        else if (this.m_firstFree + nNodes >= this.m_mapSize) {
            this.m_mapSize += nNodes + this.m_blocksize;
            final Node[] newMap = new Node[this.m_mapSize];
            System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree + nNodes);
            this.m_map = newMap;
        }
        System.arraycopy(nodes.m_map, 0, this.m_map, this.m_firstFree, nNodes);
        this.m_firstFree += nNodes;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final NodeVector clone = (NodeVector)super.clone();
        if (this.m_map != null && this.m_map == clone.m_map) {
            clone.m_map = new Node[this.m_map.length];
            System.arraycopy(this.m_map, 0, clone.m_map, 0, this.m_map.length);
        }
        return clone;
    }
    
    public boolean contains(final Node s) {
        if (this.m_map == null) {
            return false;
        }
        for (int i = 0; i < this.m_firstFree; ++i) {
            final Node node = this.m_map[i];
            if (node != null && node.equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    public Node elementAt(final int i) {
        if (this.m_map == null) {
            return null;
        }
        return this.m_map[i];
    }
    
    public int indexOf(final Node elem) {
        if (this.m_map == null) {
            return -1;
        }
        for (int i = 0; i < this.m_firstFree; ++i) {
            final Node node = this.m_map[i];
            if (node != null && node.equals(elem)) {
                return i;
            }
        }
        return -1;
    }
    
    public int indexOf(final Node elem, final int index) {
        if (this.m_map == null) {
            return -1;
        }
        for (int i = index; i < this.m_firstFree; ++i) {
            final Node node = this.m_map[i];
            if (node != null && node.equals(elem)) {
                return i;
            }
        }
        return -1;
    }
    
    public void insertElementAt(final Node value, final int at) {
        if (this.m_map == null) {
            this.m_map = new Node[this.m_blocksize];
            this.m_mapSize = this.m_blocksize;
        }
        else if (this.m_firstFree + 1 >= this.m_mapSize) {
            this.m_mapSize += this.m_blocksize;
            final Node[] newMap = new Node[this.m_mapSize];
            System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree + 1);
            this.m_map = newMap;
        }
        if (at <= this.m_firstFree - 1) {
            System.arraycopy(this.m_map, at, this.m_map, at + 1, this.m_firstFree - at);
        }
        this.m_map[at] = value;
        ++this.m_firstFree;
    }
    
    public final Node peepOrNull() {
        return (this.m_map != null && this.m_firstFree > 0) ? this.m_map[this.m_firstFree - 1] : null;
    }
    
    public final Node peepTail() {
        return this.m_map[this.m_firstFree - 1];
    }
    
    public final Node peepTailSub1() {
        return this.m_map[this.m_firstFree - 2];
    }
    
    public final Node pop() {
        --this.m_firstFree;
        final Node n = this.m_map[this.m_firstFree];
        this.m_map[this.m_firstFree] = null;
        return n;
    }
    
    public final Node popAndTop() {
        --this.m_firstFree;
        this.m_map[this.m_firstFree] = null;
        return (this.m_firstFree == 0) ? null : this.m_map[this.m_firstFree - 1];
    }
    
    public final void popPair() {
        this.m_firstFree -= 2;
        this.m_map[this.m_firstFree] = null;
        this.m_map[this.m_firstFree + 1] = null;
    }
    
    public final void popQuick() {
        --this.m_firstFree;
        this.m_map[this.m_firstFree] = null;
    }
    
    public final void push(final Node value) {
        int ff = this.m_firstFree;
        if (ff + 1 >= this.m_mapSize) {
            if (this.m_map == null) {
                this.m_map = new Node[this.m_blocksize];
                this.m_mapSize = this.m_blocksize;
            }
            else {
                this.m_mapSize += this.m_blocksize;
                final Node[] newMap = new Node[this.m_mapSize];
                System.arraycopy(this.m_map, 0, newMap, 0, ff + 1);
                this.m_map = newMap;
            }
        }
        this.m_map[ff] = value;
        ++ff;
        this.m_firstFree = ff;
    }
    
    public final void pushPair(final Node v1, final Node v2) {
        if (this.m_map == null) {
            this.m_map = new Node[this.m_blocksize];
            this.m_mapSize = this.m_blocksize;
        }
        else if (this.m_firstFree + 2 >= this.m_mapSize) {
            this.m_mapSize += this.m_blocksize;
            final Node[] newMap = new Node[this.m_mapSize];
            System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree);
            this.m_map = newMap;
        }
        this.m_map[this.m_firstFree] = v1;
        this.m_map[this.m_firstFree + 1] = v2;
        this.m_firstFree += 2;
    }
    
    public void removeAllElements() {
        if (this.m_map == null) {
            return;
        }
        for (int i = 0; i < this.m_firstFree; ++i) {
            this.m_map[i] = null;
        }
        this.m_firstFree = 0;
    }
    
    public boolean removeElement(final Node s) {
        if (this.m_map == null) {
            return false;
        }
        for (int i = 0; i < this.m_firstFree; ++i) {
            final Node node = this.m_map[i];
            if (node != null && node.equals(s)) {
                if (i > this.m_firstFree) {
                    System.arraycopy(this.m_map, i + 1, this.m_map, i - 1, this.m_firstFree - i);
                }
                else {
                    this.m_map[i] = null;
                }
                --this.m_firstFree;
                return true;
            }
        }
        return false;
    }
    
    public void removeElementAt(final int i) {
        if (this.m_map == null) {
            return;
        }
        if (i > this.m_firstFree) {
            System.arraycopy(this.m_map, i + 1, this.m_map, i - 1, this.m_firstFree - i);
        }
        else {
            this.m_map[i] = null;
        }
    }
    
    public void setElementAt(final Node node, final int index) {
        if (this.m_map == null) {
            this.m_map = new Node[this.m_blocksize];
            this.m_mapSize = this.m_blocksize;
        }
        this.m_map[index] = node;
    }
    
    public final void setTail(final Node n) {
        this.m_map[this.m_firstFree - 1] = n;
    }
    
    public final void setTailSub1(final Node n) {
        this.m_map[this.m_firstFree - 2] = n;
    }
    
    public int size() {
        return this.m_firstFree;
    }
}
