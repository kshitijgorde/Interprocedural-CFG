import java.awt.event.ItemEvent;
import javax.swing.JOptionPane;
import java.awt.Component;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBoxMenuItem;
import java.io.File;
import javax.swing.JMenuItem;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.JMenu;

// 
// Decompiled by Procyon v0.5.30
// 

public class MapLayerMenu extends JMenu implements ItemListener, ActionListener
{
    private imgViewer applet;
    private ArrayList checkboxList;
    private MapLayers mapLayers;
    private JMenuItem showAttributesMenuItem;
    private File currentDirectory;
    
    public MapLayerMenu(final MapLayers mapLayers, final imgViewer applet) {
        super("Map Layers");
        this.setMnemonic(77);
        this.applet = applet;
        final JMenuItem menuItem = new JMenuItem("All Map Layers", 65);
        menuItem.addActionListener(this);
        this.add(menuItem);
        this.mapLayers = mapLayers;
        final int numberOfLayers = mapLayers.getNumberOfLayers();
        this.checkboxList = new ArrayList();
        for (int i = 0; i < numberOfLayers; ++i) {
            final MapLayer layer = mapLayers.getLayerAt(i);
            final JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem(layer.getName(), layer.isLayerOn());
            if (!layer.isHidden()) {
                final int menuShortcut = layer.getMenuShortcut();
                if (menuShortcut != 0) {
                    checkBoxMenuItem.setMnemonic(menuShortcut);
                }
                checkBoxMenuItem.addItemListener(this);
            }
            else {
                checkBoxMenuItem.setVisible(false);
            }
            this.add(checkBoxMenuItem);
            this.checkboxList.add(checkBoxMenuItem);
        }
        final JMenuItem menuItem2 = new JMenuItem("No Map Layers", 78);
        menuItem2.addActionListener(this);
        this.add(menuItem2);
        this.addSeparator();
        final JMenuItem menuItem3 = new JMenuItem("Modify Colors...", 77);
        menuItem3.addActionListener(this);
        this.add(menuItem3);
        if (!mapLayers.getAddressSearchMapLayer().isHidden()) {
            final JMenuItem menuItem4 = new JMenuItem("Search for Address...", 69);
            menuItem4.addActionListener(this);
            this.add(menuItem4);
        }
        final JMenuItem menuItem5 = new JMenuItem("Set Point of Interest...", 79);
        menuItem5.addActionListener(this);
        this.add(menuItem5);
        final JMenuItem menuItem6 = new JMenuItem("Configure Scene List Overlay...", 70);
        menuItem6.addActionListener(this);
        this.add(menuItem6);
        if (applet.grantedPrivileges) {
            final JMenuItem menuItem7 = new JMenuItem("Read Shapefile...", 83);
            menuItem7.addActionListener(this);
            this.add(menuItem7);
            (this.showAttributesMenuItem = new JMenuItem("Show Shapefile Attributes...", 72)).addActionListener(this);
            this.showAttributesMenuItem.setEnabled(false);
            this.add(this.showAttributesMenuItem);
        }
        this.configureForSensor(applet.sensorMenu.getCurrentSensor());
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final int numberOfStandardLayers = this.mapLayers.getNumberOfStandardLayers();
        final int numberOfLayers = this.mapLayers.getNumberOfLayers();
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("All Map Layers")) {
            boolean b = false;
            if (this.applet.sensorMenu.getCurrentSensor().getLowestResolution() > 2000.0) {
                b = true;
            }
            for (int i = 0; i < numberOfStandardLayers; ++i) {
                final MapLayer layer = this.mapLayers.getLayerAt(i);
                if (layer.isEnabled() && (!b || layer.isLowResAvailable())) {
                    ((JCheckBoxMenuItem)this.checkboxList.get(i)).setState(true);
                    layer.setLayerOn(true);
                }
            }
            this.mapLayers.showLayers();
        }
        else if (actionCommand.equals("No Map Layers")) {
            for (int j = 0; j < numberOfStandardLayers; ++j) {
                ((JCheckBoxMenuItem)this.checkboxList.get(j)).setState(false);
                this.mapLayers.getLayerAt(j).setLayerOn(false);
            }
            boolean b2 = true;
            for (int k = numberOfStandardLayers; k < numberOfLayers; ++k) {
                if (this.mapLayers.getLayerAt(k).isLayerOn()) {
                    b2 = false;
                    break;
                }
            }
            if (b2) {
                this.mapLayers.clearLayers();
            }
            else {
                this.mapLayers.showLayers();
            }
        }
        else if (actionCommand.equals("Modify Colors...")) {
            this.mapLayers.showModifyLayerColors();
        }
        else if (actionCommand.equals("Search for Address...")) {
            final Point dialogLoc;
            final Point location = dialogLoc = this.applet.getDialogLoc();
            dialogLoc.y += 30;
            this.applet.searchForAddressDialog.setLocation(location);
            this.applet.searchForAddressDialog.setVisible(true);
        }
        else if (actionCommand.equals("Set Point of Interest...")) {
            final LatLong pointOfInterestLatLong = this.mapLayers.getPointOfInterestMapLayer().getPointOfInterestLatLong();
            if (pointOfInterestLatLong != null) {
                this.applet.pointOfInterestDialog.setLatLong(pointOfInterestLatLong);
            }
            final Point dialogLoc2;
            final Point location2 = dialogLoc2 = this.applet.getDialogLoc();
            dialogLoc2.y += 30;
            this.applet.pointOfInterestDialog.setLocation(location2);
            this.applet.pointOfInterestDialog.setVisible(true);
        }
        else if (actionCommand.equals("Configure Scene List Overlay...")) {
            this.mapLayers.showSceneOverlayConfiguration();
        }
        else if (actionCommand.equals("Read Shapefile...")) {
            this.readShapeFile();
        }
        else if (actionCommand.equals("Show Shapefile Attributes...")) {
            this.mapLayers.showAttributes();
        }
    }
    
    private void readShapeFile() {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(0);
        fileChooser.setDialogTitle("Select shapefile name");
        fileChooser.setFileFilter(new ShapeFileFilter());
        if (this.currentDirectory != null) {
            fileChooser.setCurrentDirectory(this.currentDirectory);
        }
        if (fileChooser.showOpenDialog(this.applet.getDialogContainer()) == 0) {
            final int numberOfStandardLayers = this.mapLayers.getNumberOfStandardLayers();
            final int numberOfLayers = this.mapLayers.getNumberOfLayers();
            this.currentDirectory = fileChooser.getCurrentDirectory();
            final File selectedFile = fileChooser.getSelectedFile();
            final String name = selectedFile.getName();
            final String substring = name.substring(0, name.lastIndexOf(46));
            boolean b = false;
            for (int i = numberOfStandardLayers; i < numberOfLayers; ++i) {
                if (this.mapLayers.getLayerAt(i).getName().equals(substring)) {
                    b = true;
                    break;
                }
            }
            if (b) {
                JOptionPane.showMessageDialog(this.applet.getDialogContainer(), new String[] { "The map layer " + substring + "has already been loaded" }, "ShapeFile not loaded", 2);
            }
            else {
                boolean b2 = true;
                if (ShapeFileMapLayer.isValidShapeFile(selectedFile)) {
                    final ShapeFileMapLayer shapeFileMapLayer = new ShapeFileMapLayer(this.applet, substring, "Shapefile", this.mapLayers.getAttributesDialog(), selectedFile);
                    if (shapeFileMapLayer.isValid()) {
                        if (numberOfLayers == numberOfStandardLayers) {
                            this.addSeparator();
                        }
                        this.mapLayers.addMapLayer(shapeFileMapLayer);
                        final MapLayer layer = this.mapLayers.getLayerAt(this.mapLayers.getNumberOfLayers() - 1);
                        final JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem(layer.getName(), layer.isLayerOn());
                        checkBoxMenuItem.addItemListener(this);
                        this.add(checkBoxMenuItem);
                        this.checkboxList.add(checkBoxMenuItem);
                        this.configureForSensor(this.applet.sensorMenu.getCurrentSensor());
                        this.setLayerState(layer.getName(), true, true);
                        this.mapLayers.showLayers();
                        this.showAttributesMenuItem.setEnabled(true);
                        b2 = false;
                    }
                }
                if (b2) {
                    JOptionPane.showMessageDialog(this.applet.getDialogContainer(), new String[] { substring + ".dbf Does not appear " + "to be a supported shapefile.", "Verify the .dbf, .shp, and .shx are readable and  that the .prj, ", "if it exists, indicates the file is in geographic coordinates." }, "ShapeFile not loaded", 0);
                }
            }
        }
    }
    
    public void setLayerState(final String s, final boolean selected, final boolean enabled) {
        for (int numberOfLayers = this.mapLayers.getNumberOfLayers(), i = 0; i < numberOfLayers; ++i) {
            if (s.equals(this.mapLayers.getLayerAt(i).getName())) {
                final JCheckBoxMenuItem checkBoxMenuItem = this.checkboxList.get(i);
                checkBoxMenuItem.setSelected(selected);
                checkBoxMenuItem.setEnabled(enabled);
                break;
            }
        }
    }
    
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        boolean b = false;
        final int numberOfLayers = this.mapLayers.getNumberOfLayers();
        final String actionCommand = ((JCheckBoxMenuItem)itemEvent.getSource()).getActionCommand();
        for (int i = 0; i < numberOfLayers; ++i) {
            final MapLayer layer = this.mapLayers.getLayerAt(i);
            if (actionCommand.equals(layer.getName())) {
                layer.setLayerOn(this.checkboxList.get(i).getState());
                b = true;
                break;
            }
        }
        if (!b) {
            return;
        }
        boolean b2 = false;
        for (int j = 0; j < numberOfLayers; ++j) {
            if (((JCheckBoxMenuItem)this.checkboxList.get(j)).getState()) {
                b2 = true;
                break;
            }
        }
        if (b2) {
            this.mapLayers.showLayers();
        }
        else {
            this.mapLayers.clearLayers();
        }
    }
    
    void configureForSensor(final Sensor sensor) {
        boolean b = false;
        if (sensor.getLowestResolution() > 2000.0) {
            b = true;
        }
        boolean layersOn = false;
        for (int numberOfLayers = this.mapLayers.getNumberOfLayers(), i = 0; i < numberOfLayers; ++i) {
            final MapLayer layer = this.mapLayers.getLayerAt(i);
            final boolean enabled = layer.isEnabled();
            final JCheckBoxMenuItem checkBoxMenuItem = this.checkboxList.get(i);
            if (enabled && (!b || layer.isLowResAvailable())) {
                checkBoxMenuItem.setEnabled(true);
                if (checkBoxMenuItem.getState()) {
                    layer.setLayerOn(true);
                    layersOn = true;
                }
            }
            else {
                checkBoxMenuItem.setEnabled(false);
                checkBoxMenuItem.setState(false);
                layer.setLayerOn(false);
            }
        }
        this.mapLayers.layersOn = layersOn;
    }
    
    class ShapeFileFilter extends FileFilter
    {
        @Override
        public boolean accept(final File file) {
            if (file.isDirectory()) {
                return true;
            }
            final String name = file.getName();
            final int lastIndex = name.lastIndexOf(46);
            return lastIndex != -1 && name.substring(lastIndex).equals(".dbf");
        }
        
        @Override
        public String getDescription() {
            return "ShapeFiles";
        }
    }
}
