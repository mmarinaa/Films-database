import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;


public class GUI extends JFrame {
    private JButton button1 = new JButton("Add film");

    private JTextField Search = new JTextField("search",5);
    private JTextField inputN = new JTextField("",5);
    private JTextField inputY = new JTextField("",5);
    private JTextField inputR = new JTextField("",5);
    private JLabel label1 = new JLabel("Input Title");
    private JLabel label2 = new JLabel("Input year");
    private JLabel label3 = new JLabel("Input Rating");
    private JButton buttonSA = new JButton("Show All");
    private JButton buttonsearch = new JButton("Search");
    private JButton buttonsort = new JButton("Sort");
    private JRadioButton radioSN = new JRadioButton("Search by Name");
    private JRadioButton radioSY = new JRadioButton("Search by Year");
    private JRadioButton radioSR = new JRadioButton("Search by Rating");

    private JRadioButton radioSAN = new JRadioButton("Sort by Name");
    private JRadioButton radioSAY = new JRadioButton("Sort by Year");
    private JRadioButton radioSAR = new JRadioButton("Sort by Rating");
    //private JCheckBox check = new JCheckBox("check", false);


    public List<Film> read(){

        String fileName = "src/films.txt";

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        FilmDetails filmDetails = new FilmDetails();

        try {
            return  filmDetails.getFilmFromFile(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public GUI() {
        super("Films Catalogue");
        this.setBounds(200, 200, 300, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(20, 1, 2, 2));
        container.add(label1);
        container.add(inputN);
        container.add(label2);
        container.add(inputY);
        container.add(label3);
        container.add(inputR);
        container.add(button1);

        /*
         * Add new film parameters(Title, Year, Rating)
         * using text fields
         * Button "Add Film"
         * */
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Film> films = read();
                if (!inputN.getText().isEmpty()) {
                    try {

                        Film newf = new Film();
                        newf.setName(inputN.getText());
                        if (!inputY.getText().isEmpty()) {
                            int year = Integer.parseInt(inputY.getText());
                            newf.setYear(year);
                        }
                        if (!inputR.getText().isEmpty()) {
                            newf.setRating(Double.parseDouble(inputR.getText()));
                        }
                        boolean cont = true;

                            if(search(newf.getName())){
                                JOptionPane.showMessageDialog(null, "Film already exists", "Error", JOptionPane.PLAIN_MESSAGE);
                                cont = false;
                        }
                        if(cont) {
                            try {
                                newf.writeFilm();
                                JOptionPane.showMessageDialog(null, "Film added!", "Congrats!", JOptionPane.PLAIN_MESSAGE);

                            } catch (FileNotFoundException ex) {
                                JOptionPane.showMessageDialog(null, "File not found", "Error!", JOptionPane.PLAIN_MESSAGE);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }


                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Something went wrong!", "Error!", JOptionPane.PLAIN_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Field \"Title\" cant be empty", "Error!", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        /*
         * Show all films using ShowMessageDialog
         * Button "show all"
         * */

        container.add(buttonSA);
        buttonSA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Film> films = read();
                String list = "";
                for (int i = 0; i < films.size(); i++) {
                    list += i + 1 + ": " + films.get(i).getName() + "\n     year: " + films.get(i).getYear() + "\n    " +
                            " rating: " + films.get(i).getRating();
                    list += "\n";
                }
                String finalList = list;
                SwingUtilities.invokeLater(() -> {
                    JTextArea textArea = new JTextArea(20, 25);
                    textArea.setText(finalList);
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    JOptionPane.showMessageDialog(scrollPane, scrollPane, "Film list", JOptionPane.PLAIN_MESSAGE);
                });
            }
        });
        /*
         * Sort films by Title, Year, Rating
         * Button "Sort"
         * */
        ButtonGroup group1 = new ButtonGroup();//sort parametrs
        group1.add(radioSAN);                   //by name
        group1.add(radioSAY);                   //by year
        group1.add(radioSAR);                   //by rating
        container.add(radioSAN);
        container.add(radioSAY);
        container.add(radioSAR);
        container.add(buttonsort);
        //button "Sort"
        buttonsort.addActionListener(new ActionListener() {
            List<Film> films = read();
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioSAN.isSelected()) {
                    films.sort(Film.FilmNameComparator);
                }
                if (radioSAY.isSelected()) {
                    films.sort(Film::compareYearTo);
                }
                if (radioSAR.isSelected()) {
                    films.sort(Film.FilmRatingComparator);
                }
                String list = "";
                for (int i = 0; i < films.size(); i++) {
                    list += i + 1 + ": " + films.get(i).getName() + "\n     year: " + films.get(i).getYear() + "\n    " +
                            " rating: " + films.get(i).getRating();
                    list += "\n";
                }
                //JOptionPane.showMessageDialog(null, list, "Film List", JOptionPane.PLAIN_MESSAGE);
                String finalList = list;
                {
                    JTextArea textArea = new JTextArea(20, 25);
                    textArea.setText(finalList);
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    JOptionPane.showMessageDialog(scrollPane, scrollPane, "Film list", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        /*
         * Search films by Title, Year, Rating
         * Button "Search"
         * */
        container.add(Search);
        /*ButtonGroup group = new ButtonGroup();
        group.add(radioSN);
        group.add(radioSY);
        group.add(radioSR);
        container.add(radioSN);
        container.add(radioSY);
        container.add(radioSR);*/
        container.add(buttonsearch);
        buttonsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search(String.valueOf(Search.getText()));
            }
        });
    }
    public boolean search(String s){
        boolean res = false;
        try {
            String message = parseFile("src/films.txt", s);
            if (message == "")
            {
                JOptionPane.showMessageDialog(null, "Film not found", "Film not Found!", JOptionPane.PLAIN_MESSAGE);
            }
            else {
                res = true;
                JOptionPane.showMessageDialog(null, message, "Film Found!", JOptionPane.PLAIN_MESSAGE);
            }

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return res;
    }
public String parseFile(String fileName, String searchStr) throws FileNotFoundException {
    Scanner scan = new Scanner(new File(fileName));
String message = "";
    int counter = 0;
    while (scan.hasNext()) {
        String line = scan.nextLine().toString();
        if (line.contains(searchStr)) {
            counter++;
            String[] filmDetails = line.trim().split(",");
            message+=counter+": "+filmDetails[0]+"\n   year: "+filmDetails[1]+"\n   rating: "+filmDetails[2]+"\n";
        }
    }
return message;

}
}

