import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.util.Arrays;

public class FileFFT {
    private File file = null;
    private double[] data;
    private DoubleFFT_1D fft;
    private static int numOfSamples;

    public FileFFT(File file) throws IOException, UnsupportedAudioFileException {
        this.file = file;
        init();
        calculateFFT();
    }

    private void init() throws IOException, UnsupportedAudioFileException {
        this.data = CustomUtilities.toDoubleArray(CustomUtilities.fileToByteArray(file));
    }

    private void calculateFFT() throws IOException {
        numOfSamples = 2048;
        double[] fftData = new double[numOfSamples * 2];
        fft = new DoubleFFT_1D(numOfSamples);
        double[] frequencyBands = new double[3];
        BufferedWriter outputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("FreqBands.txt"), "UTF-8"));

        for (int k = 0; k < data.length / numOfSamples; k++) {
            for (int i = 0; i < numOfSamples; i++) {
                int j = i;
                fftData[2 * i] = (this.data[i + k * numOfSamples]) * 0.5 * (1.0 - Math.cos(2.0 * Math.PI * j / numOfSamples)) / Math.pow(10, 300); //Hann Window  * 0.5 * (1.0 - Math.cos(2.0 * Math.PI * j / numOfSamples))
                fftData[2 * i + 1] = 0;
            }

            int zeroCount = 0;
            for (int i = numOfSamples * k; i < numOfSamples * (k + 1); i++) {
                if (data[i] == 0) {
                    zeroCount++;
                }
            }
            System.out.println(zeroCount);

            fft.complexForward(fftData);
            for (int i = 0; i < frequencyBands.length; i++) {
                for (int j = 0; j < fftData.length; j += fftData.length / frequencyBands.length * i + 2) {
                    frequencyBands[i] += Math.sqrt(fftData[j] * fftData[j] + fftData[j + 1] * fftData[j + 1]) / 3;
                }
            }
            outputStream.write(Arrays.toString(frequencyBands));
            outputStream.newLine();
            outputStream.flush();
        }
        outputStream.close();
    }

    public static int getNumOfSamples() {
        return numOfSamples;
    }
}