// 
// Decompiled by Procyon v0.5.30
// 

package mechanalyst;

public class PrePost
{
    String src;
    String dest;
    
    PrePost(final String src, final String dest) {
        this.src = src;
        this.dest = dest;
    }
    
    public void print(final int n) {
        for (int i = 0; i < n; ++i) {
            System.out.print(" ");
        }
        System.out.println("pre-post: " + this.src + "  " + this.dest);
    }
    
    public String src() {
        return this.src;
    }
    
    public String dest() {
        return this.dest;
    }
}
