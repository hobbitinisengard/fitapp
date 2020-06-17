package fa.fitapp;

import android.os.CountDownTimer;

public abstract class CountUpTimer extends CountDownTimer {
    private static final long INTERVAL_MS = 1000;
    private final long duration;
    private long AdditionalTime = 0;

    protected CountUpTimer(long durationMs) {
        super(durationMs, INTERVAL_MS);
        this.duration = durationMs;
    }
    protected CountUpTimer(long durationMs, long AdditionalTime) {
        super(durationMs, INTERVAL_MS);
        this.duration = durationMs;
        this.AdditionalTime = AdditionalTime;
    }
    public abstract void onTick(int second);

    @Override
    public void onTick(long msUntilFinished) {
        int second = (int) ((AdditionalTime + duration - msUntilFinished) / 1000);
        onTick(second);
    }

    @Override
    public void onFinish() {
        onTick(duration / 1000);
    }
}