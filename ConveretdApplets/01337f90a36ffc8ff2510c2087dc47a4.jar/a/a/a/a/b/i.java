// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a.b;

import java.io.IOException;
import java.io.EOFException;
import java.io.DataInputStream;

public abstract class i
{
    public static String a(final DataInputStream dataInputStream, final boolean b) throws EOFException, IOException {
        final byte[] array = new byte[256];
        int i = 0;
        while (true) {
            array[0] = dataInputStream.readByte();
            if (b) {
                if (array[0] != 34 && array[0] != 32 && array[0] != 9 && array[0] != 13 && array[0] != 10) {
                    break;
                }
                continue;
            }
            else {
                if (array[0] != 34 && array[0] != 61 && array[0] != 32 && array[0] != 91 && array[0] != 9 && array[0] != 13 && array[0] != 10) {
                    break;
                }
                continue;
            }
        }
        while (true) {
            if (b) {
                if (array[i] == 34 || array[i] == 44 || array[i] == 91 || array[i] == 9 || array[i] == 13) {
                    break;
                }
                if (array[i] == 10) {
                    break;
                }
            }
            else {
                if (array[i] == 34 || array[i] == 44 || array[i] == 32 || array[i] == 91 || array[i] == 9 || array[i] == 13) {
                    break;
                }
                if (array[i] == 10) {
                    break;
                }
            }
            ++i;
            try {
                array[i] = dataInputStream.readByte();
                continue;
            }
            catch (Exception ex) {}
            break;
        }
        if (i == 0) {
            return null;
        }
        final byte[] array2 = new byte[i];
        --i;
        while (i >= 0) {
            array2[i] = array[i];
            --i;
        }
        return new String(array2, 0);
    }
}
