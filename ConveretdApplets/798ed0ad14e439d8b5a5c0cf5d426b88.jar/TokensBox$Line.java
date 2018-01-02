import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class TokensBox$Line
{
    public final Rectangle bounds;
    public int baseline;
    public int firstIndex;
    public int lastIndex;
    private int nbInSpace;
    private int inWidth;
    private final TokensBox this$0;
    
    public TokensBox$Line(final TokensBox this$0) {
        this.this$0 = this$0;
        this.bounds = new Rectangle();
        this.firstIndex = 0;
        this.lastIndex = -1;
        this.nbInSpace = 0;
        this.inWidth = 0;
    }
    
    private void computeVMetrics() {
        int baseline = 0;
        int n = 0;
        for (int i = this.firstIndex; i <= this.lastIndex; ++i) {
            if (this.this$0.fill[i].baseline > baseline) {
                baseline = this.this$0.fill[i].baseline;
            }
            final int n2 = this.this$0.fill[i].height - this.this$0.fill[i].baseline;
            if (n2 > n) {
                n = n2;
            }
        }
        this.bounds.height = n + baseline;
        this.baseline = baseline;
    }
    
    private void doLayout(final int x, final int y, boolean b, final int n) {
        int x2 = x;
        int nbInSpace = this.nbInSpace;
        if (nbInSpace == 0) {
            b = false;
        }
        this.bounds.x = x;
        this.bounds.y = y;
        for (int i = this.firstIndex; i <= this.lastIndex; ++i) {
            this.this$0.fill[i].y = y + this.baseline - this.this$0.fill[i].baseline;
            this.this$0.fill[i].x = x2;
            if (b && (SpaceBox.getBoxType(this.this$0.fill[i]) == 0 || SpaceBox.getBoxType(this.this$0.fill[i]) == 2)) {
                final int round = Math.round((n - this.inWidth) / nbInSpace);
                --nbInSpace;
                this.inWidth += round;
                final Rectangle bounds = this.bounds;
                bounds.width += round;
                final AbstractBox abstractBox = this.this$0.fill[i];
                abstractBox.width += round;
                if (nbInSpace == 0) {
                    b = false;
                }
            }
            x2 += this.this$0.fill[i].width;
        }
    }
    
    public final AbstractBox getBoxAt(final int n) {
        AbstractBox abstractBox = null;
        int i = this.lastIndex;
        int firstIndex = this.firstIndex;
        while (i >= firstIndex) {
            final int n2 = (firstIndex + i) / 2;
            abstractBox = this.this$0.fill[n2];
            if (n < abstractBox.x) {
                i = n2 - 1;
            }
            else {
                if (n <= abstractBox.x + abstractBox.width) {
                    break;
                }
                firstIndex = n2 + 1;
            }
        }
        return abstractBox;
    }
    
    static final void I(final TokensBox$Line tokensBox$Line) {
        tokensBox$Line.computeVMetrics();
    }
    
    static final int Z(final TokensBox$Line tokensBox$Line) {
        return tokensBox$Line.inWidth;
    }
    
    static final void I(final TokensBox$Line tokensBox$Line, final int n, final int n2, final boolean b, final int n3) {
        tokensBox$Line.doLayout(n, n2, b, n3);
    }
    
    static final int I(final TokensBox$Line tokensBox$Line, final int n) {
        return tokensBox$Line.nbInSpace += n;
    }
    
    static final int Z(final TokensBox$Line tokensBox$Line, final int n) {
        return tokensBox$Line.inWidth += n;
    }
}
