// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class LinkedNode
{
    public Object value;
    public LinkedNode next;
    
    public LinkedNode() {
    }
    
    public LinkedNode(final Object value) {
        this.value = value;
    }
    
    public LinkedNode(final Object value, final LinkedNode next) {
        this.value = value;
        this.next = next;
    }
}
