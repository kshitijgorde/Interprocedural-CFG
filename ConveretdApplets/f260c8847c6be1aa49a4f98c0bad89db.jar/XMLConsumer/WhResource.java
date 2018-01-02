// 
// Decompiled by Procyon v0.5.30
// 

package XMLConsumer;

import hhapplet.GloViewSkin;
import hhapplet.FtsViewSkin;
import hhapplet.TocViewSkin;
import hhapplet.BsscFont;
import java.awt.Image;
import java.awt.Color;
import java.net.URL;
import hhapplet.ViewSkin;
import hhapplet.IndexViewSkin;
import BsscXML.IBsscXMLElementReader;
import hhapplet.PaneSetting;
import java.util.Vector;
import java.util.Hashtable;

public class WhResource extends Consumer
{
    private Hashtable m_hashResource;
    private Hashtable m_hashPane;
    private Vector m_vStopWords;
    private Vector m_vStems;
    private int m_nFirstPane;
    
    private void processIdxSkin(final PaneSetting paneSetting, final IBsscXMLElementReader bsscXMLElementReader) {
        final IndexViewSkin viewSkin = new IndexViewSkin();
        viewSkin.loadFromDom(bsscXMLElementReader);
        paneSetting.setViewSkin(viewSkin);
    }
    
    public String getValue(final String s) {
        return this.m_hashResource.get(s);
    }
    
    public int getFirstShowPane() {
        return this.m_nFirstPane;
    }
    
    public WhResource(final URL url) {
        super(url);
        this.m_hashResource = null;
        this.m_hashPane = null;
        this.m_vStopWords = null;
        this.m_vStems = null;
        this.m_nFirstPane = 0;
        this.m_hashResource = new Hashtable();
        this.m_hashPane = new Hashtable();
    }
    
    public void consume(final IBsscXMLElementReader bsscXMLElementReader) {
        if (bsscXMLElementReader.getName().equals("resource")) {
            int n = 0;
            while (true) {
                final IBsscXMLElementReader child = bsscXMLElementReader.getChild(n++);
                if (child == null) {
                    break;
                }
                if (child.getName().equals("item")) {
                    final String attribute = child.getAttribute("name");
                    final String attribute2 = child.getAttribute("value");
                    if (attribute == null || attribute.length() == 0 || attribute2 == null) {
                        continue;
                    }
                    this.m_hashResource.put(attribute, attribute2);
                }
                else if (child.getName().equals("panes")) {
                    int n2 = 0;
                    int n3 = 0;
                    final String attribute3 = child.getAttribute("show");
                    if (attribute3 != null) {
                        try {
                            this.m_nFirstPane = Integer.parseInt(attribute3);
                        }
                        catch (NumberFormatException ex) {}
                    }
                    while (true) {
                        final IBsscXMLElementReader child2 = child.getChild(n2++);
                        if (child2 == null) {
                            break;
                        }
                        if (child2.getName().equals("toc")) {
                            this.addPane("toc", new PaneSetting(n3++));
                        }
                        else if (child2.getName().equals("index")) {
                            this.addPane("index", new PaneSetting(n3++));
                        }
                        else if (child2.getName().equals("fts")) {
                            this.addPane("fts", new PaneSetting(n3++));
                        }
                        else {
                            if (!child2.getName().equals("glossary")) {
                                continue;
                            }
                            this.addPane("glossary", new PaneSetting(n3++));
                        }
                    }
                }
                else if (child.getName().equals("stopwords")) {
                    int n4 = 0;
                    while (true) {
                        if (this.m_vStopWords == null) {
                            this.m_vStopWords = new Vector();
                        }
                        final IBsscXMLElementReader child3 = child.getChild(n4++);
                        if (child3 == null) {
                            break;
                        }
                        if (!child3.getName().equals("stopword")) {
                            continue;
                        }
                        final String attribute4 = child3.getAttribute("name");
                        if (attribute4 == null) {
                            continue;
                        }
                        this.m_vStopWords.addElement(attribute4);
                    }
                }
                else if (child.getName().equals("stems")) {
                    int n5 = 0;
                    while (true) {
                        if (this.m_vStems == null) {
                            this.m_vStems = new Vector();
                        }
                        final IBsscXMLElementReader child4 = child.getChild(n5++);
                        if (child4 == null) {
                            break;
                        }
                        if (!child4.getName().equals("stem")) {
                            continue;
                        }
                        final String attribute5 = child4.getAttribute("name");
                        if (attribute5 == null) {
                            continue;
                        }
                        this.m_vStems.addElement(attribute5);
                    }
                }
                else {
                    if (!child.getName().equals("paneskin")) {
                        continue;
                    }
                    int n6 = 0;
                    while (true) {
                        final IBsscXMLElementReader child5 = child.getChild(n6++);
                        if (child5 == null) {
                            break;
                        }
                        if (child5.getName().equals("background")) {
                            final Color color = ViewSkin.getColor(child5.getAttribute("color"));
                            if (color != null) {
                                ViewSkin.m_PaneColorBg = color;
                            }
                            final Image image = ViewSkin.getImage(child5.getAttribute("img"));
                            if (image == null) {
                                continue;
                            }
                            ViewSkin.m_PaneImageBg = image;
                        }
                        else if (child5.getName().equals("font")) {
                            final BsscFont font = ViewSkin.getFont(child5);
                            if (font == null) {
                                continue;
                            }
                            ViewSkin.m_PaneFont = font;
                        }
                        else if (child5.getName().equals("toc")) {
                            final PaneSetting paneSetting = this.getPaneSetting("toc");
                            if (paneSetting == null) {
                                continue;
                            }
                            this.processTocSkin(paneSetting, child5);
                        }
                        else if (child5.getName().equals("index")) {
                            final PaneSetting paneSetting2 = this.getPaneSetting("index");
                            if (paneSetting2 == null) {
                                continue;
                            }
                            this.processIdxSkin(paneSetting2, child5);
                        }
                        else if (child5.getName().equals("fts")) {
                            final PaneSetting paneSetting3 = this.getPaneSetting("fts");
                            if (paneSetting3 == null) {
                                continue;
                            }
                            this.processFtsSkin(paneSetting3, child5);
                        }
                        else {
                            if (!child5.getName().equals("glossary")) {
                                continue;
                            }
                            final PaneSetting paneSetting4 = this.getPaneSetting("glossary");
                            if (paneSetting4 == null) {
                                continue;
                            }
                            this.processGloSkin(paneSetting4, child5);
                        }
                    }
                }
            }
        }
    }
    
    public Vector getStopWords() {
        return this.m_vStopWords;
    }
    
    private void processTocSkin(final PaneSetting paneSetting, final IBsscXMLElementReader bsscXMLElementReader) {
        final TocViewSkin viewSkin = new TocViewSkin();
        viewSkin.loadFromDom(bsscXMLElementReader);
        paneSetting.setViewSkin(viewSkin);
    }
    
    private void processFtsSkin(final PaneSetting paneSetting, final IBsscXMLElementReader bsscXMLElementReader) {
        final FtsViewSkin viewSkin = new FtsViewSkin();
        viewSkin.loadFromDom(bsscXMLElementReader);
        paneSetting.setViewSkin(viewSkin);
    }
    
    private void processGloSkin(final PaneSetting paneSetting, final IBsscXMLElementReader bsscXMLElementReader) {
        final GloViewSkin viewSkin = new GloViewSkin();
        viewSkin.loadFromDom(bsscXMLElementReader);
        paneSetting.setViewSkin(viewSkin);
    }
    
    private void addPane(final String s, final PaneSetting paneSetting) {
        this.m_hashPane.put(s, paneSetting);
    }
    
    public PaneSetting getPaneSetting(final String s) {
        return this.m_hashPane.get(s);
    }
    
    public Vector getStems() {
        return this.m_vStems;
    }
}
