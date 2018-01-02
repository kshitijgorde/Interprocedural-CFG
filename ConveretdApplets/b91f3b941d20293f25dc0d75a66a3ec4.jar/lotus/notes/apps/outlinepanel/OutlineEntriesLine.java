// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.outlinepanel;

public class OutlineEntriesLine
{
    public int Level;
    public int entryType;
    public String Position;
    public String URL;
    public String targetFrame;
    public String entryLabel;
    public String UNID;
    public boolean isExpandable;
    public boolean isExpanded;
    public boolean isHidden;
    public boolean isPrivate;
    public String Icon;
    public int actionID;
    public boolean isRefuseSelection;
    public int iconImagesWide;
    public int iconImagesHigh;
    public int readingOrder;
    
    public OutlineEntriesLine() {
        this.Level = -1;
        this.entryType = 0;
        this.Position = null;
        this.URL = null;
        this.targetFrame = null;
        this.entryLabel = null;
        this.UNID = null;
        this.isExpandable = false;
        this.isExpanded = false;
        this.isHidden = false;
        this.isPrivate = false;
        this.Icon = null;
        this.actionID = -1;
        this.isRefuseSelection = false;
        this.iconImagesWide = 0;
        this.iconImagesHigh = 0;
        this.readingOrder = 0;
    }
}
