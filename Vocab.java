import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

/**
 * Takes in the Italian vocab that I know and adds to a list.
 * 
 * It will save it each time and load it, then data will get added
 * to the list each time.
 * 
 * It may not always load the data depending on what the user chooses
 * There are also methods to organize the data (i.e. alphabetize)
 *
 * @author (Tucker Tavarone)
 * @version (5/4/18)
 */
public class Vocab extends JPanel implements ActionListener
{
    private static PrintWriter out; //writing new words
    private static Scanner in; //for reading the previous save file
    private static String fileName = "italianVocab.txt"; //final name
    private static File saveFile; //file object for it

    public void actionPerformed(ActionEvent e){}

    /**
     * Initial constructor for everything
     */
    public Vocab(){
        super();
        try{
            setOpaque(true);
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(500, 500));

            saveFile = new File(fileName);
            out = new PrintWriter(new FileWriter(fileName, true));
            in = new Scanner(saveFile);

        }
        catch(FileNotFoundException e){
            System.err.print("The file "+fileName+" does not exist.");
        }
        catch(IOException e){
            System.err.print(e.toString());
        }
    }

    /**
     * Gets input from the user for new words, when inputting the user
     * types the word, and then selects the type of word that it is
     * 
     * The user may also choice to rapid fire words, which will save them
     * elsewhere temporarily and at the end they can go through and select
     * what words are what
     */
    private void getInput(){
        JOptionPane chooser = new JOptionPane();
        Button[] options = {new Button("Rapid-fire enter"), 
                new Button("Detailed entry")};//new buttons

        //action listeners to begin a specified event
        options[0].addActionListener(new ActionListener() {//rapid fire
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane input = new JOptionPane(chooser);
                    chooser.getRootFrame().dispose();
                    
                    ArrayList<String> newWords = new ArrayList<>();
                    String word;
                    do{
                        word = input.
                        showInputDialog(null, "Enter a word");

                        if(word!=null){ newWords.add(word); }

                    }while(word!=null);
                    logger(newWords);
                }
            });
        options[1].addActionListener(new ActionListener() {//detailed
                @Override
                public void actionPerformed(ActionEvent e) {
                    //You may enter one word, or choose to enter another
                    //It is intended to be more tedious so that each
                    //word has all the proper modifications
                    System.out.println("Choice 1 is a success!");
                    System.exit(0);
                }
            });

        int choice = chooser.showOptionDialog(null,"Choose method of input",
                "Input Method Chooser", JOptionPane.DEFAULT_OPTION, 
                JOptionPane.QUESTION_MESSAGE, null, options, null);
    }

    public void askToDisplay(){
        JOptionPane pane = new JOptionPane("List previous words?", 
                JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);

        int choice = pane.showConfirmDialog(null,
                "Would you like to list previouly learned words?","Italian Vocab", 
                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

        if(choice==JOptionPane.YES_OPTION){
            displayTxt();
        }
        if(choice==JOptionPane.NO_OPTION){
            getInput();
        }
    }

    /**
     * Displays text from the file if user chooses to display it
     */
    public void displayTxt(){

    }

    /**
     * Similar to the method below, however it inputs single words
     * each time and tabulates them with the appropriate info
     * 
     * @param The word to log (in italian)
     * @param The type of word represented by an int value, passed from
     *  the method calling it;
     *   0 = adject
     *   1 = noun
     *   2 = verb
     *   3 = pronoun
     *   4 = undecided as of yet
     * @param The english translation for the word, will have to be defined
     *  by the user as of now
     */
    private void logger(String word, int type, String englishTran){
    }

    /**
     * Will write new words to the save file and saves them in a tabular 
     * format, when the document is reread to load for a new session, each
     * word will be in this format:
     * 
     * WORD(ITALIAN) TYPEOFWORD WORD(ENGLISHTranslation)
     */
    private void logger(ArrayList newWords){
        JOptionPane extraInfo = new JOptionPane();
        //this will have radio buttons and a text box to get the type of 
        //word and its english translation
        for(int i = 0; i < newWords.size(); i++){
            //show the dialog box with the word and options to modify it

            out.println(newWords.get(i));
            //for now, to debug, it just printlns the words
            //System.out.println(newWords.get(i));
            
        }
        out.close();
    }

    /**
     * This will display words if the user chooses to do so, otherwise
     * it will just display the mode that the user is in (i.e. rapid fire
     * enter or specify each word and type)
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    /**
     * 
     */
    private static void createAndShowGUI(JPanel in) {
        JFrame frame = new JFrame("Italian Vocab");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(in);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * This method creates the GUI that will begin to execute the program.
     * @param args - Command line arguments.
     */
    public static void main(String[] args) {
        Vocab vPanel = new Vocab();
        vPanel.askToDisplay();
        /*
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
        createAndShowGUI(vPanel);
        }
        });
         */
    }
}
