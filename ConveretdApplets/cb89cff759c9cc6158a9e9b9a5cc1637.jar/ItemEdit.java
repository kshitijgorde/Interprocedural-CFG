import java.awt.event.WindowEvent;
import java.awt.Image;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.List;
import java.awt.event.ItemListener;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class ItemEdit extends Dialog implements ActionListener, WindowListener, ItemListener
{
    CMFrame cmf;
    WorkPanel wp;
    List list;
    TextField name;
    TextField action;
    TextField target;
    TextField icon;
    Label lname;
    Label laction;
    Label ltarget;
    Label licon;
    Label llist;
    Label ldepth;
    Label linfo;
    boolean adding;
    MenuSet ms;
    int level;
    int itemsadded;
    Button ok;
    Button edit;
    Button save;
    Button add;
    Button remove;
    Item[] items;
    Item currentItem;
    Item i;
    String mainhelp;
    
    public ItemEdit(final CMFrame cmf, final WorkPanel wp, MenuSet ms, final Item i, final int level) {
        super(cmf, "Item Edit", true);
        this.mainhelp = "Select an item, when changed press Save. To go a level deeper press Edit";
        if (ms == null) {
            ms = new MenuSet();
        }
        this.setLayout(null);
        this.ms = ms;
        this.cmf = cmf;
        this.level = level;
        this.wp = wp;
        this.addWindowListener(this);
        this.createGUI();
        this.placeGUI();
        this.extractItems();
        this.i = i;
        this.updateFields();
        if (level == 1) {
            this.remove(this.icon);
            this.remove(this.licon);
        }
    }
    
    private void extractItems() {
        this.ms.reset();
        this.items = new Item[100];
        for (int i = 0; i < this.ms.getSize(); ++i) {
            this.items[i] = this.ms.getNextItem();
            this.list.add(this.items[i].getText());
        }
        this.ms.reset();
    }
    
    private void createGUI() {
        this.ok = new Button("OK");
        this.save = new Button("Save Item");
        this.edit = new Button("Edit Sublevel");
        this.remove = new Button("Remove this");
        this.add = new Button("Add new Item");
        this.name = new TextField();
        this.action = new TextField();
        this.target = new TextField();
        this.icon = new TextField();
        this.list = new List(1, false);
        this.lname = new Label("Item Text");
        this.laction = new Label("Link");
        this.ltarget = new Label("Target Window");
        this.licon = new Label("Icon image");
        this.ldepth = new Label("Level: " + this.level);
        this.linfo = new Label(this.mainhelp);
    }
    
    private void placeGUI() {
        final int n = 150;
        final int n2 = 80;
        final int n3 = 20;
        this.save.setBounds(40, 200, 120, 30);
        this.add(this.save);
        this.lname.setBounds(30, 30, n2, n3);
        this.add(this.lname);
        this.name.setBounds(120, 30, n, n3);
        this.add(this.name);
        this.laction.setBounds(30, 70, n2, n3);
        this.add(this.laction);
        this.action.setBounds(120, 70, n, n3);
        this.add(this.action);
        this.ltarget.setBounds(30, 110, n2, n3);
        this.add(this.ltarget);
        this.target.setBounds(120, 110, n, n3);
        this.add(this.target);
        this.licon.setBounds(30, 150, n2, n3);
        this.add(this.licon);
        this.icon.setBounds(120, 150, n, n3);
        this.add(this.icon);
        this.ok.setBounds(40, 280, 60, n3 + 20);
        this.add(this.ok);
        this.edit.setBounds(120, 300, 100, n3);
        this.add(this.edit);
        this.edit.setEnabled(false);
        this.add.setBounds(240, 300, 100, n3);
        this.add(this.add);
        this.add.setEnabled(true);
        this.add.addActionListener(this);
        this.remove.setBounds(360, 300, 100, n3);
        this.add(this.remove);
        this.remove.setEnabled(true);
        this.remove.addActionListener(this);
        this.list.setBounds(300, 30, 100, 200);
        this.list.addItemListener(this);
        this.ldepth.setBounds(300, 250, n2, n3);
        this.add(this.ldepth);
        this.linfo.setBounds(30, 330, 400, n3);
        this.add(this.linfo);
        this.save.addActionListener(this);
        this.ok.addActionListener(this);
        this.edit.addActionListener(this);
        this.add(this.list);
        this.list.select(0);
    }
    
    private void updateFields() {
        this.name.setEnabled(true);
        this.target.setEnabled(true);
        this.edit.setEnabled(true);
        this.action.setEnabled(true);
        this.icon.setEnabled(true);
        this.save.setEnabled(true);
        this.name.setText("");
        this.action.setText("");
        this.target.setText("");
        this.icon.setText("");
        if (this.list.getItemCount() > 0) {
            this.edit.setEnabled(true);
            this.currentItem = this.items[this.list.getSelectedIndex()];
            this.name.setText(this.currentItem.getText());
            final String urlName = this.currentItem.getURLName();
            if (urlName != null) {
                this.action.setText(urlName);
            }
            this.target.setText(this.currentItem.getTarget());
            if (this.currentItem.getIcon() != null) {
                this.icon.setText(this.currentItem.getImageName());
            }
            System.out.println("Icon:" + this.currentItem.getImageName());
        }
        else {
            this.save.setEnabled(false);
            this.name.setEnabled(false);
            this.target.setEnabled(false);
            this.edit.setEnabled(false);
            this.action.setEnabled(false);
            this.icon.setEnabled(false);
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.updateFields();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.ok) {
            this.saveAll();
            this.dispose();
        }
        else if (actionEvent.getSource() == this.edit) {
            final ItemEdit itemEdit = new ItemEdit(this.cmf, this.wp, this.items[this.list.getSelectedIndex()].getMenuSet(), this.items[this.list.getSelectedIndex()], this.level + 1);
            itemEdit.setSize(500, 400);
            itemEdit.setVisible(true);
        }
        else if (actionEvent.getSource() == this.save) {
            this.saveItem();
        }
        else if (actionEvent.getSource() == this.remove) {
            this.deleteItem();
        }
        else if (actionEvent.getSource() == this.add) {
            this.addItem();
        }
    }
    
    private void saveItem() {
        boolean b = false;
        if (this.level == 1) {
            b = true;
        }
        URL url = null;
        Image image = null;
        if (this.level > 1 && this.icon.getText().length() < 1 && this.wp.defaulticon.getText().length() > 1) {
            this.icon.setText(this.wp.defaulticon.getText());
        }
        if (this.target.getText().length() < 1 && this.wp.defaulttarget.getText().length() > 1) {
            this.target.setText(this.wp.defaulttarget.getText());
        }
        if (this.icon.getText().length() > 3) {
            image = this.cmf.cme.getImage(this.cmf.cme.getCodeBase(), this.icon.getText());
        }
        try {
            url = new URL(this.action.getText());
        }
        catch (Exception ex) {
            try {
                url = new URL(this.cmf.cme.getCodeBase(), this.action.getText());
            }
            catch (Exception ex2) {}
        }
        if (this.action.getText().length() < 1) {
            url = null;
        }
        if (!this.adding) {
            final MenuSet menuSet = this.items[this.list.getSelectedIndex()].getMenuSet();
            (this.items[this.list.getSelectedIndex()] = new Item(this.name.getText(), url, this.target.getText(), b, image)).setImageName(this.icon.getText());
            this.items[this.list.getSelectedIndex()].setURLName(this.action.getText());
            this.items[this.list.getSelectedIndex()].giveSub(menuSet);
            this.ms.replaceItem(this.currentItem.getText(), this.items[this.list.getSelectedIndex()]);
            this.list.replaceItem(this.name.getText(), this.list.getSelectedIndex());
        }
        if (this.adding) {
            (this.items[this.list.getItemCount()] = new Item(this.name.getText(), url, this.target.getText(), b, image)).setImageName(this.icon.getText());
            this.items[this.list.getItemCount()].setURLName(this.action.getText());
            this.ms.addItem(this.items[this.list.getItemCount()]);
            this.list.add(this.items[this.list.getItemCount()].getText());
        }
        this.adding = false;
        this.list.requestFocus();
        this.list.select(0);
    }
    
    private void deleteItem() {
        if (this.level > 1) {
            final int selectedIndex = this.list.getSelectedIndex();
            this.items[selectedIndex] = null;
            this.list.replaceItem("REMOVED", selectedIndex);
        }
        else {
            final int selectedIndex2 = this.list.getSelectedIndex();
            this.ms.removeItem(this.items[selectedIndex2].getText());
            this.items[selectedIndex2] = null;
            this.list.replaceItem("REMOVED", selectedIndex2);
        }
        this.list.requestFocus();
        this.list.select(0);
    }
    
    private void addItem() {
        this.name.setEnabled(true);
        this.target.setEnabled(true);
        this.edit.setEnabled(true);
        this.action.setEnabled(true);
        this.icon.setEnabled(true);
        this.save.setEnabled(true);
        ++this.itemsadded;
        this.name.setText("New Item" + this.itemsadded);
        this.target.setText("");
        this.action.setText("");
        this.icon.setText("");
        this.adding = true;
        this.list.select(this.list.getItemCount());
    }
    
    private void saveAll() {
        if (this.level > 1) {
            final MenuSet set = new MenuSet();
            for (int i = 0; i < this.list.getItemCount(); ++i) {
                if (this.items[i] != null) {
                    set.addItem(this.items[i]);
                }
                System.out.println("Newms got: " + this.items[i]);
            }
            if (set.getSize() > 0) {
                this.i.giveSub(set);
            }
            else {
                this.i.giveSub(null);
            }
            System.out.println("Item " + this.i + " got " + set);
        }
        this.dispose();
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.dispose();
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
}
