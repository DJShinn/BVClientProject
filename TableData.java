package bvclient.project;
import java.text.SimpleDateFormat;
import java.util.*; 

class TableData {
    
public static class Row 
 {
            public long startTime;
            private Boolean state;

            public Row(Boolean state, Long start) {
                this.startTime = start;
                this.state = state;
            }
 }
 
    private String name;
    private String lastName;
    private String number;
    private String email;
    private String party;
    private String section;
    private Integer wait;
    private String timeAdded;
    private String timeWaiting;
    private String priority;
    //ArrayList<String>timeVisible;
    private Value visible;
    public Row rowList;
    private Boolean delete;
    private String notes;
    private Date startTime;
    private SimpleDateFormat hoursMin = new SimpleDateFormat("hh:mm");
    private SimpleDateFormat twoMin = new SimpleDateFormat("mm");
    private SimpleDateFormat oneMin = new SimpleDateFormat("m");
    public String min = " minute";
    public String minutes = " minutes";
    

    
    
           public TableData(){
               this.name = "";
               this.lastName = "";
               this.number = "";
               this.email = "";
               this.party = "";
               this.section = "";
               this.wait = 5;
               this.notes = "";
               this.timeWaiting = "00:00";
              
               twoMin.setTimeZone(TimeZone.getTimeZone("CST"));
            }
           
           //this constructor is used for adding a row
           public TableData(String fName, String lName, String _number, String _email, String _party, String _section, Integer _wait, String _notes)
           {
               String newTimeAdded = hoursMin.format(new Date());
               String newTimeWaiting = oneMin.format(new Date(new Long(0)));
               String newPriority = "Normal";
               Value newVisible = new Value(new Boolean(false), "00:00");
               Row aRow = new Row(new Boolean(false), new Long(0));
               Boolean newDelete = false;
                   
               if ("1(   )    -    ".equals(_number))
                   _number = "";
               number = _number;
               startTime = new Date();
               name = fName;
               lastName = lName;
               email = _email;
               party = _party;
               section = _section;
               wait = _wait;
               timeAdded = newTimeAdded;
               timeWaiting = newTimeWaiting;
               priority = newPriority;
               //timeVisible.add(newName);
               rowList = aRow;
               visible = newVisible;
               delete = newDelete;
               notes = _notes;
           }
           
           public void setName(Object newVar)
           {
               name = newVar.toString();
           }
           
           public void setLastName(Object newVar)
           {
               lastName = newVar.toString();
           }
           
           public void setNumber(Object newVar)
           {
               number = newVar.toString();
           }
           
           public void setEMail(Object newVar)
           {
               email = newVar.toString();
           } 
           
           public void setParty(Object newVar)
           {
               party = newVar.toString();
           }
           
           public void setSection(Object newVar)
           {
               section = newVar.toString();
           }
           
           public void setWait(Object newVar)
           {
               wait = (Integer) newVar;
           }
           
           public void setTimeAdded(Object newVar)
           {
               timeAdded = newVar.toString();
           }
           
           public void setTimeWaiting(Object newVar)
           {
               timeWaiting = newVar.toString();
           }
           
           public void setPriority(Object newVar)
           {
               priority = newVar.toString();
           }
           
           public void setVisible(Object newVar)
           {
               if (newVar.getClass() == Boolean.class)
               {
                   if ((Boolean) newVar)
                   {
                        visible = new Value(new Boolean(true), visible.value);
                        rowList = new Row((Boolean)newVar, System.currentTimeMillis());
                   
                   }
                   else if ((Boolean) newVar == false)
                   {                    
                        rowList = new Row(new Boolean(false), new Long(0));                      
                        visible = new Value((Boolean)newVar, "00:00");
                   }
               }
               else if (newVar.getClass() == String.class)
               {
                   visible = new Value(visible.selected, newVar.toString());
               }
              
               
           }
           
           public void setDelete(Object newVar)
           {
               delete = (Boolean) newVar;
           }
           
           public void setNotes(Object newVar)
           {
               notes = newVar.toString();
           }
           
           public String getName()
           {
               return name;
           }
           
           public String getLastName()
           {
               return lastName;
           }
           
           public String getNumber()
           {
               return number;
           }
           
           public String getEMail()
           {
               return email;
           }
         
           public String getParty()
           {
               return party;
           }
           
           public String getSection()
           {
               return section;
           }
           
           public int getWait()
           {
               return wait;
           }
           
           public String getTimeWaiting()
           {
               return timeWaiting;
           }
           
           public String getTimeAdded()
           {
               return timeAdded;
           }
           
           public String getPriority()
           {
               return priority;
           }
           
           public Value getVisible()
           {
               return visible;
           }
           
           public Boolean getDelete()
           {
               return delete;
           }
           public String getNotes()
           {
               return notes;
           }
           
           public Long getStartTime()
           {
               Long longDate = startTime.getTime();
               return longDate;
           }
                      
           public void addRow(String fName, String lName, String aNumber, String aEMail, String aParty, String aSection, Integer aWait, String aNotes)
           {   
               String newTimeAdded = hoursMin.format(new Date());
               String newTimeWaiting = oneMin.format(new Date(new Long(0)));
               String newPriority = "Normal";
               Value newVisible = new Value(new Boolean(false), "00:00");
               Row aRow = new Row(new Boolean(false), new Long(0));
               Boolean newDelete = false;
               
               startTime = new Date();
               name = fName;
               lastName = lName;
               
               if ("1(   )    -    ".equals(aNumber))
                   aNumber = "";
               number = aNumber;
               
               
               email = aEMail;
               party = aParty;
               section = aSection;
               wait = aWait;
               timeAdded = newTimeAdded;
               timeWaiting = newTimeWaiting;
               priority = newPriority;
               //timeVisible.add(newName);
               rowList = aRow;
               visible = newVisible;
               delete = newDelete;
               notes = aNotes;
               //System.out.print((Boolean)delete.get(delete.size()-1));
           }
           
}