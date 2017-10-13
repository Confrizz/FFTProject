import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Util {

    public static int getNextRealNumberIndex(double[] data, int start) {
        int position = start;

        while (position < data.length && Double.isNaN(data[position])) {
            position++;
        }
        return position;
    }

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

        int counter = 0;
        for (int i = 0; i < shortArray.length; i++) {
            if (shortArray[i] == 0) {
                counter++;
            }
        }
        System.out.println(counter);

//        int nextRealNumberIndex;
//        double average;
//        for (int i = 0; i < doubleArray.length; i++) {
//            if (Double.isNaN(doubleArray[i])) {
//                nextRealNumberIndex = getNextRealNumberIndex(doubleArray, i);
//
//                if ((i + 1) > (doubleArray.length - 1)) {
//                    doubleArray[i] = doubleArray[i - 1];
//                } else if ((i - 1) < 0) {
//                    for (int j = i; j < nextRealNumberIndex + 1; j++) {
//                        doubleArray[j] = doubleArray[nextRealNumberIndex];
//                    }
//                } else {
//                    average = (doubleArray[i - 1] + doubleArray[nextRealNumberIndex]) / 2;
//                    for (int j = i; j < nextRealNumberIndex + 1; j++) {
//                        doubleArray[j] = average;
//                    }
//                }
//            }
//        }

        return shortArray;
    }

//    public static double[] toDoubleArray(byte[] byteArray) {
//        double[] doubleArray = new double[byteArray.length / 8];
//
//        for (int i = 0; i < doubleArray.length; i++) {
//            doubleArray[i] = ByteBuffer.wrap(byteArray, i * 8, 8).getDouble();
//        }
//
//        int nextRealNumberIndex;
//        double average;
//        for (int i = 0; i < doubleArray.length; i++) {
//            if (Double.isNaN(doubleArray[i])) {
//                nextRealNumberIndex = getNextRealNumberIndex(doubleArray, i);
//
//                if ((i + 1) > (doubleArray.length - 1)) {
//                    doubleArray[i] = doubleArray[i - 1];
//                } else if ((i - 1) < 0) {
//                    for (int j = i; j < nextRealNumberIndex + 1; j++) {
//                        doubleArray[j] = doubleArray[nextRealNumberIndex];
//                    }
//                } else {
//                    average = (doubleArray[i - 1] + doubleArray[nextRealNumberIndex]) / 2;
//                    for (int j = i; j < nextRealNumberIndex + 1; j++) {
//                        doubleArray[j] = average;
//                    }
//                }
//            }
//        }
//
//        return doubleArray;
//    }

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
