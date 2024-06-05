package net.luka.ll.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {

    public static final CreativeModeTab Custom_tab = new CreativeModeTab("lltab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.zircon.get());
        }
    };
}
