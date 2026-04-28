package com.shanebeestudios.betterleaves.mixin;

import com.shanebeestudios.betterleaves.BetterFallingLeaves;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.client.particle.Particle;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FallingLeavesParticle.class)
public abstract class FallingLeavesMixin extends Particle {

    private int countdown = 20;

    protected FallingLeavesMixin(ClientLevel clientLevel, double d, double e, double f) {
        super(clientLevel, d, e, f);
    }

    @Override
    public void move(double d, double e, double f) {
        super.move(d, e, f);
        if (BetterFallingLeaves.IS_BETTER_FALLING_LEAVES) {
            if (this.onGround || this.countdown < 20) {
                this.countdown--;
                this.onGround = false;
                this.hasPhysics = false;
                if (this.countdown <= 0) {
                    this.remove();
                }
            }
        }
    }

}
