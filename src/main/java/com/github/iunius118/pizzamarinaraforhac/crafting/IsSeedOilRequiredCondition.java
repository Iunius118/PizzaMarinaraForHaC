package com.github.iunius118.pizzamarinaraforhac.crafting;

import com.github.iunius118.pizzamarinaraforhac.PizzaMarinaraForHaC;
import com.github.iunius118.pizzamarinaraforhac.config.MainConfig;
import com.google.gson.JsonObject;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;

import java.util.function.BooleanSupplier;

public class IsSeedOilRequiredCondition implements BooleanSupplier {
    private final boolean isSeedOilRequired;

    public IsSeedOilRequiredCondition(boolean isSeedOilRequired) {
        this.isSeedOilRequired = isSeedOilRequired;
    }

    @Override
    public boolean getAsBoolean() {
        if (MainConfig.debug.getBoolean())
            PizzaMarinaraForHaC.logger.info("IsOliveOilRequiredCondition: {}", MainConfig.pizzaMarinaraRequiresSeedOil.getBoolean() == isSeedOilRequired);
        return MainConfig.pizzaMarinaraRequiresSeedOil.getBoolean() == isSeedOilRequired;
    }

    public static class Factory implements IConditionFactory {
        @Override
        public BooleanSupplier parse(JsonContext context, JsonObject json) {
            boolean value = JsonUtils.getBoolean(json, "value", true);
            if (MainConfig.debug.getBoolean())
                PizzaMarinaraForHaC.logger.info("is_seed_oil_required: {}", value);
            return new IsSeedOilRequiredCondition(value);
        }
    }
}
