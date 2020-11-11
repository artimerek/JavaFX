package properties;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class Controller {

    @FXML
    private TextField nameTextField;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField surnameTextField;

    @FXML
    private Label surnameLabel;

    @FXML
    private  TextField yearTextField;

    @FXML
    private CheckBox confirmCheckBox;

    @FXML
    private Label ageLabel;

    @FXML
    private Button loginButton;

    private PersonViewModel personViewModel = new PersonViewModel();

    public void initialize()
    {
        nameTextField.textProperty().bindBidirectional(personViewModel.getNameProperty());
        surnameTextField.textProperty().bindBidirectional(personViewModel.getSurnameProperty());
        surnameTextField.disableProperty().bind(personViewModel.getDisableSurnameProperty());

        StringConverter stringConverter = new NumberStringConverter(); //int to string
        yearTextField.textProperty().bindBidirectional(personViewModel.getYearProperty(),stringConverter);
        confirmCheckBox.selectedProperty().bindBidirectional(personViewModel.getConfirmProperty());

        ageLabel.textProperty().bind(personViewModel.getAgeProperty());
        nameLabel.visibleProperty().bind(personViewModel.getNameOkProperty());
        surnameLabel.visibleProperty().bind(personViewModel.getSurnameOKProperty());

        loginButton.disableProperty().bind(personViewModel.getButtonProperty());
    }


}
