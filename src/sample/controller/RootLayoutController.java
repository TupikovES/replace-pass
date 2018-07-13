package sample.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import sample.Main;
import sample.database.DatabaseCore;
import sample.database.FileDatabase;
import sample.model.RFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RootLayoutController {

    @FXML
    private MenuItem openFile;

    @FXML
    private MenuItem saveFile;

    @FXML
    private MenuItem addFile;

    @FXML
    private MenuItem deleteFile;

    @FXML
    private Button startReplace;

    @FXML
    private TextField oldPass;

    @FXML
    private TextField newPass;

    private Main main;
    private DatabaseCore databaseCore;

    public RootLayoutController() {
        databaseCore = FileDatabase.getInstance();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void addNewFileInList() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Add file for replacement password");
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());
        RFile rFile = new RFile(file.getName(), file.getPath());
        System.out.println(rFile);
        databaseCore.addRFileCollection(rFile);
    }

    public void saveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Add file for replacement password");
        if (databaseCore.getLastFolder() != null) {
            fileChooser.setInitialDirectory(databaseCore.getLastFolder());
        }
        File file = fileChooser.showSaveDialog(main.getPrimaryStage());
        if (file != null) {
            databaseCore.saveDBFile(file);
        }
    }

    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());
        if (file != null) {
            databaseCore.openDBFile(file);
        }
    }

    public void startReplace() {
        String _old = oldPass.getText();
        String _new = newPass.getText();

        TextArea console = main.getPathOverviewController().getViewConsole();

        console.appendText("old pass: " + _old + ", new pass: " + _new + "\n");

        if (_old != null && !_old.equals("") && _new != null && !_new.equals("")) {
            ObservableList<RFile> list = databaseCore.getRFilesCollection();
            for (RFile rFile : list) {
                console.appendText("replace in " + rFile.getName() + "... ");
                Path path = Paths.get(rFile.getPath());
                try {
                    String content = new String(Files.readAllBytes(path));
                    content = content.replaceAll("\\b" + _old + "\\b", _new);
                    Files.write(path, content.getBytes());
                    console.appendText("done! \n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        console.appendText("Replace complete! \n");
    }

    public void delFileInTable() {
        RFile rFile = main.getPathOverviewController().getFileTableList().getSelectionModel().getSelectedItem();
        databaseCore.getRFilesCollection().remove(rFile);
        System.out.println("remove: " + rFile.getName());
    }
}
