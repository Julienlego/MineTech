package mod.minetech.block;

import java.util.Random;

import mod.minetech.MTCore;
import net.minecraft.block.material.Material;

public class BlockMagnesiumBrick extends GenericBlock{
	
	public BlockMagnesiumBrick(int i){
        super(i,Material.iron);
    }
    
    //define what block is dropped when you mine the ore
    public int idDropped(int i, Random random){
        return MTCore.magnesiumBrick.blockID;
    }
}
