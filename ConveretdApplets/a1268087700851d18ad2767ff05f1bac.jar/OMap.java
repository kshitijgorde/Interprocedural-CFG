import java.io.IOException;
import java.io.DataInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class OMap
{
    public int firstNum;
    public int nCol;
    public int nRow;
    public double scale;
    public double x;
    public double y;
    
    public OMap() {
    }
    
    public OMap(final DataInputStream in) {
        try {
            this.firstNum = in.readShort();
            this.nCol = in.readShort();
            this.nRow = in.readShort();
            this.scale = in.readDouble();
            this.x = in.readDouble();
            this.y = in.readDouble();
        }
        catch (IOException e) {
            System.out.println("Error: ".concat(String.valueOf(String.valueOf(e.getMessage()))));
            this.firstNum = -1;
        }
    }
}
