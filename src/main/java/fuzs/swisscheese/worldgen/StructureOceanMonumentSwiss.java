package fuzs.swisscheese.worldgen;

import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.StructureOceanMonument;

import java.util.Map;
import java.util.Random;

public class StructureOceanMonumentSwiss extends StructureOceanMonument {
    private int spacing;
    private int separation;

    public StructureOceanMonumentSwiss()
    {
        this.spacing = 32;
        this.separation = 5;
    }

    public StructureOceanMonumentSwiss(Map<String, String> p_i45608_1_)
    {
        this();

        for (Map.Entry<String, String> entry : p_i45608_1_.entrySet())
        {
            if (((String)entry.getKey()).equals("spacing"))
            {
                this.spacing = MathHelper.getInt(entry.getValue(), this.spacing, 1);
            }
            else if (((String)entry.getKey()).equals("separation"))
            {
                this.separation = MathHelper.getInt(entry.getValue(), this.separation, 1);
            }
        }
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0)
        {
            chunkX -= this.spacing - 1;
        }

        if (chunkZ < 0)
        {
            chunkZ -= this.spacing - 1;
        }

        int k = chunkX / this.spacing;
        int l = chunkZ / this.spacing;
        Random random = this.world.setRandomSeed(k, l, 10387313);
        k = k * this.spacing;
        l = l * this.spacing;
        k = k + (random.nextInt(this.spacing - this.separation) + random.nextInt(this.spacing - this.separation)) / 2;
        l = l + (random.nextInt(this.spacing - this.separation) + random.nextInt(this.spacing - this.separation)) / 2;

        if (i == k && j == l)
        {
            if (this.world.getBiomeProvider().getBiome(new BlockPos(i * 16 + 8, 64, j * 16 + 8)) != Biomes.DEEP_OCEAN)
            {
                return false;
            }

            boolean flag = this.world.getBiomeProvider().areBiomesViable(i * 16 + 8, j * 16 + 8, 29, WATER_BIOMES);

            if (flag)
            {
                return true;
            }
        }

        return false;
    }

}
