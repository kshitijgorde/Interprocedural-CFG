// 
// Decompiled by Procyon v0.5.30
// 

public class PayoffLine
{
    private static final int handTypeIndex = 0;
    private static final int minWildIndex = 1;
    private static final int maxWildIndex = 2;
    private static final int minRankIndex = 3;
    private static final int maxRankIndex = 4;
    private int numCriteria;
    private int[][] criteria;
    private String description;
    private int[] payoff;
    
    public PayoffLine() {
        this.payoff = new int[5];
    }
    
    public void addCriteria(final int n, final int n2, final int n3, final int n4, final int n5) {
        final int[][] criteria = new int[this.numCriteria + 1][5];
        for (int i = 0; i < this.numCriteria; ++i) {
            for (int j = 0; j < 5; ++j) {
                criteria[i][j] = this.criteria[i][j];
            }
        }
        criteria[this.numCriteria][0] = n;
        criteria[this.numCriteria][1] = n2;
        criteria[this.numCriteria][2] = n3;
        criteria[this.numCriteria][3] = n4;
        criteria[this.numCriteria][4] = n5;
        ++this.numCriteria;
        this.criteria = criteria;
    }
    
    public boolean testCriteria(final int n, final int n2, final int n3) {
        for (int i = 0; i < this.numCriteria; ++i) {
            if (this.criteria[i][0] == n && this.criteria[i][1] <= n3 && this.criteria[i][2] >= n3 && this.criteria[i][3] <= n2 && this.criteria[i][4] >= n2) {
                return true;
            }
        }
        return false;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setPayoff(final int n, final int n2) {
        this.payoff[n - 1] = n2;
    }
    
    public int getPayoff(final int n) {
        return this.payoff[n - 1];
    }
}
