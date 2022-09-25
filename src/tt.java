import com.opencsv.*;

import java.io.*;
import java.nio.file.Path;
//5
public class tt {

    public static void main(String[] args) throws IOException {
        Path filePath = Path.of("students.csv");


        File file = new File("students.csv");
        File_Writer fileWriter = new File_Writer();
        fileWriter.fileWrite(file.toPath());
        fileWriter.readData(file.toPath());
        File_Reader fileReader = new File_Reader();
        fileReader.readStrings();

    }
}
class File_Writer {
    public static void fileWrite(Path filePath) throws FileNotFoundException {
        try {
            File file = new File(filePath.toUri());
            FileWriter outputfile = new FileWriter(file);
            CSVWriter newWrite = new CSVWriter(new FileWriter("students.csv"));

            CSVWriter writer = new CSVWriter(outputfile);

            String[] header = {"Name", "Class", "Marks"};
            writer.writeNext(header);

            String[] data1 = {"Ismailov", "10", "4 5 5 5"};
            writer.writeNext(data1);
            String[] data2 = {"Akmatov", "10", "4 5 5 4"};
            writer.writeNext(data2);
            String[] data3 = {"Kurbanov", "10", "4 3 3 3"};
            writer.writeNext(data3);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readData(Path filePath) throws FileNotFoundException {
        int sum_emir = 0, sum_tar = 0, sum_dj = 0, counter = 0;
        String student_marks = "",students="",line = "",splitBy = ",";
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(String.valueOf(filePath)));
            while ((line = br.readLine()) != null) {

                String[] student = line.split(splitBy);
                students += student[0] + splitBy;
                student_marks += student[2] + splitBy;
            }

            String students_arr[] = students.split(splitBy);
            String student_marks_arr[] = student_marks.split(splitBy);

            char emir[] = student_marks_arr[1].toCharArray();
            char tar[] = student_marks_arr[2].toCharArray();
            char dj[] = student_marks_arr[3].toCharArray();

            for (int i = 0; i < emir.length; i++) {
                if (Character.isDigit(emir[i])) {
                    sum_emir+=Integer.parseInt(Character.toString(emir[i]));
                    counter++;
                }
                if (Character.isDigit(tar[i])) {
                    sum_tar+=Integer.parseInt(Character.toString(tar[i]));
                }
                if (Character.isDigit(dj[i])) {
                    sum_dj+=Integer.parseInt(Character.toString(dj[i]));
                }
            }

            System.out.println(((double) sum_emir/counter >= 4) ? students_arr[1].toUpperCase() : "");

            System.out.println(((double) sum_tar/counter >= 4) ? students_arr[2].toUpperCase() : "");

            System.out.println(((double) sum_dj/counter >= 4) ? students_arr[3].toUpperCase() : "");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
//7
class File_Reader {
    public static void readStrings() throws FileNotFoundException {

        try(FileReader reader = new FileReader("text_for_h2.txt")) {
            BufferedReader reader1 = new BufferedReader(reader);
            String line = reader1.readLine(), line_new = "";
            while (line != null) {
                System.out.println(line);
                line_new += line;
                line = reader1.readLine();
            }
            String[] words = line_new.split(" ");
            char[] words_new = line_new.toCharArray();
            for (int i = 0; i < words.length; i++) {
                if (Character.toString(words_new[i]).equals("-")) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(line_new);
                    sb.deleteCharAt(i);
                    words_new = sb.toString().toCharArray();
                }
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}