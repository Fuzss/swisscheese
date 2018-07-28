package fuzs.swisscheese.config;

import java.io.File;

import fuzs.swisscheese.SwissCheese;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class ConfigHandler {
    public static Configuration config;
    public static String categoryGeneral = "general";
    public static boolean denseCaves;
    public static boolean sandstoneEntrances;
    public static boolean commonMineshafts;
    public static boolean aquaticCavities;
    public static boolean surfaceRavines;
    public static boolean netherRavines;
    public static boolean commonMonuments;

    public static void init(File configFile) {
        if (config == null) {
            config = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration() {
        config.getCategory(categoryGeneral);
        denseCaves = config.getBoolean("Dense Caves", categoryGeneral, true, "Cave systems are more dense.");
        sandstoneEntrances = config.getBoolean("Sandstone Entrances", categoryGeneral, true, "Sandstone replaces possible sand at cave entrances to prevent it from floating.");
        commonMineshafts = config.getBoolean("Common Mineshafts", categoryGeneral, true, "Mineshafts are 2.5x more common.");
        aquaticCavities = config.getBoolean("Aquatic Cavities", categoryGeneral, false, "Cavities in ocean biomes are filled with water, and they are able to break through the ocean floor.");
        surfaceRavines = config.getBoolean("Surface Ravines", categoryGeneral, true, "Ravines are able to replace more types of blocks so they can reach the surface more often.");
        netherRavines = config.getBoolean("Nether Ravines", categoryGeneral, true, "Ravines generate in the Nether.");
        commonMonuments = config.getBoolean("Common Monuments", categoryGeneral, true, "Ocean monuments only require a single deep ocean block instead of a 33 block radius.");

        if (config.hasChanged()) {
            config.save();
        }
    }

    @SubscribeEvent
    public static void onConfigurationChanged(OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(SwissCheese.MODID)) {
            loadConfiguration();
        }
    }
}
