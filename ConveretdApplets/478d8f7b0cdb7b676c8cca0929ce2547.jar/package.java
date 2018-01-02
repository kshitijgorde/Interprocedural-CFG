// 
// Decompiled by Procyon v0.5.30
// 

class package implements Runnable
{
    private final null S;
    private static String T = "\u7fb2\u7f9f\u7f91\u7f9f\u7f82\u7f97\u7f9a\u7fd6\u7f92\u7f9f\u7f85\u7f86\u7f9a\u7f97\u7f8f\u7fd6\u7f9f\u7f85\u7fd6\u7f85\u7f93\u7f82\u7fd6\u7f82\u7f99\u7fd6\u7f85\u7f9e\u7f99\u7f81\u7fd6\u7f82\u7f9f\u7f9b\u7f93\u7fd7";
    private static String U = "\u7fb2\u7f9f\u7f91\u7f9f\u7f82\u7f97\u7f9a\u7fd6\u7f92\u7f9f\u7f85\u7f86\u7f9a\u7f97\u7f8f\u7fd6\u7f9f\u7f85\u7fd6\u7f85\u7f93\u7f82\u7fd6\u7f82\u7f99\u7fd6\u7f85\u7f9e\u7f99\u7f81\u7fd6\u7f92\u7f97\u7f82\u7f93\u7fd7";
    
    package(final null s) {
        this.S = s;
    }
    
    public void run() {
        while (Thread.currentThread() == Thread.currentThread()) {
            try {
                Thread.sleep(null._(this.S));
                while (null.b(this.S)) {
                    Thread.sleep(1L);
                }
            }
            catch (InterruptedException ex) {}
            null._(this.S, -1);
            if (null.a(this.S) == 1) {
                null._(this.S, package.T);
            }
            else {
                null._(this.S, package.U);
            }
        }
    }
    
    private static String k(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x17FF6);
        }
        return new String(array);
    }
    
    static {
        package.T = k(package.T);
        package.U = k(package.U);
    }
}
