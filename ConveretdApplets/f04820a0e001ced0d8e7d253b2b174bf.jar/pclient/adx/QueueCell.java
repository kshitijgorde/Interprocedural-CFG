// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListCellRenderer;
import javax.swing.JLabel;

public class QueueCell extends JLabel implements ListCellRenderer
{
    private ModWindow modWindow;
    private DefaultListModel listModel;
    private JList dataList;
    
    public QueueCell(final ModWindow modWindow, final DefaultListModel listModel, final JList dataList) {
        this.modWindow = modWindow;
        this.listModel = listModel;
        this.dataList = dataList;
        this.setOpaque(true);
    }
    
    public Component getListCellRendererComponent(final JList list, final Object o, int n, final boolean b, final boolean b2) {
        if (n == -1) {
            final int selectedIndex = list.getSelectedIndex();
            if (selectedIndex == -1) {
                return this;
            }
            n = selectedIndex;
        }
        final QueueData queueData = this.listModel.get(n);
        if (queueData == null) {
            System.out.println("Err 28732");
            return this;
        }
        this.setText(queueData.id + " \"" + queueData.sender + "\" " + queueData.question);
        if (queueData.question != null) {
            this.setToolTipText("<html><center>" + queueData.question + "</center></html>");
        }
        Color background;
        if (queueData.background != null) {
            background = queueData.background;
        }
        else {
            background = list.getBackground();
        }
        Color foreground;
        if (queueData.foreground != null) {
            foreground = queueData.foreground;
        }
        else {
            foreground = list.getForeground();
        }
        if (b) {
            final Color color = foreground;
            foreground = background;
            background = color;
        }
        this.setBackground(background);
        this.setForeground(foreground);
        return this;
    }
}
