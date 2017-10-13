import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PlayFFT {
    private File fftFile;
    private File audioFile;
    private int numOfSamples = FileFFT.getNumOfSamples();
    private boolean isReady = false;

    public PlayFFT(File audioFile, File fftFile) throws IOException, UnsupportedAudioFileException, InterruptedException, LineUnavailableException {
        this.audioFile = audioFile;
        this.fftFile = fftFile;

        play();
        System.out.println(Thread.activeCount());
    }

    private void play() throws IOException, UnsupportedAudioFileException, InterruptedException, LineUnavailableException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fftFile));
        int dataLength = Util.fileToByteArray(this.audioFile).length;

        String line;
        double bandOne, bandTwo, bandThree;
        int comma;

        Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(audioFile));
        clip.start();

        double sleepTime = ((double) numOfSamples / 44100) * 1000;
        while ((line = bufferedReader.readLine()) != null) {
            this.isReady = true;

            bandOne = Double.parseDouble(line.substring(line.indexOf("[") + 1, (comma = line.indexOf(","))));
            bandTwo = Double.parseDouble(line.substring(comma + 1, (comma = line.indexOf(",", comma + 1))));
            bandThree = Double.parseDouble(line.substring(comma + 1, line.indexOf("]")));

            System.out.println(bandOne + " " + bandTwo + " " + bandThree);

            Thread.sleep((long) sleepTime);
        }

    }
}