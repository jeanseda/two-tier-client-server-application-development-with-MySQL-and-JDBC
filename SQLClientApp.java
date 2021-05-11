
/*  Name:  Jean Seda
 * Course: CNT 4714 
 * Spring 2021
 * Assignment title: Project3–Project Three:  Two-Tier Client-Server Application Development With MySQL andJDBC
 * Due Date: March 30, 2021 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.Box;

public class SQLClientApp extends JPanel {

   private JLabel QueryLabel, dbLabel, JdbcLabel, UrlLabel, UserLabel, PasswordLabel;
   private JTextArea TextQuery;
   private JComboBox DriverCombo;
   private JComboBox UrlCombo;
   private JButton ConnectButton, ClearCommandButton, ExecuteButton, ClearWindowButton;
   private JTextField UserTextField;
   private JPasswordField PasswordTextField;
   private JLabel StatusLabel, WindowLabel;
   private Connection connection;
   private TableModel Empty;
   private JTable resultTable;
   private ResultSetTableModel tableModel;
   private JTextArea queryArea;

   // create ResultSetTableModel and GUI
   public SQLClientApp() {

      String[] DriverList = { "com.mysql.cj.jdbc.Driver", "Helloo this is jean testing" };
      String[] URLList = { "jdbc:mysql://localhost:3306/bikedb?useTimezone=true&serverTimezone=UTC" };

      setPreferredSize(new Dimension(1000, 1000));
      setBackground(Color.PINK);

      QueryLabel = new JLabel();
      QueryLabel.setFont(new Font("Serif", Font.BOLD, 14));
      QueryLabel.setForeground(Color.BLACK);
      QueryLabel.setText("Enter An SQL Comannd");

      ClearCommandButton = new JButton("Clear SQL Command");
      ClearCommandButton.setFont(new Font("Serif", Font.BOLD, 15));
      ClearCommandButton.setBounds(470, 240, 170, 40);
      ClearCommandButton.setForeground(Color.BLACK);
      ClearCommandButton.setBackground(Color.GREEN);
      ClearCommandButton.setBorderPainted(true);
      ClearCommandButton.setOpaque(true);

      ConnectButton = new JButton("Connect to Database");
      ConnectButton.setFont(new Font("Serif", Font.BOLD, 15));
      ConnectButton.setBounds(50, 240, 170, 40);
      ConnectButton.setForeground(Color.BLACK);
      ConnectButton.setBackground(Color.GREEN);
      ConnectButton.setBorderPainted(true);
      ConnectButton.setOpaque(true);

      ExecuteButton = new JButton("Execute SQL Command");
      ExecuteButton.setFont(new Font("Serif", Font.BOLD, 15));
      ExecuteButton.setBounds(780, 240, 170, 40);
      ExecuteButton.setForeground(Color.BLACK);
      ExecuteButton.setBackground(Color.GREEN);
      ExecuteButton.setBorderPainted(true);
      ExecuteButton.setOpaque(true);

      ClearWindowButton = new JButton("Clear Result Window");
      ClearWindowButton.setFont(new Font("Serif", Font.BOLD, 15));
      ClearWindowButton.setBounds(50, 950, 168, 40);
      ClearWindowButton.setBackground(Color.GREEN);
      ClearWindowButton.setBorderPainted(true);
      ClearWindowButton.setOpaque(true);

      JdbcLabel = new JLabel(" JDBC Driver");
      JdbcLabel.setOpaque(true);
      JdbcLabel.setBackground(Color.GREEN);
      JdbcLabel.setForeground(Color.BLACK);

      UrlLabel = new JLabel(" Database URL ");
      UrlLabel.setOpaque(true);
      UrlLabel.setBackground(Color.GREEN);
      UrlLabel.setForeground(Color.BLACK);

      dbLabel = new JLabel();
      dbLabel.setFont(new Font("Serif", Font.BOLD, 15));
      dbLabel.setText("Enter Database Information");
      dbLabel.setBounds(50, 0, 300, 25);
      dbLabel.setForeground(Color.BLACK);

      UserLabel = new JLabel(" Username");
      UserLabel.setOpaque(true);
      UserLabel.setBackground(Color.GREEN);
      UserLabel.setForeground(Color.BLACK);

      PasswordLabel = new JLabel("Password");
      PasswordLabel.setOpaque(true);
      PasswordLabel.setBackground(Color.GREEN);
      PasswordLabel.setForeground(Color.BLACK);

      TextQuery = new JTextArea(5, 5);
      TextQuery.setEditable(false);
      DriverCombo = new JComboBox(DriverList);
      DriverCombo.setVisible(true);

      UrlCombo = new JComboBox(URLList);
      UrlCombo.setVisible(true);

      UserTextField = new JTextField("", 10);
      PasswordTextField = new JPasswordField("", 10);
      StatusLabel = new JLabel("                                  No Connection");
      StatusLabel.setOpaque(true);
      StatusLabel.setBackground(Color.CYAN);
      StatusLabel.setForeground(Color.BLACK);
      WindowLabel = new JLabel();
      WindowLabel.setFont(new Font("Serif", Font.BOLD, 15));
      WindowLabel.setForeground(Color.BLACK);
      WindowLabel.setText("SQL Execution Result Window");
      resultTable = new JTable();
      Empty = new DefaultTableModel();

      setLayout(null);
      final Box square = Box.createHorizontalBox();
      square.add(new JScrollPane(resultTable));
      Box sqlSquare = Box.createHorizontalBox();
      sqlSquare.add(new JScrollPane(TextQuery));

      resultTable.setEnabled(false);
      resultTable.setGridColor(Color.BLACK);
      StatusLabel.setBounds(50, 150, 350, 40);
      WindowLabel.setBounds(50, 180, 200, 220);
      PasswordLabel.setBounds(50, 120, 110, 25);
      JdbcLabel.setBounds(50, 30, 110, 25);
      UrlLabel.setBounds(50, 60, 110, 25);
      UserLabel.setBounds(50, 90, 110, 25);

      square.setBounds(50, 300, 900, 650);
      QueryLabel.setBounds(470, 0, 215, 25);
      sqlSquare.setBounds(470, 20, 500, 200);
      DriverCombo.setBounds(175, 30, 280, 20);
      UrlCombo.setBounds(175, 60, 280, 20);
      UserTextField.setBounds(175, 90, 280, 20);
      PasswordTextField.setBounds(175, 120, 280, 20);

      // add the buttons text and labels, etc
      add(dbLabel);
      add(JdbcLabel);
      add(StatusLabel);
      add(WindowLabel);
      add(ConnectButton);
      add(ClearCommandButton);
      add(ExecuteButton);
      add(ClearWindowButton);
      add(QueryLabel);
      add(sqlSquare);
      add(UrlLabel);
      add(UserLabel);
      add(PasswordLabel);
      add(DriverCombo);
      add(UrlCombo);
      add(UserTextField);
      add(PasswordTextField);
      add(square);

      // Clear Result Window

      ClearWindowButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            resultTable.setModel(Empty);
         }
      });

      // Clear Query Button

      ClearCommandButton.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            TextQuery.setText("");
         }

      });

      // action Listeners
      // creating the connect button
      ConnectButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent event) {
            try {
               if (connection != null)
                  connection.close();

               StatusLabel.setText("No Connection ");

               // load the driver
               Class.forName((String) DriverCombo.getSelectedItem());

               // establish the connection
               connection = DriverManager.getConnection((String) UrlCombo.getSelectedItem(), UserTextField.getText(),
                     PasswordTextField.getText());

               if (connection != null) {
                  StatusLabel.setText("Connected to " + (String) UrlCombo.getSelectedItem());
                  TextQuery.setEditable(true);
               }
            }

            catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, ex.getMessage(), " Database error", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException classnotfound) {
               JOptionPane.showMessageDialog(SQLClientApp.this, " Driver not found");
            }

         }
      });

      // Execute button

      ExecuteButton.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            try {
               resultTable.setEnabled(true);
               resultTable.setAutoscrolls(true);
               tableModel = new ResultSetTableModel(connection, TextQuery.getText());

               if (TextQuery.getText().toUpperCase().contains("SELECT")) {
                  tableModel.setQuery(TextQuery.getText());
                  resultTable.setModel(tableModel);
               } else {
                  tableModel.setUpdate(TextQuery.getText());
               }

            } catch (SQLException ex) {

               JOptionPane.showMessageDialog(null, ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);

            } catch (ClassNotFoundException ClassNotFound) {
               JOptionPane.showMessageDialog(null, "My SQL Driver Not Found", " Driver not found",
                     JOptionPane.ERROR_MESSAGE);
            }
         }

      });

   }

   // execute application
   public static void main(String args[]) {

      JFrame frame = new JFrame("SQL Client App");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      frame.getContentPane().add(new SQLClientApp());
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setBackground(Color.GREEN);
      frame.setVisible(true);
   }
}
