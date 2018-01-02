import javax.swing.event.ListDataEvent;
import java.awt.event.ActionEvent;
import java.util.Observable;
import javax.swing.event.ListDataListener;
import javax.swing.JMenuItem;
import java.util.Observer;
import java.awt.event.ActionListener;
import javax.swing.JMenu;

// 
// Decompiled by Procyon v0.5.30
// 

public class FileMenu extends JMenu implements ActionListener, Observer
{
    private imgViewer applet;
    private JMenuItem downloadCellData;
    private JMenuItem downloadSceneListData;
    
    public FileMenu(final imgViewer applet) {
        super("File");
        this.setMnemonic(70);
        this.applet = applet;
        final JMenuItem menuItem = new JMenuItem("Save All Scene Lists...", 83);
        menuItem.addActionListener(this);
        menuItem.setEnabled(false);
        this.add(menuItem);
        final JMenuItem menuItem2 = new JMenuItem("Load Saved Scene List...", 76);
        menuItem2.addActionListener(this);
        this.add(menuItem2);
        final JMenuItem menuItem3 = new JMenuItem("Save Hidden Scene Lists...", 72);
        menuItem3.addActionListener(this);
        menuItem3.setEnabled(false);
        this.add(menuItem3);
        final JMenuItem menuItem4 = new JMenuItem("Load Hidden Scene List...", 79);
        menuItem4.addActionListener(this);
        this.add(menuItem4);
        this.addSeparator();
        final JMenuItem menuItem5 = new JMenuItem("Download Visible Browse & Metadata...", 86);
        menuItem5.addActionListener(this);
        this.add(menuItem5);
        (this.downloadCellData = new JMenuItem("Download Cell Browse & Metadata...", 68)).addActionListener(this);
        this.add(this.downloadCellData);
        (this.downloadSceneListData = new JMenuItem("Download Scene List Browse & Metadata...", 66)).addActionListener(this);
        this.downloadSceneListData.setEnabled(false);
        this.add(this.downloadSceneListData);
        final SceneListListener sceneListListener = new SceneListListener(menuItem, false, false);
        final SceneListListener sceneListListener2 = new SceneListListener(menuItem3, true, false);
        final SceneListListener sceneListListener3 = new SceneListListener(this.downloadSceneListData, false, true);
        final Sensor[] sensors = applet.getSensors();
        for (int i = 0; i < sensors.length; ++i) {
            final Sensor sensor = sensors[i];
            sensor.sceneList.addListDataListener(sceneListListener);
            sensor.hiddenSceneList.addListDataListener(sceneListListener2);
            sensor.sceneList.addListDataListener(sceneListListener3);
        }
    }
    
    @Override
    public void update(final Observable observable, final Object o) {
        if (observable == this.applet.md) {
            final Sensor currentSensor = this.applet.sensorMenu.getCurrentSensor();
            final NavigationModel navModel = currentSensor.navModel;
            final int gridCol = this.applet.md.gridCol;
            final int gridRow = this.applet.md.gridRow;
            final int numCellsAtResolution = currentSensor.getNumCellsAtResolution(this.applet.md.pixelSize);
            String string;
            if (currentSensor.hideGridEntry) {
                string = "Download Center Cell Browse & Metadata...";
            }
            else {
                string = "Download " + navModel.getColName() + " " + navModel.getColumnString(gridCol) + ", " + navModel.getRowName() + " " + navModel.getRowString(gridRow) + " Browse & Metadata...";
            }
            this.downloadCellData.setText(string);
            this.downloadCellData.setEnabled(numCellsAtResolution != 0);
            this.downloadSceneListData.setEnabled(currentSensor.sceneList.getSceneCount() > 0);
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Save All Scene Lists...")) {
            SaveOrLoadSceneLists.save(this.applet, false);
        }
        else if (actionCommand.equals("Load Saved Scene List...")) {
            SaveOrLoadSceneLists.load(this.applet, false);
        }
        else if (actionCommand.equals("Save Hidden Scene Lists...")) {
            SaveOrLoadSceneLists.save(this.applet, true);
        }
        else if (actionCommand.equals("Load Hidden Scene List...")) {
            SaveOrLoadSceneLists.load(this.applet, true);
        }
        else if (actionCommand.equals("Download Visible Browse & Metadata...")) {
            DownloadData.downloadData(this.applet, false, false);
        }
        else if (actionCommand.equals("Download Scene List Browse & Metadata...")) {
            DownloadData.downloadData(this.applet, false, true);
        }
        else if (actionCommand.equals(this.downloadCellData.getText())) {
            DownloadData.downloadData(this.applet, true, false);
        }
    }
    
    private class SceneListListener implements ListDataListener
    {
        private JMenuItem menuItem;
        private boolean hiddenList;
        private boolean onlyCheckCurrentSensor;
        
        SceneListListener(final JMenuItem menuItem, final boolean hiddenList, final boolean onlyCheckCurrentSensor) {
            this.menuItem = menuItem;
            this.hiddenList = hiddenList;
            this.onlyCheckCurrentSensor = onlyCheckCurrentSensor;
        }
        
        @Override
        public void intervalAdded(final ListDataEvent listDataEvent) {
            if (!this.onlyCheckCurrentSensor) {
                this.menuItem.setEnabled(true);
            }
            else if (FileMenu.this.applet.sensorMenu.getCurrentSensor().sceneList.getSceneCount() > 0) {
                this.menuItem.setEnabled(true);
            }
        }
        
        @Override
        public void intervalRemoved(final ListDataEvent listDataEvent) {
            boolean b = false;
            if (!this.onlyCheckCurrentSensor) {
                final Sensor[] sensors = FileMenu.this.applet.getSensors();
                for (int i = 0; i < sensors.length; ++i) {
                    final Sensor sensor = sensors[i];
                    SceneList list;
                    if (this.hiddenList) {
                        list = sensor.hiddenSceneList;
                    }
                    else {
                        list = sensor.sceneList;
                    }
                    if (list.getSceneCount() > 0) {
                        b = true;
                        break;
                    }
                }
            }
            else if (FileMenu.this.applet.sensorMenu.getCurrentSensor().sceneList.getSceneCount() > 0) {
                b = true;
            }
            if (!b) {
                this.menuItem.setEnabled(false);
            }
        }
        
        @Override
        public void contentsChanged(final ListDataEvent listDataEvent) {
        }
    }
}
