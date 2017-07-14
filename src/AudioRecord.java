import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;

import javax.sound.sampled.TargetDataLine;

public class AudioRecord implements Runnable{
    private final static int SAMPLERATE = 44100;
    private final static int BUFFERSIZE = 512;

    private TargetDataLine tdl;
    private DoubleFFT_1D fft;

    AudioRecord(TargetDataLine tdl) {
        this.tdl = tdl;
    }

    public void run() {

    }
}
