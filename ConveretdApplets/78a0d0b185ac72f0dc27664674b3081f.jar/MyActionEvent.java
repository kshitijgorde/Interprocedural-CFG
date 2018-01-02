// 
// Decompiled by Procyon v0.5.30
// 

public class MyActionEvent extends MyEvent
{
    public static final int ACTION_PERFORMED = 1;
    String command;
    int modifiers;
    
    public MyActionEvent(final Object source, final int id, final String command) {
        super(source, id);
        this.command = command;
        this.modifiers = 0;
    }
    
    public MyActionEvent(final Object source, final int id, final String command, final int modifiers) {
        super(source, id);
        this.command = command;
        this.modifiers = modifiers;
    }
    
    public String getActionCommand() {
        return this.command;
    }
    
    public int getModifiers() {
        return this.modifiers;
    }
}
