// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.JAXBElement;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory
{
    private static final QName _CgSetuserTypeFloat2X2_QNAME;
    private static final QName _CgSetuserTypeFloat2X3_QNAME;
    private static final QName _CgSetuserTypeFloat4_QNAME;
    private static final QName _CgSetuserTypeFloat2X4_QNAME;
    private static final QName _CgSetuserTypeHalf_QNAME;
    private static final QName _CgSetuserTypeFloat2_QNAME;
    private static final QName _CgSetuserTypeFloat3_QNAME;
    private static final QName _CgSetuserTypeFloat1_QNAME;
    private static final QName _CgSetuserTypeInt1X1_QNAME;
    private static final QName _CgSetuserTypeInt1X3_QNAME;
    private static final QName _CgSetuserTypeInt1X2_QNAME;
    private static final QName _CgSetuserTypeFloat2X1_QNAME;
    private static final QName _CgSetuserTypeInt1X4_QNAME;
    private static final QName _CgSetuserTypeInt1_QNAME;
    private static final QName _CgSetuserTypeInt2_QNAME;
    private static final QName _CgSetuserTypeInt3_QNAME;
    private static final QName _CgSetuserTypeInt4_QNAME;
    private static final QName _CgSetuserTypeEnum_QNAME;
    private static final QName _CgSetuserTypeInt_QNAME;
    private static final QName _CgSetuserTypeFixed2X1_QNAME;
    private static final QName _CgSetuserTypeConnectParam_QNAME;
    private static final QName _CgSetuserTypeSamplerRECT_QNAME;
    private static final QName _CgSetuserTypeSampler1D_QNAME;
    private static final QName _CgSetuserTypeFixed4_QNAME;
    private static final QName _CgSetuserTypeFixed2X4_QNAME;
    private static final QName _CgSetuserTypeFixed2X3_QNAME;
    private static final QName _CgSetuserTypeFixed2X2_QNAME;
    private static final QName _CgSetuserTypeFixed1_QNAME;
    private static final QName _CgSetuserTypeFixed2_QNAME;
    private static final QName _CgSetuserTypeFixed3_QNAME;
    private static final QName _CgSetuserTypeFixed3X1_QNAME;
    private static final QName _CgSetuserTypeInt3X4_QNAME;
    private static final QName _CgSetuserTypeFixed3X2_QNAME;
    private static final QName _CgSetuserTypeFixed3X3_QNAME;
    private static final QName _CgSetuserTypeFixed3X4_QNAME;
    private static final QName _CgSetuserTypeInt4X4_QNAME;
    private static final QName _CgSetuserTypeInt4X3_QNAME;
    private static final QName _CgSetuserTypeHalf2_QNAME;
    private static final QName _CgSetuserTypeHalf1_QNAME;
    private static final QName _CgSetuserTypeBool2X3_QNAME;
    private static final QName _CgSetuserTypeBool2X2_QNAME;
    private static final QName _CgSetuserTypeBool2X1_QNAME;
    private static final QName _CgSetuserTypeBool2X4_QNAME;
    private static final QName _CgSetuserTypeSampler2D_QNAME;
    private static final QName _CgSetuserTypeHalf1X3_QNAME;
    private static final QName _CgSetuserTypeHalf1X2_QNAME;
    private static final QName _CgSetuserTypeHalf1X4_QNAME;
    private static final QName _CgSetuserTypeHalf1X1_QNAME;
    private static final QName _CgSetuserTypeHalf3_QNAME;
    private static final QName _CgSetuserTypeHalf4_QNAME;
    private static final QName _CgSetuserTypeInt4X1_QNAME;
    private static final QName _CgSetuserTypeHalf2X1_QNAME;
    private static final QName _CgSetuserTypeHalf2X2_QNAME;
    private static final QName _CgSetuserTypeInt4X2_QNAME;
    private static final QName _CgSetuserTypeHalf2X3_QNAME;
    private static final QName _CgSetuserTypeBool_QNAME;
    private static final QName _CgSetuserTypeHalf2X4_QNAME;
    private static final QName _CgSetuserTypeInt3X2_QNAME;
    private static final QName _CgSetuserTypeHalf3X1_QNAME;
    private static final QName _CgSetuserTypeInt3X3_QNAME;
    private static final QName _CgSetuserTypeBool4X1_QNAME;
    private static final QName _CgSetuserTypeHalf3X2_QNAME;
    private static final QName _CgSetuserTypeInt3X1_QNAME;
    private static final QName _CgSetuserTypeHalf3X3_QNAME;
    private static final QName _CgSetuserTypeHalf3X4_QNAME;
    private static final QName _CgSetuserTypeBool4X3_QNAME;
    private static final QName _CgSetuserTypeBool4X2_QNAME;
    private static final QName _CgSetuserTypeSampler3D_QNAME;
    private static final QName _CgSetuserTypeBool4X4_QNAME;
    private static final QName _CgSetuserTypeInt2X2_QNAME;
    private static final QName _CgSetuserTypeInt2X1_QNAME;
    private static final QName _CgSetuserTypeFloat_QNAME;
    private static final QName _CgSetuserTypeFixed_QNAME;
    private static final QName _CgSetuserTypeInt2X4_QNAME;
    private static final QName _CgSetuserTypeBool1_QNAME;
    private static final QName _CgSetuserTypeFloat1X2_QNAME;
    private static final QName _CgSetuserTypeFloat1X1_QNAME;
    private static final QName _CgSetuserTypeInt2X3_QNAME;
    private static final QName _CgSetuserTypeFloat3X2_QNAME;
    private static final QName _CgSetuserTypeFloat3X1_QNAME;
    private static final QName _CgSetuserTypeFloat3X4_QNAME;
    private static final QName _CgSetuserTypeFloat3X3_QNAME;
    private static final QName _CgSetuserTypeBool3_QNAME;
    private static final QName _CgSetuserTypeBool2_QNAME;
    private static final QName _CgSetuserTypeBool4_QNAME;
    private static final QName _CgSetuserTypeSurface_QNAME;
    private static final QName _CgSetuserTypeHalf4X1_QNAME;
    private static final QName _CgSetuserTypeHalf4X2_QNAME;
    private static final QName _CgSetuserTypeHalf4X3_QNAME;
    private static final QName _CgSetuserTypeFloat1X3_QNAME;
    private static final QName _CgSetuserTypeHalf4X4_QNAME;
    private static final QName _CgSetuserTypeFloat1X4_QNAME;
    private static final QName _CgSetuserTypeArray_QNAME;
    private static final QName _CgSetuserTypeFixed1X1_QNAME;
    private static final QName _CgSetuserTypeFixed1X2_QNAME;
    private static final QName _CgSetuserTypeFixed1X3_QNAME;
    private static final QName _CgSetuserTypeFixed1X4_QNAME;
    private static final QName _CgSetuserTypeSamplerCUBE_QNAME;
    private static final QName _CgSetuserTypeBool1X3_QNAME;
    private static final QName _CgSetuserTypeBool1X4_QNAME;
    private static final QName _CgSetuserTypeBool1X1_QNAME;
    private static final QName _CgSetuserTypeBool1X2_QNAME;
    private static final QName _CgSetuserTypeUsertype_QNAME;
    private static final QName _CgSetuserTypeBool3X3_QNAME;
    private static final QName _CgSetuserTypeBool3X4_QNAME;
    private static final QName _CgSetuserTypeBool3X1_QNAME;
    private static final QName _CgSetuserTypeBool3X2_QNAME;
    private static final QName _CgSetuserTypeSamplerDEPTH_QNAME;
    private static final QName _CgSetuserTypeFixed4X4_QNAME;
    private static final QName _CgSetuserTypeFixed4X3_QNAME;
    private static final QName _CgSetuserTypeFixed4X2_QNAME;
    private static final QName _CgSetuserTypeFixed4X1_QNAME;
    private static final QName _CgSetuserTypeFloat4X4_QNAME;
    private static final QName _CgSetuserTypeString_QNAME;
    private static final QName _CgSetuserTypeFloat4X2_QNAME;
    private static final QName _CgSetuserTypeFloat4X3_QNAME;
    private static final QName _CgSetuserTypeFloat4X1_QNAME;
    private static final QName _ProfileGLSL_QNAME;
    private static final QName _ProfileCG_QNAME;
    private static final QName _FxProfileAbstract_QNAME;
    private static final QName _InstanceCamera_QNAME;
    private static final QName _InstanceLight_QNAME;
    private static final QName _ProfileGLES_QNAME;
    private static final QName _Scale_QNAME;
    private static final QName _GlHookAbstract_QNAME;
    private static final QName _InstanceForceField_QNAME;
    private static final QName _Translate_QNAME;
    private static final QName _InstancePhysicsMaterial_QNAME;
    private static final QName _P_QNAME;
    private static final QName _InstanceNode_QNAME;
    private static final QName _ProfileCOMMON_QNAME;
    private static final QName _PolygonsPhH_QNAME;
    private static final QName _VisualSceneEvaluateSceneRenderLayer_QNAME;
    private static final QName _PolygonsPh_QNAME;
    
    static {
        _CgSetuserTypeFloat2X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float2x2");
        _CgSetuserTypeFloat2X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float2x3");
        _CgSetuserTypeFloat4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float4");
        _CgSetuserTypeFloat2X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float2x4");
        _CgSetuserTypeHalf_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half");
        _CgSetuserTypeFloat2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float2");
        _CgSetuserTypeFloat3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float3");
        _CgSetuserTypeFloat1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float1");
        _CgSetuserTypeInt1X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int1x1");
        _CgSetuserTypeInt1X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int1x3");
        _CgSetuserTypeInt1X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int1x2");
        _CgSetuserTypeFloat2X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float2x1");
        _CgSetuserTypeInt1X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int1x4");
        _CgSetuserTypeInt1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int1");
        _CgSetuserTypeInt2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int2");
        _CgSetuserTypeInt3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int3");
        _CgSetuserTypeInt4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int4");
        _CgSetuserTypeEnum_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "enum");
        _CgSetuserTypeInt_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int");
        _CgSetuserTypeFixed2X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed2x1");
        _CgSetuserTypeConnectParam_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "connect_param");
        _CgSetuserTypeSamplerRECT_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "samplerRECT");
        _CgSetuserTypeSampler1D_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "sampler1D");
        _CgSetuserTypeFixed4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed4");
        _CgSetuserTypeFixed2X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed2x4");
        _CgSetuserTypeFixed2X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed2x3");
        _CgSetuserTypeFixed2X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed2x2");
        _CgSetuserTypeFixed1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed1");
        _CgSetuserTypeFixed2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed2");
        _CgSetuserTypeFixed3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed3");
        _CgSetuserTypeFixed3X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed3x1");
        _CgSetuserTypeInt3X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int3x4");
        _CgSetuserTypeFixed3X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed3x2");
        _CgSetuserTypeFixed3X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed3x3");
        _CgSetuserTypeFixed3X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed3x4");
        _CgSetuserTypeInt4X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int4x4");
        _CgSetuserTypeInt4X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int4x3");
        _CgSetuserTypeHalf2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half2");
        _CgSetuserTypeHalf1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half1");
        _CgSetuserTypeBool2X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool2x3");
        _CgSetuserTypeBool2X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool2x2");
        _CgSetuserTypeBool2X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool2x1");
        _CgSetuserTypeBool2X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool2x4");
        _CgSetuserTypeSampler2D_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "sampler2D");
        _CgSetuserTypeHalf1X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half1x3");
        _CgSetuserTypeHalf1X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half1x2");
        _CgSetuserTypeHalf1X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half1x4");
        _CgSetuserTypeHalf1X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half1x1");
        _CgSetuserTypeHalf3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half3");
        _CgSetuserTypeHalf4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half4");
        _CgSetuserTypeInt4X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int4x1");
        _CgSetuserTypeHalf2X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half2x1");
        _CgSetuserTypeHalf2X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half2x2");
        _CgSetuserTypeInt4X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int4x2");
        _CgSetuserTypeHalf2X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half2x3");
        _CgSetuserTypeBool_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool");
        _CgSetuserTypeHalf2X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half2x4");
        _CgSetuserTypeInt3X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int3x2");
        _CgSetuserTypeHalf3X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half3x1");
        _CgSetuserTypeInt3X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int3x3");
        _CgSetuserTypeBool4X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool4x1");
        _CgSetuserTypeHalf3X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half3x2");
        _CgSetuserTypeInt3X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int3x1");
        _CgSetuserTypeHalf3X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half3x3");
        _CgSetuserTypeHalf3X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half3x4");
        _CgSetuserTypeBool4X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool4x3");
        _CgSetuserTypeBool4X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool4x2");
        _CgSetuserTypeSampler3D_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "sampler3D");
        _CgSetuserTypeBool4X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool4x4");
        _CgSetuserTypeInt2X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int2x2");
        _CgSetuserTypeInt2X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int2x1");
        _CgSetuserTypeFloat_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float");
        _CgSetuserTypeFixed_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed");
        _CgSetuserTypeInt2X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int2x4");
        _CgSetuserTypeBool1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool1");
        _CgSetuserTypeFloat1X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float1x2");
        _CgSetuserTypeFloat1X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float1x1");
        _CgSetuserTypeInt2X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int2x3");
        _CgSetuserTypeFloat3X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float3x2");
        _CgSetuserTypeFloat3X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float3x1");
        _CgSetuserTypeFloat3X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float3x4");
        _CgSetuserTypeFloat3X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float3x3");
        _CgSetuserTypeBool3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool3");
        _CgSetuserTypeBool2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool2");
        _CgSetuserTypeBool4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool4");
        _CgSetuserTypeSurface_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "surface");
        _CgSetuserTypeHalf4X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half4x1");
        _CgSetuserTypeHalf4X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half4x2");
        _CgSetuserTypeHalf4X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half4x3");
        _CgSetuserTypeFloat1X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float1x3");
        _CgSetuserTypeHalf4X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half4x4");
        _CgSetuserTypeFloat1X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float1x4");
        _CgSetuserTypeArray_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "array");
        _CgSetuserTypeFixed1X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed1x1");
        _CgSetuserTypeFixed1X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed1x2");
        _CgSetuserTypeFixed1X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed1x3");
        _CgSetuserTypeFixed1X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed1x4");
        _CgSetuserTypeSamplerCUBE_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "samplerCUBE");
        _CgSetuserTypeBool1X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool1x3");
        _CgSetuserTypeBool1X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool1x4");
        _CgSetuserTypeBool1X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool1x1");
        _CgSetuserTypeBool1X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool1x2");
        _CgSetuserTypeUsertype_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "usertype");
        _CgSetuserTypeBool3X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool3x3");
        _CgSetuserTypeBool3X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool3x4");
        _CgSetuserTypeBool3X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool3x1");
        _CgSetuserTypeBool3X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool3x2");
        _CgSetuserTypeSamplerDEPTH_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "samplerDEPTH");
        _CgSetuserTypeFixed4X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed4x4");
        _CgSetuserTypeFixed4X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed4x3");
        _CgSetuserTypeFixed4X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed4x2");
        _CgSetuserTypeFixed4X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed4x1");
        _CgSetuserTypeFloat4X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float4x4");
        _CgSetuserTypeString_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "string");
        _CgSetuserTypeFloat4X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float4x2");
        _CgSetuserTypeFloat4X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float4x3");
        _CgSetuserTypeFloat4X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float4x1");
        _ProfileGLSL_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "profile_GLSL");
        _ProfileCG_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "profile_CG");
        _FxProfileAbstract_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fx_profile_abstract");
        _InstanceCamera_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "instance_camera");
        _InstanceLight_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "instance_light");
        _ProfileGLES_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "profile_GLES");
        _Scale_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "scale");
        _GlHookAbstract_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "gl_hook_abstract");
        _InstanceForceField_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "instance_force_field");
        _Translate_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "translate");
        _InstancePhysicsMaterial_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "instance_physics_material");
        _P_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "p");
        _InstanceNode_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "instance_node");
        _ProfileCOMMON_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "profile_COMMON");
        _PolygonsPhH_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "h");
        _VisualSceneEvaluateSceneRenderLayer_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "layer");
        _PolygonsPh_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "ph");
    }
    
    public RigidConstraint.TechniqueCommon.Enabled createRigidConstraintTechniqueCommonEnabled() {
        return new RigidConstraint.TechniqueCommon.Enabled();
    }
    
    public ProfileCOMMON.Technique.Constant createProfileCOMMONTechniqueConstant() {
        return new ProfileCOMMON.Technique.Constant();
    }
    
    public FxClearstencilCommon createFxClearstencilCommon() {
        return new FxClearstencilCommon();
    }
    
    public InstanceRigidBody createInstanceRigidBody() {
        return new InstanceRigidBody();
    }
    
    public ProfileCG.Technique.Pass.ColorMaterial.Mode createProfileCGTechniquePassColorMaterialMode() {
        return new ProfileCG.Technique.Pass.ColorMaterial.Mode();
    }
    
    public Light.TechniqueCommon createLightTechniqueCommon() {
        return new Light.TechniqueCommon();
    }
    
    public GlSampler1D createGlSampler1D() {
        return new GlSampler1D();
    }
    
    public ProfileCG.Technique.Pass.AlphaFunc.Value createProfileCGTechniquePassAlphaFuncValue() {
        return new ProfileCG.Technique.Pass.AlphaFunc.Value();
    }
    
    public ProfileCOMMON createProfileCOMMON() {
        return new ProfileCOMMON();
    }
    
    public ProfileGLES.Technique.Pass.AlphaFunc.Value createProfileGLESTechniquePassAlphaFuncValue() {
        return new ProfileGLES.Technique.Pass.AlphaFunc.Value();
    }
    
    public GlesNewparam createGlesNewparam() {
        return new GlesNewparam();
    }
    
    public RigidConstraint.TechniqueCommon.Limits.Linear createRigidConstraintTechniqueCommonLimitsLinear() {
        return new RigidConstraint.TechniqueCommon.Limits.Linear();
    }
    
    public CgSamplerRECT createCgSamplerRECT() {
        return new CgSamplerRECT();
    }
    
    public ProfileGLSL.Technique.Pass createProfileGLSLTechniquePass() {
        return new ProfileGLSL.Technique.Pass();
    }
    
    public ProfileGLES.Technique.Pass.FogEnable createProfileGLESTechniquePassFogEnable() {
        return new ProfileGLES.Technique.Pass.FogEnable();
    }
    
    public CommonColorOrTextureType createCommonColorOrTextureType() {
        return new CommonColorOrTextureType();
    }
    
    public ProfileCG.Technique.Pass.StencilMask createProfileCGTechniquePassStencilMask() {
        return new ProfileCG.Technique.Pass.StencilMask();
    }
    
    public Image createImage() {
        return new Image();
    }
    
    public LibraryLights createLibraryLights() {
        return new LibraryLights();
    }
    
    public ProfileCG.Technique.Pass.LightSpotCutoff createProfileCGTechniquePassLightSpotCutoff() {
        return new ProfileCG.Technique.Pass.LightSpotCutoff();
    }
    
    public Node createNode() {
        return new Node();
    }
    
    public ProfileGLES.Technique.Pass.StencilOp.Zpass createProfileGLESTechniquePassStencilOpZpass() {
        return new ProfileGLES.Technique.Pass.StencilOp.Zpass();
    }
    
    public RigidBody.TechniqueCommon.Shape createRigidBodyTechniqueCommonShape() {
        return new RigidBody.TechniqueCommon.Shape();
    }
    
    public ProfileCG.Technique.Pass.LightSpotDirection createProfileCGTechniquePassLightSpotDirection() {
        return new ProfileCG.Technique.Pass.LightSpotDirection();
    }
    
    public Asset.Contributor createAssetContributor() {
        return new Asset.Contributor();
    }
    
    public InstanceRigidBody.TechniqueCommon.Dynamic createInstanceRigidBodyTechniqueCommonDynamic() {
        return new InstanceRigidBody.TechniqueCommon.Dynamic();
    }
    
    public Light.TechniqueCommon.Spot createLightTechniqueCommonSpot() {
        return new Light.TechniqueCommon.Spot();
    }
    
    public ProfileCG.Technique.Pass.BlendEquationSeparate.Rgb createProfileCGTechniquePassBlendEquationSeparateRgb() {
        return new ProfileCG.Technique.Pass.BlendEquationSeparate.Rgb();
    }
    
    public GlslSurfaceType createGlslSurfaceType() {
        return new GlslSurfaceType();
    }
    
    public ProfileCG.Technique.Pass.LightModelColorControl createProfileCGTechniquePassLightModelColorControl() {
        return new ProfileCG.Technique.Pass.LightModelColorControl();
    }
    
    public ProfileCG.Technique.Pass.ColorMaterialEnable createProfileCGTechniquePassColorMaterialEnable() {
        return new ProfileCG.Technique.Pass.ColorMaterialEnable();
    }
    
    public ProfileCG.Technique.Pass.LineStipple createProfileCGTechniquePassLineStipple() {
        return new ProfileCG.Technique.Pass.LineStipple();
    }
    
    public GlesTextureUnit.Texcoord createGlesTextureUnitTexcoord() {
        return new GlesTextureUnit.Texcoord();
    }
    
    public ProfileCG.Technique.Pass.ColorLogicOpEnable createProfileCGTechniquePassColorLogicOpEnable() {
        return new ProfileCG.Technique.Pass.ColorLogicOpEnable();
    }
    
    public ProfileGLES.Technique.Pass.BlendEnable createProfileGLESTechniquePassBlendEnable() {
        return new ProfileGLES.Technique.Pass.BlendEnable();
    }
    
    public RigidConstraint.TechniqueCommon createRigidConstraintTechniqueCommon() {
        return new RigidConstraint.TechniqueCommon();
    }
    
    public ProfileCG.Technique.Pass.StencilOpSeparate createProfileCGTechniquePassStencilOpSeparate() {
        return new ProfileCG.Technique.Pass.StencilOpSeparate();
    }
    
    public ProfileCG.Technique.Pass.StencilOpSeparate.Zpass createProfileCGTechniquePassStencilOpSeparateZpass() {
        return new ProfileCG.Technique.Pass.StencilOpSeparate.Zpass();
    }
    
    public ProfileGLES.Technique.Pass.LightEnable createProfileGLESTechniquePassLightEnable() {
        return new ProfileGLES.Technique.Pass.LightEnable();
    }
    
    public ProfileGLES.Technique.Pass.ClipPlaneEnable createProfileGLESTechniquePassClipPlaneEnable() {
        return new ProfileGLES.Technique.Pass.ClipPlaneEnable();
    }
    
    public ProfileCG.Technique.Pass.StencilFunc.Mask createProfileCGTechniquePassStencilFuncMask() {
        return new ProfileCG.Technique.Pass.StencilFunc.Mask();
    }
    
    public AnimationClip createAnimationClip() {
        return new AnimationClip();
    }
    
    public Camera createCamera() {
        return new Camera();
    }
    
    public GlslSurfaceType.Generator.Name createGlslSurfaceTypeGeneratorName() {
        return new GlslSurfaceType.Generator.Name();
    }
    
    public ProfileCG.Technique.Pass.FogStart createProfileCGTechniquePassFogStart() {
        return new ProfileCG.Technique.Pass.FogStart();
    }
    
    public TaperedCylinder createTaperedCylinder() {
        return new TaperedCylinder();
    }
    
    public LibraryForceFields createLibraryForceFields() {
        return new LibraryForceFields();
    }
    
    public CgSampler3D createCgSampler3D() {
        return new CgSampler3D();
    }
    
    public ProfileCG.Technique.Pass.MaterialSpecular createProfileCGTechniquePassMaterialSpecular() {
        return new ProfileCG.Technique.Pass.MaterialSpecular();
    }
    
    public BindMaterial createBindMaterial() {
        return new BindMaterial();
    }
    
    public ProfileCG.Technique.Pass.ColorMaterial.Face createProfileCGTechniquePassColorMaterialFace() {
        return new ProfileCG.Technique.Pass.ColorMaterial.Face();
    }
    
    public ProfileCOMMON.Technique.Lambert createProfileCOMMONTechniqueLambert() {
        return new ProfileCOMMON.Technique.Lambert();
    }
    
    public ProfileGLSL.Technique.Pass.Shader createProfileGLSLTechniquePassShader() {
        return new ProfileGLSL.Technique.Pass.Shader();
    }
    
    public ProfileCG.Technique.Pass.Shader.Bind createProfileCGTechniquePassShaderBind() {
        return new ProfileCG.Technique.Pass.Shader.Bind();
    }
    
    public ProfileGLES.Technique.Pass.ClearStencil createProfileGLESTechniquePassClearStencil() {
        return new ProfileGLES.Technique.Pass.ClearStencil();
    }
    
    public ProfileCG.Technique.Pass.PolygonMode createProfileCGTechniquePassPolygonMode() {
        return new ProfileCG.Technique.Pass.PolygonMode();
    }
    
    public ProfileGLES.Technique.Pass.MaterialAmbient createProfileGLESTechniquePassMaterialAmbient() {
        return new ProfileGLES.Technique.Pass.MaterialAmbient();
    }
    
    public LibraryPhysicsModels createLibraryPhysicsModels() {
        return new LibraryPhysicsModels();
    }
    
    public Effect createEffect() {
        return new Effect();
    }
    
    public FxSurfaceInitCubeCommon.All createFxSurfaceInitCubeCommonAll() {
        return new FxSurfaceInitCubeCommon.All();
    }
    
    public ProfileCG.Technique.Pass.LightModelLocalViewerEnable createProfileCGTechniquePassLightModelLocalViewerEnable() {
        return new ProfileCG.Technique.Pass.LightModelLocalViewerEnable();
    }
    
    public ProfileGLES.Technique.Pass.LineWidth createProfileGLESTechniquePassLineWidth() {
        return new ProfileGLES.Technique.Pass.LineWidth();
    }
    
    public ProfileGLES.Technique.Pass.DepthRange createProfileGLESTechniquePassDepthRange() {
        return new ProfileGLES.Technique.Pass.DepthRange();
    }
    
    public ProfileCG.Technique.Pass.DepthFunc createProfileCGTechniquePassDepthFunc() {
        return new ProfileCG.Technique.Pass.DepthFunc();
    }
    
    public ProfileCG.Technique.Pass.AlphaFunc.Func createProfileCGTechniquePassAlphaFuncFunc() {
        return new ProfileCG.Technique.Pass.AlphaFunc.Func();
    }
    
    public ProfileCG.Technique.Pass.ClipPlane createProfileCGTechniquePassClipPlane() {
        return new ProfileCG.Technique.Pass.ClipPlane();
    }
    
    public FxAnnotateCommon createFxAnnotateCommon() {
        return new FxAnnotateCommon();
    }
    
    public ProfileGLES.Technique.Pass.PointSizeMax createProfileGLESTechniquePassPointSizeMax() {
        return new ProfileGLES.Technique.Pass.PointSizeMax();
    }
    
    public ProfileCG.Technique.Pass.DepthBounds createProfileCGTechniquePassDepthBounds() {
        return new ProfileCG.Technique.Pass.DepthBounds();
    }
    
    public ProfileCG.Technique.Pass.LightPosition createProfileCGTechniquePassLightPosition() {
        return new ProfileCG.Technique.Pass.LightPosition();
    }
    
    public GlSampler2D createGlSampler2D() {
        return new GlSampler2D();
    }
    
    public RigidBody.TechniqueCommon createRigidBodyTechniqueCommon() {
        return new RigidBody.TechniqueCommon();
    }
    
    public ProfileGLES.Technique.Pass.ShadeModel createProfileGLESTechniquePassShadeModel() {
        return new ProfileGLES.Technique.Pass.ShadeModel();
    }
    
    public ProfileGLES.Technique.Pass.PointFadeThresholdSize createProfileGLESTechniquePassPointFadeThresholdSize() {
        return new ProfileGLES.Technique.Pass.PointFadeThresholdSize();
    }
    
    public ProfileCG.Technique.Pass.StencilFunc.Ref createProfileCGTechniquePassStencilFuncRef() {
        return new ProfileCG.Technique.Pass.StencilFunc.Ref();
    }
    
    public GlesTextureConstantType createGlesTextureConstantType() {
        return new GlesTextureConstantType();
    }
    
    public CommonTransparentType createCommonTransparentType() {
        return new CommonTransparentType();
    }
    
    public ProfileGLES.Technique.Pass.AlphaTestEnable createProfileGLESTechniquePassAlphaTestEnable() {
        return new ProfileGLES.Technique.Pass.AlphaTestEnable();
    }
    
    public ProfileGLES.Technique.Pass.BlendFunc createProfileGLESTechniquePassBlendFunc() {
        return new ProfileGLES.Technique.Pass.BlendFunc();
    }
    
    public ProfileCG.Technique.Pass.FrontFace createProfileCGTechniquePassFrontFace() {
        return new ProfileCG.Technique.Pass.FrontFace();
    }
    
    public ProfileCG.Technique.Pass.BlendEquationSeparate createProfileCGTechniquePassBlendEquationSeparate() {
        return new ProfileCG.Technique.Pass.BlendEquationSeparate();
    }
    
    public COLLADA.Scene createCOLLADAScene() {
        return new COLLADA.Scene();
    }
    
    public ProfileCG.Technique.Pass.RescaleNormalEnable createProfileCGTechniquePassRescaleNormalEnable() {
        return new ProfileCG.Technique.Pass.RescaleNormalEnable();
    }
    
    public GlesTexcombinerCommandType createGlesTexcombinerCommandType() {
        return new GlesTexcombinerCommandType();
    }
    
    public InstanceEffect.TechniqueHint createInstanceEffectTechniqueHint() {
        return new InstanceEffect.TechniqueHint();
    }
    
    public RigidConstraint.TechniqueCommon.Limits.SwingConeAndTwist createRigidConstraintTechniqueCommonLimitsSwingConeAndTwist() {
        return new RigidConstraint.TechniqueCommon.Limits.SwingConeAndTwist();
    }
    
    public ProfileCG.Technique.Pass.LightLinearAttenuation createProfileCGTechniquePassLightLinearAttenuation() {
        return new ProfileCG.Technique.Pass.LightLinearAttenuation();
    }
    
    public InstanceRigidBody.TechniqueCommon.MassFrame createInstanceRigidBodyTechniqueCommonMassFrame() {
        return new InstanceRigidBody.TechniqueCommon.MassFrame();
    }
    
    public ProfileCG.Technique.Pass.PointSizeMax createProfileCGTechniquePassPointSizeMax() {
        return new ProfileCG.Technique.Pass.PointSizeMax();
    }
    
    public ProfileCG.Technique.Pass.MaterialDiffuse createProfileCGTechniquePassMaterialDiffuse() {
        return new ProfileCG.Technique.Pass.MaterialDiffuse();
    }
    
    public ProfileGLES.Technique.Pass.ClearDepth createProfileGLESTechniquePassClearDepth() {
        return new ProfileGLES.Technique.Pass.ClearDepth();
    }
    
    public ProfileCG.Technique.Pass.BlendEquation createProfileCGTechniquePassBlendEquation() {
        return new ProfileCG.Technique.Pass.BlendEquation();
    }
    
    public ProfileCG.Technique.Pass.Texture2D createProfileCGTechniquePassTexture2D() {
        return new ProfileCG.Technique.Pass.Texture2D();
    }
    
    public LibraryImages createLibraryImages() {
        return new LibraryImages();
    }
    
    public ProfileCG.Technique.Pass.Shader.CompilerTarget createProfileCGTechniquePassShaderCompilerTarget() {
        return new ProfileCG.Technique.Pass.Shader.CompilerTarget();
    }
    
    public ProfileGLES.Technique.Pass.CullFace createProfileGLESTechniquePassCullFace() {
        return new ProfileGLES.Technique.Pass.CullFace();
    }
    
    public Geometry createGeometry() {
        return new Geometry();
    }
    
    public InstanceMaterial.BindVertexInput createInstanceMaterialBindVertexInput() {
        return new InstanceMaterial.BindVertexInput();
    }
    
    public InputLocalOffset createInputLocalOffset() {
        return new InputLocalOffset();
    }
    
    public ProfileCG.Technique.Pass.FogEnable createProfileCGTechniquePassFogEnable() {
        return new ProfileCG.Technique.Pass.FogEnable();
    }
    
    public FxCodeProfile createFxCodeProfile() {
        return new FxCodeProfile();
    }
    
    public CgSetparamSimple createCgSetparamSimple() {
        return new CgSetparamSimple();
    }
    
    public ProfileGLES.Technique.Pass.RescaleNormalEnable createProfileGLESTechniquePassRescaleNormalEnable() {
        return new ProfileGLES.Technique.Pass.RescaleNormalEnable();
    }
    
    public ProfileGLES.Technique createProfileGLESTechnique() {
        return new ProfileGLES.Technique();
    }
    
    public ProfileGLES.Technique.Pass.LightAmbient createProfileGLESTechniquePassLightAmbient() {
        return new ProfileGLES.Technique.Pass.LightAmbient();
    }
    
    public ProfileCG.Technique.Pass.DepthTestEnable createProfileCGTechniquePassDepthTestEnable() {
        return new ProfileCG.Technique.Pass.DepthTestEnable();
    }
    
    public ProfileGLES.Technique.Pass.PolygonOffsetFillEnable createProfileGLESTechniquePassPolygonOffsetFillEnable() {
        return new ProfileGLES.Technique.Pass.PolygonOffsetFillEnable();
    }
    
    public GlesTexenvCommandType createGlesTexenvCommandType() {
        return new GlesTexenvCommandType();
    }
    
    public InstanceController createInstanceController() {
        return new InstanceController();
    }
    
    public ProfileCG.Technique.Pass.MaterialShininess createProfileCGTechniquePassMaterialShininess() {
        return new ProfileCG.Technique.Pass.MaterialShininess();
    }
    
    public ProfileCG.Technique.Pass.StencilFuncSeparate.Front createProfileCGTechniquePassStencilFuncSeparateFront() {
        return new ProfileCG.Technique.Pass.StencilFuncSeparate.Front();
    }
    
    public ProfileCG.Technique.Pass.BlendEnable createProfileCGTechniquePassBlendEnable() {
        return new ProfileCG.Technique.Pass.BlendEnable();
    }
    
    public ProfileGLES.Technique.Pass.DepthTestEnable createProfileGLESTechniquePassDepthTestEnable() {
        return new ProfileGLES.Technique.Pass.DepthTestEnable();
    }
    
    public LibraryControllers createLibraryControllers() {
        return new LibraryControllers();
    }
    
    public ProfileCG.Technique.Pass.CullFace createProfileCGTechniquePassCullFace() {
        return new ProfileCG.Technique.Pass.CullFace();
    }
    
    public ProfileGLES.Technique.Pass.LightConstantAttenuation createProfileGLESTechniquePassLightConstantAttenuation() {
        return new ProfileGLES.Technique.Pass.LightConstantAttenuation();
    }
    
    public ProfileGLES.Technique.Pass.DitherEnable createProfileGLESTechniquePassDitherEnable() {
        return new ProfileGLES.Technique.Pass.DitherEnable();
    }
    
    public FxDepthtargetCommon createFxDepthtargetCommon() {
        return new FxDepthtargetCommon();
    }
    
    public CommonFloatOrParamType createCommonFloatOrParamType() {
        return new CommonFloatOrParamType();
    }
    
    public Asset.Unit createAssetUnit() {
        return new Asset.Unit();
    }
    
    public ProfileCG.Technique.Pass.BlendFunc.Dest createProfileCGTechniquePassBlendFuncDest() {
        return new ProfileCG.Technique.Pass.BlendFunc.Dest();
    }
    
    public Camera.Optics.TechniqueCommon.Orthographic createCameraOpticsTechniqueCommonOrthographic() {
        return new Camera.Optics.TechniqueCommon.Orthographic();
    }
    
    public ProfileCG.Technique.Pass.SampleAlphaToCoverageEnable createProfileCGTechniquePassSampleAlphaToCoverageEnable() {
        return new ProfileCG.Technique.Pass.SampleAlphaToCoverageEnable();
    }
    
    public FxIncludeCommon createFxIncludeCommon() {
        return new FxIncludeCommon();
    }
    
    public InputLocal createInputLocal() {
        return new InputLocal();
    }
    
    public ProfileCG.Technique.Pass.StencilMaskSeparate.Mask createProfileCGTechniquePassStencilMaskSeparateMask() {
        return new ProfileCG.Technique.Pass.StencilMaskSeparate.Mask();
    }
    
    public RigidConstraint.Attachment createRigidConstraintAttachment() {
        return new RigidConstraint.Attachment();
    }
    
    public TargetableFloat3 createTargetableFloat3() {
        return new TargetableFloat3();
    }
    
    public FxSamplerDEPTHCommon createFxSamplerDEPTHCommon() {
        return new FxSamplerDEPTHCommon();
    }
    
    public Spline createSpline() {
        return new Spline();
    }
    
    public PhysicsScene createPhysicsScene() {
        return new PhysicsScene();
    }
    
    public Skin.Joints createSkinJoints() {
        return new Skin.Joints();
    }
    
    public ProfileCG.Technique.Pass.BlendFunc.Src createProfileCGTechniquePassBlendFuncSrc() {
        return new ProfileCG.Technique.Pass.BlendFunc.Src();
    }
    
    public CgSamplerCUBE createCgSamplerCUBE() {
        return new CgSamplerCUBE();
    }
    
    public ProfileGLES.Technique.Pass.StencilOp.Zfail createProfileGLESTechniquePassStencilOpZfail() {
        return new ProfileGLES.Technique.Pass.StencilOp.Zfail();
    }
    
    public ProfileCG.Technique.Pass.LightModelTwoSideEnable createProfileCGTechniquePassLightModelTwoSideEnable() {
        return new ProfileCG.Technique.Pass.LightModelTwoSideEnable();
    }
    
    public Lines createLines() {
        return new Lines();
    }
    
    public ProfileCG.Technique.Pass.FogEnd createProfileCGTechniquePassFogEnd() {
        return new ProfileCG.Technique.Pass.FogEnd();
    }
    
    public ProfileCG.Technique.Pass createProfileCGTechniquePass() {
        return new ProfileCG.Technique.Pass();
    }
    
    public GlslSurfaceType.Generator createGlslSurfaceTypeGenerator() {
        return new GlslSurfaceType.Generator();
    }
    
    public ProfileCG.Technique.Pass.DepthClampEnable createProfileCGTechniquePassDepthClampEnable() {
        return new ProfileCG.Technique.Pass.DepthClampEnable();
    }
    
    public LibraryPhysicsScenes createLibraryPhysicsScenes() {
        return new LibraryPhysicsScenes();
    }
    
    public Mesh createMesh() {
        return new Mesh();
    }
    
    public ProfileGLES createProfileGLES() {
        return new ProfileGLES();
    }
    
    public FloatArray createFloatArray() {
        return new FloatArray();
    }
    
    public ProfileGLSL.Technique.Pass.Shader.Bind.Param createProfileGLSLTechniquePassShaderBindParam() {
        return new ProfileGLSL.Technique.Pass.Shader.Bind.Param();
    }
    
    public FxSurfaceInitCubeCommon createFxSurfaceInitCubeCommon() {
        return new FxSurfaceInitCubeCommon();
    }
    
    public Controller createController() {
        return new Controller();
    }
    
    public CgSampler1D createCgSampler1D() {
        return new CgSampler1D();
    }
    
    public TargetableFloat createTargetableFloat() {
        return new TargetableFloat();
    }
    
    public RigidBody createRigidBody() {
        return new RigidBody();
    }
    
    public FxSurfaceInitPlanarCommon.All createFxSurfaceInitPlanarCommonAll() {
        return new FxSurfaceInitPlanarCommon.All();
    }
    
    public ProfileCG.Technique.Pass.PolygonMode.Mode createProfileCGTechniquePassPolygonModeMode() {
        return new ProfileCG.Technique.Pass.PolygonMode.Mode();
    }
    
    public ProfileCG.Technique.Pass.ClipPlaneEnable createProfileCGTechniquePassClipPlaneEnable() {
        return new ProfileCG.Technique.Pass.ClipPlaneEnable();
    }
    
    public ProfileCG.Technique.Pass.StencilOpSeparate.Zfail createProfileCGTechniquePassStencilOpSeparateZfail() {
        return new ProfileCG.Technique.Pass.StencilOpSeparate.Zfail();
    }
    
    public ForceField createForceField() {
        return new ForceField();
    }
    
    public ProfileGLES.Technique.Pass.DepthMask createProfileGLESTechniquePassDepthMask() {
        return new ProfileGLES.Technique.Pass.DepthMask();
    }
    
    public ProfileCG.Technique.Pass.PolygonStippleEnable createProfileCGTechniquePassPolygonStippleEnable() {
        return new ProfileCG.Technique.Pass.PolygonStippleEnable();
    }
    
    public ConvexMesh createConvexMesh() {
        return new ConvexMesh();
    }
    
    public Skin.VertexWeights createSkinVertexWeights() {
        return new Skin.VertexWeights();
    }
    
    public Morph.Targets createMorphTargets() {
        return new Morph.Targets();
    }
    
    public ProfileGLES.Technique.Pass.PointSize createProfileGLESTechniquePassPointSize() {
        return new ProfileGLES.Technique.Pass.PointSize();
    }
    
    public ProfileGLSL.Technique.Pass.Shader.Name createProfileGLSLTechniquePassShaderName() {
        return new ProfileGLSL.Technique.Pass.Shader.Name();
    }
    
    public GlSamplerDEPTH createGlSamplerDEPTH() {
        return new GlSamplerDEPTH();
    }
    
    public RigidConstraint.TechniqueCommon.Interpenetrate createRigidConstraintTechniqueCommonInterpenetrate() {
        return new RigidConstraint.TechniqueCommon.Interpenetrate();
    }
    
    public ProfileCG.Technique.Pass.ClearDepth createProfileCGTechniquePassClearDepth() {
        return new ProfileCG.Technique.Pass.ClearDepth();
    }
    
    public ProfileCG.Technique.Pass.PolygonOffsetPointEnable createProfileCGTechniquePassPolygonOffsetPointEnable() {
        return new ProfileCG.Technique.Pass.PolygonOffsetPointEnable();
    }
    
    public ProfileGLES.Technique.Pass.StencilMask createProfileGLESTechniquePassStencilMask() {
        return new ProfileGLES.Technique.Pass.StencilMask();
    }
    
    public ProfileGLES.Technique.Pass.SampleCoverageEnable createProfileGLESTechniquePassSampleCoverageEnable() {
        return new ProfileGLES.Technique.Pass.SampleCoverageEnable();
    }
    
    public InstanceRigidBody.TechniqueCommon createInstanceRigidBodyTechniqueCommon() {
        return new InstanceRigidBody.TechniqueCommon();
    }
    
    public ProfileGLES.Technique.Pass.LightDiffuse createProfileGLESTechniquePassLightDiffuse() {
        return new ProfileGLES.Technique.Pass.LightDiffuse();
    }
    
    public ProfileCG.Technique.Pass.LightingEnable createProfileCGTechniquePassLightingEnable() {
        return new ProfileCG.Technique.Pass.LightingEnable();
    }
    
    public RigidConstraint.TechniqueCommon.Limits createRigidConstraintTechniqueCommonLimits() {
        return new RigidConstraint.TechniqueCommon.Limits();
    }
    
    public ProfileCG.Technique.Pass.Texture3DEnable createProfileCGTechniquePassTexture3DEnable() {
        return new ProfileCG.Technique.Pass.Texture3DEnable();
    }
    
    public Light.TechniqueCommon.Ambient createLightTechniqueCommonAmbient() {
        return new Light.TechniqueCommon.Ambient();
    }
    
    public InstanceRigidBody.TechniqueCommon.Shape createInstanceRigidBodyTechniqueCommonShape() {
        return new InstanceRigidBody.TechniqueCommon.Shape();
    }
    
    public TaperedCapsule createTaperedCapsule() {
        return new TaperedCapsule();
    }
    
    public ProfileCG.Technique createProfileCGTechnique() {
        return new ProfileCG.Technique();
    }
    
    public LibraryNodes createLibraryNodes() {
        return new LibraryNodes();
    }
    
    public Capsule createCapsule() {
        return new Capsule();
    }
    
    public Linestrips createLinestrips() {
        return new Linestrips();
    }
    
    public ProfileGLES.Technique.Pass.PointDistanceAttenuation createProfileGLESTechniquePassPointDistanceAttenuation() {
        return new ProfileGLES.Technique.Pass.PointDistanceAttenuation();
    }
    
    public PhysicsMaterial.TechniqueCommon createPhysicsMaterialTechniqueCommon() {
        return new PhysicsMaterial.TechniqueCommon();
    }
    
    public CommonColorOrTextureType.Color createCommonColorOrTextureTypeColor() {
        return new CommonColorOrTextureType.Color();
    }
    
    public CgNewarrayType createCgNewarrayType() {
        return new CgNewarrayType();
    }
    
    public ProfileCG.Technique.Pass.TextureCUBE createProfileCGTechniquePassTextureCUBE() {
        return new ProfileCG.Technique.Pass.TextureCUBE();
    }
    
    public ProfileCG.Technique.Pass.Texture2DEnable createProfileCGTechniquePassTexture2DEnable() {
        return new ProfileCG.Technique.Pass.Texture2DEnable();
    }
    
    public ProfileGLES.Technique.Pass.MaterialShininess createProfileGLESTechniquePassMaterialShininess() {
        return new ProfileGLES.Technique.Pass.MaterialShininess();
    }
    
    public FxColortargetCommon createFxColortargetCommon() {
        return new FxColortargetCommon();
    }
    
    public ProfileGLES.Technique.Pass.ProjectionMatrix createProfileGLESTechniquePassProjectionMatrix() {
        return new ProfileGLES.Technique.Pass.ProjectionMatrix();
    }
    
    public ProfileCG.Technique.Pass.ClearColor createProfileCGTechniquePassClearColor() {
        return new ProfileCG.Technique.Pass.ClearColor();
    }
    
    public CgSetuserType createCgSetuserType() {
        return new CgSetuserType();
    }
    
    public ProfileCG.Technique.Pass.StencilOp.Zpass createProfileCGTechniquePassStencilOpZpass() {
        return new ProfileCG.Technique.Pass.StencilOp.Zpass();
    }
    
    public ProfileCG.Technique.Pass.ColorMaterial createProfileCGTechniquePassColorMaterial() {
        return new ProfileCG.Technique.Pass.ColorMaterial();
    }
    
    public FxStenciltargetCommon createFxStenciltargetCommon() {
        return new FxStenciltargetCommon();
    }
    
    public ProfileCG.Technique.Pass.PolygonOffsetLineEnable createProfileCGTechniquePassPolygonOffsetLineEnable() {
        return new ProfileCG.Technique.Pass.PolygonOffsetLineEnable();
    }
    
    public ProfileCG.Technique.Pass.StencilFunc createProfileCGTechniquePassStencilFunc() {
        return new ProfileCG.Technique.Pass.StencilFunc();
    }
    
    public ProfileGLES.Technique.Pass.BlendFunc.Dest createProfileGLESTechniquePassBlendFuncDest() {
        return new ProfileGLES.Technique.Pass.BlendFunc.Dest();
    }
    
    public LibraryEffects createLibraryEffects() {
        return new LibraryEffects();
    }
    
    public RigidConstraint.TechniqueCommon.Spring.Linear createRigidConstraintTechniqueCommonSpringLinear() {
        return new RigidConstraint.TechniqueCommon.Spring.Linear();
    }
    
    public CgSurfaceType createCgSurfaceType() {
        return new CgSurfaceType();
    }
    
    public ProfileCG.Technique.Pass.ProjectionMatrix createProfileCGTechniquePassProjectionMatrix() {
        return new ProfileCG.Technique.Pass.ProjectionMatrix();
    }
    
    public PhysicsModel createPhysicsModel() {
        return new PhysicsModel();
    }
    
    public ProfileGLES.Technique.Pass.AlphaFunc createProfileGLESTechniquePassAlphaFunc() {
        return new ProfileGLES.Technique.Pass.AlphaFunc();
    }
    
    public LibraryCameras createLibraryCameras() {
        return new LibraryCameras();
    }
    
    public ProfileGLES.Technique.Pass.LightLinearAttenutation createProfileGLESTechniquePassLightLinearAttenutation() {
        return new ProfileGLES.Technique.Pass.LightLinearAttenutation();
    }
    
    public IDREFArray createIDREFArray() {
        return new IDREFArray();
    }
    
    public ProfileCG.Technique.Pass.ClearStencil createProfileCGTechniquePassClearStencil() {
        return new ProfileCG.Technique.Pass.ClearStencil();
    }
    
    public GlSampler3D createGlSampler3D() {
        return new GlSampler3D();
    }
    
    public Camera.Optics.TechniqueCommon createCameraOpticsTechniqueCommon() {
        return new Camera.Optics.TechniqueCommon();
    }
    
    public Cylinder createCylinder() {
        return new Cylinder();
    }
    
    public Trifans createTrifans() {
        return new Trifans();
    }
    
    public CgSampler2D createCgSampler2D() {
        return new CgSampler2D();
    }
    
    public VisualScene.EvaluateScene createVisualSceneEvaluateScene() {
        return new VisualScene.EvaluateScene();
    }
    
    public Animation createAnimation() {
        return new Animation();
    }
    
    public FxSurfaceInitVolumeCommon.Primary createFxSurfaceInitVolumeCommonPrimary() {
        return new FxSurfaceInitVolumeCommon.Primary();
    }
    
    public ProfileCG.Technique.Pass.LogicOpEnable createProfileCGTechniquePassLogicOpEnable() {
        return new ProfileCG.Technique.Pass.LogicOpEnable();
    }
    
    public ProfileCG.Technique.Pass.StencilFuncSeparate.Ref createProfileCGTechniquePassStencilFuncSeparateRef() {
        return new ProfileCG.Technique.Pass.StencilFuncSeparate.Ref();
    }
    
    public ProfileGLES.Technique.Pass.FogDensity createProfileGLESTechniquePassFogDensity() {
        return new ProfileGLES.Technique.Pass.FogDensity();
    }
    
    public Vertices createVertices() {
        return new Vertices();
    }
    
    public LibraryAnimations createLibraryAnimations() {
        return new LibraryAnimations();
    }
    
    public InputGlobal createInputGlobal() {
        return new InputGlobal();
    }
    
    public ProfileCG.Technique.Pass.LightAmbient createProfileCGTechniquePassLightAmbient() {
        return new ProfileCG.Technique.Pass.LightAmbient();
    }
    
    public Polylist createPolylist() {
        return new Polylist();
    }
    
    public ProfileCG.Technique.Pass.StencilOpSeparate.Fail createProfileCGTechniquePassStencilOpSeparateFail() {
        return new ProfileCG.Technique.Pass.StencilOpSeparate.Fail();
    }
    
    public CommonColorOrTextureType.Param createCommonColorOrTextureTypeParam() {
        return new CommonColorOrTextureType.Param();
    }
    
    public InstanceWithExtra createInstanceWithExtra() {
        return new InstanceWithExtra();
    }
    
    public FxSurfaceCommon createFxSurfaceCommon() {
        return new FxSurfaceCommon();
    }
    
    public FxNewparamCommon createFxNewparamCommon() {
        return new FxNewparamCommon();
    }
    
    public LibraryVisualScenes createLibraryVisualScenes() {
        return new LibraryVisualScenes();
    }
    
    public ProfileGLES.Technique.Pass.LogicOp createProfileGLESTechniquePassLogicOp() {
        return new ProfileGLES.Technique.Pass.LogicOp();
    }
    
    public ProfileCG.Technique.Pass.LightModelAmbient createProfileCGTechniquePassLightModelAmbient() {
        return new ProfileCG.Technique.Pass.LightModelAmbient();
    }
    
    public LibraryMaterials createLibraryMaterials() {
        return new LibraryMaterials();
    }
    
    public ProfileCG.Technique.Pass.FogColor createProfileCGTechniquePassFogColor() {
        return new ProfileCG.Technique.Pass.FogColor();
    }
    
    public VisualScene.EvaluateScene.Render createVisualSceneEvaluateSceneRender() {
        return new VisualScene.EvaluateScene.Render();
    }
    
    public ProfileCG.Technique.Pass.PointSmoothEnable createProfileCGTechniquePassPointSmoothEnable() {
        return new ProfileCG.Technique.Pass.PointSmoothEnable();
    }
    
    public ProfileCG.Technique.Pass.LightEnable createProfileCGTechniquePassLightEnable() {
        return new ProfileCG.Technique.Pass.LightEnable();
    }
    
    public Matrix createMatrix() {
        return new Matrix();
    }
    
    public FxSurfaceInitCubeCommon.Primary createFxSurfaceInitCubeCommonPrimary() {
        return new FxSurfaceInitCubeCommon.Primary();
    }
    
    public GlslNewparam createGlslNewparam() {
        return new GlslNewparam();
    }
    
    public ProfileGLES.Technique.Pass.LightSpotCutoff createProfileGLESTechniquePassLightSpotCutoff() {
        return new ProfileGLES.Technique.Pass.LightSpotCutoff();
    }
    
    public ProfileGLES.Technique.Pass.PointSmoothEnable createProfileGLESTechniquePassPointSmoothEnable() {
        return new ProfileGLES.Technique.Pass.PointSmoothEnable();
    }
    
    public Rotate createRotate() {
        return new Rotate();
    }
    
    public ProfileCG.Technique.Pass.Texture3D createProfileCGTechniquePassTexture3D() {
        return new ProfileCG.Technique.Pass.Texture3D();
    }
    
    public ProfileGLES.Technique.Pass.CullFaceEnable createProfileGLESTechniquePassCullFaceEnable() {
        return new ProfileGLES.Technique.Pass.CullFaceEnable();
    }
    
    public ProfileCG.Technique.Pass.SampleAlphaToOneEnable createProfileCGTechniquePassSampleAlphaToOneEnable() {
        return new ProfileCG.Technique.Pass.SampleAlphaToOneEnable();
    }
    
    public ProfileCG.Technique.Pass.LightSpotExponent createProfileCGTechniquePassLightSpotExponent() {
        return new ProfileCG.Technique.Pass.LightSpotExponent();
    }
    
    public ProfileCG.Technique.Pass.MaterialAmbient createProfileCGTechniquePassMaterialAmbient() {
        return new ProfileCG.Technique.Pass.MaterialAmbient();
    }
    
    public ProfileCG.Technique.Pass.TextureEnvMode createProfileCGTechniquePassTextureEnvMode() {
        return new ProfileCG.Technique.Pass.TextureEnvMode();
    }
    
    public ProfileCG.Technique.Pass.TextureDEPTH createProfileCGTechniquePassTextureDEPTH() {
        return new ProfileCG.Technique.Pass.TextureDEPTH();
    }
    
    public ProfileGLES.Technique.Pass.LightSpotDirection createProfileGLESTechniquePassLightSpotDirection() {
        return new ProfileGLES.Technique.Pass.LightSpotDirection();
    }
    
    public ProfileCG.Technique.Pass.StencilOp.Zfail createProfileCGTechniquePassStencilOpZfail() {
        return new ProfileCG.Technique.Pass.StencilOp.Zfail();
    }
    
    public ProfileCG.Technique.Pass.AutoNormalEnable createProfileCGTechniquePassAutoNormalEnable() {
        return new ProfileCG.Technique.Pass.AutoNormalEnable();
    }
    
    public FxSurfaceInitVolumeCommon createFxSurfaceInitVolumeCommon() {
        return new FxSurfaceInitVolumeCommon();
    }
    
    public ProfileGLES.Technique.Pass.MaterialEmission createProfileGLESTechniquePassMaterialEmission() {
        return new ProfileGLES.Technique.Pass.MaterialEmission();
    }
    
    public ProfileCG.Technique.Pass.ScissorTestEnable createProfileCGTechniquePassScissorTestEnable() {
        return new ProfileCG.Technique.Pass.ScissorTestEnable();
    }
    
    public GlslSetparamSimple createGlslSetparamSimple() {
        return new GlslSetparamSimple();
    }
    
    public ProfileCG.Technique.Pass.BlendFuncSeparate.DestRgb createProfileCGTechniquePassBlendFuncSeparateDestRgb() {
        return new ProfileCG.Technique.Pass.BlendFuncSeparate.DestRgb();
    }
    
    public Light.TechniqueCommon.Directional createLightTechniqueCommonDirectional() {
        return new Light.TechniqueCommon.Directional();
    }
    
    public ProfileCG.Technique.Pass.PointFadeThresholdSize createProfileCGTechniquePassPointFadeThresholdSize() {
        return new ProfileCG.Technique.Pass.PointFadeThresholdSize();
    }
    
    public ProfileCG.Technique.Pass.BlendFuncSeparate.SrcRgb createProfileCGTechniquePassBlendFuncSeparateSrcRgb() {
        return new ProfileCG.Technique.Pass.BlendFuncSeparate.SrcRgb();
    }
    
    public InstanceGeometry createInstanceGeometry() {
        return new InstanceGeometry();
    }
    
    public FxSurfaceInitFromCommon createFxSurfaceInitFromCommon() {
        return new FxSurfaceInitFromCommon();
    }
    
    public Extra createExtra() {
        return new Extra();
    }
    
    public ProfileGLES.Technique.Pass.StencilTestEnable createProfileGLESTechniquePassStencilTestEnable() {
        return new ProfileGLES.Technique.Pass.StencilTestEnable();
    }
    
    public ProfileCG.Technique.Pass.BlendEquationSeparate.Alpha createProfileCGTechniquePassBlendEquationSeparateAlpha() {
        return new ProfileCG.Technique.Pass.BlendEquationSeparate.Alpha();
    }
    
    public ProfileGLES.Technique.Pass.SampleAlphaToOneEnable createProfileGLESTechniquePassSampleAlphaToOneEnable() {
        return new ProfileGLES.Technique.Pass.SampleAlphaToOneEnable();
    }
    
    public Skew createSkew() {
        return new Skew();
    }
    
    public ProfileGLES.Technique.Pass.FogEnd createProfileGLESTechniquePassFogEnd() {
        return new ProfileGLES.Technique.Pass.FogEnd();
    }
    
    public CgConnectParam createCgConnectParam() {
        return new CgConnectParam();
    }
    
    public FxSamplerCUBECommon createFxSamplerCUBECommon() {
        return new FxSamplerCUBECommon();
    }
    
    public Source createSource() {
        return new Source();
    }
    
    public FxSurfaceFormatHintCommon createFxSurfaceFormatHintCommon() {
        return new FxSurfaceFormatHintCommon();
    }
    
    public ProfileCG.Technique.Pass.MaterialEmission createProfileCGTechniquePassMaterialEmission() {
        return new ProfileCG.Technique.Pass.MaterialEmission();
    }
    
    public FxSurfaceInitCubeCommon.Face createFxSurfaceInitCubeCommonFace() {
        return new FxSurfaceInitCubeCommon.Face();
    }
    
    public ProfileGLES.Technique.Pass createProfileGLESTechniquePass() {
        return new ProfileGLES.Technique.Pass();
    }
    
    public ProfileCG.Technique.Pass.TextureCUBEEnable createProfileCGTechniquePassTextureCUBEEnable() {
        return new ProfileCG.Technique.Pass.TextureCUBEEnable();
    }
    
    public FxSurfaceInitVolumeCommon.All createFxSurfaceInitVolumeCommonAll() {
        return new FxSurfaceInitVolumeCommon.All();
    }
    
    public GlslNewarrayType createGlslNewarrayType() {
        return new GlslNewarrayType();
    }
    
    public GlesTexcombinerArgumentRGBType createGlesTexcombinerArgumentRGBType() {
        return new GlesTexcombinerArgumentRGBType();
    }
    
    public Polygons createPolygons() {
        return new Polygons();
    }
    
    public ProfileCG.Technique.Pass.CullFaceEnable createProfileCGTechniquePassCullFaceEnable() {
        return new ProfileCG.Technique.Pass.CullFaceEnable();
    }
    
    public ProfileGLES.Technique.Pass.LightModelAmbient createProfileGLESTechniquePassLightModelAmbient() {
        return new ProfileGLES.Technique.Pass.LightModelAmbient();
    }
    
    public ProfileCG.Technique.Pass.StencilOp createProfileCGTechniquePassStencilOp() {
        return new ProfileCG.Technique.Pass.StencilOp();
    }
    
    public RigidConstraint.TechniqueCommon.Spring createRigidConstraintTechniqueCommonSpring() {
        return new RigidConstraint.TechniqueCommon.Spring();
    }
    
    public BoolArray createBoolArray() {
        return new BoolArray();
    }
    
    public GlSamplerCUBE createGlSamplerCUBE() {
        return new GlSamplerCUBE();
    }
    
    public ProfileCG.Technique.Pass.PointDistanceAttenuation createProfileCGTechniquePassPointDistanceAttenuation() {
        return new ProfileCG.Technique.Pass.PointDistanceAttenuation();
    }
    
    public Light createLight() {
        return new Light();
    }
    
    public ProfileCG.Technique.Pass.ColorMask createProfileCGTechniquePassColorMask() {
        return new ProfileCG.Technique.Pass.ColorMask();
    }
    
    public ProfileCG.Technique.Pass.StencilMaskSeparate createProfileCGTechniquePassStencilMaskSeparate() {
        return new ProfileCG.Technique.Pass.StencilMaskSeparate();
    }
    
    public ProfileGLSL.Technique.Pass.Shader.Bind createProfileGLSLTechniquePassShaderBind() {
        return new ProfileGLSL.Technique.Pass.Shader.Bind();
    }
    
    public ProfileCG.Technique.Pass.TextureRECTEnable createProfileCGTechniquePassTextureRECTEnable() {
        return new ProfileCG.Technique.Pass.TextureRECTEnable();
    }
    
    public ProfileGLES.Technique.Pass.NormalizeEnable createProfileGLESTechniquePassNormalizeEnable() {
        return new ProfileGLES.Technique.Pass.NormalizeEnable();
    }
    
    public ProfileCG.Technique.Pass.BlendFuncSeparate.DestAlpha createProfileCGTechniquePassBlendFuncSeparateDestAlpha() {
        return new ProfileCG.Technique.Pass.BlendFuncSeparate.DestAlpha();
    }
    
    public ProfileCG.Technique.Pass.PolygonOffsetFillEnable createProfileCGTechniquePassPolygonOffsetFillEnable() {
        return new ProfileCG.Technique.Pass.PolygonOffsetFillEnable();
    }
    
    public InstancePhysicsModel createInstancePhysicsModel() {
        return new InstancePhysicsModel();
    }
    
    public ProfileGLES.Technique.Pass.LightingEnable createProfileGLESTechniquePassLightingEnable() {
        return new ProfileGLES.Technique.Pass.LightingEnable();
    }
    
    public CommonFloatOrParamType.Param createCommonFloatOrParamTypeParam() {
        return new CommonFloatOrParamType.Param();
    }
    
    public Sampler createSampler() {
        return new Sampler();
    }
    
    public ProfileGLES.Technique.Pass.MaterialDiffuse createProfileGLESTechniquePassMaterialDiffuse() {
        return new ProfileGLES.Technique.Pass.MaterialDiffuse();
    }
    
    public RigidConstraint.RefAttachment createRigidConstraintRefAttachment() {
        return new RigidConstraint.RefAttachment();
    }
    
    public GlesTexcombinerCommandAlphaType createGlesTexcombinerCommandAlphaType() {
        return new GlesTexcombinerCommandAlphaType();
    }
    
    public Channel createChannel() {
        return new Channel();
    }
    
    public ProfileGLES.Technique.Pass.BlendFunc.Src createProfileGLESTechniquePassBlendFuncSrc() {
        return new ProfileGLES.Technique.Pass.BlendFunc.Src();
    }
    
    public Technique createTechnique() {
        return new Technique();
    }
    
    public Camera.Imager createCameraImager() {
        return new Camera.Imager();
    }
    
    public Morph createMorph() {
        return new Morph();
    }
    
    public ProfileCG.Technique.Pass.StencilFuncSeparate createProfileCGTechniquePassStencilFuncSeparate() {
        return new ProfileCG.Technique.Pass.StencilFuncSeparate();
    }
    
    public GlesSamplerState createGlesSamplerState() {
        return new GlesSamplerState();
    }
    
    public ProfileGLES.Technique.Pass.LightModelTwoSideEnable createProfileGLESTechniquePassLightModelTwoSideEnable() {
        return new ProfileGLES.Technique.Pass.LightModelTwoSideEnable();
    }
    
    public ProfileGLES.Technique.Pass.StencilFunc.Mask createProfileGLESTechniquePassStencilFuncMask() {
        return new ProfileGLES.Technique.Pass.StencilFunc.Mask();
    }
    
    public ProfileCG.Technique.Pass.PointSize createProfileCGTechniquePassPointSize() {
        return new ProfileCG.Technique.Pass.PointSize();
    }
    
    public BindMaterial.TechniqueCommon createBindMaterialTechniqueCommon() {
        return new BindMaterial.TechniqueCommon();
    }
    
    public GlslSetparam createGlslSetparam() {
        return new GlslSetparam();
    }
    
    public ProfileGLES.Technique.Pass.PointSizeMin createProfileGLESTechniquePassPointSizeMin() {
        return new ProfileGLES.Technique.Pass.PointSizeMin();
    }
    
    public Material createMaterial() {
        return new Material();
    }
    
    public Accessor createAccessor() {
        return new Accessor();
    }
    
    public LibraryPhysicsMaterials createLibraryPhysicsMaterials() {
        return new LibraryPhysicsMaterials();
    }
    
    public Box createBox() {
        return new Box();
    }
    
    public ProfileCG.Technique.Pass.AlphaTestEnable createProfileCGTechniquePassAlphaTestEnable() {
        return new ProfileCG.Technique.Pass.AlphaTestEnable();
    }
    
    public Polygons.Ph createPolygonsPh() {
        return new Polygons.Ph();
    }
    
    public ProfileGLES.Technique.Pass.TexturePipeline createProfileGLESTechniquePassTexturePipeline() {
        return new ProfileGLES.Technique.Pass.TexturePipeline();
    }
    
    public ProfileCG.Technique.Pass.FogDensity createProfileCGTechniquePassFogDensity() {
        return new ProfileCG.Technique.Pass.FogDensity();
    }
    
    public ProfileGLES.Technique.Pass.ModelViewMatrix createProfileGLESTechniquePassModelViewMatrix() {
        return new ProfileGLES.Technique.Pass.ModelViewMatrix();
    }
    
    public ProfileCG.Technique.Pass.StencilFunc.Func createProfileCGTechniquePassStencilFuncFunc() {
        return new ProfileCG.Technique.Pass.StencilFunc.Func();
    }
    
    public InstanceMaterial createInstanceMaterial() {
        return new InstanceMaterial();
    }
    
    public GlesTextureUnit createGlesTextureUnit() {
        return new GlesTextureUnit();
    }
    
    public ProfileCG.Technique.Pass.LightQuadraticAttenuation createProfileCGTechniquePassLightQuadraticAttenuation() {
        return new ProfileCG.Technique.Pass.LightQuadraticAttenuation();
    }
    
    public CommonColorOrTextureType.Texture createCommonColorOrTextureTypeTexture() {
        return new CommonColorOrTextureType.Texture();
    }
    
    public ProfileGLES.Technique.Pass.ScissorTestEnable createProfileGLESTechniquePassScissorTestEnable() {
        return new ProfileGLES.Technique.Pass.ScissorTestEnable();
    }
    
    public ProfileGLES.Technique.Pass.ClearColor createProfileGLESTechniquePassClearColor() {
        return new ProfileGLES.Technique.Pass.ClearColor();
    }
    
    public ProfileGLES.Technique.Pass.AlphaFunc.Func createProfileGLESTechniquePassAlphaFuncFunc() {
        return new ProfileGLES.Technique.Pass.AlphaFunc.Func();
    }
    
    public ProfileCG.Technique.Pass.LineSmoothEnable createProfileCGTechniquePassLineSmoothEnable() {
        return new ProfileCG.Technique.Pass.LineSmoothEnable();
    }
    
    public ProfileCG.Technique.Pass.LightConstantAttenuation createProfileCGTechniquePassLightConstantAttenuation() {
        return new ProfileCG.Technique.Pass.LightConstantAttenuation();
    }
    
    public GlslSetarrayType createGlslSetarrayType() {
        return new GlslSetarrayType();
    }
    
    public ProfileGLES.Technique.Pass.FogColor createProfileGLESTechniquePassFogColor() {
        return new ProfileGLES.Technique.Pass.FogColor();
    }
    
    public Light.TechniqueCommon.Point createLightTechniqueCommonPoint() {
        return new Light.TechniqueCommon.Point();
    }
    
    public ProfileCG.Technique.Pass.PolygonMode.Face createProfileCGTechniquePassPolygonModeFace() {
        return new ProfileCG.Technique.Pass.PolygonMode.Face();
    }
    
    public Param createParam() {
        return new Param();
    }
    
    public ProfileGLES.Technique.Setparam createProfileGLESTechniqueSetparam() {
        return new ProfileGLES.Technique.Setparam();
    }
    
    public ProfileCG.Technique.Pass.Shader.Bind.Param createProfileCGTechniquePassShaderBindParam() {
        return new ProfileCG.Technique.Pass.Shader.Bind.Param();
    }
    
    public CgSetarrayType createCgSetarrayType() {
        return new CgSetarrayType();
    }
    
    public ProfileCG.Technique.Pass.PointSizeMin createProfileCGTechniquePassPointSizeMin() {
        return new ProfileCG.Technique.Pass.PointSizeMin();
    }
    
    public ProfileCG.Technique.Pass.Scissor createProfileCGTechniquePassScissor() {
        return new ProfileCG.Technique.Pass.Scissor();
    }
    
    public LibraryAnimationClips createLibraryAnimationClips() {
        return new LibraryAnimationClips();
    }
    
    public FxSampler3DCommon createFxSampler3DCommon() {
        return new FxSampler3DCommon();
    }
    
    public GlSamplerRECT createGlSamplerRECT() {
        return new GlSamplerRECT();
    }
    
    public ProfileCG.Technique.Pass.Shader createProfileCGTechniquePassShader() {
        return new ProfileCG.Technique.Pass.Shader();
    }
    
    public ProfileGLES.Technique.Pass.FogMode createProfileGLESTechniquePassFogMode() {
        return new ProfileGLES.Technique.Pass.FogMode();
    }
    
    public Lookat createLookat() {
        return new Lookat();
    }
    
    public ProfileCG.Technique.Pass.PolygonSmoothEnable createProfileCGTechniquePassPolygonSmoothEnable() {
        return new ProfileCG.Technique.Pass.PolygonSmoothEnable();
    }
    
    public ProfileCG.Technique.Pass.DepthBoundsEnable createProfileCGTechniquePassDepthBoundsEnable() {
        return new ProfileCG.Technique.Pass.DepthBoundsEnable();
    }
    
    public ProfileCG.Technique.Pass.ModelViewMatrix createProfileCGTechniquePassModelViewMatrix() {
        return new ProfileCG.Technique.Pass.ModelViewMatrix();
    }
    
    public ProfileGLES.Technique.Pass.FogStart createProfileGLESTechniquePassFogStart() {
        return new ProfileGLES.Technique.Pass.FogStart();
    }
    
    public ProfileCG.Technique.Pass.StencilFuncSeparate.Mask createProfileCGTechniquePassStencilFuncSeparateMask() {
        return new ProfileCG.Technique.Pass.StencilFuncSeparate.Mask();
    }
    
    public FxCleardepthCommon createFxCleardepthCommon() {
        return new FxCleardepthCommon();
    }
    
    public ProfileGLES.Technique.Pass.LightPosition createProfileGLESTechniquePassLightPosition() {
        return new ProfileGLES.Technique.Pass.LightPosition();
    }
    
    public ProfileCG.Technique.Pass.Texture1D createProfileCGTechniquePassTexture1D() {
        return new ProfileCG.Technique.Pass.Texture1D();
    }
    
    public ProfileCG.Technique.Pass.BlendFuncSeparate.SrcAlpha createProfileCGTechniquePassBlendFuncSeparateSrcAlpha() {
        return new ProfileCG.Technique.Pass.BlendFuncSeparate.SrcAlpha();
    }
    
    public IntArray createIntArray() {
        return new IntArray();
    }
    
    public ProfileCOMMON.Technique.Phong createProfileCOMMONTechniquePhong() {
        return new ProfileCOMMON.Technique.Phong();
    }
    
    public CgSetparam createCgSetparam() {
        return new CgSetparam();
    }
    
    public CgSurfaceType.Generator createCgSurfaceTypeGenerator() {
        return new CgSurfaceType.Generator();
    }
    
    public Plane createPlane() {
        return new Plane();
    }
    
    public RigidBody.TechniqueCommon.MassFrame createRigidBodyTechniqueCommonMassFrame() {
        return new RigidBody.TechniqueCommon.MassFrame();
    }
    
    public ProfileGLES.Technique.Pass.PolygonOffset createProfileGLESTechniquePassPolygonOffset() {
        return new ProfileGLES.Technique.Pass.PolygonOffset();
    }
    
    public ProfileCG.Technique.Pass.StencilMaskSeparate.Face createProfileCGTechniquePassStencilMaskSeparateFace() {
        return new ProfileCG.Technique.Pass.StencilMaskSeparate.Face();
    }
    
    public InstanceRigidConstraint createInstanceRigidConstraint() {
        return new InstanceRigidConstraint();
    }
    
    public ProfileCG.Technique.Pass.SampleCoverageEnable createProfileCGTechniquePassSampleCoverageEnable() {
        return new ProfileCG.Technique.Pass.SampleCoverageEnable();
    }
    
    public CgNewparam createCgNewparam() {
        return new CgNewparam();
    }
    
    public ProfileGLES.Technique.Pass.LightSpotExponent createProfileGLESTechniquePassLightSpotExponent() {
        return new ProfileGLES.Technique.Pass.LightSpotExponent();
    }
    
    public ProfileCG.Technique.Pass.MultisampleEnable createProfileCGTechniquePassMultisampleEnable() {
        return new ProfileCG.Technique.Pass.MultisampleEnable();
    }
    
    public Triangles createTriangles() {
        return new Triangles();
    }
    
    public ProfileGLES.Technique.Pass.ColorMask createProfileGLESTechniquePassColorMask() {
        return new ProfileGLES.Technique.Pass.ColorMask();
    }
    
    public CgSamplerDEPTH createCgSamplerDEPTH() {
        return new CgSamplerDEPTH();
    }
    
    public Camera.Optics createCameraOptics() {
        return new Camera.Optics();
    }
    
    public ProfileCG.Technique.Pass.StencilFuncSeparate.Back createProfileCGTechniquePassStencilFuncSeparateBack() {
        return new ProfileCG.Technique.Pass.StencilFuncSeparate.Back();
    }
    
    public ProfileGLES.Technique.Pass.Scissor createProfileGLESTechniquePassScissor() {
        return new ProfileGLES.Technique.Pass.Scissor();
    }
    
    public Spline.ControlVertices createSplineControlVertices() {
        return new Spline.ControlVertices();
    }
    
    public ProfileGLES.Technique.Pass.StencilFunc.Func createProfileGLESTechniquePassStencilFuncFunc() {
        return new ProfileGLES.Technique.Pass.StencilFunc.Func();
    }
    
    public ProfileGLES.Technique.Pass.FrontFace createProfileGLESTechniquePassFrontFace() {
        return new ProfileGLES.Technique.Pass.FrontFace();
    }
    
    public ProfileGLES.Technique.Pass.LineSmoothEnable createProfileGLESTechniquePassLineSmoothEnable() {
        return new ProfileGLES.Technique.Pass.LineSmoothEnable();
    }
    
    public ProfileGLES.Technique.Pass.StencilOp.Fail createProfileGLESTechniquePassStencilOpFail() {
        return new ProfileGLES.Technique.Pass.StencilOp.Fail();
    }
    
    public ProfileGLSL createProfileGLSL() {
        return new ProfileGLSL();
    }
    
    public ProfileGLES.Technique.Pass.ColorMaterialEnable createProfileGLESTechniquePassColorMaterialEnable() {
        return new ProfileGLES.Technique.Pass.ColorMaterialEnable();
    }
    
    public ProfileCOMMON.Technique createProfileCOMMONTechnique() {
        return new ProfileCOMMON.Technique();
    }
    
    public Skin createSkin() {
        return new Skin();
    }
    
    public ProfileCG.Technique.Pass.FogCoordSrc createProfileCGTechniquePassFogCoordSrc() {
        return new ProfileCG.Technique.Pass.FogCoordSrc();
    }
    
    public ProfileCG.Technique.Pass.PolygonOffset createProfileCGTechniquePassPolygonOffset() {
        return new ProfileCG.Technique.Pass.PolygonOffset();
    }
    
    public ProfileCG.Technique.Pass.TextureDEPTHEnable createProfileCGTechniquePassTextureDEPTHEnable() {
        return new ProfileCG.Technique.Pass.TextureDEPTHEnable();
    }
    
    public ProfileCG.Technique.Pass.TextureRECT createProfileCGTechniquePassTextureRECT() {
        return new ProfileCG.Technique.Pass.TextureRECT();
    }
    
    public NameArray createNameArray() {
        return new NameArray();
    }
    
    public ProfileGLES.Technique.Pass.MultisampleEnable createProfileGLESTechniquePassMultisampleEnable() {
        return new ProfileGLES.Technique.Pass.MultisampleEnable();
    }
    
    public Tristrips createTristrips() {
        return new Tristrips();
    }
    
    public ProfileGLES.Technique.Pass.SampleAlphaToCoverageEnable createProfileGLESTechniquePassSampleAlphaToCoverageEnable() {
        return new ProfileGLES.Technique.Pass.SampleAlphaToCoverageEnable();
    }
    
    public Source.TechniqueCommon createSourceTechniqueCommon() {
        return new Source.TechniqueCommon();
    }
    
    public GlesTexcombinerArgumentAlphaType createGlesTexcombinerArgumentAlphaType() {
        return new GlesTexcombinerArgumentAlphaType();
    }
    
    public ProfileCG.Technique.Pass.AlphaFunc createProfileCGTechniquePassAlphaFunc() {
        return new ProfileCG.Technique.Pass.AlphaFunc();
    }
    
    public ProfileCG.Technique.Pass.BlendFuncSeparate createProfileCGTechniquePassBlendFuncSeparate() {
        return new ProfileCG.Technique.Pass.BlendFuncSeparate();
    }
    
    public ProfileCG.Technique.Pass.LineStippleEnable createProfileCGTechniquePassLineStippleEnable() {
        return new ProfileCG.Technique.Pass.LineStippleEnable();
    }
    
    public LibraryGeometries createLibraryGeometries() {
        return new LibraryGeometries();
    }
    
    public ProfileCG.Technique.Pass.Texture1DEnable createProfileCGTechniquePassTexture1DEnable() {
        return new ProfileCG.Technique.Pass.Texture1DEnable();
    }
    
    public CgSurfaceType.Generator.Name createCgSurfaceTypeGeneratorName() {
        return new CgSurfaceType.Generator.Name();
    }
    
    public ProfileCG.Technique.Pass.BlendFunc createProfileCGTechniquePassBlendFunc() {
        return new ProfileCG.Technique.Pass.BlendFunc();
    }
    
    public InstanceEffect createInstanceEffect() {
        return new InstanceEffect();
    }
    
    public Asset createAsset() {
        return new Asset();
    }
    
    public FxSamplerRECTCommon createFxSamplerRECTCommon() {
        return new FxSamplerRECTCommon();
    }
    
    public CommonNewparamType createCommonNewparamType() {
        return new CommonNewparamType();
    }
    
    public InstanceMaterial.Bind createInstanceMaterialBind() {
        return new InstanceMaterial.Bind();
    }
    
    public ProfileCOMMON.Technique.Blinn createProfileCOMMONTechniqueBlinn() {
        return new ProfileCOMMON.Technique.Blinn();
    }
    
    public ProfileGLSL.Technique.Pass.Shader.CompilerTarget createProfileGLSLTechniquePassShaderCompilerTarget() {
        return new ProfileGLSL.Technique.Pass.Shader.CompilerTarget();
    }
    
    public ProfileGLES.Technique.Pass.ColorLogicOpEnable createProfileGLESTechniquePassColorLogicOpEnable() {
        return new ProfileGLES.Technique.Pass.ColorLogicOpEnable();
    }
    
    public RigidConstraint createRigidConstraint() {
        return new RigidConstraint();
    }
    
    public InstanceRigidBody.TechniqueCommon.Shape.Hollow createInstanceRigidBodyTechniqueCommonShapeHollow() {
        return new InstanceRigidBody.TechniqueCommon.Shape.Hollow();
    }
    
    public Camera.Optics.TechniqueCommon.Perspective createCameraOpticsTechniqueCommonPerspective() {
        return new Camera.Optics.TechniqueCommon.Perspective();
    }
    
    public ProfileCG.Technique.Pass.Shader.Name createProfileCGTechniquePassShaderName() {
        return new ProfileCG.Technique.Pass.Shader.Name();
    }
    
    public ProfileCG.Technique.Pass.TextureEnvColor createProfileCGTechniquePassTextureEnvColor() {
        return new ProfileCG.Technique.Pass.TextureEnvColor();
    }
    
    public ProfileCG.Technique.Pass.LineWidth createProfileCGTechniquePassLineWidth() {
        return new ProfileCG.Technique.Pass.LineWidth();
    }
    
    public CommonFloatOrParamType.Float createCommonFloatOrParamTypeFloat() {
        return new CommonFloatOrParamType.Float();
    }
    
    public ProfileCG.Technique.Pass.DepthRange createProfileCGTechniquePassDepthRange() {
        return new ProfileCG.Technique.Pass.DepthRange();
    }
    
    public ProfileCG.Technique.Pass.LogicOp createProfileCGTechniquePassLogicOp() {
        return new ProfileCG.Technique.Pass.LogicOp();
    }
    
    public ProfileCG.Technique.Pass.NormalizeEnable createProfileCGTechniquePassNormalizeEnable() {
        return new ProfileCG.Technique.Pass.NormalizeEnable();
    }
    
    public FxSurfaceInitPlanarCommon createFxSurfaceInitPlanarCommon() {
        return new FxSurfaceInitPlanarCommon();
    }
    
    public ProfileGLES.Technique.Pass.StencilFunc.Ref createProfileGLESTechniquePassStencilFuncRef() {
        return new ProfileGLES.Technique.Pass.StencilFunc.Ref();
    }
    
    public Sphere createSphere() {
        return new Sphere();
    }
    
    public FxSampler1DCommon createFxSampler1DCommon() {
        return new FxSampler1DCommon();
    }
    
    public ProfileCG.Technique.Pass.DepthMask createProfileCGTechniquePassDepthMask() {
        return new ProfileCG.Technique.Pass.DepthMask();
    }
    
    public Ellipsoid createEllipsoid() {
        return new Ellipsoid();
    }
    
    public FxSampler2DCommon createFxSampler2DCommon() {
        return new FxSampler2DCommon();
    }
    
    public COLLADA createCOLLADA() {
        return new COLLADA();
    }
    
    public ProfileGLES.Technique.Pass.TexturePipelineEnable createProfileGLESTechniquePassTexturePipelineEnable() {
        return new ProfileGLES.Technique.Pass.TexturePipelineEnable();
    }
    
    public ProfileCG.Technique.Pass.LightDiffuse createProfileCGTechniquePassLightDiffuse() {
        return new ProfileCG.Technique.Pass.LightDiffuse();
    }
    
    public PhysicsMaterial createPhysicsMaterial() {
        return new PhysicsMaterial();
    }
    
    public ProfileCG.Technique.Pass.DitherEnable createProfileCGTechniquePassDitherEnable() {
        return new ProfileCG.Technique.Pass.DitherEnable();
    }
    
    public PhysicsScene.TechniqueCommon createPhysicsSceneTechniqueCommon() {
        return new PhysicsScene.TechniqueCommon();
    }
    
    public InstanceEffect.Setparam createInstanceEffectSetparam() {
        return new InstanceEffect.Setparam();
    }
    
    public ProfileGLES.Technique.Pass.LightQuadraticAttenuation createProfileGLESTechniquePassLightQuadraticAttenuation() {
        return new ProfileGLES.Technique.Pass.LightQuadraticAttenuation();
    }
    
    public RigidBody.TechniqueCommon.Dynamic createRigidBodyTechniqueCommonDynamic() {
        return new RigidBody.TechniqueCommon.Dynamic();
    }
    
    public ProfileGLES.Technique.Pass.LightSpecular createProfileGLESTechniquePassLightSpecular() {
        return new ProfileGLES.Technique.Pass.LightSpecular();
    }
    
    public FxClearcolorCommon createFxClearcolorCommon() {
        return new FxClearcolorCommon();
    }
    
    public ProfileGLES.Technique.Pass.ClipPlane createProfileGLESTechniquePassClipPlane() {
        return new ProfileGLES.Technique.Pass.ClipPlane();
    }
    
    public ProfileCG.Technique.Pass.BlendColor createProfileCGTechniquePassBlendColor() {
        return new ProfileCG.Technique.Pass.BlendColor();
    }
    
    public VisualScene createVisualScene() {
        return new VisualScene();
    }
    
    public ProfileCG.Technique.Pass.FogMode createProfileCGTechniquePassFogMode() {
        return new ProfileCG.Technique.Pass.FogMode();
    }
    
    public ProfileCG.Technique.Pass.LightSpecular createProfileCGTechniquePassLightSpecular() {
        return new ProfileCG.Technique.Pass.LightSpecular();
    }
    
    public ProfileGLES.Technique.Pass.DepthFunc createProfileGLESTechniquePassDepthFunc() {
        return new ProfileGLES.Technique.Pass.DepthFunc();
    }
    
    public ProfileCG.Technique.Pass.ShadeModel createProfileCGTechniquePassShadeModel() {
        return new ProfileCG.Technique.Pass.ShadeModel();
    }
    
    public GlesTexcombinerCommandRGBType createGlesTexcombinerCommandRGBType() {
        return new GlesTexcombinerCommandRGBType();
    }
    
    public ProfileCG.Technique.Pass.StencilOp.Fail createProfileCGTechniquePassStencilOpFail() {
        return new ProfileCG.Technique.Pass.StencilOp.Fail();
    }
    
    public ProfileCG.Technique.Pass.StencilOpSeparate.Face createProfileCGTechniquePassStencilOpSeparateFace() {
        return new ProfileCG.Technique.Pass.StencilOpSeparate.Face();
    }
    
    public ProfileGLES.Technique.Pass.MaterialSpecular createProfileGLESTechniquePassMaterialSpecular() {
        return new ProfileGLES.Technique.Pass.MaterialSpecular();
    }
    
    public RigidConstraint.TechniqueCommon.Spring.Angular createRigidConstraintTechniqueCommonSpringAngular() {
        return new RigidConstraint.TechniqueCommon.Spring.Angular();
    }
    
    public ProfileGLSL.Technique createProfileGLSLTechnique() {
        return new ProfileGLSL.Technique();
    }
    
    public GlesTexturePipeline createGlesTexturePipeline() {
        return new GlesTexturePipeline();
    }
    
    public ProfileCG.Technique.Pass.StencilTestEnable createProfileCGTechniquePassStencilTestEnable() {
        return new ProfileCG.Technique.Pass.StencilTestEnable();
    }
    
    public RigidBody.TechniqueCommon.Shape.Hollow createRigidBodyTechniqueCommonShapeHollow() {
        return new RigidBody.TechniqueCommon.Shape.Hollow();
    }
    
    public ProfileCG createProfileCG() {
        return new ProfileCG();
    }
    
    public ProfileGLES.Technique.Pass.StencilFunc createProfileGLESTechniquePassStencilFunc() {
        return new ProfileGLES.Technique.Pass.StencilFunc();
    }
    
    public ProfileGLES.Technique.Pass.StencilOp createProfileGLESTechniquePassStencilOp() {
        return new ProfileGLES.Technique.Pass.StencilOp();
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float2x2", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFloat2X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat2X2_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float2x3", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFloat2X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat2X3_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float4", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFloat4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat4_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float2x4", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFloat2X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat2X4_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half", scope = CgSetuserType.class)
    public JAXBElement<Float> createCgSetuserTypeHalf(final Float value) {
        return new JAXBElement<Float>(ObjectFactory._CgSetuserTypeHalf_QNAME, Float.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float2", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFloat2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat2_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float3", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFloat3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat3_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float1", scope = CgSetuserType.class)
    public JAXBElement<Float> createCgSetuserTypeFloat1(final Float value) {
        return new JAXBElement<Float>(ObjectFactory._CgSetuserTypeFloat1_QNAME, Float.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int1x1", scope = CgSetuserType.class)
    public JAXBElement<List<Integer>> createCgSetuserTypeInt1X1(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt1X1_QNAME, (Class<List<Integer>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int1x3", scope = CgSetuserType.class)
    public JAXBElement<List<Integer>> createCgSetuserTypeInt1X3(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt1X3_QNAME, (Class<List<Integer>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int1x2", scope = CgSetuserType.class)
    public JAXBElement<List<Integer>> createCgSetuserTypeInt1X2(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt1X2_QNAME, (Class<List<Integer>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float2x1", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFloat2X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat2X1_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int1x4", scope = CgSetuserType.class)
    public JAXBElement<List<Integer>> createCgSetuserTypeInt1X4(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt1X4_QNAME, (Class<List<Integer>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int1", scope = CgSetuserType.class)
    public JAXBElement<Integer> createCgSetuserTypeInt1(final Integer value) {
        return new JAXBElement<Integer>(ObjectFactory._CgSetuserTypeInt1_QNAME, Integer.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int2", scope = CgSetuserType.class)
    public JAXBElement<List<Integer>> createCgSetuserTypeInt2(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt2_QNAME, (Class<List<Integer>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int3", scope = CgSetuserType.class)
    public JAXBElement<List<Integer>> createCgSetuserTypeInt3(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt3_QNAME, (Class<List<Integer>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int4", scope = CgSetuserType.class)
    public JAXBElement<List<Integer>> createCgSetuserTypeInt4(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt4_QNAME, (Class<List<Integer>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "enum", scope = CgSetuserType.class)
    public JAXBElement<String> createCgSetuserTypeEnum(final String value) {
        return new JAXBElement<String>(ObjectFactory._CgSetuserTypeEnum_QNAME, String.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int", scope = CgSetuserType.class)
    public JAXBElement<Integer> createCgSetuserTypeInt(final Integer value) {
        return new JAXBElement<Integer>(ObjectFactory._CgSetuserTypeInt_QNAME, Integer.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed2x1", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFixed2X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed2X1_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "connect_param", scope = CgSetuserType.class)
    public JAXBElement<CgConnectParam> createCgSetuserTypeConnectParam(final CgConnectParam value) {
        return new JAXBElement<CgConnectParam>(ObjectFactory._CgSetuserTypeConnectParam_QNAME, CgConnectParam.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "samplerRECT", scope = CgSetuserType.class)
    public JAXBElement<CgSamplerRECT> createCgSetuserTypeSamplerRECT(final CgSamplerRECT value) {
        return new JAXBElement<CgSamplerRECT>(ObjectFactory._CgSetuserTypeSamplerRECT_QNAME, CgSamplerRECT.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "sampler1D", scope = CgSetuserType.class)
    public JAXBElement<CgSampler1D> createCgSetuserTypeSampler1D(final CgSampler1D value) {
        return new JAXBElement<CgSampler1D>(ObjectFactory._CgSetuserTypeSampler1D_QNAME, CgSampler1D.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed4", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFixed4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed4_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed2x4", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFixed2X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed2X4_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed2x3", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFixed2X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed2X3_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed2x2", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFixed2X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed2X2_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed1", scope = CgSetuserType.class)
    public JAXBElement<Float> createCgSetuserTypeFixed1(final Float value) {
        return new JAXBElement<Float>(ObjectFactory._CgSetuserTypeFixed1_QNAME, Float.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed2", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFixed2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed2_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed3", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFixed3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed3_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed3x1", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFixed3X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed3X1_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int3x4", scope = CgSetuserType.class)
    public JAXBElement<List<Integer>> createCgSetuserTypeInt3X4(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt3X4_QNAME, (Class<List<Integer>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed3x2", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFixed3X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed3X2_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed3x3", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFixed3X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed3X3_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed3x4", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFixed3X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed3X4_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int4x4", scope = CgSetuserType.class)
    public JAXBElement<List<Integer>> createCgSetuserTypeInt4X4(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt4X4_QNAME, (Class<List<Integer>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int4x3", scope = CgSetuserType.class)
    public JAXBElement<List<Integer>> createCgSetuserTypeInt4X3(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt4X3_QNAME, (Class<List<Integer>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half2", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeHalf2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf2_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half1", scope = CgSetuserType.class)
    public JAXBElement<Float> createCgSetuserTypeHalf1(final Float value) {
        return new JAXBElement<Float>(ObjectFactory._CgSetuserTypeHalf1_QNAME, Float.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool2x3", scope = CgSetuserType.class)
    public JAXBElement<List<Boolean>> createCgSetuserTypeBool2X3(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool2X3_QNAME, (Class<List<Boolean>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool2x2", scope = CgSetuserType.class)
    public JAXBElement<List<Boolean>> createCgSetuserTypeBool2X2(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool2X2_QNAME, (Class<List<Boolean>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool2x1", scope = CgSetuserType.class)
    public JAXBElement<List<Boolean>> createCgSetuserTypeBool2X1(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool2X1_QNAME, (Class<List<Boolean>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool2x4", scope = CgSetuserType.class)
    public JAXBElement<List<Boolean>> createCgSetuserTypeBool2X4(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool2X4_QNAME, (Class<List<Boolean>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "sampler2D", scope = CgSetuserType.class)
    public JAXBElement<CgSampler2D> createCgSetuserTypeSampler2D(final CgSampler2D value) {
        return new JAXBElement<CgSampler2D>(ObjectFactory._CgSetuserTypeSampler2D_QNAME, CgSampler2D.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half1x3", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeHalf1X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf1X3_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half1x2", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeHalf1X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf1X2_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half1x4", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeHalf1X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf1X4_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half1x1", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeHalf1X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf1X1_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half3", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeHalf3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf3_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half4", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeHalf4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf4_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int4x1", scope = CgSetuserType.class)
    public JAXBElement<List<Integer>> createCgSetuserTypeInt4X1(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt4X1_QNAME, (Class<List<Integer>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half2x1", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeHalf2X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf2X1_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half2x2", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeHalf2X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf2X2_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int4x2", scope = CgSetuserType.class)
    public JAXBElement<List<Integer>> createCgSetuserTypeInt4X2(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt4X2_QNAME, (Class<List<Integer>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half2x3", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeHalf2X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf2X3_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool", scope = CgSetuserType.class)
    public JAXBElement<Boolean> createCgSetuserTypeBool(final Boolean value) {
        return new JAXBElement<Boolean>(ObjectFactory._CgSetuserTypeBool_QNAME, Boolean.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half2x4", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeHalf2X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf2X4_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int3x2", scope = CgSetuserType.class)
    public JAXBElement<List<Integer>> createCgSetuserTypeInt3X2(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt3X2_QNAME, (Class<List<Integer>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half3x1", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeHalf3X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf3X1_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int3x3", scope = CgSetuserType.class)
    public JAXBElement<List<Integer>> createCgSetuserTypeInt3X3(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt3X3_QNAME, (Class<List<Integer>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool4x1", scope = CgSetuserType.class)
    public JAXBElement<List<Boolean>> createCgSetuserTypeBool4X1(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool4X1_QNAME, (Class<List<Boolean>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half3x2", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeHalf3X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf3X2_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int3x1", scope = CgSetuserType.class)
    public JAXBElement<List<Integer>> createCgSetuserTypeInt3X1(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt3X1_QNAME, (Class<List<Integer>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half3x3", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeHalf3X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf3X3_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half3x4", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeHalf3X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf3X4_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool4x3", scope = CgSetuserType.class)
    public JAXBElement<List<Boolean>> createCgSetuserTypeBool4X3(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool4X3_QNAME, (Class<List<Boolean>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool4x2", scope = CgSetuserType.class)
    public JAXBElement<List<Boolean>> createCgSetuserTypeBool4X2(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool4X2_QNAME, (Class<List<Boolean>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "sampler3D", scope = CgSetuserType.class)
    public JAXBElement<CgSampler3D> createCgSetuserTypeSampler3D(final CgSampler3D value) {
        return new JAXBElement<CgSampler3D>(ObjectFactory._CgSetuserTypeSampler3D_QNAME, CgSampler3D.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool4x4", scope = CgSetuserType.class)
    public JAXBElement<List<Boolean>> createCgSetuserTypeBool4X4(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool4X4_QNAME, (Class<List<Boolean>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int2x2", scope = CgSetuserType.class)
    public JAXBElement<List<Integer>> createCgSetuserTypeInt2X2(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt2X2_QNAME, (Class<List<Integer>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int2x1", scope = CgSetuserType.class)
    public JAXBElement<List<Integer>> createCgSetuserTypeInt2X1(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt2X1_QNAME, (Class<List<Integer>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float", scope = CgSetuserType.class)
    public JAXBElement<Float> createCgSetuserTypeFloat(final Float value) {
        return new JAXBElement<Float>(ObjectFactory._CgSetuserTypeFloat_QNAME, Float.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed", scope = CgSetuserType.class)
    public JAXBElement<Float> createCgSetuserTypeFixed(final Float value) {
        return new JAXBElement<Float>(ObjectFactory._CgSetuserTypeFixed_QNAME, Float.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int2x4", scope = CgSetuserType.class)
    public JAXBElement<List<Integer>> createCgSetuserTypeInt2X4(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt2X4_QNAME, (Class<List<Integer>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool1", scope = CgSetuserType.class)
    public JAXBElement<Boolean> createCgSetuserTypeBool1(final Boolean value) {
        return new JAXBElement<Boolean>(ObjectFactory._CgSetuserTypeBool1_QNAME, Boolean.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float1x2", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFloat1X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat1X2_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float1x1", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFloat1X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat1X1_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int2x3", scope = CgSetuserType.class)
    public JAXBElement<List<Integer>> createCgSetuserTypeInt2X3(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt2X3_QNAME, (Class<List<Integer>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float3x2", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFloat3X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat3X2_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float3x1", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFloat3X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat3X1_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float3x4", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFloat3X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat3X4_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float3x3", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFloat3X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat3X3_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool3", scope = CgSetuserType.class)
    public JAXBElement<List<Boolean>> createCgSetuserTypeBool3(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool3_QNAME, (Class<List<Boolean>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool2", scope = CgSetuserType.class)
    public JAXBElement<List<Boolean>> createCgSetuserTypeBool2(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool2_QNAME, (Class<List<Boolean>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool4", scope = CgSetuserType.class)
    public JAXBElement<List<Boolean>> createCgSetuserTypeBool4(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool4_QNAME, (Class<List<Boolean>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "surface", scope = CgSetuserType.class)
    public JAXBElement<CgSurfaceType> createCgSetuserTypeSurface(final CgSurfaceType value) {
        return new JAXBElement<CgSurfaceType>(ObjectFactory._CgSetuserTypeSurface_QNAME, CgSurfaceType.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half4x1", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeHalf4X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf4X1_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half4x2", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeHalf4X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf4X2_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half4x3", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeHalf4X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf4X3_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float1x3", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFloat1X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat1X3_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half4x4", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeHalf4X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf4X4_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float1x4", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFloat1X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat1X4_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "array", scope = CgSetuserType.class)
    public JAXBElement<CgSetarrayType> createCgSetuserTypeArray(final CgSetarrayType value) {
        return new JAXBElement<CgSetarrayType>(ObjectFactory._CgSetuserTypeArray_QNAME, CgSetarrayType.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed1x1", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFixed1X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed1X1_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed1x2", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFixed1X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed1X2_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed1x3", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFixed1X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed1X3_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed1x4", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFixed1X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed1X4_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "samplerCUBE", scope = CgSetuserType.class)
    public JAXBElement<CgSamplerCUBE> createCgSetuserTypeSamplerCUBE(final CgSamplerCUBE value) {
        return new JAXBElement<CgSamplerCUBE>(ObjectFactory._CgSetuserTypeSamplerCUBE_QNAME, CgSamplerCUBE.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool1x3", scope = CgSetuserType.class)
    public JAXBElement<List<Boolean>> createCgSetuserTypeBool1X3(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool1X3_QNAME, (Class<List<Boolean>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool1x4", scope = CgSetuserType.class)
    public JAXBElement<List<Boolean>> createCgSetuserTypeBool1X4(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool1X4_QNAME, (Class<List<Boolean>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool1x1", scope = CgSetuserType.class)
    public JAXBElement<List<Boolean>> createCgSetuserTypeBool1X1(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool1X1_QNAME, (Class<List<Boolean>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool1x2", scope = CgSetuserType.class)
    public JAXBElement<List<Boolean>> createCgSetuserTypeBool1X2(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool1X2_QNAME, (Class<List<Boolean>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "usertype", scope = CgSetuserType.class)
    public JAXBElement<CgSetuserType> createCgSetuserTypeUsertype(final CgSetuserType value) {
        return new JAXBElement<CgSetuserType>(ObjectFactory._CgSetuserTypeUsertype_QNAME, CgSetuserType.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool3x3", scope = CgSetuserType.class)
    public JAXBElement<List<Boolean>> createCgSetuserTypeBool3X3(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool3X3_QNAME, (Class<List<Boolean>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool3x4", scope = CgSetuserType.class)
    public JAXBElement<List<Boolean>> createCgSetuserTypeBool3X4(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool3X4_QNAME, (Class<List<Boolean>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool3x1", scope = CgSetuserType.class)
    public JAXBElement<List<Boolean>> createCgSetuserTypeBool3X1(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool3X1_QNAME, (Class<List<Boolean>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool3x2", scope = CgSetuserType.class)
    public JAXBElement<List<Boolean>> createCgSetuserTypeBool3X2(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool3X2_QNAME, (Class<List<Boolean>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "samplerDEPTH", scope = CgSetuserType.class)
    public JAXBElement<CgSamplerDEPTH> createCgSetuserTypeSamplerDEPTH(final CgSamplerDEPTH value) {
        return new JAXBElement<CgSamplerDEPTH>(ObjectFactory._CgSetuserTypeSamplerDEPTH_QNAME, CgSamplerDEPTH.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed4x4", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFixed4X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed4X4_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed4x3", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFixed4X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed4X3_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed4x2", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFixed4X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed4X2_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed4x1", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFixed4X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed4X1_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float4x4", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFloat4X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat4X4_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "string", scope = CgSetuserType.class)
    public JAXBElement<String> createCgSetuserTypeString(final String value) {
        return new JAXBElement<String>(ObjectFactory._CgSetuserTypeString_QNAME, String.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float4x2", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFloat4X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat4X2_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float4x3", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFloat4X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat4X3_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float4x1", scope = CgSetuserType.class)
    public JAXBElement<List<Float>> createCgSetuserTypeFloat4X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat4X1_QNAME, (Class<List<Float>>)List.class, CgSetuserType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "profile_GLSL", substitutionHeadNamespace = "http://www.collada.org/2005/11/COLLADASchema", substitutionHeadName = "fx_profile_abstract")
    public JAXBElement<ProfileGLSL> createProfileGLSL(final ProfileGLSL value) {
        return new JAXBElement<ProfileGLSL>(ObjectFactory._ProfileGLSL_QNAME, ProfileGLSL.class, null, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "profile_CG", substitutionHeadNamespace = "http://www.collada.org/2005/11/COLLADASchema", substitutionHeadName = "fx_profile_abstract")
    public JAXBElement<ProfileCG> createProfileCG(final ProfileCG value) {
        return new JAXBElement<ProfileCG>(ObjectFactory._ProfileCG_QNAME, ProfileCG.class, null, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fx_profile_abstract")
    public JAXBElement<Object> createFxProfileAbstract(final Object value) {
        return new JAXBElement<Object>(ObjectFactory._FxProfileAbstract_QNAME, Object.class, null, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "instance_camera")
    public JAXBElement<InstanceWithExtra> createInstanceCamera(final InstanceWithExtra value) {
        return new JAXBElement<InstanceWithExtra>(ObjectFactory._InstanceCamera_QNAME, InstanceWithExtra.class, null, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "instance_light")
    public JAXBElement<InstanceWithExtra> createInstanceLight(final InstanceWithExtra value) {
        return new JAXBElement<InstanceWithExtra>(ObjectFactory._InstanceLight_QNAME, InstanceWithExtra.class, null, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "profile_GLES", substitutionHeadNamespace = "http://www.collada.org/2005/11/COLLADASchema", substitutionHeadName = "fx_profile_abstract")
    public JAXBElement<ProfileGLES> createProfileGLES(final ProfileGLES value) {
        return new JAXBElement<ProfileGLES>(ObjectFactory._ProfileGLES_QNAME, ProfileGLES.class, null, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "scale")
    public JAXBElement<TargetableFloat3> createScale(final TargetableFloat3 value) {
        return new JAXBElement<TargetableFloat3>(ObjectFactory._Scale_QNAME, TargetableFloat3.class, null, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "gl_hook_abstract")
    public JAXBElement<Object> createGlHookAbstract(final Object value) {
        return new JAXBElement<Object>(ObjectFactory._GlHookAbstract_QNAME, Object.class, null, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "instance_force_field")
    public JAXBElement<InstanceWithExtra> createInstanceForceField(final InstanceWithExtra value) {
        return new JAXBElement<InstanceWithExtra>(ObjectFactory._InstanceForceField_QNAME, InstanceWithExtra.class, null, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "translate")
    public JAXBElement<TargetableFloat3> createTranslate(final TargetableFloat3 value) {
        return new JAXBElement<TargetableFloat3>(ObjectFactory._Translate_QNAME, TargetableFloat3.class, null, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "instance_physics_material")
    public JAXBElement<InstanceWithExtra> createInstancePhysicsMaterial(final InstanceWithExtra value) {
        return new JAXBElement<InstanceWithExtra>(ObjectFactory._InstancePhysicsMaterial_QNAME, InstanceWithExtra.class, null, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "p")
    public JAXBElement<List<BigInteger>> createP(final List<BigInteger> value) {
        return new JAXBElement<List<BigInteger>>(ObjectFactory._P_QNAME, (Class<List<BigInteger>>)List.class, null, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "instance_node")
    public JAXBElement<InstanceWithExtra> createInstanceNode(final InstanceWithExtra value) {
        return new JAXBElement<InstanceWithExtra>(ObjectFactory._InstanceNode_QNAME, InstanceWithExtra.class, null, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "profile_COMMON", substitutionHeadNamespace = "http://www.collada.org/2005/11/COLLADASchema", substitutionHeadName = "fx_profile_abstract")
    public JAXBElement<ProfileCOMMON> createProfileCOMMON(final ProfileCOMMON value) {
        return new JAXBElement<ProfileCOMMON>(ObjectFactory._ProfileCOMMON_QNAME, ProfileCOMMON.class, null, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "h", scope = Polygons.Ph.class)
    public JAXBElement<List<BigInteger>> createPolygonsPhH(final List<BigInteger> value) {
        return new JAXBElement<List<BigInteger>>(ObjectFactory._PolygonsPhH_QNAME, (Class<List<BigInteger>>)List.class, Polygons.Ph.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float2x2", scope = GlslSetarrayType.class)
    public JAXBElement<List<Float>> createGlslSetarrayTypeFloat2X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat2X2_QNAME, (Class<List<Float>>)List.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float", scope = GlslSetarrayType.class)
    public JAXBElement<Float> createGlslSetarrayTypeFloat(final Float value) {
        return new JAXBElement<Float>(ObjectFactory._CgSetuserTypeFloat_QNAME, Float.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float4", scope = GlslSetarrayType.class)
    public JAXBElement<List<Float>> createGlslSetarrayTypeFloat4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat4_QNAME, (Class<List<Float>>)List.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int2", scope = GlslSetarrayType.class)
    public JAXBElement<List<Integer>> createGlslSetarrayTypeInt2(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt2_QNAME, (Class<List<Integer>>)List.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int3", scope = GlslSetarrayType.class)
    public JAXBElement<List<Integer>> createGlslSetarrayTypeInt3(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt3_QNAME, (Class<List<Integer>>)List.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int4", scope = GlslSetarrayType.class)
    public JAXBElement<List<Integer>> createGlslSetarrayTypeInt4(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt4_QNAME, (Class<List<Integer>>)List.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "samplerCUBE", scope = GlslSetarrayType.class)
    public JAXBElement<GlSamplerCUBE> createGlslSetarrayTypeSamplerCUBE(final GlSamplerCUBE value) {
        return new JAXBElement<GlSamplerCUBE>(ObjectFactory._CgSetuserTypeSamplerCUBE_QNAME, GlSamplerCUBE.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "enum", scope = GlslSetarrayType.class)
    public JAXBElement<String> createGlslSetarrayTypeEnum(final String value) {
        return new JAXBElement<String>(ObjectFactory._CgSetuserTypeEnum_QNAME, String.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float3x3", scope = GlslSetarrayType.class)
    public JAXBElement<List<Float>> createGlslSetarrayTypeFloat3X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat3X3_QNAME, (Class<List<Float>>)List.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int", scope = GlslSetarrayType.class)
    public JAXBElement<Integer> createGlslSetarrayTypeInt(final Integer value) {
        return new JAXBElement<Integer>(ObjectFactory._CgSetuserTypeInt_QNAME, Integer.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float2", scope = GlslSetarrayType.class)
    public JAXBElement<List<Float>> createGlslSetarrayTypeFloat2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat2_QNAME, (Class<List<Float>>)List.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool3", scope = GlslSetarrayType.class)
    public JAXBElement<List<Boolean>> createGlslSetarrayTypeBool3(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool3_QNAME, (Class<List<Boolean>>)List.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool2", scope = GlslSetarrayType.class)
    public JAXBElement<List<Boolean>> createGlslSetarrayTypeBool2(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool2_QNAME, (Class<List<Boolean>>)List.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float3", scope = GlslSetarrayType.class)
    public JAXBElement<List<Float>> createGlslSetarrayTypeFloat3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat3_QNAME, (Class<List<Float>>)List.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool4", scope = GlslSetarrayType.class)
    public JAXBElement<List<Boolean>> createGlslSetarrayTypeBool4(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool4_QNAME, (Class<List<Boolean>>)List.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "surface", scope = GlslSetarrayType.class)
    public JAXBElement<GlslSurfaceType> createGlslSetarrayTypeSurface(final GlslSurfaceType value) {
        return new JAXBElement<GlslSurfaceType>(ObjectFactory._CgSetuserTypeSurface_QNAME, GlslSurfaceType.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "samplerDEPTH", scope = GlslSetarrayType.class)
    public JAXBElement<GlSamplerDEPTH> createGlslSetarrayTypeSamplerDEPTH(final GlSamplerDEPTH value) {
        return new JAXBElement<GlSamplerDEPTH>(ObjectFactory._CgSetuserTypeSamplerDEPTH_QNAME, GlSamplerDEPTH.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "array", scope = GlslSetarrayType.class)
    public JAXBElement<GlslSetarrayType> createGlslSetarrayTypeArray(final GlslSetarrayType value) {
        return new JAXBElement<GlslSetarrayType>(ObjectFactory._CgSetuserTypeArray_QNAME, GlslSetarrayType.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "samplerRECT", scope = GlslSetarrayType.class)
    public JAXBElement<GlSamplerRECT> createGlslSetarrayTypeSamplerRECT(final GlSamplerRECT value) {
        return new JAXBElement<GlSamplerRECT>(ObjectFactory._CgSetuserTypeSamplerRECT_QNAME, GlSamplerRECT.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool", scope = GlslSetarrayType.class)
    public JAXBElement<Boolean> createGlslSetarrayTypeBool(final Boolean value) {
        return new JAXBElement<Boolean>(ObjectFactory._CgSetuserTypeBool_QNAME, Boolean.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "sampler1D", scope = GlslSetarrayType.class)
    public JAXBElement<GlSampler1D> createGlslSetarrayTypeSampler1D(final GlSampler1D value) {
        return new JAXBElement<GlSampler1D>(ObjectFactory._CgSetuserTypeSampler1D_QNAME, GlSampler1D.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float4x4", scope = GlslSetarrayType.class)
    public JAXBElement<List<Float>> createGlslSetarrayTypeFloat4X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat4X4_QNAME, (Class<List<Float>>)List.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "sampler3D", scope = GlslSetarrayType.class)
    public JAXBElement<GlSampler3D> createGlslSetarrayTypeSampler3D(final GlSampler3D value) {
        return new JAXBElement<GlSampler3D>(ObjectFactory._CgSetuserTypeSampler3D_QNAME, GlSampler3D.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "sampler2D", scope = GlslSetarrayType.class)
    public JAXBElement<GlSampler2D> createGlslSetarrayTypeSampler2D(final GlSampler2D value) {
        return new JAXBElement<GlSampler2D>(ObjectFactory._CgSetuserTypeSampler2D_QNAME, GlSampler2D.class, GlslSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float2x2", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFloat2X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat2X2_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float2x3", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFloat2X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat2X3_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float2x4", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFloat2X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat2X4_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float4", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFloat4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat4_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half", scope = CgSetarrayType.class)
    public JAXBElement<Float> createCgSetarrayTypeHalf(final Float value) {
        return new JAXBElement<Float>(ObjectFactory._CgSetuserTypeHalf_QNAME, Float.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float2", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFloat2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat2_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float3", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFloat3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat3_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float1", scope = CgSetarrayType.class)
    public JAXBElement<Float> createCgSetarrayTypeFloat1(final Float value) {
        return new JAXBElement<Float>(ObjectFactory._CgSetuserTypeFloat1_QNAME, Float.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int1x1", scope = CgSetarrayType.class)
    public JAXBElement<List<Integer>> createCgSetarrayTypeInt1X1(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt1X1_QNAME, (Class<List<Integer>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int1x3", scope = CgSetarrayType.class)
    public JAXBElement<List<Integer>> createCgSetarrayTypeInt1X3(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt1X3_QNAME, (Class<List<Integer>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int1x2", scope = CgSetarrayType.class)
    public JAXBElement<List<Integer>> createCgSetarrayTypeInt1X2(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt1X2_QNAME, (Class<List<Integer>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float2x1", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFloat2X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat2X1_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int1x4", scope = CgSetarrayType.class)
    public JAXBElement<List<Integer>> createCgSetarrayTypeInt1X4(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt1X4_QNAME, (Class<List<Integer>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int1", scope = CgSetarrayType.class)
    public JAXBElement<Integer> createCgSetarrayTypeInt1(final Integer value) {
        return new JAXBElement<Integer>(ObjectFactory._CgSetuserTypeInt1_QNAME, Integer.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int2", scope = CgSetarrayType.class)
    public JAXBElement<List<Integer>> createCgSetarrayTypeInt2(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt2_QNAME, (Class<List<Integer>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int3", scope = CgSetarrayType.class)
    public JAXBElement<List<Integer>> createCgSetarrayTypeInt3(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt3_QNAME, (Class<List<Integer>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int4", scope = CgSetarrayType.class)
    public JAXBElement<List<Integer>> createCgSetarrayTypeInt4(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt4_QNAME, (Class<List<Integer>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "enum", scope = CgSetarrayType.class)
    public JAXBElement<String> createCgSetarrayTypeEnum(final String value) {
        return new JAXBElement<String>(ObjectFactory._CgSetuserTypeEnum_QNAME, String.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int", scope = CgSetarrayType.class)
    public JAXBElement<Integer> createCgSetarrayTypeInt(final Integer value) {
        return new JAXBElement<Integer>(ObjectFactory._CgSetuserTypeInt_QNAME, Integer.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed2x1", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFixed2X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed2X1_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "samplerRECT", scope = CgSetarrayType.class)
    public JAXBElement<CgSamplerRECT> createCgSetarrayTypeSamplerRECT(final CgSamplerRECT value) {
        return new JAXBElement<CgSamplerRECT>(ObjectFactory._CgSetuserTypeSamplerRECT_QNAME, CgSamplerRECT.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "sampler1D", scope = CgSetarrayType.class)
    public JAXBElement<CgSampler1D> createCgSetarrayTypeSampler1D(final CgSampler1D value) {
        return new JAXBElement<CgSampler1D>(ObjectFactory._CgSetuserTypeSampler1D_QNAME, CgSampler1D.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed4", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFixed4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed4_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed2x4", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFixed2X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed2X4_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed2x3", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFixed2X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed2X3_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed2x2", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFixed2X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed2X2_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed1", scope = CgSetarrayType.class)
    public JAXBElement<Float> createCgSetarrayTypeFixed1(final Float value) {
        return new JAXBElement<Float>(ObjectFactory._CgSetuserTypeFixed1_QNAME, Float.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed2", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFixed2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed2_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed3", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFixed3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed3_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed3x1", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFixed3X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed3X1_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int3x4", scope = CgSetarrayType.class)
    public JAXBElement<List<Integer>> createCgSetarrayTypeInt3X4(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt3X4_QNAME, (Class<List<Integer>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed3x2", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFixed3X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed3X2_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed3x3", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFixed3X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed3X3_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed3x4", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFixed3X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed3X4_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int4x4", scope = CgSetarrayType.class)
    public JAXBElement<List<Integer>> createCgSetarrayTypeInt4X4(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt4X4_QNAME, (Class<List<Integer>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int4x3", scope = CgSetarrayType.class)
    public JAXBElement<List<Integer>> createCgSetarrayTypeInt4X3(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt4X3_QNAME, (Class<List<Integer>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half2", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeHalf2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf2_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half1", scope = CgSetarrayType.class)
    public JAXBElement<Float> createCgSetarrayTypeHalf1(final Float value) {
        return new JAXBElement<Float>(ObjectFactory._CgSetuserTypeHalf1_QNAME, Float.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool2x3", scope = CgSetarrayType.class)
    public JAXBElement<List<Boolean>> createCgSetarrayTypeBool2X3(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool2X3_QNAME, (Class<List<Boolean>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool2x2", scope = CgSetarrayType.class)
    public JAXBElement<List<Boolean>> createCgSetarrayTypeBool2X2(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool2X2_QNAME, (Class<List<Boolean>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool2x1", scope = CgSetarrayType.class)
    public JAXBElement<List<Boolean>> createCgSetarrayTypeBool2X1(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool2X1_QNAME, (Class<List<Boolean>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool2x4", scope = CgSetarrayType.class)
    public JAXBElement<List<Boolean>> createCgSetarrayTypeBool2X4(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool2X4_QNAME, (Class<List<Boolean>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "sampler2D", scope = CgSetarrayType.class)
    public JAXBElement<CgSampler2D> createCgSetarrayTypeSampler2D(final CgSampler2D value) {
        return new JAXBElement<CgSampler2D>(ObjectFactory._CgSetuserTypeSampler2D_QNAME, CgSampler2D.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half1x3", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeHalf1X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf1X3_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half1x2", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeHalf1X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf1X2_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half1x4", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeHalf1X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf1X4_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half1x1", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeHalf1X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf1X1_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half3", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeHalf3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf3_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half4", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeHalf4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf4_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half2x1", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeHalf2X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf2X1_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int4x1", scope = CgSetarrayType.class)
    public JAXBElement<List<Integer>> createCgSetarrayTypeInt4X1(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt4X1_QNAME, (Class<List<Integer>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int4x2", scope = CgSetarrayType.class)
    public JAXBElement<List<Integer>> createCgSetarrayTypeInt4X2(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt4X2_QNAME, (Class<List<Integer>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half2x2", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeHalf2X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf2X2_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half2x3", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeHalf2X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf2X3_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half2x4", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeHalf2X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf2X4_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool", scope = CgSetarrayType.class)
    public JAXBElement<Boolean> createCgSetarrayTypeBool(final Boolean value) {
        return new JAXBElement<Boolean>(ObjectFactory._CgSetuserTypeBool_QNAME, Boolean.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int3x2", scope = CgSetarrayType.class)
    public JAXBElement<List<Integer>> createCgSetarrayTypeInt3X2(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt3X2_QNAME, (Class<List<Integer>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int3x3", scope = CgSetarrayType.class)
    public JAXBElement<List<Integer>> createCgSetarrayTypeInt3X3(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt3X3_QNAME, (Class<List<Integer>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half3x1", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeHalf3X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf3X1_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half3x2", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeHalf3X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf3X2_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool4x1", scope = CgSetarrayType.class)
    public JAXBElement<List<Boolean>> createCgSetarrayTypeBool4X1(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool4X1_QNAME, (Class<List<Boolean>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int3x1", scope = CgSetarrayType.class)
    public JAXBElement<List<Integer>> createCgSetarrayTypeInt3X1(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt3X1_QNAME, (Class<List<Integer>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half3x3", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeHalf3X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf3X3_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool4x3", scope = CgSetarrayType.class)
    public JAXBElement<List<Boolean>> createCgSetarrayTypeBool4X3(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool4X3_QNAME, (Class<List<Boolean>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half3x4", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeHalf3X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf3X4_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool4x2", scope = CgSetarrayType.class)
    public JAXBElement<List<Boolean>> createCgSetarrayTypeBool4X2(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool4X2_QNAME, (Class<List<Boolean>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "sampler3D", scope = CgSetarrayType.class)
    public JAXBElement<CgSampler3D> createCgSetarrayTypeSampler3D(final CgSampler3D value) {
        return new JAXBElement<CgSampler3D>(ObjectFactory._CgSetuserTypeSampler3D_QNAME, CgSampler3D.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool4x4", scope = CgSetarrayType.class)
    public JAXBElement<List<Boolean>> createCgSetarrayTypeBool4X4(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool4X4_QNAME, (Class<List<Boolean>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int2x2", scope = CgSetarrayType.class)
    public JAXBElement<List<Integer>> createCgSetarrayTypeInt2X2(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt2X2_QNAME, (Class<List<Integer>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed", scope = CgSetarrayType.class)
    public JAXBElement<Float> createCgSetarrayTypeFixed(final Float value) {
        return new JAXBElement<Float>(ObjectFactory._CgSetuserTypeFixed_QNAME, Float.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int2x1", scope = CgSetarrayType.class)
    public JAXBElement<List<Integer>> createCgSetarrayTypeInt2X1(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt2X1_QNAME, (Class<List<Integer>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float", scope = CgSetarrayType.class)
    public JAXBElement<Float> createCgSetarrayTypeFloat(final Float value) {
        return new JAXBElement<Float>(ObjectFactory._CgSetuserTypeFloat_QNAME, Float.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool1", scope = CgSetarrayType.class)
    public JAXBElement<Boolean> createCgSetarrayTypeBool1(final Boolean value) {
        return new JAXBElement<Boolean>(ObjectFactory._CgSetuserTypeBool1_QNAME, Boolean.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float1x2", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFloat1X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat1X2_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int2x4", scope = CgSetarrayType.class)
    public JAXBElement<List<Integer>> createCgSetarrayTypeInt2X4(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt2X4_QNAME, (Class<List<Integer>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int2x3", scope = CgSetarrayType.class)
    public JAXBElement<List<Integer>> createCgSetarrayTypeInt2X3(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt2X3_QNAME, (Class<List<Integer>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float1x1", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFloat1X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat1X1_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float3x2", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFloat3X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat3X2_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float3x1", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFloat3X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat3X1_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float3x4", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFloat3X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat3X4_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float3x3", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFloat3X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat3X3_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool3", scope = CgSetarrayType.class)
    public JAXBElement<List<Boolean>> createCgSetarrayTypeBool3(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool3_QNAME, (Class<List<Boolean>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool2", scope = CgSetarrayType.class)
    public JAXBElement<List<Boolean>> createCgSetarrayTypeBool2(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool2_QNAME, (Class<List<Boolean>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "surface", scope = CgSetarrayType.class)
    public JAXBElement<CgSurfaceType> createCgSetarrayTypeSurface(final CgSurfaceType value) {
        return new JAXBElement<CgSurfaceType>(ObjectFactory._CgSetuserTypeSurface_QNAME, CgSurfaceType.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool4", scope = CgSetarrayType.class)
    public JAXBElement<List<Boolean>> createCgSetarrayTypeBool4(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool4_QNAME, (Class<List<Boolean>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half4x1", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeHalf4X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf4X1_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half4x2", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeHalf4X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf4X2_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half4x3", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeHalf4X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf4X3_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float1x3", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFloat1X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat1X3_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float1x4", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFloat1X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat1X4_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half4x4", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeHalf4X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf4X4_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "array", scope = CgSetarrayType.class)
    public JAXBElement<CgSetarrayType> createCgSetarrayTypeArray(final CgSetarrayType value) {
        return new JAXBElement<CgSetarrayType>(ObjectFactory._CgSetuserTypeArray_QNAME, CgSetarrayType.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed1x1", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFixed1X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed1X1_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed1x2", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFixed1X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed1X2_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed1x3", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFixed1X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed1X3_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed1x4", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFixed1X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed1X4_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "samplerCUBE", scope = CgSetarrayType.class)
    public JAXBElement<CgSamplerCUBE> createCgSetarrayTypeSamplerCUBE(final CgSamplerCUBE value) {
        return new JAXBElement<CgSamplerCUBE>(ObjectFactory._CgSetuserTypeSamplerCUBE_QNAME, CgSamplerCUBE.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool1x3", scope = CgSetarrayType.class)
    public JAXBElement<List<Boolean>> createCgSetarrayTypeBool1X3(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool1X3_QNAME, (Class<List<Boolean>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool1x4", scope = CgSetarrayType.class)
    public JAXBElement<List<Boolean>> createCgSetarrayTypeBool1X4(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool1X4_QNAME, (Class<List<Boolean>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool1x1", scope = CgSetarrayType.class)
    public JAXBElement<List<Boolean>> createCgSetarrayTypeBool1X1(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool1X1_QNAME, (Class<List<Boolean>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool1x2", scope = CgSetarrayType.class)
    public JAXBElement<List<Boolean>> createCgSetarrayTypeBool1X2(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool1X2_QNAME, (Class<List<Boolean>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "usertype", scope = CgSetarrayType.class)
    public JAXBElement<CgSetuserType> createCgSetarrayTypeUsertype(final CgSetuserType value) {
        return new JAXBElement<CgSetuserType>(ObjectFactory._CgSetuserTypeUsertype_QNAME, CgSetuserType.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool3x3", scope = CgSetarrayType.class)
    public JAXBElement<List<Boolean>> createCgSetarrayTypeBool3X3(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool3X3_QNAME, (Class<List<Boolean>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool3x4", scope = CgSetarrayType.class)
    public JAXBElement<List<Boolean>> createCgSetarrayTypeBool3X4(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool3X4_QNAME, (Class<List<Boolean>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool3x1", scope = CgSetarrayType.class)
    public JAXBElement<List<Boolean>> createCgSetarrayTypeBool3X1(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool3X1_QNAME, (Class<List<Boolean>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool3x2", scope = CgSetarrayType.class)
    public JAXBElement<List<Boolean>> createCgSetarrayTypeBool3X2(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool3X2_QNAME, (Class<List<Boolean>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "samplerDEPTH", scope = CgSetarrayType.class)
    public JAXBElement<CgSamplerDEPTH> createCgSetarrayTypeSamplerDEPTH(final CgSamplerDEPTH value) {
        return new JAXBElement<CgSamplerDEPTH>(ObjectFactory._CgSetuserTypeSamplerDEPTH_QNAME, CgSamplerDEPTH.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed4x4", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFixed4X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed4X4_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed4x3", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFixed4X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed4X3_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed4x2", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFixed4X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed4X2_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed4x1", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFixed4X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed4X1_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float4x4", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFloat4X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat4X4_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "string", scope = CgSetarrayType.class)
    public JAXBElement<String> createCgSetarrayTypeString(final String value) {
        return new JAXBElement<String>(ObjectFactory._CgSetuserTypeString_QNAME, String.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float4x2", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFloat4X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat4X2_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float4x3", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFloat4X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat4X3_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float4x1", scope = CgSetarrayType.class)
    public JAXBElement<List<Float>> createCgSetarrayTypeFloat4X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat4X1_QNAME, (Class<List<Float>>)List.class, CgSetarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "layer", scope = VisualScene.EvaluateScene.Render.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createVisualSceneEvaluateSceneRenderLayer(final String value) {
        return new JAXBElement<String>(ObjectFactory._VisualSceneEvaluateSceneRenderLayer_QNAME, String.class, VisualScene.EvaluateScene.Render.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float2x2", scope = GlslNewarrayType.class)
    public JAXBElement<List<Float>> createGlslNewarrayTypeFloat2X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat2X2_QNAME, (Class<List<Float>>)List.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float", scope = GlslNewarrayType.class)
    public JAXBElement<Float> createGlslNewarrayTypeFloat(final Float value) {
        return new JAXBElement<Float>(ObjectFactory._CgSetuserTypeFloat_QNAME, Float.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float4", scope = GlslNewarrayType.class)
    public JAXBElement<List<Float>> createGlslNewarrayTypeFloat4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat4_QNAME, (Class<List<Float>>)List.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int2", scope = GlslNewarrayType.class)
    public JAXBElement<List<Integer>> createGlslNewarrayTypeInt2(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt2_QNAME, (Class<List<Integer>>)List.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int3", scope = GlslNewarrayType.class)
    public JAXBElement<List<Integer>> createGlslNewarrayTypeInt3(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt3_QNAME, (Class<List<Integer>>)List.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int4", scope = GlslNewarrayType.class)
    public JAXBElement<List<Integer>> createGlslNewarrayTypeInt4(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt4_QNAME, (Class<List<Integer>>)List.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "samplerCUBE", scope = GlslNewarrayType.class)
    public JAXBElement<GlSamplerCUBE> createGlslNewarrayTypeSamplerCUBE(final GlSamplerCUBE value) {
        return new JAXBElement<GlSamplerCUBE>(ObjectFactory._CgSetuserTypeSamplerCUBE_QNAME, GlSamplerCUBE.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "enum", scope = GlslNewarrayType.class)
    public JAXBElement<String> createGlslNewarrayTypeEnum(final String value) {
        return new JAXBElement<String>(ObjectFactory._CgSetuserTypeEnum_QNAME, String.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float3x3", scope = GlslNewarrayType.class)
    public JAXBElement<List<Float>> createGlslNewarrayTypeFloat3X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat3X3_QNAME, (Class<List<Float>>)List.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int", scope = GlslNewarrayType.class)
    public JAXBElement<Integer> createGlslNewarrayTypeInt(final Integer value) {
        return new JAXBElement<Integer>(ObjectFactory._CgSetuserTypeInt_QNAME, Integer.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float2", scope = GlslNewarrayType.class)
    public JAXBElement<List<Float>> createGlslNewarrayTypeFloat2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat2_QNAME, (Class<List<Float>>)List.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool3", scope = GlslNewarrayType.class)
    public JAXBElement<List<Boolean>> createGlslNewarrayTypeBool3(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool3_QNAME, (Class<List<Boolean>>)List.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float3", scope = GlslNewarrayType.class)
    public JAXBElement<List<Float>> createGlslNewarrayTypeFloat3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat3_QNAME, (Class<List<Float>>)List.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool2", scope = GlslNewarrayType.class)
    public JAXBElement<List<Boolean>> createGlslNewarrayTypeBool2(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool2_QNAME, (Class<List<Boolean>>)List.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "samplerDEPTH", scope = GlslNewarrayType.class)
    public JAXBElement<GlSamplerDEPTH> createGlslNewarrayTypeSamplerDEPTH(final GlSamplerDEPTH value) {
        return new JAXBElement<GlSamplerDEPTH>(ObjectFactory._CgSetuserTypeSamplerDEPTH_QNAME, GlSamplerDEPTH.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool4", scope = GlslNewarrayType.class)
    public JAXBElement<List<Boolean>> createGlslNewarrayTypeBool4(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool4_QNAME, (Class<List<Boolean>>)List.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "surface", scope = GlslNewarrayType.class)
    public JAXBElement<GlslSurfaceType> createGlslNewarrayTypeSurface(final GlslSurfaceType value) {
        return new JAXBElement<GlslSurfaceType>(ObjectFactory._CgSetuserTypeSurface_QNAME, GlslSurfaceType.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "array", scope = GlslNewarrayType.class)
    public JAXBElement<GlslNewarrayType> createGlslNewarrayTypeArray(final GlslNewarrayType value) {
        return new JAXBElement<GlslNewarrayType>(ObjectFactory._CgSetuserTypeArray_QNAME, GlslNewarrayType.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "samplerRECT", scope = GlslNewarrayType.class)
    public JAXBElement<GlSamplerRECT> createGlslNewarrayTypeSamplerRECT(final GlSamplerRECT value) {
        return new JAXBElement<GlSamplerRECT>(ObjectFactory._CgSetuserTypeSamplerRECT_QNAME, GlSamplerRECT.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool", scope = GlslNewarrayType.class)
    public JAXBElement<Boolean> createGlslNewarrayTypeBool(final Boolean value) {
        return new JAXBElement<Boolean>(ObjectFactory._CgSetuserTypeBool_QNAME, Boolean.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "sampler1D", scope = GlslNewarrayType.class)
    public JAXBElement<GlSampler1D> createGlslNewarrayTypeSampler1D(final GlSampler1D value) {
        return new JAXBElement<GlSampler1D>(ObjectFactory._CgSetuserTypeSampler1D_QNAME, GlSampler1D.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float4x4", scope = GlslNewarrayType.class)
    public JAXBElement<List<Float>> createGlslNewarrayTypeFloat4X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat4X4_QNAME, (Class<List<Float>>)List.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "sampler3D", scope = GlslNewarrayType.class)
    public JAXBElement<GlSampler3D> createGlslNewarrayTypeSampler3D(final GlSampler3D value) {
        return new JAXBElement<GlSampler3D>(ObjectFactory._CgSetuserTypeSampler3D_QNAME, GlSampler3D.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "sampler2D", scope = GlslNewarrayType.class)
    public JAXBElement<GlSampler2D> createGlslNewarrayTypeSampler2D(final GlSampler2D value) {
        return new JAXBElement<GlSampler2D>(ObjectFactory._CgSetuserTypeSampler2D_QNAME, GlSampler2D.class, GlslNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float2x2", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFloat2X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat2X2_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float2x3", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFloat2X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat2X3_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float2x4", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFloat2X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat2X4_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float4", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFloat4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat4_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half", scope = CgNewarrayType.class)
    public JAXBElement<Float> createCgNewarrayTypeHalf(final Float value) {
        return new JAXBElement<Float>(ObjectFactory._CgSetuserTypeHalf_QNAME, Float.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float2", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFloat2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat2_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float3", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFloat3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat3_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float1", scope = CgNewarrayType.class)
    public JAXBElement<Float> createCgNewarrayTypeFloat1(final Float value) {
        return new JAXBElement<Float>(ObjectFactory._CgSetuserTypeFloat1_QNAME, Float.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int1x1", scope = CgNewarrayType.class)
    public JAXBElement<List<Integer>> createCgNewarrayTypeInt1X1(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt1X1_QNAME, (Class<List<Integer>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int1x3", scope = CgNewarrayType.class)
    public JAXBElement<List<Integer>> createCgNewarrayTypeInt1X3(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt1X3_QNAME, (Class<List<Integer>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int1x2", scope = CgNewarrayType.class)
    public JAXBElement<List<Integer>> createCgNewarrayTypeInt1X2(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt1X2_QNAME, (Class<List<Integer>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float2x1", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFloat2X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat2X1_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int1x4", scope = CgNewarrayType.class)
    public JAXBElement<List<Integer>> createCgNewarrayTypeInt1X4(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt1X4_QNAME, (Class<List<Integer>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int1", scope = CgNewarrayType.class)
    public JAXBElement<Integer> createCgNewarrayTypeInt1(final Integer value) {
        return new JAXBElement<Integer>(ObjectFactory._CgSetuserTypeInt1_QNAME, Integer.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int2", scope = CgNewarrayType.class)
    public JAXBElement<List<Integer>> createCgNewarrayTypeInt2(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt2_QNAME, (Class<List<Integer>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int3", scope = CgNewarrayType.class)
    public JAXBElement<List<Integer>> createCgNewarrayTypeInt3(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt3_QNAME, (Class<List<Integer>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int4", scope = CgNewarrayType.class)
    public JAXBElement<List<Integer>> createCgNewarrayTypeInt4(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt4_QNAME, (Class<List<Integer>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "enum", scope = CgNewarrayType.class)
    public JAXBElement<String> createCgNewarrayTypeEnum(final String value) {
        return new JAXBElement<String>(ObjectFactory._CgSetuserTypeEnum_QNAME, String.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int", scope = CgNewarrayType.class)
    public JAXBElement<Integer> createCgNewarrayTypeInt(final Integer value) {
        return new JAXBElement<Integer>(ObjectFactory._CgSetuserTypeInt_QNAME, Integer.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed2x1", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFixed2X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed2X1_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "connect_param", scope = CgNewarrayType.class)
    public JAXBElement<CgConnectParam> createCgNewarrayTypeConnectParam(final CgConnectParam value) {
        return new JAXBElement<CgConnectParam>(ObjectFactory._CgSetuserTypeConnectParam_QNAME, CgConnectParam.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "samplerRECT", scope = CgNewarrayType.class)
    public JAXBElement<CgSamplerRECT> createCgNewarrayTypeSamplerRECT(final CgSamplerRECT value) {
        return new JAXBElement<CgSamplerRECT>(ObjectFactory._CgSetuserTypeSamplerRECT_QNAME, CgSamplerRECT.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "sampler1D", scope = CgNewarrayType.class)
    public JAXBElement<CgSampler1D> createCgNewarrayTypeSampler1D(final CgSampler1D value) {
        return new JAXBElement<CgSampler1D>(ObjectFactory._CgSetuserTypeSampler1D_QNAME, CgSampler1D.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed4", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFixed4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed4_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed2x4", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFixed2X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed2X4_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed2x3", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFixed2X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed2X3_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed2x2", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFixed2X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed2X2_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed1", scope = CgNewarrayType.class)
    public JAXBElement<Float> createCgNewarrayTypeFixed1(final Float value) {
        return new JAXBElement<Float>(ObjectFactory._CgSetuserTypeFixed1_QNAME, Float.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed2", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFixed2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed2_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed3", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFixed3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed3_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed3x1", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFixed3X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed3X1_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed3x2", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFixed3X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed3X2_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int3x4", scope = CgNewarrayType.class)
    public JAXBElement<List<Integer>> createCgNewarrayTypeInt3X4(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt3X4_QNAME, (Class<List<Integer>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed3x3", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFixed3X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed3X3_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed3x4", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFixed3X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed3X4_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int4x4", scope = CgNewarrayType.class)
    public JAXBElement<List<Integer>> createCgNewarrayTypeInt4X4(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt4X4_QNAME, (Class<List<Integer>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int4x3", scope = CgNewarrayType.class)
    public JAXBElement<List<Integer>> createCgNewarrayTypeInt4X3(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt4X3_QNAME, (Class<List<Integer>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half2", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeHalf2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf2_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half1", scope = CgNewarrayType.class)
    public JAXBElement<Float> createCgNewarrayTypeHalf1(final Float value) {
        return new JAXBElement<Float>(ObjectFactory._CgSetuserTypeHalf1_QNAME, Float.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool2x3", scope = CgNewarrayType.class)
    public JAXBElement<List<Boolean>> createCgNewarrayTypeBool2X3(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool2X3_QNAME, (Class<List<Boolean>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool2x2", scope = CgNewarrayType.class)
    public JAXBElement<List<Boolean>> createCgNewarrayTypeBool2X2(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool2X2_QNAME, (Class<List<Boolean>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool2x1", scope = CgNewarrayType.class)
    public JAXBElement<List<Boolean>> createCgNewarrayTypeBool2X1(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool2X1_QNAME, (Class<List<Boolean>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool2x4", scope = CgNewarrayType.class)
    public JAXBElement<List<Boolean>> createCgNewarrayTypeBool2X4(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool2X4_QNAME, (Class<List<Boolean>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "sampler2D", scope = CgNewarrayType.class)
    public JAXBElement<CgSampler2D> createCgNewarrayTypeSampler2D(final CgSampler2D value) {
        return new JAXBElement<CgSampler2D>(ObjectFactory._CgSetuserTypeSampler2D_QNAME, CgSampler2D.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half1x3", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeHalf1X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf1X3_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half1x2", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeHalf1X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf1X2_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half1x4", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeHalf1X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf1X4_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half1x1", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeHalf1X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf1X1_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half3", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeHalf3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf3_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half4", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeHalf4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf4_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half2x1", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeHalf2X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf2X1_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int4x1", scope = CgNewarrayType.class)
    public JAXBElement<List<Integer>> createCgNewarrayTypeInt4X1(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt4X1_QNAME, (Class<List<Integer>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int4x2", scope = CgNewarrayType.class)
    public JAXBElement<List<Integer>> createCgNewarrayTypeInt4X2(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt4X2_QNAME, (Class<List<Integer>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half2x2", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeHalf2X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf2X2_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half2x3", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeHalf2X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf2X3_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool", scope = CgNewarrayType.class)
    public JAXBElement<Boolean> createCgNewarrayTypeBool(final Boolean value) {
        return new JAXBElement<Boolean>(ObjectFactory._CgSetuserTypeBool_QNAME, Boolean.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half2x4", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeHalf2X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf2X4_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int3x2", scope = CgNewarrayType.class)
    public JAXBElement<List<Integer>> createCgNewarrayTypeInt3X2(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt3X2_QNAME, (Class<List<Integer>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half3x1", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeHalf3X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf3X1_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int3x3", scope = CgNewarrayType.class)
    public JAXBElement<List<Integer>> createCgNewarrayTypeInt3X3(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt3X3_QNAME, (Class<List<Integer>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half3x2", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeHalf3X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf3X2_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool4x1", scope = CgNewarrayType.class)
    public JAXBElement<List<Boolean>> createCgNewarrayTypeBool4X1(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool4X1_QNAME, (Class<List<Boolean>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half3x3", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeHalf3X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf3X3_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int3x1", scope = CgNewarrayType.class)
    public JAXBElement<List<Integer>> createCgNewarrayTypeInt3X1(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt3X1_QNAME, (Class<List<Integer>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool4x3", scope = CgNewarrayType.class)
    public JAXBElement<List<Boolean>> createCgNewarrayTypeBool4X3(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool4X3_QNAME, (Class<List<Boolean>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half3x4", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeHalf3X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf3X4_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool4x2", scope = CgNewarrayType.class)
    public JAXBElement<List<Boolean>> createCgNewarrayTypeBool4X2(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool4X2_QNAME, (Class<List<Boolean>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "sampler3D", scope = CgNewarrayType.class)
    public JAXBElement<CgSampler3D> createCgNewarrayTypeSampler3D(final CgSampler3D value) {
        return new JAXBElement<CgSampler3D>(ObjectFactory._CgSetuserTypeSampler3D_QNAME, CgSampler3D.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool4x4", scope = CgNewarrayType.class)
    public JAXBElement<List<Boolean>> createCgNewarrayTypeBool4X4(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool4X4_QNAME, (Class<List<Boolean>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int2x2", scope = CgNewarrayType.class)
    public JAXBElement<List<Integer>> createCgNewarrayTypeInt2X2(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt2X2_QNAME, (Class<List<Integer>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed", scope = CgNewarrayType.class)
    public JAXBElement<Float> createCgNewarrayTypeFixed(final Float value) {
        return new JAXBElement<Float>(ObjectFactory._CgSetuserTypeFixed_QNAME, Float.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float", scope = CgNewarrayType.class)
    public JAXBElement<Float> createCgNewarrayTypeFloat(final Float value) {
        return new JAXBElement<Float>(ObjectFactory._CgSetuserTypeFloat_QNAME, Float.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int2x1", scope = CgNewarrayType.class)
    public JAXBElement<List<Integer>> createCgNewarrayTypeInt2X1(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt2X1_QNAME, (Class<List<Integer>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int2x4", scope = CgNewarrayType.class)
    public JAXBElement<List<Integer>> createCgNewarrayTypeInt2X4(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt2X4_QNAME, (Class<List<Integer>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool1", scope = CgNewarrayType.class)
    public JAXBElement<Boolean> createCgNewarrayTypeBool1(final Boolean value) {
        return new JAXBElement<Boolean>(ObjectFactory._CgSetuserTypeBool1_QNAME, Boolean.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float1x2", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFloat1X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat1X2_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "int2x3", scope = CgNewarrayType.class)
    public JAXBElement<List<Integer>> createCgNewarrayTypeInt2X3(final List<Integer> value) {
        return new JAXBElement<List<Integer>>(ObjectFactory._CgSetuserTypeInt2X3_QNAME, (Class<List<Integer>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float1x1", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFloat1X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat1X1_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float3x2", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFloat3X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat3X2_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float3x1", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFloat3X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat3X1_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float3x4", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFloat3X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat3X4_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float3x3", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFloat3X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat3X3_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool3", scope = CgNewarrayType.class)
    public JAXBElement<List<Boolean>> createCgNewarrayTypeBool3(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool3_QNAME, (Class<List<Boolean>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool2", scope = CgNewarrayType.class)
    public JAXBElement<List<Boolean>> createCgNewarrayTypeBool2(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool2_QNAME, (Class<List<Boolean>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool4", scope = CgNewarrayType.class)
    public JAXBElement<List<Boolean>> createCgNewarrayTypeBool4(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool4_QNAME, (Class<List<Boolean>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "surface", scope = CgNewarrayType.class)
    public JAXBElement<CgSurfaceType> createCgNewarrayTypeSurface(final CgSurfaceType value) {
        return new JAXBElement<CgSurfaceType>(ObjectFactory._CgSetuserTypeSurface_QNAME, CgSurfaceType.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half4x1", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeHalf4X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf4X1_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half4x2", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeHalf4X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf4X2_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float1x3", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFloat1X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat1X3_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half4x3", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeHalf4X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf4X3_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "half4x4", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeHalf4X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeHalf4X4_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float1x4", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFloat1X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat1X4_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "array", scope = CgNewarrayType.class)
    public JAXBElement<CgNewarrayType> createCgNewarrayTypeArray(final CgNewarrayType value) {
        return new JAXBElement<CgNewarrayType>(ObjectFactory._CgSetuserTypeArray_QNAME, CgNewarrayType.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed1x1", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFixed1X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed1X1_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed1x2", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFixed1X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed1X2_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed1x3", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFixed1X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed1X3_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed1x4", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFixed1X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed1X4_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "samplerCUBE", scope = CgNewarrayType.class)
    public JAXBElement<CgSamplerCUBE> createCgNewarrayTypeSamplerCUBE(final CgSamplerCUBE value) {
        return new JAXBElement<CgSamplerCUBE>(ObjectFactory._CgSetuserTypeSamplerCUBE_QNAME, CgSamplerCUBE.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool1x3", scope = CgNewarrayType.class)
    public JAXBElement<List<Boolean>> createCgNewarrayTypeBool1X3(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool1X3_QNAME, (Class<List<Boolean>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool1x4", scope = CgNewarrayType.class)
    public JAXBElement<List<Boolean>> createCgNewarrayTypeBool1X4(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool1X4_QNAME, (Class<List<Boolean>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool1x1", scope = CgNewarrayType.class)
    public JAXBElement<List<Boolean>> createCgNewarrayTypeBool1X1(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool1X1_QNAME, (Class<List<Boolean>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool1x2", scope = CgNewarrayType.class)
    public JAXBElement<List<Boolean>> createCgNewarrayTypeBool1X2(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool1X2_QNAME, (Class<List<Boolean>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "usertype", scope = CgNewarrayType.class)
    public JAXBElement<CgSetuserType> createCgNewarrayTypeUsertype(final CgSetuserType value) {
        return new JAXBElement<CgSetuserType>(ObjectFactory._CgSetuserTypeUsertype_QNAME, CgSetuserType.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool3x3", scope = CgNewarrayType.class)
    public JAXBElement<List<Boolean>> createCgNewarrayTypeBool3X3(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool3X3_QNAME, (Class<List<Boolean>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool3x4", scope = CgNewarrayType.class)
    public JAXBElement<List<Boolean>> createCgNewarrayTypeBool3X4(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool3X4_QNAME, (Class<List<Boolean>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool3x1", scope = CgNewarrayType.class)
    public JAXBElement<List<Boolean>> createCgNewarrayTypeBool3X1(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool3X1_QNAME, (Class<List<Boolean>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "bool3x2", scope = CgNewarrayType.class)
    public JAXBElement<List<Boolean>> createCgNewarrayTypeBool3X2(final List<Boolean> value) {
        return new JAXBElement<List<Boolean>>(ObjectFactory._CgSetuserTypeBool3X2_QNAME, (Class<List<Boolean>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "samplerDEPTH", scope = CgNewarrayType.class)
    public JAXBElement<CgSamplerDEPTH> createCgNewarrayTypeSamplerDEPTH(final CgSamplerDEPTH value) {
        return new JAXBElement<CgSamplerDEPTH>(ObjectFactory._CgSetuserTypeSamplerDEPTH_QNAME, CgSamplerDEPTH.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed4x4", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFixed4X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed4X4_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed4x3", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFixed4X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed4X3_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed4x2", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFixed4X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed4X2_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "fixed4x1", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFixed4X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFixed4X1_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float4x4", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFloat4X4(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat4X4_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "string", scope = CgNewarrayType.class)
    public JAXBElement<String> createCgNewarrayTypeString(final String value) {
        return new JAXBElement<String>(ObjectFactory._CgSetuserTypeString_QNAME, String.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float4x2", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFloat4X2(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat4X2_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float4x3", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFloat4X3(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat4X3_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "float4x1", scope = CgNewarrayType.class)
    public JAXBElement<List<Float>> createCgNewarrayTypeFloat4X1(final List<Float> value) {
        return new JAXBElement<List<Float>>(ObjectFactory._CgSetuserTypeFloat4X1_QNAME, (Class<List<Float>>)List.class, CgNewarrayType.class, value);
    }
    
    @XmlElementDecl(namespace = "http://www.collada.org/2005/11/COLLADASchema", name = "ph", scope = Polygons.class)
    public JAXBElement<Polygons.Ph> createPolygonsPh(final Polygons.Ph value) {
        return new JAXBElement<Polygons.Ph>(ObjectFactory._PolygonsPh_QNAME, Polygons.Ph.class, Polygons.class, value);
    }
}
