import sun.audio.AudioData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PlayFFT {
    private File fftFile;
    private File audioFile;

    public PlayFFT(File audioFile, File fftFIle) throws IOException{
        this.audioFile = audioFile;
        this.fftFile = fftFile;

        play();
    }

    private void play() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("Freqbands.txt"));


        String line;
        double bandOne;
        double bandTwo;
        double bandThree;
        int comma;
        while ((line = bufferedReader.readLine()) != null) {
            bandOne = Double.parseDouble(line.substring(line.indexOf("[") + 1, (comma = line.indexOf(","))));
            bandTwo = Double.parseDouble(line.substring(comma + 1, (comma = line.indexOf(",", comma + 1))));
            bandThree = Double.parseDouble(line.substring(comma + 1, line.indexOf("]")));
        }
    }
}
