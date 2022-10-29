package control;
import java.util.ArrayList;
import java.util.*;
import java.io.*;
// import java.nio.file.Path;

public abstract class DBManager {
    // An abstract class to manage the database in text file format.
    public String root;
    public ArrayList<String> columns;
    public ArrayList<ArrayList<String>> data;

    
    public DBManager(String filename) {
        // Set root to the filename
        // Create a new file if the filename is not existed
        this.root = filename;
        // if (!this.existed(filename)){
        //     create(filename);
        //     this.columns = new ArrayList<String>();
        // }
        // else {
        //     this.columns = this.getColumns(filename);
        // }
    }

    public DBManager(){
        this.root = "defaultAssets.txt";
        // this.columns = getColumns(this.root);
    }

    public ArrayList<String> getColumns(String filename) {
        ArrayList<String> data = new ArrayList<String>();
        // Scanner sc = new Scanner(new FileInputStream(filename));
        // String unsplit = sc.nextLine();
        // for (String col : unsplit.split(",")){
        //     data.add(col);
        // }
        return data;
    }

    // public abstract Boolean existed();

    // public abstract ArrayList<String> getColumns();

    public void read(String root) throws IOException{
        Scanner sc = new Scanner(new FileInputStream(root));
        sc.nextLine();
        String nextLine;
        ArrayList<String> newEle = new ArrayList<String>();
        while (sc.hasNext()) {
            nextLine = sc.nextLine();
            for (String e: nextLine.trim().split(";", 10)){
                newEle.add(e);
            }
            this.data.add(newEle);
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
            String overWrite = String.join("|", this.columns) + "\n" + line;
            writer.write(overWrite);
        }
        writer.close();
    };
}
