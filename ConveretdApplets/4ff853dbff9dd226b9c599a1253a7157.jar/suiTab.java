import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Event;
import java.awt.Dimension;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class suiTab extends Panel
{
    int top;
    int left;
    int down;
    int right;
    String[] Title;
    int num;
    int height;
    int width;
    GridBagConstraints c;
    suiTablabel[] tl;
    suiTabpanel tp;
    Panel[] p;
    CardLayout cardLayout;
    
    suiTab(final String s, final int num, final int height, final int width) {
        this.c = new GridBagConstraints();
        this.cardLayout = new CardLayout();
        this.Title = this.parse(s);
        this.num = num;
        this.height = height;
        this.width = width;
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(192, 192, 192));
        this.tl = new suiTablabel[num];
        for (int i = 0; i < num; ++i) {
            this.add(this.tl[i] = new suiTablabel(this.Title[i]), i, 0, 1, 1, 0.0, 0.0);
        }
        this.add(this.tp = new suiTabpanel(height, width), 0, 1, num + 1, 1, 0.0, 0.0);
        this.tp.setLayout(this.cardLayout);
        this.p = new Panel[num];
        for (int j = 0; j < num; ++j) {
            this.p[j] = new Panel();
            this.tp.add(this.p[j], this.Title[j]);
        }
        this.tl[0].on = true;
        this.tl[0].repaint();
    }
    
    void add(final Component component, final int gridx, final int gridy, final int gridwidth, final int gridheight, final double weightx, final double weighty) {
        final GridBagLayout gridBagLayout = (GridBagLayout)this.getLayout();
        this.c.fill = 1;
        this.c.anchor = 10;
        this.c.gridx = gridx;
        this.c.gridy = gridy;
        this.c.gridwidth = gridwidth;
        this.c.gridheight = gridheight;
        this.c.weightx = weightx;
        this.c.weighty = weighty;
        this.add(component);
        gridBagLayout.setConstraints(component, this.c);
    }
    
    void addComp(final Component component, final int n) {
        this.p[n].add(component);
    }
    
    String[] parse(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    public Dimension getMinimizeSize() {
        return new Dimension(this.width + 20, this.height + 80);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width + 20, this.height + 80);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        for (int i = 0; i < this.num; ++i) {
            if (this.locate(n, n2) == this.tl[i] && !this.tl[i].on) {
                for (int j = 0; j < this.num; ++j) {
                    if (this.tl[j].on) {
                        this.tl[j].on = false;
                        this.tl[j].repaint();
                        break;
                    }
                }
                this.tl[i].on = true;
                this.tl[i].repaint();
                this.cardLayout.show(this.tp, this.Title[i]);
                break;
            }
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(new Color(240, 240, 240));
        final int x = this.tl[0].location().x;
        final int x2 = this.tl[this.num - 1].location().x;
        final int n = this.tl[this.num - 1].location().y + 25;
        graphics.drawLine(x2 + this.tl[this.num - 1].size().width, n - 1, x + this.width - 1, n - 1);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public Insets getInsets() {
        return new Insets(this.top, this.left, this.down, this.right);
    }
}
