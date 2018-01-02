// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.awt.Dimension;
import java.util.Enumeration;
import java.net.URL;
import java.net.MalformedURLException;
import XMLConsumer.Toc;
import XMLConsumer.Project;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.image.ImageObserver;
import XMLConsumer.TocEntry;
import java.awt.Color;
import java.util.Vector;

public class TocView extends BsscImagePanel
{
    private Vector m_vProjects;
    private Vector m_vToc;
    private TocListView m_list;
    private Object m_Cursor;
    
    public void sync(final String s, final String s2) {
        if (this.m_list != null) {
            this.m_list.sync(s, s2);
        }
    }
    
    public TocView(final Vector vProjects, final TocViewSkin tocViewSkin) {
        this.m_Cursor = null;
        this.m_vProjects = vProjects;
        this.m_vToc = new Vector();
        this.loadTocInfo();
        this.m_list = new TocListView(this.m_vProjects, this.m_vToc);
        final Color bgColor = tocViewSkin.getBgColor();
        if (bgColor != null) {
            this.m_list.setBackground(bgColor);
        }
        else {
            this.m_list.setBackground(Color.white);
        }
        final Image bgImage = tocViewSkin.getBgImage();
        if (bgImage != null) {
            this.m_list.setBgImage(bgImage);
        }
        final BsscFont normalFont = tocViewSkin.getNormalFont();
        if (normalFont != null) {
            final FontMetrics fontMetrics = this.getFontMetrics(normalFont.getFont());
            if (fontMetrics.getHeight() > 16) {
                this.m_list.setUnitHeight(fontMetrics.getHeight());
            }
            final Font font = normalFont.getFont();
            if (font != null) {
                TocEntry.setNormalFont(font);
            }
            final Color color = normalFont.getColor();
            if (color != null) {
                TocEntry.setNormalColor(color);
            }
            TocEntry.setNormalUnderline(normalFont.isUnderline());
        }
        final BsscFont hoverFont = tocViewSkin.getHoverFont();
        if (hoverFont != null) {
            final Font font2 = hoverFont.getFont();
            if (font2 != null) {
                TocEntry.setHoverFont(font2);
            }
            final Color color2 = hoverFont.getColor();
            if (color2 != null) {
                TocEntry.setHoverColor(color2);
            }
            TocEntry.setHoverUnderline(hoverFont.isUnderline());
        }
        tocViewSkin.getIconImage(1);
        TocEntry.SetObserver(this.m_list);
        TocEntry.setIconImage(1, tocViewSkin.getIconImage(1));
        TocEntry.setIconImage(2, tocViewSkin.getIconImage(2));
        TocEntry.setIconImage(3, tocViewSkin.getIconImage(3));
        TocEntry.setIconImage(4, tocViewSkin.getIconImage(4));
        TocEntry.setIconImage(5, tocViewSkin.getIconImage(5));
        TocEntry.setIconImage(6, tocViewSkin.getIconImage(6));
        TocEntry.setIconImage(7, tocViewSkin.getIconImage(7));
        TocEntry.setIconImage(8, tocViewSkin.getIconImage(8));
        TocEntry.setActiveColor(tocViewSkin.getActiveColor());
        this.setLayout(new BorderLayout());
        this.add("Center", this.m_list);
    }
    
    private void updateTocRootPath(final Project project, final Toc toc) {
        final Vector rootTocPaths = toc.getRootTocPaths();
        final URL url = project.getURL();
        final Enumeration remoteProjPaths = toc.getRemoteProjPaths();
        while (remoteProjPaths.hasMoreElements()) {
            final String s = remoteProjPaths.nextElement();
            URL url2 = null;
            try {
                url2 = URLFileHandler.makeURL(url, s, null);
                url2 = URLFileHandler.makeURL(url2, Project.getFileName(), null);
            }
            catch (MalformedURLException ex) {}
            if (url2 != null) {
                final Enumeration<Project> elements = this.m_vProjects.elements();
                while (elements.hasMoreElements()) {
                    final Project project2 = elements.nextElement();
                    if (project2.getURL().equals(url2)) {
                        final Toc toc2 = project2.getToc();
                        if (toc2 == null || toc2.getRootTocPaths() != null) {
                            break;
                        }
                        final Vector projTocPaths = toc.getProjTocPaths(s);
                        if (projTocPaths == null || projTocPaths.size() <= 0) {
                            break;
                        }
                        if (rootTocPaths != null && rootTocPaths.size() > 0) {
                            final Vector<String> rootTocPaths2 = new Vector<String>();
                            final Enumeration<String> elements2 = rootTocPaths.elements();
                            while (elements2.hasMoreElements()) {
                                final String s2 = elements2.nextElement();
                                final Enumeration<String> elements3 = projTocPaths.elements();
                                while (elements3.hasMoreElements()) {
                                    rootTocPaths2.addElement(s2 + elements3.nextElement());
                                }
                            }
                            toc2.setRootTocPaths(rootTocPaths2);
                            break;
                        }
                        toc2.setRootTocPaths(projTocPaths);
                        break;
                    }
                }
            }
        }
    }
    
    public void loadTocInfo() {
        try {
            if (this.m_vProjects != null) {
                final Toc toc = this.m_vProjects.elementAt(0).getToc();
                final Vector<String> rootTocPaths = new Vector<String>();
                rootTocPaths.addElement("/");
                toc.setRootTocPaths(rootTocPaths);
                final Enumeration<Project> elements = (Enumeration<Project>)this.m_vProjects.elements();
                while (elements.hasMoreElements()) {
                    final Project nextElement = elements.nextElement();
                    if (nextElement instanceof Project) {
                        final Toc toc2 = nextElement.getToc();
                        if (toc2 == null) {
                            continue;
                        }
                        toc2.process();
                        this.updateTocRootPath(nextElement, toc2);
                        this.m_vToc.addElement(toc2);
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Dimension getPreferredSize() {
        return this.getParent().getSize();
    }
}
