import javax.swing.*;

/**
 * Created by Alin on 10/17/2016.
 */
public class LabeledTextField {

    private JLabel textLabel;
    private JTextField Area;

    public  LabeledTextField(JPanel frame,String Label,String textArea, int leftPadding, int topPadding, int height, int width){

        textLabel = new JLabel(Label);
        Area = new JTextField(textArea);

        int labelLeftPadding = leftPadding + 70;
        int areaWidth = width + 100;
        Area.setBounds(labelLeftPadding,topPadding,areaWidth,height);
        textLabel.setBounds(leftPadding,topPadding,width,height);
        textLabel.setVisible(true);
        frame.add(textLabel);
        frame.add(Area);

    }

    public String getText(){

        return Area.getText();
    }

}
