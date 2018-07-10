package fuzs.swisscheese.worldgen;

import net.minecraft.world.gen.structure.MapGenMineshaft;

public class MapGenMineshaftSwiss extends MapGenMineshaft {

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        double chance2 = 0.01D;
        return this.rand.nextDouble() < chance2 && this.rand.nextInt(80) < Math.max(Math.abs(chunkX), Math.abs(chunkZ));
    }
}
