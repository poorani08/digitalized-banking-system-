module com.example.digitalbanking {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;

    opens com.example.digitalbanking to javafx.fxml;
    exports com.example.digitalbanking;
}
