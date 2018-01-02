// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.utils;

public class NamespacesScope
{
    private NamespacesHandler fHandler;
    private int fElementDepth;
    private int[][] fNamespaceMappings;
    
    public NamespacesScope() {
        this(new NamespacesHandler() {
            public void startNamespaceDeclScope(final int n, final int n2) throws Exception {
            }
            
            public void endNamespaceDeclScope(final int n) throws Exception {
            }
        });
    }
    
    public NamespacesScope(final NamespacesHandler fHandler) {
        this.fHandler = null;
        this.fElementDepth = 0;
        this.fNamespaceMappings = new int[8][];
        this.fHandler = fHandler;
        (this.fNamespaceMappings[0] = new int[9])[0] = 1;
    }
    
    public void setNamespaceForPrefix(final int n, final int n2) throws Exception {
        int n3 = this.fNamespaceMappings[this.fElementDepth][0];
        if (n3 == this.fNamespaceMappings[this.fElementDepth].length) {
            final int[] array = new int[n3 + 8];
            System.arraycopy(this.fNamespaceMappings[this.fElementDepth], 0, array, 0, n3);
            this.fNamespaceMappings[this.fElementDepth] = array;
        }
        this.fNamespaceMappings[this.fElementDepth][n3++] = n;
        this.fNamespaceMappings[this.fElementDepth][n3++] = n2;
        this.fNamespaceMappings[this.fElementDepth][0] = n3;
        if (this.fElementDepth > 0) {
            this.fHandler.startNamespaceDeclScope(n, n2);
        }
    }
    
    public int getNamespaceForPrefix(final int n) {
        for (int i = this.fElementDepth; i >= 0; --i) {
            for (int n2 = this.fNamespaceMappings[i][0], j = 1; j < n2; j += 2) {
                if (n == this.fNamespaceMappings[i][j]) {
                    return this.fNamespaceMappings[i][j + 1];
                }
            }
        }
        return 0;
    }
    
    public void increaseDepth() throws Exception {
        ++this.fElementDepth;
        if (this.fElementDepth == this.fNamespaceMappings.length) {
            final int[][] fNamespaceMappings = new int[this.fElementDepth + 8][];
            System.arraycopy(this.fNamespaceMappings, 0, fNamespaceMappings, 0, this.fElementDepth);
            this.fNamespaceMappings = fNamespaceMappings;
        }
        if (this.fNamespaceMappings[this.fElementDepth] == null) {
            this.fNamespaceMappings[this.fElementDepth] = new int[9];
        }
        this.fNamespaceMappings[this.fElementDepth][0] = 1;
    }
    
    public void decreaseDepth() throws Exception {
        if (this.fElementDepth > 0) {
            int i = this.fNamespaceMappings[this.fElementDepth][0];
            while (i > 1) {
                i -= 2;
                this.fHandler.endNamespaceDeclScope(this.fNamespaceMappings[this.fElementDepth][i]);
            }
        }
        --this.fElementDepth;
    }
    
    public interface NamespacesHandler
    {
        void startNamespaceDeclScope(final int p0, final int p1) throws Exception;
        
        void endNamespaceDeclScope(final int p0) throws Exception;
    }
}
