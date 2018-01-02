// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import treeview.TreeViewImageSet;
import java.awt.Color;
import BsscXML.IBsscXMLElementReader;
import java.awt.Image;

public class TocViewSkin extends ViewSkin
{
    private Image m_iconBookOpen;
    private Image m_iconBookClose;
    private Image m_iconItem;
    private Image m_iconRemote;
    private Image m_iconNewBookOpen;
    private Image m_iconNewBookClose;
    private Image m_iconNewItem;
    private Image m_iconNewRemote;
    public static final int BOOKOPEN = 1;
    public static final int BOOKCLOSE = 2;
    public static final int ITEM = 3;
    public static final int REMOTE = 4;
    public static final int NEWBOOKOPEN = 5;
    public static final int NEWBOOKCLOSE = 6;
    public static final int NEWITEM = 7;
    public static final int NEWREMOTE = 8;
    
    public void loadFromDom(final IBsscXMLElementReader bsscXMLElementReader) {
        super.loadFromDom(bsscXMLElementReader);
        int n = 0;
        while (true) {
            final IBsscXMLElementReader child = bsscXMLElementReader.getChild(n++);
            if (child == null) {
                break;
            }
            if (!child.getName().equals("icons")) {
                continue;
            }
            int n2 = 0;
            while (true) {
                final IBsscXMLElementReader child2 = child.getChild(n2++);
                if (child2 == null) {
                    break;
                }
                if (child2.getName().equals("book")) {
                    final Image image = ViewSkin.getImage(child2.getAttribute("open"));
                    if (image != null) {
                        this.m_iconBookOpen = image.getScaledInstance(16, 16, 4);
                    }
                    final Image image2 = ViewSkin.getImage(child2.getAttribute("close"));
                    if (image2 == null) {
                        continue;
                    }
                    this.m_iconBookClose = image2.getScaledInstance(16, 16, 4);
                }
                else if (child2.getName().equals("item")) {
                    final Image image3 = ViewSkin.getImage(child2.getAttribute("local"));
                    if (image3 != null) {
                        this.m_iconItem = image3.getScaledInstance(16, 16, 4);
                    }
                    final Image image4 = ViewSkin.getImage(child2.getAttribute("remote"));
                    if (image4 == null) {
                        continue;
                    }
                    this.m_iconRemote = image4.getScaledInstance(16, 16, 4);
                }
                else if (child2.getName().equals("newbook")) {
                    final Image image5 = ViewSkin.getImage(child2.getAttribute("open"));
                    if (image5 != null) {
                        this.m_iconNewBookOpen = image5.getScaledInstance(16, 16, 4);
                    }
                    final Image image6 = ViewSkin.getImage(child2.getAttribute("close"));
                    if (image6 == null) {
                        continue;
                    }
                    this.m_iconNewBookClose = image6.getScaledInstance(16, 16, 4);
                }
                else {
                    if (!child2.getName().equals("newitem")) {
                        continue;
                    }
                    final Image image7 = ViewSkin.getImage(child2.getAttribute("local"));
                    if (image7 != null) {
                        this.m_iconNewItem = image7.getScaledInstance(16, 16, 4);
                    }
                    final Image image8 = ViewSkin.getImage(child2.getAttribute("remote"));
                    if (image8 == null) {
                        continue;
                    }
                    this.m_iconNewRemote = image8.getScaledInstance(16, 16, 4);
                }
            }
        }
    }
    
    public TocViewSkin() {
        this.m_iconBookOpen = null;
        this.m_iconBookClose = null;
        this.m_iconItem = null;
        this.m_iconRemote = null;
        this.m_iconNewBookOpen = null;
        this.m_iconNewBookClose = null;
        this.m_iconNewItem = null;
        this.m_iconNewRemote = null;
        this.m_iconBookOpen = getImage(1);
        this.m_iconBookClose = getImage(0);
        this.m_iconItem = getImage(8);
        this.m_iconRemote = getImage(14);
        this.m_iconNewBookOpen = getImage(3);
        this.m_iconNewBookClose = getImage(2);
        this.m_iconNewItem = getImage(9);
        this.m_iconNewRemote = getImage(15);
    }
    
    public TocViewSkin(final Image image, final Color color, final BsscFont bsscFont, final BsscFont bsscFont2, final Color color2, final int n, final int n2, final Image image2, final Image image3, final Image image4, final Image image5, final Image image6, final Image image7, final Image image8, final Image image9) {
        super(image, color, bsscFont, bsscFont2, color2, n, n2);
        this.m_iconBookOpen = null;
        this.m_iconBookClose = null;
        this.m_iconItem = null;
        this.m_iconRemote = null;
        this.m_iconNewBookOpen = null;
        this.m_iconNewBookClose = null;
        this.m_iconNewItem = null;
        this.m_iconNewRemote = null;
        if (image2 != null) {
            this.m_iconBookOpen = image2.getScaledInstance(16, 16, 4);
        }
        else {
            this.m_iconBookOpen = getImage(1);
        }
        if (image3 != null) {
            this.m_iconBookClose = image3.getScaledInstance(16, 16, 4);
        }
        else {
            this.m_iconBookClose = getImage(0);
        }
        if (image4 != null) {
            this.m_iconItem = image4.getScaledInstance(16, 16, 4);
        }
        else {
            this.m_iconItem = getImage(8);
        }
        if (image5 != null) {
            this.m_iconRemote = image5.getScaledInstance(16, 16, 4);
        }
        else {
            this.m_iconRemote = getImage(14);
        }
        if (image6 != null) {
            this.m_iconNewBookOpen = image6.getScaledInstance(16, 16, 4);
        }
        else {
            this.m_iconNewBookOpen = getImage(3);
        }
        if (image7 != null) {
            this.m_iconNewBookClose = image7.getScaledInstance(16, 16, 4);
        }
        else {
            this.m_iconNewBookClose = getImage(2);
        }
        if (image8 != null) {
            this.m_iconNewItem = image8.getScaledInstance(16, 16, 4);
        }
        else {
            this.m_iconNewItem = getImage(9);
        }
        if (image9 != null) {
            this.m_iconNewRemote = image9.getScaledInstance(16, 16, 4);
            return;
        }
        this.m_iconNewRemote = getImage(15);
    }
    
    static Image getImage(final int n) {
        final TreeViewImageSet instance = TreeViewImageSet.getInstance();
        if (instance != null) {
            return instance.getImage(n);
        }
        return null;
    }
    
    public Image getIconImage(final int n) {
        switch (n) {
            case 1: {
                return this.m_iconBookOpen;
            }
            case 2: {
                return this.m_iconBookClose;
            }
            case 3: {
                return this.m_iconItem;
            }
            case 4: {
                return this.m_iconRemote;
            }
            case 5: {
                return this.m_iconNewBookOpen;
            }
            case 6: {
                return this.m_iconNewBookClose;
            }
            case 7: {
                return this.m_iconNewItem;
            }
            case 8: {
                return this.m_iconNewRemote;
            }
            default: {
                return null;
            }
        }
    }
}
