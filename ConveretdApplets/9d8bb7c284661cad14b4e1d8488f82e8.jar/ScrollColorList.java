import java.awt.Graphics;
import java.awt.Event;
import java.awt.Color;
import java.awt.Component;
import java.awt.Scrollbar;

// 
// Decompiled by Procyon v0.5.30
// 

class ScrollColorList extends ColorList
{
    Scrollbar \u0196;
    Scrollbar \u0197;
    boolean \u0198;
    boolean \u0199;
    
    public ScrollColorList() {
        this.\u0196 = new Scrollbar(0, 0, 5, 0, 100);
        this.\u0197 = new Scrollbar(1, 0, 5, 0, 100);
        this.\u0196.hide();
        this.\u0197.hide();
        this.\u0198 = false;
        this.\u0199 = false;
        this.add(this.\u0196);
        this.add(this.\u0197);
    }
    
    void \u0196(final boolean b) {
        for (int i = 0; i < super.\u00e3.size(); ++i) {
            final ListItem listItem = super.\u00e3.elementAt(i);
            if (b) {
                listItem.\u0128(listItem.x, listItem.y, listItem.\u00e6 - 15, listItem.\u00e7);
            }
            else {
                listItem.\u0128(listItem.x, listItem.y, listItem.\u00e6 + 15, listItem.\u00e7);
            }
        }
    }
    
    public void adaptScrollbarAfterAdd() {
        if (!this.\u0198 && super.\u00ea > super.\u00e8) {
            this.\u0198 = true;
            super.\u00e9 -= 15;
            if (this.\u0199) {
                this.\u0196.reshape(2, super.\u00e7 - 17, super.\u00e6 - 4 - 15, 15);
                this.\u0197.reshape(super.\u00e6 - 17, 2, 15, super.\u00e7 - 4 - 15);
                this.\u0197.setValues(this.\u0197.getValue(), (this.\u0197.getMaximum() - this.\u0197.getMinimum()) * super.\u00e9 / super.\u00eb, this.\u0197.getMinimum(), this.\u0197.getMaximum());
                this.\u0197.setPageIncrement(this.\u0197.getVisible());
            }
            else {
                this.\u0196.reshape(2, super.\u00e7 - 17, super.\u00e6 - 4, 15);
            }
            this.\u0196.show();
        }
        if (this.\u0198) {
            this.\u0196.setValues(this.\u0196.getValue(), (this.\u0196.getMaximum() - this.\u0196.getMinimum()) * super.\u00e8 / super.\u00ea, this.\u0196.getMinimum(), this.\u0196.getMaximum());
            this.\u0196.setPageIncrement(this.\u0196.getVisible());
        }
        if (!this.\u0199 && super.\u00eb > super.\u00e9) {
            this.\u0199 = true;
            super.\u00e8 -= 15;
            if (this.\u0198) {
                this.\u0197.reshape(super.\u00e6 - 17, 2, 15, super.\u00e7 - 4 - 15);
                this.\u0197.setPageIncrement(this.\u0197.getVisible());
                this.\u0196.reshape(2, super.\u00e7 - 17, super.\u00e6 - 4 - 15, 15);
                this.\u0196.setValues(this.\u0196.getValue(), (this.\u0196.getMaximum() - this.\u0196.getMinimum()) * super.\u00e8 / super.\u00ea, this.\u0196.getMinimum(), this.\u0196.getMaximum());
                this.\u0196.setPageIncrement(this.\u0196.getVisible());
            }
            else {
                this.\u0197.reshape(super.\u00e6 - 17, 2, 15, super.\u00e7 - 4);
                if (super.\u00ea > super.\u00e8) {
                    this.\u0198 = true;
                    super.\u00e9 -= 15;
                    this.\u0196.reshape(2, super.\u00e7 - 17, super.\u00e6 - 4 - 15, 15);
                    this.\u0197.reshape(super.\u00e6 - 17, 2, 15, super.\u00e7 - 4 - 15);
                    this.\u0197.setValues(this.\u0197.getValue(), (this.\u0197.getMaximum() - this.\u0197.getMinimum()) * super.\u00e9 / super.\u00eb, this.\u0197.getMinimum(), this.\u0197.getMaximum());
                    this.\u0197.setPageIncrement(this.\u0197.getVisible());
                    this.\u0196.show();
                }
            }
            this.\u0197.show();
            this.\u0196(true);
        }
        if (this.\u0199) {
            this.\u0197.setValues(this.\u0197.getValue(), (this.\u0197.getMaximum() - this.\u0197.getMinimum()) * super.\u00e9 / super.\u00eb, this.\u0197.getMinimum(), this.\u0197.getMaximum());
            this.\u0197.setPageIncrement(this.\u0197.getVisible());
        }
    }
    
    public void adaptScrollbarAfterDel() {
        if (this.\u0198 && super.\u00ea <= super.\u00e8) {
            this.\u0198 = false;
            super.\u00e9 += 15;
            if (this.\u0199) {
                if (super.\u00eb <= super.\u00e9) {
                    this.\u0199 = false;
                    super.\u00e8 += 15;
                    this.\u0197.hide();
                    this.\u0196(false);
                }
                else {
                    this.\u0197.reshape(super.\u00e6 - 17, 2, 15, super.\u00e7 - 4);
                }
            }
            this.\u0196.hide();
        }
        if (this.\u0198) {
            this.\u0196.setValues(this.\u0196.getValue(), (this.\u0196.getMaximum() - this.\u0196.getMinimum()) * super.\u00e8 / super.\u00ea, this.\u0196.getMinimum(), this.\u0196.getMaximum());
            this.\u0196.setPageIncrement(this.\u0196.getVisible());
        }
        if (this.\u0199 && super.\u00eb <= super.\u00e9) {
            this.\u0199 = false;
            super.\u00e8 += 15;
            if (this.\u0198) {
                if (super.\u00ea <= super.\u00e8) {
                    this.\u0198 = false;
                    super.\u00e9 += 15;
                    this.\u0196.hide();
                }
            }
            else {
                this.\u0196.setValues(this.\u0196.getValue(), (this.\u0196.getMaximum() - this.\u0196.getMinimum()) * super.\u00e8 / super.\u00ea, this.\u0196.getMinimum(), this.\u0196.getMaximum());
                this.\u0196.setPageIncrement(this.\u0196.getVisible());
            }
            this.\u0197.hide();
            this.\u0196(false);
        }
        if (this.\u0199) {
            this.\u0197.setValues(this.\u0197.getValue(), (this.\u0197.getMaximum() - this.\u0197.getMinimum()) * super.\u00e9 / super.\u00eb, this.\u0197.getMinimum(), this.\u0197.getMaximum());
            this.\u0197.setPageIncrement(this.\u0197.getVisible());
        }
    }
    
    void \u00e1(final String s) {
        super.\u00e1(s);
        this.adaptScrollbarAfterAdd();
    }
    
    void \u00e1(final String s, final Color color) {
        super.\u00e1(s, color);
        this.adaptScrollbarAfterAdd();
    }
    
    boolean \u00e4(final int n) {
        if (super.\u00e4(n)) {
            this.adaptScrollbarAfterDel();
            this.\u0199();
        }
        return true;
    }
    
    boolean \u00e4(final String s) {
        if (super.\u00e4(s)) {
            this.adaptScrollbarAfterDel();
            this.\u0199();
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this.\u0197) {
            this.\u0197(((Scrollbar)event.target).getValue());
            this.repaint();
            return true;
        }
        if (event.target == this.\u0196) {
            this.\u0198(((Scrollbar)event.target).getValue());
            this.repaint();
            return true;
        }
        super.handleEvent(event);
        return false;
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.\u0198 && this.\u0199) {
            graphics.setColor(Color.lightGray);
            graphics.fillRect(super.\u00e6 - 17, super.\u00e7 - 17, 15, 15);
        }
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
        this.\u0196.reshape(2, super.\u00e7 - 17, super.\u00e6 - 4, 15);
        this.\u0197.reshape(super.\u00e6 - 17, 2, 15, super.\u00e7 - 4);
    }
    
    void \u0197(final int n) {
        final int n2 = n * super.\u00eb / this.\u0197.getMaximum();
        int n3 = 0;
        int n4 = 0;
        int \u00ec;
        if (n2 == 0) {
            \u00ec = 0;
        }
        else {
            while (n2 > n4 && n3 < super.\u00e3.size()) {
                n4 += super.\u00e3.elementAt(n3).\u00e7;
                ++n3;
            }
            \u00ec = n3 - 1;
        }
        if (super.\u00ec != \u00ec) {
            super.\u00ec = \u00ec;
            this.\u0199();
        }
    }
    
    void \u0198(final int n) {
        for (int i = 0; i < super.\u00e3.size(); ++i) {
            ((ListItem)super.\u00e3.elementAt(i)).\u012f(n * super.\u00ea / this.\u0196.getMaximum());
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    void \u0199() {
        int n = 0;
        for (int i = super.\u00ec; i < super.\u00e3.size(); ++i) {
            final ListItem listItem = super.\u00e3.elementAt(i);
            listItem.\u0128(2, 2 + n, listItem.\u00e6, listItem.\u00e7);
            n += listItem.\u00e7;
        }
    }
}
