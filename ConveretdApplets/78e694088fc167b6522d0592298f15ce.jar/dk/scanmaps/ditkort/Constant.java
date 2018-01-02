// 
// Decompiled by Procyon v0.5.30
// 

package dk.scanmaps.ditkort;

import java.awt.Dimension;

public class Constant
{
    public static final String DITKORT_VARENR = "0935500";
    public static final String DITKORT_VARENR_DTK100 = "0935600";
    public static final String DITKORT_VARENR_1870H = "0935002";
    public static final String DITKORT_VARENR_1928L = "0935003";
    public static final String DITKORT_VARENR_1953 = "0935004";
    public static final String DITKORT_VARENR_1953_oversigt = "0935006";
    public static final String DITKORT_VARENR_1980 = "0935005";
    public static float DANMARKSKORT_GRIDSIZE_X;
    public static float DANMARKSKORT_GRIDSIZE_Y;
    public static float DANMARKSKORT_X;
    public static float DANMARKSKORT_Y;
    public static float BORNHOLM_X_25;
    public static float BORNHOLM_Y_25;
    public static float BORNHOLM_X_100;
    public static float BORNHOLM_Y_100;
    public static float LAESOE_X_100;
    public static float LAESOE_Y_100;
    public static float LAESOE_X_25;
    public static float LAESOE_Y_25;
    public static float ANHOLT_X;
    public static float ANHOLT_Y;
    public static float SKAGEN_X_100;
    public static float SKAGEN_Y_100;
    public static boolean debugMode;
    public static boolean coordsValues;
    public static boolean enableBorderLines;
    public static String base;
    public static String baseEgnskortURL;
    public static String imageURL;
    public static Dimension appletFrameDim;
    public static int DEFAULT_HEIGHT;
    public static String servicename_DTK100;
    public static String servicename_H1870;
    public static String servicename_L1928;
    public static String servicename_DTK25;
    public static String servicename_1953;
    public static String servicename_1980;
    public static String layerName_DTK100;
    public static String layerName_H1870;
    public static String layerName_L1928;
    public static String layerName_DTK25;
    public static String layerName_1953;
    public static String layerName_1980;
    public static String imageFormat;
    public static int DTK25;
    public static int DTK100;
    public static int H1870;
    public static int L1928;
    public static int D25_1953;
    public static int D25_1980;
    
    static {
        Constant.DANMARKSKORT_GRIDSIZE_X = 23.41f;
        Constant.DANMARKSKORT_GRIDSIZE_Y = 18.75f;
        Constant.DANMARKSKORT_X = 437100.0f;
        Constant.DANMARKSKORT_Y = 6403700.0f;
        Constant.BORNHOLM_X_25 = 862914.0f;
        Constant.BORNHOLM_Y_25 = 6149434.0f;
        Constant.BORNHOLM_X_100 = 871700.0f;
        Constant.BORNHOLM_Y_100 = 6131075.0f;
        Constant.LAESOE_X_100 = 639500.0f;
        Constant.LAESOE_Y_100 = 6341825.0f;
        Constant.LAESOE_X_25 = 618030.0f;
        Constant.LAESOE_Y_25 = 6353966.0f;
        Constant.ANHOLT_X = 657626.0f;
        Constant.ANHOLT_Y = 6288134.0f;
        Constant.SKAGEN_X_100 = 571050.0f;
        Constant.SKAGEN_Y_100 = 6386825.0f;
        Constant.debugMode = false;
        Constant.coordsValues = false;
        Constant.enableBorderLines = false;
        Constant.base = "http://www.scanmaps.dk/ditkort/danmark";
        Constant.baseEgnskortURL = String.valueOf(Constant.base) + "/egnskort";
        Constant.imageURL = String.valueOf(Constant.base) + "/images/";
        Constant.appletFrameDim = new Dimension(873, 740);
        Constant.DEFAULT_HEIGHT = 450;
        Constant.servicename_DTK100 = "topo100";
        Constant.servicename_H1870 = "topo20_hoeje_maalebordsblade";
        Constant.servicename_L1928 = "topo20_lave_maalebordsblade";
        Constant.servicename_DTK25 = "print_topo25";
        Constant.servicename_1953 = "topo4cm_1953_1976";
        Constant.servicename_1980 = "topo4cm_1980_2001";
        Constant.layerName_DTK100 = "dtk_1cm";
        Constant.layerName_H1870 = "dtk_hoeje_maalebordsblade";
        Constant.layerName_L1928 = "dtk_lave_maalebordsblade";
        Constant.layerName_DTK25 = "dtk_kort25_klassisk_254dpi";
        Constant.layerName_1953 = "dtk_4cm_1953_1976";
        Constant.layerName_1980 = "dtk_4cm_1980_2001";
        Constant.imageFormat = "image/jpeg";
        Constant.DTK25 = 0;
        Constant.DTK100 = 1;
        Constant.H1870 = 2;
        Constant.L1928 = 3;
        Constant.D25_1953 = 4;
        Constant.D25_1980 = 5;
    }
}
