module com.mycompany.martinezgallardopablofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires MiLibreria;
    requires java.base;

    opens com.mycompany.martinezgallardopablofinal to javafx.fxml;
    exports com.mycompany.martinezgallardopablofinal;
}
