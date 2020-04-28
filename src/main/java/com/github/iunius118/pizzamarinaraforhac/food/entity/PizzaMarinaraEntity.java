package com.github.iunius118.pizzamarinaraforhac.food.entity;

import com.github.iunius118.pizzamarinaraforhac.PizzaMarinaraForHaC;
import com.github.iunius118.pizzamarinaraforhac.food.item.ModItems;
import defeatedcrow.hac.core.base.FoodEntityBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PizzaMarinaraEntity extends FoodEntityBase {
    public PizzaMarinaraEntity(World worldIn) {
        super(worldIn);
        setSize(0.5F, 0.1F);
    }

    public PizzaMarinaraEntity(World worldIn, double posX, double posY, double posZ) {
        super(worldIn, posX, posY, posZ);
        setSize(0.5F, 0.1F);
    }

    public PizzaMarinaraEntity(World world, double x, double y, double z, EntityPlayer player) {
        super(world, x, y, z, player);
        setSize(0.5F, 0.1F);
    }

    @Override
    protected ItemStack[] drops() {
        return new ItemStack[] {
                new ItemStack(ModItems.pizza, 1, 0),
                new ItemStack(ModItems.pizza, 1, 1)
        };
    }

    @Override
    protected void addParticle() {
        if (!getRaw()) {
            PizzaMarinaraForHaC.proxy.addCloudParticle(world, posX, posY, posZ);
        }
    }
}
