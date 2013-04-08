package mod.minetech.items;

import mod.minetech.entities.EntityNitroGlycerin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemNitroGlycerin extends GenericItem{
	
	public ItemNitroGlycerin(int itemIndex){
		super(itemIndex);
		maxStackSize = 32;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
		if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
			par1ItemStack.stackSize--;
        }
		
		if(!par2World.isRemote){
			par2World.spawnEntityInWorld(new EntityNitroGlycerin(par2World, par3EntityPlayer));
		}
		
		return par1ItemStack;
	}

}
