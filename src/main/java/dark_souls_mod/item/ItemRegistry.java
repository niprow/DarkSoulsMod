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
    public static final Item TEST_ITEM = register(new ResourceLocation("jfjf"), new BlockItem(BlockRegistry.TEST_BLOCK, new Item.Properties()));
    public static final Item FULL_ESTUS_FLASK = register(Constants.FULL_ESTUS_FLASK, new FullEstusFlask());
    public static final Item EMPTY_ESTUS_FLASK = register(Constants.EMPTY_ESTUS_FLASK, new EmptyEstusFlask());

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
