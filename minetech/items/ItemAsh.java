package mods.minetech.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockStem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class ItemAsh extends GenericItem {
	
	public ItemAsh(int par1){
		super(par1);
	}
	
	/**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS !
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {

    	int l = par3World.getBlockId(par4, par5, par6);

        BonemealEvent event = new BonemealEvent(par2EntityPlayer, par3World, l, par4, par5, par6);
        if (MinecraftForge.EVENT_BUS.post(event))
        {
            return false;
        }

        if (event.getResult() == Result.ALLOW)
        {
            if (!par3World.isRemote)
            {
                par1ItemStack.stackSize--;
            }
            return true;
        }

        if (l == Block.sapling.blockID)
        {
            if (!par3World.isRemote)
            {
                if ((double)par3World.rand.nextFloat() < 0.45D)
                {
                    ((BlockSapling)Block.sapling).func_96477_c(par3World, par4, par5, par6, par3World.rand);
                }

                --par1ItemStack.stackSize;
            }

            return true;
        }
        else if (l != Block.mushroomBrown.blockID && l != Block.mushroomRed.blockID)
        {
            if (l != Block.melonStem.blockID && l != Block.pumpkinStem.blockID)
            {
                if (l > 0 && Block.blocksList[l] instanceof BlockCrops)
                {
                    if (par3World.getBlockMetadata(par4, par5, par6) == 7)
                    {
                        return false;
                    }
                    else
                    {
                        if (!par3World.isRemote)
                        {
                            ((BlockCrops)Block.blocksList[l]).fertilize(par3World, par4, par5, par6);
                            --par1ItemStack.stackSize;
                        }

                        return true;
                    }
                }
                else
                {
                    int i1;
                    int j1;
                    int k1;

                    if (l == Block.cocoaPlant.blockID)
                    {
                        i1 = par3World.getBlockMetadata(par4, par5, par6);
                        j1 = BlockDirectional.getDirection(i1);
                        k1 = BlockCocoa.func_72219_c(i1);

                        if (k1 >= 2)
                        {
                            return false;
                        }
                        else
                        {
                            if (!par3World.isRemote)
                            {
                                ++k1;
                                par3World.setBlockMetadataWithNotify(par4, par5, par6, k1 << 2 | j1, 2);
                                --par1ItemStack.stackSize;
                            }

                            return true;
                        }
                    }
                    else if (l != Block.grass.blockID)
                    {
                        return false;
                    }
                    else
                    {
                        if (!par3World.isRemote)
                        {
                            --par1ItemStack.stackSize;
                            label102:

                            for (i1 = 0; i1 < 128; ++i1)
                            {
                                j1 = par4;
                                k1 = par5 + 1;
                                int l1 = par6;

                                for (int i2 = 0; i2 < i1 / 16; ++i2)
                                {
                                    j1 += itemRand.nextInt(3) - 1;
                                    k1 += (itemRand.nextInt(3) - 1) * itemRand.nextInt(3) / 2;
                                    l1 += itemRand.nextInt(3) - 1;

                                    if (par3World.getBlockId(j1, k1 - 1, l1) != Block.grass.blockID || par3World.isBlockNormalCube(j1, k1, l1))
                                    {
                                        continue label102;
                                    }
                                }

                                if (par3World.getBlockId(j1, k1, l1) == 0)
                                {
                                    if (itemRand.nextInt(10) != 0)
                                    {
                                        if (Block.tallGrass.canBlockStay(par3World, j1, k1, l1))
                                        {
                                            par3World.setBlockAndMetadataWithNotify(j1, k1, l1, Block.tallGrass.blockID, 1, 3);
                                        }
                                    }
                                    else
                                    {
                                        ForgeHooks.plantGrass(par3World, j1, k1, l1);
                                    }
                                }
                            }
                        }

                        return true;
                    }
                }
            }
            else if (par3World.getBlockMetadata(par4, par5, par6) == 7)
            {
                return false;
            }
            else
            {
                if (!par3World.isRemote)
                {
                    ((BlockStem)Block.blocksList[l]).fertilizeStem(par3World, par4, par5, par6);
                    --par1ItemStack.stackSize;
                }

                return true;
            }
        }
        else
        {
            if (!par3World.isRemote)
            {
                if ((double)par3World.rand.nextFloat() < 0.4D)
                {
                    ((BlockMushroom)Block.blocksList[l]).fertilizeMushroom(par3World, par4, par5, par6, par3World.rand);
                }

                --par1ItemStack.stackSize;
            }

            return true;
        }
    }
}
