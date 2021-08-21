package todoproject;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import todoproject.data.Items;
import todoproject.data.ItemsData;

import java.time.LocalDate;

public class DialogController {
    @FXML
    private TextField shortDescData;
    @FXML
    private TextArea detailsData;
    @FXML
    private DatePicker deadlineData;

    public Items processResults(){       // getting input form user then save to a temp object, add to list then returned to be displayed
        String shortDescription = shortDescData.getText().trim();
        String details = detailsData.getText().trim();
        LocalDate deadline = deadlineData.getValue();
        Items itemToBeReturned = new Items(deadline,shortDescription,details);
        ItemsData.getInstance().addItem(itemToBeReturned);
        return itemToBeReturned;
    }
}

