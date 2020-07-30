package com.github.iunius118.pizzamarinaraforhac.config;

import com.github.iunius118.pizzamarinaraforhac.PizzaMarinaraForHaC;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class MainConfig {
    public static Property pizzaMarinaraRequiresSeedOil;
    public static Property debug;

    public static void init(File file) {
        Configuration cfg = new Configuration(file);

        cfg.load();

        pizzaMarinaraRequiresSeedOil = cfg.get("general", "PizzaMarinaraRequiresSeedOil", true, "Is Seed Oil Pack required to craft Pizza Marinara.");
        debug = cfg.get("general", "debug", false, "Log debug massages.");

        cfg.save();

        if (debug.getBoolean()) PizzaMarinaraForHaC.logger.info("PizzaMarinaraRequiresSeedOil: {}", pizzaMarinaraRequiresSeedOil.getBoolean());
    }
}
