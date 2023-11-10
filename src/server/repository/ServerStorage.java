package server.repository;

import server.repository.Repository;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class ServerStorage implements Repository {
    private static final String FILE_NAME = "log.txt";

    @Override
    public void fileWriter(String s) {
        try(BufferedWriter bfWriter = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bfWriter.append(s);
            bfWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String fileReader() {
        StringBuilder strBuilder = new StringBuilder();
        try(FileReader reader = new FileReader(FILE_NAME)){
            int c;
            while ((c = reader.read()) != -1) {
                strBuilder.append((char) c);
            }
            strBuilder.delete(strBuilder.length() - 1, strBuilder.length());
            return strBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
