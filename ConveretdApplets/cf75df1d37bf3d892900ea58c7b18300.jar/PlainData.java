import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class PlainData
{
    int[] item;
    int n;
    String name;
    
    public void init(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        this.n = stringTokenizer.countTokens();
        --this.n;
        this.name = stringTokenizer.nextToken();
        this.item = new int[this.n];
        for (int i = 0; i < this.n; ++i) {
            this.item[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
    }
}
