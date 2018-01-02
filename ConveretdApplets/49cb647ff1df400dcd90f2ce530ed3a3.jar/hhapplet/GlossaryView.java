// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import XMLConsumer.Glossary;
import java.util.Enumeration;
import XMLConsumer.Project;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import XMLConsumer.GlossaryEntry;
import java.awt.Color;
import java.util.Vector;

public class GlossaryView extends BsscImagePanel implements IActionSink
{
    private Vector m_vProjects;
    private Vector m_vGlossary;
    private BsscTextArea m_edtDefinition;
    private GloListView m_list;
    private BsscImageLabel m_lblDefinition;
    private String m_sDefaultDefinitionText;
    
    public GlossaryView(final Vector vProjects, final GloViewSkin gloViewSkin) {
        this.m_vProjects = vProjects;
        final BsscImagePanel bsscImagePanel = new BsscImagePanel();
        final BsscImageLabel bsscImageLabel = new BsscImageLabel(ResourceLib.GetRes("Term"));
        this.m_vGlossary = new Vector();
        this.loadGlossaryInfo();
        (this.m_list = new GloListView(this.m_vGlossary)).setBackground(Color.white);
        this.m_list.addActionSink(this);
        (this.m_edtDefinition = new BsscTextArea()).setEditable(false);
        final Color bgColor = gloViewSkin.getBgColor();
        if (bgColor != null) {
            this.m_list.setBackground(bgColor);
        }
        else {
            this.m_list.setBackground(Color.white);
        }
        final Image bgImage = gloViewSkin.getBgImage();
        if (bgImage != null) {
            this.m_list.setBgImage(bgImage);
        }
        final BsscFont normalFont = gloViewSkin.getNormalFont();
        if (normalFont != null) {
            final FontMetrics fontMetrics = this.getFontMetrics(normalFont.getFont());
            fontMetrics.getHeight();
            this.m_list.setUnitHeight(fontMetrics.getHeight());
            final Font font = normalFont.getFont();
            if (font != null) {
                GlossaryEntry.setNormalFont(font);
                this.m_edtDefinition.setFont(font);
            }
            final Color color = normalFont.getColor();
            if (color != null) {
                GlossaryEntry.setNormalColor(color);
                this.m_edtDefinition.setForeground(color);
            }
            GlossaryEntry.setNormalUnderline(normalFont.isUnderline());
        }
        GlossaryEntry.setActiveColor(gloViewSkin.getActiveColor());
        final BsscFont hoverFont = gloViewSkin.getHoverFont();
        if (hoverFont != null) {
            final Font font2 = hoverFont.getFont();
            if (font2 != null) {
                GlossaryEntry.setHoverFont(font2);
            }
            final Color color2 = hoverFont.getColor();
            if (color2 != null) {
                GlossaryEntry.setHoverColor(color2);
            }
            GlossaryEntry.setHoverUnderline(hoverFont.isUnderline());
        }
        bsscImagePanel.setLayout(new BorderLayout(2, 2));
        bsscImagePanel.add("North", bsscImageLabel);
        bsscImagePanel.add("Center", this.m_list);
        final BsscImagePanel bsscImagePanel2 = new BsscImagePanel() {
            {
                GlossaryView.this.getClass();
            }
            
            public Dimension getPreferredSize() {
                final Dimension size = this.getParent().getSize();
                size.height /= 3;
                return size;
            }
        };
        this.m_sDefaultDefinitionText = ResourceLib.GetRes("Definition") + " ";
        this.m_lblDefinition = new BsscImageLabel(this.m_sDefaultDefinitionText);
        bsscImagePanel2.setLayout(new BorderLayout(2, 2));
        bsscImagePanel2.add("North", this.m_lblDefinition);
        if (bgColor != null) {
            if (!bgColor.equals(this.m_edtDefinition.getForeground())) {
                this.m_edtDefinition.setBackground(bgColor);
            }
        }
        else if (!Color.white.equals(this.m_edtDefinition.getForeground())) {
            this.m_edtDefinition.setBackground(Color.white);
        }
        final Color background = this.m_edtDefinition.getBackground();
        final Color foreground = this.m_edtDefinition.getForeground();
        if ((background != null && foreground != null && background.equals(foreground)) || (background == null && foreground != null && foreground.equals(Color.white))) {
            final int blue = foreground.getBlue();
            final int red = foreground.getRed();
            final int green = foreground.getGreen();
            final int n = 255 - blue;
            final int n2 = 255 - red;
            final int n3 = 255 - green;
            Color black;
            if (blue == n && red == n2 && green == n3) {
                black = Color.black;
            }
            else {
                black = new Color(n2, n3, n);
            }
            this.m_edtDefinition.setForeground(black);
        }
        bsscImagePanel2.add("Center", this.m_edtDefinition);
        this.setLayout(new BorderLayout());
        this.add("Center", bsscImagePanel);
        this.add("South", bsscImagePanel2);
    }
    
    public void accept(final Vector vector) {
        if (vector != null && vector.size() == 2) {
            final String element = vector.elementAt(0);
            final String element2 = vector.elementAt(1);
            if (element instanceof String && element2 instanceof String) {
                final String s = element;
                final String text = element2;
                this.m_lblDefinition.setText(this.m_sDefaultDefinitionText + s);
                this.m_edtDefinition.setText(text);
            }
        }
    }
    
    public void loadGlossaryInfo() {
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
                        final Glossary glossary = nextElement.getGlossary();
                        if (glossary == null) {
                            continue;
                        }
                        glossary.process();
                        this.m_vGlossary.addElement(glossary);
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
