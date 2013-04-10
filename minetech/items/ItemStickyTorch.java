package mods.minetech.items;

import mods.minetech.entities.EntityStickyTorch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemStickyTorch extends GenericItem{
	
	public ItemStickyTorch(int i){
        super(i);
        maxStackSize = 32;
    }
	
	@Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer){
    	if (!entityplayer.capabilities.isCreativeMode)
        {
            itemstack.stackSize--;
        }
    	
        world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if(!world.isRemote)
        {
            world.spawnEntityInWorld(new EntityStickyTorch(world, entityplayer));
        	//world.spawnEntityInWorld(new EntityPig(world));
        }
        return itemstack;
    }
}
