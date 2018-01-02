// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.transforms;

import com.itt.J2KViewer.util.Matrix;
import com.itt.J2KViewer.georvm.coords.Gdc_Coord_3d;
import com.itt.J2KViewer.georvm.NITFGeoUtils;

public class RPC_Image_to_Geodetic
{
    private static final double conv_crit = 1.0E-4;
    private static final double perturbation = 1.0E-5;
    
    public Gdc_Coord_3d RPCImagetoGeo(final int n, final int n2, final NITFGeoUtils nitfGeoUtils) {
        final Gdc_Coord_3d gdc_Coord_3d = new Gdc_Coord_3d();
        double rpcLastLat = nitfGeoUtils.getRPCLastLat();
        double rpcLastLon = nitfGeoUtils.getRPCLastLon();
        final double n3 = nitfGeoUtils.OFFSETS[4];
        long n4 = 0L;
        double sqrt;
        do {
            ++n4;
            final double[] geo_to_image = this.geo_to_image(rpcLastLat, rpcLastLon, n3, nitfGeoUtils);
            final double n5 = n - geo_to_image[1];
            final double n6 = n2 - geo_to_image[0];
            sqrt = Math.sqrt(n6 * n6 + n5 * n5);
            if (sqrt >= 1.0E-4) {
                final double[] geo_to_image2 = this.geo_to_image(rpcLastLat + 1.0E-5, rpcLastLon, n3, nitfGeoUtils);
                final double[] geo_to_image3 = this.geo_to_image(rpcLastLat, rpcLastLon + 1.0E-5, n3, nitfGeoUtils);
                final Matrix times = new Matrix(new double[][] { { (geo_to_image2[0] - geo_to_image[0]) / 1.0E-5, (geo_to_image3[0] - geo_to_image[0]) / 1.0E-5 }, { (geo_to_image2[1] - geo_to_image[1]) / 1.0E-5, (geo_to_image3[1] - geo_to_image[1]) / 1.0E-5 } }).inverse().times(new Matrix(new double[][] { { n6 }, { n5 } }));
                final double value = times.get(0, 0);
                final double value2 = times.get(1, 0);
                rpcLastLat += value;
                rpcLastLon += value2;
            }
        } while (sqrt >= 1.0E-4 && n4 <= 50L);
        if (n4 > 50L && sqrt > 1.0E-4) {
            gdc_Coord_3d.latitude = -9999.0;
            gdc_Coord_3d.longitude = -9999.0;
            return gdc_Coord_3d;
        }
        nitfGeoUtils.setRPCLastLat(rpcLastLat);
        nitfGeoUtils.setRPCLastLon(rpcLastLon);
        gdc_Coord_3d.latitude = rpcLastLat;
        gdc_Coord_3d.longitude = rpcLastLon;
        return gdc_Coord_3d;
    }
    
    public double[] geo_to_image(final double n, final double n2, final double n3, final NITFGeoUtils nitfGeoUtils) {
        final double n4 = (n - nitfGeoUtils.OFFSETS[2]) / nitfGeoUtils.SCALES[2];
        final double n5 = (n2 - nitfGeoUtils.OFFSETS[3]) / nitfGeoUtils.SCALES[3];
        final double n6 = (n3 - nitfGeoUtils.OFFSETS[4]) / nitfGeoUtils.SCALES[4];
        final double n7 = n4 * n4;
        final double n8 = n5 * n5;
        final double n9 = n6 * n6;
        final double[] array = { n5, n4, n6, n5 * n4, n5 * n6, n4 * n6, n4 * n5 * n6, n8, n7, n9, n8 * n5, n8 * n4, n8 * n6, n5 * n7, n7 * n4, n7 * n6, n5 * n9, n4 * n9, n9 * n6 };
        final double[] array2 = { n5, n4, n6, n5 * n4, n5 * n6, n4 * n6, n8, n7, n9, n4 * n5 * n6, n8 * n5, n5 * n7, n5 * n9, n8 * n4, n7 * n4, n4 * n9, n8 * n6, n7 * n6, n9 * n6 };
        double[] array3;
        if ("RPC00A".equalsIgnoreCase(nitfGeoUtils.CETAG)) {
            array3 = array;
        }
        else {
            array3 = array2;
        }
        double n10 = (nitfGeoUtils.LNUMCOEF[0] + nitfGeoUtils.LNUMCOEF[1] * array3[0] + nitfGeoUtils.LNUMCOEF[2] * array3[1] + nitfGeoUtils.LNUMCOEF[3] * array3[2] + nitfGeoUtils.LNUMCOEF[4] * array3[3] + nitfGeoUtils.LNUMCOEF[5] * array3[4] + nitfGeoUtils.LNUMCOEF[6] * array3[5] + nitfGeoUtils.LNUMCOEF[7] * array3[6] + nitfGeoUtils.LNUMCOEF[8] * array3[7] + nitfGeoUtils.LNUMCOEF[9] * array3[8] + nitfGeoUtils.LNUMCOEF[10] * array3[9] + nitfGeoUtils.LNUMCOEF[11] * array3[10] + nitfGeoUtils.LNUMCOEF[12] * array3[11] + nitfGeoUtils.LNUMCOEF[13] * array3[12] + nitfGeoUtils.LNUMCOEF[14] * array3[13] + nitfGeoUtils.LNUMCOEF[15] * array3[14] + nitfGeoUtils.LNUMCOEF[16] * array3[15] + nitfGeoUtils.LNUMCOEF[17] * array3[16] + nitfGeoUtils.LNUMCOEF[18] * array3[17] + nitfGeoUtils.LNUMCOEF[19] * array3[18]) / (nitfGeoUtils.LDENCOEF[0] + nitfGeoUtils.LDENCOEF[1] * array3[0] + nitfGeoUtils.LDENCOEF[2] * array3[1] + nitfGeoUtils.LDENCOEF[3] * array3[2] + nitfGeoUtils.LDENCOEF[4] * array3[3] + nitfGeoUtils.LDENCOEF[5] * array3[4] + nitfGeoUtils.LDENCOEF[6] * array3[5] + nitfGeoUtils.LDENCOEF[7] * array3[6] + nitfGeoUtils.LDENCOEF[8] * array3[7] + nitfGeoUtils.LDENCOEF[9] * array3[8] + nitfGeoUtils.LDENCOEF[10] * array3[9] + nitfGeoUtils.LDENCOEF[11] * array3[10] + nitfGeoUtils.LDENCOEF[12] * array3[11] + nitfGeoUtils.LDENCOEF[13] * array3[12] + nitfGeoUtils.LDENCOEF[14] * array3[13] + nitfGeoUtils.LDENCOEF[15] * array3[14] + nitfGeoUtils.LDENCOEF[16] * array3[15] + nitfGeoUtils.LDENCOEF[17] * array3[16] + nitfGeoUtils.LDENCOEF[18] * array3[17] + nitfGeoUtils.LDENCOEF[19] * array3[18]) * nitfGeoUtils.SCALES[0] + nitfGeoUtils.OFFSETS[0] - nitfGeoUtils.l_off;
        double n11 = (nitfGeoUtils.PNUMCOEF[0] + nitfGeoUtils.PNUMCOEF[1] * array3[0] + nitfGeoUtils.PNUMCOEF[2] * array3[1] + nitfGeoUtils.PNUMCOEF[3] * array3[2] + nitfGeoUtils.PNUMCOEF[4] * array3[3] + nitfGeoUtils.PNUMCOEF[5] * array3[4] + nitfGeoUtils.PNUMCOEF[6] * array3[5] + nitfGeoUtils.PNUMCOEF[7] * array3[6] + nitfGeoUtils.PNUMCOEF[8] * array3[7] + nitfGeoUtils.PNUMCOEF[9] * array3[8] + nitfGeoUtils.PNUMCOEF[10] * array3[9] + nitfGeoUtils.PNUMCOEF[11] * array3[10] + nitfGeoUtils.PNUMCOEF[12] * array3[11] + nitfGeoUtils.PNUMCOEF[13] * array3[12] + nitfGeoUtils.PNUMCOEF[14] * array3[13] + nitfGeoUtils.PNUMCOEF[15] * array3[14] + nitfGeoUtils.PNUMCOEF[16] * array3[15] + nitfGeoUtils.PNUMCOEF[17] * array3[16] + nitfGeoUtils.PNUMCOEF[18] * array3[17] + nitfGeoUtils.PNUMCOEF[19] * array3[18]) / (nitfGeoUtils.PDENCOEF[0] + nitfGeoUtils.PDENCOEF[1] * array3[0] + nitfGeoUtils.PDENCOEF[2] * array3[1] + nitfGeoUtils.PDENCOEF[3] * array3[2] + nitfGeoUtils.PDENCOEF[4] * array3[3] + nitfGeoUtils.PDENCOEF[5] * array3[4] + nitfGeoUtils.PDENCOEF[6] * array3[5] + nitfGeoUtils.PDENCOEF[7] * array3[6] + nitfGeoUtils.PDENCOEF[8] * array3[7] + nitfGeoUtils.PDENCOEF[9] * array3[8] + nitfGeoUtils.PDENCOEF[10] * array3[9] + nitfGeoUtils.PDENCOEF[11] * array3[10] + nitfGeoUtils.PDENCOEF[12] * array3[11] + nitfGeoUtils.PDENCOEF[13] * array3[12] + nitfGeoUtils.PDENCOEF[14] * array3[13] + nitfGeoUtils.PDENCOEF[15] * array3[14] + nitfGeoUtils.PDENCOEF[16] * array3[15] + nitfGeoUtils.PDENCOEF[17] * array3[16] + nitfGeoUtils.PDENCOEF[18] * array3[17] + nitfGeoUtils.PDENCOEF[19] * array3[18]) * nitfGeoUtils.SCALES[1] + nitfGeoUtils.OFFSETS[1] - nitfGeoUtils.p_off;
        if ("RPC00A".equalsIgnoreCase(nitfGeoUtils.CETAG)) {
            n11 -= nitfGeoUtils.xstart;
            n10 -= nitfGeoUtils.ystart;
        }
        return new double[] { n10, n11 };
    }
}
