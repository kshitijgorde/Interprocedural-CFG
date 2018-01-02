// 
// Decompiled by Procyon v0.5.30
// 

package anon.terms;

public abstract class TCComponent
{
    protected double id;
    protected Object content;
    
    public TCComponent() {
        this.id = -1.0;
        this.content = null;
    }
    
    public TCComponent(final double id) {
        this.id = -1.0;
        this.content = null;
        this.id = id;
    }
    
    public TCComponent(final double id, final Object content) {
        this.id = -1.0;
        this.content = null;
        this.id = id;
        this.content = content;
    }
    
    public double getId() {
        return this.id;
    }
    
    public void setId(final double id) {
        this.id = id;
    }
    
    public Object getContent() {
        return this.content;
    }
    
    public void setContent(final Object content) {
        this.content = content;
    }
    
    public boolean hasContent() {
        return this.content != null;
    }
    
    public boolean equals(final Object o) {
        return o instanceof TCComponent && ((TCComponent)o).getId() == this.id && this.getClass().equals(o.getClass());
    }
    
    public abstract Object clone();
    
    public String toString() {
        return (this.content != null) ? (this.getClass() + "@" + this.id + ": " + this.content.toString()) : null;
    }
}
