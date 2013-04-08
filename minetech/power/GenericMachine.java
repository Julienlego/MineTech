package mod.minetech.power;

import mod.minetech.block.GenericBlock;
import net.minecraft.block.material.Material;

public class GenericMachine extends GenericBlock {
	
	private int outputVolts;
	private int outputAmps;
	
	public GenericMachine(int par1){
		super(par1, Material.iron);
	}
	
	public int getOutputVolts(){
		return outputVolts;
	}
	
	public int getOutputAmps(){
		return outputAmps;
	}

}
