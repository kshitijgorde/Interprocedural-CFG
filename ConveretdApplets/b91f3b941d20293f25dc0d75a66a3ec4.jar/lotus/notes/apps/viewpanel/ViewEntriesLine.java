// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewpanel;

import java.util.Vector;

public class ViewEntriesLine
{
    public String UNID;
    public String noteID;
    public int children;
    public int descendants;
    public int siblings;
    public int indentLevel;
    public boolean isConflict;
    public boolean isCategoryTotal;
    public boolean isCollapsed;
    public boolean isMarkedForDelete;
    public String Position;
    public Vector vColumn;
    
    public ViewEntriesLine() {
        this.UNID = null;
        this.noteID = null;
        this.children = 0;
        this.descendants = 0;
        this.siblings = 0;
        this.indentLevel = 0;
        this.isConflict = false;
        this.isCategoryTotal = false;
        this.isCollapsed = false;
        this.isMarkedForDelete = false;
        this.Position = null;
        this.vColumn = null;
    }
}
