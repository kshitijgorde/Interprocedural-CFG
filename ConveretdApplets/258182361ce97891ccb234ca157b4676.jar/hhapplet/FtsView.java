// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.awt.Dimension;
import XMLConsumer.Fts;
import java.util.Enumeration;
import XMLConsumer.Project;
import java.awt.event.ActionEvent;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import XMLConsumer.FtsEntry;
import java.awt.Color;
import java.awt.BorderLayout;
import java.util.Vector;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionListener;

public class FtsView extends BsscImagePanel implements ActionListener
{
    private TextField m_tfEdit;
    private Button m_btnDisplay;
    protected Button m_btnFind;
    private int m_nSelectedIndex;
    private Vector m_vProjects;
    private Vector m_vFts;
    private FtsListView m_list;
    private Object m_Cursor;
    
    public FtsView(final Vector vProjects, final FtsViewSkin ftsViewSkin) {
        this.m_Cursor = null;
        this.m_vProjects = vProjects;
        this.m_tfEdit = new TextField();
        this.m_btnDisplay = new Button(ResourceLib.GetRes("Display"));
        this.m_nSelectedIndex = -1;
        final BsscImagePanel bsscImagePanel = new BsscImagePanel();
        final BsscImageLabel bsscImageLabel = new BsscImageLabel(ResourceLib.GetRes("FtsInputPrompt"));
        this.m_btnFind = new Button(ResourceLib.GetRes("Find"));
        final BorderLayout layout = new BorderLayout(0, 0);
        this.m_vFts = new Vector();
        this.loadFtsInfo();
        this.m_list = new FtsListView(this.m_vFts);
        final Color bgColor = ftsViewSkin.getBgColor();
        if (bgColor != null) {
            this.m_list.setBackground(bgColor);
        }
        else {
            this.m_list.setBackground(Color.white);
        }
        final Image bgImage = ftsViewSkin.getBgImage();
        if (bgImage != null) {
            this.m_list.setBgImage(bgImage);
        }
        final BsscFont normalFont = ftsViewSkin.getNormalFont();
        if (normalFont != null) {
            final FontMetrics fontMetrics = this.getFontMetrics(normalFont.getFont());
            fontMetrics.getHeight();
            this.m_list.setUnitHeight(fontMetrics.getHeight());
            final Font font = normalFont.getFont();
            if (font != null) {
                FtsEntry.setNormalFont(font);
            }
            final Color color = normalFont.getColor();
            if (color != null) {
                FtsEntry.setNormalColor(color);
            }
            FtsEntry.setNormalUnderline(normalFont.isUnderline());
        }
        FtsEntry.setActiveColor(ftsViewSkin.getActiveColor());
        final BsscFont hoverFont = ftsViewSkin.getHoverFont();
        if (hoverFont != null) {
            final Font font2 = hoverFont.getFont();
            if (font2 != null) {
                FtsEntry.setHoverFont(font2);
            }
            final Color color2 = hoverFont.getColor();
            if (color2 != null) {
                FtsEntry.setHoverColor(color2);
            }
            FtsEntry.setHoverUnderline(hoverFont.isUnderline());
        }
        final BsscFont errorFont = ftsViewSkin.getErrorFont();
        if (errorFont != null) {
            final Font font3 = errorFont.getFont();
            if (font3 != null) {
                ErrEntry.setErrorFont(font3);
            }
            final Color color3 = errorFont.getColor();
            if (color3 != null) {
                ErrEntry.setErrorColor(color3);
            }
            ErrEntry.setErrorUnderline(errorFont.isUnderline());
        }
        bsscImagePanel.setLayout(layout);
        bsscImagePanel.add("North", bsscImageLabel);
        bsscImagePanel.add("Center", this.m_tfEdit);
        this.m_tfEdit.addActionListener(this);
        this.m_tfEdit.setBackground(Color.white);
        this.setLayout(new BorderLayout(2, 2));
        final int getFontSize = BsscFontFixPatch.GetFontSize();
        this.m_list.setFont(new Font(BsscFontFixPatch.GetFontName(), 0, getFontSize));
        this.m_tfEdit.setFont(new Font(BsscFontFixPatch.GetFontName(), 0, getFontSize));
        this.m_btnDisplay.setFont(new Font(BsscFontFixPatch.GetFontName(), 0, getFontSize));
        this.m_btnFind.setFont(new Font(BsscFontFixPatch.GetFontName(), 0, getFontSize));
        final BsscImagePanel bsscImagePanel2 = new BsscImagePanel();
        bsscImagePanel2.setLayout(new BorderLayout(2, 2));
        bsscImagePanel2.add("North", bsscImagePanel);
        bsscImagePanel2.add("South", this.m_btnFind);
        this.add("North", bsscImagePanel2);
        this.add("Center", this.m_list);
        this.add("South", this.m_btnDisplay);
        this.m_btnFind.addActionListener(this);
        this.m_btnDisplay.addActionListener(this);
        this.m_tfEdit.requestFocus();
    }
    
    public void setSearchString(final String text) {
        this.m_tfEdit.setText(text);
        if (text.length() != 0) {
            this.m_list.search(text);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.m_tfEdit || actionEvent.getSource() == this.m_btnFind) {
            final String text = this.m_tfEdit.getText();
            if (text.length() != 0) {
                this.m_list.search(text);
            }
        }
        else if (actionEvent.getSource() == this.m_btnDisplay) {
            this.m_list.active();
        }
    }
    
    public void loadFtsInfo() {
        try {
            if (this.m_vProjects != null) {
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
                        final Fts fts = nextElement.getFts();
                        if (fts == null) {
                            continue;
                        }
                        fts.process();
                        this.m_vFts.addElement(fts);
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
