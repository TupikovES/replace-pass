package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import sample.database.DatabaseCore;
import sample.database.FileDatabase;
import sample.model.RFile;

public class PathOverviewController {

    @FXML
    private TableView<RFile> fileTableList;

    @FXML
    private TableColumn<RFile, String> fileName;

    @FXML
    private TableColumn<RFile, String> filePath;

    @FXML
    private TextArea viewConsole;

    private DatabaseCore databaseCore;

    public PathOverviewController() {
        databaseCore = FileDatabase.getInstance();
    }

    @FXML
    private void initialize() {
        fileTableList.setItems(databaseCore.getRFilesCollection());

        fileName.setCellValueFactory(param -> param.getValue().nameProperty());
        filePath.setCellValueFactory(param -> param.getValue().pathProperty());
    }

    public TableView<RFile> getFileTableList() {
        return fileTableList;
    }

    public TextArea getViewConsole() {
        return viewConsole;
    }
}
