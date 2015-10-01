package bvclient.project;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.text.JTextComponent;

class data
{
    String name;
    String number;
}
public class BVTableModel extends AbstractTableModel {
    
        private boolean isSelectAllForMouseEvent = false;
	private boolean isSelectAllForActionEvent = false;
	private boolean isSelectAllForKeyEvent = false;
        
        private SimpleDateFormat twoMin = new SimpleDateFormat("mm");
        private SimpleDateFormat oneMin = new SimpleDateFormat("m");
        
        private boolean DEBUG = false;
        String[] columnNames = {"<html>First<br>Name",
                                "<html>Last<br>Name",
                                "<html>Phone<br>Number",
                                "E-Mail",
                                "<html>Party<br>Size",
                                "Section",
                                "<html>Wait Time<br>Proposed",
                                "<html>Time<br>Waiting",
                                "<html>Time<br>Added",
                                "Priority",
                                "<html>Display<br>on TV",
                                "Completed",
                                "Notes"
                                };

        java.util.List<TableData> data = new ArrayList<TableData>();
        SimpleDateFormat df = new SimpleDateFormat("mm:ss");

        public BVTableModel()
        {
            
        }
        
        @Override
        public int getColumnCount() 
        {
            return columnNames.length;
        }
        
        @Override
        public int getRowCount() 
        {
            return data.size();
        }
        
        @Override
        public String getColumnName(int col) 
        {
            return columnNames[col];
        }
        
        @Override
        public Object getValueAt(int row, int c)
        {           
            switch (c)
            {
                case 0: return data.get(row).getName();
                case 1: return data.get(row).getLastName();
                case 2: return data.get(row).getNumber(); 
                case 3: return data.get(row).getEMail();
                case 4: return data.get(row).getParty();
                case 5: return data.get(row).getSection();
                case 6: return data.get(row).getWait();
                case 7: return data.get(row).getTimeWaiting();
                case 8: return data.get(row).getTimeAdded();
                case 9: return data.get(row).getPriority();
                //case 10: return data.timeVisible.get(row);
                case 10: return data.get(row).getVisible();
                case 11: return data.get(row).getDelete();
                case 12: return data.get(row).getNotes();
                default: return null;
            }
        }
        
        @Override
        public Class getColumnClass(int c) {
            //return getValueAt(0, c).getClass();
            
            switch (c)
            {
                case 0: return String.class;
                case 1: return String.class;
                case 2: return String.class;
                case 3: return String.class;    
                case 4: return String.class; 
                case 5: return String.class;
                case 6: return String.class;
                case 7: return Integer.class;
                case 8: return String.class;
                case 9: return String.class;
                //case 10: return String.class;
                case 10: return Value.class;
                case 11: return Boolean.TYPE;
                case 12: return String.class;
                default: return String.class;
            }
        }
        
        @Override
        public boolean isCellEditable(int row, int col) 
        {
            switch(col)
            {
                case 6: return false;
                case 7: return false;
                case 8: return false;
                default: return true;
            }
        }
        
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            for (int i = 0; i < data.size(); i++)
            {
                if (data.get(i).getVisible().selected) 
                        table.setBackground(Color.RED);
            }
            return table;
        }
        
        public void updateTime()///sets the time at each tick
        {
            Date newTime = new Date();
            Integer time;
                
            for (int i = 0; i < data.size(); i++)
            {
                time = Integer.parseInt(twoMin.format(new Date(newTime.getTime() - data.get(i).getStartTime())));
                if (time >= 10)
                {
                    data.get(i).setTimeWaiting(twoMin.format(new Date(newTime.getTime() - data.get(i).getStartTime()))); 
                }
                else if (time < 10)
                {
                    data.get(i).setTimeWaiting(oneMin.format(new Date(newTime.getTime() - data.get(i).getStartTime())));
                }
                
                if (data.get(i).getVisible().selected == true)
                {
                    String value = df.format(new Date(newTime.getTime() - data.get(i).rowList.startTime));
                    data.get(i).setVisible(value);
                }
                else if (data.get(i).getVisible().selected == false)
                    data.get(i).setVisible(new String("00:00"));
                
                fireTableRowsUpdated(i,i);
            }  
        }
        
        public void addRow(String fName, String lName, String _number, String _email, String _party, String section, Integer wait, String notes)
        {  //add a new row
            data.add(new TableData(fName, lName, _number, 
                        _email, _party, section, wait, notes));
            fireTableDataChanged();
            fireTableRowsInserted(data.size() - 1, data.size() - 1);
        }
        
        public void deleteRow() 
        {
            for (int i = data.size() - 1; i >= 0; i--)
               {
                    if (data.get(i).getDelete()) 
                    {
                        data.remove(i);
                    }                   
               } 
            fireTableDataChanged();
            fireTableRowsDeleted(data.size() - 1, data.size() - 1);       
        }

        @Override
         public void setValueAt(Object value, int row, int c) 
         {

            if (c == 0)
                data.get(row).setName(value);
            else if (c == 1)
                data.get(row).setLastName(value);
            else if (c == 2)
                data.get(row).setNumber(value);
            else if (c == 3)
                data.get(row).setEMail(value);
            else if (c == 4)
                data.get(row).setParty(value);
            else if (c == 5)
                data.get(row).setSection(value);
            else if (c == 6)
                data.get(row).setWait(value);
            else if (c == 7)               
                data.get(row).setTimeWaiting(value);                
            else if (c == 8)
                data.get(row).setTimeAdded(value);
            else if (c == 9)
                data.get(row).setPriority(value);
            else if (c == 10)
            {
                data.get(row).setVisible(value);    
                if (value.getClass() == Boolean.class)
                {
                    if ((Boolean) value)
                    {
                        AddToDatabase(data.get(row).getName(), data.get(row).getLastName());
                    }
                    else if ((Boolean) value == false)
                    {
                        RemoveFromDatabase(data.get(row).getName(), data.get(row).getLastName());
                    }
                }
            }
            else if (c == 11)
                data.get(row).setDelete(value);
            else if (c == 12)
                data.get(row).setNotes(value);

            fireTableRowsUpdated(row, data.size() - 1);
        }
         
        void AddToDatabase(String firstName, String lastName)
        {
            try 
            {
                String name = firstName + "%20" + lastName;
                String bvURL = "http://app.bannervision.tv/api/add/" + name;
                if(bvURL.contains(" "))
                    bvURL = bvURL.replace(" ","%20");
                URL myURL = new URL(bvURL);
                URLConnection myURLConnection = myURL.openConnection();
                myURLConnection.getContent();
                System.out.println(bvURL);
            } 
            catch (MalformedURLException e) 
            { 
                System.err.println("MalformedURLException: " + e);
            } 
            catch (IOException e) 
            {   
                System.err.println("IOException: " + e);
            }
        }
        
        void RemoveFromDatabase(String firstName, String lastName)
        {
            try 
            {
                String name = firstName + "%20" + lastName;
                String bvURL = "http://app.bannervision.tv/api/remove/" + name;
                if(bvURL.contains(" "))
                     bvURL = bvURL.replace(" ","%20");
                URL myURL = new URL(bvURL);
                URLConnection myURLConnection = myURL.openConnection();
                myURLConnection.getContent();
                System.out.println(bvURL);
                } 
                catch (MalformedURLException e) 
                { 
                     System.err.println("MalformedURLException: " + e);
                } 
                catch (IOException e) 
                {   
                     System.err.println("IOException: " + e);
                }            
        }
/*
        private void printDebugData() 
        {
            int numRows = getRowCount();
            int numCols = getColumnCount();
 
            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                   // System.out.print("  " + data.get(i));
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
*/
}
