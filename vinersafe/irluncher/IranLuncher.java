package vinersafe.irluncher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class IranLuncher {
	private static int ram =2;
	private static String version;
	private static String mFolder = System.getenv("APPDATA") + "\\.minecraft";
    public static void main(String[] args) {
    	Run myRun = new Run();
    	myRun.Music();
        // Create a new JFrame (window)
        JFrame frame = new JFrame("Iran Luncher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        ////////////////////background//////////////////////////////////////
        
        
        // Create a text area for displaying output
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        // Create a scroll pane and add the text area to it
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(370, 100));
        frame.add(scrollPane);
        textArea.setText("\n\n\n\t             Made by: vinerSAFE");
        
        JLabel usernameLabel = new JLabel("Username:");
        frame.add(usernameLabel);
        
        // Create a text field for user input
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 20));
        frame.add(textField);

        // Create a button to generate UUID
        JButton button = new JButton("Enter");
        frame.add(button);

        // Set layout manager and alignment
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

        // Add ActionListener to the butto
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String playerName = textField.getText();
            	textArea.setText("");
            	
            	boolean run_o_game = true;
            	//check version of JAVA
            	int javaVersion = Integer.parseInt(System.getProperty("java.version").substring(0,2));
            	if((version=="1.17.1"||version=="1.18.2"||version=="1.19.4"||version=="1.20.1"||version=="1.20.4")&&javaVersion<17) {
            		textArea.append("ERROR: Java version 17 or higher is required! current version: "+javaVersion+'\n');
            		run_o_game = false;
            	}else if((version=="1.20.6"||version=="1.21.3")&&javaVersion<21) {
            		textArea.append("ERROR: Java version 21 or higher is required! current version: "+javaVersion+'\n');
            		run_o_game = false;
            	}
            	//check null userName
            	if (button.getText().equals("Enter")&&playerName.isEmpty()) {
            		textArea.append("ERROR: User name cant be empty\n");
            		run_o_game = false;
            	}
            	//check null GameVersion
            	if (button.getText().equals("Enter")&& version==null) {
            		textArea.append("ERROR: No version found");
            		run_o_game = false;
            	}
            	if (button.getText().equals("Enter")&&run_o_game) {
            	textArea.setText("");
            	//String javaVersion = System.getProperty("java.version");
                //String os = System.getProperty("os.name");
                String uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + playerName).getBytes()).toString();
                //String uuid0 = uuid.replaceAll("-", "");
                String currentFolder = System.getProperty("user.dir");
                
                String javaPath = System.getProperty("java.home") + File.separator + "bin" + File.separator + "javaw.exe";
                textArea.append("User: " + playerName + "\nOperating System: "+ System.getProperty("os.name") + "\nUUID: "+ uuid + "\nMinecraft Folder: "+ mFolder +
                "\nLuncher Folder: "+ currentFolder +"\nJava Folder: "+ javaPath +"\nJava version: "+System.getProperty("java.version")+
                "\nMinecraft version: "+ version +"\nSelected RAM: "+ ram +" GB");
                
                myRun.Game(version,mFolder,ram,playerName,uuid.replaceAll("-", ""),currentFolder,javaPath);
                button.setText("Exit");
                	}else if (button.getText().equals("Exit")) {
                		frame.dispose();
                	}
        }
        });
////////////////////////////////////////Create a panel for the RAM selection components///////////////////////////////
        JPanel panel = new JPanel();
        frame.add(panel);
        
        // Create the toggle button
        JToggleButton toggleButton = new JToggleButton("RAM: 2 GB");
        panel.add(toggleButton);
        
        // Create the popup menu
        JPopupMenu popupMenu = new JPopupMenu();
        ButtonGroup buttonGroup = new ButtonGroup();

        // Create the selectable options
        for (int i = 1; i <= 4; i++) {
            JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(i+" GB");
            popupMenu.add(menuItem);
            buttonGroup.add(menuItem);

            if (i == 2) {
                menuItem.setSelected(true); // Set the second option as default
            }

            // Attach an action listener to each menu item
            menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    AbstractButton selectedButton = (AbstractButton) e.getSource();
                    toggleButton.setText("RAM: "+selectedButton.getText()); // Update the label of the toggle button with the selected option
                    String selectedRam = ((AbstractButton) e.getSource()).getText().split(" ")[0];
                    ram = Integer.parseInt(selectedRam);
                    toggleButton.setSelected(false);
                }
            });
        }

        // Add action listener to handle toggle button click
        toggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JToggleButton button = (JToggleButton) e.getSource();
                if (button.isSelected()) {
                    popupMenu.show(button, button.getWidth()/5, button.getHeight());
                    toggleButton.setSelected(false);
                }
                    //popupMenu.setVisible(false);
            }
        });
        
//////////////////////////////////////////////version///////////////////////////////////////////////////////////////
      

        // Create the toggle button
        JToggleButton toggleButtonw = new JToggleButton("Not selected");
        panel.add(toggleButtonw);
        JPopupMenu popupMenuw = new JPopupMenu();
        ButtonGroup buttonGroupw = new ButtonGroup();
        List<String> versionList = Arrays.asList("1.14.4","1.15.2","1.16.5","1.17.1","1.18.2","1.19.4","1.20.1","1.20.4","1.20.6","1.21.3");
        boolean select = true;
        if(new File(System.getProperty("user.dir")+"\\assets").isDirectory()) {
        	mFolder = System.getProperty("user.dir").toString();
        }
        for(int i=versionList.size()-1;i >=0 ;i--) {
        	String versionNow = versionList.get(i);
        	boolean versiont = new File(mFolder+"\\versions\\"+versionNow+"\\"+versionNow+".jar").exists();
        	
        	if(versiont) {
        		JRadioButtonMenuItem items = new JRadioButtonMenuItem(versionNow);
        		popupMenuw.add(items);
        		buttonGroupw.add(items);
        		if(select) {
        			items.setSelected(true);
        			toggleButtonw.setText("version: "+versionNow);
        			version = versionNow;
        			select=false;
        		}
        		// Attach an action listener to each menu item
        		items.addActionListener(new ActionListener() {
        		@Override
                public void actionPerformed(ActionEvent e) {
                    AbstractButton selectedButtonw = (AbstractButton) e.getSource();
                    toggleButtonw.setText("version: "+(selectedButtonw.getText()));
                    button.setText("Enter");
                    version = selectedButtonw.getText();
                    toggleButtonw.setSelected(false);
                }
            });
        	}
        }
        
       // Add action listener to handle toggle button click
        toggleButtonw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JToggleButton button = (JToggleButton) e.getSource();
                if (button.isSelected()) {
                    popupMenuw.show(button,button.getWidth()/5, button.getHeight());
                    toggleButtonw.setSelected(false);
                }
                    //popupMenuw.setVisible(false);
            }
        });
            
/////////////////////////////////////////////volume/////////////////////////////////////////////////////////////////
        JPanel panelw = new JPanel();
        frame.add(panelw);
        ImageIcon Vicon = new ImageIcon(IranLuncher.class.getResource("/data/000001"));
        JLabel volumeLabel = new JLabel(Vicon);
        
        volumeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelw.add(volumeLabel, BorderLayout.PAGE_START);

        // Create a slider to control the volume
        JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 30);
        volumeSlider.setMajorTickSpacing(10);
        volumeSlider.setPaintTicks(true);
        panelw.add(volumeSlider, BorderLayout.CENTER);

        // Add a change listener to the slider
        volumeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider slider = (JSlider) e.getSource();
                float a = slider.getValue()/100f;
                myRun.Volume(a);
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}