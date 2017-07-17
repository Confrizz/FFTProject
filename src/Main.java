import java.io.File;

public class Main {
    private static File file = new File("C:\\Users\\Philip\\Music\\Roland-GR-1-Clarinet-C5.wav");

    public static void main(String[] args) throws Exception {
        FileFFT fileFFT = new FileFFT(file);
    }

}
