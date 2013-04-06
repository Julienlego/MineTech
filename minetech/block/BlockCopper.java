package mods.minetech.block;
import java.util.Random;

import mods.minetech.MTCore;
import net.minecraft.block.material.Material;

public class BlockCopper extends GenericBlock{
    
    public BlockCopper(int i){
        super(i,Material.rock);
    }
    
    //define what block is dropped when you mine the ore
    public int idDropped(int i, Random random){
        return MTCore.copperOre.blockID;//drops the ore block
    }
}