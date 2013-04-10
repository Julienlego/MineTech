package mods.minetech.block;

import java.util.Random;

import mods.minetech.MTCore;
import net.minecraft.block.material.Material;

public class BlockMagnesium extends GenericBlock{
	
	public BlockMagnesium(int i){
        super(i,Material.ground);
    }
    
    //define what block is dropped when you mine the ore
    public int idDropped(int i, Random random){
        return MTCore.magnesiumBlock.blockID;
    }
    
    public int quantityDropped(Random random){
        return 1;
    }
}
