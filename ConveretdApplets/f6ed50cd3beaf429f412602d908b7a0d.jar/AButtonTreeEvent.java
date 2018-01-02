import java.io.Serializable;
import java.util.EventObject;

// 
// Decompiled by Procyon v0.5.30
// 

public class AButtonTreeEvent extends EventObject implements Serializable
{
    private String actionString;
    private int selectedLine;
    
    public AButtonTreeEvent(final Object o, final String actionString, final int selectedLine) {
        super(o);
        this.selectedLine = 0;
        this.actionString = actionString;
        if (selectedLine >= 0) {
            this.selectedLine = selectedLine;
        }
    }
    
    public String getActionString() {
        return this.actionString;
    }
    
    public int getSelectedLine() {
        return this.selectedLine;
    }
}
