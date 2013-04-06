package mods.minetech.items;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GenericItem extends Item {
	
	/* location where the texture resides */
	private String texturePathName = "minetech/common: ";
	
	public GenericItem(int id){
		super(id);
	}
	
	/*public String getTextureFile(){
		return "/minetech/common/MTitems.png";
	}*/
	
	/**
	 * Sets the unlocalized name of the block
	 */
	@Override
	public Item setUnlocalizedName(String par1Str)
    {
        this.texturePathName += par1Str;
        return this;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void func_94581_a(IconRegister parIconRegister){
		this.iconIndex = parIconRegister.func_94245_a(texturePathName);
	}

}
