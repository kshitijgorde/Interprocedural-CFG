import java.io.DataInputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.FileInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class DataBase
{
    public static byte[][] allFrames;
    public static byte[][] allSkinlist;
    public static byte[][] allModels;
    public static int[] modelList;
    
    private static String getDir() {
        return SignLink.findcachedir();
    }
    
    public static void loadAnimations() {
        int i = 0;
        try {
            final DataInputStream dataInputStream = new DataInputStream(new GZIPInputStream(new FileInputStream(getDir() + "/frames.dat")));
            final DataInputStream dataInputStream2 = new DataInputStream(new GZIPInputStream(new FileInputStream(getDir() + "/frames.idx")));
            for (final int int1 = dataInputStream2.readInt(), i = 0; i < int1; ++i) {
                final int int2 = dataInputStream2.readInt();
                final byte[] array = new byte[dataInputStream2.readInt()];
                dataInputStream.readFully(array);
                DataBase.allFrames[int2] = array;
            }
            dataInputStream2.close();
            dataInputStream.close();
        }
        catch (Exception ex) {
            System.out.println("Error: " + i);
            ex.printStackTrace();
        }
        try {
            final DataInputStream dataInputStream3 = new DataInputStream(new GZIPInputStream(new FileInputStream(getDir() + "/skinlist.dat")));
            final DataInputStream dataInputStream4 = new DataInputStream(new GZIPInputStream(new FileInputStream(getDir() + "/skinlist.idx")));
            for (final int int3 = dataInputStream4.readInt(), i = 0; i < int3; ++i) {
                final int int4 = dataInputStream4.readInt();
                final byte[] array2 = new byte[dataInputStream4.readInt()];
                dataInputStream3.readFully(array2);
                DataBase.allSkinlist[int4] = array2;
            }
            dataInputStream4.close();
            dataInputStream3.close();
        }
        catch (Exception ex2) {
            System.out.println("Error: " + i);
            ex2.printStackTrace();
        }
    }
    
    public static void loadModels() {
        int i = 0;
        try {
            final DataInputStream dataInputStream = new DataInputStream(new GZIPInputStream(new FileInputStream(getDir() + "/models.dat")));
            final DataInputStream dataInputStream2 = new DataInputStream(new GZIPInputStream(new FileInputStream(getDir() + "/models.idx")));
            final int int1 = dataInputStream2.readInt();
            DataBase.modelList = new int[int1];
            for (i = 0; i < int1; ++i) {
                final int int2 = dataInputStream2.readInt();
                DataBase.modelList[i] = int2;
                final byte[] array = new byte[dataInputStream2.readInt()];
                dataInputStream.readFully(array);
                DataBase.allModels[int2] = array;
            }
            dataInputStream2.close();
            dataInputStream.close();
        }
        catch (Exception ex) {
            System.out.println("Error: " + i + " / " + DataBase.modelList[i]);
            ex.printStackTrace();
        }
    }
    
    static {
        DataBase.allFrames = new byte[7000][];
        DataBase.allSkinlist = new byte[7000][];
        DataBase.allModels = new byte[70000][];
    }
}
