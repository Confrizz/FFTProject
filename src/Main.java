import java.io.File;

public class Main {
    private static File audio = new File("C:\\Users\\belou\\Music\\Kryptonite.wav");
    private static File fftFile = new File("FreqBands.txt");

    public static void main(String[] args) throws Exception {
        FileFFT fileFFT = new FileFFT(audio, 2048);
        PlayFFT playFFT = new PlayFFT(audio, fftFile);

//        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audio);
//        AudioFormat audioFormat = audioInputStream.getFormat();
//        System.out.println(audioFormat.toString());
    }
}