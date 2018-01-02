import java.awt.event.ActionEvent;
import javax.swing.MenuElement;
import java.util.Enumeration;
import java.awt.Component;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.util.Vector;
import java.awt.event.ActionListener;
import javax.swing.JPopupMenu;

// 
// Decompiled by Procyon v0.5.30
// 

public class SceneMenu extends JPopupMenu implements ActionListener
{
    private imgViewer applet;
    private MosaicData md;
    private Metadata scene;
    private Vector intersectingScenes;
    private JMenuItem sceneID;
    private JMenuItem sceneInfo;
    private JMenuItem raise;
    private JMenuItem lower;
    private JMenuItem setScenesToDate;
    private JMenuItem setDefaultScene;
    private JMenuItem prevScene;
    private JMenuItem nextScene;
    private JMenuItem addAllScenes;
    private JMenuItem addSwathScenes;
    private JMenuItem hideSwathScenes;
    private JMenuItem addUserScenes;
    private JMenuItem removeScene;
    private JMenuItem hideScene;
    private boolean nextPrevPresent;
    private boolean setScenesToDatePresent;
    private boolean setDefaultScenePresent;
    private boolean allScenesPresent;
    private boolean swathScenesPresent;
    private boolean addUserScenesPresent;
    JMenu selectByScene;
    String setScenesToDateTitle;
    
    public SceneMenu(final imgViewer applet, final MosaicData md) {
        super("Scene Menu");
        this.selectByScene = null;
        this.applet = applet;
        this.md = md;
        (this.sceneID = new JMenuItem("Scene ID")).setFont(applet.normalFont);
        this.sceneID.addActionListener(this);
        this.add(this.sceneID);
        (this.sceneInfo = new JMenuItem("Scene Info")).setFont(applet.normalFont);
        this.sceneInfo.addActionListener(this);
        this.add(this.sceneInfo);
        this.addSeparator();
        final JMenuItem menuItem = new JMenuItem("Show Metadata");
        menuItem.setFont(applet.normalFont);
        menuItem.addActionListener(this);
        this.add(menuItem);
        final JMenuItem menuItem2 = new JMenuItem("Show Browse");
        menuItem2.setFont(applet.normalFont);
        menuItem2.addActionListener(this);
        this.add(menuItem2);
        final JMenuItem menuItem3 = new JMenuItem("Add To Scene List");
        menuItem3.setFont(applet.normalFont);
        menuItem3.addActionListener(this);
        this.add(menuItem3);
        (this.removeScene = new JMenuItem("Remove From Scene List")).setFont(applet.normalFont);
        this.removeScene.addActionListener(this);
        this.add(this.removeScene);
        (this.hideScene = new JMenuItem("Hide Scene")).setFont(applet.normalFont);
        this.hideScene.addActionListener(this);
        this.add(this.hideScene);
        (this.raise = new JMenuItem("Bring To Front")).setFont(applet.normalFont);
        this.raise.addActionListener(this);
        this.add(this.raise);
        (this.lower = new JMenuItem("Send To Back")).setFont(applet.normalFont);
        this.lower.addActionListener(this);
        this.add(this.lower);
        final JMenuItem menuItem4 = new JMenuItem("Set Point Of Interest");
        menuItem4.setFont(applet.normalFont);
        menuItem4.addActionListener(this);
        this.add(menuItem4);
        (this.setScenesToDate = new JMenuItem("Set Scenes To")).setFont(applet.normalFont);
        this.setScenesToDate.addActionListener(this);
        (this.setDefaultScene = new JMenuItem("Default Scene")).setFont(applet.normalFont);
        this.setDefaultScene.addActionListener(this);
        (this.prevScene = new JMenuItem("Previous Avail. Date")).setFont(applet.normalFont);
        this.prevScene.addActionListener(this);
        (this.nextScene = new JMenuItem("Next Avail. Date")).setFont(applet.normalFont);
        this.nextScene.addActionListener(this);
        (this.addAllScenes = new JMenuItem("Add All To Scene List")).setFont(applet.normalFont);
        this.addAllScenes.addActionListener(this);
        (this.addSwathScenes = new JMenuItem("Add Swath To Scene List")).setFont(applet.normalFont);
        this.addSwathScenes.addActionListener(this);
        (this.hideSwathScenes = new JMenuItem("Hide Swath")).setFont(applet.normalFont);
        this.hideSwathScenes.addActionListener(this);
        (this.addUserScenes = new JMenuItem("Add User Area Scenes")).setFont(applet.normalFont);
        this.addUserScenes.addActionListener(this);
        this.nextPrevPresent = false;
        this.setScenesToDatePresent = false;
        this.setDefaultScenePresent = false;
        this.allScenesPresent = false;
        this.swathScenesPresent = false;
        this.addUserScenesPresent = false;
    }
    
    public void configureMenu(final Metadata scene, final Vector intersectingScenes, final boolean b, final boolean b2, boolean b3, final boolean b4) {
        this.scene = scene;
        this.intersectingScenes = intersectingScenes;
        final Sensor currentSensor = this.applet.sensorMenu.getCurrentSensor();
        if (this.selectByScene != null) {
            this.remove(this.selectByScene);
            this.selectByScene = null;
        }
        boolean b5 = true;
        if (b) {
            b5 = false;
        }
        this.raise.setEnabled(b5);
        this.lower.setEnabled(b5);
        this.sceneID.setText("ID: " + scene.getEntityIDForDisplay());
        String text = "";
        int n = 1;
        if (currentSensor.hasCloudCover) {
            text = text + "" + scene.cloudCover + "% Cloud";
            n = 0;
        }
        if (currentSensor.numQualityValues > 0) {
            if (n == 0) {
                text += ", ";
            }
            else {
                n = 0;
            }
            text = text + "Qlty " + scene.getQuality();
        }
        if (currentSensor.hasAcqDate) {
            if (n == 0) {
                text += ", ";
            }
            else {
                n = 0;
            }
            text += scene.getDateString();
        }
        this.sceneInfo.setText(text);
        this.sceneInfo.setVisible(n == 0);
        this.setScenesToDateTitle = "Set Scenes To " + scene.getDateString();
        this.setScenesToDate.setText(this.setScenesToDateTitle);
        if (!currentSensor.hasAcqDate) {
            b3 = false;
        }
        if (b2) {
            if (!this.nextPrevPresent) {
                this.insertMenuItemAfter(this.prevScene, this.lower.getText());
                this.insertMenuItemAfter(this.nextScene, this.prevScene.getText());
                this.nextPrevPresent = true;
            }
            if (this.md.sceneFilter.isPrevDateAvailable(scene)) {
                this.prevScene.setEnabled(true);
            }
            else {
                this.prevScene.setEnabled(false);
            }
            if (this.md.sceneFilter.isNextDateAvailable(scene)) {
                this.nextScene.setEnabled(true);
            }
            else {
                this.nextScene.setEnabled(false);
            }
        }
        else if (this.nextPrevPresent) {
            this.remove(this.prevScene);
            this.remove(this.nextScene);
            this.nextPrevPresent = false;
        }
        if (b3) {
            if (!this.setScenesToDatePresent) {
                this.add(this.setScenesToDate);
                this.setScenesToDatePresent = true;
            }
        }
        else if (this.setScenesToDatePresent) {
            this.remove(this.setScenesToDate);
            this.setScenesToDatePresent = false;
        }
        if (b4) {
            if (!this.setDefaultScenePresent) {
                this.add(this.setDefaultScene);
                this.setDefaultScenePresent = true;
            }
        }
        else if (this.setDefaultScenePresent) {
            this.remove(this.setDefaultScene);
            this.setDefaultScenePresent = false;
        }
        if (!currentSensor.hasSwathMode || !this.applet.toolsMenu.isSwathModeEnabled() || b || this.applet.searchLimitDialog.isSceneListFilterEnabled()) {
            if (this.swathScenesPresent) {
                this.remove(this.addSwathScenes);
                this.remove(this.hideSwathScenes);
                this.swathScenesPresent = false;
            }
        }
        else if (!this.swathScenesPresent) {
            this.insertMenuItemAfter(this.addSwathScenes, "Add To Scene List");
            this.insertMenuItemAfter(this.hideSwathScenes, "Hide Scene");
            this.swathScenesPresent = true;
        }
        if (!currentSensor.allowAddAll || b || this.applet.searchLimitDialog.isSceneListFilterEnabled()) {
            if (this.allScenesPresent) {
                this.remove(this.addAllScenes);
                this.allScenesPresent = false;
            }
        }
        else if (!this.allScenesPresent) {
            if (this.swathScenesPresent) {
                this.insertMenuItemAfter(this.addAllScenes, "Add Swath To Scene List");
            }
            else {
                this.insertMenuItemAfter(this.addAllScenes, "Add To Scene List");
            }
            this.allScenesPresent = true;
        }
        if (!currentSensor.hasUserDefinedArea || !this.applet.userDefinedAreaDialog.isUserDefinedAreaClosed()) {
            if (this.addUserScenesPresent) {
                this.remove(this.addUserScenes);
                this.addUserScenesPresent = false;
            }
        }
        else if (!this.addUserScenesPresent) {
            this.insertMenuItemAfter(this.addUserScenes, "Add To Scene List");
            this.addUserScenesPresent = true;
        }
        if (currentSensor.sceneList.find(scene) != -1) {
            this.removeScene.setEnabled(true);
        }
        else {
            this.removeScene.setEnabled(false);
        }
        if (intersectingScenes != null && intersectingScenes.size() > 1) {
            (this.selectByScene = new JMenu("Select Scene")).setFont(this.applet.normalFont);
            final Enumeration<Metadata> elements = intersectingScenes.elements();
            while (elements.hasMoreElements()) {
                final Metadata metadata = elements.nextElement();
                final JMenuItem menuItem = new JMenuItem(metadata.getEntityIDForDisplay() + " " + metadata.getDateString());
                menuItem.setFont(this.applet.normalFont);
                menuItem.addActionListener(this);
                this.selectByScene.add(menuItem);
            }
            this.add(this.selectByScene);
        }
    }
    
    private void insertMenuItemAfter(final JMenuItem menuItem, final String s) {
        final MenuElement[] subElements = this.getSubElements();
        final int length = subElements.length;
        int n = -1;
        for (int i = 0; i < length; ++i) {
            if (((JMenuItem)subElements[i]).getText().equals(s)) {
                n = i + 2;
                break;
            }
        }
        if (n == -1) {
            n = length;
        }
        this.insert(menuItem, n);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Bring To Front")) {
            this.md.setSelectedScene(this.scene);
        }
        else if (actionCommand.equals("Send To Back")) {
            this.md.lowerScene(this.scene);
        }
        else if (actionCommand.equals("Set Point Of Interest")) {
            this.md.mapLayers.getPointOfInterestMapLayer().setPoint(this.applet.imgArea.getRightClickLatLong());
        }
        else if (actionCommand.equals("Add To Scene List")) {
            this.applet.sensorMenu.getCurrentSensor().sceneList.add(this.scene);
        }
        else if (actionCommand.equals("Hide Scene")) {
            this.applet.hideSceneDialog.hideScene(this.scene);
        }
        else if (actionCommand.equals("Show Metadata")) {
            this.scene.showMetadata();
        }
        else if (actionCommand.equals("Show Browse")) {
            this.scene.showBrowse();
        }
        else if (actionCommand.equals("Default Scene")) {
            this.md.setDefaultScene(this.scene);
        }
        else if (actionCommand.equals("Previous Avail. Date")) {
            this.md.sceneFilter.prevDate(this.scene);
        }
        else if (actionCommand.equals("Next Avail. Date")) {
            this.md.sceneFilter.nextDate(this.scene);
        }
        else if (actionCommand.equals(this.setScenesToDateTitle)) {
            this.md.setScenesToDate(this.scene);
        }
        else if (actionCommand.equals("Add All To Scene List")) {
            this.md.selectAllScenes(this.scene);
        }
        else if (actionCommand.equals("Add Swath To Scene List")) {
            this.md.selectSwathScenes(this.scene, false);
        }
        else if (actionCommand.equals("Hide Swath")) {
            this.md.selectSwathScenes(this.scene, true);
        }
        else if (actionCommand.equals("Add User Area Scenes")) {
            this.md.selectUserAreaScenes(this.scene);
        }
        else if (actionCommand.equals("Remove From Scene List")) {
            this.applet.sensorMenu.getCurrentSensor().sceneList.remove(this.scene);
        }
        else if (this.intersectingScenes != null) {
            final Enumeration<Metadata> elements = (Enumeration<Metadata>)this.intersectingScenes.elements();
            final String substring = actionCommand.substring(0, actionCommand.indexOf(32));
            while (elements.hasMoreElements()) {
                final Metadata selectedScene = elements.nextElement();
                if (substring.equals(selectedScene.getEntityIDForDisplay())) {
                    this.md.setSelectedScene(selectedScene);
                    break;
                }
            }
        }
    }
}
