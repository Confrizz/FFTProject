 import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.nio.ByteBuffer;

public class DoubleByteConverter {

    public static byte[] toByteArray(double[] doubleArray) {
        byte[] byteArray = new byte[doubleArray.length * 8];

        return byteArray;
    }

    public static double[] toDoubleArray(byte[] byteArray) {
        double[] doubleArray = new double[byteArray.length / 8];
        boolean prevDataNan = false;

        for (int i = 0; i < doubleArray.length; i++) {
            doubleArray[i] = ByteBuffer.wrap(byteArray, i * 8, 8).getDouble();

            if (prevDataNan) {
                doubleArray[i - 1] = (doubleArray[i - 2] + doubleArray[i]) /  2;
            }

            if (Double.isNaN(doubleArray[i])) {
                prevDataNan = true;
            } else {
                prevDataNan = false;
            }
        }

        if (Double.isNaN(doubleArray[doubleArray.length - 1])) {
            doubleArray[doubleArray.length - 1] = doubleArray[doubleArray.length - 2];
        }

        return doubleArray;
    }

    public static byte[] fileToByteArray(File file) throws IOException, UnsupportedAudioFileException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));

        byte[] bytes = new byte[1024];
        int c;
        while ((c = in.read(bytes)) > 0) {
            out.write(bytes, 0, c);
        }
        out.flush();

        return out.toByteArray();
    }
}
