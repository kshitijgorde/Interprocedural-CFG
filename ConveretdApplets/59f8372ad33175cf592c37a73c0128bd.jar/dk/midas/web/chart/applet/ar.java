// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.event.ActionListener;
import java.awt.MenuComponent;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.PopupMenu;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Canvas;

public class ar extends Canvas implements MouseListener
{
    private String case;
    private Image do;
    private Image new;
    private boolean else;
    private boolean int;
    private MediaTracker char;
    protected int for;
    protected int byte;
    PopupMenu a;
    a5 if;
    private String try;
    
    public ar(final String case1, final Image do1, final String try1, final boolean b) {
        this.try = try1;
        this.for = 4;
        this.byte = 6;
        this.do = null;
        this.new = null;
        this.else = false;
        this.int = true;
        this.case = case1;
        this.addMouseListener(this);
        this.setFont(a0.if);
        this.a = new PopupMenu(case1);
        if (do1 != null) {
            this.prepareImage(this.do = do1, this);
            if (this.do != null) {
                (this.char = new MediaTracker(this)).addImage(this.do, 0);
                try {
                    if (!this.char.waitForAll(2000L)) {
                        System.out.println("MediaTracker timed out (" + case1 + ")");
                    }
                }
                catch (Exception ex) {}
                if (b) {
                    this.new = this.a(this.do);
                }
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.int = true;
        this.repaint();
        if (this.a != null) {
            this.a.show(this, 0, this.getSize().height);
            this.if.m = true;
        }
    }
    
    public void addNotify() {
        super.addNotify();
        if (this.char != null) {
            try {
                if (!this.char.waitForAll(2000L)) {
                    System.out.println("MediaTracker timed out ( notify )");
                }
            }
            catch (Exception ex) {}
        }
        if (this.if != null && this.if.j) {
            this.if();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.else = true;
        this.repaint();
        if (this.try != null) {
            this.if.for(this.try);
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.else = false;
        this.int = true;
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.int = false;
        this.else = true;
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final Image image = this.createImage(this.getSize().width, this.getSize().height);
        if (image == null) {
            return;
        }
        final Graphics graphics2 = image.getGraphics();
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        graphics2.setColor(this.getBackground());
        if (this.else) {
            graphics2.draw3DRect(0, 0, this.getSize().width - 1, this.getSize().height - 1, this.int);
        }
        if (!this.else && this.if.d) {
            this.a(graphics2, fontMetrics, this.new);
        }
        else {
            this.a(graphics2, fontMetrics, this.do);
        }
        this.a(graphics2, fontMetrics);
        graphics.drawImage(image, 0, 0, this);
        graphics2.dispose();
    }
    
    private void if() {
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final StringTokenizer stringTokenizer = new StringTokenizer(this.case, "'");
        final int countTokens = stringTokenizer.countTokens();
        int n = 0;
        int width = 0;
        if (this.do != null) {
            n = this.do.getHeight(null) + this.byte;
            width = this.do.getWidth(null);
            if (this.if.e) {
                width += 6;
            }
        }
        final int n2 = fontMetrics.getAscent() * countTokens + 2 * this.for;
        int stringWidth = 0;
        int n3;
        if (this.if.e) {
            n3 = n + n2;
        }
        else {
            n3 = n + (this.for - this.byte);
            if (n3 < n2) {
                n3 = n2;
            }
        }
        while (stringTokenizer.hasMoreTokens()) {
            final String string = " " + stringTokenizer.nextToken() + " ";
            if (stringWidth < fontMetrics.stringWidth(string)) {
                stringWidth = fontMetrics.stringWidth(string);
            }
        }
        if (this.if.e) {
            if (width < stringWidth) {
                width = stringWidth;
            }
        }
        else {
            width += stringWidth + this.byte + this.for;
        }
        this.setSize(width, n3);
    }
    
    private Image a(final Image image) {
        final int width = this.do.getWidth(null);
        final int height = this.do.getHeight(null);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                final int n = array[i * width + j];
                final int n2 = (n & 0xFF000000) >> 24;
                final int n3 = (int)(((n & 0xFF0000) >> 16) * 0.299) + (int)(((n & 0xFF00) >> 8) * 0.587) + (int)((n & 0xFF) * 0.114);
                array[i * width + j] = (n2 << 24 | n3 << 16 | n3 << 8 | n3);
            }
        }
        return this.createImage(new MemoryImageSource(width, height, array, 0, width));
    }
    
    private void a(final Graphics graphics, final FontMetrics fontMetrics, final Image image) {
        if (image != null) {
            if (this.if.e) {
                if (this.if.b) {
                    graphics.drawImage(image, (this.getSize().width - image.getWidth(null)) / 2, this.for, this);
                }
                else {
                    graphics.drawImage(image, (this.getSize().width - image.getWidth(null)) / 2, this.getSize().height - this.for - image.getHeight(null), this);
                }
            }
            else if (this.if.h) {
                graphics.drawImage(image, this.getSize().width - image.getWidth(null) - this.for, (this.getSize().height - image.getHeight(null)) / 2, this);
            }
            else {
                graphics.drawImage(image, this.for, (this.getSize().height - image.getHeight(null)) / 2, this);
            }
        }
    }
    
    protected void a(final Graphics graphics, final FontMetrics fontMetrics) {
        final Point point = new Point();
        final StringTokenizer stringTokenizer = new StringTokenizer(this.case, "'");
        final int countTokens = stringTokenizer.countTokens();
        graphics.setColor(this.getForeground());
        if (this.if.e) {
            if (this.if.b) {
                point.y = this.getSize().height - this.for - (countTokens - 1) * fontMetrics.getAscent();
            }
            else {
                point.y = this.for + fontMetrics.getAscent();
            }
        }
        else {
            if (this.if.h) {
                point.x = 0;
            }
            else if (this.do != null) {
                point.x = this.for + this.do.getWidth(null);
            }
            point.y = this.getSize().height / 2 - (fontMetrics.getMaxAscent() - 2 * fontMetrics.getLeading() - 2 * fontMetrics.getDescent()) * (countTokens / 2 - 1);
            if (countTokens > 1) {
                final Point point2 = point;
                point2.y -= (countTokens - 1) * (fontMetrics.getDescent() + fontMetrics.getLeading());
            }
            else {
                point.y = this.getSize().height / 2;
                final Point point3 = point;
                point3.y += fontMetrics.getAscent() / 2 - 1;
            }
        }
        if (this.do == null) {
            point.y = this.getSize().height / 2 - (fontMetrics.getMaxAscent() - 2 * fontMetrics.getLeading() - 2 * fontMetrics.getDescent()) * (countTokens / 2 - 1);
            if (countTokens > 1) {
                final Point point4 = point;
                point4.y -= (countTokens - 1) * (fontMetrics.getDescent() + fontMetrics.getLeading());
            }
            else {
                point.y = this.getSize().height / 2;
                final Point point5 = point;
                point5.y += fontMetrics.getAscent() / 2 - 1;
            }
        }
        while (stringTokenizer.hasMoreTokens()) {
            final String string = "  " + stringTokenizer.nextToken() + "  ";
            if (this.if.e || this.do == null) {
                if (this.if.i == 0) {
                    point.x = (this.getSize().width - fontMetrics.stringWidth(string)) / 2;
                }
                if (this.if.i == 1) {
                    point.x = 0;
                }
                if (this.if.i == 2) {
                    point.x = this.getSize().width - fontMetrics.stringWidth(string);
                }
            }
            if (!this.if.e && this.do != null) {
                if (this.if.i == 0) {
                    point.x = (this.getSize().width - fontMetrics.stringWidth(string) - this.do.getWidth(null)) / 2;
                }
                if (this.if.i == 1) {
                    point.x = 0;
                }
                if (this.if.i == 2) {
                    point.x = this.getSize().width - fontMetrics.stringWidth(string) - this.do.getWidth(null);
                }
                if (!this.if.h) {
                    final Point point6 = point;
                    point6.x += this.do.getWidth(null);
                }
            }
            graphics.drawString(string, point.x, point.y);
            final Point point7 = point;
            point7.y += fontMetrics.getAscent();
        }
    }
    
    void a(final MenuItem menuItem) {
        this.a.add(menuItem);
    }
    
    boolean a(final String s, final boolean b) {
        for (int i = 0; i < this.a.getItemCount(); ++i) {
            final MenuItem item = this.a.getItem(i);
            if (item instanceof Menu) {
                final Menu menu = (Menu)item;
                for (int j = 0; j < menu.getItemCount(); ++j) {
                    if (menu.getItem(j).getActionCommand().compareTo(s) == 0) {
                        menu.getItem(j).setEnabled(b);
                        return true;
                    }
                }
            }
            else if (item.getActionCommand().compareTo(s) == 0) {
                item.setEnabled(b);
                return true;
            }
        }
        return false;
    }
    
    boolean if(final String s, final boolean b) {
        for (int i = 0; i < this.a.getItemCount(); ++i) {
            final MenuItem item = this.a.getItem(i);
            if (item instanceof Menu) {
                final Menu menu = (Menu)item;
                for (int j = 0; j < menu.getItemCount(); ++j) {
                    if (menu.getItem(j).getActionCommand().compareTo(s) == 0) {
                        ((ai)menu.getItem(j)).a(b);
                        return true;
                    }
                }
            }
            else if (item.getActionCommand().compareTo(s) == 0) {
                ((ai)item).a(b);
                return true;
            }
        }
        return false;
    }
    
    MenuItem if(final String s) {
        for (int i = 0; i < this.a.getItemCount(); ++i) {
            final MenuItem item = this.a.getItem(i);
            if (item instanceof Menu) {
                final Menu menu = (Menu)item;
                for (int j = 0; j < menu.getItemCount(); ++j) {
                    if (menu.getItem(j).getActionCommand().compareTo(s) == 0) {
                        return menu.getItem(j);
                    }
                }
            }
            else if (item.getActionCommand().compareTo(s) == 0) {
                return item;
            }
        }
        return null;
    }
    
    boolean a(final String s) {
        for (int i = 0; i < this.a.getItemCount(); ++i) {
            final MenuItem item = this.a.getItem(i);
            if (item instanceof Menu) {
                final Menu menu = (Menu)item;
                for (int j = 0; j < menu.getItemCount(); ++j) {
                    if (menu.getItem(j).getActionCommand().compareTo(s) == 0) {
                        menu.remove(j);
                        return true;
                    }
                }
            }
            else if (item.getActionCommand().compareTo(s) == 0) {
                this.a.remove(item);
                return true;
            }
        }
        return false;
    }
    
    void a(final ActionListener actionListener) {
        this.a.addActionListener(actionListener);
    }
    
    private void a(final MenuItem menuItem, final ActionListener actionListener) {
        if (menuItem instanceof ai) {
            menuItem.removeActionListener(actionListener);
        }
        else if (menuItem instanceof Menu) {
            for (int i = 0; i < ((Menu)menuItem).getItemCount(); ++i) {
                this.a(((Menu)menuItem).getItem(i), actionListener);
            }
        }
    }
    
    PopupMenu a() {
        return this.a;
    }
}
