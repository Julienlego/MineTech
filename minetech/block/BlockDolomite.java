package mod.minetech.block;
import java.util.Random;

import mod.minetech.MTCore;
import net.minecraft.block.material.Material;

public class BlockDolomite extends GenericBlock{
    
    public BlockDolomite(int i, int j){
        super(i,Material.rock);
    }
    
    //define what block is dropped when you mine the ore
    public int idDropped(int i, Random random, int j){
        return MTCore.magnesiumDust.itemID;//drops dust
    }
    
        //determines how many items should be dropped when mined
    public int quantityDropped(Random random){
        return 4;
    }
}