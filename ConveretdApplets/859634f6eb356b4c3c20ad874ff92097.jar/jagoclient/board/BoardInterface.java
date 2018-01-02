// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import java.awt.Font;
import java.awt.Color;

public interface BoardInterface
{
    boolean boardShowing();
    
    void activate();
    
    boolean bwColor();
    
    boolean blackOnly();
    
    Color boardColor();
    
    Color blackColor();
    
    Color blackSparkleColor();
    
    Color whiteColor();
    
    Color whiteSparkleColor();
    
    Color markerColor();
    
    Color labelColor();
    
    Color backgroundColor();
    
    boolean blocked();
    
    void setLabelM(final String p0);
    
    void setLabel(final String p0);
    
    void advanceTextmark();
    
    void setState(final int p0, final boolean p1);
    
    void setState(final int p0);
    
    void setMarkState(final int p0);
    
    String getComment();
    
    void setComment(final String p0);
    
    void appendComment(final String p0);
    
    void addComment(final String p0);
    
    boolean showTarget();
    
    boolean lastNumber();
    
    boolean askUndo();
    
    boolean askInsert();
    
    void yourMove(final boolean p0);
    
    void result(final int p0, final int p1);
    
    String resourceString(final String p0);
    
    boolean getParameter(final String p0, final boolean p1);
    
    Color getColor(final String p0, final int p1, final int p2, final int p3);
    
    String version();
    
    Font boardFont();
}
