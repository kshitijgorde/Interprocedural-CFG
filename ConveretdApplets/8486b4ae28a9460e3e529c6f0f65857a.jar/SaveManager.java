import java.awt.Color;
import java.util.Observable;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class SaveManager extends Observable
{
    Design[] designs;
    
    public SaveManager() {
        this.designs = null;
    }
    
    public abstract void performSave(final DesignCanvas p0, final Color p1);
    
    public abstract String formatMessage(final Object p0);
    
    protected void getDesigns(final DesignCanvas designCanvas) {
        if (designCanvas.countImages() == 0) {
            return;
        }
        this.designs = new Design[designCanvas.countImages()];
        designCanvas.designs.copyInto(this.designs);
    }
}
