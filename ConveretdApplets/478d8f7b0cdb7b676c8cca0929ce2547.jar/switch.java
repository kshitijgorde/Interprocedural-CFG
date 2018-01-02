// 
// Decompiled by Procyon v0.5.30
// 

class switch implements Runnable
{
    private int R;
    private final protected S;
    private static String T = "\u93be\u9382\u9398\u938f\u938b\u938e\u93ca\u938f\u9392\u938f\u9389\u939f\u939e\u9383\u9384\u938d\u93c4\u93c4\u93c4";
    private static String U = "\u93be\u9382\u938f\u93ca\u9389\u9386\u9385\u9389\u9381\u93ca\u939e\u9383\u9387\u938f\u93ca\u9382\u938b\u9399\u93ca\u9389\u9382\u938b\u9384\u938d\u938f\u938e\u93d0\u93ca";
    private static String V = "\u93b9\u9386\u938f\u938f\u939a\u93ca\u9383\u9384\u939e\u938f\u9398\u9398\u939f\u939a\u939e\u938f\u938e\u93d0\u93ca";
    private static String W = "\u93ca\u93c7\u93ca";
    
    private switch(final protected s) {
        this.S = s;
        this.R = -1;
    }
    
    public void run() {
        while (Thread.currentThread() == Thread.currentThread()) {
            this.b(switch.T);
            if (this.S._() != this.R) {
                final static static1 = new static(1);
                static1._(protected.b(this.S));
                static1.a(protected._(this.S));
                static1.b(this.S.a());
                static1.h(this.S._());
                static1.i(this.S.b());
                static1._(this.S.a());
                static1.a(this.S.b());
                static1.h(this.S._());
                this.b(switch.U.concat(String.valueOf(String.valueOf(static1.toString()))));
                this._(static1);
                this.R = static1._();
            }
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {
                this.b(switch.V.concat(String.valueOf(String.valueOf(ex.toString()))));
            }
            this.S.reset();
        }
    }
    
    private void _(final static static1) {
        protected.a(this.S).a(static1);
    }
    
    private void b(final String s) {
        if (protected._(this.S)) {
            System.out.println(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.i()))).append(switch.W).append(s))));
        }
    }
    
    private String i() {
        return this.getClass().getName();
    }
    
    switch(final protected protected1, final synchronized synchronized1) {
        this(protected1);
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFE93EA);
        }
        return new String(array);
    }
    
    static {
        switch.T = _(switch.T);
        switch.U = _(switch.U);
        switch.V = _(switch.V);
        switch.W = _(switch.W);
    }
}
