package CalendeR22;

import java.time.LocalDate;

public class C_Event_GUI{
    private int calorieTemp;
    private int temp1;
    private int calorieAmount;
    private String localDate;
    private LocalDate date;
    private int day;
    private int calorie;

    public C_Event_GUI(LocalDate date, int calorieAmount, int calorieTemp) {
        this.date = date;
        this.calorieAmount = calorieAmount;
        this.calorieTemp = calorieTemp;
    }
    public int getCalorieTemp(){
        return calorieTemp;
    }
    public void setTemp1(int calorieTemp){this.calorieTemp = temp1;}
    public int getCalorieAmount(){
        return calorieAmount;
    }

    public String getLocalDate() {
        return localDate;
    }
    public LocalDate getDate() {
        return date;
    }

    public int getDayOfMonth() {
        return day;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public int getCalorie() {
        return calorie;
    }
}
