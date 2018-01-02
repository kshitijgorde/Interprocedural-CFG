// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.parser;

public interface Tokens
{
    public static final int yyErrorCode = 256;
    public static final int kCLASS = 257;
    public static final int kMODULE = 258;
    public static final int kDEF = 259;
    public static final int kUNDEF = 260;
    public static final int kBEGIN = 261;
    public static final int kRESCUE = 262;
    public static final int kENSURE = 263;
    public static final int kEND = 264;
    public static final int kIF = 265;
    public static final int kUNLESS = 266;
    public static final int kTHEN = 267;
    public static final int kELSIF = 268;
    public static final int kELSE = 269;
    public static final int kCASE = 270;
    public static final int kWHEN = 271;
    public static final int kWHILE = 272;
    public static final int kUNTIL = 273;
    public static final int kFOR = 274;
    public static final int kBREAK = 275;
    public static final int kNEXT = 276;
    public static final int kREDO = 277;
    public static final int kRETRY = 278;
    public static final int kIN = 279;
    public static final int kDO = 280;
    public static final int kDO_COND = 281;
    public static final int kDO_BLOCK = 282;
    public static final int kRETURN = 283;
    public static final int kYIELD = 284;
    public static final int kSUPER = 285;
    public static final int kSELF = 286;
    public static final int kNIL = 287;
    public static final int kTRUE = 288;
    public static final int kFALSE = 289;
    public static final int kAND = 290;
    public static final int kOR = 291;
    public static final int kNOT = 292;
    public static final int kIF_MOD = 293;
    public static final int kUNLESS_MOD = 294;
    public static final int kWHILE_MOD = 295;
    public static final int kUNTIL_MOD = 296;
    public static final int kRESCUE_MOD = 297;
    public static final int kALIAS = 298;
    public static final int kDEFINED = 299;
    public static final int klBEGIN = 300;
    public static final int klEND = 301;
    public static final int k__LINE__ = 302;
    public static final int k__FILE__ = 303;
    public static final int k__ENCODING__ = 304;
    public static final int kDO_LAMBDA = 305;
    public static final int tIDENTIFIER = 306;
    public static final int tFID = 307;
    public static final int tGVAR = 308;
    public static final int tIVAR = 309;
    public static final int tCONSTANT = 310;
    public static final int tCVAR = 311;
    public static final int tINTEGER = 378;
    public static final int tFLOAT = 379;
    public static final int tSTRING_CONTENT = 377;
    public static final int tSTRING_BEG = 365;
    public static final int tSTRING_END = 372;
    public static final int tSTRING_DBEG = 370;
    public static final int tSTRING_DVAR = 371;
    public static final int tXSTRING_BEG = 366;
    public static final int tREGEXP_BEG = 367;
    public static final int tREGEXP_END = 380;
    public static final int tWORDS_BEG = 368;
    public static final int tQWORDS_BEG = 369;
    public static final int tBACK_REF = 376;
    public static final int tBACK_REF2 = 363;
    public static final int tNTH_REF = 375;
    public static final int tUPLUS = 314;
    public static final int tUMINUS = 315;
    public static final int tUMINUS_NUM = 316;
    public static final int tPOW = 317;
    public static final int tCMP = 318;
    public static final int tEQ = 319;
    public static final int tEQQ = 320;
    public static final int tNEQ = 321;
    public static final int tGEQ = 322;
    public static final int tLEQ = 323;
    public static final int tANDOP = 324;
    public static final int tOROP = 325;
    public static final int tMATCH = 326;
    public static final int tNMATCH = 327;
    public static final int tDOT = 328;
    public static final int tDOT2 = 329;
    public static final int tDOT3 = 330;
    public static final int tAREF = 331;
    public static final int tASET = 332;
    public static final int tLSHFT = 333;
    public static final int tRSHFT = 334;
    public static final int tCOLON2 = 335;
    public static final int tCOLON3 = 336;
    public static final int tOP_ASGN = 337;
    public static final int tASSOC = 338;
    public static final int tLPAREN = 339;
    public static final int tLPAREN2 = 340;
    public static final int tRPAREN = 341;
    public static final int tLPAREN_ARG = 342;
    public static final int tLBRACK = 343;
    public static final int tRBRACK = 344;
    public static final int tLBRACE = 345;
    public static final int tLBRACE_ARG = 346;
    public static final int tSTAR = 347;
    public static final int tSTAR2 = 348;
    public static final int tAMPER = 349;
    public static final int tAMPER2 = 350;
    public static final int tSYMBEG = 364;
    public static final int tTILDE = 351;
    public static final int tPERCENT = 352;
    public static final int tDIVIDE = 353;
    public static final int tPLUS = 354;
    public static final int tMINUS = 355;
    public static final int tLT = 356;
    public static final int tGT = 357;
    public static final int tCARET = 360;
    public static final int tBANG = 359;
    public static final int tLCURLY = 361;
    public static final int tRCURLY = 362;
    public static final int tPIPE = 358;
    public static final int tLAMBDA = 373;
    public static final int tLAMBEG = 374;
    public static final int tLABEL = 312;
    public static final String[] operators = { "+@", "-@", "**", "<=>", "==", "===", "!=", ">=", "<=", "&&", "||", "=~", "!~", "..", "...", "[]", "[]=", "<<", ">>", "::" };
}