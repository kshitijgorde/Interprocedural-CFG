// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import jclass.util.JCConverter;
import jclass.util.JCVector;
import java.awt.Component;

class ListConverter
{
    static void getParams(final JCListComponent jcListComponent) {
        final JCConverter conv = JCComponent.conv;
        final String param = jcListComponent.getParam("Items");
        final JCVector vector = conv.toVector(jcListComponent, param, ',', true, jcListComponent.getItems());
        if (param != null && vector != null && BWTUtil.instanceOf(jcListComponent, "JCMultiColumnListComponent")) {
            for (int i = 0; i < vector.size(); ++i) {
                final String element = vector.elementAt(i);
                if (element instanceof String) {
                    final JCVector vector2 = conv.toVector(jcListComponent, element, '|', true, null);
                    if (vector2 != null) {
                        vector.setElementAt((String)vector2, i);
                    }
                }
            }
        }
        jcListComponent.setItems(vector);
        jcListComponent.setSelectedBackground(conv.toColor(jcListComponent.getParam("SelectedBackground"), jcListComponent.getSelectedBackground()));
        jcListComponent.setSelectedForeground(conv.toColor(jcListComponent.getParam("SelectedForeground"), jcListComponent.getSelectedBackground()));
        jcListComponent.setAllowMultipleSelections(conv.toBoolean(jcListComponent.getParam("AllowMultipleSelections"), jcListComponent.allowsMultipleSelections()));
        jcListComponent.setAutoSelect(conv.toBoolean(jcListComponent.getParam("AutoSelect"), jcListComponent.auto_select));
        jcListComponent.setVisibleRows(conv.toInt(jcListComponent.getParam("VisibleRows"), jcListComponent.getVisibleRows()));
        final String param2 = jcListComponent.getParam("RowHeight");
        if (param2 != null) {
            if (param2.trim().equalsIgnoreCase("font_height")) {
                jcListComponent.setRowHeight(-997);
            }
            else {
                jcListComponent.setRowHeight(conv.toInt(param2, jcListComponent.getRowHeight()));
            }
        }
        jcListComponent.setSpacing(conv.toInt(jcListComponent.getParam("Spacing"), jcListComponent.getSpacing()));
    }
}
