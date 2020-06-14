package dark_souls_mod.item;

import dark_souls_mod.capability.PlayerCapability;
import dark_souls_mod.item.food.Foods;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class FullEstusFlask extends Item {
    public FullEstusFlask() {
        super(new Item.Properties()
                .maxStackSize(15)
                .containerItem(ItemRegistry.EMPTY_ESTUS_FLASK)
                .food(Foods.FULL_ESTUS_FLASK)
        );
    }

    @Nonnull
    @Override
    public ItemStack onItemUseFinish(@Nonnull ItemStack stack, @Nonnull World world, @Nonnull LivingEntity living_entity) {
        if(living_entity instanceof PlayerEntity) {
            if(living_entity instanceof ServerPlayerEntity) {
                living_entity.heal(6 + 60 * PlayerCapability.getInstance((PlayerEntity) living_entity).getEstusFactor());
            }
            if(!((PlayerEntity) living_entity).inventory.addItemStackToInventory(new ItemStack(ItemRegistry.EMPTY_ESTUS_FLASK))) {
                ((PlayerEntity) living_entity).dropItem(new ItemStack(ItemRegistry.EMPTY_ESTUS_FLASK), false);
            }
        }
        return super.onItemUseFinish(stack, world, living_entity);
    }
}
