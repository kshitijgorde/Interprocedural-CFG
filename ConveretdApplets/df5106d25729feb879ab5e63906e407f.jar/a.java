import java.io.IOException;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class a
{
    private e a;
    
    protected e a() {
        return this.a;
    }
    
    a(final InputStream inputStream) throws Exception {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
            final int intValue = (int)(Object)Float.valueOf(bufferedReader.readLine());
            this.a = new e(String.valueOf(bufferedReader.readLine()) + ".xyz", intValue, (int)(intValue * 1.15f));
            for (int i = 0; i < intValue; ++i) {
                final StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
                this.a.l[i] = stringTokenizer.nextToken();
                this.a.c[i] = Float.valueOf(stringTokenizer.nextToken());
                this.a.b[i] = Float.valueOf(stringTokenizer.nextToken());
                this.a.a[i] = Float.valueOf(stringTokenizer.nextToken());
            }
            bufferedReader.close();
        }
        catch (IOException ex) {
            System.err.println("Cannot access file.");
        }
    }
    
    a() {
        this.a = null;
    }
}
