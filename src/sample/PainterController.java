package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class PainterController {

    private enum  PenSize{
        SMALL(2),
        MEDIUM(4),
        LARGE(6);

        private final int radius;

        PenSize(int radius){
            this.radius =  radius;
        }
        public int getRadius(){
            return radius;
        }
    }

    @FXML private AnchorPane drawingAreaPane;
    @FXML private Slider redSlider;
    @FXML private Slider greenSlider;
    @FXML private Slider blueSlider;
    @FXML private Slider alphaSlider;
    @FXML private TextField redTextField;
    @FXML private TextField greenTextField;
    @FXML private TextField blueTextField;
    @FXML private TextField alphaTextField;
    @FXML private Rectangle colorRectangle;
    @FXML private RadioButton smallRadioButton;
    @FXML private ToggleGroup sizeToggleGroup;
    @FXML private RadioButton mediumRadioButton;
    @FXML private RadioButton largeRadioButton;
    @FXML private Button clearButton;
    @FXML private Button undoButton;

    private PenSize radius = PenSize.MEDIUM;
    private Paint brushColor;


    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private double alpha = 1.0;

    public void initialize(){

        smallRadioButton.setUserData(PenSize.SMALL);
        mediumRadioButton.setUserData(PenSize.MEDIUM);
        largeRadioButton.setUserData(PenSize.LARGE);

        redTextField.textProperty().bind(redSlider.valueProperty().asString("%.0f"));
        greenTextField.textProperty().bind(greenSlider.valueProperty().asString("%.0f"));
        blueTextField.textProperty().bind(blueSlider.valueProperty().asString("%.0f"));
        alphaTextField.textProperty().bind(alphaSlider.valueProperty().asString("%.2f"));

        redSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                red = newValue.intValue();
                colorRectangle.setFill(Color.rgb(red, green, blue,alpha));
                brushColor = colorRectangle.getFill();
            }
        }
        );

        greenSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,Number oldValue, Number newValue) {
                green = newValue.intValue();
                colorRectangle.setFill(Color.rgb(red, green, blue,alpha));
                brushColor = colorRectangle.getFill();
            }
        }
        );

        blueSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number>ov, Number oldValue, Number newValue) {
                blue = newValue.intValue();
                colorRectangle.setFill(Color.rgb(red, green, blue,alpha));
                brushColor = colorRectangle.getFill();
            }
        }
        );

        alphaSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number>ov, Number oldValue, Number newValue) {
                alpha = newValue.doubleValue();
                colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                brushColor = colorRectangle.getFill();
            }
        }
        );
    }




    @FXML
    public void drawingAreaMouseDragged(MouseEvent mouseEvent) {
        Circle newCircle = new Circle(mouseEvent.getX(), mouseEvent.getY(), radius.getRadius(), brushColor);
        drawingAreaPane.getChildren().add(newCircle);
    }


    @FXML
    void sizeRadioButtonSelected(ActionEvent event) {
        radius = (PenSize) sizeToggleGroup.getSelectedToggle().getUserData();
    }

    @FXML
    void undoButtonPressed(ActionEvent event) {
        int count = drawingAreaPane.getChildren().size();
        if(count > 0){
            drawingAreaPane.getChildren().remove(count-1);
        }
    }

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }


}