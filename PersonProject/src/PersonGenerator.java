import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {

    public static void main(String args[]){
        String ID;
        String fName;
        String lName;
        String title;
        int YOB;
        String csvRec;
        Boolean done;

        Scanner in = new Scanner(System.in);

        ArrayList<String> recs = new ArrayList<>();

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter their ID");
            fName = SafeInput.getNonZeroLenString(in, "Enter their First Name");
            lName = SafeInput.getNonZeroLenString(in, "Enter their Last Name");
            title = SafeInput.getNonZeroLenString(in, "Enter their title");
            YOB = SafeInput.getRangedInt(in, "Enter their Year of Birth", 1000,2024);
            csvRec = ID + ", " + fName + ", "  + lName + ", " + title + ", " + YOB;

            recs.add(csvRec);

            done = SafeInput.getYNConfirm(in, "Are you done");
        } while(!done);


        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "//PersonProject//src//PersonTestData.txt");

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String rec : recs)
            {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}