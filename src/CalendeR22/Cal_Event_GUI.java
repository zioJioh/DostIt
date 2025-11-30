package CalendeR22;

import java.time.LocalDateTime;

public class Cal_Event_GUI {
    String Name;
    LocalDateTime Time;
    String Location;
    boolean Existince;
    String Description;
    public Cal_Event_GUI(String name, LocalDateTime time, String location) {
        Name = name;
        Time = time;
        Location = location;
        Existince = true;
    }

    public void setDateTime(LocalDateTime time) {

        Time = time;
    }

    public LocalDateTime getDateTime() {

        return Time;
    }

    public String getName() {

        return Name;
    }

    public void setLocation(String location){

        Location = location;
    }

    public String getLocation(){

        return Location;
    }



    public void cancelEvent(){

        Existince = false;
    }


    public void setDescription(String string){

        Description = string;
    }

    public String getDescription(){

        return Description;
    }



}