package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

enum Gender {
    MALE, FEMALE
}

public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    private LocalDate joiningDate;  // Menggunakan LocalDate untuk tanggal bergabung

    private boolean isForeigner;
    private Gender gender;  // Menggunakan enum Gender

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames;
    private List<String> childIdNumbers;

    // Konstruktor
    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,
                    LocalDate joiningDate, boolean isForeigner, Gender gender) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.joiningDate = joiningDate;
        this.isForeigner = isForeigner;
        this.gender = gender;

        childNames = new LinkedList<>();
        childIdNumbers = new LinkedList<>();
    }

	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya
	 * (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3:
	 * 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */

	 private static final int GRADE_1_SALARY = 3000000;
	 private static final int GRADE_2_SALARY = 5000000;
	 private static final int GRADE_3_SALARY = 7000000;
	 private static final double FOREIGNER_MULTIPLIER = 1.5;
 
	 public void setMonthlySalary(int grade) {
		 int baseSalary = getBaseSalaryByGrade(grade);
 
		 if (isForeigner) {
			 baseSalary *= FOREIGNER_MULTIPLIER;
		 }
		 this.monthlySalary = baseSalary;
	 }
 
	 private int getBaseSalaryByGrade(int grade) {
		 switch (grade) {
			 case 1:
				 return GRADE_1_SALARY;
			 case 2:
				 return GRADE_2_SALARY;
			 case 3:
				 return GRADE_3_SALARY;
			 default:
				 throw new IllegalArgumentException("Invalid grade: " + grade);
		 }
	 }
 
	 public void setAnnualDeductible(int deductible) {
		 this.annualDeductible = deductible;
	 }
 
	 public void setAdditionalIncome(int income) {
		 this.otherMonthlyIncome = income;
	 }
 
	 public void setSpouse(String spouseName, String spouseIdNumber) {
		 this.spouseName = spouseName;
		 this.spouseIdNumber = spouseIdNumber;  // Perbaikan: menggunakan parameter dengan benar
	 }
 
	 public void addChild(String childName, String childIdNumber) {
		 childNames.add(childName);
		 childIdNumbers.add(childIdNumber);
	 }
 
	 public int getAnnualIncomeTax() {
        LocalDate now = LocalDate.now();
        int monthsWorked = (now.getYear() == joiningDate.getYear())
                ? (now.getMonthValue() - joiningDate.getMonthValue())
                : 12;

        Family family = new Family(
                spouseIdNumber.isEmpty() ? MaritalStatus.SINGLE : MaritalStatus.MARRIED,
                childIdNumbers.size()
        );

        return TaxFunction.calculateTax(
                monthlySalary,
                otherMonthlyIncome,
                monthsWorked,
                annualDeductible,
                family
        );
    }
}
