package Timing;

/**
 *  This class provides the basic functionality of a timer.
 *  For proper use, it should be used in a continuous loop with a specific frame rate.
 *  The default period is 60 frames per second (fps).
 *  @note This timer isn't reset by default, which means that it will activate in the next frame.
 *  If you want it to be active in the current frame after instantiating the class, call void resetTimer().
 */
public class Timer {
    public static final float DELTA_TIME = 0.016666668F; // default value for frame period
    private float startTime;
    private float endTime;
    private float duration; // variable for saving the timer duration

    /**
     * @param duration specific to a timer
     */
    public Timer(float duration) {
        this.duration = duration;
        this.startTime = 0.0F;
        this.endTime = 0.0F;
    }

    /**
     * @return true/false if timer hasn't/has reached to the target
     */
    public boolean getTimerState() {
        if (this.startTime >= this.endTime) {
            return false;
        } else {
            this.startTime += 0.016666668F;
            return true;
        }
    }

    /**
     * reset the startTime and endTime
     * @note : if this method isn't called, the timer functionality will stop.
     */
    public void resetTimer() {
        this.startTime = 0.0F;
        this.endTime = this.duration;
    }

    /**
     * @param newDuration the new timer target
     */
    public void changeDuration(float newDuration) {
        this.duration = newDuration;
    }

    /**
     *
     * @return current timer situation
     */
    public float getRemainingTime() {
        return this.startTime;
    }
}
