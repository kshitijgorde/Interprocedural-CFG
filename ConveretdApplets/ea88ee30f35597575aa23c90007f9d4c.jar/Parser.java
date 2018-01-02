import java.io.Serializable;
import java.util.Enumeration;
import java.applet.Applet;
import java.awt.Component;
import java.io.IOException;
import java.util.Stack;
import java.io.StreamTokenizer;
import java.awt.Image;
import java.net.MalformedURLException;
import java.awt.Font;
import java.util.Hashtable;
import java.awt.MediaTracker;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class Parser implements Runnable
{
    private static final String XT_treeJIT = "treecontrol";
    private static final String XT_folder = "folder";
    private static final String XT_jitfolder = "jitfolder";
    private static final String XT_item = "item";
    private static final String XT_baseitem = "idefault";
    private static final String XT_font = "font";
    private static final String XT_rootfolder = "rootfolder";
    private static final String XT_TreeJITClose = "/treecontrol";
    private static final String XT_folderClose = "/folder";
    private static final String XT_fontClose = "/font";
    private static final String XT_baseitemClose = "/idefault";
    private static final String XI_title = "title";
    private static final String XI_image = "image";
    private static final String XI_highImage = "imagetwo";
    private static final String XI_link = "link";
    private static final String XI_rlink = "rlink";
    private static final String XI_frame = "target";
    private static final String XI_rframe = "rtarget";
    private static final String XI_info = "info";
    private static final String XI_width = "width";
    private static final String XI_height = "height";
    private static final String XF_jit = "jit";
    private static final String XF_expand = "expand";
    private static final String XFont_face = "face";
    private static final String XFont_size = "size";
    private static final String XFont_style = "style";
    private static final String XJ_linkbaseURL = "linkurl";
    private static final String XJ_imagebaseURL = "imageurl";
    private static final char XML_opentag = '<';
    private static final char XML_closetag = '>';
    private static final char XML_equal = '=';
    private URL m_baseImageURL;
    private URL m_baseLinkURL;
    private MediaTracker m_tracker;
    private Hashtable m_imageList;
    private TreeApp m_applet;
    public static final boolean m_as = false;
    URL m_jitFile;
    FolderItem m_parent;
    Thread m_thread;
    
    private void processJitFolderTag(final FolderItem folderItem, final Hashtable hashtable) {
        if (hashtable.containsKey("jit")) {
            try {
                folderItem.m_treejit = new Parser(new URL(this.m_baseLinkURL, hashtable.get("jit")), folderItem, ((Applet)this.m_applet).getDocumentBase(), ((Applet)this.m_applet).getDocumentBase(), this.m_applet);
            }
            catch (Exception ex) {
                folderItem.setStatus((String)hashtable.get("Jit error: " + ex.getMessage()));
            }
        }
        this.processItemTag(folderItem, hashtable);
    }
    
    private Font processFontTag(final Font font, final Hashtable hashtable) {
        String name = null;
        int n = font.getSize();
        int style = font.getStyle();
        if (hashtable.containsKey("face")) {
            name = hashtable.get("face");
        }
        if (hashtable.containsKey("size") && hashtable.get("size") instanceof Double) {
            n = (int)hashtable.get("size");
        }
        if (hashtable.containsKey("style")) {
            final String lowerCase = hashtable.get("style").toLowerCase();
            if (lowerCase.compareTo("plain") == 0) {
                style = 0;
            }
            if (lowerCase.compareTo("italic") == 0) {
                style = 2;
            }
            if (lowerCase.compareTo("bold") == 0) {
                style = 1;
            }
            if (lowerCase.compareTo("bolditalic") == 0) {
                style = 3;
            }
        }
        if (name == null) {
            name = font.getName();
        }
        return new Font(name, style, n);
    }
    
    private void processItemTag(final Item item, final Hashtable hashtable) {
        if (hashtable.containsKey("title")) {
            item.m_title = hashtable.get("title");
        }
        if (hashtable.containsKey("image")) {
            item.m_icon = this.addImage(hashtable.get("image"));
        }
        if (hashtable.containsKey("imagetwo")) {
            item.m_highlightIcon = this.addImage(hashtable.get("imagetwo"));
        }
        if (hashtable.containsKey("target")) {
            item.m_docTarget = hashtable.get("target");
        }
        if (hashtable.containsKey("rtarget")) {
            item.m_rightdocTarget = hashtable.get("rtarget");
        }
        if (hashtable.containsKey("width") && hashtable.get("width") instanceof Double) {
            final int intValue = (int)hashtable.get("width");
            if (item instanceof FolderItem) {
                ((FolderItem)item).getTitleRect().width = intValue;
            }
            else {
                item.getRect().width = intValue;
            }
        }
        if (hashtable.containsKey("height") && hashtable.get("height") instanceof Double) {
            final int intValue2 = (int)hashtable.get("height");
            if (item instanceof FolderItem) {
                ((FolderItem)item).getTitleRect().height = intValue2;
            }
            else {
                item.getRect().height = intValue2;
            }
        }
        try {
            if (hashtable.containsKey("link")) {
                item.m_doc = new URL(this.m_baseLinkURL, hashtable.get("link"));
            }
            if (hashtable.containsKey("rlink")) {
                item.m_rightdoc = new URL(this.m_baseLinkURL, hashtable.get("rlink"));
            }
            if (hashtable.containsKey("info")) {
                item.setStatus((String)hashtable.get("info"));
            }
        }
        catch (MalformedURLException ex) {
            item.setStatus((String)hashtable.get("URL error: " + ex.getMessage()));
        }
        if (item.m_status == null) {
            item.setStatus("");
        }
    }
    
    private final Image addImage(final String s) {
        if (s.length() == 0) {
            return null;
        }
        if (this.m_imageList.containsKey(s)) {
            return this.m_imageList.get(s);
        }
        try {
            final Image image = ((Applet)this.m_applet).getImage(new URL(this.m_baseImageURL, s));
            this.m_imageList.put(s, image);
            this.m_tracker.addImage(image, 0);
            this.m_tracker.checkAll(true);
            return image;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private void processTreeJITTag(final Hashtable hashtable) {
        final URL baseLinkURL = this.m_baseLinkURL;
        final URL baseImageURL = this.m_baseImageURL;
        try {
            if (hashtable.containsKey("imageurl")) {
                this.m_baseImageURL = new URL(this.m_baseImageURL, hashtable.get("imageurl"));
            }
            if (hashtable.containsKey("linkurl")) {
                this.m_baseLinkURL = new URL(this.m_baseLinkURL, hashtable.get("linkurl"));
            }
        }
        catch (Exception ex) {
            this.m_baseLinkURL = baseLinkURL;
            this.m_baseImageURL = baseImageURL;
        }
    }
    
    private void processFolderTag(final FolderItem folderItem, final Hashtable hashtable) {
        folderItem.m_expanded = hashtable.containsKey("expand");
        this.processItemTag(folderItem, hashtable);
    }
    
    void ProcessTags(final StreamTokenizer streamTokenizer, FolderItem folderItem) {
        streamTokenizer.ordinaryChar(60);
        streamTokenizer.ordinaryChar(62);
        streamTokenizer.ordinaryChar(61);
        streamTokenizer.quoteChar(34);
        streamTokenizer.wordChars(47, 47);
        streamTokenizer.wordChars(92, 92);
        final Hashtable hashtable = new Hashtable();
        final Stack stack = new Stack<Object>();
        Font font = folderItem.m_font;
        Item item = new Item("default");
        final Stack stack2 = new Stack<Item>();
        if (font == null) {
            font = new Font("Dialog", 0, 10);
        }
        final Stack<Font> stack3 = new Stack<Font>();
        int n = 0;
        try {
            while (this.readTag(streamTokenizer, hashtable)) {
                if (hashtable.containsKey("treecontrol")) {
                    this.processTreeJITTag(hashtable);
                    n = 1;
                }
                else {
                    if (n == 0) {
                        continue;
                    }
                    if (hashtable.containsKey("item")) {
                        final Item item2 = new Item(item);
                        item2.m_font = font;
                        if (item2 == null) {
                            continue;
                        }
                        this.processItemTag(item2, hashtable);
                        folderItem.addElement(item2);
                    }
                    else if (hashtable.containsKey("folder")) {
                        final FolderItem folderItem2 = new FolderItem(item);
                        folderItem2.m_font = font;
                        if (folderItem2 == null) {
                            continue;
                        }
                        this.processFolderTag(folderItem2, hashtable);
                        folderItem.addElement(folderItem2);
                        stack.push(folderItem);
                        folderItem = folderItem2;
                    }
                    else if (hashtable.containsKey("jitfolder")) {
                        final FolderItem folderItem3 = new FolderItem(item);
                        folderItem3.m_font = font;
                        if (folderItem3 == null) {
                            continue;
                        }
                        this.processJitFolderTag(folderItem3, hashtable);
                        folderItem.addElement(folderItem3);
                    }
                    else if (hashtable.containsKey("font")) {
                        final Font processFontTag = this.processFontTag(font, hashtable);
                        if (processFontTag == null) {
                            continue;
                        }
                        stack3.push(font);
                        font = processFontTag;
                    }
                    else if (hashtable.containsKey("rootfolder")) {
                        FolderItem folderItem4;
                        if (!stack.isEmpty()) {
                            folderItem4 = stack.firstElement();
                        }
                        else {
                            folderItem4 = folderItem;
                        }
                        folderItem4.m_font = font;
                        this.processItemTag(folderItem4, hashtable);
                    }
                    else if (hashtable.containsKey("idefault")) {
                        final Item item3 = new Item(item);
                        stack2.push(item);
                        item = item3;
                        this.processItemTag(item, hashtable);
                    }
                    else if (hashtable.containsKey("/treecontrol")) {
                        n = 0;
                    }
                    else if (hashtable.containsKey("/folder")) {
                        if (stack.empty()) {
                            continue;
                        }
                        folderItem = stack.pop();
                    }
                    else if (hashtable.containsKey("/font")) {
                        if (stack3.empty()) {
                            continue;
                        }
                        font = stack3.pop();
                    }
                    else {
                        if (!hashtable.containsKey("/idefault") || stack2.empty()) {
                            continue;
                        }
                        item = stack2.pop();
                    }
                }
            }
        }
        catch (IOException ex) {}
    }
    
    public void start() {
        (this.m_thread = new Thread(this)).start();
    }
    
    Parser(final URL jitFile, final FolderItem parent, final URL baseImageURL, final URL baseLinkURL, final TreeApp applet) {
        this.m_imageList = applet.m_imageList;
        this.m_parent = parent;
        this.m_jitFile = jitFile;
        this.m_baseImageURL = baseImageURL;
        this.m_baseLinkURL = baseLinkURL;
        this.m_applet = applet;
    }
    
    public void run() {
        final FolderItem folderItem = new FolderItem(this.m_parent);
        folderItem.getTitleRect().width = 0;
        folderItem.getTitleRect().height = 0;
        this.m_tracker = new MediaTracker((Component)this.m_applet);
        try {
            final Enumeration<Image> elements = this.m_imageList.elements();
            while (elements.hasMoreElements()) {
                this.m_tracker.addImage(elements.nextElement(), 0);
            }
        }
        catch (Exception ex3) {}
        final Item item = new Item("Loading items..");
        item.m_font = this.m_parent.m_font;
        this.m_parent.addElement(item);
        this.m_applet.initalise();
        try {
            this.ProcessTags(new StreamTokenizer(this.m_jitFile.openStream()), folderItem);
        }
        catch (Exception ex) {
            item.setTitle("Error loading file");
            item.setStatus("Error: " + ex);
            this.m_applet.initalise();
            return;
        }
        item.setTitle("Loading images...");
        this.m_applet.initalise();
        try {
            this.m_tracker.waitForAll();
        }
        catch (Exception ex2) {
            item.setTitle("Error loading images...");
            item.setStatus("Error: " + ex2);
            this.m_applet.initalise();
        }
        folderItem.initalise((Applet)this.m_applet);
        if (!this.m_applet.replaceItem(this.m_parent, folderItem)) {
            this.m_parent.copy(folderItem);
        }
    }
    
    boolean readTag(final StreamTokenizer streamTokenizer, final Hashtable hashtable) throws IOException {
        hashtable.clear();
        int nextToken;
        while ((nextToken = streamTokenizer.nextToken()) != 60) {
            if (nextToken == -1) {
                return false;
            }
        }
        Object o = null;
        int n = 0;
        int nextToken2;
        while ((nextToken2 = streamTokenizer.nextToken()) != 62) {
            if (nextToken2 == -1) {
                return false;
            }
            if (nextToken2 == 60) {
                int nextToken3;
                while ((nextToken3 = streamTokenizer.nextToken()) != 62 && nextToken3 != -1) {}
            }
            if (nextToken2 == 61) {
                n = 1;
            }
            else {
                Serializable lowerCase = null;
                if (nextToken2 == -3 || nextToken2 == 34) {
                    lowerCase = new String(streamTokenizer.sval);
                }
                if (nextToken2 == -2) {
                    lowerCase = new Double(streamTokenizer.nval);
                }
                if (lowerCase == null) {
                    continue;
                }
                if (n == 1 && o != null) {
                    hashtable.put(o, lowerCase);
                    n = 0;
                    o = null;
                }
                else {
                    if (o != null) {
                        hashtable.put(o, "");
                    }
                    if (lowerCase instanceof String) {
                        lowerCase = ((String)lowerCase).toLowerCase();
                    }
                    o = lowerCase;
                }
            }
        }
        if (o != null) {
            hashtable.put(o, "");
        }
        return true;
    }
}
