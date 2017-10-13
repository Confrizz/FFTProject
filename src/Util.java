import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Util {

    public static byte[] toByteArray(double[] doubleArray) {
        byte[] byteArray = new byte[doubleArray.length * 8];

        return byteArray;
    }

    public static short[] toShortArray(byte[] byteArray) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(2);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        short[] shortArray = new short[byteArray.length / 2];

        for (int i = 0; i < shortArray.length; i++) {
            shortArray[i] = ByteBuffer.wrap(byteArray, i * 2, 2).getShort();
        }

        return shortArray;
    }

    public static byte[] fileToByteArray(File file) throws IOException, UnsupportedAudioFileException {
        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] bytes = new byte[(int) file.length()];

        fileInputStream.read(bytes);
        fileInputStream.close();

        return bytes;
    }

    public static byte[] stereoByteArrayToMonoByteArray(byte[] stereoBytes) {
        byte[] monoBytes = new byte[stereoBytes.length / 2];

        for (int i = 0; i < monoBytes.length; i += 2) {
            monoBytes[i] = stereoBytes[2 * i];
            monoBytes[i + 1] = stereoBytes[(2 * i) + 1];
        }

        return monoBytes;
    }

}
