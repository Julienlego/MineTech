package mod.minetech.block;
import java.util.Random;

import mod.minetech.MTCore;
import net.minecraft.block.material.Material;

public class BlockBauxite extends GenericBlock{
    
    public BlockBauxite(int i){
        super(i,Material.rock);
    }
    
    //define what block is dropped when you mine the ore
    public int idDropped(int i, Random random){
        return MTCore.bauxiteOre.blockID;//drops the ore block
    }
}