package br.icondev;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.codec.binary.Base64;

public class Tester extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtURLArquivo;
	private JTextField txtArquivoFinal;
	private JTextField txtTimeout;
	private JLabel lblV;
	private JTextField txtProxyURL;
	private JTextField txtProxyUsuario;
	private JPasswordField txtProxySenha;
	private JTextField txtProxyDomain;

	/**
	 * Launch the application.
	 * 
	 * System.setProperty("java.net.useSystemProxies", "true");
	 * 
	 * https://memorynotfound.com/configure-http-proxy-settings-java/
	 * 
	 * http://web.brunocandido.com/2013/03/25/utilizando-autenticacao-ntlm-com-proxy-no-apache-httpcomponents/
	 */
	public static void main(String[] args) {
		
		//java -Djava.net.useSystemProxies=true ...
		System.setProperty("java.net.useSystemProxies", "true");		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tester frame = new Tester();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tester() {
		setType(Type.POPUP);
		setResizable(false);
		setTitle("Teste de acesso externo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtURLArquivo = new JTextField();
		txtURLArquivo.setText("http://sicad.iconeagenciadigital.com.br/admin/customers/customers/download/?file=YToyOntzOjU6ImZpbGUxIjtzOjc4OiJmaWxlOi8vL2RhdGEvdXNlci8wL2JyLWNvbS1pY29uZWFnZW5jaWFkaWdpdGFsLXNpY2FkL2ZpbGVzL3NpY2FkXzRqOHphaGR0cy5qcGciO3M6NToiZmlsZTIiO3M6Nzg6ImZpbGU6Ly8vZGF0YS91c2VyLzAvYnItY29tLWljb25lYWdlbmNpYWRpZ2l0YWwtc2ljYWQvZmlsZXMvc2ljYWRfY2V5azB6ajFvLmpwZyI7fQ==&type=PDF&size=1&color=color&name=identificacao");
		txtURLArquivo.setBounds(10, 31, 424, 20);
		contentPane.add(txtURLArquivo);
		txtURLArquivo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("URL do arquivo");
		lblNewLabel.setBounds(10, 11, 135, 14);
		contentPane.add(lblNewLabel);
		
		txtArquivoFinal = new JTextField();
		txtArquivoFinal.setText("c:/tmp/arquivo_final.pdf");
		txtArquivoFinal.setBounds(10, 75, 335, 20);
		contentPane.add(txtArquivoFinal);
		txtArquivoFinal.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Destino com nome do arquivo final");
		lblNewLabel_1.setBounds(10, 59, 222, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnDown1 = new JButton("Download 1");
		btnDown1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tryDownload();
			}
		});
		btnDown1.setBounds(323, 145, 111, 23);
		contentPane.add(btnDown1);
		
		txtTimeout = new JTextField();
		txtTimeout.setText("3000");
		txtTimeout.setBounds(348, 75, 86, 20);
		contentPane.add(txtTimeout);
		txtTimeout.setColumns(10);
		
		JLabel lblTimeout = new JLabel("Timeout");
		lblTimeout.setBounds(348, 59, 46, 14);
		contentPane.add(lblTimeout);
		
		lblV = new JLabel("V.5");
		lblV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblV.setBounds(10, 145, 46, 23);
		contentPane.add(lblV);
		
		txtProxyURL = new JTextField();
		txtProxyURL.setText("http://wpad.sicredi.net/wpad.dat");
		txtProxyURL.setBounds(10, 114, 177, 20);
		contentPane.add(txtProxyURL);
		txtProxyURL.setColumns(10);
		
		txtProxyUsuario = new JTextField();
		txtProxyUsuario.setText("diego_moreira");
		txtProxyUsuario.setBounds(259, 114, 86, 20);
		contentPane.add(txtProxyUsuario);
		txtProxyUsuario.setColumns(10);
		
		txtProxySenha = new JPasswordField();
		txtProxySenha.setBounds(348, 114, 86, 20);
		contentPane.add(txtProxySenha);
		
		JLabel lblProxyUrl = new JLabel("Proxy URL");
		lblProxyUrl.setBounds(10, 99, 86, 14);
		contentPane.add(lblProxyUrl);
		
		JLabel lblUsurio = new JLabel("Usuário");
		lblUsurio.setBounds(259, 99, 56, 14);
		contentPane.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.RED);
		lblSenha.setBounds(348, 99, 46, 14);
		contentPane.add(lblSenha);
		
		txtProxyDomain = new JTextField();
		txtProxyDomain.setText("Sicredi");
		txtProxyDomain.setBounds(192, 114, 64, 20);
		contentPane.add(txtProxyDomain);
		txtProxyDomain.setColumns(10);
		
		JLabel lblDomain = new JLabel("Domain");
		lblDomain.setBounds(192, 99, 46, 14);
		contentPane.add(lblDomain);
	}
	
	protected void tryDownload() {
		try {
			System.out.println("Iniciando a validação... " + lblV.getText());
			
			if (txtProxySenha.getPassword().length==0){
				JOptionPane.showMessageDialog(this, "Informe a senha do proxy!");
				txtProxySenha.requestFocus();
				return;
			}
			
			System.out.println("Apagando aquivo caso exista...");
			new File(txtArquivoFinal.getText()).delete();
			
			System.out.println("Iniciando setando o proxy host");
			System.setProperty("http.proxyHost", txtProxyURL.getText());
			System.setProperty("http.auth.ntlm.domain", txtProxyDomain.getText());
			//System.setProperty("http.proxyPort",port);
			
			System.out.println("Criando e setando o Authenticator...");
			URL server = new URL(txtURLArquivo.getText());
			Authenticator.setDefault(new Authenticator() {
				 public PasswordAuthentication getPasswordAuthentication() {
			            return (new PasswordAuthentication(txtProxyUsuario.getText(), txtProxySenha.getPassword()));
			        }
			});

			System.out.println("Abrindo a conexão...");
			URLConnection connection = (URLConnection)server.openConnection();
			
			String auth = new String(Base64.encodeBase64(new String(txtProxyUsuario.getText()+":"+txtProxySenha.getPassword()).getBytes()));
			auth = "Basic " + auth;
			connection.setRequestProperty("Proxy-Connection","Keep-Alive");
			connection.setRequestProperty("Proxy-Authorization",auth);
			
			
			connection.setConnectTimeout(Integer.valueOf(txtTimeout.getText()));
			System.out.println("Conectando...");
			connection.connect();
			
			System.out.println("connection.getInputStream...");
			InputStream is = connection.getInputStream();		
			
			// opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(txtArquivoFinal.getText());
 
            System.out.println("Escrevendo o arquivo...");
            int bytesRead = -1;
            byte[] buffer = new byte[4096];
            while ((bytesRead = is.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
 
            System.out.println("Fechando tudo...");
            outputStream.close();
            is.close();			

            if (new File(txtArquivoFinal.getText()).exists())
            	JOptionPane.showMessageDialog(this, 
            			"Aparente sucesso ao salvar arquivo.Tente abrir o arquivo em:\n\n"+
            					txtArquivoFinal.getText() +
            					"\n\nE verifique se não esta corrompido!"            			
            			, "Sucesso!!", JOptionPane.INFORMATION_MESSAGE);
            else
            	JOptionPane.showMessageDialog(this, "Não encontrei o arquivo final...", "Ops!!", JOptionPane.WARNING_MESSAGE);
			
			System.out.println("Fim de código!");
		} catch (Exception e) {
			System.out.println("Ocorreu um erro na versão " + lblV.getText());
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro ao salvar arquivo!", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
