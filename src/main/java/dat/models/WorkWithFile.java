package dat.models;

import java.io.*;
import java.util.ArrayList;

public class WorkWithFile {
    public boolean delete(String path) {
        return new File(path).delete();
    }

    public void writeMore(String path, String content) {
        try {
            FileWriter myWriter = new FileWriter(path,true);
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void overwrite(String path, String content) {
        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> read(String path) {
        ArrayList<String> rs = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            for(String l = br.readLine(); l != null; l = br.readLine())
                rs.add(l);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rs;
    }
    public String readContent(String path) {
        StringBuilder rs = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            for(String l = br.readLine(); l != null; l = br.readLine())
                rs.append(l).append("\n");
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rs.toString();
    }
}
