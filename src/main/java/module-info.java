module ru.pfur.skis {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;

    requires java.desktop;
    requires com.google.gson;
    requires lombok;

    opens ru.pfur.skis to javafx.fxml;
    exports ru.pfur.skis;
}

