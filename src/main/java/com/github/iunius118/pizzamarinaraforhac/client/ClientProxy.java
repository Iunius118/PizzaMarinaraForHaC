package com.github.iunius118.pizzamarinaraforhac.client;

import com.github.iunius118.pizzamarinaraforhac.CommonProxy;
import com.github.iunius118.pizzamarinaraforhac.PizzaMarinaraForHaC;
import com.github.iunius118.pizzamarinaraforhac.food.entity.PizzaMarinaraEntity;
import com.github.iunius118.pizzamarinaraforhac.food.entity.client.PizzaMarinaraRenderer;
import com.github.iunius118.pizzamarinaraforhac.food.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ClientProxy extends CommonProxy {
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        String modID = PizzaMarinaraForHaC.MOD_ID;

        String[] modelNames = ModItems.pizza.getNameSuffix();
        ModelLoader.setCustomModelResourceLocation(ModItems.pizza, 0,
                new ModelResourceLocation(modID + ":food/pizza_" + modelNames[0], "inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.pizza, 1,
                new ModelResourceLocation(modID + ":food/pizza_" + modelNames[1], "inventory"));

        RenderingRegistry.registerEntityRenderingHandler(PizzaMarinaraEntity.class, new PizzaMarinaraRenderer.Factory());
    }

    @Override
    public void addCloudParticle(World world, double posX, double posY, double posZ) {
        if (!initParticleFactory()) {
            return;
        }

        int particleCount = 0;
        int particleSetting = Minecraft.getMinecraft().gameSettings.particleSetting;

        switch (particleSetting) {
            case 0:
                particleCount = 12;
                break;
            case 2: // No particle
                return;
            default:
                particleCount = 6;
        }

        if (world.rand.nextInt(particleCount) == 0) {
            Particle particle = getParticle(world, posX, posY, posZ);

            if (particle != null) {
                FMLClientHandler.instance().getClient().effectRenderer.addEffect(particle);
            }
        }
    }

    private Object particleCloudDCFactory = null;
    private Method createParticle = null;
    private boolean hasInitParticleMethod = false;

    private boolean initParticleFactory() {
        if (!hasInitParticleMethod) {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();

            try {
                particleCloudDCFactory = loader.loadClass("defeatedcrow.hac.main.client.particle.ParticleCloudDC$Factory").getConstructor().newInstance();
                Class<?>[] types = {int.class, World.class, double.class, double.class, double.class, double.class, double.class, double.class, int[].class};
                createParticle = particleCloudDCFactory.getClass().getMethod("func_178902_a", types);
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }

            hasInitParticleMethod = true;
        }

        return particleCloudDCFactory != null && createParticle != null;
    }

    private Particle getParticle(World world, double posX, double posY, double posZ) {
        Object particle = null;

        double x = posX - 0.25D + world.rand.nextDouble() * 0.5D;
        double y = posY + world.rand.nextDouble() * 0.25D;
        double z = posZ - 0.25D + world.rand.nextDouble() * 0.5D;
        double dx = 0D;
        double dy = 0D;
        double dz = 0D;

        try {
            particle = createParticle.invoke(particleCloudDCFactory, 0, world, x, y, z, dx, dy, dz, null);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }

        if (particle instanceof Particle) {
            return (Particle)particle;
        }

        return null;
    }
}
