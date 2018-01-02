import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import javax.swing.JPopupMenu;

// 
// Decompiled by Procyon v0.5.30
// 

public class SceneListMenu extends JPopupMenu implements ActionListener
{
    private imgViewer applet;
    private MosaicData md;
    private SceneListList sceneListList;
    private Metadata scene;
    private JMenuItem sceneID;
    private JMenuItem sceneInfo;
    private JMenuItem shopList;
    private JMenuItem display;
    private JMenuItem showMetadata;
    private JMenuItem showBrowse;
    private JMenuItem deleteScene;
    private JMenuItem unhideScene;
    private JMenuItem restore;
    private JMenuItem more;
    private static final int NORMAL = 0;
    private static final int REMOVE = 1;
    private static final int EMPTY_LIST = 2;
    private int currentState;
    
    public SceneListMenu(final imgViewer applet, final MosaicData md, final boolean b, final boolean b2) {
        super("Scene List Menu");
        this.applet = applet;
        this.md = md;
        applet.sensorMenu.getCurrentSensor();
        (this.sceneID = new JMenuItem("Scene ID")).setFont(applet.normalFont);
        this.sceneID.addActionListener(this);
        (this.sceneInfo = new JMenuItem("Scene Info")).setFont(applet.normalFont);
        this.sceneInfo.addActionListener(this);
        (this.showMetadata = new JMenuItem("Show Metadata")).setFont(applet.normalFont);
        this.showMetadata.addActionListener(this);
        (this.showBrowse = new JMenuItem("Show Browse")).setFont(applet.normalFont);
        this.showBrowse.addActionListener(this);
        if (b2) {
            (this.display = new JMenuItem("Display")).setFont(applet.normalFont);
            this.display.addActionListener(this);
            (this.deleteScene = new JMenuItem("Delete")).setFont(applet.normalFont);
            this.deleteScene.addActionListener(this);
            (this.restore = new JMenuItem("Restore")).setFont(applet.normalFont);
            this.restore.addActionListener(this);
            (this.shopList = new JMenuItem("Send to Cart")).setFont(applet.normalFont);
            this.shopList.addActionListener(this);
        }
        else {
            (this.unhideScene = new JMenuItem("Unhide")).setFont(applet.normalFont);
            this.unhideScene.addActionListener(this);
        }
        if (b) {
            (this.more = new JMenuItem("More...")).setFont(applet.normalFont);
            this.more.addActionListener(this);
        }
        this.currentState = -1;
    }
    
    public void configureMenu(final SceneListList sceneListList, final Metadata scene) {
        this.sceneListList = sceneListList;
        this.scene = scene;
        final Sensor currentSensor = this.applet.sensorMenu.getCurrentSensor();
        if (scene != null) {
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
            if (this.display != null) {
                this.applet.searchLimitDialog.applySearchLimits(scene);
                if (scene.visible) {
                    this.display.setEnabled(true);
                }
                else {
                    this.display.setEnabled(false);
                }
            }
            if (this.shopList != null) {
                if ((currentSensor.isOrderable || currentSensor.isDownloadable) && !this.shopList.isEnabled()) {
                    this.shopList.setEnabled(true);
                    this.currentState = -1;
                }
                else if (this.shopList.isEnabled() && !currentSensor.isOrderable && !currentSensor.isDownloadable) {
                    this.shopList.setEnabled(false);
                    this.currentState = -1;
                }
            }
            if (this.currentState != 0) {
                this.removeAll();
                this.add(this.sceneID);
                this.add(this.sceneInfo);
                this.addSeparator();
                this.add(this.showMetadata);
                this.add(this.showBrowse);
                if (this.display != null) {
                    this.add(this.display);
                }
                if (this.deleteScene != null) {
                    this.add(this.deleteScene);
                }
                if (this.unhideScene != null) {
                    this.add(this.unhideScene);
                }
                this.addSeparator();
                if (this.shopList != null) {
                    if (currentSensor.isOrderable || currentSensor.isDownloadable) {
                        this.shopList.setEnabled(true);
                        this.add(this.shopList);
                    }
                    else if (this.shopList.isEnabled()) {
                        this.shopList.setEnabled(false);
                        this.remove(this.shopList);
                    }
                }
                if (this.more != null) {
                    this.add(this.more);
                }
                this.currentState = 0;
            }
        }
        else if (sceneListList.isRestoreEnabled()) {
            if (this.currentState != 1) {
                this.removeAll();
                this.add(this.restore);
                if (this.more != null) {
                    this.add(this.more);
                }
                this.currentState = 1;
            }
        }
        else if (this.currentState != 2) {
            this.removeAll();
            if (this.more != null) {
                this.add(this.more);
            }
            this.currentState = 2;
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Show Metadata")) {
            this.scene.showMetadata();
        }
        else if (actionCommand.equals("Show Browse")) {
            this.scene.showBrowse();
        }
        else if (actionCommand.equals("Display")) {
            this.md.showScene(this.scene);
        }
        else if (actionCommand.equals("Delete")) {
            this.sceneListList.remove();
        }
        else if (actionCommand.equals("Unhide")) {
            this.sceneListList.remove();
        }
        else if (actionCommand.equals("Send to Cart")) {
            this.sceneListList.order();
        }
        else if (actionCommand.equals("Restore")) {
            this.sceneListList.restore();
        }
        else if (actionCommand.equals("More...")) {
            this.applet.sceneListDialog.setLocation(this.applet.sceneListPanel.getLocationOnScreen());
            this.applet.sceneListDialog.setVisible(true);
        }
    }
}
