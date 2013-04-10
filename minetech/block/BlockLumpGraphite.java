package mods.minetech.block;
import java.util.Random;

import mods.minetech.MTCore;
import net.minecraft.block.material.Material;

public class BlockLumpGraphite extends GenericBlock{
    
    public BlockLumpGraphite(int i){
        super(i,Material.rock);
    }
    
    //define what block is dropped when you mine the ore
    public int idDropped(int i, Random random, int j){
        return MTCore.graphiteFlakes.itemID;//drops the ore block
    }
    
    //determines how many items should be dropped when mined
    public int quantityDropped(Random random){
    	return 3 + random.nextInt(5);
    }
}