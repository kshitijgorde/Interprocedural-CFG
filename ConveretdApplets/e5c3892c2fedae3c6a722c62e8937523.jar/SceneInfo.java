import java.util.Observable;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.util.Observer;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

class SceneInfo extends JPanel implements Observer
{
    private imgViewer applet;
    private JLabel sceneInfoHeader;
    private MosaicData md;
    private JTextArea sceneInfo;
    
    SceneInfo(final imgViewer applet, final MosaicData md) {
        this.applet = applet;
        this.md = md;
        this.setLayout(new BorderLayout());
        (this.sceneInfoHeader = new JLabel("Scene Information:")).setFont(applet.boldFont);
        final JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEtchedBorder());
        (this.sceneInfo = new JTextArea("", 3, 24)).setEditable(false);
        this.sceneInfo.setBackground(Color.WHITE);
        this.sceneInfo.setToolTipText("Scene Information");
        panel.add(this.sceneInfo, "Center");
        this.add(this.sceneInfoHeader, "North");
        this.add(panel, "Center");
        final Dimension preferredSize = this.getPreferredSize();
        preferredSize.width = 100;
        this.setMinimumSize(preferredSize);
        preferredSize.width = 240;
        this.setMaximumSize(preferredSize);
    }
    
    @Override
    public void update(final Observable observable, final Object o) {
        final Metadata currentScene = this.md.getCurrentScene();
        final Sensor currentSensor = this.applet.sensorMenu.getCurrentSensor();
        int cloudCover = -1;
        String shortEntityIDForDisplay;
        String string;
        String dateString;
        int quality;
        if (currentScene != null) {
            shortEntityIDForDisplay = currentScene.getShortEntityIDForDisplay();
            cloudCover = currentScene.cloudCover;
            string = cloudCover + "%";
            if (currentSensor.hasAcqDate) {
                dateString = currentScene.getDateString();
            }
            else {
                dateString = "";
            }
            quality = currentScene.getQuality();
        }
        else {
            shortEntityIDForDisplay = "";
            string = "";
            dateString = "";
            quality = -1;
        }
        final StringBuffer sb = new StringBuffer();
        if (currentSensor.hasCloudCover) {
            if (currentSensor.hasCustomSceneInfoLine) {
                sb.append("CC: " + string);
                if (currentSensor.hasAcqDate) {
                    if (cloudCover < 10) {
                        sb.append("    Date: " + dateString);
                    }
                    else if (cloudCover < 100) {
                        sb.append("  Date: " + dateString);
                    }
                    else {
                        sb.append(" Date: " + dateString);
                    }
                }
            }
            else {
                sb.append("Cloud Cover: " + string);
                if (currentSensor.numQualityValues > 0) {
                    if (quality >= 0) {
                        if (cloudCover < 10) {
                            sb.append("    Qlty: " + quality);
                        }
                        else if (cloudCover < 100) {
                            sb.append("  Qlty: " + quality);
                        }
                        else {
                            sb.append(" Qlty: " + quality);
                        }
                    }
                    else {
                        sb.append("          Qlty: ");
                    }
                }
            }
            sb.append("\n");
        }
        else if (currentSensor.numQualityValues > 0) {
            if (quality >= 0) {
                sb.append("Qlty: " + quality + "\n");
            }
            else {
                sb.append("Qlty:\n");
            }
        }
        if (!currentSensor.hasCustomSceneInfoLine) {
            String text = "ID: " + shortEntityIDForDisplay + "\n" + sb.toString();
            if (currentSensor.hasAcqDate) {
                text = text + "Date: " + dateString;
            }
            this.sceneInfo.setText(text);
        }
        else {
            this.sceneInfo.setText("ID: " + shortEntityIDForDisplay + "\n" + sb.toString() + currentSensor.getCustomSceneInfo(currentScene));
        }
    }
}
