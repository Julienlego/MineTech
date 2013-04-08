package mod.minetech;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class MTWorldGenerator implements IWorldGenerator{
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId) {
			case 1:
				generateNether(world, random, chunkX * 16, chunkZ * 16);
				break;
			case 0:
				generateSurface(world, random, chunkX*16, chunkZ*16);
				break;
			case -1:
				generateEnd(world, random, chunkX * 16, chunkZ * 16);
				break;
		}
	}

	private void generateEnd(World world, Random random, int i, int j) {
	//we're not doing ore ore in the nether
	}

	public void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
		//generates titanium ore
        for(int x = 0; x < 1; x++){//5 ores per chunk
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(20);//measured in number of levels above bedrock
            int randPosZ = chunkZ + rand.nextInt(16);
            //tells minecraft to place ore at x,y,z position
            (new WorldGenMinable(MTCore.titaniumOre.blockID, 3)).generate(world, rand, randPosX, randPosY, randPosZ);//2 blocks per vein
        }
        //generates copper ore
        for(int x = 0; x < 15; x++){
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(40);
            int randPosZ = chunkZ + rand.nextInt(16);
            (new WorldGenMinable(MTCore.copperOre.blockID, 4)).generate(world, rand, randPosX, randPosY, randPosZ);
        }
        //generates aluminium ore
        for(int x = 0; x < 15; x++){
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(50);
            int randPosZ = chunkZ + rand.nextInt(16);
            (new WorldGenMinable(MTCore.bauxiteOre.blockID, 6)).generate(world, rand, randPosX, randPosY, randPosZ);
        }
        //generates magnesium
        for(int x = 0; x < 10; x++){
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(30);
            int randPosZ = chunkZ + rand.nextInt(16);
            (new WorldGenMinable(MTCore.dolomite.blockID, 4)).generate(world, rand, randPosX, randPosY, randPosZ);
        }
        //generates graphite
        for(int x = 0; x < 7; x++){
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(20);
            int randPosZ = chunkZ + rand.nextInt(16);
            (new WorldGenMinable(MTCore.lumpgraphite.blockID, 4)).generate(world, rand, randPosX, randPosY, randPosZ);
        }
        //generate uranitite
        for(int x = 0; x < 1; x++){
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(20);
            int randPosZ = chunkZ + rand.nextInt(16);
            (new WorldGenMinable(MTCore.uraninite.blockID, 2)).generate(world, rand, randPosX, randPosY, randPosZ);
        }
	}

	private void generateNether(World world, Random random, int i, int j) {
	//we're not going to generate in the end either
	}
	}
