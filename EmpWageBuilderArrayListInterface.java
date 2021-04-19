import java.util.*;

interface InterfaceComputeEmpWage {
	public void addCompanyEmpWage(String company, int empRatePerHours, int numOfWorkingDays, int maxHoursPerMonth);

	public void computeEmpWage();
}

class CompanyEmpWage {

	public final String company;
	public final int empRatePerHours;
	public final int numOfWorkingDays;
	public final int maxHoursPerMonth;
	public int totalEmpWage;

	public CompanyEmpWage(String company, int empRatePerHours, int numOfWorkingDays, int maxHoursPerMonth) {
		this.company = company;
		this.empRatePerHours = empRatePerHours;
		this.numOfWorkingDays = numOfWorkingDays;
		this.maxHoursPerMonth = maxHoursPerMonth;
	}

	public void setTotalEmpWage(int totalEmpWage) {
		this.totalEmpWage = totalEmpWage;
	}

	@Override
	public String toString() {
		return "Total Employee Wage for Company:" + company + " is: " + totalEmpWage + "\n";
	}
}

public class EmpWageBuilderArrayListInterface implements InterfaceComputeEmpWage {
	// Constants
	public static final int IS_PART_TIME = 1;
	public static final int IS_FULL_TIME = 2;

	private int numOfCompany = 0;
	private ArrayList<CompanyEmpWage> companyEmpWageArrayList;
	private ArrayList<Integer> dailyWageList;

	public EmpWageBuilderArrayListInterface() {
		companyEmpWageArrayList = new ArrayList<>();
		dailyWageList = new ArrayList<>();
	}

	public void addCompanyEmpWage(String company, int empRatePerHours, int numOfWorkingDays, int maxHoursPerMonth) {
		CompanyEmpWage companyEmpWage = new CompanyEmpWage(company, empRatePerHours, numOfWorkingDays,
				maxHoursPerMonth);
		companyEmpWageArrayList.add(companyEmpWage);
	}

	public void computeEmpWage() {
		for (int i = 0; i < companyEmpWageArrayList.size(); i++) {
			CompanyEmpWage companyEmpWage = companyEmpWageArrayList.get(i);
			companyEmpWage.setTotalEmpWage(this.computeEmpWage(companyEmpWage));
			System.out.println(companyEmpWage);
		}
	}

	public void computeDailyWage() {
		for (int j = 0; j < dailyWageList.size(); j++) {
			System.out.println("Daily wage of employee is: " + dailyWageList.get(j));
		}
	}

	private int computeEmpWage(CompanyEmpWage companyEmpWage) {
		// Variables
		int empHrs = 0, totalEmpHrs = 0, totalWorkingDays = 0;
		// Computation
		while (totalEmpHrs <= companyEmpWage.maxHoursPerMonth && totalWorkingDays < companyEmpWage.numOfWorkingDays) {
			totalWorkingDays++;
			int empCheck = (int) Math.floor(Math.random() * 10) % 3;
			switch (empCheck) {
			case IS_PART_TIME:
				empHrs = 8;
				break;
			case IS_FULL_TIME:
				empHrs = 12;
				break;
			default:
				empHrs = 0;
			}
			int dailyWage = empHrs * companyEmpWage.empRatePerHours;
			dailyWageList.add(dailyWage);
			totalEmpHrs += empHrs;
			System.out.println("Day: " + totalWorkingDays + " ... " + "Employee Hours: " + empHrs);
		}
		return totalEmpHrs * companyEmpWage.empRatePerHours;
	}

	public static void main(String[] args) {
		EmpWageBuilderArrayListInterface empWageBuilder = new EmpWageBuilderArrayListInterface();
		empWageBuilder.addCompanyEmpWage("DMart", 20, 20, 50);
		empWageBuilder.addCompanyEmpWage("Walmart", 10, 30, 50);
		empWageBuilder.computeEmpWage();
		empWageBuilder.computeDailyWage();
	}
}
