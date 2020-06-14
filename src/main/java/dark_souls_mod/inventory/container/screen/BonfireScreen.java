package dark_souls_mod.inventory.container.screen;

import dark_souls_mod.inventory.container.BonfireContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class BonfireScreen extends ContainerScreen<BonfireContainer> {

    public BonfireScreen(BonfireContainer screen_container, PlayerInventory inv, ITextComponent title) {
        super(screen_container, inv, title);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }
}
