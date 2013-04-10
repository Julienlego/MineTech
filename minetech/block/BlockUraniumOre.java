package mods.minetech.block;
import java.util.Random;

import mods.minetech.MTCore;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BlockUraniumOre extends GenericBlock{
    
    public BlockUraniumOre(int i){
        super(i, Material.rock);
    }
    
    //define what block is dropped when you mine the ore
    public int idDropped(int i, Random random){
        return MTCore.uraninite.blockID;//drops the ore block
    }
    
    /*public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
            float f = 0.0625F;
            return AxisAlignedBB.getBoundingBoxFromPool((float)i + f, j, (float)k + f, (float)(i + 1) - f, (float)(j + 1) - f, (float)(k + 1) - f);
    }*/
    
    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity){
        entity.attackEntityFrom(DamageSource.cactus, 1);
        entity.isBurning();
    }
}