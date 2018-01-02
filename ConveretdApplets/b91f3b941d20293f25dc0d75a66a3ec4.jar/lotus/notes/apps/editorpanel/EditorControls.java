// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editorpanel;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Event;
import java.util.Properties;
import java.net.MalformedURLException;
import java.awt.Image;
import lotus.notes.apps.editorapplet.EnProperties;
import java.net.URL;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Choice;
import lotus.notes.apps.editor.RTEdit;
import lotus.notes.apps.editorapplet.EditorApplet;
import java.util.Vector;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Panel;

public class EditorControls extends Panel
{
    public boolean bNeedsPriv;
    private MultiImgButton boldButton;
    private MultiImgButton italicsButton;
    private MultiImgButton underlineButton;
    private MultiImgButton alignmentButton;
    private MultiImgButton bulletButton;
    private MultiImgButton indentButton;
    private MultiImgButton linkButton;
    private ColorSelection colorSelect;
    private MultiImgButton h1Button;
    private MultiImgButton headlineButton;
    private MultiImgButton imageButton;
    private MultiImgButton spellButton;
    private MultiImgButton acceptButton;
    private MultiImgButton cancelButton;
    private MultiImgButton intlButton;
    private Panel URLPanel;
    private TextField URLField;
    private Label URLLabel;
    private boolean optPanelShowing;
    private String linkPrompt;
    private String unicodePrompt;
    private Panel bP;
    private Vector faceNames;
    private String[] haikuSizeNames;
    private String[] v5SizeNames;
    private String h1Style;
    private String indentStyle;
    private String defaultStyle;
    private String ULbulletStyle;
    private String OLbulletStyle;
    private EditorApplet theEditorApplet;
    private RTEdit rtEditor;
    private int behavior;
    private Choice fontChoice;
    private Choice sizeChoice;
    private int baseIndent;
    
    public Insets insets() {
        return new Insets(0, 0, 0, 0);
    }
    
    public EditorControls(final EditorApplet theEditorApplet, final RTEdit rtEditor) {
        this.bNeedsPriv = false;
        this.h1Button = null;
        this.optPanelShowing = false;
        this.bP = new Panel();
        this.haikuSizeNames = new String[] { "8", "10", "12", "14", "18", "24", "36" };
        this.v5SizeNames = new String[] { "6", "8", "10", "12", "16", "22", "36" };
        this.h1Style = "Heading 1 (H1)";
        this.indentStyle = "Blockquote (BLOCKQUOTE)";
        this.defaultStyle = "Default";
        this.ULbulletStyle = "Unordered List (UL)";
        this.OLbulletStyle = "Ordered List (OL)";
        this.fontChoice = new Choice();
        this.sizeChoice = new Choice();
        this.theEditorApplet = theEditorApplet;
        this.rtEditor = rtEditor;
        this.bNeedsPriv = theEditorApplet.bNeedsPriv;
        this.behavior = theEditorApplet.getBehavior();
        this.baseIndent = rtEditor.getHTMLBaseIndent();
        this.createButtons(theEditorApplet.getCodeBase());
        this.initFaceStrings();
        final int size = this.faceNames.size();
        if (size == 0) {
            System.out.println("System error, unable to initialize Font Strings");
        }
        for (int i = 0; i < size; ++i) {
            this.fontChoice.addItem((String)this.faceNames.elementAt(i));
        }
        if (this.fontChoice.countItems() == 0) {
            System.out.println("System error, failed to init Font Choice");
        }
        String[] array;
        if (this.behavior == 1) {
            array = this.haikuSizeNames;
        }
        else {
            array = this.v5SizeNames;
        }
        for (int j = 0; j < array.length; ++j) {
            this.sizeChoice.addItem(array[j]);
        }
        this.setLayout(new BorderLayout());
        this.add("Center", this.bP);
        this.bP.setLayout(new FlowLayout(0, 0, 2));
        this.bP.add(this.boldButton);
        this.bP.add(this.italicsButton);
        this.bP.add(this.underlineButton);
        if (this.behavior == 1) {
            this.bP.add(this.colorSelect);
        }
        this.bP.add(new Gap(8));
        this.bP.add(this.alignmentButton);
        this.bP.add(this.indentButton);
        this.bP.add(this.bulletButton);
        if (this.behavior == 1) {
            this.bP.add(this.h1Button);
            this.bP.add(new Gap(8));
            this.bP.add(this.headlineButton);
            this.bP.add(this.imageButton);
            this.bP.add(this.spellButton);
        }
        else {
            this.bP.add(new Gap(8));
        }
        this.bP.add(this.linkButton);
        if (this.behavior == 0) {
            this.bP.add(this.intlButton);
        }
        this.bP.add(new Gap(8));
        if (this.behavior != 1) {
            this.bP.add(this.colorSelect);
            this.bP.add(new Gap(4));
        }
        this.bP.add(this.sizeChoice);
        this.bP.add(new Gap(4));
        this.bP.add(this.fontChoice);
        if (this.behavior == 0) {
            this.createToolPanels();
        }
    }
    
    private void initFaceStrings() {
        boolean b = false;
        if (this.behavior == 0) {
            final String language = this.theEditorApplet.getLocale().getLanguage();
            final String property = System.getProperty("os.name");
            b = (language != null && language.length() > 0 && language.equals("th") && property != null && property.indexOf("Win") != -1 && property.indexOf("98") != -1);
        }
        int n = 3;
        if (b) {
            ++n;
        }
        this.faceNames = new Vector(n);
        if (this.behavior == 1) {
            this.faceNames.addElement("Courier");
            this.faceNames.addElement("Helvetica");
            this.faceNames.addElement("Times");
        }
        else {
            this.faceNames.addElement("Monospace");
            this.faceNames.addElement("Sans Serif");
            this.faceNames.addElement("Serif");
            if (b) {
                this.faceNames.addElement("DialogInput");
            }
        }
    }
    
    private String convertFaceName(final String s) {
        String s2 = s;
        if (s != null && s.length() > 0 && s.indexOf("Times", 0) != -1) {
            s2 = "Times";
        }
        if (!this.faceNames.contains(s2)) {
            this.faceNames.addElement(s2);
            this.fontChoice.addItem(s2);
        }
        return s2;
    }
    
    private void createButtons(final URL url) {
        if (this.bNeedsPriv) {
            Utility.bNeedsPriv = true;
        }
        Properties properties = this.theEditorApplet.getProperties();
        if (properties == null || properties.isEmpty()) {
            properties = new EnProperties();
        }
        String s = "";
        if (this.behavior == 1) {
            s = "lotus/notes/apps/editorpanel/images/";
        }
        final Image loadImage = Utility.loadImage(url, s + "BlankButton.gif", this, true);
        final Image loadImage2 = Utility.loadImage(url, s + "mousIn.gif", this, true);
        if (loadImage == null) {
            System.out.println("Error creating Applet buttons!");
            return;
        }
        this.boldButton = new MultiImgButton(loadImage, loadImage2, Utility.loadImage(url, s + "Bold.gif", this, true));
        this.italicsButton = new MultiImgButton(loadImage, loadImage2, Utility.loadImage(url, s + "Italic.gif", this, true));
        this.underlineButton = new MultiImgButton(loadImage, loadImage2, Utility.loadImage(url, s + "Underline.gif", this, true));
        this.alignmentButton = new MultiImgButton(loadImage, loadImage2, Utility.loadImages(url, s, new String[] { "AlignL.gif", "AlignC.gif", "AlignR.gif" }, this, true));
        final String[] array = new String[3];
        if (this.behavior == 1) {
            array[0] = "Bullet0.gif";
        }
        else {
            array[0] = "Bullet.gif";
        }
        array[1] = "Bullet.gif";
        array[2] = "NumberedList.gif";
        this.bulletButton = new MultiImgButton(loadImage, loadImage2, Utility.loadImages(url, s, array, this, true));
        (this.indentButton = new MultiImgButton(loadImage, loadImage2, Utility.loadImages(url, s, new String[] { "Indent.gif", "Outdent.gif" }, this, true))).setFiltered(70);
        this.linkButton = new MultiImgButton(loadImage, loadImage2, Utility.loadImage(url, s + "Link.gif", this, true));
        if (this.behavior == 1) {
            final MultiImgButton multiImgButton = new MultiImgButton(loadImage, loadImage2, Utility.loadImage(url, s + "TextColor.gif", this, true));
            multiImgButton.showSelected(false);
            this.colorSelect = new ColorSelection(multiImgButton, false);
        }
        else {
            this.colorSelect = new ColorSelection(new MultiImgButton(null, null, Utility.loadImage(url, s + "dnarrow.gif", this, true)), true);
        }
        if (this.behavior == 1) {
            this.boldButton.showSelected(false);
            this.italicsButton.showSelected(false);
            this.underlineButton.showSelected(false);
            this.bulletButton.showSelected(false);
            this.linkButton.showSelected(false);
            this.indentButton.showSelected(false);
            this.h1Button = new MultiImgButton(loadImage, loadImage2, Utility.loadImage(url, s + "Headline.gif", this, true));
            this.headlineButton = new MultiImgButton(loadImage, loadImage2, Utility.loadImage(url, s + "GraphicHeadline.gif", this, true));
            this.imageButton = new MultiImgButton(loadImage, loadImage2, Utility.loadImage(url, s + "Image.gif", this, true));
            this.spellButton = new MultiImgButton(loadImage, loadImage2, Utility.loadImage(url, s + "spellcheck.gif", this, true));
            this.headlineButton.showSelected(false);
            this.imageButton.showSelected(false);
            this.h1Button.showSelected(false);
            this.spellButton.showSelected(false);
            this.alignmentButton.showSelected(false);
            this.h1Button.setToolTipText(properties.getProperty("H1"));
            this.headlineButton.setToolTipText(properties.getProperty("HEADLINE"));
            this.imageButton.setToolTipText(properties.getProperty("IMAGE"));
            this.spellButton.setToolTipText(properties.getProperty("SPELL"));
            this.colorSelect.setToolTipText(properties.getProperty("COLORSELECT"));
        }
        else {
            (this.intlButton = new MultiImgButton(loadImage, loadImage2, Utility.loadImage(url, s + "Intl.gif", this, true))).showSelected(false);
            URL url2;
            try {
                url2 = new URL(url.getProtocol(), url.getHost(), url.getPort(), "/icons/");
            }
            catch (MalformedURLException ex) {
                url2 = url;
            }
            final Image loadImage3 = Utility.loadImage(url2, "blank.gif", this, true);
            this.acceptButton = new MultiImgButton(loadImage3, null, Utility.loadImage(url2, "actn010.gif", this, true));
            this.cancelButton = new MultiImgButton(loadImage3, null, Utility.loadImage(url2, "actn011.gif", this, true));
            this.acceptButton.showSelected(false);
            this.cancelButton.showSelected(false);
            this.acceptButton.setToolTipText(properties.getProperty("ACCEPT"));
            this.cancelButton.setToolTipText(properties.getProperty("CANCEL"));
            this.intlButton.setToolTipText(properties.getProperty("INTL"));
            this.linkPrompt = properties.getProperty("LINK_PROMPT");
            this.unicodePrompt = properties.getProperty("UNICODE_PROMPT");
        }
        this.boldButton.setToolTipText(properties.getProperty("BOLD"));
        this.italicsButton.setToolTipText(properties.getProperty("ITALIC"));
        this.underlineButton.setToolTipText(properties.getProperty("UNDERLINE"));
        this.alignmentButton.setToolTipText(new String[] { properties.getProperty("ALIGN_LEFT"), properties.getProperty("ALIGN_CENTER"), properties.getProperty("ALIGN_RIGHT") });
        final String[] toolTipText = new String[3];
        if (this.behavior == 1) {
            toolTipText[0] = properties.getProperty("BULLET");
        }
        else {
            toolTipText[0] = properties.getProperty("UNORDERED");
        }
        toolTipText[1] = properties.getProperty("UNORDERED");
        toolTipText[2] = properties.getProperty("ORDERED");
        this.bulletButton.setToolTipText(toolTipText);
        this.indentButton.setToolTipText(new String[] { properties.getProperty("INDENT"), properties.getProperty("OUTDENT") });
        this.linkButton.setToolTipText(properties.getProperty("LINK"));
    }
    
    private void createToolPanels() {
        this.URLLabel = new Label(this.unicodePrompt);
        this.URLField = new TextField("", 25);
        (this.URLPanel = new Panel()).setLayout(new eFlowLayout(2, 0, 2));
        this.URLPanel.add(this.URLLabel);
        this.URLPanel.add(this.URLField);
        this.URLPanel.add(new Gap(8, 20));
        this.URLPanel.add(this.acceptButton);
        this.URLPanel.add(this.cancelButton);
    }
    
    private void addPanel(final Panel panel) {
        this.add("South", panel);
        panel.show();
        this.validate();
        this.theEditorApplet.invalidate();
        this.theEditorApplet.validate();
        this.optPanelShowing = true;
    }
    
    private void removePanel(final Panel panel) {
        panel.hide();
        this.remove(panel);
        this.optPanelShowing = false;
        this.theEditorApplet.invalidate();
        this.theEditorApplet.validate();
    }
    
    private boolean doAccept() {
        final boolean b = false;
        final String text = this.URLField.getText();
        if (this.URLLabel.getText().equals(this.linkPrompt)) {
            this.rtEditor.setURL(text);
        }
        else {
            try {
                final String stringToUnicode = Utility.stringToUnicode(text);
                if (stringToUnicode == null) {
                    this.URLField.setText("");
                    this.URLField.requestFocus();
                    return true;
                }
                this.rtEditor.insertText(stringToUnicode);
            }
            catch (Exception ex) {
                this.URLField.setText("");
                this.URLField.requestFocus();
                return true;
            }
        }
        this.removePanel(this.URLPanel);
        return b;
    }
    
    public void updateUI() {
        if (this.optPanelShowing) {
            this.removePanel(this.URLPanel);
        }
        final String style = this.rtEditor.getStyle();
        this.boldButton.setSelected(this.rtEditor.isBold());
        this.italicsButton.setSelected(this.rtEditor.isItalic());
        this.underlineButton.setSelected(this.rtEditor.isUnderline());
        this.linkButton.setSelected(this.rtEditor.getURL() != null);
        boolean doFiltered = false;
        if (this.behavior == 1) {
            this.h1Button.setSelected(style.equals(this.h1Style));
            doFiltered = this.indentButton.doFiltered();
            if (style.equals(this.h1Style)) {
                this.indentButton.doFiltered(true);
            }
            else {
                this.indentButton.doFiltered(false);
            }
        }
        int n = 0;
        if (this.behavior == 1) {
            n = 1;
        }
        final int alignment = this.rtEditor.getAlignment();
        if (this.alignmentButton.getCurImage() != (alignment + n) % 3) {
            this.alignmentButton.setCurImage((alignment + n) % 3);
        }
        this.alignmentButton.setSelected(alignment != 0);
        final char bullet = this.rtEditor.getBullet();
        this.bulletButton.getCurImage();
        int curImage;
        if (bullet > 'i') {
            curImage = 2;
        }
        else if (bullet != '\0') {
            curImage = 0;
        }
        else {
            curImage = 1;
        }
        if (this.behavior == 0) {
            curImage = (curImage + 2) % 3;
        }
        this.bulletButton.setCurImage(curImage);
        this.bulletButton.setSelected(bullet != '\0');
        int curImage2 = 0;
        final int restIndent = this.rtEditor.getRestIndent();
        if (style.equals(this.indentStyle) || restIndent > this.baseIndent * 2) {
            ++curImage2;
        }
        if (this.indentButton.getCurImage() != curImage2) {
            this.indentButton.setCurImage(curImage2);
        }
        else if (this.indentButton.doFiltered() != doFiltered) {
            this.indentButton.repaint();
        }
        this.indentButton.setSelected(curImage2 > 0);
        this.fontChoice.select(this.convertFaceName(this.rtEditor.getFaceName()));
        if (this.behavior != 1) {
            final int itemCount = this.sizeChoice.getItemCount();
            final int pointSize = this.rtEditor.getPointSize();
            for (int i = 0; i <= itemCount - 1; ++i) {
                final int int1 = Integer.parseInt(this.sizeChoice.getItem(i));
                if (pointSize == int1) {
                    this.sizeChoice.select(i);
                    break;
                }
                if (pointSize < int1) {
                    this.sizeChoice.insert(Integer.toString(pointSize), i);
                    this.sizeChoice.select(i);
                    break;
                }
                if (i == itemCount - 1) {
                    this.sizeChoice.insert(Integer.toString(pointSize), i + 1);
                    this.sizeChoice.select(i + 1);
                    break;
                }
                final int int2 = Integer.parseInt(this.sizeChoice.getItem(i + 1));
                if (pointSize > int1 && pointSize < int2) {
                    this.sizeChoice.insert(Integer.toString(pointSize), i + 1);
                    this.sizeChoice.select(i + 1);
                    break;
                }
            }
        }
        else {
            this.sizeChoice.select(this.rtEditor.getHTMLSize() - 1);
        }
        if (this.behavior != 1) {
            this.colorSelect.setColor(this.rtEditor.getFontColor());
        }
    }
    
    public boolean handleEvent(final Event event) {
        int n = 0;
        final boolean b = false;
        int n2 = 0;
        int n3 = 0;
        if (this.behavior == 1) {
            n3 = 2;
        }
        if (event.id != 1001) {
            if (event.id == 401) {
                if (event.key == 10 && this.optPanelShowing) {
                    if (!this.doAccept()) {
                        this.theEditorApplet.setFocus();
                    }
                    return true;
                }
                if (event.key == 27 && this.optPanelShowing) {
                    this.removePanel(this.URLPanel);
                    this.theEditorApplet.setFocus();
                    return true;
                }
            }
            else if ((event.id == 502 || event.id == 505) && event.target == this.colorSelect) {
                this.theEditorApplet.setFocus();
                return true;
            }
            return super.handleEvent(event);
        }
        if (event.target == this.fontChoice) {
            this.rtEditor.setFaceName((String)event.arg);
            return true;
        }
        if (event.target == this.sizeChoice) {
            this.rtEditor.setPointSize(Integer.parseInt((String)event.arg));
            return true;
        }
        if (event.target == this.colorSelect) {
            this.rtEditor.setFontColor(this.colorSelect.getColor());
            return true;
        }
        if (event.target == this.boldButton) {
            this.rtEditor.setBold(this.boldButton.getSelected());
        }
        else if (event.target == this.italicsButton) {
            this.rtEditor.setItalic(this.italicsButton.getSelected());
        }
        else if (event.target == this.underlineButton) {
            this.rtEditor.setUnderline(this.underlineButton.getSelected());
        }
        else if (event.target == this.alignmentButton) {
            this.rtEditor.setAlignment((this.alignmentButton.getCurImage() + n3) % 3);
            this.rtEditor.repaint();
        }
        else if (event.target == this.bulletButton) {
            n = 1;
            final boolean b2 = this.indentButton.getCurImage() != 0;
            final int n4 = (this.bulletButton.getCurImage() + n3) % 3;
            if (n4 > 0) {
                String s;
                if (n4 == 1) {
                    s = this.ULbulletStyle;
                }
                else {
                    s = this.OLbulletStyle;
                }
                if (this.h1Button != null && this.h1Style.equals(this.rtEditor.getStyle())) {
                    this.rtEditor.setStyle(s);
                }
                else {
                    this.rtEditor.setStylePreserve(s);
                }
                if (b2) {
                    n2 = this.baseIndent * 3;
                }
                else {
                    n2 = this.baseIndent * 2;
                }
            }
            else if (b2) {
                this.rtEditor.setStylePreserve(this.indentStyle);
                n2 = this.baseIndent;
            }
            else {
                this.rtEditor.setStylePreserve(this.defaultStyle);
            }
        }
        else if (event.target == this.indentButton) {
            n = 1;
            final boolean b3 = this.rtEditor.getBullet() != '\0';
            if (this.indentButton.getCurImage() != 0) {
                if (b3) {
                    n2 = this.baseIndent * 3;
                }
                else if (this.h1Button == null || !this.h1Style.equals(this.rtEditor.getStyle())) {
                    n2 = this.baseIndent;
                    this.rtEditor.setStylePreserve(this.indentStyle);
                }
            }
            else if (b3) {
                n2 = this.baseIndent * 2;
            }
            else {
                this.rtEditor.setStylePreserve(this.defaultStyle);
            }
        }
        else if (event.target == this.h1Button) {
            if (!this.h1Style.equals(this.rtEditor.getStyle())) {
                this.rtEditor.setStyle(this.h1Style);
                this.indentButton.doFiltered(true);
            }
            else {
                this.rtEditor.setStyle(this.defaultStyle);
                this.indentButton.doFiltered(false);
            }
            this.indentButton.repaint();
        }
        else if (event.target == this.imageButton) {
            this.theEditorApplet.imageClicked = true;
        }
        else if (event.target == this.headlineButton) {
            this.theEditorApplet.headlineClicked = true;
        }
        else if (event.target == this.spellButton) {
            this.theEditorApplet.spellClicked = true;
        }
        else if (event.target == this.linkButton) {
            if (this.behavior == 0) {
                if (!this.URLPanel.isShowing() || !this.URLLabel.getText().equals(this.linkPrompt)) {
                    this.URLField.setText(this.rtEditor.getURL());
                    this.URLLabel.setText(this.linkPrompt);
                    this.URLField.invalidate();
                    this.URLLabel.invalidate();
                    this.URLLabel.getParent().validate();
                    if (!this.URLPanel.isShowing()) {
                        this.addPanel(this.URLPanel);
                    }
                    this.URLField.requestFocus();
                    return true;
                }
                this.removePanel(this.URLPanel);
            }
            else {
                this.theEditorApplet.linkClicked = true;
            }
        }
        else if (event.target == this.acceptButton) {
            if (this.doAccept()) {
                return true;
            }
        }
        else if (event.target == this.cancelButton) {
            this.removePanel(this.URLPanel);
        }
        else if (event.target == this.intlButton && this.behavior == 0) {
            if (!this.URLPanel.isShowing() || !this.URLLabel.getText().equals(this.unicodePrompt)) {
                this.URLField.setText("");
                this.URLLabel.setText(this.unicodePrompt);
                this.URLField.invalidate();
                this.URLLabel.invalidate();
                this.URLLabel.getParent().validate();
                if (!this.URLPanel.isShowing()) {
                    this.addPanel(this.URLPanel);
                }
                this.URLField.requestFocus();
                return true;
            }
            this.removePanel(this.URLPanel);
        }
        if (this.optPanelShowing && !b) {
            this.removePanel(this.URLPanel);
        }
        if (n == 1) {
            this.rtEditor.setRestIndent(n2);
            this.rtEditor.setFirstIndent(n2);
        }
        this.theEditorApplet.setFocus();
        return true;
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.behavior != 1) {
            final Rectangle bounds = this.colorSelect.bounds();
            this.colorSelect.reshape(bounds.x, this.fontChoice.bounds().y, bounds.width, bounds.height);
        }
    }
}
