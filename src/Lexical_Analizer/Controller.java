package Lexical_Analizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bttn_save;

    @FXML
    private ImageView img_monkey;

    @FXML
    private Button bttn_analize;

    @FXML
    private TextArea txt_field;

    @FXML
    private TextField txt_directory;

    @FXML
    private Label lbl_instructions3;

    @FXML
    private Label lbl_instructions2;

    @FXML
    private Button bttn_reset;

    @FXML
    private Label lbl_title;

    @FXML
    private Label lbl_instructions1;

    @FXML
    private Separator separator;

    @FXML
    private Label lbl_preview;


    public String file_path;
    public File file;
    public Scanner read;

    public String code;
    public String symbol_table = "";
    public int character_count;
    public char aux_char;
    public String aux_string;
    public boolean real_flag = false;
    public boolean keyword_flag = false;


    public int count_if = 0; // Keyword counters
    public int count_then = 0;
    public int count_else = 0;
    public int count_begin = 0;
    public int count_end = 0;
    public int count_keyword = 0;

    public int count_character = 0; // Identifier Counter
    public int count_identifier = 0;

    public int count_integer = 0; // Integer Counter
    public int count_real = 0; // Real Counter
    public int count_digit = 0;

    public int count_openParenthesis = 0; // Special Counters
    public int count_closeParenthesis = 0;
    public int count_openBracket = 0;
    public int count_closeBracket = 0;
    public int count_plus = 0;
    public int count_minus = 0;
    public int count_equals = 0;
    public int count_coma = 0;
    public int count_semiColon = 0;
    public int count_special = 0;

    public Controller() throws FileNotFoundException {
    }

    @FXML
    void analizeCode(ActionEvent event) {

        file_path = txt_directory.getText(); // fetch file path from GUI object text_directory
        file = new File(file_path);

        try {

            read = new Scanner(file);


            while (read.hasNext()){

                code = read.nextLine();
                //symbol_table = symbol_table + ("\nANALYZING LINE: " + code);

                character_count = code.length();

                for (int i = 0; i < character_count; i++){
                    aux_char = code.charAt(i);
                    //symbol_table = symbol_table + ("ANALYZING CHAR: " + aux_char);

                    if (Character.isDigit(aux_char)){
                        aux_string = aux_char + "";
                        real_flag = false;

                        for (int j = i + 1 ; j < character_count; j++){
                            if (Character.isDigit(code.charAt(j)) | code.charAt(j) == '.'){
                                aux_string = aux_string + code.charAt(j); // finds all chars in integer

                                i = j; // i continues from where j left

                                if (code.charAt(j) == '.' & !real_flag){ // flag for real numbers
                                    real_flag = true;
                                }

                            } else {
                                i = j - 1; // i continues where j left
                                j = character_count; // exit loop
                            }
                        }

                        if (!real_flag){ // check if number is integer or real
                            symbol_table = symbol_table +  ("\nfound:\t\t " + aux_string + " \t\t\t:token class integer");
                            count_integer++;
                            count_digit = count_digit + aux_string.length();

                        } else {
                            symbol_table = symbol_table +  ("\nfound:\t\t " + aux_string + " \t\t\t:token class real");
                            count_real++;
                        }
                    }

                    if (Character.isLetter(aux_char)){
                        aux_string = aux_char + "";
                        keyword_flag = false;

                        for (int j = i + 1 ; j < character_count; j++){
                            if (Character.isLetter(code.charAt(j))){
                                aux_string = aux_string + code.charAt(j); // finds all chars in word
                                i = j; // i continues from where j left
                            } else {
                                i = j - 1; // i continues where j left
                                j = character_count; // exit loop
                            }
                        }

                        switch (aux_string) {
                            case "if":
                                keyword_flag = true;
                                count_if++;
                                break;
                            case "then":
                                keyword_flag = true;
                                count_then++;
                                break;
                            case "else":
                                keyword_flag = true;
                                count_else++;
                                break;
                            case "begin":
                                keyword_flag = true;
                                count_begin++;
                                break;
                            case "end":
                                keyword_flag = true;
                                count_end++;
                                break;
                        }

                        if (keyword_flag) {
                            symbol_table = symbol_table + ("\nfound:\t\t " + aux_string + " \t\t\t:token class keyword");
                             count_keyword = count_keyword + count_begin + count_end + count_else + count_if + count_then;
                        } else {
                            symbol_table = symbol_table +  ("\nfound:\t\t " + aux_string + " \t\t\t:token class identifier");
                            count_character = count_character + aux_string.length();
                            count_identifier++;
                        }
                    }

                    switch (aux_char){
                        case '(': symbol_table = symbol_table + ("\nfound:\t\t " + aux_char + " \t\t\t:token class special");
                            count_openParenthesis++;
                            count_special++;
                            break;
                        case ')': symbol_table = symbol_table + ("\nfound:\t\t " + aux_char + " \t\t\t:token class special");
                            count_closeParenthesis++;
                            count_special++;
                            break;
                        case '[': symbol_table = symbol_table + ("\nfound:\t\t " + aux_char + " \t\t\t:token class special");
                            count_openBracket++;
                            count_special++;
                            break;
                        case ']': symbol_table = symbol_table + ("\nfound:\t\t " + aux_char + " \t\t\t:token class special");
                            count_closeBracket++;
                            count_special++;
                            break;
                        case '+': symbol_table = symbol_table + ("\nfound:\t\t " + aux_char + " \t\t\t:token class special");
                            count_plus++;
                            count_special++;
                            break;
                        case '-': symbol_table = symbol_table + ("\nfound:\t\t " + aux_char + " \t\t\t:token class special");
                            count_minus++;
                            count_special++;
                            break;
                        case '=': symbol_table = symbol_table + ("\nfound:\t\t " + aux_char + " \t\t\t:token class special");
                            count_equals++;
                            count_special++;
                            break;
                        case ',': symbol_table = symbol_table + ("\nfound:\t\t " + aux_char + " \t\t\t:token class special");
                            count_coma++;
                            count_special++;
                            break;
                        case ';': symbol_table = symbol_table + ("\nfound:\t\t " + aux_char + " \t\t\t:token class special");
                            count_semiColon++;
                            count_special++;
                            break;
                    }
                }
            }

            symbol_table = symbol_table + ("\n\n<<<<< TOTAL TOKENS FOUND >>>>> \n" +
                    "\nIdentifiers x" + count_identifier + "\nA total of " + count_character + " Characters\n" +
                    "\nIntegers x" + count_integer + " and Real x" + count_real + "\nA total of " + count_digit +" Digits"+ "\n" +
                    "\nKeywords x" + count_keyword + "\n if x" + count_if + " then x" + count_then + " else x" + count_else + " begin x" + count_begin + " end x" + count_end + "\n" +
                    "\nSpecial x" + count_special + "\n ( x" + count_openParenthesis + "\t\t ) x" + count_closeParenthesis + "\t\t [ x" + count_openBracket + "\n ] x" + count_closeBracket + "\t\t + x" + count_plus + "\t\t - x" + count_minus + "\n = x" + count_equals + "\t\t , x" + count_coma + "\t\t ; x" + count_semiColon );

            txt_field.setText(symbol_table);
            bttn_reset.setDisable(false);
            bttn_save.setDisable(false);
            bttn_analize.setDisable(true);
            txt_directory.setEditable(false);

        } catch (FileNotFoundException e) {
            txt_directory.setText("ERROR!: .txt file not found or invalid path. Try Again!");
            e.printStackTrace();
        }
    }

    @FXML
    void save(ActionEvent event) {
        try(  PrintWriter out = new PrintWriter( "symbol_table.txt" )  ){
            out.println(txt_field.getText());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void reset(ActionEvent event) {

        bttn_analize.setDisable(false);
        bttn_save.setDisable(true);
        bttn_reset.setDisable(true);
        txt_directory.setEditable(true);

        txt_field.setText("Introduction to Programming Languages\n" +
                "\n" +
                "HOMEWORK #2\n" +
                "\n" +
                "Author: Mauro J. Pappaterra\n" +
                "Net id: ew3525\n" +
                "Area: Computer Science\n" +
                "Course Code: CS 3120\n" +
                "Quarter: Winter 2017\n" +
                "\n" +
                "California State University East Bay");

        count_if = 0; // Keyword counters
        count_then = 0;
        count_else = 0;
        count_begin = 0;
        count_end = 0;
        count_keyword = 0;

        count_character = 0; // Identifier Counter
        count_identifier = 0;

        count_integer = 0; // Integer Counter
        count_real = 0; // Real Counter
        count_digit = 0;

        count_openParenthesis = 0; // Special Counters
        count_closeParenthesis = 0;
        count_openBracket = 0;
        count_closeBracket = 0;
        count_plus = 0;
        count_minus = 0;
        count_equals = 0;
        count_coma = 0;
        count_semiColon = 0;
        count_special = 0;
    }

    @FXML
    void initialize() {
        assert bttn_save != null : "fx:id=\"bttn_save\" was not injected: check your FXML file 'GUI.fxml'.";
        assert img_monkey != null : "fx:id=\"img_monkey\" was not injected: check your FXML file 'GUI.fxml'.";
        assert bttn_analize != null : "fx:id=\"bttn_analize\" was not injected: check your FXML file 'GUI.fxml'.";
        assert txt_field != null : "fx:id=\"txt_field\" was not injected: check your FXML file 'GUI.fxml'.";
        assert txt_directory != null : "fx:id=\"txt_directory\" was not injected: check your FXML file 'GUI.fxml'.";
        assert lbl_instructions3 != null : "fx:id=\"lbl_instructions3\" was not injected: check your FXML file 'GUI.fxml'.";
        assert lbl_instructions2 != null : "fx:id=\"lbl_instructions2\" was not injected: check your FXML file 'GUI.fxml'.";
        assert bttn_reset != null : "fx:id=\"bttn_reset\" was not injected: check your FXML file 'GUI.fxml'.";
        assert lbl_title != null : "fx:id=\"lbl_title\" was not injected: check your FXML file 'GUI.fxml'.";
        assert lbl_instructions1 != null : "fx:id=\"lbl_instructions1\" was not injected: check your FXML file 'GUI.fxml'.";
        assert separator != null : "fx:id=\"separator\" was not injected: check your FXML file 'GUI.fxml'.";
        assert lbl_preview != null : "fx:id=\"lbl_preview\" was not injected: check your FXML file 'GUI.fxml'.";

    }
}