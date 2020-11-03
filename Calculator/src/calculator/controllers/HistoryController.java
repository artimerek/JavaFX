package calculator.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class HistoryController {

    @FXML
    private ListView history;

    public void initliazeCalculation(ArrayList<String> calc_history){
        calc_history.forEach((calculation)->{
            history.getItems().add(calculation);
        });
        }
    }

