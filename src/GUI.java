import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;


public class GUI extends JFrame {
    private JButton button1 = new JButton("Add film");

    private JTextField Search = new JTextField("search",5);
    private JTextField inputN = new JTextField("",5);
    private JTextField inputY = new JTextField("",5);
    private JTextField inputR = new JTextField("",5);
    private JLabel label1 = new JLabel("Input name");
    private JLabel label2 = new JLabel("Input year");
    private JLabel label3 = new JLabel("Input Rating");
    private JButton buttonSA = new JButton("Show All");
    private JButton buttonsearch = new JButton("Search");
    private JRadioButton radioSN = new JRadioButton("Search by Name");
    private JRadioButton radioSY = new JRadioButton("Search by Year");
    private JRadioButton radioSR = new JRadioButton("Search by Rating");

    private JRadioButton radioSAN = new JRadioButton("Sort by Name");
    private JRadioButton radioSAY = new JRadioButton("Sort by Year");
    private JRadioButton radioSAR = new JRadioButton("Sort by Rating");
    //private JCheckBox check = new JCheckBox("check", false);

    public GUI(){
        super("Films Catalogue");
        this.setBounds(200, 200, 300, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(20, 1, 2,2));
        container.add(label1);
        container.add(inputN);
        container.add(label2);
        container.add(inputY);
        container.add(label3);
        container.add(inputR);

        ButtonGroup group = new ButtonGroup();
        group.add(radioSN);
        group.add(radioSY);
       //buttonSA.addActionListener(new ButtonEventListener());
        container.add(button1);     //add film
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!inputN.getText().isEmpty()){
                    try{
                        Film newf = new Film();
                        newf.setName(inputN.getText());
                        if(!inputY.getText().isEmpty()){
                            int year = Integer.parseInt(inputY.getText());
                            newf.setYear(year);
                        }
                        if(!inputY.getText().isEmpty()){
                            newf.setRating(Integer.parseInt(inputR.getText()));
                        }
                        try{
                            newf.writeFilm();
                            JOptionPane.showMessageDialog(null, "Film added!", "Congrats!", JOptionPane.PLAIN_MESSAGE);

                        } catch (FileNotFoundException ex){
                            JOptionPane.showMessageDialog(null, "File not found", "Error!", JOptionPane.PLAIN_MESSAGE);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                      } catch (Exception ex){
                       JOptionPane.showMessageDialog(null, "Something went wrong!", "Error!", JOptionPane.PLAIN_MESSAGE);
                    }

                }
            }
        });
       container.add(buttonSA);     //show all

        container.add(radioSAN);
        //radioSN.setSelected(true);
        container.add(radioSAY);
        container.add(radioSAR);
        container.add(Search);
        container.add(radioSN);
        //radioSN.setSelected(true);
        container.add(radioSY);
        container.add(radioSR);
        container.add(buttonsearch);
    }

//class ButtonEventListener implements ActionListener{
 //       public  void actionPerformed(ActionEvent e){
          //input.getText()
        //  radio1.isSelected()
       //   check.isSelected())
      // JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
      //  }
//}
}
