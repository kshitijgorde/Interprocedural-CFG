// 
// Decompiled by Procyon v0.5.30
// 

package mechanalyst;

public class Mem
{
    final int memMax = 20;
    String[] memory;
    int memTop;
    
    public void save(final String s) {
        if (this.memTop < 20) {
            this.memory[this.memTop++] = new String(s);
        }
    }
    
    public String get() {
        if (this.memTop == 0) {
            return null;
        }
        final String s = this.memory[0];
        for (int i = 0; i < this.memTop - 1; ++i) {
            this.memory[i] = this.memory[i + 1];
        }
        --this.memTop;
        return s;
    }
    
    public Mem() {
        this.memMax = 20;
        this.memory = new String[20];
    }
}
