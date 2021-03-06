package io.github.icrazyblaze.beefyupdate.util;

import com.google.gson.JsonObject;
import io.github.icrazyblaze.beefyupdate.init.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;

public class CowLootModifier extends LootModifier {

    protected CowLootModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(new ItemStack(ModItems.ANTIBEEF.get(), 1));
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<CowLootModifier> {
        @Override
        public CowLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] conditions) {
            return new CowLootModifier(conditions);
        }

        @Override
        public JsonObject write(CowLootModifier instance) {
            return this.makeConditions(instance.conditions);
        }
    }

}
