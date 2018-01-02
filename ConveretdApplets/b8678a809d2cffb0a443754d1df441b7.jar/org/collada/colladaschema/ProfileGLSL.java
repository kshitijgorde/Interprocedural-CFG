// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.XmlList;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.List;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "asset", "includesAndCodes", "newparamsAndImages", "techniques", "extras" })
public class ProfileGLSL
{
    protected Asset asset;
    @XmlElements({ @XmlElement(name = "code", type = FxCodeProfile.class), @XmlElement(name = "include", type = FxIncludeCommon.class) })
    protected List<Object> includesAndCodes;
    @XmlElements({ @XmlElement(name = "newparam", type = GlslNewparam.class), @XmlElement(name = "image", type = Image.class) })
    protected List<Object> newparamsAndImages;
    @XmlElement(name = "technique", required = true)
    protected List<Technique> techniques;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    
    public Asset getAsset() {
        return this.asset;
    }
    
    public void setAsset(final Asset value) {
        this.asset = value;
    }
    
    public List<Object> getIncludesAndCodes() {
        if (this.includesAndCodes == null) {
            this.includesAndCodes = new ArrayList<Object>();
        }
        return this.includesAndCodes;
    }
    
    public List<Object> getNewparamsAndImages() {
        if (this.newparamsAndImages == null) {
            this.newparamsAndImages = new ArrayList<Object>();
        }
        return this.newparamsAndImages;
    }
    
    public List<Technique> getTechniques() {
        if (this.techniques == null) {
            this.techniques = new ArrayList<Technique>();
        }
        return this.techniques;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId(final String value) {
        this.id = value;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "annotates", "includesAndCodes", "imagesAndSetparamsAndNewparams", "pass", "extras" })
    public static class Technique
    {
        @XmlElement(name = "annotate")
        protected List<FxAnnotateCommon> annotates;
        @XmlElements({ @XmlElement(name = "code", type = FxCodeProfile.class), @XmlElement(name = "include", type = FxIncludeCommon.class) })
        protected List<Object> includesAndCodes;
        @XmlElements({ @XmlElement(name = "newparam", type = GlslNewparam.class), @XmlElement(name = "setparam", type = GlslSetparam.class), @XmlElement(name = "image", type = Image.class) })
        protected List<Object> imagesAndSetparamsAndNewparams;
        @XmlElement(required = true)
        protected List<Pass> pass;
        @XmlElement(name = "extra")
        protected List<Extra> extras;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        protected String id;
        @XmlAttribute(required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String sid;
        
        public List<FxAnnotateCommon> getAnnotates() {
            if (this.annotates == null) {
                this.annotates = new ArrayList<FxAnnotateCommon>();
            }
            return this.annotates;
        }
        
        public List<Object> getIncludesAndCodes() {
            if (this.includesAndCodes == null) {
                this.includesAndCodes = new ArrayList<Object>();
            }
            return this.includesAndCodes;
        }
        
        public List<Object> getImagesAndSetparamsAndNewparams() {
            if (this.imagesAndSetparamsAndNewparams == null) {
                this.imagesAndSetparamsAndNewparams = new ArrayList<Object>();
            }
            return this.imagesAndSetparamsAndNewparams;
        }
        
        public List<Pass> getPass() {
            if (this.pass == null) {
                this.pass = new ArrayList<Pass>();
            }
            return this.pass;
        }
        
        public List<Extra> getExtras() {
            if (this.extras == null) {
                this.extras = new ArrayList<Extra>();
            }
            return this.extras;
        }
        
        public String getId() {
            return this.id;
        }
        
        public void setId(final String value) {
            this.id = value;
        }
        
        public String getSid() {
            return this.sid;
        }
        
        public void setSid(final String value) {
            this.sid = value;
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "annotates", "colorTargets", "depthTargets", "stencilTargets", "colorClears", "depthClears", "stencilClears", "draw", "colorMaterialsAndFogColorsAndSampleAlphaToCoverageEnables", "extras" })
        public static class Pass
        {
            @XmlElement(name = "annotate")
            protected List<FxAnnotateCommon> annotates;
            @XmlElement(name = "color_target")
            protected List<FxColortargetCommon> colorTargets;
            @XmlElement(name = "depth_target")
            protected List<FxDepthtargetCommon> depthTargets;
            @XmlElement(name = "stencil_target")
            protected List<FxStenciltargetCommon> stencilTargets;
            @XmlElement(name = "color_clear")
            protected List<FxClearcolorCommon> colorClears;
            @XmlElement(name = "depth_clear")
            protected List<FxCleardepthCommon> depthClears;
            @XmlElement(name = "stencil_clear")
            protected List<FxClearstencilCommon> stencilClears;
            protected String draw;
            @XmlElements({ @XmlElement(name = "line_width", type = ProfileCG.Technique.Pass.LineWidth.class), @XmlElement(name = "texture1D_enable", type = ProfileCG.Technique.Pass.Texture1DEnable.class), @XmlElement(name = "light_model_ambient", type = ProfileCG.Technique.Pass.LightModelAmbient.class), @XmlElement(name = "stencil_mask", type = ProfileCG.Technique.Pass.StencilMask.class), @XmlElement(name = "sample_alpha_to_coverage_enable", type = ProfileCG.Technique.Pass.SampleAlphaToCoverageEnable.class), @XmlElement(name = "light_spot_cutoff", type = ProfileCG.Technique.Pass.LightSpotCutoff.class), @XmlElement(name = "textureCUBE_enable", type = ProfileCG.Technique.Pass.TextureCUBEEnable.class), @XmlElement(name = "depth_clamp_enable", type = ProfileCG.Technique.Pass.DepthClampEnable.class), @XmlElement(name = "line_smooth_enable", type = ProfileCG.Technique.Pass.LineSmoothEnable.class), @XmlElement(name = "material_emission", type = ProfileCG.Technique.Pass.MaterialEmission.class), @XmlElement(name = "clear_depth", type = ProfileCG.Technique.Pass.ClearDepth.class), @XmlElement(name = "polygon_smooth_enable", type = ProfileCG.Technique.Pass.PolygonSmoothEnable.class), @XmlElement(name = "front_face", type = ProfileCG.Technique.Pass.FrontFace.class), @XmlElement(name = "light_model_two_side_enable", type = ProfileCG.Technique.Pass.LightModelTwoSideEnable.class), @XmlElement(name = "multisample_enable", type = ProfileCG.Technique.Pass.MultisampleEnable.class), @XmlElement(name = "polygon_offset_line_enable", type = ProfileCG.Technique.Pass.PolygonOffsetLineEnable.class), @XmlElement(name = "projection_matrix", type = ProfileCG.Technique.Pass.ProjectionMatrix.class), @XmlElement(name = "cull_face", type = ProfileCG.Technique.Pass.CullFace.class), @XmlElement(name = "polygon_mode", type = ProfileCG.Technique.Pass.PolygonMode.class), @XmlElement(name = "light_constant_attenuation", type = ProfileCG.Technique.Pass.LightConstantAttenuation.class), @XmlElement(name = "stencil_func_separate", type = ProfileCG.Technique.Pass.StencilFuncSeparate.class), @XmlElement(name = "texture2D_enable", type = ProfileCG.Technique.Pass.Texture2DEnable.class), @XmlElement(name = "light_position", type = ProfileCG.Technique.Pass.LightPosition.class), @XmlElement(name = "fog_coord_src", type = ProfileCG.Technique.Pass.FogCoordSrc.class), @XmlElement(name = "blend_enable", type = ProfileCG.Technique.Pass.BlendEnable.class), @XmlElement(name = "blend_func", type = ProfileCG.Technique.Pass.BlendFunc.class), @XmlElement(name = "fog_end", type = ProfileCG.Technique.Pass.FogEnd.class), @XmlElement(name = "stencil_op_separate", type = ProfileCG.Technique.Pass.StencilOpSeparate.class), @XmlElement(name = "blend_equation_separate", type = ProfileCG.Technique.Pass.BlendEquationSeparate.class), @XmlElement(name = "material_diffuse", type = ProfileCG.Technique.Pass.MaterialDiffuse.class), @XmlElement(name = "alpha_test_enable", type = ProfileCG.Technique.Pass.AlphaTestEnable.class), @XmlElement(name = "line_stipple", type = ProfileCG.Technique.Pass.LineStipple.class), @XmlElement(name = "polygon_offset_fill_enable", type = ProfileCG.Technique.Pass.PolygonOffsetFillEnable.class), @XmlElement(name = "texture_env_color", type = ProfileCG.Technique.Pass.TextureEnvColor.class), @XmlElement(name = "texture_env_mode", type = ProfileCG.Technique.Pass.TextureEnvMode.class), @XmlElement(name = "fog_enable", type = ProfileCG.Technique.Pass.FogEnable.class), @XmlElement(name = "stencil_op", type = ProfileCG.Technique.Pass.StencilOp.class), @XmlElement(name = "color_logic_op_enable", type = ProfileCG.Technique.Pass.ColorLogicOpEnable.class), @XmlElement(name = "texture3D", type = ProfileCG.Technique.Pass.Texture3D.class), @XmlElement(name = "light_linear_attenuation", type = ProfileCG.Technique.Pass.LightLinearAttenuation.class), @XmlElement(name = "fog_start", type = ProfileCG.Technique.Pass.FogStart.class), @XmlElement(name = "fog_color", type = ProfileCG.Technique.Pass.FogColor.class), @XmlElement(name = "light_enable", type = ProfileCG.Technique.Pass.LightEnable.class), @XmlElement(name = "rescale_normal_enable", type = ProfileCG.Technique.Pass.RescaleNormalEnable.class), @XmlElement(name = "stencil_mask_separate", type = ProfileCG.Technique.Pass.StencilMaskSeparate.class), @XmlElement(name = "alpha_func", type = ProfileCG.Technique.Pass.AlphaFunc.class), @XmlElement(name = "material_specular", type = ProfileCG.Technique.Pass.MaterialSpecular.class), @XmlElement(name = "stencil_test_enable", type = ProfileCG.Technique.Pass.StencilTestEnable.class), @XmlElement(name = "blend_color", type = ProfileCG.Technique.Pass.BlendColor.class), @XmlElement(name = "light_spot_direction", type = ProfileCG.Technique.Pass.LightSpotDirection.class), @XmlElement(name = "shade_model", type = ProfileCG.Technique.Pass.ShadeModel.class), @XmlElement(name = "light_model_color_control", type = ProfileCG.Technique.Pass.LightModelColorControl.class), @XmlElement(name = "fog_mode", type = ProfileCG.Technique.Pass.FogMode.class), @XmlElement(name = "logic_op", type = ProfileCG.Technique.Pass.LogicOp.class), @XmlElement(name = "polygon_offset", type = ProfileCG.Technique.Pass.PolygonOffset.class), @XmlElement(name = "clip_plane", type = ProfileCG.Technique.Pass.ClipPlane.class), @XmlElement(name = "textureDEPTH_enable", type = ProfileCG.Technique.Pass.TextureDEPTHEnable.class), @XmlElement(name = "light_model_local_viewer_enable", type = ProfileCG.Technique.Pass.LightModelLocalViewerEnable.class), @XmlElement(name = "depth_bounds", type = ProfileCG.Technique.Pass.DepthBounds.class), @XmlElement(name = "scissor_test_enable", type = ProfileCG.Technique.Pass.ScissorTestEnable.class), @XmlElement(name = "depth_test_enable", type = ProfileCG.Technique.Pass.DepthTestEnable.class), @XmlElement(name = "depth_func", type = ProfileCG.Technique.Pass.DepthFunc.class), @XmlElement(name = "point_fade_threshold_size", type = ProfileCG.Technique.Pass.PointFadeThresholdSize.class), @XmlElement(name = "blend_func_separate", type = ProfileCG.Technique.Pass.BlendFuncSeparate.class), @XmlElement(name = "sample_coverage_enable", type = ProfileCG.Technique.Pass.SampleCoverageEnable.class), @XmlElement(name = "light_quadratic_attenuation", type = ProfileCG.Technique.Pass.LightQuadraticAttenuation.class), @XmlElement(name = "light_specular", type = ProfileCG.Technique.Pass.LightSpecular.class), @XmlElement(name = "texture3D_enable", type = ProfileCG.Technique.Pass.Texture3DEnable.class), @XmlElement(name = "material_shininess", type = ProfileCG.Technique.Pass.MaterialShininess.class), @XmlElement(name = "material_ambient", type = ProfileCG.Technique.Pass.MaterialAmbient.class), @XmlElement(name = "point_size", type = ProfileCG.Technique.Pass.PointSize.class), @XmlElement(name = "textureRECT_enable", type = ProfileCG.Technique.Pass.TextureRECTEnable.class), @XmlElement(name = "color_mask", type = ProfileCG.Technique.Pass.ColorMask.class), @XmlElement(name = "texture1D", type = ProfileCG.Technique.Pass.Texture1D.class), @XmlElement(name = "cull_face_enable", type = ProfileCG.Technique.Pass.CullFaceEnable.class), @XmlElement(name = "sample_alpha_to_one_enable", type = ProfileCG.Technique.Pass.SampleAlphaToOneEnable.class), @XmlElement(name = "stencil_func", type = ProfileCG.Technique.Pass.StencilFunc.class), @XmlElement(name = "texture2D", type = ProfileCG.Technique.Pass.Texture2D.class), @XmlElement(name = "clip_plane_enable", type = ProfileCG.Technique.Pass.ClipPlaneEnable.class), @XmlElement(name = "textureCUBE", type = ProfileCG.Technique.Pass.TextureCUBE.class), @XmlElement(name = "color_material_enable", type = ProfileCG.Technique.Pass.ColorMaterialEnable.class), @XmlElement(name = "depth_range", type = ProfileCG.Technique.Pass.DepthRange.class), @XmlElement(name = "polygon_offset_point_enable", type = ProfileCG.Technique.Pass.PolygonOffsetPointEnable.class), @XmlElement(name = "point_smooth_enable", type = ProfileCG.Technique.Pass.PointSmoothEnable.class), @XmlElement(name = "model_view_matrix", type = ProfileCG.Technique.Pass.ModelViewMatrix.class), @XmlElement(name = "line_stipple_enable", type = ProfileCG.Technique.Pass.LineStippleEnable.class), @XmlElement(name = "polygon_stipple_enable", type = ProfileCG.Technique.Pass.PolygonStippleEnable.class), @XmlElement(name = "point_size_min", type = ProfileCG.Technique.Pass.PointSizeMin.class), @XmlElement(name = "gl_hook_abstract"), @XmlElement(name = "point_size_max", type = ProfileCG.Technique.Pass.PointSizeMax.class), @XmlElement(name = "normalize_enable", type = ProfileCG.Technique.Pass.NormalizeEnable.class), @XmlElement(name = "shader", type = Shader.class), @XmlElement(name = "textureDEPTH", type = ProfileCG.Technique.Pass.TextureDEPTH.class), @XmlElement(name = "lighting_enable", type = ProfileCG.Technique.Pass.LightingEnable.class), @XmlElement(name = "depth_mask", type = ProfileCG.Technique.Pass.DepthMask.class), @XmlElement(name = "blend_equation", type = ProfileCG.Technique.Pass.BlendEquation.class), @XmlElement(name = "logic_op_enable", type = ProfileCG.Technique.Pass.LogicOpEnable.class), @XmlElement(name = "scissor", type = ProfileCG.Technique.Pass.Scissor.class), @XmlElement(name = "fog_density", type = ProfileCG.Technique.Pass.FogDensity.class), @XmlElement(name = "textureRECT", type = ProfileCG.Technique.Pass.TextureRECT.class), @XmlElement(name = "color_material", type = ProfileCG.Technique.Pass.ColorMaterial.class), @XmlElement(name = "auto_normal_enable", type = ProfileCG.Technique.Pass.AutoNormalEnable.class), @XmlElement(name = "light_ambient", type = ProfileCG.Technique.Pass.LightAmbient.class), @XmlElement(name = "clear_color", type = ProfileCG.Technique.Pass.ClearColor.class), @XmlElement(name = "light_spot_exponent", type = ProfileCG.Technique.Pass.LightSpotExponent.class), @XmlElement(name = "light_diffuse", type = ProfileCG.Technique.Pass.LightDiffuse.class), @XmlElement(name = "dither_enable", type = ProfileCG.Technique.Pass.DitherEnable.class), @XmlElement(name = "point_distance_attenuation", type = ProfileCG.Technique.Pass.PointDistanceAttenuation.class), @XmlElement(name = "depth_bounds_enable", type = ProfileCG.Technique.Pass.DepthBoundsEnable.class), @XmlElement(name = "clear_stencil", type = ProfileCG.Technique.Pass.ClearStencil.class) })
            protected List<Object> colorMaterialsAndFogColorsAndSampleAlphaToCoverageEnables;
            @XmlElement(name = "extra")
            protected List<Extra> extras;
            @XmlAttribute
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String sid;
            
            public List<FxAnnotateCommon> getAnnotates() {
                if (this.annotates == null) {
                    this.annotates = new ArrayList<FxAnnotateCommon>();
                }
                return this.annotates;
            }
            
            public List<FxColortargetCommon> getColorTargets() {
                if (this.colorTargets == null) {
                    this.colorTargets = new ArrayList<FxColortargetCommon>();
                }
                return this.colorTargets;
            }
            
            public List<FxDepthtargetCommon> getDepthTargets() {
                if (this.depthTargets == null) {
                    this.depthTargets = new ArrayList<FxDepthtargetCommon>();
                }
                return this.depthTargets;
            }
            
            public List<FxStenciltargetCommon> getStencilTargets() {
                if (this.stencilTargets == null) {
                    this.stencilTargets = new ArrayList<FxStenciltargetCommon>();
                }
                return this.stencilTargets;
            }
            
            public List<FxClearcolorCommon> getColorClears() {
                if (this.colorClears == null) {
                    this.colorClears = new ArrayList<FxClearcolorCommon>();
                }
                return this.colorClears;
            }
            
            public List<FxCleardepthCommon> getDepthClears() {
                if (this.depthClears == null) {
                    this.depthClears = new ArrayList<FxCleardepthCommon>();
                }
                return this.depthClears;
            }
            
            public List<FxClearstencilCommon> getStencilClears() {
                if (this.stencilClears == null) {
                    this.stencilClears = new ArrayList<FxClearstencilCommon>();
                }
                return this.stencilClears;
            }
            
            public String getDraw() {
                return this.draw;
            }
            
            public void setDraw(final String value) {
                this.draw = value;
            }
            
            public List<Object> getColorMaterialsAndFogColorsAndSampleAlphaToCoverageEnables() {
                if (this.colorMaterialsAndFogColorsAndSampleAlphaToCoverageEnables == null) {
                    this.colorMaterialsAndFogColorsAndSampleAlphaToCoverageEnables = new ArrayList<Object>();
                }
                return this.colorMaterialsAndFogColorsAndSampleAlphaToCoverageEnables;
            }
            
            public List<Extra> getExtras() {
                if (this.extras == null) {
                    this.extras = new ArrayList<Extra>();
                }
                return this.extras;
            }
            
            public String getSid() {
                return this.sid;
            }
            
            public void setSid(final String value) {
                this.sid = value;
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = { "annotates", "compilerTarget", "compilerOptions", "name", "binds" })
            public static class Shader
            {
                @XmlElement(name = "annotate")
                protected List<FxAnnotateCommon> annotates;
                @XmlElement(name = "compiler_target")
                protected CompilerTarget compilerTarget;
                @XmlElement(name = "compiler_options")
                protected String compilerOptions;
                @XmlElement(required = true)
                protected Name name;
                @XmlElement(name = "bind")
                protected List<Bind> binds;
                @XmlAttribute
                protected GlslPipelineStage stage;
                
                public List<FxAnnotateCommon> getAnnotates() {
                    if (this.annotates == null) {
                        this.annotates = new ArrayList<FxAnnotateCommon>();
                    }
                    return this.annotates;
                }
                
                public CompilerTarget getCompilerTarget() {
                    return this.compilerTarget;
                }
                
                public void setCompilerTarget(final CompilerTarget value) {
                    this.compilerTarget = value;
                }
                
                public String getCompilerOptions() {
                    return this.compilerOptions;
                }
                
                public void setCompilerOptions(final String value) {
                    this.compilerOptions = value;
                }
                
                public Name getName() {
                    return this.name;
                }
                
                public void setName(final Name value) {
                    this.name = value;
                }
                
                public List<Bind> getBinds() {
                    if (this.binds == null) {
                        this.binds = new ArrayList<Bind>();
                    }
                    return this.binds;
                }
                
                public GlslPipelineStage getStage() {
                    return this.stage;
                }
                
                public void setStage(final GlslPipelineStage value) {
                    this.stage = value;
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = { "param", "float2", "surface", "sampler2D", "bool", "float2X2", "float3", "samplerDEPTH", "int4", "int2", "int3", "_float", "bool3", "sampler1D", "float4", "_enum", "bool2", "samplerRECT", "sampler3D", "_int", "samplerCUBE", "bool4", "float3X3", "float4X4" })
                public static class Bind
                {
                    protected Param param;
                    @XmlList
                    @XmlElement(type = Float.class)
                    protected List<Float> float2;
                    protected GlslSurfaceType surface;
                    protected GlSampler2D sampler2D;
                    protected Boolean bool;
                    @XmlList
                    @XmlElement(name = "float2x2", type = Float.class)
                    protected List<Float> float2X2;
                    @XmlList
                    @XmlElement(type = Float.class)
                    protected List<Float> float3;
                    protected GlSamplerDEPTH samplerDEPTH;
                    @XmlList
                    @XmlElement(type = Integer.class)
                    protected List<Integer> int4;
                    @XmlList
                    @XmlElement(type = Integer.class)
                    protected List<Integer> int2;
                    @XmlList
                    @XmlElement(type = Integer.class)
                    protected List<Integer> int3;
                    @XmlElement(name = "float")
                    protected Float _float;
                    @XmlList
                    @XmlElement(type = Boolean.class)
                    protected List<Boolean> bool3;
                    protected GlSampler1D sampler1D;
                    @XmlList
                    @XmlElement(type = Float.class)
                    protected List<Float> float4;
                    @XmlElement(name = "enum")
                    protected String _enum;
                    @XmlList
                    @XmlElement(type = Boolean.class)
                    protected List<Boolean> bool2;
                    protected GlSamplerRECT samplerRECT;
                    protected GlSampler3D sampler3D;
                    @XmlElement(name = "int")
                    protected Integer _int;
                    protected GlSamplerCUBE samplerCUBE;
                    @XmlList
                    @XmlElement(type = Boolean.class)
                    protected List<Boolean> bool4;
                    @XmlList
                    @XmlElement(name = "float3x3", type = Float.class)
                    protected List<Float> float3X3;
                    @XmlList
                    @XmlElement(name = "float4x4", type = Float.class)
                    protected List<Float> float4X4;
                    @XmlAttribute(required = true)
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String symbol;
                    
                    public Param getParam() {
                        return this.param;
                    }
                    
                    public void setParam(final Param value) {
                        this.param = value;
                    }
                    
                    public List<Float> getFloat2() {
                        if (this.float2 == null) {
                            this.float2 = new ArrayList<Float>();
                        }
                        return this.float2;
                    }
                    
                    public GlslSurfaceType getSurface() {
                        return this.surface;
                    }
                    
                    public void setSurface(final GlslSurfaceType value) {
                        this.surface = value;
                    }
                    
                    public GlSampler2D getSampler2D() {
                        return this.sampler2D;
                    }
                    
                    public void setSampler2D(final GlSampler2D value) {
                        this.sampler2D = value;
                    }
                    
                    public Boolean isBool() {
                        return this.bool;
                    }
                    
                    public void setBool(final Boolean value) {
                        this.bool = value;
                    }
                    
                    public List<Float> getFloat2X2() {
                        if (this.float2X2 == null) {
                            this.float2X2 = new ArrayList<Float>();
                        }
                        return this.float2X2;
                    }
                    
                    public List<Float> getFloat3() {
                        if (this.float3 == null) {
                            this.float3 = new ArrayList<Float>();
                        }
                        return this.float3;
                    }
                    
                    public GlSamplerDEPTH getSamplerDEPTH() {
                        return this.samplerDEPTH;
                    }
                    
                    public void setSamplerDEPTH(final GlSamplerDEPTH value) {
                        this.samplerDEPTH = value;
                    }
                    
                    public List<Integer> getInt4() {
                        if (this.int4 == null) {
                            this.int4 = new ArrayList<Integer>();
                        }
                        return this.int4;
                    }
                    
                    public List<Integer> getInt2() {
                        if (this.int2 == null) {
                            this.int2 = new ArrayList<Integer>();
                        }
                        return this.int2;
                    }
                    
                    public List<Integer> getInt3() {
                        if (this.int3 == null) {
                            this.int3 = new ArrayList<Integer>();
                        }
                        return this.int3;
                    }
                    
                    public Float getFloat() {
                        return this._float;
                    }
                    
                    public void setFloat(final Float value) {
                        this._float = value;
                    }
                    
                    public List<Boolean> getBool3() {
                        if (this.bool3 == null) {
                            this.bool3 = new ArrayList<Boolean>();
                        }
                        return this.bool3;
                    }
                    
                    public GlSampler1D getSampler1D() {
                        return this.sampler1D;
                    }
                    
                    public void setSampler1D(final GlSampler1D value) {
                        this.sampler1D = value;
                    }
                    
                    public List<Float> getFloat4() {
                        if (this.float4 == null) {
                            this.float4 = new ArrayList<Float>();
                        }
                        return this.float4;
                    }
                    
                    public String getEnum() {
                        return this._enum;
                    }
                    
                    public void setEnum(final String value) {
                        this._enum = value;
                    }
                    
                    public List<Boolean> getBool2() {
                        if (this.bool2 == null) {
                            this.bool2 = new ArrayList<Boolean>();
                        }
                        return this.bool2;
                    }
                    
                    public GlSamplerRECT getSamplerRECT() {
                        return this.samplerRECT;
                    }
                    
                    public void setSamplerRECT(final GlSamplerRECT value) {
                        this.samplerRECT = value;
                    }
                    
                    public GlSampler3D getSampler3D() {
                        return this.sampler3D;
                    }
                    
                    public void setSampler3D(final GlSampler3D value) {
                        this.sampler3D = value;
                    }
                    
                    public Integer getInt() {
                        return this._int;
                    }
                    
                    public void setInt(final Integer value) {
                        this._int = value;
                    }
                    
                    public GlSamplerCUBE getSamplerCUBE() {
                        return this.samplerCUBE;
                    }
                    
                    public void setSamplerCUBE(final GlSamplerCUBE value) {
                        this.samplerCUBE = value;
                    }
                    
                    public List<Boolean> getBool4() {
                        if (this.bool4 == null) {
                            this.bool4 = new ArrayList<Boolean>();
                        }
                        return this.bool4;
                    }
                    
                    public List<Float> getFloat3X3() {
                        if (this.float3X3 == null) {
                            this.float3X3 = new ArrayList<Float>();
                        }
                        return this.float3X3;
                    }
                    
                    public List<Float> getFloat4X4() {
                        if (this.float4X4 == null) {
                            this.float4X4 = new ArrayList<Float>();
                        }
                        return this.float4X4;
                    }
                    
                    public String getSymbol() {
                        return this.symbol;
                    }
                    
                    public void setSymbol(final String value) {
                        this.symbol = value;
                    }
                    
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "")
                    public static class Param
                    {
                        @XmlAttribute(required = true)
                        protected String ref;
                        
                        public String getRef() {
                            return this.ref;
                        }
                        
                        public void setRef(final String value) {
                            this.ref = value;
                        }
                    }
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = { "value" })
                public static class CompilerTarget
                {
                    @XmlValue
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String value;
                    
                    public String getValue() {
                        return this.value;
                    }
                    
                    public void setValue(final String value) {
                        this.value = value;
                    }
                }
                
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = { "value" })
                public static class Name
                {
                    @XmlValue
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String value;
                    @XmlAttribute
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    protected String source;
                    
                    public String getValue() {
                        return this.value;
                    }
                    
                    public void setValue(final String value) {
                        this.value = value;
                    }
                    
                    public String getSource() {
                        return this.source;
                    }
                    
                    public void setSource(final String value) {
                        this.source = value;
                    }
                }
            }
        }
    }
}
