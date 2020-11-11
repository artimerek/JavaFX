package properties;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonViewModel {

    private StringProperty propertyTextField = new SimpleStringProperty(this, "nameProperty", "Konradek");

    public StringProperty getPropertyTextField() {
        return propertyTextField;
    }

    public StringProperty propertyTextFieldProperty() {
        return propertyTextField;
    }

    public void setPropertyTextField(StringProperty propertyTextField) {
        this.propertyTextField = propertyTextField;
    }
}
