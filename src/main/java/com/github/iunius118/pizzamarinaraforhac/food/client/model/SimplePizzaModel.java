package com.github.iunius118.pizzamarinaraforhac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;

public class SimplePizzaModel extends DCFoodModelBase {
    private ModelRenderer pizzaBase;

    public SimplePizzaModel(boolean baked) {
        super(baked);

        pizzaBase = new ModelRenderer(this, 0, 0);
        pizzaBase.addBox(-6F, -1F, -6F, 12, 1, 12);
        pizzaBase.setRotationPoint(0F, 0F, 0F);
        pizzaBase.setTextureSize(64, 32);
        pizzaBase.mirror = true;
    }

    @Override
    public void render(float scale, FoodEntityBase foodEntityBase) {
        pizzaBase.render(0.0625F);
    }
}
