package mods.minetech.gui;

import mods.minetech.container.*;
import mods.minetech.tileentities.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiManager implements IGuiHandler{
	
	/**
	 * Returns the Gui linked with the guiID
	 * Par: guiID, entityPlayer
	 */
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
	    TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
	    if(tileEntity instanceof TileEntityBioCarbonizer){
	    	return new ContainerBioCarbonizer(player.inventory, (TileEntityBioCarbonizer)tileEntity);
	    }else if(tileEntity instanceof TileEntityBlast){
	    	return new ContainerBlast(player.inventory,(TileEntityBlast)tileEntity);
	    }else if(tileEntity instanceof TileEntityIncinerator){
	    	return new ContainerIncinerator(player.inventory,(TileEntityIncinerator)tileEntity);
	    }
    return null;
}	
		//case 0: return new GuiBioCarbonizer(player.inventory,(TileEntityBioCarbonizer)tileentity);
		//case 1: return new GuiBlast(player.inventory,(TileEntityBlast)tileentity);
		//case 2: return new GuiIncinerator(player.inventory,(TileEntityIncinerator)tileentity);
	
	/**
	 * Returns the Container linked with the guiID
	 * Par: guiID, entityPlayer
	 */
	@Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world,int x, int y, int z) {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if(tileEntity instanceof TileEntityBioCarbonizer){
                    return new GuiBioCarbonizer(player.inventory, (TileEntityBioCarbonizer) tileEntity);
            }else if(tileEntity instanceof TileEntityBlast){
    	    	return new GuiBlast(player.inventory,(TileEntityBlast)tileEntity);
    	    }else if(tileEntity instanceof TileEntityIncinerator){
    	    	return new GuiIncinerator(player.inventory,(TileEntityIncinerator)tileEntity);
    	    }
            return null;

    }

}
