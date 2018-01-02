import java.awt.Image;
import java.awt.Frame;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.util.Hashtable;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class WorkPanel extends Panel implements ActionListener, ItemListener, FocusListener
{
    CMFrame cmf;
    public TextField itemheight;
    public TextField menuheight;
    public TextField appletwidth;
    public TextField appletheight;
    public TextField itemoffset;
    public TextField defaulttarget;
    public TextField defaulticon;
    public TextField menucolor1;
    public TextField menucolor2;
    public TextField menucolor3;
    public TextField menucolor4;
    public TextField itemcolor1;
    public TextField itemcolor2;
    public TextField itemcolor3;
    public TextField itemcolor4;
    public TextField bgcolor;
    public TextField bgimage;
    Choice itemfont1;
    Choice itemfont2;
    Choice menufont1;
    Choice menufont2;
    public Choice menutype1;
    public Choice menutype2;
    public Choice itemtype1;
    public Choice itemtype2;
    public Choice useanim;
    Label litemheight;
    Label lmenuheight;
    Label lappletwidth;
    Label lappletheight;
    Label litemoffset;
    Label ldefaulttarget;
    Label ldefaulticon;
    Label lmenucolor1;
    Label lmenucolor2;
    Label lmenufont1;
    Label lmenufont2;
    Label lmenusize1;
    Label lmenusize2;
    Label litemcolor1;
    Label litemcolor2;
    Label litemfont1;
    Label litemfont2;
    Label litemsize1;
    Label litemsize2;
    Label lmenutype1;
    Label lmenutype2;
    Label litemtype1;
    Label litemtype2;
    Label luseanim;
    Label lmenucolor3;
    Label lmenucolor4;
    Label litemcolor3;
    Label litemcolor4;
    Label lbgcolor;
    Label lbgimage;
    Label lmain;
    Label lmenu;
    Label litem;
    Choice menusize1;
    Choice menusize2;
    Choice itemsize1;
    Choice itemsize2;
    MenuSet ms;
    ItemEdit ie;
    Label help;
    String mainhelp;
    Panel pmain;
    Panel pitems;
    Panel pmenu;
    Panel pchoice;
    Checkbox c1;
    Checkbox c2;
    Checkbox c3;
    CheckboxGroup cg;
    TextField activeField;
    TextField key;
    Label lkey;
    Button updateB;
    Button itemedit;
    Button colorEdit;
    Button export;
    Button getkey;
    Panel pkey;
    
    public WorkPanel(final CMFrame cmf) {
        this.help = new Label();
        this.mainhelp = "Change fields and press \"View Results\" to check results";
        this.setLayout(null);
        this.setBackground(Color.lightGray);
        this.cmf = cmf;
        this.createGUI();
        this.placeGUI();
        this.ms = this.createBasicMenu();
        this.help.setText(this.mainhelp);
        this.add(this.pmain);
        this.colorEdit.setEnabled(false);
    }
    
    private void placeGUI() {
        this.lkey.setBounds(10, 10, 100, 20);
        this.key.setBounds(10, 40, 100, 20);
        this.getkey.setBounds(10, 70, 100, 20);
        this.pkey.setBounds(520, 50, 120, 100);
        this.pkey.add(this.lkey);
        this.pkey.add(this.key);
        this.pkey.add(this.getkey);
        this.add(this.pkey);
        this.getkey.addActionListener(this);
        this.c1.setBounds(10, 10, 120, 20);
        this.c2.setBounds(10, 40, 120, 20);
        this.c3.setBounds(10, 70, 120, 20);
        this.pchoice.add(this.c1);
        this.c1.addItemListener(this);
        this.c2.addItemListener(this);
        this.c3.addItemListener(this);
        this.pchoice.add(this.c2);
        this.pchoice.add(this.c3);
        this.pchoice.setBounds(20, 50, 140, 100);
        this.add(this.pchoice);
        this.pmain.setBounds(200, 10, 300, 305);
        this.pmenu.setBounds(200, 10, 300, 305);
        this.pitems.setBounds(200, 10, 300, 305);
        this.updateB.setBounds(30, 250, 80, 30);
        this.add(this.updateB);
        this.export.setBounds(530, 200, 80, 30);
        this.add(this.export);
        this.export.addActionListener(this);
        this.updateB.addActionListener(this);
        this.itemedit.setBounds(30, 150, 80, 30);
        this.add(this.itemedit);
        this.colorEdit.setBounds(30, 200, 80, 30);
        this.add(this.colorEdit);
        this.colorEdit.addActionListener(this);
        this.itemedit.addActionListener(this);
        final int n = 190;
        final int n2 = 20;
        this.help.setBounds(30, 330, 500, 20);
        this.add(this.help);
        this.litemheight.setBounds(20, 10, n, n2);
        this.pmain.add(this.litemheight);
        this.lmenuheight.setBounds(20, 40, n, n2);
        this.pmain.add(this.lmenuheight);
        this.lappletwidth.setBounds(20, 70, n, n2);
        this.pmain.add(this.lappletwidth);
        this.lappletheight.setBounds(20, 100, n, n2);
        this.pmain.add(this.lappletheight);
        this.litemoffset.setBounds(20, 130, n, n2);
        this.pmain.add(this.litemoffset);
        this.ldefaulttarget.setBounds(20, 160, n, n2);
        this.pmain.add(this.ldefaulttarget);
        this.ldefaulticon.setBounds(20, 190, n, n2);
        this.pmain.add(this.ldefaulticon);
        this.lmenucolor1.setBounds(20, 10, n, n2);
        this.pmenu.add(this.lmenucolor1);
        this.lmenucolor2.setBounds(20, 40, n, n2);
        this.pmenu.add(this.lmenucolor2);
        this.lmenucolor3.setBounds(20, 70, n, n2);
        this.pmenu.add(this.lmenucolor3);
        this.lmenucolor4.setBounds(20, 100, n, n2);
        this.pmenu.add(this.lmenucolor4);
        this.lmenufont1.setBounds(20, 130, n, n2);
        this.pmenu.add(this.lmenufont1);
        this.lmenufont2.setBounds(20, 160, n, n2);
        this.pmenu.add(this.lmenufont2);
        this.lmenusize1.setBounds(20, 190, n, n2);
        this.pmenu.add(this.lmenusize1);
        this.lmenusize2.setBounds(20, 220, n, n2);
        this.pmenu.add(this.lmenusize2);
        this.litemcolor1.setBounds(20, 10, n, n2);
        this.pitems.add(this.litemcolor1);
        this.litemcolor2.setBounds(20, 40, n, n2);
        this.pitems.add(this.litemcolor2);
        this.litemcolor3.setBounds(20, 70, n, n2);
        this.pitems.add(this.litemcolor3);
        this.litemcolor4.setBounds(20, 100, n, n2);
        this.pitems.add(this.litemcolor4);
        this.litemfont1.setBounds(20, 130, n, n2);
        this.pitems.add(this.litemfont1);
        this.litemfont2.setBounds(20, 160, n, n2);
        this.pitems.add(this.litemfont2);
        this.litemsize1.setBounds(20, 190, n, n2);
        this.pitems.add(this.litemsize1);
        this.litemsize2.setBounds(20, 220, n, n2);
        this.pitems.add(this.litemsize2);
        this.lmenutype1.setBounds(20, 250, n, n2);
        this.pmenu.add(this.lmenutype1);
        this.lmenutype2.setBounds(20, 280, n, n2);
        this.pmenu.add(this.lmenutype2);
        this.litemtype1.setBounds(20, 250, n, n2);
        this.pitems.add(this.litemtype1);
        this.litemtype2.setBounds(20, 280, n, n2);
        this.pitems.add(this.litemtype2);
        this.luseanim.setBounds(20, 220, n, n2);
        this.pmain.add(this.luseanim);
        this.lbgcolor.setBounds(20, 250, n, n2);
        this.pmain.add(this.lbgcolor);
        this.lbgimage.setBounds(20, 280, n, n2);
        this.pmain.add(this.lbgimage);
        final int n3 = 80;
        this.itemheight.setBounds(210, 10, n3, n2);
        this.pmain.add(this.itemheight);
        this.menuheight.setBounds(210, 40, n3, n2);
        this.pmain.add(this.menuheight);
        this.appletwidth.setBounds(210, 70, n3, n2);
        this.pmain.add(this.appletwidth);
        this.appletheight.setBounds(210, 100, n3, n2);
        this.pmain.add(this.appletheight);
        this.itemoffset.setBounds(210, 130, n3, n2);
        this.pmain.add(this.itemoffset);
        this.defaulttarget.setBounds(210, 160, n3, n2);
        this.pmain.add(this.defaulttarget);
        this.defaulticon.setBounds(210, 190, n3, n2);
        this.pmain.add(this.defaulticon);
        this.menucolor1.setBounds(210, 10, n3, n2);
        this.pmenu.add(this.menucolor1);
        this.menucolor2.setBounds(210, 40, n3, n2);
        this.pmenu.add(this.menucolor2);
        this.menucolor3.setBounds(210, 70, n3, n2);
        this.pmenu.add(this.menucolor3);
        this.menucolor4.setBounds(210, 100, n3, n2);
        this.pmenu.add(this.menucolor4);
        this.menufont1.setBounds(210, 130, n3, n2);
        this.pmenu.add(this.menufont1);
        this.menufont2.setBounds(210, 160, n3, n2);
        this.pmenu.add(this.menufont2);
        this.menusize1.setBounds(210, 190, n3, n2);
        this.pmenu.add(this.menusize1);
        this.menusize2.setBounds(210, 220, n3, n2);
        this.pmenu.add(this.menusize2);
        this.itemcolor1.setBounds(210, 10, n3, n2);
        this.pitems.add(this.itemcolor1);
        this.itemcolor2.setBounds(210, 40, n3, n2);
        this.pitems.add(this.itemcolor2);
        this.itemcolor3.setBounds(210, 70, n3, n2);
        this.pitems.add(this.itemcolor3);
        this.itemcolor4.setBounds(210, 100, n3, n2);
        this.pitems.add(this.itemcolor4);
        this.itemfont1.setBounds(210, 130, n3, n2);
        this.pitems.add(this.itemfont1);
        this.itemfont2.setBounds(210, 160, n3, n2);
        this.pitems.add(this.itemfont2);
        this.itemsize1.setBounds(210, 190, n3, n2);
        this.pitems.add(this.itemsize1);
        this.itemsize2.setBounds(210, 220, n3, n2);
        this.pitems.add(this.itemsize2);
        this.menutype1.setBounds(210, 250, n3, n2);
        this.pmenu.add(this.menutype1);
        this.menutype2.setBounds(210, 280, n3, n2);
        this.pmenu.add(this.menutype2);
        this.itemtype1.setBounds(210, 250, n3, n2);
        this.pitems.add(this.itemtype1);
        this.itemtype2.setBounds(210, 280, n3, n2);
        this.pitems.add(this.itemtype2);
        this.useanim.setBounds(210, 220, n3, n2);
        this.pmain.add(this.useanim);
        this.bgcolor.setBounds(210, 250, n3, n2);
        this.pmain.add(this.bgcolor);
        this.bgimage.setBounds(210, 280, n3, n2);
        this.pmain.add(this.bgimage);
    }
    
    private void createGUI() {
        this.getkey = new Button("Register for Key");
        this.key = new TextField("Unregistered");
        this.lkey = new Label("Registration key");
        this.pkey = new Panel(null);
        this.export = new Button("Export");
        this.cg = new CheckboxGroup();
        this.c1 = new Checkbox("Main Settings", true, this.cg);
        this.c2 = new Checkbox("Menubar Settings", false, this.cg);
        this.c3 = new Checkbox("Item Settings", false, this.cg);
        this.pchoice = new Panel(null);
        this.pmain = new Panel(null);
        this.pitems = new Panel(null);
        this.pmenu = new Panel(null);
        this.lbgimage = new Label("Background Image");
        this.lbgcolor = new Label("Background Color");
        this.litem = new Label("Item Settings");
        this.lmenu = new Label("Menu Settings");
        this.lmain = new Label("Main Settings");
        this.litemheight = new Label("Item Height");
        this.lmenuheight = new Label("Menubar Height");
        this.lappletwidth = new Label("Applet Width");
        this.lappletheight = new Label("Applet Height");
        this.litemoffset = new Label("Head Spacing");
        this.ldefaulttarget = new Label("Default Target");
        this.ldefaulticon = new Label("Default Icon Image");
        this.lmenucolor1 = new Label("Background Color");
        this.lmenucolor2 = new Label("Background Color Over");
        this.lmenucolor3 = new Label("Text Color");
        this.lmenucolor4 = new Label("Text Color Over");
        this.lmenufont1 = new Label("Menu Font");
        this.lmenufont2 = new Label("Menu Font Over");
        this.lmenusize1 = new Label("Font Size");
        this.lmenusize2 = new Label("Font Size Over");
        this.litemcolor1 = new Label("Background Color");
        this.litemcolor2 = new Label("Background Color Over");
        this.litemcolor3 = new Label("Text Color");
        this.litemcolor4 = new Label("Text Color Over");
        this.litemfont1 = new Label("Item Font");
        this.litemfont2 = new Label("Item Font Over");
        this.litemsize1 = new Label("Font Size");
        this.litemsize2 = new Label("Font Size Over");
        this.lmenutype1 = new Label("Font Style");
        this.lmenutype2 = new Label("Font Style Over");
        this.litemtype1 = new Label("Font Style");
        this.litemtype2 = new Label("Font Style Over");
        this.luseanim = new Label("Animation / Shadow");
        this.bgcolor = new TextField("FFFFFF");
        this.bgimage = new TextField("logo.gif");
        this.itemheight = new TextField("16");
        this.menuheight = new TextField("20");
        this.appletwidth = new TextField("500");
        this.appletheight = new TextField("120");
        this.itemoffset = new TextField("10");
        this.defaulttarget = new TextField("_self");
        this.defaulticon = new TextField("icon1.gif");
        (this.menucolor1 = new TextField("000088")).addFocusListener(this);
        (this.menucolor2 = new TextField("0000FF")).addFocusListener(this);
        (this.menucolor3 = new TextField("FFFF00")).addFocusListener(this);
        (this.menucolor4 = new TextField("FF0000")).addFocusListener(this);
        this.addFonts(this.menufont1 = new Choice());
        this.addFonts(this.menufont2 = new Choice());
        this.fillSizes(this.menusize1 = new Choice());
        this.fillSizes(this.menusize2 = new Choice());
        (this.itemcolor1 = new TextField("000088")).addFocusListener(this);
        (this.itemcolor2 = new TextField("0000FF")).addFocusListener(this);
        (this.itemcolor3 = new TextField("FFFFFF")).addFocusListener(this);
        (this.itemcolor4 = new TextField("000000")).addFocusListener(this);
        this.addFonts(this.itemfont1 = new Choice());
        this.addFonts(this.itemfont2 = new Choice());
        this.fillSizes(this.itemsize1 = new Choice());
        this.fillSizes(this.itemsize2 = new Choice());
        (this.menutype1 = new Choice()).add("Normal");
        this.menutype1.add("Bold");
        this.menutype1.add("Italic");
        (this.menutype2 = new Choice()).add("Normal");
        this.menutype2.add("Bold");
        this.menutype2.add("Italic");
        (this.itemtype1 = new Choice()).add("Normal");
        this.itemtype1.add("Bold");
        this.itemtype1.add("Italic");
        (this.itemtype2 = new Choice()).add("Normal");
        this.itemtype2.add("Bold");
        this.itemtype2.add("Italic");
        (this.useanim = new Choice()).add("Yes / Yes");
        this.useanim.add("Yes / No");
        this.useanim.add("No / Yes");
        this.useanim.add("No / No");
        this.bgcolor.addFocusListener(this);
        this.updateB = new Button("Show Effect");
        this.itemedit = new Button("Edit Items");
        this.colorEdit = new Button("Pick Colors");
    }
    
    public void fillSizes(final Choice choice) {
        for (int i = 8; i < 21; ++i) {
            choice.add(i + "");
        }
        choice.select(4);
    }
    
    public void addFonts(final Choice choice) {
        final String[] fontList = this.getToolkit().getFontList();
        for (int i = 0; i < fontList.length; ++i) {
            choice.addItem(fontList[i]);
        }
    }
    
    public Hashtable extractData() {
        String s = "Yes";
        String s2 = "Yes";
        if (this.useanim.getSelectedIndex() == 1) {
            s = "No";
        }
        else if (this.useanim.getSelectedIndex() == 2) {
            s2 = "No";
        }
        else if (this.useanim.getSelectedIndex() == 3) {
            s2 = "No";
            s = "No";
        }
        System.out.println("UA: " + s2 + "US: " + s);
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put("use_animation", s2);
        hashtable.put("registrationkey", this.key.getText());
        hashtable.put("menubar_height", this.menuheight.getText());
        hashtable.put("item_height", this.itemheight.getText());
        hashtable.put("item_offset", this.itemoffset.getText());
        hashtable.put("applet_height", this.appletheight.getText());
        hashtable.put("applet_width", this.appletwidth.getText());
        hashtable.put("default_target", this.defaulttarget.getText());
        hashtable.put("default_icon", this.defaulticon.getText());
        hashtable.put("menubar_bgcolorout", this.menucolor1.getText());
        hashtable.put("menubar_bgcolorover", this.menucolor2.getText());
        hashtable.put("menubar_fgcolorout", this.menucolor3.getText());
        hashtable.put("menubar_fgcolorover", this.menucolor4.getText());
        hashtable.put("item_bgcolorout", this.itemcolor1.getText());
        hashtable.put("item_bgcolorover", this.itemcolor2.getText());
        hashtable.put("item_fgcolorout", this.itemcolor3.getText());
        hashtable.put("item_fgcolorover", this.itemcolor4.getText());
        hashtable.put("menubar_fontstyleout", this.menufont1.getSelectedItem());
        hashtable.put("menubar_fontstyleover", this.menufont2.getSelectedItem());
        hashtable.put("menubar_fontsizeout", this.menusize1.getSelectedItem());
        hashtable.put("menubar_fontsizeover", this.menusize2.getSelectedItem());
        hashtable.put("menubar_fonttypeout", this.menutype1.getSelectedIndex() + "");
        hashtable.put("menubar_fonttypeover", this.menutype2.getSelectedIndex() + "");
        hashtable.put("item_fontstyleout", this.itemfont1.getSelectedItem());
        hashtable.put("item_fontstyleover", this.itemfont2.getSelectedItem());
        hashtable.put("item_fontsizeout", this.itemsize1.getSelectedItem());
        hashtable.put("item_fontsizeover", this.itemsize2.getSelectedItem());
        hashtable.put("item_fonttypeout", this.itemtype1.getSelectedIndex() + "");
        hashtable.put("item_fonttypeover", this.itemtype2.getSelectedIndex() + "");
        hashtable.put("bgcolor", this.bgcolor.getText());
        hashtable.put("bgimage", this.bgimage.getText());
        hashtable.put("use_shadow", s);
        return hashtable;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.lightGray);
        graphics.draw3DRect(198, 8, 303, 307, false);
        graphics.draw3DRect(518, 48, 122, 102, false);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.updateB) {
            this.cmf.updateNow(this.ms);
        }
        else if (actionEvent.getSource() == this.itemedit) {
            this.doItemEdit();
        }
        else if (actionEvent.getSource() == this.colorEdit) {
            this.doColorEdit();
        }
        else if (actionEvent.getSource() == this.export) {
            this.doExport();
        }
        else if (actionEvent.getSource() == this.getkey) {
            this.doGetKey();
        }
    }
    
    public MenuSet getSet() {
        return this.ms;
    }
    
    public void doGetKey() {
        URL url = null;
        try {
            url = new URL("http://www.realapplets.com");
        }
        catch (Exception ex) {}
        this.cmf.cme.getAppletContext().showDocument(url, "_blank");
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.remove(this.pmenu);
        this.remove(this.pmain);
        this.remove(this.pitems);
        if (itemEvent.getSource() == this.c1) {
            this.add(this.pmain);
        }
        else if (itemEvent.getSource() == this.c2) {
            this.add(this.pmenu);
        }
        else if (itemEvent.getSource() == this.c3) {
            this.add(this.pitems);
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.colorEdit.setEnabled(true);
        if (focusEvent.getSource() instanceof TextField) {
            this.activeField = (TextField)focusEvent.getSource();
        }
        else {
            System.out.println("Not a textfield");
        }
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.colorEdit.setEnabled(false);
    }
    
    public MenuSet getMenuSet() {
        return this.createBasicMenu();
    }
    
    public void doExport() {
        final ExportWindow exportWindow = new ExportWindow(this, this.cmf);
        exportWindow.setSize(600, 430);
        exportWindow.setVisible(true);
    }
    
    public void doColorEdit() {
        if (this.activeField != null) {
            final HexColorTool hexColorTool = new HexColorTool(this.cmf, this.activeField);
            hexColorTool.setSize(350, 360);
            hexColorTool.setVisible(true);
        }
    }
    
    public void doItemEdit() {
        (this.ie = new ItemEdit(this.cmf, this, this.ms, null, 1)).setSize(500, 400);
        this.ie.setVisible(true);
    }
    
    public MenuSet createBasicMenu() {
        URL url = null;
        try {
            url = new URL("http://www.realapplets.com");
        }
        catch (Exception ex) {}
        final Item item = new Item("Head1", null, "_blank", true, null);
        final Item item2 = new Item("Head2", null, "_blank", true, null);
        final Item item3 = new Item("Item 1_1", null, "_blank", false, this.cmf.cme.getImage(this.cmf.cme.getCodeBase(), this.defaulticon.getText()));
        item3.setImageName(this.defaulticon.getText());
        final Item item4 = new Item("Item 2_1", null, "_blank", false, this.cmf.cme.getImage(this.cmf.cme.getCodeBase(), "icon2.gif"));
        item4.setImageName("icon2.gif");
        final Item item5 = new Item("Item 1_2", null, "_blank", false, this.cmf.cme.getImage(this.cmf.cme.getCodeBase(), this.defaulticon.getText()));
        item5.setImageName(this.defaulticon.getText());
        final Item item6 = new Item("Item 1_3", null, "_blank", false, this.cmf.cme.getImage(this.cmf.cme.getCodeBase(), this.defaulticon.getText()));
        item6.setImageName(this.defaulticon.getText());
        final Item item7 = new Item("Item 1_3_1", url, "_blank", false, this.cmf.cme.getImage(this.cmf.cme.getCodeBase(), this.defaulticon.getText()));
        item7.setImageName(this.defaulticon.getText());
        item7.setURLName("http://www.realapplets.com");
        final MenuSet set = new MenuSet();
        set.addItem(item7);
        final MenuSet set2 = new MenuSet();
        item6.giveSub(set);
        set2.addItem(item3);
        set2.addItem(item5);
        set2.addItem(item6);
        item.giveSub(set2);
        final MenuSet set3 = new MenuSet();
        set3.addItem(item);
        final MenuSet set4 = new MenuSet();
        set4.addItem(item4);
        item2.giveSub(set4);
        set3.addItem(item2);
        return set3;
    }
}
