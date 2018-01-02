// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

public class h
{
    public static final int a = 9;
    public static final int do = 8;
    public static final int case = 7;
    public static final int int = 6;
    public static final int new = 5;
    public static final int byte = 4;
    public static final int for = 3;
    public static final int if = 2;
    public static final int try = 1;
    
    public static ChartBody a(final int n, final DataSource dataSource, final be be) {
        ChartBody chartBody = null;
        switch (n) {
            case 1: {
                chartBody = new aw(be, dataSource);
                break;
            }
            case 2: {
                chartBody = new bj(be, dataSource);
                break;
            }
            case 3: {
                chartBody = new c(be, dataSource);
                break;
            }
            case 4: {
                chartBody = new a6(be, dataSource);
                break;
            }
            case 5: {
                chartBody = new n(be, dataSource);
                break;
            }
            case 6: {
                chartBody = new bg(be, dataSource);
                break;
            }
            case 7: {
                chartBody = new x(be, dataSource);
                break;
            }
            case 8: {
                chartBody = new bb(be, dataSource);
                break;
            }
            case 9: {
                chartBody = new ab(be, dataSource);
                break;
            }
            default: {
                chartBody = new aw(be, dataSource);
                break;
            }
        }
        return chartBody;
    }
}
