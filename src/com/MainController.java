package com;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableColumn<Person, Integer> colAge;

    @FXML
    private TableColumn<Person, String> colGender;

    @FXML
    private TableColumn<Person, String> colName;

    @FXML
    private Label lblCount;

    @FXML
    private Button btnExcel;

    @FXML
    private Button btnPDF;

    @FXML
    private TableView<Person> tblPeople;

    @FXML
    private TextField txtSearch;
    ObservableList<Person> people = FXCollections.observableArrayList();

    public void setData(){
        people.add(new Person("beryl",20,"female"));
        people.add(new Person("mark",21,"male"));
        people.add(new Person("vincent",22,"male"));
        people.add(new Person("joyce",18,"female"));
        people.add(new Person("barkley",20,"female"));
        people.add(new Person("Dennis",23,"male"));
        people.add(new Person("Cynthia",25,"female"));
        people.add(new Person("Brian",19,"male"));
        people.add(new Person("Albert",24,"male"));
        people.add(new Person("Veronica",19,"female"));
    }

    public void initWidget() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colGender.setCellValueFactory(v -> new SimpleStringProperty(
                v.getValue().getGender()
        ));

        FilteredList<Person> filteredList = new FilteredList<>(people, p -> true);
        txtSearch.textProperty().addListener((observable,oldVal, newVal) -> {
            filteredList.setPredicate(person -> {
                if(newVal == null || newVal.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newVal.toLowerCase();
                if(person.getName().toLowerCase().contains(lowerCaseFilter))
                    return true;
                else if((person.getName() + " " + person.getAge()).toLowerCase().contains(lowerCaseFilter))
                    return true;
                else if((person.getAge() + " " + person.getName()).toLowerCase().contains(lowerCaseFilter))
                    return true;
                else if((person.getName() + " " + person.getGender()).toLowerCase().contains(lowerCaseFilter))
                    return true;
                else if((person.getGender() + " " + person.getName()).toLowerCase().contains(lowerCaseFilter))
                    return true;
                else if((person.getGender() + " " + person.getAge()).toLowerCase().contains(lowerCaseFilter))
                    return true;
                else if((person.getAge() + " " + person.getGender()).toLowerCase().contains(lowerCaseFilter))
                    return true;
                else if(person.getGender().toLowerCase().contains(lowerCaseFilter))
                    return true;
                else
                    return String.valueOf(person.getAge()).toLowerCase().contains(lowerCaseFilter);
            });
            lblCount.setText(filteredList.size() + "");
        });
        SortedList<Person> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tblPeople.comparatorProperty());
        tblPeople.setItems(sortedList);
        lblCount.setText(sortedList.size() + "");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
        initWidget();

        btnExcel.setOnAction(e -> {
            ExportExcel.exportExcel(tblPeople);
        });

        btnPDF.setOnAction(e -> {
            try {
                GeneratePDF.exportPDF(people);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }
}
