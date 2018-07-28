package fuzs.swisscheese;

import fuzs.swisscheese.config.ConfigHandler;
import fuzs.swisscheese.worldgen.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;
import java.util.Random;

@Mod(modid = SwissCheese.MODID, name = SwissCheese.NAME, version = SwissCheese.VERSION, acceptedMinecraftVersions = SwissCheese.AVERSIONS, acceptableRemoteVersions = SwissCheese.REMOTE, guiFactory = SwissCheese.GUI)
public class SwissCheese
{
    public static final String MODID = "swisscheese";
    public static final String NAME = "Swiss Cheese";
    public static final String VERSION = "1.1";
    public static final String AVERSIONS = "[1.12,1.12.2]";
    public static final String REMOTE = "*";
    public static final String GUI = "fuzs.swisscheese.config.GuiFactory";

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        ConfigHandler.init(new File(event.getModConfigurationDirectory(), MODID + ".cfg"));
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.TERRAIN_GEN_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onInitMapGen(InitMapGenEvent event) {
        if (event.getType() == InitMapGenEvent.EventType.CAVE && ConfigHandler.denseCaves) {
            event.setNewGen(new MapGenCavesSwiss());
        }
        if (event.getType() == InitMapGenEvent.EventType.MINESHAFT && ConfigHandler.commonMineshafts) {
            event.setNewGen(new MapGenMineshaftSwiss());
        }
        if (event.getType() == InitMapGenEvent.EventType.RAVINE && ConfigHandler.surfaceRavines) {
            event.setNewGen(new MapGenRavineSwiss());
        }
        if (event.getType() == InitMapGenEvent.EventType.NETHER_CAVE && ConfigHandler.netherRavines) {
            event.setNewGen(new MapGenCavesHellSwiss());
        }
        if (event.getType() == InitMapGenEvent.EventType.OCEAN_MONUMENT && ConfigHandler.commonMonuments) {
            event.setNewGen(new StructureOceanMonumentSwiss());
        }
    }

    @SubscribeEvent
    public void replaceBiomeBlocks(ChunkGeneratorEvent.ReplaceBiomeBlocks event) {
        if (ConfigHandler.netherRavines) {
            final MapGenRavineHell ravine = new MapGenRavineHell();
            ravine.generate(event.getWorld(), event.getX(), event.getZ(), event.getPrimer(), new Random(event.getWorld().getSeed()));
        }
    }
}
