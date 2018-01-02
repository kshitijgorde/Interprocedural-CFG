import java.io.IOException;
import java.util.StringTokenizer;
import java.io.DataInputStream;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class b
{
    private e a;
    
    protected e a() {
        return this.a;
    }
    
    b(final InputStream inputStream) throws Exception {
        try {
            final DataInputStream dataInputStream = new DataInputStream(inputStream);
            final int intValue = (int)(Object)Float.valueOf(dataInputStream.readLine());
            this.a = new e(String.valueOf(dataInputStream.readLine()) + ".xyz", intValue, (int)(intValue * 1.15f));
            for (int i = 0; i < intValue; ++i) {
                final StringTokenizer stringTokenizer = new StringTokenizer(dataInputStream.readLine(), " ");
                this.a.l[i] = stringTokenizer.nextToken();
                this.a.c[i] = Float.valueOf(stringTokenizer.nextToken());
                this.a.b[i] = Float.valueOf(stringTokenizer.nextToken());
                this.a.a[i] = Float.valueOf(stringTokenizer.nextToken());
            }
            dataInputStream.close();
        }
        catch (IOException ex) {
            System.err.println("Cannot access file.");
        }
    }
    
    b() {
        this.a = null;
    }
}
