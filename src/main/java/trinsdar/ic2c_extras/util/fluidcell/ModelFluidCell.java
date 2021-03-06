package trinsdar.ic2c_extras.util.fluidcell;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.fluids.Fluid;
import trinsdar.ic2c_extras.IC2CExtras;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class ModelFluidCell implements IModel {

    private static ResourceLocation BASE = new ResourceLocation(IC2CExtras.MODID, "items/universal_fluid_cell");
    private static IBakedModel BAKED_BASE, BAKED_OVERLAY;
    private Fluid fluid;

    public ModelFluidCell() {
        // Default Constructor
    }

    public ModelFluidCell(Fluid fluid) {
        this.fluid = fluid;
    }

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format,
                            Function<ResourceLocation, TextureAtlasSprite> getter) {
        if (BAKED_BASE == null)
            BAKED_BASE = ModelUtils.load(IC2CExtras.MODID, "universal_fluid_cell_model").bake(state, format, getter);
        if (BAKED_OVERLAY == null)
            BAKED_OVERLAY = ModelUtils.load(IC2CExtras.MODID, "universal_fluid_cell_overlay_model").bake(state, format, getter);
        ImmutableList.Builder<BakedQuad> builder = ImmutableList.builder();
        if (fluid != null) {
            TextureAtlasSprite sprite = getter.apply(fluid.getStill());
            if (sprite != null) {
                List<BakedQuad> quads = BAKED_OVERLAY.getQuads(null, null, 0);
                quads = ModelUtils.texAndTint(quads, fluid.getColor(), sprite);
                builder.addAll(quads);
            }
        }
        builder.addAll(BAKED_BASE.getQuads(null, null, 0));
        return new BakedFluidCell(builder.build(), this, getter.apply(BASE), format);
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        return Collections.singletonList(BASE);
    }
}
