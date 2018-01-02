import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class Intervalo
{
    private Limite menor;
    private Limite maior;
    
    public Intervalo(final String s) throws IllegalArgumentException {
        final String[] split = split(s, ',');
        if (split.length != 2) {
            throw new IllegalArgumentException("O intervalo tem de ter 2 elementos!");
        }
        if (split[0].length() == 0) {
            this.menor = null;
        }
        else {
            this.menor = new Limite(split[0]);
        }
        if (split[1].length() == 0) {
            this.maior = null;
            return;
        }
        this.maior = new Limite(split[1]);
    }
    
    public boolean contains(final double n) {
        if (this.menor != null) {
            if (this.menor.fechado && n < this.menor.valor) {
                return false;
            }
            if (!this.menor.fechado && n <= this.menor.valor) {
                return false;
            }
        }
        if (this.maior != null) {
            if (this.maior.fechado && n > this.maior.valor) {
                return false;
            }
            if (!this.maior.fechado && n >= this.maior.valor) {
                return false;
            }
        }
        return true;
    }
    
    public static String[] split(String substring, final char c) {
        if (substring == null || substring.length() == 0) {
            return new String[0];
        }
        if (substring.indexOf(c) == -1) {
            return new String[] { substring };
        }
        final Vector vector = new Vector<String>();
        for (int i = substring.indexOf(c); i != -1; i = substring.indexOf(c)) {
            vector.addElement(substring.substring(0, i));
            substring = substring.substring(i + 1);
        }
        vector.addElement(substring);
        final String[] array = new String[vector.size()];
        for (int j = 0; j < array.length; ++j) {
            array[j] = vector.elementAt(j);
        }
        return array;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        if (this.menor != null) {
            sb.append(String.valueOf(this.menor.fechado ? "[" : "]") + this.menor.valor);
        }
        sb.append(" - ");
        if (this.maior != null) {
            sb.append(String.valueOf(this.maior.valor) + " " + (this.maior.fechado ? "[" : "]"));
        }
        return sb.toString();
    }
}
