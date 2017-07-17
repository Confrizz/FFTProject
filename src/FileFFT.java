import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class FileFFT {
    private File file = null;
    private AudioInputStream audioInputStream;
    private double[] data;
    private DoubleFFT_1D fft;
    private int numOfSamples;

    public FileFFT(File file) throws IOException, UnsupportedAudioFileException {
        this.file = file;
        init();
        calculateFFT();
    }

    private void init() throws IOException, UnsupportedAudioFileException {
        this.data = DoubleByteConverter.toDoubleArray(DoubleByteConverter.fileToByteArray(file));
    }

    private void calculateFFT() throws IOException {
        numOfSamples = 2048;
        double[] fftData = new double[numOfSamples * 2];
        for (int i = 0; i < numOfSamples; i++) {
            fftData[2 * i] = (this.data[i] * 0.5 * (1.0 - Math.cos(2.0 * Math.PI * i / numOfSamples))) / Math.pow(10, 300); //Hann Window
            fftData[2 * i + 1] = 0;
        }
        fft = new DoubleFFT_1D(numOfSamples);
        fft.complexForward(fftData);

        BufferedWriter outputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("FreqBands.txt"), "UTF-8"));
        double[] frequencyBands = new double[3];
        for (int i = 0; i < frequencyBands.length; i++) {
            for (int j = 0; j < fftData.length; j += fftData.length / frequencyBands.length * i + 2) {
                frequencyBands[i] += Math.sqrt(fftData[j] * fftData[j] + fftData[j + 1] * fftData[j + 1]) / 3;
            }
        }
        outputStream.write(Arrays.toString(frequencyBands));
        outputStream.flush();
    }
}
