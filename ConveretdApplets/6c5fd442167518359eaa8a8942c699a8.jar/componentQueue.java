import java.awt.List;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.TextComponent;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class componentQueue extends circQueue
{
    public synchronized void addElement(final Component component) {
        if (component instanceof TextComponent || component instanceof Checkbox || component instanceof Button || component instanceof Choice || component instanceof List) {
            super.addElement(component);
        }
    }
    
    public Component reset() {
        return (Component)this.resetQueue();
    }
    
    public Component next() {
        return (Component)this.getNext();
    }
    
    public Component prev() {
        return (Component)super.getPrev();
    }
}
