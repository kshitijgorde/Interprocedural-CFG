import java.awt.Point;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Label;
import java.awt.CheckboxGroup;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.ImageObserver;
import java.awt.Frame;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class MapWindow extends Dialog
{
    int iWidth;
    int iHeight;
    TextField lat1;
    TextField long1;
    TextField lat2;
    TextField long2;
    commonData data;
    MapImage map;
    MapProjection mp;
    TextField parentLat1;
    TextField parentLong1;
    TextField parentLat2;
    TextField parentLong2;
    wgCheckbox from;
    wgCheckbox to;
    
    public MapWindow(final Image[] array) {
        super(new Frame(), true);
        this.initiallizeMapWindow(array);
    }
    
    public MapWindow(final Image[] array, final TextField parentLat1, final TextField parentLong1, final TextField parentLat2, final TextField parentLong2) {
        super(new Frame(), true);
        this.initiallizeMapWindow(array);
        this.parentLat1 = parentLat1;
        this.parentLong1 = parentLong1;
        this.parentLat2 = parentLat2;
        this.parentLong2 = parentLong2;
    }
    
    void initiallizeMapWindow(final Image[] array) {
        this.addNotify();
        if (array[0] != null) {
            this.iWidth = array[0].getWidth(this.getParent());
            this.iHeight = array[0].getHeight(this.getParent());
        }
        final int n = this.iWidth + 50;
        final int n2 = this.iHeight + 144;
        this.resize(n, n2);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setFont(new Font("Helvetica", 1, 12));
        this.setLayout(layout);
        this.setBackground(Color.lightGray);
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(3, 10, 3, 10);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 10;
        layout.setConstraints(this.map = new MapImage(this, array, this.iWidth, this.iHeight, this.mp), gridBagConstraints);
        this.add(this.map);
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.from = new wgCheckbox("From", checkboxGroup, true);
        this.to = new wgCheckbox("To  ", checkboxGroup, false);
        gridBagConstraints.insets = new Insets(0, 10, 3, 0);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 10;
        layout.setConstraints(this.from, gridBagConstraints);
        this.add(this.from);
        final Label label = new Label("N.Latitude:");
        gridBagConstraints.insets = new Insets(0, 0, 3, 0);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 13;
        layout.setConstraints(label, gridBagConstraints);
        this.add(label);
        this.lat1 = new TextField("", 15);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 13;
        layout.setConstraints(this.lat1, gridBagConstraints);
        this.add(this.lat1);
        final Label label2 = new Label("W.Longitude:");
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 13;
        layout.setConstraints(label2, gridBagConstraints);
        this.add(label2);
        gridBagConstraints.insets = new Insets(0, 0, 3, 10);
        this.long1 = new TextField("", 15);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 13;
        layout.setConstraints(this.long1, gridBagConstraints);
        this.add(this.long1);
        gridBagConstraints.insets = new Insets(0, 10, 3, 0);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 10;
        layout.setConstraints(this.from, gridBagConstraints);
        this.add(this.to);
        this.lat2 = new TextField("", 15);
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = 13;
        layout.setConstraints(this.lat2, gridBagConstraints);
        this.add(this.lat2);
        gridBagConstraints.insets = new Insets(0, 0, 3, 10);
        this.long2 = new TextField("", 15);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 13;
        layout.setConstraints(this.long2, gridBagConstraints);
        this.add(this.long2);
        final Panel panel = new Panel();
        panel.add(new Button("OK"));
        panel.add(new Button("Cancel"));
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 10;
        layout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        this.lat1.setEditable(false);
        this.long1.setEditable(false);
        this.lat2.setEditable(false);
        this.long2.setEditable(false);
        this.lat1.setBackground(Color.white);
        this.long1.setBackground(Color.white);
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.move((screenSize.width - n) / 2 + 50, (screenSize.height - n2) / 2 + 50);
        this.setTitle("Pick your point(s)");
    }
    
    public boolean action(final Event event, final Object o) {
        if (o.equals("Cancel")) {
            this.hide();
            commonData.fromPicked = false;
            commonData.toPicked = false;
            return true;
        }
        if (o.equals("OK") && (commonData.fromPicked || commonData.toPicked)) {
            this.hide();
            if (commonData.fromPicked) {
                if (this.parentLat1 != null) {
                    this.parentLat1.setText(dms.decdeg2dms(commonData.latitude1, 0, false));
                }
                if (this.parentLong1 != null) {
                    this.parentLong1.setText(dms.decdeg2dms(commonData.longitude1, 0, false));
                }
            }
            if (commonData.toPicked) {
                if (this.parentLat2 != null) {
                    this.parentLat2.setText(dms.decdeg2dms(commonData.latitude2, 0, false));
                }
                if (this.parentLong1 != null) {
                    this.parentLong2.setText(dms.decdeg2dms(commonData.longitude2, 0, false));
                }
            }
            return true;
        }
        return false;
    }
    
    void repaintFields() {
        if (this.from.getState()) {
            this.lat2.setBackground(Color.lightGray);
            this.long2.setBackground(Color.lightGray);
            this.lat1.setBackground(Color.white);
            this.long1.setBackground(Color.white);
        }
        else {
            this.lat1.setBackground(Color.lightGray);
            this.long1.setBackground(Color.lightGray);
            this.lat2.setBackground(Color.white);
            this.long2.setBackground(Color.white);
        }
        this.lat1.setText(this.lat1.getText());
        this.lat2.setText(this.lat2.getText());
        this.long1.setText(this.long1.getText());
        this.long2.setText(this.long2.getText());
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201 || (event.id == 401 && event.key == 27)) {
            this.hide();
            commonData.fromPicked = false;
            commonData.toPicked = false;
            return true;
        }
        if (event.id == 1) {
            this.repaintFields();
        }
        if (event.id == 0) {
            if (this.from.getState()) {
                final geoPos inverse = this.mp.inverse((Point)event.arg);
                commonData.latitude1 = inverse.latitude;
                commonData.longitude1 = inverse.longitude;
                this.lat1.setText(dms.decdeg2dms(commonData.latitude1, 0));
                this.long1.setText(dms.decdeg2dms(commonData.longitude1, 0));
                if (this.parentLat1 != null) {
                    this.parentLat1.setText(dms.decdeg2dms(commonData.latitude1, 0, false));
                }
                if (this.parentLong1 != null) {
                    this.parentLong1.setText(dms.decdeg2dms(commonData.longitude1, 0, false));
                }
                commonData.fromPicked = true;
            }
            else if (this.to.getState()) {
                final geoPos inverse2 = this.mp.inverse((Point)event.arg);
                commonData.latitude2 = inverse2.latitude;
                commonData.longitude2 = inverse2.longitude;
                this.lat2.setText(dms.decdeg2dms(commonData.latitude2, 0));
                this.long2.setText(dms.decdeg2dms(commonData.longitude2, 0));
                if (this.parentLat2 != null) {
                    this.parentLat2.setText(dms.decdeg2dms(commonData.latitude2, 0, false));
                }
                if (this.parentLong2 != null) {
                    this.parentLong2.setText(dms.decdeg2dms(commonData.longitude2, 0, false));
                }
                commonData.toPicked = true;
            }
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void show(final MapProjection mp) {
        this.lat1.setText("");
        this.long1.setText("");
        this.lat2.setText("");
        this.long2.setText("");
        commonData.fromPicked = false;
        commonData.toPicked = false;
        this.from.setState(true);
        this.to.setState(false);
        this.repaintFields();
        this.mp = mp;
        super.show();
        this.map.requestFocus();
    }
    
    public void show(final MapProjection mp, final double latitude2, final double longitude2) {
        this.lat1.setText("");
        this.long1.setText("");
        this.lat2.setText(dms.decdeg2dms(latitude2, 3));
        this.long2.setText(dms.decdeg2dms(longitude2, 3));
        commonData.latitude2 = latitude2;
        commonData.longitude2 = longitude2;
        commonData.fromPicked = false;
        commonData.toPicked = false;
        this.mp = mp;
        this.map.doPin(this.mp.forward(new geoPos(latitude2, longitude2)));
        this.from.setState(true);
        this.to.setState(false);
        this.repaintFields();
        super.show();
        this.map.requestFocus();
    }
}
