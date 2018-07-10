package fuzs.swisscheese;

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
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

@Mod(modid = SwissCheese.MODID, name = SwissCheese.NAME, version = SwissCheese.VERSION, acceptableRemoteVersions = SwissCheese.REMOTE)
public class SwissCheese
{
    public static final String MODID = "swisscheese";
    public static final String NAME = "Swiss Cheese";
    public static final String VERSION = "1.0.1";
    public static final String REMOTE = "*";

    private final MapGenRavineHell ravine = new MapGenRavineHell();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.TERRAIN_GEN_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onInitMapGen(InitMapGenEvent event) {
        if(event.getType() == InitMapGenEvent.EventType.CAVE)
            event.setNewGen(new MapGenCavesSwiss());
        if(event.getType() == InitMapGenEvent.EventType.MINESHAFT)
            event.setNewGen(new MapGenMineshaftSwiss());
        if(event.getType() == InitMapGenEvent.EventType.RAVINE)
            event.setNewGen(new MapGenRavineSwiss());
        if(event.getType() == InitMapGenEvent.EventType.NETHER_CAVE)
            event.setNewGen(new MapGenCavesHellSwiss());
    }

    @SubscribeEvent
    public void onReplaceBiomeBlocks(ChunkGeneratorEvent.ReplaceBiomeBlocks event) {
            ravine.generate(event.getWorld(), event.getX(), event.getZ(), event.getPrimer(), new Random(event.getWorld().getSeed()));
    }
}
