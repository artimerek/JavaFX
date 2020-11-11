package properties;


import javafx.beans.binding.When;
import javafx.beans.property.*;

import java.time.LocalDate;

public class PersonViewModel {

    private StringProperty nameProperty = new SimpleStringProperty();

    private BooleanProperty nameOkProperty = new SimpleBooleanProperty(false);

    private StringProperty surnameProperty = new SimpleStringProperty();

    private BooleanProperty surnameOkProperty = new SimpleBooleanProperty(false);

    private IntegerProperty yearProperty = new SimpleIntegerProperty();

    private BooleanProperty checkBoxOk = new SimpleBooleanProperty(false);

    private BooleanProperty disableSurnameProperty = new SimpleBooleanProperty(true);

    private StringProperty ageProperty = new SimpleStringProperty();

    private BooleanProperty buttonProperty = new SimpleBooleanProperty(false);

    private IntegerProperty actualYearProperty = new SimpleIntegerProperty(LocalDate.now().getYear());


    //logic of the program
    public PersonViewModel(){
        nameOkProperty.bind(nameProperty.isNotEmpty());
        disableSurnameProperty.bind(nameProperty.isEmpty());
        surnameOkProperty.bind(surnameProperty.isNotEmpty());
        buttonProperty.bind(checkBoxOk.not());
        ageProperty.bind(new When(yearProperty.isNotEqualTo(0))
                .then(actualYearProperty.subtract(yearProperty).asString()).otherwise(""));

    }

    public BooleanProperty getButtonProperty() {
        return buttonProperty;
    }

    public void setButtonProperty(BooleanProperty buttonProperty) {
        this.buttonProperty = buttonProperty;
    }

    public IntegerProperty getActualYearProperty() {
        return actualYearProperty;
    }

    public void setActualYearProperty(IntegerProperty actualYearProperty) {
        this.actualYearProperty = actualYearProperty;
    }

    public BooleanProperty getDisableSurnameProperty() {
        return disableSurnameProperty;
    }

    public void setDisableSurnameProperty(BooleanProperty disableSurnameProperty) {
        this.disableSurnameProperty = disableSurnameProperty;
    }

    public StringProperty getNameProperty() {
        return nameProperty;
    }

    public void setNameProperty(StringProperty nameProperty) {
        this.nameProperty = nameProperty;
    }

    public StringProperty getSurnameProperty() {
        return surnameProperty;
    }

    public void setSurnameProperty(StringProperty surnameProperty) {
        this.surnameProperty = surnameProperty;
    }

    public IntegerProperty getYearProperty() {
        return yearProperty;
    }

    public void setYearProperty(IntegerProperty yearProperty) {
        this.yearProperty = yearProperty;
    }

    public BooleanProperty getConfirmProperty() {
        return checkBoxOk;
    }

    public void setConfirmProperty(BooleanProperty confirmProperty) {
        this.checkBoxOk = confirmProperty;
    }

    public StringProperty getAgeProperty() {
        return ageProperty;
    }

    public void setAgeProperty(StringProperty ageProperty) {
        this.ageProperty = ageProperty;
    }

    public BooleanProperty getNameOkProperty() {
        return nameOkProperty;
    }

    public void setNameOkProperty(BooleanProperty nameOkProperty) {
        this.nameOkProperty = nameOkProperty;
    }

    public BooleanProperty getSurnameOKProperty() {
        return surnameOkProperty;
    }

    public void setSurnameOKProperty(BooleanProperty surnameOKProperty) {
        this.surnameOkProperty = surnameOKProperty;
    }

}