// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.animate;

import prefuse.visual.VisualItem;
import prefuse.util.PrefuseLib;
import java.util.logging.Logger;
import prefuse.action.ItemAction;

public class ArrayAnimator extends ItemAction
{
    private static final Logger s_logger;
    private String m_field;
    private String m_start;
    private String m_end;
    
    public ArrayAnimator(final String s, final String field) {
        super(s);
        this.m_field = field;
        this.m_start = PrefuseLib.getStartField(field);
        this.m_end = PrefuseLib.getEndField(field);
    }
    
    public void process(final VisualItem visualItem, final double n) {
        final Object value = visualItem.get(this.m_field);
        if (value instanceof float[]) {
            final float[] array = (float[])value;
            final float[] array2 = (float[])visualItem.get(this.m_start);
            final float[] array3 = (float[])visualItem.get(this.m_end);
            final float n2 = (float)n;
            for (int n3 = 0; n3 < array.length && !Float.isNaN(array[n3]); ++n3) {
                array[n3] = array2[n3] + n2 * (array3[n3] - array2[n3]);
            }
            visualItem.setValidated(false);
        }
        else if (value instanceof double[]) {
            final double[] array4 = (double[])value;
            final double[] array5 = (double[])visualItem.get(this.m_start);
            final double[] array6 = (double[])visualItem.get(this.m_end);
            for (int n4 = 0; n4 < array4.length && !Double.isNaN(array4[n4]); ++n4) {
                array4[n4] = array5[n4] + n * (array6[n4] - array5[n4]);
            }
            visualItem.setValidated(false);
        }
        else {
            ArrayAnimator.s_logger.warning("Encountered non-double/non-float array type: " + ((value == null) ? "null" : ((double[])value).getClass().getName()));
        }
    }
    
    static {
        s_logger = Logger.getLogger(ArrayAnimator.class.getName());
    }
}
