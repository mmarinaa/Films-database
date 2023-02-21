import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;


public class GUI extends JFrame {
    private JButton button1 = new JButton("Add film");

    private JButton button_wf = new JButton("Show watched films");
    private JButton button_wl = new JButton("Show wish list");
    private JCheckBox watched = new JCheckBox("Watched", false);
    private JCheckBox wishList = new JCheckBox("Add to wish list", false);
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



    /*
    * method for reading film
    * objects from txt file
    * "films.txt"
    */
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

        ButtonGroup group = new ButtonGroup();//sort parametrs
        group.add(wishList);                   //by name
        group.add(watched);
        container.add(watched);
        container.add(wishList);
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
                        if(wishList.isSelected()||((!watched.isSelected())&&(!wishList.isSelected()))){
                            newf.setCategory("l");
                        }else
                        if(watched.isSelected()){
                            newf.setCategory("w");
                        }

                        boolean cont = true;

                        for(int i = 0; i < films.size();i++){
                            if(Film.FilmNameComparator.compare(newf, films.get(i)) == 0){
                                JOptionPane.showMessageDialog(null, "Film already exists", "Error", JOptionPane.PLAIN_MESSAGE);
                                cont = false;
                        }
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
        container.add(button_wf);
        button_wf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Film> films = read();
                String list = "";
                int counter = 0;
                for (int i = 0; i < films.size(); i++) {
                    if (Objects.equals(films.get(i).getCategory(), "w")) {
                        counter ++;
                        list += counter + ": " + films.get(i).getName() + "\n     year: " + films.get(i).getYear() + "\n    " +
                                " rating: " + films.get(i).getRating();
                        list += "\n";
                    }
                }
                if(counter == 0) list = "Watched list is empty";
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

        container.add(button_wl);
        button_wl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Film> films = read();
                String list = "";
                int counter = 0;
                for (int i = 0; i < films.size(); i++) {
                    if (Objects.equals(films.get(i).getCategory(), "l")) {
                        counter ++;
                        list += counter + ": " + films.get(i).getName() + "\n     year: " + films.get(i).getYear() + "\n    " +
                                " rating: " + films.get(i).getRating();
                        list += "\n";
                    }
                }
                if(counter == 0) list = "Wish list is empty";
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
                }else
                if (radioSAY.isSelected()) {
                    films.sort(Film::compareYearTo);
                }else
                if (radioSAR.isSelected()) {
                    films.sort(Film.FilmRatingComparator);
                }else {
                    JOptionPane.showMessageDialog(null, "Choose parameter for sorting", "Sorting is not available", JOptionPane.PLAIN_MESSAGE);

                }

                String list = "";
                for (int i = 0; i < films.size(); i++) {
                    list += i + 1 + ": " + films.get(i).getName() + "\n     year: " + films.get(i).getYear() + "\n    " +
                            " rating: " + films.get(i).getRating();
                    list += "\n";
                }

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
        container.add(buttonsearch);
        buttonsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!search(String.valueOf(Search.getText())))
                    JOptionPane.showMessageDialog(null, "Film not found", "Film not Found!", JOptionPane.PLAIN_MESSAGE);

            }
        });
    }
    public boolean search(String s){
        boolean res = false;
        try {
            String message = parseFile("src/films.txt", s);
            if (message != "")
            {
                res = true;
                JOptionPane.showMessageDialog(null, message, "Film Found!", JOptionPane.PLAIN_MESSAGE);
            }
            //JOptionPane.showMessageDialog(null, "Film not found", "Film not Found!", JOptionPane.PLAIN_MESSAGE);
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
            String cat = "";
            if(filmDetails[3].equals("w")) cat = "Watched";
            if(filmDetails[3].equals("l")) cat = "Wish list";
            message+=counter+": "+filmDetails[0]+"\n   year: "+filmDetails[1]+"\n   rating: "+filmDetails[2]+"\n    category: "+cat+"\n";
        }
    }
return message;

}
}

