package calculator.controllers;

import calculator.utils.EvaluateString;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CalculatorController {

    @FXML
    private Label expression;

    @FXML
    private Label result;

    public void insertNumber(String number){
        
        expression.setText(expression.getText() + number);

    }

    public void insertOperator(String operator){
        expression.setText(expression.getText()+ " "+ operator +" ");
    }

    public void clearExpression(){
        expression.setText("");
        result.setText("");
    }
    public void deleteExpression()
    {
        String exp = expression.getText();
        //todo in stringbuilder
        if(exp.length()>0) {
            char[] tab = new char[exp.length() - 1];


            for (int i = 0; i < exp.length() - 1; i++)
                tab[i] = exp.charAt(i);

            exp = String.valueOf(tab);
            expression.setText(exp);
        }
    }

    public Label getExpression() {
        return expression;
    }


    public void setResult(String newResult) {
        this.result.setText("= "+newResult);
    }

    public Label getResult() {
        return result;
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        
        Button button = (Button)mouseEvent.getSource();
        String buttonText = button.getText();


        switch (buttonText){
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "0":
                insertNumber(buttonText);
                break;
            case"+":
            case"-":
            case"*":
            case"/":
                insertOperator(buttonText);
                break;
            case"Clear":
                clearExpression();
                break;
            case"Del":
                deleteExpression();
                break;
            case "=":
                double result = EvaluateString.evaluate(this.getExpression().getText());
                setResult(String.valueOf(result));
                break;
            case "Hist":
                //openHistoryWindow();
                break;

        }
    }
}
