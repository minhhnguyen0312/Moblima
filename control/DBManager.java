package control;
import java.util.ArrayList;
import java.util.*;
import java.io.*;
// import java.nio.file.Path;

public abstract class DBManager<T> {
    // An abstract class to manage the database in text file format.
    protected String root;
    protected ArrayList<String> columns;
    protected ArrayList<T> data;

    
    public DBManager(String filename) {
        this.root = filename;
        this.data = new ArrayList<T>();
    }

    public DBManager(){
        this.root = "defaultAssets.txt";
    }

    public void read(String root) throws IOException{
        Scanner sc = new Scanner(new FileInputStream(root));
        sc.nextLine();
        String nextLine;
        ArrayList<String> newEle;
        while (sc.hasNext()) {
            nextLine = sc.nextLine();
            newEle = new ArrayList<String>();
            for (String e: nextLine.trim().split(";", 10)){
                newEle.add(e);
            }
            this.data.add(constructFromArr(newEle));
        }
        sc.close();
    };

    public void write(String line, boolean append) throws IOException{
        FileWriter writer = new FileWriter(this.root, append);
        if (append){
            BufferedWriter bw = new BufferedWriter(writer);
            bw.newLine();
            bw.write(line);
            bw.close();
        }
        else{
            String overWrite = String.join("|", this.columns) + line;
            writer.write(overWrite);
        }
        writer.close();
    };

    public void remove(String column, String value, Boolean overw) throws IOException{
        Integer index = getColumnsIndex(column) - 1;
        for (T record: this.data){
            if (decodeFromObj(record).get(index).equals(value)){
                this.data.remove(record);
                break;
            }
        }
        if (overw) {
            String line = "";
            // System.out.println(this.data.size());
            for (int i = 0; i < this.data.size(); i++){
                // System.out.println("Hello");
                line += String.format("\n%d;%s", i+1, this.data.get(i).toString());
            }
            write(line, false);
        }
    }

    public Integer getColumnsIndex(String column){
        int index = 0;
        for (String col: this.columns){
            if (column.equals(col))
                return index;
            index++;
        }
        return -1;
    }

    public abstract T constructFromArr(ArrayList<String> ele) throws NumberFormatException, IOException;
    public abstract ArrayList<String> decodeFromObj(T obj);
}
