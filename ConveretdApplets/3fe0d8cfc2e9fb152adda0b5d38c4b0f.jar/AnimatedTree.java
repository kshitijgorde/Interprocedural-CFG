import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.AppletContext;
import java.util.Enumeration;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Label;
import java.net.URL;
import java.awt.Image;
import java.util.Vector;
import java.awt.Color;
import java.util.Hashtable;
import div.tree.TreeNode;
import java.awt.Panel;
import div.tree.TreePanel;
import java.awt.Frame;
import div.tree.Treeable;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class AnimatedTree extends Applet implements Treeable
{
    Frame tree_frame;
    Frame loading;
    TreePanel treePanel;
    Panel empty;
    TreeNode tnc1;
    static int loadedImagesCnt;
    private static final String version = "2.0.046";
    private static final String homePage = "http://www.career.ru/dima/studio/index.html";
    private static final String email = "dimitry@career.ru";
    Hashtable images;
    Hashtable treeNodes;
    Hashtable links;
    protected String m_Tree;
    protected String m_Mode;
    protected String m_Links;
    protected String m_Images;
    protected String m_Target;
    protected String m_Title;
    protected String m_FocusedNode;
    protected Color bgColor;
    protected Color fgColor;
    protected Color bgSelectedColor;
    protected Color fgSelectedColor;
    protected Color fgHighlight;
    protected int frameHeight;
    protected int frameWidth;
    protected boolean enableCollapseRootNode;
    protected boolean enableAnimatedMode;
    private final String PARAM_Title = "Title";
    private final String PARAM_Mode = "Mode";
    private final String PARAM_Tree = "Tree";
    private final String PARAM_Images = "Images";
    private final String PARAM_Links = "Links";
    private final String PARAM_Target = "Target";
    private final String PARAM_fgColor = "FgColor";
    private final String PARAM_bgColor = "BgColor";
    private final String PARAM_fgSelectedColor = "FgSelectedColor";
    private final String PARAM_bgSelectedColor = "BgSelectedColor";
    private final String PARAM_fgHighlight = "FgHighlight";
    private final String PARAM_frameHeight = "FrameHeight";
    private final String PARAM_frameWidth = "FrameWidth";
    private final String PARAM_parentNodeExpanded = "ParentNodeExpanded";
    private final String PARAM_enableCollapseRootNode = "EnableCollapseRootNode";
    private final String PARAM_enableAnimatedMode = "EnableAnimatedMode";
    private final String PARAM_focusedNode = "FocusedNode";
    
    public void stop() {
        if ("Frame".equals(this.m_Mode) && this.tree_frame != null) {
            this.tree_frame.hide();
        }
    }
    
    void parseLinks() {
        this.links = new Hashtable();
        int index;
        while ((index = this.m_Links.indexOf("\n")) != -1) {
            this.m_Links = this.m_Links.substring(0, index) + this.m_Links.substring(index + 1);
        }
        int index2;
        while ((index2 = this.m_Links.indexOf("\r")) != -1) {
            this.m_Links = this.m_Links.substring(0, index2) + this.m_Links.substring(index2 + 1);
        }
        int n = -1;
        int index3;
        while ((index3 = this.m_Links.indexOf(";", n + 1)) != -1) {
            final String substring = this.m_Links.substring(n + 1, index3);
            n = index3;
            final int index4 = substring.indexOf("|");
            if (index4 == -1) {
                throw new Error("Invalid link format: " + substring);
            }
            this.links.put(substring.substring(0, index4).trim(), substring.substring(index4 + 1));
        }
    }
    
    void dowloadImages() {
        this.images = new Hashtable();
        int index;
        while ((index = this.m_Images.indexOf("\n")) != -1) {
            this.m_Images = this.m_Images.substring(0, index) + this.m_Images.substring(index + 1);
        }
        int index2;
        while ((index2 = this.m_Images.indexOf("\r")) != -1) {
            this.m_Images = this.m_Images.substring(0, index2) + this.m_Images.substring(index2 + 1);
        }
        if (!this.m_Images.endsWith(";")) {
            this.m_Images += ";";
        }
        int n = -1;
        final URL documentBase = this.getDocumentBase();
        int index3;
        while ((index3 = this.m_Images.indexOf(";", n + 1)) != -1) {
            final String substring = this.m_Images.substring(n + 1, index3);
            n = index3;
            final int index4 = substring.indexOf("|");
            final int n2;
            if ((n2 = index4) == -1) {
                throw new Error("Invalid syntax in Images parameter: " + substring);
            }
            final String trim = substring.substring(0, index4).trim();
            final int index5 = substring.indexOf("|", n2 + 1);
            final Vector<Image> vector = new Vector<Image>();
            try {
                if (index5 == -1) {
                    vector.addElement(this.getImage(new URL(documentBase, substring.substring(n2 + 1))));
                }
                else {
                    final Image image = this.getImage(new URL(documentBase, substring.substring(n2 + 1, index5)));
                    final Image image2 = this.getImage(new URL(documentBase, substring.substring(index5 + 1)));
                    vector.addElement(image);
                    vector.addElement(image2);
                }
            }
            catch (Exception ex) {
                System.out.println("Error: " + ex);
            }
            this.images.put(trim, vector);
        }
    }
    
    public void expanded(final String s) {
    }
    
    public AnimatedTree() {
        this.m_Mode = "Frame";
        this.m_Links = "";
        this.m_Images = "";
        this.m_Target = "";
        this.m_Title = "Animated Tree";
        this.bgColor = Color.white;
        this.fgColor = Color.black;
        this.bgSelectedColor = Color.blue;
        this.fgSelectedColor = Color.white;
        this.fgHighlight = Color.red;
        this.frameHeight = 280;
        this.frameWidth = 160;
        this.enableCollapseRootNode = true;
        this.enableAnimatedMode = true;
        (this.treePanel = new TreePanel((Treeable)this)).setBackground(Color.white);
        this.treePanel.setForeground(Color.black);
        (this.loading = new Frame("Please wait")).setResizable(false);
        final Label label = new Label("Loading images. Please wait...", 1);
        label.setBackground(Color.lightGray);
        this.loading.add("Center", label);
        this.loading.resize(200, 70);
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        this.loading.move((defaultToolkit.getScreenSize().width - this.size().width) / 2, (defaultToolkit.getScreenSize().height - this.size().height) / 2);
        this.loading.setBackground(Color.lightGray);
        this.loading.setCursor(3);
    }
    
    public void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Dimension size = this.size();
        final Enumeration<Vector<Image>> elements = this.images.elements();
        while (elements.hasMoreElements()) {
            final Enumeration<Image> elements2 = elements.nextElement().elements();
            while (elements2.hasMoreElements()) {
                graphics.drawImage(elements2.nextElement(), size.width + 1, size.height + 1, this);
            }
        }
    }
    
    void parseTree() {
        this.treeNodes = new Hashtable();
        int index;
        while ((index = this.m_Tree.indexOf("\n")) != -1) {
            this.m_Tree = this.m_Tree.substring(0, index) + this.m_Tree.substring(index + 1);
        }
        int index2;
        while ((index2 = this.m_Tree.indexOf("\r")) != -1) {
            this.m_Tree = this.m_Tree.substring(0, index2) + this.m_Tree.substring(index2 + 1);
        }
        int n = -1;
        String s = this.m_Tree.trim();
        if (!s.endsWith(";")) {
            s += ";";
        }
        do {
            String string = "";
            int index3;
            while ((index3 = s.indexOf(";", n + 1)) != -1) {
                String substring = null;
                final String substring2 = s.substring(n + 1, index3);
                n = index3;
                final int index4 = substring2.indexOf("|");
                if (index4 == -1) {
                    throw new Error("Invalid tree node: " + substring2);
                }
                final String trim = substring2.substring(0, index4).trim();
                final int n2 = index4;
                final int index5 = substring2.indexOf("|", n2 + 1);
                if (index5 == -1) {
                    throw new Error("Invalid tree node: " + substring2);
                }
                final String substring3 = substring2.substring(n2 + 1, index5);
                final int n3 = index5;
                final int index6 = substring2.indexOf("|", n3 + 1);
                String s2;
                if (index6 == -1) {
                    s2 = substring2.substring(n3 + 1);
                }
                else {
                    s2 = substring2.substring(n3 + 1, index6);
                    substring = substring2.substring(index6 + 1);
                }
                if (substring != null && !this.treeNodes.containsKey(substring)) {
                    string = string + substring2 + ";";
                }
                else {
                    final TreeNode treeNode = new TreeNode(substring3, trim, s2, substring, this.getImage(s2, false), this.getImage(s2, true));
                    this.treeNodes.put(trim, treeNode);
                    if (substring != null) {
                        this.treePanel.addChild(treeNode, (TreeNode)this.treeNodes.get(substring));
                    }
                    else {
                        this.treePanel.append(treeNode);
                    }
                }
            }
            s = string;
        } while (s.length() > 0);
    }
    
    public void runTree() {
        this.treePanel.enableAnimatedMode(this.enableAnimatedMode);
        this.treePanel.enableCollapseRoot(this.enableCollapseRootNode);
        if ("Frame".equals(this.m_Mode)) {
            (this.tree_frame = new Frame(this.m_Title)).add("Center", (Component)this.treePanel);
            this.tree_frame.reshape(0, 0, this.frameWidth, this.frameHeight);
            this.tree_frame.show();
            return;
        }
        this.add("Center", (Component)this.treePanel);
        ((Component)this.treePanel).show();
        this.treePanel.reshape(0, 0, this.size().width, this.size().height);
    }
    
    private void setCurFolder(final String s) {
    }
    
    public static void main(final String[] array) {
        try {
            new AnimatedTree().runTree();
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public void start() {
        if ("Frame".equals(this.m_Mode) && this.tree_frame != null) {
            this.tree_frame.show();
        }
    }
    
    Image getImage(final String s, final boolean b) {
        final Vector<Image> vector = this.images.get(s);
        if (vector == null) {
            return null;
        }
        final int n = b ? 1 : 0;
        if (n < vector.size()) {
            return vector.elementAt(n);
        }
        return null;
    }
    
    public Vector getImages(final String s, final String s2) {
        return null;
    }
    
    public void check(final String s) {
        final URL documentBase = this.getDocumentBase();
        String substring = this.links.get(s.substring(0, s.indexOf(",")));
        String s2 = this.m_Target;
        final AppletContext appletContext = this.getAppletContext();
        if (substring != null) {
            final int index;
            if ((index = substring.indexOf("|")) != -1) {
                s2 = substring.substring(index + 1);
                substring = substring.substring(0, index);
                this.setCurFolder(substring);
            }
            URL url;
            try {
                url = new URL(documentBase, substring);
            }
            catch (Exception ex) {
                System.out.println("WARNING: " + ex);
                return;
            }
            if (s2.length() > 0) {
                appletContext.showDocument(url, s2);
                return;
            }
            appletContext.showDocument(url);
        }
    }
    
    public void init() {
        System.out.println("Tree Applet v" + "2.0.046");
        System.out.println("Copyright © 1997-1998 by Dima Ivanov");
        System.out.println("For non-commercial use.");
        System.out.println("Home page: " + "http://www.career.ru/dima/studio/index.html");
        System.out.println("E-Mail: " + "dimitry@career.ru");
        this.setLayout(new BorderLayout());
        final String parameter = this.getParameter("Title");
        if (parameter != null) {
            this.m_Title = parameter;
        }
        final String parameter2 = this.getParameter("Mode");
        if (parameter2 != null) {
            this.m_Mode = parameter2;
        }
        final String parameter3 = this.getParameter("Tree");
        if (parameter3 == null) {
            throw new Error("Required applet parameter '" + "Tree" + "' not found");
        }
        this.m_Tree = parameter3;
        final String parameter4 = this.getParameter("Links");
        if (parameter4 != null) {
            this.m_Links = parameter4;
            final String parameter5 = this.getParameter("Images");
            if (parameter5 != null) {
                this.m_Images = parameter5;
            }
            final String parameter6 = this.getParameter("Target");
            if (parameter6 != null) {
                this.m_Target = parameter6;
            }
            final String parameter7 = this.getParameter("FgColor");
            if (parameter7 != null) {
                this.fgColor = this.toColor(parameter7);
            }
            final String parameter8 = this.getParameter("BgColor");
            if (parameter8 != null) {
                this.bgColor = this.toColor(parameter8);
            }
            final String parameter9 = this.getParameter("FgSelectedColor");
            if (parameter9 != null) {
                this.fgSelectedColor = this.toColor(parameter9);
            }
            final String parameter10 = this.getParameter("BgSelectedColor");
            if (parameter10 != null) {
                this.bgSelectedColor = this.toColor(parameter10);
            }
            final String parameter11 = this.getParameter("FgHighlight");
            if (parameter11 != null) {
                this.fgHighlight = this.toColor(parameter11);
            }
            final String parameter12 = this.getParameter("FrameWidth");
            if (parameter12 != null) {
                this.frameWidth = Integer.parseInt(parameter12);
            }
            final String parameter13 = this.getParameter("FrameHeight");
            if (parameter13 != null) {
                this.frameHeight = Integer.parseInt(parameter13);
            }
            final String parameter14 = this.getParameter("EnableCollapseRootNode");
            if (parameter14 != null) {
                this.enableCollapseRootNode = "yes".equals(parameter14);
            }
            final String parameter15 = this.getParameter("EnableAnimatedMode");
            if (parameter15 != null) {
                this.enableAnimatedMode = "yes".equals(parameter15);
            }
            final String parameter16 = this.getParameter("FocusedNode");
            if (parameter16 != null) {
                this.m_FocusedNode = parameter16;
            }
            this.dowloadImages();
            this.parseTree();
            this.parseLinks();
            this.treePanel.setBackground(this.bgColor);
            this.treePanel.setForeground(this.fgColor);
            this.treePanel.setFgHilite(this.fgHighlight);
            this.treePanel.setBgSelect(this.bgSelectedColor);
            this.treePanel.setFgSelect(this.fgSelectedColor);
            if (this.m_FocusedNode != null) {
                this.treePanel.focusNode((TreeNode)this.treeNodes.get(this.m_FocusedNode));
            }
            this.runTree();
            return;
        }
        throw new Error("Required applet parameter '" + "Links" + "' not found");
    }
    
    protected Color toColor(String string) {
        if (string.length() > 6) {
            throw new Error("Invalid color: " + string);
        }
        while (string.length() < 6) {
            string = "0" + string;
        }
        return new Color(Integer.parseInt(string.substring(0, 2), 16), Integer.parseInt(string.substring(2, 4), 16), Integer.parseInt(string.substring(4, 6), 16));
    }
}
