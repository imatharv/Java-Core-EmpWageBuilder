import java.util.*;

interface InterfaceComputeEmpWage {
	public void addCompanyEmpWage(String company, int empRatePerHours, int numOfWorkingDays, int maxHoursPerMonth);
	public void computeEmpWage();
	public int getTotalWage(String company);
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
		totalEmpWage = 0;
	}

	public void setTotalEmpWage(int totalEmpWage) {
		this.totalEmpWage = totalEmpWage;
	}

	@Override
	public String toString() {
		return "Total Employee Wage for Company:" + company + " is: " + totalEmpWage + "\n";
	}
}

public class EmpWageBuilder implements InterfaceComputeEmpWage {
	// Constants
	public static final int IS_PART_TIME = 1;
	public static final int IS_FULL_TIME = 2;

	private int numOfCompany = 0;
	private ArrayList<CompanyEmpWage> companyEmpWageArrayList;
	private Map<String, CompanyEmpWage> companyToEmpWageMap;;

	public EmpWageBuilder() {
		companyEmpWageArrayList = new ArrayList<>();
		companyToEmpWageMap = new HashMap<>();
	}

	public void addCompanyEmpWage(String company, int empRatePerHours, int numOfWorkingDays, int maxHoursPerMonth) {
		CompanyEmpWage companyEmpWage = new CompanyEmpWage(company, empRatePerHours, numOfWorkingDays,
				maxHoursPerMonth);
		companyEmpWageArrayList.add(companyEmpWage);
		companyToEmpWageMap.put(company, companyEmpWage);
	}

	public void computeEmpWage() {
		for (int i = 0; i < companyEmpWageArrayList.size(); i++) {
			CompanyEmpWage companyEmpWage = companyEmpWageArrayList.get(i);
			companyEmpWage.setTotalEmpWage(this.computeEmpWage(companyEmpWage));
			System.out.println(companyEmpWage);
		}
	}

	@Override
	public int getTotalWage(String company) {
		return companyToEmpWageMap.get(company).totalEmpWage;
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
			totalEmpHrs += empHrs;
			System.out.println("Day: " + totalWorkingDays + " ... " + "Employee Hours: " + empHrs);
		}
		return totalEmpHrs * companyEmpWage.empRatePerHours;
	}

	public static void main(String[] args) {
		EmpWageBuilder empWageBuilder = new EmpWageBuilder();
		empWageBuilder.addCompanyEmpWage("DMart", 20, 20, 100);
		empWageBuilder.addCompanyEmpWage("Walmart", 10, 4, 50);
		empWageBuilder.computeEmpWage();
		System.out.println("Total wage for Walmart company: " + empWageBuilder.getTotalWage("Walmart"));
	}
}
