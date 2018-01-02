import java.awt.Image;
import java.io.DataInputStream;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class OMaps
{
    private OCAD apl;
    private Vector maps;
    private Vector images;
    private int[] imAge;
    private int imCount;
    public int resol;
    public double x;
    public double y;
    
    public OMaps() {
        this.maps = new Vector();
        this.images = new Vector();
        this.imCount = 0;
    }
    
    public OMaps(final OCAD apl) {
        this.maps = new Vector();
        this.images = new Vector();
        this.imCount = 0;
        this.apl = apl;
        this.imAge = new int[32];
    }
    
    public void addMap(final DataInputStream in) {
        final OMap map = new OMap(in);
        this.maps.addElement(map);
        while (this.images.size() < map.firstNum + map.nCol * map.nRow) {
            this.images.addElement(null);
        }
    }
    
    public int mapCount() {
        return this.maps.size();
    }
    
    public OMap getMap(final int i) {
        return this.maps.elementAt(i);
    }
    
    public Image getImage(final int i) {
        if (this.images.elementAt(i) == null) {
            if (i > 0) {
                if (this.imCount == 32) {
                    this.images.setElementAt(null, this.imAge[31]);
                }
                else {
                    ++this.imCount;
                }
                for (int j = this.imCount - 1; j > 0; --j) {
                    this.imAge[j] = this.imAge[j - 1];
                }
                this.imAge[0] = i;
            }
            this.images.setElementAt(this.apl.getImage(this.apl.getCodeBase(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.apl.fileName))).append("_").append(Integer.toString(i)).append(".gif")))), i);
        }
        return this.images.elementAt(i);
    }
}
