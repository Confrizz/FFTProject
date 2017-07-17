import java.io.File;

public class Main {
    private static File audio = new File("C:\\Users\\Philip\\Music\\Roland-GR-1-Clarinet-C5.wav");
    private static File fftFile = new File("FreqBands");

    public static void main(String[] args) throws Exception {
        FileFFT fileFFT = new FileFFT(audio);
        PlayFFT playFFT = new PlayFFT(audio, fftFile);
    }

}
