// 
// Decompiled by Procyon v0.5.30
// 

package mechanalyst;

public class Key
{
    String key;
    int rank;
    DecompList decomp;
    
    Key(final String key, final int rank, final DecompList decomp) {
        this.key = key;
        this.rank = rank;
        this.decomp = decomp;
    }
    
    Key() {
        this.key = null;
        this.rank = 0;
        this.decomp = null;
    }
    
    public void copy(final Key key) {
        this.key = key.key();
        this.rank = key.rank();
        this.decomp = key.decomp();
    }
    
    public void print(final int n) {
        for (int i = 0; i < n; ++i) {
            System.out.print(" ");
        }
        System.out.println("key: " + this.key + " " + this.rank);
        this.decomp.print(n + 2);
    }
    
    public void printKey(final int n) {
        for (int i = 0; i < n; ++i) {
            System.out.print(" ");
        }
        System.out.println("key: " + this.key + " " + this.rank);
    }
    
    public String key() {
        return this.key;
    }
    
    public int rank() {
        return this.rank;
    }
    
    public DecompList decomp() {
        return this.decomp;
    }
}
