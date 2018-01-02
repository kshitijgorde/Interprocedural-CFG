// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import XMLConsumer.Idx;
import java.util.Enumeration;
import XMLConsumer.Project;
import java.awt.event.TextEvent;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Component;
import java.awt.BorderLayout;
import XMLConsumer.IdxEntry;
import java.awt.Color;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.TextListener;

public class IndexView extends BsscImagePanel implements Runnable, TextListener, ActionListener
{
    private TextField m_tfEdit;
    private Button m_btnDisplay;
    private Vector m_vProjects;
    private Vector m_vIdx;
    private IndexListView m_list;
    int m_bResearch;
    private Object m_Cursor;
    private LayoutManager m_layout;
    
    public IndexView(final Vector vProjects, final IndexViewSkin indexViewSkin) {
        this.m_bResearch = 1;
        this.m_Cursor = null;
        this.m_vProjects = vProjects;
        this.m_tfEdit = new TextField();
        this.m_btnDisplay = new Button(ResourceLib.GetRes("Display"));
        final BsscImagePanel bsscImagePanel = new BsscImagePanel();
        final BsscImageLabel bsscImageLabel = new BsscImageLabel(ResourceLib.GetRes("IndexInputPrompt"));
        this.m_vIdx = new Vector();
        this.loadIdxInfo();
        this.m_list = new IndexListView(this.m_vIdx);
        final Color bgColor = indexViewSkin.getBgColor();
        if (bgColor != null) {
            this.m_list.setBackground(bgColor);
        }
        else {
            this.m_list.setBackground(Color.white);
        }
        final Image bgImage = indexViewSkin.getBgImage();
        if (bgImage != null) {
            this.m_list.setBgImage(bgImage);
        }
        final BsscFont normalFont = indexViewSkin.getNormalFont();
        if (normalFont != null) {
            final FontMetrics fontMetrics = this.getFontMetrics(normalFont.getFont());
            fontMetrics.getHeight();
            this.m_list.setUnitHeight(fontMetrics.getHeight());
            final Font font = normalFont.getFont();
            if (font != null) {
                IdxEntry.setNormalFont(font);
            }
            final Color color = normalFont.getColor();
            if (color != null) {
                IdxEntry.setNormalColor(color);
            }
            IdxEntry.setNormalUnderline(normalFont.isUnderline());
        }
        final BsscFont hoverFont = indexViewSkin.getHoverFont();
        if (hoverFont != null) {
            final Font font2 = hoverFont.getFont();
            if (font2 != null) {
                IdxEntry.setHoverFont(font2);
            }
            final Color color2 = hoverFont.getColor();
            if (color2 != null) {
                IdxEntry.setHoverColor(color2);
            }
            IdxEntry.setHoverUnderline(hoverFont.isUnderline());
        }
        IdxEntry.setActiveColor(indexViewSkin.getActiveColor());
        bsscImagePanel.setLayout(new BorderLayout(0, 0));
        bsscImagePanel.add("North", bsscImageLabel);
        bsscImagePanel.add("Center", this.m_tfEdit);
        this.m_tfEdit.setBackground(Color.white);
        this.m_tfEdit.addTextListener(this);
        this.m_tfEdit.addActionListener(this);
        this.setLayout(new BorderLayout(2, 2));
        final int getFontSize = BsscFontFixPatch.GetFontSize();
        this.m_list.setFont(new Font(BsscFontFixPatch.GetFontName(), 0, getFontSize));
        this.m_tfEdit.setFont(new Font(BsscFontFixPatch.GetFontName(), 0, getFontSize));
        this.m_btnDisplay.setFont(new Font(BsscFontFixPatch.GetFontName(), 0, getFontSize));
        this.add("North", bsscImagePanel);
        this.add("Center", this.m_list);
        this.add("South", this.m_btnDisplay);
        this.m_btnDisplay.addActionListener(this);
        this.m_tfEdit.requestFocus();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.m_tfEdit || actionEvent.getSource() == this.m_btnDisplay) {
            this.m_list.active();
        }
    }
    
    public Dimension getPreferredSize() {
        return this.getParent().getSize();
    }
    
    public void run() {
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        if (textEvent.getSource() == this.m_tfEdit) {
            this.m_list.findAndSelect(this.m_tfEdit.getText());
        }
    }
    
    public void loadIdxInfo() {
        try {
            if (this.m_vProjects != null && this.m_vProjects.size() > 0) {
                String langId = null;
                final Enumeration<Project> elements = this.m_vProjects.elements();
                while (elements.hasMoreElements()) {
                    final Project nextElement = elements.nextElement();
                    if (nextElement instanceof Project) {
                        if (langId == null) {
                            langId = nextElement.getLangId();
                        }
                        else if (!langId.equals(nextElement.getLangId())) {
                            continue;
                        }
                        final Idx idx = nextElement.getIdx();
                        if (idx == null) {
                            continue;
                        }
                        idx.process();
                        this.m_vIdx.addElement(idx);
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
