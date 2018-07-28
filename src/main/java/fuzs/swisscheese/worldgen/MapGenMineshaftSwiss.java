package fuzs.swisscheese.worldgen;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.structure.MapGenMineshaft;

import java.util.Map;

public class MapGenMineshaftSwiss extends MapGenMineshaft {

    private double chance = 0.01D;

    public MapGenMineshaftSwiss()
    {
    }

    public MapGenMineshaftSwiss(Map<String, String> p_i2034_1_)
    {
        for (Map.Entry<String, String> entry : p_i2034_1_.entrySet())
        {
            if (((String)entry.getKey()).equals("chance"))
            {
                this.chance = MathHelper.getDouble(entry.getValue(), this.chance);
            }
        }
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        return this.rand.nextDouble() < chance && this.rand.nextInt(80) < Math.max(Math.abs(chunkX), Math.abs(chunkZ));
    }
}
