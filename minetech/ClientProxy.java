package mod.minetech;

import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void registerRenderers(){
		MinecraftForgeClient.preloadTexture(Blocks_PNG);
		MinecraftForgeClient.preloadTexture(Items_PNG);
	}
}
