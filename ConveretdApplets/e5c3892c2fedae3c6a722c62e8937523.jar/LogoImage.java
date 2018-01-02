import java.net.URL;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class LogoImage
{
    private imgViewer applet;
    private Image[] sensorLogos;
    private Image currentLogo;
    private int logoLocation;
    
    LogoImage(final imgViewer applet) {
        this.applet = applet;
        final Sensor[] sensors = this.applet.getSensors();
        this.sensorLogos = new Image[sensors.length];
        final MediaTracker mediaTracker = new MediaTracker(this.applet);
        for (int i = 0; i < sensors.length; ++i) {
            mediaTracker.addImage(this.sensorLogos[i] = this.applet.getImage(this.applet.getCodeBase(), "graphics/" + sensors[i].logoName), i);
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        for (int j = 0; j < sensors.length; ++j) {
            if (mediaTracker.isErrorID(j)) {
                System.out.println("Error loading graphics/" + sensors[j].logoName);
            }
        }
        this.setSensor(this.applet.sensorMenu.getCurrentSensor());
    }
    
    public void setSensor(final Sensor sensor) {
        final Sensor[] sensors = this.applet.getSensors();
        for (int i = 0; i < sensors.length; ++i) {
            if (sensors[i] == sensor) {
                this.currentLogo = this.sensorLogos[i];
                this.logoLocation = sensors[i].logoLocation;
                break;
            }
        }
    }
    
    public Image getLogo() {
        return this.currentLogo;
    }
    
    public int getLocation() {
        return this.logoLocation;
    }
    
    public void clicked() {
        try {
            this.applet.getAppletContext().showDocument(new URL(this.applet.sensorMenu.getCurrentSensor().logoLink), "_blank");
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    
    public void cleanup() {
        for (int i = 0; i < this.sensorLogos.length; ++i) {
            if (this.sensorLogos[i] != null) {
                this.sensorLogos[i].flush();
                this.sensorLogos[i] = null;
            }
        }
    }
}
