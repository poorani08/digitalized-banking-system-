package com.example.digitalbanking;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;

public class HelloController {
    @FXML
    private TextField nameid;
    @FXML
    private PasswordField passid;
    @FXML
    private Label sid;
    @FXML
    private TextField accountNumberField;
    @FXML
    private TextField amountDepositedField;
    @FXML
    private TextField depositDateField;
    @FXML
    private TextField depositTypeField;
    @FXML
    private TextField referenceNumberField;

    @FXML
    private Label depositlabel;
    @FXML
    private Label accountlabel;
    @FXML
    private Label transferlabel;
    @FXML
    private Label withdrawlabel;
    @FXML
    private TextField withdrawalAmountField;
    @FXML
    private TextField withdrawalDateField;
    @FXML
    private TextField withdrawalTypeField;
    @FXML
    private TextField sourceAccountField;
    @FXML
    private TextField destinationAccountField;
    @FXML
    private TextField transferAmountField;
    @FXML
    private TextField transferDateField;
    @FXML
    private TextField transferTypeField;
    @FXML
    private TextField accountHolderNameField;
    @FXML
    private TextField initialDepositField;
    @FXML
    private TextField accountTypeField;
    @FXML
    public void gotologin(ActionEvent event) throws IOException {
        String mongoHost = "127.0.0.1";
        int mongoPort = 27017;
        String dbName = "digitalbank";
        String collectionName = "user";

        String username = nameid.getText();
        String password = passid.getText();

        if (username.isEmpty() || password.isEmpty()) {
            sid.setText("Insufficient Information");
            return;
        }

        System.out.println("My details: " + username + " " + password + " ");

        try {
            MongoClient mongoClient = MongoClients.create("mongodb://" + mongoHost + ":" + mongoPort);
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            Document userDocument = new Document();
            userDocument.append("username", username)
                    .append("password", password);

            collection.insertOne(userDocument);

            System.out.println("Inserted into user collection");

            FXMLLoader loginfxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            try {
                Scene firstscene = new Scene(loginfxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("login");
                stage.setScene(firstscene);
                stage.show();
            } catch (IOException e) {
                System.out.println("Error loading signup.fxml: " + e.getMessage());
            }

            mongoClient.close();

        } catch (Exception e) {
            System.out.println("MongoDB error: " + e.getMessage());
        }}


    @FXML
    private TextField Usernameid;
    @FXML
    private PasswordField passwordid;
    @FXML
    private Label labelid;

    @FXML
    public void gotofrontend(ActionEvent actionEvent) throws IOException {
        String mongoHost = "127.0.0.1";
        int mongoPort = 27017;
        String dbName = "digitalbank";
        String collectionName = "user";
        String username1 = Usernameid.getText();
        String password1 = passwordid.getText();
        System.out.println("My details : " + username1 + " " + password1);

        try {
            MongoClient mongoClient = MongoClients.create("mongodb://" + mongoHost + ":" + mongoPort);
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            Document userDocument = new Document();
            Bson filter = Filters.and(
                    Filters.eq("username", username1),
                    Filters.eq("password", password1)
            );

            FindIterable<Document> result = collection.find(filter);

            if (result.iterator().hasNext()) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("frontend.fxml"));
                Parent root = fxmlLoader.load();

                Scene certScene = new Scene(root);

                Stage stage = new Stage();
                stage.setTitle("login");
                stage.setScene(certScene);
                stage.show();

                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } else {
                labelid.setText("Invalid username or password.");
            }
            mongoClient.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @FXML
    public void gotodashboard(ActionEvent actionEvent) throws IOException {
        FXMLLoader loginfxmlLoader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        Scene firstscene = new Scene(loginfxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("dashboard");
        stage.setScene(firstscene);
        stage.show();
    }

    public void gotodeposit(ActionEvent actionEvent) throws IOException {
        FXMLLoader loginfxmlLoader = new FXMLLoader(getClass().getResource("deposit.fxml"));
        Scene firstscene = new Scene(loginfxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("deposit");
        stage.setScene(firstscene);
        stage.show();
    }

    public void gotowithdraw(ActionEvent actionEvent) throws IOException {
        FXMLLoader loginfxmlLoader = new FXMLLoader(getClass().getResource("withdraw.fxml"));
        Scene firstscene = new Scene(loginfxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("withdraw");
        stage.setScene(firstscene);
        stage.show();
    }

    public void gototransfer(ActionEvent actionEvent) throws IOException {
        FXMLLoader loginfxmlLoader = new FXMLLoader(getClass().getResource("transfer.fxml"));
        Scene firstscene = new Scene(loginfxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("transfer");
        stage.setScene(firstscene);
        stage.show();
    }
    public void gotoaccount(ActionEvent actionEvent) throws IOException {
        FXMLLoader loginfxmlLoader = new FXMLLoader(getClass().getResource("account.fxml"));
        Scene firstscene = new Scene(loginfxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("account");
        stage.setScene(firstscene);
        stage.show();
    }
    public void gotosuccess1(ActionEvent event) throws IOException {
        String mongoHost = "127.0.0.1";
        int mongoPort = 27017;
        String dbName = "digitalbank";
        String collectionName = "deposit";

        String accountno = accountNumberField.getText();
        String amount = amountDepositedField.getText();
        String  Ddate= depositDateField.getText();
        String Dtype = depositTypeField.getText();
        String refno = referenceNumberField.getText();

        if (accountno.isEmpty()||amount.isEmpty() || Ddate.isEmpty() || Dtype.isEmpty() || refno.isEmpty()) {
            depositlabel.setText("Insufficient Information");
            return;
        }

        System.out.println("My details: " + accountno + " " + amount + " " + Ddate +" " + Dtype + " " + refno);

        try {
            MongoClient mongoClient = MongoClients.create("mongodb://" + mongoHost + ":" + mongoPort);
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            Document userDocument = new Document();
            userDocument.append("accountno", accountno)
                    .append("amount", amount)
            .append("Ddate", Ddate)
            .append("Dtype", Dtype)
            .append("refno", refno);

            collection.insertOne(userDocument);

            System.out.println("Inserted into deposit collection");

            FXMLLoader loginfxmlLoader = new FXMLLoader(getClass().getResource("success.fxml"));
            try {
                Scene firstscene = new Scene(loginfxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Deposit");
                stage.setScene(firstscene);
                stage.show();
            } catch (IOException e) {
                System.out.println("Error loading signup.fxml: " + e.getMessage());
            }

            mongoClient.close();

        } catch (Exception e) {
            System.out.println("MongoDB error: " + e.getMessage());
        }
    }
    public void gotosuccess2(ActionEvent event) throws IOException {
        String mongoHost = "127.0.0.1";
        int mongoPort = 27017;
        String dbName = "digitalbank";
        String collectionName = "Withdraw";

        String accountno = accountNumberField.getText();
        String Wamount = withdrawalAmountField.getText();
        String  Wdate= withdrawalDateField.getText();
        String Wtype = withdrawalTypeField.getText();
        String refno = referenceNumberField.getText();

        if (accountno.isEmpty() || accountno.isEmpty() || Wamount.isEmpty() || Wdate.isEmpty() || Wtype.isEmpty() ) {
            withdrawlabel.setText("Insufficient Information");
            return;
        }

        System.out.println("My details: " + accountno + " " + Wamount + " " + Wdate +" " + Wtype + " " + refno);

        try {
            MongoClient mongoClient = MongoClients.create("mongodb://" + mongoHost + ":" + mongoPort);
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            Document userDocument = new Document();
            userDocument.append("accountno", accountno)
                    .append("amount", Wamount)
                    .append("Ddate", Wdate)
                    .append("Dtype", Wtype)
                    .append("refno", refno);

            collection.insertOne(userDocument);

            System.out.println("Inserted into withdraw collection");

            FXMLLoader loginfxmlLoader = new FXMLLoader(getClass().getResource("success.fxml"));
            try {
                Scene firstscene = new Scene(loginfxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Withdraw");
                stage.setScene(firstscene);
                stage.show();
            } catch (IOException e) {
                System.out.println("Error loading signup.fxml: " + e.getMessage());
            }

            mongoClient.close();

        } catch (Exception e) {
            System.out.println("MongoDB error: " + e.getMessage());
        }
    }
    public void gotosuccess3(ActionEvent event) throws IOException {
        String mongoHost = "127.0.0.1";
        int mongoPort = 27017;
        String dbName = "digitalbank";
        String collectionName = "Transfer";

        String sourceacc = sourceAccountField.getText();
        String desacc = destinationAccountField.getText();
        String  Tamount= transferAmountField.getText();
        String Ttype = transferTypeField.getText();
        String Tdate = transferDateField.getText();
        String refno = referenceNumberField.getText();

        if (sourceacc.isEmpty() || desacc.isEmpty() || Tamount.isEmpty() || Ttype.isEmpty() || Tdate.isEmpty() || refno.isEmpty()) {
            transferlabel.setText("Insufficient Information");
            return;
        }

        System.out.println("My details: " +sourceacc + " " + desacc + " " + Tamount + " " + Ttype +" " + Tdate + " " + refno);

        try {
            MongoClient mongoClient = MongoClients.create("mongodb://" + mongoHost + ":" + mongoPort);
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            Document userDocument = new Document();
            userDocument.append("accountno", sourceacc)
                    .append("amount", desacc)
                    .append("amount", Tamount)
                    .append("Ddate", Tdate)
                    .append("Dtype", Ttype)
                    .append("refno", refno);

            collection.insertOne(userDocument);

            System.out.println("Inserted into transfer collection");

            FXMLLoader loginfxmlLoader = new FXMLLoader(getClass().getResource("success.fxml"));
            try {
                Scene firstscene = new Scene(loginfxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Transfer");
                stage.setScene(firstscene);
                stage.show();
            } catch (IOException e) {
                System.out.println("Error loading transfer.fxml: " + e.getMessage());
            }

            mongoClient.close();

        } catch (Exception e) {
            System.out.println("MongoDB error: " + e.getMessage());
        }
    }
    public void gotosuccess4(ActionEvent event) throws IOException {
        String mongoHost = "127.0.0.1";
        int mongoPort = 27017;
        String dbName = "digitalbank";
        String collectionName = "Account";

        String accountno = accountNumberField.getText();
        String acchoName = accountHolderNameField.getText();
        String  initialdep = initialDepositField.getText();
        String acctype = accountTypeField.getText();
        String refno = referenceNumberField.getText();

        if (accountno.isEmpty() || acchoName.isEmpty() || initialdep.isEmpty() || acctype.isEmpty() || refno.isEmpty()) {
            accountlabel.setText("Insufficient Information");
            return;
        }

        System.out.println("My details: " + accountno + " " + acchoName + " " + initialdep +" " +acctype + " " + refno);

        try {
            MongoClient mongoClient = MongoClients.create("mongodb://" + mongoHost + ":" + mongoPort);
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            Document userDocument = new Document();
            userDocument.append("accountno", accountno)
                    .append("AccountHolderName", acchoName)
                    .append("InitialDeposit", initialdep)
                    .append("Accountype", acctype)
                    .append("refno", refno);

            collection.insertOne(userDocument);

            System.out.println("Inserted into account collection");

            FXMLLoader loginfxmlLoader = new FXMLLoader(getClass().getResource("success.fxml"));
            try {
                Scene firstscene = new Scene(loginfxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Account");
                stage.setScene(firstscene);
                stage.show();
            } catch (IOException e) {
                System.out.println("Error loading signup.fxml: " + e.getMessage());
            }

            mongoClient.close();

        } catch (Exception e) {
            System.out.println("MongoDB error: " + e.getMessage());
        }
    }
}




