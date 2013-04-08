package mod.minetech.entities;
import mod.minetech.MTCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityStickyTorch extends EntityThrowable{
	
	@SideOnly(Side.CLIENT)
	public EntityStickyTorch(World par1World, EntityPlayer entityplayer) {
		super(par1World, entityplayer);
	}
	
    @Override
    protected void onImpact(MovingObjectPosition movingobjectposition){
        
        if(movingobjectposition.entityHit != null){
            if(!movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 0));
        }
        
        if(!worldObj.isRemote){

            byte byte0 = 1;
            if(rand.nextInt(32) == 0)
            {
                byte0 = 4;
            }
            for(int j = 0; j < byte0; j++)
            {
            	int l = (int)movingobjectposition.blockX;
                int k = (int)movingobjectposition.blockY;
                int m = (int)movingobjectposition.blockZ;
                int n = (int)movingobjectposition.sideHit;
                if (n == 0) { // -y 
                        worldObj.func_96440_m(l, k - 1, m, MTCore.stickyglowBlock.blockID);
                }
                if (n == 1) { // +y
                        worldObj.func_96440_m(l, k + 1, m, MTCore.stickyglowBlock.blockID);
                }
                if (n == 2) { // -z
                        worldObj.func_96440_m(l, k, m - 1, MTCore.stickyglowBlock.blockID);
                }
                if (n == 3) { // +z
                        worldObj.func_96440_m(l, k, m + 1, MTCore.stickyglowBlock.blockID);
                }
                if (n == 4) { // -x
                        worldObj.func_96440_m(l - 1, k, m, MTCore.stickyglowBlock.blockID);
                }
                if (n == 5) { // +x
                        worldObj.func_96440_m(l + 1, k, m, MTCore.stickyglowBlock.blockID);
                }
            }
            for (int var5 = 0; var5 < 8; ++var5)
            {
                this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            }
            if (!this.worldObj.isRemote)
            {
                this.setDead();
            }
        }
    }
}
