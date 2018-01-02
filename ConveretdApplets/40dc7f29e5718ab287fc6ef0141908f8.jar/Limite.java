// 
// Decompiled by Procyon v0.5.30
// 

class Limite
{
    public double valor;
    public boolean fechado;
    
    public Limite(String s) {
        this.fechado = true;
        final char char1 = s.charAt(0);
        if (char1 == '[' || char1 == ']') {
            if (char1 == '[') {
                this.fechado = true;
            }
            else if (char1 == ']') {
                this.fechado = false;
            }
            s = s.substring(1);
        }
        else {
            final char char2 = s.charAt(s.length() - 1);
            if (char2 == '[' || char2 == ']') {
                if (char2 == '[') {
                    this.fechado = true;
                }
                else if (char2 == ']') {
                    this.fechado = false;
                }
            }
            s = s.substring(0, s.length() - 1);
        }
        this.valor = new Double(s);
    }
}
