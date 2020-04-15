package skengine2.utils;

import javax.swing.*;
import java.awt.*;

public class Debug {
    private static String logText;
    private static JTextArea textArea;
    private static JFrame frame;
    public Debug(){
        init();
    }
    private static void init(){
        frame = new JFrame("Debug console");
        textArea = new JTextArea();
        logText = "";
        JScrollPane sp = new JScrollPane(textArea);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setSize(900, 220);
        frame.getContentPane().add(sp);
        frame.setVisible(true);
    }

    public static void log(String text){
        textArea.append(text+'\n');
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    public static void close(){
        frame.dispose();
    }
}
