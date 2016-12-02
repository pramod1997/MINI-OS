
package os;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

//TODO: Keep a global StringBuilder to decrease memory footprint

public class TestTerminal {
    public static void main(String[] args) {
        Terminal term = Terminal.getInstance();
        term.open(0, 0, 700, 700);
       
        
    }
}

 class Terminal {
    private final JFrame frm = new JFrame("Terminal");
    private final JTextArea txtArea = new JTextArea();
    private final JScrollPane scrollPane = new JScrollPane();
    private final CommandProcessor processor = CommandProcessor.getInstance();
    private final String LINE_SEPARATOR = System.lineSeparator();
    private final Font font = new Font("SansSerif", Font.BOLD, 15);
    private String dirstring="/home";

    private Terminal() {
        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frm.getContentPane().add(scrollPane);
        scrollPane.setViewportView(txtArea);
        txtArea.addKeyListener(new KeyListener());
        txtArea.setFont(font);
        disableArrowKeys(txtArea.getInputMap());
    }

    private void disableArrowKeys(InputMap inputMap) {
        String[] keystrokeNames = { "UP", "DOWN", "LEFT", "RIGHT", "HOME" };
        for (int i = 0; i < keystrokeNames.length; ++i)
            inputMap.put(KeyStroke.getKeyStroke(keystrokeNames[i]), "none");
    }

    public void open(final int xLocation, final int yLocation, final int width,
            final int height) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frm.setBounds(xLocation, yLocation, width, height);
                frm.setVisible(true);
                showPrompt();
            }
        });
    }

    public void close() {
        frm.dispose();
    }

    public void clear() {
        txtArea.setText("");
        showPrompt();
    }

    private void showPrompt() {
        txtArea.setText(txtArea.getText() + "> ");
        
        
    }

    private void showNewLine() {
        txtArea.setText(txtArea.getText() + LINE_SEPARATOR);
    }

    private class KeyListener extends KeyAdapter {
        private final int ENTER_KEY = KeyEvent.VK_ENTER;
        private final int BACK_SPACE_KEY = KeyEvent.VK_BACK_SPACE;
        private final String BACK_SPACE_KEY_BINDING = getKeyBinding(
                txtArea.getInputMap(), "BACK_SPACE");
        private final int INITIAL_CURSOR_POSITION = 2;

        private boolean isKeysDisabled;
        private int minCursorPosition = INITIAL_CURSOR_POSITION;

        private String getKeyBinding(InputMap inputMap, String name) {
            return (String) inputMap.get(KeyStroke.getKeyStroke(name));
        }

        public void keyPressed(KeyEvent evt) {
            int keyCode = evt.getKeyCode();
            if (keyCode == BACK_SPACE_KEY) {
                int cursorPosition = txtArea.getCaretPosition();
                if (cursorPosition == minCursorPosition && !isKeysDisabled) {
                    disableBackspaceKey();
                } else if (cursorPosition > minCursorPosition && isKeysDisabled) {
                    enableBackspaceKey();
                }
            } else if (keyCode == ENTER_KEY) {
                disableTerminal();
                String command = extractCommand();
                executeCommand(command);
                showNewLine();
                showPrompt();
                enableTerminal();
            }
        }

        public void keyReleased(KeyEvent evt) {
            int keyCode = evt.getKeyCode();
            if (keyCode == ENTER_KEY) {
                txtArea.setCaretPosition(txtArea.getCaretPosition() - 1);
                setMinCursorPosition();
            }
        }

        private void disableBackspaceKey() {
            isKeysDisabled = true;
            txtArea.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"),
                    "none");
        }

        private void enableBackspaceKey() {
            isKeysDisabled = false;
            txtArea.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"),
                    BACK_SPACE_KEY_BINDING);
        }

        private void setMinCursorPosition() {
            minCursorPosition = txtArea.getCaretPosition();
        }
    }

    public void enableTerminal() {
        txtArea.setEnabled(true);
    }

    public void disableTerminal() {
        txtArea.setEnabled(false);
    }
    
    private void executeCommand(String command) {
       // processor.processCmd(command);
       
       // breaking the command in two parts
       String firstcommand="";
       String secondcommand="";
       for(int i=0;i<command.length();i++)
       {
           if( command.charAt(i)==' ')
           {
               firstcommand=command.substring(0,i);
               secondcommand=command.substring(i+1,command.length());
               break;
           }
           if(i==command.length()-1)
           {
               firstcommand=command;
               secondcommand="";
               break;
           }
       }
       
       //System.out.println(firstcommand + " " + secondcommand);
       
       //done 
       if ("cd".equals(firstcommand) && ("".equals(secondcommand))){
           txtArea.append("\nNow in home directory");
            dirstring = "/home";
            //txtArea.append("/home>");
           
           
       }
       else if("cd".equals(firstcommand) && (".".equals(secondcommand)))
       {
           //change the dirstring 
           if ("/home".equals(dirstring))
                   {
                       txtArea.append("\nYou are already in home");
                   }
                       
           else{
                   
           for(int i=dirstring.length()-1;i>=0;i--)
           {
               if(dirstring.charAt(i)=='/') {
                   dirstring=dirstring.substring(0,i);
                   break;
               }
           }txtArea.append("\nyou are here"+dirstring);
       }
       }
       else if ("cd".equals(firstcommand)&& (!("".equals(secondcommand))))
               {
                   Path p1 = Paths.get(dirstring+"/"+secondcommand); 
                    if (Files.notExists(p1)) {
               txtArea.append("\npath doesnot exist");
               
           }
                    else{
                   dirstring=dirstring+"/"+secondcommand;
                   txtArea.append("\nyou are here "+dirstring);
               }
               }
       else if("ls".equals(firstcommand)){ 
           txtArea.append(dirstring);// make provision of second command as well
          
       File folder = new File(dirstring);
        File[] listOfFiles = folder.listFiles();

    for(int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
        System.out.println("File " + listOfFiles[i].getName());
        txtArea.append("\nFile " + listOfFiles[i].getName());
      } else if (listOfFiles[i].isDirectory()) {
        System.out.println("Directory " + listOfFiles[i].getName());
        txtArea.append("\nDirectory " + listOfFiles[i].getName());
      }
    }
       }
       else  if("md".equals(firstcommand)){
       File dir = new File(secondcommand);
    
    // attempt to create the directory here
    boolean successful = dir.mkdir();
    if (successful)
    {
      // creating the directory succeeded
      System.out.println("directory was created successfully");
      txtArea.append("\ndirectory was created successfully");
    }
    else
    {
      // creating the directory failed
      System.out.println("failed trying to create the directory");
      txtArea.append("\nfailed trying to create the directory");
    }
       }
       
       else if("rm".equals(firstcommand))
       {
           try{

    		File file = new File(secondcommand);

    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
                        txtArea.append("\n"+file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
                        txtArea.append("\nDelete operation is failed.");
    		}

    	}catch(Exception e){

    		e.printStackTrace();
       }
       }
      //another command 
       else if("cat".equals(firstcommand))
      {
       String fname;
       fname = secondcommand;
        
        /* this will reference only one line at a time */
        
        String line = null;
        try
        {
            /* FileReader reads text files in the default encoding */
            //FileReader fileReader = new FileReader(fname);
            
            /* always wrap the FileReader in BufferedReader */
            //
            //BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedReader bufferedReader= new BufferedReader(new FileReader(dirstring+"/"+secondcommand));
            txtArea.append("\n");
            while((line = bufferedReader.readLine()) != null)
            {
                System.out.println(line);
                txtArea.append(line+"\n");
            }
            
            /* always close the file after use */
            bufferedReader.close();
        }
        catch(IOException ex)
        {
            System.out.println("Error reading file named '" + fname + "'");
        }
       
   }
   // another command 
       
       else if("touch".equals(firstcommand))
       {
            File file = new File(secondcommand);
             boolean blnCreated = false;
     try
     {
       blnCreated = file.createNewFile();
       
           
     }
     catch(IOException ioe)
     {
       System.out.println("Error while creating a new empty file :" + ioe);
     }
     if(blnCreated==true)
     {
     System.out.println("file " + file.getPath() + " creation  success  ");
      txtArea.append("\nfile " + file.getPath() + " creation  success  ");
     
     }
     else{
        System.out.println("file " + file.getPath() + " creation  failed ");
     txtArea.append("\nfile " + file.getPath() + " creation  failed  ");
       }
       }
       //another command
       else if("run".equals(firstcommand))
       {
            if ("Notepad".equals(secondcommand)){ Notepad.main(null);
            txtArea.append("\nNotepad opened");}
            else  if ("Manager".equals(secondcommand)) {OpenFile.main(null);
                    txtArea.append("\nManager opened");}
            else if ("Calendar".equals(secondcommand)){ calendarprogra.main(null);
                    txtArea.append("\nCalendar opened");}
            else  if ("Browser".equals(secondcommand)){ MiniBrowser.main(null);
                    txtArea.append("\nBrowser opened");}
            else  if ("pagereplacement".equals(secondcommand)) {pagereplacement.main(null);
            txtArea.append("\nPage Algo  opened");}
       }
       //custom commands
       else if("quit".equals(firstcommand))
       {
           System.exit(0);
       }
           
       else if("pwd".equals(firstcommand))
               {
               System.out.println("Working Directory = " +
              System.getProperty("user.dir"));
               txtArea.append("\nWorking Directory = " +
              System.getProperty("user.dir"));
           
               }
       else {
           System.out.println("wrong command");
           txtArea.append("\nwrong command");
          
       }
       
    }

    private String extractCommand() {
        removeLastLineSeparator();
        String newCommand = stripPreviousCommands();
        System.out.println("success" + newCommand);
        return newCommand;
    }

    private void removeLastLineSeparator() {
        String terminalText = txtArea.getText();
        terminalText = terminalText.substring(0, terminalText.length() - 1);
        txtArea.setText(terminalText);
        
    }

    private String stripPreviousCommands() {
        String terminalText = txtArea.getText();
        int lastPromptIndex = terminalText.lastIndexOf('>') + 2;
        if (lastPromptIndex < 0 || lastPromptIndex >= terminalText.length())
            return "";
        else
            return terminalText.substring(lastPromptIndex);
    }

    public static Terminal getInstance() {
        return TerminalHolder.INSTANCE;
    }

    private static final class TerminalHolder {
        static final Terminal INSTANCE = new Terminal();
    }
}

class CommandProcessor {
    private CommandProcessor() {
    }

    public void processCmd(String command) {
        System.out.println("User command: " + command);
    }

    public static CommandProcessor getInstance() {
        return CommandProcessorHolder.INSTANCE;
    }

    private static final class CommandProcessorHolder {
        static final CommandProcessor INSTANCE = new CommandProcessor();
    }
}