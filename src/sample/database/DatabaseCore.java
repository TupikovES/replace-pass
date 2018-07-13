package sample.database;

import javafx.collections.ObservableList;
import sample.model.RFile;

import java.io.File;

public interface DatabaseCore {

    void openDBFile(File file);
    void saveDBFile(File file);
    void addRFileCollection(RFile rFile);
    File getLastFolder();
    ObservableList<RFile> getRFilesCollection();

}
