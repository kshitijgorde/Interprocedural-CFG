import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mrlc2001RADataset extends MrlcDataset
{
    private static int[] resolutions;
    private static final int[] borderX;
    private static final int[] borderY;
    
    Mrlc2001RADataset(final imgViewer imgViewer) {
        super(imgViewer, "MRLC/MTBS Reflectance", "mrlc_2001_ra", "MRLC2K_SITC_REF", "http://eros.usgs.gov/#Find_Data/Products_and_Data_Available/MRLC2001", Mrlc2001RADataset.resolutions, Mrlc2001RADataset.borderX, Mrlc2001RADataset.borderY, Color.YELLOW);
        this.numQualityValues = 0;
        this.sceneIdHint = new String("Enter a 21 character scene ID");
        this.navModel = new WRS2Model();
        this.isOrderable = false;
        this.downloadFileFormat = "tarred NDF";
        this.isDownloadable = true;
        this.slowDownloadStart = true;
        this.cgiDatasetName = "MRLC2K_SITC";
    }
    
    @Override
    public int getStartingYear() {
        return 1982;
    }
    
    @Override
    public int getEndingYear() {
        return -1;
    }
    
    @Override
    public int getImageFileSize(final int n) {
        if (n == 1000) {
            return 35000;
        }
        return 120000;
    }
    
    static {
        Mrlc2001RADataset.resolutions = new int[] { 1000, 240 };
        borderX = new int[] { -4, 4, 4, -4 };
        borderY = new int[] { -4, -4, 4, 4 };
    }
}
