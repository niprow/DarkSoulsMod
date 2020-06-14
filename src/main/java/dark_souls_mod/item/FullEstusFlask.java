package dark_souls_mod.item;

import dark_souls_mod.item.food.Foods;
import net.minecraft.item.Item;

public class FullEstusFlask extends Item {
    public FullEstusFlask() {
        super(new Item.Properties()
                .maxStackSize(15)
                .containerItem(ItemRegistry.EMPTY_ESTUS_FLASK)
                .food(Foods.FULL_ESTUS_FLASK)
        );
    }
}
