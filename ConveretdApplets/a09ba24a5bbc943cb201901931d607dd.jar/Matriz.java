// 
// Decompiled by Procyon v0.5.30
// 

class Matriz
{
    private double[] valores;
    private Intervalo[] colunas;
    private Intervalo[] linhas;
    
    public Matriz(final String s, final String s2, final String s3) {
        final String[] split = Intervalo.split(s, ';');
        this.colunas = new Intervalo[split.length];
        for (int i = 0; i < split.length; ++i) {
            this.colunas[i] = new Intervalo(split[i]);
        }
        final String[] split2 = Intervalo.split(s2, ';');
        this.linhas = new Intervalo[split2.length];
        for (int j = 0; j < split2.length; ++j) {
            this.linhas[j] = new Intervalo(split2[j]);
        }
        String[] array;
        if (s3.indexOf(59) != -1) {
            array = Intervalo.split(s3, ';');
        }
        else {
            array = Intervalo.split(s3, '\t');
        }
        this.valores = new double[array.length];
        for (int k = 0; k < this.valores.length; ++k) {
            this.valores[k] = new Double(array[k]);
        }
    }
    
    public double getValue(final double n, final double n2) {
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i < this.colunas.length; ++i) {
            if (this.colunas[i].contains(n)) {
                n3 = i;
                break;
            }
        }
        for (int j = 0; j < this.linhas.length; ++j) {
            if (this.linhas[j].contains(n2)) {
                n4 = j;
                break;
            }
        }
        return this.valores[n4 * this.linhas.length + n3];
    }
}
