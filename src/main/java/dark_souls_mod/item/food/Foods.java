package dark_souls_mod.item.food;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class Foods {
    public static final Food FULL_ESTUS_FLASK = new Food.Builder().effect(Foods::getEstusEffect, 1.0f).build();

    private static EffectInstance getEstusEffect() {
        return new EffectInstance(Effects.INSTANT_HEALTH, 0, 6);
    }
}
