import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class ModelDecompressor
{
    public static void loadModelz() {
        try {
            final DataInputStream dataInputStream = new DataInputStream(new FileInputStream(SignLink.findcachedir() + "/Data/Animation/models.idx"));
            final DataInputStream dataInputStream2 = new DataInputStream(new FileInputStream(SignLink.findcachedir() + "/Data/Animation/models.dat"));
            for (int int1 = dataInputStream.readInt(), i = 0; i < int1; ++i) {
                final int int2 = dataInputStream.readInt();
                final byte[] array = new byte[dataInputStream.readInt()];
                dataInputStream2.readFully(array);
                Model.method460(array, int2);
            }
            dataInputStream.close();
            dataInputStream2.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void hdgfx() {
        try {
            final DataInputStream dataInputStream = new DataInputStream(new FileInputStream(SignLink.findcachedir() + "/Data/Animation/models6.idx"));
            final DataInputStream dataInputStream2 = new DataInputStream(new FileInputStream(SignLink.findcachedir() + "/Data/Animation/models6.dat"));
            for (int int1 = dataInputStream.readInt(), i = 0; i < int1; ++i) {
                final int int2 = dataInputStream.readInt();
                final byte[] array = new byte[dataInputStream.readInt()];
                dataInputStream2.readFully(array);
                Model.method460(array, int2);
            }
            dataInputStream.close();
            dataInputStream2.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void loadModelz1() {
        try {
            final DataInputStream dataInputStream = new DataInputStream(new FileInputStream(SignLink.findcachedir() + "/Data/Animation/models1.idx"));
            final DataInputStream dataInputStream2 = new DataInputStream(new FileInputStream(SignLink.findcachedir() + "/Data/Animation/models1.dat"));
            for (int int1 = dataInputStream.readInt(), i = 0; i < int1; ++i) {
                final int int2 = dataInputStream.readInt();
                final byte[] array = new byte[dataInputStream.readInt()];
                dataInputStream2.readFully(array);
                Model.method460(array, int2);
            }
            dataInputStream.close();
            dataInputStream2.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void loadModelz3() {
        try {
            final DataInputStream dataInputStream = new DataInputStream(new FileInputStream(SignLink.findcachedir() + "/Data/Animation/models3.idx"));
            final DataInputStream dataInputStream2 = new DataInputStream(new FileInputStream(SignLink.findcachedir() + "/Data/Animation/models3.dat"));
            for (int int1 = dataInputStream.readInt(), i = 0; i < int1; ++i) {
                final int int2 = dataInputStream.readInt();
                final byte[] array = new byte[dataInputStream.readInt()];
                dataInputStream2.readFully(array);
                Model.method460(array, int2);
            }
            dataInputStream.close();
            dataInputStream2.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void loadModelz4() {
        try {
            final DataInputStream dataInputStream = new DataInputStream(new FileInputStream(SignLink.findcachedir() + "/Data/Animation/models4.idx"));
            final DataInputStream dataInputStream2 = new DataInputStream(new FileInputStream(SignLink.findcachedir() + "/Data/Animation/models4.dat"));
            for (int int1 = dataInputStream.readInt(), i = 0; i < int1; ++i) {
                final int int2 = dataInputStream.readInt();
                final byte[] array = new byte[dataInputStream.readInt()];
                dataInputStream2.readFully(array);
                Model.method460(array, int2);
            }
            dataInputStream.close();
            dataInputStream2.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void loadModelz5() {
        try {
            final DataInputStream dataInputStream = new DataInputStream(new FileInputStream(SignLink.findcachedir() + "/Data/Animation/models5.idx"));
            final DataInputStream dataInputStream2 = new DataInputStream(new FileInputStream(SignLink.findcachedir() + "/Data/Animation/models5.dat"));
            for (int int1 = dataInputStream.readInt(), i = 0; i < int1; ++i) {
                final int int2 = dataInputStream.readInt();
                final byte[] array = new byte[dataInputStream.readInt()];
                dataInputStream2.readFully(array);
                Model.method460(array, int2);
            }
            dataInputStream.close();
            dataInputStream2.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void loadModelDataFile() {
        try {
            final DataInputStream dataInputStream = new DataInputStream(new FileInputStream(SignLink.findcachedir() + "null_cache.idx"));
            final DataInputStream dataInputStream2 = new DataInputStream(new FileInputStream(SignLink.findcachedir() + "null_cache.dat"));
            for (int int1 = dataInputStream.readInt(), i = 0; i < int1; ++i) {
                final int int2 = dataInputStream.readInt();
                final byte[] array = new byte[dataInputStream.readInt()];
                dataInputStream2.readFully(array);
                Model.method460(array, int2);
            }
            dataInputStream.close();
            dataInputStream2.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
