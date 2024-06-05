package net.luka.ll.item;

import net.luka.ll.Ll;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Ll.MOD_ID);


    public static final RegistryObject<Item> zircon = ITEMS.register("zircon",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.Custom_tab)));
        public static final RegistryObject<Item> raw_zircon = ITEMS.register("raw_zircon",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.Custom_tab)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
