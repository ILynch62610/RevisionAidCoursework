package Controller;

import Model.Configuration;
import Model.ConfigurationService;
import Views.LayoutGenerator;
import Views.SettingsView;

public class SettingsController {
    public static void saveBackground(String r, String g, String b) {
        if (r.isEmpty()){   //if r does not have a value
            r = "255";      //it will be set to 255
        }
        if (g.isEmpty()){
            g = "255";      //g represents the green value
        }
        if (b.isEmpty()) {
            b = "255";      //b represents the blue value
        }
        if (r.length() < 3) {
            if(r.length() < 2) {    //if r is one digit, it will add two zeroes in front of the digit
                r = "00" + r;       //r represents the red value
            } else {
                r = "0" + r;        //if r is two digits, it will add a zero in front of the digits
            }
        }
        if (g.length() < 3) {
            if(g.length() < 2) {
                g = "00" + g;
            } else {
                g = "0" + g;
            }
        }
        if (b.length() < 3) {
            if(b.length() < 2) {
                b = "00" + b;
            } else {
                b = "0" + b;
            }
        }
        for (Configuration c : Main.configurations) {
            if(c.getSettingName().equals("Background")) {
                String backgroundColour = r+g+b;
                c.setSettingValue(backgroundColour);
            }
        }

        saveAndClose();
    }

    public static void saveAndClose() {
        ConfigurationService.setConfigurations(Main.configurations,Main.database);
        LayoutGenerator.root.setEffect(null);
        SettingsView.settingsStage.close();
    }
    public static void saveName(String name) {
        for (Configuration c : Main.configurations) {
            if(c.getSettingName().equals("Name")) {
                c.setSettingValue(name);
            }
        }

        saveAndClose();
    }
    public static void saveTimers(Boolean learningValue, String learningTime, Boolean studyValue, String studyTime) {
        String time = "";   //creates string that will be be saved to the database with the timer information
        if (learningValue || learningTime == null) {
            if (learningTime.length() > 3) {
                time = learningTime.substring(0,3); //if the timer is longer than 3 digits, it will take the first 3 digits
            }
            if (learningTime.length() < 3) {
                if(learningTime.length() < 2) { //if the length of the timer is 1 digit, two zeroes will be added
                    time = "00" + learningTime;
                } else {
                    time = "0" + learningTime;  //if the length is 2 digits, one zero will be added
                }
            } else {
                time = learningTime;    //if the length is 3 digits, this is added to the string for the database
            }
        }
        else {  //if no value was given for the timer or it was set as off, it will be set to 0
            time = "000";
        }
        if (studyValue || studyTime == null) {
            if (studyTime.length() > 3) {   //the code is repeated to validate the study timer
                time = time + studyTime.substring(0,3);
            }
            if (studyTime.length() < 3) {
                if (studyTime.length() < 2) {
                    time = time + "00" + studyTime;
                } else {
                    time = time + "0" + studyTime;
                }
            } else {
                time = time + studyTime;
            }
        }
        else {
            time = time + "000";
        }

        for (Configuration c : Main.configurations) {
            if(c.getSettingName().equals("Timer")) {
                c.setSettingValue(time);
            }
        }

        saveAndClose();
    }
}
