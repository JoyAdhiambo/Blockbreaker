 package joy;


	import javax.swing.JFrame;
import javax.swing.JOptionPane;
	public class Main {
		
		
		
	 public static void main (String[] args){
		
		//window pane 
	 JFrame frame = new JFrame("BlockBreaker");
	 
	 BlockBreakPanel panel = new BlockBreakPanel();
	 
	 frame.getContentPane().add(panel);
	 
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 frame.setVisible(true);
	 frame.setSize(490,900);
	 frame.setResizable(false);
	 frame.setLocation(400,200);
	 frame.add(panel);
	 frame.setBounds(10,10,700,600);
	 
	 }
	 
	}




