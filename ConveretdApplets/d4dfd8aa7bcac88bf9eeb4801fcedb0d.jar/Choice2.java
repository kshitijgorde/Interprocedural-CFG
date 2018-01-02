import java.awt.Dimension;
import java.awt.Choice;

// 
// Decompiled by Procyon v0.5.30
// 

public class Choice2 extends Choice
{
    public Dimension minimumSize() {
        return this.preferredSize();
    }
    
    public Dimension preferredSize() {
        final int height = super.preferredSize().height;
        return new Dimension(this.size().width, (height > 19) ? 25 : height);
    }
}
