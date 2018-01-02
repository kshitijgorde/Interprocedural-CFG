// 
// Decompiled by Procyon v0.5.30
// 

package mechanalyst;

public class KeyStack
{
    final int stackSize = 20;
    Key[] keyStack;
    int keyTop;
    
    public void print() {
        System.out.println("Key stack " + this.keyTop);
        for (int i = 0; i < this.keyTop; ++i) {
            this.keyStack[i].printKey(0);
        }
    }
    
    public int keyTop() {
        return this.keyTop;
    }
    
    public void reset() {
        this.keyTop = 0;
    }
    
    public Key key(final int n) {
        if (n < 0 || n >= this.keyTop) {
            return null;
        }
        return this.keyStack[n];
    }
    
    public void pushKey(final Key key) {
        if (key == null) {
            System.out.println("push null key");
            return;
        }
        int keyTop;
        for (keyTop = this.keyTop; keyTop > 0 && key.rank > this.keyStack[keyTop - 1].rank; --keyTop) {
            this.keyStack[keyTop] = this.keyStack[keyTop - 1];
        }
        this.keyStack[keyTop] = key;
        ++this.keyTop;
    }
    
    public KeyStack() {
        this.stackSize = 20;
        this.keyStack = new Key[20];
    }
}
