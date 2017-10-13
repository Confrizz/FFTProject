import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.util.Arrays;

public class FileFFT {
    private static int numOfSamples;

    private File file = null;
    private short[] data;
    private DoubleFFT_1D fft;

    public FileFFT(File file, int numOfSamples) throws IOException, UnsupportedAudioFileException {
        this.file = file;
        this.numOfSamples = numOfSamples;
        //Move method statements to Main class
        init();
        calculateFFT();
    }

    private void init() throws IOException, UnsupportedAudioFileException {
        //Remove from init, in case we have more than one file NOTE: unless you want to make a new object every time
        this.data = Util.toShortArray(Util.stereoByteArrayToMonoByteArray(Util.fileToByteArray(file)));
    }

    private void calculateFFT() throws IOException {
        double[] fftData = new double[numOfSamples * 2];
        fft = new DoubleFFT_1D(numOfSamples);
        double[] frequencyBands = new double[3];
        BufferedWriter outputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("FreqBands.txt"), "UTF-8"));
        for (int k = 0; k < data.length / numOfSamples; k++) {
            for (int i = 0; i < numOfSamples; i++) {
                int j = i;
                fftData[2 * i] = (double) (this.data[i + k * numOfSamples]) * 0.5 * (1.0 - Math.cos(2.0 * Math.PI * j / numOfSamples)); //Hann Window  * 0.5 * (1.0 - Math.cos(2.0 * Math.PI * j / numOfSamples))
                fftData[2 * i + 1] = 0;
            }
            fft.complexForward(fftData);

            for (int i = 0; i < frequencyBands.length; i++) {
                for (int j = 0; j < fftData.length; j += (fftData.length / frequencyBands.length * i) + 2) {
                    frequencyBands[i] += Math.sqrt(fftData[j] * fftData[j] + fftData[j + 1] * fftData[j + 1]) / (numOfSamples / (2 * frequencyBands.length));
                }
            }
            outputStream.write(Arrays.toString(frequencyBands));
            outputStream.newLine();
            outputStream.flush();

            for (int i = 0; i < frequencyBands.length; i++) {
                frequencyBands[i] = 0;
            }
        }
        outputStream.close();
    }

    public static int getNumOfSamples() {
        return numOfSamples;
    }
}