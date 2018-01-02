// 
// Decompiled by Procyon v0.5.30
// 

package menu;

class c
{
    private String a(String lowerCase) {
        final int n = 20;
        lowerCase = lowerCase.toLowerCase();
        final char[] array = new char[n];
        final int length = lowerCase.length();
        char c = '\0';
        final int n2 = 0;
        for (int i = 0; i < length; c += lowerCase.charAt(i++)) {}
        int n3 = 0;
        array[0] = 'N';
        array[1] = 'F';
        int j = 2;
        int abs = 0;
        while (j < n) {
            final int n4 = (Math.abs(abs) + lowerCase.charAt(n3)) % 10;
            if (n4 < 10) {
                array[j] = (char)(n4 + 48);
            }
            else if (n4 < 34) {
                array[j] = (char)(n4 - 10 + 97);
            }
            else {
                array[j] = (char)(n4 - 34 + 65);
            }
            ++j;
            if (++n3 >= length) {
                n3 = n2;
                abs = Math.abs(j);
            }
        }
        return new String(array);
    }
    
    boolean a(final String s, final String s2) {
        if (s2.equals("")) {
            return true;
        }
        if (s != null && this.a(s2).equals(s)) {
            System.out.println("Registreted Version.");
            return true;
        }
        return false;
    }
}
