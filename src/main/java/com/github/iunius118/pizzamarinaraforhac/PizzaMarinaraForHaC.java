package com.github.iunius118.pizzamarinaraforhac;

import com.github.iunius118.pizzamarinaraforhac.config.MainConfig;
import com.github.iunius118.pizzamarinaraforhac.food.entity.PizzaMarinaraEntity;
import com.github.iunius118.pizzamarinaraforhac.food.item.PizzaItem;
import com.github.iunius118.pizzamarinaraforhac.food.recipe.PizzaClimateRecipe;
import defeatedcrow.hac.core.event.DispenseEntityItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import org.apache.logging.log4j.Logger;

@Mod(   modid = PizzaMarinaraForHaC.MOD_ID,
        name = PizzaMarinaraForHaC.NAME,
        version = PizzaMarinaraForHaC.VERSION,
        dependencies = PizzaMarinaraForHaC.DEPENDENCIES)
@Mod.EventBusSubscriber
public class PizzaMarinaraForHaC
{
    public static final String MOD_ID = "pizzamarinaraforhac";
    public static final String NAME = "Pizza Marinara for HaC";
    public static final String VERSION = "1.12.2-1.1.0.2";
    public static final String DEPENDENCIES = "required-after:dcs_lib@[3.3.1,);required:dcs_climate";

    @SidedProxy(
            clientSide = "com.github.iunius118.pizzamarinaraforhac.client.ClientProxy",
            serverSide = "com.github.iunius118.pizzamarinaraforhac.CommonProxy")
    public static CommonProxy proxy;

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        MinecraftForge.EVENT_BUS.register(proxy);
        MainConfig.init(event.getSuggestedConfigurationFile());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        PizzaClimateRecipe.load();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        String pizzaName = "pizza";
        Item pizzaItem = new PizzaItem(false).setRegistryName(pizzaName).setUnlocalizedName(MOD_ID + "." + pizzaName).setCreativeTab(CreativeTabs.FOOD);

        event.getRegistry().registerAll(
                pizzaItem
        );

        DispenseEntityItem.getInstance().dispenceList.add(pizzaItem);
    }

    private static int entityCount = 0;

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        event.getRegistry().registerAll(
                EntityEntryBuilder.create()
                        .entity(PizzaMarinaraEntity.class)
                        .id(MOD_ID + "pizza_marinara", entityCount++)
                        .name(MOD_ID + ".pizza_marinara")
                        .tracker(128, 5, true)
                        .build()
        );
    }
}
