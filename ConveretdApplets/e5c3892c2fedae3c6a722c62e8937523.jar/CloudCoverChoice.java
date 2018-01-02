import javax.swing.JComboBox;

// 
// Decompiled by Procyon v0.5.30
// 

public class CloudCoverChoice extends JComboBox
{
    public CloudCoverChoice() {
        this.setEditable(false);
        this.addItem("100%");
        this.addItem("90%");
        this.addItem("80%");
        this.addItem("70%");
        this.addItem("60%");
        this.addItem("50%");
        this.addItem("40%");
        this.addItem("30%");
        this.addItem("20%");
        this.addItem("10%");
        this.addItem("0%");
        this.setCloudCover(100);
    }
    
    public int getCloudCover() {
        return 100 - this.getSelectedIndex() * 10;
    }
    
    public void setCloudCover(final int n) {
        this.setSelectedIndex((100 - n) / 10);
    }
}
