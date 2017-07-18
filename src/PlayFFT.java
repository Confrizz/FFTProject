import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PlayFFT {
    private File fftFile;
    private File audioFile;
    private int numOfSamples = FileFFT.getNumOfSamples();

    public PlayFFT(File audioFile, File fftFile) throws IOException, UnsupportedAudioFileException {
        this.audioFile = audioFile;
        this.fftFile = fftFile;

        play();
    }

    private void play() throws IOException, UnsupportedAudioFileException {
        byte[] byteData = DoubleByteConverter.fileToByteArray(audioFile);
        byte[] slicedData = new byte[numOfSamples * 8];

        AudioDataStream audioStream;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fftFile));

        String line;
        double bandOne, bandTwo, bandThree;
        int comma;
        int counter = 0;
        while ((line = bufferedReader.readLine()) != null) {
//        line = bufferedReader.readLine();
//        for (int j = 0; j < 1; j++) {
            bandOne = Double.parseDouble(line.substring(line.indexOf("[") + 1, (comma = line.indexOf(","))));
            bandTwo = Double.parseDouble(line.substring(comma + 1, (comma = line.indexOf(",", comma + 1))));
            bandThree = Double.parseDouble(line.substring(comma + 1, line.indexOf("]")));

            for (int i = 0; i < numOfSamples * 8; i++) {
                slicedData[i] = byteData[i + (numOfSamples * counter * 8)];
            }
            audioStream = new AudioDataStream(new AudioData(slicedData));
            AudioPlayer.player.start(audioStream);
            AudioPlayer.player.interrupt();

//            counter++;
        }

    }
}