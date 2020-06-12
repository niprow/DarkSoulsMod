package dark_souls_mod.item;

import com.google.common.collect.Lists;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class ItemRegistry {
    /*-------------------------------- constants --------------------------------*/

    /*-------------------------------- automation --------------------------------*/
    public static final List<Item> ITEMS = Lists.newArrayList();
    public static Item register(ResourceLocation tag, Item item) {
        item.setRegistryName(tag);
        ITEMS.add(item);
        return item;
    }
    public static Item[] getAll() {
        return ITEMS.toArray(new Item[0]);
    }
}
