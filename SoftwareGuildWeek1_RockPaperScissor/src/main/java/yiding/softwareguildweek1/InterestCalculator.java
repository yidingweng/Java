/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yiding.softwareguildweek1;

/**
 *
 * @author yidingweng
 */
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
//import yiding.softwareguildweek1.InterestCalculator.MathOperator;

public class InterestCalculator {
    public static BigDecimal annuallyInterestRate ;
    public static BigDecimal quarterlyInterestRate;
    public static BigDecimal monthlyInterestRate;
    public static BigDecimal dailyInterestRate;
    public static BigDecimal totalMoney;
    public static BigDecimal initialTotal;
    //public static int noOfYear;
    public static int interestOption;
    public static int noOfYears;
    public static BigDecimal one = new BigDecimal("1");
    public static BigDecimal hundred = new BigDecimal("100");
    //public BigDecimalMath math = new BigDecimalMath();
    
    public static void main(String[] args) {
        getUserInfo();
        BigDecimalMath math = new BigDecimalMath();
        BigDecimal four = new BigDecimal("4");
        BigDecimal twelve = new BigDecimal("12");
        BigDecimal everyDay = new BigDecimal("365");
        quarterlyInterestRate = math.calculate(MathOperator.DIVIDE,annuallyInterestRate,four);
        monthlyInterestRate = math.calculate(MathOperator.DIVIDE,annuallyInterestRate,twelve);
        dailyInterestRate = math.calculate(MathOperator.DIVIDE,annuallyInterestRate,everyDay);
        for (int i = 0; i < noOfYears; i++){
            totalMoney = calcYearSum(totalMoney);
        }
        printReport();
    }
    public static void getUserInfo() {
        getInterestRate();
        getInitialTotal();
        getYears();
        getInterestOption();
    }
    public static void getInterestRate(){
        System.out.println("Interest Rate? ");
        Scanner sc = new Scanner(System.in);
        annuallyInterestRate = new BigDecimal(sc.nextLine());
    }
    public static void getInitialTotal(){
        System.out.println("How much money? ");
        Scanner sc = new Scanner(System.in);
        totalMoney = new BigDecimal(sc.nextLine());
        initialTotal = totalMoney;
    }
    
    public static void getYears(){
        System.out.println("How many years: ");
        Scanner sc = new Scanner(System.in);
        noOfYears = sc.nextInt();
    }
    
    public static BigDecimal calcYearSum(BigDecimal currentBalance){
        //getInterestOption();
        switch(interestOption){
            case 1:
                for(int i = 0; i < 4; i++){
                currentBalance = calcQuarterSum(currentBalance);
                }
                break;
            case 2:
                for(int i = 0; i < 12; i++){
                currentBalance = calcMonthSum(currentBalance);
            }
             case 3:
                for(int i = 0; i < 365; i++){
                currentBalance = calcDaySum(currentBalance);
            }    
        }
        return currentBalance;
    }
    public static void getInterestOption() {
        System.out.println("Enter Interest Option (1 for quarerly, 2 for monthly, 3 for daily) ");
        Scanner sc = new Scanner(System.in);
        interestOption = sc.nextInt();
        //return interestOption;
    }
    public static BigDecimal calcQuarterSum(BigDecimal currentBalance){
        BigDecimalMath math = new BigDecimalMath();
        BigDecimal rate = math.calculate(MathOperator.DIVIDE,quarterlyInterestRate,hundred);
        BigDecimal increment = math.calculate(MathOperator.PLUS,one,rate);
        currentBalance = math.calculate(MathOperator.MULTIPLY,currentBalance,increment);
        return currentBalance;
    }
    public static BigDecimal calcMonthSum(BigDecimal currentBalance){
        BigDecimalMath math = new BigDecimalMath();
        BigDecimal rate = math.calculate(MathOperator.DIVIDE,monthlyInterestRate,hundred);
        BigDecimal increment = math.calculate(MathOperator.PLUS,one,rate);
        currentBalance = math.calculate(MathOperator.MULTIPLY,currentBalance,increment);
        return currentBalance;
    }
    public static BigDecimal calcDaySum(BigDecimal currentBalance){
        BigDecimalMath math = new BigDecimalMath();
        BigDecimal rate = math.calculate(MathOperator.DIVIDE,dailyInterestRate,hundred);
        BigDecimal increment = math.calculate(MathOperator.PLUS,one,rate);
        currentBalance = math.calculate(MathOperator.MULTIPLY,currentBalance,increment);
        return currentBalance;
    }
    public static void printReport() {
        BigDecimalMath math = new BigDecimalMath();
        BigDecimal difference = math.calculate(MathOperator.MINUS,totalMoney,initialTotal);
        System.out.println("Initial Total: " + initialTotal.setScale(2, RoundingMode.HALF_UP));
        System.out.println("Current Sum: " + totalMoney.setScale(2, RoundingMode.HALF_UP));
        System.out.println("Years: " + noOfYears);
        System.out.println("Total Interests: " + difference.setScale(2, RoundingMode.HALF_UP));
    }
    public BigDecimal calculate(MathOperator operator, BigDecimal operand1, BigDecimal operand2){
        switch (operator){
            case PLUS:
                return operand1.add(operand2);
            case MINUS:
                return operand1.subtract(operand2);
            case MULTIPLY:
                return operand1.multiply(operand2);
            case DIVIDE:
                return operand1.divide(operand2);
            default:
                throw new UnsupportedOperationException("Unknown Math Operator");
        }
    }
    
}

