import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

// 
// Decompiled by Procyon v0.5.30
// 

class ListWatcherPane
{
    static final int CELL_MARGIN = 20;
    static final int CELL_WIDTH = 42;
    static final int CELL_HEIGHT = 21;
    Object[] list;
    ArrayList cells;
    ListWatcher ownerListWatcher;
    int w;
    int maxIndexWidth;
    int totalHeight;
    
    ListWatcherPane(final ListWatcher ownerListWatcher) {
        this.w = 0;
        this.maxIndexWidth = 0;
        this.totalHeight = 0;
        this.cells = new ArrayList();
        this.ownerListWatcher = ownerListWatcher;
    }
    
    public void setList(final Object[] list) {
        this.list = list;
        this.cells = new ArrayList();
        this.totalHeight = 0;
        this.maxIndexWidth = this.maxIndexWidth(Skin.bubbleFrame.createGraphics());
        for (int i = 0; i < list.length; ++i) {
            final ListWatcherCell listWatcherCell = new ListWatcherCell(this.list[i].toString(), this.w - 20 - 5 - this.maxIndexWidth - 5);
            this.cells.add(listWatcherCell);
            this.totalHeight += listWatcherCell.h;
        }
    }
    
    public void paint(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        int n5 = n3 - n2;
        if (n5 > n3 || n2 == 0 || this.totalHeight < n4) {
            n5 = n3;
        }
        else if (n5 < n3 - (this.totalHeight - n4)) {
            n5 = n3 - (this.totalHeight - n4);
        }
        graphics.setClip(n, n3, this.w - 20 - 5, n4);
        graphics.setFont(ListWatcher.CELL_NUM_FONT);
        if (this.list == null) {
            return;
        }
        for (int i = 0; i < this.cells.size(); ++i) {
            final ListWatcherCell listWatcherCell = this.cells.get(i);
            listWatcherCell.x = n + this.maxIndexWidth + 3;
            listWatcherCell.y = n5 + 2;
            listWatcherCell.paint(graphics);
            if (this.ownerListWatcher.highlightedIndices.contains(new Integer(i + 1))) {
                graphics.setColor(Color.WHITE);
            }
            else {
                graphics.setColor(new Color(60, 60, 60));
            }
            final String string = Integer.toString(i + 1);
            graphics.drawString(string, n + (this.maxIndexWidth - WatcherReadout.stringWidth(string, ListWatcher.CELL_NUM_FONT, graphics)) / 2, n5 + (int)(listWatcherCell.h / 2.0f) + 5);
            n5 += listWatcherCell.h;
        }
    }
    
    public int getYPositionAtIndex(final int n) {
        if (this.cells.size() > 0) {
            int n2 = 0;
            for (int i = 0; i < n; ++i) {
                n2 += ((ListWatcherCell)this.cells.get(i)).h;
            }
            return n2;
        }
        return 0;
    }
    
    int maxIndexWidth(final Graphics graphics) {
        double max = 0.0;
        for (int i = 1; i < this.list.length + 1; ++i) {
            max = Math.max(max, WatcherReadout.stringWidth(Integer.toString(i), ListWatcher.LABEL_FONT, graphics));
        }
        return (int)max;
    }
}
