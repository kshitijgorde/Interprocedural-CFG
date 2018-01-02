import javax.swing.event.ListDataEvent;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import java.awt.Image;
import java.awt.MediaTracker;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListDataListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class SceneListList extends JPanel implements MouseListener, ListDataListener
{
    private imgViewer applet;
    private JList currDisplayedList;
    private JLabel title;
    private SceneList currSceneList;
    private ListEntryBuilder entryBuilder;
    private ListSelectionListener selectionListener;
    private SceneListMenu sceneMenu;
    private SceneListPanel sceneListPanel;
    private JPanel titlePanel;
    private String listTitle;
    private SceneList[] sceneLists;
    private boolean doubleClickAction;
    private Font cellFont;
    private ImageIcon downloadIcon;
    private DecimalFormat digitFormat;
    private boolean seenOrderNotice;
    
    SceneListList(final imgViewer applet, final ListDataListener listDataListener, final ListSelectionListener selectionListener, final Font font, final int n, final ListEntryBuilder entryBuilder, final boolean b, final boolean b2, final String listTitle, final SceneList[] sceneLists, final boolean doubleClickAction) {
        this.applet = applet;
        this.entryBuilder = entryBuilder;
        this.selectionListener = selectionListener;
        this.sceneLists = sceneLists;
        this.doubleClickAction = doubleClickAction;
        this.cellFont = font;
        this.seenOrderNotice = false;
        this.digitFormat = new DecimalFormat("#0.0");
        this.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        this.listTitle = listTitle;
        this.titlePanel = new JPanel();
        (this.title = new JLabel("Scene List")).setFont(applet.boldFont);
        this.titlePanel.add(this.title);
        this.add(this.titlePanel, gridBagConstraints);
        final Sensor[] sensors = applet.getSensors();
        (this.currDisplayedList = new JList()).setToolTipText("Scene list");
        this.currDisplayedList.setFont(font);
        this.currDisplayedList.setSelectionMode(0);
        this.currDisplayedList.setCellRenderer(new SceneCellRenderer());
        this.currDisplayedList.setFixedCellHeight(15);
        final JScrollPane scrollPane = new JScrollPane(this.currDisplayedList);
        this.currDisplayedList.setVisibleRowCount(4);
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.weighty = 100.0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        this.add(scrollPane, gridBagConstraints);
        for (int i = 0; i < sensors.length; ++i) {
            sceneLists[i].addListDataListener(this);
            sceneLists[i].addListDataListener(listDataListener);
        }
        if (selectionListener != null) {
            this.currDisplayedList.addListSelectionListener(selectionListener);
        }
        this.currDisplayedList.addMouseListener(this);
        this.add(this.sceneMenu = new SceneListMenu(applet, applet.md, b, b2));
        final Image image = applet.getImage(applet.getCodeBase(), "graphics/download_extra_small.gif");
        final MediaTracker mediaTracker = new MediaTracker(applet);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
            this.downloadIcon = new ImageIcon(image);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.setSensor(applet.sensorMenu.getCurrentSensor());
    }
    
    public void setSensor(final Sensor sensor) {
        final Sensor[] sensors = this.applet.getSensors();
        for (int i = 0; i < sensors.length; ++i) {
            if (sensors[i] == sensor) {
                String s = this.listTitle;
                if (sensor.sensorName.length() > 17) {
                    final int index = this.listTitle.indexOf(32);
                    if (index > 0) {
                        s = this.listTitle.substring(index + 1);
                    }
                }
                this.title.setText(sensor.sensorName + " " + s);
                final int sceneCount = this.sceneLists[i].getSceneCount();
                this.currDisplayedList.setModel(this.sceneLists[i].getModel());
                if (sceneCount > 0) {
                    this.currDisplayedList.setSelectedIndex(sceneCount - 1);
                }
                this.titlePanel.doLayout();
                this.currSceneList = this.sceneLists[i];
                break;
            }
        }
        this.setSceneCountDisplay();
    }
    
    public int getSceneCount() {
        return this.currSceneList.getSceneCount();
    }
    
    public Metadata getSelectedScene() {
        final int selectedIndex = this.currDisplayedList.getSelectedIndex();
        if (selectedIndex < 0) {
            return null;
        }
        if (selectedIndex < this.getSceneCount()) {
            return this.currSceneList.getSceneAt(selectedIndex);
        }
        return null;
    }
    
    public void addActiveScene() {
        final Metadata currentScene = this.applet.imgArea.md.getCurrentScene();
        if (currentScene != null) {
            this.add(currentScene);
        }
    }
    
    public void showBrowse(final boolean b) {
        final int selectedIndex = this.currDisplayedList.getSelectedIndex();
        if (selectedIndex < 0) {
            return;
        }
        final Metadata scene = this.currSceneList.getSceneAt(selectedIndex);
        if (b) {
            scene.showBrowse();
        }
        else {
            scene.showMetadata();
        }
    }
    
    public void add(final Metadata metadata) {
        this.currSceneList.add(metadata);
        final int find = this.currSceneList.find(metadata);
        this.currDisplayedList.setSelectedIndex(find);
        this.currDisplayedList.ensureIndexIsVisible(find);
    }
    
    public void remove() {
        if (this.getSceneCount() <= 0) {
            return;
        }
        this.currSceneList.remove(this.currDisplayedList.getSelectedValue());
    }
    
    public void order() {
        boolean b = true;
        if (this.applet.sensorMenu.getCurrentSensor().isDownloadable && this.applet.sensorMenu.getCurrentSensor().isOrderable && !this.seenOrderNotice) {
            if (JOptionPane.showOptionDialog(this.applet.getDialogContainer(), new String[] { "Scenes in your Scene List may be added to the", "Orders area of your Shopping Cart, the Downloads", "area, or both, depending on the scene's status." }, "Notice", -1, 1, null, null, null) == -1) {
                b = false;
            }
            else {
                this.seenOrderNotice = true;
            }
        }
        if (b) {
            this.currSceneList.order();
        }
    }
    
    public void download() {
        final int selectedIndex = this.currDisplayedList.getSelectedIndex();
        if (selectedIndex < 0) {
            return;
        }
        final Metadata scene = this.currSceneList.getSceneAt(selectedIndex);
        final double n = scene.downloadFileSize / 53248;
        final double n2 = scene.downloadFileSize / 5120;
        final double n3 = scene.downloadFileSize / 1024.0 / 1024.0;
        final String formatDownloadTime = this.formatDownloadTime(n);
        final String formatDownloadTime2 = this.formatDownloadTime(n2);
        String[] array;
        int n4;
        if (scene.getSensor().slowDownloadStart) {
            array = new String[] { null, null, null, null, null, null, null, "Note: Download may be slow to start.", " ", null, null };
            n4 = 9;
        }
        else {
            array = new String[9];
            n4 = 7;
        }
        array[0] = "Scene: " + scene.entityID;
        array[1] = "<html>Product size: <font color='red'>" + this.digitFormat.format(n3) + " MB </font></html>";
        array[2] = "File format: " + scene.getSensor().downloadFileFormat;
        array[3] = "Estimated download time: ";
        array[4] = "<html><font color='red'>" + formatDownloadTime + " @ 512 Kbps</font></html>";
        array[5] = "<html><font color='red'>" + formatDownloadTime2 + " @ 56 Kbps</font></html>";
        array[6] = " ";
        array[n4] = "Do you want to continue?";
        array[n4 + 1] = " ";
        if (JOptionPane.showConfirmDialog(this.applet.getDialogContainer(), array, "Confirm Download At No Charge", 0, 2) == 0) {
            this.currSceneList.download(scene);
            this.currSceneList.remove(scene);
            this.applet.imgArea.repaint();
        }
    }
    
    private String formatDownloadTime(final double n) {
        String s;
        if (n >= 60.0) {
            if (n / 60.0 >= 60.0) {
                s = this.digitFormat.format(n / 60.0 / 60.0) + " Hours";
            }
            else {
                s = this.digitFormat.format(n / 60.0) + " Minutes";
            }
        }
        else {
            s = this.digitFormat.format(n) + " Seconds";
        }
        return s;
    }
    
    public void restore() {
        this.currSceneList.restore();
    }
    
    public boolean isRestoreEnabled() {
        return this.currSceneList.isRestoreEnabled();
    }
    
    public void showSelectedScene() {
        this.applet.md.showScene(this.currSceneList.getSceneAt(this.currDisplayedList.getSelectedIndex()));
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            this.showSelectedScene();
        }
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0xC) != 0x0) {
            this.sceneMenu.configureMenu(this, this.getSelectedScene());
            this.sceneMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    private void setSceneCountDisplay() {
        final int sceneCount = this.getSceneCount();
        final StringBuffer sb = new StringBuffer("Scene list");
        if (sceneCount > 0) {
            sb.append(" with ");
            sb.append(sceneCount);
            sb.append(" scene");
            if (sceneCount > 1) {
                sb.append("s");
            }
        }
        this.currDisplayedList.setToolTipText(sb.toString());
    }
    
    @Override
    public void intervalAdded(final ListDataEvent listDataEvent) {
        final int index0 = listDataEvent.getIndex0();
        this.currDisplayedList.setSelectedIndex(index0);
        this.currDisplayedList.ensureIndexIsVisible(index0);
        this.setSceneCountDisplay();
    }
    
    @Override
    public void intervalRemoved(final ListDataEvent listDataEvent) {
        int index0 = listDataEvent.getIndex0();
        if (index0 > this.getSceneCount() - 1) {
            index0 = this.getSceneCount() - 1;
        }
        this.currDisplayedList.setSelectedIndex(index0);
        this.currDisplayedList.ensureIndexIsVisible(index0);
        this.setSceneCountDisplay();
    }
    
    @Override
    public void contentsChanged(final ListDataEvent listDataEvent) {
        final int index0 = listDataEvent.getIndex0();
        this.currDisplayedList.setSelectedIndex(index0);
        this.currDisplayedList.ensureIndexIsVisible(index0);
        this.setSceneCountDisplay();
    }
    
    class SceneCellRenderer extends JLabel implements ListCellRenderer
    {
        @Override
        public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
            final Metadata metadata = (Metadata)o;
            final String entry = SceneListList.this.entryBuilder.getEntry(metadata);
            this.setIcon(null);
            if (metadata.getSensor().mightBeDownloadable && metadata.isDownloadable) {
                this.setIcon(SceneListList.this.downloadIcon);
            }
            this.setText(entry);
            this.setHorizontalTextPosition(10);
            this.setOpaque(true);
            this.setFont(SceneListList.this.cellFont);
            this.setBackground(b ? list.getSelectionBackground() : list.getBackground());
            this.setForeground(b ? list.getSelectionForeground() : list.getForeground());
            return this;
        }
    }
}
