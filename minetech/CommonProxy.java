package mod.minetech;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler{
	public static String Blocks_PNG = "/minetech/common/MTblocks.png";
	public static String Items_PNG = "/minetech/common/MTitems.png";
	
	// Client stuff
	public void registerRenderers(){}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
	return null;
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}
}
