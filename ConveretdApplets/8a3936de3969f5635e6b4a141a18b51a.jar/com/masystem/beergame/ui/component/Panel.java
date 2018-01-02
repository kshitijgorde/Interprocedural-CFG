// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.component;

import java.awt.Color;
import javax.swing.JComponent;
import com.masystem.beergame.ui.component.swing.PanelComponent;
import com.masystem.beergame.ui.scene.Node;

public class Panel extends Node
{
    public Panel() {
        this.setComponent(new PanelComponent());
    }
    
    public Panel(final Color color) {
        this.setComponent(new PanelComponent(color));
    }
    
    public final void pack() {
        final int nbrChildren;
        if ((nbrChildren = this.getNbrChildren()) != 0) {
            final Node child;
            float left = (child = this.getChild(0)).getLeft();
            float top = child.getTop();
            float right = child.getRight();
            float bottom = child.getBottom();
            for (int i = 1; i < nbrChildren; ++i) {
                final Node child2;
                final float left2 = (child2 = this.getChild(i)).getLeft();
                final float top2 = child2.getTop();
                final float right2 = child2.getRight();
                final float bottom2 = child2.getBottom();
                if (left2 < left) {
                    left = left2;
                }
                if (top2 < top) {
                    top = top2;
                }
                if (right2 > right) {
                    right = right2;
                }
                if (bottom2 > bottom) {
                    bottom = bottom2;
                }
            }
            for (int j = 0; j < nbrChildren; ++j) {
                final Node child3;
                (child3 = this.getChild(j)).setPosition(child3.getX() - left, child3.getY() - top);
            }
            this.setSize(right - left, bottom - top);
        }
    }
}
