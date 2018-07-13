package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RFile {

    private StringProperty name;
    private StringProperty path;

    public RFile() {
    }

    public RFile(String name, String path) {
        this.name = new SimpleStringProperty(name);
        this.path = new SimpleStringProperty(path);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPath() {
        return path.get();
    }

    public StringProperty pathProperty() {
        return path;
    }

    public void setPath(String path) {
        this.path.set(path);
    }

    @Override
    public String toString() {
        return "RFile{" +
                "name=" + name +
                ", path=" + path +
                '}';
    }
}
