package electroblob.wizardry.item;

import electroblob.wizardry.entity.projectile.EntitySparkBomb;
import electroblob.wizardry.registry.WizardrySounds;
import electroblob.wizardry.registry.WizardryTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemSparkBomb extends Item {

	public ItemSparkBomb(){
		setMaxStackSize(16);
		setCreativeTab(WizardryTabs.WIZARDRY);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand){

		ItemStack stack = player.getHeldItem(hand);

		if(!player.isCreative()){
			stack.shrink(1);
		}

		player.playSound(WizardrySounds.ENTITY_SPARK_BOMB_THROW, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

		if(!world.isRemote){
			EntitySparkBomb sparkBomb = new EntitySparkBomb(world);
			sparkBomb.aim(player, 1);
			world.spawnEntity(sparkBomb);
		}

		return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
	}
	
}