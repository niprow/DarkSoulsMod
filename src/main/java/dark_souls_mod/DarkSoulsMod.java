package dark_souls_mod;

import dark_souls_mod.block.BlockRegistry;
import dark_souls_mod.item.ItemRegistry;
import dark_souls_mod.tileentity.TileEntityTypeRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class DarkSoulsMod {
    public DarkSoulsMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupCommon);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);

        MinecraftForge.EVENT_BUS.register(ForgeEvents.class);
        FMLJavaModLoadingContext.get().getModEventBus().register(ModEvents.class);
    }

    /**
     * common_pre_init
     */
    private void setupCommon(final FMLCommonSetupEvent event) {
    }

    /**
     * client_pre_init
     */
    private void setupClient(final FMLClientSetupEvent event) {
    }

    public static class ForgeEvents {
    }

    public static class ModEvents {
        @SubscribeEvent
        public static void onBlockRegistry(final RegistryEvent.Register<Block> event) {
            event.getRegistry().registerAll(BlockRegistry.getAll());
        }

        @SubscribeEvent
        public static void onItemRegistry(final RegistryEvent.Register<Item> event) {
            event.getRegistry().registerAll(ItemRegistry.getAll());
        }

        @SubscribeEvent
        public static void onTileEntityTypeRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
            event.getRegistry().registerAll(TileEntityTypeRegistry.getAll());
        }
    }
}
