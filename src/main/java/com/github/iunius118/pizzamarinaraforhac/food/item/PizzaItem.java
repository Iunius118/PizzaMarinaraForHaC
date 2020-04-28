package com.github.iunius118.pizzamarinaraforhac.food.item;

import com.github.iunius118.pizzamarinaraforhac.PizzaMarinaraForHaC;
import com.github.iunius118.pizzamarinaraforhac.food.entity.PizzaMarinaraEntity;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class PizzaItem extends FoodItemBase {
    public PizzaItem(boolean isWolfFood) {
        super(isWolfFood);
    }

    @Override
    public int getMaxMeta() {
        return 1;
    }

    @Override
    public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
        int i = item.getMetadata();
        FoodEntityBase ret = new PizzaMarinaraEntity(world, x, y, z, player);

        if ((i & 1) == 0) {
            ret.setRAW(true);
        }

        return ret;
    }

    @Override
    public String[] getNameSuffix() {
        return new String[]{
                "marinara_raw",
                "marinara_baked"};
    }

    @Override
    public int getFoodAmo(int meta) {
        switch (meta) {
            case 1:
                return 10;
            default:
                return 0;
        }
    }

    @Override
    public float getSaturation(int meta) {
        return (meta & 1) == 0 ? 0F : 0.5F;
    }

    @Override
    public String getTexPath(int meta, boolean isFull) {
        int i = MathHelper.clamp(meta, 0, 1);
        String s = "items/food/" + this.getNameSuffix()[i];

        if (isFull) {
            s = "textures/" + s;
        }

        return PizzaMarinaraForHaC.MOD_ID + ":" + s;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
        tooltip.add(I18n.format("dcs.tip.placeable_entity"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (this.isInCreativeTab(tab)) {
            List<ItemStack> itms = getSubItemList();
            subItems.addAll(itms);
        }
    }
}
