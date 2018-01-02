// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import java.awt.FontMetrics;
import java.awt.Graphics;
import com.objectbox.runner.beans.TableView;
import java.awt.Color;
import com.objectbox.runner.beans.ICellRenderer;

public class CellRenderer implements ICellRenderer
{
    Color high;
    Color medium;
    Color low;
    
    public CellRenderer() {
        this.high = new Color(222, 222, 222);
        this.medium = new Color(192, 192, 192);
        this.low = new Color(152, 152, 152);
    }
    
    public void renderCell(final TableView tableView, final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b) {
        final int n7 = 5;
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int cellheight = tableView.getCellheight();
        final int n8 = cellheight / 2 - fontMetrics.getHeight() / 2;
        if (n2 == 0) {
            graphics.setColor(Color.lightGray);
            graphics.fill3DRect(n3, n4, n5 - n3, n6 - n4, true);
            graphics.setColor(Color.black);
            graphics.drawString(tableView.getModel().getHeaders()[n], n3 + n7, n4 + cellheight - n8);
        }
        else {
            graphics.setColor(Color.black);
            if (b) {
                graphics.setColor(Color.gray);
                graphics.drawRect(n3 + 2, n4 + 2, n5 - n3 - 4, n6 - n4 - 4);
            }
            else {
                final String cell = tableView.getModel().getCell(2, n2);
                if (cell.equals("jbprops")) {
                    graphics.setColor(this.high);
                }
                else if (cell.equals("props")) {
                    graphics.setColor(this.low);
                }
                else if (cell.equals("param")) {
                    graphics.setColor(this.medium);
                }
                graphics.fillRect(n3, n4, n5 - n3, n6 - n4);
            }
            if (b) {
                graphics.setColor(Color.blue.darker());
            }
            else {
                graphics.setColor(Color.black);
            }
            graphics.drawRect(n3, n4, n5 - n3, n6 - n4);
            graphics.drawString(tableView.getModel().getCell(n, n2), n3 + n7, n4 + cellheight - n8);
        }
    }
}
