import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Event;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class RichEdit extends Canvas
{
    private Vector Lines;
    private Vector Images;
    private IRCQNetPanel parent;
    private int width;
    private int height;
    private IRCQNetScrollBar SBar;
    private int top;
    private int LHeight;
    private Vector Colors;
    
    public RichEdit(final IRCQNetScrollBar sBar, final IRCQNetPanel parent) {
        this.Lines = new Vector(90, 3);
        this.Images = new Vector(90, 3);
        this.Colors = new Vector(17, 1);
        this.parent = parent;
        this.SBar = sBar;
        this.setFont(new Font("TimesRoman", 0, 16));
        this.Colors.addElement(new Color(255, 255, 255));
        this.Colors.addElement(new Color(0, 0, 0));
        this.Colors.addElement(new Color(0, 0, 123));
        this.Colors.addElement(new Color(0, 146, 0));
        this.Colors.addElement(new Color(255, 0, 0));
        this.Colors.addElement(new Color(123, 0, 0));
        this.Colors.addElement(new Color(156, 0, 156));
        this.Colors.addElement(new Color(255, 125, 0));
        this.Colors.addElement(new Color(255, 255, 0));
        this.Colors.addElement(new Color(0, 255, 0));
        this.Colors.addElement(new Color(0, 146, 148));
        this.Colors.addElement(new Color(0, 255, 255));
        this.Colors.addElement(new Color(0, 0, 255));
        this.Colors.addElement(new Color(255, 0, 255));
        this.Colors.addElement(new Color(123, 125, 123));
        this.Colors.addElement(new Color(214, 211, 214));
    }
    
    public void SyncScroll() {
        this.Images.size();
        this.SBar.getValue();
        if (this.LHeight > 0) {
            final int n = this.height / this.LHeight;
            if (this.Images.size() >= n) {
                if ((this.SBar.getValue() == this.top && this.top == this.SBar.getMaximum() - n) || this.top == 0) {
                    this.Images.size();
                }
                else {
                    this.SBar.getValue();
                }
                final int n2 = this.Images.size() - n;
                this.SBar.setValues(n2, 1, 0, n2);
            }
            else {
                this.SBar.setValues(0, 0, 0, 0);
            }
        }
        else {
            this.SBar.setValues(0, 0, 0, 0);
        }
        this.SBarMove();
    }
    
    public void appendToLine(final Line line, final String s, final Color color, final Color color2, final Boolean b, final Boolean b2) {
        line.Strings.addElement(s);
        line.BG.addElement(color2);
        line.FG.addElement(color);
        line.UnderLine.addElement(b);
        line.Bold.addElement(b2);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key != 10 && event.key != 13) {
                    event.target = this.parent;
                    this.parent.postEvent(event);
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void appendText(final String s) {
        final Line line = new Line();
        Color white = Color.white;
        Color black = Color.black;
        Boolean b = new Boolean(false);
        Boolean b2 = new Boolean(false);
        String string = "";
        new StringTokenizer(s);
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            switch (s.charAt(i)) {
                case '\u0001': {
                    if (n != 0) {
                        this.appendToLine(line, string, black, white, b2, b);
                        string = "";
                    }
                    n = 0;
                    break;
                }
                case '\u0002': {
                    if (n != 0) {
                        this.appendToLine(line, string, black, white, b2, b);
                        string = "";
                    }
                    b = new Boolean(!b);
                    n = 0;
                    break;
                }
                case '\u0003': {
                    if (n != 0) {
                        this.appendToLine(line, string, black, white, b2, b);
                        string = "";
                    }
                    n = 0;
                    int index = -1;
                    final String s2 = "";
                    try {
                        if (i + 1 < s.length()) {
                            if (!Character.isDigit(s.charAt(i + 1))) {
                                break;
                            }
                            String s3 = s2 + s.charAt(i + 1);
                            if (++i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                                s3 += s.charAt(i + 1);
                                ++i;
                            }
                            final int int1 = Integer.parseInt(s3);
                            if (int1 >= 0 && int1 < 16) {
                                black = (Color)this.Colors.elementAt(int1);
                            }
                            if (i + 2 < s.length()) {
                                index = s.substring(i + 1, i + 2).indexOf(",");
                            }
                            if (index != -1) {
                                ++i;
                                try {
                                    String s4 = "";
                                    if (i + 1 < s.length()) {
                                        if (Character.isDigit(s.charAt(i + 1))) {
                                            s4 += s.charAt(i + 1);
                                            if (++i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                                                s4 += s.charAt(i + 1);
                                                ++i;
                                            }
                                        }
                                        else {
                                            --i;
                                        }
                                        final int int2 = Integer.parseInt(s4);
                                        if (int2 >= 0 && int2 < 16) {
                                            white = (Color)this.Colors.elementAt(int2);
                                        }
                                    }
                                }
                                catch (NumberFormatException ex) {}
                            }
                        }
                    }
                    catch (NumberFormatException ex2) {}
                    n = 0;
                    break;
                }
                case '\u001f': {
                    if (n != 0) {
                        this.appendToLine(line, string, black, white, b2, b);
                        string = "";
                    }
                    b2 = new Boolean(!b2);
                    n = 0;
                    break;
                }
                default: {
                    n = 1;
                    string += s.charAt(i);
                    if (i + 1 == s.length()) {
                        this.appendToLine(line, string, black, white, b2, b);
                        string = "";
                        break;
                    }
                    break;
                }
            }
        }
        this.Lines.addElement(line);
        line.RenderLine(this.width, this);
        for (int j = 0; j < line.getImages().size(); ++j) {
            this.SBar.getMaximum();
            this.SBar.getValue();
            if (this.Images == null) {
                this.Images = new Vector(30, 30);
            }
            this.Images.addElement(line.getImages().elementAt(j));
            if (this.Images.size() > 80) {
                while (this.Images.size() > 70) {
                    this.Images.removeElementAt(0);
                }
                System.gc();
            }
            if (this.Lines.size() > 80) {
                while (this.Lines.size() > 70) {
                    this.Lines.removeElementAt(0);
                }
                System.gc();
            }
        }
        this.SyncScroll();
        if (this.isVisible()) {
            this.repaint();
        }
    }
    
    public void reshape(final int n, final int n2, final int width, final int height) {
        super.reshape(n, n2, width, height);
        this.width = width;
        this.height = height;
        this.SyncScroll();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.isVisible()) {
            return;
        }
        try {
            if (this.height <= 0 || this.width <= 0) {
                return;
            }
            final Image image = this.createImage(this.width, this.height);
            if (image == null) {
                return;
            }
            final Graphics graphics2 = image.getGraphics();
            final int height = graphics.getFontMetrics().getHeight();
            this.LHeight = height;
            final int size = this.Images.size();
            int height2 = this.height;
            this.setBackground(Color.white);
            if ((size - this.SBar.getValue()) * height < this.height) {
                for (int i = size - 1; i >= 0; --i) {
                    if (height2 < 0) {
                        break;
                    }
                    height2 -= height;
                    graphics2.drawImage((Image)this.Images.elementAt(i), 0, height2, null);
                }
            }
            else {
                for (int n = this.SBar.getValue() + this.height / this.LHeight - 1; n >= 0 && height2 >= 0; --n) {
                    height2 -= height;
                    graphics2.drawImage((Image)this.Images.elementAt(n), 0, height2, null);
                }
            }
            image.flush();
            graphics2.dispose();
            System.gc();
            graphics.drawImage(image, 0, image.getHeight(null) - this.height, null);
        }
        catch (IllegalArgumentException ex) {}
        catch (NullPointerException ex2) {}
    }
    
    public void SBarMove() {
        this.top = this.SBar.getValue();
        this.repaint();
    }
}
