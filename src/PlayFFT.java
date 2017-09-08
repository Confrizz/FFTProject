import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PlayFFT implements Runnable{
    private File fftFile;
    private File audioFile;
    private int numOfSamples = FileFFT.getNumOfSamples();

    public PlayFFT(File audioFile, File fftFile) throws IOException, UnsupportedAudioFileException, InterruptedException, LineUnavailableException {
        this.audioFile = audioFile;
        this.fftFile = fftFile;

        play();
    }

    private void play() throws IOException, UnsupportedAudioFileException, InterruptedException, LineUnavailableException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fftFile));
        int dataLength = CustomUtilities.fileToByteArray(audioFile).length;

        String line;
        double bandOne, bandTwo, bandThree;
        int comma;
        int counter = 0;

        run();

        while ((line = bufferedReader.readLine()) != null) {
            bandOne = Double.parseDouble(line.substring(line.indexOf("[") + 1, (comma = line.indexOf(","))));
            bandTwo = Double.parseDouble(line.substring(comma + 1, (comma = line.indexOf(",", comma + 1))));
            bandThree = Double.parseDouble(line.substring(comma + 1, line.indexOf("]")));

//            System.out.println(bandOne + " " + bandTwo + " " + bandThree);
            Thread.sleep(8 * numOfSamples * numOfSamples / dataLength);

            counter++;
        }

    }

    @Override
    public void run() {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(audioFile));
            clip.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}