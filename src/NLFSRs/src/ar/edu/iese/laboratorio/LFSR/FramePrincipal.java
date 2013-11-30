package ar.edu.iese.laboratorio.LFSR;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import com.fasteasytrade.JRandTest.IO.FileRandomStream;
import com.fasteasytrade.JRandTest.IO.JTextareaOutputDestination;
import com.fasteasytrade.JRandTest.Tests.Base;

/**
 * Frame, usado en este caso como ventana, principal del programa.
 */
public class FramePrincipal extends JFrame {

	private static final long serialVersionUID = -5091655130559471461L;
	private static JFrame _frame = null;
	private JPanel contentPane;

	protected JButton btnIniciar;
	private JTextField txtTamanoSalida;
	private JTextField txtDescarteInicial;
	private JTextArea txtEstadoInicialA;
	private JTextArea txtEstadoInicialB;
	private JTextArea txtEstadoInicialC;
	private JTextField txtTamanoRegistroA;
	private JTextField txtTamanoRegistroB;
	private JTextField txtTamanoRegistroC;
	private JTextField txtFormaRetroA;
	private JTextField txtFormaRetroB;
	private JTextField txtFormaRetroC;
	private JTextField txtGeneracionKey;
	private JTextArea txtSalida;
	private JTextField txtSalidaArchivo;
	private JTextField txtTest3;
	private JTextField txtTest4;
	private JTextField txtTest5;
	private JTextField txtTestMaurerL;
	private JTextField txtTestMaurerQ;
	private JTextField txtTestMaurerK;
	private JTextField txtImg;
	private JTextField txtMuestra;
	private JTextArea txtSalidaJRT;
	private JComboBox comboJRT;
	private static String BITSTREAM = "";
	private boolean BITSTREAM_DIRTY = true;
	private JTextField txtHistorial;
	private JTextField txtArchivoHistorial;
	private JTextField txtXHistorial;

	private JComboBox comboAlgo;

	private JLabel lblDescarteIncial;
	private JLabel lblTamanoRegistroA;
	private JLabel lblFormaRetroA;
	private JLabel lblEstadoInicialA;
	private JLabel lblTamanoRegistroB;
	private JLabel lblFormaRetroB;
	private JLabel lblEstadoInicialB;
	private JLabel lblTamanoRegistroC;
	private JLabel lblFormaRetroC;
	private JLabel lblEstadoInicialC;
	private JLabel lblGeneracionKey;
	private JScrollPane scrollPaneEstadoIncialC;
	private JSeparator separator_4;

	/**
	 * Punto de entrada (main) al programa.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						for (LookAndFeelInfo info : UIManager
								.getInstalledLookAndFeels()) {
							if ("Nimbus".equals(info.getName())) {
								UIManager.setLookAndFeel(info.getClassName());
								break;
							}
						}
					} catch (Exception e) {
					}
					FramePrincipal frame = new FramePrincipal();
					frame.setVisible(true);
					_frame = frame;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor del frame principal.
	 */
	public FramePrincipal() {

		setTitle("NLFSRs - Laboratorio de Criptolog\u00EDa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1020, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblAlgo = new JLabel(
				"<html><b>Selecci�n&nbsp;de&nbsp;Algoritmo</b></html>");
		GridBagConstraints gbc_lblAlgo = new GridBagConstraints();
		gbc_lblAlgo.anchor = GridBagConstraints.WEST;
		gbc_lblAlgo.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlgo.gridx = 0;
		gbc_lblAlgo.gridy = 0;
		contentPane.add(lblAlgo, gbc_lblAlgo);

		comboAlgo = new JComboBox();
		comboAlgo.addItem("Trivium");
		comboAlgo.addItem("Trivium Toy");
		comboAlgo.addItem("Grain-128");
		comboAlgo.addItem("Mickey-v2-128");
		GridBagConstraints gbc_comboAlgo = new GridBagConstraints();
		gbc_comboAlgo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboAlgo.insets = new Insets(0, 0, 5, 5);
		gbc_comboAlgo.gridx = 1;
		gbc_comboAlgo.gridy = 0;
		contentPane.add(comboAlgo, gbc_comboAlgo);

		JLabel lblTamanoSalida = new JLabel(
				"<html>Tama\u00F1o de salida (<i>N</i> bits)<br/><i> (incluyendo inicializaci\u00F3n)</i></html>");
		GridBagConstraints gbc_lblTamanoSalida = new GridBagConstraints();
		gbc_lblTamanoSalida.anchor = GridBagConstraints.EAST;
		gbc_lblTamanoSalida.insets = new Insets(0, 0, 5, 5);
		gbc_lblTamanoSalida.gridx = 2;
		gbc_lblTamanoSalida.gridy = 0;
		contentPane.add(lblTamanoSalida, gbc_lblTamanoSalida);

		txtTamanoSalida = new JTextField();
		txtTamanoSalida.setText("25000");
		GridBagConstraints gbc_txtTamanoSalida = new GridBagConstraints();
		gbc_txtTamanoSalida.insets = new Insets(0, 0, 5, 5);
		gbc_txtTamanoSalida.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTamanoSalida.gridx = 3;
		gbc_txtTamanoSalida.gridy = 0;
		contentPane.add(txtTamanoSalida, gbc_txtTamanoSalida);
		txtTamanoSalida.setColumns(10);

		btnIniciar = new JButton("GENERAR");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnIniciar.setEnabled(false);
				try {
					if (txtSalidaArchivo.getText().equals("")) {
						JOptionPane.showMessageDialog(_frame,
								"Debe especificarse el archivo.", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					BITSTREAM = NLFSR.getBitsStrNLFSRs(
							Integer.parseInt(txtTamanoRegistroA.getText()),
							Integer.parseInt(txtTamanoRegistroB.getText()),
							Integer.parseInt(txtTamanoRegistroC.getText()),
							txtFormaRetroA.getText(), txtFormaRetroB.getText(),
							txtFormaRetroC.getText(),
							txtGeneracionKey.getText(),
							txtEstadoInicialA.getText(),
							txtEstadoInicialB.getText(),
							txtEstadoInicialC.getText(),
							Integer.parseInt(txtTamanoSalida.getText()),
							Integer.parseInt(txtDescarteInicial.getText()),
							comboAlgo.getSelectedIndex()).substring(
							Integer.parseInt(txtDescarteInicial.getText()));
					try {
						PrintWriter out;
						out = new PrintWriter(txtSalidaArchivo.getText());
						out.println(BITSTREAM);
						out.close();
						JOptionPane.showMessageDialog(_frame,
								"Archivo generado exitosamente: "
										+ txtSalidaArchivo.getText(),
								"Salida a archivo", JOptionPane.PLAIN_MESSAGE);
					} catch (Exception fe) {
						throw new RuntimeException("error al escribir archivo "
								+ txtSalidaArchivo.getText() + ": "
								+ fe.getMessage());
					}
					txtSalida.setText(BITSTREAM.substring(0,
							Integer.parseInt(txtMuestra.getText())));
					BITSTREAM_DIRTY = false;

				} catch (RuntimeException e) {
					txtSalida.setText("");
					JOptionPane.showMessageDialog(_frame,
							"ERROR: " + e.getMessage() + ": " + e, "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				btnIniciar.setEnabled(true);
			}
		});

		lblDescarteIncial = new JLabel();
		GridBagConstraints gbc_lblDescarteIncial = new GridBagConstraints();
		gbc_lblDescarteIncial.anchor = GridBagConstraints.EAST;
		gbc_lblDescarteIncial.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescarteIncial.gridx = 3;
		gbc_lblDescarteIncial.gridy = 11;
		contentPane.add(lblDescarteIncial, gbc_lblDescarteIncial);

		txtDescarteInicial = new JTextField();
		GridBagConstraints gbc_txtDescarteInicial = new GridBagConstraints();
		gbc_txtDescarteInicial.insets = new Insets(0, 0, 5, 5);
		gbc_txtDescarteInicial.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDescarteInicial.gridx = 4;
		gbc_txtDescarteInicial.gridy = 11;
		contentPane.add(txtDescarteInicial, gbc_txtDescarteInicial);
		txtDescarteInicial.setColumns(10);
		GridBagConstraints gbc_btnIniciar = new GridBagConstraints();
		gbc_btnIniciar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnIniciar.insets = new Insets(0, 0, 5, 0);
		gbc_btnIniciar.gridx = 4;
		gbc_btnIniciar.gridy = 0;
		contentPane.add(btnIniciar, gbc_btnIniciar);

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.gridwidth = 5;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 1;
		contentPane.add(separator_1, gbc_separator_1);

		lblTamanoRegistroA = new JLabel();
		GridBagConstraints gbc_lblTamanoRegistroA = new GridBagConstraints();
		gbc_lblTamanoRegistroA.anchor = GridBagConstraints.WEST;
		gbc_lblTamanoRegistroA.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTamanoRegistroA.insets = new Insets(0, 0, 5, 5);
		gbc_lblTamanoRegistroA.gridx = 0;
		gbc_lblTamanoRegistroA.gridy = 2;
		contentPane.add(lblTamanoRegistroA, gbc_lblTamanoRegistroA);

		txtTamanoRegistroA = new JTextField();
		GridBagConstraints gbc_txtTamanoRegistroA = new GridBagConstraints();
		gbc_txtTamanoRegistroA.insets = new Insets(0, 0, 5, 5);
		gbc_txtTamanoRegistroA.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTamanoRegistroA.gridx = 1;
		gbc_txtTamanoRegistroA.gridy = 2;
		contentPane.add(txtTamanoRegistroA, gbc_txtTamanoRegistroA);
		txtTamanoRegistroA.setColumns(10);

		lblFormaRetroA = new JLabel();
		GridBagConstraints gbc_lblFormaRetroA = new GridBagConstraints();
		gbc_lblFormaRetroA.anchor = GridBagConstraints.EAST;
		gbc_lblFormaRetroA.insets = new Insets(0, 0, 5, 5);
		gbc_lblFormaRetroA.gridx = 2;
		gbc_lblFormaRetroA.gridy = 2;
		contentPane.add(lblFormaRetroA, gbc_lblFormaRetroA);

		txtFormaRetroA = new JTextField();
		GridBagConstraints gbc_txtFormaRetroA = new GridBagConstraints();
		gbc_txtFormaRetroA.gridwidth = 2;
		gbc_txtFormaRetroA.insets = new Insets(0, 0, 5, 0);
		gbc_txtFormaRetroA.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFormaRetroA.gridx = 3;
		gbc_txtFormaRetroA.gridy = 2;
		contentPane.add(txtFormaRetroA, gbc_txtFormaRetroA);
		txtFormaRetroA.setColumns(10);

		lblEstadoInicialA = new JLabel();
		GridBagConstraints gbc_lblEstadoInicialA = new GridBagConstraints();
		gbc_lblEstadoInicialA.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEstadoInicialA.anchor = GridBagConstraints.WEST;
		gbc_lblEstadoInicialA.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstadoInicialA.gridx = 0;
		gbc_lblEstadoInicialA.gridy = 3;
		contentPane.add(lblEstadoInicialA, gbc_lblEstadoInicialA);

		JScrollPane scrollPaneEstadoInicialA = new JScrollPane();
		GridBagConstraints gbc_scrollPaneEstadoInicialA = new GridBagConstraints();
		gbc_scrollPaneEstadoInicialA.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneEstadoInicialA.gridwidth = 4;
		gbc_scrollPaneEstadoInicialA.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneEstadoInicialA.gridx = 1;
		gbc_scrollPaneEstadoInicialA.gridy = 3;
		contentPane.add(scrollPaneEstadoInicialA, gbc_scrollPaneEstadoInicialA);

		txtEstadoInicialA = new JTextArea();
		txtEstadoInicialA.setLineWrap(true);
		scrollPaneEstadoInicialA.setViewportView(txtEstadoInicialA);

		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.gridwidth = 5;
		gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_2.insets = new Insets(0, 0, 5, 0);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 4;
		contentPane.add(separator_2, gbc_separator_2);

		lblTamanoRegistroB = new JLabel();
		GridBagConstraints gbc_lblTamanoRegistroB = new GridBagConstraints();
		gbc_lblTamanoRegistroB.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTamanoRegistroB.anchor = GridBagConstraints.WEST;
		gbc_lblTamanoRegistroB.insets = new Insets(0, 0, 5, 5);
		gbc_lblTamanoRegistroB.gridx = 0;
		gbc_lblTamanoRegistroB.gridy = 5;
		contentPane.add(lblTamanoRegistroB, gbc_lblTamanoRegistroB);

		txtTamanoRegistroB = new JTextField();
		GridBagConstraints gbc_txtTamanoRegistroB = new GridBagConstraints();
		gbc_txtTamanoRegistroB.insets = new Insets(0, 0, 5, 5);
		gbc_txtTamanoRegistroB.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTamanoRegistroB.gridx = 1;
		gbc_txtTamanoRegistroB.gridy = 5;
		contentPane.add(txtTamanoRegistroB, gbc_txtTamanoRegistroB);
		txtTamanoRegistroB.setColumns(10);

		lblFormaRetroB = new JLabel();
		GridBagConstraints gbc_lblFormaRetroB = new GridBagConstraints();
		gbc_lblFormaRetroB.anchor = GridBagConstraints.EAST;
		gbc_lblFormaRetroB.insets = new Insets(0, 0, 5, 5);
		gbc_lblFormaRetroB.gridx = 2;
		gbc_lblFormaRetroB.gridy = 5;
		contentPane.add(lblFormaRetroB, gbc_lblFormaRetroB);

		txtFormaRetroB = new JTextField();
		GridBagConstraints gbc_txtFormaRetroB = new GridBagConstraints();
		gbc_txtFormaRetroB.gridwidth = 2;
		gbc_txtFormaRetroB.insets = new Insets(0, 0, 5, 0);
		gbc_txtFormaRetroB.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFormaRetroB.gridx = 3;
		gbc_txtFormaRetroB.gridy = 5;
		contentPane.add(txtFormaRetroB, gbc_txtFormaRetroB);
		txtFormaRetroB.setColumns(10);

		lblEstadoInicialB = new JLabel();
		GridBagConstraints gbc_lblEstadoIncialB = new GridBagConstraints();
		gbc_lblEstadoIncialB.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEstadoIncialB.anchor = GridBagConstraints.WEST;
		gbc_lblEstadoIncialB.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstadoIncialB.gridx = 0;
		gbc_lblEstadoIncialB.gridy = 6;
		contentPane.add(lblEstadoInicialB, gbc_lblEstadoIncialB);

		JScrollPane scrollPaneEstadoIncialB = new JScrollPane();
		GridBagConstraints gbc_scrollPaneEstadoIncialB = new GridBagConstraints();
		gbc_scrollPaneEstadoIncialB.gridwidth = 4;
		gbc_scrollPaneEstadoIncialB.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneEstadoIncialB.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneEstadoIncialB.gridx = 1;
		gbc_scrollPaneEstadoIncialB.gridy = 6;
		contentPane.add(scrollPaneEstadoIncialB, gbc_scrollPaneEstadoIncialB);

		txtEstadoInicialB = new JTextArea();
		txtEstadoInicialB.setLineWrap(true);
		scrollPaneEstadoIncialB.setViewportView(txtEstadoInicialB);

		JSeparator separator_3 = new JSeparator();
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.gridwidth = 5;
		gbc_separator_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_3.insets = new Insets(0, 0, 5, 0);
		gbc_separator_3.gridx = 0;
		gbc_separator_3.gridy = 7;
		contentPane.add(separator_3, gbc_separator_3);

		lblTamanoRegistroC = new JLabel();
		GridBagConstraints gbc_lblTamanoRegistroC = new GridBagConstraints();
		gbc_lblTamanoRegistroC.anchor = GridBagConstraints.WEST;
		gbc_lblTamanoRegistroC.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTamanoRegistroC.insets = new Insets(0, 0, 5, 5);
		gbc_lblTamanoRegistroC.gridx = 0;
		gbc_lblTamanoRegistroC.gridy = 8;
		contentPane.add(lblTamanoRegistroC, gbc_lblTamanoRegistroC);

		txtTamanoRegistroC = new JTextField();
		GridBagConstraints gbc_txtTamanoRegistroC = new GridBagConstraints();
		gbc_txtTamanoRegistroC.insets = new Insets(0, 0, 5, 5);
		gbc_txtTamanoRegistroC.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTamanoRegistroC.gridx = 1;
		gbc_txtTamanoRegistroC.gridy = 8;
		contentPane.add(txtTamanoRegistroC, gbc_txtTamanoRegistroC);
		txtTamanoRegistroC.setColumns(10);

		lblFormaRetroC = new JLabel();
		GridBagConstraints gbc_lblFormaRetroC = new GridBagConstraints();
		gbc_lblFormaRetroC.anchor = GridBagConstraints.EAST;
		gbc_lblFormaRetroC.insets = new Insets(0, 0, 5, 5);
		gbc_lblFormaRetroC.gridx = 2;
		gbc_lblFormaRetroC.gridy = 8;
		contentPane.add(lblFormaRetroC, gbc_lblFormaRetroC);

		txtFormaRetroC = new JTextField();
		GridBagConstraints gbc_txtFormaRetroC = new GridBagConstraints();
		gbc_txtFormaRetroC.gridwidth = 2;
		gbc_txtFormaRetroC.insets = new Insets(0, 0, 5, 0);
		gbc_txtFormaRetroC.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFormaRetroC.gridx = 3;
		gbc_txtFormaRetroC.gridy = 8;
		contentPane.add(txtFormaRetroC, gbc_txtFormaRetroC);
		txtFormaRetroC.setColumns(10);

		lblEstadoInicialC = new JLabel();
		GridBagConstraints gbc_lblEstadoInicialC = new GridBagConstraints();
		gbc_lblEstadoInicialC.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEstadoInicialC.anchor = GridBagConstraints.WEST;
		gbc_lblEstadoInicialC.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstadoInicialC.gridx = 0;
		gbc_lblEstadoInicialC.gridy = 9;
		contentPane.add(lblEstadoInicialC, gbc_lblEstadoInicialC);

		scrollPaneEstadoIncialC = new JScrollPane();
		GridBagConstraints gbc_scrollPaneEstadoIncialC = new GridBagConstraints();
		gbc_scrollPaneEstadoIncialC.gridwidth = 4;
		gbc_scrollPaneEstadoIncialC.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneEstadoIncialC.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneEstadoIncialC.gridx = 1;
		gbc_scrollPaneEstadoIncialC.gridy = 9;
		contentPane.add(scrollPaneEstadoIncialC, gbc_scrollPaneEstadoIncialC);

		txtEstadoInicialC = new JTextArea();
		txtEstadoInicialC.setLineWrap(true);
		scrollPaneEstadoIncialC.setViewportView(txtEstadoInicialC);

		separator_4 = new JSeparator();
		GridBagConstraints gbc_separator_4 = new GridBagConstraints();
		gbc_separator_4.gridwidth = 5;
		gbc_separator_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_4.insets = new Insets(0, 0, 5, 0);
		gbc_separator_4.gridx = 0;
		gbc_separator_4.gridy = 10;
		contentPane.add(separator_4, gbc_separator_4);

		lblGeneracionKey = new JLabel();
		GridBagConstraints gbc_lblGeneracionKey = new GridBagConstraints();
		gbc_lblGeneracionKey.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblGeneracionKey.anchor = GridBagConstraints.WEST;
		gbc_lblGeneracionKey.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeneracionKey.gridx = 0;
		gbc_lblGeneracionKey.gridy = 11;
		contentPane.add(lblGeneracionKey, gbc_lblGeneracionKey);

		txtGeneracionKey = new JTextField();
		GridBagConstraints gbc_txtGeneracionKey = new GridBagConstraints();
		gbc_txtGeneracionKey.gridwidth = 2;
		gbc_txtGeneracionKey.insets = new Insets(0, 0, 5, 0);
		gbc_txtGeneracionKey.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtGeneracionKey.gridx = 1;
		gbc_txtGeneracionKey.gridy = 11;
		contentPane.add(txtGeneracionKey, gbc_txtGeneracionKey);
		txtGeneracionKey.setColumns(10);

		JSeparator separator_5 = new JSeparator();
		GridBagConstraints gbc_separator_5 = new GridBagConstraints();
		gbc_separator_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_5.gridwidth = 5;
		gbc_separator_5.insets = new Insets(0, 0, 5, 0);
		gbc_separator_5.gridx = 0;
		gbc_separator_5.gridy = 12;
		contentPane.add(separator_5, gbc_separator_5);

		JLabel lblSalida = new JLabel("Cantidad de bits de muestra");
		GridBagConstraints gbc_lblSalida = new GridBagConstraints();
		gbc_lblSalida.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSalida.anchor = GridBagConstraints.WEST;
		gbc_lblSalida.insets = new Insets(0, 0, 5, 5);
		gbc_lblSalida.gridx = 0;
		gbc_lblSalida.gridy = 14;
		contentPane.add(lblSalida, gbc_lblSalida);

		txtMuestra = new JTextField();
		txtMuestra.setText("10000");
		GridBagConstraints gbc_txtMuestra = new GridBagConstraints();
		gbc_txtMuestra.insets = new Insets(0, 0, 5, 5);
		gbc_txtMuestra.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMuestra.gridx = 1;
		gbc_txtMuestra.gridy = 14;
		contentPane.add(txtMuestra, gbc_txtMuestra);
		txtMuestra.setColumns(10);

		JLabel lblSalidaArchivo = new JLabel(
				"Escribir salida completa a archivo");
		GridBagConstraints gbc_lblSalidaArchivo = new GridBagConstraints();
		gbc_lblSalidaArchivo.anchor = GridBagConstraints.EAST;
		gbc_lblSalidaArchivo.insets = new Insets(0, 0, 5, 5);
		gbc_lblSalidaArchivo.gridx = 2;
		gbc_lblSalidaArchivo.gridy = 14;
		contentPane.add(lblSalidaArchivo, gbc_lblSalidaArchivo);

		txtSalidaArchivo = new JTextField();
		txtSalidaArchivo.setText("nlfsrs.txt");
		GridBagConstraints gbc_txtSalidaArchivo = new GridBagConstraints();
		gbc_txtSalidaArchivo.insets = new Insets(0, 0, 5, 5);
		gbc_txtSalidaArchivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSalidaArchivo.gridx = 3;
		gbc_txtSalidaArchivo.gridy = 14;
		contentPane.add(txtSalidaArchivo, gbc_txtSalidaArchivo);
		txtSalidaArchivo.setColumns(10);

		JButton btnSalidaArchivo = new JButton("...");
		btnSalidaArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				int tmp = fc.showSaveDialog(_frame);
				if (tmp == JFileChooser.APPROVE_OPTION) {
					txtSalidaArchivo.setText(fc.getCurrentDirectory()
							.toString()
							+ File.separator
							+ fc.getSelectedFile().getName());
					BITSTREAM_DIRTY = true;
					// txtSalida.setEnabled(false);
				}
				if (tmp == JFileChooser.CANCEL_OPTION) {
					// txtSalidaArchivo.setText("");
					// txtSalida.setEnabled(true);
				}
			}
		});
		GridBagConstraints gbc_btnSalidaArchivo = new GridBagConstraints();
		gbc_btnSalidaArchivo.anchor = GridBagConstraints.WEST;
		gbc_btnSalidaArchivo.insets = new Insets(0, 0, 5, 0);
		gbc_btnSalidaArchivo.gridx = 4;
		gbc_btnSalidaArchivo.gridy = 14;
		contentPane.add(btnSalidaArchivo, gbc_btnSalidaArchivo);

		JScrollPane scrollPaneSalida = new JScrollPane();
		GridBagConstraints gbc_scrollPaneSalida = new GridBagConstraints();
		gbc_scrollPaneSalida.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneSalida.gridwidth = 5;
		gbc_scrollPaneSalida.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneSalida.gridx = 0;
		gbc_scrollPaneSalida.gridy = 15;
		contentPane.add(scrollPaneSalida, gbc_scrollPaneSalida);

		txtSalida = new JTextArea();
		txtSalida.setLineWrap(true);
		scrollPaneSalida.setViewportView(txtSalida);

		JButton btnImg = new JButton("Prueba de Imagen");
		btnImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtSalidaArchivo.getText().equals("")) {
					JOptionPane.showMessageDialog(_frame,
							"Debe especificarse el archivo.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					if (BITSTREAM_DIRTY) {
						BITSTREAM = new Scanner(new File(txtSalidaArchivo
								.getText())).useDelimiter("\\Z").next();
						BITSTREAM_DIRTY = false;
					}
					String salida = BITSTREAM;
					int salidaBitLen = salida.length();
					if (salidaBitLen == 0)
						throw new Exception(
								"Se debe generar salida o key bit stream previamente.");
					int salidaBitIdx = 0;
					URL url = new URL(txtImg.getText());
					BufferedImage bufferedImage = ImageIO.read(url);
					int width = bufferedImage.getWidth();
					int height = bufferedImage.getHeight();
					BufferedImage bufferedImage2 = new BufferedImage(width,
							height, BufferedImage.TYPE_INT_RGB);
					BufferedImage bufferedImage3 = new BufferedImage(width,
							height, BufferedImage.TYPE_INT_RGB);
					BufferedImage bufferedImage4 = new BufferedImage(width,
							height, BufferedImage.TYPE_INT_RGB);
					for (int i = 0; i < width; i++) {
						for (int j = 0; j < height; j++) {
							int rgb = bufferedImage.getRGB(i, j);
							rgb = rgb & 0xffffff;
							bufferedImage2.setRGB(i, j, rgb);
							int rgb2;
							if (salidaBitIdx + 24 >= salidaBitLen) {
								rgb = 0;
								rgb2 = 0;
							} else {
								int aXorear = 0;
								for (int k = 0; k < 24; k++) {
									if (salida.charAt(salidaBitIdx + k) == '1') {
										aXorear += (int) Math.pow(2, k);
									}
								}
								salidaBitIdx += 24;
								rgb = rgb ^ aXorear;
								rgb2 = rgb ^ aXorear;
							}
							bufferedImage3.setRGB(i, j, rgb);
							bufferedImage4.setRGB(i, j, rgb2);
						}
					}
					ImageIcon icon2 = new ImageIcon(bufferedImage2);
					ImageIcon icon3 = new ImageIcon(bufferedImage3);
					ImageIcon icon4 = new ImageIcon(bufferedImage4);
					JLabel label2 = new JLabel(icon2);
					JLabel label3 = new JLabel(icon3);
					JLabel label4 = new JLabel(icon4);
					final JPanel panel = new JPanel();
					panel.add(label2);
					panel.add(label3);
					panel.add(label4);

					File outputfile1 = new File("test_imagen_1.jpg");
					File outputfile2 = new File("test_imagen_2.jpg");
					File outputfile3 = new File("test_imagen_3.jpg");
					ImageIO.write(bufferedImage2, "jpg", outputfile1);
					ImageIO.write(bufferedImage3, "jpg", outputfile2);
					ImageIO.write(bufferedImage4, "jpg", outputfile3);

					JOptionPane.showMessageDialog(_frame, panel,
							"PRUEBA DE IMAGEN", JOptionPane.PLAIN_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(_frame,
							"ERROR: " + e.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnBM = new JButton("Berlekamp-Massey");
		btnBM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtSalidaArchivo.getText().equals("")) {
					JOptionPane.showMessageDialog(_frame,
							"Debe especificarse el archivo.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					/*
					 * Process process = new ProcessBuilder(
					 * "C:\\tests\\test_bm.exe", txtSalidaArchivo
					 * .getText()).start(); InputStream is =
					 * process.getInputStream(); InputStreamReader isr = new
					 * InputStreamReader(is); BufferedReader br = new
					 * BufferedReader(isr); String salidaExe = ""; String linea;
					 * while ((linea = br.readLine()) != null) { salidaExe +=
					 * linea + "\n"; } JOptionPane.showMessageDialog(_frame,
					 * salidaExe, "Berlekamp-Massey",
					 * JOptionPane.PLAIN_MESSAGE);
					 */

					if (BITSTREAM_DIRTY) {
						BITSTREAM = new Scanner(new File(txtSalidaArchivo
								.getText())).useDelimiter("\\Z").next();
						BITSTREAM_DIRTY = false;
					}
					JOptionPane.showMessageDialog(_frame,
							NLFSR.sBerlekampMassey(BITSTREAM),
							"Berlekamp-Massey", JOptionPane.PLAIN_MESSAGE);

				} catch (Exception e) {
					JOptionPane.showMessageDialog(_frame,
							"ERROR: " + e.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnColoreo = new JButton("Coloreo de rachas (muestra)");
		btnColoreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTextArea txtColoreo = new JTextArea();
				txtColoreo.setLineWrap(true);
				txtColoreo.setText(txtSalida.getText());
				ColoreoRachas.textarea(txtColoreo);
				final JPanel panel = new JPanel(new BorderLayout(5, 5));
				JPanel panel2 = new JPanel(new GridLayout(0, 1));
				JScrollPane scroll = new JScrollPane(panel2);
				scroll.setPreferredSize(new Dimension(700, 500));
				scroll.setViewportView(txtColoreo);
				panel.add(scroll, BorderLayout.CENTER);
				// xxx - resizeable
				panel.addHierarchyListener(new HierarchyListener() {
					public void hierarchyChanged(HierarchyEvent e) {
						Window window = SwingUtilities.getWindowAncestor(panel);
						if (window instanceof Dialog) {
							Dialog dialog = (Dialog) window;
							if (!dialog.isResizable()) {
								dialog.setResizable(true);
							}
						}
					}
				});
				// xxx - fin resizeable
				JOptionPane.showMessageDialog(_frame, panel,
						"Coloreo de rachas", JOptionPane.PLAIN_MESSAGE);
			}
		});

		JSeparator separator_7 = new JSeparator();
		GridBagConstraints gbc_separator_7 = new GridBagConstraints();
		gbc_separator_7.gridwidth = 5;
		gbc_separator_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_7.insets = new Insets(0, 0, 5, 0);
		gbc_separator_7.gridx = 0;
		gbc_separator_7.gridy = 16;
		contentPane.add(separator_7, gbc_separator_7);
		GridBagConstraints gbc_btnColoreo = new GridBagConstraints();
		gbc_btnColoreo.insets = new Insets(0, 0, 5, 5);
		gbc_btnColoreo.gridx = 0;
		gbc_btnColoreo.gridy = 17;
		contentPane.add(btnColoreo, gbc_btnColoreo);
		GridBagConstraints gbc_btnBM = new GridBagConstraints();
		gbc_btnBM.insets = new Insets(0, 0, 5, 5);
		gbc_btnBM.gridx = 1;
		gbc_btnBM.gridy = 17;
		contentPane.add(btnBM, gbc_btnBM);

		JButton btnBMdB = new JButton("Berlekamp-Massey+DeBruijn");
		btnBMdB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtSalidaArchivo.getText().equals("")) {
					JOptionPane.showMessageDialog(_frame,
							"Debe especificarse el archivo.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					if (BITSTREAM_DIRTY) {
						BITSTREAM = new Scanner(new File(txtSalidaArchivo
								.getText())).useDelimiter("\\Z").next();
						BITSTREAM_DIRTY = false;
					}
					JOptionPane.showMessageDialog(_frame,
							NLFSR.sBerlekampMasseyDeBruijn(BITSTREAM),
							"Berlekamp-Massey+DeBruijn",
							JOptionPane.PLAIN_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(_frame,
							"ERROR: " + e.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		GridBagConstraints gbc_btnBMdB = new GridBagConstraints();
		gbc_btnBMdB.insets = new Insets(0, 0, 5, 5);
		gbc_btnBMdB.gridx = 2;
		gbc_btnBMdB.gridy = 17;
		contentPane.add(btnBMdB, gbc_btnBMdB);

		txtImg = new JTextField();
		txtImg.setText("http://www.ams.org/featurecolumn/images/april2006/internet12.jpg");
		txtImg.setColumns(10);
		GridBagConstraints gbc_txtImg = new GridBagConstraints();
		gbc_txtImg.insets = new Insets(0, 0, 5, 5);
		gbc_txtImg.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtImg.gridx = 3;
		gbc_txtImg.gridy = 17;
		contentPane.add(txtImg, gbc_txtImg);
		GridBagConstraints gbc_btnImg = new GridBagConstraints();
		gbc_btnImg.insets = new Insets(0, 0, 5, 0);
		gbc_btnImg.gridx = 4;
		gbc_btnImg.gridy = 17;
		contentPane.add(btnImg, gbc_btnImg);

		JSeparator separator_8 = new JSeparator();
		GridBagConstraints gbc_separator_8 = new GridBagConstraints();
		gbc_separator_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_8.gridwidth = 5;
		gbc_separator_8.insets = new Insets(0, 0, 5, 0);
		gbc_separator_8.gridx = 0;
		gbc_separator_8.gridy = 18;
		contentPane.add(separator_8, gbc_separator_8);

		JLabel lblTestTit = new JLabel("Tests varios (Menezez 5.4)");
		GridBagConstraints gbc_lblTestTit = new GridBagConstraints();
		gbc_lblTestTit.gridwidth = 2;
		gbc_lblTestTit.insets = new Insets(0, 0, 5, 5);
		gbc_lblTestTit.gridx = 0;
		gbc_lblTestTit.gridy = 19;
		contentPane.add(lblTestTit, gbc_lblTestTit);

		JButton btnTest1 = new JButton("Test #1 (Frequency test)");
		btnTest1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtSalidaArchivo.getText().equals("")) {
					JOptionPane.showMessageDialog(_frame,
							"Debe especificarse el archivo.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					Process process = new ProcessBuilder(
							"C:\\tests\\test1.exe", txtSalidaArchivo.getText())
							.start();
					InputStream is = process.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String salidaExe = "";
					String linea;
					while ((linea = br.readLine()) != null) {
						salidaExe += linea + "\n";
					}
					JOptionPane.showMessageDialog(_frame, salidaExe,
							"TEST # 1", JOptionPane.PLAIN_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(_frame,
							"ERROR: " + e.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnTest1 = new GridBagConstraints();
		gbc_btnTest1.anchor = GridBagConstraints.WEST;
		gbc_btnTest1.insets = new Insets(0, 0, 5, 5);
		gbc_btnTest1.gridx = 2;
		gbc_btnTest1.gridy = 19;
		contentPane.add(btnTest1, gbc_btnTest1);

		JButton btnTest2 = new JButton("Test #2 (Serial test)");
		btnTest2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtSalidaArchivo.getText().equals("")) {
					JOptionPane.showMessageDialog(_frame,
							"Debe especificarse el archivo.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					Process process = new ProcessBuilder(
							"C:\\tests\\test2.exe", txtSalidaArchivo.getText())
							.start();
					InputStream is = process.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String salidaExe = "";
					String linea;
					while ((linea = br.readLine()) != null) {
						salidaExe += linea + "\n";
					}
					JOptionPane.showMessageDialog(_frame, salidaExe,
							"TEST # 2", JOptionPane.PLAIN_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(_frame,
							"ERROR: " + e.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnTest2 = new GridBagConstraints();
		gbc_btnTest2.anchor = GridBagConstraints.WEST;
		gbc_btnTest2.insets = new Insets(0, 0, 5, 5);
		gbc_btnTest2.gridx = 3;
		gbc_btnTest2.gridy = 19;
		contentPane.add(btnTest2, gbc_btnTest2);

		JLabel lblHistorial = new JLabel("HISTORIAL (archivo, t, x)");
		GridBagConstraints gbc_lblHistorial = new GridBagConstraints();
		gbc_lblHistorial.anchor = GridBagConstraints.EAST;
		gbc_lblHistorial.insets = new Insets(0, 0, 5, 0);
		gbc_lblHistorial.gridx = 4;
		gbc_lblHistorial.gridy = 19;
		contentPane.add(lblHistorial, gbc_lblHistorial);

		JLabel lblTest3 = new JLabel("Valor de m (1..10)");
		GridBagConstraints gbc_lblTest3 = new GridBagConstraints();
		gbc_lblTest3.anchor = GridBagConstraints.EAST;
		gbc_lblTest3.insets = new Insets(0, 0, 5, 5);
		gbc_lblTest3.gridx = 0;
		gbc_lblTest3.gridy = 20;
		contentPane.add(lblTest3, gbc_lblTest3);

		txtTest3 = new JTextField();
		txtTest3.setText("5");
		GridBagConstraints gbc_txtTest3 = new GridBagConstraints();
		gbc_txtTest3.anchor = GridBagConstraints.NORTH;
		gbc_txtTest3.insets = new Insets(0, 0, 5, 5);
		gbc_txtTest3.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTest3.gridx = 1;
		gbc_txtTest3.gridy = 20;
		contentPane.add(txtTest3, gbc_txtTest3);
		txtTest3.setColumns(10);

		JButton btnTest3 = new JButton("Test #3 (Poker test)");
		btnTest3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtSalidaArchivo.getText().equals("")) {
					JOptionPane.showMessageDialog(_frame,
							"Debe especificarse el archivo.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					Process process = new ProcessBuilder(
							"C:\\tests\\test3.exe", txtSalidaArchivo.getText(),
							txtTest3.getText()).start();
					InputStream is = process.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String salidaExe = "";
					String linea;
					while ((linea = br.readLine()) != null) {
						salidaExe += linea + "\n";
					}
					JOptionPane.showMessageDialog(_frame, salidaExe,
							"TEST # 3", JOptionPane.PLAIN_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(_frame,
							"ERROR: " + e.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnTest3 = new GridBagConstraints();
		gbc_btnTest3.anchor = GridBagConstraints.WEST;
		gbc_btnTest3.insets = new Insets(0, 0, 5, 5);
		gbc_btnTest3.gridx = 2;
		gbc_btnTest3.gridy = 20;
		contentPane.add(btnTest3, gbc_btnTest3);

		txtArchivoHistorial = new JTextField();
		txtArchivoHistorial.setText("C:\\historial.xls");
		GridBagConstraints gbc_txtArchivoHistorial = new GridBagConstraints();
		gbc_txtArchivoHistorial.anchor = GridBagConstraints.EAST;
		gbc_txtArchivoHistorial.insets = new Insets(0, 0, 5, 5);
		gbc_txtArchivoHistorial.gridx = 3;
		gbc_txtArchivoHistorial.gridy = 20;
		contentPane.add(txtArchivoHistorial, gbc_txtArchivoHistorial);
		txtArchivoHistorial.setColumns(10);

		txtHistorial = new JTextField();
		txtHistorial.setText("385");
		GridBagConstraints gbc_txtHistorial = new GridBagConstraints();
		gbc_txtHistorial.anchor = GridBagConstraints.EAST;
		gbc_txtHistorial.insets = new Insets(0, 0, 5, 0);
		gbc_txtHistorial.gridx = 4;
		gbc_txtHistorial.gridy = 20;
		contentPane.add(txtHistorial, gbc_txtHistorial);
		txtHistorial.setColumns(10);

		JLabel lblTest4 = new JLabel("Valor de k (1..100)");
		GridBagConstraints gbc_lblTest4 = new GridBagConstraints();
		gbc_lblTest4.anchor = GridBagConstraints.EAST;
		gbc_lblTest4.insets = new Insets(0, 0, 5, 5);
		gbc_lblTest4.gridx = 0;
		gbc_lblTest4.gridy = 21;
		contentPane.add(lblTest4, gbc_lblTest4);

		txtTest4 = new JTextField();
		txtTest4.setText("50");
		GridBagConstraints gbc_txtTest4 = new GridBagConstraints();
		gbc_txtTest4.insets = new Insets(0, 0, 5, 5);
		gbc_txtTest4.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTest4.gridx = 1;
		gbc_txtTest4.gridy = 21;
		contentPane.add(txtTest4, gbc_txtTest4);
		txtTest4.setColumns(10);

		JButton btnTest4 = new JButton("Test #4 (Runs test)");
		btnTest4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtSalidaArchivo.getText().equals("")) {
					JOptionPane.showMessageDialog(_frame,
							"Debe especificarse el archivo.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					Process process = new ProcessBuilder(
							"C:\\tests\\test4.exe", txtSalidaArchivo.getText(),
							txtTest4.getText()).start();
					InputStream is = process.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String salidaExe = "";
					String linea;
					while ((linea = br.readLine()) != null) {
						salidaExe += linea + "\n";
					}
					JOptionPane.showMessageDialog(_frame, salidaExe,
							"TEST # 4", JOptionPane.PLAIN_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(_frame,
							"ERROR: " + e.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnTest4 = new GridBagConstraints();
		gbc_btnTest4.anchor = GridBagConstraints.WEST;
		gbc_btnTest4.insets = new Insets(0, 0, 5, 5);
		gbc_btnTest4.gridx = 2;
		gbc_btnTest4.gridy = 21;
		contentPane.add(btnTest4, gbc_btnTest4);

		JButton btnHistorial = new JButton("Generar HISTORIAL");
		btnHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					NLFSR.HistorialExcel(txtArchivoHistorial.getText(),
							Integer.parseInt(txtTamanoRegistroA.getText()),
							Integer.parseInt(txtTamanoRegistroB.getText()),
							Integer.parseInt(txtTamanoRegistroC.getText()),
							txtFormaRetroA.getText(), txtFormaRetroB.getText(),
							txtFormaRetroC.getText(),
							txtGeneracionKey.getText(),
							txtEstadoInicialA.getText(),
							txtEstadoInicialB.getText(),
							txtEstadoInicialC.getText(),
							Integer.parseInt(txtHistorial.getText()),
							txtXHistorial.getText());
					JOptionPane.showMessageDialog(_frame,
							"Archivo generado exitosamente: "
									+ txtArchivoHistorial.getText(),
							"Historial", JOptionPane.PLAIN_MESSAGE);
				} catch (Exception fe) {
					JOptionPane.showMessageDialog(_frame,
							"ERROR: " + fe.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		txtXHistorial = new JTextField();
		txtXHistorial.setText("s0");
		GridBagConstraints gbc_txtXHistorial = new GridBagConstraints();
		gbc_txtXHistorial.anchor = GridBagConstraints.EAST;
		gbc_txtXHistorial.insets = new Insets(0, 0, 5, 5);
		gbc_txtXHistorial.gridx = 3;
		gbc_txtXHistorial.gridy = 21;
		contentPane.add(txtXHistorial, gbc_txtXHistorial);
		txtXHistorial.setColumns(10);
		GridBagConstraints gbc_btnHistorial = new GridBagConstraints();
		gbc_btnHistorial.anchor = GridBagConstraints.EAST;
		gbc_btnHistorial.insets = new Insets(0, 0, 5, 0);
		gbc_btnHistorial.gridx = 4;
		gbc_btnHistorial.gridy = 21;
		contentPane.add(btnHistorial, gbc_btnHistorial);

		JLabel lblTest5 = new JLabel("Valor de d (1..n/2)");
		GridBagConstraints gbc_lblTest5 = new GridBagConstraints();
		gbc_lblTest5.anchor = GridBagConstraints.EAST;
		gbc_lblTest5.insets = new Insets(0, 0, 5, 5);
		gbc_lblTest5.gridx = 0;
		gbc_lblTest5.gridy = 22;
		contentPane.add(lblTest5, gbc_lblTest5);

		txtTest5 = new JTextField();
		txtTest5.setText("5000");
		GridBagConstraints gbc_txtTest5 = new GridBagConstraints();
		gbc_txtTest5.insets = new Insets(0, 0, 5, 5);
		gbc_txtTest5.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTest5.gridx = 1;
		gbc_txtTest5.gridy = 22;
		contentPane.add(txtTest5, gbc_txtTest5);
		txtTest5.setColumns(10);

		JButton btnTest5 = new JButton("Test #5 (Autocor. test)");
		btnTest5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtSalidaArchivo.getText().equals("")) {
					JOptionPane.showMessageDialog(_frame,
							"Debe especificarse el archivo.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					Process process = new ProcessBuilder(
							"C:\\tests\\test5.exe", txtSalidaArchivo.getText(),
							txtTest5.getText()).start();
					InputStream is = process.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String salidaExe = "";
					String linea;
					while ((linea = br.readLine()) != null) {
						salidaExe += linea + "\n";
					}
					JOptionPane.showMessageDialog(_frame, salidaExe,
							"TEST # 5", JOptionPane.PLAIN_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(_frame,
							"ERROR: " + e.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnTest5 = new GridBagConstraints();
		gbc_btnTest5.anchor = GridBagConstraints.WEST;
		gbc_btnTest5.insets = new Insets(0, 0, 5, 5);
		gbc_btnTest5.gridx = 2;
		gbc_btnTest5.gridy = 22;
		contentPane.add(btnTest5, gbc_btnTest5);

		JButton btnJRTest = new JButton("JRandTest (C)");
		btnJRTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (txtSalidaArchivo.getText().equals("")) {
					JOptionPane.showMessageDialog(_frame,
							"Debe especificarse el archivo.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				JLabel label2 = new JLabel("Selecci�n de test:   ");
				comboJRT = new JComboBox();
				comboJRT.addItem("Runs");
				comboJRT.addItem("1 Bit");
				comboJRT.addItem("2 Bits");
				comboJRT.addItem("3 Bits");
				comboJRT.addItem("4 Bits");
				comboJRT.addItem("8 Bits");
				comboJRT.addItem("16 Bits");
				comboJRT.addItem("Monte Carlo");
				comboJRT.addItem("Squeeze");
				comboJRT.addItem("Min Distance");
				comboJRT.addItem("CountThe1s");
				comboJRT.addItem("CountThe1sSpecificBytes");
				comboJRT.addItem("BirthdaySpacings");
				comboJRT.addItem("BinaryRank6x8Matrices");
				comboJRT.addItem("BinaryRank31x31Matrices");
				comboJRT.addItem("BinaryRank32x32Matrices");
				comboJRT.addItem("Overlapping20TuplesBitstream");
				comboJRT.addItem("OverlappingPairsSparseOccupancy");
				comboJRT.addItem("OverlappingQuadruplesSparseOccupancy");
				comboJRT.addItem("DNA");
				JButton button = new JButton("Testear");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						txtSalidaJRT
								.append("\n\n===========================================================================\n\n");
						String classname = "";
						String selected = (String) comboJRT.getSelectedItem();
						if (selected.equals("Runs"))
							classname = "com.fasteasytrade.JRandTest.Tests.Run";
						else if (selected.equals("1 Bit"))
							classname = "com.fasteasytrade.JRandTest.Tests.Count1Bit";
						else if (selected.equals("2 Bits"))
							classname = "com.fasteasytrade.JRandTest.Tests.Count2Bits";
						else if (selected.equals("3 Bits"))
							classname = "com.fasteasytrade.JRandTest.Tests.Count3Bits";
						else if (selected.equals("4 Bits"))
							classname = "com.fasteasytrade.JRandTest.Tests.Count4Bits";
						else if (selected.equals("8 Bits"))
							classname = "com.fasteasytrade.JRandTest.Tests.Count8Bits";
						else if (selected.equals("16 Bits"))
							classname = "com.fasteasytrade.JRandTest.Tests.Count16Bits";
						else if (selected.equals("Monte Carlo"))
							classname = "com.fasteasytrade.JRandTest.Tests.MonteCarlo";
						else if (selected.equals("Squeeze"))
							classname = "com.fasteasytrade.JRandTest.Tests.Squeeze";
						else if (selected.equals("Min Distance"))
							classname = "com.fasteasytrade.JRandTest.Tests.MinimumDistance";
						else if (selected.equals("CountThe1s"))
							classname = "com.fasteasytrade.JRandTest.Tests.CountThe1s";
						else if (selected.equals("CountThe1sSpecificBytes"))
							classname = "com.fasteasytrade.JRandTest.Tests.CountThe1sSpecificBytes";
						else if (selected.equals("BirthdaySpacings"))
							classname = "com.fasteasytrade.JRandTest.Tests.BirthdaySpacings";
						else if (selected.equals("BinaryRank6x8Matrices"))
							classname = "com.fasteasytrade.JRandTest.Tests.BinaryRankTestFor6x8Matrices";
						else if (selected.equals("BinaryRank31x31Matrices"))
							classname = "com.fasteasytrade.JRandTest.Tests.BinaryRankTestFor31x31Matrices";
						else if (selected.equals("BinaryRank32x32Matrices"))
							classname = "com.fasteasytrade.JRandTest.Tests.BinaryRankTestFor32x32Matrices";
						else if (selected
								.equals("Overlapping20TuplesBitstream"))
							classname = "com.fasteasytrade.JRandTest.Tests.Overlapping20TuplesBitstream";
						else if (selected
								.equals("OverlappingPairsSparseOccupancy"))
							classname = "com.fasteasytrade.JRandTest.Tests.OverlappingPairsSparseOccupancy";
						else if (selected
								.equals("OverlappingQuadruplesSparseOccupancy"))
							classname = "com.fasteasytrade.JRandTest.Tests.OverlappingQuadruplesSparseOccupancy";
						else if (selected.equals("DNA"))
							classname = "com.fasteasytrade.JRandTest.Tests.DNA";

						Base ob = null;
						try {
							ob = (Base) Class.forName(classname).newInstance();
						} catch (Exception e) {
							txtSalidaJRT
									.setText("No pudo cargarse clase de test.");
							return;
						}
						try {
							ob.registerInput(new FileRandomStream(
									txtSalidaArchivo.getText()));
							ob.addOutputDestination(new JTextareaOutputDestination(
									txtSalidaJRT));
							ob.help();
							ob.test(txtSalidaArchivo.getText());
						} catch (Exception e) {
							txtSalidaJRT
									.setText("Error al ejecutar test: " + e);
							return;
						}

					}
				});
				JButton button2 = new JButton("Limpiar");
				button2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						txtSalidaJRT.setText("");
					}
				});
				txtSalidaJRT = new JTextArea();
				txtSalidaJRT.setLineWrap(true);
				txtSalidaJRT.setFont(new Font("Monospaced", Font.PLAIN, 11));
				txtSalidaJRT.setText("");
				JLabel label3 = new JLabel(
						"<html>Adaptado&nbsp;desde&nbsp;JRandTest&nbsp;package,&nbsp;o&nbsp;Java&nbsp;Random&nbsp;Test&nbsp;Suite<br />"
								+ " http://jrandtest.sourceforge.net/<br />"
								+ "Copyright (c) 2005, Zur Aougav, aougav@hotmail.com.<br />"
								+ "All rights reserved.</html>");
				final JPanel panel0 = new JPanel();
				panel0.setLayout(new BoxLayout(panel0, BoxLayout.LINE_AXIS));
				final JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
				JPanel panel2 = new JPanel(new GridLayout(0, 1));
				JScrollPane scroll = new JScrollPane(panel2);
				scroll.setPreferredSize(new Dimension(400, 300));
				scroll.setViewportView(txtSalidaJRT);
				panel0.add(label2);
				panel0.add(comboJRT);
				panel0.add(button);
				panel0.add(button2);
				panel.add(panel0);
				panel.add(scroll);
				panel.add(label3);
				// xxx - resizeable
				panel.addHierarchyListener(new HierarchyListener() {
					public void hierarchyChanged(HierarchyEvent e) {
						Window window = SwingUtilities.getWindowAncestor(panel);
						if (window instanceof Dialog) {
							Dialog dialog = (Dialog) window;
							if (!dialog.isResizable()) {
								dialog.setResizable(true);
							}
						}
					}
				});
				// xxx - fin resizeable
				JOptionPane.showOptionDialog(_frame, panel, "JRandTest (C)",
						JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null,
						new String[] { "Cerrar" }, "default");

			}
		});
		GridBagConstraints gbc_btnJRTest = new GridBagConstraints();
		gbc_btnJRTest.insets = new Insets(0, 0, 5, 0);
		gbc_btnJRTest.gridx = 4;
		gbc_btnJRTest.gridy = 22;
		contentPane.add(btnJRTest, gbc_btnJRTest);

		JLabel lblTestMaurer = new JLabel(
				"<html>Valores&nbsp;L,&nbsp;Q,&nbsp;K&nbsp;(6..16,<br />>10*2^L,&nbsp;>1000*&nbsp;2^L)</html>");
		GridBagConstraints gbc_lblTestMaurer = new GridBagConstraints();
		gbc_lblTestMaurer.anchor = GridBagConstraints.EAST;
		gbc_lblTestMaurer.insets = new Insets(0, 0, 0, 5);
		gbc_lblTestMaurer.gridx = 0;
		gbc_lblTestMaurer.gridy = 23;
		contentPane.add(lblTestMaurer, gbc_lblTestMaurer);

		txtTestMaurerL = new JTextField();
		txtTestMaurerL.setText("6");
		GridBagConstraints gbc_txtTestMaurerL = new GridBagConstraints();
		gbc_txtTestMaurerL.insets = new Insets(0, 0, 0, 5);
		gbc_txtTestMaurerL.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTestMaurerL.gridx = 1;
		gbc_txtTestMaurerL.gridy = 23;
		contentPane.add(txtTestMaurerL, gbc_txtTestMaurerL);
		txtTestMaurerL.setColumns(10);

		txtTestMaurerQ = new JTextField();
		txtTestMaurerQ.setText("640");
		GridBagConstraints gbc_txtTestMaurerQ = new GridBagConstraints();
		gbc_txtTestMaurerQ.insets = new Insets(0, 0, 0, 5);
		gbc_txtTestMaurerQ.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTestMaurerQ.gridx = 2;
		gbc_txtTestMaurerQ.gridy = 23;
		contentPane.add(txtTestMaurerQ, gbc_txtTestMaurerQ);
		txtTestMaurerQ.setColumns(10);

		txtTestMaurerK = new JTextField();
		txtTestMaurerK.setText("64000");
		txtTestMaurerK.setColumns(10);
		GridBagConstraints gbc_txtTestMaurerK = new GridBagConstraints();
		gbc_txtTestMaurerK.insets = new Insets(0, 0, 0, 5);
		gbc_txtTestMaurerK.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTestMaurerK.gridx = 3;
		gbc_txtTestMaurerK.gridy = 23;
		contentPane.add(txtTestMaurerK, gbc_txtTestMaurerK);

		JButton btnTestMaurer = new JButton("Test de Maurer");
		btnTestMaurer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtSalidaArchivo.getText().equals("")) {
					JOptionPane.showMessageDialog(_frame,
							"Debe especificarse el archivo.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					Process process = new ProcessBuilder(
							"C:\\tests\\test_maurer.exe", txtSalidaArchivo
									.getText(), txtTestMaurerL.getText(),
							txtTestMaurerQ.getText(), txtTestMaurerK.getText())
							.start();
					InputStream is = process.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String salidaExe = "";
					String linea;
					while ((linea = br.readLine()) != null) {
						salidaExe += linea + "\n";
					}
					JOptionPane.showMessageDialog(_frame, salidaExe,
							"TEST DE MAURER", JOptionPane.PLAIN_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(_frame,
							"ERROR: " + e.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnTestMaurer = new GridBagConstraints();
		gbc_btnTestMaurer.gridx = 4;
		gbc_btnTestMaurer.gridy = 23;
		contentPane.add(btnTestMaurer, gbc_btnTestMaurer);

		comboAlgo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				setAlgo(comboAlgo.getSelectedIndex());
			}
		});

		setAlgo(0);

	}

	// /////////////////////////////////////////////////////////////////////////
	public void setAlgo(int algo) {

		BITSTREAM_DIRTY = true;
		txtSalida.setText("");

		switch (comboAlgo.getSelectedIndex()) {
		case 0:

			// TRIVIUM

			lblDescarteIncial.setText("Inicializaci\u00F3n (bits)");
			txtDescarteInicial.setEnabled(true);

			lblTamanoRegistroA.setText("Tama\u00F1o del registo A (bits)");
			lblFormaRetroA.setText("F\u00F3rmula de retroalimentaci\u00F3n");
			lblEstadoInicialA
					.setText("<html>Estado inicial del registro<br /><i>( a<sub>0</sub> , a<sub>1</sub> , a<sub>2</sub> , ... , a<sub>Na-1</sub> )</i></html>");

			lblTamanoRegistroB.setText("Tama\u00F1o del registo B (bits)");
			lblFormaRetroB.setText("F\u00F3rmula de retroalimentaci\u00F3n");
			lblEstadoInicialB
					.setText("<html>Estado inicial del registro<br /><i>( b<sub>0</sub> , b<sub>1</sub> , b<sub>2</sub> , ... , b<sub>Nb-1</sub> )</i></html>");

			lblTamanoRegistroC.setText("Tama\u00F1o del registo C (bits)");
			lblFormaRetroC.setText("F\u00F3rmula de retroalimentaci\u00F3n");
			lblEstadoInicialC
					.setText("<html>Estado inicial del registro<br /><i>( c<sub>0</sub> , c<sub>1</sub> , c<sub>2</sub> , ... , c<sub>Nc-1</sub> )</i></html>");

			lblGeneracionKey
					.setText("<html>F\u00F3rmula para la generaci\u00F3n <br />del <i>key bit stream</i></html>");

			txtDescarteInicial.setText("1152");

			txtTamanoRegistroA.setText("93");
			txtFormaRetroA.setText("c65 + c110 + c109c108 + a68");
			txtEstadoInicialA
					.setText("000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");

			txtTamanoRegistroB.setText("84");
			txtFormaRetroB.setText("a65 + a92 + a91a90 + b77");
			txtEstadoInicialB
					.setText("000000000000000000000000000000000000000000000000000000000000000000000000000000000000");

			txtTamanoRegistroC.setText("111");
			txtFormaRetroC.setText("b68 + b83 + b82b81 + c86");
			txtEstadoInicialC
					.setText("000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111");

			lblTamanoRegistroA.setVisible(true);
			lblFormaRetroA.setVisible(true);
			txtTamanoRegistroA.setVisible(true);
			txtFormaRetroA.setVisible(true);

			lblTamanoRegistroB.setVisible(true);
			lblFormaRetroB.setVisible(true);
			txtTamanoRegistroB.setVisible(true);
			txtFormaRetroB.setVisible(true);

			lblTamanoRegistroC.setVisible(true);
			lblFormaRetroC.setVisible(true);
			lblEstadoInicialC.setVisible(true);
			txtTamanoRegistroC.setVisible(true);
			txtFormaRetroC.setVisible(true);
			txtEstadoInicialC.setVisible(true);
			scrollPaneEstadoIncialC.setVisible(true);
			separator_4.setVisible(true);

			lblGeneracionKey.setVisible(true);
			txtGeneracionKey.setVisible(true);

			txtGeneracionKey.setText("a65 + a92 + b68 + b83 + c65 + c110");

			break;
		case 1:

			// TRIVUM TOY

			lblDescarteIncial.setText("Inicializaci\u00F3n (bits)");
			txtDescarteInicial.setEnabled(true);

			lblTamanoRegistroA.setText("Tama\u00F1o del registo A (bits)");
			lblFormaRetroA.setText("F\u00F3rmula de retroalimentaci\u00F3n");
			lblEstadoInicialA
					.setText("<html>Estado inicial del registro<br /><i>( a<sub>0</sub> , a<sub>1</sub> , a<sub>2</sub> , ... , a<sub>Na-1</sub> )</i></html>");

			lblTamanoRegistroB.setText("Tama\u00F1o del registo B (bits)");
			lblFormaRetroB.setText("F\u00F3rmula de retroalimentaci\u00F3n");
			lblEstadoInicialB
					.setText("<html>Estado inicial del registro<br /><i>( b<sub>0</sub> , b<sub>1</sub> , b<sub>2</sub> , ... , b<sub>Nb-1</sub> )</i></html>");

			lblTamanoRegistroC.setText("Tama\u00F1o del registo C (bits)");
			lblFormaRetroC.setText("F\u00F3rmula de retroalimentaci\u00F3n");
			lblEstadoInicialC
					.setText("<html>Estado inicial del registro<br /><i>( c<sub>0</sub> , c<sub>1</sub> , c<sub>2</sub> , ... , c<sub>Nc-1</sub> )</i></html>");

			lblGeneracionKey
					.setText("<html>F\u00F3rmula para la generaci\u00F3n <br />del <i>key bit stream</i></html>");

			txtDescarteInicial.setText("384");

			txtTamanoRegistroA.setText("31");
			txtFormaRetroA.setText("c21 + c36 + c35c34 + a22");
			txtEstadoInicialA.setText("0000000000000000000000000000000");

			txtTamanoRegistroB.setText("28");
			txtFormaRetroB.setText("a21 + a30 + a29a28 + b25");
			txtEstadoInicialB.setText("0000000000000000000000000000");

			txtTamanoRegistroC.setText("37");
			txtFormaRetroC.setText("b22 + b27 + b26b25 + c28");
			txtEstadoInicialC.setText("0000000000000000000000000000000000111");

			lblTamanoRegistroA.setVisible(true);
			lblFormaRetroA.setVisible(true);
			txtTamanoRegistroA.setVisible(true);
			txtFormaRetroA.setVisible(true);

			lblTamanoRegistroB.setVisible(true);
			lblFormaRetroB.setVisible(true);
			txtTamanoRegistroB.setVisible(true);
			txtFormaRetroB.setVisible(true);

			lblTamanoRegistroC.setVisible(true);
			lblFormaRetroC.setVisible(true);
			lblEstadoInicialC.setVisible(true);
			txtTamanoRegistroC.setVisible(true);
			txtFormaRetroC.setVisible(true);
			txtEstadoInicialC.setVisible(true);
			scrollPaneEstadoIncialC.setVisible(true);
			separator_4.setVisible(true);

			lblGeneracionKey.setVisible(true);
			txtGeneracionKey.setVisible(true);

			txtGeneracionKey.setText("a21 + a30 + b22 + b27 + c21 + c36");

			break;

		case 2:

			// GRAIN

			lblDescarteIncial.setText("Inicializaci\u00F3n (bits)");
			txtDescarteInicial.setEnabled(false);

			lblTamanoRegistroA.setText("Tama\u00F1o del LFSR (s) (bits)");
			lblFormaRetroA.setText("F\u00F3rmula de retroalimentaci\u00F3n");
			lblEstadoInicialA
					.setText("<html>Estado inicial del registro<br /><i>( a<sub>0</sub> , a<sub>1</sub> , a<sub>2</sub> , ... , a<sub>Na-1</sub> )</i></html>");

			lblTamanoRegistroB.setText("Tama\u00F1o del NLFSR (b) (bits)");
			lblFormaRetroB.setText("F\u00F3rmula de retroalimentaci\u00F3n");
			lblEstadoInicialB
					.setText("<html>Estado inicial del registro<br /><i>( b<sub>0</sub> , b<sub>1</sub> , b<sub>2</sub> , ... , b<sub>Nb-1</sub> )</i></html>");

			lblTamanoRegistroC.setText("Tama\u00F1o del registo C (bits)");
			lblFormaRetroC.setText("F\u00F3rmula de retroalimentaci\u00F3n");
			lblEstadoInicialC
					.setText("<html>Estado inicial del registro<br /><i>( c<sub>0</sub> , c<sub>1</sub> , c<sub>2</sub> , ... , c<sub>Nc-1</sub> )</i></html>");

			lblGeneracionKey
					.setText("<html>F\u00F3rmula para la generaci\u00F3n <br />del <i>key bit stream</i></html>");

			txtDescarteInicial.setText("256");

			txtTamanoRegistroA.setText("128");
			txtFormaRetroA.setText("a31 + a46 + a57 + a89 + a120 + a127");
			txtEstadoInicialA
					.setText("11111111111111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");

			txtTamanoRegistroB.setText("128");
			txtFormaRetroB
					.setText("a127 + b31 + b36 + b71 + b101 + b127 + b43b59 + b60b124 + b62b66 + b68b100 + b79b87 + b109b110 + b114b116");
			// extras + b45b49b57 + b102b103b105 + b32b34b35b39
			txtEstadoInicialB
					.setText("00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");

			txtTamanoRegistroC.setText("0");
			txtFormaRetroC.setText("");
			txtEstadoInicialC.setText("");

			lblTamanoRegistroA.setVisible(true);
			lblFormaRetroA.setVisible(true);
			txtTamanoRegistroA.setVisible(true);
			txtFormaRetroA.setVisible(true);

			lblTamanoRegistroB.setVisible(true);
			lblFormaRetroB.setVisible(true);
			txtTamanoRegistroB.setVisible(true);
			txtFormaRetroB.setVisible(true);

			lblTamanoRegistroC.setVisible(false);
			lblFormaRetroC.setVisible(false);
			lblEstadoInicialC.setVisible(false);
			txtTamanoRegistroC.setVisible(false);
			txtFormaRetroC.setVisible(false);
			txtEstadoInicialC.setVisible(false);
			scrollPaneEstadoIncialC.setVisible(false);
			separator_4.setVisible(false);

			lblGeneracionKey.setVisible(true);
			txtGeneracionKey.setVisible(true);

			txtGeneracionKey
					.setText("a34 + b115a119 + a114a107 + b32a85 + a67a48 + b115b32a32 + b125 + b112 + b91 + b82 + b63 + b54 + b38");

			// https://github.com/cantora/avr-crypto-lib/blob/master/testvectors/grain-128-unverified.test-vectors
			// F09B7BF7D7F6B5C2DE2FFC73AC21397F
			// 11110000
			// 10011011
			// 01111011
			// 11110111
			// 11010111111101101011010111000010
			//
			// 00001111
			// 11011001
			// 11011110
			// 11101111
			// 1110101101101111101011010100

			break;
		case 3:

			// MICKEY

			lblDescarteIncial.setText("Inicializaci\u00F3n (bits)");
			txtDescarteInicial.setEnabled(false);

			lblEstadoInicialA
					.setText("<html><i>Feedback mask</i> para el registro R<br /><i>( m<sub>0</sub> , m<sub>1</sub> , m<sub>2</sub> , ... , m<sub>159</sub> )</i></html>");

			lblEstadoInicialB
					.setText("<html>Primer <i>FB</i> para registro S<br /><i>( fb0<sub>0</sub> , fb0<sub>1</sub> , fb0<sub>2</sub> , ... , fb0<sub>159</sub> )</i></html>");

			lblEstadoInicialC
					.setText("<html>Segundo <i>FB</i> para registro S<br /><i>( fb1<sub>0</sub> , fb1<sub>1</sub> , fb1<sub>2</sub> , ... , fb1<sub>159</sub> )</i></html>");

			txtDescarteInicial.setText("160");

			txtEstadoInicialA
					.setText("1000110010110010100010000100001010011010001100100011011111001111011001000110100111100110001110010100110001111101110111000000000111110101110010100100101011101100");

			txtEstadoInicialB
					.setText("1111010111111000001111000010001101000100110001011111010001110000100000011011001010100111011001101000100111010010001010100010101110000011110100001100011011000001");

			txtEstadoInicialC
					.setText("1101010111101110001011111101100100001001001100011001111000001110011011010001100001011001111101101110011101111110110100100011011011110111000000011110010110001000");

			txtTamanoRegistroA.setText("160");
			txtTamanoRegistroB.setText("160");
			txtTamanoRegistroC.setText("160");

			lblFormaRetroA
					.setText("<html>COMP0 <i>( COMP0<sub>0</sub> , COMP0<sub>1</sub> , ... , COMP0<sub>158</sub> )</i></html>");
			txtFormaRetroA
					.setText("011110100100111101101011101110101010101010010000011001001001111001000110000011100000000010011110100011001001101111110101111011000111110101100000011111011111000");

			lblFormaRetroB
					.setText("<html>COMP1 <i>( COMP1<sub>0</sub> , COMP1<sub>1</sub> , ... , COMP1<sub>158</sub> )</i></html>");
			txtFormaRetroB
					.setText("000011001111100010011000101111100001100100111100011011010111111100000111110000110000000000111110101000101100011100000110011001101010110111011010001011111111111");

			lblFormaRetroC.setText("<html><b>IV</b> <i>(128 bits)</i>");
			txtFormaRetroC
					.setText("00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");

			lblTamanoRegistroA.setVisible(false);
			lblFormaRetroA.setVisible(true);
			txtTamanoRegistroA.setVisible(false);
			txtFormaRetroA.setVisible(true);

			lblGeneracionKey
					.setText("<html><b>LLAVE / KEY</b> <i>(128 bits)</i>");
			txtGeneracionKey
					.setText("00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");

			lblTamanoRegistroB.setVisible(false);
			lblFormaRetroB.setVisible(true);
			txtTamanoRegistroB.setVisible(false);
			txtFormaRetroB.setVisible(true);

			lblTamanoRegistroC.setVisible(false);
			lblFormaRetroC.setVisible(true);
			lblEstadoInicialC.setVisible(true);
			txtTamanoRegistroC.setVisible(false);
			txtFormaRetroC.setVisible(true);
			txtEstadoInicialC.setVisible(true);
			scrollPaneEstadoIncialC.setVisible(true);
			separator_4.setVisible(true);

			// lblGeneracionKey.setVisible(false);
			// txtGeneracionKey.setVisible(false);

			// http://www.ecrypt.eu.org/stream/svn/viewcvs.cgi/ecrypt/trunk/submissions/mickey/v2-128/unverified.test-vectors?rev=210&view=markup
			// F0EE0A97D29BF33D2F9944F95E5583D5
			// 1111 0000 1110 1110 0000 1010 1001 0111 110100101001101111110011

			break;
		}

	}

}