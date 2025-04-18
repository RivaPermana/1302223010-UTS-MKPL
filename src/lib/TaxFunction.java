package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
	private static final int NON_TAXABLE_BASE = 54000000;
    private static final int NON_TAXABLE_MARRIED = 4500000;
    private static final int NON_TAXABLE_PER_CHILD = 1500000;

    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int monthsWorked, int deductible, Family family) {
        if (monthsWorked > 12) {
            System.err.println("Jumlah bulan bekerja tidak boleh lebih dari 12");
        }

        int yearlyIncome = (monthlySalary + otherMonthlyIncome) * monthsWorked;
        int nonTaxableIncome = calculateNonTaxableIncome(family);
        int taxableIncome = yearlyIncome - deductible - nonTaxableIncome;

        return calculateFinalTax(taxableIncome);
    }

    private static int calculateNonTaxableIncome(Family family) {
        int income = NON_TAXABLE_BASE;
        if (family.isMarried()) {
            income += NON_TAXABLE_MARRIED + (family.getNumberOfChildren() * NON_TAXABLE_PER_CHILD);
        }
        return income;
    }

    private static int calculateFinalTax(int taxableIncome) {
        return (int) Math.round(0.05 * taxableIncome);
    }
	
}
