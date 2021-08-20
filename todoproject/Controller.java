package todoproject;

import javafx.fxml.FXML;
import todoproject.data.Items;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<Items> itemsTodo;

    @FXML
    public void initialize(){
        // some data for tests
        Items item1 = new Items(LocalDate.of(2020,8,31),"Birthday Party",
                "Prepare a cake, buy some drinks etc");
        Items item2 = new Items(LocalDate.of(2019,8,31),"Birthday Party",
                "Prepare a cake, buy some drinks etc");
        Items item3 = new Items(LocalDate.of(2018,8,31),"Birthday Party",
                "Prepare a cake, buy some drinks etc");
        // init arraylist with type of items
        this.itemsTodo = new ArrayList<Items>();

        itemsTodo.add(item1);
        itemsTodo.add(item2);
        itemsTodo.add(item3);
    }

}
