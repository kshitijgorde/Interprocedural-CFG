// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import org.eclipse.swt.graphics.ImageLoaderEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import java.io.InputStream;
import java.io.IOException;
import org.eclipse.swt.SWT;

public class JPEGDecoder
{
    static final int DCTSIZE = 8;
    static final int DCTSIZE2 = 64;
    static final int NUM_QUANT_TBLS = 4;
    static final int NUM_HUFF_TBLS = 4;
    static final int NUM_ARITH_TBLS = 16;
    static final int MAX_COMPS_IN_SCAN = 4;
    static final int MAX_COMPONENTS = 10;
    static final int MAX_SAMP_FACTOR = 4;
    static final int D_MAX_BLOCKS_IN_MCU = 10;
    static final int HUFF_LOOKAHEAD = 8;
    static final int MAX_Q_COMPS = 4;
    static final int IFAST_SCALE_BITS = 2;
    static final int MAXJSAMPLE = 255;
    static final int CENTERJSAMPLE = 128;
    static final int MIN_GET_BITS = 25;
    static final int INPUT_BUFFER_SIZE = 4096;
    static final int SCALEBITS = 16;
    static final int ONE_HALF = 32768;
    static final int RGB_RED = 2;
    static final int RGB_GREEN = 1;
    static final int RGB_BLUE = 0;
    static final int RGB_PIXELSIZE = 3;
    static final int JBUF_PASS_THRU = 0;
    static final int JBUF_SAVE_SOURCE = 1;
    static final int JBUF_CRANK_DEST = 2;
    static final int JBUF_SAVE_AND_PASS = 3;
    static final int JPEG_MAX_DIMENSION = 65500;
    static final int BITS_IN_JSAMPLE = 8;
    static final int JDITHER_NONE = 0;
    static final int JDITHER_ORDERED = 1;
    static final int JDITHER_FS = 2;
    static final int JDCT_ISLOW = 0;
    static final int JDCT_IFAST = 1;
    static final int JDCT_FLOAT = 2;
    static final int JDCT_DEFAULT = 0;
    static final int JCS_UNKNOWN = 0;
    static final int JCS_GRAYSCALE = 1;
    static final int JCS_RGB = 2;
    static final int JCS_YCbCr = 3;
    static final int JCS_CMYK = 4;
    static final int JCS_YCCK = 5;
    static final int SAVED_COEFS = 6;
    static final int Q01_POS = 1;
    static final int Q10_POS = 8;
    static final int Q20_POS = 16;
    static final int Q11_POS = 9;
    static final int Q02_POS = 2;
    static final int CTX_PREPARE_FOR_IMCU = 0;
    static final int CTX_PROCESS_IMCU = 1;
    static final int CTX_POSTPONED_ROW = 2;
    static final int APP0_DATA_LEN = 14;
    static final int APP14_DATA_LEN = 12;
    static final int APPN_DATA_LEN = 14;
    static final int M_SOF0 = 192;
    static final int M_SOF1 = 193;
    static final int M_SOF2 = 194;
    static final int M_SOF3 = 195;
    static final int M_SOF5 = 197;
    static final int M_SOF6 = 198;
    static final int M_SOF7 = 199;
    static final int M_JPG = 200;
    static final int M_SOF9 = 201;
    static final int M_SOF10 = 202;
    static final int M_SOF11 = 203;
    static final int M_SOF13 = 205;
    static final int M_SOF14 = 206;
    static final int M_SOF15 = 207;
    static final int M_DHT = 196;
    static final int M_DAC = 204;
    static final int M_RST0 = 208;
    static final int M_RST1 = 209;
    static final int M_RST2 = 210;
    static final int M_RST3 = 211;
    static final int M_RST4 = 212;
    static final int M_RST5 = 213;
    static final int M_RST6 = 214;
    static final int M_RST7 = 215;
    static final int M_SOI = 216;
    static final int M_EOI = 217;
    static final int M_SOS = 218;
    static final int M_DQT = 219;
    static final int M_DNL = 220;
    static final int M_DRI = 221;
    static final int M_DHP = 222;
    static final int M_EXP = 223;
    static final int M_APP0 = 224;
    static final int M_APP1 = 225;
    static final int M_APP2 = 226;
    static final int M_APP3 = 227;
    static final int M_APP4 = 228;
    static final int M_APP5 = 229;
    static final int M_APP6 = 230;
    static final int M_APP7 = 231;
    static final int M_APP8 = 232;
    static final int M_APP9 = 233;
    static final int M_APP10 = 234;
    static final int M_APP11 = 235;
    static final int M_APP12 = 236;
    static final int M_APP13 = 237;
    static final int M_APP14 = 238;
    static final int M_APP15 = 239;
    static final int M_JPG0 = 240;
    static final int M_JPG13 = 253;
    static final int M_COM = 254;
    static final int M_TEM = 1;
    static final int M_ERROR = 256;
    static final int CSTATE_START = 100;
    static final int CSTATE_SCANNING = 101;
    static final int CSTATE_RAW_OK = 102;
    static final int CSTATE_WRCOEFS = 103;
    static final int DSTATE_START = 200;
    static final int DSTATE_INHEADER = 201;
    static final int DSTATE_READY = 202;
    static final int DSTATE_PRELOAD = 203;
    static final int DSTATE_PRESCAN = 204;
    static final int DSTATE_SCANNING = 205;
    static final int DSTATE_RAW_OK = 206;
    static final int DSTATE_BUFIMAGE = 207;
    static final int DSTATE_BUFPOST = 208;
    static final int DSTATE_RDCOEFS = 209;
    static final int DSTATE_STOPPING = 210;
    static final int JPEG_REACHED_SOS = 1;
    static final int JPEG_REACHED_EOI = 2;
    static final int JPEG_ROW_COMPLETED = 3;
    static final int JPEG_SCAN_COMPLETED = 4;
    static final int JPEG_SUSPENDED = 0;
    static final int JPEG_HEADER_OK = 1;
    static final int JPEG_HEADER_TABLES_ONLY = 2;
    static final int DECOMPRESS_DATA = 0;
    static final int DECOMPRESS_SMOOTH_DATA = 1;
    static final int DECOMPRESS_ONEPASS = 2;
    static final int CONSUME_DATA = 0;
    static final int DUMMY_CONSUME_DATA = 1;
    static final int PROCESS_DATA_SIMPLE_MAIN = 0;
    static final int PROCESS_DATA_CONTEXT_MAIN = 1;
    static final int PROCESS_DATA_CRANK_POST = 2;
    static final int POST_PROCESS_1PASS = 0;
    static final int POST_PROCESS_DATA_UPSAMPLE = 1;
    static final int NULL_CONVERT = 0;
    static final int GRAYSCALE_CONVERT = 1;
    static final int YCC_RGB_CONVERT = 2;
    static final int GRAY_RGB_CONVERT = 3;
    static final int YCCK_CMYK_CONVERT = 4;
    static final int NOOP_UPSAMPLE = 0;
    static final int FULLSIZE_UPSAMPLE = 1;
    static final int H2V1_FANCY_UPSAMPLE = 2;
    static final int H2V1_UPSAMPLE = 3;
    static final int H2V2_FANCY_UPSAMPLE = 4;
    static final int H2V2_UPSAMPLE = 5;
    static final int INT_UPSAMPLE = 6;
    static final int INPUT_CONSUME_INPUT = 0;
    static final int COEF_CONSUME_INPUT = 1;
    static int[] extend_test;
    static int[] extend_offset;
    static int[] jpeg_natural_order;
    static final int CONST_BITS = 13;
    static final int PASS1_BITS = 2;
    static final int RANGE_MASK = 1023;
    
    static {
        JPEGDecoder.extend_test = new int[] { 0, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384 };
        JPEGDecoder.extend_offset = new int[] { 0, -1, -3, -7, -15, -31, -63, -127, -255, -511, -1023, -2047, -4095, -8191, -16383, -32767 };
        JPEGDecoder.jpeg_natural_order = new int[] { 0, 1, 8, 16, 9, 2, 3, 10, 17, 24, 32, 25, 18, 11, 4, 5, 12, 19, 26, 33, 40, 48, 41, 34, 27, 20, 13, 6, 7, 14, 21, 28, 35, 42, 49, 56, 57, 50, 43, 36, 29, 22, 15, 23, 30, 37, 44, 51, 58, 59, 52, 45, 38, 31, 39, 46, 53, 60, 61, 54, 47, 55, 62, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63 };
    }
    
    static void error() {
        SWT.error(40);
    }
    
    static void error(final int n) {
        SWT.error(n);
    }
    
    static void error(final String s) {
        SWT.error(40, null, s);
    }
    
    static void jinit_marker_reader(final jpeg_decompress_struct jpeg_decompress_struct) {
        final jpeg_marker_reader marker = new jpeg_marker_reader();
        jpeg_decompress_struct.marker = marker;
        marker.length_limit_COM = 0;
        reset_marker_reader(jpeg_decompress_struct);
    }
    
    static void jinit_d_coef_controller(final jpeg_decompress_struct jpeg_decompress_struct, final boolean b) {
        final jpeg_d_coef_controller coef = new jpeg_d_coef_controller();
        jpeg_decompress_struct.coef = coef;
        coef.coef_bits_latch = null;
        if (b) {
            for (int i = 0; i < jpeg_decompress_struct.num_components; ++i) {
                final jpeg_component_info jpeg_component_info = jpeg_decompress_struct.comp_info[i];
                coef.whole_image[i] = new short[(int)jround_up(jpeg_component_info.height_in_blocks, jpeg_component_info.v_samp_factor)][(int)jround_up(jpeg_component_info.width_in_blocks, jpeg_component_info.h_samp_factor)][64];
            }
            coef.decompress_data = 0;
            coef.coef_arrays = coef.whole_image[0];
        }
        else {
            coef.MCU_buffer = new short[10][64];
            coef.decompress_data = 2;
            coef.coef_arrays = null;
        }
    }
    
    static void start_output_pass(final jpeg_decompress_struct jpeg_decompress_struct) {
        final jpeg_d_coef_controller coef = jpeg_decompress_struct.coef;
        if (coef.coef_arrays != null) {
            if (jpeg_decompress_struct.do_block_smoothing && smoothing_ok(jpeg_decompress_struct)) {
                coef.decompress_data = 1;
            }
            else {
                coef.decompress_data = 0;
            }
        }
        jpeg_decompress_struct.output_iMCU_row = 0;
    }
    
    static void jpeg_create_decompress(final jpeg_decompress_struct jpeg_decompress_struct) {
        jpeg_decompress_struct.is_decompressor = true;
        jpeg_decompress_struct.marker_list = null;
        jinit_marker_reader(jpeg_decompress_struct);
        jinit_input_controller(jpeg_decompress_struct);
        jpeg_decompress_struct.global_state = 200;
    }
    
    static void jpeg_calc_output_dimensions(final jpeg_decompress_struct jpeg_decompress_struct) {
        if (jpeg_decompress_struct.global_state != 202) {
            error();
        }
        jpeg_decompress_struct.output_width = jpeg_decompress_struct.image_width;
        jpeg_decompress_struct.output_height = jpeg_decompress_struct.image_height;
        switch (jpeg_decompress_struct.out_color_space) {
            case 1: {
                jpeg_decompress_struct.out_color_components = 1;
                break;
            }
            case 2:
            case 3: {
                jpeg_decompress_struct.out_color_components = 3;
                break;
            }
            case 4:
            case 5: {
                jpeg_decompress_struct.out_color_components = 4;
                break;
            }
            default: {
                jpeg_decompress_struct.out_color_components = jpeg_decompress_struct.num_components;
                break;
            }
        }
        jpeg_decompress_struct.output_components = (jpeg_decompress_struct.quantize_colors ? 1 : jpeg_decompress_struct.out_color_components);
        if (use_merged_upsample(jpeg_decompress_struct)) {
            jpeg_decompress_struct.rec_outbuf_height = jpeg_decompress_struct.max_v_samp_factor;
        }
        else {
            jpeg_decompress_struct.rec_outbuf_height = 1;
        }
    }
    
    static boolean use_merged_upsample(final jpeg_decompress_struct jpeg_decompress_struct) {
        return !jpeg_decompress_struct.do_fancy_upsampling && !jpeg_decompress_struct.CCIR601_sampling && jpeg_decompress_struct.jpeg_color_space == 3 && jpeg_decompress_struct.num_components == 3 && jpeg_decompress_struct.out_color_space == 2 && jpeg_decompress_struct.out_color_components == 3 && jpeg_decompress_struct.comp_info[0].h_samp_factor == 2 && jpeg_decompress_struct.comp_info[1].h_samp_factor == 1 && jpeg_decompress_struct.comp_info[2].h_samp_factor == 1 && jpeg_decompress_struct.comp_info[0].v_samp_factor <= 2 && jpeg_decompress_struct.comp_info[1].v_samp_factor == 1 && jpeg_decompress_struct.comp_info[2].v_samp_factor == 1 && jpeg_decompress_struct.comp_info[0].DCT_scaled_size == jpeg_decompress_struct.min_DCT_scaled_size && jpeg_decompress_struct.comp_info[1].DCT_scaled_size == jpeg_decompress_struct.min_DCT_scaled_size && jpeg_decompress_struct.comp_info[2].DCT_scaled_size == jpeg_decompress_struct.min_DCT_scaled_size;
    }
    
    static void prepare_range_limit_table(final jpeg_decompress_struct jpeg_decompress_struct) {
        final byte[] sample_range_limit = new byte[1408];
        int sample_range_limit_offset = 256;
        jpeg_decompress_struct.sample_range_limit_offset = sample_range_limit_offset;
        jpeg_decompress_struct.sample_range_limit = sample_range_limit;
        for (int i = 0; i <= 255; ++i) {
            sample_range_limit[i + sample_range_limit_offset] = (byte)i;
        }
        sample_range_limit_offset += 128;
        for (int j = 128; j < 512; ++j) {
            sample_range_limit[j + sample_range_limit_offset] = -1;
        }
        System.arraycopy(jpeg_decompress_struct.sample_range_limit, jpeg_decompress_struct.sample_range_limit_offset, sample_range_limit, sample_range_limit_offset + 896, 128);
    }
    
    static void build_ycc_rgb_table(final jpeg_decompress_struct jpeg_decompress_struct) {
        final jpeg_color_deconverter cconvert = jpeg_decompress_struct.cconvert;
        cconvert.Cr_r_tab = new int[256];
        cconvert.Cb_b_tab = new int[256];
        cconvert.Cr_g_tab = new int[256];
        cconvert.Cb_g_tab = new int[256];
        for (int i = 0, n = -128; i <= 255; ++i, ++n) {
            cconvert.Cr_r_tab[i] = 91881 * n + 32768 >> 16;
            cconvert.Cb_b_tab[i] = 116130 * n + 32768 >> 16;
            cconvert.Cr_g_tab[i] = -46802 * n;
            cconvert.Cb_g_tab[i] = -22554 * n + 32768;
        }
    }
    
    static void jinit_color_deconverter(final jpeg_decompress_struct jpeg_decompress_struct) {
        final jpeg_color_deconverter cconvert = new jpeg_color_deconverter();
        jpeg_decompress_struct.cconvert = cconvert;
        final jpeg_color_deconverter jpeg_color_deconverter = cconvert;
        switch (jpeg_decompress_struct.jpeg_color_space) {
            case 1: {
                if (jpeg_decompress_struct.num_components != 1) {
                    error();
                    break;
                }
                break;
            }
            case 2:
            case 3: {
                if (jpeg_decompress_struct.num_components != 3) {
                    error();
                    break;
                }
                break;
            }
            case 4:
            case 5: {
                if (jpeg_decompress_struct.num_components != 4) {
                    error();
                    break;
                }
                break;
            }
            default: {
                if (jpeg_decompress_struct.num_components < 1) {
                    error();
                    break;
                }
                break;
            }
        }
        switch (jpeg_decompress_struct.out_color_space) {
            case 1: {
                jpeg_decompress_struct.out_color_components = 1;
                if (jpeg_decompress_struct.jpeg_color_space == 1 || jpeg_decompress_struct.jpeg_color_space == 3) {
                    jpeg_color_deconverter.color_convert = 1;
                    for (int i = 1; i < jpeg_decompress_struct.num_components; ++i) {
                        jpeg_decompress_struct.comp_info[i].component_needed = false;
                    }
                    break;
                }
                error();
                break;
            }
            case 2: {
                jpeg_decompress_struct.out_color_components = 3;
                if (jpeg_decompress_struct.jpeg_color_space == 3) {
                    jpeg_color_deconverter.color_convert = 2;
                    build_ycc_rgb_table(jpeg_decompress_struct);
                    break;
                }
                if (jpeg_decompress_struct.jpeg_color_space == 1) {
                    jpeg_color_deconverter.color_convert = 3;
                    break;
                }
                if (jpeg_decompress_struct.jpeg_color_space == 2) {
                    jpeg_color_deconverter.color_convert = 0;
                    break;
                }
                error();
                break;
            }
            case 4: {
                jpeg_decompress_struct.out_color_components = 4;
                if (jpeg_decompress_struct.jpeg_color_space == 5) {
                    jpeg_color_deconverter.color_convert = 4;
                    build_ycc_rgb_table(jpeg_decompress_struct);
                    break;
                }
                if (jpeg_decompress_struct.jpeg_color_space == 4) {
                    jpeg_color_deconverter.color_convert = 0;
                    break;
                }
                error();
                break;
            }
            default: {
                if (jpeg_decompress_struct.out_color_space == jpeg_decompress_struct.jpeg_color_space) {
                    jpeg_decompress_struct.out_color_components = jpeg_decompress_struct.num_components;
                    jpeg_color_deconverter.color_convert = 0;
                    break;
                }
                error();
                break;
            }
        }
        if (jpeg_decompress_struct.quantize_colors) {
            jpeg_decompress_struct.output_components = 1;
        }
        else {
            jpeg_decompress_struct.output_components = jpeg_decompress_struct.out_color_components;
        }
    }
    
    static void jinit_d_post_controller(final jpeg_decompress_struct jpeg_decompress_struct, final boolean b) {
        final jpeg_d_post_controller post = new jpeg_d_post_controller();
        jpeg_decompress_struct.post = post;
        final jpeg_d_post_controller jpeg_d_post_controller = post;
        jpeg_d_post_controller.whole_image = null;
        jpeg_d_post_controller.buffer = null;
        if (jpeg_decompress_struct.quantize_colors) {
            error(20);
        }
    }
    
    static void make_funny_pointers(final jpeg_decompress_struct jpeg_decompress_struct) {
        final jpeg_d_main_controller main = jpeg_decompress_struct.main;
        final int min_DCT_scaled_size = jpeg_decompress_struct.min_DCT_scaled_size;
        for (int i = 0; i < jpeg_decompress_struct.num_components; ++i) {
            final jpeg_component_info jpeg_component_info = jpeg_decompress_struct.comp_info[i];
            final int n = jpeg_component_info.v_samp_factor * jpeg_component_info.DCT_scaled_size / jpeg_decompress_struct.min_DCT_scaled_size;
            final byte[][] array = main.xbuffer[0][i];
            final int n2 = main.xbuffer_offset[0][i];
            final byte[][] array2 = main.xbuffer[1][i];
            final int n3 = main.xbuffer_offset[1][i];
            final byte[][] array3 = main.buffer[i];
            for (int j = 0; j < n * (min_DCT_scaled_size + 2); ++j) {
                array[j + n2] = (array2[j + n3] = array3[j]);
            }
            for (int k = 0; k < n * 2; ++k) {
                array2[n * (min_DCT_scaled_size - 2) + k + n3] = array3[n * min_DCT_scaled_size + k];
                array2[n * min_DCT_scaled_size + k + n3] = array3[n * (min_DCT_scaled_size - 2) + k];
            }
            for (int l = 0; l < n; ++l) {
                array[l - n + n2] = array[0 + n2];
            }
        }
    }
    
    static void alloc_funny_pointers(final jpeg_decompress_struct jpeg_decompress_struct) {
        final jpeg_d_main_controller main = jpeg_decompress_struct.main;
        final int min_DCT_scaled_size = jpeg_decompress_struct.min_DCT_scaled_size;
        main.xbuffer[0] = new byte[jpeg_decompress_struct.num_components][][];
        main.xbuffer[1] = new byte[jpeg_decompress_struct.num_components][][];
        main.xbuffer_offset[0] = new int[jpeg_decompress_struct.num_components];
        main.xbuffer_offset[1] = new int[jpeg_decompress_struct.num_components];
        for (int i = 0; i < jpeg_decompress_struct.num_components; ++i) {
            final jpeg_component_info jpeg_component_info = jpeg_decompress_struct.comp_info[i];
            final int n = jpeg_component_info.v_samp_factor * jpeg_component_info.DCT_scaled_size / jpeg_decompress_struct.min_DCT_scaled_size;
            final byte[][] array = new byte[2 * (n * (min_DCT_scaled_size + 4))][];
            final int n2 = n;
            main.xbuffer_offset[0][i] = n2;
            main.xbuffer[0][i] = array;
            main.xbuffer_offset[1][i] = n2 + n * (min_DCT_scaled_size + 4);
            main.xbuffer[1][i] = array;
        }
    }
    
    static void jinit_d_main_controller(final jpeg_decompress_struct jpeg_decompress_struct, final boolean b) {
        final jpeg_d_main_controller main = new jpeg_d_main_controller();
        jpeg_decompress_struct.main = main;
        final jpeg_d_main_controller jpeg_d_main_controller = main;
        if (b) {
            error();
        }
        int min_DCT_scaled_size;
        if (jpeg_decompress_struct.upsample.need_context_rows) {
            if (jpeg_decompress_struct.min_DCT_scaled_size < 2) {
                error();
            }
            alloc_funny_pointers(jpeg_decompress_struct);
            min_DCT_scaled_size = jpeg_decompress_struct.min_DCT_scaled_size + 2;
        }
        else {
            min_DCT_scaled_size = jpeg_decompress_struct.min_DCT_scaled_size;
        }
        for (int i = 0; i < jpeg_decompress_struct.num_components; ++i) {
            final jpeg_component_info jpeg_component_info = jpeg_decompress_struct.comp_info[i];
            jpeg_d_main_controller.buffer[i] = new byte[jpeg_component_info.v_samp_factor * jpeg_component_info.DCT_scaled_size / jpeg_decompress_struct.min_DCT_scaled_size * min_DCT_scaled_size][jpeg_component_info.width_in_blocks * jpeg_component_info.DCT_scaled_size];
        }
    }
    
    static long jround_up(long n, final long n2) {
        n += n2 - 1L;
        return n - n % n2;
    }
    
    static void jinit_upsampler(final jpeg_decompress_struct jpeg_decompress_struct) {
        final jpeg_upsampler upsample = new jpeg_upsampler();
        jpeg_decompress_struct.upsample = upsample;
        upsample.need_context_rows = false;
        if (jpeg_decompress_struct.CCIR601_sampling) {
            error();
        }
        final boolean b = jpeg_decompress_struct.do_fancy_upsampling && jpeg_decompress_struct.min_DCT_scaled_size > 1;
        for (int i = 0; i < jpeg_decompress_struct.num_components; ++i) {
            final jpeg_component_info jpeg_component_info = jpeg_decompress_struct.comp_info[i];
            final int n = jpeg_component_info.h_samp_factor * jpeg_component_info.DCT_scaled_size / jpeg_decompress_struct.min_DCT_scaled_size;
            final int n2 = jpeg_component_info.v_samp_factor * jpeg_component_info.DCT_scaled_size / jpeg_decompress_struct.min_DCT_scaled_size;
            final int max_h_samp_factor = jpeg_decompress_struct.max_h_samp_factor;
            final int max_v_samp_factor = jpeg_decompress_struct.max_v_samp_factor;
            upsample.rowgroup_height[i] = n2;
            boolean b2 = true;
            if (!jpeg_component_info.component_needed) {
                upsample.methods[i] = 0;
                b2 = false;
            }
            else if (n == max_h_samp_factor && n2 == max_v_samp_factor) {
                upsample.methods[i] = 1;
                b2 = false;
            }
            else if (n * 2 == max_h_samp_factor && n2 == max_v_samp_factor) {
                if (b && jpeg_component_info.downsampled_width > 2) {
                    upsample.methods[i] = 2;
                }
                else {
                    upsample.methods[i] = 3;
                }
            }
            else if (n * 2 == max_h_samp_factor && n2 * 2 == max_v_samp_factor) {
                if (b && jpeg_component_info.downsampled_width > 2) {
                    upsample.methods[i] = 4;
                    upsample.need_context_rows = true;
                }
                else {
                    upsample.methods[i] = 5;
                }
            }
            else if (max_h_samp_factor % n == 0 && max_v_samp_factor % n2 == 0) {
                upsample.methods[i] = 6;
                upsample.h_expand[i] = (byte)(max_h_samp_factor / n);
                upsample.v_expand[i] = (byte)(max_v_samp_factor / n2);
            }
            else {
                error();
            }
            if (b2) {
                upsample.color_buf[i] = new byte[jpeg_decompress_struct.max_v_samp_factor][(int)jround_up(jpeg_decompress_struct.output_width, jpeg_decompress_struct.max_h_samp_factor)];
            }
        }
    }
    
    static void jinit_phuff_decoder(final jpeg_decompress_struct jpeg_decompress_struct) {
        jpeg_decompress_struct.entropy = new phuff_entropy_decoder();
        jpeg_decompress_struct.coef_bits = new int[jpeg_decompress_struct.num_components][64];
        final int[][] coef_bits = jpeg_decompress_struct.coef_bits;
        for (int i = 0; i < jpeg_decompress_struct.num_components; ++i) {
            for (int j = 0; j < 64; ++j) {
                coef_bits[i][j] = -1;
            }
        }
    }
    
    static void jinit_huff_decoder(final jpeg_decompress_struct jpeg_decompress_struct) {
        jpeg_decompress_struct.entropy = new huff_entropy_decoder();
    }
    
    static void jinit_inverse_dct(final jpeg_decompress_struct jpeg_decompress_struct) {
        final jpeg_inverse_dct idct = new jpeg_inverse_dct();
        jpeg_decompress_struct.idct = idct;
        final jpeg_inverse_dct jpeg_inverse_dct = idct;
        for (int i = 0; i < jpeg_decompress_struct.num_components; ++i) {
            jpeg_decompress_struct.comp_info[i].dct_table = new int[64];
            jpeg_inverse_dct.cur_method[i] = -1;
        }
    }
    
    static void jpeg_idct_islow(final jpeg_decompress_struct jpeg_decompress_struct, final jpeg_component_info jpeg_component_info, final short[] array, final byte[][] array2, final int n, final int n2) {
        final byte[] sample_range_limit = jpeg_decompress_struct.sample_range_limit;
        final int n3 = jpeg_decompress_struct.sample_range_limit_offset + 128;
        final int[] workspace = jpeg_decompress_struct.workspace;
        final int[] dct_table = jpeg_component_info.dct_table;
        final int[] array3 = workspace;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        for (int i = 8; i > 0; --i) {
            if (array[8 + n4] == 0 && array[16 + n4] == 0 && array[24 + n4] == 0 && array[32 + n4] == 0 && array[40 + n4] == 0 && array[48 + n4] == 0 && array[56 + n4] == 0) {
                final int n7 = array[0 + n4] * dct_table[0 + n5] << 2;
                array3[8 + n6] = (array3[0 + n6] = n7);
                array3[24 + n6] = (array3[16 + n6] = n7);
                array3[40 + n6] = (array3[32 + n6] = n7);
                array3[56 + n6] = (array3[48 + n6] = n7);
                ++n4;
                ++n5;
                ++n6;
            }
            else {
                final int n8 = array[16 + n4] * dct_table[16 + n5];
                final int n9 = array[48 + n4] * dct_table[48 + n5];
                final int n10 = (n8 + n9) * 4433;
                final int n11 = n10 + n9 * -15137;
                final int n12 = n10 + n8 * 6270;
                final int n13 = array[0 + n4] * dct_table[0 + n5];
                final int n14 = array[32 + n4] * dct_table[32 + n5];
                final int n15 = n13 + n14 << 13;
                final int n16 = n13 - n14 << 13;
                final int n17 = n15 + n12;
                final int n18 = n15 - n12;
                final int n19 = n16 + n11;
                final int n20 = n16 - n11;
                final int n21 = array[56 + n4] * dct_table[56 + n5];
                final int n22 = array[40 + n4] * dct_table[40 + n5];
                final int n23 = array[24 + n4] * dct_table[24 + n5];
                final int n24 = array[8 + n4] * dct_table[8 + n5];
                final int n25 = n21 + n24;
                final int n26 = n22 + n23;
                final int n27 = n21 + n23;
                final int n28 = n22 + n24;
                final int n29 = (n27 + n28) * 9633;
                final int n30 = n21 * 2446;
                final int n31 = n22 * 16819;
                final int n32 = n23 * 25172;
                final int n33 = n24 * 12299;
                final int n34 = n25 * -7373;
                final int n35 = n26 * -20995;
                final int n36 = n27 * -16069;
                final int n37 = n28 * -3196;
                final int n38 = n36 + n29;
                final int n39 = n37 + n29;
                final int n40 = n30 + (n34 + n38);
                final int n41 = n31 + (n35 + n39);
                final int n42 = n32 + (n35 + n38);
                final int n43 = n33 + (n34 + n39);
                array3[0 + n6] = n17 + n43 + 1024 >> 11;
                array3[56 + n6] = n17 - n43 + 1024 >> 11;
                array3[8 + n6] = n19 + n42 + 1024 >> 11;
                array3[48 + n6] = n19 - n42 + 1024 >> 11;
                array3[16 + n6] = n20 + n41 + 1024 >> 11;
                array3[40 + n6] = n20 - n41 + 1024 >> 11;
                array3[24 + n6] = n18 + n40 + 1024 >> 11;
                array3[32 + n6] = n18 - n40 + 1024 >> 11;
                ++n4;
                ++n5;
                ++n6;
            }
        }
        final int[] array4 = workspace;
        int n44 = 0;
        for (int j = 0; j < 8; ++j) {
            final byte[] array5 = array2[j + n];
            if (array4[1 + n44] == 0 && array4[2 + n44] == 0 && array4[3 + n44] == 0 && array4[4 + n44] == 0 && array4[5 + n44] == 0 && array4[6 + n44] == 0 && array4[7 + n44] == 0) {
                final byte b = sample_range_limit[n3 + (array4[0 + n44] + 16 >> 5 & 0x3FF)];
                array5[1 + n2] = (array5[0 + n2] = b);
                array5[3 + n2] = (array5[2 + n2] = b);
                array5[5 + n2] = (array5[4 + n2] = b);
                array5[7 + n2] = (array5[6 + n2] = b);
                n44 += 8;
            }
            else {
                final int n45 = array4[2 + n44];
                final int n46 = array4[6 + n44];
                final int n47 = (n45 + n46) * 4433;
                final int n48 = n47 + n46 * -15137;
                final int n49 = n47 + n45 * 6270;
                final int n50 = array4[0 + n44] + array4[4 + n44] << 13;
                final int n51 = array4[0 + n44] - array4[4 + n44] << 13;
                final int n52 = n50 + n49;
                final int n53 = n50 - n49;
                final int n54 = n51 + n48;
                final int n55 = n51 - n48;
                final int n56 = array4[7 + n44];
                final int n57 = array4[5 + n44];
                final int n58 = array4[3 + n44];
                final int n59 = array4[1 + n44];
                final int n60 = n56 + n59;
                final int n61 = n57 + n58;
                final int n62 = n56 + n58;
                final int n63 = n57 + n59;
                final int n64 = (n62 + n63) * 9633;
                final int n65 = n56 * 2446;
                final int n66 = n57 * 16819;
                final int n67 = n58 * 25172;
                final int n68 = n59 * 12299;
                final int n69 = n60 * -7373;
                final int n70 = n61 * -20995;
                final int n71 = n62 * -16069;
                final int n72 = n63 * -3196;
                final int n73 = n71 + n64;
                final int n74 = n72 + n64;
                final int n75 = n65 + (n69 + n73);
                final int n76 = n66 + (n70 + n74);
                final int n77 = n67 + (n70 + n73);
                final int n78 = n68 + (n69 + n74);
                array5[0 + n2] = sample_range_limit[n3 + (n52 + n78 + 131072 >> 18 & 0x3FF)];
                array5[7 + n2] = sample_range_limit[n3 + (n52 - n78 + 131072 >> 18 & 0x3FF)];
                array5[1 + n2] = sample_range_limit[n3 + (n54 + n77 + 131072 >> 18 & 0x3FF)];
                array5[6 + n2] = sample_range_limit[n3 + (n54 - n77 + 131072 >> 18 & 0x3FF)];
                array5[2 + n2] = sample_range_limit[n3 + (n55 + n76 + 131072 >> 18 & 0x3FF)];
                array5[5 + n2] = sample_range_limit[n3 + (n55 - n76 + 131072 >> 18 & 0x3FF)];
                array5[3 + n2] = sample_range_limit[n3 + (n53 + n75 + 131072 >> 18 & 0x3FF)];
                array5[4 + n2] = sample_range_limit[n3 + (n53 - n75 + 131072 >> 18 & 0x3FF)];
                n44 += 8;
            }
        }
    }
    
    static void upsample(final jpeg_decompress_struct jpeg_decompress_struct, final byte[][][] array, final int[] array2, final int[] array3, final int n, final byte[][] array4, final int[] array5, final int n2) {
        sep_upsample(jpeg_decompress_struct, array, array2, array3, n, array4, array5, n2);
    }
    
    static boolean smoothing_ok(final jpeg_decompress_struct jpeg_decompress_struct) {
        final jpeg_d_coef_controller coef = jpeg_decompress_struct.coef;
        boolean b = false;
        if (!jpeg_decompress_struct.progressive_mode || jpeg_decompress_struct.coef_bits == null) {
            return false;
        }
        if (coef.coef_bits_latch == null) {
            coef.coef_bits_latch = new int[jpeg_decompress_struct.num_components * 6];
        }
        final int[] coef_bits_latch = coef.coef_bits_latch;
        int n = 0;
        for (int i = 0; i < jpeg_decompress_struct.num_components; ++i) {
            final JQUANT_TBL quant_table;
            if ((quant_table = jpeg_decompress_struct.comp_info[i].quant_table) == null) {
                return false;
            }
            if (quant_table.quantval[0] == 0 || quant_table.quantval[1] == 0 || quant_table.quantval[8] == 0 || quant_table.quantval[16] == 0 || quant_table.quantval[9] == 0 || quant_table.quantval[2] == 0) {
                return false;
            }
            final int[] array = jpeg_decompress_struct.coef_bits[i];
            if (array[0] < 0) {
                return false;
            }
            for (int j = 1; j <= 5; ++j) {
                coef_bits_latch[j + n] = array[j];
                if (array[j] != 0) {
                    b = true;
                }
            }
            n += 6;
        }
        return b;
    }
    
    static void master_selection(final jpeg_decompress_struct jpeg_decompress_struct) {
        final jpeg_decomp_master master = jpeg_decompress_struct.master;
        jpeg_calc_output_dimensions(jpeg_decompress_struct);
        prepare_range_limit_table(jpeg_decompress_struct);
        final long n = jpeg_decompress_struct.output_width * jpeg_decompress_struct.out_color_components;
        if ((int)n != n) {
            error();
        }
        master.pass_number = 0;
        master.using_merged_upsample = use_merged_upsample(jpeg_decompress_struct);
        master.quantizer_1pass = null;
        master.quantizer_2pass = null;
        if (!jpeg_decompress_struct.quantize_colors || !jpeg_decompress_struct.buffered_image) {
            jpeg_decompress_struct.enable_1pass_quant = false;
            jpeg_decompress_struct.enable_external_quant = false;
            jpeg_decompress_struct.enable_2pass_quant = false;
        }
        if (jpeg_decompress_struct.quantize_colors) {
            error(20);
        }
        if (!jpeg_decompress_struct.raw_data_out) {
            if (master.using_merged_upsample) {
                error();
            }
            else {
                jinit_color_deconverter(jpeg_decompress_struct);
                jinit_upsampler(jpeg_decompress_struct);
            }
            jinit_d_post_controller(jpeg_decompress_struct, jpeg_decompress_struct.enable_2pass_quant);
        }
        jinit_inverse_dct(jpeg_decompress_struct);
        if (jpeg_decompress_struct.arith_code) {
            error();
        }
        else if (jpeg_decompress_struct.progressive_mode) {
            jinit_phuff_decoder(jpeg_decompress_struct);
        }
        else {
            jinit_huff_decoder(jpeg_decompress_struct);
        }
        jinit_d_coef_controller(jpeg_decompress_struct, jpeg_decompress_struct.inputctl.has_multiple_scans || jpeg_decompress_struct.buffered_image);
        if (!jpeg_decompress_struct.raw_data_out) {
            jinit_d_main_controller(jpeg_decompress_struct, false);
        }
        start_input_pass(jpeg_decompress_struct);
    }
    
    static void jinit_master_decompress(final jpeg_decompress_struct jpeg_decompress_struct) {
        final jpeg_decomp_master master = new jpeg_decomp_master();
        jpeg_decompress_struct.master = master;
        master.is_dummy_pass = false;
        master_selection(jpeg_decompress_struct);
    }
    
    static void jcopy_sample_rows(final byte[][] array, final int n, final byte[][] array2, final int n2, final int n3, final int n4) {
        int n5 = n;
        int n6 = n2;
        for (int i = n3; i > 0; --i) {
            System.arraycopy(array[n5++], 0, array2[n6++], 0, n4);
        }
    }
    
    static boolean jpeg_start_decompress(final jpeg_decompress_struct jpeg_decompress_struct) {
        if (jpeg_decompress_struct.global_state == 202) {
            jinit_master_decompress(jpeg_decompress_struct);
            if (jpeg_decompress_struct.buffered_image) {
                jpeg_decompress_struct.global_state = 207;
                return true;
            }
            jpeg_decompress_struct.global_state = 203;
        }
        if (jpeg_decompress_struct.global_state == 203) {
            if (jpeg_decompress_struct.inputctl.has_multiple_scans) {
                int i;
                do {
                    i = consume_input(jpeg_decompress_struct);
                    if (i == 0) {
                        return false;
                    }
                } while (i != 2);
            }
            jpeg_decompress_struct.output_scan_number = jpeg_decompress_struct.input_scan_number;
        }
        else if (jpeg_decompress_struct.global_state != 204) {
            error();
        }
        return output_pass_setup(jpeg_decompress_struct);
    }
    
    static void prepare_for_output_pass(final jpeg_decompress_struct jpeg_decompress_struct) {
        final jpeg_decomp_master master = jpeg_decompress_struct.master;
        if (master.is_dummy_pass) {
            error(20);
        }
        else {
            if (jpeg_decompress_struct.quantize_colors && jpeg_decompress_struct.colormap == null) {
                if (jpeg_decompress_struct.two_pass_quantize && jpeg_decompress_struct.enable_2pass_quant) {
                    jpeg_decompress_struct.cquantize = master.quantizer_2pass;
                    master.is_dummy_pass = true;
                }
                else if (jpeg_decompress_struct.enable_1pass_quant) {
                    jpeg_decompress_struct.cquantize = master.quantizer_1pass;
                }
                else {
                    error();
                }
            }
            jpeg_decompress_struct.idct.start_pass(jpeg_decompress_struct);
            start_output_pass(jpeg_decompress_struct);
            if (!jpeg_decompress_struct.raw_data_out) {
                if (!master.using_merged_upsample) {
                    jpeg_decompress_struct.cconvert.start_pass(jpeg_decompress_struct);
                }
                jpeg_decompress_struct.upsample.start_pass(jpeg_decompress_struct);
                if (jpeg_decompress_struct.quantize_colors) {
                    jpeg_decompress_struct.cquantize.start_pass(jpeg_decompress_struct, master.is_dummy_pass);
                }
                jpeg_decompress_struct.post.start_pass(jpeg_decompress_struct, master.is_dummy_pass ? 3 : 0);
                jpeg_decompress_struct.main.start_pass(jpeg_decompress_struct, 0);
            }
        }
    }
    
    static boolean jpeg_resync_to_restart(final jpeg_decompress_struct jpeg_decompress_struct, final int n) {
        int n2 = jpeg_decompress_struct.unread_marker;
        while (true) {
            int n3;
            if (n2 < 192) {
                n3 = 2;
            }
            else if (n2 < 208 || n2 > 215) {
                n3 = 3;
            }
            else if (n2 == 208 + (n + 1 & 0x7) || n2 == 208 + (n + 2 & 0x7)) {
                n3 = 3;
            }
            else if (n2 == 208 + (n - 1 & 0x7) || n2 == 208 + (n - 2 & 0x7)) {
                n3 = 2;
            }
            else {
                n3 = 1;
            }
            switch (n3) {
                case 1: {
                    jpeg_decompress_struct.unread_marker = 0;
                    return true;
                }
                case 2: {
                    if (!next_marker(jpeg_decompress_struct)) {
                        return false;
                    }
                    n2 = jpeg_decompress_struct.unread_marker;
                    continue;
                }
                case 3: {
                    return true;
                }
                default: {
                    continue;
                }
            }
        }
    }
    
    static boolean read_restart_marker(final jpeg_decompress_struct jpeg_decompress_struct) {
        if (jpeg_decompress_struct.unread_marker == 0 && !next_marker(jpeg_decompress_struct)) {
            return false;
        }
        if (jpeg_decompress_struct.unread_marker == 208 + jpeg_decompress_struct.marker.next_restart_num) {
            jpeg_decompress_struct.unread_marker = 0;
        }
        else if (!jpeg_resync_to_restart(jpeg_decompress_struct, jpeg_decompress_struct.marker.next_restart_num)) {
            return false;
        }
        jpeg_decompress_struct.marker.next_restart_num = (jpeg_decompress_struct.marker.next_restart_num + 1 & 0x7);
        return true;
    }
    
    static boolean jpeg_fill_bit_buffer(final bitread_working_state bitread_working_state, int n, int i, final int n2) {
        byte[] array = bitread_working_state.buffer;
        int n3 = bitread_working_state.bytes_in_buffer;
        int n4 = bitread_working_state.bytes_offset;
        final jpeg_decompress_struct cinfo = bitread_working_state.cinfo;
        if (cinfo.unread_marker == 0) {
            while (i < 25) {
                if (n4 == n3) {
                    if (!fill_input_buffer(cinfo)) {
                        return false;
                    }
                    array = cinfo.buffer;
                    n3 = cinfo.bytes_in_buffer;
                    n4 = cinfo.bytes_offset;
                }
                int n5 = array[n4++] & 0xFF;
                if (n5 == 255) {
                    int j;
                    do {
                        if (n4 == n3) {
                            if (!fill_input_buffer(cinfo)) {
                                return false;
                            }
                            array = cinfo.buffer;
                            n3 = cinfo.bytes_in_buffer;
                            n4 = cinfo.bytes_offset;
                        }
                        j = (array[n4++] & 0xFF);
                    } while (j == 255);
                    if (j != 0) {
                        cinfo.unread_marker = j;
                        if (n2 > i) {
                            if (!cinfo.entropy.insufficient_data) {
                                cinfo.entropy.insufficient_data = true;
                            }
                            n <<= 25 - i;
                            i = 25;
                        }
                        bitread_working_state.buffer = array;
                        bitread_working_state.bytes_in_buffer = n3;
                        bitread_working_state.bytes_offset = n4;
                        bitread_working_state.get_buffer = n;
                        bitread_working_state.bits_left = i;
                        return true;
                    }
                    n5 = 255;
                }
                n = (n << 8 | n5);
                i += 8;
            }
        }
        else if (n2 > i) {
            if (!cinfo.entropy.insufficient_data) {
                cinfo.entropy.insufficient_data = true;
            }
            n <<= 25 - i;
            i = 25;
        }
        bitread_working_state.buffer = array;
        bitread_working_state.bytes_in_buffer = n3;
        bitread_working_state.bytes_offset = n4;
        bitread_working_state.get_buffer = n;
        bitread_working_state.bits_left = i;
        return true;
    }
    
    static int jpeg_huff_decode(final bitread_working_state bitread_working_state, int get_buffer, int bits_left, final d_derived_tbl d_derived_tbl, final int n) {
        int n2 = n;
        if (bits_left < n2) {
            if (!jpeg_fill_bit_buffer(bitread_working_state, get_buffer, bits_left, n2)) {
                return -1;
            }
            get_buffer = bitread_working_state.get_buffer;
            bits_left = bitread_working_state.bits_left;
        }
        int i;
        int n3;
        for (i = (get_buffer >> (bits_left -= n2) & (1 << n2) - 1); i > d_derived_tbl.maxcode[n2]; i = (n3 | (get_buffer >> --bits_left & 0x1)), ++n2) {
            n3 = i << 1;
            if (bits_left < 1) {
                if (!jpeg_fill_bit_buffer(bitread_working_state, get_buffer, bits_left, 1)) {
                    return -1;
                }
                get_buffer = bitread_working_state.get_buffer;
                bits_left = bitread_working_state.bits_left;
            }
        }
        bitread_working_state.get_buffer = get_buffer;
        bitread_working_state.bits_left = bits_left;
        if (n2 > 16) {
            return 0;
        }
        return d_derived_tbl.pub.huffval[i + d_derived_tbl.valoffset[n2]] & 0xFF;
    }
    
    static int decompress_onepass(final jpeg_decompress_struct jpeg_decompress_struct, final byte[][][] array, final int[] array2) {
        final jpeg_d_coef_controller coef = jpeg_decompress_struct.coef;
        final int n = jpeg_decompress_struct.MCUs_per_row - 1;
        final int n2 = jpeg_decompress_struct.total_iMCU_rows - 1;
        for (int i = coef.MCU_vert_offset; i < coef.MCU_rows_per_iMCU_row; ++i) {
            for (int j = coef.MCU_ctr; j <= n; ++j) {
                for (int k = 0; k < jpeg_decompress_struct.blocks_in_MCU; ++k) {
                    final short[] array3 = coef.MCU_buffer[k];
                    for (int l = 0; l < array3.length; ++l) {
                        array3[l] = 0;
                    }
                }
                if (!jpeg_decompress_struct.entropy.decode_mcu(jpeg_decompress_struct, coef.MCU_buffer)) {
                    coef.MCU_vert_offset = i;
                    coef.MCU_ctr = j;
                    return 0;
                }
                int n3 = 0;
                for (int n4 = 0; n4 < jpeg_decompress_struct.comps_in_scan; ++n4) {
                    final jpeg_component_info jpeg_component_info = jpeg_decompress_struct.cur_comp_info[n4];
                    if (!jpeg_component_info.component_needed) {
                        n3 += jpeg_component_info.MCU_blocks;
                    }
                    else {
                        final int n5 = (j < n) ? jpeg_component_info.MCU_width : jpeg_component_info.last_col_width;
                        final byte[][] array4 = array[jpeg_component_info.component_index];
                        int n6 = array2[jpeg_component_info.component_index] + i * jpeg_component_info.DCT_scaled_size;
                        final int n7 = j * jpeg_component_info.MCU_sample_width;
                        for (int n8 = 0; n8 < jpeg_component_info.MCU_height; ++n8) {
                            if (jpeg_decompress_struct.input_iMCU_row < n2 || i + n8 < jpeg_component_info.last_row_height) {
                                int n9 = n7;
                                for (int n10 = 0; n10 < n5; ++n10) {
                                    jpeg_idct_islow(jpeg_decompress_struct, jpeg_component_info, coef.MCU_buffer[n3 + n10], array4, n6, n9);
                                    n9 += jpeg_component_info.DCT_scaled_size;
                                }
                            }
                            n3 += jpeg_component_info.MCU_width;
                            n6 += jpeg_component_info.DCT_scaled_size;
                        }
                    }
                }
            }
            coef.MCU_ctr = 0;
        }
        ++jpeg_decompress_struct.output_iMCU_row;
        if (++jpeg_decompress_struct.input_iMCU_row < jpeg_decompress_struct.total_iMCU_rows) {
            coef.start_iMCU_row(jpeg_decompress_struct);
            return 3;
        }
        finish_input_pass(jpeg_decompress_struct);
        return 4;
    }
    
    static int decompress_smooth_data(final jpeg_decompress_struct jpeg_decompress_struct, final byte[][][] array, final int[] array2) {
        final jpeg_d_coef_controller coef = jpeg_decompress_struct.coef;
        final int n = jpeg_decompress_struct.total_iMCU_rows - 1;
        short[] workspace = coef.workspace;
        if (workspace == null) {
            final jpeg_d_coef_controller jpeg_d_coef_controller = coef;
            final short[] workspace2 = new short[64];
            jpeg_d_coef_controller.workspace = workspace2;
            workspace = workspace2;
        }
        while (jpeg_decompress_struct.input_scan_number <= jpeg_decompress_struct.output_scan_number && !jpeg_decompress_struct.inputctl.eoi_reached && (jpeg_decompress_struct.input_scan_number != jpeg_decompress_struct.output_scan_number || jpeg_decompress_struct.input_iMCU_row <= jpeg_decompress_struct.output_iMCU_row + ((jpeg_decompress_struct.Ss == 0) ? 1 : 0))) {
            if (consume_input(jpeg_decompress_struct) == 0) {
                return 0;
            }
        }
        for (int i = 0; i < jpeg_decompress_struct.num_components; ++i) {
            final jpeg_component_info jpeg_component_info = jpeg_decompress_struct.comp_info[i];
            if (jpeg_component_info.component_needed) {
                int n2;
                boolean b;
                if (jpeg_decompress_struct.output_iMCU_row < n) {
                    n2 = jpeg_component_info.v_samp_factor;
                    b = false;
                }
                else {
                    n2 = jpeg_component_info.height_in_blocks % jpeg_component_info.v_samp_factor;
                    if (n2 == 0) {
                        n2 = jpeg_component_info.v_samp_factor;
                    }
                    b = true;
                }
                short[][][] array3;
                int n3;
                boolean b2;
                if (jpeg_decompress_struct.output_iMCU_row > 0) {
                    array3 = coef.whole_image[i];
                    n3 = (jpeg_decompress_struct.output_iMCU_row - 1) * jpeg_component_info.v_samp_factor + jpeg_component_info.v_samp_factor;
                    b2 = false;
                }
                else {
                    array3 = coef.whole_image[i];
                    n3 = 0;
                    b2 = true;
                }
                final int[] coef_bits_latch = coef.coef_bits_latch;
                final int n4 = i * 6;
                final JQUANT_TBL quant_table = jpeg_component_info.quant_table;
                final short n5 = quant_table.quantval[0];
                final short n6 = quant_table.quantval[1];
                final short n7 = quant_table.quantval[8];
                final short n8 = quant_table.quantval[16];
                final short n9 = quant_table.quantval[9];
                final short n10 = quant_table.quantval[2];
                final byte[][] array4 = array[i];
                int n11 = array2[i];
                for (int j = 0; j < n2; ++j) {
                    final short[][] array5 = array3[j + n3];
                    int n12 = 0;
                    short[][] array6;
                    int n13;
                    if (b2 && j == 0) {
                        array6 = array5;
                        n13 = n12;
                    }
                    else {
                        array6 = array3[j - 1 + n3];
                        n13 = 0;
                    }
                    short[][] array7;
                    int n14;
                    if (b && j == n2 - 1) {
                        array7 = array5;
                        n14 = n12;
                    }
                    else {
                        array7 = array3[j + 1 + n3];
                        n14 = 0;
                    }
                    short n17;
                    short n16;
                    short n15 = n16 = (n17 = array6[0 + n13][0]);
                    short n20;
                    short n19;
                    short n18 = n19 = (n20 = array5[0 + n12][0]);
                    short n23;
                    short n22;
                    short n21 = n22 = (n23 = array7[0 + n14][0]);
                    int n24 = 0;
                    for (int n25 = jpeg_component_info.width_in_blocks - 1, k = 0; k <= n25; ++k) {
                        System.arraycopy(array5[n12], 0, workspace, 0, workspace.length);
                        if (k < n25) {
                            n17 = array6[1 + n13][0];
                            n20 = array5[1 + n12][0];
                            n23 = array7[1 + n14][0];
                        }
                        final int n26;
                        if ((n26 = coef_bits_latch[1 + n4]) != 0 && workspace[1] == 0) {
                            final short n27 = (short)(36 * n5 * (n19 - n20));
                            int n28;
                            if (n27 >= 0) {
                                n28 = ((n6 << 7) + n27) / (n6 << 8);
                                if (n26 > 0 && n28 >= 1 << n26) {
                                    n28 = (1 << n26) - 1;
                                }
                            }
                            else {
                                int n29 = ((n6 << 7) - n27) / (n6 << 8);
                                if (n26 > 0 && n29 >= 1 << n26) {
                                    n29 = (1 << n26) - 1;
                                }
                                n28 = -n29;
                            }
                            workspace[1] = (short)n28;
                        }
                        final int n30;
                        if ((n30 = coef_bits_latch[2 + n4]) != 0 && workspace[8] == 0) {
                            final short n31 = (short)(36 * n5 * (n15 - n21));
                            int n32;
                            if (n31 >= 0) {
                                n32 = ((n7 << 7) + n31) / (n7 << 8);
                                if (n30 > 0 && n32 >= 1 << n30) {
                                    n32 = (1 << n30) - 1;
                                }
                            }
                            else {
                                int n33 = ((n7 << 7) - n31) / (n7 << 8);
                                if (n30 > 0 && n33 >= 1 << n30) {
                                    n33 = (1 << n30) - 1;
                                }
                                n32 = -n33;
                            }
                            workspace[8] = (short)n32;
                        }
                        final int n34;
                        if ((n34 = coef_bits_latch[3 + n4]) != 0 && workspace[16] == 0) {
                            final short n35 = (short)(9 * n5 * (n15 + n21 - 2 * n18));
                            int n36;
                            if (n35 >= 0) {
                                n36 = ((n8 << 7) + n35) / (n8 << 8);
                                if (n34 > 0 && n36 >= 1 << n34) {
                                    n36 = (1 << n34) - 1;
                                }
                            }
                            else {
                                int n37 = ((n8 << 7) - n35) / (n8 << 8);
                                if (n34 > 0 && n37 >= 1 << n34) {
                                    n37 = (1 << n34) - 1;
                                }
                                n36 = -n37;
                            }
                            workspace[16] = (short)n36;
                        }
                        final int n38;
                        if ((n38 = coef_bits_latch[4 + n4]) != 0 && workspace[9] == 0) {
                            final short n39 = (short)(5 * n5 * (n16 - n17 - n22 + n23));
                            int n40;
                            if (n39 >= 0) {
                                n40 = ((n9 << 7) + n39) / (n9 << 8);
                                if (n38 > 0 && n40 >= 1 << n38) {
                                    n40 = (1 << n38) - 1;
                                }
                            }
                            else {
                                int n41 = ((n9 << 7) - n39) / (n9 << 8);
                                if (n38 > 0 && n41 >= 1 << n38) {
                                    n41 = (1 << n38) - 1;
                                }
                                n40 = -n41;
                            }
                            workspace[9] = (short)n40;
                        }
                        final int n42;
                        if ((n42 = coef_bits_latch[5 + n4]) != 0 && workspace[2] == 0) {
                            final short n43 = (short)(9 * n5 * (n19 + n20 - 2 * n18));
                            int n44;
                            if (n43 >= 0) {
                                n44 = ((n10 << 7) + n43) / (n10 << 8);
                                if (n42 > 0 && n44 >= 1 << n42) {
                                    n44 = (1 << n42) - 1;
                                }
                            }
                            else {
                                int n45 = ((n10 << 7) - n43) / (n10 << 8);
                                if (n42 > 0 && n45 >= 1 << n42) {
                                    n45 = (1 << n42) - 1;
                                }
                                n44 = -n45;
                            }
                            workspace[2] = (short)n44;
                        }
                        jpeg_idct_islow(jpeg_decompress_struct, jpeg_component_info, workspace, array4, n11, n24);
                        n16 = n15;
                        n15 = n17;
                        n19 = n18;
                        n18 = n20;
                        n22 = n21;
                        n21 = n23;
                        ++n12;
                        ++n13;
                        ++n14;
                        n24 += jpeg_component_info.DCT_scaled_size;
                    }
                    n11 += jpeg_component_info.DCT_scaled_size;
                }
            }
        }
        if (++jpeg_decompress_struct.output_iMCU_row < jpeg_decompress_struct.total_iMCU_rows) {
            return 3;
        }
        return 4;
    }
    
    static int decompress_data(final jpeg_decompress_struct jpeg_decompress_struct, final byte[][][] array, final int[] array2) {
        final jpeg_d_coef_controller coef = jpeg_decompress_struct.coef;
        final int n = jpeg_decompress_struct.total_iMCU_rows - 1;
        while (jpeg_decompress_struct.input_scan_number < jpeg_decompress_struct.output_scan_number || (jpeg_decompress_struct.input_scan_number == jpeg_decompress_struct.output_scan_number && jpeg_decompress_struct.input_iMCU_row <= jpeg_decompress_struct.output_iMCU_row)) {
            if (consume_input(jpeg_decompress_struct) == 0) {
                return 0;
            }
        }
        for (int i = 0; i < jpeg_decompress_struct.num_components; ++i) {
            final jpeg_component_info jpeg_component_info = jpeg_decompress_struct.comp_info[i];
            if (jpeg_component_info.component_needed) {
                final short[][][] array3 = coef.whole_image[i];
                final int n2 = jpeg_decompress_struct.output_iMCU_row * jpeg_component_info.v_samp_factor;
                int n3;
                if (jpeg_decompress_struct.output_iMCU_row < n) {
                    n3 = jpeg_component_info.v_samp_factor;
                }
                else {
                    n3 = jpeg_component_info.height_in_blocks % jpeg_component_info.v_samp_factor;
                    if (n3 == 0) {
                        n3 = jpeg_component_info.v_samp_factor;
                    }
                }
                final byte[][] array4 = array[i];
                int n4 = array2[i];
                for (int j = 0; j < n3; ++j) {
                    final short[][] array5 = array3[j + n2];
                    int n5 = 0;
                    int n6 = 0;
                    for (int k = 0; k < jpeg_component_info.width_in_blocks; ++k) {
                        jpeg_idct_islow(jpeg_decompress_struct, jpeg_component_info, array5[n5], array4, n4, n6);
                        ++n5;
                        n6 += jpeg_component_info.DCT_scaled_size;
                    }
                    n4 += jpeg_component_info.DCT_scaled_size;
                }
            }
        }
        if (++jpeg_decompress_struct.output_iMCU_row < jpeg_decompress_struct.total_iMCU_rows) {
            return 3;
        }
        return 4;
    }
    
    static void post_process_data(final jpeg_decompress_struct jpeg_decompress_struct, final byte[][][] array, final int[] array2, final int[] array3, final int n, final byte[][] array4, final int[] array5, final int n2) {
        upsample(jpeg_decompress_struct, array, array2, array3, n, array4, array5, n2);
    }
    
    static void set_bottom_pointers(final jpeg_decompress_struct jpeg_decompress_struct) {
        final jpeg_d_main_controller main = jpeg_decompress_struct.main;
        for (int i = 0; i < jpeg_decompress_struct.num_components; ++i) {
            final jpeg_component_info jpeg_component_info = jpeg_decompress_struct.comp_info[i];
            final int n = jpeg_component_info.v_samp_factor * jpeg_component_info.DCT_scaled_size;
            final int n2 = n / jpeg_decompress_struct.min_DCT_scaled_size;
            int n3 = jpeg_component_info.downsampled_height % n;
            if (n3 == 0) {
                n3 = n;
            }
            if (i == 0) {
                main.rowgroups_avail = (n3 - 1) / n2 + 1;
            }
            final byte[][] array = main.xbuffer[main.whichptr][i];
            final int n4 = main.xbuffer_offset[main.whichptr][i];
            for (int j = 0; j < n2 * 2; ++j) {
                array[n3 + j + n4] = array[n3 - 1 + n4];
            }
        }
    }
    
    static void set_wraparound_pointers(final jpeg_decompress_struct jpeg_decompress_struct) {
        final jpeg_d_main_controller main = jpeg_decompress_struct.main;
        final int min_DCT_scaled_size = jpeg_decompress_struct.min_DCT_scaled_size;
        for (int i = 0; i < jpeg_decompress_struct.num_components; ++i) {
            final jpeg_component_info jpeg_component_info = jpeg_decompress_struct.comp_info[i];
            final int n = jpeg_component_info.v_samp_factor * jpeg_component_info.DCT_scaled_size / jpeg_decompress_struct.min_DCT_scaled_size;
            final byte[][] array = main.xbuffer[0][i];
            final int n2 = main.xbuffer_offset[0][i];
            final byte[][] array2 = main.xbuffer[1][i];
            final int n3 = main.xbuffer_offset[1][i];
            for (int j = 0; j < n; ++j) {
                array[j - n + n2] = array[n * (min_DCT_scaled_size + 1) + j + n2];
                array2[j - n + n3] = array2[n * (min_DCT_scaled_size + 1) + j + n3];
                array[n * (min_DCT_scaled_size + 2) + j + n2] = array[j + n2];
                array2[n * (min_DCT_scaled_size + 2) + j + n3] = array2[j + n3];
            }
        }
    }
    
    static void process_data_crank_post(final jpeg_decompress_struct jpeg_decompress_struct, final byte[][] array, final int[] array2, final int n) {
        error();
    }
    
    static void process_data_context_main(final jpeg_decompress_struct jpeg_decompress_struct, final byte[][] array, final int[] array2, final int n) {
        final jpeg_d_main_controller main = jpeg_decompress_struct.main;
        if (!main.buffer_full) {
            int n2 = 0;
            switch (jpeg_decompress_struct.coef.decompress_data) {
                case 0: {
                    n2 = decompress_data(jpeg_decompress_struct, main.xbuffer[main.whichptr], main.xbuffer_offset[main.whichptr]);
                    break;
                }
                case 1: {
                    n2 = decompress_smooth_data(jpeg_decompress_struct, main.xbuffer[main.whichptr], main.xbuffer_offset[main.whichptr]);
                    break;
                }
                case 2: {
                    n2 = decompress_onepass(jpeg_decompress_struct, main.xbuffer[main.whichptr], main.xbuffer_offset[main.whichptr]);
                    break;
                }
                default: {
                    n2 = 0;
                    break;
                }
            }
            if (n2 == 0) {
                return;
            }
            main.buffer_full = true;
            final jpeg_d_main_controller jpeg_d_main_controller = main;
            ++jpeg_d_main_controller.iMCU_row_ctr;
        }
        switch (main.context_state) {
            case 2: {
                post_process_data(jpeg_decompress_struct, main.xbuffer[main.whichptr], main.xbuffer_offset[main.whichptr], main.rowgroup_ctr, main.rowgroups_avail, array, array2, n);
                if (main.rowgroup_ctr[0] < main.rowgroups_avail) {
                    return;
                }
                main.context_state = 0;
                if (array2[0] >= n) {
                    return;
                }
            }
            case 0: {
                main.rowgroup_ctr[0] = 0;
                main.rowgroups_avail = jpeg_decompress_struct.min_DCT_scaled_size - 1;
                if (main.iMCU_row_ctr == jpeg_decompress_struct.total_iMCU_rows) {
                    set_bottom_pointers(jpeg_decompress_struct);
                }
                main.context_state = 1;
            }
            case 1: {
                post_process_data(jpeg_decompress_struct, main.xbuffer[main.whichptr], main.xbuffer_offset[main.whichptr], main.rowgroup_ctr, main.rowgroups_avail, array, array2, n);
                if (main.rowgroup_ctr[0] < main.rowgroups_avail) {
                    return;
                }
                if (main.iMCU_row_ctr == 1) {
                    set_wraparound_pointers(jpeg_decompress_struct);
                }
                final jpeg_d_main_controller jpeg_d_main_controller2 = main;
                jpeg_d_main_controller2.whichptr ^= 0x1;
                main.buffer_full = false;
                main.rowgroup_ctr[0] = jpeg_decompress_struct.min_DCT_scaled_size + 1;
                main.rowgroups_avail = jpeg_decompress_struct.min_DCT_scaled_size + 2;
                main.context_state = 2;
                break;
            }
        }
    }
    
    static void process_data_simple_main(final jpeg_decompress_struct jpeg_decompress_struct, final byte[][] array, final int[] array2, final int n) {
        final jpeg_d_main_controller main = jpeg_decompress_struct.main;
        if (!main.buffer_full) {
            int n2 = 0;
            switch (jpeg_decompress_struct.coef.decompress_data) {
                case 0: {
                    n2 = decompress_data(jpeg_decompress_struct, main.buffer, main.buffer_offset);
                    break;
                }
                case 1: {
                    n2 = decompress_smooth_data(jpeg_decompress_struct, main.buffer, main.buffer_offset);
                    break;
                }
                case 2: {
                    n2 = decompress_onepass(jpeg_decompress_struct, main.buffer, main.buffer_offset);
                    break;
                }
                default: {
                    n2 = 0;
                    break;
                }
            }
            if (n2 == 0) {
                return;
            }
            main.buffer_full = true;
        }
        final int min_DCT_scaled_size = jpeg_decompress_struct.min_DCT_scaled_size;
        post_process_data(jpeg_decompress_struct, main.buffer, main.buffer_offset, main.rowgroup_ctr, min_DCT_scaled_size, array, array2, n);
        if (main.rowgroup_ctr[0] >= min_DCT_scaled_size) {
            main.buffer_full = false;
            main.rowgroup_ctr[0] = 0;
        }
    }
    
    static int jpeg_read_scanlines(final jpeg_decompress_struct jpeg_decompress_struct, final byte[][] array, final int n) {
        if (jpeg_decompress_struct.global_state != 205) {
            error();
        }
        if (jpeg_decompress_struct.output_scanline >= jpeg_decompress_struct.output_height) {
            return 0;
        }
        jpeg_decompress_struct.row_ctr[0] = 0;
        switch (jpeg_decompress_struct.main.process_data) {
            case 0: {
                process_data_simple_main(jpeg_decompress_struct, array, jpeg_decompress_struct.row_ctr, n);
                break;
            }
            case 1: {
                process_data_context_main(jpeg_decompress_struct, array, jpeg_decompress_struct.row_ctr, n);
                break;
            }
            case 2: {
                process_data_crank_post(jpeg_decompress_struct, array, jpeg_decompress_struct.row_ctr, n);
                break;
            }
            default: {
                error();
                break;
            }
        }
        jpeg_decompress_struct.output_scanline += jpeg_decompress_struct.row_ctr[0];
        return jpeg_decompress_struct.row_ctr[0];
    }
    
    static boolean output_pass_setup(final jpeg_decompress_struct jpeg_decompress_struct) {
        if (jpeg_decompress_struct.global_state != 204) {
            prepare_for_output_pass(jpeg_decompress_struct);
            jpeg_decompress_struct.output_scanline = 0;
            jpeg_decompress_struct.global_state = 204;
        }
        while (jpeg_decompress_struct.master.is_dummy_pass) {
            error();
        }
        jpeg_decompress_struct.global_state = (jpeg_decompress_struct.raw_data_out ? 206 : 205);
        return true;
    }
    
    static boolean get_dht(final jpeg_decompress_struct jpeg_decompress_struct) {
        final byte[] array = new byte[17];
        final byte[] array2 = new byte[256];
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        final int n = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF) << 8;
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        int i = n | (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF);
        i -= 2;
        while (i > 16) {
            if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
                fill_input_buffer(jpeg_decompress_struct);
            }
            int n2 = jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF;
            array[0] = 0;
            int n3 = 0;
            for (int j = 1; j <= 16; ++j) {
                if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
                    fill_input_buffer(jpeg_decompress_struct);
                }
                array[j] = jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++];
                n3 += (array[j] & 0xFF);
            }
            i -= 17;
            if (n3 > 256 || n3 > i) {
                error();
            }
            for (int k = 0; k < n3; ++k) {
                if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
                    fill_input_buffer(jpeg_decompress_struct);
                }
                array2[k] = jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++];
            }
            i -= n3;
            JHUFF_TBL jhuff_TBL2;
            if ((n2 & 0x10) != 0x0) {
                n2 -= 16;
                final JHUFF_TBL[] ac_huff_tbl_ptrs = jpeg_decompress_struct.ac_huff_tbl_ptrs;
                final int n4 = n2;
                final JHUFF_TBL jhuff_TBL = new JHUFF_TBL();
                ac_huff_tbl_ptrs[n4] = jhuff_TBL;
                jhuff_TBL2 = jhuff_TBL;
            }
            else {
                final JHUFF_TBL[] dc_huff_tbl_ptrs = jpeg_decompress_struct.dc_huff_tbl_ptrs;
                final int n5 = n2;
                final JHUFF_TBL jhuff_TBL3 = new JHUFF_TBL();
                dc_huff_tbl_ptrs[n5] = jhuff_TBL3;
                jhuff_TBL2 = jhuff_TBL3;
            }
            if (n2 < 0 || n2 >= 4) {
                error();
            }
            System.arraycopy(array, 0, jhuff_TBL2.bits, 0, array.length);
            System.arraycopy(array2, 0, jhuff_TBL2.huffval, 0, array2.length);
        }
        if (i != 0) {
            error();
        }
        return true;
    }
    
    static boolean get_dqt(final jpeg_decompress_struct jpeg_decompress_struct) {
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        final int n = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF) << 8;
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        int i;
        for (i = (n | (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF)), i -= 2; i > 0; i -= 64) {
            if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
                fill_input_buffer(jpeg_decompress_struct);
            }
            final int n2 = jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF;
            final int n3 = n2 >> 4;
            final int n4 = n2 & 0xF;
            if (n4 >= 4) {
                error();
            }
            if (jpeg_decompress_struct.quant_tbl_ptrs[n4] == null) {
                jpeg_decompress_struct.quant_tbl_ptrs[n4] = new JQUANT_TBL();
            }
            final JQUANT_TBL jquant_TBL = jpeg_decompress_struct.quant_tbl_ptrs[n4];
            for (int j = 0; j < 64; ++j) {
                int n6;
                if (n3 != 0) {
                    if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
                        fill_input_buffer(jpeg_decompress_struct);
                    }
                    final int n5 = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF) << 8;
                    if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
                        fill_input_buffer(jpeg_decompress_struct);
                    }
                    n6 = (n5 | (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF));
                }
                else {
                    if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
                        fill_input_buffer(jpeg_decompress_struct);
                    }
                    n6 = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF);
                }
                jquant_TBL.quantval[JPEGDecoder.jpeg_natural_order[j]] = (short)n6;
            }
            i -= 65;
            if (n3 != 0) {}
        }
        if (i != 0) {
            error();
        }
        return true;
    }
    
    static boolean get_dri(final jpeg_decompress_struct jpeg_decompress_struct) {
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        final int n = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF) << 8;
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        if ((n | (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF)) != 0x4) {
            error();
        }
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        final int n2 = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF) << 8;
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        jpeg_decompress_struct.restart_interval = (n2 | (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF));
        return true;
    }
    
    static boolean get_dac(final jpeg_decompress_struct jpeg_decompress_struct) {
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        final int n = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF) << 8;
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        int i = n | (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF);
        i -= 2;
        while (i > 0) {
            if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
                fill_input_buffer(jpeg_decompress_struct);
            }
            final int n2 = jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF;
            if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
                fill_input_buffer(jpeg_decompress_struct);
            }
            final int n3 = jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF;
            i -= 2;
            if (n2 < 0 || n2 >= 32) {
                error();
            }
            if (n2 >= 16) {
                jpeg_decompress_struct.arith_ac_K[n2 - 16] = (byte)n3;
            }
            else {
                jpeg_decompress_struct.arith_dc_L[n2] = (byte)(n3 & 0xF);
                jpeg_decompress_struct.arith_dc_U[n2] = (byte)(n3 >> 4);
                if (jpeg_decompress_struct.arith_dc_L[n2] <= jpeg_decompress_struct.arith_dc_U[n2]) {
                    continue;
                }
                error();
            }
        }
        if (i != 0) {
            error();
        }
        return true;
    }
    
    static boolean get_sos(final jpeg_decompress_struct jpeg_decompress_struct) {
        jpeg_component_info jpeg_component_info = null;
        if (!jpeg_decompress_struct.marker.saw_SOF) {
            error();
        }
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        final int n = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF) << 8;
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        final int n2 = n | (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF);
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        final int comps_in_scan = jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF;
        if (n2 != comps_in_scan * 2 + 6 || comps_in_scan < 1 || comps_in_scan > 4) {
            error();
        }
        jpeg_decompress_struct.comps_in_scan = comps_in_scan;
        for (int i = 0; i < comps_in_scan; ++i) {
            if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
                fill_input_buffer(jpeg_decompress_struct);
            }
            final int n3 = jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF;
            if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
                fill_input_buffer(jpeg_decompress_struct);
            }
            final int n4 = jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF;
            int j;
            for (j = 0; j < jpeg_decompress_struct.num_components; ++j) {
                jpeg_component_info = jpeg_decompress_struct.comp_info[j];
                if (n3 == jpeg_component_info.component_id) {
                    break;
                }
            }
            if (j == jpeg_decompress_struct.num_components) {
                error();
            }
            jpeg_decompress_struct.cur_comp_info[i] = jpeg_component_info;
            jpeg_component_info.dc_tbl_no = (n4 >> 4 & 0xF);
            jpeg_component_info.ac_tbl_no = (n4 & 0xF);
        }
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        jpeg_decompress_struct.Ss = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF);
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        jpeg_decompress_struct.Se = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF);
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        final int n5 = jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF;
        jpeg_decompress_struct.Ah = (n5 >> 4 & 0xF);
        jpeg_decompress_struct.Al = (n5 & 0xF);
        jpeg_decompress_struct.marker.next_restart_num = 0;
        ++jpeg_decompress_struct.input_scan_number;
        return true;
    }
    
    static boolean get_sof(final jpeg_decompress_struct jpeg_decompress_struct, final boolean progressive_mode, final boolean arith_code) {
        jpeg_decompress_struct.progressive_mode = progressive_mode;
        jpeg_decompress_struct.arith_code = arith_code;
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        final int n = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF) << 8;
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        int n2 = n | (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF);
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        jpeg_decompress_struct.data_precision = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF);
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        jpeg_decompress_struct.image_height = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF) << 8;
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        jpeg_decompress_struct.image_height |= (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF);
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        jpeg_decompress_struct.image_width = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF) << 8;
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        jpeg_decompress_struct.image_width |= (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF);
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        jpeg_decompress_struct.num_components = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF);
        n2 -= 8;
        if (jpeg_decompress_struct.marker.saw_SOF) {
            error();
        }
        if (jpeg_decompress_struct.image_height <= 0 || jpeg_decompress_struct.image_width <= 0 || jpeg_decompress_struct.num_components <= 0) {
            error();
        }
        if (n2 != jpeg_decompress_struct.num_components * 3) {
            error();
        }
        if (jpeg_decompress_struct.comp_info == null) {
            jpeg_decompress_struct.comp_info = new jpeg_component_info[jpeg_decompress_struct.num_components];
        }
        for (int i = 0; i < jpeg_decompress_struct.num_components; ++i) {
            final jpeg_component_info[] comp_info = jpeg_decompress_struct.comp_info;
            final int n3 = i;
            final jpeg_component_info jpeg_component_info = new jpeg_component_info();
            comp_info[n3] = jpeg_component_info;
            final jpeg_component_info jpeg_component_info2 = jpeg_component_info;
            jpeg_component_info2.component_index = i;
            if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
                fill_input_buffer(jpeg_decompress_struct);
            }
            jpeg_component_info2.component_id = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF);
            if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
                fill_input_buffer(jpeg_decompress_struct);
            }
            final int n4 = jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF;
            jpeg_component_info2.h_samp_factor = (n4 >> 4 & 0xF);
            jpeg_component_info2.v_samp_factor = (n4 & 0xF);
            if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
                fill_input_buffer(jpeg_decompress_struct);
            }
            jpeg_component_info2.quant_tbl_no = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF);
        }
        return jpeg_decompress_struct.marker.saw_SOF = true;
    }
    
    static void sep_upsample(final jpeg_decompress_struct jpeg_decompress_struct, final byte[][][] array, final int[] array2, final int[] array3, final int n, final byte[][] array4, final int[] array5, int n2) {
        final jpeg_upsampler upsample = jpeg_decompress_struct.upsample;
        if (upsample.next_row_out >= jpeg_decompress_struct.max_v_samp_factor) {
            for (int i = 0; i < jpeg_decompress_struct.num_components; ++i) {
                final jpeg_component_info jpeg_component_info = jpeg_decompress_struct.comp_info[i];
                final int n3 = array2[i] + array3[0] * upsample.rowgroup_height[i];
                switch (upsample.methods[i]) {
                    case 0: {
                        noop_upsample(jpeg_decompress_struct, jpeg_component_info, array[i], n3, upsample.color_buf, upsample.color_buf_offset, i);
                        break;
                    }
                    case 1: {
                        fullsize_upsample(jpeg_decompress_struct, jpeg_component_info, array[i], n3, upsample.color_buf, upsample.color_buf_offset, i);
                        break;
                    }
                    case 2: {
                        h2v1_fancy_upsample(jpeg_decompress_struct, jpeg_component_info, array[i], n3, upsample.color_buf, upsample.color_buf_offset, i);
                        break;
                    }
                    case 3: {
                        h2v1_upsample(jpeg_decompress_struct, jpeg_component_info, array[i], n3, upsample.color_buf, upsample.color_buf_offset, i);
                        break;
                    }
                    case 4: {
                        h2v2_fancy_upsample(jpeg_decompress_struct, jpeg_component_info, array[i], n3, upsample.color_buf, upsample.color_buf_offset, i);
                        break;
                    }
                    case 5: {
                        h2v2_upsample(jpeg_decompress_struct, jpeg_component_info, array[i], n3, upsample.color_buf, upsample.color_buf_offset, i);
                        break;
                    }
                    case 6: {
                        int_upsample(jpeg_decompress_struct, jpeg_component_info, array[i], n3, upsample.color_buf, upsample.color_buf_offset, i);
                        break;
                    }
                }
            }
            upsample.next_row_out = 0;
        }
        int rows_to_go = jpeg_decompress_struct.max_v_samp_factor - upsample.next_row_out;
        if (rows_to_go > upsample.rows_to_go) {
            rows_to_go = upsample.rows_to_go;
        }
        n2 -= array5[0];
        if (rows_to_go > n2) {
            rows_to_go = n2;
        }
        switch (jpeg_decompress_struct.cconvert.color_convert) {
            case 0: {
                null_convert(jpeg_decompress_struct, upsample.color_buf, upsample.color_buf_offset, upsample.next_row_out, array4, array5[0], rows_to_go);
                break;
            }
            case 1: {
                grayscale_convert(jpeg_decompress_struct, upsample.color_buf, upsample.color_buf_offset, upsample.next_row_out, array4, array5[0], rows_to_go);
                break;
            }
            case 2: {
                ycc_rgb_convert(jpeg_decompress_struct, upsample.color_buf, upsample.color_buf_offset, upsample.next_row_out, array4, array5[0], rows_to_go);
                break;
            }
            case 3: {
                gray_rgb_convert(jpeg_decompress_struct, upsample.color_buf, upsample.color_buf_offset, upsample.next_row_out, array4, array5[0], rows_to_go);
                break;
            }
            case 4: {
                error();
                break;
            }
        }
        final int n4 = 0;
        array5[n4] += rows_to_go;
        final jpeg_upsampler jpeg_upsampler = upsample;
        jpeg_upsampler.rows_to_go -= rows_to_go;
        final jpeg_upsampler jpeg_upsampler2 = upsample;
        jpeg_upsampler2.next_row_out += rows_to_go;
        if (upsample.next_row_out >= jpeg_decompress_struct.max_v_samp_factor) {
            final int n5 = 0;
            ++array3[n5];
        }
    }
    
    static void noop_upsample(final jpeg_decompress_struct jpeg_decompress_struct, final jpeg_component_info jpeg_component_info, final byte[][] array, final int n, final byte[][][] array2, final int[] array3, final int n2) {
        array2[n2] = null;
    }
    
    static void fullsize_upsample(final jpeg_decompress_struct jpeg_decompress_struct, final jpeg_component_info jpeg_component_info, final byte[][] array, final int n, final byte[][][] array2, final int[] array3, final int n2) {
        array2[n2] = array;
        array3[n2] = n;
    }
    
    static void h2v1_upsample(final jpeg_decompress_struct jpeg_decompress_struct, final jpeg_component_info jpeg_component_info, final byte[][] array, final int n, final byte[][][] array2, final int[] array3, final int n2) {
        final byte[][] array4 = array2[n2];
        array3[n2] = 0;
        for (int i = 0; i < jpeg_decompress_struct.max_v_samp_factor; ++i) {
            final byte[] array5 = array[i + n];
            final byte[] array6 = array4[i];
            int n3 = 0;
            byte b;
            for (int j = 0; j < j + jpeg_decompress_struct.output_width; array6[j++] = b, array6[j++] = b) {
                b = array5[n3++];
            }
        }
    }
    
    static void h2v2_upsample(final jpeg_decompress_struct jpeg_decompress_struct, final jpeg_component_info jpeg_component_info, final byte[][] array, final int n, final byte[][][] array2, final int[] array3, final int n2) {
        final byte[][] array4 = array2[n2];
        array3[n2] = 0;
        int n3;
        for (int i = n3 = 0; i < jpeg_decompress_struct.max_v_samp_factor; i += 2) {
            final byte[] array5 = array[n3 + n];
            final byte[] array6 = array4[i];
            int n4 = 0;
            byte b;
            for (int j = 0; j < j + jpeg_decompress_struct.output_width; array6[j++] = b, array6[j++] = b) {
                b = array5[n4++];
            }
            jcopy_sample_rows(array4, i, array4, i + 1, 1, jpeg_decompress_struct.output_width);
            ++n3;
        }
    }
    
    static void h2v1_fancy_upsample(final jpeg_decompress_struct jpeg_decompress_struct, final jpeg_component_info jpeg_component_info, final byte[][] array, final int n, final byte[][][] array2, final int[] array3, final int n2) {
        final byte[][] array4 = array2[n2];
        array3[n2] = 0;
        for (int i = 0; i < jpeg_decompress_struct.max_v_samp_factor; ++i) {
            final byte[] array5 = array[i + n];
            final byte[] array6 = array4[i];
            int n3 = 0;
            int n4 = 0;
            final int n5 = array5[n3++] & 0xFF;
            array6[n4++] = (byte)n5;
            array6[n4++] = (byte)(n5 * 3 + (array5[n3] & 0xFF) + 2 >> 2);
            for (int j = jpeg_component_info.downsampled_width - 2; j > 0; --j) {
                final int n6 = (array5[n3++] & 0xFF) * 3;
                array6[n4++] = (byte)(n6 + (array5[n3 - 2] & 0xFF) + 1 >> 2);
                array6[n4++] = (byte)(n6 + (array5[n3] & 0xFF) + 2 >> 2);
            }
            final int n7 = array5[n3] & 0xFF;
            array6[n4++] = (byte)(n7 * 3 + (array5[n3 - 1] & 0xFF) + 1 >> 2);
            array6[n4++] = (byte)n7;
        }
    }
    
    static void h2v2_fancy_upsample(final jpeg_decompress_struct jpeg_decompress_struct, final jpeg_component_info jpeg_component_info, final byte[][] array, final int n, final byte[][][] array2, final int[] array3, final int n2) {
        final byte[][] array4 = array2[n2];
        array3[n2] = 0;
        int n3;
        int i = n3 = 0;
        while (i < jpeg_decompress_struct.max_v_samp_factor) {
            for (int j = 0; j < 2; ++j) {
                final byte[] array5 = array[n3 + n];
                byte[] array6;
                if (j == 0) {
                    array6 = array[n3 - 1 + n];
                }
                else {
                    array6 = array[n3 + 1 + n];
                }
                final byte[] array7 = array4[i++];
                int n4 = 0;
                int n5 = 0;
                int n6 = 0;
                final int n7 = (array5[n4++] & 0xFF) * 3 + (array6[n5++] & 0xFF);
                final int n8 = (array5[n4++] & 0xFF) * 3 + (array6[n5++] & 0xFF);
                array7[n6++] = (byte)(n7 * 4 + 8 >> 4);
                array7[n6++] = (byte)(n7 * 3 + n8 + 7 >> 4);
                int n9 = n7;
                int n10 = n8;
                for (int k = jpeg_component_info.downsampled_width - 2; k > 0; --k) {
                    final int n11 = (array5[n4++] & 0xFF) * 3 + (array6[n5++] & 0xFF);
                    array7[n6++] = (byte)(n10 * 3 + n9 + 8 >> 4);
                    array7[n6++] = (byte)(n10 * 3 + n11 + 7 >> 4);
                    n9 = n10;
                    n10 = n11;
                }
                array7[n6++] = (byte)(n10 * 3 + n9 + 8 >> 4);
                array7[n6++] = (byte)(n10 * 4 + 7 >> 4);
            }
            ++n3;
        }
    }
    
    static void int_upsample(final jpeg_decompress_struct jpeg_decompress_struct, final jpeg_component_info jpeg_component_info, final byte[][] array, final int n, final byte[][][] array2, final int[] array3, final int n2) {
        final jpeg_upsampler upsample = jpeg_decompress_struct.upsample;
        final byte[][] array4 = array2[n2];
        array3[n2] = 0;
        final byte b = upsample.h_expand[jpeg_component_info.component_index];
        final byte b2 = upsample.v_expand[jpeg_component_info.component_index];
        int n3;
        for (int i = n3 = 0; i < jpeg_decompress_struct.max_v_samp_factor; i += b2) {
            final byte[] array5 = array[n3 + n];
            int n4 = 0;
            final byte[] array6 = array4[i];
            int j = 0;
            while (j < j + jpeg_decompress_struct.output_width) {
                final byte b3 = array5[n4++];
                for (int k = b; k > 0; --k) {
                    array6[j++] = b3;
                }
            }
            if (b2 > 1) {
                jcopy_sample_rows(array4, i, array4, i + 1, b2 - 1, jpeg_decompress_struct.output_width);
            }
            ++n3;
        }
    }
    
    static void null_convert(final jpeg_decompress_struct jpeg_decompress_struct, final byte[][][] array, final int[] array2, int n, final byte[][] array3, int n2, int n3) {
        final int num_components = jpeg_decompress_struct.num_components;
        final int output_width = jpeg_decompress_struct.output_width;
        while (--n3 >= 0) {
            for (int i = 0; i < num_components; ++i) {
                final byte[] array4 = array[i][n + array2[0]];
                final byte[] array5 = array3[n2];
                int n4 = 0;
                switch (i) {
                    case 2: {
                        n4 = 0;
                        break;
                    }
                    case 1: {
                        n4 = 1;
                        break;
                    }
                    case 0: {
                        n4 = 2;
                        break;
                    }
                }
                int n5 = n4;
                int n6 = 0;
                for (int j = output_width; j > 0; --j) {
                    array5[n5] = array4[n6++];
                    n5 += num_components;
                }
            }
            ++n;
            ++n2;
        }
    }
    
    static void grayscale_convert(final jpeg_decompress_struct jpeg_decompress_struct, final byte[][][] array, final int[] array2, final int n, final byte[][] array3, final int n2, final int n3) {
        jcopy_sample_rows(array[0], n + array2[0], array3, n2, n3, jpeg_decompress_struct.output_width);
    }
    
    static void gray_rgb_convert(final jpeg_decompress_struct jpeg_decompress_struct, final byte[][][] array, final int[] array2, int n, final byte[][] array3, int n2, int n3) {
        final int output_width = jpeg_decompress_struct.output_width;
        while (--n3 >= 0) {
            final byte[] array4 = array[0][n++ + array2[0]];
            final byte[] array5 = array3[n2++];
            int n4 = 0;
            for (int i = 0; i < output_width; ++i) {
                final byte[] array6 = array5;
                final int n5 = 2 + n4;
                final byte[] array7 = array5;
                final int n6 = 1 + n4;
                final byte[] array8 = array5;
                final int n7 = 0 + n4;
                final byte b = array4[i];
                array8[n7] = b;
                array6[n5] = (array7[n6] = b);
                n4 += 3;
            }
        }
    }
    
    static void ycc_rgb_convert(final jpeg_decompress_struct jpeg_decompress_struct, final byte[][][] array, final int[] array2, int n, final byte[][] array3, int n2, int n3) {
        final jpeg_color_deconverter cconvert = jpeg_decompress_struct.cconvert;
        final int output_width = jpeg_decompress_struct.output_width;
        final byte[] sample_range_limit = jpeg_decompress_struct.sample_range_limit;
        final int sample_range_limit_offset = jpeg_decompress_struct.sample_range_limit_offset;
        final int[] cr_r_tab = cconvert.Cr_r_tab;
        final int[] cb_b_tab = cconvert.Cb_b_tab;
        final int[] cr_g_tab = cconvert.Cr_g_tab;
        final int[] cb_g_tab = cconvert.Cb_g_tab;
        while (--n3 >= 0) {
            final byte[] array4 = array[0][n + array2[0]];
            final byte[] array5 = array[1][n + array2[1]];
            final byte[] array6 = array[2][n + array2[2]];
            ++n;
            final byte[] array7 = array3[n2++];
            int n4 = 0;
            for (int i = 0; i < output_width; ++i) {
                final int n5 = array4[i] & 0xFF;
                final int n6 = array5[i] & 0xFF;
                final int n7 = array6[i] & 0xFF;
                array7[n4 + 2] = sample_range_limit[n5 + cr_r_tab[n7] + sample_range_limit_offset];
                array7[n4 + 1] = sample_range_limit[n5 + (cb_g_tab[n6] + cr_g_tab[n7] >> 16) + sample_range_limit_offset];
                array7[n4 + 0] = sample_range_limit[n5 + cb_b_tab[n6] + sample_range_limit_offset];
                n4 += 3;
            }
        }
    }
    
    static boolean process_APPn(final int n, final jpeg_decompress_struct jpeg_decompress_struct) {
        if (n == 0 || n == 14) {
            return get_interesting_appn(jpeg_decompress_struct);
        }
        return skip_variable(jpeg_decompress_struct);
    }
    
    static boolean process_COM(final jpeg_decompress_struct jpeg_decompress_struct) {
        return skip_variable(jpeg_decompress_struct);
    }
    
    static void skip_input_data(final jpeg_decompress_struct jpeg_decompress_struct, int i) {
        if (i > 0) {
            while (i > jpeg_decompress_struct.bytes_in_buffer - jpeg_decompress_struct.bytes_offset) {
                i -= jpeg_decompress_struct.bytes_in_buffer - jpeg_decompress_struct.bytes_offset;
                if (!fill_input_buffer(jpeg_decompress_struct)) {
                    error();
                }
            }
            jpeg_decompress_struct.bytes_offset += i;
        }
    }
    
    static boolean skip_variable(final jpeg_decompress_struct jpeg_decompress_struct) {
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        final int n = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF) << 8;
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        int n2 = n | (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF);
        n2 -= 2;
        if (n2 > 0) {
            skip_input_data(jpeg_decompress_struct, n2);
        }
        return true;
    }
    
    static boolean get_interesting_appn(final jpeg_decompress_struct jpeg_decompress_struct) {
        final byte[] array = new byte[14];
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        final int n = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF) << 8;
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        int n2 = n | (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF);
        n2 -= 2;
        int n3;
        if (n2 >= 14) {
            n3 = 14;
        }
        else if (n2 > 0) {
            n3 = n2;
        }
        else {
            n3 = 0;
        }
        for (int i = 0; i < n3; ++i) {
            if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
                fill_input_buffer(jpeg_decompress_struct);
            }
            array[i] = jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++];
        }
        final int n4 = n2 - n3;
        switch (jpeg_decompress_struct.unread_marker) {
            case 224: {
                examine_app0(jpeg_decompress_struct, array, n3, n4);
                break;
            }
            case 238: {
                examine_app14(jpeg_decompress_struct, array, n3, n4);
                break;
            }
            default: {
                error();
                break;
            }
        }
        if (n4 > 0) {
            skip_input_data(jpeg_decompress_struct, n4);
        }
        return true;
    }
    
    static void examine_app0(final jpeg_decompress_struct jpeg_decompress_struct, final byte[] array, final int n, final int n2) {
        int n3 = n + n2;
        if (n >= 14 && (array[0] & 0xFF) == 0x4A && (array[1] & 0xFF) == 0x46 && (array[2] & 0xFF) == 0x49 && (array[3] & 0xFF) == 0x46 && (array[4] & 0xFF) == 0x0) {
            jpeg_decompress_struct.saw_JFIF_marker = true;
            jpeg_decompress_struct.JFIF_major_version = array[5];
            jpeg_decompress_struct.JFIF_minor_version = (byte)(array[6] & 0xFF);
            jpeg_decompress_struct.density_unit = (byte)(array[7] & 0xFF);
            jpeg_decompress_struct.X_density = (short)(((array[8] & 0xFF) << 8) + (array[9] & 0xFF));
            jpeg_decompress_struct.Y_density = (short)(((array[10] & 0xFF) << 8) + (array[11] & 0xFF));
            final byte jfif_major_version = jpeg_decompress_struct.JFIF_major_version;
            final byte b = array[12];
            final byte b2 = array[13];
            n3 -= 14;
            final byte b3 = array[12];
            final byte b4 = array[13];
        }
        else if (n >= 6 && (array[0] & 0xFF) == 0x4A && (array[1] & 0xFF) == 0x46 && (array[2] & 0xFF) == 0x58 && (array[3] & 0xFF) == 0x58 && (array[4] & 0xFF) == 0x0) {
            switch (array[5] & 0xFF) {
                case 16: {}
                case 17: {}
            }
        }
    }
    
    static void examine_app14(final jpeg_decompress_struct jpeg_decompress_struct, final byte[] array, final int n, final int n2) {
        if (n >= 12 && (array[0] & 0xFF) == 0x41 && (array[1] & 0xFF) == 0x64 && (array[2] & 0xFF) == 0x6F && (array[3] & 0xFF) == 0x62 && (array[4] & 0xFF) == 0x65) {
            final int n3 = array[11] & 0xFF;
            jpeg_decompress_struct.saw_Adobe_marker = true;
            jpeg_decompress_struct.Adobe_transform = (byte)n3;
        }
    }
    
    static boolean get_soi(final jpeg_decompress_struct jpeg_decompress_struct) {
        if (jpeg_decompress_struct.marker.saw_SOI) {
            error();
        }
        for (int i = 0; i < 16; ++i) {
            jpeg_decompress_struct.arith_dc_L[i] = 0;
            jpeg_decompress_struct.arith_dc_U[i] = 1;
            jpeg_decompress_struct.arith_ac_K[i] = 5;
        }
        jpeg_decompress_struct.restart_interval = 0;
        jpeg_decompress_struct.jpeg_color_space = 0;
        jpeg_decompress_struct.CCIR601_sampling = false;
        jpeg_decompress_struct.saw_JFIF_marker = false;
        jpeg_decompress_struct.JFIF_major_version = 1;
        jpeg_decompress_struct.JFIF_minor_version = 1;
        jpeg_decompress_struct.density_unit = 0;
        jpeg_decompress_struct.X_density = 1;
        jpeg_decompress_struct.Y_density = 1;
        jpeg_decompress_struct.saw_Adobe_marker = false;
        jpeg_decompress_struct.Adobe_transform = 0;
        return jpeg_decompress_struct.marker.saw_SOI = true;
    }
    
    static void jinit_input_controller(final jpeg_decompress_struct jpeg_decompress_struct) {
        final jpeg_input_controller inputctl = new jpeg_input_controller();
        jpeg_decompress_struct.inputctl = inputctl;
        final jpeg_input_controller jpeg_input_controller = inputctl;
        jpeg_input_controller.has_multiple_scans = false;
        jpeg_input_controller.eoi_reached = false;
        jpeg_input_controller.inheaders = true;
    }
    
    static void reset_marker_reader(final jpeg_decompress_struct jpeg_decompress_struct) {
        final jpeg_marker_reader marker = jpeg_decompress_struct.marker;
        jpeg_decompress_struct.comp_info = null;
        jpeg_decompress_struct.input_scan_number = 0;
        jpeg_decompress_struct.unread_marker = 0;
        marker.saw_SOI = false;
        marker.saw_SOF = false;
        marker.discarded_bytes = 0;
    }
    
    static void reset_input_controller(final jpeg_decompress_struct jpeg_decompress_struct) {
        final jpeg_input_controller inputctl = jpeg_decompress_struct.inputctl;
        inputctl.has_multiple_scans = false;
        inputctl.eoi_reached = false;
        inputctl.inheaders = true;
        reset_marker_reader(jpeg_decompress_struct);
        jpeg_decompress_struct.coef_bits = null;
    }
    
    static void finish_output_pass(final jpeg_decompress_struct jpeg_decompress_struct) {
        final jpeg_decomp_master master = jpeg_decompress_struct.master;
        if (jpeg_decompress_struct.quantize_colors) {
            error(20);
        }
        final jpeg_decomp_master jpeg_decomp_master = master;
        ++jpeg_decomp_master.pass_number;
    }
    
    static void jpeg_destroy(final jpeg_decompress_struct jpeg_decompress_struct) {
        jpeg_decompress_struct.global_state = 0;
    }
    
    static void jpeg_destroy_decompress(final jpeg_decompress_struct jpeg_decompress_struct) {
        jpeg_destroy(jpeg_decompress_struct);
    }
    
    static boolean jpeg_input_complete(final jpeg_decompress_struct jpeg_decompress_struct) {
        if (jpeg_decompress_struct.global_state < 200 || jpeg_decompress_struct.global_state > 210) {
            error();
        }
        return jpeg_decompress_struct.inputctl.eoi_reached;
    }
    
    static boolean jpeg_start_output(final jpeg_decompress_struct jpeg_decompress_struct, int input_scan_number) {
        if (jpeg_decompress_struct.global_state != 207 && jpeg_decompress_struct.global_state != 204) {
            error();
        }
        if (input_scan_number <= 0) {
            input_scan_number = 1;
        }
        if (jpeg_decompress_struct.inputctl.eoi_reached && input_scan_number > jpeg_decompress_struct.input_scan_number) {
            input_scan_number = jpeg_decompress_struct.input_scan_number;
        }
        jpeg_decompress_struct.output_scan_number = input_scan_number;
        return output_pass_setup(jpeg_decompress_struct);
    }
    
    static boolean jpeg_finish_output(final jpeg_decompress_struct jpeg_decompress_struct) {
        if ((jpeg_decompress_struct.global_state == 205 || jpeg_decompress_struct.global_state == 206) && jpeg_decompress_struct.buffered_image) {
            finish_output_pass(jpeg_decompress_struct);
            jpeg_decompress_struct.global_state = 208;
        }
        else if (jpeg_decompress_struct.global_state != 208) {
            error();
        }
        while (jpeg_decompress_struct.input_scan_number <= jpeg_decompress_struct.output_scan_number && !jpeg_decompress_struct.inputctl.eoi_reached) {
            if (consume_input(jpeg_decompress_struct) == 0) {
                return false;
            }
        }
        jpeg_decompress_struct.global_state = 207;
        return true;
    }
    
    static boolean jpeg_finish_decompress(final jpeg_decompress_struct jpeg_decompress_struct) {
        if ((jpeg_decompress_struct.global_state == 205 || jpeg_decompress_struct.global_state == 206) && !jpeg_decompress_struct.buffered_image) {
            if (jpeg_decompress_struct.output_scanline < jpeg_decompress_struct.output_height) {
                error();
            }
            finish_output_pass(jpeg_decompress_struct);
            jpeg_decompress_struct.global_state = 210;
        }
        else if (jpeg_decompress_struct.global_state == 207) {
            jpeg_decompress_struct.global_state = 210;
        }
        else if (jpeg_decompress_struct.global_state != 210) {
            error();
        }
        while (!jpeg_decompress_struct.inputctl.eoi_reached) {
            if (consume_input(jpeg_decompress_struct) == 0) {
                return false;
            }
        }
        jpeg_abort(jpeg_decompress_struct);
        return true;
    }
    
    static int jpeg_read_header(final jpeg_decompress_struct jpeg_decompress_struct, final boolean b) {
        if (jpeg_decompress_struct.global_state != 200 && jpeg_decompress_struct.global_state != 201) {
            error();
        }
        int jpeg_consume_input = jpeg_consume_input(jpeg_decompress_struct);
        switch (jpeg_consume_input) {
            case 1: {
                jpeg_consume_input = 1;
                break;
            }
            case 2: {
                if (b) {
                    error();
                }
                jpeg_abort(jpeg_decompress_struct);
                jpeg_consume_input = 2;
                break;
            }
        }
        return jpeg_consume_input;
    }
    
    static int dummy_consume_data(final jpeg_decompress_struct jpeg_decompress_struct) {
        return 0;
    }
    
    static int consume_data(final jpeg_decompress_struct jpeg_decompress_struct) {
        final jpeg_d_coef_controller coef = jpeg_decompress_struct.coef;
        for (int i = coef.MCU_vert_offset; i < coef.MCU_rows_per_iMCU_row; ++i) {
            for (int j = coef.MCU_ctr; j < jpeg_decompress_struct.MCUs_per_row; ++j) {
                int n = 0;
                for (int k = 0; k < jpeg_decompress_struct.comps_in_scan; ++k) {
                    final jpeg_component_info jpeg_component_info = jpeg_decompress_struct.cur_comp_info[k];
                    final int n2 = j * jpeg_component_info.MCU_width;
                    for (int l = 0; l < jpeg_component_info.MCU_height; ++l) {
                        final short[][] array = coef.whole_image[jpeg_component_info.component_index][l + i + jpeg_decompress_struct.input_iMCU_row * jpeg_component_info.v_samp_factor];
                        int n3 = n2;
                        for (int n4 = 0; n4 < jpeg_component_info.MCU_width; ++n4) {
                            coef.MCU_buffer[n++] = array[n3++];
                        }
                    }
                }
                if (!jpeg_decompress_struct.entropy.decode_mcu(jpeg_decompress_struct, coef.MCU_buffer)) {
                    coef.MCU_vert_offset = i;
                    coef.MCU_ctr = j;
                    return 0;
                }
            }
            coef.MCU_ctr = 0;
        }
        if (++jpeg_decompress_struct.input_iMCU_row < jpeg_decompress_struct.total_iMCU_rows) {
            coef.start_iMCU_row(jpeg_decompress_struct);
            return 3;
        }
        finish_input_pass(jpeg_decompress_struct);
        return 4;
    }
    
    static int consume_input(final jpeg_decompress_struct jpeg_decompress_struct) {
        Label_0080: {
            switch (jpeg_decompress_struct.inputctl.consume_input) {
                case 1: {
                    switch (jpeg_decompress_struct.coef.consume_data) {
                        case 0: {
                            return consume_data(jpeg_decompress_struct);
                        }
                        case 1: {
                            return dummy_consume_data(jpeg_decompress_struct);
                        }
                        default: {
                            error();
                            break Label_0080;
                        }
                    }
                    break;
                }
                case 0: {
                    return consume_markers(jpeg_decompress_struct);
                }
                default: {
                    error();
                    break;
                }
            }
        }
        return 0;
    }
    
    static boolean fill_input_buffer(final jpeg_decompress_struct jpeg_decompress_struct) {
        try {
            int read = jpeg_decompress_struct.inputStream.read(jpeg_decompress_struct.buffer);
            if (read <= 0) {
                if (jpeg_decompress_struct.start_of_file) {
                    error();
                }
                jpeg_decompress_struct.buffer[0] = -1;
                jpeg_decompress_struct.buffer[1] = -39;
                read = 2;
            }
            jpeg_decompress_struct.bytes_in_buffer = read;
            jpeg_decompress_struct.bytes_offset = 0;
            jpeg_decompress_struct.start_of_file = false;
        }
        catch (IOException ex) {
            error(39);
            return false;
        }
        return true;
    }
    
    static boolean first_marker(final jpeg_decompress_struct jpeg_decompress_struct) {
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        final int n = jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF;
        if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
            fill_input_buffer(jpeg_decompress_struct);
        }
        final int unread_marker = jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF;
        if (n != 255 || unread_marker != 216) {
            error();
        }
        jpeg_decompress_struct.unread_marker = unread_marker;
        return true;
    }
    
    static boolean next_marker(final jpeg_decompress_struct jpeg_decompress_struct) {
        int j;
        while (true) {
            if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
                fill_input_buffer(jpeg_decompress_struct);
            }
            for (int i = jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF; i != 255; i = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF)) {
                final jpeg_marker_reader marker = jpeg_decompress_struct.marker;
                ++marker.discarded_bytes;
                if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
                    fill_input_buffer(jpeg_decompress_struct);
                }
            }
            do {
                if (jpeg_decompress_struct.bytes_offset == jpeg_decompress_struct.bytes_in_buffer) {
                    fill_input_buffer(jpeg_decompress_struct);
                }
                j = (jpeg_decompress_struct.buffer[jpeg_decompress_struct.bytes_offset++] & 0xFF);
            } while (j == 255);
            if (j != 0) {
                break;
            }
            final jpeg_marker_reader marker2 = jpeg_decompress_struct.marker;
            marker2.discarded_bytes += 2;
        }
        if (jpeg_decompress_struct.marker.discarded_bytes != 0) {
            jpeg_decompress_struct.marker.discarded_bytes = 0;
        }
        jpeg_decompress_struct.unread_marker = j;
        return true;
    }
    
    static int read_markers(final jpeg_decompress_struct jpeg_decompress_struct) {
        while (true) {
            if (jpeg_decompress_struct.unread_marker == 0) {
                if (!jpeg_decompress_struct.marker.saw_SOI) {
                    if (!first_marker(jpeg_decompress_struct)) {
                        return 0;
                    }
                }
                else if (!next_marker(jpeg_decompress_struct)) {
                    return 0;
                }
            }
            switch (jpeg_decompress_struct.unread_marker) {
                case 216: {
                    if (!get_soi(jpeg_decompress_struct)) {
                        return 0;
                    }
                    break;
                }
                case 192:
                case 193: {
                    if (!get_sof(jpeg_decompress_struct, false, false)) {
                        return 0;
                    }
                    break;
                }
                case 194: {
                    if (!get_sof(jpeg_decompress_struct, true, false)) {
                        return 0;
                    }
                    break;
                }
                case 201: {
                    if (!get_sof(jpeg_decompress_struct, false, true)) {
                        return 0;
                    }
                    break;
                }
                case 202: {
                    if (!get_sof(jpeg_decompress_struct, true, true)) {
                        return 0;
                    }
                    break;
                }
                case 195:
                case 197:
                case 198:
                case 199:
                case 200:
                case 203:
                case 205:
                case 206:
                case 207: {
                    error();
                    break;
                }
                case 218: {
                    if (!get_sos(jpeg_decompress_struct)) {
                        return 0;
                    }
                    jpeg_decompress_struct.unread_marker = 0;
                    return 1;
                }
                case 217: {
                    jpeg_decompress_struct.unread_marker = 0;
                    return 2;
                }
                case 204: {
                    if (!get_dac(jpeg_decompress_struct)) {
                        return 0;
                    }
                    break;
                }
                case 196: {
                    if (!get_dht(jpeg_decompress_struct)) {
                        return 0;
                    }
                    break;
                }
                case 219: {
                    if (!get_dqt(jpeg_decompress_struct)) {
                        return 0;
                    }
                    break;
                }
                case 221: {
                    if (!get_dri(jpeg_decompress_struct)) {
                        return 0;
                    }
                    break;
                }
                case 224:
                case 225:
                case 226:
                case 227:
                case 228:
                case 229:
                case 230:
                case 231:
                case 232:
                case 233:
                case 234:
                case 235:
                case 236:
                case 237:
                case 238:
                case 239: {
                    if (!process_APPn(jpeg_decompress_struct.unread_marker - 224, jpeg_decompress_struct)) {
                        return 0;
                    }
                    break;
                }
                case 254: {
                    if (!process_COM(jpeg_decompress_struct)) {
                        return 0;
                    }
                    break;
                }
                case 1:
                case 208:
                case 209:
                case 210:
                case 211:
                case 212:
                case 213:
                case 214:
                case 215: {
                    break;
                }
                case 220: {
                    if (!skip_variable(jpeg_decompress_struct)) {
                        return 0;
                    }
                    break;
                }
                default: {
                    error();
                    break;
                }
            }
            jpeg_decompress_struct.unread_marker = 0;
        }
    }
    
    static long jdiv_round_up(final long n, final long n2) {
        return (n + n2 - 1L) / n2;
    }
    
    static void initial_setup(final jpeg_decompress_struct jpeg_decompress_struct) {
        if (jpeg_decompress_struct.image_height > 65500 || jpeg_decompress_struct.image_width > 65500) {
            error();
        }
        if (jpeg_decompress_struct.data_precision != 8) {
            error(" [data precision=" + jpeg_decompress_struct.data_precision + "]");
        }
        if (jpeg_decompress_struct.num_components > 10) {
            error();
        }
        jpeg_decompress_struct.max_h_samp_factor = 1;
        jpeg_decompress_struct.max_v_samp_factor = 1;
        for (int i = 0; i < jpeg_decompress_struct.num_components; ++i) {
            final jpeg_component_info jpeg_component_info = jpeg_decompress_struct.comp_info[i];
            if (jpeg_component_info.h_samp_factor <= 0 || jpeg_component_info.h_samp_factor > 4 || jpeg_component_info.v_samp_factor <= 0 || jpeg_component_info.v_samp_factor > 4) {
                error();
            }
            jpeg_decompress_struct.max_h_samp_factor = Math.max(jpeg_decompress_struct.max_h_samp_factor, jpeg_component_info.h_samp_factor);
            jpeg_decompress_struct.max_v_samp_factor = Math.max(jpeg_decompress_struct.max_v_samp_factor, jpeg_component_info.v_samp_factor);
        }
        jpeg_decompress_struct.min_DCT_scaled_size = 8;
        for (int j = 0; j < jpeg_decompress_struct.num_components; ++j) {
            final jpeg_component_info jpeg_component_info2 = jpeg_decompress_struct.comp_info[j];
            jpeg_component_info2.DCT_scaled_size = 8;
            jpeg_component_info2.width_in_blocks = (int)jdiv_round_up(jpeg_decompress_struct.image_width * jpeg_component_info2.h_samp_factor, jpeg_decompress_struct.max_h_samp_factor * 8);
            jpeg_component_info2.height_in_blocks = (int)jdiv_round_up(jpeg_decompress_struct.image_height * jpeg_component_info2.v_samp_factor, jpeg_decompress_struct.max_v_samp_factor * 8);
            jpeg_component_info2.downsampled_width = (int)jdiv_round_up(jpeg_decompress_struct.image_width * jpeg_component_info2.h_samp_factor, jpeg_decompress_struct.max_h_samp_factor);
            jpeg_component_info2.downsampled_height = (int)jdiv_round_up(jpeg_decompress_struct.image_height * jpeg_component_info2.v_samp_factor, jpeg_decompress_struct.max_v_samp_factor);
            jpeg_component_info2.component_needed = true;
            jpeg_component_info2.quant_table = null;
        }
        jpeg_decompress_struct.total_iMCU_rows = (int)jdiv_round_up(jpeg_decompress_struct.image_height, jpeg_decompress_struct.max_v_samp_factor * 8);
        if (jpeg_decompress_struct.comps_in_scan < jpeg_decompress_struct.num_components || jpeg_decompress_struct.progressive_mode) {
            jpeg_decompress_struct.inputctl.has_multiple_scans = true;
        }
        else {
            jpeg_decompress_struct.inputctl.has_multiple_scans = false;
        }
    }
    
    static void per_scan_setup(final jpeg_decompress_struct jpeg_decompress_struct) {
        if (jpeg_decompress_struct.comps_in_scan == 1) {
            final jpeg_component_info jpeg_component_info = jpeg_decompress_struct.cur_comp_info[0];
            jpeg_decompress_struct.MCUs_per_row = jpeg_component_info.width_in_blocks;
            jpeg_decompress_struct.MCU_rows_in_scan = jpeg_component_info.height_in_blocks;
            jpeg_component_info.MCU_width = 1;
            jpeg_component_info.MCU_height = 1;
            jpeg_component_info.MCU_blocks = 1;
            jpeg_component_info.MCU_sample_width = jpeg_component_info.DCT_scaled_size;
            jpeg_component_info.last_col_width = 1;
            int v_samp_factor = jpeg_component_info.height_in_blocks % jpeg_component_info.v_samp_factor;
            if (v_samp_factor == 0) {
                v_samp_factor = jpeg_component_info.v_samp_factor;
            }
            jpeg_component_info.last_row_height = v_samp_factor;
            jpeg_decompress_struct.blocks_in_MCU = 1;
            jpeg_decompress_struct.MCU_membership[0] = 0;
        }
        else {
            if (jpeg_decompress_struct.comps_in_scan <= 0 || jpeg_decompress_struct.comps_in_scan > 4) {
                error();
            }
            jpeg_decompress_struct.MCUs_per_row = (int)jdiv_round_up(jpeg_decompress_struct.image_width, jpeg_decompress_struct.max_h_samp_factor * 8);
            jpeg_decompress_struct.MCU_rows_in_scan = (int)jdiv_round_up(jpeg_decompress_struct.image_height, jpeg_decompress_struct.max_v_samp_factor * 8);
            jpeg_decompress_struct.blocks_in_MCU = 0;
            for (int i = 0; i < jpeg_decompress_struct.comps_in_scan; ++i) {
                final jpeg_component_info jpeg_component_info2 = jpeg_decompress_struct.cur_comp_info[i];
                jpeg_component_info2.MCU_width = jpeg_component_info2.h_samp_factor;
                jpeg_component_info2.MCU_height = jpeg_component_info2.v_samp_factor;
                jpeg_component_info2.MCU_blocks = jpeg_component_info2.MCU_width * jpeg_component_info2.MCU_height;
                jpeg_component_info2.MCU_sample_width = jpeg_component_info2.MCU_width * jpeg_component_info2.DCT_scaled_size;
                int mcu_width = jpeg_component_info2.width_in_blocks % jpeg_component_info2.MCU_width;
                if (mcu_width == 0) {
                    mcu_width = jpeg_component_info2.MCU_width;
                }
                jpeg_component_info2.last_col_width = mcu_width;
                int mcu_height = jpeg_component_info2.height_in_blocks % jpeg_component_info2.MCU_height;
                if (mcu_height == 0) {
                    mcu_height = jpeg_component_info2.MCU_height;
                }
                jpeg_component_info2.last_row_height = mcu_height;
                int mcu_blocks = jpeg_component_info2.MCU_blocks;
                if (jpeg_decompress_struct.blocks_in_MCU + mcu_blocks > 10) {
                    error();
                }
                while (mcu_blocks-- > 0) {
                    jpeg_decompress_struct.MCU_membership[jpeg_decompress_struct.blocks_in_MCU++] = i;
                }
            }
        }
    }
    
    static void latch_quant_tables(final jpeg_decompress_struct jpeg_decompress_struct) {
        for (int i = 0; i < jpeg_decompress_struct.comps_in_scan; ++i) {
            final jpeg_component_info jpeg_component_info = jpeg_decompress_struct.cur_comp_info[i];
            if (jpeg_component_info.quant_table == null) {
                final int quant_tbl_no = jpeg_component_info.quant_tbl_no;
                if (quant_tbl_no < 0 || quant_tbl_no >= 4 || jpeg_decompress_struct.quant_tbl_ptrs[quant_tbl_no] == null) {
                    error();
                }
                final JQUANT_TBL quant_table = new JQUANT_TBL();
                System.arraycopy(jpeg_decompress_struct.quant_tbl_ptrs[quant_tbl_no].quantval, 0, quant_table.quantval, 0, quant_table.quantval.length);
                quant_table.sent_table = jpeg_decompress_struct.quant_tbl_ptrs[quant_tbl_no].sent_table;
                jpeg_component_info.quant_table = quant_table;
            }
        }
    }
    
    static void jpeg_make_d_derived_tbl(final jpeg_decompress_struct jpeg_decompress_struct, final boolean b, final int n, final d_derived_tbl d_derived_tbl) {
        final byte[] array = new byte[257];
        final int[] array2 = new int[257];
        if (n < 0 || n >= 4) {
            error();
        }
        final JHUFF_TBL pub = b ? jpeg_decompress_struct.dc_huff_tbl_ptrs[n] : jpeg_decompress_struct.ac_huff_tbl_ptrs[n];
        if (pub == null) {
            error();
        }
        d_derived_tbl.pub = pub;
        int n2 = 0;
        for (int i = 1; i <= 16; ++i) {
            int n3 = pub.bits[i] & 0xFF;
            if (n3 < 0 || n2 + n3 > 256) {
                error();
            }
            while (n3-- != 0) {
                array[n2++] = (byte)i;
            }
        }
        array[n2] = 0;
        final int n4 = n2;
        int n5 = 0;
        byte b2 = array[0];
        int n6 = 0;
        while (array[n6] != 0) {
            while (array[n6] == b2) {
                array2[n6++] = n5;
                ++n5;
            }
            if (n5 >= 1 << b2) {
                error();
            }
            n5 <<= 1;
            ++b2;
        }
        int n7 = 0;
        for (int j = 1; j <= 16; ++j) {
            if ((pub.bits[j] & 0xFF) != 0x0) {
                d_derived_tbl.valoffset[j] = n7 - array2[n7];
                n7 += (pub.bits[j] & 0xFF);
                d_derived_tbl.maxcode[j] = array2[n7 - 1];
            }
            else {
                d_derived_tbl.maxcode[j] = -1;
            }
        }
        d_derived_tbl.maxcode[17] = 1048575;
        for (int k = 0; k < d_derived_tbl.look_nbits.length; ++k) {
            d_derived_tbl.look_nbits[k] = 0;
        }
        int n8 = 0;
        for (int l = 1; l <= 8; ++l) {
            for (int n9 = 1; n9 <= (pub.bits[l] & 0xFF); ++n9, ++n8) {
                int n10 = array2[n8] << 8 - l;
                for (int n11 = 1 << 8 - l; n11 > 0; --n11) {
                    d_derived_tbl.look_nbits[n10] = l;
                    d_derived_tbl.look_sym[n10] = pub.huffval[n8];
                    ++n10;
                }
            }
        }
        if (b) {
            for (int n12 = 0; n12 < n4; ++n12) {
                final int n13 = pub.huffval[n12] & 0xFF;
                if (n13 < 0 || n13 > 15) {
                    error();
                }
            }
        }
    }
    
    static void start_input_pass(final jpeg_decompress_struct jpeg_decompress_struct) {
        per_scan_setup(jpeg_decompress_struct);
        latch_quant_tables(jpeg_decompress_struct);
        jpeg_decompress_struct.entropy.start_pass(jpeg_decompress_struct);
        jpeg_decompress_struct.coef.start_input_pass(jpeg_decompress_struct);
        jpeg_decompress_struct.inputctl.consume_input = 1;
    }
    
    static void finish_input_pass(final jpeg_decompress_struct jpeg_decompress_struct) {
        jpeg_decompress_struct.inputctl.consume_input = 0;
    }
    
    static int consume_markers(final jpeg_decompress_struct jpeg_decompress_struct) {
        final jpeg_input_controller inputctl = jpeg_decompress_struct.inputctl;
        if (inputctl.eoi_reached) {
            return 2;
        }
        final int read_markers = read_markers(jpeg_decompress_struct);
        switch (read_markers) {
            case 1: {
                if (inputctl.inheaders) {
                    initial_setup(jpeg_decompress_struct);
                    inputctl.inheaders = false;
                    break;
                }
                if (!inputctl.has_multiple_scans) {
                    error();
                }
                start_input_pass(jpeg_decompress_struct);
                break;
            }
            case 2: {
                inputctl.eoi_reached = true;
                if (inputctl.inheaders) {
                    if (jpeg_decompress_struct.marker.saw_SOF) {
                        error();
                        break;
                    }
                    break;
                }
                else {
                    if (jpeg_decompress_struct.output_scan_number > jpeg_decompress_struct.input_scan_number) {
                        jpeg_decompress_struct.output_scan_number = jpeg_decompress_struct.input_scan_number;
                        break;
                    }
                    break;
                }
                break;
            }
        }
        return read_markers;
    }
    
    static void default_decompress_parms(final jpeg_decompress_struct jpeg_decompress_struct) {
        switch (jpeg_decompress_struct.num_components) {
            case 1: {
                jpeg_decompress_struct.jpeg_color_space = 1;
                jpeg_decompress_struct.out_color_space = 1;
                break;
            }
            case 3: {
                if (jpeg_decompress_struct.saw_JFIF_marker) {
                    jpeg_decompress_struct.jpeg_color_space = 3;
                }
                else if (jpeg_decompress_struct.saw_Adobe_marker) {
                    switch (jpeg_decompress_struct.Adobe_transform) {
                        case 0: {
                            jpeg_decompress_struct.jpeg_color_space = 2;
                            break;
                        }
                        case 1: {
                            jpeg_decompress_struct.jpeg_color_space = 3;
                            break;
                        }
                        default: {
                            jpeg_decompress_struct.jpeg_color_space = 3;
                            break;
                        }
                    }
                }
                else {
                    final int component_id = jpeg_decompress_struct.comp_info[0].component_id;
                    final int component_id2 = jpeg_decompress_struct.comp_info[1].component_id;
                    final int component_id3 = jpeg_decompress_struct.comp_info[2].component_id;
                    if (component_id == 1 && component_id2 == 2 && component_id3 == 3) {
                        jpeg_decompress_struct.jpeg_color_space = 3;
                    }
                    else if (component_id == 82 && component_id2 == 71 && component_id3 == 66) {
                        jpeg_decompress_struct.jpeg_color_space = 2;
                    }
                    else {
                        jpeg_decompress_struct.jpeg_color_space = 3;
                    }
                }
                jpeg_decompress_struct.out_color_space = 2;
                break;
            }
            case 4: {
                if (jpeg_decompress_struct.saw_Adobe_marker) {
                    switch (jpeg_decompress_struct.Adobe_transform) {
                        case 0: {
                            jpeg_decompress_struct.jpeg_color_space = 4;
                            break;
                        }
                        case 2: {
                            jpeg_decompress_struct.jpeg_color_space = 5;
                            break;
                        }
                        default: {
                            jpeg_decompress_struct.jpeg_color_space = 5;
                            break;
                        }
                    }
                }
                else {
                    jpeg_decompress_struct.jpeg_color_space = 4;
                }
                jpeg_decompress_struct.out_color_space = 4;
                break;
            }
            default: {
                jpeg_decompress_struct.jpeg_color_space = 0;
                jpeg_decompress_struct.out_color_space = 0;
                break;
            }
        }
        jpeg_decompress_struct.scale_num = 1;
        jpeg_decompress_struct.scale_denom = 1;
        jpeg_decompress_struct.output_gamma = 1.0;
        jpeg_decompress_struct.buffered_image = false;
        jpeg_decompress_struct.raw_data_out = false;
        jpeg_decompress_struct.dct_method = 0;
        jpeg_decompress_struct.do_fancy_upsampling = true;
        jpeg_decompress_struct.do_block_smoothing = true;
        jpeg_decompress_struct.quantize_colors = false;
        jpeg_decompress_struct.dither_mode = 2;
        jpeg_decompress_struct.two_pass_quantize = true;
        jpeg_decompress_struct.desired_number_of_colors = 256;
        jpeg_decompress_struct.colormap = null;
        jpeg_decompress_struct.enable_1pass_quant = false;
        jpeg_decompress_struct.enable_external_quant = false;
        jpeg_decompress_struct.enable_2pass_quant = false;
    }
    
    static void init_source(final jpeg_decompress_struct jpeg_decompress_struct) {
        jpeg_decompress_struct.buffer = new byte[4096];
        jpeg_decompress_struct.bytes_in_buffer = 0;
        jpeg_decompress_struct.bytes_offset = 0;
        jpeg_decompress_struct.start_of_file = true;
    }
    
    static int jpeg_consume_input(final jpeg_decompress_struct jpeg_decompress_struct) {
        int n = 0;
        switch (jpeg_decompress_struct.global_state) {
            case 200: {
                reset_input_controller(jpeg_decompress_struct);
                init_source(jpeg_decompress_struct);
                jpeg_decompress_struct.global_state = 201;
            }
            case 201: {
                n = consume_input(jpeg_decompress_struct);
                if (n == 1) {
                    default_decompress_parms(jpeg_decompress_struct);
                    jpeg_decompress_struct.global_state = 202;
                    break;
                }
                break;
            }
            case 202: {
                n = 1;
                break;
            }
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
            case 208:
            case 210: {
                n = consume_input(jpeg_decompress_struct);
                break;
            }
            default: {
                error();
                break;
            }
        }
        return n;
    }
    
    static void jpeg_abort(final jpeg_decompress_struct jpeg_decompress_struct) {
        if (jpeg_decompress_struct.is_decompressor) {
            jpeg_decompress_struct.global_state = 200;
        }
        else {
            jpeg_decompress_struct.global_state = 100;
        }
    }
    
    static boolean isFileFormat(final LEDataInputStream leDataInputStream) {
        try {
            final byte[] array = new byte[2];
            leDataInputStream.read(array);
            leDataInputStream.unread(array);
            return (array[0] & 0xFF) == 0xFF && (array[1] & 0xFF) == 0xD8;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    static ImageData[] loadFromByteStream(final InputStream inputStream, final ImageLoader imageLoader) {
        final jpeg_decompress_struct jpeg_decompress_struct = new jpeg_decompress_struct();
        jpeg_decompress_struct.inputStream = inputStream;
        jpeg_create_decompress(jpeg_decompress_struct);
        jpeg_read_header(jpeg_decompress_struct, true);
        jpeg_decompress_struct.buffered_image = (jpeg_decompress_struct.progressive_mode && imageLoader.hasListeners());
        jpeg_start_decompress(jpeg_decompress_struct);
        PaletteData paletteData = null;
        switch (jpeg_decompress_struct.out_color_space) {
            case 2: {
                paletteData = new PaletteData(255, 65280, 16711680);
                break;
            }
            case 1: {
                final RGB[] array = new RGB[256];
                for (int i = 0; i < array.length; ++i) {
                    array[i] = new RGB(i, i, i);
                }
                paletteData = new PaletteData(array);
                break;
            }
            default: {
                error();
                break;
            }
        }
        final int n = 4;
        final int n2 = ((jpeg_decompress_struct.output_width * jpeg_decompress_struct.out_color_components * 8 + 7) / 8 + (n - 1)) / n * n;
        final byte[][] array2 = new byte[1][n2];
        final byte[] array3 = new byte[n2 * jpeg_decompress_struct.output_height];
        final ImageData internal_new = ImageData.internal_new(jpeg_decompress_struct.output_width, jpeg_decompress_struct.output_height, paletteData.isDirect ? 24 : 8, paletteData, n, array3, 0, null, null, -1, -1, 4, 0, 0, 0, 0);
        if (jpeg_decompress_struct.buffered_image) {
            boolean jpeg_input_complete;
            do {
                final int n3 = jpeg_decompress_struct.input_scan_number - 1;
                jpeg_start_output(jpeg_decompress_struct, jpeg_decompress_struct.input_scan_number);
                while (jpeg_decompress_struct.output_scanline < jpeg_decompress_struct.output_height) {
                    final int n4 = n2 * jpeg_decompress_struct.output_scanline;
                    jpeg_read_scanlines(jpeg_decompress_struct, array2, 1);
                    System.arraycopy(array2[0], 0, array3, n4, n2);
                }
                jpeg_finish_output(jpeg_decompress_struct);
                imageLoader.notifyListeners(new ImageLoaderEvent(imageLoader, (ImageData)internal_new.clone(), n3, jpeg_input_complete = jpeg_input_complete(jpeg_decompress_struct)));
            } while (!jpeg_input_complete);
        }
        else {
            while (jpeg_decompress_struct.output_scanline < jpeg_decompress_struct.output_height) {
                final int n5 = n2 * jpeg_decompress_struct.output_scanline;
                jpeg_read_scanlines(jpeg_decompress_struct, array2, 1);
                System.arraycopy(array2[0], 0, array3, n5, n2);
            }
        }
        jpeg_finish_decompress(jpeg_decompress_struct);
        jpeg_destroy_decompress(jpeg_decompress_struct);
        return new ImageData[] { internal_new };
    }
    
    static final class JHUFF_TBL
    {
        byte[] bits;
        byte[] huffval;
        boolean sent_table;
        
        JHUFF_TBL() {
            this.bits = new byte[17];
            this.huffval = new byte[256];
        }
    }
    
    static final class JQUANT_TBL
    {
        short[] quantval;
        boolean sent_table;
        
        JQUANT_TBL() {
            this.quantval = new short[64];
        }
    }
    
    static final class bitread_perm_state
    {
        int get_buffer;
        int bits_left;
    }
    
    static final class bitread_working_state
    {
        byte[] buffer;
        int bytes_offset;
        int bytes_in_buffer;
        int get_buffer;
        int bits_left;
        jpeg_decompress_struct cinfo;
    }
    
    static final class jpeg_decompress_struct
    {
        boolean is_decompressor;
        int global_state;
        InputStream inputStream;
        byte[] buffer;
        int bytes_in_buffer;
        int bytes_offset;
        boolean start_of_file;
        int image_width;
        int image_height;
        int num_components;
        int jpeg_color_space;
        int out_color_space;
        int scale_num;
        int scale_denom;
        double output_gamma;
        boolean buffered_image;
        boolean raw_data_out;
        int dct_method;
        boolean do_fancy_upsampling;
        boolean do_block_smoothing;
        boolean quantize_colors;
        int dither_mode;
        boolean two_pass_quantize;
        int desired_number_of_colors;
        boolean enable_1pass_quant;
        boolean enable_external_quant;
        boolean enable_2pass_quant;
        int output_width;
        int output_height;
        int out_color_components;
        int output_components;
        int rec_outbuf_height;
        int actual_number_of_colors;
        int[] colormap;
        int output_scanline;
        int input_scan_number;
        int input_iMCU_row;
        int output_scan_number;
        int output_iMCU_row;
        int[][] coef_bits;
        JQUANT_TBL[] quant_tbl_ptrs;
        JHUFF_TBL[] dc_huff_tbl_ptrs;
        JHUFF_TBL[] ac_huff_tbl_ptrs;
        int data_precision;
        jpeg_component_info[] comp_info;
        boolean progressive_mode;
        boolean arith_code;
        byte[] arith_dc_L;
        byte[] arith_dc_U;
        byte[] arith_ac_K;
        int restart_interval;
        boolean saw_JFIF_marker;
        byte JFIF_major_version;
        byte JFIF_minor_version;
        byte density_unit;
        short X_density;
        short Y_density;
        boolean saw_Adobe_marker;
        byte Adobe_transform;
        boolean CCIR601_sampling;
        jpeg_marker_reader marker_list;
        int max_h_samp_factor;
        int max_v_samp_factor;
        int min_DCT_scaled_size;
        int total_iMCU_rows;
        byte[] sample_range_limit;
        int sample_range_limit_offset;
        int comps_in_scan;
        jpeg_component_info[] cur_comp_info;
        int MCUs_per_row;
        int MCU_rows_in_scan;
        int blocks_in_MCU;
        int[] MCU_membership;
        int Ss;
        int Se;
        int Ah;
        int Al;
        int unread_marker;
        int[] workspace;
        int[] row_ctr;
        jpeg_decomp_master master;
        jpeg_d_main_controller main;
        jpeg_d_coef_controller coef;
        jpeg_d_post_controller post;
        jpeg_input_controller inputctl;
        jpeg_marker_reader marker;
        jpeg_entropy_decoder entropy;
        jpeg_inverse_dct idct;
        jpeg_upsampler upsample;
        jpeg_color_deconverter cconvert;
        jpeg_color_quantizer cquantize;
        
        jpeg_decompress_struct() {
            this.quant_tbl_ptrs = new JQUANT_TBL[4];
            this.dc_huff_tbl_ptrs = new JHUFF_TBL[4];
            this.ac_huff_tbl_ptrs = new JHUFF_TBL[4];
            this.arith_dc_L = new byte[16];
            this.arith_dc_U = new byte[16];
            this.arith_ac_K = new byte[16];
            this.cur_comp_info = new jpeg_component_info[4];
            this.MCU_membership = new int[10];
            this.workspace = new int[64];
            this.row_ctr = new int[1];
        }
    }
    
    static final class jpeg_color_deconverter
    {
        int color_convert;
        int[] Cr_r_tab;
        int[] Cb_b_tab;
        int[] Cr_g_tab;
        int[] Cb_g_tab;
        
        void start_pass(final jpeg_decompress_struct jpeg_decompress_struct) {
        }
    }
    
    static final class jpeg_color_quantizer
    {
        int[][] sv_colormap;
        int sv_actual;
        int[][] colorindex;
        boolean is_padded;
        int[] Ncolors;
        int row_index;
        boolean on_odd_row;
        
        jpeg_color_quantizer() {
            this.Ncolors = new int[4];
        }
        
        void start_pass(final jpeg_decompress_struct jpeg_decompress_struct, final boolean b) {
            JPEGDecoder.error();
        }
    }
    
    static final class jpeg_component_info
    {
        int component_id;
        int component_index;
        int h_samp_factor;
        int v_samp_factor;
        int quant_tbl_no;
        int dc_tbl_no;
        int ac_tbl_no;
        int width_in_blocks;
        int height_in_blocks;
        int DCT_scaled_size;
        int downsampled_width;
        int downsampled_height;
        boolean component_needed;
        int MCU_width;
        int MCU_height;
        int MCU_blocks;
        int MCU_sample_width;
        int last_col_width;
        int last_row_height;
        JQUANT_TBL quant_table;
        int[] dct_table;
    }
    
    static final class jpeg_d_coef_controller
    {
        int consume_data;
        int decompress_data;
        short[][][] coef_arrays;
        int MCU_ctr;
        int MCU_vert_offset;
        int MCU_rows_per_iMCU_row;
        short[][] MCU_buffer;
        short[][][][] whole_image;
        int[] coef_bits_latch;
        short[] workspace;
        
        jpeg_d_coef_controller() {
            this.MCU_buffer = new short[10][];
            this.whole_image = new short[10][][][];
        }
        
        void start_input_pass(final jpeg_decompress_struct jpeg_decompress_struct) {
            jpeg_decompress_struct.input_iMCU_row = 0;
            this.start_iMCU_row(jpeg_decompress_struct);
        }
        
        void start_iMCU_row(final jpeg_decompress_struct jpeg_decompress_struct) {
            final jpeg_d_coef_controller coef = jpeg_decompress_struct.coef;
            if (jpeg_decompress_struct.comps_in_scan > 1) {
                coef.MCU_rows_per_iMCU_row = 1;
            }
            else if (jpeg_decompress_struct.input_iMCU_row < jpeg_decompress_struct.total_iMCU_rows - 1) {
                coef.MCU_rows_per_iMCU_row = jpeg_decompress_struct.cur_comp_info[0].v_samp_factor;
            }
            else {
                coef.MCU_rows_per_iMCU_row = jpeg_decompress_struct.cur_comp_info[0].last_row_height;
            }
            coef.MCU_ctr = 0;
            coef.MCU_vert_offset = 0;
        }
    }
    
    static final class jpeg_d_main_controller
    {
        int process_data;
        byte[][][] buffer;
        int[] buffer_offset;
        boolean buffer_full;
        int[] rowgroup_ctr;
        byte[][][][] xbuffer;
        int[][] xbuffer_offset;
        int whichptr;
        int context_state;
        int rowgroups_avail;
        int iMCU_row_ctr;
        
        jpeg_d_main_controller() {
            this.buffer = new byte[10][][];
            this.buffer_offset = new int[10];
            this.rowgroup_ctr = new int[1];
            this.xbuffer = new byte[2][][][];
            this.xbuffer_offset = new int[2][];
        }
        
        void start_pass(final jpeg_decompress_struct jpeg_decompress_struct, final int n) {
            final jpeg_d_main_controller main = jpeg_decompress_struct.main;
            switch (n) {
                case 0: {
                    if (jpeg_decompress_struct.upsample.need_context_rows) {
                        main.process_data = 1;
                        JPEGDecoder.make_funny_pointers(jpeg_decompress_struct);
                        main.whichptr = 0;
                        main.context_state = 0;
                        main.iMCU_row_ctr = 0;
                    }
                    else {
                        main.process_data = 0;
                    }
                    main.buffer_full = false;
                    main.rowgroup_ctr[0] = 0;
                    break;
                }
                default: {
                    JPEGDecoder.error();
                    break;
                }
            }
        }
    }
    
    static final class jpeg_upsampler
    {
        boolean need_context_rows;
        byte[][][] color_buf;
        int[] color_buf_offset;
        int[] methods;
        int next_row_out;
        int rows_to_go;
        int[] rowgroup_height;
        byte[] h_expand;
        byte[] v_expand;
        
        jpeg_upsampler() {
            this.color_buf = new byte[10][][];
            this.color_buf_offset = new int[10];
            this.methods = new int[10];
            this.rowgroup_height = new int[10];
            this.h_expand = new byte[10];
            this.v_expand = new byte[10];
        }
        
        void start_pass(final jpeg_decompress_struct jpeg_decompress_struct) {
            final jpeg_upsampler upsample = jpeg_decompress_struct.upsample;
            upsample.next_row_out = jpeg_decompress_struct.max_v_samp_factor;
            upsample.rows_to_go = jpeg_decompress_struct.output_height;
        }
    }
    
    static final class jpeg_d_post_controller
    {
        int post_process_data;
        int[] whole_image;
        int[][] buffer;
        int strip_height;
        int starting_row;
        int next_row;
        
        void start_pass(final jpeg_decompress_struct jpeg_decompress_struct, final int n) {
            final jpeg_d_post_controller post = jpeg_decompress_struct.post;
            switch (n) {
                case 0: {
                    if (jpeg_decompress_struct.quantize_colors) {
                        JPEGDecoder.error(20);
                        break;
                    }
                    post.post_process_data = 1;
                    break;
                }
                default: {
                    JPEGDecoder.error();
                    break;
                }
            }
            final jpeg_d_post_controller jpeg_d_post_controller = post;
            final jpeg_d_post_controller jpeg_d_post_controller2 = post;
            final boolean b = false;
            jpeg_d_post_controller2.next_row = (b ? 1 : 0);
            jpeg_d_post_controller.starting_row = (b ? 1 : 0);
        }
    }
    
    static final class jpeg_decomp_master
    {
        boolean is_dummy_pass;
        int pass_number;
        boolean using_merged_upsample;
        jpeg_color_quantizer quantizer_1pass;
        jpeg_color_quantizer quantizer_2pass;
    }
    
    abstract static class jpeg_entropy_decoder
    {
        boolean insufficient_data;
        bitread_working_state br_state_local;
        savable_state state_local;
        
        jpeg_entropy_decoder() {
            this.br_state_local = new bitread_working_state();
            this.state_local = new savable_state();
        }
        
        abstract void start_pass(final jpeg_decompress_struct p0);
        
        abstract boolean decode_mcu(final jpeg_decompress_struct p0, final short[][] p1);
    }
    
    static final class savable_state
    {
        int EOBRUN;
        int[] last_dc_val;
        
        savable_state() {
            this.last_dc_val = new int[4];
        }
    }
    
    static final class jpeg_input_controller
    {
        int consume_input;
        boolean has_multiple_scans;
        boolean eoi_reached;
        boolean inheaders;
    }
    
    static final class jpeg_inverse_dct
    {
        int[] cur_method;
        
        jpeg_inverse_dct() {
            this.cur_method = new int[10];
        }
        
        void start_pass(final jpeg_decompress_struct jpeg_decompress_struct) {
            final jpeg_inverse_dct idct = jpeg_decompress_struct.idct;
            boolean b = false;
            for (int i = 0; i < jpeg_decompress_struct.num_components; ++i) {
                final jpeg_component_info jpeg_component_info = jpeg_decompress_struct.comp_info[i];
                Label_0083: {
                    switch (jpeg_component_info.DCT_scaled_size) {
                        case 8: {
                            switch (jpeg_decompress_struct.dct_method) {
                                case 0: {
                                    b = false;
                                    break Label_0083;
                                }
                                default: {
                                    JPEGDecoder.error();
                                    break Label_0083;
                                }
                            }
                            break;
                        }
                        default: {
                            JPEGDecoder.error();
                            break;
                        }
                    }
                }
                if (jpeg_component_info.component_needed) {
                    if (idct.cur_method[i] != (b ? 1 : 0)) {
                        final JQUANT_TBL quant_table = jpeg_component_info.quant_table;
                        if (quant_table != null) {
                            switch (idct.cur_method[i] = (b ? 1 : 0)) {
                                case 0: {
                                    final int[] dct_table = jpeg_component_info.dct_table;
                                    for (int j = 0; j < 64; ++j) {
                                        dct_table[j] = quant_table.quantval[j];
                                    }
                                    break;
                                }
                                default: {
                                    JPEGDecoder.error();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    static final class jpeg_marker_reader
    {
        boolean saw_SOI;
        boolean saw_SOF;
        int next_restart_num;
        int discarded_bytes;
        int length_limit_COM;
        int[] length_limit_APPn;
        
        jpeg_marker_reader() {
            this.length_limit_APPn = new int[16];
        }
    }
    
    static final class d_derived_tbl
    {
        int[] maxcode;
        int[] valoffset;
        JHUFF_TBL pub;
        int[] look_nbits;
        byte[] look_sym;
        
        d_derived_tbl() {
            this.maxcode = new int[18];
            this.valoffset = new int[17];
            this.look_nbits = new int[256];
            this.look_sym = new byte[256];
        }
    }
    
    static final class huff_entropy_decoder extends jpeg_entropy_decoder
    {
        bitread_perm_state bitstate;
        savable_state saved;
        int restarts_to_go;
        d_derived_tbl[] dc_derived_tbls;
        d_derived_tbl[] ac_derived_tbls;
        d_derived_tbl[] dc_cur_tbls;
        d_derived_tbl[] ac_cur_tbls;
        boolean[] dc_needed;
        boolean[] ac_needed;
        
        huff_entropy_decoder() {
            this.bitstate = new bitread_perm_state();
            this.saved = new savable_state();
            this.dc_derived_tbls = new d_derived_tbl[4];
            this.ac_derived_tbls = new d_derived_tbl[4];
            this.dc_cur_tbls = new d_derived_tbl[10];
            this.ac_cur_tbls = new d_derived_tbl[10];
            this.dc_needed = new boolean[10];
            this.ac_needed = new boolean[10];
        }
        
        void start_pass(final jpeg_decompress_struct jpeg_decompress_struct) {
            this.start_pass_huff_decoder(jpeg_decompress_struct);
        }
        
        boolean decode_mcu(final jpeg_decompress_struct cinfo, final short[][] array) {
            final bitread_working_state br_state_local = this.br_state_local;
            final savable_state state_local = this.state_local;
            if (cinfo.restart_interval != 0 && this.restarts_to_go == 0 && !this.process_restart(cinfo)) {
                return false;
            }
            if (!this.insufficient_data) {
                br_state_local.cinfo = cinfo;
                br_state_local.buffer = cinfo.buffer;
                br_state_local.bytes_in_buffer = cinfo.bytes_in_buffer;
                br_state_local.bytes_offset = cinfo.bytes_offset;
                int get_buffer = this.bitstate.get_buffer;
                int bits_left = this.bitstate.bits_left;
                state_local.last_dc_val[0] = this.saved.last_dc_val[0];
                state_local.last_dc_val[1] = this.saved.last_dc_val[1];
                state_local.last_dc_val[2] = this.saved.last_dc_val[2];
                state_local.last_dc_val[3] = this.saved.last_dc_val[3];
                for (int i = 0; i < cinfo.blocks_in_MCU; ++i) {
                    final short[] array2 = array[i];
                    final d_derived_tbl d_derived_tbl = this.dc_cur_tbls[i];
                    final d_derived_tbl d_derived_tbl2 = this.ac_cur_tbls[i];
                    int n = 0;
                    int n2 = 0;
                    if (bits_left < 8) {
                        if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state_local, get_buffer, bits_left, 0)) {
                            return false;
                        }
                        get_buffer = br_state_local.get_buffer;
                        bits_left = br_state_local.bits_left;
                        if (bits_left < 8) {
                            n2 = 1;
                            if ((n = JPEGDecoder.jpeg_huff_decode(br_state_local, get_buffer, bits_left, d_derived_tbl, n2)) < 0) {
                                return false;
                            }
                            get_buffer = br_state_local.get_buffer;
                            bits_left = br_state_local.bits_left;
                        }
                    }
                    if (n2 != 1) {
                        final int n3 = get_buffer >> bits_left - 8 & 0xFF;
                        final int n4;
                        if ((n4 = d_derived_tbl.look_nbits[n3]) != 0) {
                            bits_left -= n4;
                            n = (d_derived_tbl.look_sym[n3] & 0xFF);
                        }
                        else {
                            if ((n = JPEGDecoder.jpeg_huff_decode(br_state_local, get_buffer, bits_left, d_derived_tbl, 9)) < 0) {
                                return false;
                            }
                            get_buffer = br_state_local.get_buffer;
                            bits_left = br_state_local.bits_left;
                        }
                    }
                    if (n != 0) {
                        if (bits_left < n) {
                            if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state_local, get_buffer, bits_left, n)) {
                                return false;
                            }
                            get_buffer = br_state_local.get_buffer;
                            bits_left = br_state_local.bits_left;
                        }
                        final int n5 = get_buffer >> (bits_left -= n) & (1 << n) - 1;
                        n = ((n5 < JPEGDecoder.extend_test[n]) ? (n5 + JPEGDecoder.extend_offset[n]) : n5);
                    }
                    if (this.dc_needed[i]) {
                        final int n6 = cinfo.MCU_membership[i];
                        n += state_local.last_dc_val[n6];
                        state_local.last_dc_val[n6] = n;
                        array2[0] = (short)n;
                    }
                    if (this.ac_needed[i]) {
                        for (int j = 1; j < 64; ++j) {
                            int n7 = 0;
                            if (bits_left < 8) {
                                if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state_local, get_buffer, bits_left, 0)) {
                                    return false;
                                }
                                get_buffer = br_state_local.get_buffer;
                                bits_left = br_state_local.bits_left;
                                if (bits_left < 8) {
                                    n7 = 1;
                                    if ((n = JPEGDecoder.jpeg_huff_decode(br_state_local, get_buffer, bits_left, d_derived_tbl2, n7)) < 0) {
                                        return false;
                                    }
                                    get_buffer = br_state_local.get_buffer;
                                    bits_left = br_state_local.bits_left;
                                }
                            }
                            if (n7 != 1) {
                                final int n8 = get_buffer >> bits_left - 8 & 0xFF;
                                final int n9;
                                if ((n9 = d_derived_tbl2.look_nbits[n8]) != 0) {
                                    bits_left -= n9;
                                    n = (d_derived_tbl2.look_sym[n8] & 0xFF);
                                }
                                else {
                                    if ((n = JPEGDecoder.jpeg_huff_decode(br_state_local, get_buffer, bits_left, d_derived_tbl2, 9)) < 0) {
                                        return false;
                                    }
                                    get_buffer = br_state_local.get_buffer;
                                    bits_left = br_state_local.bits_left;
                                }
                            }
                            final int n10 = n >> 4;
                            n &= 0xF;
                            if (n != 0) {
                                j += n10;
                                if (bits_left < n) {
                                    if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state_local, get_buffer, bits_left, n)) {
                                        return false;
                                    }
                                    get_buffer = br_state_local.get_buffer;
                                    bits_left = br_state_local.bits_left;
                                }
                                final int n11 = get_buffer >> (bits_left -= n) & (1 << n) - 1;
                                n = ((n11 < JPEGDecoder.extend_test[n]) ? (n11 + JPEGDecoder.extend_offset[n]) : n11);
                                array2[JPEGDecoder.jpeg_natural_order[j]] = (short)n;
                            }
                            else {
                                if (n10 != 15) {
                                    break;
                                }
                                j += 15;
                            }
                        }
                    }
                    else {
                        for (int k = 1; k < 64; ++k) {
                            int n12 = 0;
                            if (bits_left < 8) {
                                if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state_local, get_buffer, bits_left, 0)) {
                                    return false;
                                }
                                get_buffer = br_state_local.get_buffer;
                                bits_left = br_state_local.bits_left;
                                if (bits_left < 8) {
                                    n12 = 1;
                                    if ((n = JPEGDecoder.jpeg_huff_decode(br_state_local, get_buffer, bits_left, d_derived_tbl2, n12)) < 0) {
                                        return false;
                                    }
                                    get_buffer = br_state_local.get_buffer;
                                    bits_left = br_state_local.bits_left;
                                }
                            }
                            if (n12 != 1) {
                                final int n13 = get_buffer >> bits_left - 8 & 0xFF;
                                final int n14;
                                if ((n14 = d_derived_tbl2.look_nbits[n13]) != 0) {
                                    bits_left -= n14;
                                    n = (d_derived_tbl2.look_sym[n13] & 0xFF);
                                }
                                else {
                                    if ((n = JPEGDecoder.jpeg_huff_decode(br_state_local, get_buffer, bits_left, d_derived_tbl2, 9)) < 0) {
                                        return false;
                                    }
                                    get_buffer = br_state_local.get_buffer;
                                    bits_left = br_state_local.bits_left;
                                }
                            }
                            final int n15 = n >> 4;
                            n &= 0xF;
                            if (n != 0) {
                                k += n15;
                                if (bits_left < n) {
                                    if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state_local, get_buffer, bits_left, n)) {
                                        return false;
                                    }
                                    get_buffer = br_state_local.get_buffer;
                                    bits_left = br_state_local.bits_left;
                                }
                                bits_left -= n;
                            }
                            else {
                                if (n15 != 15) {
                                    break;
                                }
                                k += 15;
                            }
                        }
                    }
                }
                cinfo.buffer = br_state_local.buffer;
                cinfo.bytes_in_buffer = br_state_local.bytes_in_buffer;
                cinfo.bytes_offset = br_state_local.bytes_offset;
                this.bitstate.get_buffer = get_buffer;
                this.bitstate.bits_left = bits_left;
                this.saved.last_dc_val[0] = state_local.last_dc_val[0];
                this.saved.last_dc_val[1] = state_local.last_dc_val[1];
                this.saved.last_dc_val[2] = state_local.last_dc_val[2];
                this.saved.last_dc_val[3] = state_local.last_dc_val[3];
            }
            --this.restarts_to_go;
            return true;
        }
        
        void start_pass_huff_decoder(final jpeg_decompress_struct jpeg_decompress_struct) {
            if (jpeg_decompress_struct.Ss == 0 && jpeg_decompress_struct.Se == 63 && jpeg_decompress_struct.Ah == 0) {
                final int al = jpeg_decompress_struct.Al;
            }
            for (int i = 0; i < jpeg_decompress_struct.comps_in_scan; ++i) {
                final jpeg_component_info jpeg_component_info = jpeg_decompress_struct.cur_comp_info[i];
                final int dc_tbl_no = jpeg_component_info.dc_tbl_no;
                final int ac_tbl_no = jpeg_component_info.ac_tbl_no;
                JPEGDecoder.jpeg_make_d_derived_tbl(jpeg_decompress_struct, true, dc_tbl_no, this.dc_derived_tbls[dc_tbl_no] = new d_derived_tbl());
                JPEGDecoder.jpeg_make_d_derived_tbl(jpeg_decompress_struct, false, ac_tbl_no, this.ac_derived_tbls[ac_tbl_no] = new d_derived_tbl());
                this.saved.last_dc_val[i] = 0;
            }
            for (int j = 0; j < jpeg_decompress_struct.blocks_in_MCU; ++j) {
                final jpeg_component_info jpeg_component_info2 = jpeg_decompress_struct.cur_comp_info[jpeg_decompress_struct.MCU_membership[j]];
                this.dc_cur_tbls[j] = this.dc_derived_tbls[jpeg_component_info2.dc_tbl_no];
                this.ac_cur_tbls[j] = this.ac_derived_tbls[jpeg_component_info2.ac_tbl_no];
                if (jpeg_component_info2.component_needed) {
                    this.dc_needed[j] = true;
                    this.ac_needed[j] = (jpeg_component_info2.DCT_scaled_size > 1);
                }
                else {
                    this.dc_needed[j] = (this.ac_needed[j] = false);
                }
            }
            this.bitstate.bits_left = 0;
            this.bitstate.get_buffer = 0;
            this.insufficient_data = false;
            this.restarts_to_go = jpeg_decompress_struct.restart_interval;
        }
        
        boolean process_restart(final jpeg_decompress_struct jpeg_decompress_struct) {
            final jpeg_marker_reader marker = jpeg_decompress_struct.marker;
            marker.discarded_bytes += this.bitstate.bits_left / 8;
            this.bitstate.bits_left = 0;
            if (!JPEGDecoder.read_restart_marker(jpeg_decompress_struct)) {
                return false;
            }
            for (int i = 0; i < jpeg_decompress_struct.comps_in_scan; ++i) {
                this.saved.last_dc_val[i] = 0;
            }
            this.restarts_to_go = jpeg_decompress_struct.restart_interval;
            if (jpeg_decompress_struct.unread_marker == 0) {
                this.insufficient_data = false;
            }
            return true;
        }
    }
    
    static final class phuff_entropy_decoder extends jpeg_entropy_decoder
    {
        bitread_perm_state bitstate;
        savable_state saved;
        int restarts_to_go;
        d_derived_tbl[] derived_tbls;
        d_derived_tbl ac_derived_tbl;
        int[] newnz_pos;
        
        phuff_entropy_decoder() {
            this.bitstate = new bitread_perm_state();
            this.saved = new savable_state();
            this.derived_tbls = new d_derived_tbl[4];
            this.newnz_pos = new int[64];
        }
        
        void start_pass(final jpeg_decompress_struct jpeg_decompress_struct) {
            this.start_pass_phuff_decoder(jpeg_decompress_struct);
        }
        
        boolean decode_mcu(final jpeg_decompress_struct jpeg_decompress_struct, final short[][] array) {
            final boolean b = jpeg_decompress_struct.Ss == 0;
            if (jpeg_decompress_struct.Ah == 0) {
                if (b) {
                    return this.decode_mcu_DC_first(jpeg_decompress_struct, array);
                }
                return this.decode_mcu_AC_first(jpeg_decompress_struct, array);
            }
            else {
                if (b) {
                    return this.decode_mcu_DC_refine(jpeg_decompress_struct, array);
                }
                return this.decode_mcu_AC_refine(jpeg_decompress_struct, array);
            }
        }
        
        boolean decode_mcu_DC_refine(final jpeg_decompress_struct cinfo, final short[][] array) {
            final int n = 1 << cinfo.Al;
            final bitread_working_state br_state_local = this.br_state_local;
            if (cinfo.restart_interval != 0 && this.restarts_to_go == 0 && !this.process_restart(cinfo)) {
                return false;
            }
            br_state_local.cinfo = cinfo;
            br_state_local.buffer = cinfo.buffer;
            br_state_local.bytes_in_buffer = cinfo.bytes_in_buffer;
            br_state_local.bytes_offset = cinfo.bytes_offset;
            int get_buffer = this.bitstate.get_buffer;
            int bits_left = this.bitstate.bits_left;
            for (int i = 0; i < cinfo.blocks_in_MCU; ++i) {
                final short[] array2 = array[i];
                if (bits_left < 1) {
                    if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state_local, get_buffer, bits_left, 1)) {
                        return false;
                    }
                    get_buffer = br_state_local.get_buffer;
                    bits_left = br_state_local.bits_left;
                }
                if ((get_buffer >> --bits_left & 0x1) != 0x0) {
                    final short[] array3 = array2;
                    final int n2 = 0;
                    array3[n2] |= (short)n;
                }
            }
            cinfo.buffer = br_state_local.buffer;
            cinfo.bytes_in_buffer = br_state_local.bytes_in_buffer;
            cinfo.bytes_offset = br_state_local.bytes_offset;
            this.bitstate.get_buffer = get_buffer;
            this.bitstate.bits_left = bits_left;
            --this.restarts_to_go;
            return true;
        }
        
        boolean decode_mcu_AC_refine(final jpeg_decompress_struct cinfo, final short[][] array) {
            final int se = cinfo.Se;
            final int n = 1 << cinfo.Al;
            final int n2 = -1 << cinfo.Al;
            int n3 = 0;
            final bitread_working_state br_state_local = this.br_state_local;
            final int[] newnz_pos = this.newnz_pos;
            if (cinfo.restart_interval != 0 && this.restarts_to_go == 0 && !this.process_restart(cinfo)) {
                return false;
            }
            if (!this.insufficient_data) {
                br_state_local.cinfo = cinfo;
                br_state_local.buffer = cinfo.buffer;
                br_state_local.bytes_in_buffer = cinfo.bytes_in_buffer;
                br_state_local.bytes_offset = cinfo.bytes_offset;
                int get_buffer = this.bitstate.get_buffer;
                int bits_left = this.bitstate.bits_left;
                int eobrun = this.saved.EOBRUN;
                final short[] array2 = array[0];
                final d_derived_tbl ac_derived_tbl = this.ac_derived_tbl;
                int i = 0;
                int j = cinfo.Ss;
                if (eobrun == 0) {
                    while (j <= se) {
                        int n4 = 0;
                        if (bits_left < 8) {
                            if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state_local, get_buffer, bits_left, 0)) {
                                while (i > 0) {
                                    array2[newnz_pos[--i]] = 0;
                                }
                                return false;
                            }
                            get_buffer = br_state_local.get_buffer;
                            bits_left = br_state_local.bits_left;
                            if (bits_left < 8) {
                                n4 = 1;
                                if ((n3 = JPEGDecoder.jpeg_huff_decode(br_state_local, get_buffer, bits_left, ac_derived_tbl, n4)) < 0) {
                                    while (i > 0) {
                                        array2[newnz_pos[--i]] = 0;
                                    }
                                    return false;
                                }
                                get_buffer = br_state_local.get_buffer;
                                bits_left = br_state_local.bits_left;
                            }
                        }
                        if (n4 != 1) {
                            final int n5 = get_buffer >> bits_left - 8 & 0xFF;
                            final int n6;
                            if ((n6 = ac_derived_tbl.look_nbits[n5]) != 0) {
                                bits_left -= n6;
                                n3 = (ac_derived_tbl.look_sym[n5] & 0xFF);
                            }
                            else {
                                if ((n3 = JPEGDecoder.jpeg_huff_decode(br_state_local, get_buffer, bits_left, ac_derived_tbl, 9)) < 0) {
                                    while (i > 0) {
                                        array2[newnz_pos[--i]] = 0;
                                    }
                                    return false;
                                }
                                get_buffer = br_state_local.get_buffer;
                                bits_left = br_state_local.bits_left;
                            }
                        }
                        int n7 = n3 >> 4;
                        n3 &= 0xF;
                        if (n3 != 0) {
                            if (bits_left < 1) {
                                if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state_local, get_buffer, bits_left, 1)) {
                                    while (i > 0) {
                                        array2[newnz_pos[--i]] = 0;
                                    }
                                    return false;
                                }
                                get_buffer = br_state_local.get_buffer;
                                bits_left = br_state_local.bits_left;
                            }
                            if ((get_buffer >> --bits_left & 0x1) != 0x0) {
                                n3 = n;
                            }
                            else {
                                n3 = n2;
                            }
                        }
                        else if (n7 != 15) {
                            eobrun = 1 << n7;
                            if (n7 != 0) {
                                if (bits_left < n7) {
                                    if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state_local, get_buffer, bits_left, n7)) {
                                        while (i > 0) {
                                            array2[newnz_pos[--i]] = 0;
                                        }
                                        return false;
                                    }
                                    get_buffer = br_state_local.get_buffer;
                                    bits_left = br_state_local.bits_left;
                                }
                                eobrun += (get_buffer >> (bits_left -= n7) & (1 << n7) - 1);
                                break;
                            }
                            break;
                        }
                        do {
                            final short[] array3 = array2;
                            final int n8 = JPEGDecoder.jpeg_natural_order[j];
                            if (array3[n8] != 0) {
                                if (bits_left < 1) {
                                    if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state_local, get_buffer, bits_left, 1)) {
                                        while (i > 0) {
                                            array2[newnz_pos[--i]] = 0;
                                        }
                                        return false;
                                    }
                                    get_buffer = br_state_local.get_buffer;
                                    bits_left = br_state_local.bits_left;
                                }
                                if ((get_buffer >> --bits_left & 0x1) == 0x0 || (array3[n8] & n) != 0x0) {
                                    continue;
                                }
                                if (array3[n8] >= 0) {
                                    final short[] array4 = array3;
                                    final int n9 = n8;
                                    array4[n9] += (short)n;
                                }
                                else {
                                    final short[] array5 = array3;
                                    final int n10 = n8;
                                    array5[n10] += (short)n2;
                                }
                            }
                            else {
                                if (--n7 < 0) {
                                    break;
                                }
                                continue;
                            }
                        } while (++j <= se);
                        if (n3 != 0) {
                            final int n11 = JPEGDecoder.jpeg_natural_order[j];
                            array2[n11] = (short)n3;
                            newnz_pos[i++] = n11;
                        }
                        ++j;
                    }
                }
                if (eobrun > 0) {
                    while (j <= se) {
                        final short[] array6 = array2;
                        final int n12 = JPEGDecoder.jpeg_natural_order[j];
                        if (array6[n12] != 0) {
                            if (bits_left < 1) {
                                if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state_local, get_buffer, bits_left, 1)) {
                                    while (i > 0) {
                                        array2[newnz_pos[--i]] = 0;
                                    }
                                    return false;
                                }
                                get_buffer = br_state_local.get_buffer;
                                bits_left = br_state_local.bits_left;
                            }
                            if ((get_buffer >> --bits_left & 0x1) != 0x0 && (array6[n12] & n) == 0x0) {
                                if (array6[n12] >= 0) {
                                    final short[] array7 = array6;
                                    final int n13 = n12;
                                    array7[n13] += (short)n;
                                }
                                else {
                                    final short[] array8 = array6;
                                    final int n14 = n12;
                                    array8[n14] += (short)n2;
                                }
                            }
                        }
                        ++j;
                    }
                    --eobrun;
                }
                cinfo.buffer = br_state_local.buffer;
                cinfo.bytes_in_buffer = br_state_local.bytes_in_buffer;
                cinfo.bytes_offset = br_state_local.bytes_offset;
                this.bitstate.get_buffer = get_buffer;
                this.bitstate.bits_left = bits_left;
                this.saved.EOBRUN = eobrun;
            }
            --this.restarts_to_go;
            return true;
        }
        
        boolean decode_mcu_AC_first(final jpeg_decompress_struct cinfo, final short[][] array) {
            final int se = cinfo.Se;
            final int al = cinfo.Al;
            int n = 0;
            final bitread_working_state br_state_local = this.br_state_local;
            if (cinfo.restart_interval != 0 && this.restarts_to_go == 0 && !this.process_restart(cinfo)) {
                return false;
            }
            if (!this.insufficient_data) {
                int eobrun = this.saved.EOBRUN;
                if (eobrun > 0) {
                    --eobrun;
                }
                else {
                    br_state_local.cinfo = cinfo;
                    br_state_local.buffer = cinfo.buffer;
                    br_state_local.bytes_in_buffer = cinfo.bytes_in_buffer;
                    br_state_local.bytes_offset = cinfo.bytes_offset;
                    int get_buffer = this.bitstate.get_buffer;
                    int bits_left = this.bitstate.bits_left;
                    final short[] array2 = array[0];
                    final d_derived_tbl ac_derived_tbl = this.ac_derived_tbl;
                    for (int i = cinfo.Ss; i <= se; ++i) {
                        int n2 = 0;
                        if (bits_left < 8) {
                            if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state_local, get_buffer, bits_left, 0)) {
                                return false;
                            }
                            get_buffer = br_state_local.get_buffer;
                            bits_left = br_state_local.bits_left;
                            if (bits_left < 8) {
                                n2 = 1;
                                if ((n = JPEGDecoder.jpeg_huff_decode(br_state_local, get_buffer, bits_left, ac_derived_tbl, n2)) < 0) {
                                    return false;
                                }
                                get_buffer = br_state_local.get_buffer;
                                bits_left = br_state_local.bits_left;
                            }
                        }
                        if (n2 != 1) {
                            final int n3 = get_buffer >> bits_left - 8 & 0xFF;
                            final int n4;
                            if ((n4 = ac_derived_tbl.look_nbits[n3]) != 0) {
                                bits_left -= n4;
                                n = (ac_derived_tbl.look_sym[n3] & 0xFF);
                            }
                            else {
                                if ((n = JPEGDecoder.jpeg_huff_decode(br_state_local, get_buffer, bits_left, ac_derived_tbl, 9)) < 0) {
                                    return false;
                                }
                                get_buffer = br_state_local.get_buffer;
                                bits_left = br_state_local.bits_left;
                            }
                        }
                        final int n5 = n >> 4;
                        n &= 0xF;
                        if (n != 0) {
                            i += n5;
                            if (bits_left < n) {
                                if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state_local, get_buffer, bits_left, n)) {
                                    return false;
                                }
                                get_buffer = br_state_local.get_buffer;
                                bits_left = br_state_local.bits_left;
                            }
                            final int n6 = get_buffer >> (bits_left -= n) & (1 << n) - 1;
                            n = ((n6 < JPEGDecoder.extend_test[n]) ? (n6 + JPEGDecoder.extend_offset[n]) : n6);
                            array2[JPEGDecoder.jpeg_natural_order[i]] = (short)(n << al);
                        }
                        else {
                            if (n5 != 15) {
                                eobrun = 1 << n5;
                                if (n5 != 0) {
                                    if (bits_left < n5) {
                                        if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state_local, get_buffer, bits_left, n5)) {
                                            return false;
                                        }
                                        get_buffer = br_state_local.get_buffer;
                                        bits_left = br_state_local.bits_left;
                                    }
                                    eobrun += (get_buffer >> (bits_left -= n5) & (1 << n5) - 1);
                                }
                                --eobrun;
                                break;
                            }
                            i += 15;
                        }
                    }
                    cinfo.buffer = br_state_local.buffer;
                    cinfo.bytes_in_buffer = br_state_local.bytes_in_buffer;
                    cinfo.bytes_offset = br_state_local.bytes_offset;
                    this.bitstate.get_buffer = get_buffer;
                    this.bitstate.bits_left = bits_left;
                }
                this.saved.EOBRUN = eobrun;
            }
            --this.restarts_to_go;
            return true;
        }
        
        boolean decode_mcu_DC_first(final jpeg_decompress_struct cinfo, final short[][] array) {
            final int al = cinfo.Al;
            int n = 0;
            final bitread_working_state br_state_local = this.br_state_local;
            final savable_state state_local = this.state_local;
            if (cinfo.restart_interval != 0 && this.restarts_to_go == 0 && !this.process_restart(cinfo)) {
                return false;
            }
            if (!this.insufficient_data) {
                br_state_local.cinfo = cinfo;
                br_state_local.buffer = cinfo.buffer;
                br_state_local.bytes_in_buffer = cinfo.bytes_in_buffer;
                br_state_local.bytes_offset = cinfo.bytes_offset;
                int get_buffer = this.bitstate.get_buffer;
                int bits_left = this.bitstate.bits_left;
                state_local.EOBRUN = this.saved.EOBRUN;
                state_local.last_dc_val[0] = this.saved.last_dc_val[0];
                state_local.last_dc_val[1] = this.saved.last_dc_val[1];
                state_local.last_dc_val[2] = this.saved.last_dc_val[2];
                state_local.last_dc_val[3] = this.saved.last_dc_val[3];
                for (int i = 0; i < cinfo.blocks_in_MCU; ++i) {
                    final short[] array2 = array[i];
                    final int n2 = cinfo.MCU_membership[i];
                    final d_derived_tbl d_derived_tbl = this.derived_tbls[cinfo.cur_comp_info[n2].dc_tbl_no];
                    int n3 = 0;
                    if (bits_left < 8) {
                        if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state_local, get_buffer, bits_left, 0)) {
                            return false;
                        }
                        get_buffer = br_state_local.get_buffer;
                        bits_left = br_state_local.bits_left;
                        if (bits_left < 8) {
                            n3 = 1;
                            if ((n = JPEGDecoder.jpeg_huff_decode(br_state_local, get_buffer, bits_left, d_derived_tbl, n3)) < 0) {
                                return false;
                            }
                            get_buffer = br_state_local.get_buffer;
                            bits_left = br_state_local.bits_left;
                        }
                    }
                    if (n3 != 1) {
                        final int n4 = get_buffer >> bits_left - 8 & 0xFF;
                        final int n5;
                        if ((n5 = d_derived_tbl.look_nbits[n4]) != 0) {
                            bits_left -= n5;
                            n = (d_derived_tbl.look_sym[n4] & 0xFF);
                        }
                        else {
                            if ((n = JPEGDecoder.jpeg_huff_decode(br_state_local, get_buffer, bits_left, d_derived_tbl, 9)) < 0) {
                                return false;
                            }
                            get_buffer = br_state_local.get_buffer;
                            bits_left = br_state_local.bits_left;
                        }
                    }
                    if (n != 0) {
                        if (bits_left < n) {
                            if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state_local, get_buffer, bits_left, n)) {
                                return false;
                            }
                            get_buffer = br_state_local.get_buffer;
                            bits_left = br_state_local.bits_left;
                        }
                        final int n6 = get_buffer >> (bits_left -= n) & (1 << n) - 1;
                        n = ((n6 < JPEGDecoder.extend_test[n]) ? (n6 + JPEGDecoder.extend_offset[n]) : n6);
                    }
                    n += state_local.last_dc_val[n2];
                    state_local.last_dc_val[n2] = n;
                    array2[0] = (short)(n << al);
                }
                cinfo.buffer = br_state_local.buffer;
                cinfo.bytes_in_buffer = br_state_local.bytes_in_buffer;
                cinfo.bytes_offset = br_state_local.bytes_offset;
                this.bitstate.get_buffer = get_buffer;
                this.bitstate.bits_left = bits_left;
                this.saved.EOBRUN = state_local.EOBRUN;
                this.saved.last_dc_val[0] = state_local.last_dc_val[0];
                this.saved.last_dc_val[1] = state_local.last_dc_val[1];
                this.saved.last_dc_val[2] = state_local.last_dc_val[2];
                this.saved.last_dc_val[3] = state_local.last_dc_val[3];
            }
            --this.restarts_to_go;
            return true;
        }
        
        boolean process_restart(final jpeg_decompress_struct jpeg_decompress_struct) {
            final jpeg_marker_reader marker = jpeg_decompress_struct.marker;
            marker.discarded_bytes += this.bitstate.bits_left / 8;
            this.bitstate.bits_left = 0;
            if (!JPEGDecoder.read_restart_marker(jpeg_decompress_struct)) {
                return false;
            }
            for (int i = 0; i < jpeg_decompress_struct.comps_in_scan; ++i) {
                this.saved.last_dc_val[i] = 0;
            }
            this.saved.EOBRUN = 0;
            this.restarts_to_go = jpeg_decompress_struct.restart_interval;
            if (jpeg_decompress_struct.unread_marker == 0) {
                this.insufficient_data = false;
            }
            return true;
        }
        
        void start_pass_phuff_decoder(final jpeg_decompress_struct jpeg_decompress_struct) {
            final boolean b = jpeg_decompress_struct.Ss == 0;
            boolean b2 = false;
            if (b) {
                if (jpeg_decompress_struct.Se != 0) {
                    b2 = true;
                }
            }
            else {
                if (jpeg_decompress_struct.Ss > jpeg_decompress_struct.Se || jpeg_decompress_struct.Se >= 64) {
                    b2 = true;
                }
                if (jpeg_decompress_struct.comps_in_scan != 1) {
                    b2 = true;
                }
            }
            if (jpeg_decompress_struct.Ah != 0 && jpeg_decompress_struct.Al != jpeg_decompress_struct.Ah - 1) {
                b2 = true;
            }
            if (jpeg_decompress_struct.Al > 13) {
                b2 = true;
            }
            if (b2) {
                JPEGDecoder.error();
            }
            for (int i = 0; i < jpeg_decompress_struct.comps_in_scan; ++i) {
                final int[] array = jpeg_decompress_struct.coef_bits[jpeg_decompress_struct.cur_comp_info[i].component_index];
                if (!b) {
                    final int n = array[0];
                }
                for (int j = jpeg_decompress_struct.Ss; j <= jpeg_decompress_struct.Se; ++j) {
                    final int n2 = (array[j] < 0) ? 0 : array[j];
                    final int ah = jpeg_decompress_struct.Ah;
                    array[j] = jpeg_decompress_struct.Al;
                }
            }
            for (int k = 0; k < jpeg_decompress_struct.comps_in_scan; ++k) {
                final jpeg_component_info jpeg_component_info = jpeg_decompress_struct.cur_comp_info[k];
                if (b) {
                    if (jpeg_decompress_struct.Ah == 0) {
                        final int dc_tbl_no = jpeg_component_info.dc_tbl_no;
                        JPEGDecoder.jpeg_make_d_derived_tbl(jpeg_decompress_struct, true, dc_tbl_no, this.derived_tbls[dc_tbl_no] = new d_derived_tbl());
                    }
                }
                else {
                    final int ac_tbl_no = jpeg_component_info.ac_tbl_no;
                    JPEGDecoder.jpeg_make_d_derived_tbl(jpeg_decompress_struct, false, ac_tbl_no, this.derived_tbls[ac_tbl_no] = new d_derived_tbl());
                    this.ac_derived_tbl = this.derived_tbls[ac_tbl_no];
                }
                this.saved.last_dc_val[k] = 0;
            }
            this.bitstate.bits_left = 0;
            this.bitstate.get_buffer = 0;
            this.insufficient_data = false;
            this.saved.EOBRUN = 0;
            this.restarts_to_go = jpeg_decompress_struct.restart_interval;
        }
    }
}
