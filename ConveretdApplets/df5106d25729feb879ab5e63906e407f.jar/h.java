import java.io.IOException;
import java.util.StringTokenizer;
import java.io.DataInputStream;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class h
{
    private e a;
    
    protected e a() {
        return this.a;
    }
    
    h(final InputStream inputStream) throws Exception {
        try {
            final DataInputStream dataInputStream = new DataInputStream(inputStream);
            final String string = String.valueOf(dataInputStream.readLine()) + ".mol";
            dataInputStream.readLine();
            dataInputStream.readLine();
            final String line = dataInputStream.readLine();
            final int intValue = (int)(Object)Float.valueOf(line.substring(0, 3));
            final int intValue2 = (int)(Object)Float.valueOf(line.substring(3, 6));
            this.a = new e(string, intValue, intValue2);
            for (int i = 0; i < intValue; ++i) {
                final StringTokenizer stringTokenizer = new StringTokenizer(dataInputStream.readLine(), " ");
                this.a.c[i] = Float.valueOf(stringTokenizer.nextToken());
                this.a.b[i] = Float.valueOf(stringTokenizer.nextToken());
                this.a.a[i] = Float.valueOf(stringTokenizer.nextToken());
                this.a.l[i] = stringTokenizer.nextToken();
            }
            for (int j = 0; j < intValue2; ++j) {
                final String line2 = dataInputStream.readLine();
                this.a.j[j] = (int)(Object)Float.valueOf(line2.substring(0, 3)) - 1;
                this.a.i[j] = (int)(Object)Float.valueOf(line2.substring(3, 6)) - 1;
                this.a.k[j] = (int)(Object)Float.valueOf(line2.substring(6, 9));
            }
            dataInputStream.close();
        }
        catch (IOException ex) {
            System.err.println("Cannot access file.");
        }
    }
    
    h() {
        this.a = null;
    }
}
