// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

public interface Position
{
    int getOffset();
    
    public static final class Bias
    {
        public static final Bias Forward;
        public static final Bias Backward;
        private String name;
        
        static {
            Forward = new Bias("Forward");
            Backward = new Bias("Backward");
        }
        
        private Bias(final String name) {
            this.name = name;
        }
        
        public String toString() {
            return this.name;
        }
    }
}
