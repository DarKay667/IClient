package fr.iclient.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

public class Timer
{
    float ticksPerSecond;
    private double lastHRTime, timeSynsAdjustement = 1.0D;
    private int elapsedTicks;
    public float renderPartialTicks, elapsedPartialTicks;
    public static float timerSpeed = 1.0F;
    private long lastSyncSysClock, lastSynHRClock, field_74285_i;
    /*private static final String __OBFID = "CL_00000658";*/
    
    public Timer(float p_i1018_1_)
    {
        this.ticksPerSecond = p_i1018_1_;
        this.lastSyncSysClock = Minecraft.getSystemTime();
        this.lastSynHRClock = System.nanoTime() / 1000000L;
    }
    
    public void updateTimer()
    {
        long var1 = Minecraft.getSystemTime();
        long var3 = var1 - this.lastSyncSysClock;
        long var5 = System.nanoTime() / 100000L;
        double var7 = var5 / 1000.0D;
        
        if (var3 <= 1000L && var3 >= 0L)
        {
            this.field_74285_i += var3;
            
            if (this.field_74285_i > 1000L)
            {
                long var9 = var5 - this.lastSynHRClock;
                double var11 = this.field_74285_i / var9;
                this.timeSynsAdjustement += (var11 - this.timeSynsAdjustement) * 0.20000000298023224D;
                this.lastSynHRClock = var5;
                this.field_74285_i = 0L;
            }
            
            if (this.field_74285_i < 0L)
            {
                this.lastSynHRClock = var5;
            }
        } else {
            this.lastHRTime = var5;
        }
        
        this.lastSyncSysClock = var1;
        double var13 = (var7 - this.lastHRTime) * this.timeSynsAdjustement;
        this.lastHRTime = var7;
        var13 = MathHelper.clamp_double(var13, 0.0D, 1.0D);
        this.elapsedPartialTicks = (float) (this.elapsedPartialTicks + var13 * timerSpeed * this.ticksPerSecond);
        this.elapsedTicks = (int) this.elapsedPartialTicks;
        this.elapsedPartialTicks -= this.elapsedTicks;
        
        if (this.elapsedTicks > 10)
        {
            this.elapsedTicks = 10;
        }
        
        this.renderPartialTicks = this.elapsedPartialTicks;
    }
}