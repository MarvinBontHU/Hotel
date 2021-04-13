package hotel.userinterface;

import hotel.model.Hotel;
import hotel.model.Kamer;
import hotel.model.KamerType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.List;

public class BoekingenController {
    public TextField naamTextField;
    public TextField adresTextField;
    public DatePicker aankomstDatePicker;
    public DatePicker vertrekDatePicker;
    public ComboBox<KamerType> kamerComboBox;
    public Button boekButton;
    public Button resetButton;
    public Label bovenLabel;

    private Hotel hotel = Hotel.getHotel();

    public void initialize(){
        System.out.println("BoekingenController.initialize()");
        List<KamerType> kamerType = hotel.getKamerTypen();
        kamerComboBox.setItems(FXCollections.observableList(kamerType))   ;
    }

    public void resetBoeking(ActionEvent actionEvent) {
        naamTextField.clear();
        adresTextField.clear();
        aankomstDatePicker.getEditor().clear();
        vertrekDatePicker.getEditor().clear();
        aankomstDatePicker.setValue(null);
        vertrekDatePicker.setValue(null);
        kamerComboBox.setValue(null);
    }

    public void boek(ActionEvent actionEvent) {
        try {
            if (!(naamTextField.getText().equals("")) &&
                !(adresTextField.getText().equals("")) &&
                !(aankomstDatePicker.getValue().isBefore(LocalDate.now())) &&
                !(vertrekDatePicker.getValue().isBefore(LocalDate.now())) &&
                aankomstDatePicker.getValue().isBefore(vertrekDatePicker.getValue()) &&
                !(kamerComboBox.getValue().equals(""))) {
                    hotel.voegBoekingToe(aankomstDatePicker.getValue(), vertrekDatePicker.getValue(), naamTextField.getText(), adresTextField.getText(), kamerComboBox.getValue());
                    bovenLabel.setText("Uw boeking is gelukt!");
            } else {
                bovenLabel.setText("Er ontbreken gegevens!");
            }
        } catch (Exception e){
            bovenLabel.setText("Er ontbreken gegevens!");
        }
    }
}
