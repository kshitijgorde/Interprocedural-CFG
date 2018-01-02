import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.net.MalformedURLException;
import java.awt.Dimension;
import java.util.Enumeration;
import java.net.URL;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Rectangle;
import java.util.Hashtable;
import java.util.Vector;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TreeApp extends Applet
{
    private LightScrollbar m_vbar;
    private LightScrollbar m_hbar;
    boolean has_vbar;
    boolean has_hbar;
    public int m_offsetx;
    public int m_offsety;
    private int m_image_width;
    private int m_image_height;
    private Image m_image;
    private Image m_bgimage;
    private Image m_cachebgimage;
    private Image m_bimage;
    boolean m_tileBackground;
    boolean m_init_size;
    public FolderItem m_folder;
    private Item m_selected;
    private Item m_mouseHit;
    private int m_activation_type;
    private popup m_popup;
    public Color m_detailColour;
    public Color m_highColour;
    public Color m_textColour;
    public Color m_highTextColour;
    public Color m_bgColour;
    public Color m_mouseOverColour;
    public Color m_underColour;
    public Color m_plusColour;
    private Vector m_folderlist;
    private int m_foldernumber;
    public Hashtable m_imageList;
    private boolean m_block;
    private boolean m_autoclose;
    
    public Rectangle getRect() {
        final Rectangle rectangle = new Rectangle(this.size());
        rectangle.x = this.m_offsetx;
        rectangle.y = this.m_offsety;
        if (this.has_vbar) {
            final Rectangle rectangle2 = rectangle;
            rectangle2.width -= this.m_vbar.size().width;
        }
        if (this.has_hbar) {
            final Rectangle rectangle3 = rectangle;
            rectangle3.height -= this.m_hbar.size().height;
        }
        return rectangle;
    }
    
    public synchronized void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
        if (this.m_folder != null) {
            this.draw();
            this.refresh();
        }
    }
    
    public void stop() {
        this.m_popup.stop();
        this.m_selected = null;
        this.m_mouseHit = null;
        this.m_activation_type = 0;
    }
    
    public TreeApp() {
        this.m_init_size = true;
        this.m_detailColour = Color.black;
        this.m_textColour = Color.black;
        this.m_highTextColour = Color.lightGray;
        this.m_bgColour = Color.white;
        this.m_mouseOverColour = Color.blue;
        this.m_underColour = Color.blue;
        this.m_plusColour = Color.black;
    }
    
    public void initalise() {
        this.m_folder.initalise(this);
        this.draw();
    }
    
    public void setSelected(final Item selected) {
        if (this.m_selected != selected) {
            (this.m_selected = selected).activate(3, (Event)null, (Applet)this);
            this.makeVisible(selected.getRect());
            this.refresh();
        }
    }
    
    public synchronized boolean replaceItem(final FolderItem folderItem, final FolderItem folder) {
        this.m_block = true;
        this.m_popup.clear(true);
        this.refresh();
        if (this.m_folder == folderItem) {
            this.m_folder = folder;
        }
        else if (!this.m_folder.replaceItem(folderItem, folder)) {
            return this.m_block = false;
        }
        this.m_selected = null;
        this.m_mouseHit = null;
        this.m_activation_type = 0;
        this.initalise();
        this.m_block = false;
        return true;
    }
    
    public void draw() {
        final Rectangle layout = this.m_folder.layout(2, 2);
        this.setSize(layout.x + layout.width, layout.y + layout.height);
        final Graphics canvas = this.getCanvas();
        if (this.m_cachebgimage != null) {
            canvas.drawImage(this.m_cachebgimage, 0, 0, this.m_bgColour, this);
        }
        else {
            canvas.setColor(this.m_bgColour);
            canvas.fillRect(0, 0, this.m_image_width, this.m_image_height);
        }
        this.m_folder.draw(canvas, this, 1);
        canvas.dispose();
        this.refresh();
    }
    
    public void paint(final Graphics graphics) {
        if (this.m_image != null) {
            graphics.drawImage(this.m_image, -this.m_offsetx, -this.m_offsety, this.m_bgColour, this);
            graphics.translate(-this.m_offsetx, -this.m_offsety);
            if (this.m_selected != null) {
                this.m_selected.draw(graphics, this, 2);
            }
        }
    }
    
    public void destroy() {
        this.m_popup.stop();
        this.m_popup = null;
    }
    
    private void scroll(final FolderItem folderItem, final boolean b) {
        this.m_popup.clear(true);
        folderItem.expand(true);
        if (b) {
            if (this.m_autoclose && b) {
                this.closeAll(folderItem);
            }
            final Rectangle layout = this.m_folder.layout(2, 2);
            this.setSize(layout.x + layout.width, layout.y + layout.height);
            this.makeVisible(folderItem.getRect());
        }
        final Item selected = this.m_selected;
        this.m_selected = null;
        try {
            if (this.m_bimage != null) {
                final Graphics canvas = this.getCanvas();
                canvas.setColor(this.m_bgColour);
                canvas.fillRect(0, 0, this.m_image_width, this.m_image_height);
                if (this.m_cachebgimage != null) {
                    canvas.drawImage(this.m_cachebgimage, 0, 0, this.m_bgColour, this);
                }
                this.m_folder.draw(canvas, this, 1);
                canvas.dispose();
                final Graphics graphics = this.m_bimage.getGraphics();
                final Graphics graphics2 = this.getGraphics();
                final Rectangle rect = folderItem.getRect();
                final Rectangle titleRect = folderItem.getTitleRect();
                final int n = titleRect.y + titleRect.height;
                final int n2 = rect.height - titleRect.height;
                graphics.drawImage(this.m_image, 0, 0, this.m_bgColour, this);
                graphics.clipRect(0, n, this.m_image_width, this.m_image_height);
                graphics.setColor(this.m_bgColour);
                for (int n3 = 15, i = n2; i > 0; i -= n3, n3 += 30) {
                    int n4 = 0;
                    while (++n4 < 10000) {}
                    if (i < n3) {
                        i = 0;
                    }
                    if (this.m_cachebgimage != null) {
                        graphics.drawImage(this.m_cachebgimage, 0, 0, this.m_bgColour, this);
                    }
                    else {
                        graphics.fillRect(0, n, this.m_image_width, this.m_image_height);
                    }
                    if (b) {
                        graphics.drawImage(this.m_image, 0, -i, this.m_bgColour, this);
                    }
                    else {
                        graphics.drawImage(this.m_image, 0, -(n2 - i), this.m_bgColour, this);
                    }
                    graphics2.drawImage(this.m_bimage, -this.m_offsetx, -this.m_offsety, this.m_bgColour, this);
                }
                if (this.m_cachebgimage != null) {
                    graphics.drawImage(this.m_cachebgimage, 0, 0, this.m_bgColour, this);
                }
                else {
                    graphics.fillRect(0, n, this.m_image_width, this.m_image_height);
                }
                if (b) {
                    graphics.drawImage(this.m_image, 0, 0, this.m_bgColour, this);
                }
                else {
                    graphics.drawImage(this.m_image, 0, -n2, this.m_bgColour, this);
                }
                graphics2.drawImage(this.m_bimage, -this.m_offsetx, -this.m_offsety, this.m_bgColour, this);
                graphics2.dispose();
                graphics.dispose();
            }
        }
        catch (Exception ex) {}
        this.m_selected = selected;
        folderItem.expand(b);
        if (!b) {
            this.draw();
            return;
        }
        this.refresh();
    }
    
    public void select(final String s) {
        try {
            final Item item = this.m_folder.findItem(new URL(this.getDocumentBase(), s), true);
            if (item != null) {
                this.draw();
                this.setSelected(item);
            }
        }
        catch (Exception ex) {}
    }
    
    public void update(final Graphics graphics) {
        if (this.m_init_size) {
            this.m_init_size = false;
            this.draw();
            return;
        }
        this.paint(graphics);
    }
    
    private Color getParamColor(final String s) {
        Color color = Color.white;
        s.trim();
        final String parameter;
        if ((parameter = this.getParameter(s)) != null) {
            if (parameter.compareTo("black") == 0) {
                color = Color.black;
            }
            else if (parameter.compareTo("blue") == 0) {
                color = Color.blue;
            }
            else if (parameter.compareTo("cyan") == 0) {
                color = Color.cyan;
            }
            else if (parameter.compareTo("darkgray") == 0) {
                color = Color.darkGray;
            }
            else if (parameter.compareTo("gray") == 0) {
                color = Color.gray;
            }
            else if (parameter.compareTo("green") == 0) {
                color = Color.green;
            }
            else if (parameter.compareTo("lightgray") == 0) {
                color = Color.lightGray;
            }
            else if (parameter.compareTo("magenta") == 0) {
                color = Color.magenta;
            }
            else if (parameter.compareTo("orange") == 0) {
                color = Color.orange;
            }
            else if (parameter.compareTo("pink") == 0) {
                color = Color.pink;
            }
            else if (parameter.compareTo("red") == 0) {
                color = Color.red;
            }
            else if (parameter.compareTo("white") == 0) {
                color = Color.white;
            }
            else if (parameter.compareTo("yellow") == 0) {
                color = Color.yellow;
            }
            else if (parameter.compareTo("rose") == 0) {
                color = new Color(153, 0, 66);
            }
            else {
                try {
                    final Integer value = Integer.valueOf(parameter, 16);
                    if (value != null) {
                        color = new Color(value);
                    }
                }
                catch (NumberFormatException ex) {
                    return null;
                }
            }
            return color;
        }
        return null;
    }
    
    public void next(final boolean b) {
        final Item next;
        if ((next = this.m_folder.getNext(this.m_selected, b)) != null) {
            if (b) {
                this.draw();
            }
            this.setSelected(next);
        }
    }
    
    public boolean makeVisible(final Rectangle rectangle) {
        final Rectangle rect = this.getRect();
        final int n = rect.y + rect.height;
        int n2 = rectangle.y + rectangle.height;
        int n3 = 0;
        if (rect.y < rectangle.y && n < n2) {
            if (rect.height < rectangle.height) {
                n2 -= rectangle.height - rect.height;
            }
            n3 = n2 - n;
        }
        else if (rect.y > rectangle.y) {
            n3 = rectangle.y - rect.y;
        }
        if (n3 != 0) {
            this.m_offsety += n3;
            if (this.m_vbar != null) {
                this.m_vbar.setValue(this.m_offsety);
            }
            return true;
        }
        if (this.m_folder.getRect().height < rect.height && this.m_offsety != 0) {
            this.m_offsety = 0;
            if (this.m_vbar != null) {
                this.m_vbar.setValue(0);
            }
            return true;
        }
        return false;
    }
    
    public void start() {
        this.initalise();
        this.m_popup.start();
    }
    
    private void closeAll(final FolderItem folderItem) {
        if (folderItem == null || folderItem.m_parent == null) {
            return;
        }
        final Enumeration elements = folderItem.m_parent.elements();
        while (elements.hasMoreElements()) {
            final Item item = elements.nextElement();
            if (item instanceof FolderItem) {
                final FolderItem folderItem2 = (FolderItem)item;
                if (!folderItem2.isExpanded() || folderItem2 == folderItem) {
                    continue;
                }
                folderItem2.expand(false);
            }
        }
    }
    
    public Graphics getCanvas() {
        return this.m_image.getGraphics();
    }
    
    public void clearRect(final Graphics graphics, final Rectangle rectangle) {
        graphics.setColor(this.m_bgColour);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        if (this.m_cachebgimage != null) {
            graphics.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            graphics.drawImage(this.m_cachebgimage, 0, 0, this);
        }
    }
    
    public void setSize(int image_width, int image_height) {
        final Dimension size = this.size();
        if (size.width <= 0) {
            size.width = 1;
        }
        if (size.height <= 0) {
            size.height = 1;
        }
        if (image_width <= 0) {
            image_width = 1;
        }
        if (image_height <= 0) {
            image_height = 1;
        }
        if (image_width != this.m_image_width || image_height != this.m_image_height) {
            this.m_image_width = image_width;
            this.m_image_height = image_height;
            if (image_width < size.width) {
                this.m_image_width = size.width;
            }
            if (image_height < size.height) {
                this.m_image_height = size.height;
            }
            if (this.m_image != null) {
                this.m_image.flush();
            }
            if (this.m_cachebgimage != null) {
                this.m_cachebgimage.flush();
            }
            if (this.m_bimage != null) {
                this.m_bimage.flush();
            }
            this.m_image = this.createImage(this.m_image_width, this.m_image_height);
            this.m_bimage = this.createImage(this.m_image_width, this.m_image_height);
            if (this.m_bgimage != null) {
                this.m_cachebgimage = this.createImage(this.m_image_width, this.m_image_height);
            }
        }
        if (this.m_cachebgimage != null) {
            final Graphics graphics = this.m_cachebgimage.getGraphics();
            if (graphics == null) {
                return;
            }
            graphics.setColor(this.m_bgColour);
            graphics.fillRect(0, 0, this.m_image_width, this.m_image_height);
            if (this.m_bgimage != null) {
                if (this.m_tileBackground) {
                    final int width = this.m_bgimage.getWidth(this);
                    final int height = this.m_bgimage.getHeight(this);
                    if (width > 0 && height > 0) {
                        int i = 0;
                        int n = 0;
                        while (i < this.m_image_width) {
                            graphics.drawImage(this.m_bgimage, i, n, this.m_bgColour, this);
                            while (n + height < this.m_image_height) {
                                n += height;
                                graphics.drawImage(this.m_bgimage, i, n, this.m_bgColour, this);
                            }
                            n = 0;
                            i += width;
                        }
                    }
                }
                else {
                    graphics.drawImage(this.m_bgimage, 0, 0, this.m_bgColour, this);
                }
                graphics.dispose();
            }
        }
        if (this.getRect().height > image_height - this.m_offsety) {
            this.m_offsety = image_height - this.getRect().height;
        }
        if (this.getRect().width > image_width - this.m_offsetx) {
            this.m_offsetx = image_width - this.getRect().width;
        }
        boolean b = false;
        if (this.m_hbar == null || this.m_vbar == null) {
            return;
        }
        boolean b2 = false;
        boolean b3 = false;
        boolean b4;
        do {
            b4 = false;
            int width2 = size.width;
            int height2 = size.height;
            if (b3) {
                height2 -= 11;
            }
            if (b2) {
                width2 -= 11;
            }
            if (b2 != image_height > height2) {
                b2 = (image_height > height2);
                b4 = true;
            }
            if (b3 != image_width > width2) {
                b4 = true;
                b3 = (image_width > width2);
            }
        } while (b4);
        if (!this.has_vbar) {
            this.m_offsety = 0;
        }
        if (this.has_vbar != b2) {
            this.m_vbar.setVisible(b2);
            b = true;
            if (!(this.has_vbar = b2)) {
                this.m_offsety = 0;
            }
        }
        if (b2) {
            if (b3) {
                final Dimension dimension = size;
                dimension.height -= 11;
            }
            this.m_vbar.setValues(this.m_offsety, size.height, 0, this.m_image_height - size.height);
            this.m_vbar.setLineIncrement(15);
        }
        else {
            this.m_offsety = 0;
        }
        if (!this.has_hbar) {
            this.m_offsetx = 0;
        }
        if (this.has_hbar != b3) {
            b = true;
            this.m_hbar.setVisible(b3);
            this.has_hbar = b3;
        }
        if (b3) {
            if (b2) {
                final Dimension dimension2 = size;
                dimension2.width -= 11;
            }
            this.m_hbar.setValues(this.m_offsetx, size.width, 0, this.m_image_width - size.width);
            this.m_hbar.setLineIncrement(15);
            this.m_hbar.setLeft(this.has_vbar ? 12 : 0);
        }
        else {
            this.m_offsetx = 0;
        }
        if (b) {
            this.layout();
        }
    }
    
    public void refresh() {
        final Graphics graphics = this.getGraphics();
        this.update(graphics);
        graphics.dispose();
    }
    
    public void init() {
        this.m_tileBackground = (this.getParameter("tile") != null);
        Item.m_showURL = (this.getParameter("showURL") != null);
        this.m_autoclose = (this.getParameter("autoclose") != null);
        this.m_imageList = new Hashtable();
        this.m_popup = new popup(this);
        final String parameter;
        if ((parameter = this.getParameter("bgImage")) != null) {
            try {
                this.m_bgimage = this.getImage(new URL(this.getCodeBase(), parameter));
            }
            catch (MalformedURLException ex2) {}
        }
        final Color paramColor;
        if ((paramColor = this.getParamColor("bgColor")) != null) {
            this.m_bgColour = paramColor;
        }
        this.setBackground(this.m_bgColour);
        final Color paramColor2;
        if ((paramColor2 = this.getParamColor("bgHighlight")) != null) {
            this.m_highColour = paramColor2;
        }
        final Color paramColor3;
        if ((paramColor3 = this.getParamColor("fgColor")) != null) {
            this.m_detailColour = paramColor3;
        }
        final Color paramColor4;
        if ((paramColor4 = this.getParamColor("textColor")) != null) {
            this.m_textColour = paramColor4;
        }
        final Color paramColor5;
        if ((paramColor5 = this.getParamColor("textHighlight")) != null) {
            this.m_highTextColour = paramColor5;
        }
        final Color paramColor6;
        if ((paramColor6 = this.getParamColor("mouseOver")) != null) {
            this.m_mouseOverColour = paramColor6;
        }
        final Color paramColor7;
        if ((paramColor7 = this.getParamColor("mouseunder")) != null) {
            this.m_underColour = paramColor7;
        }
        else {
            this.m_underColour = this.m_mouseOverColour;
        }
        final Color paramColor8;
        if ((paramColor8 = this.getParamColor("tooltext")) != null) {
            this.m_popup.m_textCol = paramColor8;
        }
        final Color paramColor9;
        if ((paramColor9 = this.getParamColor("toolback")) != null) {
            this.m_popup.m_backCol = paramColor9;
        }
        final Color paramColor10;
        if ((paramColor10 = this.getParamColor("plusColor")) != null) {
            this.m_plusColour = paramColor10;
        }
        else {
            this.m_plusColour = this.m_detailColour;
        }
        this.setFont(new Font("Dialog", 0, 10));
        this.m_vbar = new LightScrollbar(1);
        this.m_hbar = new LightScrollbar(0);
        this.setLayout(new BorderLayout(0, 0));
        this.add("East", this.m_vbar);
        this.add("South", this.m_hbar);
        this.has_vbar = true;
        this.has_hbar = true;
        this.setSize(1, 1);
        this.m_selected = null;
        this.m_activation_type = 0;
        this.m_mouseHit = null;
        this.m_folderlist = new Vector();
        String parameter2 = this.getParameter("title");
        if (parameter2 == null) {
            parameter2 = "www.jexplorer.com v1.02";
        }
        int n = 1;
        String parameter3;
        while ((parameter3 = this.getParameter("file" + n)) != null) {
            final FolderItem folderItem = new FolderItem(parameter2);
            folderItem.m_font = this.getFont();
            this.m_folderlist.addElement(folderItem);
            ++n;
            try {
                folderItem.m_treejit = new Parser(new URL(this.getDocumentBase(), parameter3), folderItem, this.getDocumentBase(), this.getDocumentBase(), this);
            }
            catch (Exception ex) {
                folderItem.setStatus("Error: " + ex);
            }
        }
        if (this.m_folderlist.isEmpty()) {
            this.m_folder = new FolderItem("Error no file items");
        }
        else {
            this.m_folder = this.m_folderlist.firstElement();
        }
        this.m_foldernumber = 0;
        this.m_folder.expand(true);
        if (this.m_folder.m_treejit != null) {
            this.m_folder.m_treejit.start();
        }
        this.m_folder.m_treejit = null;
        this.initalise();
    }
    
    public boolean handleEvent(final Event event) {
        if (this.m_block) {
            return true;
        }
        if (event.target == this.m_hbar) {
            switch (event.id) {
                case 601:
                case 602:
                case 603:
                case 604:
                case 605: {
                    this.m_offsetx = (int)event.arg;
                    this.update(this.getGraphics());
                    break;
                }
            }
            return true;
        }
        if (event.target == this.m_vbar) {
            switch (event.id) {
                case 601:
                case 602:
                case 603:
                case 604:
                case 605: {
                    this.m_offsety = (int)event.arg;
                    this.update(this.getGraphics());
                    break;
                }
            }
            return true;
        }
        event.translate(this.m_offsetx, this.m_offsety);
        int n = 2;
        switch (event.id) {
            case 503: {
                n |= 0x8;
            }
            case 501: {
                if (this.m_popup.clear(false)) {
                    this.refresh();
                }
                final Item hitTest;
                if ((hitTest = this.m_folder.hitTest(event.x, event.y)) != null) {
                    if (hitTest != this.m_mouseHit) {
                        if (hitTest != this.m_selected) {
                            final Graphics graphics = this.getGraphics();
                            graphics.translate(-this.m_offsetx, -this.m_offsety);
                            hitTest.draw(graphics, this, n);
                            graphics.dispose();
                        }
                        final Graphics graphics2 = this.getGraphics();
                        graphics2.translate(-this.m_offsetx, -this.m_offsety);
                        if (this.m_mouseHit != null && this.m_mouseHit != this.m_selected) {
                            this.m_mouseHit.draw(graphics2, this, 4);
                        }
                        (this.m_mouseHit = hitTest).activate(3, (Event)null, (Applet)this);
                        this.m_popup.setText(hitTest.m_status, event);
                        graphics2.dispose();
                    }
                }
                else {
                    if (this.m_mouseHit != null) {
                        final Graphics graphics3 = this.getGraphics();
                        graphics3.translate(-this.m_offsetx, -this.m_offsety);
                        if (this.m_mouseHit != this.m_selected) {
                            this.m_mouseHit.draw(graphics3, this, 4);
                        }
                        graphics3.dispose();
                        this.m_mouseHit = null;
                    }
                    this.m_popup.clear(true);
                }
                return true;
            }
            case 502: {
                if (this.m_mouseHit != null) {
                    final Item hitTest2;
                    if ((hitTest2 = this.m_folder.hitTest(event.x, event.y)) != this.m_mouseHit) {
                        final Graphics graphics4 = this.getGraphics();
                        graphics4.translate(-this.m_offsetx, -this.m_offsety);
                        this.m_mouseHit.draw(graphics4, this, 4);
                        graphics4.dispose();
                    }
                    else {
                        if (event.clickCount > 1) {
                            this.m_activation_type = 1;
                        }
                        else {
                            this.m_activation_type = 2;
                        }
                        this.m_selected = hitTest2;
                        if (hitTest2.activate(this.m_activation_type, event, (Applet)this) && hitTest2 instanceof FolderItem) {
                            final FolderItem folderItem = (FolderItem)this.m_selected;
                            this.scroll(folderItem, folderItem.isExpanded());
                        }
                        this.m_mouseHit = null;
                        this.refresh();
                    }
                }
                return true;
            }
            case 505: {
                if (this.m_popup.clear(true) || this.m_mouseHit != null) {
                    this.refresh();
                }
                this.m_mouseHit = null;
                this.showStatus("JExplorer - www.jexplorer.com");
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public void prev(final boolean b) {
        final Item prev;
        if ((prev = this.m_folder.getPrev(this.m_selected, b)) != null) {
            if (b) {
                this.draw();
            }
            this.setSelected(prev);
        }
    }
    
    public void setFile(int foldernumber) {
        --foldernumber;
        try {
            if (!this.m_folderlist.isEmpty() && foldernumber != this.m_foldernumber) {
                this.m_folderlist.setElementAt(this.m_folder, this.m_foldernumber);
                if (this.replaceItem(this.m_folder, this.m_folderlist.elementAt(foldernumber))) {
                    this.m_folder.expand(true);
                    if (this.m_folder.m_treejit != null) {
                        this.m_folder.m_treejit.start();
                    }
                    this.m_folder.m_treejit = null;
                    this.m_foldernumber = foldernumber;
                    this.draw();
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (this.m_block) {
            return true;
        }
        if (this.m_popup.clear(true)) {
            this.refresh();
        }
        switch (n) {
            case 1004: {
                this.prev(false);
                break;
            }
            case 1005: {
                this.next(false);
                break;
            }
            case 10: {
                if (!this.m_selected.activate(1, (Event)null, (Applet)this)) {
                    break;
                }
                if (this.m_selected instanceof FolderItem) {
                    final FolderItem folderItem = (FolderItem)this.m_selected;
                    this.scroll(folderItem, folderItem.isExpanded());
                    break;
                }
                this.draw();
                if (this.makeVisible(this.m_selected.getRect())) {
                    this.refresh();
                    break;
                }
                break;
            }
            case 1007: {
                if (this.has_hbar) {
                    this.m_hbar.setValue(this.m_hbar.value + 5);
                    this.m_offsetx = this.m_hbar.value;
                    this.refresh();
                    break;
                }
                break;
            }
            case 1006: {
                this.m_hbar.setValue(this.m_hbar.value - 5);
                this.m_offsetx = this.m_hbar.value;
                this.refresh();
                break;
            }
            default: {
                return false;
            }
        }
        return true;
    }
}
