package CalendeR22;


import java.time.LocalDate;

public class F_Event_GUI{
    private int incomeTemp, exposeTemp;
    private int temp1, temp2;
    private int incomeAmount;
    private int exposeAmount;
    private String localDate;
    private LocalDate date;
    private int day;
    private int income;
    private int expose;

    public F_Event_GUI(LocalDate date, int incomeAmount, int exposeAmount, int incomeTemp, int exposeTemp) {
        this.date = date;
        this.incomeAmount = incomeAmount;
        this.exposeAmount = exposeAmount;
        this.incomeTemp = incomeTemp;
        this.exposeTemp = exposeTemp;
    }
    public int getIncomeTemp(){
        return incomeTemp;
    }
    public void setTemp1(int incomeTemp){this.incomeTemp = temp1;}
    public int getExposeTemp(){
        return exposeTemp;
    }
    public void setTemp2(int exposeTemp){this.exposeTemp = temp2;}
    public int getIncomeAmount(){
        return incomeAmount;
    }

    public int getExposeAmount(){
        return exposeAmount;
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

    public void setIncome(int income) {
        this.income = income;
    }

    public int getIncome() {
        return income;
    }

    public void setExpose(int expose) {
        this.expose = expose;
    }

    public int getExpose() {
        return expose;
    }

}
