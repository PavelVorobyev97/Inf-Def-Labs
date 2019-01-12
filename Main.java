import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {

        int min = 2;
        List<String> pList = getPlist();
        Random pRandom = new Random(System.currentTimeMillis());
        int P = Integer.parseInt(pList.get(pRandom.nextInt(pList.size())));

        int g = min + pRandom.nextInt(P - 1 - min + 1);

        Random random1 = new Random(System.currentTimeMillis());
        int a = (min + random1.nextInt(P - min - 1)) / 3;
        int b = (min + random1.nextInt(P - min - 1)) / 3;

        System.out.println("P = " + P + ";");
        System.out.println("g = " + g + ";");

        System.out.println("Alice private key: " + a);
        System.out.println("Bob private key: " + b);
        System.out.println();

        long aShared = ((long) g ^ a) % P;
        System.out.println("Alice shared key: " + aShared);

        long bShared = ((long) g ^ b) % P;
        System.out.println("Bob shared key: " + bShared);
        System.out.println();

        long aPrivate = (bShared ^ a) % P;
        System.out.println("Alice got secret: " + aPrivate);
        long bPrivate = (aShared ^ b) % P;
        System.out.println("Bob got secret: " + bPrivate);

        System.out.println();
        if (aPrivate == bPrivate)
            System.out.println("ALice and Bob can communicate with each other!!!");
        else
            System.out.println("ALice and Bob cannot communicate with each other!!!");

    }


    private static List<String> getPlist() throws IOException {
        FileInputStream fstream = new FileInputStream("P.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(fstream));
        List<String> strList = new ArrayList<>();
        String strLine;

        while ((strLine = reader.readLine()) != null) {
            strList.add(strLine);
        }
        return strList;

    }
}
