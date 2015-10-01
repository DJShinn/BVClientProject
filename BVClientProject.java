package bvclient.project;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import static java.awt.Color.black;
import static java.awt.Color.green;
import static java.awt.Color.red;
import static java.awt.Color.white;
import java.awt.event.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.text.MaskFormatter;

public class BVClientProject extends JPanel implements ActionListener{
    
    public JTextField textFName;
    public JTextField textLName;
    public JTextField textEMail;
    public JFormattedTextField textNumber;
    public JFormattedTextField textParty;
    public JFormattedTextField textSection;
    public JComboBox comboBox;
    public JTextField textNotes;
    
    private boolean DEBUG = false;
    ArrayList<Integer> currentSelected = new ArrayList();
    
    
    BVTableModel aTable = new BVTableModel(); 
    JTable table = new JTable(aTable)
    {
        //set row background color
        @Override
        public Component prepareRenderer(TableCellRenderer renderer, 
                                         int row, int column)
        {
            Component c = super.prepareRenderer(renderer, row, column);
            
            //set row text to red if time waiting has passed estimated wait time
            if (Integer.parseInt(aTable.data.get(row).getTimeWaiting()) >= 
                                 aTable.data.get(row).getWait())
            {
                c.setForeground(red);
                c.setFont(c.getFont().deriveFont(Font.BOLD));
            }
            else 
            {
                c.setForeground(black);
                c.setFont(c.getFont().deriveFont(Font.PLAIN));
            }
            //set background to green if 'displayed' checkbox is checked
            if (aTable.data.get(row).getVisible().selected)
                c.setBackground(green);
            else 
                c.setBackground(white);
            
            return c;
        }
    };
    
    public BVClientProject() {
        super(new GridLayout(1,0));      
        tableSetup(table);
        
        TableCellRenderer defaultRenderer = table.getDefaultRenderer(
                                                                  Object.class);
        TableCellRenderer CheckboxCellRenderer = new CheckboxCellRenderer(
                                                               defaultRenderer);
        
        table.setDefaultRenderer(Value.class, CheckboxCellRenderer);
        table.setDefaultEditor(Value.class, new ValueEditor());
        
        JScrollPane tablePanel = new JScrollPane(table);
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        
        container.add(setupTextboxPanel());   
        container.add(setupButtonPanel());         
        container.add(tablePanel);  
        add(container);

        ActionListener actListner = new ActionListener() 
        {
            public void actionPerformed(ActionEvent event) 
            {
                aTable.updateTime();
            }
        };
        
        Timer timer = new Timer(1000, actListner);
        timer.start();
        
        
 
    }

    
    public JPanel setupButtonPanel()
    {
        JButton addRowButton = new JButton("Add Row");
        addRowButton.setPreferredSize(new Dimension(90,35));
        
        JButton deleteRowButton = new JButton("Complete Selected (Delete Row)");
        deleteRowButton.setPreferredSize(new Dimension(225,35));
        
        addRowButton.addActionListener(this);
        deleteRowButton.addActionListener(this);
        
        JPanel panelButton = new JPanel();
        panelButton.getPreferredSize();
        panelButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelButton.add(addRowButton);
        panelButton.add(deleteRowButton);
        
        return panelButton;
    }
    
    public JPanel setupTextboxPanel()
    {
        try 
        {
        MaskFormatter phoneFormatter = new MaskFormatter("1(###) ###-####");
        phoneFormatter.setValidCharacters(
                                        "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
        
        MaskFormatter numFormatter = new MaskFormatter("##");
        NumberFormat integerNumberInstance = NumberFormat.getIntegerInstance();
        
        textFName= new JTextField(15);
        textLName = new JTextField(15);
        textNumber = new JFormattedTextField(phoneFormatter);
        textEMail = new JTextField(15);
        textParty = new JFormattedTextField(integerNumberInstance);
        textSection = new JFormattedTextField();
        comboBox = new JComboBox();
        textNotes = new JTextField(30);
        }
        catch(ParseException ex)
        {
            ex.printStackTrace();
        }
        
        JLabel lblFName = new JLabel("First Name");
        JLabel lblLName = new JLabel("Last Name");
        JLabel lblNumber = new JLabel("Phone Number");
        JLabel lblEMail = new JLabel("E-Mail");
        JLabel lblParty = new JLabel("Party Size");
        JLabel lblSection = new JLabel("Section");
        JLabel lblWait = new JLabel("Wait");
        JLabel lblNotes = new JLabel("Notes");

        comboBox.addItem(new Integer(0));
        comboBox.addItem(new Integer(1));
        comboBox.addItem(new Integer(5));
        comboBox.addItem(new Integer(10));
        comboBox.addItem(new Integer(15));
        comboBox.addItem(new Integer(20));
        comboBox.addItem(new Integer(25));
        comboBox.addItem(new Integer(30));
        comboBox.addItem(new Integer(35));
        comboBox.addItem(new Integer(40));
        comboBox.addItem(new Integer(45));
        comboBox.addItem(new Integer(50));
        comboBox.addItem(new Integer(55));
        comboBox.addItem(new Integer(60));
        
        textNumber.setPreferredSize(new Dimension(100, 20));
        textParty.setPreferredSize(new Dimension(20, 20));
        textSection.setPreferredSize(new Dimension(35, 20));
        textNumber.setFocusLostBehavior(textNumber.COMMIT);
        textSection.setFocusLostBehavior(textSection.COMMIT);
        textParty.setFocusLostBehavior(textParty.COMMIT);
  
        JPanel panelTextbox = new JPanel(new GridBagLayout());
        panelTextbox.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelTextbox.add(lblFName);
        panelTextbox.add(textFName);
        panelTextbox.add(lblLName);
        panelTextbox.add(textLName);
        panelTextbox.add(lblNumber);
        panelTextbox.add(textNumber);
        panelTextbox.add(lblEMail);
        panelTextbox.add(textEMail);
        panelTextbox.add(lblParty);
        panelTextbox.add(textParty);
        panelTextbox.add(lblSection);
        panelTextbox.add(textSection);
        panelTextbox.add(lblWait);
        panelTextbox.add(comboBox);
        panelTextbox.add(lblNotes);
        panelTextbox.add(textNotes);
        
        return panelTextbox;
    }
    
        private void tableSetup(JTable table)
    {
        DefaultTableCellRenderer renderLeft = new DefaultTableCellRenderer();
        renderLeft.setHorizontalAlignment(SwingConstants.LEFT);
        table.setPreferredScrollableViewportSize(new Dimension(700, 200));
        table.getTableHeader().setBackground(new Color(100,50,100));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().resizeAndRepaint();
        
        TableColumn column = table.getColumnModel().getColumn(9);

        table.getColumnModel().getColumn(7).setCellRenderer(renderLeft);
        table.getColumnModel().getColumn(11).setCellRenderer(
                                       table.getDefaultRenderer(Boolean.class));
        table.getColumnModel().getColumn(11).setCellEditor(
                                       table.getDefaultEditor(Boolean.class));   
        
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(125);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(12).setPreferredWidth(200);

        setUpWaitColumn(table, table.getColumnModel().getColumn(6));
        setUpPriorityColumn(table, table.getColumnModel().getColumn(9));
        
        column = null;
        
        for (int i = 0; i < 12; i++) 
        {
            column = table.getColumnModel().getColumn(i);
            column.getPreferredWidth();
        }
    }
        
    public void clearText()
    {
        textFName.setText("");
        textLName.setText("");
        textNumber.setText("");
        textEMail.setText("");
        textParty.setText("");
        textSection.setText("");
        textNotes.setText("");
        comboBox.setSelectedItem(5);
    }
    @Override
    public void actionPerformed(ActionEvent ae)
            {
                if (ae.getActionCommand().equals("Add Row"))
                {
                    if (!textFName.getText().trim().equals(""))
                    {
                        aTable.addRow(textFName.getText(), textLName.getText(), 
                                      textNumber.getText(), textEMail.getText(), 
                                      textParty.getText(), textSection.getText(), 
                                      (Integer)comboBox.getSelectedItem(), 
                                      textNotes.getText()); 
                        clearText();
                    }
                    tableSetup(table);             
                    table.repaint();
                }
                else if (ae.getActionCommand().equals(
                                              "Complete Selected (Delete Row)"))
                {
                    aTable.deleteRow();
                    tableSetup(table);
                    table.repaint();
                }    
                else if (ae.getActionCommand().equals("Display Selected"))
                {
                    table.repaint();   
                    //aTable.displayRow();
                }           
            }
    

    
    
    
   /* public void setUpSectionColumn(JTable table,
                                 TableColumn sectionColumn) {

        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Smoking");
        comboBox.addItem("Non-Smoking");
        sectionColumn.setCellEditor(new DefaultCellEditor(comboBox));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        sectionColumn.setCellRenderer(renderer);
    }*/
    
        public void setUpPriorityColumn(JTable table,
                                 TableColumn priorityColumn) {
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Elevated");
        comboBox.addItem("Normal");
        priorityColumn.setCellEditor(new DefaultCellEditor(comboBox));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Set Priority");
        priorityColumn.setCellRenderer(renderer);
    }
    
        public void setUpWaitColumn(JTable table,
                                 TableColumn waitColumn) {
        //set up waits
        JComboBox comboBox = new JComboBox();
        comboBox.addItem(new Integer(5));
        comboBox.addItem(new Integer(10));
        comboBox.addItem(new Integer(15));
        comboBox.addItem(new Integer(20));
        comboBox.addItem(new Integer(25));
        comboBox.addItem(new Integer(30));
        comboBox.addItem(new Integer(35));
        comboBox.addItem(new Integer(40));
        comboBox.addItem(new Integer(45));
        comboBox.addItem(new Integer(50));
        comboBox.addItem(new Integer(55));
        comboBox.addItem(new Integer(60));
        
        waitColumn.setCellEditor(new DefaultCellEditor(comboBox));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        
        waitColumn.setCellRenderer(renderer);
    }
    
    
    private static void createAndShowGUI  ()
    {
        JFrame frame = new JFrame("Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        
        frame.getPreferredSize();
        frame.setMaximumSize(frame.getPreferredSize());

        //create and set up the content pane.
        BVClientProject newContentPane = new BVClientProject();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.add(newContentPane);

        //display the window.
        frame.pack();
        frame.setVisible(true);
    }
    

    public static void main(String[] args) 
    {

        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                createAndShowGUI();
            }
        });
    }

  
}
