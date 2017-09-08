import java.io.File;

public class Main {
    private static File audio = new File("C:\\Users\\belou\\Music\\Kryptonite.wav");
    private static File fftFile = new File("FreqBands.txt");

    public static void main(String[] args) throws Exception {
        FileFFT fileFFT = new FileFFT(audio);
//        PlayFFT playFFT = new PlayFFT(audio, fftFile);
//        Clip clip = AudioSystem.getClip();
//        clip.open(AudioSystem.getAudioInputStream(audio));
//        clip.start();
//        Thread.sleep(10000);
//        clip.close();
    }
}