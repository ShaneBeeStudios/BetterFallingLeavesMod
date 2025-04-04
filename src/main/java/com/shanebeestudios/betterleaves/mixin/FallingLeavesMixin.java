package com.shanebeestudios.betterleaves.mixin;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.LightTexture;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FallingLeavesParticle.class)
public abstract class FallingLeavesMixin extends Particle {

    protected FallingLeavesMixin(ClientLevel clientLevel, double d, double e, double f) {
        super(clientLevel, d, e, f);
    }

    @Override
    public void move(double d, double e, double f) {
        super.move(d, e, f);
        this.onGround = false;
        this.hasPhysics = false;
    }

    @Override
    protected int getLightColor(float f) {
        return LightTexture.FULL_BRIGHT;
    }

}
