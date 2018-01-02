import java.net.URL;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Vector;
import java.io.DataInputStream;
import java.io.IOException;
import java.beans.PropertyChangeSupport;
import java.io.DataOutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class OmegaClient
{
    boolean alliberaConnexio;
    boolean append;
    DataOutputStream I;
    boolean Z;
    EvaluateStream charAt;
    private PropertyChangeSupport propertyChangeSupport;
    
    public OmegaClient() {
        this.alliberaConnexio = false;
        this.append = false;
        this.Z = false;
        this.charAt = new EvaluateStream();
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }
    
    public final void init() {
        if (this.append) {
            throw new OException("Ja inicialitzat.");
        }
        this.append = true;
    }
    
    public final void end() {
        if (!this.append) {
            throw new OException("No inicialitzat.");
        }
        this.append = false;
    }
    
    public void interrumpt() {
        try {
            if (this.Z) {
                this.I.writeInt(1);
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public final String[] evaluate(final String[] array, final int n) {
        OException ex = null;
        String[] receive = new String[array.length];
        try {
            final DataOutputStream iniciaConnexio = this.iniciaConnexio();
            this.Z = true;
            this.charAt.send(array, n, iniciaConnexio);
            final DataInputStream dataInputStream = new DataInputStream(this.getInputStream());
            try {
                receive = this.charAt.receive(array.length, dataInputStream, n);
            }
            catch (OException ex2) {
                this.append();
                ex = ex2;
            }
            this.Z = false;
            this.alliberaConnexio();
        }
        catch (Exception ex3) {
            this.append();
            this.Z = false;
            ex3.printStackTrace();
            try {
                this.alliberaConnexio();
            }
            catch (IOException ex4) {}
            throw new OException("Cannot connect to the kernel.");
        }
        if (ex != null) {
            throw ex;
        }
        return receive;
    }
    
    public final boolean testOmegaStatus(final int n) {
        switch (n) {
            case 1: {
                return this.append;
            }
            case 3: {
                return this.alliberaConnexio;
            }
            case 4: {
                return this.charAt.testErrorsWarnings();
            }
            case 5: {
                return this.charAt.testGraphics();
            }
            default: {
                throw new OException("Estat no disponible.");
            }
        }
    }
    
    public final void setOmegaStatus(final int n, final boolean b) {
        switch (n) {
            case 1: {
                this.append = b;
            }
            case 3: {
                this.alliberaConnexio = b;
            }
            default: {
                throw new OException("Estat no disponible.");
            }
        }
    }
    
    public final Vector getErrorsWarnings() {
        return this.charAt.getErrorsWarnings();
    }
    
    public final Vector getGraphics() {
        return this.charAt.getGraphics();
    }
    
    public final String[] arreglaError(final String[] array, final String s) {
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = "";
        }
        int j;
        for (j = 0; j < array.length; ++j) {
            int length;
            int n;
            for (length = array[j].length(), n = 0; n < length && Character.isWhitespace(array[j].charAt(n)); ++n) {}
            if (n < length) {
                break;
            }
        }
        final String string = "<ms>" + s + "</ms>";
        if (j == array.length) {
            array2[0] = string;
        }
        else {
            array2[j] = string;
        }
        return array2;
    }
    
    public DataOutputStream iniciaConnexio() {
        return null;
    }
    
    public void alliberaConnexio() {
    }
    
    public InputStream getInputStream() {
        return null;
    }
    
    public final void startupIfNeeded() {
    }
    
    private static final void alliberaConnexio(final DataInputStream dataInputStream, final DataOutputStream dataOutputStream) {
        int i;
        do {
            i = dataInputStream.readInt();
            final int int1 = dataInputStream.readInt();
            dataOutputStream.writeInt(i);
            dataOutputStream.writeInt(int1);
            for (int j = 0; j < int1; ++j) {
                dataOutputStream.write(dataInputStream.readByte());
            }
        } while (i != 18);
    }
    
    static final ByteArrayInputStream I(final InputStream inputStream) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        alliberaConnexio(new DataInputStream(inputStream), new DataOutputStream(byteArrayOutputStream));
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }
    
    public final void setLanguage(final String language) {
        this.charAt.setLanguage(language);
    }
    
    public final void setDocumentBase(final URL url) {
        String string = null;
        if (url != null) {
            string = url.toString();
        }
        this.charAt.setDocumentBase(string);
    }
    
    public static final OmegaClient newOmega() {
        return newOmega(0);
    }
    
    public static final OmegaClient newOmega(final int n) {
        return newOmega(n, "en");
    }
    
    public static final OmegaClient newOmega(final int n, final String language) {
        if (n == 0) {
            final OmegaClientURL omegaClientURL = new OmegaClientURL(Configuration.url_calculate_exec());
            omegaClientURL.setLanguage(language);
            omegaClientURL.init();
            return omegaClientURL;
        }
        throw new OException("Unknown connection mode: " + n);
    }
    
    public final void setSeed(final int seed) {
        this.charAt.setSeed(seed);
    }
    
    protected final void append() {
    }
    
    public final boolean loading() {
        return false;
    }
    
    public final PropertyChangeSupport getPropertyChangeSupport() {
        return this.propertyChangeSupport;
    }
}
