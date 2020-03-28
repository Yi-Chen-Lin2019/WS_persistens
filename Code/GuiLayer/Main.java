package GuiLayer;

import javax.swing.JFrame;
import GuiLayer.*;
import javax.swing.UIManager;


public class Main {

    public static void main(String[] args) {
        
        //Set system window look
        try{UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch(Exception e) {}
        

        MainMenuUI main = new MainMenuUI();
        OrderUI f = new OrderUI();

        }
    
}
