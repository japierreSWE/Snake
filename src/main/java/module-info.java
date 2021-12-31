module org.jpierre {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.jpierre to javafx.fxml;
    opens org.jpierre.controller to javafx.fxml;
    exports org.jpierre;
    exports org.jpierre.controller;
}
