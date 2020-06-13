package dark_souls_mod.item;

import com.google.common.collect.Lists;
import dark_souls_mod.Constants;
import dark_souls_mod.block.BlockRegistry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class ItemRegistry {
    /*-------------------------------- constants --------------------------------*/
    public static final List<Item> ITEMS = Lists.newArrayList();
    public static final Item BONFIRE = register(Constants.BONFIRE, new BlockItem(BlockRegistry.BONFIRE, new Item.Properties().maxStackSize(1)));

    /*-------------------------------- automation --------------------------------*/
    public static Item register(ResourceLocation tag, Item item) {
        item.setRegistryName(tag);
        ITEMS.add(item);
        return item;
    }
    public static Item[] getAll() {
        return ITEMS.toArray(new Item[0]);
    }
}
