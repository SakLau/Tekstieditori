import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;


public class tekstieditori extends JFrame {

	private JPanel contentPane;
	JEditorPane editorPane_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tekstieditori frame = new tekstieditori();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}
	
	private int rivi;
		
		public int setRivi(){
			return rivi;
		}

	
	/**
	 * Create the frame.
	 */
	public tekstieditori() {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnTiedosto = new JMenu("Tiedosto");
		menuBar.add(mnTiedosto);
		
		// Luodaan avaa toiminto
		
		JMenuItem mntmAvaa = new JMenuItem("Avaa");
		mntmAvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				
				JFileChooser valintaikkuna = new JFileChooser();				
				valintaikkuna.setApproveButtonText("Avaa tiedosto");
				valintaikkuna.setDialogTitle("Tiedoston valinta");
				valintaikkuna.showOpenDialog(null);
				String rivi = "";
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();

			try {
				Scanner lukija = null;
				File tiedosto = new File(uusiTiedosto);
				lukija = new Scanner(tiedosto);
				
				while (lukija.hasNextLine()) {
				rivi += lukija.nextLine()+"\n";
//					System.out.println(rivi);
				}
				
			} catch (FileNotFoundException p) {
				System.out.println("Tiedostoa ei löydy");
			}
					
			editorPane_1.setText(rivi);
			}
			
		// Kaikki ikonien kuvat ovat mukana repossa, mutta en saanut niitä näkymään muuta kuin laittamalla ne samaan
		// kansioon / hakemistoon kuin itse tekstieditori.
			
		});
		mntmAvaa.setIcon(new ImageIcon("C:\\Users\\Sakke\\eclipse-workspace\\Projekti\\src\\open.png"));
		mntmAvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmAvaa);
		
		// Luodaan tallenna toiminto
		
		JMenuItem mntmTallenna = new JMenuItem("Tallenna");
		mntmTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser valintaikkuna = new JFileChooser();
				
				valintaikkuna.setApproveButtonText("Tallenna");
				valintaikkuna.setDialogTitle("Tiedoston valinta");
				valintaikkuna.showSaveDialog(null);
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				System.out.println("Kirjoitettava tiedosto: " + uusiTiedosto);
						
			try {
			
				PrintWriter writer = new PrintWriter(uusiTiedosto);
				String sisalto = editorPane_1.getText();
				System.out.println(sisalto);
				
				writer.println( sisalto );
				writer.flush();
				writer.close();
			
			} catch (Exception e1) {
				System.out.println("Tiedoston tallennuksessa tapahtui virhe!");
				e1.printStackTrace();
			}
				
			}
		});
		mnTiedosto.add(mntmTallenna);
			
		JMenuItem mntmLopeta = new JMenuItem("Lopeta");
		mnTiedosto.add(mntmLopeta);
		
		JMenuItem mntmSulje = new JMenuItem("Sulje");
		mnTiedosto.add(mntmSulje);
		
		JMenu mnMuokkaa = new JMenu("Muokkaa");
		menuBar.add(mnMuokkaa);
		
		// Luodaan etsi toiminto, jolla haetaan sanaa auto
		
		JMenuItem mntmEtsi = new JMenuItem("Etsi");
		mntmEtsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
								
				String sisalto = editorPane_1.getText();
				sisalto = sisalto.toLowerCase();
				
				String haettava = "auto";
				int indeksi = sisalto.indexOf(haettava);
								
				editorPane_1.setSelectionColor(Color.YELLOW);
				
				editorPane_1.setSelectionStart(indeksi);
				editorPane_1.setSelectionEnd(indeksi + haettava.length());
				System.out.println("Indeksi: " + indeksi);
				
			}
		});
		mnMuokkaa.add(mntmEtsi);
		
		JMenuItem mntmKorvaa = new JMenuItem("Korvaa");
		mnMuokkaa.add(mntmKorvaa);
		
		JMenu mnTietoja = new JMenu("Tietoja");
		menuBar.add(mnTietoja);
		
		JMenuItem mntmTietojaOhjelmasta = new JMenuItem("Tietoja ohjelmasta");
		mnTietoja.add(mntmTietojaOhjelmasta);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("C:\\Users\\Sakke\\eclipse-workspace\\Projekti\\src\\New_file.png"));
		toolBar.add(button);
		
		// Tämä on tallenna-nappi, joka on luotu ikonista.
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser valintaikkuna = new JFileChooser();
				
				valintaikkuna.setApproveButtonText("Tallenna");
				valintaikkuna.setDialogTitle("Tiedoston valinta");
				valintaikkuna.showSaveDialog(null);
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				System.out.println("Kirjoitettava tiedosto: " + uusiTiedosto);
				
			try {
			
				PrintWriter writer = new PrintWriter(uusiTiedosto);
				
				String sisalto = editorPane_1.getText();
				System.out.println(sisalto);
				
				writer.println( sisalto );
				writer.flush();
				writer.close();
			
			} catch (Exception e1) {
				System.out.println("Tiedoston tallennuksessa tapahtui virhe!");
				e1.printStackTrace();
			}
				
			}
		});
		button_1.setIcon(new ImageIcon("C:\\Users\\Sakke\\eclipse-workspace\\Projekti\\src\\Save.png"));
		toolBar.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon("C:\\Users\\Sakke\\eclipse-workspace\\Projekti\\src\\cut.png"));
		toolBar.add(button_2);
		
		editorPane_1 = new JEditorPane();
		contentPane.add(editorPane_1, BorderLayout.CENTER);
	}
	
}
