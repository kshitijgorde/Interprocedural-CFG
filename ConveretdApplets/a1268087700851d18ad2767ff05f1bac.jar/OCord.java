import java.io.IOException;
import java.io.DataInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class OCord
{
    public double x;
    public double y;
    
    public OCord() {
    }
    
    public OCord(final DataInputStream in) {
        try {
            this.x = in.readDouble();
            this.y = in.readDouble();
        }
        catch (IOException e) {
            System.out.println("Error: ".concat(String.valueOf(String.valueOf(e.getMessage()))));
        }
    }
}
