import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GUI extends JFrame {
    private JButton button = new JButton("Click");
    private JTextField input = new JTextField("",5);
    private JLabel label = new JLabel("input");
    private JRadioButton radio1 = new JRadioButton("select 1");
    private JRadioButton radio2 = new JRadioButton("select 2");
    private JCheckBox check = new JCheckBox("check", false);

    public GUI(){
        super("Films Catalogue");
        this.setBounds(200, 200, 250, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 2, 2,2));
        container.add(label);
        container.add(input);

        ButtonGroup group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        container.add(radio1);
        radio1.setSelected(true);
       container.add(radio2);
       container.add(check);

       button.addActionListener(new ButtonEventListener());
       container.add(button);
    }

class ButtonEventListener implements ActionListener{
        public  void actionPerformed(ActionEvent e){
            String message = "";
            message += "Button was pressed\n";
            message += "Text is" + input.getText() + "\n";
            message += (radio1.isSelected() ? "Radio 1":"Radio 2" + "is selected\n");
            message += "Checkbox is " + ((check.isSelected())? "checked":"not checked");
       JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
        }
}
}
