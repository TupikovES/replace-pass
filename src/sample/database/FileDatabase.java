package sample.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import sample.Main;
import sample.model.RFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileDatabase implements DatabaseCore {

    private static FileDatabase ourInstance;

    public static FileDatabase getInstance() {
        if (ourInstance == null) {
            ourInstance = new FileDatabase();
        }
        return ourInstance;
    }

    private Main main;
    private ObservableList<RFile> rFiles;
    private File lastFolder;

    private FileDatabase() {
        rFiles = FXCollections.observableArrayList();
    }

    @Override
    public void openDBFile(File file) {
        try {
            List<String> list = Files.readAllLines(Paths.get(file.getPath()));
            for (String s : list) {
                String[] split = s.split("=");
                rFiles.add(new RFile(split[0], split[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveDBFile(File file) {
        lastFolder = file.getParentFile();
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            for (RFile rFile : rFiles) {
                String s = rFile.getName() + "=" + rFile.getPath() + "\n";
                fileWriter.write(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void addRFileCollection(RFile rFile) {
        rFiles.add(rFile);
    }

    @Override
    public ObservableList<RFile> getRFilesCollection() {
        return rFiles;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public File getLastFolder() {
        return lastFolder;
    }
}
