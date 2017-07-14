import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class FileFFT implements Runnable{
    private File file = null;
    private AudioInputStream audioInputStream;
    private double[] data;
    private DoubleFFT_1D fft;

    public FileFFT(File file) throws IOException, UnsupportedAudioFileException {
        this.file = file;
        init();
        calculateFFT();
    }

    private void init() throws IOException, UnsupportedAudioFileException {
        audioInputStream = AudioSystem.getAudioInputStream(file);
        byte[] bytes = new byte[(int) (audioInputStream.getFrameLength()) * (audioInputStream.getFormat().getFrameSize())];

        ByteArrayOutputStream baout = new ByteArrayOutputStream();
        int c;
        while ((c = audioInputStream.read(bytes, 0, bytes.length)) != -1) {
            baout.write(bytes, 0, c);
        }

        data = new double[bytes.length / 8];
        for (int i = 0; i < data.length; i++) {
            data[i] = ByteBuffer.wrap(bytes, i * 8, 8).getDouble();
        }
    }

    private void calculateFFT() {
        double[] fftData = new double[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            fftData[2 * i] = data[i];
            fftData[2 * i + 1] = 0;
        }
        fft = new DoubleFFT_1D(data.length);
        fft.complexForward(fftData);
    }

    @Override
    public void run() {

    }

}
