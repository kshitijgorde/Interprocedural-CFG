// 
// Decompiled by Procyon v0.5.30
// 

package abc.notation;

public class TwoNotesLink
{
    protected NoteAbstract start;
    protected NoteAbstract end;
    
    protected TwoNotesLink() {
        this.start = null;
        this.end = null;
    }
    
    public NoteAbstract getEnd() {
        return this.end;
    }
    
    public void setEnd(final NoteAbstract end) {
        this.end = end;
    }
    
    public NoteAbstract getStart() {
        return this.start;
    }
    
    public void setStart(final NoteAbstract start) {
        this.start = start;
    }
}
