package com.github.iunius118.pizzamarinaraforhac.food.client;

import com.github.iunius118.pizzamarinaraforhac.PizzaMarinaraForHaC;
import com.github.iunius118.pizzamarinaraforhac.food.client.model.SimplePizzaModel;
import com.github.iunius118.pizzamarinaraforhac.food.entity.PizzaMarinaraEntity;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import defeatedcrow.hac.core.client.base.DCRenderFoodBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class PizzaMarinaraRenderer extends DCRenderFoodBase<PizzaMarinaraEntity> {
    private static final ResourceLocation RAW_TEX = new ResourceLocation(PizzaMarinaraForHaC.MOD_ID,
            "textures/entity/food/pizza_marinara_raw.png");
    private static final ResourceLocation BAKED_TEX = new ResourceLocation(PizzaMarinaraForHaC.MOD_ID,
            "textures/entity/food/pizza_marinara_baked.png");
    private static final DCFoodModelBase MODEL = new SimplePizzaModel(false);

    protected PizzaMarinaraRenderer(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    protected ResourceLocation getFoodTexture(boolean baked) {
        return baked ? BAKED_TEX : RAW_TEX;
    }

    @Override
    protected DCFoodModelBase getEntityModel(boolean baked) {
        return MODEL;
    }

    public static class Factory implements IRenderFactory<PizzaMarinaraEntity> {
        @Override
        public Render<PizzaMarinaraEntity> createRenderFor(RenderManager manager) {
            return new PizzaMarinaraRenderer(manager);
        }
    }
}
