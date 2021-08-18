package io.github.icrazyblaze.beefyupdate.item;

import com.google.common.primitives.Ints;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Collection;

import static io.github.icrazyblaze.beefyupdate.util.EffectInstanceHelper.effect;

public class RedstoneBeefItem extends Item {

    public RedstoneBeefItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {

        if (livingEntity instanceof ServerPlayer player) {

            Collection<MobEffectInstance> effects = player.getActiveEffects();

            for (int i = 0; i < effects.size(); i++) {
                // Get the current effect instance
                MobEffectInstance e = (MobEffectInstance) effects.toArray()[i];

                int newDuration = e.getDuration();

                // Clamp the duration to a max of 10 minutes
                if (newDuration < 12000) {
                    newDuration = Ints.constrainToRange((int) (e.getDuration() * 1.2), 1, 12000);
                }

                // Get the same effect but with longer duration
                MobEffectInstance newEffect = effect(e.getEffect(), newDuration, e.getAmplifier());
                // Remove the old effect and add the new one
                player.removeEffect(e.getEffect());
                player.addEffect(newEffect);

            }
            level.playSound(null, player.blockPosition(), SoundEvents.BREWING_STAND_BREW, SoundSource.PLAYERS, 1.0F, 1.0F);
            player.getCooldowns().addCooldown(this, 40);
        }
        super.finishUsingItem(itemStack, level, livingEntity);
        return itemStack;
    }

}