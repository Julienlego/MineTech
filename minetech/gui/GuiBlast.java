package mods.minetech.gui;
import mods.minetech.container.ContainerBlast;
import mods.minetech.tileentities.TileEntityBlast;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBlast extends GuiContainer implements IGuiHandler
{
    private TileEntityBlast blastInventory;
    
    public GuiBlast(InventoryPlayer inventoryplayer, TileEntityBlast tileentityblast)
    {
        super(new ContainerBlast(inventoryplayer, tileentityblast));
        this.blastInventory = tileentityblast;
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2)
    {
        fontRenderer.drawString("Blast", 60, 6, 0x404040);
        fontRenderer.drawString("Inventory", 8, (this.ySize - 96) + 2, 0x404040);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.func_98187_b("/mining/blastFurnaceGUI.png");
        int l = (this.width - this.xSize) / 2;
        int i1 = (this.height - this.ySize) / 2;
        drawTexturedModalRect(l, i1, 0, 0, this.xSize, this.ySize);
        if(this.blastInventory.isBurning())
        {
            int j1 = this.blastInventory.getBurnTimeRemainingScaled(12);
            drawTexturedModalRect(l + 56, (i1 + 36 + 12) - j1, 176, 12 - j1, 14, j1 + 2);
        }
        int k1 = this.blastInventory.getCookProgressScaled(24);
        drawTexturedModalRect(l + 79, i1 + 34, 176, 14, k1 + 1, 16);
    }
    
  //returns an instance of the Container you made earlier
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world,
                    int x, int y, int z) {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if(tileEntity instanceof TileEntityBlast){
                    return new ContainerBlast(player.inventory, (TileEntityBlast) tileEntity);
            }
            return null;
    }

    //returns an instance of the Gui you made earlier
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world,
                    int x, int y, int z) {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if(tileEntity instanceof TileEntityBlast){
                    return new GuiBlast(player.inventory, (TileEntityBlast) tileEntity);
            }
            return null;
    }
}
