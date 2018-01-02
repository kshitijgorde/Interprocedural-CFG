// 
// Decompiled by Procyon v0.5.30
// 

public enum SensorStatus
{
    NAPP(true, true), 
    NHAP_BW(true, true), 
    NHAP_CIR(true, true), 
    ASTER_VNIR(true, true), 
    ASTER_TIR(true, true), 
    ASTER_VNIR_DATAPOOL(true, true), 
    ASTER_TIR_DATAPOOL(true, true), 
    EO1_ALI(true, true), 
    EO1_HYP(true, true), 
    LANDSAT_ETM_COMBINED(false, true), 
    LANDSAT_ETM_SLC_OFF(true, true), 
    LANDSAT_ETM(true, true), 
    LANDSAT_TM(true, true), 
    LANDSAT_4_5MSS(true, true), 
    LANDSAT_1_3MSS(true, true), 
    LANDSAT_COMBINED(true, true), 
    GLS2010(true, true), 
    GLS2005(true, true), 
    GLS2005_EO1(true, true), 
    GLS2005_ANTARCTICA(false, true), 
    GLS2000(true, true), 
    GLS1990(true, true), 
    GLS1975_4_5MSS(true, true), 
    GLS1975_1_3MSS(true, true), 
    MRLC_2001TC(true, true), 
    MRLC_2001RA(true, true), 
    ETM_MOSAIC(true, true), 
    TM_MOSAIC(true, true), 
    PANSHARP_ETM_PLUS(true, true), 
    ORTHO_ETM_PLUS(true, true), 
    ORTHO_TM(true, true), 
    ORTHO4_5MSS(true, true), 
    ORTHO1_3MSS(true, true), 
    ANTARCTICA_ETM(false, true), 
    SYSTEMATIC_L1G(true, true), 
    NALC(true, true), 
    MODIS_MYD09A1(true, true), 
    MODIS_MYD09GA(true, true), 
    MODIS_MYD09GQ(true, true), 
    MODIS_MYD09Q1(true, true), 
    MODIS_MYD11A1_DAY(true, true), 
    MODIS_MYD11A1_NIGHT(true, true), 
    MODIS_MYD11A2_DAY(true, true), 
    MODIS_MYD11A2_NIGHT(true, true), 
    MODIS_MYD11B1_DAY(true, true), 
    MODIS_MYD11B1_NIGHT(true, true), 
    MODIS_MYD13A1_EVI(true, true), 
    MODIS_MYD13A1_NDVI(true, true), 
    MODIS_MYD13A2_EVI(true, true), 
    MODIS_MYD13A2_NDVI(true, true), 
    MODIS_MYD13A3_EVI(true, true), 
    MODIS_MYD13A3_NDVI(true, true), 
    MODIS_MYD13Q1_EVI(true, true), 
    MODIS_MYD13Q1_NDVI(true, true), 
    MODIS_MYD14A1(true, true), 
    MODIS_MYD14A2(true, true), 
    MODIS_MYD15A2_FPAR(true, true), 
    MODIS_MYD15A2_LAI(true, true), 
    MODIS_MYD17A2_GPP(true, true), 
    MODIS_MYD17A2_NETPSN(true, true), 
    MODIS_MOD09A1(true, true), 
    MODIS_MOD09GA(true, true), 
    MODIS_MOD09GQ(true, true), 
    MODIS_MOD09Q1(true, true), 
    MODIS_MOD11A1_DAY(true, true), 
    MODIS_MOD11A1_NIGHT(true, true), 
    MODIS_MOD11A2_DAY(true, true), 
    MODIS_MOD11A2_NIGHT(true, true), 
    MODIS_MOD11B1_DAY(true, true), 
    MODIS_MOD11B1_NIGHT(true, true), 
    MODIS_MOD13A1_EVI(true, true), 
    MODIS_MOD13A1_NDVI(true, true), 
    MODIS_MOD13A2_EVI(true, true), 
    MODIS_MOD13A2_NDVI(true, true), 
    MODIS_MOD13A3_EVI(true, true), 
    MODIS_MOD13A3_NDVI(true, true), 
    MODIS_MOD13Q1_EVI(true, true), 
    MODIS_MOD13Q1_NDVI(true, true), 
    MODIS_MOD14A1(true, true), 
    MODIS_MOD14A2(true, true), 
    MODIS_MOD15A2_FPAR(true, true), 
    MODIS_MOD15A2_LAI(true, true), 
    MODIS_MOD17A2_GPP(true, true), 
    MODIS_MOD17A2_NETPSN(true, true), 
    MODIS_MOD43B1(false, true), 
    MODIS_MOD43B3(false, true), 
    MODIS_MOD43B4(false, true), 
    MODIS_MCD15A2_FPAR(true, true), 
    MODIS_MCD15A2_LAI(true, true), 
    MODIS_MCD43A1(true, true), 
    MODIS_MCD43A2(true, true), 
    MODIS_MCD43A3(true, true), 
    MODIS_MCD43A4(true, true), 
    MODIS_MCD43B1(true, true), 
    MODIS_MCD43B2(true, true), 
    MODIS_MCD43B3(true, true), 
    MODIS_MCD43B4(true, true), 
    TERRALOOK_ASTER_VNIR(true, true), 
    TERRALOOK_GLS2005(true, true), 
    TERRALOOK_GLS2000(true, true), 
    TERRALOOK_GLS1990(true, true), 
    TERRALOOK_GLS1975_L4_5(true, true), 
    TERRALOOK_GLS1975_L1_3(true, true);
    
    private final boolean isUsed;
    private boolean canSendToCart;
    private int sensorMenuIndex;
    
    private SensorStatus(final boolean isUsed, final boolean canSendToCart) {
        this.isUsed = isUsed;
        this.canSendToCart = canSendToCart;
        this.sensorMenuIndex = -1;
    }
    
    boolean isUsed() {
        return this.isUsed;
    }
    
    boolean canSendToCart() {
        return this.canSendToCart;
    }
    
    int sensorMenuIndex() {
        return this.sensorMenuIndex;
    }
    
    void setSensorMenuIndex(final int sensorMenuIndex) {
        this.sensorMenuIndex = sensorMenuIndex;
    }
}
