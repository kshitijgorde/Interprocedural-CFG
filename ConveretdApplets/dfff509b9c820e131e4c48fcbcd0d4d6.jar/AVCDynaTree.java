import java.awt.Color;
import java.awt.Event;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class AVCDynaTree extends Applet implements Runnable
{
    private Thread m_thread;
    CTreeElementList m_treeList;
    protected int m_rowHeight;
    protected int m_indent;
    protected int m_nodeWidth;
    int m_iHeadOff;
    protected boolean m_hasCopyRight;
    protected CImageLoader m_imgLoader;
    protected String m_serverURL;
    protected Image m_expandIcon;
    protected Image m_compressIcon;
    protected Image m_staticIcon;
    protected Font m_font;
    protected Image m_imgRow;
    protected Graphics m_grpRow;
    
    public void stop() {
        if (this.m_thread != null) {
            this.m_thread.stop();
            this.m_thread = null;
        }
    }
    
    public AVCDynaTree() {
        this.m_rowHeight = 16;
        this.m_indent = 16;
        this.m_nodeWidth = 16;
        this.m_iHeadOff = 16;
        this.m_imgLoader = new CImageLoader(this);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.m_hasCopyRight) {
            graphics.drawString("Unlicensed AVCDynaTree", 10, 20);
            return;
        }
        graphics.drawString("Copyright - Apollo V Cabrera", 0, 12);
        graphics.setFont(this.m_font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        this.m_rowHeight = fontMetrics.getHeight();
        this.m_nodeWidth = fontMetrics.charWidth('Z');
        int n = 0;
        int n2 = 0;
        int n3;
        if (this.m_rowHeight < 16) {
            n3 = fontMetrics.getAscent() - this.m_rowHeight + (this.m_rowHeight - 16) / 2;
            this.m_rowHeight = 16;
        }
        else {
            n = (this.m_rowHeight - 16) / 2;
            n3 = fontMetrics.getAscent() - this.m_rowHeight;
        }
        if (this.m_nodeWidth < 16) {
            this.m_nodeWidth = 16;
        }
        else {
            n2 = (this.m_nodeWidth - 16) / 2;
        }
        if (this.m_imgRow == null) {
            this.m_imgRow = this.createImage(this.size().width, this.m_rowHeight);
            (this.m_grpRow = this.m_imgRow.getGraphics()).setFont(this.m_font);
        }
        this.m_treeList.first();
        for (int i = 0; i < this.m_treeList.getSize(); ++i) {
            final CTreeElement element = this.m_treeList.getElement(i);
            if (this.m_imgLoader.getCount() == 0) {
                this.m_grpRow.setColor(this.getBackground());
                this.m_grpRow.fillRect(0, 0, this.m_imgRow.getWidth(null), this.m_imgRow.getHeight(null));
                Image image = this.m_expandIcon;
                if (element.m_nodeLink == null || element.m_nodeLink.compareTo("none") == 0) {
                    image = null;
                }
                else if (element.m_isExpanded) {
                    image = this.m_compressIcon;
                }
                if (image != null) {
                    this.m_grpRow.drawImage(image, this.m_indent * element.m_level + n2, n, this);
                }
                Image image2 = element.m_imgIcon;
                if (image2 == null) {
                    image2 = this.m_staticIcon;
                }
                if (image2 != null) {
                    this.m_grpRow.drawImage(image2, this.m_indent * element.m_level + n2 + 18, n, this);
                }
                this.m_grpRow.setColor(this.getForeground());
                this.m_grpRow.drawString(element.m_label, this.m_indent * element.m_level + this.m_nodeWidth + 20, this.m_rowHeight + n3);
                graphics.drawImage(this.m_imgRow, 0, this.m_rowHeight * (i + 1), this);
            }
        }
        final Rectangle bounds = this.bounds();
        final int n4 = (this.m_treeList.getSize() + 1) * this.m_rowHeight;
        if (n4 < bounds.height) {
            graphics.setColor(this.getBackground());
            graphics.fillRect(0, n4, bounds.width, bounds.height - n4);
        }
    }
    
    public void destroy() {
    }
    
    public void goHome() {
        try {
            this.getAppletContext().showDocument(new URL("http://www.apollodev.com"), "_top");
        }
        catch (MalformedURLException ex) {}
    }
    
    void expandElement(final int n) {
        if (n < 0) {
            final CTreeElement first = this.m_treeList.first();
            if (first != null) {
                this.m_treeList.removeElement(0, first.getNumberTrailers() + 1);
            }
            this.m_treeList.insertElement(0, this.fetchElements(this.getParameter("NodeLink"), 0));
            this.repaint();
            return;
        }
        final CTreeElement element = this.m_treeList.getElement(n);
        if (element == null) {
            return;
        }
        if (element.m_isExpanded) {
            final int numberInnerElements = element.getNumberInnerElements();
            if (numberInnerElements > 0) {
                this.m_treeList.removeElement(n + 1, numberInnerElements);
            }
            element.m_isExpanded = false;
            this.repaint();
            return;
        }
        if (element.m_nodeLink != null && element.m_nodeLink.compareTo("none") != 0) {
            this.m_treeList.insertElement(n + 1, this.fetchElements(element.m_nodeLink, element.m_level + 1));
            element.m_isExpanded = true;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
        if (this.m_thread == null) {
            (this.m_thread = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "Name: AVCDynaTree\r\n" + "Author: Apollo V. Cabrera";
    }
    
    CTreeElement fetchElements(String concat, final int level) {
        concat = this.m_serverURL.concat("/" + concat);
        if (concat == null || concat.compareTo("none") == 0) {
            return null;
        }
        URL url;
        try {
            url = new URL(concat);
        }
        catch (MalformedURLException ex) {
            this.getAppletContext().showStatus("Bad URL: " + concat);
            return null;
        }
        final CTreeElement cTreeElement = new CTreeElement();
        try {
            CTreeElement cTreeElement2 = cTreeElement;
            CTreeElement next = cTreeElement;
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(url.openStream()));
            int n = 0;
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                if (line.compareTo("<p>") == 0) {
                    n = 1;
                }
                if (n != 0) {
                    n = 0;
                    final String line2 = dataInputStream.readLine();
                    if (line2.compareTo("</p>") == 0) {
                        continue;
                    }
                    if (cTreeElement2 != next) {
                        cTreeElement2.setNext(next);
                    }
                    cTreeElement2 = next;
                    next = new CTreeElement();
                    n = 0;
                    cTreeElement2.m_label = line2;
                    cTreeElement2.m_level = level;
                    final String line3 = dataInputStream.readLine();
                    if (line3.compareTo("</p>") == 0) {
                        continue;
                    }
                    cTreeElement2.m_iconLink = line3;
                    if (line3 != null && line3.compareTo("none") != 0) {
                        cTreeElement2.m_imgIcon = this.m_imgLoader.loadImage(this.m_serverURL.concat("/" + line3));
                    }
                    final String line4 = dataInputStream.readLine();
                    if (line4.compareTo("</p>") == 0) {
                        continue;
                    }
                    cTreeElement2.m_contentLink = line4;
                    final String line5 = dataInputStream.readLine();
                    if (line5.compareTo("</p>") == 0) {
                        continue;
                    }
                    cTreeElement2.m_nodeLink = line5;
                    final String line6 = dataInputStream.readLine();
                    if (line6.compareTo("</p>") == 0) {
                        continue;
                    }
                    cTreeElement2.m_nodeTarget = line6;
                }
            }
        }
        catch (IOException ex2) {}
        if (cTreeElement.m_label == null) {
            return null;
        }
        return cTreeElement;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.m_imgLoader.getCount() != 0) {
            return true;
        }
        final int n3 = (n2 - this.m_iHeadOff) / this.m_rowHeight;
        if (n2 - this.m_iHeadOff < 0) {
            this.goHome();
            return true;
        }
        final int regionClicked = this.m_treeList.regionClicked(n3, n, this.m_indent, this.m_nodeWidth);
        if (regionClicked == 1) {
            this.expandElement(n3);
            this.repaint();
        }
        else if (regionClicked == 2) {
            this.m_treeList.fetchDocument(n3, this);
        }
        return true;
    }
    
    public void run() {
    }
    
    public void init() {
        final String parameter = this.getParameter("CopyRight");
        if (parameter != null && parameter.compareTo("Apollo Vaughn Cabrera") == 0) {
            this.m_hasCopyRight = true;
            this.m_serverURL = this.getParameter("ServerURL");
            if (this.m_serverURL == null) {
                this.m_serverURL = new String(this.getCodeBase().toString());
            }
            String parameter2 = this.getParameter("FontName");
            final String parameter3 = this.getParameter("FontWeight");
            final String parameter4 = this.getParameter("FontSize");
            int n = 0;
            int intValue = 10;
            if (parameter2 == null) {
                parameter2 = "Times New Roman";
            }
            if (parameter3 != null) {
                if (parameter3.compareTo("bold") == 0) {
                    n = 1;
                }
                else if (parameter3.compareTo("italic") == 0) {
                    n = 2;
                }
            }
            if (parameter4 != null) {
                intValue = Integer.valueOf(parameter4);
                if (intValue == 0) {
                    intValue = 10;
                }
            }
            this.m_font = new Font(parameter2, n, intValue);
            if (this.m_treeList == null) {
                this.m_treeList = new CTreeElementList();
                this.expandElement(-1);
            }
            final String parameter5 = this.getParameter("BackGround");
            final String parameter6 = this.getParameter("ForeGround");
            if (parameter5 != null && parameter5.compareTo("") != 0) {
                this.setBackground(new Color(Integer.parseInt(parameter5, 16)));
            }
            if (parameter6 != null && parameter6.compareTo("") != 0) {
                this.setForeground(new Color(Integer.parseInt(parameter6, 16)));
            }
            final String parameter7 = this.getParameter("ExpandIcon");
            if (parameter7 != null) {
                this.m_expandIcon = this.m_imgLoader.loadImage(this.m_serverURL.concat("/" + parameter7));
            }
            final String parameter8 = this.getParameter("CompressIcon");
            if (parameter8 != null) {
                this.m_compressIcon = this.m_imgLoader.loadImage(this.m_serverURL.concat("/" + parameter8));
            }
            final String parameter9 = this.getParameter("StaticIcon");
            if (parameter9 != null) {
                this.m_staticIcon = this.m_imgLoader.loadImage(this.m_serverURL.concat("/" + parameter9));
            }
        }
    }
}
