import java.awt.geom.PathIterator;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import net.eprevue.easyplan.selection.SelectionTreeView;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_D
{
    public String a;
    public rp_ao a;
    
    private rp_D(final SelectionTreeView selectionTreeView, final byte b) {
        this.a = null;
        this.a = null;
    }
    
    public rp_D() {
    }
    
    public static String a(final GeneralPath generalPath) {
        final float[] array = new float[6];
        final StringBuffer sb = new StringBuffer();
        final PathIterator pathIterator = generalPath.getPathIterator(new AffineTransform());
        while (!pathIterator.isDone()) {
            switch (pathIterator.currentSegment(array)) {
                case 4: {
                    sb.append(" Z");
                    break;
                }
                case 3: {
                    sb.append(new StringBuffer().append(" C ").append(array[0]).append(" ").append(array[1]).append(" ").append(array[2]).append(" ").append(array[3]).append(" ").append(array[4]).append(" ").append(array[5]).toString());
                    break;
                }
                case 1: {
                    sb.append(new StringBuffer().append(" L ").append(array[0]).append(" ").append(array[1]).toString());
                    break;
                }
                case 0: {
                    sb.append(new StringBuffer().append(" M ").append(array[0]).append(" ").append(array[1]).toString());
                    break;
                }
                case 2: {
                    sb.append(new StringBuffer().append(" Q ").append(array[0]).append(" ").append(array[1]).append(" ").append(array[2]).append(" ").append(array[3]).toString());
                    break;
                }
            }
            pathIterator.next();
        }
        return sb.toString();
    }
}
