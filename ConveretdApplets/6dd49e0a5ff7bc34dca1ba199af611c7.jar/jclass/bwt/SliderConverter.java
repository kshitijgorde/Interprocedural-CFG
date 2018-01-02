// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import jclass.util.JCConverter;

class SliderConverter
{
    static void getParams(final JCSlider jcSlider) {
        final JCConverter conv = JCContainer.conv;
        jcSlider.auto_tick = conv.toBoolean(jcSlider.getParam("AutoTick"), jcSlider.auto_tick);
        jcSlider.num_ticks = conv.toInt(jcSlider.getParam("NumTicks"), jcSlider.num_ticks);
        final String param = jcSlider.getParam("MaximumLabel");
        if (param != null) {
            jcSlider.setMaximumLabel(new JCLabel(param));
        }
        final String param2 = jcSlider.getParam("MinimumLabel");
        if (param2 != null) {
            jcSlider.setMinimumLabel(new JCLabel(param2));
        }
    }
}
