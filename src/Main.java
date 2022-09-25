import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Path filePath = Path.of("/home/iskender/Tariel/fileSolver/tar.csv");
        File file = new File("tar.csv");
        File newFile = new File("tari.txt");
        FileSolver fileSolver = new FileSolver();
//        fileSolver.fileReader(file);
        fileSolver.fileReader(file);
    }

}
  class FileSolver {

      //1.1 заполнение csv файла
      public static void fileWrite(Path filePath) throws FileNotFoundException {
          try {
              File file = new File(filePath.toUri());
              // create FileWriter object with file as parameter
              FileWriter outputfile = new FileWriter(file);
              CSVWriter newWrite = new CSVWriter(new FileWriter("tar.csv"));

              CSVWriter writer = new CSVWriter(outputfile);

              // adding header to csv
              String[] header = {"Фамилия", "Год установки", "Номер телефона"};
              writer.writeNext(header);

              // add data to csv
              String[] data1 = {"Аманов", "2010", "0700023132"};
              writer.writeNext(data1);
              String[] data2 = {"Суражев", "2015", "0550314312"};
              writer.writeNext(data2);
              String[] data3 = {"Давлетов", "2022", "0507232312"};
              writer.writeNext(data3);
              String[] data4 = {"Акматов", "2021", "0700024498"};
              writer.writeNext(data4);
              String[] data5 = {"Мамбетов", "2017", "0506324113"};
              writer.writeNext(data5);
              String[] data6 = {"Жээнбеков", "2016", "07773123144"};
              writer.writeNext(data6);
              String[] data7 = {"Уранов", "2010", "07083123921"};
              writer.writeNext(data7);
              String[] data8 = {"Уранов", "2010", "07084233921"};
              writer.writeNext(data8);
              String[] data9 = {"Akmatov", "2010", "05084233921"};
              writer.writeNext(data9);

              writer.close();
          } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
      }

      //1.2
      public static void fileReader(File file) throws FileNotFoundException {
          BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
          try {
              Scanner scanner = new Scanner(System.in);
              System.out.println("Какого вы ищите?");
              String find = scanner.nextLine().trim();
              String nextLine;
              while ((nextLine = bufferedReader.readLine()) != null) {
                  StringBuilder stringBuilder = new StringBuilder(nextLine);
                  for (int i = 0; i < stringBuilder.length(); i++) {
                      if (stringBuilder.charAt(i) == '"') stringBuilder.deleteCharAt(i);
                  }
                  List<String> array = List.of(stringBuilder.toString().split(","));
                  if (array.contains(find)) System.out.println(array.get(0) + " " + array.get(2));
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
      }


      public static void countByYEar(File file) throws FileNotFoundException {
          BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
          try {
              Scanner scanner = new Scanner(System.in);
              System.out.println("C какого вы ищите?");
              int find = scanner.nextInt();
              String nextLine;
              while ((nextLine = bufferedReader.readLine()) != null) {
                  StringBuilder stringBuilder = new StringBuilder(nextLine);
                  for (int i = 0; i < stringBuilder.length(); i++) {
                      if (stringBuilder.charAt(i) == '"') stringBuilder.deleteCharAt(i);
                  }
                  List<String> array = List.of(stringBuilder.toString().split(","));
                  if (!array.get(1).equals("Год установки") && Integer.parseInt(array.get(1)) >= find) {
                      System.out.println(array);

                  }
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
      }

      //2.1
      public static void TwoPoints(File file) {
          try {
              FileWriter fileWriter = new FileWriter(file);
              Scanner sc = new Scanner(System.in);
              System.out.println("Сколько рядов вы хотите?");
              int times = sc.nextInt();
              for (int i = 0; i < times; i++) {
                  System.out.println("Введите два числа");
                  fileWriter.write(sc.nextInt() + " " + sc.nextInt() + "\n");
              }
              fileWriter.close();
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
      }

      //2.2
      public static void distance(File file) {
          try {
              FileReader fileReader = new FileReader(file);
              BufferedReader bufferedReader = new BufferedReader(fileReader);
              List<List> br = new ArrayList<>();
              String newLine;
              while ((newLine = bufferedReader.readLine()) != null) {
                  br.add(List.of(newLine.split(" ")));
              }
              System.out.println(br);
              System.out.println(br.get(0));
              double max = -1;
              double min = 10000;
              for (int i = 0; i < br.size(); i++) {
                  for (int j = i + 1; j < br.size(); j++) {
                      double dist = Math.sqrt(Math.pow(Integer.parseInt((String) br.get(j).get(0)) - Integer.parseInt((String) br.get(i).get(0)), 2) + Math.pow(Integer.parseInt((String) br.get(j).get(1)) - Integer.parseInt((String) br.get(i).get(1)), 2));
                      System.out.println(dist);
                      if (max < dist) {
                          max = dist;

                      }
                      if (min > dist) {
                          min = dist;
                      }


                  }

              }
              System.out.println(max + " is maximum distance and  " + min + " is minimum distance");
          } catch (FileNotFoundException e) {
              throw new RuntimeException(e);
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
      }


//8
      public static void t8() throws IOException {
          String[] strs = new String[10];
          try (Scanner sc = new Scanner(new File("input.txt"));
               FileWriter writer = new FileWriter(new File("оutput.txt"))) {
              int count = 0;
              String spaces = "";
              while (sc.hasNextLine()) {
                  String linee = sc.nextLine();
                  count = 0;
                  for(int i = 0; i<linee.length(); i++) {
                      count++;
                  }
                  count = 50 - count;
                  for (int i = 0; i < count; i++) {
                      spaces = spaces + " ";
                  }

                  writer.write(spaces+linee+"\n");
                  spaces = "";
              }

          } catch (IOException ex) {
              ex.printStackTrace();
          }
      }
//4
      public static void t4() throws IOException {
          Scanner input = new Scanner(System.in);
          System.out.println("Введите количество пробелов:");
          int lineLength = input.nextInt();

          File Zadanie = new File("Primer4_F.txt");
          Scanner sc = new Scanner(Zadanie);
          FileWriter writer = new FileWriter("Primer4_G.txt");
          while (sc.hasNextLine()) {
              String line = sc.nextLine();
              if (line.length() == 0) { continue; }

              if (line.length() > lineLength) { line = line.substring(0, lineLength); }
              if (line.length() < lineLength) { line = line + String.join("", Collections.nCopies(lineLength - line.length(), " ")); }
              writer.write(line+"\n");
          }
          System.out.println("Done");
          writer.close();
          sc.close();
          input.close();

      }


      public static List<String> choose_words(String rawLine) {
          List<String> words = new ArrayList<>(Arrays.asList(rawLine.split(" ")));
          List<Integer> words_remove = which_word_to_remove(words);
          remove_words(words_remove, words);
          return words;
      }

      public static List<Integer> which_word_to_remove(List<String> words) {
          List<Integer> words_remove = new ArrayList<>();
          for (int i = 0; i < words.size(); ++i) {
              if (words.get(i).length() >= 3 && words.get(i).length() <= 5) {
                  words_remove.add(i);
              }
          }
          return words_remove;
      }

      public static void remove_words(List<Integer> remove_words, List<String> words) {
          int size = remove_words.size() % 2 == 0 ? remove_words.size() : remove_words.size() - 1;
          for (int i = size - 1; i >= 0; --i) {
              words.remove((int) remove_words.get(i));
          }
      }
//6
      public static void t6() throws IOException {
          File Task6 = new File("c");
          Scanner sc = new Scanner("Task6.txt");
          List<String> lines = new ArrayList<String>();
          while (sc.hasNextLine()) {
              String line = sc.nextLine();
              List<String> words = choose_words(line);
              lines.add(String.join(" ", words));
          }
          sc.close();

          FileWriter writer = new FileWriter("Task6.txt");
          for (String line : lines) {
              System.out.println(line);
              writer.write(line + "\n");
          }
          writer.close();
          System.out.println("Закончена работа");

      }



}

