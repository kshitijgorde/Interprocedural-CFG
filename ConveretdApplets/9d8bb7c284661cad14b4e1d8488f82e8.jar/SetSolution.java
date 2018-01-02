// 
// Decompiled by Procyon v0.5.30
// 

class SetSolution
{
    int[] \u026a;
    String \u026b;
    
    public SetSolution(final int n, final int n2, final int n3) {
        this.\u026a = new int[3];
        this.\u026b = "";
        this.\u026a[0] = n;
        this.\u026a[1] = n2;
        this.\u026a[2] = n3;
    }
    
    public SetSolution(final int n, final int n2, final int n3, final String \u026b) {
        this.\u026a = new int[3];
        this.\u026b = "";
        this.\u026a[0] = n;
        this.\u026a[1] = n2;
        this.\u026a[2] = n3;
        this.\u026b = \u026b;
    }
    
    public String getLoginName() {
        return this.\u026b;
    }
    
    public int getCard(final int n) {
        if (n >= 1 && n < 4) {
            return this.\u026a[n - 1];
        }
        return 0;
    }
    
    public boolean isSolution(final int n, final int n2, final int n3) {
        return (n == this.\u026a[0] || n == this.\u026a[1] || n == this.\u026a[2]) && (n2 == this.\u026a[0] || n2 == this.\u026a[1] || n2 == this.\u026a[2]) && (n3 == this.\u026a[0] || n3 == this.\u026a[1] || n3 == this.\u026a[2]) && n != n2 && n2 != n3 && n != n3;
    }
}
