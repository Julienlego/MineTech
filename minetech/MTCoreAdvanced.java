package mod.minetech;

import mod.minetech.items.GenericItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MTCoreAdvanced {
	
	//public static final Item copperWireItem = new ItemCopperWire(716).setIconCoord(7,3).setCreativeTab(CreativeTabs.tabRedstone).setItemName("Copper Wire");
    //public static final Item aluminiumWireItem = new ItemAluminiumWire(717).setIconCoord(6,3).setCreativeTab(CreativeTabs.tabRedstone).setItemName("Aluminium Wire");
    public static final Item twostrokeEngine = new GenericItem(718).setUnlocalizedName(6,2).setMaxStackSize(1).setCreativeTab(CreativeTabs.tabMisc).setItemName("Two-stroke Engine");
    public static final Item alternator = new GenericItem(719).setUnlocalizedName(7,2).setMaxStackSize(16).setCreativeTab(CreativeTabs.tabMisc).setItemName("Alternator");
    public static final Item electricMotor = new GenericItem(720).setUnlocalizedName(9,2).setMaxStackSize(32).setCreativeTab(CreativeTabs.tabMisc).setItemName("Electric Motor");
    public static final Item aluminiumCasing = new GenericItem(721).setUnlocalizedName(8,2).setCreativeTab(CreativeTabs.tabMisc).setItemName("Aluminium Casing");
	
	/*public static final Block copperWire;
    public static final Block aluminiumWire;
    public static final Block combGenOn;
    public static final Block combGenOff;
    public static final Block geoGenOn;
    public static final Block geoGenOff;
    public static final Block transformer;
    public static final Block inductionFurnaceOn;
    public static final Block inductionFurnaceOff;
    */
}
