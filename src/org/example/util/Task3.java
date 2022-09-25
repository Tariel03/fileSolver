package org.example.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Task {
    public static List<String> readFromFile(String filePath){
        List<String> lines=new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            while (reader.ready()) {
                lines.add(reader.readLine());
            }
            reader.close();

        }catch (IOException e){
            e.printStackTrace();
        }
        return lines;
    }

    public static void writeToFile(String filePath,List<String> lines,boolean append){
        try{
            BufferedWriter writer=new BufferedWriter(new FileWriter(filePath,append));
            for(String l:lines){
                writer.write(l);
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void printTaskDescription(int taskNum,String description){
        System.out.printf("%s. %s\n\n",taskNum,description);
    }

    public static void printStrings(List<String> strings){
        strings.forEach(System.out::println);
    }
}
//3 Задание
public class Task3 {
    static int taskNum=3;
    static String taskDescription="Имеется файл с текстом. Осуществить шифрование данного текста в новый файл\n" +
            "путем записи текста в матрицу символов по строкам, а затем чтение символов из\n" +
            "этой матрицы по столбцам. Осуществить расшифровку полученного текста.";
    static String fileTextPath="/home/iskender/Tariel/fileSolver/src/org/example/util/cipher.txt";
    static String fileCypherPath="decipher.txt";
    static List<String> originalText= Task.readFromFile(fileTextPath);
    static String ch="%";


    public static int getLongestLength(List<String> originalText){
        if(!originalText.isEmpty()){
            return originalText.stream().max(Comparator.comparingInt(String::length)).get().length();
        }
        return -1;
    }

    public static List<String> getFilledLines(List<String> lines){
        int longest=getLongestLength(lines);
        for (int i = 0; i < lines.size(); i++) {
            String current=lines.get(i);
            if(current.length()<longest){
                lines.set(i,padStringRight(current,longest,ch));
            }
        }
        return lines;
    }

    public static String padStringRight(String s,int length,String ch){
        if (s.length() >= length) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        while (sb.length() !=length) {
            sb.append(ch);
        }

        return sb.toString();
    }

    public static String[][] parse2dArray(List<String> lines){
        String[][] result=new String[lines.size()][lines.get(0).length()];
        for (int i = 0; i < result.length; i++) {
            result[i]=lines.get(i).split("");
        }

        return result;
    }

    public static void print2dArray(String[][] arr){
        System.out.println("{");
        for(String[] a:arr){
            System.out.printf("\t%s%n", Arrays.toString(a));
        }
        System.out.print("}");
    }

    public static String getCypher(String[][] arr){
        StringBuilder sb=new StringBuilder();
        for (int col = 0; col < arr[0].length; col++) {
            for (int row = 0; row < arr.length; row++) {
                sb.append(arr[row][col]);
            }
        }
        return sb.toString();
    }


    public static void saveAndPrintCypher(List<String> text,String filePath){
        String cypher=getCypher(parse2dArray(getFilledLines(text)));
        int originalCols=text.size();

        Task.writeToFile(filePath,List.of(String.format("@rows:%s%n",originalCols),cypher),false);

        System.out.println("Зашифрованный текст");
        System.out.println(getCypher(parse2dArray(getFilledLines(text))));
    }

    public static String[] getStringArr(int rows){
        String[] arr=new String[rows];
        for (int i = 0; i < arr.length; i++) {
            arr[i]="";
        }
        return arr;

    }

    public static String[] decipherText(String filePath){
        List<String> fileLines=Task.readFromFile(filePath);
        int rows=Integer.parseInt(fileLines.get(0).substring(6));
        String cypher=fileLines.get(1);
        String[] result=getStringArr(rows);

        while(!cypher.isEmpty()){
            String part1;
            String part2;
            if(cypher.length()>rows){
                part1=cypher.substring(0,rows);
                part2=cypher.substring(rows);
            }else{
                part1=cypher;
                part2="";
            }

            cypher=part2;
            String[] chars=part1.split("");
            for (int i = 0; i < chars.length; i++) {
                if(!chars[i].equals(ch)){
                    result[i]+=chars[i];
                }
            }
        }

        return result;

    }

    public static void printFileContent(String path){
        List<String> lines=Task.readFromFile(path);
        for(String l:lines){
            System.out.println(l);
        }
    }


    public static void execute(){
        Task.printTaskDescription(taskNum,taskDescription);
        System.out.printf("вводный файл: %s\nвыводный файл: %s\n\n",fileTextPath,fileCypherPath);
        System.out.println("Изначальный текст");
        Task.printStrings(Task.readFromFile(fileTextPath));
        System.out.println();
        saveAndPrintCypher(originalText,fileCypherPath);
        System.out.println("_".repeat(30)+"\n");
        System.out.println("Зашифрованнный текст: ");
        Task.printStrings(Task.readFromFile(fileCypherPath));
        System.out.println();
        System.out.println("Расшифрованный текст:");
        String[] decipheredText=decipherText(fileCypherPath);
        for(String d:decipheredText){
            System.out.println(d);
        }

    }

    public static void main(String[] args) {
        execute();
    }


}
